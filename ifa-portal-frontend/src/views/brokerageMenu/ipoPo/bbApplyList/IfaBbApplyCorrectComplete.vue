<template>
  <!-- BB訂正・取消ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    class="buy-background-color status_change"
    title="BB申込訂正完了"
    width="1200px"
    :style="{ 'background-color': '#fef0f0' }"
    :center="true"
    :close-on-click-modal="false"
    :show-close="false"
    @open="onDialogOpen"
  >
    <el-row style="margin-top: 30px;"></el-row>
    <!-- エラー/警告表示 -->
    <ifa-message-area
      class="el-col-offset-1"
      :main-messages="['下記の内容で申込訂正を受付ました。']"
    >
    </ifa-message-area>

    <!-- 顧客情報/口座情報 -->
    <el-row
      style="padding-top: 0.2rem; font-weight: bold;"
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
          style="background-color: #eee; margin: 0.5rem 0;"
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
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <el-col
              :span="8"
            >
              <span>BB申込内容</span>
            </el-col>
            <el-col
              :span="2"
              style="margin-right: -25px;"
            >
              <span>訂正前</span>
            </el-col>
            <el-col
              :span="6"
              style="display: flex; justify-content: center; align-items: center; margin-right: 25px;"
            >
              <span class="el-icon-right arrow">→</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>訂正後</span>
            </el-col>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">数量</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ completeForm.quantityBeforeCorrect ? `${$_withCommaInteger(completeForm.quantityBeforeCorrect)}${completeForm.sellBuyUnitType}`: $_out(completeForm.quantityBeforeCorrect) }}</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ completeForm.quantity ? `${$_withCommaInteger(completeForm.quantity)}${completeForm.sellBuyUnitType}`: $_out(completeForm.quantity) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">価格/ディスカウント率</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span v-if="completeForm.marketOrderBeforeCorrect==='1'">成行</span>
              <span v-else-if="completeForm.issuePriceType === '1'">{{ completeForm.priceBeforeCorrect ? `${$_withCommaNoneZeroPadding(completeForm.priceBeforeCorrect)}円` : $_out(completeForm.price) }}</span>
              <span v-else-if="completeForm.issuePriceType === '2'">{{ completeForm.discountRateBeforeCorrect? `${$_withCommaNoneZeroPadding(completeForm.discountRateBeforeCorrect)}%` : $_out(completeForm.discountRate) }}</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span v-if="completeForm.marketOrder==='1'">成行</span>
              <span v-else-if="completeForm.issuePriceType === '1'">{{ completeForm.price ? `${$_withCommaNoneZeroPadding(completeForm.price)}円` : $_out(completeForm.price) }}</span>
              <span v-else-if="completeForm.issuePriceType === '2'">{{ completeForm.discountRate? `${$_withCommaNoneZeroPadding(completeForm.discountRate)}%` : $_out(completeForm.discountRate) }}</span>
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
        <el-card class="box-card">
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <el-col
              :span="8"
            >
              <span>その他申込内容</span>
            </el-col>
            <el-col
              :span="2"
              style="margin-right: -25px;"
            >
              <span>訂正前</span>
            </el-col>
            <el-col
              :span="6"
              style="display: flex; justify-content: center; align-items: center; margin-right: 25px;"
            >
              <span class="el-icon-right arrow">→</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>訂正後</span>
            </el-col>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">裁量希望数量</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ completeForm.discretionQuantityBeforeCorrect ? `${$_withCommaInteger(completeForm.discretionQuantityBeforeCorrect)}${completeForm.sellBuyUnitType}`: $_out(completeForm.discretionQuantityBeforeCorrect) }}</span>
              <!--<span>100株</span>-->
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ completeForm.discretionQuantity ? `${$_withCommaInteger(completeForm.discretionQuantity)}${completeForm.sellBuyUnitType}`: $_out(completeForm.discretionQuantity) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">裁量選定理由</span>
            </el-col>
            <el-col
              :span="8"
              style="min-height: 1px;"
            >
              <span>{{ $_out(completeForm.selectReasonBeforeCorrect) }}</span>
              <!-- <span>取引拡大のため</span> -->
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ $_out(completeForm.selectReason) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">投資家属性</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ $_out(completeForm.investorAttributeNameBeforeCorrect) }}</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ $_out(completeForm.investorAttributeName) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">勧誘区分</span>
            </el-col>
            <el-col
              :span="8"
              style="min-height: 1px;"
            >
              <span>-</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ completeForm.kanyuKbn ? $_getCodeValue('INVITATION_TYPE', 1, completeForm.kanyuKbn) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">受付方法</span>
            </el-col>
            <el-col
              :span="8"
              style="min-height: 1px;"
            >
              <span>-</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ completeForm.receiveMethod ? $_getCodeValue('ORDER_METHOD_TYPE', 1, completeForm.receiveMethod) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">備考</span>
            </el-col>
            <el-col
              :span="8"
              style="min-height: 1px;"
            >
              <span>{{ $_out(completeForm.bbRemarkBeforeCorrect) }}</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ $_out(completeForm.bbRemark) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <el-row
      v-if="completeForm.complianceRankCheckChkBoxLabel ||
        completeForm.discretionAllocateTimesOverFiveWarningMsg ||
        completeForm.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg
      "
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          class="box-card"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            v-if="completeForm.complianceRankCheckChkBoxLabel"
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">{{ $_out($_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, completeForm.complianceRankCheckChkBoxLabel)) }}</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, completeForm.complianceRankCheckChkBoxLabel)) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="completeForm.discretionAllocateTimesOverFiveWarningMsg"
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">裁量配分割当回数5回以上</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('DISCRETION_ALLOCATE_TIMES_OVER_FIVE', 1, '1')) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="completeForm.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg"
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <span class="_bold_black_m">金融資産3,000万円未満の裁量申込</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('FINANCIAL_ASSET_LESS_THAN_THIRTY_MILLION_YEN_DISCRETION_APPLY', 1, '1')) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <br>
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="text-align: left; margin-top: 1rem;"
      >
        <ifa-button
          text="BB申込一覧へ"
          color="primary"
          action-type="originalAction"
          style="padding-left: 0;"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaBbApplyCorrectCompleteFormModel } from './js/IfaBbApplyCorrectCompleteFormModel.js'
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
      completeForm: new IfaBbApplyCorrectCompleteFormModel()
    }
  },
  methods: {
    onDialogOpen() {
      Object.assign(this.completeForm, this.form)
    },
    onDialogClose() {
      this.$emit('close-modal')
      const params = {
        brandCode12: this.completeForm.brandCode,
        butenCode: this.completeForm.butenCode,
        accountNumber: this.completeForm.accountNumber
      }
      this.$_startShowMenu('SUB0204_02', params)
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
:deep(.el-card__body) {
  margin-left: 0px !important;
}
.status_change{
    .el-dialog__header{
      margin-top: 10px;
      padding-bottom: 0px;
      padding-top: 30px;
      .el-dialog__title{
        font-weight: bold;
      }
    }
  }
:deep(.main-message) {
  padding-left: 0px !important;
}
</style>
