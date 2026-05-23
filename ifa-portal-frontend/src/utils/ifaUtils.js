import { BigNumber } from 'bignumber.js'

// /** BigNumberフォーマット指定オブジェクト */
// const valueFormat = {
//   prefix: '',
//   decimalSeparator: '.',
//   groupSeparator: ',',
//   groupSize: 3,
//   secondaryGroupSize: 0,
//   fractionGroupSeparator: '',
//   fractionGroupSize: 0,
//   suffix: ''
// }

function addComma(value) {
  const pattern = /^\s*-?[0-9.]+$/
  if (!value) {
    return value
  }
  if (value === '') {
    return ''
  } else if (value === '-') {
    return '-'
  } else if (value === '.') {
    // 小数点のみは数値とみなさずにそのまま返却
    return value
  } else if (!pattern.test(value)) {
    // 小数点か数字以外の文字が含まれる場合はそのまま返却
    return value
  }
  const isMinus = value < 0
  const valueStr = String(value)
  const period = valueStr.includes('.')
  const v = valueStr.split('.')
  // 小数点が2つ以上ある場合は数値とみなさずにそのまま返却
  if (v.length > 2) return value
  return (isMinus ? '-' : '') + BigNumber(v[0]).abs().toFormat() + (period ? '.' + v[1] : '')
}

function nullSorting(val1, val2) {
  if (val1 === null && val2 === null) {
    return 0
  } else if (val1 === null && val2 !== null) {
    return 1
  } else if (val1 !== null && val2 === null) {
    return -1
  } else { // if(val1 !== null && val2 !== null){
    if (val1 > val2) {
      return 1
    } else if (val1 < val2) {
      return -1
    } else {
      return 0
    }
  }
}

function copyModel(form, request) {
  if (form !== null) {
    const requestParam = Object.assign(request, form)
    return requestParam
  }
  return null
}

function nullToMinus(value) {
  return value || '-'
}

function nullToEmpty(value) {
  return value || ''
}

function getByteLength(str) {
  if (typeof str !== 'string') {
    return 0
  }
  try {
    return new TextEncoder().encode(str).length
  } catch (e) {
    let byteLength = 0
    for (let i = 0; i < str.length; i++) {
      const charCode = str.charCodeAt(i)
      if (charCode <= 0x7f) { // ASCII
        byteLength += 1
      } else if (charCode <= 0x7ff) { // 2 bytes
        byteLength += 2
      } else if (charCode <= 0xffff) { // 3 bytes
        byteLength += 3
      } else { // 4 bytes
        byteLength += 4
      }
    }
    return byteLength
  }
}

function isByteLengthExceeded(str, maxLength) {
  if (typeof str !== 'string' || typeof maxLength !== 'number' || maxLength < 0 || !Number.isInteger(maxLength)) {
    return false
  }
  const byteLength = getEUCJPByteLength(str)
  return byteLength > maxLength
}

function getEUCJPByteLength(str) {
  let byteLength = 0
  for (let i = 0; i < str.length; i++) {
    const charCode = str.charCodeAt(i)
    if (charCode < 0x80) {
      byteLength += 1 // ASCII
    } else if (charCode < 0xFFFF) {
      byteLength += 2
    } else {
      byteLength += 3
    }
  }
  return byteLength
}

/**
 * 半角英数記号を全角に変換
 * @param value String 半角英数字
 * @returns 全角英数字
 */
function halfWidthtoFullWidth(value) {
  return value.replace(/[\u0021-\u007E]/g, (s) => {
    // 変換前の文字コードを取得
    const code = s.charCodeAt(0)
    // 半角文字のコードを調整して全角に変換
    return String.fromCharCode(code + 0xFEE0)
  })
    // 文字コードシフトで対応できない文字の変換
    // OracleのTO_MULTI_BYTE関数と統一
    .replace(/ /g, '　')
    .replace(/＂/g, '”')
    .replace(/＇/g, '’')
    .replace(/－/g, '−')
    .replace(/＼/g, '￥')
    .replace(/｀/g, '’')
    .replace(/～/g, '￣')
}

/**
 * 半角カタカナを全角に変換
 * @param value String 半角カタカナ
 * @returns 全角カタカナ
 */
