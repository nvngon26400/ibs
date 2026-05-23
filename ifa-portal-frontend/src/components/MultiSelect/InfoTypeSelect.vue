<template>
  <!-- TODO
    本マルチセレクト部品は Ph.1 開発においてどこからも使用されていない｡
    Ph.2 以降で使用する可能性があるため､ソースは削除せずに残しておく｡
    Ph.2 以降で使用する必要性が出た時点で IfaInputMultiSelece を使用した実装を行う｡
  -->
  <select
    :id="elmId"
    v-model="InfoSelectProp.selected"
    name="iInfoType"
    multiple="multiple"
  >
    <option
      v-for="course in InfoSelectProp.info"
      :key="course.id"
      :label="course.label"
      :value="course.id"
      v-text="course.label"
    ></option>
  </select>
</template>

<script>
import $ from 'jquery'
import { multiSelectOption } from './js/ifa-multi-select'
$(function() {
// eslint-disable-next-line no-unused-vars
  const multiselect = require('@/assets/lib/js/jquery.multiselect')
})

export default {
  props: {
    idString: {
      required: false,
      type: String,
      default: ''
    }

  },
  emits: ['change-selected'],
  computed: {
    elmId() {
      return 'iInfoType' + this.idString
    },
    InfoSelectProp() {
      return {
        selected: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18'],
        info: [
          {
            id: '01',
            label: '最低保証金３０万円割れ'
          },
          {
            id: '02',
            label: '維持率３１％未満'
          },
          {
            id: '03',
            label: '維持率２５％未満'
          },
          {
            id: '04',
            label: '追加保証金発生'
          },
          {
            id: '05',
            label: '決済期日'
          },
          {
            id: '06',
            label: '赤残'
          },
          {
            id: '07',
            label: '外貨預り金赤残'
          },
          {
            id: '08',
            label: '外貨最低保証金２，５００ドル割れ'
          },
          {
            id: '09',
            label: '外貨保証金維持率５１％未満'
          },
          {
            id: '10',
            label: '外貨保証金維持率３５％未満'
          },
          {
            id: '11',
            label: '外貨追加保証金発生'
          },
          {
            id: '12',
            label: '投信基準価額変動'
          },
          {
            id: '13',
            label: 'ノックイン'
          },
          {
            id: '14',
            label: 'ノックアウト'
          },
          {
            id: '15',
            label: '国内債券の満期償還'
          },
          {
            id: '16',
            label: '外国債券の満期償還'
          },
          {
            id: '17',
            label: '入出金'
          },
          {
            id: '18',
            label: '入出庫'
          }
        ]
      }
    }
  },
  mounted() {
    setTimeout(() => {
    // ウィジェットの設定。
      $('#' + this.elmId).multiselect(multiSelectOption)
      // チェックボックス操作時イベントハンドラの設定。
      $('#' + this.elmId).on('change', this.changeHandler)

      // 親コンポーネントに初期表示時点のリスト設定用データを送るためイベント発火。
      this.changeHandler()
    }, 100)
  },
  methods: {
    changeHandler() {
      triggerChangeSelectEvent(this, $('#' + this.elmId))
    }
  }
}
const triggerChangeSelectEvent = (self, element) => {
  if (element[0] && element[0].options) {
    const selectedElement = Array.from(element[0].options)
    const selectedList = []
    selectedElement.forEach((v) => {
      selectedList.push({
        id: v._value,
        isSelected: v.selected
      })
    })
    self.$emit('change-selected', selectedList)
  }
}
</script>

<style>
@import "./styles/ifa-multi-select.scss";
</style>
