import moment from 'moment'
import store from '@/store'
/**
 * フォーマット済み日付文字列を返却する。
 * フォーマット可能の場合：'YYYY/MM'
 * 空文字の場合：''
 * フォーマット不可の場合：null
 * @param {string} value フォーマット対象の文字列。
 * @return {string} 'YYYY/MM' or '' or null
 */
export const getFormattedMonthValue = (value, domainFormat = 'date6YearMonth') => {
  // valueがnullまたは空文字の場合
  if (!value) return value

  let m = null
  if (value.match(/^\d{4}\/\d{1,2}/)) {
    // valueが(4桁数字)/(2桁数字)の場合
    m = moment(value, 'YYYY/MM')
  } else if (value.match(/^\d{2}\/\d{1,2}/)) {
    // valueが(2桁数字)/(2桁数字)の場合
    m = moment(`20${value}`, 'YYYY/MM')
  } else if (value.length === 6) {
    // valueが6桁数字の場合
    m = moment(value, 'YYYYMM')
  }

  if (m && m.isValid()) {
    // フォーマット可能の場合
    const format = monthFormat(domainFormat)
    return m.format(format)
  } else {
    // 空文字の場合
    return null
  }
}

export const getPlaceholderString = (domainFormat, months = 0) => {
  // 当日日付取得
  const m = moment(new Date(store.getters.requestedTime))
  if (months && Number(months)) {
    m.add(months, 'M')
  }
  const format = monthFormat(domainFormat)
  return m.format(format)
}

export const isReversal = (startDateString, endDateString) => {
  const startDate = moment(startDateString, 'YYYY/MM')
  const endDate = moment(endDateString, 'YYYY/MM')
  return startDate.isAfter(endDate, 'day')
}

export const monthFormat = (domainFormat) => {
  switch (domainFormat) {
    case 'date6YearMonthKanji':
      return 'YYYY年MM月'
    case 'date6YearMonthNoSlash':
      return 'YYYYMM'
    case 'date6YearMonth':
    default:
      return 'YYYY/MM'
  }
}
