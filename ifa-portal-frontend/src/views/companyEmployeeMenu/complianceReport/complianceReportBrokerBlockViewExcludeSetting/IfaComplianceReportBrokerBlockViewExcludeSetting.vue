<template>
  <!-- 画面名：コンプライアンス通信仲介業者一括閲覧不要設定 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>

    <!-- 検索フォーム -->
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
        <!-- 検索フォーム 1行目 -->
        <div class="filter-container">
          <el-row style="flex-wrap: nowrap;">
            <ifa-input-text
              id="brokerName"
              v-model="form.brokerName"
              label="仲介業者名"
              prop="brokerName"
              size="small"
              original-screen-id="SUB0505_03-01"
              :domain="IfaBrokerNameDomainModel"
              @keydown.enter.prevent
            >
            </ifa-input-text>
            <span
              style="flex: 0 0 6rem; margin: 0.5rem 0;"
            >（部分検索）</span>
            <ifa-balloon-icon
              :show-message="showMessage"
              icon-type="info"
              style="flex: 0 0 30px; margin-left: auto; margin-right: 1rem;"
            >
              <div>本画面は内部管理統括部のみが操作を行います。</div>
            </ifa-balloon-icon>
          </el-row>
        </div>

        <!-- 検索フォーム 2行目 全仲介業者名表示 / 表示 ボタン -->
        <el-row class="form_button">
          <ifa-button
            id="btnDisplay"
            name="btnDisplay"
            class="ifa-button-A002"
            text="全仲介業者名表示"
            search
            small
            action-type="requestAction"
            action-id="SUB0505_03-01#A002"
            :form="formRef"
            :pre-request-handler="preRequestHandlerA002"
            @response-handler="responseHandlerA002($event)"
          ></ifa-button>
          <ifa-button
            id="btnDisplay"
            name="btnDisplay"
            text="表示"
            width="90"
            search
            small
            action-type="requestAction"
            action-id="SUB0505_03-01#A003"
            :form="formRef"
            :request-model="IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestModel"
            :pre-request-handler="preRequestHandlerA003"
            @response-handler="responseHandlerA003($event)"
            @response-error-handler="responseErrorHandlerA003($event)"
          ></ifa-button>
        </el-row>
      </el-card>
    </el-form>

    <!-- 表示エリア -->
    <el-card
      class="content-card"
      style="width: calc(100% - 2rem) !important"
    >
      <!-- 登録 / 登録解除 ボタン -->
      <el-row>
        <ifa-button
          id="btnRegister"
          :disabled="!activateRegister"
          name="btnRegister"
          text="登録"
          width="90"
          small
          action-type="requestAction"
          action-id="SUB0505_03-01#A004"
          :request-model="IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestModel"
          @response-handler="responseHandlerA004($event)"
        ></ifa-button>
        <ifa-button
          id="btnDisplay"
          :disabled="!activateUnregister"
          name="btnDisplay"
          text="登録解除"
          width="150"
          small
          action-type="requestAction"
          action-id="SUB0505_03-01#A005"
          :request-model="IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestModel"
          @response-handler="responseHandlerA005($event)"
        ></ifa-button>
      </el-row>

      <!-- コンプライアンス通信仲介業者一括閲覧不要設定一覧 -->
      <grid-table
        ref="gridTable"
        style="margin-top: 1rem"
        :options="pqGridOption"
        :auto-refresh="false"
        @click="handleClickGridTable"
      ></grid-table>
    </el-card>

    <!-- 再検索用のifa-requester -->
    <ifa-requester
      id="IfaComplianceReportBrokerBlockViewExcludeSettingA002"
      action-type="requestAction"
      action-id="SUB0505_03-01#A002"
      :pre-request-handler="preRequestHandlerA002"
      @response-handler="responseHandlerA002($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaComplianceReportBrokerBlockViewExcludeSettingA003"
      action-type="requestAction"
      action-id="SUB0505_03-01#A003"
      :request-model="IfaComplianceReportBrokerBlockViewExcludeSettingA003ReSearchRequestModel"
      :pre-request-handler="preRequestHandlerA003Research"
      @response-handler="responseHandlerA003($event)"
      @response-error-handler="responseErrorHandlerA003($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption } from '@/utils/pqgridHelper'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrokerNameDomainModel from '@/resource/domain/IfaBrokerNameDomainModel.json'
