<template>
	<div class="flex col-span-7" style="position: static; margin-top: 2rem">
		<div class="col-span-2">
			<form @submit.prevent="searchLoc()">
				<input v-model="searchText" placeholder="검색어를 입력하세요." />
				<button>검색</button>
			</form>
			<div id="map"></div>
		</div>

		<div class="col-span-4">
			<h1>관광지 리스트</h1>
			<table id="list">
				<thead>
					<th style="width: 50px">번호</th>
					<th style="width: 170px">장소</th>
					<th style="width: 200px">주소</th>
					<th style="width: 200px">번호</th>
					<th style="width: 120px">카테고리</th>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
	<router-link to="/plan">
		<button
			type="submit"
			class="mt-1 w-full border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
		>
			계획하기
		</button>
	</router-link>
</template>

<script>
import { toRaw, ref } from 'vue';
export default {
	name: 'KakaoMap',
	data() {
		return {
			markers: [],
			selectedMarker: null,
			infowindow: null,
			searchText: ref(''),
			geocoder: null,
			rowNum: 0,
		};
	},
	created() {
		this.removeAllRow();
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
	methods: {
		initMap() {
			const container = document.getElementById('map');
			const options = {
				center: new kakao.maps.LatLng(33.450701, 126.570667),
				level: 5,
			};

			//지도 객체를 등록합니다.
			//지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
			this.map = new kakao.maps.Map(container, options);
			this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
			this.geocoder = new kakao.maps.services.Geocoder();
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

			this.removeAllRow();

			const positions = [];
			for (var i = 0; i < datas.length; i++) {
				positions.push(new kakao.maps.LatLng(datas[i].y, datas[i].x));
			}

			if (positions.length > 0) {
				for (var i = 0; i < positions.length; i++) {
					var marker = new kakao.maps.Marker({
						map: toRaw(this.map),
						position: positions[i],
					});

					var row = this.addRow(datas[i], i);

					kakao.maps.event.addListener(
						marker,
						'click',
						this.makeClickListener(this.map, marker, datas[i], this.infowindow),
					);

					row.addEventListener(
						'click',
						this.makeClickListener(this.map, marker, datas[i], this.infowindow),
					);
				}

				const bounds = positions.reduce(
					(bounds, latlng) => bounds.extend(latlng),
					new kakao.maps.LatLngBounds(),
				);

				toRaw(this.map).setBounds(bounds);
			}
		},
		makeClickListener(map, marker, data, infowindow) {
			return function () {
				console.log(data);
				const content = document.createElement('div');
				content.classList.add('custom');

				const placeName = document.createElement('div');
				placeName.classList.add('place-name');
				placeName.innerText = data.place_name;

				const address = document.createElement('div');
				address.innerText = data.address_name;

				const category = document.createElement('div');
				category.innerText = data.category_group_name;

				content.appendChild(placeName);
				content.appendChild(address);
				content.appendChild(category);

				infowindow.setContent(content);
				infowindow.setPosition(marker.getPosition());
				infowindow.open(map, marker);

				// var customOverlay = new kakao.maps.CustomOverlay({
				// 	position: marker.getPosition(),
				// 	content: content,
				// });

				// customOverlay.setMap(map);
			};
		},
		searchLoc() {
			var ps = new kakao.maps.services.Places();
			ps.keywordSearch(this.searchText, (data, status, pagination) => {
				if (status === kakao.maps.services.Status.OK) {
					var bounds = new kakao.maps.LatLngBounds();

					for (var i = 0; i < data.length; i++) {
						bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
					}
					this.displayMarker(data);
				}
			});
		},
		addRow(data, i) {
			this.rowNum++;

			const table = document.getElementById('list');
			const newRow = table.insertRow();

			const newCell0 = newRow.insertCell(0);
			const newCell1 = newRow.insertCell(1);
			const newCell2 = newRow.insertCell(2);
			const newCell3 = newRow.insertCell(3);
			const newCell4 = newRow.insertCell(4);

			newCell0.innerText = i + 1;
			newCell1.innerText = data.place_name;
			newCell2.innerText = data.address_name;
			newCell3.innerText = data.phone;
			newCell4.innerText = data.category_group_name;

			return newRow;
		},
		removeAllRow() {
			const table = document.getElementById('list');
			for (var i = 0; i < this.rowNum; i++) {
				table.deleteRow(-1);
			}

			this.rowNum = 0;
		},
	},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#map {
	width: 600px;
	height: 400px;
	margin-right: 100px;
}

.button-group {
	margin: 10px 0px;
}

button {
	margin: 0 3px;
}

tbody:hover {
	cursor: pointer !important;
}
.custom {
	width: 250px;
	padding: 10px;
	background-color: #fff;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 15px;
	z-index: 10;
}

.place-name {
	font-weight: bold;
	border-bottom: 1px solid;
	margin-bottom: 0.5rem;
}
</style>
