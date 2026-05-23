import moment from 'moment-timezone'
import Logger from '@/utils/ifaLog.js'
import store from '@/store'

/**
 * フォーマット済み日付文字列を返却する。
 * フォーマット可能の場合：'YYYY/MM/DD'
 * 空文字の場合：''
 * フォーマット不可の場合：null
 * @param {string} value フォーマット対象の文字列。
 * @return {string} 'YYYY/MM/DD' or '' or null
 */
export const getFormattedDateValue = (value, domainFormat = 'date8') => {
  if (!value) return value

  const values = []
  // 入力形式: 'YYYY/MM/DD' or 'YYYY-MM-DD'
  if (value.match(/^(\d{4}|\d{2})[\/-]\d{1,2}[\/-]\d{1,2}?.*$/)) {
    const dateValues = value.match(/^(\d{4}|\d{2})[\/-](\d{1,2})[\/-](\d{1,2})?.*$/)
    values.push(
      `20${dateValues[1]}`.slice(-4),
      dateValues[2],
      dateValues[3])
  // 入力形式: 'YYYYMMDD'
  } else if (value.match(/^(\d{4}|\d{2})\d{2}\d{2}.*$/)) {
    const dateValues = value.match(/^(\d{4}|\d{2})(\d{2})(\d{2}).*$/)
    values.push(
      `20${dateValues[1]}`.slice(-4),
      dateValues[2],
      dateValues[3])
  } else {
    return null
  }
  const m = moment({
    years: values[0],
    months: values[1] - 1,
    date: values[2]
  })

  if (m && m.isValid()) {
    const format = dateFormat(domainFormat)
    return m.format(format)
  } else {
    return null
  }
}

/**
 * フォーマット済み時刻文字列を返却する。
 * フォーマット可能の場合：'HH/mm/ss'
 * 空文字の場合：''
 * フォーマット不可の場合：null
 * @param {string} value フォーマット対象の文字列。
 * @return {string} 'HH:mm:ss' or '' or null
 */
export const getFormattedTimeValue = (value, domainFormat = 'time6') => {
  if (!value) return value

  const values = []
  // 入力形式: 'hh:mm:ss' or 'hh:mm'
  if (value.match(/^(\d{1,2}:\d{1,2}:\d{1,2}|\d{1,2}:\d{1,2})$/)) {
    const timeValues = value.split(':')
    values.push(
      timeValues[0],
      timeValues[1],
      timeValues[2] || '00'
    )
  // 入力形式: 'hhmmss' or 'hhmm'
  } else if (value.length === 6 || value.length === 4) {
    const timeValues = value.match(/^(\d{2})(\d{2})(\d{2})?$/)
    if (timeValues && timeValues.length >= 2) {
      values.push(
        timeValues[1],
        timeValues[2],
        timeValues[3] || '00'
      )
    } else {
      return null
    }
  } else {
    return null
  }

  const m = moment({
    hours: values[0],
    minutes: values[1],
    seconds: values[2]
  })

  if (m && m.isValid()) {
    const format = timeFormat(domainFormat)
    return m.format(format)
  } else {
    return null
  }
}

/**
 * フォーマット済み日時文字列を返却する。
 * フォーマット可能の場合：'YYYY/MM/DD HH:mm:ss'(default) or 'YYYY/MM/DD HH:mm'
 * 空文字の場合：''
 * フォーマット不可の場合：null
 * @param {string} value フォーマット対象の文字列。
 * @return {string} 'YYYY/MM/DD HH:mm:ss' or 'YYYY/MM/DD HH:mm' or '' or null
 */
