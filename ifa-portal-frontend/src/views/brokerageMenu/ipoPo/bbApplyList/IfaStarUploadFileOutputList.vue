<template>
  <div class="star-upload-file-output-list__wrapper">
    <div class="main-dialog">
      <el-dialog
        :model-value="isVisible"
        :close-on-click-modal="false"
        :show-close="false"
        :title="form.title.name"
        :center="true"
        width="1400px"
        class=""
        :before-close="handleBackButton"
      >
        <!-- 戻るボタン (A003) -->
        <el-row>
          <ifa-button
            class="main-dialog__back-button"
            color="secondary"
            text="戻る"
            action-type="originalAction"
            @app-action-handler="handleBackButton"
          ></ifa-button>
        </el-row>

        <el-card
          text-size="20px"
          text-color="#0058a2"
          background-color="Menu"
        >

          <el-row class="main-dialog__grid-table">
            <grid-table :options="pqGridOption"></grid-table>
          </el-row>

          <!-- ダウンロードボタン (A005) -->
          <el-row>
            <ifa-button
              :disabled="disabled.downloadButton"
              text="ダウンロード"
              action-type="originalAction"
              @app-action-handler="handleDownloadButton"
            ></ifa-button>
          </el-row>

        </el-card>
      </el-dialog>
    </div>

    <!-- 確認ダイアログ -->
    <div class="confirm-dialog">
      <el-dialog
        v-model="dialogConfirmVisible"
        width="350px"
        :close-on-click-modal="false"
        :show-close="false"
        style="margin-top: 20vh;"
      >
        <span>募集入力済み{{ hidden.dataCnt }}件をダウンロードします。</span><br>
        <span>よろしいですか？</span>
        <template #footer>
          <span
            class="dialog-footer"
          >
            <!-- OKボタン (A002) -->
            <ifa-button
              small
              text="OK"
              action-type="originalAction"
              @app-action-handler="handleOkButton"
            ></ifa-button>
            <!-- キャンセルボタン (A004) -->
            <ifa-button
              small
              text="キャンセル"
              color="secondary"
              action-type="originalAction"
              @app-action-handler="handleCancelButton"
            ></ifa-button>
          </span>
        </template>
      </el-dialog>
    </div>

    <!-- 初期化 (A001) -->
    <ifa-requester
      id="IfaStarUploadFileOutputListA001"
      action-type="requestAction"
      action-id="SUB0204_02-05#A001"
      @response-handler="handleResponseA001"
    ></ifa-requester>

    <!-- CSVダウンロード (A002) -->
    <ifa-requester
      id="IfaStarUploadFileOutputListA002"
      action-type="outputCsvAction"
      action-id="SUB0204_02-05#A002"
      :request-model="ifaStarUploadFileOutputListA002RequestModel"
      csv-file-name="wkdcyup1256Z10"
      :is-csv-file-name-date-time="true"
      :is-check-csv-download-privacy-confirmation="false"
      @response-handler="handleResponseA002"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { notifyMessage, getMessage } from '@/utils/errorHandler.js'
