<template>
  <!-- 画面名：顧客一覧先OP -->
  <div>
    <screen-title :text="form.screenTitle"></screen-title>
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
                ref="commonSearch"
                :form="form"
                display-pattern="pt1"
                list-pattern="pt2"
                :broker-code-validate="false"
                :emp-code-validate="false"
                :course-validate="true"
                :add-internet-to-courses="'on'"
                original-screen-id="SUB0201_03-01"
                @mediate-user-privacy="mediateUserPrivacy"
              ></ifa-common-search>
            </el-col>
          </el-row>
        </div>
        <!-- Collapseタグ -->
        <el-collapse v-model="activeDetailSearchArea">
          <el-collapse-item>
            <template #title>
              <el-icon
                v-if="activeDetailSearchArea == 1"
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
              <!-- 詳細検索条件 -->
              <el-row>
                <div
                  class="adjust_form_margin price_input form_area"
                  style="display: flex; align-items: start;"
                >
                  <label
                    class="left-label"
                    style=" margin-top: 9px;"
                  >必要委託証拠金</label>
                  <ifa-input-price
                    id="necessaryEntrustDepositFrom"
                    v-model="form.necessaryEntrustDepositFrom"
                    class="price_input"
                    size="small"
                    style="margin-right: 0;"
                    input-class="ifa-input_base__wrapper"
                    prop="necessaryEntrustDepositFrom"
                    :min="'-999999999999999999'"
                    :max="'9999999999999999999'"
                    :digit="0"
                    :step="1"
                    :domain="IfaCurrency190DomainModel"
                  ></ifa-input-price>
                  <span class="custom-label">～</span>
                  <ifa-input-price
                    id="necessaryEntrustDepositTo"
                    v-model="form.necessaryEntrustDepositTo"
                    class="price_input"
                    size="small"
                    style="margin-right: 0;"
                    prop="necessaryEntrustDepositTo"
                    :min="'-999999999999999999'"
                    :max="'9999999999999999999'"
                    :digit="0"
                    :step="1"
                    :domain="IfaCurrency190DomainModel"
                  ></ifa-input-price>
                </div>
                <div
                  class="adjust_form_margin price_input form_area"
                  style="display: flex; align-items: start;"
                >
                  <label
                    class="left-label"
                    style=" margin-top: 9px;"
                  >受入証拠金</label>
                  <ifa-input-price
                    id="marginMoneyFrom"
                    v-model="form.marginMoneyFrom"
                    class="price_input"
                    size="small"
                    style="margin-right: 0;"
                    prop="marginMoneyFrom"
                    :min="'-999999999999999999'"
                    :max="'9999999999999999999'"
                    :digit="0"
                    :step="1"
                    :domain="IfaCurrency190DomainModel"
                  ></ifa-input-price>
                  <span class="custom-label">～</span>
                  <ifa-input-price
                    id="marginMoneyTo"
                    v-model="form.marginMoneyTo"
                    class="price_input"
                    size="small"
                    style="margin-right: 0;"
                    prop="marginMoneyTo"
                    :min="'-999999999999999999'"
                    :max="'9999999999999999999'"
                    :digit="0"
                    :step="1"
                    :domain="IfaCurrency190DomainModel"
                  ></ifa-input-price>
                </div>
              </el-row>
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
                    id="beforeProfitAndLossFrom"
                    v-model="form.beforeProfitAndLossFrom"
                    class="price_input"
                    size="small"
                    style="margin-right: 0;"
                    prop="beforeProfitAndLossFrom"
                    :min="'-999999999999999999'"
                    :max="'9999999999999999999'"
                    :step="1"
                    :domain="IfaCurrency190DomainModel"
                  ></ifa-input-price>
                  <span class="custom-label">～</span>
                  <ifa-input-price
                    id="beforeProfitAndLossTo"
                    v-model="form.beforeProfitAndLossTo"
                    class="price_input"
                    size="small"
                    style="margin-right: 0;"
                    prop="beforeProfitAndLossTo"
                    :min="'-999999999999999999'"
                    :max="'9999999999999999999'"
                    :step="1"
                    :domain="IfaCurrency190DomainModel"
                  ></ifa-input-price>
                </el-col>
              </el-row>
            </div>
          </el-collapse-item>
        </el-collapse>
        <el-row style="padding: 1rem 56px 0;">
          <ifa-button
            id="btnDisplay"
            name="btnDisplay"
            text="表示"
            width="90"
            search
            small
            :form="formRef"
            action-id="SUB0201_03-01#A002"
            action-type="requestAction"
            :request-model="IfaCustomerListFuturesOptionsA002RequestModel"
            @response-handler="responseHandlerDisplayA002($event)"
          ></ifa-button>
          <ifa-button
            id="btnTopInputClear"
            name="btnTopInputClear"
            text="クリア"
            width="90"
            color="white"
            small
            action-type="originalAction"
            @app-action-handler="clearA003"
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
                :disabled="customerMenuBtn"
                text="顧客別メニュー"
                color="primary"
                small
                action-type="originalAction"
                @app-action-handler="customerMenuA004"
              ></ifa-button>
              <ifa-button
                id="btnCsvDownload"
                name="btnCsvDownload"
                text="CSV出力"
                small
                width="90"
                :form="formRef"
                :disabled="csvbtn"
                :request-model="IfaCustomerListFuturesOptionsA005RequestModel"
                action-id="SUB0201_03-01#A005"
                action-type="outputCsvAction"
                csv-file-name="先OP"
                :is-check-csv-download-allowed="true"
                :is-check-csv-download-privacy-confirmation="true"
                :pre-request-handler="preRequestHandlerA005"
                @response-handler="responseHandlerA005($event)"
                @response-error-handler="responseErrorHandlerA005($event)"
              ></ifa-button>
            </div>
          </el-row>
          <grid-table
            ref="pqGrid"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaCurrency190DomainModel from '@/resource/domain/IfaCurrency190DomainModel.json'