function kanaHalfToFull(value) {
  const kanaMap = {
    'ｶﾞ': 'ガ', 'ｷﾞ': 'ギ', 'ｸﾞ': 'グ', 'ｹﾞ': 'ゲ', 'ｺﾞ': 'ゴ',
    'ｻﾞ': 'ザ', 'ｼﾞ': 'ジ', 'ｽﾞ': 'ズ', 'ｾﾞ': 'ゼ', 'ｿﾞ': 'ゾ',
    'ﾀﾞ': 'ダ', 'ﾁﾞ': 'ヂ', 'ﾂﾞ': 'ヅ', 'ﾃﾞ': 'デ', 'ﾄﾞ': 'ド',
    'ﾊﾞ': 'バ', 'ﾋﾞ': 'ビ', 'ﾌﾞ': 'ブ', 'ﾍﾞ': 'ベ', 'ﾎﾞ': 'ボ',
    'ﾊﾟ': 'パ', 'ﾋﾟ': 'ピ', 'ﾌﾟ': 'プ', 'ﾍﾟ': 'ペ', 'ﾎﾟ': 'ポ',
    'ｳﾞ': 'ヴ', 'ﾜﾞ': 'ヷ', 'ｦﾞ': 'ヺ',
    'ｱ': 'ア', 'ｲ': 'イ', 'ｳ': 'ウ', 'ｴ': 'エ', 'ｵ': 'オ',
    'ｶ': 'カ', 'ｷ': 'キ', 'ｸ': 'ク', 'ｹ': 'ケ', 'ｺ': 'コ',
    'ｻ': 'サ', 'ｼ': 'シ', 'ｽ': 'ス', 'ｾ': 'セ', 'ｿ': 'ソ',
    'ﾀ': 'タ', 'ﾁ': 'チ', 'ﾂ': 'ツ', 'ﾃ': 'テ', 'ﾄ': 'ト',
    'ﾅ': 'ナ', 'ﾆ': 'ニ', 'ﾇ': 'ヌ', 'ﾈ': 'ネ', 'ﾉ': 'ノ',
    'ﾊ': 'ハ', 'ﾋ': 'ヒ', 'ﾌ': 'フ', 'ﾍ': 'ヘ', 'ﾎ': 'ホ',
    'ﾏ': 'マ', 'ﾐ': 'ミ', 'ﾑ': 'ム', 'ﾒ': 'メ', 'ﾓ': 'モ',
    'ﾔ': 'ヤ', 'ﾕ': 'ユ', 'ﾖ': 'ヨ',
    'ﾗ': 'ラ', 'ﾘ': 'リ', 'ﾙ': 'ル', 'ﾚ': 'レ', 'ﾛ': 'ロ',
    'ﾜ': 'ワ', 'ｦ': 'ヲ', 'ﾝ': 'ン',
    'ｧ': 'ァ', 'ｨ': 'ィ', 'ｩ': 'ゥ', 'ｪ': 'ェ', 'ｫ': 'ォ',
    'ｯ': 'ッ', 'ｬ': 'ャ', 'ｭ': 'ュ', 'ｮ': 'ョ',
    '｡': '。', '､': '、', 'ｰ': 'ー', '｢': '「', '｣': '」', '･': '・',
    // 半角の濁点&半濁点 → 全角の濁点&半濁点
    'ﾞ': '゛', 'ﾟ': '゜'
  }
  const reg = new RegExp('(' + Object.keys(kanaMap).join('|') + ')', 'g')
  return value.replace(reg, (s) => {
    return kanaMap[s]
  })
}

/**
 * ひらがなを全角カタカナに変換
 * @param value String ひらがな
 * @returns 全角カタカナ
 */
function hiraToKata(value) {
  return value.replace(/[\u3041-\u3096]/g, (s) => {
    // 変換前の文字コードを取得
    const code = s.charCodeAt(0)
    // ひらがな文字のコードを調整してカタカナに変換
    return String.fromCharCode(code + 0x60)
  })
}

export default {
  addComma,
  nullSorting,
  copyModel,
  nullToMinus,
  nullToEmpty,
  getByteLength,
  isByteLengthExceeded,
  halfWidthtoFullWidth,
  kanaHalfToFull,
  hiraToKata
}