import { IfaStarUploadFileOutputListA002RequestModel } from './js/IfaStarUploadFileOutputListA002RequestModel'
import { IfaStarUploadFileOutputListFormModel } from './js/IfaStarUploadFileOutputListFormModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  components: {
    GridTable
  },
  props: {
    isVisible: { type: Boolean, required: true }
  },
  emits: ['close-modal'],
  data() {
    return {
      form: new IfaStarUploadFileOutputListFormModel(),
      dialogConfirmVisible: false,
      pqGridOption: getConvertedOption(obj),
      disabled: {
        downloadButton: true
      },
      hidden: {
        sysDate: '',
        maxKey: '',
        minKey: '',
        dataCnt: ''
      }
    }
  },
  computed: {
    ifaStarUploadFileOutputListA002RequestModel() {
      return new IfaStarUploadFileOutputListA002RequestModel(this.hidden)
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  methods: {
    // 初期化 (A001)
    onShow() {
      this.$nextTick(() => {
        document.getElementById('IfaStarUploadFileOutputListA001').click()
      })
    },
    handleResponseA001(response) {
      this.pqGridOption.dataModel.data = [...response.dataList[0].fileOutputList.map(item => ({
        ...item,
        bbPrice: this.getGridcolBbPrice(item)
      }))]
      this.hidden.sysDate = response.dataList[0].sysDate
      this.hidden.minKey = response.dataList[0].minKey
      this.hidden.maxKey = response.dataList[0].maxKey
      this.hidden.dataCnt = response.dataList[0].dataCnt

      // ファイル出力リストが1件以上の場合、ダウンロードボタンを活性にする
      if ((this.hidden.dataCnt + 0) > 0) {
        this.disabled.downloadButton = false
      }
    },
    // OKボタン(A002)
    // CSVダウンロード
    handleOkButton() {
      this.dialogConfirmVisible = false
      document.getElementById('IfaStarUploadFileOutputListA002').click()
    },
    handleResponseA002(response) {
      notifyMessage(0, getMessage('info.dms.offering.downLoaded', [this.hidden.dataCnt]), 'STARアップロードファイル出力')
    },
    // 戻るボタン(A003)
    // STARアップロードファイル出力画面を非表示にする。
    handleBackButton() {
      this.$emit('close-modal')
    },
    // キャンセルボタン (A004)
    // ダウンロード確認ダイアログを閉じる
    handleCancelButton() {
      this.dialogConfirmVisible = false
    },
    // ダウンロードボタン (A005)
    // ダウンロード確認ダイアログを開く
    handleDownloadButton() {
      this.dialogConfirmVisible = true
    },
    getGridcolBbPrice(item) {
      return item.bbPrice === '成行'
        ? '成行'
        : !item.bbPrice.substring(0, item.bbPrice.length - 1)
          ? '-'
          : item.bbPrice.substring(item.bbPrice.length - 1) === '円'
            ? ifaFormatUtils.withCommaInteger(item.bbPrice.substring(0, item.bbPrice.length - 1)) + '円'
            : item.bbPrice
    }
  }
}
const obj = {
  title: 'STARアップロードファイル出力一覧'
}
obj.colModel = [
  {
    title: '銘柄コード',
    dataIndx: 'bbProductCode',
    width: 90,
    halign: 'center',
    align: 'left'
  },
  {
    title: '銘柄名',
    dataIndx: 'bbProductName',
    width: 180,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    width: 120,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    width: 180,
    halign: 'center',
    align: 'left'
  },
  {
    title: '支店コード',
    dataIndx: 'branchCode',
    width: 100,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'branchName',
    width: 200,
    halign: 'center',
    align: 'left'
  },
  {
    title: '営業員コード',
    dataIndx: 'intermediaryEmpCd',
    width: 110,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'brokerChargeName',
    width: 150,
    halign: 'center',
    align: 'left'
  },
  {
    title: '部店',
    dataIndx: 'butenCode',
    width: 60,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    width: 90,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名（漢字）',
    dataIndx: 'nameKanji',
    width: 150,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名（カナ）',
    dataIndx: 'nameKana',
    width: 150,
    halign: 'center',
    align: 'left'
  },
  {
    title: '投資家属性',
    dataIndx: 'bbInvestorAttName',
    width: 100,
    halign: 'center',
    align: 'left'
  },
  {
    title: 'BB申込株数',
    dataIndx: 'bbQuantity',
    width: 100,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.bbQuantity) || '-'
    }
  },
  {
    title: '申込価格',
    dataIndx: 'bbPrice',
    width: 120,
    halign: 'center',
    align: 'right'
  },
  {
    title: '裁量希望株数',
    dataIndx: 'quantitySairyou',
    width: 110,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.quantitySairyou) || '-'
    }
  },
  {
    title: '当選株数',
    dataIndx: 'bbQuantityAlloc',
    width: 90,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.bbQuantityAlloc) || '-'
    }
  },
  {
    title: '抽選結果',
    dataIndx: 'lotteryResult',
    width: 100,
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
    halign: 'center',
    align: 'left',
    codeValue: {
      codeListId: 'FACE_TO_FACE_SUBSCRIPT_ORDER_STATUS',
      dispPattern: 1
    }
  },
  {
    title: '注文株数',
    dataIndx: 'orderCount',
    width: 90,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.orderCount) || '-'
    }
  },
  {
    title: '預り区分',
    dataIndx: 'depositType',
    width: 100,
    halign: 'center',
    align: 'left',
    codeValue: {
      codeListId: 'FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE',
      dispPattern: 1
    }
  },
  {
    title: '申込日時',
    dataIndx: 'bbCreateDate',
    minWidth: 130,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return getFormattedDateTimeValue(ui.rowData.bbCreateDate, 'datetime12') || '-'
    }
  },
  {
    title: '申込者',
    dataIndx: 'bbCreateUserName',
    width: 150,
    halign: 'center',
    align: 'left'
  },
  {
    title: 'セクション名',
    dataIndx: 'bbCreateSectionName',
    width: 150,
    halign: 'center',
    align: 'left'
  },
  {
    title: '備考',
    dataIndx: 'bbRemark',
    width: 200,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      const rowData = ui.rowData
      let bbRemark = rowData.bbRemark
      if (bbRemark) {
        bbRemark = bbRemark.replace(/</g, '&lt;').replace(/>/g, '&gt;')
        bbRemark = bbRemark.replace(/[\r\n]/g, '<br>')
      }
      return bbRemark || '-'
    }
  }
]
// Define a page model for the table
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>

<style lang="scss" scoped>
.star-upload-file-output-list__wrapper {
  .main-dialog {
    &__back-button {
      display: flex;
      justify-content: flex-end;
      margin: -20px 0 20px auto;
    }
    &__grid-table {
      margin-bottom: 20px;
    }
  }
  .confirm-dialog {
    :deep(.el-dialog__header) {
      display: none;
    }
  }
}
</style>
