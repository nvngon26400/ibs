<template>
  <div class="wrapper">
    <caption-card caption="受発信状況照会">
      <el-form
        ref="form"
        class="form-wrapper"
        :model="searchForm"
        :form="formRef"
      >
        <el-row class="form-row">
          <el-form-item
            label="資料名:"
            style="margin-bottom: 0"
          >
            <ifa-input-select
              v-model="searchForm.paperCd"
              code-list-id="original"
              :original-list="options"
              placeholder="全て"
              @change="handleSelect"
            ></ifa-input-select>
          </el-form-item>
          <el-form-item style="margin-bottom: 0">
            <ifa-input-text
              id="keyword"
              v-model="searchForm.keyword"
              placeholder="キーワード"
              prop="keyword"
              original-screen-id="SUB0202_0703-01"
              :domain="IfaText200DomainModel"
              :disabled="!!searchForm.paperCd"
            >
            </ifa-input-text>
          </el-form-item>
          <ifa-button
            id="searchBtn"
            name="searchBtn"
            text="検索"
            search
            small
            :form="formRef"
            :disabled="!!searchForm.paperCd"
            action-type="requestAction"
            :request-model="A003RequestModel"
            action-id="SUB0202_0703-01#A003"
            @response-handler="responseA003Handler"
            @response-error-handler="responseA003ErrorHandler"
          ></ifa-button>
        </el-row>
        <el-row v-show="isShowCSV">
          <ifa-button
            id="btnCsvDownload"
            text="CSV出力"
            small
            :disabled="csvDisabled"
            color="secondary"
            :request-model="A004RequestModel"
            action-id="SUB0202_0703-01#A004"
            action-type="outputCsvAction"
            csv-file-name="受発信状況"
            :is-check-csv-download-allowed="true"
            :is-check-csv-download-privacy-confirmation="false"
          ></ifa-button>
        </el-row>
        <grid-table
          ref="pqGrid"
          :options="pqGridOption"
          :auto-refresh="false"
          @click="handleRow"
        ></grid-table>
      </el-form>
    </caption-card>
    <el-dialog
      v-if="!!requestNo"
      v-model="additionalInfoVisible"
      destroy-on-close
      center
      :close-on-click-modal="false"
      :width="1000"
      :show-close="false"
      :before-close="handleAdditionalInfoClose"
    >
      <template #header="{ close }">
        <div class="infoHeader">
          <ifa-button
            text="閉じる"
            color="secondary"
            action-type="originalAction"
            small
            @app-action-handler="close"
          ></ifa-button>
        </div>
      </template>
      <doc-request-add-info
        :request-no="requestNo"
        :paper-name="paperName"
        :detail-info="detailInfo"
      ></doc-request-add-info>
    </el-dialog>

    <!-- init query -->
    <ifa-requester
      id="ifaSendReceiveStatusLookupInitializeA001"
      action-id="SUB0202_0703-01#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      :pre-request-handler="handleReset"
      @response-handler="responseA001Handler($event)"
      @response-error-handler="responseA001ErrorHandler"
    ></ifa-requester>

    <!-- paperCd query -->
    <ifa-requester
      id="ifaSendReceiveStatusLookupDisplayA002"
      action-id="SUB0202_0703-01#A002"
      action-type="requestAction"
      :request-model="A002RequestModel"
      @response-handler="responseA002Handler($event)"
      @response-error-handler="responseA002ErrorHandler"
    ></ifa-requester>

    <!-- keyword query -->
    <ifa-requester
      id="ifaSendReceiveStatusLookupDisplayA003"
      action-id="SUB0202_0703-01#A003"
      action-type="requestAction"
      :request-model="A003RequestModel"
      @response-handler="responseA003Handler($event)"
      @response-error-handler="responseA003ErrorHandler"
    ></ifa-requester>

    <!-- detail query -->
    <ifa-requester
      id="ifaSendReceiveStatusLookupSelectDocRequestAddInfoA005"
      action-id="SUB0202_0703-02#A005"
      action-type="requestAction"
      :request-model="A005RequestModel"
      @response-handler="detailResponseA005Handler($event)"
      @response-error-handler="detailResponseA005ErrorHandler($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { setupColModel } from '@/utils/pqgridHelper'
