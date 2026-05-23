<template>
  <!-- 画面名：社員：仲介業者からの要望確認, 仲介業者：あなたの要望 -->
  <div
    style="width: 100%; overflow-x: auto;"
  >
    <el-form
      ref="form"
      :model="form"
      :inline="true"
    >

      <screen-title :text="privTitle"></screen-title>
      <!-- 検索条件 始 -->
      <el-card
        class="content-card"
        shadow="always"
      >
        <div class="filter-container">
          <el-row v-if="privCheck">

            <!-- 仲介業者コード -->
            <ifa-input-text
              id="brokerCode"
              v-model="form.brokerCode"
              prop="brokerCode"
              label="仲介業者コード"
              size="small"
              name="brokerCode"
              input-class="broker-code-input"
              :domain="IfaBrokerCodeDomainModel"
            ></ifa-input-text>

            <!-- 仲介業者名 -->
            <ifa-input-text
              id="brokerName"
              v-model="form.brokerName"
              prop="brokerName"
              label="仲介業者名"
              size="small"
              input-class="broker-name-input"
              original-screen-id="SUB00_01-06"
              :domain="IfaBrokerNameDomainModel"
            ></ifa-input-text>
            <div
              style="margin-left: 2rem;padding-top:10px;"
            >（部分検索）</div>
          </el-row>
          <el-row>

            <!-- ステータス -->
            <ifa-input-radio
              v-model="form.status"
              label="ステータス"
              :code-list-id="'SUG_BOX_STATUS'"
              :disp-pattern="1"
              :select-pattern="1"
              :domain="IfaClassRadioDomainModel"
              prop="status"
              :required="true"
            ></ifa-input-radio>

            <!-- バルーンアイコン -->
            <el-col class="right_icon">
              <el-col>
                <ifa-balloon-icon
                  v-if="checkSugBoxPersonalComment"
                  placement="bottom-end"
                  icon-type="info"
                  :message="form.screenComment"
                >
                </ifa-balloon-icon>
              </el-col>
            </el-col>
          </el-row>
          <el-row>
            <!-- 登録日_期間指定 -->
            <ifa-date-range-picker
              ref="datePicker"
              v-model="form.registerDate"
              default-value="pickerOptions.defaultSetting"
              label="登録日"
              :picker-options="pickerOptions"
              size="small"
              style="margin-left: 70px;"
              prop="registerDate"
              :required="true"
            ></ifa-date-range-picker>
          </el-row>
          <el-row class="form_title">
            <ifa-input-text
              id="title"
              v-model="form.title"
              prop="title"
              type="text"
              size="small"
              style="width: 722px;"
              prefix-icon="Search"
              :placeholder="'タイトルを入力'"
              :domain="IfaText127DomainModel"
              original-screen-id="SUB00_01-06"
            ></ifa-input-text>
          </el-row>
        </div>
        <div>
          <!-- 検索用ボタン -->
          <el-row class="form_button">
            <ifa-button
              id="btnDisplay"
              icon="Search"
              text="表示"
              width="90"
              small
              :request-model="IfaSuggestionBoxPersonalA002ApiRequest"
              :form="formRef"
              :action-id="displayId"
              action-type="requestAction"
              @response-handler="displayA002ResponseHandler($event)"
            ></ifa-button>
          </el-row>
        </div>
      </el-card>

      <el-row>
        <el-card
          class="content-card"
        >
          <!-- CSV出力ボタン(権限1) -->
          <el-row v-if="privCheck">
            <div class="gridButtonArea">
              <ifa-button
                id="btnCsvDownload"
                name="btnCsvDownload"
                :disabled="disabledCsv"
                text="CSV出力"
                width="90"
                color="primary"
                small=""
                :request-model="IfaSuggestionBoxPersonalA006ApiRequest"
                :form="formRef"
                action-id="SUB0511_01-01#A006"
                csv-file-name="仲介業者からの要望確認"
                action-type="outputCsvAction"
                :is-check-csv-download-allowed="true"
                :is-check-csv-download-privacy-confirmation="false"
              ></ifa-button>
            </div>
          </el-row>
          <!-- 新規登録ボタン(権限1以外) -->
          <el-row v-if="!privCheck">
            <div class="gridButtonArea">
              <ifa-button
                id="btnSuggestionRegister"
                text="新規登録"
                width="110"
                color="primary"
                small
                action-type="originalAction"
                action-id="SUB00_01-06_2#A001"
                @app-action-handler="newRegisterA003ResponseHandler(true)"
              ></ifa-button>
            </div>
          </el-row>
          <el-row>
            <div
              class="pq-grid-title"
              style="width: 100%;"
            >{{ gridTitle }}</div>
            <grid-table
              ref="gridTable"
              :selected-info="setGridInfo"
              :options="pqGridOption"
              :auto-refresh="false"
              @click="handleClick"
            ></grid-table>
          </el-row>
          <!-- 新規登録 ダイアログ -->
          <ifa-suggestion-register
            ref="IfaSuggestionRegister"
            :is-visible="suggestionRegisterVisible"
            @close-modal="handleCloseModalRegister"
            @update-table="handleUpdateTable"
          ></ifa-suggestion-register>
          <!-- 要望事項詳細 ダイアログ -->
          <ifa-suggestion-detail
            ref="IfaSuggestionDetail"
            :form-data="ifaSuggestionBoxPersonalDetailData"
            :is-visible="suggestionBoxDetailVisible"
            :param="detailData"
            @close-modal="handleCloseModal"
            @update-table="handleUpdateTable"
          ></ifa-suggestion-detail>
          <!-- 仲介業者からの要望詳細 ダイアログ -->
          <ifa-suggestion-detail-broker
            ref="IfaSuggestionDetailBroker"
            :form-data="ifaSuggestionBoxPersonalDetailFromBrokerData"
            :is-visible="suggestionBoxDetaiFromBrokerlVisible"
            :param="detailData"
            @close-modal="handleCloseModalBroker"
            @update-table="handleUpdateTable"
          ></ifa-suggestion-detail-broker>
        </el-card>
      </el-row>
    </el-form>

    <ifa-requester
      id="ifaSuggestionBoxPersonalInitializeX001"
      action-id="SUB00_01-06_1#X001"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxPersonalX001ApiRequest"
      @response-handler="initializeX001ResponseHandler($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaSuggestionBoxPersonalBrokerInitializeX001"
      action-id="SUB0511_01-01#X001"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxPersonalX001ApiRequest"
      @response-handler="initializeX001ResponseHandler($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaSuggestionBoxPersonalRegisterInitializeA001"
      action-type="originalAction"
      @app-action-handler="newRegisterA003ResponseHandler(true)"
    ></ifa-requester>
    <ifa-requester
      id="IfaSuggestionBoxPersonalDetailInitializeA001"
      action-id="SUB00_01-06_3#A001"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxPersonalDetailA001RequestModel"
      @response-handler="detailDisplayA001responseHandler($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaSuggestionBoxPersonalDetailFromBrokerInitializeA001"
      action-id="SUB0511_01-02#A001"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxPersonalDetailFromBrokerA001RequestModel"
      @response-handler="detailFromBrokerdetailDisplayA001responseHandler($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaSuggestionBoxPersonalDisplay002"
      :action-id="displayId"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxPersonalA002ApiRequest"
      @response-handler="displayA002ResponseHandler($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaSuggestionRegister from './IfaSuggestionBoxPersonalRegister'
