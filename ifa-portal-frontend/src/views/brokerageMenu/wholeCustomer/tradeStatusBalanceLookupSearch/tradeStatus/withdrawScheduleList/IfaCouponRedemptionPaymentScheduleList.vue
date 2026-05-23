<template>
  <!-- 画面名：利金・償還金支払予定一覧 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      label-position="left"
    >
      <el-card
        class="content-card"
        shadow="always"
      >
        <div class="filter-container">
          <el-row>
            <!-- 共通検索項目(リストパターン: pt1, 表示パターン: pt1) -->
            <el-col :span="23">
              <ifa-common-search
                ref="commonSearch"
                display-pattern="pt1"
                list-pattern="pt2"
                :form="form"
                course-validate="false"
                original-screen-id="SUB020302_0104-01"
              ></ifa-common-search>
            </el-col>
            <!-- インフォメーションアイコン（青） -->
            <!-- 利金・償還金支払予定一覧コメントに値がある場合のみ表示 -->
            <el-col
              v-if="form.oneMonth10PercentDeclineBrandListComment?.trim()"
              :span="1"
            >
              <ifa-balloon-icon icon-type="info">
                <div v-html="form.oneMonth10PercentDeclineBrandListComment"></div>
              </ifa-balloon-icon>
            </el-col>
          </el-row>
          <el-row>
            <!-- 期間指定 -->
            <el-col :span="10">
              <div style="display: grid;">
                <ifa-date-range-picker
                  v-model="form.periodDate"
                  label="期間指定"
                  size="small"
                  :picker-options="pickerOptions"
                  class="form_label"
                  prop="periodDate"
                  required
                ></ifa-date-range-picker>
                <span style="margin: 4px 0 16px 155px;">※期間は当日～来月末までを指定して下さい。</span>
              </div>
            </el-col>

            <!-- 証券種別(リストパターン: pt8) -->
            <div style="margin-left: 14px;">
              <product-code-select
                ref="securityClassList"
                label="証券種別"
                class="form_label"
                list-kind="pt8"
                style="width: 180px;"
                id-string="securityClassList"
                @change-selected="handleChangeSecurityClassList"
              ></product-code-select>
            </div>

            <!-- 銘柄コード -->
            <div style="margin-left: 49px;">
              <ifa-input-text
                id="brandCode"
                v-model="form.brandCode"
                label="銘柄コード"
                class="form_label search-form__item middle_input"
                size="small"
                style="margin-left: -1.9px"
                maxlength="12"
                prop="brandCode"
                :domain="IfaBrandCode12PeriodDomainModel"
              ></ifa-input-text>
            </div>
          </el-row>
        </div>
        <div
          id="indicator-display"
          class="form_button"
        >
          <!-- A002: 表示リクエスト -->
          <ifa-button
            id="btnDisplay"
            text="表示"
            :form="formRef"
            width="90"
            color="primary"
            search
            small
            action-type="requestAction"
            action-id="SUB020302_0104-01#A002"
            :request-model="requestModelA002"
            @response-handler="responseHandlerA002"
            @response-error-handler="responseErrorHandlerA002"
          ></ifa-button>
          <!-- A003: クリア -->
          <ifa-button
            text="クリア"
            width="90"
            color="secondary"
            small
            action-type="originalAction"
            @app-action-handler="handleClearA003"
          ></ifa-button>
        </div>
      </el-card>
      <el-row>
        <el-card class="content-card">
          <el-row v-if="isCsvButtonVisible">
            <div class="grid-button-area">
              <!-- A004: CSV出力リクエスト -->
              <ifa-button
                id="btnCsvDownload"
                :disabled="isCsvButtonDisable"
                text="CSV出力"
                width="90"
                color="primary"
                small
                action-type="outputCsvAction"
                action-id="SUB020302_0104-01#A004"
                :request-model="requestModelA004"
                csv-file-name="利金償還金支払予定一覧"
                @response-handler="responseHandlerA004"
                @response-error-handler="responseErrorHandlerA004"
              ></ifa-button>
            </div>
          </el-row>
          <!-- A005: 仕組債銘柄情報 -->
          <grid-table
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="rowSelectedA005"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>

    <!-- 仕組債銘柄情報 -->
    <ifa-structured-bond-brand-info
      :key="ifaStructuredBondBrandInfoKey"
      ref="ifaStructuredBondBrandInfo"
      :is-visible="isVisible"
      @close-modal="handleCloseModal"
    ></ifa-structured-bond-brand-info>

    <!-- A001: 初期表示リクエスト -->
    <ifa-requester
      id="ifaCouponRedemptionPaymentScheduleListA001"
      action-type="requestAction"
      action-id="SUB020302_0104-01#A001"
      @response-handler="responseHandlerA001"
      @response-error-handler="responseErrorHandlerA001"
    ></ifa-requester>
    <!-- A002: 表示リクエスト ※ 総合ポータルからの遷移用 -->
    <ifa-requester
      id="ifaCouponRedemptionPaymentScheduleListA002"
      action-type="requestAction"
      action-id="SUB020302_0104-01#A002"
      :request-model="requestModelA002IfaWholePortalHome"
      @response-handler="responseHandlerA002"
      @response-error-handler="responseErrorHandlerA002"
    ></ifa-requester>
    <!-- A005: 仕組債銘柄情報リクエスト (A001: 仕組債銘柄情報初期化) -->
    <ifa-requester
      id="ifaStructuredBondBrandInfoA001"
      action-type="requestAction"
      action-id="SUB020302_0104-02#A001"
      :request-model="requestModelA005"
      @response-handler="responseHandlerA005"
      @response-error-handler="responseErrorHandlerA005"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption } from '@/utils/pqgridHelper'
