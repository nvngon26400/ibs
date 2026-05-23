<template>
  <div>
    <el-row>
      <el-card style="background-color: #eee; margin-bottom: 0.5rem; padding: 8px 15px;">
        <el-row>
          <!-- 銘柄 -->
          <el-col
            :span="4"
          >
            <span
              class="search_header"
            >銘柄：</span>
            <el-input
              v-model="searchWord"
              :disabled="form.tradeCd===''"
              clearable
              style="width: 100px;"
              size="small"
              @clear="resetAll()"
              @input="handleSearchWordInput"
            ></el-input>
          </el-col>

          <!-- 株価表示 -->
          <el-col
            v-if="form.brandCode === ''"
            :span="3"
            style="padding-left: 0.5rem;"
          >
            <ifa-button
              id="btnStockPriceDisplay"
              ref="searchBtn"
              text="株価表示"
              :small="true"
              :disabled="searchWord === ''"
              action-type="requestAction"
              action-id="SUB0202_0303-01_1#A003"
              :request-model="a003RequestModel"
              :pre-request-handler="stockPriceDisplayA003Pre()"
              @response-handler="stockPriceDisplayA003Res($event)"
            ></ifa-button>
          </el-col>

          <!-- 銘柄検索 -->
          <el-col
            v-if="form.brandCode === ''"
            :span="3"
          >
            <div
              style="padding-top: 0.25rem;"
              class="inner-link"
            >
              <el-link
                :underline="false"
                class="inner-link_add"
                :disabled="form.tradeCd===''"
                @click="openDialog"
              >銘柄検索</el-link>
            </div>
          </el-col>
          <!-- 銘柄名 -->
          <el-col
            v-if="form.brandCode !== ''"
            :span="4"
          >
            <span
              class="search_header"
            >{{ $_out(form.brandName) }}</span>
          </el-col>
          <!-- 上場市場 -->
          <el-col
            v-if="form.brandCode !== ''"
            :span="5"
            style="display: flex;"
          >
            <div style="min-width: 80px;">
              <span class="search_header __left">上場市場:</span>
            </div>
            <div>
              <span class="search_header">{{ $_out(form.listedMarket) }}</span>
            </div>
          </el-col>

          <!-- 取引注意情報・株価チャート -->
          <el-col
            v-if="form.brandCode !== ''"
            style="text-align:right;"
            :span="5"
          >
            <span v-if="form.tradeLimit">
              <el-icon style="color: red; vertical-align: middle;"><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                :param-url="form.tradeLimitUrl"
                disp-name="取引注意情報"
                link-icon-image="externalLink"
              ></ifa-link>
            </span>
            <span style="padding-left: 8px;">
              <div
                class="external-link-wrapper"
                :class="{
                  'external-link-disabled': isDisabled,
                  'external-link-enabled': !isDisabled
                }"
              >
                <el-link
                  :underline="false"
                  type="primary"
                  class="external-link"
                  @click="ifaForeignBrandGetNewMainSite(50,'1','POST',form.brandCode)"
                > {{ "株価チャート" }}
                </el-link>
                <el-icon
                  class="external-link-icon"
                  @click="ifaForeignBrandGetNewMainSite(50,'1','POST',form.brandCode)"
                >
                  <img
                    src="@/assets/icons/external-link.svg"
                  >
                </el-icon>
              </div>
            </span>
          </el-col>

          <!-- 更新 -->
          <el-col
            v-if="form.brandCode !== ''"
            :span="2"
            class="update-button"
          >
            <ifa-button
              id="btnUpdate"
              text="更新"
              :small="true"
              icon="RefreshRight"
              action-type="requestAction"
              action-id="SUB0202_0303-01_1#A005"
              :request-model="a005RequestModel"
              @response-handler="updateA005($event)"
            ></ifa-button>
          </el-col>
        </el-row>

        <el-row
          :gutter="20"
          style="margin-top: 1rem;"
        >
          <el-col
            :span="7"
            :offset="1"
          >
            <div class="info_xs">
              <span class="info-item__header __left">現在値:</span>
              <span class="info-item__current __right">
                <el-row v-if="form.brandCode !== ''">
                  <el-col :span="21">
                    <span>{{ $_withCommaZeroPadding($_out(form.priceBasicInfo ? form.priceBasicInfo[0].currentPrice : ''), 4) }}</span>
                  </el-col>
                  <el-col :span="3">
                    <ifa-text
                      v-if="form.priceBasicInfo ? form.priceBasicInfo[0].currentPrice : '' > 0"
                      :class="[tickColor]"
                      code-list-id="CURRENT_TICK"
                      :disp-pattern="1"
                      :code-key="form.priceBasicInfo ? form.priceBasicInfo[0].tick : ''"
                    ></ifa-text>
                  </el-col>
                </el-row>
                <el-row
                  v-else
                  class="__color_empty __center"
                >
                  <span>-</span>
                </el-row>
              </span>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="info_xs">
              <span class="info-item__header __left">始値:</span>
              <span
                v-if="form.brandCode !== ''"
                class="info-item__value __right"
              >{{ $_withCommaZeroPadding($_out(form.priceBasicInfo ? form.priceBasicInfo[0].start : ''), 4) }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >-</span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="info_xs">
              <span class="info-item__header __left">高値:</span>
              <span
                v-if="form.brandCode !== ''"
                class="info-item__value __right"
              >{{ $_withCommaZeroPadding($_out(form.priceBasicInfo ? form.priceBasicInfo[0].high : ''), 4) }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >-</span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="info_xs">
              <span class="info-item__header __left">安値:</span>
              <span
                v-if="form.brandCode !== ''"
                class="info-item__value __right"
              >{{ $_withCommaZeroPadding($_out(form.priceBasicInfo ? form.priceBasicInfo[0].low : ''), 4) }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >-</span>
            </div>
          </el-col>
        </el-row>

        <el-row
          :gutter="20"
          style="padding-top: 0.5rem;"
        >
          <el-col
            :span="7"
            :offset="1"
          >
            <div class="info_xs">
              <span class="info-item__header __left">前日比</span>
              <span
                v-if="form.brandCode !== ''"
                class="info-item__value __right"
              >
                <el-row>
                  <el-col :span="12">
                    <span :class="[ratioColor(form.priceBasicInfo ? form.priceBasicInfo[0].diff : '')]">{{ $_withCommaZeroPadding($_out(form.priceBasicInfo ? form.priceBasicInfo[0].diff : ''), 2) }}</span>
                    <span> (</span>
                    <span :class="[ratioColor(form.priceBasicInfo ? form.priceBasicInfo[0].ratio : '')]">{{ $_withCommaZeroPadding($_out(form.priceBasicInfo ? form.priceBasicInfo[0].ratio : ''), 2) }}%</span>
                    <span>) </span>
                  </el-col>
                  <el-col :span="11">
                    <span style="font-size :12px;">({{ $_out(currentDateTime) }} {{ $_out(form.timeZoneAbbreviatedName) }})</span>
                  </el-col>
                </el-row>
              </span>
              <span
                v-else
                class="info-item__empty __right"
              >
                <el-row>
                  <el-col :span="12">
                    <span>( -%)</span>
                  </el-col>
                  <el-col :span="12">
                    <span>(--/--/-- --:--)</span>
                  </el-col>
                </el-row>
              </span>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="info_xs">
              <span class="info-item__header __left">前日終値:</span>
              <span
                v-if="form.brandCode !== ''"
                class="info-item__value __right"
                style="font-size: 10.5px"
              >{{ $_withCommaZeroPadding($_out(form.priceBasicInfo ? form.priceBasicInfo[0].last : ''), 4) }} ({{ $_out($_getFormattedDateValue(form.priceBasicInfo ? form.priceBasicInfo[0].lastDate : '', 'date6')) }})</span>
              <span
                v-else
                class="info-item__empty __right"
              >- (--/--/--)</span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="info_xs">
              <span class="info-item__header __left">出来高:</span>
              <span
                v-if="form.brandCode !== ''"
                class="info-item__value __right"
              >{{ $_withCommaNoneZeroPadding($_out(form.priceBasicInfo ? form.priceBasicInfo[0].volume : '')) }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >-</span>
            </div>
          </el-col>
          <el-col
            :span="5"
            style="text-align: right;"
          >
            <span class="data-provider">© REFINITIV</span>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
    <!--銘柄検索ダイアログ-->
    <ifa-foreign-stock-brand-search-popup
      ref="ifaForeignStockBrandSearchPopup"
      :is-margin="true"
      :is-visible="brandSerachIsVisible"
      :params="a011RequestModel"
      @close-modal="handleCloseModal"
      @result="brandCodeSetA013($event)"
    ></ifa-foreign-stock-brand-search-popup>
    <ifa-requester
      id="IfaForeignBrandGetNewMainSite"
      action-id="SUB0202_0303-01_1#A023"
      action-type="requestAction"
      :request-model="ifaLinkRequestModel"
      @response-handler="responseHandlerGetNewMainSite($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaForeignStockBrandSearchPopup from '@/views/common/IfaForeignStockBrandSearchPopup'
import { IfaLinkRequestModel } from '../js/IfaLinkRequestModel'
import { notifyMessage } from '@/utils/errorHandler'
export default {
  components: {
    IfaForeignStockBrandSearchPopup
  },
  props: {
    // 銘柄入力可能:1 入力不可：1以外
    enableInput: { type: String, default: '1' },
    form: { type: Object, default: null },
    a003RequestModel: { type: Object, default: null },
    a005RequestModel: { type: Object, default: null },
    a011RequestModel: { type: Object, default: null }
  },
  emits: ['change', 'a003Pre', 'a003Res', 'resetBrandSearch', 'a005'],
  data() {
    return {
      searchWord: '',
      brandSerachIsVisible: false,
      request: '',
      requestProps: {},
      ifaLinkRequestModel: {}
    }
  },
  computed: {
    currentDateTime() {
      const formattedDateTimeValue = this.$_getFormattedDateTimeValue(this.form.priceBasicInfo ? this.form.priceBasicInfo[0].currentDateTime : '')
      return formattedDateTimeValue ? formattedDateTimeValue.slice(2, 16) : formattedDateTimeValue
    },
    // v-modelをcomputed->変数のように間接的に扱う場合はgetterとsetterの両方が必要。
    // 参考: https://qiita.com/shizen-shin/items/a48cfbe7c88cc9f42e30
    // 前日比の色を算出する
    ratioColor() {
      return function(value) {
        const n = Number(value)
        return n > 0 ? 'font-color__plus __bold' : n < 0 ? 'font-color__minus __bold' : '__black'
      }
    },
    // 上下矢印(現在値ティック)に表示する色を返す
    tickColor() {
      switch (this.form.priceBasicInfo ? this.form.priceBasicInfo[0].tick : '') {
        case '1':
          return 'font-color__plus __bold'
        case '2':
          return 'font-color__minus __bold'
        default:
          return '__black __bold'
      }
    },
    target() {
      return 'link' + (this.requestProps.urlId === 0 ? '' : this.requestProps.urlId.toString())
    }
  },

  methods: {
    stockPriceDisplayA003Pre() {
      this.$emit('a003Pre', this.searchWord)
    },
    stockPriceDisplayA003Res(response) {
      this.$emit('a003Res', response)
      setTimeout(() => {
        this.searchWord = this.form.brandCode
      }, 500)
    },
    updateA005(response) {
      this.$emit('a005', response)
    },
    brandCodeSetA013(parameter) {
      this.searchWord = parameter.brandCode
      this.$refs['searchBtn'].execute()
    },
    resetAll() {
      this.$emit('resetBrandSearch')
    },
    clearSearchWord() {
      this.searchWord = ''
    },
    handleCloseModal() {
      this.brandSerachIsVisible = false
    },
    openDialog() {
      this.$refs['ifaForeignStockBrandSearchPopup'].clear()
      // A011 リクエスト: 国コードと取引種別
      this.$refs['ifaForeignStockBrandSearchPopup'].setCuntoryByString('US')
      this.$refs['ifaForeignStockBrandSearchPopup'].setTradeCd(this.a011RequestModel.tradeCd)
      this.brandSerachIsVisible = true
    },
    handleSearchWordInput(value) {
      if (this.form.brandCode !== '') {
        this.searchWord = this.form.brandCode
        return
      }
    },
    ifaForeignBrandGetNewMainSite(urlId, patternId, httpMethod, brandCode) {
      const newData = {
        urlId: urlId,
        patternId: patternId,
        httpMethod: httpMethod,
        brandCode: brandCode
      }
      this.requestProps = newData
      this.ifaLinkRequestModel = new IfaLinkRequestModel(this.requestProps)
      document.getElementById('IfaForeignBrandGetNewMainSite').click()
    },
    responseHandlerGetNewMainSite(response) {
      Object.assign(this.form, response?.dataList?.[0])
      if (this.form.postRequest) {
        this.request = this.form.postRequest
      }
      this.openWindow()
    },
    openWindow() {
      // cursor: not-allowed; と pointer-events: none; が同時に使えなかったため
      this.linkUrl = this.form.linkUrl
      this.paramObject = this.form.newMainSiteParamList[0]
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
        document.body.appendChild(linkForm)
        linkForm.submit()
        document.body.removeChild(linkForm)
      } else {
        const label = this.$store.getters.pageInfo.label
        notifyMessage(2, 'ポップアップを許可してください｡', label)
      }
    }
  }
}

</script>
<style lang="scss" scoped>
.search_header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  padding-right: 0.5rem;
  height: 25px;
  line-height: 25px;
}
.update-button {
  text-align: right;
  position: absolute;
  right: 0.5rem;
  top: 0;
}
.market-label {
  color: #f00;
  font-weight: bold;
  padding-left: 0.1rem;
}
.brand-name {
  width: 100%;
  color: #606266;
  font-weight: bold;
  font-size: 14px;
  padding-left: 0.5rem;
}
.__bold {
  font-weight: bold;
}
.__black {
  color: #606266;
}
.__color_empty {
  color: #bfcbd9;
}
.__right {
  text-align: right;
  padding-right: 0.5rem;
}
.info_xs {
  display: grid;
  width: 100%;
  grid-template-columns: 3.5rem 1fr;
}
.info_s {
  display: grid;
  width: 100%;
  grid-template-columns: 5rem 1fr;
}
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 12px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__value {
  resize: none;
  border: none;
  color: #606266;
  font-size: 11px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__current {
  resize: none;
  border: none;
  color: #606266;
  font-size: 15px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__empty {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.data-provider {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
}
.badge-item {
  padding-top: 0.4rem;
  padding-left: 0.4rem;
}
.inner-link {
  :deep(.el-link) {
  color: #092987;
  }
  :deep(.el-link.is-disabled) {
    color: var(--el-link-disabled-text-color);
    cursor: not-allowed;
  }
}
.inner-link_add {
  padding: 3px 7px;
  text-decoration: underline;
  &:hover, &:focus {
    text-decoration: underline;
    opacity: 0.7;
      }
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
.external-link-wrapper {
  display: inline-block
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