import IfaText10DomainModel from '@/resource/domain/IfaText10DomainModel.json'
import IfaBrokerCodeDomainModel from '@/resource/domain/IfaBrokerCodeDomainModel.json'
import IfaDate8DomainModel from '@/resource/domain/IfaDate8DomainModel.json'
import { IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestModel } from './js/IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestModel'
import { IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestModel } from './js/IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestModel'
import { IfaComplianceReportBrokerBlockViewExcludeSettingA003ReSearchRequestModel } from './js/IfaComplianceReportBrokerBlockViewExcludeSettingA003ReSearchRequestModel'
import { IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestModel } from './js/IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestModel'
import { IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestModel } from './js/IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestModel'
import { IfaComplianceReportBrokerBlockViewExcludeSettingFormModel } from './js/IfaComplianceReportBrokerBlockViewExcludeSettingFormModel'
import IfaRequester from '@/components/Button/IfaRequester.vue'
export default {
  components: {
    GridTable,
    screenTitle,
    IfaRequester
  },
  data() {
    return {
      formRef: {},
      activateRegister: false,
      activateUnregister: false,
      form: new IfaComplianceReportBrokerBlockViewExcludeSettingFormModel(),
      IfaDate8DomainModel: IfaDate8DomainModel,
      IfaText10DomainModel: IfaText10DomainModel,
      IfaBrokerCodeDomainModel: IfaBrokerCodeDomainModel,
      IfaBrokerNameDomainModel: IfaBrokerNameDomainModel,
      rules: {
      },
      pqGridOption: getConvertedOption(obj),
      rowData: [],
      showMessage: false
    }
  },
  computed: {
    IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestModel() {
      return new IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestModel(this.form)
    },
    IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestModel() {
      return new IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestModel(this.form)
    },
    IfaComplianceReportBrokerBlockViewExcludeSettingA003ReSearchRequestModel() {
      return new IfaComplianceReportBrokerBlockViewExcludeSettingA003ReSearchRequestModel(this.form)
    },
    IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestModel() {
      return new IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestModel(this.form)
    },
    IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestModel() {
      return new IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestModel(this.form)
    }
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.showMessage = true
      this.activateRegister = false
      this.activateUnregister = false
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView()
      })
    },
    preRequestHandlerA002() {
      // 検索方法を設定
      this.form.branchNameOfBrokerLastSearchWord = null

      // 登録・登録解除ボタンを非活性化
      this.activateRegister = false
      this.activateUnregister = false
    },
    responseHandlerA002(event) {
      // 取得した情報をgridTableに表示
      this.pqGridOption.dataModel.data = event.dataList
      this.$refs['gridTable'].refreshView()
    },
    preRequestHandlerA003() {
      // 検索方法を設定
      this.form.branchNameOfBrokerLastSearchWord = this.form.brokerName

      // 登録・登録解除ボタンを非活性化
      this.activateRegister = false
      this.activateUnregister = false
    },
    preRequestHandlerA003Research() {
      // 登録・登録解除ボタンを非活性化
      this.activateRegister = false
      this.activateUnregister = false
    },
    responseHandlerA003(event) {
      // 取得した情報をgridTableに表示
      this.pqGridOption.dataModel.data = event.dataList
      this.$refs['gridTable'].refreshView()
    },
    responseErrorHandlerA003(event) {
      // gridTableをクリア
      this.pqGridOption.dataModel.data = []
      this.$refs[`gridTable`].refreshView()
    },
    responseHandlerA004(event) {
      if (this.form.branchNameOfBrokerLastSearchWord === null) {
        document.getElementById('IfaComplianceReportBrokerBlockViewExcludeSettingA002').click()
      } else {
        document.getElementById('IfaComplianceReportBrokerBlockViewExcludeSettingA003').click()
      }
    },
    responseHandlerA005(event) {
      if (this.form.branchNameOfBrokerLastSearchWord === null) {
        document.getElementById('IfaComplianceReportBrokerBlockViewExcludeSettingA002').click()
      } else {
        document.getElementById('IfaComplianceReportBrokerBlockViewExcludeSettingA003').click()
      }
    },
    // 一覧選択
    handleClickGridTable(ui) {
      this.form.brokerCode = ui.rowData.brokerCode
      this.activateUnregister = ui.rowData.corBrowseFlag === '1'
      this.activateRegister = ui.rowData.corBrowseFlag === null || ui.rowData.corBrowseFlag === '0'
    }
  }
}

// Grid表示用データ
const obj = {
  width: 0,
  height: 0,
  title: 'コンプライアンス通信仲介業者一括閲覧不要設定一覧',
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
  { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '200', halign: 'center', align: 'center', minWidth: '200' },
  { title: '仲介業者名', dataType: 'string', dataIndx: 'branchNameOfBroker', width: '1100', halign: 'center', align: 'left' },
  { title: '閲覧要否', dataType: 'string', dataIndx: 'corBrowseFlag', width: '120', halign: 'center', align: 'center', minWidth: '120',
    render: function(ui) {
      if (ui.rowData.corBrowseFlag === null || ui.rowData.corBrowseFlag === '0') {
        return {
          text: '閲覧必要',
          style: ''
        }
      } else if (ui.rowData.corBrowseFlag === '1') {
        return {
          text: '閲覧不要',
          style: 'color:#ff1e00;'
        }
      } else {
        return {
          text: '-',
          style: ''
        }
      }
    }
  },
  { title: '登録日', dataType: 'string', dataIndx: 'registeredDate', width: '120', halign: 'center', align: 'left', minWidth: '120' },
  { title: '&nbsp;', dataType: 'string', dataIndx: '', width: 270, halign: 'center', align: 'center',
    render: function() {
      return {
        text: '',
        style: ''
      }
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
.filter-container {
  margin-top:1rem;
}
.content-card {
  margin: 0.5rem 1rem;
}

:deep(.el-textarea__inner) {
  width:82%;
  margin-left:85px;
  resize: none;
}

:deep(.el-form-item) {
  margin-bottom: 2.4rem;
  margin-right: 10px;
}
</style>
