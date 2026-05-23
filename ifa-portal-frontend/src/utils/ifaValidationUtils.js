import { getMessage } from '@/utils/errorHandler'

// 数字
function number() {
  return { pattern: '^[0-9]+$', message: getMessage('errors.required', ['数字']), validationType: 'number' }
}
// 数値(整数)
function numberInteger() {
  return { pattern: '^[+-]?\\d+$', message: getMessage('errors.required', ['整数']), validationType: 'numberInteger' }
}
// 数値(小数)
function numberFloat() {
  return { pattern: '^[+-]?(\\d+?\\.?\\d*|\\.\\d+)$', message: getMessage('errors.required', ['数値']), validationType: 'numberFloat' }
}
// 全角半角 (validate on server)
function fullWidthHalfWidth() {
  return { validationType: 'fullWidthHalfWidth' }
}
// 全角 (validate on server)
function fullWidth() {
  return { validationType: 'fullWidth' }
}
// メールアドレス
function mailAddress() {
  return { pattern: '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$', message: getMessage('errors.login.mailError'), validationType: 'mailAddress' }
}
// 半角英数字
function alphaNumber() {
  return { pattern: '^[0-9a-zA-Z]+$', message: getMessage('errors.required', ['英数字']), validationType: 'alphaNumber' }
}
// 英字（大文字）
function upperAlpha() {
  return { pattern: '^[A-Z]+$', message: getMessage('errors.required', ['英大文字']), validationType: 'upperAlpha' }
}
// 英数字記号A(+-_./@*#%)
function alphaNumberSpecialPatternA() {
  return { pattern: '^[\\w\\d\\+\\-\\_\\.\\/\\@\\*\\#\\%]+$', message: getMessage('errors.required', ['英数記号(+-_./@*#%)']), validationType: 'alphaNumberSpecialPatternA' }
}
// 英数字記号B(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<')
function alphaNumberSpecialPatternB() {
  return { pattern: '^[\\w\\d\\+\\-\\_\\.\\/\\@\\*\\#\\%\\!\\"\\$\\&\\(\\)\\=\\~\\^\\\\\\?\\>\\,\\|\\`\\[\\]\\{\\}\\:\\;\\<\\' + '\'' + ']+$', message: getMessage('errors.required', ['英数記号(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<\')']), validationType: 'alphaNumberSpecialPatternB' }
}
// 銘柄コード
function securityCode() {
  return { pattern: '^[0-9a-zA-Z.]+$', message: getMessage('errors.required', ['英数字または.']), validationType: 'securityCode' }
}
// 3桁区切り
function threeDigitsWithComma(self) {
  return { pattern: '^([0-9a-zA-Z]{3},?)+$', message: getMessage('errors.neqSize', [self.label ?? '値', '3']), validationType: 'threeDigitsWithComma' }
}
// 3桁区切り(数字のみ)
function threeDigitsNumberWithComma() {
  return { pattern: '^([0-9]{3},?)+$', validationType: 'threeDigitsNumberWithComma' }
}
// 4桁区切り(英数字のみ)
function fourDigitsAlphaNumberWithComma() {
  return { pattern: '^([0-9a-zA-Z]{4},?)+$', validationType: 'fourDigitsAlphaNumberWithComma' }
}
// 4桁区切り
function fourDigitsWithComma(self) {
  return { pattern: '^([0-9]{4},?)+$', message: getMessage('errors.neqSize', [self.label ?? '値', '4']), validationType: 'fourDigitsWithComma' }
}
// 英字
function alpha() {
  return { pattern: '^[a-zA-Z]+$', message: getMessage('errors.required', ['英字']), validationType: 'alpha' }
}
// URL
function url() {
  return { pattern: '^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-zA-Z\\u00a1-\\uffff0-9]-*)*[a-zA-Z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-zA-Z\\u00a1-\\uffff0-9]-*)*[a-zA-Z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-zA-Z\\u00a1-\\uffff]{2,})).?)(?::\\d{2,5})?(?:[/?#]\\S*)?$', message: getMessage('errors.accurately', ['URL']), validationType: 'url' }
}

export default {
  number,
  numberInteger,
  numberFloat,
  fullWidthHalfWidth,
  fullWidth,
  mailAddress,
  alphaNumber,
  upperAlpha,
  alphaNumberSpecialPatternA,
  alphaNumberSpecialPatternB,
  securityCode,
  threeDigitsWithComma,
  threeDigitsNumberWithComma,
  fourDigitsAlphaNumberWithComma,
  fourDigitsWithComma,
  alpha,
  url
}
