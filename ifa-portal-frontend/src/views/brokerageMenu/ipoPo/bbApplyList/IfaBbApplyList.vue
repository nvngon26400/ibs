<template>
  <!-- 画面名：BB申込一覧 -->
  <div style="overflow-x: scroll;">
    <screen-title text="BB申込一覧"></screen-title>
    <div
      class="ifa-search-view__main-default"
      style="width: 1660px;"
    >
      <div id="contentAreaInput">
        <el-card
          class="content-card"
          shadow="always"
        >
          <el-form
            ref="form"
            :model="form"
            :inline="true"
            label-position="right"
          >
            <div class="filter-container">
              <ifa-common-search
                ref="commonSearch"
                :form="form"
                display-pattern="pt1"
                list-pattern="pt2"
                :broker-code-validate="false"
                :emp-code-validate="false"
                :course-validate="false"
                :add-internet-to-courses="'on'"
                original-screen-id="SUB0204_02-01"
                @mediate-user-privacy="getAutoDisplay"
              ></ifa-common-search>
              <el-row>
                <ifa-input-text
                  id="brandCode"
                  v-model="form.brandCode"
                  size="small"
                  :domain="IfaBrandCode10DomainModel"
                  prop="brandCode"
                  label="銘柄コード"
                  style="width: 180px;"
                ></ifa-input-text>
                <ifa-input-select
                  id="lotteryResult"
                  v-model="form.lotteryResult"
                  select-class="search-form__item"
                  label="抽選結果"
                  style="width: 180px;"
                  code-list-id="SUBSCRIPT_BB_DRAWING_RESULT"
                  :disp-pattern="1"
                  :select-pattern="1"
                  :clearable="false"
                >
                </ifa-input-select>
                <ifa-input-select
                  id="faceToFaceSubscriptOrderStatusList"
                  v-model="form.orderStatus"
                  select-class="search-form__item"
                  code-list-id="FACE_TO_FACE_SUBSCRIPT_ORDER_STATUS"
                  :disp-pattern="1"
                  :select-pattern="2"
                  label="注文状況"
                  style="width: 180px;"
                  :clearable="false"
                >
                </ifa-input-select>
                <ifa-input-check
                  id="historyInclude"
                  v-model="form.historyInclude"
                  code-list-id="original"
                  :original-list="historyIncludeList"
                  :is-button="false"
                >
                </ifa-input-check>
              </el-row>
              <el-row style="padding-left: 46px; margin-top:20px;">
                <el-col>
                  <!-- @app-action-handler="searchHandler" -->
                  <ifa-button
                    id="btnDisplay"
                    name="btnDisplay"
                    text="表示"
                    action-type="requestAction"
                    action-id="SUB0204_02-01#X002"
                    width="90"
                    search
                    small
                    :form="$refs.form"
                    :request-model="ifaBbApplyListX002RequestModel"
                    :pre-request-handler="searchHandler"
                    @response-handler="x002ResponseHandler($event)"
                    @response-error-handler="responseErrorHandlerx002($event)"
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
                </el-col>
              </el-row>
            </div>
          </el-form>
        </el-card>
      </div>
      <el-row id="paneBottom">
        <el-card class="content-card">
          <div class="gridButtonArea">
            <ifa-button
              id="btmCustomerPortal"
              name="btmCustomerPortal"
              :disabled="disabledBtn"
              text="顧客別メニュー"
              small
              action-type="originalAction"
              @app-action-handler="customerMenuA008"
            ></ifa-button>
            <ifa-button
              v-if="!csvClickable"
              id="btnCsvDownload"
              name="btnCsvDownload"
              text="CSV出力"
              :disabled="disabledCsv"
              small
              width="90"
              action-type="outputCsvAction"
              csv-file-name="BB申込"
              action-id="SUB0204_02-01#A004"
              :request-model="ifaBbApplyListA004RequestModel"
              :is-check-csv-download-allowed="true"
              :is-check-csv-download-privacy-confirmation="true"
              :pre-request-handler="preCsvDownload"
              @response-handler="responseHandlerA004($event)"
              @response-error-handler="responseErrorHandlerA004($event)"
            ></ifa-button>
            <ifa-button
              id="btnApplyRevise"
              name="btnApplyRevise"
              :disabled="disabledBtn"
              text="BB訂正・取消"
              small
              width="122"
              :request-model="ifaBbApplyListA005RequestModel"
              action-id="SUB0204_02-01#A005"
              action-type="requestAction"
              @response-handler="responseHandlerA005($event)"
              @response-error-handler="responseErrorHandlerA005($event)"
            ></ifa-button>
            <ifa-button
              id="btnFundInsert"
              name="btnFundInsert"
              :disabled="disabledBtn"
              text="募集入力"
              small
              width="90"
              :request-model="ifaBbApplyListA006RequestModel"
              action-id="SUB0204_02-01#A006"
              action-type="requestAction"
              @response-handler="responseHandlerA006($event)"
              @response-error-handler="responseErrorHandlerA006($event)"
            ></ifa-button>
            <ifa-button
              v-if="startUploadShow"
              id="btnStarUploadCSVDownload"
              name="btnStarUploadCSVDownload"
              text="STARアップロードファイル出力"
              small
              action-type="originalAction"
              @app-action-handler="exportStarUploadCSV"
            ></ifa-button>
          </div>
          <grid-table
            id="outputTable"
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
      <!-- 募集入力 -->
      <ifa-subscript-input
        ref="IfaSubscriptInput"
        :is-visible="dialogSubscriptInputVisible"
        @close-modal="subscriptComplete"
        @open-modal="dialogSubscriptInputVisible = true"
      ></ifa-subscript-input>
      <!-- BB訂正・取消 -->
      <ifa-bb-apply-correct-cancel-input
        ref="ifaBbApplyCorrectCancelInput"
        :is-visible="dialogBbApplyCorrectionVisible"
        @close-modal="dialogBbApplyCorrectionVisible = false"
        @open-modal="dialogBbApplyCorrectionVisible = true"
      ></ifa-bb-apply-correct-cancel-input>
      <!-- STARアップロードファイル出力 -->
      <ifa-star-upload-file-output-list
        :key="reloadStartupLoad"
        ref="IfaStarUploadFileOutputList"
        :is-visible="dialogIpopoStarUploadFileVisible"
        @close-modal="dialogIpopoStarUploadFileVisible = false"
      ></ifa-star-upload-file-output-list>
    </div>
  </div>
