<template>
  <!-- TODO
    本マルチセレクト部品は Ph.1 開発においてどこからも使用されていない｡
    Ph.2 以降で使用する可能性があるため､ソースは削除せずに残しておく｡
    Ph.2 以降で使用する必要性が出た時点で IfaInputMultiSelece を使用した実装を行う｡
  -->
  <select
    :id="elmId"
    v-model="NoticeSelectProp.selected"
    name="iNoticeSelect"
    multiple="multiple"
  >
    <option
      v-for="notice in NoticeSelectProp.notices"
      :key="notice.id"
      :label="notice.label"
      :value="notice.id"
      v-text="notice.label"
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
      return 'iNoticeSelect' + this.idString
    },
    NoticeSelectProp() {
      return {
        selected: ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16'],
        notices: [
          {
            id: '0',
            label: '最低保証金３０万円割れ'
          },
          {
            id: '1',
            label: '維持率３１％未満'
          },
          {
            id: '2',
            label: '維持率２５％未満'
          },
          {
            id: '3',
            label: '追加保証金発生'
          },
          {
            id: '4',
            label: '決済期日'
          },
          {
            id: '5',
            label: '赤残'
          },
          {
            id: '6',
            label: '外貨預り金赤残'
          },
          {
            id: '7',
            label: '外貨最低保証金２，５００ドル割れ'
          },
          {
            id: '8',
            label: '外貨保証金維持率５１％未満'
          },
          {
            id: '9',
            label: '外貨保証金維持率３５％未満'
          },
          {
            id: '10',
            label: '投信基準価額変動'
          },
          {
            id: '11',
            label: 'ノックイン'
          },
          {
            id: '12',
            label: 'ノックアウト'
          },
          {
            id: '13',
            label: '国内債券の満期償還'
          },
          {
            id: '14',
            label: '外国債券の満期償還'
          },
          {
            id: '15',
            label: '入出金'
          },
          {
            id: '16',
            label: '入出庫'
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
