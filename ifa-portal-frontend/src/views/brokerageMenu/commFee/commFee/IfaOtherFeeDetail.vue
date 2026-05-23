<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      left
      :close-on-click-modal="false"
      :show-close="false"
      height="1000px"
      width="900px"
      @open="onShow"
    >
      <el-row>
        <el-col>
          <page-caption
            text="その他報酬 詳細"
            background-color="rgba(190, 190, 190, 0.35)"
          ></page-caption>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <div class="form-button__wrapper">
            <ifa-button
              text="戻る"
              color="secondary"
              small
              action-type="originalAction"
              @app-action-handler="onDialogClose"
            ></ifa-button>
          </div>
        </el-col>
      </el-row>

      <el-card
        class="content-card"
        shadow="always"
      >
        <div class="filter-container">
          <!-- ここに検索条件用のコンテンツを記述する -->
          <el-row>
            <el-col
              :span="3"
              :offset="1"
            >
              <span class="__bold">仲介業者名：</span>
            </el-col>
            <el-col :span="15">
              {{ $_out(form.otherFeeDetailList[0]?.brokerName) }}
            </el-col>
          </el-row>
          <el-row style="margin-top:1rem">
            <el-col
              :span="3"
              :offset="1"
            >
              <span class="__bold">対象年月：</span>
            </el-col>
            <el-col :span="15">
              {{ $_out($_getFormattedMonthValue(form.otherFeeDetailList[0]?.targetDateYm, '')) }}
            </el-col>
          </el-row>
          <el-row style="margin: 1rem 0">
            <el-col
              :span="3"
              :offset="1"
            >
              <span class="__bold">合計金額：</span>
            </el-col>
            <el-col :span="15">
              {{ $_out(ifaFormatUtils.withCommaNoneZeroPadding(form.otherFeeDetailList[0]?.feeAmountTotal)) }}
            </el-col>
          </el-row>
        </div>
      </el-card>
      <div>
        <el-row>
          <el-card class="content-card">
            <el-row style="padding-top:0.5rem">
              <div
                class="pq-grid-title"
                style="width: 100%;"
              >{{ gridTitle }}</div>
              <grid-table
                ref="pqGrid"
                :options="pqGridOption"
              ></grid-table>
            </el-row>
          </el-card>
        </el-row>
      </div>
    </el-dialog>
    <ifa-requester
      id="ifaOtherFeeDetailA001"
      action-id="SUB020502-02#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="initialDisplayA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption } from '@/utils/pqgridHelper'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
import { IfaOtherFeeDetailA001RequestModel } from './js/IfaOtherFeeDetailA001RequestModel'
import { IfaOtherFeeDetailFormModel } from './js/IfaOtherFeeDetailFormModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils'

export default {
  components: {
    GridTable,
    pageCaption
  },
  props: {
    isVisible: { type: Boolean, required: true },
    param: { type: Object, required: true }
  },
  emits: ['close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaOtherFeeDetailFormModel(),
      pqGridOption: getDefaultOption(obj),
      gridTitle: 'その他報酬 詳細',
      ifaFormatUtils: ifaFormatUtils
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaOtherFeeDetailA001RequestModel(this.form)
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.param)
      this.form.otherFeeDetailList = []
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        document.getElementById('ifaOtherFeeDetailA001').click()
      })
    },
    initialDisplayA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.pqGridOption.dataModel.data = response.dataList[0].otherFeeDetailList
    },
    onDialogClose() {
      this.$emit('close-modal')
    }
  }
}
const obj = [
  { title: '扱者コード', dataIndx: 'dealerNumber', width: 150, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '連番', dataIndx: 'sequentialNumber', width: 150, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '金額', dataIndx: 'feeAmount', width: 150, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.feeAmount) || '-'
    }
  },
  { title: '内容', dataIndx: 'otherFeeMessage', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '&nbsp', dataIndx: '', width: 0, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false,
    render: function() {
      return ''
    }
  }
]

</script>
<style scoped>
.filter-container {
  margin-top:1rem;
}
.content-card {
  margin: 0.5rem 1rem;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  padding: 0.5rem 0 0.2rem 0;
}
.__bold {
  font-weight: bold;
}
</style>
