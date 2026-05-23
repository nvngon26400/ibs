<template>
  <div>
    <screen-title :text="form.pageTitle.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
    >
      <el-row>
        <el-card
          class="content-card"
          shadow="always"
          style="width: 100%;"
        >
          <div class="filter-container">
            <!-- ここに検索条件用のコンテンツを記述する -->
            <el-row style="margin-bottom:1rem; align-items: center;">
              <ifa-input-text
                id="brokerCode"
                v-model="form.brokerCode"
                input-class="broker-code-input"
                label="仲介業者コード"
                prop="brokerCode"
                size="small"
                name="brokerCode"
                :domain="IfaBrokerCodeDomainModel"
              ></ifa-input-text>
              <div style="margin-left: 92px;"></div>
              <ifa-input-text
                id="brokerName"
                v-model="form.brokerName"
                input-class="broker-name-input"
                label="仲介業者名"
                prop="brokerName"
                size="small"
                name="brokerName"
                original-screen-id="SUB0506_01-01"
                :domain="IfaBrokerNameDomainModel"
              ></ifa-input-text>
              <div style="margin-left: 2rem">（部分検索）</div>
            </el-row>
            <el-row style="margin-bottom: 1rem">
              <ifa-input-select
                id="registerDate"
                v-model="form.registerDate"
                label="登録年月"
                prop="registerDate"
                :required="true"
                code-list-id="original"
                :original-list="form.registerDateList"
                size="small"
                style="width:175px;"
              ></ifa-input-select>
            </el-row>
            <el-row>
              <ifa-input-radio
                v-model="form.answerStatus"
                label="回答状況"
                code-list-id="original"
                :original-list="answerStatusList"
                input-class="form_label"
                prop="answerResult"
                :required="true"
                :disabled="false"
              ></ifa-input-radio>
              <ifa-input-radio
                v-model="form.answerResult"
                label="回答結果"
                code-list-id="original"
                :original-list="answerResultList"
                input-class="form_label"
                prop="answerResult"
                :required="true"
                :disabled="false"
              ></ifa-input-radio>
            </el-row>
          </div>
          <div
            class="form_button"
            style="margin-top: 1rem"
          >
            <ifa-button
              id="allBrokerNameDisplay"
              text="全仲介業者名表示"
              :search="true"
              small
              :request-model="A002RequestModel"
              :form="formRef"
              action-type="requestAction"
              action-id="SUB0506_01-01#A002"
              @response-handler="responseHandlerA002"
              @response-error-handler="errorHandlerA002($event)"
            ></ifa-button>
            <ifa-button
              id="searchDisplay"
              text="表示"
              :search="true"
              width="90"
              small
              :request-model="A003RequestModel"
              :form="formRef"
              action-type="requestAction"
              action-id="SUB0506_01-01#A003"
              @response-handler="responseHandlerA003"
              @response-error-handler="errorHandlerA003($event)"
            ></ifa-button>
          </div>
        </el-card>
      </el-row>
      <el-row>
        <el-card
          class="content-card"
          style="width: 100%;"
        >
          <el-row>
            <div class="gridButtonArea">
              <ifa-button
                id="btnCsvDownload"
                name="btnCsvDownload"
                text="CSV出力"
                :disabled="disabledCsv"
                small
                action-type="outputCsvAction"
                :request-model="A004RequestModel"
                :form="formRef"
                action-id="SUB0506_01-01#A004"
                csv-file-name="自己点検記録簿確認（管理者用）"
                :is-check-csv-download-allowed="true"
                :is-check-csv-download-privacy-confirmation="false"
              ></ifa-button>
            </div>
          </el-row>
          <grid-table
            ref="ifaSelfInspectBlotterConfirmManagerGridTable"
            class="ifaSelfInspectBlotterConfirmManagerGridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClickA005"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>
    <ifa-self-inspect-blotter-detail
      :is-visible="selfCheckRecordBookDetailDialogVisible"
      :detail-data="detailData"
      @close-modal="selfCheckRecordBookDetailDialogVisible = false"
    ></ifa-self-inspect-blotter-detail>
    <ifa-requester
      id="IfaSelfInspectBlotterConfirmManagerInitializeA001"
      action-id="SUB0506_01-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaSelfInspectBlotterDetailInitializeA001"
      action-id="SUB0506_01-02#A001"
      action-type="requestAction"
      :request-model="IfaSelfInspectBlotterDetailA001RequestModel"
      @response-handler="responseHandlerIfaSelfInspectBlotterDetailA001($event)"
      @response-error-handler="responseErrorHandlerIfaSelfInspectBlotterDetailA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import IfaSelfInspectBlotterDetail from './IfaSelfInspectBlotterDetail'
