import request from '@/utils/request'

export const getCustomerInfo = (butenCode, accountNumber) => {
  return request.post(
    '/customerlist',
    {
      butenCode: butenCode,
      accountNumber: accountNumber
    }
  ).then((data) => {
    return data
  }).catch((error) => {
    throw error
  })
}
