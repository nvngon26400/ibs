import store from '@/store'

// 共通リクエストBodyモデル。
export class RequestModel {
  // コンストラクタ。
  constructor(data) {
    this.frameworkSessionId = store.getters.user_sessionId
    this.authToken = store.getters.user_token
    this.postBody = data
  }
}
