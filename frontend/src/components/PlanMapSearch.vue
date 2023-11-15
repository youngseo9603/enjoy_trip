<template>
  <div>
    <form @submit.prevent="searchLoc()">
      <input v-model="searchText" placeholder="검색어를 입력하세요." />
      <button>검색</button>
    </form>
    <div id="map"></div>
  </div>
</template>

<script>
import { toRaw, ref } from 'vue'
export default {
  name: 'KakaoMap',
  data() {
    return {
      markers: [],
      selectedMarker: null,
      infowindow: null,
      searchText: ref(''),
      geocoder: null,
      rowNum: 0
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap()
    } else {
      const script = document.createElement('script')
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap)
      script.src =
        '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7009d1e5a2c7dab12dc8600689b2c884&libraries=services'
      document.head.appendChild(script)
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById('map')
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5
      }

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options)
      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 })
      this.geocoder = new kakao.maps.services.Geocoder()
    },
    changeSize(size) {
      const container = document.getElementById('map')
      container.style.width = `${size}px`
      container.style.height = `${size}px`
      toRaw(this.map).relayout()
    },
    displayMarker(datas) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null))
      }

      const positions = []
      for (var i = 0; i < datas.length; i++) {
        positions.push(new kakao.maps.LatLng(datas[i].y, datas[i].x))
      }

      if (positions.length > 0) {
        for (var i = 0; i < positions.length; i++) {
          var marker = new kakao.maps.Marker({
            map: toRaw(this.map),
            position: positions[i]
          })

          kakao.maps.event.addListener(
            marker,
            'click',
            this.makeClickListener(this.map, marker, datas[i], this.infowindow)
          )
        }

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        )

        toRaw(this.map).setBounds(bounds)
      }
    },
    makeClickListener(map, marker, data, infowindow) {
      return function () {
        infowindow.setContent(
          '<div>' +
            data.place_name +
            '</div><div>' +
            data.address_name +
            '</div>' +
            '<div>' +
            data.category_group_name +
            '</div>' +
            '<button id="addPlace">추가하기</button>' //여행 검색시 없앨 부분
        )
        infowindow.setPosition(marker.getPosition())
        infowindow.open(map, marker)
      }
    },
    searchLoc() {
      var ps = new kakao.maps.services.Places()
      ps.keywordSearch(this.searchText, (data, status, pagination) => {
        if (status === kakao.maps.services.Status.OK) {
          var bounds = new kakao.maps.LatLngBounds()

          for (var i = 0; i < data.length; i++) {
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x))
          }
          this.displayMarker(data)
        }
      })
    }
  }
}
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
