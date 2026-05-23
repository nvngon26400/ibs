<template>
  <el-form
    ref="formRef"
    :model="form"
    :rules="rules"
    label-width="180px"
    label-position="left"
  >
    <div style="display: flex; flex-direction: column; row-gap: 10px;">
      <el-card style="background-color: #eeeeee;">
        <div class="form-area__section">
          <el-row>
            <el-col :span="21">
              <el-form-item
                label="決済方法"
                style="font-weight: 700;"
              >
                <ifa-text
                  code-list-id="FUND_RESERVE_PAYMENT_METHOD"
                  style="font-weight: normal;"
                  :disp-pattern="1"
                  :select-pattern="1"
                  :code-key="form.paymentMethod"
                ></ifa-text>
              </el-form-item>
            </el-col>
            <el-col :span="3">
              <div style="display: flex; align-items: center; justify-content: flex-end;">
                <ifa-button
                  id="btnReset"
                  text="リセット"
                  color="secondary"
                  action-type="originalAction"
                  @app-action-handler="handleReset"
                ></ifa-button>
                <ifa-button
                  id="btnBack"
                  text="戻る"
                  color="secondary"
                  action-type="originalAction"
                  @app-action-handler="handleClose"
                ></ifa-button>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="form-area__section">
          <el-row>
            <el-col :span="20">
              <el-form-item
                prop="accountType"
                style="font-weight: 700;"
                label="預り区分"
              >
                <div>
                  <span style="font-weight: normal;">{{ info?.accountTypeName ?? '-' }}</span>
                  <input
                    v-model="form.accountType"
                    type="hidden"
                  >
                </div>
              </el-form-item>

              <el-form-item
                v-if="info?.reserveOrderUnit === '0' &&
                  (form.accountType === 'H' || form.accountType === 'I')
                "
                class="no-label-class"
                prop="nisaOptions"
                label=" "
                label-class="hidden-label"
                label-width="180px"
              >
                <el-checkbox-group v-model="form.nisaOptions">
                  <div style="display: flex; align-items: center; column-gap: 30px;">
                    <el-checkbox label="1">
                      <div style="display: flex; align-items: center; column-gap: 4px;">
                        <span>NISA枠ぎりぎり注文</span>
                        <span
                          class="icon-wrapper"
                        >
                          <ifa-balloon-icon
                            icon-type="info"
                            :message="info?.nisaBarelyBuyingTypeComment"
                            :show-msg-on-mount="false"
                          >
                          </ifa-balloon-icon>
                        </span>
                      </div>
                    </el-checkbox>
                    <el-checkbox
                      v-if="form.accountType === 'H'"
                      label="2"
                    >
                      <div style="display: flex; align-items: center; column-gap: 4px;">
                        <span>課税枠シフト注文</span>
                        <span
                          class="icon-wrapper"
                        >
                          <ifa-balloon-icon
                            icon-type="info"
                            :message="info?.taxShiftTypeComment"
                            :show-msg-on-mount="false"
                          >
                          </ifa-balloon-icon>
                        </span>
                      </div>
                    </el-checkbox>
                  </div>
                </el-checkbox-group>
              </el-form-item>
            </el-col>
          </el-row>
        </div>

        <div class="form-area__section">
          <el-row>
            <el-col :span="20">
              <ifa-input-radio
                v-model="form.courseType"
                prop="courseType"
                label="積立コース"
                required
                code-list-id="FUND_RESERVE_COURSE_KBN"
                :disp-pattern="form.accountType === 'I' ? 2 : 1"
                :select-pattern="form.accountType === 'I' ? 2 : 1"
                @change="handleCourseTypeChange"
              ></ifa-input-radio>
            </el-col>
          </el-row>
        </div>

        <div class="form-area__section">
          <el-col
            :span="20"
            style="font-weight: 700;"
          >
            <el-row>
              <div class="el-form-item__label fake-label">設定日</div>
              <div>
                <div
                  v-if="form.courseType === '1'"
                  style="height: 100%; display: flex; align-items: center; font-weight: normal;"
                >
                  毎営業日に積立発注
                </div>
                <div
                  v-if="form.courseType === '2'"
                  style="height: 100%; display: flex; align-items: baseline; font-weight: normal"
                >
                  <span style="padding-right: 5px;">毎週</span>
                  <ifa-input-select
                    v-model="form.settingReserveWeek"
                    prop="settingReserveWeek"
                    style="width: 80px;"
                    code-list-id="FUND_RESERVE_WEEKLY_SETTING"
                    :select-pattern="1"
                    :disp-pattern="1"
                  >
                    <template #suffix>
                      <span style="padding-left: 5px">曜日に積立発注</span>
                    </template>
                  </ifa-input-select>
                </div>
                <div
                  v-if="form.courseType === '3'"
                  style="height: 100%; display: flex; align-items: baseline; font-weight: normal"
                >
                  <span style="padding-right: 5px;">毎月</span>
                  <ifa-input-select
                    v-model="form.settingReserveMonth"
                    prop="settingReserveMonth"
                    style="width: 80px;"
                    required
                    code-list-id="FUND_RESERVE_DAY_SETTING"
                    :select-pattern="1"
                    :disp-pattern="2"
                  >
                    <template #suffix>
                      <span style="padding-left: 5px;">日に積立発注</span>
                    </template>
                  </ifa-input-select>
                </div>
                <div
                  v-if="form.courseType === '4'"
                  style="height: 100%; display: flex; align-items: flex-end; column-gap: 20px; font-weight: normal"
                >
                  <div
                    style="display: flex; width: 510px; align-items: baseline; flex-wrap: wrap; font-weight: normal"
                  >
                    <ifa-input-check
                      v-model="form.settingReserveMultiDay"
                      prop="settingReserveMultiDay"
                      label="設定日"
                      label-class="hidden-label"
                      required
                      code-list-id="FUND_RESERVE_DAY_SETTING"
                      :select-pattern="4"
                      :disp-pattern="4"
                    ></ifa-input-check>
                  </div>
                </div>
                <div
                  v-if="form.courseType === '5'"
                  style="height: 100%; display: flex; align-items: baseline; font-weight: normal"
                >
                  <span style="padding-right: 5px;">奇数月の</span>
                  <ifa-input-select
                    v-model="form.settingReserveOddDay"
                    prop="settingReserveOddDay"
                    style="width: 80px;"
                    required
                    code-list-id="FUND_RESERVE_DAY_SETTING"
                    :select-pattern="1"
                    :disp-pattern="2"
                  >
                    <template #suffix>
                      <span style="padding-left: 5px;">日に積立発注</span>
                    </template>
                  </ifa-input-select>
                </div>
                <div
                  v-if="form.courseType === '6'"
                  style="height: 100%; display: flex; align-items: baseline; font-weight: normal;"
                >
                  <span style="padding-right: 5px;">偶数月の</span>
                  <ifa-input-select
                    v-model="form.settingReserveEvenDay"
                    prop="settingReserveEvenDay"
                    style="width: 80px;"
                    code-list-id="FUND_RESERVE_DAY_SETTING"
                    :select-pattern="1"
                    :disp-pattern="2"
                  >
                    <template #suffix>
                      <span style="padding-left: 5px;">日に積立発注</span>
                    </template>
                  </ifa-input-select>
                </div>
              </div>
            </el-row>
          </el-col>
        </div>

        <div class="form-area__section">
          <el-row>
            <el-col
              :span="22"
              style="font-weight: 700;"
            >
              <el-row>
                <div class="el-form-item__label fake-label">積立金額</div>
                <div style="display: flex; flex-direction: column; row-gap: 10px; font-weight: normal;">
                  <div style="display: flex; align-items: center; column-gap: 10px;">
                    <ifa-input-amount
                      v-model="form.settingAmount"
                      label="金額"
                      prop="settingAmount"
                      unit="円"
                      required
                      :min="minSettingAmount"
                      :max="availableAppSettingAmount.value"
                      label-class="label-class"
                      :domain="IfaYen80DomainModel"
                    ></ifa-input-amount>
                    <div style="height: 32px; display: flex; align-items: center; align-self: baseline;">
                      （手数料・消費税込）
                    </div>
                  </div>
                  <div style="display: flex; align-items: center; column-gap: 30px;">
                    <span>最低申込金額：{{ minAppAmount }}</span>
                    <span>申込単位：{{ info?.reserveOrderUnit === '1' ? '1千円' : '1円' }}</span>
                    <span>申込可能金額：{{ availableAppSettingAmount.text }}</span>
                  </div>
                </div>
              </el-row>
            </el-col>
          </el-row>
        </div>

        <div class="form-area__section">
          <el-row>
            <el-col
              :span="20"
              style="font-weight: 700;"
            >
              <el-row>
                <div class="el-form-item__label fake-label-no-required">１ヶ月あたりの概算</div>
                <div style="display: flex; align-items: center; column-gap: 30px; font-weight: normal;">
                  <span>申込回数：{{ numberOfApplications }}回</span>
                  <span>申込金額：{{ applicationAmount }}</span>
                </div>
              </el-row>
            </el-col>
          </el-row>
        </div>

        <div class="form-area__section">
          <el-row style="font-weight: 700;">
            <el-col :span="20">
              <el-row>
                <div class="el-form-item__label fake-label">ボーナス月設定</div>
                <div style="display: flex; flex-direction: column; row-gap: 10px; font-weight: normal;">
                  <ifa-input-radio
                    v-model="form.settingBonusFlag"
                    label="ボーナス月設定"
                    required
                    label-class="hidden-label"
                    prop="settingBonusFlag"
                    code-list-id="original"
                    :original-list="[
                      { key: '1', value: 'する' },
                      { key: '2', value: 'しない' }
                    ]"
                    @change="handleBonusSettingChange"
                  ></ifa-input-radio>
                  <template v-if="form.settingBonusFlag ==='1'">
                    <div style="display: flex; column-gap: 10px;">
                      <ifa-input-amount
                        v-model="form.settingBonusAmount"
                        unit="円"
                        label="金額"
                        prop="settingBonusAmount"
                        required
                        label-class="label-class"
                        :min="minSettingAmount"
                        :max="availableAppSettingBonusAmount.value"
                        :domain="IfaYen80DomainModel"
                      ></ifa-input-amount>
                      <div style="height: 32px; display: flex; align-items: center; align-self: baseline; column-gap: 30px;">
                        <span>（手数料・消費税込）</span>
                        <span>申込可能金額：{{ availableAppSettingBonusAmount.text }}</span>
                      </div>
                    </div>
                    <el-row style="display: flex; column-gap: 30px;">
                      <div
                        class="bonus-wrapper"
                        style="display: flex; align-items: baseline; column-gap: 10px;"
                      >
                        <span>設定日１</span>
                        <ifa-input-select
                          v-model="form.settingBonus1Month"
                          prop="settingBonus1Month"
                          style="width: 100px;"
                          label="月"
                          label-class="hidden-label"
                          required
                          code-list-id="FUND_RESERVE_MONTHLY_SETTING"
                          :select-pattern="1"
                          :disp-pattern="1"
                          @change="handleSettingDateChange"
                        >
                          <template #suffix>
                            <span style="padding-left: 5px">月</span>
                          </template>
                        </ifa-input-select>

                        <ifa-input-select
                          v-model="form.settingBonus1Day"
                          prop="settingBonus1Day"
                          style="width: 100px;"
                          label="日"
                          label-class="hidden-label"
                          required
                          code-list-id="FUND_RESERVE_DAY_SETTING"
                          :select-pattern="1"
                          :disp-pattern="2"
                          @change="handleSettingDateChange"
                        >
                          <template #suffix>
                            <span style="padding-left: 5px">日</span>
                          </template>
                        </ifa-input-select>
                      </div>
                      <div
                        class="bonus-wrapper"
                        style="display: flex; align-items: baseline; column-gap: 10px;"
                      >
                        <span>設定日２</span>
                        <ifa-input-select
                          v-model="form.settingBonus2Month"
                          prop="settingBonus2Month"
                          style="width: 100px;"
                          label="月"
                          label-class="hidden-label"
                          :disabled="isInputDisabled"
                          code-list-id="FUND_RESERVE_MONTHLY_SETTING"
                          :select-pattern="1"
                          :disp-pattern="1"
                        >
                          <template #suffix>
                            <span style="padding-left: 5px">月</span>
                          </template>
                        </ifa-input-select>

                        <ifa-input-select
                          v-model="form.settingBonus2Day"
                          prop="settingBonus2Day"
                          style="width: 100px;"
                          label="日"
                          label-class="hidden-label"
                          :disabled="isInputDisabled"
                          code-list-id="FUND_RESERVE_DAY_SETTING"
                          :select-pattern="1"
                          :disp-pattern="2"
                        >
                          <template #suffix>
                            <span style="padding-left: 5px">日</span>
                          </template>
                        </ifa-input-select>
                      </div>
                    </el-row>
                  </template>
                </div>
              </el-row>
            </el-col>
          </el-row>
        </div>

        <div class="form-area__section">
          <el-row>
            <el-col :span="20">
              <ifa-input-select
                v-model="form.kanyuKbn"
                label="勧誘区分"
                prop="kanyuKbn"
                required
                label-class="label-item-class"
                code-list-id="INVITATION_TYPE"
                :disp-pattern="1"
                :select-pattern="1"
              ></ifa-input-select>
            </el-col>
          </el-row>
        </div>

        <div class="form-area__section">
          <el-row>
            <el-col :span="20">
              <ifa-input-select
                v-model="form.receiveMethod"
                label="受付方法"
                code-list-id="ORDER_METHOD_TYPE"
                required
                label-class="label-item-class"
                prop="receiveMethod"
                :disp-pattern="1"
                :select-pattern="1"
              ></ifa-input-select>
            </el-col>
          </el-row>
        </div>

        <div class="form-area__section">
          <el-row>
            <el-col
              :span="24"
              style="font-weight: 700;"
            >
              <el-row>
                <div class="el-form-item__label fake-label">ご注意事項</div>
                <div style="display: flex; flex-direction: column; font-weight: normal;">
                  <ifa-input-check
                    v-model="form.checkMadoAki"
                    prop="checkMadoAki"
                    label="ご注意事項"
                    label-class="hidden-label"
                    code-list-id="original"
                    required
                    :original-list="[{
                      key: 1,
                      value: '以下記載の内容を全て説明済みである。'
                    }]"
                  ></ifa-input-check>
                  <ifa-mutual-fund-accumulate-input-check-text></ifa-mutual-fund-accumulate-input-check-text>
                </div>
              </el-row>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <ifa-button
        text="設定変更確認"
        color="primary"
        action-type="originalAction"
        style="padding: 0"
        @app-action-handler="handleValidate"
      ></ifa-button>
    </div>
  </el-form>

  <ifa-mutual-fund-accumulate-editable-confirm
    v-if="confirmVisible"
    :visible="confirmVisible"
    :confirm-info="confirmInfo"
    :confirm-payload="A009RequestModel"
    @on-cancel="handleCloseFormPanel"
    @on-close="handleClose"
  ></ifa-mutual-fund-accumulate-editable-confirm>

  <ifa-requester
    id="ifaMutualFundAccumulateSettingChangeInputConfirmA009"
    action-id="SUB0202_0403-03_2#A009"
    action-type="requestAction"
    :request-model="A009RequestModel"
    @response-handler="settingChangeInputConfirmA009Handler($event)"
    @response-error-handler="settingChangeInputConfirmA009ErrorHandler"
  ></ifa-requester>
