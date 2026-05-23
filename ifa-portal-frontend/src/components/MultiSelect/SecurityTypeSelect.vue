<template>
  <!-- TODO
    本マルチセレクト部品は Ph.1 開発においてどこからも使用されていない｡
    Ph.2 以降で使用する可能性があるため､ソースは削除せずに残しておく｡
    Ph.2 以降で使用する必要性が出た時点で IfaInputMultiSelece を使用した実装を行う｡
  -->
  <select
    :id="elmId"
    v-model="SecuritySelectProp.selected"
    name="iSecurityType"
    multiple="multiple"
  >
    <option
      v-for="course in SecuritySelectProp.security"
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
// eslint-disable-next-line no-unused-vars
const multiselect = require('@/assets/lib/js/jquery.multiselect')

export default {
  props: {
    listKind: {
      required: false,
      type: String,
      default: ''
    },
    idString: {
      required: false,
      type: String,
      default: ''
    }

  },
  emits: ['change-selected'],
  computed: {
    elmId() {
      return 'iSecurityType' + this.idString
    },
    SecuritySelectProp() {
      return {
        selected: ['100', '01', '02', '04', '08', '09', '10', '11', '12', '13', '14', '15', '16', '23', '25', '50', '51', '52', '98', '99', '0'],
        security: [
          {
            id: '100',
            label: '金銭(ハイブリッド口座)'
          },
          {
            id: '01',
            label: '国内株式現物'
          },
          {
            id: '02',
            label: '国内株式信用'
          },
          {
            id: '04',
            label: '国内CB'
          },
          {
            id: '08',
            label: '外国投信'
          },
          {
            id: '09',
            label: '外国投信(他)'
          },
          {
            id: '10',
            label: '先物'
          },
          {
            id: '11',
            label: 'オプション'
          },
          {
            id: '12',
            label: '国内債券'
          },
          {
            id: '13',
            label: '外国債券(円建)'
          },
          {
            id: '14',
            label: '外国債券(外貨建)'
          },
          {
            id: '15',
            label: '外国株式'
          },
          {
            id: '16',
            label: '外貨建MMF'
          },
          {
            id: '23',
            label: 'カバードワラント'
          },
          {
            id: '25',
            label: '国内債券(国債)'
          },
          {
            id: '50',
            label: 'MRF'
          },
          {
            id: '51',
            label: 'MMF'
          },
          {
            id: '52',
            label: '中国F'
          },
          {
            id: '98',
            label: '金銭(外貨)'
          },
          {
            id: '99',
            label: '金銭(円貨)'
          },
          {
            id: '0',
            label: '全商品'
          }
        ]
      }
    }
  },
  mounted() {
    // ウィジェットの設定。
    $('#' + this.elmId).multiselect(multiSelectOption)
    // this.$_logDebug('#' + this.elmId)
    // チェックボックス操作時イベントハンドラの設定。
    $('#' + this.elmId).on('change', this.changeHandler)

    // 親コンポーネントに初期表示時点のリスト設定用データを送るためイベント発火。
    this.changeHandler()
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
