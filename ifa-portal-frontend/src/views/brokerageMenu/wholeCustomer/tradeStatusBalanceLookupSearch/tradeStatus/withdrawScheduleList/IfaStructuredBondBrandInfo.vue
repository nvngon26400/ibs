<template>
  <div>
    <el-dialog
      :model-value="isVisible"
      :title="form.title.name"
      :close-on-click-modal="false"
      :show-close="false"
      :center="true"
      :before-close="handleDialogClose"
    >
      <el-row>
        <el-col class="__right">
          <!-- Ä002: 戻る -->
          <ifa-button
            color="secondary"
            text="戻る"
            action-type="originalAction"
            @app-action-handler="handleDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>

      <el-row>
        <el-col>
          <div class="subject">銘柄基本情報</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">債券コード</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.bondCode) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">ISIN</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.isinCode) }}</div></td>
            </tr>
          </table>
          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">銘柄名</div></th>
              <td class="table-span-20 border"><div class="table-content">{{ $_out(form.bondName) }}</div></td>
            </tr>
          </table>
          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">発行日</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getFormattedDateValue(form.issueDate)) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">満期償還日</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getFormattedDateValue(form.maturityRedemptionDate)) }}</div></td>
            </tr>
          </table>
        </el-col>
      </el-row>

      <el-row style="margin-top: 1rem;">
        <el-col>
          <div class="subject">仕組債条件情報</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">公募/私募</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getCodeValue('PUBLIC_PRIVATE_OFFER_TYPE', 1, form.publicPrivateOfferingName)) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">ノックイン有無</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getCodeValue('KNOCK_IN_EVENT_TYPE', 1, form.knockInEventType)) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">早期償還有無</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getCodeValue('EARLY_REDEMPTION_EVENT_TYPE', 1, form.earlyRedemptionEventType)) }}</div></td>
            </tr>
          </table>

          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">参照営業日カレンダー</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.businessDayCalendar) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">参照取引所カレンダー</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.exchangeCalendar) }}</div></td>
            </tr>
          </table>

          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">判定日数</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getCodeValue('TRADE_FLOOR_JUDGMEN_DAYS', 1, form.exchangeJudgmentDays)) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">ノンコール期間</div></th>
              <td class="table-span-1 border"><div class="table-content">{{ $_out($_withCommaInteger(form.nonCallPeriod)) }}</div></td>
              <td class="table-span-2"><div class="table-content bottom">(Month)</div></td>
            </tr>
          </table>

          <table>
            <tr>
              <th
                class="table-span-3 border"
                rowspan="7"
              ><div class="table-content">利率(%)</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ addUnit(form.appInterest) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">クーポン種類</div></th>
              <td
                class="table-span-3 border"
                colspan="2"
              ><div class="table-content">{{ $_out($_getCodeValue('COUPON_KIND', 1, form.couponKind)) }}</div></td>
              <td></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th class="table-span-3 border"><div class="table-content">固定クーポン</div></th>
              <td class="table-spacer"></td>

              <td class="table-span-3 border">
                <div
                  class="table-content"
                  :class="{
                    'pale-color': isCouponKind('1')
                  }"
                >{{ addUnit(form.coupon) }}</div>
              </td>
              <td class="table-span-1 border">
                <div
                  class="table-content"
                  :class="{
                    'pale-color': isCouponKind('1')
                  }"
                >{{ $_out($_withCommaInteger(form.couponCount)) }}
                </div>
              </td>
              <td class="table-span-2"><div class="table-content bottom">回目まで適用</div></td>
              <td></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th
                class="table-span-3 border"
                rowspan="3"
              ><div class="table-content">デジタルクーポン</div></th>
              <td
                class="table-spacer"
                rowspan="3"
              ></td>

              <td class="table-span-3"><div class="table-content no-wrap">利用判定水準　当初価格　×</div></td>
              <td
                class="table-span-8"
                colspan="3"
              >
                <table class="no-margin">
                  <tr>
                    <td class="table-span-1_5 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ addUnit(form.highJudgmentLevel) }}</div>
                    </td>
                    <td class="table-span-3 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ $_out(form.highCouponFractionCalculation) }}</div>
                    </td>
                    <td class="table-span-1 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ $_out(form.highCouponRounding) }}</div>
                    </td>
                    <td class="table-span-1_5 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ addUnit(form.highCoupon) }}</div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>

            <tr>
              <td class="table-span-3"><div class="table-content no-wrap">利用判定水準　当初価格　×</div></td>
              <td
                class="table-span-8"
                colspan="3"
              >
                <table class="no-margin">
                  <tr>
                    <td class="table-span-1_5 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ addUnit(form.midJudgmentLevel) }}</div>
                    </td>
                    <td class="table-span-3 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ $_out(form.midCouponFractionCalculation) }}</div>
                    </td>
                    <td class="table-span-1 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ $_out(form.midCouponRounding) }}</div>
                    </td>
                    <td class="table-span-1_5 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ addUnit(form.midCoupon) }}</div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>

            <tr>
              <td class="table-span-3"><div class="table-content no-wrap">利用判定水準　当初価格　×</div></td>
              <td
                class="table-span-8"
                colspan="3"
              >
                <table class="no-margin">
                  <tr>
                    <td class="table-span-1_5 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ addUnit(form.lowJudgmentLevel) }}</div>
                    </td>
                    <td class="table-span-3 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ $_out(form.lowCouponFractionCalculation) }}</div>
                    </td>
                    <td class="table-span-1 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ $_out(form.lowCouponRounding) }}</div>
                    </td>
                    <td class="table-span-1_5 border">
                      <div
                        class="table-content"
                        :class="{
                          'pale-color': isCouponKind('2')
                        }"
                      >{{ addUnit(form.lowCoupon) }}</div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>

          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content font-size-small">早期償還判定日／利率判定日</div></th>
              <td class="table-span-3plus border"><div class="table-content">
                <grid-table
                  class="hide-footer"
                  :options="options1"
                ></grid-table>
              </div></td>
            </tr>
          </table>

          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">満期償還判定日</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getFormattedDateValue(form.maturityRedemptionJudgmentDate)) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">当初価格決定日</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getFormattedDateValue(form.initiallyPriceSettingJudgmentDate)) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">観察期間</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getFormattedDateValue(form.observationPeriodFrom)) }}</div></td>
              <td class="table-span-0_5"><div class="table-content">～</div></td>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getFormattedDateValue(form.observationPeriodTo)) }}</div></td>
            </tr>
          </table>

          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">参照値</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getCodeValue('REFERENCE_VALUE', 1, form.referenceValue)) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">通貨ペア</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.currencyPair) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">使用レート</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getCodeValue('USE_RATE', 1, form.useRate)) }}</div></td>
              <td class="table-spacer"></td>
              <th class="table-span-3 border"><div class="table-content">レバレッジ</div></th>
              <td class="table-span-1 border"><div class="table-content">{{ $_out($_withCommaInteger(form.leverage)) }}</div></td>
            </tr>
          </table>

          <table>
            <tr>
              <th
                class="table-span-3 border"
                rowspan="17"
              ><div class="table-content">価格：判定水準</div></th>
              <th class="table-span-3 border"><div class="table-content">ノックイン判定水準</div></th>
              <td class="table-spacer"></td>
              <td class="table-span-1_5"><div class="table-content no-wrap">当初価格　×</div></td>
              <td class="table-span-1_5 border"><div class="table-content">{{ addUnit(form.knockInJudgmentLevel) }}</div></td>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.knockInJudgmentFractionCalculation) }}</div></td>
              <td class="table-span-1 border"><div class="table-content">{{ $_out(form.knockInJudgmentRounding) }}</div></td>
              <td></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th class="table-span-3 border"><div class="table-content">早期償還判定水準</div></th>
              <td class="table-spacer"></td>
              <td class="table-span-1_5"><div class="table-content no-wrap">当初価格　×</div></td>
              <td class="table-span-1_5 border"><div class="table-content">{{ addUnit(form.earlyRedemptionJudgmentLevel) }}</div></td>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.earlyRedemptionJudgmentFractionCalculation) }}</div></td>
              <td class="table-span-1 border"><div class="table-content">{{ $_out(form.earlyRedemptionJudgmentRounding) }}</div></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th
                class="table-span-3 border"
                rowspan="3"
              ><div class="table-content font-size-small">早期償還判定水準ステップダウン</div></th>
              <td
                class="table-spacer"
                rowspan="3"
              ></td>
              <td
                class="table-span-3 border"
                colspan="2"
              ><div class="table-content">{{ $_out($_getCodeValue('EARLY_REDEMPTION_JUDGMENT_STEP_DOWN', 1, form.earlyRedemptionJudgmentStepDown)) }}</div></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <td class="table-span-1_5"><div class="table-content no-wrap">早期償還判定</div></td>
              <td class="table-span-1_5 border"><div class="table-content">{{ $_out($_withCommaInteger(form.stepDownStartCount)) }}</div></td>
              <td colspan="3">
                <table class="no-margin">
                  <tr>
                    <td class="table-span-2"><div class="table-content no-wrap bottom">回目の約定から</div></td>
                    <td class="table-span-1_5 border"><div class="table-content">{{ addUnit(form.earlyRedemptionJudgmentStepDownSettingValue) }}</div></td>
                    <td class="table-span-2"><div class="table-content no-wrap bottom">ステップダウン</div></td>
                  </tr>
                </table>
              </td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th class="table-span-3 border"><div class="table-content font-size-small">早期償還判定水準ステップダウン下限</div></th>
              <td class="table-spacer"></td>
              <td class="table-span-1_5"><div class="table-content no-wrap">当初価格　×</div></td>
              <td class="table-span-1_5 border"><div class="table-content">{{ addUnit(form.earlyRedemptionJudgmentStepDownLowerLimit) }}</div></td>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.earlyRedemptionJudgmentStepDownLowerLimitFractionCalculation) }}</div></td>
              <td class="table-span-1 border"><div class="table-content">{{ $_out(form.earlyRedemptionJudgmentStepDownLowerLimitRounding) }}</div></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th class="table-span-3 border"><div class="table-content">早期償還価格</div></th>
              <td class="table-spacer"></td>
              <td class="table-span-1_5"><div class="table-content no-wrap">額面金額　×</div></td>
              <td class="table-span-1_5 border"><div class="table-content">{{ addUnit(form.earlyRedemptionPriceRatio) }}</div></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th class="table-span-3 border"><div class="table-content">行使価格</div></th>
              <td class="table-spacer"></td>
              <td class="table-span-1_5"><div class="table-content no-wrap">当初価格　×</div></td>
              <td class="table-span-1_5 border"><div class="table-content">{{ addUnit(form.usePriceRatio) }}</div></td>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.usePriceFractionCalculation) }}</div></td>
              <td class="table-span-1 border"><div class="table-content">{{ $_out(form.usePriceRounding) }}</div></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th class="table-span-3 border"><div class="table-content">満期償還判定水準</div></th>
              <td class="table-spacer"></td>
              <td class="table-span-1_5"><div class="table-content no-wrap">当初価格　×</div></td>
              <td class="table-span-1_5 border"><div class="table-content">{{ addUnit(form.maturityRedemptionJudgmentLevel) }}</div></td>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.maturityRedemptionFractionCalculation) }}</div></td>
              <td class="table-span-1 border"><div class="table-content">{{ $_out(form.maturityRedemptionRounding) }}</div></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th class="table-span-3 border"><div class="table-content">満期償還価格</div></th>
              <td class="table-spacer"></td>
              <td class="table-span-1_5"><div class="table-content no-wrap">額面金額　×</div></td>
              <td class="table-span-1_5 border"><div class="table-content">{{ addUnit(form.maturityRedemptionPriceRatio) }}</div></td>
            </tr>
          </table>

          <table>
            <tr>
              <th
                class="table-span-3 border"
                rowspan="3"
              ><div class="table-content">ノックイン免除</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getCodeValue('KNOCK_IN_EXEMPT', 1, form.knockInExemptExistence)) }}</div></td>
              <td class="table-spacer"></td>
              <th
                class="table-span-3 border"
                colspan="2"
              ><div class="table-content">観察期間</div></th>
              <td
                class="table-span-4 border"
                colspan="2"
              ><div class="table-content">{{ $_out($_getFormattedDateValue(form.knockInExemptobservationPeriodFrom)) }}</div></td>
              <td class="table-span-0_5"><div class="table-content">～</div></td>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getFormattedDateValue(form.knockInExemptobservationPeriodTo)) }}</div></td>
            </tr>

            <tr><td class="table-vspacer"></td></tr>

            <tr>
              <th class="table-span-3 border"><div class="table-content">ノックイン免除判定水準</div></th>
              <td class="table-spacer"></td>
              <td class="table-span-1_5"><div class="table-content no-wrap">当初価格　×</div></td>
              <td class="table-span-1_5 border"><div class="table-content">{{ addUnit(form.knockInExemptJudgmentLevel) }}</div></td>
              <td class="table-span-3 border"><div class="table-content">{{ $_out(form.knockInExemptFractionCalculation) }}</div></td>
              <td class="table-span-1 border"><div class="table-content">{{ $_out(form.knockInExemptRounding) }}</div></td>
            </tr>
          </table>

          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">早期償還メモリ</div></th>
              <td class="table-span-3 border"><div class="table-content">{{ $_out($_getCodeValue('EARLY_REDEMPTION_MEMORY', 1, form.earlyRedemptionMemoryExistence)) }}</div></td>
            </tr>
          </table>

          <table>
            <tr>
              <th class="table-span-3 border"><div class="table-content">参照銘柄</div></th>
              <td class="table-span-20 border"><div class="table-content">
                <grid-table :options="options2"></grid-table>
              </div></td>
            </tr>
          </table>

        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption } from '@/utils/pqgridHelper'