</template>
<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaSubscriptInput from './IfaSubscriptInput'
import IfaBbApplyCorrectCancelInput from './IfaBbApplyCorrectCancelInput'
import IfaStarUploadFileOutputList from './IfaStarUploadFileOutputList'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrandCode10DomainModel from '@/resource/domain/IfaBrandCode10DomainModel.json'
import { IfaBbApplyListX002RequestModel } from './js/IfaBbApplyListX002RequestModel'
import { IfaBbApplyListA004RequestModel } from './js/IfaBbApplyListA004RequestModel'
import { IfaBbApplyListA005RequestModel } from './js/IfaBbApplyListA005RequestModel'
import { IfaBbApplyListA006RequestModel } from './js/IfaBbApplyListA006RequestModel'
import { IfaBbApplyListFormModel } from './js/IfaBbApplyListFormModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getMessage, notifyMessage } from '@/utils/errorHandler'
import { isAccessible } from '@/utils/controlAuth'

export default {
  components: {
    GridTable,
    IfaSubscriptInput,
    IfaBbApplyCorrectCancelInput,
    IfaStarUploadFileOutputList,
    IfaCommonSearch,
    screenTitle
  },
  data() {
    return {
      IfaBrandCode10DomainModel: IfaBrandCode10DomainModel,
      dialogIpopoStarUploadFileVisible: false,
      dialogSubscriptInputVisible: false,
      dialogBbApplyCorrectionVisible: false,
      pqGridOption: getConvertedOption(obj),
      form: new IfaBbApplyListFormModel(),
      disabledBtn: true,
      disabledCsv: true,
      delFlag: false,
      historyIncludeList: [{ key: '0', value: '過去の申込も表示する' }, { key: '1', value: '過去の申込も表示する' }],
      onShowSerachCondition: {
        brandCode: '',
        lotteryResult: '',
        orderStatus: '',
        historyInclude: '0',
        customerNameKanjiKanaTerms: '3'
      },
      pqGridSelectedInfo: {},
      reloadStartupLoad: Date.now()
    }
  },
  computed: {
    ifaBbApplyListX002RequestModel() {
      return new IfaBbApplyListX002RequestModel(this.form)
    },
    ifaBbApplyListA004RequestModel() {
      return new IfaBbApplyListA004RequestModel(this.form)
    },
    ifaBbApplyListA005RequestModel() {
      return new IfaBbApplyListA005RequestModel(this.pqGridSelectedInfo.rowData || {})
    },
    ifaBbApplyListA006RequestModel() {
      return new IfaBbApplyListA006RequestModel(this.pqGridSelectedInfo.rowData || {})
    },
    // csv button 表示/非表示
    csvClickable() {
      return this.$store.getters.userAccount.medUsers.privId === '4' || this.$store.getters.userAccount.medUsers.privId === '9'
    },
    // STARアップロードファイル出力 表示/非表示
    startUploadShow() {
      return this.$store.getters.userAccount.userPermissionInfo.accessibleViewList.includes('SUB0204_02-05')
    }
  },
  watch: {
    'form.courseSelected': {
      deep: true,
      handler(newValue) {
        this.form.course = newValue.filter(e => e.isSelected).map(e => e.id).join(',')
      }
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  methods: {
    showList() {
      this.$nextTick(() => {
        document.querySelector('#btnDisplay')?.click()
      })
    },
    onShow() {
      const params = this.$store.getters.pageInfo.params
      if (params && !params.backFlg) {
        this.handleClear()
        this.form.brandCode = params.brandCode12 || ''
        this.form.butenCode = params.butenCode || ''
        this.form.accountNumber = params.accountNumber || ''
        this.showList()
      } else {
        if (sessionStorage.getItem('IfaBbApplyForm')) {
          this.form = JSON.parse(sessionStorage.getItem('IfaBbApplyForm'))
          this.$refs['commonSearch'].$refs['courseSelect'].handleChangeAny(this.form.courseSelected) // 取引コース【前回入力値】
          const param = this.$_getMenuParams()
          if (param && param.backFlg) {
            this.showList()
          }
        } else {
          this.handleClear()
        }
      }
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
      })
      // Object.keys(this.onShowSerachCondition).forEach(key => {
      //   this.onShowSerachCondition[key] = this.form[key]
      // })
      this.reloadStartupLoad = Date.now()
      this.disabledBtn = true
    },
    setBrandCode(param) {
      this.form.brandCode = param
    },
    // クリアボタン押下
    handleClear() {
      // セッションをクリアする
      sessionStorage.removeItem('IfaBbApplyForm')
      this.$refs.form.clearValidate()
      this.$refs.commonSearch.formClear()
      Object.assign(this.form, this.onShowSerachCondition)
    },
    preCsvDownload() {
      this.ifaBbApplyListA004RequestModel.chkBrokerCodeExclude = this.form.chkBrokerCodeExclude
    },
    // 共通検索条件.営業員コード自動設定判定
    getAutoDisplay(data) {
      this.form.empCodeAutoDispFlag = data.empCodeAutoDispFlag
      if (data.empCodeAutoDispFlag !== '0') {
        obj.colModel.forEach(e => {
          if (e.dataIndx === 'brokerCode') {
            e.hidden = true
          } else if (e.dataIndx === 'brokerName') {
            e.hidden = true
          } else if (e.dataIndx === 'brokerBranchCode') {
            e.hidden = true
          } else if (e.dataIndx === 'brokerBranchName') {
            e.hidden = true
          } else if (e.dataIndx === 'brokerChargeCode') {
            e.hidden = true
          } else if (e.dataIndx === 'employeeName') {
            e.hidden = true
          }
        })
      } else {
        obj.colModel.forEach(e => {
          if (e.dataIndx === 'brokerCode') {
            e.hidden = false
          } else if (e.dataIndx === 'brokerName') {
            e.hidden = false
          } else if (e.dataIndx === 'brokerBranchCode') {
            e.hidden = false
          } else if (e.dataIndx === 'brokerBranchName') {
            e.hidden = false
          } else if (e.dataIndx === 'brokerChargeCode') {
            e.hidden = false
          } else if (e.dataIndx === 'employeeName') {
            e.hidden = false
          }
        })
      }
    },
    // 表示ボタン押下
    searchHandler() {
      this.disabledBtn = true
      this.ifaBbApplyListX002RequestModel.chkBrokerCodeExclude = this.form.chkBrokerCodeExclude
      this.ifaBbApplyListX002RequestModel.customerNameKanjiKanaTerms = +this.form.customerNameKanjiKanaTerms - 1
    },
    // STARアップロードファイル出力ボタン押下
    exportStarUploadCSV() {
      this.$refs['IfaStarUploadFileOutputList'].onShow()
      this.dialogIpopoStarUploadFileVisible = true
    },
    subscriptComplete() {
      this.dialogSubscriptInputVisible = false
      document.querySelector('#btnDisplay').click()
    },
    // 一覧選択
    handleClick(ui) {
      this.pqGridSelectedInfo = ui
      this.disabledBtn = false
    },
    x002ResponseHandler(data) {
      if (data.dataList.length > 0) {
        this.pqGridOption.dataModel.data = data.dataList[0].bbApplyListList.map(item => ({
          ...item,
          bbPrice: this.getGridcolBbPrice(item)
        }))
        this.disabledCsv = false
      } else {
        this.pqGridOption.dataModel.data = []
        this.disabledCsv = true
      }

      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
      })
      this.reloadStartupLoad = Date.now()
      // セッションへ保存
      sessionStorage.setItem('IfaBbApplyForm', JSON.stringify(this.form))
    },
    getGridcolBbPrice(item) {
      return item.bbPrice === '成行'
        ? '成行'
        : !item.bbPrice.substring(0, item.bbPrice.length - 1)
          ? '-'
          : item.bbPrice.substring(item.bbPrice.length - 1) === '円'
            ? ifaFormatUtils.withCommaInteger(item.bbPrice.substring(0, item.bbPrice.length - 1)) + '円'
            : item.bbPrice
    },

    responseErrorHandlerx002(error) {
      this.$_logError(error)
      this.disabledCsv = true
    },
    responseHandlerA004(data) {
      this.$_logDebug(data)
    },
    responseErrorHandlerA004(error) {
      this.$_logError(error)
    },
    responseHandlerA005() {
      this.$refs.ifaBbApplyCorrectCancelInput.onShow(this.ifaBbApplyListA005RequestModel)
    },
    responseErrorHandlerA005(error) {
      this.$_logError(error)
      this.dialogBbApplyCorrectionVisible = false
    },
    responseHandlerA006() {
      this.$refs['IfaSubscriptInput'].onShow(this.ifaBbApplyListA006RequestModel)
    },
    responseErrorHandlerA006(error) {
      this.$_logError(error)
      this.dialogSubscriptInputVisible = false
    },
    customerMenuA008() {
      const userName = this.pqGridSelectedInfo.rowData.customerNameKanji
      const accountNumber = this.pqGridSelectedInfo.rowData.accountNumber || ''
      const butenCode = this.pqGridSelectedInfo.rowData.butenCode || ''
      const menuId = this.$store.getters.pageInfo.menuId
      const label = this.$store.getters.pageInfo.label
      const params = {
        accountNumber,
        userName,
        butenCode,
        menuId,
        label
      }

      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        this.$_startShowMenu(this.$customerMenuInitialScreenId, params)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          'BB申込一覧'
        )
      }
    }
  }
}
const VIEW_NAME = 'BB申込一覧'

