<template>
  <div
    v-show="visible"
    class="external-link-wrapper"
    :class="{
      'external-link-disabled': isDisabled,
      'external-link-enabled': !isDisabled
    }"
  >
    <el-link
      v-if="dispLink"
      :underline="false"
      type="primary"
      class="external-link"
      @click="openWindow()"
    > {{ linkName }}
    </el-link>
    <el-icon
      v-if="externalLinkIconVisible"
      class="external-link-icon"
      @click="openWindow()"
    >
      <img
        v-if="linkIconImage === '' || iconName === 'externalLink'"
        src="@/assets/icons/external-link.svg"
      >
      <component
        :is="iconName"
        v-else
      ></component>
    </el-icon>

    <ifa-requester
      :id="requesterId"
      action-id="ifaLinkUrlUrlAcquire"
      action-type="requestAction"
      :request-model="ifaLinkRequestModel"
      @response-handler="responseHandlerA001($event)"
      @response-error-handler="responseErrorHandlerA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { IfaLinkRequestModel } from './IfaLinkRequestModel'
import { notifyMessage } from '@/utils/errorHandler'
export default {
  props: {
    visible: {
      type: Boolean,
      required: false,
      default: true
    },
    urlId: {
      type: Number,
      required: false,
      default: 0
    },
    patternId: {
      type: Number,
      required: false,
      default: 1
    },
    httpMethod: {
      type: String,
      required: false,
      default: 'GET'
    },
    paramUrl: {
      type: String,
      required: false,
      default: ''
    },
    dispName: {
      type: String,
      required: false,
      default: ''
    },
    linkIconImage: {
      type: String,
      required: false,
      default: ''
    },
    invalid: {
      type: Boolean,
      required: false,
      default: false
    },
    dispLink: {
      type: Boolean,
      required: false,
      default: true
    },
    urlObject: {
      type: Object,
      required: false,
      default: null
    },
    paramObject: {
      type: Object,
      required: false,
      default: null
    },
    manualInit: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  emits: ['urlChanged', 'urlChangeError'],
  data() {
    return {
      linkUrl: '', // リンクURL
      linkIcon: '', // アイコン
      linkName: '', // 画面に表示されるリンクラベル
      extLinkFlag: '0', // 外部リンクフラグ
      request: '',
      requestProps: {
        urlId: this.urlId,
        patternId: this.patternId,
        httpMethod: this.httpMethod
      },
      ifaLinkRequestModel: {}
    }
  },
  computed: {
    iconName() {
      return this.linkIconImage ?? ''
    },
    isDisabled() {
      return this.invalid === true
    },
    requesterId() {
      // 7文字のランダムな文字列を生成する [0-9a-z]{7}
      const uid = Math.random().toString(36).slice(-7)
      return `ifaLinkUrlUrlAcquire_${this.urlId}_${this.patternId}_${uid}`
    },
    externalLinkIconVisible() {
      return this.extLinkFlag === '1' ||
        (this.urlId === 0 && this.paramUrl && this.iconName)
    },
    target() {
      return 'link' + (this.urlId === 0 ? '' : this.urlId.toString())
    }
    // 動的アイコン表示
    // styles() {
    //   return {
    //     '--background-image': this.linkIcon
    //   }
    // },
    // バインドするクラスを生成
    // classes() {
    //   return {
    //     'external-link-icon': true
    //   }
    // }
  },
  watch: {
    paramUrl: {
      handler(newVal) {
        this.updateLinkUrl(newVal)
      }
    },
    urlId() {
      if (!this.manualInit) {
        this.initializeRequestUrl()
      }
    }
  },
  created() {
    if (!this.manualInit) {
      this.initializeRequestUrl()
    }
    this.linkIcon = "url('" + this.image + "')"
  },
  methods: {
    trigger() {
      this.initializeRequestUrl()
    },
    updateLinkUrl(newParamUrl) {
      if (this.urlId === 0) {
        this.linkUrl = newParamUrl
      }
    },
    openWindow() {
      // cursor: not-allowed; と pointer-events: none; が同時に使えなかったため
      // disabled の時は､ここでクリックイベントを無効にする
      if (this.isDisabled) return

      if (this.httpMethod === 'GET') {
        window.open(this.linkUrl, this.target)
      } else {
        const newWindow = window.open('', this.target)

        if (newWindow) {
          const linkForm = document.createElement('form')
          linkForm.target = this.target
          linkForm.method = 'POST'
          linkForm.action = this.linkUrl

          // request と paramObject をマージする｡
          // key が同じ場合は､ paramObject を優先する
          const objs = Object.assign({}, this.request, this.paramObject)
          const params = Object.entries(objs)
            .map(e => ({ name: e[0], value: e[1] }))
          if (params) {
            for (const param of params) {
              const linkInput = document.createElement('input')
              linkInput.type = 'hidden'
              linkInput.name = param.name
              linkInput.value = param.value
              linkForm.appendChild(linkInput)
            }
          }

          newWindow.document.body.appendChild(linkForm)
          linkForm.submit()
        } else {
          const label = this.$store.getters.pageInfo.label
          notifyMessage(2, 'ポップアップを許可してください｡', label)
        }
      }
    },
    initializeRequestUrl() {
      if (this.urlId !== 0) {
        this.$nextTick(() => {
          this.ifaLinkRequestModel = new IfaLinkRequestModel(this.requestProps)
          this.$el.querySelector('#' + this.requesterId).click()
        })
      }

      if (this.linkUrl === '') {
        if (this.paramUrl !== '') {
          this.linkUrl = this.paramUrl
        } else {
          // TODO:エラー処理
        }
      }

      if (this.dispName !== '') {
        this.linkName = this.dispName
      }

      if (this.urlObject) {
        this.setUrlObject()
      }

      if (this.paramObject) {
        this.setUrlParameter()
      }
    },
    responseHandlerA001(response) {
      this.linkUrl = response.linkUrl

      if (this.dispName !== '') {
        this.linkName = this.dispName
      } else {
        this.linkName = response.dispName
      }

      if (response.postRequest) {
        this.request = response.postRequest
      }

      this.extLinkFlag = response.extLinkFlag

      if (this.urlObject) {
        this.setUrlObject()
      }

      if (this.paramObject) {
        this.setUrlParameter()
      }

      this.$emit('urlChanged')
    },
    responseErrorHandlerA001(response) {
      this.$_logError('responseErrorHandlerA001', response)

      this.$emit('urlChangeError')
    },

    // URLとオブジェクトを連結
    setUrlObject() {
      let count = 0
      let replaceValue = ''
      for (const address in this.urlObject) {
        if (address === 'countryCode') {
          replaceValue = this.getCountryUrl(this.urlObject[address])
        } else {
          replaceValue = this.urlObject[address]
        }
        this.linkUrl = this.linkUrl.replace('{' + count.toString() + '}', replaceValue)
        count = count + 1
      }
    },

    // URLにパラメータを設定
    // GET の場合のみ処理､PUT の場合は windowOpen 時に処理する
    setUrlParameter() {
      const entries = Object.entries(this.paramObject)
      if (this.httpMethod === 'GET' && entries.length > 0) {
        this.linkUrl = this.linkUrl + '?' +
        entries.map((e) => {
          const key = e[0]
          const value = encodeURI(e[1])
          return `${key}=${value}`
        })
          .join('&')
      }
    },
    getCountryUrl(countryCode) {
      switch (countryCode) {
        case 'US':
          return 'usstock'
        case 'HK':
          return 'hk_cash'
        case 'KR':
          return 'kor_cash'
        case 'RU':
          return 'ru_cash'
        case 'VN':
          return 'vn_cash'
        case 'ID':
          return 'id_cash'
        case 'SG':
          return 'sg_cash'
        case 'TH':
          return 'th_cash'
        case 'MY':
          return 'my_cash'
        default:
          break
      }
    }
  }
}

</script>

<style lang="scss">
.external-link-wrapper {
  display: inline-block
}
.external-link {
  padding: 3px 7px;
  text-decoration: underline;
  &:hover, &:focus {
    text-decoration: underline;
      }
}
.external-link-icon {
  --background-image: url(~@/assets/icons/external-link.svg);
  content: "";

  height: 14px;
  width: 14px;
  display: inline-block;
  vertical-align: middle;
}
.external-link-enabled {
  cursor: pointer;
  .el-link {
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  &:hover {
    opacity: 0.7;
  }
}
.external-link-disabled {
  .el-link {
    // el-link が pointer アイコンにしてしまうので強制的にアイコンを変更
    cursor: not-allowed;
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  cursor: not-allowed;
  &:hover {
    opacity: 0.7;
  }
}
</style>