import { IfaStructuredBondBrandInfoFormModel } from './js/IfaStructuredBondBrandInfoFormModel'
export default {
  components: {
    GridTable
  },
  props: {
    isVisible: { type: Boolean, required: true }
  },
  emits: ['close-modal'],
  data() {
    return {
      form: new IfaStructuredBondBrandInfoFormModel(),
      options1: null,
      options2: null
    }
  },
  computed: {
    isCouponKind() {
      return (kind) => {
        return this.form.couponKind === kind
      }
    }
  },
  mounted() {
    // ｢早期償還判定日／利率判定日｣に表示する表の初期化
    const options1 = getDefaultOption(colModel1)
    options1.maxHeight = 130 // 表の高さ固定
    options1.pageModel.rPP = 100 // 最大表示件数100
    options1.strNoRows = '判定日はありません。' // 早期償還判定日／利率判定日が一件もない場合に表示する
    options1.scrollModel = { autoFit: true } // 横スクロールバー非表示
    this.options1 = options1
    this.options1.wrap = true

    // ｢参照銘柄｣に表示する表の初期化
    const options2 = getDefaultOption(colModel2)
    options2.maxHeight = 170 // 表の高さ固定
    options2.strNoRows = '参照銘柄はありません。' // 参照銘柄が一件もない場合
    options2.scrollModel = { autoFit: true } // 横スクロールバー非表示
    this.options2 = options2
    this.options2.wrap = true
  },
  methods: {
    // A001: 初期化
    onShow(dispInfo) {
      Object.assign(this.form, dispInfo)
      // ｢早期償還判定日／利率判定日｣を表示する｡(最大表示件数100)
      this.options1.dataModel.data = this.form.earlyStageRedemptionJudgmentDateRateJudgmentDateList.slice(0, 100)

      // ｢参照銘柄｣を表示する｡
      this.options2.dataModel.data = this.form.referenceBrandList
    },
    // A002: 戻る
    handleDialogClose() {
      this.$emit('close-modal')
    },
    // 入力値(value) が有効な場合､入力値をフォーマットして単位(unit､デフォルトは'%')を追加して返す｡
    // 入力値が無効な場合は､ハイフン('-')を返す｡
    addUnit(value, unit = '%') {
      const out = this.$_out(this.$_withCommaNoneZeroPadding(value))
      if (out && out !== '-') {
        return `${out}${unit}`
      }
      return out
    }
  }
}

