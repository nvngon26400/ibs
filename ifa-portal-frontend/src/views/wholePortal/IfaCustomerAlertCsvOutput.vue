<template>
  <captionCard
    caption="顧客アラート"
    style="margin: 5px 10px 5px 10px;"
  >
    <div class="filter-container">
      <el-row>
        <el-col
          :span="13"
          class="area-border-color"
          style="padding: 8px 1.5em"
        >
          <span>
            ・各種アラートが発生している場合は必ず該当項目を押下して、アラートの解消を実施するようご対応をお願いいたします。<br>
            ・発生日は各種アラート発生後に未解決のアラートがある場合、該当アラートが発生した日付を表示しています。<br>
            ・各種アラートは、全て前営業日基準です。<br>
            ・リアルタイムの情報は、遷移先の詳細情報画面をご確認ください。
          </span>
        </el-col>
        <el-col
          v-if="shownPrivId.includes(medUsersPrivId)"
          :span="10"
        >
          <div class="csv-button">
            <ifa-button
              id="btnCsvDownload"
              text="CSV出力"
              color="primary"
              style="width: 105px;"
              action-type="originalAction"
              @app-action-handler="ifaCsvOutputDialogVisible = true"
            ></ifa-button>
          </div>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="7">
          <ifa-customer-alert-table
            :title="title = '国内担保不足・信用期日アラート'"
            :customer-alert-list="customerAlertList"
          ></ifa-customer-alert-table>
        </el-col>
        <el-col
          :span="7"
          :offset="1"
        >
          <ifa-customer-alert-table
            :title="title = '外国担保不足・信用期日アラート'"
            :customer-alert-list="customerAlertList"
          ></ifa-customer-alert-table>
        </el-col>
        <el-col
          :span="7"
          :offset="1"
        >
          <ifa-customer-alert-table
            :title="title = '投信'"
            :customer-alert-list="customerAlertList"
          ></ifa-customer-alert-table>
        </el-col>
      </el-row>

      <el-row style="margin-bottom: 5px;">
        <el-col :span="7">
          <ifa-customer-alert-table
            :title="title = '国内債券・外国債券'"
            :customer-alert-list="customerAlertList"
            @transition-a011="transitionA011"
          ></ifa-customer-alert-table>
        </el-col>
        <el-col
          :span="7"
          :offset="1"
        >
          <ifa-customer-alert-table
            :title="title = '入出金・入出庫'"
            :customer-alert-list="customerAlertList"
          ></ifa-customer-alert-table>
        </el-col>
      </el-row>
      <ifa-csv-output-dialog
        :is-visible="ifaCsvOutputDialogVisible"
        @close-modal="handleCloseModal"
      >
      </ifa-csv-output-dialog>
      <ifa-requester
        id="ifaWholePortalHomeA011"
        action-id="SUB01-01#A011"
        action-type="requestAction"
        :request-model="IfaWholePortalHomeA011RequestModel"
        @response-handler="responseHandlerA011($event)"
      ></ifa-requester>
    </div>
  </captionCard>

</template>

<script>
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard.vue'
import ifaCustomerAlertTable from './components/ifaCustomerAlertTable.vue'
import ifaCsvOutputDialog from './components/IfaCsvOutputDialog.vue'
import { IfaWholePortalHomeA011RequestModel } from './js/IfaWholePortalHomeA011RequestModel'
export default {
  components: {
    captionCard,
    ifaCustomerAlertTable,
    ifaCsvOutputDialog
  },
  props: {
    form: { type: Object, required: true },
    medUsersPrivId: { type: String, required: true }
  },
  data() {
    return {
      shownPrivId: ['1', '2', '3'],
      ifaCsvOutputDialogVisible: false,
      title: '',
      customerAlertList: [],
      infoForeign: [],
      infoTrust: [],
      infoBond: [],
      infoDeposit: [],
      selectedIndex: [],
      isVisible: false,
      a011RequestParam: {
        periodDesignationFrom: ''
      }
    }
  },
  computed: {
    IfaWholePortalHomeA011RequestModel() {
      return new IfaWholePortalHomeA011RequestModel(this.a011RequestParam)
    }
  },
  watch: {
    form: {
      handler: function() {
        this.customerAlertList = this.form.customerAlertList
      },
      deep: true
    }
  },
  methods: {
    handleCloseModal() {
      this.ifaCsvOutputDialogVisible = false
    },
    transitionA011(params) {
      // 日付文字列を Date オブジェクトに変換
      const dateString = this.$store.getters.requestedTime
      const date = new Date(dateString)

      // 年、月、日を取得し、フォーマットを整える
      const year = date.getFullYear()
      const month = ('0' + (date.getMonth() + 1)).slice(-2)
      const day = ('0' + date.getDate()).slice(-2)

      // yyyymmdd形式で出力
      this.a011RequestParam.periodDesignationFrom = `${year}${month}${day}`
      this.$nextTick(() => {
        document.getElementById('ifaWholePortalHomeA011').click()
      })
    },
    responseHandlerA011(response) {
      const designatedDate = response.dataList[0].designatedDate
      const periodDate = [this.$_getFormattedDateValue(this.a011RequestParam.periodDesignationFrom, 'date8'), this.$_getFormattedDateValue(designatedDate, 'date8')]
      // 利金・償還金支払予定一覧に遷移
      this.$_startShowMenu('SUB020302_0104', periodDate)
    }
  }
}

</script>
<style scoped>
  .notice-unchecked {
      color:red;
  }
  .msg-area{
    border: 2px solid;
    background-color: rgba(248, 248, 195, 0.7);;
  }
  :deep(.pq-grid-link) {
    color:#409EFF;
    text-decoration: underline;
    text-underline-offset:0.2em;
    cursor: pointer;
}
.hidden {
    display: none;
}
.area-border-color {
  border: solid 1px rgb(189, 189, 189);
}
.csv-button {
  display: flex;
  justify-content: flex-end;
  text-align: right;
  margin-top: 2rem;
}
.filter-container {
  margin:10px 1rem 3px 1rem;
  margin-right: 0;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
