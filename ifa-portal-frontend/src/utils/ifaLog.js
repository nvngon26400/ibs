import store from '@/store'

function getCommonHeader(logLevel) {
  const currentTime = new Date().toISOString()
  const sessionId = store.getters.frameworkSessionId || 'unknown'
  const screenId = store.getters.pageInfo.menuId || 'unknown'
  return `[${currentTime}][${logLevel}][SessionID: ${sessionId}][ScreenID: ${screenId}]`
}

const LOG_LEVEL = {
  ERROR: 0,
  WARN: 1,
  INFO: 2,
  DEBUG: 3
}
const logLevel = process.env.VUE_APP_LOG_LEVEL

function debug(...messages) {
  if (LOG_LEVEL.DEBUG <= logLevel) {
    console.debug(getCommonHeader('DEBUG'), ...messages)
  }
}

function info(...messages) {
  if (LOG_LEVEL.INFO <= logLevel) {
    console.info(getCommonHeader('INFO'), ...messages)
  }
}

function warn(...messages) {
  if (LOG_LEVEL.WARN <= logLevel) {
    console.warn(getCommonHeader('WARN'), ...messages)
  }
}

function error(...messages) {
  if (LOG_LEVEL.ERROR <= logLevel) {
    console.error(getCommonHeader('ERROR'), ...messages)
  }
}

export default {
  debug,
  info,
  warn,
  error
}
