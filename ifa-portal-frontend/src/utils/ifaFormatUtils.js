import utils from '@/utils/ifaUtils.js'

function isNumber(value) {
  return value !== null && !isNaN(value) && /^\s*[+-]?[\d.]+$/.test(value)
}

function zeroPad(value, digits) {
  const v = String(value).split('.')
  v.push('') // 小数部がない場合に備えて､1要素プッシュしておく
  v[1] = v[1].padEnd(digits, '0')
  return v[0] + (v[1].length > 0 ? '.' + v[1] : '')
}

function noneZeroPad(formatValue) {
  let period = ''
  if (formatValue === 0) {
    // 数値0の時はincludesメソッドでTypeErrorが出るためそのまま返す
    return formatValue
  } else {
    period = formatValue.includes('.')
  }
  if (period) {
  // 小数部がある時
    const v = formatValue.split('.')
    // 小数点が2つ以上ある場合は数値とみなさずにそのまま返却
    if (v.length > 2) return formatValue
    const fractionalPart = v[1].replace(/0+$/, '')
    // 0置き換え後も小数部がある場合は、小数部を表示
    return v[0] + (fractionalPart ? '.' + fractionalPart : '')
  } else {
  // 小数部がない時
    return formatValue
  }
}

/**
 * カンマ区切り0埋め
 * 数字を3桁ごとにカンマを付けて整形します
 * 小数点以下を指定桁数になるように0を補完します
 * @param value String 数字
 * @param digits Number | String 小数部桁数
 * @returns String カンマ区切り0埋め数字
 */
function withCommaZeroPadding(value, digits) {
  return isNumber(value) ? zeroPad(utils.addComma(value), digits) : value
}

/**
 * カンマ区切り0埋めなし
 * 符号付き数字を3桁ごとにカンマを付けて整形します
 * @param value String 数字
 * @returns String カンマ区切り0埋めなし数字
 */
function withCommaNoneZeroPadding(value) {
  if (isNumber(value)) {
    const formatValue = utils.addComma(value)
    return noneZeroPad(formatValue)
  } else {
    return value
  }
}

/**
 * カンマ区切り無し0埋め
 * 小数点以下を指定桁数になるように0を補完します
 * @param value String 数字
 * @param digits Number | String 小数部桁数
 * @returns String カンマ区切り無し0埋め数字
 */
function noneWithCommaZeroPadding(value, digits) {
  return isNumber(value) ? zeroPad(value, digits) : value
}

/**
 * カンマ区切り無し0埋めなし
 * @param value String 数字
 * @returns String カンマ区切り無し0埋めなし数字
 */
function noneWithCommaNoneZeroPadding(value) {
  if (isNumber(value)) {
    // 引数のチェック
    const pattern = /^\s*-?[0-9.]+$/
    if (!value) {
      return value
    }
    if (value === '' || value === '-' || value === '.') {
      return value
    } else if (!pattern.test(value)) {
      // 小数点か数字以外の文字が含まれる場合はそのまま返却
      return value
    }

    const isMinus = value < 0
    const valueStr = String(value)
    if (noneZeroPad(valueStr) === valueStr) {
      return value
    } else {
      return (isMinus ? '-' : '') + noneZeroPad(valueStr)
    }
  } else {
    return value
  }
}

/**
 * 符号付きカンマ区切り0埋め
 * 数字を3桁ごとにカンマを付けて整形します
 * 小数点以下を指定桁数になるように0を補完します
 * 符号を付加します
 * @param value String 数字
 * @param digits Number | String 小数部桁数
 * @returns String 符号付きカンマ区切り0埋め数字
 */
function signedWithCommaZeroPadding(value, digits) {
  if (isNumber(value)) {
    const sign = value > 0 ? '+' : ''
    return sign + zeroPad(utils.addComma(value), digits)
  }
  return value
}

/**
 * 符号付きカンマ区切り0埋めなし
 * 数字を3桁ごとにカンマを付けて整形します
 * 符号を付加します
 * @param value String 固定小数
 * @returns String 符号付きカンマ区切り0埋めなし数字
 */
function signedWithCommaNoneZeroPadding(value) {
  if (isNumber(value)) {
    const sign = value > 0 ? '+' : ''
    const formatValue = utils.addComma(value)
    return sign + noneZeroPad(formatValue)
  } else {
    return value
  }
}

/**
 * カンマ区切り整数
 * 整数を3桁ごとにカンマを付けて整形します
 * @param value String 整数
 * @returns String カンマ区切り数字
 */
function withCommaInteger(value) {
  if (isNumber(value)) {
    return utils.addComma(value)
  }
  return value
}

/**
 * 符号付きカンマ区切り整数
 * 整数を3桁ごとにカンマを付けて整形します
 * 符号を付加します
 * @param value String 整数
 * @returns String 符号付きカンマ区切り数字
 */
function signedWithCommaInteger(value) {
  if (isNumber(value)) {
    const sign = value > 0 ? '+' : ''
    return sign + utils.addComma(value)
  }
  return value
}

/**
 * 0埋め
 * 数字を0埋めします
 * @param value String 数字
 * @param precision Number | String 全体桁数
 * @returns 0埋め数字
 */
function zeroPadding(value, precision) {
  if (!isNaN(value)) {
    return value.padStart(precision, '0')
  }
  return value
}

export default {
  withCommaZeroPadding,
  withCommaNoneZeroPadding,
  noneWithCommaZeroPadding,
  noneWithCommaNoneZeroPadding,
  signedWithCommaZeroPadding,
  signedWithCommaNoneZeroPadding,
  withCommaInteger,
  signedWithCommaInteger,
  zeroPadding
}
