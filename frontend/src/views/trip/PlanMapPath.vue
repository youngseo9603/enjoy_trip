<template>
	<div class="grid grid-cols-4 gap-4 back items-center">
		<div class="col-span-3">
			<h3>{{ $route.params.index }}일째 여행지</h3>
			<draggable
				class="list-group"
				:list="dayList"
				group="people"
				@change="log"
				itemKey="name"
			>
				<template #item="{ element }">
					<div class="list-group-item">{{ element.placeName }}</div>
				</template>
			</draggable>
		</div>
		<div class="col-span-1">
			<h3>즐겨찾기한 여행지</h3>
			<draggable
				class="list-group"
				:list="wishlist"
				group="people"
				@change="log"
				itemKey="name"
			>
				<template #item="{ element }">
					<div class="list-group-item">
						{{ element.placeName }}
					</div>
				</template>
			</draggable>
		</div>

		<router-link to="/plan">
			<button
				type="submit"
				class="mt-1 w-full border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
			>
				다음
			</button>
		</router-link>
		<button @click="startFunc">최적 경로 찾기</button>
	</div>
	<div>
		<div id="map"></div>
	</div>
</template>

<script>
// import { getWishlist } from '../../api/wishlist';
import wishAPI from '@/api/wish';
import { useRoute, useRouter } from 'vue-router';
import store from '@/stores/index';
// const fetchWishs = () => {
// 	wishs.value = getWishlist();
// };
// fetchWishs();
import draggable from 'vuedraggable';
import { toRaw, ref, handleError } from 'vue';
export default {
	name: 'KakaoMap',
	components: {
		draggable,
	},
	data() {
		return {
			reqPositions: [
				// [33.450705, 126.570677], //시작 지점이 주어져야 됨
				// [33.450936, 126.569477],
				// [33.450879, 126.56994],
			],
			reqData: [
				// { title: '카카오' }, { title: '생태연못' }, { title: '텃밭' }
			],
			markers: [],
			geocoder: null,
			posSize: null,
			graph: null,
			dp: null,
			trackArr: null,
			path: null,
			wishlist: store.state.planList.wishList,
			dayList: null,
			// days: Array.from({ length: days }, (_, index) => ({
			// 	// 생성자로 초기값 설정
			// 	place_name: `Place ${index + 1}`,
			// })),
			index: 0,
			linePath: null,
		};
	},
	mounted() {
		if (window.kakao && window.kakao.maps) {
			this.initMap();
		} else {
			const script = document.createElement('script');
			/* global kakao */
			script.onload = () => kakao.maps.load(this.initMap);
			script.src =
				'//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7009d1e5a2c7dab12dc8600689b2c884&libraries=services';
			document.head.appendChild(script);
		}
	},
	created() {
		this.index = this.$route.params.index;
		this.dayList = store.state.planList.daysList[this.index - 1];

		console.log('dayList    ', this.dayList[0]);
		for (var i = 0; i < this.dayList.length; i++) {
			this.reqPositions.push([
				this.dayList[i].address.longitude,
				this.dayList[i].address.latitude,
			]);
			this.reqData.push({ title: this.dayList[i].placeName });
		}
	},
	methods: {
		log: function (evt) {
			window.console.log(evt);

			this.reqPositions = [];
			for (var i = 0; i < this.dayList.length; i++) {
				this.reqPositions.push([
					this.dayList[i].address.longitude,
					this.dayList[i].address.latitude,
				]);
				this.reqData.push({ title: this.dayList[i].placeName });
			}

			console.log('req   ', this.reqPositions);
			this.linePath.setMap(null);
			this.relocationMarker('black');
		},
		initMap() {
			const container = document.getElementById('map');
			const options = {
				center: new kakao.maps.LatLng(33.450701, 126.570667),
				level: 5,
			};

			//지도 객체를 등록합니다.
			//지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
			this.map = new kakao.maps.Map(container, options);
			this.geocoder = new kakao.maps.services.Geocoder();

			this.displayMarker(this.reqPositions);

			this.path = new Array();
			for (var i = 0; i < this.reqPositions.length; i++) {
				this.path.push(i);
			}
			this.relocationMarker('black');
			//	this.startFunc();
		},
		changeSize(size) {
			const container = document.getElementById('map');
			container.style.width = `${size}px`;
			container.style.height = `${size}px`;
			toRaw(this.map).relayout();
		},
		displayMarker(datas) {
			if (this.markers.length > 0) {
				this.markers.forEach(marker => marker.setMap(null));
			}

			const positions = [];
			for (var i = 0; i < datas.length; i++) {
				positions.push(new kakao.maps.LatLng(datas[i][0], datas[i][1]));
			}

			if (positions.length > 0) {
				for (var i = 0; i < positions.length; i++) {
					var marker = new kakao.maps.Marker({
						map: toRaw(this.map),
						position: positions[i],
					});
					console.log(this.reqData[i].title);

					this.makeInfoWindow(this.map, marker, this.reqData[i].title);
				}

				const bounds = positions.reduce(
					(bounds, latlng) => bounds.extend(latlng),
					new kakao.maps.LatLngBounds(),
				);

				toRaw(this.map).setBounds(bounds);
			}
		},
		makeInfoWindow(map, marker, title) {
			var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
			infowindow.setContent('<div>' + title + '</div>');
			infowindow.setPosition(marker.getPosition());
			infowindow.open(map, marker);
		},
		startFunc() {
			this.linePath.setMap(null);
			this.posSize = this.reqPositions.length;
			this.graph = new Array(this.posSize);
			this.dp = new Array(this.posSize);
			this.trackArr = new Array(this.posSize);
			this.path = new Array();

			console.log(this.reqPositions);

			for (var i = 0; i < this.posSize; i++) {
				this.graph[i] = new Array(this.posSize);
			}

			for (var i = 0; i < this.posSize; i++) {
				for (var j = 0; j < this.posSize; j++) {
					this.graph[i][j] =
						(this.reqPositions[i][0] - this.reqPositions[j][0]) *
							(this.reqPositions[i][0] - this.reqPositions[j][0]) +
						(this.reqPositions[i][1] - this.reqPositions[j][1]) *
							(this.reqPositions[i][1] - this.reqPositions[j][1]);
				}
			}

			for (var i = 0; i < this.posSize; i++) {
				this.dp[i] = new Array((1 << this.posSize) - 1);

				for (var j = 0; j < (1 << this.posSize) - 1; j++) {
					this.dp[i][j] = 11000000;
				}

				this.trackArr[i] = new Array((1 << this.posSize) - 1);
			}

			for (var i = 0; i < this.path.length; i++) {
				this.path = i;
			}

			this.tsp(0, 1);
			this.findPath();
			this.relocationMarker('red');
		},
		tsp(city, visitBitMask) {
			if (visitBitMask == (1 << this.posSize) - 1) {
				return this.graph[city][0];
			}

			if (this.dp[city][visitBitMask] != 11000000) {
				return this.dp[city][visitBitMask];
			}

			for (var i = 0; i < this.posSize; i++) {
				if ((visitBitMask & (1 << i)) == 0 && this.graph[city][i] != 0) {
					var tmp = this.tsp(i, visitBitMask | (1 << i)) + this.graph[city][i];
					if (this.dp[city][visitBitMask] > tmp) {
						this.dp[city][visitBitMask] = tmp;
						this.trackArr[city][visitBitMask] = i;
					}
				}
			}

			return this.dp[city][visitBitMask];
		},
		findPath() {
			var cur = 0;
			var bitMask = 1;
			this.path.push(cur);

			while (true) {
				var nxt = this.trackArr[cur][bitMask];
				bitMask = bitMask + (1 << nxt);
				cur = nxt;

				this.path.push(cur);

				if (bitMask == (1 << this.posSize) - 1) break;
			}

			this.path.push(cur);
		},
		relocationMarker(color) {
			var MapX = new Array();
			var MapY = new Array();

			console.log(this.path);

			for (var i = 0; i < this.reqPositions.length; i++) {
				MapX.push(this.reqPositions[this.path[i]][0]);
				MapY.push(this.reqPositions[this.path[i]][1]);
			}
			console.log(MapX);
			var polyLine = new Array();
			for (var i = 0; i < this.reqPositions.length; i++) {
				polyLine.push(new kakao.maps.LatLng(MapX[i], MapY[i]));
			}

			console.log(polyLine);
			this.linePath = new kakao.maps.Polyline({
				path: polyLine, // 선을 구성하는 좌표배열 입니다

				strokeWeight: 3, // 선의 두께 입니다

				strokeColor: color, // 선의 색깔입니다

				strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다

				strokeStyle: 'solid', // 선의 스타일입니다
			});

			this.linePath.setMap(this.map);
		},
	},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
	width: 700px;
	height: 400px;
}

.button-group {
	margin: 10px 0px;
}

button {
	margin: 0 3px;
}
</style>
