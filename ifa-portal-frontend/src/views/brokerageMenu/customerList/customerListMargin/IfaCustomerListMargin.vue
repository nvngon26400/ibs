<template>
  <!-- 画面名：顧客一覧信用 -->
  <div style="overflow-x: scroll;">
    <screen-title :text="form.title.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
    >
      <el-card
        class="content-card"
        shadow="always"
      >
        <div class="filter-container">
          <el-row>
            <el-col
              :span="23"
            >
              <ifa-common-search
                ref="commonSearchItem"
                display-pattern="pt1"
                list-pattern="pt2"
                :form="form"
                original-screen-id="SUB0201_02-01"
                @mediate-user-privacy="getAutoDisplay"
              ></ifa-common-search>
            </el-col>
          </el-row>
        </div>
        <!-- Collapseタグ -->
        <el-collapse v-model="activeNames">
          <el-collapse-item>
            <template #title>
              <el-icon
                v-if="activeNames == 1"
                style="font-size:20px;"
              ><CirclePlus></CirclePlus></el-icon>
              <el-icon
                v-else
                style="font-size:20px;"
              ><Remove></Remove></el-icon>
              <span style="padding-left:10px;">詳細検索条件</span>
            </template>
            <div
              class="filter-container"
              style="margin-left: 18px;"
            >
              <el-row class="form_area">
                <el-col
                  :span="24"
                  class="adjust_form_margin price_input"
                  style="display: flex; align-items: start;"
                >
                  <label
                    class="left-label"
                    style=" margin-top: 9px;"
                  >前日評価損益</label>
                  <ifa-input-price
                    v-model="form.beforeProfitAndLossFrom"
                    :domain="PLUSMINUS_SIGNED_YEN_19_0"
                    size="small"
                    style="margin-right: 0;"
                    :min="-999999999999999999"
                    :max="9999999999999999999"
                    :digit="0"
                    :step="1"
                    placeholder=""
                    prop="beforeProfitAndLossFromValidator"
                  ></ifa-input-price>
                  <span class="custom-label">～</span>
                  <ifa-input-price
                    v-model="form.beforeProfitAndLossTo"
                    :domain="PLUSMINUS_SIGNED_YEN_19_0"
                    size="small"
                    style="margin-right: 0;"
                    :min="-999999999999999999"
                    :max="9999999999999999999"
                    :digit="0"
                    :step="1"
                    placeholder=""
                    prop="beforeProfitAndLossToValidator"
                  ></ifa-input-price>
                </el-col>
              </el-row>
              <el-row class="form_area">
                <!-- 前日保証金残高 -->
                <el-col
                  :span="24"
                  class="adjust_form_margin price_input"
                  style="display: flex; align-items: start;"
                >
                  <label
                    class="left-label"
                    style="margin-top: 9px;"
                  >前日保証金残高</label>
                  <ifa-input-price
                    v-model="form.beforeDepositBalanceFrom"
                    :domain="PLUSMINUS_SIGNED_FOREIGN_17_0"
                    class="price_input"
                    size="small"
                    style="margin-right: 0;"
                    :min="-9999999999999999"
                    :max="99999999999999999"
                    :digit="0"
                    :step="1"
                    placeholder=""
                    prop="beforeDepositBalanceFromValidator"
                  ></ifa-input-price>
                  <span class="custom-label">～</span>
                  <ifa-input-price
                    v-model="form.beforeDepositBalanceTo"
                    :domain="PLUSMINUS_SIGNED_FOREIGN_17_0"
                    class="price_input"
                    size="small"
                    style="margin-right: 0;"
                    :min="-9999999999999999"
                    :max="99999999999999999"
                    :digit="0"
                    :step="1"
                    placeholder=""
                    prop="beforeDepositBalanceToValidator"
                  ></ifa-input-price>
                </el-col>
              </el-row>

              <!-- 前日委託保証金率 -->
              <el-row class="form_area">
                <el-col
                  :span="24"
                  class="adjust_form_margin price_input"
                  style="display: flex; align-items: start;"
                >
                  <label
                    class="left-label"
                    style="margin-top: 9px;"
                  >前日委託保証金率</label>
                  <ifa-input-price
                    v-model="form.beforeEntrustDepositRateFrom"
                    :domain="RATE8_2"
                    class="price_input"
                    size="small"
                    unit="%"
                    style="margin-right: 0;"
                    :min="-999999.99"
                    :max="999999.99"
                    :digit="0"
                    :step="1"
                    placeholder=""
                    prop="beforeEntrustDepositRateFromValidator"
                  ></ifa-input-price>
                  <span class="custom-label">～</span>
                  <ifa-input-price
                    v-model="form.beforeEntrustDepositRateTo"
                    :domain="RATE8_2"
                    class="price_input"
                    size="small"
                    unit="%"
                    style="margin-right: 0;"
                    :min="-999999.99"
                    :max="999999.99"
                    :digit="0"
                    :step="1"
                    placeholder=""
                    prop="beforeEntrustDepositRateToValidator"
                  ></ifa-input-price>
                </el-col>
              </el-row>
              <!-- /前日委託保証金率 -->

              <!-- 追証 -->
              <el-row class="form_area">
                <el-col
                  :span="10"
                  style="padding-left: 141px;"
                >
                  <el-checkbox
                    v-model="form.marginCallCheck"
                  >追証</el-checkbox>
                </el-col>
              </el-row>
              <!-- /追証 -->

              <!-- 引出金不足 -->
              <el-row class="form_area">
                <el-col
                  :span="10"
                  style="padding-left: 141px;"
                >
                  <el-checkbox
                    v-model="form.withdrawalDeficientCheck"
                  >引出金不足</el-checkbox>
                </el-col>
              </el-row>
              <!-- /引出金不足 -->
            </div>
          </el-collapse-item>
        </el-collapse>
        <el-row style="padding: 1rem 56px 0;">
          <ifa-button
            id="btnDisplay"
            name="btnDisplay"
            text="表示"
            :form="formRef"
            width="90"
            search
            small
            action-id="SUB0201_02-01#A002"
            :request-model="IfaCustomerListMarginA002RequestModel"
            :set-param-func="paramFunctionA002"
            action-type="requestAction"
            :pre-request-handler="preSearch"
            @response-handler="responseHandlerA002($event)"
            @response-error-handler="responseErrorHandlerA002($event)"
          ></ifa-button>
          <ifa-button
            id="btnTopInputClear"
            name="btnTopInputClear"
            text="クリア"
            width="90"
            color="white"
            small
            action-type="originalAction"
            @app-action-handler="handleClear"
          ></ifa-button>
        </el-row>
      </el-card>
      <el-row>
        <el-card class="content-card">
          <el-row>
            <div class="gridButtonArea">
              <ifa-button
                id="btmCustomerPortal"
                name="btmCustomerPortal"
                :disabled="activeBtn"
                text="顧客別メニュー"
                color="primary"
                small
                action-type="originalAction"
                @app-action-handler="handleMoveCustomerPortalClick"
              ></ifa-button>
              <ifa-button id="btnCsvDownload"
                          name="btnCsvDownload"
                          :disabled="csvbtn"
                          text="CSV出力"
                          width="90"
                          color="primary"
                          :form="formRef"
                          small=""
                          :request-model="IfaCustomerListMarginA005RequestModel"
                          action-id="SUB0201_02-01#A005"
                          :csv-file-name="form.title.name"
                          action-type="outputCsvAction"
                          :is-check-csv-download-allowed="true"
                          :is-check-csv-download-privacy-confirmation="true"
                          @response-handler="responseHandlerCsvOutputA005($event)"
                          @response-error-handler="responseErrorHandlerCsvOutputA005($event)"
              ></ifa-button>
            </div>
          </el-row>
          <grid-table
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>
    <!-- IfaLink (非表示) -->
    <ifa-link
      ref="ifaLink"
      :visible="false"
      :url-id="urlId"
      param-url="https://site1.sbisec.co.jp/ETGate"
      :pattern-id="1"
      http-method="POST"
      disp-name="委託保証金率"
      :param-object="paramObject"
    ></ifa-link>

    <!--リアル委託保証金率（国内）-->
    <ifa-real-entrust-deposit-rate-domestic
      :is-visible="realMarginDepositRateDomesticisVisible"
      :real-entrust-deposit-rate-form-model="realEntrustDepositRateDomesticFormModel"
      @close-modal="handleCloseModalrealMarginDepositRateDomestic"
    >
    </ifa-real-entrust-deposit-rate-domestic>

    <ifa-requester
      id="ifaCustomerListMarginRealEntrustDepositRateDomesticA006"
      action-id="SUB0202_010302-03#X001"
      action-type="requestAction"
      :request-model="IfaRealEntrustDepositRateDomesticX001RequestModel"
      @response-handler="responseHandlerA006"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaCustomerListMarginFormModel } from './js/IfaCustomerListMarginFormModel'
