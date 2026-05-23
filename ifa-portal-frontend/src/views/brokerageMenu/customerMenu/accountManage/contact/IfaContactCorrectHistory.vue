<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      title="接触履歴（入力）修正履歴"
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
      </el-row>
      <el-row class="info-area">
        <el-col
          :span="2"
          class="_bold_black_m"
        >
          <span>日時</span>
        </el-col>
        <el-col :span="3">
          <span>{{ historyForm.nyuuryokuDate }}</span>
        </el-col>
        <el-col
          :span="2"
          class="_bold_black_m"
        >
          <span>入力者</span>
        </el-col>
        <el-col :span="3">
          <span>{{ historyForm.nyuuryokusyaName }}</span>
        </el-col>
      </el-row>
      <el-row>
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
      id="ifaContactCorrectHistoryInitializeA001"
      action-id="SUB0202_0106-08#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="initializeA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import $ from 'jquery'
import { useVModel } from 'vue-composable'
import GridTable from '@/components/GridTable'
import { getDefaultOption } from '@/utils/pqgridHelper'
import { IfaContactCorrectHistoryA001RequestModel } from './js/IfaContactCorrectHistoryA001RequestModel.js'

export default {
  components: { GridTable },
  props: {
    isVisible: { type: Boolean, required: true },
    queryData: { type: Object, required: true }
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
      isOneRecord: false,
      historyForm: {},
      pqGridOption: getDefaultOption(colModel)
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaContactCorrectHistoryA001RequestModel(this.queryData)
    }
  },
  created() {
    this.pqGridOption.wrap = true
    this.pqGridOption.maxHeight = 600
    this.pqGridOption.scrollModel = {
      autoFit: true
    }
  },
  methods: {
    onShow() {
      const vm = this
      // 一覧エリア初期化
      this.$refs.gridTable.grid.on('refresh', function() {
        // クエリ結果が1つのデータだけを返す場合のフォーマット処理
        if (vm.isOneRecord) {
          $(`.el-dialog table tr[pq-row-indx="1"]`).css('display', 'none')
          $(`.el-dialog .pq-pager`).pqPager('option', 'totalRecords', 1)
        }
      })
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      document.getElementById('ifaContactCorrectHistoryInitializeA001').click()
    },
    // 接触履歴詳細データ表示
    initializeA001(response) {
      if (response.dataList[0].contactCorrectHistoryList) {
        this.isOneRecord = response.dataList[0].contactCorrectHistoryList.length === 1
        // 日時を設定する(修正履歴リストの最後から取得)
        this.historyForm.nyuuryokuDate = this.getLastItemValues(response.dataList[0]?.contactCorrectHistoryList).nyuuryokuDate
        // 入力者を設定する(修正履歴リストの最後から取得)
        this.historyForm.nyuuryokusyaName = this.getLastItemValues(response.dataList[0]?.contactCorrectHistoryList).nyuuryokusyaName
        // 接触履歴修正履歴取得とセット
        //  接触履歴修正履歴.内容 = 修正履歴リスト.内容1+修正履歴リスト.内容2+修正履歴リスト.内容3+修正履歴リスト.内容4+修正履歴リスト.内容5
        if (!this.isOneRecord) {
          this.pqGridOption.dataModel.data = response.dataList[0].contactCorrectHistoryList.map(item => ({
            ...item,
            naiyou: [item.naiyou1, item.naiyou2, item.naiyou3, item.naiyou4, item.naiyou5].map(v => v || '').join('')
          }))
        } else {
          this.pqGridOption.dataModel.data = [...response.dataList[0].contactCorrectHistoryList.map(item => ({
            ...item,
            naiyou: [item.naiyou1, item.naiyou2, item.naiyou3, item.naiyou4, item.naiyou5].map(v => v || '').join('')
          })), { __hiddenRecord: true }]
        }
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
        // クエリ結果が1つのデータだけを返す場合のフォーマット処理
        if (this.isOneRecord) {
          $(`.el-dialog table tr[pq-row-indx="1"]`).css('display', 'none')
          $(`.el-dialog .pq-pager`).pqPager('option', 'totalRecords', 1)
        }
      })
    },
    // ポップアップを閉じる
    onDialogClose() {
      this.$emit('close-modal')
    },
    // リストの最後からレコードを取得する
    getLastItemValues(arr) {
      if (!Array.isArray(arr) || arr.length === 0) {
        return { nyuuryokuDate: '', nyuuryokusyaName: '' }
      }
      const lastItem = arr[arr.length - 1]
      return {
        nyuuryokuDate: lastItem.nyuuryokuDate || '',
        nyuuryokusyaName: lastItem.nyuuryokusyaName || ''
      }
    }
  }
}

const colModel = [
  {
    title: '修正日時',
    width: 150,
    align: 'center',
    dataIndx: 'syuuseiDate',
    editable: false
  },
  {
    title: '修正者',
    width: 150,
    halign: 'center',
    align: 'left',
    dataIndx: 'syuuseisyaName',
    editable: false
  },
  {
    title: '内容',
    width: 920,
    halign: 'center',
    align: 'left',
    dataIndx: 'naiyou',
    editable: false
  }
]
</script>

<style lang="scss" scoped>
.info-area {
  margin-bottom: 1rem;
  padding: 0.5rem 0.2rem 0.2rem 0.5rem;
}
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
:deep(.el-dialog__header) {
  justify-content: flex-start;
  display: flex;
}
:deep(.el-dialog__header) span {
  padding-left: 1rem;
}
:deep(.el-col-2) {
  text-align: center;
}
:deep(.el-col-3) {
  margin-left: 0;
  margin-right: 20px;
  font-size: 16px;
}
:deep(.pq-grid-cell) {
  white-space: pre-wrap !important;
}
</style>