</template>

<script>
import { BigNumber } from 'bignumber.js'

import IfaInputRadio from '@/components/Input/IfaInputRadio.vue'
import IfaInputCheck from '@/components/Input/IfaInputCheck.vue'
import IfaInputSelect from '@/components/Input/IfaInputSelect.vue'
import IfaInputAmount from '@/components/Input/IfaInputAmount.vue'
import IfaYen80DomainModel from '@/resource/domain/IfaYen80DomainModel.json'
import IfaButton from '@/components/Button/IfaButton.vue'
import IfaText from '@/components/Input/IfaText.vue'
import IfaBalloonIcon from '@/components/icon/IfaBalloonIcon.vue'
import IfaUtils from '@/utils/ifaUtils'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import IfaMutualFundAccumulateEditableConfirm from './IfaMutualFundAccumulateEditableConfirm.vue'

import IfaMutualFundAccumulateInputCheckText from './IfaMutualFundAccumulateInputCheckText.vue'
import { IfaMutualFundAccumulateSettingChangeInputConfirmA009RequestModel } from '../js/IfaMutualFundAccumulateSettingChangeInputConfirmA009RequestModel'
import { IfaMutualFundAccumulateSettingChangeInputFormModel } from '../js/IfaMutualFundAccumulateSettingChangeInputFormModel'

