<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      title="接触履歴受付詳細"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      width="1300px"
      @open="onShow"
    >
      <el-row>
        <el-col class="close-button">
          <ifa-button
            text="戻る"
            width="90"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
        <el-col>
          <el-card>
            <grid-table
              ref="gridTable"
              :options="pqGridOption"
              :auto-refresh="false"
            ></grid-table>
          </el-card>
        </el-col>
      </el-row>
    </el-dialog>
    <ifa-requester
      id="IfaContactAcceptDetailInitializeA001"
      action-id="SUB0202_0106-02#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="initializeA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import GridTable from '@/components/GridTable'
import { getDefaultOption } from '@/utils/pqgridHelper'
import { IfaContactAcceptDetailA001RequestModel } from './js/IfaContactAcceptDetailA001RequestModel.js'

export default {
  components: { GridTable },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    selectedInfo: {
      type: Object,
      required: true
    }
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
      pqGridOption: getDefaultOption(colModel)
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaContactAcceptDetailA001RequestModel(this.selectedInfo)
    }
  },
  created() {
    this.pqGridOption.wrap = true
    this.pqGridOption.maxHeight = 650
  },
  methods: {
    onShow() {
      // 一覧エリア初期化
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      document.getElementById('IfaContactAcceptDetailInitializeA001').click()
    },
    // 接触履歴詳細データ表示
    initializeA001(response) {
      if (response.dataList[0].contactAcceptDetailInfoList) {
        this.pqGridOption.dataModel.data = response.dataList[0].contactAcceptDetailInfoList
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['gridTable'].refreshView()
    },
    // ポップアップを閉じる
    onDialogClose() {
      this.$emit('close-modal')
    }
  }
}

const colModel = [
  {
    title: '受付シート',
    width: 100,
    align: 'center',
    dataIndx: 'acceptSheetNo',
    editable: false,
    render: function(ui) {
      return ui.rowData.acceptSheetNo || ''
    }
  },
  {
    title: '大分類',
    width: 100,
    align: 'center',
    dataIndx: 'bigClass',
    editable: false
  },
  {
    title: '中分類',
    width: 120,
    align: 'center',
    dataIndx: 'midClass',
    editable: false
  },
  {
    title: '記録日時',
    width: 200,
    align: 'center',
    dataIndx: 'recordDate',
    editable: false
  },
  {
    title: 'ステータス',
    width: 100,
    align: 'center',
    dataIndx: 'status',
    editable: false
  },
  {
    title: '内容',
    width: 599,
    halign: 'center',
    align: 'left',
    dataIndx: 'contents',
    editable: false,
    render: (item) => {
      return `<span style="white-space: pre-wrap">${item.rowData.contents || '-'}</span>`
    }
  }
]
</script>

<style lang="scss" scoped>
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
</style>