import GridTable from '@/components/GridTable/index.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import IfaButton from '@/components/Button/IfaButton.vue'
import CaptionCard from '@/views/brokerageMenu/customerMenu/components/captionCard.vue'
import IfaUtils from '@/utils/ifaUtils'
import IfaInputSelect from '@/components/Input/IfaInputSelect.vue'
import IfaInputText from '@/components/Input/IfaInputText.vue'
import IfaText200DomainModel from '@/resource/domain/IfaText200DomainModel.json'
import store from '@/store'

import DocRequestAddInfo from './DocRequestAddInfo.vue'
import { IfaSendReceiveStatusLookupFormModel } from './js/IfaSendReceiveStatusLookupFormModel'
import { IfaSendReceiveStatusLookupA001RequestModel } from './js/IfaSendReceiveStatusLookupA001RequestModel'
import { IfaSendReceiveStatusLookupA002RequestModel } from './js/IfaSendReceiveStatusLookupA002RequestModel'
import { IfaSendReceiveStatusLookupA003RequestModel } from './js/IfaSendReceiveStatusLookupA003RequestModel'
import { IfaSendReceiveStatusLookupA004RequestModel } from './js/IfaSendReceiveStatusLookupA004RequestModel'
import { IfaSendReceiveStatusLookupA005RequestModel } from './js/IfaSendReceiveStatusLookupA005RequestModel'