import { getConvertedOption } from '@/utils/pqgridHelper'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrokerCodeDomainModel from '@/resource/domain/IfaBrokerCodeDomainModel.json'
import IfaBrokerNameDomainModel from '@/resource/domain/IfaBrokerNameDomainModel.json'
import { IfaSelfInspectBlotterConfirmManagerFormModel } from './js/IfaSelfInspectBlotterConfirmManagerFormModel'
import { IfaSelfInspectBlotterConfirmManagerA002RequestModel } from './js/IfaSelfInspectBlotterConfirmManagerA002RequestModel'
import { IfaSelfInspectBlotterConfirmManagerA003RequestModel } from './js/IfaSelfInspectBlotterConfirmManagerA003RequestModel'
import { IfaSelfInspectBlotterConfirmManagerA004RequestModel } from './js/IfaSelfInspectBlotterConfirmManagerA004RequestModel'
import { IfaSelfInspectBlotterDetailA001RequestModel } from './js/IfaSelfInspectBlotterDetailA001RequestModel'
import { getFormattedMonthValue } from '@/components/Date/js/IfaMonthPickerFunction.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction.js'

export default {
  components: {
    GridTable,
    IfaSelfInspectBlotterDetail,
    screenTitle
  },
  data() {
    return {
      IfaBrokerCodeDomainModel: IfaBrokerCodeDomainModel,
      IfaBrokerNameDomainModel: IfaBrokerNameDomainModel,
      form: new IfaSelfInspectBlotterConfirmManagerFormModel(),
      formRef: {},
      disabledCsv: true,
      pqGridOption: getConvertedOption(obj),
      answerStatusList: [
        { key: '0', value: '全て' },
        { key: '1', value: '未回答' },
        { key: '2', value: '回答済' }
      ],
      answerResultList: [
        { key: '0', value: '全て' },
        { key: '1', value: '正答のみ' },
        { key: '2', value: '誤答有' }
      ],
      detailLinkParam: {
        registerDate: '',
        brokerCode: '',
        brokerName: ''
      },
      detailData: [],
      selfCheckRecordBookDetailDialogVisible: false
    }
  },
  computed: {
    A002RequestModel() {
      return new IfaSelfInspectBlotterConfirmManagerA002RequestModel(this.form)
    },
    A003RequestModel() {
      return new IfaSelfInspectBlotterConfirmManagerA003RequestModel(this.form)
    },
    A004RequestModel() {
      return new IfaSelfInspectBlotterConfirmManagerA004RequestModel(this.form)
    },
    IfaSelfInspectBlotterDetailA001RequestModel() {
      return new IfaSelfInspectBlotterDetailA001RequestModel(this.detailLinkParam)
    }
  },
  watch: {
    'pqGridOption.dataModel.data': {
      handler: function() {
        if (this.pqGridOption.dataModel.data.length > 0) {
          this.disabledCsv = false
        } else {
          this.disabledCsv = true
        }
      },
      deep: true
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.form.answerResult = '0' // 【初期値】全て
      this.form.answerStatus = '0' // 【初期値】全て
      this.$nextTick(() => {
        document.getElementById('IfaSelfInspectBlotterConfirmManagerInitializeA001').click()
      })
    },
    // 登録年月ドロップダウンリストのデータを取得
    responseHandlerA001(response) {
      const today = new Date(this.$store.getters.requestedTime)
      const nowYear = today.getFullYear()
      const nowMonth = today.getMonth() + 1 < 10 ? `0${today.getMonth() + 1}` : today.getMonth() + 1
      const nowYearMonth = `${nowYear}${nowMonth}`
      // 当月分を先頭に過去5年分が選択可能、未来月は選択不可
      this.form.registerDateList = response.dataList[0].registerDateList.map(item => {
        return {
          key: item.codeId,
          value: item.codeName
        }
      }).filter(item => Number(item.key) <= Number(nowYearMonth) && Number(item.key.slice(0, 4)) >= Number(nowYear) - 5).sort((a, b) => Number(b.key) - Number(a.key))
      const nowIndex = this.form.registerDateList.findIndex(item => item.key === nowYearMonth)
      this.form.registerDate = nowYearMonth // 【初期値】当月
      if (nowIndex === -1) {
        this.form.registerDateList.unshift({
          key: this.form.registerDate,
          value: this.$_getFormattedMonthValue(this.form.registerDate, 'date6YearMonthKanji')
        })
      }
    },
    // 全仲介業者名表示ボタンの処理
    responseHandlerA002(response) {
      this.form.brokerCode = ''
      this.form.brokerName = ''
      this.form.selfAssessmentList = response.dataList
      this.pqGridOption.dataModel.data = this.form.selfAssessmentList
      this.$refs['ifaSelfInspectBlotterConfirmManagerGridTable'].refreshView()
    },
    errorHandlerA002(response) {
      if (response.errorLevel === -1) {
        this.pqGridOption.dataModel.data = []
        this.$refs['ifaSelfInspectBlotterConfirmManagerGridTable'].refreshView()
      }
    },
    // 表示ボタンの処理
    responseHandlerA003(response) {
      this.form.selfAssessmentList = response.dataList
      this.pqGridOption.dataModel.data = this.form.selfAssessmentList
      this.$refs['ifaSelfInspectBlotterConfirmManagerGridTable'].refreshView()
    },
    errorHandlerA003(response) {
      if (response.errorLevel === -1) {
        this.pqGridOption.dataModel.data = []
        this.$refs['ifaSelfInspectBlotterConfirmManagerGridTable'].refreshView()
      }
    },
    // 一覧選択
    handleClickA005(row) {
      if (row.dataIndx === 'detail') {
        this.detailLinkParam.registerDate = row.rowData.registerDate
        this.detailLinkParam.brokerCode = row.rowData.brokerCode
        this.detailLinkParam.brokerName = row.rowData.brokerName
        this.$nextTick(() => {
          document.getElementById('IfaSelfInspectBlotterDetailInitializeA001').click()
        })
      }
    },
    responseHandlerIfaSelfInspectBlotterDetailA001(response) {
      this.detailData = response.dataList[0]
      this.selfCheckRecordBookDetailDialogVisible = true
    }
  }
}

