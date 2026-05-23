<template>
  <!-- BB訂正・取消ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :style="{ 'background-color': '#fef0f0' }"
    class="status_change"
    :title="IfaBbApplyCompleteFormModel.title.name"
    width="1200px"
    :center="true"
    :close-on-click-modal="false"
    :show-close="false"
  >
    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="['下記の内容で申込を受け付けました。']"
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
        <span>{{ $_out(form.butenCode) + '-' + $_out(form.accountNumber) }}</span>
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
        <span
          v-if="form.corporationType === '1'"
          style="position: relative; top: 3px;"
        ><el-icon><OfficeBuilding></OfficeBuilding></el-icon></span>
        <span v-else><el-icon style="vertical-align:top"><Avatar></Avatar></el-icon></span>
        <span>{{ form.customerNameKanji + ' (' + form.customerNameKana + ')' }}</span>
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
          style="background-color: #eee; margin: 0.5rem 0;"
        >
          <el-row>
            <el-col
              :span="24"
              style="display: flex; align-items: flex-start;"
            >
              <div
                class="_bold_black_l"
                style="font-size: 20px; display: inline-block; width: auto; white-space: nowrap;"
              >
                <span>{{ '［' + form.brandCode + '］' }}</span>
              </div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; display: inline-block;"
              >
                <span>{{ form.brandName }}</span>
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
            <span>BB申込内容</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_withCommaInteger(form.quantity) + form.sellBuyUnitType) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格/ディスカウント率</span>
            </el-col>
            <el-col :span="16">
              <span v-if="form.marketOrder === '1'">成行</span>
              <span v-if="form.marketOrder === '0'">
                <span v-if="form.issuePriceType === '1'">{{ $_out($_withCommaNoneZeroPadding(form.price)) }}円</span>
                <span v-if="form.issuePriceType === '2'">{{ $_out($_withCommaNoneZeroPadding(form.discountRate)) }}%</span>
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
        <el-card class="box-card">
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>その他申込内容</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>裁量希望数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.discretionQuantity ? $_out($_withCommaInteger(form.discretionQuantity) + form.sellBuyUnitType) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>裁量選定理由</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.selectReason ? $_out($_getCodeValue('DISCRETION_SELECT_REASON', 1, form.selectReason)) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>投資家属性</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(form.investorAttributeName) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('INVITATION_TYPE', 1, form.kanyuKbn)) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('ORDER_METHOD_TYPE', 1, form.receiveMethod)) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>備考</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(form.bbRemark) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <el-row
      v-if="form.complianceRankCheckChkBoxLabel ||
        form.discretionAllocateTimesOverFiveWarningMsg ||
        form.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg"
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
            v-if="form.complianceRankCheckChkBoxLabel !== ''"
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ $_out($_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, form.complianceRankCheckChkBoxLabel)) }}</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, form.complianceRankCheckChkBoxLabel)) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="form.discretionAllocateTimesOverFiveWarningMsg !== ''"
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>裁量配分割当回数5回以上</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('DISCRETION_ALLOCATE_TIMES_OVER_FIVE', 1, '1')) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="form.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg !== ''"
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>金融資産3,000万円未満の裁量申込</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('FINANCIAL_ASSET_LESS_THAN_THIRTY_MILLION_YEN_DISCRETION_APPLY', 1, '1')) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <br>
    <el-col
      :span="22"
      :offset="1"
      style="text-align: left;"
    >
      <ifa-button
        text="BB申込一覧へ"
        color="primary"
        action-type="originalAction"
        style="padding-left: 0;"
        @app-action-handler="onDialogClose"
      ></ifa-button>
    </el-col>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { IfaBbApplyCompleteFormModel } from './js/IfaBbApplyCompleteFormModel'
import ifaNoticeInfo from '@/components/icon/IfaNoticeInfo'
import IfaMessageArea from '@/components/Message/IfaMessageArea'

export default {
  components: {
    ifaNoticeInfo,
    IfaMessageArea
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
      ifaFormatUtils: ifaFormatUtils,
      IfaBbApplyCompleteFormModel: new IfaBbApplyCompleteFormModel()
    }
  },
  methods: {
    onDialogClose() {
      this.$emit('close-modal')
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
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
:deep(.el-checkbox__input.is-checked + .el-checkbox__label) {
    color: #f00;
}
.buy-background-color_card {
  background-color: #fef0f0;
}
.sell-background-color_card {
  background-color: #ecf5ff;
}
:deep(.el-card__body){
    margin-left: 0px !important;
}
</style>

<style lang="scss">
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
</style>
