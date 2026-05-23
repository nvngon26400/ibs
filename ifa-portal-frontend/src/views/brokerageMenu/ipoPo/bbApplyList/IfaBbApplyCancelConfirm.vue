<template>
  <!-- BB訂正・取消ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    class="buy-background-color"
    title="BB申込取消確認"
    width="1200px"
    :style="{ 'background-color': '#fef0f0' }"
    :center="true"
    :close-on-click-modal="false"
    :show-close="false"
    @open="onDialogOpen"
  >

    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      class="el-col-offset-1"
      :main-messages="[
        '取消はまだ完了していません。画面下の申込取消ボタンを押下してください。'
      ]"
    >
    </ifa-message-area>

    <!-- 顧客情報/口座情報 -->
    <el-row
      class="_bold_black_l"
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
      >
        <span>{{ $_out(`${form.butenCode}-${$_zeroPadding(form.accountNumber,7)}`) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >

      <el-col
        :offset="1"
        :span="22"
      >
        <span style="position: relative; top: 3px;">
          <el-icon>
            <OfficeBuilding v-if="form.corporationType === '1'"></OfficeBuilding>
            <Avatar v-else></Avatar>
          </el-icon>
        </span>
        <span>{{ $_out(`${form.customerNameKanji}（${form.customerNameKana}）`) }}</span>
        <ifa-notice-info
          :notice-info-level="form.noticeInfoLevel"
          :buten-code="form.butenCode"
          :account-number="form.accountNumber"
          :customer-code="form.customerCode"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>
    <!-- 銘柄情報 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="background-color: #eee;"
        >
          <el-row>
            <el-col
              :span="24"
              style="display: flex; align-items: flex-start;"
            >
              <div
                style="font-size: 20px; display: inline-block; width: auto; white-space: nowrap;"
                class="_bold_black_l"
              >
                <span>［{{ $_out(form.brandCode) }}］</span>
              </div>
              <div
                style="font-size: 20px; display: inline-block;"
                class="_bold_black_l"
              >
                <span>{{ $_out(form.brandName) }}</span>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文内容(復唱項目) -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding-top: 0.7rem;"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>BB申込内容（復唱項目）</span>
          </el-row>
          <hr>
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col
              style="font-size:16px;"
              :span="16"
            >
              <span>{{ form.quantity ? `${$_withCommaInteger(form.quantity)}${form.sellBuyUnitType}`: $_out(form.quantity) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格/ディスカウント率</span>
            </el-col>
            <el-col
              style="font-size:16px;"
              :span="16"
            >
              <span v-if="form.marketOrder==='1'">成行</span>
              <span v-else-if="form.issuePriceType === '1'">{{ form.price ? `${$_withCommaNoneZeroPadding(form.price)}円` : $_out(form.price) }}</span>
              <span v-else-if="form.issuePriceType === '2'">{{ form.discountRate? `${$_withCommaNoneZeroPadding(form.discountRate)}%` : $_out(form.discountRate) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- その他注文内容 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card class="box-card"
                 style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>その他申込内容</span>
          </el-row>
          <hr>
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span class="_black_s_bold">裁量希望数量</span>
            </el-col>
            <el-col
              style="font-size:16px;"
              :span="16"
            >
              <span>{{ form.discretionQuantity ? `${$_withCommaInteger(form.discretionQuantity)}${form.sellBuyUnitType}`: $_out(form.discretionQuantity) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span class="_black_s_bold">裁量選定理由</span>
            </el-col>
            <el-col
              style="font-size:16px;"
              :span="16"
            >
              <span>{{ $_out(form.selectReason) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span class="_black_s_bold">投資家属性</span>
            </el-col>
            <el-col
              style="font-size:16px;"
              :span="16"
            >
              <span>{{ $_out(form.investorAttributeName) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span class="_black_s_bold">備考</span>
            </el-col>
            <el-col
              style="font-size:16px;"
              :span="16"
            >
              <span style="overflow-wrap: anywhere; white-space: break-spaces;">{{ $_out(form.bbRemark) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文発注ボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          text="申込取消"
          :disabled="buttonDisabled"
          action-type="requestAction"
          action-id="SUB0204_02-03_2#A001"
          style="padding-left: 0;"
          :request-model="ifaBbApplyCorrectConfirmA001RequestModel"
          @response-handler="applicationCancellationA001($event)"
          @response-error-handler="responseErrorHandlerA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaBbApplyCancelConfirmFormModel } from './js/IfaBbApplyCancelConfirmFormModel.js'
import { IfaBbApplyCancelConfirmA001RequestModel } from './js/IfaBbApplyCancelConfirmA001RequestModel.js'
import ifaNoticeInfo from '@/components/icon/IfaNoticeInfo'
export default {
  components: {
    IfaMessageArea,
    ifaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    form: { type: Object, required: true }
  },
  emits: ['close-modal', 'order-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      confirmForm: new IfaBbApplyCancelConfirmFormModel()
    }
  },
  computed: {
    ifaBbApplyCorrectConfirmA001RequestModel() {
      return new IfaBbApplyCancelConfirmA001RequestModel(this.confirmForm)
    },
    // ユーザ共通情報.権限コード
    privId() {
      return this.$store.getters.userAccount.medUsers.privId
    },
    buttonDisabled() {
      if (this.privId === '1' || this.privId === '2') {
        return true
      } else {
        return false
      }
    }
  },
  methods: {
    onDialogOpen() {
      Object.keys(this.confirmForm).forEach(key => {
        this.confirmForm[key] = this.form[key]
      })
    },
    onOrderFinish() {
      this.$emit('order-finish', this.confirmForm)
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    applicationCancellationA001(data) {
      this.$_logDebug(data)
      Object.assign(this.confirmForm, data.dataList[0])
      this.onOrderFinish()
    },
    responseErrorHandlerA001(error) {
      this.$_logError(error)
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.buy-background-color_card {
  background-color: #fef0f0;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
}
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 14px;
  white-space: pre-wrap;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: #000;
  font-size: 14px;
  white-space: pre-wrap;
}
:deep(.el-dialog) {
  padding: 40px 20px 20px 20px !important;
  padding-top: 40px;
}
._bold_red_m {
  font-size: 16px;
  font-weight: bold;
  color: #f00;
}
.required-mark {
  color: #ff2b2b;
  margin-right: 2px;
}
:deep(.el-checkbox__label) {
  padding-bottom: 0px;
  color: #f00;
  font-weight: normal !important;
}
:deep(.el-checkbox__input.is-checked) + .el-checkbox__label {
    color: #f00;
}
.buy-background-color_card {
  background-color: #fef0f0;
}
.sell-background-color_card {
  background-color: #ecf5ff;
}
:deep(.main-message) {
  padding-left: 0px !important;
}
:deep(.el-card__body) {
  margin-left: 0px !important;
}
</style>