import { IfaCustomerListMarginA002RequestModel } from './js/IfaCustomerListMarginA002RequestModel'
import { IfaCustomerListMarginA005RequestModel } from './js/IfaCustomerListMarginA005RequestModel'
import PLUSMINUS_SIGNED_YEN_19_0 from '@/resource/domain/IfaPlusminusSignedYen190DomainModel.json'
import PLUSMINUS_SIGNED_FOREIGN_17_0 from '@/resource/domain/IfaPlusminusSignedForeign170DomainModel.json'
import RATE8_2 from '@/resource/domain/IfaRate82DomainModel.json'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { notifyWrapper, getMessage, notifyMessage } from '@/utils/errorHandler'
import IfaRealEntrustDepositRateDomestic from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/IfaRealEntrustDepositRateDomestic.vue'
import { IfaRealEntrustDepositRateDomesticFormModel } from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/js/IfaRealEntrustDepositRateDomesticFormModel.js'
import { IfaRealEntrustDepositRateDomesticX001RequestModel } from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/js/IfaRealEntrustDepositRateDomesticX001RequestModel.js'
import { isAccessible } from '@/utils/controlAuth'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle,
    IfaRealEntrustDepositRateDomestic
  },
  data() {
    return {
      form: new IfaCustomerListMarginFormModel(),
      pqGridOption: getConvertedOption(obj),
      pqGridSelectedInfo: {},
      activeNames: 1,
      csvbtn: true,
      activeBtn: true,
      processing: false,
      urlId: 0,
      ccsOpId: '',
      buten: '',
      kouzaNo: '',
      tengunId: '',
      PLUSMINUS_SIGNED_YEN_19_0: PLUSMINUS_SIGNED_YEN_19_0,
      PLUSMINUS_SIGNED_FOREIGN_17_0: PLUSMINUS_SIGNED_FOREIGN_17_0,
      RATE8_2: RATE8_2,
      formRef: {},
      realMarginDepositRateDomesticisVisible: false,
      realEntrustDepositRateDomesticFormModel: new IfaRealEntrustDepositRateDomesticFormModel()
      // rules: {
      //   beforeProfitAndLossFromValidator: { required: true, trigger: 'change', validator: this.beforeProfitAndLossFromCheck },
      //   beforeProfitAndLossToValidator: { required: true, trigger: 'change', validator: this.beforeProfitAndLossToCheck },
      //   beforeDepositBalanceFromValidator: { required: true, trigger: 'change', validator: this.beforeDepositBalanceFromCheck },
      //   beforeDepositBalanceToValidator: { required: true, trigger: 'change', validator: this.beforeDepositBalanceToCheck },
      //   beforeEntrustDepositRateFromValidator: { required: true, trigger: 'change', validator: this.beforeEntrustDepositRateFromCheck },
      //   beforeEntrustDepositRateToValidator: { required: true, trigger: 'change', validator: this.beforeEntrustDepositRateToCheck }
      // }
    }
  },
  computed: {
    IfaCustomerListMarginA002RequestModel() {
      return new IfaCustomerListMarginA002RequestModel(this.form)
    },
    IfaCustomerListMarginA005RequestModel() {
      return new IfaCustomerListMarginA005RequestModel(this.form)
    },
    IfaRealEntrustDepositRateDomesticX001RequestModel() {
      return new IfaRealEntrustDepositRateDomesticX001RequestModel(this.form)
    },
    paramObject() {
      return {
        '_ControlID': 'WPLETadR001Control',
        '_DataStoreID': 'DSWPLETadR001Control',
        '_PageID': 'WPLETadR001Rlgn20',
        '_ActionID': 'RealCCSDaikoLogin',
        'ccs_op_id': this.ccsOpId,
        'buten': this.buten,
        'kouza_no': this.kouzaNo,
        'user_id': 'csadm01',
        'expansion': 'q9wFQrm5ZYfySMuqqyLrSpn1c4bb4amw',
        '_TENGUN_ID': this.tengunId,
        'getFlg': 'on'
      }
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.form.marginCallCheck = false
      this.form.withdrawalDeficientCheck = false
      this.activeBtn = true
      this.csvbtn = true
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    getAutoDisplay({ empCodeAutoDispFlag }) {
      this.form.pattern = empCodeAutoDispFlag
      obj.colModel.forEach((item, index) => {
        if (empCodeAutoDispFlag !== '0') {
          // 仲介業者名
          if (item.dataIndx === 'brokerName') {
            item.hidden = true
          }
          // 営業員コード
          if (item.dataIndx === 'brokerChargeCode') {
            item.hidden = true
          }
          // 営業員名
          if (item.dataIndx === 'chargeName') {
            item.hidden = true
          }
          // 仲介業者コード
          if (item.dataIndx === 'brokerCode') {
            item.hidden = true
          }
          // 支店コード
          if (item.dataIndx === 'branchCode') {
            item.hidden = true
          }
          // 支店名
          if (item.dataIndx === 'branchName') {
            item.hidden = true
          }
        } else {
          // 仲介業者名
          if (item.dataIndx === 'brokerName') {
            item.hidden = false
          }
          // 営業員コード
          if (item.dataIndx === 'brokerChargeCode') {
            item.hidden = false
          }
          // 営業員名
          if (item.dataIndx === 'chargeName') {
            item.hidden = false
          }
          // 仲介業者コード
          if (item.dataIndx === 'brokerCode') {
            item.hidden = false
          }
          // 支店コード
          if (item.dataIndx === 'branchCode') {
            item.hidden = false
          }
          // 支店名
          if (item.dataIndx === 'branchName') {
            item.hidden = false
          }
        }
      })
    },
    paramFunctionA002(data) {
      if (data) {
        return JSON.parse(JSON.stringify(new IfaCustomerListMarginA002RequestModel(this.form)))
      } else {
        return null
      }
    },
    responseHandlerA002(data) {
      this.$_logDebug('responseHandlerA002-OK')
      this.activeBtn = true
      this.csvbtn = true
      if (data.dataList.length > 0 && data.dataList[0].customerListList.length !== 0) {
        this.csvbtn = false // CSV出力ボタンの活性化
        this.pqGridOption.dataModel.data = data.dataList[0].customerListList
        this.$refs['gridTable'].refreshView(true)
      } else {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView(true)
      }
    },
    responseErrorHandlerA002(error) {
      this.$_logDebug('responseErrorHandlerA002', error)
    },
    responseHandlerCsvOutputA005(data) {
      this.$_logDebug('responseHandlerA005-OK')
    },
    responseErrorHandlerCsvOutputA005(error) {
      this.$_logDebug('responseErrorHandlerA005-Error', error)
      notifyWrapper({
        title: this.form.titleModel.name,
        message: error.message,
        type: 'error'
      })
    },
    responseHandlerA006(response) {
      this.realEntrustDepositRateDomesticFormModel = Object.assign(this.realEntrustDepositRateDomesticFormModel, response.dataList[0])
      // リアル委託保証金率（国内）画面を開く
      this.realMarginDepositRateDomesticisVisible = true
    },
    handleClear() {
      this.$refs.commonSearchItem.formClear()
      this.$refs.form.clearValidate()
      this.form.beforeProfitAndLossFrom = '' // 前日評価損益（From）
      this.form.beforeProfitAndLossTo = '' // 前日評価損益（To）
      this.form.beforeDepositBalanceFrom = '' // 前日保証金残高（From）
      this.form.beforeDepositBalanceTo = '' // 前日保証金残高（To）
      this.form.beforeEntrustDepositRateFrom = '' // 前日委託保証金率（From）
      this.form.beforeEntrustDepositRateTo = '' // 前日委託保証金率（To）
      this.form.marginCallCheck = false // 追証（チェック） 【初期値】OFF
      this.form.withdrawalDeficientCheck = false // 引出金不足（チェック） 【初期値】OFF
    },
    preSearch() {
      obj.colModel.forEach((item, index) => {
        // 追証（チェック）"on"の場合、明細に「信用追証」を表示する。
        if (item.dataIndx === 'marginStatus') {
          item.hidden = !this.form.marginCallCheck
        }
        // 引出金不足（チェック）"on"の場合、明細に「引出金不足」を表示する。
        if (item.dataIndx === 'chargeFlag') {
          item.hidden = !this.form.withdrawalDeficientCheck
        }
      })
    },
    // 一覧選択
    handleClick({ rowData: { butenCode, accountNumber, tengun }, dataIndx }) {
      this.form.butenCode = butenCode
      this.form.accountNumber = accountNumber
      const params = {
        butenCode, // 部店コード
        accountNumber// 口座番号
      }
      // 検索結果の顧客明細選択時に活性化
      this.activeBtn = false
      if (dataIndx === 'maintenanceRate') {
        // 仲介業メニュー＞顧客別メニュー＞口座管理-余力情報＞信用余力（国内）
        this.$_startShowMenu('SUB0202_010302', params)
      } else if (dataIndx === 'marginStatus') {
        // 仲介業メニュー＞顧客別メニュー＞口座管理-余力情報＞信用余力（国内）
        this.$_startShowMenu('SUB0202_010302', params)
      } else if (dataIndx === 'chargeFlag') {
        // 仲介業メニュー＞顧客別メニュー＞口座管理-余力情報＞信用余力（国内）
        this.$_startShowMenu('SUB0202_010302', params)
      } else if (dataIndx === 'marginDepositRate') {
        this.$nextTick(() => {
          document.getElementById('ifaCustomerListMarginRealEntrustDepositRateDomesticA006').click()
        })
      }
    },
    handleMoveCustomerPortalClick() {
      const butenCode = this.form.butenCode
      const accountNumber = this.form.accountNumber
      const params = {
        butenCode,
        accountNumber
      }

      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        this.$_startShowMenu(this.$customerMenuInitialScreenId, params)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          this.form.title.name
        )
      }
    },
    handleCloseModalrealMarginDepositRateDomestic() {
      // リアル委託保証金率（国内）画面を閉じる
      this.realMarginDepositRateDomesticisVisible = false
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '顧客一覧・信用',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}

