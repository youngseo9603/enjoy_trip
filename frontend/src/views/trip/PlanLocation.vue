<template>
	<div class="flex flex-col back items-center">
		<div class="flex col-span-7" style="position: static; margin-top: 2rem">
			<div class="col-span-2">
				<!-- <form @submit.prevent="searchLoc()">
					<input v-model="searchText" placeholder="검색어를 입력하세요." />
					<button>검색</button>
				</form> -->
				<form @submit.prevent="searchLoc()">
					<div class="relative flex h-10 w-full" style="width: 600px">
						<input
							class="peer h-full w-full rounded-[7px] border border-blue-gray-200 border-t-transparent bg-transparent px-3 py-2.5 pr-20 font-sans text-sm font-normal text-blue-gray-700 outline outline-0 transition-all placeholder-shown:border placeholder-shown:border-blue-gray-200 placeholder-shown:border-t-blue-gray-200 focus:border-2 focus:border-pink-500 focus:border-t-transparent focus:outline-0 disabled:border-0 disabled:bg-blue-gray-50"
							v-model="searchText"
							placeholder="검색어를 입력해주세요 "
							required
						/>
						<button
							class="!absolute z-10 select-none rounded bg-indigo-950 px-4 text-center align-middle font-sans text-xs font-bold uppercase text-white transition-all focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none peer-placeholder-shown:pointer-events-none peer-placeholder-shown:bg-blue-gray-500 peer-placeholder-shown:opacity-50 peer-placeholder-shown:shadow-none"
							style="height: 100%; right:-2px;"
							type="button"
							data-ripple-light="true"
						>
							검색
						</button>
					</div>
				</form>
				<div id="map"></div>
			</div>

			<div class="col-span-4">
				<table id="list">
					<thead>
						<th style="width: 50px">번호</th>
						<th style="width: 200px">장소</th>
						<th style="width: 200px">주소</th>
						<th style="width: 200px">번호</th>
						<th style="width: 120px">카테고리</th>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
		<div class="flex">
			<router-link to="/plan/wholeplan">
				<button
					type="submit"
					class="mt-1 w-full border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
					style="width: 100px"
				>
					다음
				</button>
			</router-link>
		</div>
	</div>
</template>

<script>
import { toRaw, ref } from 'vue';
import wishAPI from '@/api/wish';
import store from '@/stores/index';
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
				const content = document.createElement('div');
				content.classList.add('custom');

				const placeName = document.createElement('div');
				placeName.classList.add('place-name');
				placeName.innerText = data.place_name;

				const address = document.createElement('div');
				address.innerText = data.address_name;

				const category = document.createElement('div');
				category.innerText = data.category_group_name;
				const buttonbox = document.createElement('div');
				buttonbox.classList.add('button-box');
				const selectwish = document.createElement('button');
				selectwish.classList.add('select-wish');
				selectwish.innerText = '담기';
				selectwish.addEventListener('click', () => {
					//위시 리스트에 담기
					var wish = ref({});
					var address = ref({});
					address.value.addr1 = data.address_name;
					address.value.addr2 = data.road_address_name;
					address.value.latitude = data.x;
					address.value.longitude = data.y;

					wish.value.address = address;
					wish.value.placeName = data.place_name;
					wish.value.category = data.category_group_name;
					wish.value.memberIndex = store.state.account.memberIndex;

					wishAPI.addWish(wish.value, ({ data }) => {
						alert('성공적으로 위시리스트에 담았습니다.');
						console.log(data.message);
					}),
						() => {
							console.log('위시리스트 담기 실패');
						};
				});

				content.appendChild(placeName);
				content.appendChild(address);
				content.appendChild(category);
				content.appendChild(buttonbox);
				buttonbox.appendChild(selectwish);
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
.back {
	width: 100%;
	height: 600px;
}
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

.button-box {
	text-align: right;
	justify-content: flex-end;
}
.select-wish {
	border: lightgray 1px solid;
	width: 40px;
}

td {
	margin-right: 2px;
	/* white-space: nowrap; */
	/* overflow: hidden; */
	text-overflow: ellipsis;
}
</style>