const obj = {
  width: 0,
  height: 0,
  title: VIEW_NAME,
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false
}
obj.colModel = [
  {
    title: '銘柄コード',
    dataIndx: 'brandCode',
    width: 90,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '銘柄名',
    dataIndx: 'brandName',
    width: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    width: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerBranchCode',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'brokerBranchName',
    width: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    width: 110,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'employeeName',
    width: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render({ rowData }) {
      if (rowData.brokerChargeCode === '0000') {
        return '-'
      } else {
        return rowData.employeeName
      }
    }
  },
  {
    title: '部店',
    dataIndx: 'butenCode',
    width: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    width: 90,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名(漢字)',
    dataIndx: 'customerNameKanji',
    width: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    width: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '取引コース',
    dataIndx: 'course',
    width: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    codeValue: {
      codeListId: 'TRADE_COURSE',
      dispPattern: 1
    }
  },
  {
    title: '投資家属性',
    dataIndx: 'investorAttributeName',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: 'BB申込株数',
    dataIndx: 'bbQuantity',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.bbQuantity)
    }
  },
  {
    title: '申込価格',
    dataIndx: 'bbPrice',
    width: 120,
    editable: false,
    halign: 'center',
    align: 'right'
  },
  {
    title: '裁量希望株数',
    dataIndx: 'quantitySairyou',
    width: 110,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.quantitySairyou)
    }
  },
  {
    title: '当選株数',
    dataIndx: 'bbQuantityAlloc',
    width: 90,
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const rowData = ui.rowData
      if (rowData.bbQuantityAlloc !== '-') {
        return ifaFormatUtils.withCommaInteger(rowData.bbQuantityAlloc)
      } else {
        return '-'
      }
    }
  },
  {
    title: '抽選結果',
    dataIndx: 'lotteryResult',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    codeValue: {
      codeListId: 'SUBSCRIPT_BB_DRAWING_RESULT',
      dispPattern: 1
    }
  },
  {
    title: '注文状況',
    dataIndx: 'orderStatus',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    codeValue: {
      codeListId: 'FACE_TO_FACE_SUBSCRIPT_ORDER_STATUS',
      dispPattern: 2
    }
  },
  {
    title: '注文株数',
    dataIndx: 'orderQuantity',
    width: 90,
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const rowData = ui.rowData
      if (rowData.orderQuantity !== '-') {
        return ifaFormatUtils.withCommaInteger(rowData.orderQuantity)
      } else {
        return '-'
      }
    }
  },
  {
    title: '預り区分',
    dataIndx: 'depositType',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    codeValue: {
      codeListId: 'FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE',
      dispPattern: 1
    }
  },
  {
    title: '勧誘区分',
    dataIndx: 'kanyuKbn',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    codeValue: {
      codeListId: 'INVITATION_TYPE',
      dispPattern: 1
    }
  },
  {
    title: 'ワーニング申請チェック',
    dataIndx: 'warningApplyArranged',
    width: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      const rowData = ui.rowData
      const warningApplyArranged = rowData.warningApplyArranged
      if (warningApplyArranged === '0') {
        return '申請済チェックなし'
      } else if (warningApplyArranged === '1') {
        return '申請済チェックあり'
      } else if (warningApplyArranged === '2') {
        return '勧誘なし'
      } else {
        return '-'
      }
    }
  },
  {
    title: '申込日時',
    dataIndx: 'bbCreateDate',
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '申込者',
    dataIndx: 'bbCreateUserName',
    width: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: 'セクション名',
    dataIndx: 'sectionName',
    width: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '電子交付同意',
    dataIndx: 'edelivAgreementDate',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '目論見書閲覧',
    dataIndx: 'readTime',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '備考',
    dataIndx: 'bbRemark',
    width: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '&nbsp;',
    dataIndx: '',
    width: 0,
    dataType: 'string',
    editable: false,
    align: 'center',
    render: function(ui) {
    // 常に空白を表示
      return ''
    }
  },
  {
    title: 'ブックビルディング申込期間（開始）',
    dataIndx: 'bbPresentationFrom',
    dataType: 'string',
    hidden: true
  },
  {
    title: '明細番号',
    dataIndx: 'detailNumber',
    dataType: 'string',
    hidden: true
  }
]

// Define a datamodel for the table
obj.dataModel = {
  data: [],
  location: 'local',
  sorting: 'local',
  paging: 'local',
  sortDir: 'down'
}
// Define a page model for the table
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>
<style scoped>
:deep(.search-form__item) {
  width: 140px;
  vertical-align: top;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
}
:deep(.el-checkbox__label) {
  font-weight: bold;
}
.gridButtonArea {
  margin-bottom: 10px;
}
.content-card {
    margin: 0.5rem 1rem;
    padding: 1rem 0;
}
:deep(.el-form-item.asterisk-left.form_label) {
  margin-right: 10px !important;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