obj.colModel = [
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    minWidth: 350,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'chargeName',
    minWidth: 90,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '部店',
    dataIndx: 'butenCode',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    minWidth: 90,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttributeName',
    minWidth: 125,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '顧客名(漢字)',
    dataIndx: 'customerNameKanji',
    minWidth: 350,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    minWidth: 350,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: 'Cランク',
    dataIndx: 'tcCompRank',
    minWidth: 90,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '前日評価損益',
    dataIndx: 'customerListProfitAndLossOfMarginPosition',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const grid = this
      const data = ui.rowData.customerListProfitAndLossOfMarginPosition
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data)
      } else {
        return '-'
      }
    }
  },
  {
    title: '前日保証金残高',
    dataIndx: 'acceptanceDeposit',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.acceptanceDeposit)
    }
  },
  {
    title: 'リアルタイム委託保証金率',
    dataIndx: 'marginDepositRate',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return '<span style=\'color: #0000FF; border-bottom:solid 1px; cursor: pointer\'>委託保証金率</span>'
    }
  },
  {
    title: '前日委託保証金率',
    dataIndx: 'maintenanceRate',
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return '<span style=\'color: #0000FF; border-bottom:solid 1px; cursor: pointer\'>' + ifaFormatUtils.noneWithCommaZeroPadding(ui.rowData.maintenanceRate, 2) + '%</span>'
    }
  },
  {
    title: '信用追証',
    dataIndx: 'marginStatus',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const marginStatus = ui.rowData.marginStatus
      if (marginStatus !== '0') {
        return '<span style=\'color: #0000FF; border-bottom:solid 1px; cursor: pointer\'>○</span>'
      } else {
        return ''
      }
    }
  },
  {
    title: '引出金不足',
    dataIndx: 'chargeFlag',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const chargeFlag = ui.rowData.chargeFlag
      if (chargeFlag === '1') {
        return '<span style=\'color: #0000FF; border-bottom:solid 1px; cursor: pointer\'>○</span>'
      } else {
        return ''
      }
    }
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店コード',
    dataIndx: 'branchCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'branchName',
    minWidth: 350,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '店群',
    dataIndx: 'tengun',
    minWidth: 0,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    hidden: true
  }
]
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}