import ProductCodeSelect from '@/components/MultiSelect/ProductCodeSelect'
import IfaStructuredBondBrandInfo from './IfaStructuredBondBrandInfo.vue'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrandCode12PeriodDomainModel from '@/resource/domain/IfaBrandCode12PeriodDomainModel.json'
import { IfaCouponRedemptionPaymentScheduleListFormModel } from './js/IfaCouponRedemptionPaymentScheduleListFormModel'
import { IfaCouponRedemptionPaymentScheduleListA002RequestModel } from './js/IfaCouponRedemptionPaymentScheduleListA002RequestModel'
import { IfaCouponRedemptionPaymentScheduleListA004RequestModel } from './js/IfaCouponRedemptionPaymentScheduleListA004RequestModel'
import { IfaStructuredBondBrandInfoA001RequestModel } from './js/IfaStructuredBondBrandInfoA001RequestModel'
import store from '@/store'

export default {
  components: {
    GridTable,
    ProductCodeSelect,
    IfaStructuredBondBrandInfo,
    IfaCommonSearch,
    screenTitle
  },
  data() {
    return {
      IfaBrandCode12PeriodDomainModel: IfaBrandCode12PeriodDomainModel,
      form: new IfaCouponRedemptionPaymentScheduleListFormModel(),
      pqGridOption: getConvertedOption(obj),
      isVisible: false,
      formRef: undefined,
      rowDataA005: {},
      ifaStructuredBondBrandInfoKey: 1,
      pickerOptions: {
        disabledDate(date) {
          // 本日～来月末が選択可能
          const now = new Date(store.getters.requestedTime)
          const startDate = new Date(now.getFullYear(), now.getMonth(), now.getDate())
          const endDate = new Date(startDate.getFullYear(), startDate.getMonth() + 2, 0)
          return date < startDate || date > endDate
        },
        shortcuts: [{
          text: '今月末',
          value: () => {
            // 本日を取得
            const startDate = new Date(store.getters.requestedTime)
            // 今月末を取得
            const endDate = new Date(startDate.getFullYear(), startDate.getMonth() + 1, 0)
            return [startDate, endDate]
          }
        }, {
          text: '来月末',
          value: () => {
            // 本日を取得
            const startDate = new Date(store.getters.requestedTime)
            // 来月末を取得
            const endDate = new Date(startDate.getFullYear(), startDate.getMonth() + 2, 0)
            return [startDate, endDate]
          }
        }]
      },
      serchFormIfaWholePortalHome: {
        brokerCode: '',
        chkBrokerCodeExclude: false,
        branchCode: '',
        empCode: '',
        butenCode: '',
        accountNumber: '',
        customerNameKanjiKana: '',
        customerNameKanjiKanaTerms: '',
        courseSelected: [],
        periodDate: [],
        securityClassList: [],
        brandCode: ''
      }
    }
  },
  computed: {
    // A002: 表示リクエストモデル
    requestModelA002() {
      return new IfaCouponRedemptionPaymentScheduleListA002RequestModel(this.form)
    },
    // A002: 表示リクエストモデル ※ 総合ポータルホームからの遷移用
    requestModelA002IfaWholePortalHome() {
      return new IfaCouponRedemptionPaymentScheduleListA002RequestModel(this.serchFormIfaWholePortalHome)
    },
    // A004: CSV出力リクエストモデル
    requestModelA004() {
      return new IfaCouponRedemptionPaymentScheduleListA004RequestModel(this.form)
    },
    // Å005: 仕組債銘柄情報リクエストモデル
    requestModelA005() {
      return new IfaStructuredBondBrandInfoA001RequestModel(this.rowDataA005)
    },
    // CSVボタン表示･非表示: 権限コードが1,2,3の場合に表示、それ以外は非表示
    isCsvButtonVisible() {
      if (this.$store.getters.userAccount.medUsers) {
        const privId = this.$store.getters.userAccount.medUsers.privId
        return ['1', '2', '3'].includes(privId)
      }
      return false
    },
    // CSVボタン活性:非活性: 検索結果が0件の場合、非活性
    isCsvButtonDisable() {
      return this.form.couponRedemptionDetailList.length === 0
    }
  },
  methods: {
    // A001: 初期表示
    onShow(resume, isRouting) {
      this.handleClearA003()
      this.$nextTick(() => {
        document.getElementById('ifaCouponRedemptionPaymentScheduleListA001').click()
      })
      if (isRouting) {
        const periodDate = this.$store.getters.pageInfo.params
        this.serchFormIfaWholePortalHome.periodDate = periodDate
        const updatedCourseSelected = this.form.courseSelected.map(course => ({
          ...course,
          isSelected: true
        }))
        this.serchFormIfaWholePortalHome.courseSelected = updatedCourseSelected
        const updatedSecurityClassList = this.form.securityClassList.map(securityClass => ({
          ...securityClass,
          isSelected: true
        }))
        this.serchFormIfaWholePortalHome.securityClassList = updatedSecurityClassList
        this.$nextTick(() => {
          document.getElementById('ifaCouponRedemptionPaymentScheduleListA002').click()
        })
      }
    },
    // A003: クリア
    handleClearA003() {
      // バリデーションエラーをクリア
      this.$refs['form'].clearValidate()

      // 共通検索をリセット
      this.$refs['commonSearch'].formClear()
      this.form.customerNameKanjiKanaTerms = '3'

      // 期間指定 (初期値: 今月末)
      const startDate = new Date(store.getters.requestedTime)
      const endDate = new Date(startDate.getFullYear(), startDate.getMonth() + 1, 0)
      endDate.setDate(endDate.getDate())
      this.form.periodDate = [this.formatDate(startDate), this.formatDate(endDate)]

      // 証券種別 (初期値: 全選択)
      this.$refs['securityClassList'].handleCheckAll(true)

      // 銘柄コード (初期値: '')
      this.form.brandCode = ''
    },
    // A005: 仕組債銘柄情報
    rowSelectedA005(param) {
      if (param.dataIndx === 'couponDeterminationDate' && param.rowData.couponDeterminationDate) {
        this.rowDataA005 = param.rowData
        this.$nextTick(() => {
          document.getElementById('ifaStructuredBondBrandInfoA001').click()
        })
      }
    },
    // 仕組債銘柄情報ダイアログがクローズされた
    handleCloseModal() {
      // ダイアログ閉じる
      this.isVisible = false
      // 仕組債銘柄情報を再マウントする
      // ※再マウントしないと2回目以降のグリッドテーブルがデータなしになってしまう
      this.ifaStructuredBondBrandInfoKey++
    },
    // 証券種別の選択状態が変更された
    handleChangeSecurityClassList(newValue) {
      this.form.securityClassList = [...newValue]
    },
    // A001: 正気表示レスポンス
    responseHandlerA001(response) {
      Object.assign(this.form, response.dataList[0])

      // 期間指定 (初期値: 今月末)
      const startDate = new Date(store.getters.requestedTime)
      const endDate = new Date(startDate.getFullYear(), startDate.getMonth() + 1, 0)
      endDate.setDate(endDate.getDate())
      this.form.periodDate = [this.formatDate(startDate), this.formatDate(endDate)]

      // form へのリファレンスを保存する
      this.formRef = this.$refs['form']
    },
    // A001: 正気表示エラーレスポンス
    responseErrorHandlerA001(response) {
      this.$_logDebug(response)
    },
    // A002: 表示レスポンス
    responseHandlerA002(response) {
      if (response.dataList && Array.isArray(response.dataList) && response.dataList.length > 0) {
        this.form.couponRedemptionDetailList = response.dataList
        this.updateGridTable()
      } else {
        this.form.couponRedemptionDetailList = []
        this.updateGridTable()
      }
    },
    // A002: 表示エラーレスポンス
    responseErrorHandlerA002(response) {
      this.form.couponRedemptionDetailList = []
      this.updateGridTable()
    },
    // A004: CSV出力レスポンス
    responseHandlerA004(response) {
      this.$_logDebug(response)
    },
    // A004: CSV出力エラーレスポンス
    responseErrorHandlerA004(response) {
      this.$_logDebug(response)
    },
    // A005: 仕組債銘柄情報レスポンス
    responseHandlerA005(response) {
      // レスポンスデータを仕組債銘柄情報ダイアログに渡す
      this.$refs['ifaStructuredBondBrandInfo'].onShow(response.dataList[0])

      // 仕組債銘柄情報ダイアログを開く
      this.isVisible = true
    },
    // A005: 仕組債銘柄情報エラーレスポンス
    responseErrorHandlerA005(response) {
      this.$_logDebug(response)
    },
    updateGridTable() {
      this.pqGridOption.dataModel.data = this.form.couponRedemptionDetailList
      this.$refs['gridTable'].refreshView()
    },
    // 期間指定のショートカット用｡Date オブジェクトを文字列('YYYY/MM/DD')に変換する
    formatDate(date) {
      if (date && typeof date === 'object' && 'toLocaleDateString' in date) {
        return date.toLocaleDateString('ja-JP', { year: 'numeric', month: '2-digit', day: '2-digit' })
      } else {
        return date
      }
    }
  }
}

