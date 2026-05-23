/**
 * @param {string} fileName 出力CSVファイル名
 * @param {Array} gridColModel GridTableのカラム定義（colModel）
 * @param {Array} gridData GridTableのデータモデル（dataModel）
 */
export function generateCsvFromGrid(fileName, gridColModel, gridData) {
  let csv = '\ufeff'
  let header = ''
  gridColModel.forEach(col => {
    header += col.title + ','
  })
  csv += header.slice(0, -1) + '\n'

  gridData.data.forEach(data => {
    let line = ''
    gridColModel.forEach(col => {
      line += data[col.dataIndx] + ','
    })
    csv += line.slice(0, -1) + '\n'
  })
  const blob = new Blob([csv], { type: 'text/csv' })
  const link = document.createElement('a')
  link.href = window.URL.createObjectURL(blob)
  const today = new Date()
  link.download = fileName + '_' + today.getFullYear() + (today.getMonth() + 1) + today.getDate() + today.getTime() + '.csv'
  link.click()
}