</script>
<style scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
  margin-left: 0.5rem;
}
.content-card {
    margin: 0.5rem 1rem;
  }

.dropdown {
  width: 115px;
}

.checkmark {
  margin-right: 5px;
}
 :deep(.checkmarkClass) {
  text-align : right;
  padding-right: 10px;
  font-weight: bold;
}

 :deep(.el-checkbox__label) {
   font-weight: bold;
 }

.middle_input {
  width: 120px;
}
.short_input {
  width: 60px;
  text-align : right;
}
.num_input {
  width: 215px;
}

.date_input {
  width: 120px;
}

:deep(.checkmarkClass) .el-col-2  {
  text-align : right;
}
:deep(.num_input) .el-input__inner  {
  text-align : right;
}

:deep(.checkmark) .el-switch__label * {
  font-weight:bold;
  text-align : right;
}

:deep(.el-collapse-item__content) {
  padding-bottom: 0%;
}

.gridButtonArea {
  margin-bottom: 10px;
}

:deep(.form_upper_label) .el-form-item__label {
  width: 135px;
}

.form_lower_label {
  width: 133px;
  text-align : right;
}

:deep(.el-collapse-item__header){
  padding-left: 20px;
  background-color: transparent;
}
:deep(.el-collapse-item__header:hover){
  color: #409eff;
  background-color: #f9fafc;
}
:deep(.el-collapse) {
  border-bottom: none;
}

