<template>
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <div v-if="form.subscribed">
        <el-row>
          <ul
            type="disc"
            class="error-message"
          >
            <span>
              ※米国株式貸株サービスをご利用の方は、米国株式を委託保証金の代用としての差入を行えません。
            </span><br>
            <span style="margin-left: 17px">
              委託保証金の代用としての差入を行いたい場合は、米国株式貸株サービスをご解約ください。
            </span>
          </ul>
        </el-row>
      </div>
      <div v-if="!form.subscribed">
        <el-form
          ref="form"
          :model="form"
        >
          <div v-if="!protectionVisible">
            <el-col
              :span="12"
              style="margin-top: 1rem"
            >
              <span style="font-weight: bold;">振替指示 保護 → 代用</span>
            </el-col>
            <el-col
              class="__center"
              style="margin-top: 1rem; width: 1170px"
            >
              <span>保護預りはありません</span>
            </el-col>
          </div>
          <div v-else>
            <el-row style="width: 1170px;">
              <el-col
                :span="12"
                style="margin-top: 1rem"
              >
                <span style="font-weight: bold;">振替指示 保護 → 代用</span>
              </el-col>
              <el-col
                :span="12"
                style="text-align: right;margin-top: 1rem;"
              >
                <input
                  type="checkbox"
                  name="checkAllProtection"
                  :checked="selectAllProtection"
                  @change="allTransferProtectCollateralA005($event)"
                ><span style="margin-right: 3px;margin-left: 0.5rem; position: relative; top: -2px;">すべて振替</span>
              </el-col>
            </el-row>
            <el-col style="margin-top: 0.5rem">
              <table
                class="_table__body"
                style="width:1170px"
              >
                <thead>
                  <tr>
                    <th
                      class="_table__header __center"
                      style="width:400px"
                    >銘柄名</th>
                    <th
                      class="_table__header __center"
                      style="width:100px"
                    >代用掛目</th>
                    <th
                      class="_table__header __center"
                      style="width:100px"
                    >預り区分</th>
                    <th
                      class="_table__header __center"
                      style="width:200px"
                    >振替数量<br>（買付受渡未到来）</th>
                    <th
                      class="_table__header __center"
                      style="width:200px"
                    >評価単価<br>代用評価額</th>
                    <th
                      class="_table__header __center"
                      style=""
                    >代用振替<br>（保護→代用）</th>
                  </tr>
                </thead>
                <tbody
                  v-for="(item, idx) in form.protectionList"
                  :key="idx"
                >
                  <tr :style="item.isChecked ? 'background-color: #DDEBF7' : ''">
                    <td class="_table__data __left">{{ $_out(item.collateralDepositListBrandName) }}<br>{{ $_out(item.collateralDepositListBrandCode) }}</td>
                    <td class="_table__data __right">{{ $_withCommaInteger($_out(item.collateralAssessment)) }}%</td>
                    <td class="_table__data __center">{{ $_getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, item.specificAccountCode) }}</td>
                    <td class="_table__data __right">{{ $_withCommaInteger($_out(item.transferQuantity)) }}株<br>({{ $_withCommaInteger($_out(item.notReceivedQuantity)) }}株)</td>
                    <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(item.valuatePrice), 4) }} USD<br>{{ $_withCommaZeroPadding($_out(item.collateralValuation), 2) }} USD</td>
                    <td class="_table__data __center">
                      <input
                        v-model="item.isChecked"
                        type="checkbox"
                        name="protection"
                        :value="item.collateralDepositListBrandCode"
                        @change="collateralValuationSummaryA002($event, idx)"
                      >
                    </td>
                  </tr>
                </tbody>
                <tbody>
                  <tr>
                    <td class="__left __total">合計</td>
                    <td
                      class="__right __total"
                      colspan="5"
                    >
                      <span> {{ $_withCommaZeroPadding($_out(form.totalProtectCollateral), 2) }} USD</span></td>
                  </tr>
                </tbody>
              </table>
            </el-col>
          </div>

          <el-col
            style="margin-top: 3rem; width:680px;"
          >
            <span style="font-weight: bold;">振替指示 代用 → 保護</span>
          </el-col>
          <div v-if="!substitutionVisible">
            <el-col
              class="__center"
              style="margin-top: 1rem; width: 1170px"
            >
              <span>代用担保はありません</span>
            </el-col>
          </div>
          <div v-else>
            <el-col style="margin-top: 0.5rem">
              <table
                class="_table__body"
                style="width:680px"
              >
                <tbody>
                  <tr>
                    <th
                      class="_table__header __left"
                      style="width:300px"
                    >
                      引出可能金額
                    </th>
                    <td
                      class="_table__data __right"
                      style="width:380px"
                    > {{ $_withCommaZeroPadding($_out(form.ableWithdrawal), 2) }} USD</td>
                  </tr>
                </tbody>
              </table>
            </el-col>

            <el-col
              :span="12"
              style="margin-top: 1rem"
            >
            </el-col>
            <el-row style="width: 1170px;">
              <el-col
                style="text-align: right; margin-top: 1rem;"
              >
                <input
                  type="checkbox"
                  name="checkAllSubstitution"
                  :checked="selectAllSubstitution"
                  @change="allTransferCollateralProtectA006($event)"
                ><span style="margin-right: 3px;margin-left: 0.5rem; position: relative; top: -2px;">すべて振替</span>
              </el-col>
            </el-row>

            <el-col style="margin-top: 0.5rem">
              <table
                class="_table__body"
                style="width:1170px"
              >
                <thead>
                  <tr>
                    <th
                      class="_table__header __center"
                      style="width:400px"
                    >銘柄名</th>
                    <th
                      class="_table__header __center"
                      style="width:100px"
                    >代用掛目</th>
                    <th
                      class="_table__header __center"
                      style="width:100px"
                    >預り区分</th>
                    <th
                      class="_table__header __center"
                      style="width:200px"
                    >振替数量<br>（買付受渡未到来）</th>
                    <th
                      class="_table__header __center"
                      style="width:200px"
                    >評価単価<br>代用評価額</th>
                    <th
                      class="_table__header __center"
                      style=""
                    >代用振替<br>（代用→保護）</th>
                  </tr>
                </thead>
                <tbody
                  v-for="(item, idx) in form.substitutionList"
                  :key="idx"
                >
                  <tr :style="item.isChecked ? 'background-color: #DDEBF7' : ''">
                    <td class="_table__data __left">{{ $_out(item.collateralDepositListBrandName) }}<br>{{ $_out(item.collateralDepositListBrandCode) }}</td>
                    <td class="_table__data __right">{{ $_withCommaInteger($_out(item.collateralAssessment)) }}%</td>
                    <td class="_table__data __center">{{ $_getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, item.specificAccountCode) }}</td>
                    <td class="_table__data __right">{{ $_withCommaInteger($_out(item.transferQuantity)) }}株<br>({{ $_withCommaInteger($_out(item.notReceivedQuantity)) }}株)</td>
                    <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(item.valuatePrice), 4) }} USD<br>{{ $_withCommaZeroPadding($_out(item.collateralValuation), 2) }} USD</td>
                    <td class="_table__data __center">
                      <input
                        v-model="item.isChecked"
                        type="checkbox"
                        name="substitution"
                        :value="item.collateralDepositListBrandCode"
                        @change="collateralValuationSummaryA002($event, idx)"
                      >
                    </td>
                  </tr>
                </tbody>
                <tbody>
                  <tr>
                    <td class="__left __total">合計</td>
                    <td
                      class="__right __total"
                      colspan="5"
                    >
                      <span> {{ $_withCommaZeroPadding($_out(form.totalCollateralProtect), 2) }} USD</span></td>
                  </tr>
                </tbody>
              </table>
            </el-col>
          </div>

          <el-row style="width: 600px">
            <el-col
              style="margin-top: 4rem"
              :span="12"
            >
              <span
                class="text_bottom"
                style="font-weight: bold;"
              >振替結果（予定）</span>
            </el-col>
            <el-col
              style="margin-top: 4rem; text-align: right;"
              :span="12"
            >
              <ifa-button
                id="btnDisplayResult"
                name="btnDisplayResult"
                text="結果を表示"
                color="primary"
                style="padding-right: 0;"
                :disabled="displayResultBtnDisabled"
                :request-model="IfaForeignMarginCollateralTransferInputA003RequestModel"
                action-id="SUB0202_0305-01_1#A003"
                action-type="requestAction"
                :pre-request-handler="preRequestHandlerDisplayResultA003"
                @response-handler="responseHandlerDisplayResultA003($event)"
              >
              </ifa-button>
            </el-col>
          </el-row>
          <el-col style="margin-top: 0.5rem">
            <table
              class="_table__body"
              style="width:600px"
            >
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width:200px"
                  ></th>
                  <th
                    class="_table__header __center"
                    style="width:200px"
                  >振替指示前</th>
                  <th
                    class="_table__header __center"
                    style=""
                  >振替指示後</th>
                </tr>
                <tr>
                  <th
                    class="_table__header __left"
                  >信用建余力
                  </th>
                  <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(form.marginPositionPowerBefore), 2) }} <span class="fix_width">USD</span></td>
                  <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(form.marginPositionPowerAfter), 2) }} <span class="fix_width">USD</span></td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left"
                  >委託保証金率
                  </th>
                  <td class="_table__data __right">{{ marginDepositRateBefore }} <span class="fix_width">%</span></td>
                  <td class="_table__data __right">{{ marginDepositRateAfter }} <span class="fix_width">%</span></td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left"
                  >代用評価額
                  </th>
                  <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(form.collateralValuationBefore), 2) }} <span class="fix_width">USD</span></td>
                  <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(form.collateralValuationAfter), 2) }} <span class="fix_width">USD</span></td>
                </tr>
              </tbody>
            </table>
          </el-col>
          <el-col style="margin-top: 2rem; margin-bottom: 1rem">
            <ifa-button
              id="btnTransferConfirm"
              name="btnTransferConfirm"
              text="振替確認"
              color="primary"
              style="padding-left: 0;"
              :disabled="btnDisabled"
              :request-model="IfaForeignMarginCollateralTransferInputA004RequestModel"
              action-id="SUB0202_0305-01_1#A004"
              action-type="requestAction"
              :pre-request-handler="preRequestHandlerTransferConfirmA004"
              @response-handler="responseHandlerTransferConfirmA004($event)"
            >
            </ifa-button>
          </el-col>
        </el-form>
      </div>
    </div>
    <!--確認画面-->
    <ifa-foreign-margin-collateral-transfer-confirm
      :is-visible="confirmIsVisible"
      :form-data="confirmData"
      @close-modal="handleCloseModal"
      @guarantee-transfer-finish="handleGuaranteeTransferFinish"
    >
    </ifa-foreign-margin-collateral-transfer-confirm>

    <!--完了画面-->
    <ifa-foreign-margin-collateral-transfer-complete
      :is-visible="dialogFinish"
      :form-data="completeData"
      @close-modal="handlebackOrder"
    >
    </ifa-foreign-margin-collateral-transfer-complete>
    <ifa-requester
      id="ifaForeignMarginCollateralTransferInputInitializeA001"
      action-id="SUB0202_0305-01_1#A001"
      action-type="requestAction"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="errorHandlerInitializeA001($event)"
    ></ifa-requester>
  </div>
