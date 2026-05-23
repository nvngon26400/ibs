module.exports = function(req, res, next) {
  if (req.method === 'POST') {
    req.method = 'GET' // GETに偽装
    if (req.url === '/commonCodeCodeList') {
      req.url += '_' + req.body['codeListId'] + '_' + req.body['selectPattern'] + '_' + req.body['dispPattern']
    }
    if (req.url === '/commonCodeToValue') {
      req.url += '_' + req.body['codeListId'] + '_' + sanitizeCodekey(req.body['codeKey']) + '_' + req.body['dispPattern']
    }
    if (req.url === '/marketList' && req.body['brandCode'] === '2002') {
      req.url += '_2'
    }
    if (req.url === '/ifaCustomerPortalInitializeA001') {
      const account = req.body['butenCode'] + '_' + req.body['accountNumber']
      const postfix = [
        'Z52_0001774', 'Z52_0001776', 'Z41_0001848', 'Z52_0001784',
        'Z56_0596799', '129_0001810', 'Z44_0001904', 'Z52_0001902']
        .find(a => a === account) ?? 'Z52_0001774'
      req.url += '_' + postfix
    }
    if (req.url === '/ifaBrandSearchSearchBrandA002') {
      const len = req.body['search'].length
      req.url += len === 3 ? '_150' : len === 2 ? '_50' : len === 5 ? '_0' : '_1'
    }
    if (req.url === '/ifaBrandSearchInitializeA001') {
      req.url += req.body['brandCode'] === '1111' ? '_1111' : ''
    }
    if (req.url === '/ifaLinkUrlUrlAcquire') {
      req.url += '_' + req.body['urlId'] + '_' + req.body['patternId']
    }
  }
  next()
}

const sanitizeCodekey = function(codeKey) {
  let ret = codeKey
  if (!codeKey) {
    ret = 'Null'
  } else {
    ret = codeKey
      .replace(/ /g, 'Space')
      .replace(/\*/g, 'Asterisc')
  }
  return ret
}
