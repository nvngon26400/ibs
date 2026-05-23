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
          <!-- ここに検索条件用のコンテンツを記述する -->
          <div class="filter-container">
            <!-- ステータス -->
            <el-row>
              <div>
                <ifa-input-radio
                  v-model="form.status"
                  label="ステータス"
                  :code-list-id="'SUG_BOX_STATUS'"
                  :disp-pattern="1"
                  :select-pattern="1"
                  :domain="IfaClassRadioDomainModel"
                  :required="true"
                  prop="status"
                ></ifa-input-radio>
              </div>
              <el-col
                :span="1"
                class="right_icon"
              ><!-- 画面コメント -->
                <el-col>
                  <ifa-balloon-icon
                    v-if="checkSugBoxCommonComment"
                    :key="iconKey"
                    icon-type="info"
                    style="float:right;"
                    :message="form.screenComment"
                  ></ifa-balloon-icon>
                 </el-col>
              </el-col>
            </el-row>
            <!-- 登録日 -->
            <el-row>
              <el-form-item
                class="registerDate_label"
              >
                <ifa-date-range-picker
                  v-model="form.registerDate"
                  unlink-panels
                  label="登録日"
                  :picker-options="pickerOptions"
                  size="small"
                  style="margin-left: 70px;"
                  required
                  prop="registerDate"
                ></ifa-date-range-picker>
              </el-form-item>
            </el-row>
            <!-- 検索キーワード -->
            <el-row class="form_title">
              <ifa-input-text
                id="title"
                v-model="form.title"
                prop="title"
                size="small"
                style="width: 742px; padding-left: 20px;"
                prefix-icon="Search"
                placeholder="タイトルを入力"
                original-screen-id="SUB0501_01-01"
                :domain="IfaText127DomainModel"
                @keydown.enter="displayA002"
              ></ifa-input-text>
            </el-row>
            <!-- 表示ボタン -->
            <el-row class="form_button">
              <ifa-button
                id="btnDisplay"
                icon="Search"
                text="表示"
                small
                width="90"
                :request-model="IfaSuggestionBoxCommonA002ApiRequest"
                :form="formRef"
                :action-id="getDisplayActionId"
                action-type="requestAction"
                @response-handler="displayA002($event)"
              ></ifa-button>
            </el-row>
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
              <!-- CSV出力ボタン -->
              <ifa-button
                v-if="chkBtnRegistNewSuggestCommonIsVisible"
                id="btnCsvDownload"
                name="btnCsvDownload"
                text="CSV出力"
                width="90"
                :disabled="disabledCsv"
                small
                action-type="outputCsvAction"
                :request-model="IfaSuggestionBoxCommonA006ApiRequest"
                :form="formRef"
                action-id="SUB00_02-06_1#A006"
                csv-file-name="皆様からの要望"
                :is-check-csv-download-privacy-confirmation="false"
              ></ifa-button>
              <!-- 新規登録ボタン -->
              <ifa-button
                v-if="chkBtnRegistNewSuggestCommonIsVisible"
                id="btnRegisterInfo"
                text="新規登録"
                width="90"
                small
                action-type="originalAction"
                @app-action-handler="registerA003ResponseHandler(true)"
              ></ifa-button>
            </div>
          </el-row>
          <!-- 明細（皆様からの要望一覧） -->
          <grid-table
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>
    <!-- 初期処理 -->
    <ifa-requester
      id="initializeA001"
      :action-id="getInitializeActionId"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxCommonA001ApiRequest"
      @response-handler="initializeA001($event)"
    ></ifa-requester>
    <!-- 皆様からの要望新規登録 -->
    <ifa-requester
      id="IfaSuggestionBoxCommonRegisterInitializeA001"
      action-type="originalAction"
      @app-action-handler="registerA003ResponseHandler(true)"
    ></ifa-requester>
    <ifa-suggestion-box-common-register
      ref="ifaSuggestionBoxCommonRegister"
      :is-visible="ifaSuggestionBoxCommonRegisterVisible"
      @update-table="handleUpdateTable"
      @close-modal="ifaSuggestionBoxCommonRegisterVisible = false"
    ></ifa-suggestion-box-common-register>

    <!-- 皆様からの要望詳細 -->
    <ifa-requester
      id="IfaSuggestionBoxCommonDetailInitializeA001"
      action-id="SUB00_02-06_2#A001"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxCommonDetailA001RequestModel"
      @response-handler="detailA004ResponseHandler($event)"
    ></ifa-requester>
    <ifa-suggestion-box-common-detail
      ref="ifaSuggestionBoxCommonDetail"
      :is-visible="ifaSuggestionBoxCommonDetailVisible"
      :form-data="ifaSuggestionBoxCommonDetailData"
      @close-modal="ifaSuggestionBoxCommonDetailVisible = false"
    ></ifa-suggestion-box-common-detail>

    <!-- 皆様からの要望更新 -->
    <ifa-requester
      id="IfaSuggestionBoxCommonUpdateInitializeA001"
      action-id="SUB0511_02-03#A001"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxCommonUpdateA001RequestModel"
      @response-handler="updateA005ResponseHandler($event)"
    ></ifa-requester>
    <!-- SUB0511_02-03_皆様からの要望更新 -->
    <ifa-suggestion-box-common-update
      ref="ifaSuggestionBoxCommonUpdate"
      :is-visible="ifaSuggestionBoxCommonUpdateVisible"
      :form-data="ifaSuggestionBoxCommonUpdateData"
      @update-table="handleUpdateTable"
      @close-modal="ifaSuggestionBoxCommonUpdateVisible = false"
    ></ifa-suggestion-box-common-update>
    <!-- 更新・新規登録後再表示 -->
    <ifa-requester
      id="reDisplayA002"
      :action-id="getDisplayActionId"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxCommonA002ApiRequest"
      @response-handler="displayA002($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaSuggestionBoxCommonDetail from './IfaSuggestionBoxCommonDetail'
