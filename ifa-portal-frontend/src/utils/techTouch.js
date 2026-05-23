export function techTouch(medUsers) {
  window.TechtouchObject = {
    ...window.TechtouchObject,
    startTechtouch: function() {
      // iframe内で動作させる場合は以下の1行を削除もしくはコメントアウトしてください
      try {
        if (window.top !== window.self) {
          return
        }
      } catch (e) {
        return
      }
      if ('TechtouchObject' in window && document.querySelector('script#techtouch-snippet')) {
        return
      }
      if ('TechtouchAddon' in window) {
        return
      }
      window.TechtouchObject = {
        organizationUuid: 'orga-67bd4abd-b81f-0e05-3a12-9fa2de2711c2'
      }
      const e = document.createElement('script')
      e.async = 1
      e.src = 'https://apps.techtouch.jp/script/orga-67bd4abd-b81f-0e05-3a12-9fa2de2711c2/main.js?projectUuid=proj-67bd4d35-998c-8d02-2105-ba4a17119e58'
      e.id = 'techtouch-snippet'
      const t = document.getElementsByTagName('script')[0]
      t.parentNode.insertBefore(e, t)
    }
  }
  if (medUsers) {
    // カスタムプロパティを使用する場合に設定してください
    window.TechtouchAdmin = {
      customProperty: {
        _data: {},
        get: function(t) {
          return this._data[t]
        },
        set: function(t, e) {
          this._data[t] = e
        },
        delete: function(t) {
          delete this._data[t]
        },
        keys: function() {
          return Object.keys(this._data)
        }
      }
    }
    // 以下で各カスタムプロパティのデータを設定してください
    window.TechtouchAdmin.customProperty.set('4f0ba022-953c-4a48-b0a0-fd1017702d97', medUsers.userId ? medUsers.userId : '') // ラベル「user_id」に対応
    window.TechtouchAdmin.customProperty.set('9b67c541-09f8-468d-a68c-bf35e71ba301', medUsers.privId ? medUsers.privId : '') // ラベル「priv_id」に対応
    window.TechtouchAdmin.customProperty.set('cf31c0ae-0bb2-4912-903a-2960f1f85ce0', medUsers.branchId ? medUsers.branchId : '') // ラベル「branch_id」に対応
    window.TechtouchAdmin.customProperty.set('a88df28a-61d2-4186-bd93-d46feade5df2', medUsers.brokerId ? medUsers.brokerId : '') // ラベル「broker_id」に対応
    window.TechtouchAdmin.customProperty.set('08f40af7-3c18-46dd-a443-496dae1f1499', medUsers.subBrokerId ? medUsers.subBrokerId : '') // ラベル「sub_broker_id」に対応
    window.TechtouchAdmin.customProperty.set('9286ec0d-1f14-4fb9-8966-e057ef987bde', medUsers.employeeId ? medUsers.employeeId : '') // ラベル「employee_id」に対応
  }
  // 以下はカスタムプロパティのデータをセットしてから実行してください
  window.TechtouchObject.startTechtouch()
}
