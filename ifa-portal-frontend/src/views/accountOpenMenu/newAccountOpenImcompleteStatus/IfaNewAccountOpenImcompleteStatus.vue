<template>
  <!-- 画面名：新規口座開設不備状況 -->
  <div
    style="width: 100%; overflow-x: auto;"
  >
    <screen-title :text="form.title.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <div>
        <el-row>
          <el-card class="content-card"
                   style="min-width: 620px;"
          >
            <div class="filter-container">
              <el-row>
                <el-col :span="23">
                  <ifa-common-search
                    ref="commonSearchItem"
                    display-pattern="pt7"
                    :form="form"
                    @mediate-user-privacy="mediateUserPrivacy"
                  ></ifa-common-search>
                  <!-- 発送予定日 /-->
                  <el-form-item
                    label="発送予定日"
                    class="form_label"
                    prop="dispatchScheduleDateRange"
                  >
                    <ifa-date-range-picker
                      v-model="form.dispatchScheduleDateRange"
                      size="small"
                      :picker-options="pickerOptions"
                      style="position: relative;"
                      @update:model-value="setCommonSearchValue($event)"
                    ></ifa-date-range-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- 検索用ボタン -->
              <el-row style="padding-left: 20px; padding-top: 10px">
                <ifa-button
                  id="btnDisplay"
                  name="btnDisplay"
                  text="表示"
                  width="90"
                  search
                  small
                  :form="formRef"
                  :request-model="IfaNewAccountOpenImcompleteStatusA002RequestModel"
                  action-id="SUB020305-01#A002"
                  action-type="requestAction"
                  :pre-request-handler="preRequestHandlerA002"
                  @response-handler="responseHandlerA002($event)"
                ></ifa-button>
              </el-row>
            </div>
          </el-card>
        </el-row>
        <el-row>
          <el-card class="content-card">
            <grid-table
              ref="gridTable"
              :options="pqGridOption"
              :auto-refresh="false"
            ></grid-table>
          </el-card>
        </el-row>
      </div>
    </el-form>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaNewAccountOpenImcompleteStatusA002RequestModel } from './js/IfaNewAccountOpenImcompleteStatusA002RequestModel'