export const getFormattedDateTimeValue = (value, domainFormat = 'datetime14', timeZone = 'Asia/Tokyo') => {
  if (!value) return value

  // 入力形式: ISO8601
  // サポートする形式
  // フォーマット: {年月日}T{時分秒}{.ミリ秒}{タイムゾーン}
  // {年月日}: YYYY-MM-DD or YYYYMMDD (月や日の省略はサポートしない(YYYYMM, YYYY など))
  //           年は4桁､月と日はそれぞれ2桁で指定する
  // {時分秒}: hh:mm:ss or hhmmss (時や秒の省略はサポートしない(hhmm, hh など))
  //           時､分､秒はそれぞれ2桁で指定する
  // {.ミリ秒}: 省略可能｡桁数の制限はなし (出力フォーマットではミリ秒をサポートしないため､情報は失われる)
  // {タイムゾーン}: UTC の場合は'Z'｡UTCからの時差を +hh:mm or -hh:mm or +hhmm or -hhmm で指定する (分の省略はサポートしない(+hh, -hh など))
  // 例: 2023-12-21T12:34:56+09:00 (timezone = 'Asia/Tokyo')
  // 例: 2023-12-21T12:34:56Z (timezone = 'Europe/london'(UTC))
  // 例: 20231221T123456-0500 (timezone = 'America/New_York')
  // 例: 2023-12-21T12:34:56.123456+09:00 (timezone = 'Asia/Tokyo')
  if (value.match(/^\d{4}-?\d{2}-?\d{2}T\d{2}:?\d{2}:?\d{2}(\.[0-9]+)?(Z|[+-]\d{2}:?\d{2})$/)) {
    const m = moment(value)

    // サポートしているタイムゾーンは次を参照
    // https://gist.github.com/diogocapela/12c6617fc87607d11fd62d2a4f42b02a
    try {
      m.tz(timeZone)
    } catch (err) {
      Logger.error(`Error: ${timeZone} is not supported`)
    }

    if (m && m.isValid()) {
      const format = dateFormat(domainFormat)
      return m.format(format)
    } else {
      return null
    }
  }

  // 日付と時間を分離する
  // 日付と時間が空白で区切られた形式をサポートする
  let dt = value.split(' ')
  if (dt.length !== 2) {
    // 空白で区切れない場合は､'YYYYMMDDHHmmss' または 'YYMMDDHHmmss' をサポートする
    const d = value.substring(0, value.length - 6)
    const t = value.slice(-6)
    dt = [d, t]
  }

  const values = []
  // 日付部をパースする
  // 書式が 'YYYY/MM/DD' または 'YY/MM/DD' のように '/' または '-' で区切られている場合
  //   -> 年は2桁または4桁､月及び日は1桁または2桁をサポートする
  // 書式が 'YYYYMMDD' のようにセパレータで区切られていない場合
  //   -> 年は4桁または2桁､月及び日は2桁のみをサポートする
  // 上記以外の書式の場合は､エラーと判断して入力の value 値をそのままリターンする
  if (dt[0].match(/^(\d{4}|\d{2})[\/-]\d{1,2}[\/-]\d{1,2}?$/)) {
    const dateValues = dt[0].split(/[\/-]/)
    values.push(
      `20${dateValues[0]}`.slice(-4),
      dateValues[1],
      dateValues[2])
  } else if (dt[0].match(/^(\d{4}|\d{2})\d{2}\d{2}$/)) {
    const dateValues = dt[0].match(/^(\d{4}|\d{2})(\d{2})(\d{2})$/)
    values.push(
      `20${dateValues[1]}`.slice(-4),
      dateValues[2],
      dateValues[3])
  } else {
    return null
  }

  // 時間部をパースする
  // 書式が 'HH:mm:ss' または 'HH:mm' のように ':' で区切られている場合
  //   -> 時分秒は1桁または2桁をサポートする
  // 書式が 'HHmmss' のようにセパレータで区切られていない場合
  //   -> 時分秒は2桁のみをサポートする
  // 秒は省略することができる｡省略した場合､秒は '00' として扱う
  // 上記以外の書式の場合は､エラーと判断して入力の value 値をそのままリターンする
  if (dt[1]) {
    if (dt[1].match(/^(\d{1,2}:\d{1,2}:\d{1,2}|\d{1,2}:\d{1,2})$/)) {
      const timeValues = dt[1].split(':')
      values.push(
        timeValues[0],
        timeValues[1],
        timeValues[2] || '00'
      )
    } else if (dt[1].length === 6 || dt[1].length === 4) {
      const timeValues = dt[1].match(/^(\d{2})(\d{2})(\d{2})?$/)
      if (timeValues && timeValues.length >= 2) {
        values.push(
          timeValues[1],
          timeValues[2],
          timeValues[3] || '00'
        )
      } else {
        return null
      }
    } else {
      return null
    }
  }

  const m = moment({
    years: values[0],
    months: values[1] - 1,
    date: values[2],
    hours: values[3] || '00',
    minutes: values[4] || '00',
    seconds: values[5] || '00'
  })

  if (m && m.isValid()) {
    const format = dateFormat(domainFormat)
    return m.format(format)
  } else {
    return null
  }
}

