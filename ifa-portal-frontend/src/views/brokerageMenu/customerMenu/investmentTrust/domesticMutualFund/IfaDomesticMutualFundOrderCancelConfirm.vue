
<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.screenTitle"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      width="1200px"
      :class="(classObject.classObjBgColor) + ' order-status-list__el-dialog--disabled'"
    >
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="text-align: right;"
        >
          <ifa-button
            id="btnBack"
            text=" 戻る"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="backA003"
          ></ifa-button>
        </el-col>
      </el-row>

      <!-- エラー/警告表示 -->
      <ifa-message-area
        :main-messages="[form.warningMessage]"
      ></ifa-message-area>

      <!-- 顧客情報 -->
      <el-row
        style="padding: 0.5rem 0; font-weight: bold;"
      >
        <el-col
          :offset="1"
        >
          <span>{{ customerInfo.butenCode }}-{{ customerInfo.accountNumber.padStart(7, '0') }}</span>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :offset="1"
          :span="22"
          style="font-size: 20px; flex: initial;"
          class="_bold_black_l"
        >
          <el-icon style="position: relative; top: 3px;">
            <Avatar v-if="customerInfo.corporationType === '0'"></Avatar>
            <OfficeBuilding v-else></OfficeBuilding>
          </el-icon>
          <span>{{ customerInfo.customerNameKanji }}（{{ customerInfo.customerNameKana }}）</span>
          <ifa-notice-info
            :notice-info-level="customerInfo.noticeInfoLevel"
            :customer-code="customerInfo.customerCode"
            :buten-code="customerInfo.butenCode"
            :account-number="customerInfo.accountNumber"
            style="position: relative; top: 3px;"
          ></ifa-notice-info>
        </el-col>
      </el-row>

      <!-- 銘柄エリア -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="padding: 0.3rem 0 0.6rem 0"
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

      <!-- 注文内容 -->
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
              style="padding-top: 0.2rem; padding-left: 1rem;"
            >
              <span>ご注文内容（復唱項目）</span>
            </el-row>
            <hr>

            <el-row
              class="dotted_border"
              style="padding-bottom: 0.5rem;"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>取引種別</span>
              </el-col>
              <el-col
                :span="16"
                style="font-size:16px;font-weight: bold"
              >
                <ifa-text
                  :class="classObject.classObjFontColor"
                  code-list-id="DOMESTIC_MUTUAL_FUND_TRADE_CLASS"
                  :disp-pattern="2"
                  :code-key="form.tradeCd"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row
              v-if="setAmount()"
              class="dotted_border"
              style="padding-bottom: 0.5rem;"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>金額</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ setAmount() }}</span>
              </el-col>
            </el-row>

            <el-row
              v-if="setUnit()"
              class="dotted_border"
              style="padding-bottom: 0.5rem;"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>口数</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ setUnit() }}</span>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
              style="padding-bottom: 0.5rem;"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>預り区分</span>
              </el-col>
              <el-col
                :span="16"
              >
                <ifa-text
                  code-list-id="MUTUAL_FUND_DEPOSIT_TYPE"
                  :disp-pattern="setDepositDisplay()"
                  :code-key="form.depositType"
                ></ifa-text>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
      <!-- その他注文内容 -->
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
              style="padding-top: 0.2rem; padding-left: 1rem;"
            >
              <span>その他注文内容</span>
            </el-row>
            <hr>
            <el-row
              v-if="form.tradeCd === '0' || form.tradeCd === '1' || form.tradeCd === '2'"
              class="dotted_border"
              style="padding-bottom: 0.5rem;"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>利用ポイント</span>
              </el-col>
              <el-col
                :span="16"
              >
                <ifa-text
                  v-if="form.pointType && form.pointType !== ' '"
                  code-list-id="POINT_TYPE"
                  :disp-pattern="1"
                  :code-key="form.pointType"
                  style="margin-right: 40px;"
                ></ifa-text>
                <span v-if="form.callcenterClassification === '9'">未確定</span>
                <span v-else>{{ form.point ? $_withCommaNoneZeroPadding((form.point)) : '0' }}</span>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span class="_black_s_bold">受注日時</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out($_getFormattedDateTimeValue(form.orderDayTime, 'datetime12')) }}</span>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>

      <!--  取消完了ボタン -->
      <el-row>
        <el-col
          :offset="1"
          style="margin-top: 1rem"
        >
          <span class="dialog-footer">
            <ifa-button
              id="btnOrderCancel"
              text=" 注文取消"
              color="primary"
              :disabled="userAccount.medUsers.privId === '1' || userAccount.medUsers.privId === '2' || userAccount.medUsers.privId === '11' || userAccount.medUsers.privId === '12'"
              action-type="requestAction"
              action-id="SUB0202_0401-04_1#A002"
              style="padding-left: 0;"
              :request-model="a002RequestModel"
              @response-handler="orderCancellationA002($event)"
            ></ifa-button>
          </span>
        </el-col>
      </el-row>

    </el-dialog>

    <ifa-domestic-mutual-fund-order-cancel-complete
      ref="IfaDomesticMutualFundOrderCancelComplete"
      :is-visible="dialogFinishVisible"
      :customer-info="customerInfo"
      :class-object="classObject"
      @move-to-order-list="handleMoveToOrderList"
    ></ifa-domestic-mutual-fund-order-cancel-complete>

  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaDomesticMutualFundOrderCancelComplete from './IfaDomesticMutualFundOrderCancelComplete'