// 円貨8_0 domain max value
const DOMAIN_80_MAX_VALUE = 99999999

export default {
  components: {
    IfaInputRadio,
    IfaText,
    IfaInputCheck,
    IfaInputSelect,
    IfaInputAmount,
    IfaButton,
    IfaBalloonIcon,
    IfaRequester,
    IfaMutualFundAccumulateEditableConfirm,
    IfaMutualFundAccumulateInputCheckText

  },
  props: {
    info: { type: Object, required: true }
  },
  emits: ['on-close'],
  data() {
    return {
      IfaYen80DomainModel,
      form: new IfaMutualFundAccumulateSettingChangeInputFormModel(this.info),
      confirmVisible: false,
      confirmInfo: null,
      confirmForm: {}
    }
  },
  computed: {
    rules() {
      return {
        settingReserveWeek: [
          {
            required: true,
            message: '設定日を選択してください。',
            trigger: 'change'
          },
          {
            validator: (_rule, value, callback) => {
              if (value === '0') {
                callback(new Error('設定日を選択してください。'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ],
        settingReserveMonth: [
          {
            required: true,
            message: '設定日を選択してください。',
            trigger: 'change'
          },
          {
            validator: (_rule, value, callback) => {
              if (value === '00') {
                callback(new Error('設定日を選択してください。'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ],
        settingReserveOddDay: [
          {
            required: true,
            message: '設定日を選択してください。',
            trigger: 'change'
          },
          {
            validator: (_rule, value, callback) => {
              if (value === '00') {
                callback(new Error('設定日を選択してください。'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ],
        settingReserveEvenDay: [
          {
            required: true,
            message: '設定日を選択してください。',
            trigger: 'change'
          },
          {
            validator: (_rule, value, callback) => {
              if (value === '00') {
                callback(new Error('設定日を選択してください。'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ],
        settingBonus1Month: [
          {
            validator: (_rule, value, callback) => {
              if (value === '00') {
                callback(new Error('月を選択してください。'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ],
        settingBonus1Day: [
          {
            validator: (_rule, value, callback) => {
              if (value === '00') {
                callback(new Error('日を選択してください。'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ],
        settingBonus2Month: [
          {
            validator: (_rule, value, callback) => {
              if (value === '00' && this.form.settingBonus2Day !== '00') {
                callback(new Error('月を選択してください。'))
              } else if (value === '00' && this.form.settingBonus2Day === '00') {
                this.$refs?.formRef.clearValidate(['settingBonus2Month', 'settingBonus2Day'])
                callback()
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ],
        settingBonus2Day: [
          {
            validator: (_rule, value, callback) => {
              if (value === '00' && this.form.settingBonus2Month !== '00') {
                callback(new Error('日を選択してください。'))
              } else if (value === '00' && this.form.settingBonus2Month === '00') {
                this.$refs?.formRef.clearValidate(['settingBonus2Month', 'settingBonus2Day'])
                callback()
              } else {
                callback()
              }
            },
            trigger: 'change'
          }
        ]
      }
    },
    minAppAmount() {
      switch (this.info.reserveOrderUnit) {
        case '0':
          return '100円'
        case '1':
          return '1万円'
        case '2':
          return '1千円'
        case '3':
          return '500円'
        default:
          return ''
      }
    },
    minSettingAmount() {
      switch (this.info.reserveOrderUnit) {
        case '0':
          return 100
        case '1':
          return 10000
        case '2':
          return 1000
        case '3':
          return 500
        default:
          return 1
      }
    },
    numberOfApply() {
      switch (this.form.courseType) {
        // 毎日
        case '1':
          return '247'
        // 毎週
        case '2':
          return '52'
        // 毎月
        case '3':
          return '12'
        default:
          return '0'
      }
    },
    // ボーナス設定回数
    bonusSettingTimes() {
      const { settingBonus1Month, settingBonus2Month } = this.info

      if (settingBonus1Month) {
        if (settingBonus2Month) return '2'

        return '1'
      }

      return '0'
    },
    availableAppSettingAmount() {
      const {
        accountTypeName,
        settingBonusAmount,
        oneYearSumAmountTotal,
        bonusSumAmountNisaReserve,
        oneYearLimitNisaReserveAmount,
        oneYearSumAmount: {
          nisaReserveSettingAmount
        }
      } = this.info

      if (this.form.accountType === 'I') {
        // 1年あたりのNISA（つみたて投資枠）設定金額上限
        const oneYearLimitNisaReserveAmountVal = new BigNumber(oneYearLimitNisaReserveAmount)

        // 1年あたりの積立金額（概算）.NISA（つみたて投資枠）積立金額
        const nisaReserveSettingAmountVal = new BigNumber(nisaReserveSettingAmount)

        // NISA（つみたて投資枠）ボーナス月設定の合計金額
        const bonusSumAmountNisaReserveVal = new BigNumber(bonusSumAmountNisaReserve)

        // 1年あたりの設定金額（概算）
        const oneYearSumAmountTotalVal = new BigNumber(oneYearSumAmountTotal)

        // ボーナス設定回数
        const bonusSettingTimesVal = new BigNumber(this.bonusSettingTimes)
        // ボーナス設定金額
        const settingBonusAmountVal = new BigNumber(settingBonusAmount ?? '0')

        const minSettingAmountVal = new BigNumber(this.minSettingAmount)

        const settingBonusAmountTimesVal = bonusSettingTimesVal.times(settingBonusAmountVal)

        const diffVal = nisaReserveSettingAmountVal
          .plus(bonusSumAmountNisaReserveVal)
          .minus(oneYearSumAmountTotalVal)
          .minus(settingBonusAmountTimesVal)

        const diffTotalVal = oneYearLimitNisaReserveAmountVal.minus(diffVal)

        let divisor
        if (this.form.courseType === '1') {
          // 247日
          divisor = new BigNumber(247)
        } else if (this.form.courseType === '2') {
          // 52週
          divisor = new BigNumber(52)
        } else if (this.form.courseType === '3') {
          // 12ヶ月
          divisor = new BigNumber(12)
        } else {
          return {
            text: `-円（${accountTypeName}）`,
            value: DOMAIN_80_MAX_VALUE
          }
        }

        // ※ 算出結果に端数が発生する場合は、小数点以下切り捨てとする。"
        // e.g: 123.111 -> 123
        const result = diffTotalVal
          .dividedBy(divisor)
          .integerValue(BigNumber.ROUND_DOWN)

        return {
          text: `${IfaUtils.addComma(result.toString())}円（${accountTypeName}）`,
          value: result.isLessThan(minSettingAmountVal)
            ? DOMAIN_80_MAX_VALUE
            : result.toNumber()
        }
      }

      return {
        text: `-円（${accountTypeName}）`,
        value: DOMAIN_80_MAX_VALUE
      }
    },
    availableAppSettingBonusAmount() {
      const {
        accountTypeName,
        settingBonusAmount,
        oneYearSumAmountTotal,
        bonusSumAmountNisaReserve,
        oneYearLimitNisaReserveAmount,
        oneYearSumAmount: {
          nisaReserveSettingAmount
        }
      } = this.info

      if (!this.isLegalSettingAmount(0, DOMAIN_80_MAX_VALUE)) {
        return {
          text: `-円（${accountTypeName}）`,
          value: DOMAIN_80_MAX_VALUE
        }
      }

      if (this.form.accountType === 'I') {
        // 1年あたりのNISA（つみたて投資枠）設定金額上限
        const oneYearLimitNisaReserveAmountVal = new BigNumber(oneYearLimitNisaReserveAmount)

        // 1年あたりの積立金額（概算）.NISA（つみたて投資枠）積立金額
        const nisaReserveSettingAmountVal = new BigNumber(nisaReserveSettingAmount)

        // NISA（つみたて投資枠）ボーナス月設定の合計金額
        const bonusSumAmountNisaReserveVal = new BigNumber(bonusSumAmountNisaReserve)

        // 1年あたりの設定金額（概算）
        const oneYearSumAmountTotalVal = new BigNumber(oneYearSumAmountTotal)

        // ボーナス設定回数
        const bonusSettingTimesVal = new BigNumber(this.bonusSettingTimes)
        // ボーナス設定金額
        const settingBonusAmountVal = new BigNumber(settingBonusAmount ?? '0')

        const settingBonusAmountTimesVal = bonusSettingTimesVal.times(settingBonusAmountVal)

        const settingAmountVal = new BigNumber(this.form.settingAmount)

        const numberOfApplyVal = new BigNumber(this.numberOfApply)

        const minSettingAmountVal = new BigNumber(this.minSettingAmount)

        const settingAmountTimesVal = settingAmountVal.times(numberOfApplyVal)

        const diffVal = nisaReserveSettingAmountVal
          .plus(bonusSumAmountNisaReserveVal)
          .minus(oneYearSumAmountTotalVal)
          .minus(settingBonusAmountTimesVal)
          .plus(settingAmountTimesVal)

        const diffTotalVal = oneYearLimitNisaReserveAmountVal.minus(diffVal)

        // ボーナス２設定月に数字を設定した時：2 それ以外は、全て1
        const dynamicSettingBonusTimesVal = new BigNumber(
          this.form.settingBonus2Month && this.form.settingBonus2Month !== '00' ? '2' : '1'
        )

        // ※ 算出結果に端数が発生する場合は、小数点以下切り捨てとする。"
        // e.g: 123.111 -> 123
        const calcVal = diffTotalVal
          .dividedBy(dynamicSettingBonusTimesVal)
          .integerValue(BigNumber.ROUND_DOWN)

        const isPositive = calcVal.isPositive()
        const result = isPositive ? calcVal : new BigNumber(0)

        // 上記計算結果がマイナスの場合、""０円""を表示。
        return {
          text: `${isPositive ? IfaUtils.addComma(calcVal.toString()) : '0'}円（${accountTypeName}）`,
          value: result.isLessThan(minSettingAmountVal)
            ? DOMAIN_80_MAX_VALUE
            : result.toNumber()
        }
      }

      return {
        text: `-円（${accountTypeName}）`,
        value: DOMAIN_80_MAX_VALUE
      }
    },
    isInputDisabled() {
      return this.form.settingBonus1Month === '00' || this.form.settingBonus1Day === '00'
    },
    numberOfApplications() {
      // 毎日
      if (this.form.courseType === '1') return '23'
      // 毎週
      if (this.form.courseType === '2') return '5'
      // 毎月
      if (this.form.courseType === '3') return '1'
      // 複数日
      if (this.form.courseType === '4') {
        return `${this.form.settingReserveMultiDay.length}`
      }
      // 奇数月 | 偶数月
      if (this.form.courseType === '5' || this.form.courseType === '6') return '0.5'

      return ''
    },
    applicationAmount() {
      const min = this.minSettingAmount
      const max = this.availableAppSettingBonusAmount.value

      if (!this.isLegalSettingAmount(min, max)) return '-円'

      const settingAmountVal = new BigNumber(this.form.settingAmount)
      const numberOfApplicationsVal = new BigNumber(this.numberOfApplications)

      const isLegalSettingReserveWeek = this.form.settingReserveWeek && Number(this.form.settingReserveWeek) > 0
      const isLegalSettingReserveMonth = this.form.settingReserveMonth && Number(this.form.settingReserveMonth) > 0
      const isLegalSettingReserveMultiDay = this.form.settingReserveMultiDay && this.form.settingReserveMultiDay.length > 0
      const isLegalSettingReserveOddDay = this.form.settingReserveOddDay && Number(this.form.settingReserveOddDay) > 0
      const isLegalSettingReserveEvenDay = this.form.settingReserveEvenDay && Number(this.form.settingReserveEvenDay) > 0

      // ※ 算出結果に端数が発生する場合は、小数点以下切り上げとする。
      const val = settingAmountVal
        .times(numberOfApplicationsVal)
        .integerValue(BigNumber.ROUND_CEIL)
        .toString()

      const formattedVal = `${IfaUtils.addComma(val)}円`

      const courseTypeConditions = {
        '1': true,
        '2': isLegalSettingReserveWeek,
        '3': isLegalSettingReserveMonth,
        '4': isLegalSettingReserveMultiDay,
        '5': isLegalSettingReserveOddDay,
        '6': isLegalSettingReserveEvenDay
      }

      if (courseTypeConditions[this.form.courseType]) return formattedVal

      return '-円'
    },
    A009RequestModel() {
      return new IfaMutualFundAccumulateSettingChangeInputConfirmA009RequestModel(this.form, this.info)
    }
  },
  methods: {
    handleReset() {
      this.$refs.formRef.resetFields()
      this.form = new IfaMutualFundAccumulateSettingChangeInputFormModel(this.info)
    },
    handleCloseFormPanel() {
      this.confirmVisible = false
      this.confirmInfo = null
    },
    handleClose() {
      this.confirmVisible = false
      this.$emit('on-close')
    },
    handleSettingDateChange(e) {
      if (e === '00') {
        this.form.settingBonus2Month = '00'
        this.form.settingBonus2Day = '00'
      }
    },
    handleCourseTypeChange() {
      this.form.settingReserveWeek = '0'
      this.form.settingReserveMonth = '00'
      this.form.settingReserveOddDay = '00'
      this.form.settingReserveEvenDay = '00'
      this.form.settingReserveMultiDay = []
    },
    handleBonusSettingChange() {
      this.form.settingBonusAmount = ''
      this.form.settingBonus1Month = '00'
      this.form.settingBonus1Day = '00'
      this.form.settingBonus2Month = '00'
      this.form.settingBonus2Day = '00'
    },
    handleValidate() {
      const formEl = this.$refs.formRef
      formEl.validate((valid) => {
        if (valid) {
          this.$nextTick(() => {
            document.getElementById('ifaMutualFundAccumulateSettingChangeInputConfirmA009').click()
          })
        }
      })
    },
    isLegalSettingAmount(min, max) {
      const val = new BigNumber(this.form.settingAmount)
      const minVal = new BigNumber(min)
      const maxVal = new BigNumber(max)

      // determine whether it is empty (here it is assumed to be an empty string or null)
      if (
        this.form.settingAmount === null ||
        this.form.settingAmount === '' ||
        isNaN(this.form.settingAmount)
      ) return false

      // determine whether it is a number
      if (!val.isFinite()) return false

      // is an integer
      if (!val.isInteger()) return false

      // not less than the minimum value
      if (val.isLessThan(minVal)) return false

      // not more than the maximum value
      if (val.isGreaterThan(maxVal)) return false

      return true
    },
    settingChangeInputConfirmA009Handler(res) {
      this.$_logDebug(res)

      this.confirmInfo = res?.dataList?.[0] ?? {}
      this.confirmVisible = true
    },
    settingChangeInputConfirmA009ErrorHandler(error) {
      this.$_logError(error)
    }
  }
}
</script>

<style lang="scss" scoped>
.form-area__section {
  height: auto;
  padding-top: 0.5rem;
  padding-bottom: 1.0rem;
  white-space: pre-wrap;
}

.customer-label {
  height: 100%;
  width: 180px;
  padding-left: var(--el-form-item__label--padding-left);
  color: #18181A;
  margin: 0.1rem 0.5rem;
  font-weight: 700;

  &::before {
    content: '*';
    color: var(--el-color-danger);
    margin-right: 4px;
  }
}

.icon-wrapper {
    display: flex;
    align-items: center;
  }

  :deep(.el-icon-info) {
    font-size: 16px;
    padding-bottom: 1.5px;
  }

:deep(.el-checkbox) {
  margin-right: 5px;
}

:deep(.el-checkbox__label) {
  padding-left: 2px;
}

:deep(.el-form.el-form--label-left .el-form-item.asterisk-left .el-form-item__label) {
  align-self: flex-start;
}

:deep(.label-class) .el-form-item__label {
  width: auto !important;
  line-height: normal;
  font-weight: normal;
  padding: 0;
  margin-left: -5px;
  align-self: baseline !important;

  &::before {
    content: '' !important;
  }
}

:deep(.fake-label) {
  width: 180px;
  align-self: flex-start !important;
  &::before {
    content: '*';
    color: #f56c6c;
    margin-right: 4px;
  }
}

:deep(.fake-label-no-required) {
  width: 180px;
  align-self: flex-start !important;
  &::before {
    content: '';
    margin-right: 8px;
  }
}

:deep(.no-label-class .el-form-item__content) {
  margin-left: 0 !important;
}

:deep(.hidden-label > label) {
  display: none !important;
}

:deep(.label-item-class) .el-form-item__label {
  align-self: baseline !important;
}

:deep(.bonus-wrapper) .el-form-item {
  width: 120px;
}

:deep(.el-radio-group) {
  margin-left: 0 !important;
}
</style>