// フォーマット共通関数
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction'

const colModel1 = [
  { title: '番号', dataIndx: 'number', width: 45, halign: 'center', align: 'left' },
  { title: '判定日', dataIndx: 'judgmentDate', width: 75, halign: 'center', align: 'left',
    render: function(ui) {
      // yyyy/MM/dd (フォーマット済み日付文字列)
      return getFormattedDateValue(ui.rowData.judgmentDate) || '-'
    }
  }
]
const colModel2 = [
  { title: 'No.', dataIndx: 'disporder', width: 45, halign: 'center', align: 'center',
    render: function(ui) {
      // #,##0 (カンマ区切り整数)
      return ifaFormatUtils.withCommaInteger(ui.rowData.disporder) || '-'
    }
  },
  {
    title: '銘柄種別', dataIndx: 'judgmentBrandClass', width: 100, halign: 'center', align: 'center',
    codeValue: { codeListId: 'BRAND_CLASS', dispPattern: 1, selectPattern: 1 }
  },
  { title: '判定市場', dataIndx: 'judgmentMarketCode', width: 80, halign: 'center', align: 'center' },
  { title: '判定銘柄', dataIndx: 'judgmentBrandCode', width: 60, halign: 'center', align: 'center' },
  { title: '銘柄名', dataIndx: 'judgmentBrandName', width: 175, halign: 'center', align: 'center' },
  { title: '単位', dataIndx: 'unit', width: 45, halign: 'center', align: 'center' },
  { title: '当初価格', dataIndx: 'initiallyPrice', width: 60, halign: 'center', align: 'center',
    render: function(ui) {
      // #,##0.# (カンマ区切り0埋めなし､少数桁数2桁)
      return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.initiallyPrice, 2) || '-'
    }
  },
  { title: 'ノックイン水準価格', dataIndx: 'knockInLevelPrice', width: 110, halign: 'center', align: 'center',
    render: function(ui) {
      // #,##0.# (カンマ区切り0埋めなし､少数桁数2桁)
      return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.knockInLevelPrice, 2) || '-'
    }
  },
  { title: '早期償還水準価格', dataIndx: 'earlyRedemptionLevelPrice', width: 110, halign: 'center', align: 'center',
    render: function(ui) {
      // #,##0.# (カンマ区切り0埋めなし､少数桁数2桁)
      return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.earlyRedemptionLevelPrice, 2) || '-'
    }
  },
  { title: 'ノックイン発生日', dataIndx: 'knockInAccuralDate', width: 105, halign: 'center', align: 'center',
    render: function(ui) {
      // yyyy/MM/dd (フォーマット済み日付文字列)
      return getFormattedDateValue(ui.rowData.knockInAccuralDate) || '-'
    }
  },
  { title: '終値', dataIndx: 'knockInAccuralTimePrice', width: 45, halign: 'center', align: 'center',
    render: function(ui) {
      // #,##0.# (カンマ区切り0埋めなし､少数桁数2桁)
      return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.knockInAccuralTimePrice, 2) || '-'
    }
  }
]
</script>