import { IfaCustomerListFuturesOptionsFormModel } from './js/IfaCustomerListFuturesOptionsFormModel.js'
import { IfaCustomerListFuturesOptionsA002RequestModel } from './js/IfaCustomerListFuturesOptionsA002RequestModel'
import { IfaCustomerListFuturesOptionsA005RequestModel } from './js/IfaCustomerListFuturesOptionsA005RequestModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getMessage, notifyMessage } from '@/utils/errorHandler'
import { isAccessible } from '@/utils/controlAuth'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle
  },
  data() {
    return {
      IfaCurrency190DomainModel: IfaCurrency190DomainModel,
      form: new IfaCustomerListFuturesOptionsFormModel(),
      formRef: {},
      pqGridOption: getConvertedOption(obj),
      pqGridSelectedInfo: {},
      activeDetailSearchArea: 1,
      customerMenuBtn: true,
      csvbtn: true
    }
  },
  computed: {
    IfaCustomerListFuturesOptionsA002RequestModel() {
      return new IfaCustomerListFuturesOptionsA002RequestModel(this.form)
    },
    IfaCustomerListFuturesOptionsA005RequestModel() {
      return new IfaCustomerListFuturesOptionsA005RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      this.pqGridOption.dataModel.data = []
      this.csvbtn = true
      this.customerMenuBtn = true
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
        this.formRef = this.$refs.form
      })
    },
    // カラムの表示判定
    mediateUserPrivacy(data) {
      // 営業員コード自動設定判定 = '0':自動設定なし の場合のみ以下カラムを表示
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('brokerName', false)
        this.setHidden('brokerChargeCode', false)
        this.setHidden('chargeName', false)
        this.setHidden('brokerCode', false)
        this.setHidden('branchCode', false)
        this.setHidden('branchName', false)
      } else {
        this.setHidden('brokerName', true)
        this.setHidden('brokerChargeCode', true)
        this.setHidden('chargeName', true)
        this.setHidden('brokerCode', true)
        this.setHidden('branchCode', true)
        this.setHidden('branchName', true)
      }
      this.$refs['pqGrid'].refreshView(true)
    },
    // 行選択
    handleClick(ui) {
      this.pqGridSelectedInfo = ui
      this.customerMenuBtn = false
    },
    responseHandlerDisplayA002(data) {
      if (data.dataList.length) {
        this.csvbtn = false
      } else {
        this.csvbtn = true
        this.customerMenuBtn = true
      }
      this.pqGridOption.dataModel.data = data.dataList
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
      })
    },
    clearA003() {
      this.$refs['form'].clearValidate()
      this.$refs['commonSearch'].formClear()
      this.form.necessaryEntrustDepositFrom = ''
      this.form.necessaryEntrustDepositTo = ''
      this.form.marginMoneyFrom = ''
      this.form.marginMoneyTo = ''
      this.form.beforeProfitAndLossFrom = ''
      this.form.beforeProfitAndLossTo = ''
    },
    customerMenuA004() {
      const userName = this.pqGridSelectedInfo.rowData.customerNameKanji
      const butenCode = this.pqGridSelectedInfo.rowData.butenCode
      const accountNumber = this.pqGridSelectedInfo.rowData.accountNumber
      const params = {
        accountNumber,
        butenCode,
        userName
      }

      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        this.$_startShowMenu(this.$customerMenuInitialScreenId, params)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          this.form.screenTitle
        )
      }
    },
    preRequestHandlerA005() {
      this.$_logDebug('preRequestHandlerA005-OK')
    },
    responseHandlerA005() {
      this.$_logDebug('responseHandlerA005-OK')
    },
    responseErrorHandlerA005(error) {
      this.$_logError('responseHandlerA005-Error', error)
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    }
  }
}