import { IfaDomesticMutualFundOrderCancelConfirmFormModel } from './js/IfaDomesticMutualFundOrderCancelConfirmFormModel'
import { IfaDomesticMutualFundOrderCancelConfirmA002RequestModel } from './js/IfaDomesticMutualFundOrderCancelConfirmA002RequestModel'

export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea,
    IfaDomesticMutualFundOrderCancelComplete
  },
  props: {
    isVisible: { type: Boolean, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'update:isVisible', 'initialize-order-status-list'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaDomesticMutualFundOrderCancelConfirmFormModel(),
      dialogFinishVisible: false,
      classObject: {
        classObjBgColor: '',
        classObjFontColor: ''
      }
    }
  },
  computed: {
    userAccount() {
      return this.$store.getters.userAccount
    },
    a002RequestModel() {
      return new IfaDomesticMutualFundOrderCancelConfirmA002RequestModel(this.form)
    }
  },
  methods: {
    onShow(data) {
      Object.assign(this.form, data)
      this.changeBuyType()
    },
    // 取消発注ボタン
    orderCancellationA002(response) {
      this.dialogFinishVisible = true
      this.$nextTick(() => {
        this.$refs['IfaDomesticMutualFundOrderCancelComplete'].onShow(response.dataList[0])
      })
    },
    // 戻るボタン
    backA003() {
      this.$emit('close-modal')
    },
    // 注文一覧画面に遷移（画面更新）
    handleMoveToOrderList() {
      this.dialogFinishVisible = false
      this.$emit('initialize-order-status-list')
    },
    setAmount() {
      if (this.form.tradeCd === '1' || this.form.tradeCd === '2' || this.form.tradeCd === '5' || this.form.tradeCd === '9' || this.form.tradeCd === '11') {
        return this.form.amount ? this.$_withCommaInteger(this.$_out(this.form.amount)) + '円' : '-'
      } else if (this.form.tradeCd === '4' || this.form.tradeCd === '6' || this.form.tradeCd === '8' || this.form.tradeCd === '10' || this.form.tradeCd === '12' || this.form.tradeCd === '13' || this.form.tradeCd === '14' || this.form.tradeCd === '15') {
        if (this.form.buyCancelMethod === '1') {
          return this.form.amount ? this.$_withCommaInteger(this.$_out(this.form.amount)) + '円' : '-'
        } else if (this.form.buyCancelMethod === '3') {
          return '-'
        } else {
          return ''
        }
      } else {
        return ''
      }
    },
    setUnit() {
      if (this.form.tradeCd === '0' || this.form.tradeCd === '3' || this.form.tradeCd === '7') {
        return this.form.unit ? this.$_withCommaInteger(this.$_out(this.form.unit)) + '口' : '-'
      } else if (this.form.tradeCd === '4' || this.form.tradeCd === '8' || this.form.tradeCd === '12' || this.form.tradeCd === '13' || this.form.tradeCd === '14' || this.form.tradeCd === '15') {
        if (this.form.buyCancelMethod === '2') {
          return this.form.unit ? this.$_withCommaInteger(this.$_out(this.form.unit)) + '口' : '-'
        } else {
          return ''
        }
      } else {
        return ''
      }
    },
    setDepositDisplay() {
      // 顧客共通情報.ジュニアISA契約区分=1：契約　の場合
      if (this.customerInfo.jrIsaContractType === '1') {
        // 取引種別=購入（累投）　または　購入（一般）　の場合
        if (this.form.tradeCd === '1' || this.form.tradeCd === '0') {
          // 預り区分=5:買付時：Jr特定/Jr一般　かつ (顧客共通情報.ジュニア特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
          if (this.form.depositType === '5' && !(this.customerInfo.jrTokuteiKouzaKbn === '1' || this.customerInfo.jrTokuteiKouzaKbn === '2')) {
            return 6
          // 預り区分=△:特定/一般　かつ (顧客共通情報.特定口座区分= (1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
          } else if (this.form.depositType === ' ' && !(this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2')) {
            return 6
          // 上記以外
          } else {
            return 3
          }
        // 取引種別=上記以外　の場合
        } else {
          return 4
        }
      // 上記以外
      } else {
        // 顧客共通情報.特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない場合
        if (!(this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2')) {
          return 7
        // 上記以外
        } else {
          return 5
        }
      }
    },
    changeBuyType() {
      if (this.form.tradeCd === '0' || this.form.tradeCd === '1' || this.form.tradeCd === '2' || this.form.tradeCd === '11') {
        this.classObject.classObjBgColor = 'buy-background-color'
        this.classObject.classObjFontColor = 'font-color__plus bold'
      } else {
        this.classObject.classObjBgColor = 'sell-background-color'
        this.classObject.classObjFontColor = 'font-color__minus bold'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
</style>
