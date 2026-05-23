import IfaUtils from '@/utils/ifaUtils'

export function getTableOptions(colModel) {
  return {
    height: 'flex',
    title: null,
    freezeCols: 1,
    flexHeight: false,
    flexWidth: false,
    collapsible: {
      on: false,
      collapsed: false,
      toggle: false
    },
    showTitle: false,
    numberCell: {
      show: false
    },
    topVisible: false,
    selectionModel: {
      type: 'row',
      mode: 'single'
    },
    showTop: true,
    reactive: true,
    locale: 'en',
    columnTemplate: {
      width: 100
    },
    pageModel: {
      type: 'local',
      rPP: 30,
      curPage: 1,
      rPPOptions: [30, 50, 100, 200, 500],
      layout: ['strDisplay', '|', 'prev', 'next']
    },
    colModel,
    dataModel: {
      data: []
    },
    wrap: true,
    editable: false,
    fillHandle: ''
  }
}

export const COL_MODEL = [
  {
    title: '',
    dataIndx: 'selected',
    editable: true,
    width: 50,
    align: 'center',
    render: (item) => {
      // 1: show 2: hidden
      const canCancel = item.rowData.accumulateCancelAvailability === '1'
      return `
        ${canCancel ? `
          <input
            type='checkbox'
            style='cursor: pointer'
            data-id='${item.rowData.id}'
            ${item.rowData.selected ? 'checked' : ''}
          ></input>
        ` : ''}
      `
    }
  },
  {
    title: '協会コード<br>銘柄コード',
    dataIndx: 'fundCode',
    width: 150,
    align: 'center',
    render: (item) => ({
      cls: 'pg-grid-cell',
      text: `
      <div style="display:flex; flex-direction:column; height: 100%; justify-content: center;">
        <div id="fundCode" style="height: 100%; display: flex; align-items: center; justify-content: center; word-break: break-all; padding: 0 5px;">
          ${item.rowData.fundCode ?? '-'}
        </div>
        <div style="border-top:1px solid #ccc; margin: 8px 0";></div>
        <div id="brandCode" style="height: 100%; display: flex; align-items: center; justify-content: center; word-break: break-all; padding: 0 5px;">
          ${item.rowData.mfkaisu}.${item.rowData.mfgo}
        </div>
      </div>`
    })
  },
  {
    title: '<div>銘柄名</div>',
    dataIndx: 'fundName',
    width: 300,
    halign: 'center',
    align: 'left'
  },
  {
    title: '<div>預り区分</div>',
    dataIndx: 'accountTypeName',
    width: 200,
    align: 'center'
  },
  {
    title: '<div>決済方法</div>',
    dataIndx: 'paymentMethodName',
    width: 200,
    align: 'center'
  },
  {
    title: '積立コース<br>設定金額',
    width: 200,
    align: 'center',
    render: (item) => ({
      cls: 'pg-grid-cell',
      text: `
      <div style="display:flex; flex-direction:column; height: 100%; justify-content: center">
        <div id="courseType" style="height: 100%; display: flex; align-items: center; justify-content: center; word-break: break-all; padding: 0 5px;">
          ${item.rowData.courseType ?? '-'}
        </div>
        <div style="border-top:1px solid #ccc;margin: 8px 0";></div>
        <div id="settingAmount" style="height: 100%; display: flex; align-items: center; justify-content: center; word-break: break-all; padding: 0 5px;">
          ${item.rowData.settingAmount ? `${IfaUtils.addComma(item.rowData.settingAmount)}${' '}円` : '-'}
        </div>
      </div>`
    })
  },
  {
    title: '<div>ボーナス月の設定</div>',
    width: 200,
    align: 'center',
    render: (item) => {
      const hasDate = item.rowData.settingBonusMonthDay !== '-'

      return {
        cls: 'pg-grid-cell',
        text: hasDate ? `
          <div style="display:flex; flex-direction:column">
            <div id="settingBonusAmount">
              ${item.rowData.settingBonusAmount ? `${IfaUtils.addComma(item.rowData.settingBonusAmount)}${' '}円` : '-'}
            </div>
            <div id="settingBonusMonthDay">
              ${item.rowData.settingBonusMonthDay ?? '-'}
            </div>
          </div>`
          : '-'
      }
    }
  },
  {
    title: 'NISA枠ぎりぎり注文<br>課税枠シフト注文',
    align: 'center',
    dataIndx: 'yyyy',
    width: 200,
    render: (item) => ({
      cls: 'pg-grid-cell',
      text: `
      <div style="display:flex; flex-direction:column; height: 100%; justify-content: center">
        <div id="nisaBarelyBuyingType" style="height: 100%; display: flex; align-items: center; justify-content: center; word-break: break-all; padding: 0 5px;">
          ${item.rowData.nisaBarelyBuyingType ?? '-'}
        </div>
        <div style="border-top:1px solid #ccc;margin: 8px 0";></div>
        <div id="taxShiftType" style="height: 100%; display: flex; align-items: center; justify-content: center; word-break: break-all; padding: 0 5px;">
          ${item.rowData.taxShiftType ?? '-'}
        </div>
      </div>`
    })
  },
  {
    title: '<div>1ヵ月あたりの積立概算</div>',
    dataIndx: 'oneMonthSumAmount',
    width: 200,
    align: 'center',
    render: (item) => `
          ${item.rowData.oneMonthSumAmount ? `${IfaUtils.addComma(item.rowData.oneMonthSumAmount)}${' '}円` : '-'}
        `
  },
  {
    title: '<div>次回発注予定日</div>',
    dataIndx: 'nextReserveDate',
    width: 150,
    align: 'center',
    render: (item) => `${item.rowData.nextReserveDate ?? '-'}`
  },
  {
    title: '<div>設定</div>',
    width: 150,
    align: 'center',
    render: (item) => {
      const rowData = JSON.stringify(item)
      /*
      * 1: show
      * 2: hidden
      */
      const canUpdate = item.rowData.accumulateChangeAvailability === '1'
      const canCreate = item.rowData.accumulateAvailability === '1'

      return `
        <div style="display:flex; flex-direction:column; row-gap: 5px; align-items: center;">
          ${canUpdate ? `
            <button
              id="display-update-btn-${item.rowData.id}"
              type='button'
              class='el-button el-button--small el-button--primary ifa-button primary-class'
              style='width: 80px;'
              onclick='const btn = document.getElementById("update-btn"); btn.value = "${encodeURIComponent(rowData)}"; btn.click()'
              data-item="${encodeURIComponent(rowData)}"
              data-action='update'
            >
              <span>設定変更</span>
            </button>
          ` : ''}
          ${canCreate ? `
            <button
              id="display-create-btn-${item.rowData.id}"
              type='button'
              class='el-button el-button--small el-button--primary ifa-button primary-class'
              style='margin-left: 0; width: 80px;'
              onclick='const btn = document.getElementById(\"create-btn\"); btn.value = \"${encodeURIComponent(rowData)}\"; btn.click()'
              data-item="${encodeURIComponent(rowData)}"
              data-action='create'
            >
              <span>追加</span>
            </button>
          ` : ''}
        </div>
      `
    }
  }
]
