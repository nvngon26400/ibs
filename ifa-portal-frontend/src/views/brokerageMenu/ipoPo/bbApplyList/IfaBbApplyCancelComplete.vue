<template>
  <!-- BB訂正・取消ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    class="buy-background-color"
    title="BB申込取消完了"
    width="1200px"
    :style="{ 'background-color': '#fef0f0' }"
    :center="true"
    :close-on-click-modal="false"
    :show-close="false"
    @open="onDialogOpen"
  >

    <!-- タイトルとエラーメッセージの間に余白を追加 -->
    <div style="margin-top: 40px;"></div>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      class="el-col-offset-1"
      :main-messages="['下記の内容で申込取消を受け付けました。']"
    >
    </ifa-message-area>

    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span>{{ $_out(`${completeForm.butenCode}-${$_zeroPadding(completeForm.accountNumber, 7)}`) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="font-size: 20px; padding-top: 0.2rem;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span v-if="completeForm.corporationType === '1'"><el-icon style="position: relative; top: 3px;"><OfficeBuilding></OfficeBuilding></el-icon></span>
        <span v-else><el-icon style="position: relative; top: 3px;"><Avatar></Avatar></el-icon></span>
        <span>{{ completeForm.customerNameKanji + ' (' + completeForm.customerNameKana + ')' }}</span>
        <ifa-notice-info
          :notice-info-level="completeForm.noticeInfoLevel"
          :buten-code="completeForm.butenCode"
          :account-number="completeForm.accountNumber"
          :customer-code="completeForm.customerCode"
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
          <el-row style="margin-left: -30px;">
            <el-col
              :span="24"
              style="display: flex; align-items: flex-start;"
            >
              <div
                style="font-size: 20px; display: inline-block; width: auto; white-space: nowrap;"
                class="_bold_black_l"
              >
                <span>{{ '［' + completeForm.brandCode + '］' }}</span>
              </div>
              <div
                style="font-size: 20px; display: inline-block;"
                class="_bold_black_l"
              >
                <span>{{ completeForm.brandName }}</span>
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
            style="padding-top: 0.5rem; padding-left: 1rem; margin-left: -30px;"
          >
            <span>BB申込内容</span>
          </el-row>
          <hr style="margin-left: -30px;">
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem; margin-left: -30px;"
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
              <span>{{ $_out($_withCommaInteger(completeForm.quantity) + completeForm.sellBuyUnitType) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem; margin-left: -30px;"
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
              <span v-if="completeForm.marketOrder === '1'">成行</span>
              <span v-if="completeForm.marketOrder === '0'">
                <span v-if="completeForm.issuePriceType === '1'">{{ $_out($_withCommaNoneZeroPadding(completeForm.price)) }}円</span>
                <span v-if="completeForm.issuePriceType === '2'">{{ $_out($_withCommaNoneZeroPadding(completeForm.discountRate)) }}%</span>
              </span>
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
            style="padding-top: 0.5rem; padding-left: 1rem; margin-left: -30px;"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr style="margin-left: -30px;">
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem; margin-left: -30px;"
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
              <span>{{ form.discretionQuantity ? $_out($_withCommaInteger(form.discretionQuantity) + form.sellBuyUnitType) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem; margin-left: -30px;"
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
                  style="padding-bottom: 0.5rem; margin-left: -30px;"
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
                  style="padding-bottom: 0.5rem; margin-left: -30px;"
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
              <span>{{ $_out(form.bbRemark) }}</span>
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
          text="BB申込一覧へ"
          color="primary"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaBbApplyCancelCompleteFormModel } from './js/IfaBbApplyCancelCompleteFormModel.js'
import ifaNoticeInfo from '@/components/icon/IfaNoticeInfo'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaMessageArea,
    ifaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    form: { type: Object, required: true }
  },
  emits: ['close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      completeForm: new IfaBbApplyCancelCompleteFormModel()
    }
  },
  methods: {
    onDialogClose() {
      this.$emit('close-modal')
      const params = {
        brandCode12: this.completeForm.brandCode,
        butenCode: this.completeForm.butenCode,
        accountNumber: this.completeForm.accountNumber
      }
      this.$_startShowMenu('SUB0204_02', params)
    },
    onDialogOpen() {
      Object.assign(this.completeForm, this.form)
      this.completeForm.warningMessage = ['下記の内容で申込訂正を受付ました。']
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
  margin: -40px 2rem 0 auto;
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
</style>
