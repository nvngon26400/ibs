<template>
  <!-- 検索結果 -->
  <div style="margin: 1.5rem 0 0.5rem 0;">

    <!-- 為替取引履歴 -->
    <el-card
      class="content-card"
      shadow="always"
    >
      <el-row class="form_button grid-button-area">
        <ifa-button
          v-if="showCsvOutputButton"
          text="CSV出力"
          :disabled="results.length === 0"
          @click="handleCsvOutput"
        ></ifa-button>
        <button
          id="order-cancel-button"
          type="button"
          value=""
          hidden="true"
          @click="handleOrderCancel"
        ></button>
      </el-row>
      <el-row>
        <grid-table ref="gridTable"
                    :options="pqGridOption"
                    :auto-refresh="false"
        ></grid-table>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import IfaFormatUtils from '@/utils/ifaFormatUtils'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction'
export default {
  components: { GridTable },
  props: {
    foreignCurrencyBalanceList: { type: Array, required: true },
    tradeSuspendFlag: { type: String, required: true },
    fxTradeMediateProprietyList: { type: Array, required: true },
    foreignCurrencyList: { type: Array, required: true },
    showOrderCancelButton: { type: Boolean, required: false, default: false },
    showCsvOutputButton: { type: Boolean, required: false, default: false }
  },
  emits: ['order-cancel'],
  data() {
    return {
      pqGridOption: getConvertedOption(obj),
      enableGridTable: true
    }
  },
  computed: {
    tradeTypeColor() {
      return tradeType => {
        return tradeType === '0'
          ? 'font-color__plus __bold'
          : 'font-color__minus __bold'
      }
    }
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  methods: {
    onShow() {
      this.$refs['gridTable'].refreshView()
      this.pqGridOption.dataModel.data = []
    },
    update() {
      this.$nextTick(() => {
        this.pqGridOption.selectionModel.type = 'row'
        this.pqGridOption.dataModel.data = this.foreignCurrencyBalanceList
        obj.colModel[6].hidden = !this.showOrderCancelButton
        obj.tradeSuspendFlag = this.tradeSuspendFlag
        obj.fxTradeMediateProprietyList = this.fxTradeMediateProprietyList
        obj.foreignCurrencyList = this.foreignCurrencyList
        this.$refs['gridTable'].refreshView()
      })
    },
    // 注文状況が注文中の注文を取消する
    handleOrderCancel() {
      const cancelData = JSON.parse(unescape(document.getElementById('order-cancel-button').value))
      const order = {
        tradeCd: cancelData.tradeKbn,
        currencyCode: cancelData.currencyCode,
        currency: this.foreignCurrencyList.find((fc) => fc.currencyCode === cancelData.currencyCode).currency,
        orderNumber: cancelData.orderNumber,
        cycleNumber: cancelData.cycleNumber,
        orderEventId: cancelData.orderEventId,
        businessDay: cancelData.businessDay
      }
      this.$emit('order-cancel', order)
    },
    // CSV出力
    handleCsvOutput() {
      let csv = '\ufeff' + csvColumns1 + '\n'
      this.results.forEach(rowData => {
        const line = '"' +
          rowData.orderDateTime +
          '","' +
          (rowData.realtime ? 'リアルタイム取引' : this.formatDate(this.contractDateTime(rowData.deadlines), true)) +
          '","' +
          rowData.label +
          '","' +
          (rowData.tradeType === '0' ? '買付' : '売却') +
          '","' +
          rowData.note +
          '","' +
          parseFloat(rowData.volume).toFixed(rowData.fixed2) + ' ' + rowData.code +
          '","' +
          (rowData.status === '2' ? '- 円' : rowData.rate === '' ? '(概算 ' + rowData.price.toFixed(0) + ' 円' + ')' : rowData.price.toFixed(0) + ' 円') +
          '","' +
          (rowData.status === '0' ? '(概算用レート)' : '') + (rowData.status === '2' ? '- 円' : parseFloat(rowData.rate).toFixed(rowData.fixed) + ' 円') +
          '","' +
          ['注文中', '約定済', '取消済'][rowData.status] +
          '"\n'
        csv += line
      })
      const blob = new Blob([csv], { type: 'text/csv' })
      const link = document.createElement('a')
      link.href = window.URL.createObjectURL(blob)
      link.download = '為替取引注文履歴_' + this.formatDate() + '.csv'
      link.click()
    },
    // 受渡日を計算する
    contractDateTime(deadlines) {
      // TODO: 本来であれば対象国の銀行の休業日を加味して受渡日を算出する
      // 暫定として土日を銀行の休業日とする｡(祝日は未対応)

      const date = new Date()
      // 取得日が土日の場合は約定日は翌月曜日に補正する
      if (date.getDay() === 6) {
        date.setDate(date.getDate() + 2)
      } else if (date.getDay() === 0) {
        date.setDate(date.getDate() + 1)
      } else {
        // 取得日が営業日の場合､締め切り時間以内であれば結果を返す
        for (const dl of deadlines) {
          const dld = new Date(date)
          dld.setHours(parseInt(dl.split(':')[0]))
          dld.setMinutes(parseInt(dl.split(':')[1]))
          if (dld.getTime() > date.getTime()) {
            return dld
          }
        }
        // 当日の締切時間を過ぎていた場合は翌営業日に補正する
        if (date.getDay() === 5) {
          date.setDate(date.getDate() + 3)
        } else if (date.getDay() === 6) {
          date.setDate(date.getDate() + 2)
        } else {
          date.setDate(date.getDate() + 1)
        }
        // TODO: 翌月曜日が祝日の場合に日付を送る処理
      }

      // 翌営業日に補正された時は､翌営業日の最初の締切時刻で結果を返す
      date.setHours(deadlines[0].split(':')[0])
      date.setMinutes(deadlines[0].split(':')[1])
      return date
    },
    // 日付を文字列に変換する
    formatDate(date, f = false) {
      date = date || new Date()
      return date.getFullYear() + '/' +
            (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
            ('0' + date.getDate()).slice(-2) +
            (f ? ' ' + ('0' + date.getHours()).slice(-2) + ':' +
              ('0' + date.getMinutes()).slice(-2) : '')
    }
  }
}
const dateTimeSorting = (val1, val2) => {
  if (val1 === null && val2 === null) {
    return 0
  } else if (val1 === null && val2 !== null) {
    return 1
  } else if (val1 !== null && val2 === null) {
    return -1
  } else { // if(val1 !== null && val2 !== null){
    const d1 = (new Date(val1)).getTime()
    const d2 = (new Date(val2)).getTime()
    if (d1 > d2) {
      return 1
    } else if (d1 < d2) {
      return -1
    } else {
      return 0
    }
  }
}
// 国旗の画像を読み込む
const imgs = (icon) => {
  return require('@/assets/png/flags/32/' + icon + '.png')
}

//  GridTable Jr.Nisa表示
const jrNisaHtml = (row) => {
  // 為替注文情報リスト.口座区分＝JR_NISA（JrNISA）の場合、ジュニアNISA口座アイコンを表示。
  if (row.accountType === 'JR_NISA') {
    return '<div class="_jrnisa_tag_outer"><span class="_jrnisa_tag">ジュニアNISA口座</span></div>'
  }
  return ''
}

// GridTable 注文日表示
const orderDateHtml = (row) => {
  return '<div>' + getFormattedDateTimeValue(row.orderDate, 'datetime12') + '</div>'
}

// GridTable 締切日時の表示
const deadLineHtml = (row) => {
  // 未約定（為替注文情報リスト.為替注文ステータス　≠　COMPLETED（完了）
  //　"[締切日時]" ＋ 為替注文情報リスト.約定日時
  if (row.fxOrderStatus !== 'COMPLETED') {
    return '<div>[締切日時]' + getFormattedDateTimeValue(row.tradeDateTime, 'datetime12') + '</div>'
  }
  return ''
}

// GridTable 約定日時の表示
const contractHtml = (row) => {
  // 約定済（為替注文情報リスト.為替注文ステータス　＝　COMPLETED（完了））
  // の場合、"[約定日時]" ＋ 為替注文情報リスト.約定日時　を表示。
  // 上記以外の場合、"[約定日時]未約定"を表示。
  if (row.fxOrderStatus === 'COMPLETED') {
    return '<div>[約定日時]' + getFormattedDateTimeValue(row.tradeDateTime, 'datetime12') + '</div>'
  } else {
    return '<div>[約定日時]未約定</div>'
  }
}

function getDecimalLength(str) {
  if (str.includes('.')) {
    return str.split('.')[1].length
  }
  return 2
}

function limitToFourDecimal(str) {
  if (str.includes('.')) {
    const i = str.split('.')[0]
    let d = str.split('.')[1]
    d = d.slice(0, 4)
    return `${i}.${d}`
  }
}

const canCancelTrade = (trade) => {
  const flag = obj.fxTradeMediateProprietyList.some(item => {
    return (
      item.tradeCd === trade.tradeKbn &&
      item.currencyCode === trade.currencyCode &&
      item.mediatePropriety === '1' &&
      trade.orderStatus === '注文中' &&
      trade.fxTradeStatus !== 'PROCESSING' &&
      (trade.tradeCd === '0' || trade.tradeCd === '1')
    )
  })

  return flag
}

const obj = {
  width: 0,
  height: 0,
  title: '為替取引一覧',
  flexHeight: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false,
  postRenderInterval: 100
}
obj.colModel = [
  { title: '注文・締切・約定日時', minWidth: 205, dataType: 'string', dataIndx: 'orderDate', editable: false, halign: 'center', sortable: false,
    render: function(ui) {
      let html = ''
      html += jrNisaHtml(ui.rowData)
      html += orderDateHtml(ui.rowData)
      html += deadLineHtml(ui.rowData)
      html += contractHtml(ui.rowData)
      return html
    }

  },
  { title: '通貨', minWidth: 300, dataType: 'string', dataIndx: 'currencyCode', editable: false, halign: 'center',
    sortType: function(val1, val2, dataindx) {
      const currencyName1 = obj.foreignCurrencyList.find(fc => fc.currencyCode === val1[dataindx]).currency
      const currencyName2 = obj.foreignCurrencyList.find(fc => fc.currencyCode === val2[dataindx]).currency

      return currencyName1.localeCompare(currencyName2, 'ja-JP')
    },
    render: function(ui) {
    // 国旗、通貨名の設定
      const currencyName = obj.foreignCurrencyList.find(fc => fc.currencyCode === ui.rowData.currencyCode).currency
      return '<span style="display: flex; align-items: center;"><img src="' +
        imgs(ui.rowData.currencyCode.substring(0, 2).toLowerCase()) +
        '" style="margin-right: 0.5rem;">' + currencyName + '</span>'
    }
  },
  { title: '取引種別', minWidth: 90, dataType: 'integer', dataIndx: 'tradeKbn', editable: false, halign: 'center', align: 'center',
    sortType: function(val1, val2, dataindx) {
      const compTradeKbn = val1.tradeKbn.localeCompare(val2.tradeKbn, 'ja-JP', { numeric: true })
      if (compTradeKbn !== 0) {
        console.log(compTradeKbn)
        return compTradeKbn
      }

      const compTradeCd = val1.tradeCd.localeCompare(val2.tradeCd, 'ja-JP', { numeric: true })
      return compTradeCd
    },
    render: function(ui) {
      let r1
      let r2
      // 取引種別の売買の設定
      if (ui.rowData.tradeKbn === '2') {
        r1 = '<div class="' + 'font-color__minus bold' + '">売却</div>'
      } else if (ui.rowData.tradeKbn === '1') {
        r1 = '<div class="' + 'font-color__plus bold' + '">買付</div>'
      }
      // 取引種別の設定

      let codeValue
      switch (ui.rowData.tradeCd) {
        case '2': codeValue = '振替'; break
        case '3': codeValue = '債券購入'; break
        case '4': codeValue = '利金償還金'; break
        case '5': codeValue = '外貨建MMF購入'; break
        case '6': codeValue = '外貨建MMF売却'; break
        case '7': codeValue = '譲渡益税充当'; break
      }
      if (ui.rowData.tradeCd !== '0' && ui.rowData.tradeCd !== '1') {
        // 為替注文情報リスト.取引種別≠　（BUY（買付）または　SELL（売却））の場合、表示。
        r2 = '<div class="_sub_info">' + codeValue + '</div>'
        return r1 + r2
      }
      return r1
    }
  },
  { title: '数量・受渡金額', minWidth: 200, dataType: 'integer', dataIndx: 'deliveryAmount', editable: false, halign: 'center', align: 'right', sortable: false,
    render: function(ui) {
      // 数量、通貨コードの設定
      const r1 = '<div>' +
        IfaFormatUtils.withCommaZeroPadding(ui.rowData.quantity, 2) +
        ' ' + ui.rowData.currencyCode +
        '</div>'
      // 受渡金額の設定
      if (ui.rowData.orderStatus === '約定済') {
        // 為替注文情報リスト.注文状況　＝　”約定済”の場合、為替注文情報リスト.受渡金額（※）を表示。
        const r2 = '<div class="_sub_info">' + IfaFormatUtils.withCommaNoneZeroPadding(ui.rowData.deliveryAmount) + ' 円</div>'
        return r1 + r2
      } else if (ui.rowData.orderStatus === '買付不足' || ui.rowData.orderStatus === '取消中' || ui.rowData.orderStatus === '取消済' || ui.rowData.orderStatus === '失効') {
        // 為替注文情報リスト.注文状況＝　”買付不足”　または　”取消中”　または ”取消済”　または　”失効”　の場合、"-（※）"を表示。
        const r2 = '<div>-</div>'
        return r1 + r2
      } else {
        // 上記以外の場合、"（概算" ＋ 為替注文情報リスト.受渡金額（※）＋ ”）”
        const r2 = '<div class="_sub_info">(概算 ' + IfaFormatUtils.withCommaNoneZeroPadding(ui.rowData.deliveryAmount) + ' 円)</div>'
        return r1 + r2
      }
    }
  },
  { title: '約定レート', minWidth: 150, dataType: 'integer', dataIndx: 'contractRate', editable: false, halign: 'center', align: 'right',
    render: function(ui) {
      // 為替注文情報リスト.通貨コードに紐づく通貨リスト.小数部有効桁数取得
      // ①為替注文情報リスト.約定レートの小数桁数＜2の場合、2桁表示
      // ②2≦為替注文情報リスト.約定レートの小数桁数＜為替注文情報リスト.通貨コードに紐づく通貨リスト.小数部有効桁数の場合、為替注文情報リスト.約定レートの小数桁数表示
      // ③上記以外の場合、為替注文情報リスト.通貨コードに紐づく通貨リスト.小数部有効桁数表示
      let decimalLength
      if (obj.foreignCurrencyList.find(fc => fc.currencyCode === ui.rowData.currencyCode).decimalLength > getDecimalLength(ui.rowData.contractRate) && getDecimalLength(ui.rowData.contractRate) >= 2) {
        decimalLength = getDecimalLength(ui.rowData.contractRate)
      } else if (getDecimalLength(ui.rowData.contractRate) < 2) {
        decimalLength = 2
      } else {
        if (getDecimalLength(ui.rowData.contractRate) > 4) {
          ui.rowData.contractRate = limitToFourDecimal(ui.rowData.contractRate)
        }
        decimalLength = obj.foreignCurrencyList.find(fc => fc.currencyCode === ui.rowData.currencyCode).decimalLength
      }

      // 約定レートの設定
      if (ui.rowData.orderStatus === '約定済') {
        // 為替注文情報リスト.注文状況　＝　”約定済”の場合、為替注文情報リスト.約定レート（※）を表示。
        return '<div>' + IfaFormatUtils.withCommaZeroPadding(ui.rowData.contractRate, decimalLength) + ' 円</div>'
      } else if (ui.rowData.orderStatus === '買付不足' || ui.rowData.orderStatus === '取消中' || ui.rowData.orderStatus === '取消済') {
        // 為替注文情報リスト.注文状況　＝　”買付不足”　または　”取消中”　または　”取消済”の場合、"-（※）"を表示。
        return '<div>-</div>'
      } else {
        // 上記以外の場合、　上段："（概算用レート）"、　下段：為替注文情報リスト.約定レート（※）を表示。
        return '<div class="_sub_info">(概算用レート)</div><div>' + IfaFormatUtils.withCommaZeroPadding(ui.rowData.contractRate, decimalLength) + ' 円</div>'
      }
    }
  },
  { title: '注文状況', minWidth: 70, dataType: 'integer', dataIndx: 'orderStatus', editable: false, halign: 'center', align: 'center',
    sortType: function(val1, val2, dataindx) {
      const sortOrder = [
        { val: '注文中', sortOrder: 1 },
        { val: '完了', sortOrder: 2 },
        { val: '取消中', sortOrder: 3 },
        { val: '取消済', sortOrder: 4 },
        { val: '失効済', sortOrder: 5 },
        { val: '訂正中', sortOrder: 6 }
      ]

      const sortOrderVal1 = sortOrder.find(candidate => candidate.val === val1[dataindx])?.sortOrder || 99
      const sortOrderVal2 = sortOrder.find(candidate => candidate.val === val2[dataindx])?.sortOrder || 99

      if (sortOrderVal1 === 99 && sortOrderVal2 === 99) {
        return val1[dataindx].localeCompare(val2[dataindx], 'ja-JP')
      }

      return Math.sign(sortOrderVal1 - sortOrderVal2)
    },
    render: function(ui) {
      // 注文状況の設定
      return '<div>' + ui.rowData.orderStatus + '</div>'
    }
  },
  { title: '取消', minWidth: 70, dataType: 'integer', dataIndx: 'tradeSuspendFlag', editable: false, halign: 'center', align: 'center', hidden: false,
    sortType: function(val1, val2, dataindx) {
      const canCancelVal1 = canCancelTrade(val1) ? 1 : 0
      const canCancelVal2 = canCancelTrade(val2) ? 1 : 0

      return canCancelVal2 - canCancelVal1
    },
    render: function(ui) {
      // 取消の設定
      // 取引停止フラグ　＝　1（取引停止口座）の場合、何も表示しない。
      // 取引停止フラグ　≠　1（取引停止口座）の場合、下記の通り。
      if (obj.tradeSuspendFlag !== '1') {
        // （為替取引媒介可否リスト.取引種別　＝　為替注文情報リスト.売買区分（※）　かつ
        // 為替取引媒介可否リスト.通貨コード　＝　為替注文情報リスト.通貨コード　　かつ
        // 為替取引媒介可否リスト.媒介可否 ＝　1（媒介可））　かつ
        // （為替注文情報リスト.注文状況　＝　”注文中”　かつ
        // 為替注文情報リスト.為替処理ステータス　≠　PROCESSING（処理中）　かつ
        // 為替注文情報リスト.取引種別　為替注文情報リスト.注文種別　　＝　BUY（外貨買）または　SELL（外貨売））の場合、
        // 取消ボタンを表示。
        const flag = canCancelTrade(ui.rowData)
        if (flag) {
          // const btn = document.getElementById('order-cancel-button')
          const v = JSON.stringify(ui.rowData)

          return "<button type='button' class='el-button ifa-button el-button--default el-button--mini secondary-class' onclick='const btn = document.getElementById(\"order-cancel-button\"); btn.value = \"" + escape(v) + "\"; btn.click()'><span class='__adjust_button_text'>取消</span></button>"
        }
        // return flag ? "<button type='button' class='el-button ifa-button el-button--default el-button--mini secondary-class' onclick='document.getElementById(\"order-cancel-button\").value = " + (JSON.stringify(ui.rowData)) + "; document.getElementById(\"order-cancel-button\").click()'><span class='__adjust_button_text'>取消</span></button>" : ''
        // 上記以外の場合、何も表示しない。
      }
    }
  }

]
obj.pageModel = { type: 'local', rPP: 30, rPPOptions: [] }
const csvColumns1 = [
  '注文日時',
  '締切日時',
  '通貨',
  '注文種別',
  '注文種別備考',
  '数量',
  '受渡金額',
  '約定レート',
  '注文状況'
]
</script>

<style lang="scss">
._sub_info {
  font-size: 12px !important;
  color: #888;
}
.__bold {
  font-weight: bold;
}
._jrnisa_tag {
  font-size: 12px !important;
  border: 1px solid #67c23a;
  border-radius: 4px;
  box-sizing: border-box;
  background-color:#67c23a;
  color: #fff;
  padding: 2px 10px;
  white-space: nowrap;
}
.__adjust_button_text {
  height: 24px;
  line-height: 16px !important;
  white-space: nowrap;
}
</style>
