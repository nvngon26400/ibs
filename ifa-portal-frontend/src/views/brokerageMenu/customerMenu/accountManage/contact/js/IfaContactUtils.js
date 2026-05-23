const INIT_TEXTAREA_HEIGHT = '136px'

// 初期化時に「追加入力」テキストエリアの高さを設定する
export function initTextareaHeight(obj) {
  if (obj) {
    obj.style.height = INIT_TEXTAREA_HEIGHT
  }
}

// カテゴリ表示名フォーマット
// カテゴリー名称（大）+"　"(全角ブランク1文字)+カテゴリー名称（中）+"　"(全角ブランク1文字)+カテゴリー名称（小）
// カテゴリー名称（大）がNULLの場合、直後の"　"(全角ブランク1文字)は表示しない
// カテゴリー名称（中）がNULLの場合、直前の"　"(全角ブランク1文字)は表示しない
// カテゴリー名称（小）がNULLの場合、直前の"　"(全角ブランク1文字)は表示しない
export function formatToiawaseMei(toiawaseDMei, toiawaseMei, toiawaseSMei) {
  const parts = []
  if (toiawaseDMei) parts.push(toiawaseDMei)
  if (toiawaseMei) parts.push(toiawaseMei)
  if (toiawaseSMei) parts.push(toiawaseSMei)
  return parts.join('　')
}

// カテゴリ表示名取得
export function displayToiawaseMei(toiawaseList, toiawaseCd) {
  const item = toiawaseList.find(item => item.key === toiawaseCd)
  return item ? item.value : ''
}

// 長さ計算、半角１バイト、全角２バイト
export function calculateTextLength(str) {
  if (typeof str !== 'string') {
    return 0
  }
  let byteLength = 0
  for (let i = 0; i < str.length; i++) {
    const charCode = str.charCodeAt(i)
    if (charCode >= 0xFF61 && charCode <= 0xFF9F) {
      byteLength += 1
    } else if (str[i] === '～') {
      byteLength += 3
    } else if (charCode <= 0x7f) {
      byteLength += 1
    } else {
      byteLength += 2
    }
  }
  return byteLength
}
