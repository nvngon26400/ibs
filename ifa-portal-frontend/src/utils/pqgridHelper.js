import store from '@/store'
import { getCodeValue, getCodeList } from '@/components/Input/js/IfaCodeListFunction'
import utils from '@/utils/ifaUtils.js'

export function getDefaultOption(colModel, editable = false) {
  return {
    showTop: false,
    reactive: true,
    locale: 'en',
    height: 'flex',
    numberCell: {
      show: false
    },
    columnTemplate: { width: 100 },
    pageModel: {
      type: 'local',
      rPP: 30,
      rPPOptions: store.getters.rPPOptions,
      layout: ['strDisplay', '|', 'prev', 'next']
    },
    colModel: setupColModel(colModel),
    dataModel: {
      data: []
    },
    wrap: false,
    maxHeight: 750,
    selectionModel: { type: 'row', mode: 'single' },
    editable,
    fillHandle: ''
  }
}

export function getConvertedOption(gridModelInput, editable = false) {
  let tableTitle = ''
  if (gridModelInput.title) {
    tableTitle = gridModelInput.title !== '' ? gridModelInput.title : '[コンバート時タイトル未指定]'
  } else {
    tableTitle = '[コンバート時タイトル未指定]'
  }
  let isShowTitle = true
  if (gridModelInput.showTitle === false) {
    isShowTitle = false
  } else {
    isShowTitle = true
  }
  const gridModel = {
    showTitle: isShowTitle,
    title: tableTitle,
    showTop: true,
    reactive: true,
    topVisible: false,
    collapsible: { on: false, collapsed: false, toggle: false },
    locale: 'en',
    height: 'flex',
    numberCell: {
      show: false
    },
    columnTemplate: { width: 100 },
    pageModel: gridModelInput.pageModel,
    colModel: setupColModel(gridModelInput.colModel),
    dataModel: {
      data: []
    },
    wrap: false,
    maxHeight: 750,
    selectionModel: gridModelInput.selectionModel ? gridModelInput.selectionModel : { type: 'row', mode: 'single' },
    editable,
    fillHandle: ''
  }
  gridModel.pageModel.rPPOptions = store.getters.rPPOptions
  return gridModel
}

/**
 * colModel に codeValue オブジェクトが含まれる場合､区分値を取得して表示する
 * colModel に codeList オブジェクトが含まれる場合､区分値一覧を取得して表示する
 * colModel に checkbox オブジェクトが含まれる場合､区分値を取得してチェックボックスを表示する
 * colModel に colModel オブジェクトが含まれる場合､再帰的に処理を実行する
 * 値がない場合、-(ハイフン)を表示するrenderを設定する
 */
export function setupColModel(colModel) {
  colModel.forEach(column => {
    if (column.codeValue) {
      // 区分値を表示
      column.render = function(ui) {
        return getCodeValue(column.codeValue.codeListId, column.codeValue.dispPattern, ui.cellData) || '-'
      }
      column.editable = false
    } else if (column.codeList) {
      // 区分値一覧を表示
      const codeList = getCodeList(column.codeList.codeListId, column.codeList.dispPattern, column.codeList.selectPattern)
      column.editor = {
        type: 'select',
        options: codeList.map(item => item.value)
      }
      column.cl = codeList
    } else if (column.checkbox) {
      // 区分値からシングルチェックボックスを表示する
      const codeList = getCodeList(column.checkbox.codeListId, column.checkbox.dispPattern, column.checkbox.selectPattern, true)
      column.type = 'checkbox'
      column.cb = { check: codeList[1].key, uncheck: codeList[0].key }
      column.editor = false
    } else if (!column.render) {
      // その他
      column.render = function(ui) {
        return ui.cellData || '-'
      }
    }

    if (column.colModel) {
      // 子の colModel が含まれる場合は再帰的に処理する
      column.colModel = setupColModel(column.colModel)
    }

    if (!column.sortOptions || !column.sortOptions.disabled) {
      // sortOptions が無ければ強制的に generalSort に書き換える
      // sortOptions により disabled にされていなければ generalSort に書き換える
      if (column.sortOptions && column.sortOptions.sortFunction) {
        // sortOptions でカスタムソート処理が指定された場合は､カスタムソートを設定する
        column.dataType = (val1, val2) => { return column.sortOptions.sortFunction(val1, val2) }
      } else {
        // 指定が無ければ汎用ソートを実行する
        column.dataType = (val1, val2) => { return generalSort(val1, val2) }
      }
    }
  })
  return colModel
}

