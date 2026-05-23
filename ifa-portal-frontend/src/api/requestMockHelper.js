import request from '@/utils/request'

export function getList(params) {
  return request({
    url: params.url,
    method: 'get',
    params
  })
}