:deep(.el-icon-arrow-right) {
  content: none;
}

:deep(.customer-name-label) .el-form-item__label {
  margin-right: 0px;
  padding-right: 0px;
}
:deep(.form_lower_label) .el-form-item {
  margin-bottom: 2rem;
}

:deep(.el-form-item) {
  margin-bottom: 1.2rem;
  margin-right: 0;
}
:deep(.el-form-item__label) {
  font-weight: bold;
}

.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  white-space: nowrap;
  right: 30px;
  /* top: 10px; */
}

.form_area {
  margin-bottom:20px;
}

.price_input :deep(.el-input__wrapper) {
  width: 220px;
}

.m-left-15 {
  margin-left: 15px;
}

:deep(.ifa-input_base__controll_wrapper.without-unit[data-v-96c11ca8]) {
  margin-left: 0px
}

:deep(.ifa-input_base__controll_wrapper.small[data-v-96c11ca8]) {
  width: 65px;
}

.m-right-10 {
  margin-right: 10px;
}
.custom-label {
  padding: 7px;
}
.adjust_form_margin :deep(.el-form-item) {
  margin-bottom: 0;
}
 .left-label {
  display: inline-block;
  width: 130px;
  margin-right: 11px;
  text-align: end;
  padding-right: 12px;
  line-height: 1;
  font-weight: bold;
  font-size: 14px;
}
:deep(.ifa-input_base__wrapper) {
  min-width: 0;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