</template>
<script>
import { BigNumber } from 'bignumber.js'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaForeignMarginCollateralTransferConfirm from './IfaForeignMarginCollateralTransferConfirm.vue'
import IfaForeignMarginCollateralTransferComplete from './IfaForeignMarginCollateralTransferComplete.vue'
import { notifyMessage, getMessage } from '@/utils/errorHandler'
import { IfaForeignMarginCollateralTransferInputFormModel } from './js/IfaForeignMarginCollateralTransferInputFormModel'
import { IfaForeignMarginCollateralTransferInputA003RequestModel } from './js/IfaForeignMarginCollateralTransferInputA003RequestModel'
import { IfaForeignMarginCollateralTransferInputA004RequestModel } from './js/IfaForeignMarginCollateralTransferInputA004RequestModel'
export default {
  components: {
    screenTitle,
    IfaForeignMarginCollateralTransferConfirm,
    IfaForeignMarginCollateralTransferComplete
  },
  emits: ['initializeError', 'update-customer-portal'],
  data() {
    return {
      title: this.$store.getters.pageInfo.label,
      confirmIsVisible: false,
      dialogFinish: false,
      displayResultBtnDisabled: true, // 結果を表示ボタンの制御。ボタン非活性の場合true
      selectAllProtection: false, // 振替指示（保護→代用）すべて振替チェックボックスの値
      selectAllSubstitution: false, // 振替指示（代用→保護）すべて振替チェックボックスの値
      selectListProtection: [], // 振替指示（保護→代用）選択中のリスト保持用
      selectListSubstitution: [], // 振替指示（代用→保護）選択中のリスト保持用
      protectionVisible: true, // 振替指示（保護→代用）エリアの表示制御。表示する場合true
      substitutionVisible: true, // 振替指示（代用→保護）エリアの表示制御。表示する場合true
      form: new IfaForeignMarginCollateralTransferInputFormModel(),
      confirmData: {},
      completeData: {}
    }
  },
  computed: {
    // 振替確認ボタンの活性／非活性制御
    btnDisabled() {
      if (!this.form.protectionFlag && !this.form.substitutionFlag) {
        return true
      }
      return false
    },
    IfaForeignMarginCollateralTransferInputA003RequestModel() {
      return new IfaForeignMarginCollateralTransferInputA003RequestModel(this.form)
    },
    IfaForeignMarginCollateralTransferInputA004RequestModel() {
      return new IfaForeignMarginCollateralTransferInputA004RequestModel(this.form)
    },
    marginDepositRateBefore() {
      if (this.form.marginDepositRateBefore === null) {
        return '--'
      }
      return this.$_withCommaZeroPadding(this.$_out(this.form.marginDepositRateBefore), 2)
    },
    marginDepositRateAfter() {
      if (this.form.marginDepositRateAfter === null) {
        return '--'
      }
      return this.$_withCommaZeroPadding(this.$_out(this.form.marginDepositRateAfter), 2)
    }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('ifaForeignMarginCollateralTransferInputInitializeA001').click()
      })
    },
    responseHandlerInitializeA001(data) {
      if (data.totalSize === 0) {
        // 返却データなし
        this.protectionVisible = false
        this.substitutionVisible = false
      } else {
        this.form.ableWithdrawal = data.dataList[0].ableWithdrawal

        this.form.protectionList = data.dataList[0].protection
        if (this.form.protectionList !== undefined) {
          this.protectionVisible = this.form.protectionList.length !== 0
        } else {
          // リストが未設定の場合
          this.protectionVisible = false
        }

        this.form.substitutionList = data.dataList[0].substitution
        if (this.form.substitutionList !== undefined) {
          this.substitutionVisible = this.form.substitutionList.length !== 0
        } else {
          // リストが未設定の場合
          this.substitutionVisible = false
        }
        this.form.subscribed = data.dataList[0].subscribed
      }

      this.resetAll()
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    // 代用評価額の合計
    collateralValuationSummaryA002(event, idx) {
      // 振替結果（予定）のクリア
      this.initializedDisplayResult()
      // チェック行の色変え <tr>タグにて指定

      // 合計額の計算、合計欄に表示
      if (event.target.name === 'protection') {
        const data = this.form.protectionList[idx]
        if (event.target.checked) {
          this.form.totalProtectCollateral = (new BigNumber(this.form.totalProtectCollateral)).plus(new BigNumber(data.collateralValuation))
        } else {
          this.form.totalProtectCollateral = (new BigNumber(this.form.totalProtectCollateral)).minus(new BigNumber(data.collateralValuation))
        }
      } else if (event.target.name === 'substitution') {
        const data = this.form.substitutionList[idx]
        if (event.target.checked) {
          this.form.totalCollateralProtect = (new BigNumber(this.form.totalCollateralProtect)).plus(new BigNumber(data.collateralValuation))
        } else {
          this.form.totalCollateralProtect = (new BigNumber(this.form.totalCollateralProtect)).minus(new BigNumber(data.collateralValuation))
        }
      }
      // 選択リストの再計算
      this.onChangeProtection()
      this.onChangeSubstitution()
      this.changeDisplayResultBtnDisabled()
    },
    preRequestHandlerDisplayResultA003() {
      this.preCheck()
    },
    responseHandlerDisplayResultA003(data) {
      this.form.marginPositionPowerBefore = data.dataList[0].currentPower.marginBuyingPower
      this.form.marginDepositRateBefore = data.dataList[0].currentPower.depositRate
      this.form.collateralValuationBefore = data.dataList[0].currentPower.totalCollateralValue
      this.form.marginPositionPowerAfter = data.dataList[0].afterPower.marginBuyingPower
      this.form.marginDepositRateAfter = data.dataList[0].afterPower.depositRate
      this.form.collateralValuationAfter = data.dataList[0].afterPower.totalCollateralValue
      this.displayResultBtnDisabled = true // ボタン非活性
    },
    preRequestHandlerTransferConfirmA004() {
      this.preCheck()
    },
    responseHandlerTransferConfirmA004(data) {
      // A004レスポンスを確認画面へ送る
      this.confirmData = data.dataList[0]
      this.displayResultBtnDisabled = true // ボタン非活性
      this.$nextTick(() => {
        this.confirmIsVisible = true
      })
    },
    allTransferProtectCollateralA005(event) {
      // すべて振替（保護→代用）
      this.selectAllProtection = event.target.checked
      this.form.totalProtectCollateral = new BigNumber(0)
      this.selectListProtection = []
      if (this.selectAllProtection) {
        // チェックされた場合、すべてチェック状態にする
        this.form.protectionFlag = true

        // 合計値計算
        for (const item of this.form.protectionList) {
          item.isChecked = true
          this.form.totalProtectCollateral = (new BigNumber(this.form.totalProtectCollateral)).plus(new BigNumber(item.collateralValuation))
          this.selectListProtection.push(item)
        }
      } else {
        this.form.protectionFlag = false
        for (const item of this.form.protectionList) {
          item.isChecked = false
        }
        // 結果表示エリアの初期化
        this.initializedDisplayResult()
      }
      this.changeDisplayResultBtnDisabled()
    },
    allTransferCollateralProtectA006(event) {
      // すべて振替（代用→保護）
      this.selectAllSubstitution = event.target.checked
      this.form.totalCollateralProtect = new BigNumber(0)
      this.selectListSubstitution = []
      if (this.selectAllSubstitution) {
        // チェックされた場合、すべてチェック状態にする
        this.form.substitutionFlag = true

        // 合計値計算
        for (const item of this.form.substitutionList) {
          item.isChecked = true
          this.form.totalCollateralProtect = (new BigNumber(this.form.totalCollateralProtect)).plus(new BigNumber(item.collateralValuation))
          this.selectListSubstitution.push(item)
        }
      } else {
        this.form.substitutionFlag = false
        for (const item of this.form.substitutionList) {
          item.isChecked = false
        }
        // 結果表示エリアの初期化
        this.initializedDisplayResult()
      }
      this.changeDisplayResultBtnDisabled()
    },
    // 初期化
    resetAll() {
      // フォームが表示されている場合に初期化する
      if (!this.form.subscribed) {
        // 振替指示（保護→代用）エリアが表示されている場合に初期化する
        if (this.protectionVisible) {
          for (const item of this.form.protectionList) {
            item.isChecked = false
          }
          this.selectAllProtection = false
          this.selectListProtection = []
          this.form.protectionFlag = false
          this.form.totalProtectCollateral = new BigNumber(0)
        }

        // 振替指示（代用→保護）エリアが表示されている場合に初期化する
        if (this.substitutionVisible) {
          for (const item of this.form.substitutionList) {
            item.isChecked = false
          }
          this.selectAllSubstitution = false
          this.selectListSubstitution = []
          this.form.substitutionFlag = false
          this.form.totalCollateralProtect = new BigNumber(0)
        }

        this.form.selected = []
        this.form.transferClassification = ''

        // 結果表示エリアの初期化
        this.initializedDisplayResult()
        this.displayResultBtnDisabled = true // 非活性
      }
    },
    // 代用振替入力画面に遷移
    handleCloseModal() {
      this.confirmIsVisible = false
    },
    // 代用振替完了画面に遷移
    handleGuaranteeTransferFinish(response) {
      // 確認画面のA001レスポンスを完了画面へ送る
      this.completeData = response
      this.confirmIsVisible = false
      this.dialogFinish = true
    },
    // 注文画面へ遷移
    handlebackOrder() {
      this.dialogFinish = false
      this.form = new IfaForeignMarginCollateralTransferInputFormModel()
      document.getElementById('ifaForeignMarginCollateralTransferInputInitializeA001').click()
      this.$nextTick(() => {
        this.$emit('update-customer-portal')
      })
    },
    // 結果表示エリアの初期化
    initializedDisplayResult() {
      this.form.marginPositionPowerBefore = '--'
      this.form.marginPositionPowerAfter = '--'
      this.form.marginDepositRateBefore = '--'
      this.form.marginDepositRateAfter = '--'
      this.form.collateralValuationBefore = '--'
      this.form.collateralValuationAfter = '--'
    },
    // 保護→代用のチェックボックス制御（個別チェック時）
    onChangeProtection() {
      let count = 0
      // チェックボックスの状態確認
      if (this.form.protectionList !== undefined) {
        this.selectListProtection = this.form.protectionList.filter(item => item.isChecked)
        count = this.selectListProtection.length
      }
      // チェック個数確認
      if (count === 0) {
        // チェックが0個の場合、保護→代用の選択なし
        this.form.protectionFlag = false
        this.selectAllProtection = false
      } else {
        // チェックが1つでもある場合、保護→代用の選択あり
        this.form.protectionFlag = true

        if (count === this.form.protectionList.length) {
          // すべてのチェックが入っている場合、すべて振替（保護→代用）にチェックを入れる
          this.selectAllProtection = true
        } else {
          this.selectAllProtection = false
        }
      }
    },
    // 代用→保護のチェックボックス制御（個別チェック時）
    onChangeSubstitution() {
      let count = 0
      // チェックボックスの状態確認
      if (this.form.substitutionList !== undefined) {
        this.selectListSubstitution = this.form.substitutionList.filter(item => item.isChecked)
        count = this.selectListSubstitution.length
      }
      // チェック個数確認
      if (count === 0) {
        // チェックが0個の場合、代用→保護の選択なし
        this.form.substitutionFlag = false
        this.selectAllSubstitution = false
      } else {
        // チェックが1つでもある場合、代用→保護の選択あり
        this.form.substitutionFlag = true

        if (count === this.form.substitutionList.length) {
          // すべてのチェックが入っている場合、すべて振替（代用→保護）にチェックを入れる
          this.selectAllSubstitution = true
        } else {
          this.selectAllSubstitution = false
        }
      }
    },
    // 結果を表示ボタンの活性／非活性切替とリクエスト値算出
    changeDisplayResultBtnDisabled() {
      // どれかにチェックがある場合、ボタンを活性化する
      if (this.form.protectionFlag || this.form.substitutionFlag) {
        this.displayResultBtnDisabled = false // ボタン活性
        if (this.form.protectionFlag) {
          // 保護→代用にチェックがある場合
          this.form.selected = this.selectListProtection
          this.form.transferClassification = 'SECURITIES_DEPOSIT'
        } else if (this.form.substitutionFlag) {
          // 代用→保護にチェックがある場合
          this.form.selected = this.selectListSubstitution
          this.form.transferClassification = 'SECURITIES_WITHDRAWAL'
        }
      } else {
        this.displayResultBtnDisabled = true // ボタン非活性
        this.form.selected = []
        this.form.transferClassification = ''
      }
    },
    // チェックフラグ同時指示チェック、振替選択30件チェック
    preCheck() {
      let count = 0
      if (this.form.protectionFlag && this.form.substitutionFlag) {
        // どちらにもチェックがある場合
        notifyMessage(-1, getMessage('errors.frs.transferCondition.inconsistent'), this.title)
        throw new Error(getMessage('errors.frs.transferCondition.inconsistent'))
      } else if (this.form.protectionFlag) {
        // 保護→代用にチェックがある場合
        for (const item of this.form.protectionList) {
          if (item.isChecked) {
            count += 1
          }
        }
      } else if (this.form.substitutionFlag) {
        // 代用→保護にチェックがある場合
        for (const item of this.form.substitutionList) {
          if (item.isChecked) {
            count += 1
          }
        }
      }
      if (count > 30) {
        notifyMessage(-1, getMessage('errors.frs.transferOrder.exceeded'), this.title)
        throw new Error(getMessage('errors.frs.transferOrder.exceeded'))
      }
      return true
    }
  }
}

</script>
<style lang="scss" scoped>
@import "@/styles/table.scss";
:deep(.el-table) .__center {
  text-align: center;
}
:deep(.el-table) .__left {
  text-align: left;
}
:deep(.el-table) .__right {
  text-align: right;
}
:deep(.el-table) .__right {
  text-align: right;
}
:deep(.el-form-item__label) {
  font-weight: normal;
  text-align: right;
  width: 135px;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
:deep(.el-form-item__content) {
  text-align: right;
  width: 135px;
}
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.__total{
  padding: 2px 14px 2px 14px;
}
.text_bottom {
    position: absolute;
    bottom: 0;
    left: 0;
}
.fix_width {
  width: 27.9px;
  display: inline-block;
  text-align: left;
}
</style>