import { IfaNewAccountOpenImcompleteStatusFormModel } from './js/IfaNewAccountOpenImcompleteStatusFormModel'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaDateRangePicker from '@/components/Date/IfaDateRangePicker.vue'
import { getMessage } from '@/utils/errorHandler'
import { getFormattedDateValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle,
    IfaDateRangePicker
  },
  data() {
    return {
      form: new IfaNewAccountOpenImcompleteStatusFormModel(),
      pqGridOption: getConvertedOption(obj),
      formRef: {},
      rules: {
        dispatchScheduleDateRange: [{ required: true, type: 'array', trigger: 'change', validator: this.dispatchScheduleDateRangeValidator }]
      },
      pickerOptions: {
        shortcuts: [
          {
            text: '当月',
            value: () => {
              const startDate = new Date(getFormattedDateValue(this.$store.getters.requestedTime))
              startDate.setDate(0)
              startDate.setDate(startDate.getDate() + 1)
              const endDate = new Date(getFormattedDateValue(this.$store.getters.requestedTime))
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '1ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(getFormattedDateValue(this.$store.getters.requestedTime)), 1)
              const endDate = new Date(getFormattedDateValue(this.$store.getters.requestedTime))
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '3ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(getFormattedDateValue(this.$store.getters.requestedTime)), 3)
              const endDate = new Date(getFormattedDateValue(this.$store.getters.requestedTime))
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '6ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(getFormattedDateValue(this.$store.getters.requestedTime)), 6)
              const endDate = new Date(getFormattedDateValue(this.$store.getters.requestedTime))
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '9ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(getFormattedDateValue(this.$store.getters.requestedTime)), 9)
              const endDate = new Date(getFormattedDateValue(this.$store.getters.requestedTime))
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '1年前',
            value: () => {
              const startDate = monthsBefore(new Date(getFormattedDateValue(this.$store.getters.requestedTime)), 12)
              const endDate = new Date(getFormattedDateValue(this.$store.getters.requestedTime))
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }
        ]
      }
    }
  },
  computed: {
    IfaNewAccountOpenImcompleteStatusA002RequestModel() {
      return new IfaNewAccountOpenImcompleteStatusA002RequestModel(
        this.form
      )
    }
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
    this.pqGridOption.wrap = true
  },
  mounted() {
    this.formRef = this.$refs.form
    // 発送予定日From,Toの初期値設定
    const date = new Date(getFormattedDateValue(this.$store.getters.requestedTime))
    const dateTo = this.formatDate(date)
    date.setDate(date.getDate() - 30)
    const dateFrom = this.formatDate(date)
    this.form.dispatchScheduleDateRange = [dateFrom, dateTo]
    this.form.dispatchScheduleDateFrom = dateFrom
    this.form.dispatchScheduleDateTo = dateTo
  },
  methods: {
    dispatchScheduleDateRangeValidator(rule, value, callback) {
      if (this.form.dispatchScheduleDateRange.length === 0) {
        callback('期間を指定してください｡')
        return
      }
      const dateFrom = new Date(value[0]) // 開始日の日付をパース
      const dateTo = new Date(value[1]) // 終了日の日付をパース
      // 形式のチェック
      const dateRegex = /^\d{4}\/\d{2}\/\d{2}$/
      if (!dateRegex.test(value[0])) {
        callback(new Error(getMessage('errors.date.specifyFormat', ['発送予定日From', 'YYYY/MM/DD'])))
        return
      }
      if (!dateRegex.test(value[1])) {
        callback(new Error(getMessage('errors.date.specifyFormat', ['発送予定日To', 'YYYY/MM/DD'])))
        return
      }
      // FromToチェック
      if (value[0] && value[1]) {
        if (dateTo < dateFrom) {
          callback(new Error(getMessage('errors.date.specifyFromTo', ['発送予定日'])))
        }
      }
      // OK
      callback()
    },
    mediateUserPrivacy(data) {
      if (data.empCodeAutoDispFlag === '1') {
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
        this.setHidden('empCode', true)
        this.setHidden('brokerChargeName', true)
      } else {
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('empCode', false)
        this.setHidden('brokerChargeName', false)
      }
      this.$refs['gridTable'].refreshView(true)
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    onShow() {
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    preRequestHandlerA002: function() {
      this.$_logDebug('preRequestHandlerA002-OK')
    },
    setCommonSearchValue: function(data) {
      this.form.dispatchScheduleDateFrom = this.form.dispatchScheduleDateRange[0]
      this.form.dispatchScheduleDateTo = this.form.dispatchScheduleDateRange[1]
    },
    responseHandlerA002: function(data) {
      if (data.dataList.length > 0) {
        // 一覧へのデータの反映
        this.pqGridOption.dataModel.data = data.dataList[0].newAccountOpenImcompleteStatusList
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['gridTable'].refreshView(true)
    },
    // 日付を文字列に変換する
    formatDate(date, f = false) {
      date = date || new Date()
      return date.getFullYear() + '/' +
        (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
        ('0' + date.getDate()).slice(-2) +
        (f ? ' ' + ('0' + date.getHours()).slice(-2) + ':' +
        ('0' + date.getMinutes()).slice(-2) : '')
    }
  }
}
const obj = {
  width: 0,
  height: 0,
  title: '新規口座開設不備状況一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' }
}

obj.colModel = [
  {
    title: '仲介業者コード',
    width: 150,
    minWidth: 110,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    dataIndx: 'brokerCode',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    minWidth: 200,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    dataIndx: 'brokerName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '営業員コード',
    width: 150,
    minWidth: 100,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    dataIndx: 'empCode',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    minWidth: 200,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    dataIndx: 'brokerChargeName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '書類名',
    width: 300,
    minWidth: 100,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    dataIndx: 'documentName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '内容',
    width: 900,
    minWidth: 400,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    dataIndx: 'contents',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '発送予定日',
    width: 120,
    minWidth: 90,
    maxWidth: 250,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    render: function(ui) {
      return getFormattedDateValue(ui.rowData.shippingScheduleDate) || '-'
    },
    dataIndx: 'shippingScheduleDate',
    editable: false,
    halign: 'center',
    align: 'left'
  }
]
obj.pageModel = {
  type: 'local',
  rPP: 30,
  rPPOptions: [30, 50, 100, 200, 500],
  layout: ['strDisplay', '|', 'prev', 'next']
}
</script>

<style scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
}
.form_button {
  padding: 0.4rem 2rem 1.2rem 2rem;
}
.content-card {
  margin: 0.5rem 1rem;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
}
:deep(.form_upper_label) .el-form-item__label {
  width: 135px;
}
:deep(.el-checkbox__label) {
   font-weight: bold;
 }
</style>