import IfaSuggestionDetail from './IfaSuggestionBoxPersonalDetail'
import IfaSuggestionDetailBroker from './IfaSuggestionBoxPersonalDetailFromBroker'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption } from '@/utils/pqgridHelper'
import IfaButton from '@/components/Button/IfaButton.vue'
import IfaBrokerCodeDomainModel from '@/resource/domain/IfaBrokerCodeDomainModel.json'
import IfaBrokerNameDomainModel from '@/resource/domain/IfaBrokerNameDomainModel.json'
import IfaClassRadioDomainModel from '@/resource/domain/IfaClassRadioDomainModel.json'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'
import { IfaSuggestionBoxPersonalFormModel } from './js/IfaSuggestionBoxPersonalFormModel'
import { IfaSuggestionBoxPersonalX001ApiRequest } from './js/IfaSuggestionBoxPersonalX001ApiRequest'
import { IfaSuggestionBoxPersonalA002ApiRequest } from './js/IfaSuggestionBoxPersonalA002ApiRequest'
import { IfaSuggestionBoxPersonalA006ApiRequest } from './js/IfaSuggestionBoxPersonalA006ApiRequest'
import { IfaSuggestionBoxPersonalDetailA001RequestModel } from './js/IfaSuggestionBoxPersonalDetailA001RequestModel.js'
import { IfaSuggestionBoxPersonalDetailFromBrokerA001RequestModel } from './js/IfaSuggestionBoxPersonalDetailFromBrokerA001RequestModel.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'

