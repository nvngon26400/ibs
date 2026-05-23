<template>
  <el-dialog
    title="コンプラ項目詳細"
    class="detail-modal"
    :close-on-click-modal="false"
    :show-close="false"
    width="960px"
    @open="onShow"
  >
    <el-row>
      <el-col style="text-align: right;">
        <ifa-button
          text="閉じる"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="closeDialog"
        ></ifa-button>
      </el-col>
    </el-row>
    <table>
      <tr
        v-for="item in tableData"
        :key="item.index"
      >
        <th class="__left"
            style="width: 50%;"
        ><span>{{ item.label }}</span></th>
        <th class="__left"
            style="width: 50%;"
        ><span>{{ item.value }}</span></th>
      </tr>
    </table>
  </el-dialog>
</template>

<script>
import { IfaDomesticMutualFundOrderOtherInfoFormModel } from './js/IfaDomesticMutualFundOrderOtherInfoFormModel.js'

export default {
  props: {
    orderOtherInfo: { type: Object, required: false, default: () => ({}) }
  },
  emits: ['close-dialog'],
  data() {
    return {
      tableData: [],
      form: new IfaDomesticMutualFundOrderOtherInfoFormModel()
    }
  },
  methods: {
    onShow() {
      this.getCodeList()
      this.setTableData()
    },
    // 区分値変換
    getCodeList() {
      this.form.norikaeYuguKbn = this.orderOtherInfo.norikaeYuguKbn ? this.$_getCodeValue('TRANSFERS_PREFERENTIAL_QUOTA_APPLICATION', 1, this.orderOtherInfo.norikaeYuguKbn) : '-' // 償還優遇
      this.form.checkCompWrnAlert = this.orderOtherInfo.checkCompWrnAlert ? this.$_getCodeValue('WARNING_APPLICATION_TRAD', 1, this.orderOtherInfo.checkCompWrnAlert) : '-' // ワーニング/例外的申請取引
      this.form.mokuromiKoufuKbn = this.orderOtherInfo.mokuromiKoufuKbn ? this.$_getCodeValue('PROSPECTUS_ISSUE_METHOD', 1, this.orderOtherInfo.mokuromiKoufuKbn) : '-' // 目論見書交付方法
      this.form.norikaeKanyuKbn = this.orderOtherInfo.norikaeKanyuKbn ? this.$_getCodeValue('SOLICITING_TRANSFERS', 1, this.orderOtherInfo.norikaeKanyuKbn) : '-' // 乗換え勧誘
      this.form.conflictOfInterestExplain = this.orderOtherInfo.conflictOfInterestExplain ? this.$_getCodeValue('CONFLICT_OF_INTEREST_EXPLAIN', 3, this.orderOtherInfo.conflictOfInterestExplain) : '-' // 利益相反可能性の説明
      this.form.checkHiyou = this.orderOtherInfo.checkHiyou ? this.$_getCodeValue('COST_EXPLAINED', 3, this.orderOtherInfo.checkHiyou) : '-' // 販売手数料の利率等の説明
      this.form.checkFukusu = this.orderOtherInfo.checkFukusu ? this.$_getCodeValue('MULTIPLE_TRADE_CLEARLY_COMM_STATED', 3, this.orderOtherInfo.checkFukusu) : '-' // 複数取引業者での手数料等明示
      this.form.douitsuTukaKuniKbn = this.orderOtherInfo.douitsuTukaKuniKbn ? this.$_getCodeValue('SAME_CURRENCY_SAME_COUNTRY_TRANSFERS', 1, this.orderOtherInfo.douitsuTukaKuniKbn) : '-' // 同一通貨/同一国の乗換
      this.form.tashaNorikaeKbn = this.orderOtherInfo.tashaNorikaeKbn ? this.$_getCodeValue('INTERCOMPANY_MUTUAL_FUND_TRANSFER_SOLICITATION', 1, this.orderOtherInfo.tashaNorikaeKbn) : '-' // 他社間投信乗換え勧誘
      this.form.tankiSellKbn = this.orderOtherInfo.tankiSellKbn ? this.$_getCodeValue('SHORT_TERM_SALE_CONFIRM', 1, this.orderOtherInfo.tankiSellKbn) : '-' // 短期売却確認
      this.form.shokanMaeKbn = this.orderOtherInfo.shokanMaeKbn ? this.$_getCodeValue('PRE_REDEMPTION_SELL_CONFIRM', 1, this.orderOtherInfo.shokanMaeKbn) : '-' // 償還前売却確認
    },
    // テーブル表示用のデータ作成
    setTableData() {
      if (this.orderOtherInfo.tradeKbn === 'K') { // 売買区分'買'の場合
        this.tableData = [
          { label: this.orderOtherInfo.butenCode + '-' + this.$_zeroPadding(this.orderOtherInfo.accountNumber, 7), value: '' },
          { label: this.orderOtherInfo.customerNameKanji + '（' + this.orderOtherInfo.customerNameKana + '）', value: '' },
          { label: '償還優遇', value: this.form.norikaeYuguKbn },
          { label: 'ワーニング/例外的申請取引', value: this.form.checkCompWrnAlert },
          { label: '目論見書の交付方法', value: this.form.mokuromiKoufuKbn },
          { label: '乗換え勧誘', value: this.form.norikaeKanyuKbn },
          { label: '利益相反可能性の説明', value: this.form.conflictOfInterestExplain },
          { label: '販売手数料の利率（金額）等の説明', value: this.form.checkHiyou },
          { label: '複数取引業者での手数料等の明示', value: this.form.checkFukusu },
          { label: '同一通貨/同一国の乗換', value: this.form.douitsuTukaKuniKbn },
          { label: '他社間投信乗換え勧誘', value: this.form.tashaNorikaeKbn },
          { label: '発注者', value: (this.orderOtherInfo.userName || '-') + (this.orderOtherInfo.userId ? '（' + this.orderOtherInfo.userId + '）' : '') }
        ]
      } else {
        this.tableData = [
          { label: this.orderOtherInfo.butenCode + '-' + this.$_zeroPadding(this.orderOtherInfo.accountNumber, 7), value: '' },
          { label: this.orderOtherInfo.customerNameKanji + '（' + this.orderOtherInfo.customerNameKana + '）', value: '' },
          { label: '同一通貨/同一国の乗換', value: this.form.douitsuTukaKuniKbn },
          { label: '他社間投信乗換え勧誘', value: this.form.tashaNorikaeKbn },
          { label: '短期売却確認', value: this.form.tankiSellKbn },
          { label: '償還前売却確認', value: this.form.shokanMaeKbn },
          { label: '発注者', value: (this.orderOtherInfo.userName || '-') + (this.orderOtherInfo.userId ? '（' + this.orderOtherInfo.userId + '）' : '') }
        ]
      }
    },
    closeDialog() {
      this.$emit('close-dialog')
    }
  }
}
</script>
