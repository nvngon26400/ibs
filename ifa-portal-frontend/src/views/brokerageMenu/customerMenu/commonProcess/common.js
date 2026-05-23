exports.getBusinessDay = (n, flag) => {
  // n営業日後の日付を求める
  const dt = new Date()
  dt.setDate(dt.getDate() + n)
  // 翌日または翌々日が土日の場合は2日シフトして補正
  if (dt.getDay() === 6 || dt.getDay() === 0) {
    if (flag) {
      dt.setDate(dt.getDate() + 2)
    } else {
      dt.setDate(dt.getDate() - 2)
    }
  }
  return dt.getFullYear().toString().slice(-2) + '/' +
             (('0' + (dt.getMonth() + 1)).slice(-2)) + '/' +
             ('0' + dt.getDate()).slice(-2)
}

exports.getBusinessDayYYYYMMDD = (n, flag) => {
  // n営業日後の日付を求める
  const dt = new Date()
  dt.setDate(dt.getDate() + n)
  // 翌日または翌々日が土日の場合は2日シフトして補正
  if (dt.getDay() === 6 || dt.getDay() === 0) {
    if (flag) {
      dt.setDate(dt.getDate() + 2)
    } else {
      dt.setDate(dt.getDate() - 2)
    }
  }
  return dt.getFullYear().toString() + '/' +
             (('0' + (dt.getMonth() + 1)).slice(-2)) + '/' +
             ('0' + dt.getDate()).slice(-2)
}

// 画面表示時のタイムスタンプを求める
exports.getDatetimeOfToday = () => {
  const dt = new Date()
  return dt.getFullYear() + '/' +
    (('0' + (dt.getMonth() + 1)).slice(-2)) + '/' +
    ('0' + dt.getDate()).slice(-2) + ' ' +
    ('0' + dt.getHours()).slice(-2) + ':' +
    ('0' + dt.getMinutes()).slice(-2) + ':' +
    ('0' + dt.getSeconds()).slice(-2)
}
