import store from '@/store'
import Logger from '@/utils/ifaLog.js'
/**
 * 区分値一覧を取得する
 *
 * @param {string} codeListId 区分ID
 * @param {number} dispPattern 表示パターン
 * @param {number} selectPattern 取得パターン
 * @return {Array} コードリスト(key,value)
 */
export const getCodeList = (codeListId, dispPattern, selectPattern, isSingleCheckbox = false) => {
  const codeList = []
  if (isSingleCheckbox) {
    // チェックボックスからの取得の場合､取得パターンは強制的に1にする
    selectPattern = 1
  }
  const cl = store.getters.codeList
  const sps = cl.find(obj => obj.codeListId === codeListId)
  const tmp = sps?.selectPatternList.find(dp => dp.selectPattern === Number(selectPattern || 1))
  if (tmp) {
    const cls = tmp.codeList.sort((a, b) => a.selectOrder - b.selectOrder)
    for (const cl of cls) {
      const cv = getCodeValue(codeListId, dispPattern, cl.key, isSingleCheckbox)
      if (cv || isSingleCheckbox) {
        codeList.push({ key: cl.key, value: cv })
      }
    }
  }
  if (codeList.length === 0) {
    // 区分値が1件も取得できなかった場合はログに出力する
    Logger.error(`ERROR: 区分値の取得結果が0件です。[codeListId = '${codeListId}', dispPattern = ${dispPattern}, selectPattern = ${selectPattern}, isCheckbox = ${isSingleCheckbox}]`)
  }
  return codeList
}

/**
 * 区分値名称を取得する
 *
 * @param {string} codeListId 区分ID
 * @param {number} dispPattern 表示パターン
 * @param {string} codeKey 区分値
 * @return {string} 区分値名称
 */
export const getCodeValue = (codeListId, dispPattern, codeKey, isSingleCheckbox = false) => {
  const codeValue = store.getters.codeValue
  // codeKey が '' の場合､ '$NULL' をキーとして区分値名称の取得を行う｡ (Redmine #2902)
  const codeKey2 = codeKey === '' ? '$NULL' : codeKey
  const dps = codeValue.find(obj => obj.codeListId === codeListId)
  const cks = dps?.displayPatternList.find(dp => dp.displayPattern === Number(dispPattern || 1))
  const code = cks?.codeList.find(ck => ck.key === codeKey2)
  if (!code && !isSingleCheckbox) {
    // key に一致する区分値が見つからない場合はログに出力する
    Logger.debug(`ERROR: key('${codeKey2}')に対応する区分値の表示パターンがありません｡[codeListId = '${codeListId}', dispPattern = ${dispPattern}, isSingleCheckbox = ${isSingleCheckbox}]`)
  }
  // 特定のキーワードを置き換えする (Redmine #2902)
  // $NULL => ''
  // $CRLF => '<br>'
  if (code) {
    code.value = code.value.replace(/\$NULL/ig, '').replace(/\$CRLF/ig, '<br>')
  }
  return code?.value
}
