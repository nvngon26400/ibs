<template>
  <!-- 画面名：顧客振込先金融機関口座 -->
  <div
    style="width: 100%; overflow-x: auto;"
  >
    <screen-title :text="IfaCustomerDestinationBankAccountFormModel.title.name"></screen-title>
    <el-form
      ref="IfaCustomerDestinationBankAccountFormModel"
      :model="IfaCustomerDestinationBankAccountFormModel"
      :inline="true"
    >
      <el-card
        class="content-card"
        shadow="always"
        style="min-width: 640px;"
      >
        <div class="filter-container">
          <ifa-common-search
            ref="commonSearchItem"
            display-pattern="pt1"
            list-pattern="pt2"
            :form="IfaCustomerDestinationBankAccountFormModel"
            :course-validate="true"
            original-screen-id="SUB020303-01"
            @mediate-user-privacy="mediateUserPrivacy"
          ></ifa-common-search>
        </div>

        <!-- 検索用ボタン -->
        <el-row class="form_button">
          <ifa-button
            id="btnDisplay"
            name="btnDisplay"
            text="表示"
            width="90"
            search
            small
            :form="formRef"
            :request-model="IfaCustomerDestinationBankAccountA002RequestModel"
            action-id="SUB020303-01#A002"
            action-type="requestAction"
            @response-handler="responseHandlerA002($event)"
          ></ifa-button>
          <ifa-button
            id="btnTopInputClear"
            name="btnTopInputClear"
            text="クリア"
            width="90"
            color="white"
            small
            action-type="originalAction"
            @app-action-handler="formClear"
          ></ifa-button>
        </el-row>
      </el-card>
    </el-form>
    <el-row>
      <el-card class="content-card">
        <el-row>
          <div class="gridButtonArea">
            <ifa-button
              id="btnCsvDownload"
              name="btnCsvDownload"
              :disabled="csvbtn"
              text="CSV出力"
              width="90"
              color="primary"
              small
              action-id="SUB020303-01#A004"
              :form="formRef"
              :request-model="IfaCustomerDestinationBankAccountA004RequestModel"
              csv-file-name="顧客振込先金融機関口座"
              action-type="outputCsvAction"
              :is-check-csv-download-allowed="true"
              :is-check-csv-download-privacy-confirmation="true"
            ></ifa-button>
          </div>
        </el-row>
        <grid-table
          ref="gridTable"
          :options="pqGridOption"
          :auto-refresh="false"
        ></grid-table>
      </el-card>
    </el-row>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaCustomerDestinationBankAccountA002RequestModel } from './js/IfaCustomerDestinationBankAccountA002RequestModel'
import { IfaCustomerDestinationBankAccountA004RequestModel } from './js/IfaCustomerDestinationBankAccountA004RequestModel'
import { IfaCustomerDestinationBankAccountFormModel } from './js/IfaCustomerDestinationBankAccountFormModel'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'

export default {
  components: {
    GridTable,
    screenTitle,
    IfaCommonSearch
  },
  data() {
    return {
      IfaCustomerDestinationBankAccountFormModel: new IfaCustomerDestinationBankAccountFormModel(),
      pqGridOption: getConvertedOption(obj),
      csvbtn: true,
      formRef: {}
    }
  },
  computed: {
    IfaCustomerDestinationBankAccountA002RequestModel() {
      return new IfaCustomerDestinationBankAccountA002RequestModel(
        this.IfaCustomerDestinationBankAccountFormModel
      )
    },
    IfaCustomerDestinationBankAccountA004RequestModel() {
      return new IfaCustomerDestinationBankAccountA004RequestModel(
        this.IfaCustomerDestinationBankAccountFormModel
      )
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  mounted() {
    this.formRef = this.$refs.IfaCustomerDestinationBankAccountFormModel
  },
  methods: {
    onShow() {
      this.csvbtn = true
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    formClear() {
      this.$refs['IfaCustomerDestinationBankAccountFormModel'].clearValidate()
      this.$refs.commonSearchItem.formClear()
    },
    mediateUserPrivacy(data) {
      obj.colModel.forEach(e => {
        if (data.empCodeAutoDispFlag === '1') {
          switch (e.dataIndx) {
            case 'brokerName':
            case 'brokerChargeCode':
            case 'employeeName':
            case 'brokerCode':
            case 'brokerageBranchCode':
            case 'branchNameOfBroker':
              e.hidden = true
              break
            case 'blank':
              e.hidden = true
              break
          }
        } else {
          switch (e.dataIndx) {
            case 'brokerName':
            case 'brokerChargeCode':
            case 'employeeName':
            case 'brokerCode':
            case 'brokerageBranchCode':
            case 'branchNameOfBroker':
              e.hidden = false
              break
            case 'blank':
              e.hidden = true
              break
          }
        }
      })
      this.$refs['gridTable'].refreshView(true)
    },
    responseHandlerA002: function(data) {
      // 一覧へのデータの反映
      this.csvbtn = true // CSV出力ボタンの非活性化
      if (data.dataList.length > 0) {
        if (data.dataList[0].customerDestinationBankAccountList.length > 0) {
          this.csvbtn = false // CSV出力ボタンの活性化
        }
        this.pqGridOption.dataModel.data = data.dataList[0].customerDestinationBankAccountList
      } else {
        this.pqGridOption.dataModel.data = []
      }

      this.$refs['gridTable'].refreshView(true)
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '顧客振込先金融機関口座',
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
    width: 150,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    width: 100,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'employeeName',
    width: 140,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '部店',
    dataIndx: 'butenCode',
    width: 100,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    width: 120,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttributeName',
    width: 200,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名(漢字)',
    dataIndx: 'customerNameKanji',
    width: 150,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    width: 150,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '振込先金融機関',
    dataIndx: 'bankNameKanji',
    width: 180,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '本・支店',
    dataIndx: 'branchNameKanji',
    width: 150,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '預金種別',
    dataIndx: 'depositType',
    width: 120,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '金融機関口座番号',
    dataIndx: 'destinationAccountNumber',
    width: 130,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    width: 120,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerageBranchCode',
    width: 100,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'branchNameOfBroker',
    width: 200,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '&nbsp;',
    dataIndx: 'blank',
    minWidth: 510,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function() {
      return ''
    }
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
}
.content-card {
  margin: 0.5rem 1rem;
}

.middle_input {
  width: 120px;
}

.form_button {
  padding-left: 46px;
  margin-bottom:10px;
}

.gridButtonArea {
  margin-bottom: 10px;
}

:deep(.form_label) .el-form-item__label {
  width: 135px;
}

:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
