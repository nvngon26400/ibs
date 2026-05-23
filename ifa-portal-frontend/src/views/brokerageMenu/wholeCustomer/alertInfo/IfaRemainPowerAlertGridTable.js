import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { gridQuestionIcon } from '@/utils/pqgridHelper'

export const colDefine = {
  accuralDate: {
    title: '発生日',
    width: 120,
    dataType: 'string',
    dataIndx: 'accuralDate',
    editable: false,
    halign: 'center',
    align: 'left',
    hidden: false,
    render({ rowData }) {
      return getFormattedDateValue(rowData.accuralDate) || '-'
    }
  },
  brokerCode: {
    title: '仲介業者コード',
    width: 120,
    dataType: 'string',
    dataIndx: 'brokerCode',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  brokerName: {
    title: '仲介業者名',
    width: 350,
    dataType: 'string',
    dataIndx: 'brokerName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  brokerChargeCode: {
    title: '営業員コード',
    width: 120,
    dataType: 'string',
    dataIndx: 'brokerChargeCode',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  brokerChargeName: {
    title: '営業員名',
    width: 180,
    dataType: 'string',
    dataIndx: 'brokerChargeName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  buten: {
    title: '部店',
    width: 120,
    dataType: 'string',
    dataIndx: 'buten',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  accountNumber: {
    title: '口座番号',
    width: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'accountNumber',
    align: 'left',
    render({ rowData }) {
      return ifaFormatUtils.zeroPadding(rowData.accountNumber, 7) || '-'
    }
  },
  customerName: {
    title: '顧客名（漢字）',
    width: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'customerName',
    align: 'left'
  },
  customerNameKana: {
    title: '顧客名（カナ）',
    width: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'customerNameKana',
    align: 'left'
  },
  brandCode: {
    title: '銘柄コード',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'brandCode',
    align: 'left',
    hidden: false
  },
  brandName: {
    title: '銘柄名',
    width: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'brandName',
    align: 'left',
    hidden: false
  },
  amount: {
    title: '建株数',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'amount',
    align: 'right',
    hidden: false,
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.amount) || '-'
    }
  },
  repaymentDate: {
    title: '返済期限',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'repaymentDate',
    align: 'left',
    hidden: false,
    render({ rowData }) {
      return getFormattedDateValue(rowData.repaymentDate) || '-'
    }
  },
  alertKbn: {
    title: 'アラート区分',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'alertKbn',
    align: 'left',
    hidden: false
  },
  currency: {
    title: '通貨',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'currency',
    align: 'left',
    hidden: false
  },
  foreignPower: {
    title: '外貨余力',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'foreignPower',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const foreignPower = ui.rowData.foreignPower
      if (Number(foreignPower) < 0) {
        return `<span style='color:#ff1e00;'>` + ifaFormatUtils.withCommaInteger(ui.rowData.foreignPower) + `</span>`
      } else {
        return ifaFormatUtils.withCommaInteger(ui.rowData.foreignPower)
      }
    }
  },
  remainPower: {
    title: function() {
      return `<span title = "当日の預り金残高" style="text-decoration: underline">預り金赤残</span>`
    },
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'remainPower',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const remainPower = ui.rowData.remainPower
      if (Number(remainPower) < 0) {
        return `<span style='color:#ff1e00;'>` + ifaFormatUtils.withCommaInteger(ui.rowData.remainPower) + `</span>`
      } else {
        return ifaFormatUtils.withCommaInteger(ui.rowData.remainPower)
      }
    }
  },
  notPaymentInfo: {
    title: gridQuestionIcon('円貨未入金・赤残', '現金不足による赤残及び信用口座の30.2％割れ預り金不足を含む'),
    width: 180,
    minWidth: 160,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'remainPower',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const remainPower = ui.rowData.remainPower
      if (Number(remainPower) < 0) {
        return `<span style='color:#ff1e00;'>` + ifaFormatUtils.withCommaInteger(ui.rowData.remainPower) + `</span>`
      } else {
        return ifaFormatUtils.withCommaInteger(ui.rowData.remainPower)
      }
    }
  },
  remainPowerInfo: {
    title: gridQuestionIcon('預り金赤残', '当日の預り金残高'),
    width: 120,
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'remainPower',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const remainPower = ui.rowData.remainPower
      if (Number(remainPower) < 0) {
        return `<span style='color:#ff1e00;'>` + ifaFormatUtils.withCommaInteger(ui.rowData.remainPower) + `</span>`
      } else {
        return ifaFormatUtils.withCommaInteger(ui.rowData.remainPower)
      }
    }
  },
  foreignNotPaymentInfo: {
    title: '外貨未入金・赤残',
    width: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'remainPower',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const remainPower = ui.rowData.remainPower
      if (Number(remainPower) < 0) {
        return `<span style='color:#ff1e00;'>` + ifaFormatUtils.withCommaZeroPadding(ui.rowData.remainPower, 2) + `</span>`
      } else {
        return ifaFormatUtils.withCommaZeroPadding(ui.rowData.remainPower, 2)
      }
    }
  },
  foreignRemainPower: {
    title: '預り金赤残',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'remainPower',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const remainPower = ui.rowData.remainPower
      if (Number(remainPower) < 0) {
        return `<span style='color:#ff1e00;'>` + ifaFormatUtils.withCommaZeroPadding(ui.rowData.remainPower, 2) + `</span>`
      } else {
        return ifaFormatUtils.withCommaZeroPadding(ui.rowData.remainPower, 2)
      }
    }
  },
  detail: {
    title: '詳細',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'detail',
    align: 'center',
    render: function(ui) {
      const detail = ui.rowData.detail
      return changeColorBorderBottom(detail)
    }
  },
  creditReserve: {
    title: '信用余力',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'creditReserve',
    align: 'left',
    hidden: false
  },
  deficitStatus: {
    title: '信用追証',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'deficitStatus',
    align: 'left',
    hidden: false
  },
  inventionDetails: {
    title: '現物詳細',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'inventionDetails',
    align: 'left',
    render: function(ui) {
      const detail = ui.rowData.detail
      return `<a><span style='text-decoration: underline; text-underline-offset:0.2em;'>` + detail + `</span></a>`
    }
  },
  creditDetails1: {
    title: '信用詳細',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'creditDetails1',
    align: 'left',
    render: function(ui) {
      const detail = ui.rowData.detail
      return `<a><span style='text-decoration: underline; text-underline-offset:0.2em;'>` + detail + `</span></a>`
    }
  },
  creditDetails2: {
    title: '信用詳細2',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'creditDetails2',
    align: 'left',
    render: function(ui) {
      const detail = ui.rowData.detail
      return `<a><span style='text-decoration: underline; text-underline-offset:0.2em;'>` + detail + `</span></a>`
    }
  },
  branchCode: { title: '支店コード', dataIndx: 'branchCode', editable: false, halign: 'center', align: 'left' },
  branchName: { title: '支店名', dataIndx: 'branchName', editable: false, halign: 'center', align: 'left', width: 200 },
  course: { title: '取引コース', dataIndx: 'course', width: 150, editable: false, halign: 'center', align: 'left' },
  beforeDeposit: {
    title: gridQuestionIcon('前日保証金残高', '300,000円未満は赤字'),
    width: 150,
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'beforeDeposit',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const text = ifaFormatUtils.withCommaInteger(ui.cellData)
      const style = (Number(ui.cellData) < 300000) ? 'color:#ff1e00;' : ''

      return {
        text: text,
        style: style
      }
    }
  },
  foreignBeforeDeposit: {
    title: gridQuestionIcon('前日保証金残高', '2,500ドル未満は赤字'),
    width: 150,
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'beforeDeposit',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const text = ifaFormatUtils.withCommaZeroPadding(ui.cellData, 2)
      const style = (Number(ui.cellData) < 2500) ? 'color:#ff1e00;' : ''

      return {
        text: text,
        style: style
      }
    }
  },
  beforeDetentionRate: {
    title: gridQuestionIcon('前日維持率', '31%未満は赤字'),
    width: 120,
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'beforeDetentionRate',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const text = ifaFormatUtils.withCommaZeroPadding(ui.cellData, 2)
      const style = (Number(ui.cellData) < 31) ? 'color:#ff1e00;' : ''
      return {
        text: text,
        style: style
      }
    }
  },
  beforeDetentionRateForeign: {
    title: gridQuestionIcon('前日維持率', '51%未満は赤字'),
    width: 120,
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'beforeDetentionRate',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const text = ifaFormatUtils.withCommaZeroPadding(ui.cellData, 2)
      const style = (Number(ui.cellData) < 51) ? 'color:#ff1e00;' : ''
      return {
        text: text,
        style: style
      }
    }
  },
  marginDemandAmount: {
    title: gridQuestionIcon('追証請求額', '0円より多い場合は赤字'),
    width: 120,
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'marginDemandAmount',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const text = ifaFormatUtils.withCommaInteger(ui.cellData)
      const style = (Number(ui.cellData) > 0) ? 'color:#ff1e00;' : ''
      return {
        text: text,
        style: style
      }
    }
  },
  foreignMarginDemandAmount: {
    title: gridQuestionIcon('追証請求額', '0ドルより多い場合は赤字'),
    width: 120,
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'marginDemandAmount',
    align: 'right',
    hidden: false,
    render: function(ui) {
      const text = ifaFormatUtils.withCommaZeroPadding(ui.cellData, 2)
      const style = (Number(ui.cellData) > 0) ? 'color:#ff1e00;' : ''
      return {
        text: text,
        style: style
      }
    }
  },
  ticker: {
    title: 'ティッカー',
    dataIndx: 'brandCode',
    width: 90,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  none: {
    title: '',
    width: 400,
    dataIndx: ''
  }

}

const changeColorBorderBottom = (item) => {
  return `<a><span style="color: #092987;">` + item + `</span></a> 
        <style>
        a {
          color: #092987;
          text-decoration: underline;
        }
        a:focus, a:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
        }
        </style>`
}