<style lang="scss" scoped>
:deep(.el-dialog) {
  margin-top: 4vh;
  width: 1200px;
  max-height: 90%;
  padding: 30px 0;
}
:deep(.el-dialog__header) {
  padding: 0px;
}
:deep(.el-dialog__title) {
  font-weight: bold;
}
:deep(.el-dialog--center .el-dialog__body) {
  padding: 0 30px;
}

table {
  margin: 0 0 0.5em 1em;
  border-collapse: collapse;
  --table-span-unit-size: calc(1150px / 24);
  &.no-margin {
    margin: 0;
  }
}

th {
  background-color: rgb(230, 233, 240);
}

th, td {
  box-sizing: border-box;

  &.border {
    border: 1px solid rgb(150, 150, 150);
  }

  &.table-span {
    @for $i from 1 through 24 {
      &-#{$i} {
        width: calc(var(--table-span-unit-size) * #{$i});
        min-width: calc(var(--table-span-unit-size) * #{$i});
        max-width: calc(var(--table-span-unit-size) * #{$i});
      }
    }
    &-1_5 {
      width: calc(var(--table-span-unit-size) * 1.5);
      min-width: calc(var(--table-span-unit-size) * 1.5);
      max-width: calc(var(--table-span-unit-size) * 1.5);
    }
    &-0_5 {
      width: calc(var(--table-span-unit-size) * 0.5);
      min-width: calc(var(--table-span-unit-size) * 0.5);
      max-width: calc(var(--table-span-unit-size) * 0.5);
    }
    &-0_25 {
      width: calc(var(--table-span-unit-size) * 0.25);
      min-width: calc(var(--table-span-unit-size) * 0.25);
      max-width: calc(var(--table-span-unit-size) * 0.25);
    }
    &-3plus {
      width: calc(var(--table-span-unit-size) * 3 + 20px);
      min-width: calc(var(--table-span-unit-size) * 3 + 20px);
      max-width: calc(var(--table-span-unit-size) * 3 + 20px);
    }
  }

  .table-content {
    display: flex;
    min-height: 3em;
    padding: 0.3em 0.5em;
    align-items: center;
    text-align: left;
    font-size: 10.5px;
    color: #18181A;
    font-weight: bold;
    white-space: wrap;
    word-break: break-all;
    overflow-wrap: break-word;

    &.no-wrap {
      white-space: nowrap;
      word-break: keep-all;
      overflow-wrap: normal;
    }

    &.bottom {
      align-items: end;
      padding-bottom: 0.5em;
    }

    &.font-size-small {
      font-size: 9px;
    }

    &.pale-color {
      opacity: 50%;
    }
  }
}

.table-spacer {
  width: 1em;
  min-width: 1em;
  max-width: 1em;
  border: none;
}

.table-vspacer {
  height: 0.5em;
  border: none;
}
.subject {
  font-size: 14px;
  font-weight: 700;
  color: black;
  margin-bottom: 0.5em;
}
.hide-footer {
  :deep(.pq-grid-footer) {
    display: none;
  }
}
</style>

