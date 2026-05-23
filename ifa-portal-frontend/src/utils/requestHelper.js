import actionMappings from '@/resource/action/ActionMappings.json'
import request from '@/utils/request'

/**
 * @param {string} actionId ActionMappings.js検索用のKey
 * @param {Object} data RequestModel（リクエスト時のパラメータ）
 * @param {number} index ActionMapping情報が配列の場合に指定
 */
export async function req(actionId, data, index = 0, actionType = '', header = 'application/json; charset=utf-8') {
  const actionMapping = actionMappings[actionId] instanceof Array
    ? actionMappings[actionId][index]
    : actionMappings[actionId]

  const config = {
    url: actionMapping.url,
    method: actionMapping.method,
    actionType: actionType,
    headerContentType: header
  }
  if (actionMapping.method === 'get') {
    config.params = data
  } else {
    config.data = data
  }
  const response = await request(config)
    .catch(error => { throw error })

  return response
}