export default {
  components: {
    GridTable,
    IfaRequester,
    DocRequestAddInfo,
    IfaButton,
    IfaInputText,
    IfaInputSelect,
    CaptionCard
  },
  data() {
    return {
      options: [{ key: '', value: '' }],
      searchForm: new IfaSendReceiveStatusLookupFormModel(),
      IfaText200DomainModel,
      pqGridOption: null,
      additionalInfoVisible: false,
      requestNo: '',
      paperName: '',
      detailInfo: {}
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaSendReceiveStatusLookupA001RequestModel()
    },
    A002RequestModel() {
      return new IfaSendReceiveStatusLookupA002RequestModel(this.searchForm)
    },
    A003RequestModel() {
      return new IfaSendReceiveStatusLookupA003RequestModel(this.searchForm)
    },
    A004RequestModel() {
      return new IfaSendReceiveStatusLookupA004RequestModel(this.searchForm)
    },
    A005RequestModel() {
      return new IfaSendReceiveStatusLookupA005RequestModel(this.requestNo)
    },
    isShowCSV() {
      const { privId } = this.$store.getters.userAccount.medUsers
      return privId === '1' || privId === '2' || privId === '3'
    },
    csvDisabled() {
      return !this.pqGridOption?.dataModel?.data || this.pqGridOption.dataModel?.data?.length === 0
    }
  },
  created() {
    this.calcGridTableMaxHeight()
  },
  methods: {
    onShow() {
      this.formRef = this.$refs?.form
      this.handleReset()

      this.$nextTick(() => {
        document.getElementById('ifaSendReceiveStatusLookupInitializeA001').click()
      })

      this.calcGridTableMaxHeight()
    },
    onHide() {
      this.handleReset()
    },
    handleReset() {
      this.searchForm.paperCd = ''
      this.searchForm.keyword = ''
      this.formRef?.clearValidate()
      this.options = [{ key: '', value: '' }]
      this.pqGridOption.dataModel.data = []
      this.$refs.pqGrid.refreshView(true)
    },
    handleSelect(_val) {
      this.searchForm.keyword = ''
      this.formRef?.clearValidate()

      this.$nextTick(() => {
        document.getElementById('ifaSendReceiveStatusLookupDisplayA002').click()
      })
    },
    handleRow(row) {
      if (row.dataIndx !== 'requestNo') return
      this.requestNo = row.rowData.requestNo
      this.paperName = row.rowData.paperName

      this.$nextTick(() => {
        document.getElementById('ifaSendReceiveStatusLookupSelectDocRequestAddInfoA005').click()
      })
    },
    handleAdditionalInfoClose() {
      this.additionalInfoVisible = false
      this.requestNo = ''
      this.paperName = ''
      this.detailInfo = {}
    },

    responseA001Handler(res) {
      this.$_logDebug(res)

      const paperNameList = res?.dataList && res?.dataList.length > 0
        ? res?.dataList?.[0]?.paperNameList.map(v => ({
          key: v.paperCd,
          value: v.paperName
        }))
        : []
      const sendReceiveStatusLookupList = res?.dataList && res?.dataList.length > 0 ? res?.dataList[0].sendReceiveStatusLookupList : []

      this.pqGridOption.dataModel.data = sendReceiveStatusLookupList
      this.options = [{ key: '', value: '' }, ...paperNameList]

      this.$refs['pqGrid'].refreshView(true)
    },
    responseA001ErrorHandler(error) {
      this.$_logError(error)
    },

    responseA002Handler(res) {
      this.$_logDebug(res)
      const sendReceiveStatusLookupList = res?.dataList && res?.dataList.length > 0 ? res?.dataList[0].sendReceiveStatusLookupList : []

      this.pqGridOption.dataModel.data = sendReceiveStatusLookupList
      this.$refs['pqGrid'].refreshView(true)
    },
    responseA002ErrorHandler(error) {
      this.$_logError(error)
    },

    responseA003Handler(res) {
      this.$_logDebug(res)
      const sendReceiveStatusLookupList = res?.dataList && res?.dataList.length > 0 ? res?.dataList[0].sendReceiveStatusLookupList : []

      this.pqGridOption.dataModel.data = sendReceiveStatusLookupList
      this.$refs['pqGrid'].refreshView(true)
    },
    responseA003ErrorHandler(error) {
      this.$_logError(error)
    },

    detailResponseA005Handler(res) {
      this.detailInfo = res?.dataList?.[0] ?? {}
      this.additionalInfoVisible = true
    },
    detailResponseA005ErrorHandler(error) {
      this.$_logError(error)
    },
    getTableOptions(maxHeight) {
      return {
        showTop: false,
        reactive: true,
        locale: 'en',
        height: 'flex',
        numberCell: {
          show: false
        },
        columnTemplate: { width: 100 },
        pageModel: {
          type: 'local',
          rPP: 30,
          rPPOptions: store.getters.rPPOptions,
          layout: ['strDisplay', '|', 'prev', 'next']
        },
        colModel: setupColModel(columns),
        dataModel: {
          data: []
        },
        wrap: false,
        maxHeight,
        selectionModel: { type: 'row', mode: 'single' },
        editable: true,
        fillHandle: ''
      }
    },
    calcGridTableMaxHeight() {
      // dynamic calc the pgGrid Table's maxHeight
      const customerInfoCardEl = document.getElementById('customerInfoCard')

      const clientHeight = document.body.offsetHeight
      const customerCardHeight = customerInfoCardEl.offsetHeight
      const restPartOfCard = 327

      let maxHeight = clientHeight - customerCardHeight - restPartOfCard

      const minHeight = 387

      if (maxHeight < minHeight) {
        maxHeight = minHeight
      }

      this.pqGridOption = this.getTableOptions(maxHeight)
    }
  }
}