// フォーマット共通関数
import formatUtils from '@/utils/ifaFormatUtils'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction'

// 利金・償還金支払予定一覧(GridTable)の構成
const obj = {
  width: 0,
  height: 0,
  title: '利金・償還金支払予定一覧',
  flexHeight: false,
  collapsible: false,
  showTitle: true
}
obj.colModel = [
  { title: '仲介業者コード', dataIndx: 'brokerCode', width: 140, halign: 'center', align: 'center' },
  { title: '仲介業者名', dataIndx: 'brokerName', width: 250, halign: 'center', align: 'left' },
  { title: '営業員コード', dataIndx: 'empCode', width: 125, halign: 'center', align: 'center' },
  { title: '営業員名', dataIndx: 'brokerChargeName', width: 150, halign: 'center', align: 'left' },
  { title: '部店', dataIndx: 'buten', width: 75, halign: 'center', align: 'center' },
  { title: '口座番号', dataIndx: 'accountNumber', width: 100, dataType: 'integer', halign: 'center', align: 'left' },
  { title: '扱者コード', dataIndx: 'dealerNumber', width: 100, halign: 'center', align: 'center' },
  { title: '取引コース', dataIndx: 'courceName', width: 250, halign: 'center', align: 'left' },
  { title: '顧客名(漢字)', dataIndx: 'customerNameKanji', width: 150, halign: 'center', align: 'left' },
  { title: '顧客名(カナ)', dataIndx: 'customerNameKana', width: 150, halign: 'center', align: 'left' },
  { title: '証券種別名', dataIndx: 'securiytClassName', width: 200, halign: 'center', align: 'left' },
  { title: '銘柄コード', dataIndx: 'brandCode', width: 150, halign: 'center', align: 'left' },
  { title: '銘柄名', dataIndx: 'brandName', width: 250, halign: 'center', align: 'left' },
  {
    title: '約定基準残高', dataIndx: 'contractStandardDeposit', width: 150, halign: 'center', align: 'right',
    render: function(ui) {
      // #,##0 (カンマ区切り整数)
      return formatUtils.withCommaInteger(ui.rowData.contractStandardDeposit)
    }
  },
  { title: '通貨', dataIndx: 'currency', width: 100, halign: 'center', align: 'center' },
  { title: '元利払種別', dataIndx: 'paymentClass', width: 100, halign: 'center', align: 'left' },
  {
    title: '予定日', dataIndx: 'scheduleDate', width: 100, halign: 'center', align: 'center',
    render: function(ui) {
      // yyyy/MM/dd (フォーマット済み日付文字列)
      return getFormattedDateValue(ui.rowData.scheduleDate)
    }
  },
  {
    title: '予定利率', dataIndx: 'nextInterest', width: 150, halign: 'center', align: 'right',
    render: function(ui) {
      // #,##0.#### (カンマ区切り0埋め､少数桁数4桁)
      return formatUtils.withCommaZeroPadding(ui.rowData.nextInterest, 4)
    }
  },
  {
    title: '予定概算金額', dataIndx: 'schedulePrice', width: 150, halign: 'center', align: 'right',
    render: function(ui) {
      // #,##0.#### (カンマ区切り0埋め､少数桁数4桁)
      return formatUtils.withCommaZeroPadding(ui.rowData.schedulePrice, 4)
    }
  },
  { title: 'クーポン判定日', dataIndx: 'couponDeterminationDate', width: 150, halign: 'center', align: 'center',
    render: function(ui) {
      // yyyy/MM/dd (フォーマット済み日付文字列､リンク)
      return ui.rowData.couponDeterminationDate
        ? {
          cls: ui.rowData.couponDeterminationDate ? 'pq-grid-link' : '',
          text: getFormattedDateValue(ui.rowData.couponDeterminationDate)
        }
        : '-'
    }
  },
  { title: 'KI有無', dataIndx: 'kiCount', width: 100, halign: 'center', align: 'center',
    render: function(ui) {
      return ui.rowData.kiCount > 0 ? '●' : '-'
    }
  },
  { title: '支店コード', dataIndx: 'branchCode', width: 150, halign: 'center', align: 'left' },
  { title: '支店名', dataIndx: 'branchName', width: 250, halign: 'center', align: 'left' }
]
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>

<style lang="scss" scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}
.filter-container {
  margin: 1rem 0;
}
.content-card {
  margin: 0.5rem 1rem;
}
:deep(.el-checkbox__label) {
  font-weight: bold;
}
.middle_input {
  width: 120px;
}
.form_button {
  margin: 0 2rem 0.8rem 2.5rem;
  padding: 0;
}
.grid-button-area {
  padding: 0.5rem 0.1rem;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
  line-height: 2;
  justify-content: flex-end;
}
:deep(.el-input__inner) {
  font-size: 14px;
}
:deep(.pq-grid-link) {
  color:#409EFF;
  text-decoration: underline;
  text-underline-offset:0.2em;
  cursor: pointer;
}
.el-select, .el-select .el-input, .el-select .el-input__inner {
  height: 32px;
}

:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