// グリッドテーブルに codeList を表示するために key を value に置き換え
export function convertData(data, colModel) {
  let out = [...data]
  // colModel にあるカラムを順に処理する
  colModel.forEach((column) => {
    // 'codeList' と 'cl' オブジェクトが含まれるカラムが変換対象
    if (column.codeList && column.cl) {
      // data の全レコードに対して key を value に置き換える
      out = out.map(item => {
        const match = column.cl.find(clItem => clItem.key === item[column.dataIndx])
        return {
          ...item,
          [column.dataIndx]: match ? match.value : '-'
        }
      })
    } else if (column.colModel) {
      // 子の colModel が含まれる場合は再帰的に処理する
      out = convertData(data, column.colModel)
    }
  })
  // 変換したデータを返却
  return out
}

// 汎用ソート処理
// パラメータの型を推測して適したソートを行う
function generalSort(val1, val2) {
  const [v1, v2] = guessType(val1, val2)
  return utils.nullSorting(v1, v2)
}

// 型推測処理
/* global BigInt */
export function guessType(val1, val2) {
  if (typeof val1 === 'string' || typeof val2 === 'string') {
    // 文字列列型の場合､データ型を推測する

    // 前後の空白を削除する
    // ※顧客一覧の年齢が3桁固定となっていて､年齢が2桁の場合に末尾にスペースが入っているため対策
    const v1 = val1 ? val1.trim() : 'null'
    const v2 = val2 ? val2.trim() : 'null'

    // (1) 文字列が数値か推測する
    const isNumber = /^[+-]?(\d+?\.?\d*|\.\d+)$/
    if (isNumber.test(v1) && isNumber.test(v2)) {
      if (v1.includes('.') || v2.includes('.')) {
        return [Number(v1), Number(v2)]
      } else {
        // v1, v2 のどちらにも小数点が含まれないなら BigInt を使用する
        return [BigInt(v1), BigInt(v2)]
      }
    }

    // (2) 文字列がコンマ付き数値か推測する
    const isNumberWithComma = /^[+-]?(\d{1,3}(,\d{3})*(\.\d*)?|\.\d+)$/
    if (isNumberWithComma.test(v1) && isNumberWithComma.test(v2)) {
      if (v1.includes('.') || v2.includes('.')) {
        return [Number(v1.replace(/,/g, '')), Number(v2.replace(/,/g, ''))]
      } else {
        // v1, v2 のどちらにも小数点が含まれないなら BigInt を使用する
        return [BigInt(v1.replace(/,/g, '')), BigInt(v2.replace(/,/g, ''))]
      }
    }
  }

  // 文字列型以外､または文字列から他の型を推測できなかったの場合は､それぞれの型でソートを行う
  return [val1, val2]
}

export function gridQuestionIcon(title, tooltip) {
  return `
  <span>
  ${title}
  <span title="${tooltip}" style="display: inline-block;">
    <span
      style="color: #FFFFFF; display: inline-block; position: absolute; bottom: 1px; font-size: 14px; padding-left: 3px;"
    >●</span>
    <i class="el-icon" style="color: #005FCC; display: inline-block; position: absolute; bottom: -1px; font-size: 20px;">
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" ><path fill="currentColor" d="M512 64a448 448 0 1 1 0 896 448 448 0 0 1 0-896m23.744 191.488c-52.096 0-92.928 14.784-123.2 44.352-30.976 29.568-45.76 70.4-45.76 122.496h80.256c0-29.568 5.632-52.8 17.6-68.992 13.376-19.712 35.2-28.864 66.176-28.864 23.936 0 42.944 6.336 56.32 19.712 12.672 13.376 19.712 31.68 19.712 54.912 0 17.6-6.336 34.496-19.008 49.984l-8.448 9.856c-45.76 40.832-73.216 70.4-82.368 89.408-9.856 19.008-14.08 42.24-14.08 68.992v9.856h80.96v-9.856c0-16.896 3.52-31.68 10.56-45.76 6.336-12.672 15.488-24.64 28.16-35.2 33.792-29.568 54.208-48.576 60.544-55.616 16.896-22.528 26.048-51.392 26.048-86.592 0-42.944-14.08-76.736-42.24-101.376-28.16-25.344-65.472-37.312-111.232-37.312zm-12.672 406.208a54.272 54.272 0 0 0-38.72 14.784 49.408 49.408 0 0 0-15.488 38.016c0 15.488 4.928 28.16 15.488 38.016A54.848 54.848 0 0 0 523.072 768c15.488 0 28.16-4.928 38.72-14.784a51.52 51.52 0 0 0 16.192-38.72 51.968 51.968 0 0 0-15.488-38.016 55.936 55.936 0 0 0-39.424-14.784z"></path></svg>
    </i>
  </span>
  </span>
  `
}