import IfaSuggestionBoxCommonUpdate from './IfaSuggestionBoxCommonUpdate'
import IfaSuggestionBoxCommonRegister from './IfaSuggestionBoxCommonRegister'
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaSuggestionBoxCommonA001ApiRequest } from './js/IfaSuggestionBoxCommonA001ApiRequest'
import { IfaSuggestionBoxCommonA002ApiRequest } from './js/IfaSuggestionBoxCommonA002ApiRequest'
import { IfaSuggestionBoxCommonA006ApiRequest } from './js/IfaSuggestionBoxCommonA006ApiRequest'
import { IfaSugBoxCommonFormModel } from './js/IfaSugBoxCommonFormModel'
import { IfaSuggestionBoxCommonDetailA001RequestModel } from './js/IfaSuggestionBoxCommonDetailA001RequestModel'
import { IfaSuggestionBoxCommonUpdateA001RequestModel } from './js/IfaSuggestionBoxCommonUpdateA001RequestModel'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'

export default {
  components: {
    GridTable,
    screenTitle,
    IfaSuggestionBoxCommonUpdate,
    IfaSuggestionBoxCommonDetail,
    IfaSuggestionBoxCommonRegister
  },
  data() {
    return {
      IfaText127DomainModel: IfaText127DomainModel,
      form: new IfaSugBoxCommonFormModel(),
      formRef: {},
      pqGridOption: getConvertedOption(obj),
      disabledCsv: true,
      selectedSbcNo: '',
      pickerOptions: {
        shortcuts: [
          {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date()
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
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
      ifaSuggestionBoxCommonUpdateVisible: false, // SUB0511_02-03_皆様からの要望更新 表示フラグ
      ifaSuggestionBoxCommonUpdateData: {}, // SUB0511_02-03_皆様からの要望更新 へ受け渡すデータ

      ifaSuggestionBoxCommonDetailVisible: false, // SUB0_02-06_2_皆様からの要望詳細 表示フラグ
      ifaSuggestionBoxCommonDetailData: {}, // SUB0_02-06_2_皆様からの要望詳細 へ受け渡すデータ

      ifaSuggestionBoxCommonRegisterVisible: false // SUB0511_02-02_皆様からの要望 新規登録 表示フラグ
    }
  },
  computed: {
    checkSugBoxCommonComment() {
      if (this.form.screenComment) {
        return this.form.screenComment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
    },
    getDisplayActionId() {
      if (this.$store.getters.userAccount.medUsers.privId === '1') {
        return 'SUB0511_02-01#A002'
      } else {
        return 'SUB00_02-06_1#A002'
      }
    },
    chkBtnRegistNewSuggestCommonIsVisible() {
      if (this.$store.getters.userAccount.medUsers.privId === '1') {
        return true
      }
      return false
    },
    getInitializeActionId() {
      if (this.$store.getters.userAccount.medUsers.privId === '1') {
        return 'SUB0511_02-01#A001'
      } else {
        return 'SUB00_02-06_1#A001'
      }
    },
    IfaSuggestionBoxCommonA001ApiRequest() {
      return new IfaSuggestionBoxCommonA001ApiRequest(this.form)
    },
    IfaSuggestionBoxCommonA002ApiRequest() {
      return new IfaSuggestionBoxCommonA002ApiRequest(this.form)
    },
    IfaSuggestionBoxCommonA006ApiRequest() {
      return new IfaSuggestionBoxCommonA006ApiRequest(this.form)
    },
    IfaSuggestionBoxCommonDetailA001RequestModel() {
      return new IfaSuggestionBoxCommonDetailA001RequestModel({ sbcNo: this.selectedSbcNo })
    },
    IfaSuggestionBoxCommonUpdateA001RequestModel() {
      return new IfaSuggestionBoxCommonUpdateA001RequestModel({ sbcNo: this.selectedSbcNo })
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
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  mounted() {
  },
  methods: {
    onShow() {
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      this.$nextTick(() => {
        document.getElementById('initializeA001').click()
      })
      this.formRef = this.$refs.form
      const endDate = new Date(this.$store.getters.requestedTime)
      const startDate = new Date(this.$store.getters.requestedTime)
      startDate.setMonth(startDate.getMonth() - 1)
      this.form.registerDate = [this.formatDate(startDate), this.formatDate(endDate)]
    },
    // 表示ボタンの処理
    initializeA001(response) {
      // 一覧へのデータの反映
      if (response.dataList.length > 0 && response.dataList[0].suggestionList.length > 0) {
        this.form.gridTableList = response.dataList[0].suggestionList
        this.pqGridOption.dataModel.data = this.form.gridTableList
      } else {
        this.pqGridOption.dataModel.data = []
      }

      // 画面コメントの反映
      this.form.screenComment = response.dataList[0].sugBoxCommonScreenComment
      this.$refs['gridTable'].refreshView(true)
    },
    displayA002(response) {
      // 一覧へのデータの反映
      if (response.dataList.length > 0) {
        this.form.gridTableList = response.dataList[0].suggestionList
        this.pqGridOption.dataModel.data = this.form.gridTableList
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['gridTable'].refreshView(true)
    },
    errorHandlerA003(response) {
      if (response.errorLevel === -1) {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView()
      }
    },
    registerA003ResponseHandler(response) {
      this.ifaSuggestionBoxCommonRegisterVisible = true
    },
    detailA004ResponseHandler(response) {
      // 更新モーダルが開く
      this.ifaSuggestionBoxCommonDetailData = response.dataList[0]
      this.ifaSuggestionBoxCommonDetailVisible = true
    },
    updateA005ResponseHandler(response) {
      this.ifaSuggestionBoxCommonUpdateData = response.dataList[0]
      // 更新モーダルが開く
      this.ifaSuggestionBoxCommonUpdateVisible = true
    },
    // タイトル選択
    handleClick(ui) {
      if (ui.dataIndx === 'title') {
        if (this.$store.getters.userAccount.medUsers.privId === '1') {
          this.selectedSbcNo = ui.rowData.sbcNo
          this.$nextTick(() => {
            document.getElementById('IfaSuggestionBoxCommonUpdateInitializeA001').click()
          })
        } else {
          this.$nextTick(() => {
            this.selectedSbcNo = ui.rowData.sbcNo
            document.getElementById('IfaSuggestionBoxCommonDetailInitializeA001').click()
          })
        }
      }
    },
    // 日付を文字列に変換する
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
      document.getElementById('reDisplayA002').click()
      this.$refs['gridTable'].refreshView()
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '皆様からの要望一覧',
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
    title: '要望No',
    dataType: 'string',
    editable: false,
    dataIndx: 'sbcNo',
    width: '100',
    halign: 'center',
    align: 'center'
  },
  {
    title: '更新日',
    dataType: 'string',
    editable: false,
    dataIndx: 'updateDate',
    width: '150',
    halign: 'center',
    align: 'center'
  },
  {
    title: '登録日',
    dataType: 'string',
    editable: false,
    dataIndx: 'createDate',
    width: '150',
    halign: 'center',
    align: 'center'
  },
  {
    title: 'タイトル',
    dataType: 'string',
    editable: false,
    dataIndx: 'title',
    width: '770',
    halign: 'center',
    align: 'left',
    render: function(ui) {
      const rowData = ui.rowData
      const title = rowData.title
      return changeColorBorderBottom(title)
    }
  },
  {
    title: 'カテゴリ',
    dataType: 'string',
    editable: false,
    dataIndx: 'category',
    width: '250',
    halign: 'center',
    align: 'center',
    codeValue: { codeListId: 'SUG_BOX_CATEGORY', dispPattern: 1 }
  },
  {
    title: 'ステータス',
    dataType: 'string',
    editable: false,
    dataIndx: 'status',
    width: '150',
    halign: 'center',
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
.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 10px;
}
._page_caption {
  height: auto;
  padding-bottom: 3px;
  padding-top: 4px;
  word-break: break-all;
}
.filter-container {
  margin-top: 1rem;
}

.content-card {
  margin: 0.5rem 1rem;
}

.form_button {
  padding-left: 20px;
}
:deep(.el-form-item) {
  margin-bottom: 1.2rem;
  margin-right: 10px;
}
.registerDate_label {
  margin-bottom: 0;
}
</style>