export const getPlaceholderString = (domainFormat, days = 0) => {
  const m = moment(new Date(store.getters.requestedTime))
  if (days && Number(days)) {
    m.add(days, 'd')
  }
  const format = dateFormat(domainFormat)
  return m.format(format)
}

export const isReversal = (startDateString, endDateString) => {
  const startDate = moment(startDateString, 'YYYY/MM/DD')
  const endDate = moment(endDateString, 'YYYY/MM/DD')
  return startDate.isAfter(endDate, 'day')
}

export const dateFormat = (domainFormat) => {
  switch (domainFormat) {
    case 'datetime14':
      return 'YYYY/MM/DD HH:mm:ss'
    case 'datetime12':
      return 'YYYY/MM/DD HH:mm'
    case 'date8Kanji':
      return 'YYYY年MM月DD日'
    case 'date6':
      return 'YY/MM/DD'
    case 'date8':
    default:
      return 'YYYY/MM/DD'
  }
}

export const timeFormat = (domainFormat) => {
  switch (domainFormat) {
    case 'time4':
      return 'HH:mm'
    case 'time6':
    default:
      return 'HH:mm:ss'
  }
}

export const monthsBefore = (date, n) => {
  const beforetDate = date
  const originalDate = beforetDate.getDate()
  beforetDate.setMonth(beforetDate.getMonth() - n)

  if (beforetDate.getDate() !== originalDate) {
    beforetDate.setDate(0)
  }

  return beforetDate
}

// 期間指定のバリデーションチェック処理
export function validateDateRangeFromTo(period, n = 6, isEqual = false) {
  // 以下の条件の時エラー
  // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差がnヶ月より大きい
  if (period.length > 0) {
    // nヶ月前の日付を算出
    const startDate = parseDate(period[0])
    const endDate = parseDate(period[1])
    const y = endDate.getFullYear()
    const m = endDate.getMonth()
    let d = endDate.getDate()
    const temp = new Date(y, m - (n - 1), 0)
    const lastD = temp.getDate()
    if (d > lastD) {
      d = lastD
    }
    const beforeNMonth = new Date()
    beforeNMonth.setFullYear(y, m - n, d)
    beforeNMonth.setHours(0, 0, 0, 0)

    // 入力チェック
    let isError = false
    if (isEqual) {
      isError = startDate <= beforeNMonth
    } else {
      isError = startDate < beforeNMonth
    }
    return isError
  }
}

// 期間指定のバリデーションチェック処理
export function validateDateRangeBeforeMonths(period, n = 6, isEqual = false) {
  // 以下の条件の時エラー
  // リクエスト.期間指定（From）がシステム日付-nヶ月より小さい
  // リクエスト.期間指定（To）がシステム日付-nヶ月より小さい
  if (period.length > 0) {
    const startDate = parseDate(period[0])
    const endDate = parseDate(period[1])

    // システム日付-nヶ月の算出
    const systemDate = parseDate(store.getters.requestedTime?.split(' ')?.[0])
    const beforeSixMonth = new Date(systemDate)
    beforeSixMonth.setMonth(beforeSixMonth.getMonth() - n)

    // 入力チェック
    let isError = false
    if (isEqual) {
      isError = startDate <= beforeSixMonth || endDate <= beforeSixMonth
    } else {
      isError = startDate < beforeSixMonth || endDate < beforeSixMonth
    }
    return isError
  }
}

export function parseDate(dateStr) {
  const date = new Date()
  const params = dateStr.split('/')
  date.setFullYear(params[0], params[1] - 1, params[2])
  date.setHours(0, 0, 0, 0)
  return date
}
