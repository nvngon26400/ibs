<template>
  <!-- 画面名：SBIラップ管理報酬 -->
  <div>
    <screen-title :text="form.title"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
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
                :form="form"
                display-pattern="pt8"
                :course-validate="true"
                :add-internet-to-courses="'on'"
                @mediate-user-privacy="mediateUserPrivacy"
              ></ifa-common-search>
            </el-col>
          </el-row>
          <el-row>
            <!-- 期間指定 /-->
            <ifa-date-range-picker
              v-model="form.period"
              label="登録日"
              class="form_label"
              prop="period"
              size="small"
              style="position: initial;"
              required
            ></ifa-date-range-picker>
          </el-row>
          <el-row style="margin-left: 9rem;">
            <span style="position: initial;">
              ※期間は12ヶ月以内を指定してください。（過去2年間の履歴を照会いただけます。）
            </span>
          </el-row>
        </div>

        <!-- 検索用ボタン -->
        <el-row>
          <el-col
            :span="8"
            class="search_btn-area"
          >
            <ifa-button
              id="btnDisplay"
              name="btnDisplay"
              text="表示"
              search
              small
              width="90"
              :request-model="IfaSbiWrapManageFeeA002RequestModel"
              :form="formRef"
              action-id="SUB020504-01#A002"
              action-type="requestAction"
              @response-handler="responseHandlerDisplayA002($event)"
            ></ifa-button>
            <ifa-button
              id="btnTopInputClear"
              name="btnTopInputClear"
              text="クリア"
              small
              width="90"
              color="secondary"
              action-type="originalAction"
              @app-action-handler="clearA003"
            ></ifa-button>
          </el-col>
        </el-row>
        <!-- /検索用ボタン -->
      </el-card>
      <el-row>
        <el-card class="content-card">
          <el-row v-if="display">
            <div class="gridButtonArea">
              <ifa-button
                id="btnCsvDownload"
                text="CSV出力"
                :disabled="activeBtn"
                small
                :request-model="IfaSbiWrapManageFeeA003RequestModel"
                :form="formRef"
                action-id="SUB020504-01#A003"
                action-type="outputCsvAction"
                csv-file-name="SBIラップ管理報酬"
                :is-check-csv-download-allowed="true"
                :is-check-csv-download-privacy-confirmation="false"
              ></ifa-button>
            </div>
          </el-row>
          <div class="pq-grid-title">SBIラップ管理報酬</div>
          <grid-table
            ref="gridTable"
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
import { getDefaultOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaSbiWrapManageFeeFormModel } from './js/IfaSbiWrapManageFeeFormModel.js'
import { IfaSbiWrapManageFeeA002RequestModel } from './js/IfaSbiWrapManageFeeA002RequestModel.js'
import { IfaSbiWrapManageFeeA003RequestModel } from './js/IfaSbiWrapManageFeeA003RequestModel.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle
  },
  data() {
    return {
      form: new IfaSbiWrapManageFeeFormModel(),
      pqGridOption: null,
      pqGridSelectedInfo: {},
      activeBtn: true,
      formRef: {}
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.userAccount
    },
    IfaSbiWrapManageFeeA002RequestModel() {
      return new IfaSbiWrapManageFeeA002RequestModel(this.form)
    },
    IfaSbiWrapManageFeeA003RequestModel() {
      return new IfaSbiWrapManageFeeA003RequestModel(this.form)
    },
    column() {
      const columnValue = [
        { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '120', halign: 'center', align: 'center' },
        { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '250', halign: 'center', align: 'left' },
        { title: '営業員コード', dataType: 'string', dataIndx: 'empCode', width: '100', halign: 'center', align: 'center' },
        { title: '営業員名', dataType: 'string', dataIndx: 'brokerChargeName', width: '250', halign: 'center', align: 'left' },
        { title: '部店', dataType: 'string', dataIndx: 'buten', width: '100', halign: 'center', align: 'left' },
        { title: '口座番号', dataType: 'string', dataIndx: 'accountNumber', width: '120', halign: 'center', align: 'left',
          render: function(ui) {
            if (ui.rowData.accountNumber) {
              return ifaFormatUtils.zeroPadding(ui.rowData.accountNumber)
            } else {
              return '-'
            }
          }
        },
        { title: '扱者コード', dataType: 'string', dataIndx: 'dealerNumber', width: '120', halign: 'center', align: 'left' },
        { title: '顧客名（漢字）', dataType: 'string', dataIndx: 'customerName', width: '200', halign: 'center', align: 'left' },
        { title: '手数料徴収日', dataType: 'string', dataIndx: 'targetDateYmd', width: '150', halign: 'center', align: 'center',
          render: function(ui) {
            if (ui.rowData.targetDateYmd) {
              return getFormattedDateValue(ui.rowData.targetDateYmd, 'date8')
            } else {
              return '-'
            }
          }
        },
        { title: '手数料番号', dataType: 'string', dataIndx: 'operationFeeCollectionInfoId', width: '320', halign: 'center', align: 'center' },
        { title: '運用サービスID', dataType: 'string', dataIndx: 'name', width: '250', halign: 'center', align: 'center' },
        { title: '徴収額（税抜）', dataType: 'string', dataIndx: 'yenFee', width: '250', halign: 'center', align: 'right',
          render: function(ui) {
            if (ui.rowData.yenFee) {
              return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.yenFee)
            } else {
              return '-'
            }
          }
        }
      ]
      return columnValue
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    }
  },
  created() {
    this.pqGridOption = getDefaultOption(this.column)
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.$refs['form']?.clearValidate()
      this.pqGridOption.dataModel.data = []
      this.form.period = [this.previousBusinessDay, this.previousBusinessDay]
    },
    display() {
      return this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2' || this.userInfo.medUsers.privId === '3'
    },
    responseHandlerDisplayA002(response) {
      if (response.dataList.length > 0) {
        this.activeBtn = false
        this.pqGridOption.dataModel.data = response.dataList[0].sbiWrapManageFeeInfoList
      } else {
        this.activeBtn = true
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['gridTable'].refreshView(true)
    },
    clearA003() {
      this.$refs.commonSearchItem.formClear()
      this.$refs['form']?.clearValidate()
      this.form.buten = ''
      this.form.empCode = ''
      this.form.branchCode = ''
      this.form.isDepositWithdraw = true
      this.form.courseSelect = '0'
      this.form.period = [this.previousBusinessDay, this.previousBusinessDay]
    },
    handleClick(param) {
      this.pqGridSelectedInfo = param
    },
    // 自動入力フラグを受取り、カラムの表示制御を行う
    mediateUserPrivacy(data) {
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('empCode', false)
        this.setHidden('brokerChargeName', false)
      } else {
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
        this.setHidden('empCode', true)
        this.setHidden('brokerChargeName', true)
      }
      this.$refs['gridTable'].refreshView(true)
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    }
  }
}

</script>
<style scoped>
:deep(.search-form__item) {
  margin: 0 0.2rem 0 0.2rem;
  width: 160px;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
}
.content-card {
  margin: 0.5rem 1rem;
  padding: 1rem 0;
}

.middle_input {
  width: 120px;
}

.gridButtonArea {
  margin-bottom: 10px;
}

:deep(.form_label) .el-form-item__label {
  width: 120px;
}

:deep(.el-form-item__label) {
  margin-right: 1rem;
  padding-right: 0px;
}

.swithArea {
  padding-left: 50px;
  margin-bottom: 20px;
  margin-top: 15px;
}

:deep(.el-switch__label) * {
  font-weight: bold;
}

/* :deep(.el-select>.el-input) {
  width: 22em;
} */
.search_btn-area {
  padding: 10px 10px;;
}
</style>