// GridTableの設定
const obj = {
  width: 0,
  height: 0,
  title: '顧客一覧先OP',
  flexHeight: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  scrollModel: { autoFit: true },
  topVisible: false,
  wrap: false,
  postRenderInterval: 100
}
obj.pageModel = {
  type: 'local',
  rPP: 30,
  rPPOptions: []
}
obj.colModel = [
  { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '200', editable: false, halign: 'center' },
  { title: '営業員コード', dataType: 'string', dataIndx: 'brokerChargeCode', width: '120', halign: 'center' },
  { title: '営業員名', dataType: 'string', dataIndx: 'chargeName', width: '120', halign: 'center' },
  { title: '部店', dataType: 'string', dataIndx: 'butenCode', width: '120', halign: 'center' },
  { title: '口座番号', dataType: 'string', dataIndx: 'accountNumber', width: '120', halign: 'center',
    render: function(ui) {
      // 最大桁前ゼロ埋め
      return ifaFormatUtils.zeroPadding(ui.rowData.accountNumber, 7) || '-'
    }
  },
  { title: '取引コース', dataType: 'string', dataIndx: 'courseName', width: '200', halign: 'center' },
  { title: '顧客名(漢字)', dataType: 'string', dataIndx: 'customerNameKanji', width: '370', halign: 'center' },
  { title: '顧客名(カナ)', dataType: 'string', dataIndx: 'customerNameKana', width: '370', halign: 'center' },
  { title: 'Cランク', dataType: 'string', dataIndx: 'tcCompRank', width: '120', halign: 'center' },
  { title: '必要委託保証金', dataType: 'string', dataIndx: 'necessaryEntrustDepositFuturesOptions', width: '200', halign: 'center', align: 'right',
    render: function(ui) {
      // #,##0 (カンマ区切り整数)
      return ifaFormatUtils.withCommaInteger(ui.rowData.necessaryEntrustDepositFuturesOptions) || '-'
    }
  },
  { title: '受入証拠金', dataType: 'string', dataIndx: 'marginMoneyFuturesOptions', width: '120', halign: 'center', align: 'right',
    render: function(ui) {
      // #,##0 (カンマ区切り整数)
      return ifaFormatUtils.withCommaInteger(ui.rowData.marginMoneyFuturesOptions) || '-'
    }
  },
  { title: '前日評価損益', dataType: 'string', dataIndx: 'beforeProfitAndLossFuturesOptions', width: '120', halign: 'center', align: 'right',
    render: function(ui) {
      const grid = this
      const data = ui.rowData.beforeProfitAndLossFuturesOptions
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
  { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '200', halign: 'center' },
  { title: '支店コード', dataType: 'string', dataIndx: 'branchCode', width: '120', halign: 'center' },
  { title: '支店名', dataType: 'string', dataIndx: 'branchName', width: '370', halign: 'center' }
]

</script>
<style scoped>
.left-label {
  text-align: end;
}
:deep(.center-label) .el-form-item__label {
  padding: 0px 58px;
}
.form_area {
  margin-bottom:20px;
}
:deep(.not_margin_right) {
   margin-right: 0;
}
:deep(.ifa-input_base__wrapper) {
  min-width: 0;
}
.custom_space {
  padding: 0px 67.5px;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
  margin-left: 0.5rem;
}
.content-card {
  margin: 0.5rem 1rem;
  min-width: 550px;
}
:deep(.el-collapse-item__content) {
  padding-bottom: 0%;
}
.gridButtonArea {
  margin-bottom: 10px;
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
:deep(.el-form-item) {
  margin-bottom: 0.5rem;
  margin-right: 0;
}
:deep(.el-form-item__label) {
  font-weight: bold;
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
.price_input :deep(.el-input__wrapper) {
  width: 220px;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