function answerColumn() {
  const answerColumnList = []
  for (let i = 0; i < 20; i++) {
    answerColumnList.push(
      {
        title: '項目'.concat(i + 1),
        dataType: 'string',
        dataIndx: 'item'.concat(i + 1),
        width: '80',
        halign: 'center',
        align: 'center',
        colModel: [
          { title: '回答内容', dataIndx: 'confirm'.concat(i + 1), width: '100', align: 'center', halign: 'center',
            render: function(ui) {
              if (ui.rowData['confirm'.concat(i + 1)]) {
                return getCodeValue('SELF_INSPECT_CONFIRM', 1, ui.rowData['confirm'.concat(i + 1)])
              } else {
                return '-'
              }
            }
          },
          { title: '回答理由', dataIndx: 'answerReason'.concat(i + 1), width: '300', align: 'left', halign: 'center' }
        ]
      }
    )
  }
  return answerColumnList
}

const obj = {
  width: 0,
  height: 0,
  title: '自己点検記録簿一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false,
  pageModel: {
    type: 'local',
    curPage: 1,
    rPP: 30,
    rPPOptions: []
  }
}

obj.colModel = [
  {
    title: '詳細',
    dataType: 'string',
    dataIndx: 'detail',
    width: '100',
    align: 'center',
    halign: 'center',
    render: function(ui) {
      return !ui.rowData.confirmationDate ? `<a><span style="color: #092987;">詳細</span></a> 
        <style>
        .ifaSelfInspectBlotterConfirmManagerGridTable tr[pq-row-indx="` + ui.rowIndxPage + `"] {
          background-color: darkseagreen;
        }
        a {
          color: #092987;
          text-decoration: underline;
        }
        a:focus, a:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
        }
        </style>` : `<a><span style="color: #092987;">詳細</span></a> 
        <style>
        a {
          color: #092987;
          text-decoration: underline;
        }
        a:focus, a:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
        }
        </style>`
    }
  },
  {
    title: '仲介業者コード',
    dataType: 'string',
    dataIndx: 'brokerCode',
    width: '120',
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataType: 'string',
    dataIndx: 'brokerName',
    width: '500',
    halign: 'center',
    align: 'left'
  },
  {
    title: '登録年月',
    dataType: 'string',
    dataIndx: 'registerDate',
    minWidth: '100',
    halign: 'center',
    align: 'center',
    render: function(ui) {
      if (ui.rowData.registerDate) {
        return getFormattedMonthValue(ui.rowData.registerDate, 'date6YearMonth')
      } else {
        return '-'
      }
    }
  },
  {
    title: '最新更新日',
    dataType: 'string',
    dataIndx: 'confirmationDate',
    minWidth: '100',
    halign: 'center',
    align: 'center',
    render: function(ui) {
      if (ui.rowData.confirmationDate) {
        return getFormattedDateValue(ui.rowData.confirmationDate, 'date8')
      } else {
        return '-'
      }
    }
  },
  ...answerColumn()
]

</script>

<style>
.filter-container {
  margin-top: 1rem;
}

.content-card {
  margin: 0.5rem 1rem;
}

.form_button {
  padding-left: 20px;
}

.gridButtonArea {
  margin-bottom: 10px;
}

.el-icon-info {
  font-size: 30px;
}

.broker-code-input {
  width: 150px;
}
.broker-name-input {
  width: 280px;
}
</style>