const columns = [
  {
    title: '資料No.',
    dataIndx: 'requestNo',
    minWidth: 100,
    render: (item) => (
      `<span id="requestNo" style="color: #092987; cursor: pointer">
        ${item.rowData.requestNo ?? '-'}
      </span>
      <style>
        #requestNo {
          &:focus, &:hover {
            opacity: 0.7;
          }
        }
      </style>`
    ),
    editable: false,
    halign: 'center',
    align: 'right'
  },
  {
    title: '受発送日',
    dataIndx: 'changeStatusDate',
    minWidth: 100,
    dataType: (val1, val2) => this.$_nullSorting(val1, val2),
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '受発信状況',
    dataIndx: 'st',
    minWidth: 150,
    dataType: (val1, val2) => this.$_nullSorting(val1, val2),
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '資料名',
    dataIndx: 'paperName',
    minWidth: 300,
    dataType: (val1, val2) => this.$_nullSorting(val1, val2),
    editable: false,
    halign: 'center',
    align: 'left',
    render: ({ rowData: { paperName }}) => {
      return `<span style="white-space: pre-wrap">${paperName || '-'}</span>`
    }
  },
  {
    title: '内容・備考',
    dataIndx: 'combinedContent',
    minWidth: 500,
    editable: false,
    halign: 'center',
    align: 'left',
    /*
    * 1. 目論見書番号≠null､かつ目論見書番号が"T       "の場合、'<font color="red">[かんたん口座開設経由] </font>'を表示
    * 2. 内容≠nullなら、内容 + ' ' を追加表示
    * 3. 備考≠nullなら、備考+ ' ' を追加表示
    * 4. 理由名称≠nullなら、理由名称 + ' ' を追加表示
    * 5. 目論見書番号≠null、かつ目論見書番号が"T       "または""でない場合、"目論見書No." + 目論見書番号を追加表示
    */
    render: (item) => {
      const SUFFIX = 'T       '
      const { prospectusNo, contents, remarks, purposeName } = item.rowData
      let text = ''

      if (prospectusNo && prospectusNo.endsWith(SUFFIX)) {
        text = `<font style="color: red">[かんたん口座開設経由]${' '}</font>`
      }
      if (contents) {
        text += `${contents}${' '}`
      }
      if (remarks) {
        text += `${remarks}${' '}`
      }
      if (purposeName) {
        text += `${purposeName}${' '}`
      }
      if (prospectusNo && !prospectusNo.endsWith(SUFFIX)) {
        text += `目論見書No.${prospectusNo}`
      }
      return {
        cls: 'combined_content_cell',
        text: `<span style="white-space: pre-wrap">${text || '-'}</span>`
      }
    }
  },
  {
    minWidth: 80,
    title: 'prospectusNo',
    dataIndx: 'prospectusNo',
    hidden: true
  },
  {
    minWidth: 80,
    title: 'contents',
    dataIndx: 'contents',
    hidden: true
  },
  {
    minWidth: 80,
    title: 'remarks',
    dataIndx: 'remarks',
    hidden: true
  },
  {
    title: '',
    minWidth: 80,
    purposeName: 'purposeName',
    dataIndx: 'purposeName',
    hidden: true
  },
  {
    title: '通数',
    dataIndx: 'copies',
    minWidth: 80,
    dataType: (val1, val2) => this.$_nullSorting(val1, val2),
    editable: false,
    halign: 'center',
    align: 'right',
    render: ({ rowData: { copies }}) => copies ? IfaUtils.addComma(copies) : '-'
  },
  {
    title: '請求日時',
    dataIndx: 'requestDate',
    minWidth: 150,
    dataType: (val1, val2) => this.$_nullSorting(val1, val2),
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '取扱者',
    dataIndx: 'agentName',
    minWidth: 150,
    dataType: (val1, val2) => this.$_nullSorting(val1, val2),
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '郵便記録',
    dataIndx: 'sendMail',
    minWidth: 180,
    dataType: (val1, val2) => this.$_nullSorting(val1, val2),
    editable: false,
    halign: 'center',
    align: 'left'
  }
]
</script>

<style lang="scss" scoped>
.wrapper {
  display: flex;
  flex-direction: column;
  row-gap: 15px;
  padding: 10px;

  .box-card {
    margin: 0;
  }
  .caption-container {
    margin-left: 0;
    margin-top: 0;
  }
  .form-wrapper {
    display: flex;
    flex-direction: column;
    row-gap: 10px;
    padding: 15px 20px;

    .padding {
      padding-left: 0px;
      padding-right: 0px;
    }

    .form-row {
      display: flex;
      align-items: flex-start;
      column-gap: 20px;
    }
  }
  .infoHeader {
    display: flex;
    align-items: center;
    justify-content: flex-end;
  }
}

:deep(.el-card__body) {
  padding: 0;
}

:deep(.combined_content_cell) {
  & > span > a {
    color: #0000EE;
    text-decoration: underline;
  }
}
</style>
