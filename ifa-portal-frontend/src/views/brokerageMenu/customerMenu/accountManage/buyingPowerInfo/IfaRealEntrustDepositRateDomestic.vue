<template>
  <el-dialog
    v-model="vmIsVisible"
    width="920px"
    :center="true"
    :title="formModel.title.name"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    @open="onShow"
  >
    <el-row class="form-button__wrapper">
      <el-col
        :span="18"
        style="padding-top: 1rem; font-size: 16px; color: black;"
      >
        {{ formModel.butenCode + '-' + $_zeroPadding(formModel.accountNumber, 7) }}
      </el-col>
      <el-col
        :span="5"
        style="text-align: right;"
      >
        <ifa-button
          id="btnUpdate"
          text="更新"
          color="primary"
          icon="RefreshRight"
          :parent-ref="$refs"
          action-type="requestAction"
          action-id="SUB0202_010302-03#A002"
          :msg-title="formModel.title.name"
          :request-model="IfaRealEntrustDepositRateDomesticA002RequestModel"
          @response-handler="responseHandlerA002"
        ></ifa-button>
        <ifa-button
          text="戻る"
          color="secondary"
          style="padding-right: 0;"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>
    <div>
      <el-card class="box-card">
        <el-row>
          <el-col>
            <table
              class="_table__body"
              style="width:100%; margin: 0 0 10px 0"
            >
              <tbody>
                <tr>
                  <th class="_table__header __center"></th>
                  <th class="_table__header __center">当日基準<br>更新：リアルタイム</th>
                  <th class="_table__header __center">翌営業日基準<br>更新：リアルタイム</th>
                  <th class="_table__header __center">値洗い基準<br>更新：夕刻・朝</th>
                </tr>
                <tr>
                  <td class="_table__data __left">委託保証金率(A/B)×100</td>
                  <td
                    :class="getDepositStyleBold(formModel.todayBase.entrustDepositRate)
                      + ' '
                      + getDepositStyleBgColor(formModel.todayBase.entrustDepositRate, formModel.todayBase.entrustDepositRateType)
                      + ' _table__data __right'"
                  >
                    {{ getDepositText(formModel.todayBase.entrustDepositRate) }}
                  </td>
                  <td
                    :class="getDepositStyleBold(formModel.nextBusinessDayBase.entrustDepositRate)
                      + ' '
                      + getDepositStyleBgColor(formModel.nextBusinessDayBase.entrustDepositRate, formModel.nextBusinessDayBase.entrustDepositRateType)
                      + ' _table__data __right'"
                  >
                    {{ getDepositText(formModel.nextBusinessDayBase.entrustDepositRate) }}
                  </td>
                  <td
                    :class="getDepositStyleBold(formModel.markToBase.entrustDepositRate)
                      + ' '
                      + getDepositStyleBgColor(formModel.markToBase.entrustDepositRate, formModel.markToBase.entrustDepositRateType)
                      + ' _table__data __right'"
                  >
                    {{ getDepositText(formModel.markToBase.entrustDepositRate) }}
                  </td>
                </tr>
                <!-- 当日基準.参考委託保証金率区分が"0"以外の場合表示（当日基準,翌営業日基準,値洗い基準の参考委託保証金率区分が異なることは業務上起こりえないため、当日基準を参照） -->
                <tr v-if="formModel.todayBase.referenceMarginDepositType !== '0'">
                  <td class="_table__data __left">参考委託保証金率((A+C)/B)×100</td>
                  <td
                    :class="getDepositStyleBold(formModel.todayBase.referenceMarginDeposit)
                      + ' '
                      + getDepositStyleBgColor(formModel.todayBase.referenceMarginDeposit, formModel.todayBase.referenceMarginDepositType)
                      + ' _table__data __right'"
                  >
                    {{ getDepositText(formModel.todayBase.referenceMarginDeposit) }}
                  </td>
                  <td
                    :class="getDepositStyleBold(formModel.nextBusinessDayBase.referenceMarginDeposit)
                      + ' '
                      + getDepositStyleBgColor(formModel.nextBusinessDayBase.referenceMarginDeposit, formModel.nextBusinessDayBase.referenceMarginDepositType)
                      + ' _table__data __right'"
                  >
                    {{ getDepositText(formModel.nextBusinessDayBase.referenceMarginDeposit) }}
                  </td>
                  <td
                    :class="getDepositStyleBold(formModel.markToBase.referenceMarginDeposit)
                      + ' '
                      + getDepositStyleBgColor(formModel.markToBase.referenceMarginDeposit, formModel.markToBase.referenceMarginDepositType)
                      + ' _table__data __right'"
                  >
                    {{ getDepositText(formModel.markToBase.referenceMarginDeposit) }}
                  </td>
                </tr>
                <tr>
                  <td class="_table__data __left">委託保証金現金</td>
                  <td class="_table__data __right">{{ getChashText(formModel.todayBase.marginDepositCache) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.nextBusinessDayBase.marginDepositCache) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.markToBase.marginDepositCache) }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">代用有価証券評価額合計</td>
                  <td class="_table__data __right">{{ getChashText(formModel.todayBase.alternativeSecuritiesTotal) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.nextBusinessDayBase.alternativeSecuritiesTotal) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.markToBase.alternativeSecuritiesTotal) }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">建玉評価損合計</td>
                  <td class=" _table__data __right">
                    {{ getChashText(formModel.todayBase.totalOpenInterestValuationLoss) }}
                  </td>
                  <td class=" _table__data __right">
                    {{ getChashText(formModel.nextBusinessDayBase.totalOpenInterestValuationLoss) }}
                  </td>
                  <td class=" _table__data __right">
                    {{ getChashText(formModel.markToBase.totalOpenInterestValuationLoss) }}
                  </td>
                </tr>
                <tr>
                  <td class="_table__data __left">決済損益合計</td>
                  <td :class="getFontColor(formModel.todayBase.totalSettlementProfitLoss, true) + ' _table__data __right'">
                    {{ getChashText(formModel.todayBase.totalSettlementProfitLoss, true) }}
                  </td>
                  <td :class="getFontColor(formModel.nextBusinessDayBase.totalSettlementProfitLoss, true) + ' _table__data __right'">
                    {{ getChashText(formModel.nextBusinessDayBase.totalSettlementProfitLoss, true) }}
                  </td>
                  <td :class="getFontColor(formModel.markToBase.totalSettlementProfitLoss, true) + ' _table__data __right'">
                    {{ getChashText(formModel.markToBase.totalSettlementProfitLoss, true) }}
                  </td>
                </tr>
                <tr>
                  <td class="_table__data __left">支払諸経費等合計</td>
                  <td :class="getFontColorZero(formModel.todayBase.totalExpensesPaid) + ' _table__data __right'">
                    {{ getChashText(formModel.todayBase.totalExpensesPaid) }}
                  </td>
                  <td :class="getFontColorZero(formModel.nextBusinessDayBase.totalExpensesPaid) + ' _table__data __right'">
                    {{ getChashText(formModel.nextBusinessDayBase.totalExpensesPaid) }}
                  </td>
                  <td :class="getFontColorZero(formModel.markToBase.totalExpensesPaid) + ' _table__data __right'">
                    {{ getChashText(formModel.markToBase.totalExpensesPaid) }}
                  </td>
                </tr>
                <tr>
                  <td class="_table__data __left">実質保証金(A)</td>
                  <td class="_table__data __right">{{ getChashText(formModel.todayBase.currencyActualDeposit) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.nextBusinessDayBase.currencyActualDeposit) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.markToBase.currencyActualDeposit) }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">建代金合計(B)</td>
                  <td class="_table__data __right">{{ getChashText(formModel.todayBase.constructionPriceTotal) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.nextBusinessDayBase.constructionPriceTotal) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.markToBase.constructionPriceTotal) }}</td>
                </tr>
                <!-- 当日基準.委託保証金率区分が"0"の場合表示（当日基準,翌営業日基準,値洗い基準の委託保証金率区分が異なることは業務上起こりえないため、当日基準を参照） -->
                <tr v-if="formModel.todayBase.entrustDepositRateType === '0'">
                  <td class="_table__data __left">スィープ専用銀行口座残高(C)</td>
                  <td class="_table__data __right">{{ getChashText(formModel.todayBase.hybridDepositBalance) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.nextBusinessDayBase.hybridDepositBalance) }}</td>
                  <td class="_table__data __right">{{ getChashText(formModel.markToBase.hybridDepositBalance) }}</td>
                </tr>
              </tbody>
            </table>
            <div>
              【ご注意事項】
              <ul class="notice-list">
                <li>本画面は時価を取得して、委託保証金率などをリアルタイムで計算いたしますが、値洗い時の状況につきましては、信用建余力画面などでご確認ください。</li>
                <li>「建玉評価損合計」「支払諸経費等合計」は、受渡未到来の現引、現渡にかかる評価損、諸経費等を含めて計算されます。</li>
                <li>本画面の詳細は
                  <ifa-link
                    ref="realEntrustDepositRate"
                    :url-id="45"
                    :disp-name="'リアル委託保証金率について'"
                    :link-icon-image="null"
                    manua-init
                  ></ifa-link>
                  をご参照ください。</li>
              </ul>
            </div>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { IfaRealEntrustDepositRateDomesticA002RequestModel } from './js/IfaRealEntrustDepositRateDomesticA002RequestModel.js'

