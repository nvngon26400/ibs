<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      width="1200px"
      :center="true"
      :title="form.title"
      :show-close="false"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      @open="onOpen"
    >
      <!-- 戻るボタン -->
      <el-row>
        <el-col
          :span="24"
          :offset="22"
        >
          <ifa-button
            text="戻る"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <!-- 検索条件エリア -->
      <el-form
        ref="brandSearchForm"
        :model="form"
      >
        <el-row style="margin-top: 30px;">
          <el-col :span="20"
                  :offset="1"
          >
            <!-- 投信銘柄名・投信会社名 -->
            <ifa-input-text
              id="keyWordSearch"
              v-model="form.keyWordSearch"
              prop="keyWordSearch"
              type="text"
              placeholder="投信銘柄名・投信会社名"
              original-screen-id="SUB07-03_1"
              :domain="IfaText80DomainModel"
              style="margin-top: 5px;"
              @keydown.enter.prevent
            ></ifa-input-text>
          </el-col>
          <el-col :span="2"
                  :offset="1"
          >
            <ifa-button
              id="ifaFundBrandSearchButton"
              text="検索"
              color="secondary"
              action-type="originalAction"
              @app-action-handler="handleSearch"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-form>

      <ifa-requester
        id="ifaFundBrandSearch"
        action-id="SUB07-03_1#A002"
        action-type="requestAction"
        :request-model="A002RequestModel"
        @response-handler="responseHandlerA002($event)"
        @response-error-handler="responseErrorHandlerA002($event)"
      ></ifa-requester>
    </el-dialog>
    <el-dialog
      v-model="detailIsVisible"
      width="1200px"
      :center="true"
      title="投信銘柄一覧"
      :show-close="false"
      :before-close="onDialogDetailClose"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <!-- 戻るボタン -->
      <el-row>
        <el-col
          :span="24"
          :offset="22"
        >
          <ifa-button
            text="戻る"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogDetailClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <el-row style="margin-top: 1rem;">
        <el-col
          :offset="1"
          :span="22"
        >
          <el-card>
            <grid-table
              ref="gridTable"
              :key="gridkey"
              :options="pqGridOption"
              :auto-refresh="false"
              @click="handleClick($event)"
            ></grid-table>
          </el-card>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getDefaultOption } from '@/utils/pqgridHelper'
import { useVModel } from 'vue-composable'
import { IfaFundBrandSearchFormModel } from './js/IfaFundBrandSearchFormModel.js'
import { IfaFundBrandSearchA002RequestModel } from './js/IfaFundBrandSearchA002RequestModel.js'
import IfaText80DomainModel from '@/resource/domain/IfaText80DomainModel.json'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'

export default {
  components: {
    GridTable
  },
  props: {
    isVisible: { type: Boolean, required: true }
  },
  emits: ['close-modal', 'result', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      IfaText80DomainModel: IfaText80DomainModel,
      gridkey: 0,
      pqGridOption: getDefaultOption(columns),
      form: new IfaFundBrandSearchFormModel(),
      detailIsVisible: false
    }
  },
  computed: {
    A002RequestModel() {
      return new IfaFundBrandSearchA002RequestModel(this.form)
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  methods: {
    onOpen() {
      this.clear()
    },
    handleSearch() {
      this.$refs?.brandSearchForm.validate((valid) => {
        if (valid) {
          this.$nextTick(() => {
            document.getElementById('ifaFundBrandSearch').click()
          })
        } else {
          return
        }
      })
    },
    responseHandlerA002(response) {
      this.vmIsVisible = false
      this.detailIsVisible = true
      this.$nextTick(() => {
        this.pqGridOption.dataModel.data = response.dataList[0]?.brandInfo
        this.$refs['gridTable'].refreshView(true)
      })
    },
    responseErrorHandlerA002() {
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    onDialogClose() {
      this.clear()
      this.$emit('close-modal')
    },
    onDialogDetailClose() {
      this.$refs['brandSearchForm']?.clearValidate()
      this.detailIsVisible = false
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
      this.vmIsVisible = true
    },
    clear() {
      this.form.keyWordSearch = ''
      this.$refs['brandSearchForm']?.clearValidate()
    },
    handleClick(row) {
      if (row.dataIndx === 'fundKaisu') {
        this.detailIsVisible = false
        this.vmIsVisible = false
        this.$emit('close-modal')
        this.$emit('result', row.rowData)
      }
    }
  }
}

const columns = [
  {
    title: '銘柄コード',
    dataIndx: 'fundKaisu',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const rowData = ui.rowData
      if (rowData.fundKaisu === '' || rowData.fundKaisu === null) {
        return '<span>-</span>'
      } else {
        return '<a><span style="color: #0000FF; text-decoration: underline">' + rowData.fundKaisu + '. ' + rowData.fundGo + '</span></a>'
      }
    }
  },
  {
    title: '区分',
    dataIndx: 'fundType',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const rowData = ui.rowData
      if (rowData.fundType === '' || rowData.fundType === null) {
        return '<span>-</span>'
      } else {
        return getCodeValue('FUND_TYPE', 2, rowData.fundType ?? '$NULL')
      }
    }
  },
  {
    title: '銘柄名',
    dataIndx: 'familyName',
    dataType: 'string',
    minWidth: 750,
    editable: false,
    align: 'left',
    halign: 'center',
    render: function(ui) {
      const rowData = ui.rowData
      let familyName = rowData.familyName
      if (familyName === '' || familyName === null) {
        familyName = '-'
      }
      let fundName = rowData.fundName
      if (fundName === '' || fundName === null) {
        fundName = '-'
      }
      return familyName + '　' + fundName
    }
  }
]

</script>

<style lang="scss" scoped>
</style>