import store from '@/store'
export default {
  components: {
    IfaSuggestionRegister,
    IfaSuggestionDetail,
    IfaSuggestionDetailBroker,
    GridTable,
    screenTitle,
    IfaButton
  },
  data() {
    return {
      pickerOptions: {
        shortcuts: [
          {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date()
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // endDateに前営業日を設定
              // 前営業日が前月の場合には、当月初日を設定する
              const endDate = new Date()
              return [startDate, endDate]
            }
          },
          {
            text: '前月',
            value: () => {
              // startDateに前月の月初を設定
              const startDate = new Date()
              startDate.setHours(0, 0, 0, 0)
              startDate.setMonth(startDate.getMonth() - 1, 1)
              // endDateに前月の月末を設定
              const endDate = new Date()
              endDate.setHours(0, 0, 0, 0)
              endDate.setDate(0)
              return [startDate, endDate]
            }
          }
        ]
      },
      pqGridOption: getDefaultOption(colModel),
      gridTitle: '',
      disabledCsv: true,
      store,
      IfaBrokerCodeDomainModel: IfaBrokerCodeDomainModel,
      IfaBrokerNameDomainModel: IfaBrokerNameDomainModel,
      IfaClassRadioDomainModel: IfaClassRadioDomainModel,
      IfaText127DomainModel,
      form: new IfaSuggestionBoxPersonalFormModel(),
      suggestionRegisterVisible: false,
      suggestionBoxDetailVisible: false,
      suggestionBoxDetaiFromBrokerlVisible: false,
      registerData: {},
      detailData: {},
      ifaSuggestionBoxPersonalNewRegisterData: {},
      ifaSuggestionBoxPersonalDetailData: {},
      ifaSuggestionBoxPersonalDetailFromBrokerData: {},
      formRef: {},
      selectedInfo: ''
    }
  },
  computed: {
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    // 権限チェック 権限1の場合はtrueを返す
    privCheck() {
      if (this.$store.getters.userAccount.medUsers.privId === '1') {
        return true
      }
      return false
    },
    displayId() {
      if (this.$store.getters.userAccount.medUsers.privId === '1') {
        return 'SUB0511_01-01#A002'
      } else {
        return 'SUB00_01-06_1#A002'
      }
    },
    IfaSuggestionBoxPersonalX001ApiRequest() {
      return new IfaSuggestionBoxPersonalX001ApiRequest(this.form)
    },
    IfaSuggestionBoxPersonalA002ApiRequest() {
      return new IfaSuggestionBoxPersonalA002ApiRequest(this.form)
    },
    IfaSuggestionBoxPersonalA006ApiRequest() {
      return new IfaSuggestionBoxPersonalA006ApiRequest(this.form)
    },
    IfaSuggestionBoxPersonalDetailA001RequestModel() {
      return new IfaSuggestionBoxPersonalDetailA001RequestModel({ sbpNo: this.selectedInfo })
    },
    IfaSuggestionBoxPersonalDetailFromBrokerA001RequestModel() {
      return new IfaSuggestionBoxPersonalDetailFromBrokerA001RequestModel({ sbpNo: this.selectedInfo })
    },
    privTitle() {
      if (this.$store.getters.userAccount.medUsers.privId === '1') {
        return '仲介業者からの要望確認'
      } else {
        return 'あなたの要望'
      }
    },
    checkSugBoxPersonalComment() {
      if (this.form.screenComment) {
        return this.form.screenComment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
    }
  },
  watch: {
    'form.registerDate': {
      handler(newValue) {
        this.form.registerDateFrom = newValue[0]
        this.form.registerDateTo = newValue[1]
      }
    },
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
    this.pqGridOption.warap = true
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  mounted() {
    // 要望一覧のタイトル、項目をユーザー権限に合わせて設定する
    this.mediateUserPrivacy(this.$store.getters.userAccount.medUsers.privId)
  },
  methods: {
    onShow() {
      this.formRef = this.$refs.form
      // 1か月前～当日を取得し、期間指定フォームとformに設定
      const endDate = new Date(this.$store.getters.requestedTime)
      const startDate = new Date(this.$store.getters.requestedTime)
      startDate.setMonth(startDate.getMonth() - 1)
      this.form.registerDate = [this.formatDate(startDate), this.formatDate(endDate)]
      this.form.registerDateFrom = this.form.registerDate[0]
      this.form.registerDateTo = this.form.registerDate[1]
      // 【初期値】全て
      this.form.status = ' '
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      // 権限毎に初期処理を分岐
      if (this.$store.getters.userAccount.medUsers.privId !== '1') {
        this.$nextTick(() => {
          document.getElementById('ifaSuggestionBoxPersonalInitializeX001').click()
        })
      } else {
        this.$nextTick(() => {
          document.getElementById('ifaSuggestionBoxPersonalBrokerInitializeX001').click()
        })
      }
    },
    initializeX001ResponseHandler(response) {
      this.form.screenComment = response.dataList[0].screenComment
      if (this.$store.getters.userAccount.medUsers.privId === '1') {
        this.gridTitle = '仲介業者からの要望一覧'
      } else {
        this.gridTitle = 'あなたの要望一覧'
      }
      if (response.dataList[0].requestList.length > 0) {
        this.pqGridOption.dataModel.data = response.dataList[0].requestList
      }
      this.$refs['gridTable'].refreshView()
      this.selectedInfo = {}
    },
    displayA002ResponseHandler(response) {
      if (response.dataList.length > 0) {
        this.pqGridOption.dataModel.data = response.dataList[0].requestList
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['gridTable'].refreshView()
    },
    newRegisterA003ResponseHandler(response) {
      this.suggestionRegisterVisible = true
    },
    newRegisterA001(response) {
      this.registerData = response.dataList[0]
      this.suggestionRegisterVisible = true
      this.$nextTick(() => {
        document.getElementById('btnDisplay').click()
      })
    },
    detailDisplayA001responseHandler(response) {
      this.ifaSuggestionBoxPersonalDetailData = response.dataList[0]
      this.suggestionBoxDetailVisible = true
    },
    detailFromBrokerdetailDisplayA001responseHandler(response) {
      this.ifaSuggestionBoxPersonalDetailFromBrokerData = response.dataList[0]
      this.suggestionBoxDetaiFromBrokerlVisible = true
    },
    handleClick(ui) {
      this.selectedInfo = ui.rowData.sbpNo
      if (ui.dataIndx === 'title') {
        // 権限コード1の場合
        if (this.$store.getters.userAccount.medUsers.privId === '1') {
          this.$nextTick(() => {
            document.getElementById('IfaSuggestionBoxPersonalDetailFromBrokerInitializeA001').click()
          })
        // 権限コード1以外の場合
        } else {
          this.$nextTick(() => {
            document.getElementById('IfaSuggestionBoxPersonalDetailInitializeA001').click()
          })
        }
      }
    },
    // 仲介業者権限の場合は一部項目を非表示にし、表のタイトルを変更する
    mediateUserPrivacy(data) {
      if (data !== '1') {
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
        this.setHidden('empCode', true)
        this.setHidden('empName', true)
      } else {
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('empCode', false)
        this.setHidden('empName', false)
      }
      this.$refs['gridTable'].refreshView(true)
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    formatDate(date, f = false) {
      date = date || new Date()
      return date.getFullYear() + '/' +
            (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
            ('0' + date.getDate()).slice(-2) +
            (f ? ' ' + ('0' + date.getHours()).slice(-2) + ':' +
              ('0' + date.getMinutes()).slice(-2) : '')
    },
    // テーブル更新
    handleUpdateTable() {
      document.getElementById('IfaSuggestionBoxPersonalDisplay002').click()
      this.$refs['gridTable'].refreshView()
    },
    handleCloseModalRegister() {
      this.suggestionRegisterVisible = false
    },
    handleCloseModal() {
      this.suggestionBoxDetailVisible = false
    },
    handleCloseModalBroker() {
      this.suggestionBoxDetaiFromBrokerlVisible = false
    },
    editGridData(data) {
      return data.map(item => ({
        ...item,
        status: item.disclosureFlag
      }))
    }
  }
}

// 権限1 Grid表示用データ
// 列の定義
const colModel = [
  {
    title: '要望No',
    dataIndx: 'sbpNo',
    width: 40,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '更新日',
    dataIndx: 'ansUpdateTime',
    width: 70,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const ansUpdateTime = getFormattedDateValue(ui.rowData.ansUpdateTime, 'datetime8')
      return ansUpdateTime || '-'
    }
  },
  {
    title: '登録日',
    dataIndx: 'registerDate',
    width: 70,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const registerDate = getFormattedDateValue(ui.rowData.registerDate, 'datetime8')
      return registerDate || '-'
    }
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    width: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '営業員コード',
    dataIndx: 'empCode',
    width: 80,
    dataType: 'string',
    editable: false,
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'empName',
    width: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: 'タイトル',
    dataIndx: 'title',
    width: 450,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      const rowData = ui.rowData
      const title = rowData.title
      return title ? changeColorBorderBottom(title) : '-'
    }, className: 'TitleColumn'
  },
  {
    title: 'カテゴリ',
    dataIndx: 'category',
    width: 80,
    dataType: 'string',
    editable: false,
    align: 'center',
    codeValue: { codeListId: 'SUG_BOX_CATEGORY', dispPattern: 1 }
  },
  {
    title: 'ステータス',
    dataIndx: 'status',
    width: 80,
    dataType: 'string',
    editable: false,
    align: 'center',
    codeValue: { codeListId: 'SUG_BOX_STATUS', dispPattern: 1 }
  }
]

const changeColorBorderBottom = (item) => {
  return `<span class='grid-link'><a>` + item + `</a></span> 
          <style>
        .grid-link a {
          color: #092987;
          text-decoration: underline;
         &:focus, &:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
         }
        }
        </style>`
}

</script>
<style scoped>
.filter-container {
  margin-top: 1rem;
}

.content-card {
  margin: 0.5rem 1rem;
}

.form_button {
  padding-left: 20px;
}

.form_title {
  padding-left: 20px;
}

.gridButtonArea {
  margin-bottom: 10px;
}

.broker-code-input {
  width: 150px;
}
.broker-name-input {
  width: 280px;
}

.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 10px;
}

:deep(.el-form-item) {
  margin-bottom: 1.2rem;
  margin-right: 10px;
}
:deep(.form_label.w300) .el-form-item__content {
  width: 300px;
}
</style>