export default {
  components: {
  },
  props: {
    isVisible: { type: Boolean, required: true },
    realEntrustDepositRateFormModel: { type: Object, required: true }
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
      formModel: this.realEntrustDepositRateFormModel
    }
  },
  computed: {
    IfaRealEntrustDepositRateDomesticA002RequestModel() {
      return new IfaRealEntrustDepositRateDomesticA002RequestModel(
        {
          butenCode: this.formModel.butenCode,
          accountNumber: this.formModel.accountNumber
        }
      )
    }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        if (this.$refs['realEntrustDepositRate']) { this.$refs['realEntrustDepositRate'].trigger() }
      })
    },
    // 更新ボタン
    responseHandlerA002(response) {
      this.formModel = Object.assign(this.formModel, response.dataList[0])
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    getDepositText(value) {
      if (value && value.replace(' ', '') !== '') {
        return ifaFormatUtils.withCommaZeroPadding((Number(value) / 100).toString(), 2) + '%'
      }
      return '--'
    },
    getDepositStyleBold(value) {
      if (value && value.replace(' ', '') !== '') {
        return 'bold-label'
      }
      return ''
    },
    getDepositStyleBgColor(value, kbn) {
      if (value && value.replace(' ', '') !== '' && kbn && kbn !== '0' && kbn !== '1') {
        if (kbn === '2') {
          return 'background-color-blue'
        } else if (kbn === '3') {
          return 'background-color-red'
        }
      }
      return ''
    },
    getChashText(value, isPlus) {
      if (value) {
        if (isPlus && Number(value) > 0) {
          return '+' + this.ifaFormatUtils.withCommaNoneZeroPadding(value) + '円'
        }
        return this.ifaFormatUtils.withCommaNoneZeroPadding(value) + '円'
      }
      return '-'
    },
    getFontColor(value) {
      if (value && Number(value) < 0) {
        return 'font-color__minus bold'
      } else if (value && Number(value) >= 0) {
        return 'font-color__plus bold'
      }
      return ''
    },
    getFontColorZero(value, isPlus) {
      if (value && Number(value) < 0) {
        return 'font-color__minus'
      } else if (value && Number(value) > 0 && isPlus) {
        return 'font-color__plus'
      }
      return ''
    }

  }
}
</script>

<style lang="scss" scoped>
._right {
  text-align: right;
}
ul.notice-list {
  margin-top: 0px;
  margin-left: -10px
}
ul.notice-list li {
    list-style-type: none; /* デフォルトのリストマーカーを無効にする */
    position: relative;   /* 擬似要素の位置を設定するために必要 */
}
ul.notice-list li::before {
    content: "※";          /* カスタムマーカーを設定 */
    position: absolute;     /* 擬似要素をリストアイテムの前に配置 */
    left: -1.5em;           /* マーカーをリストアイテムの左に配置 */
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: 0 0 0 -10px;
}
.bold-label {
  font-weight: bold;
}
.background-color-red {
  background-color: #FFCCCC;
}
.background-color-blue {
  background-color: #CCCCFF;
}
:deep(.external-link) {
  margin-right: -15px;
  color: blue;
  text-decoration: underline;
}
:deep(.el-table) .__center {
  text-align: center;
}
:deep(.el-table) .__left {
  text-align: left;
}
:deep(.el-table) .__right {
  text-align: right;
}
.box-card {
  margin: 10px 10px 15px 10px;
}
</style>
