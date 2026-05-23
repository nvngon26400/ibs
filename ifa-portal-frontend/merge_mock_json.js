const fs = require('fs')
const path = require('path')
const root = path.resolve('./mock/json/')
const apiRes = {}
const allFile = fs.readdirSync(root, { withFileTypes: false })
allFile.forEach((file) => {
  const endpoint = path.basename(file, path.extname(file))
  apiRes[endpoint] = JSON.parse(fs.readFileSync(root + '/' + file, 'utf-8'))
})
fs.writeFileSync(root + '/../mock.json', JSON.stringify(apiRes), function(err) {
  if (err) throw err
})
