<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      left
      :close-on-click-modal="false"
      :show-close="false"
      width="900px"
      @open="onShow"
      @close="onDialogClose"
    >
      <el-row>
        <el-col>
          <page-caption
            :text="form.pageTitle.name"
            background-color="rgba(190, 190, 190, 0.35)"
          ></page-caption>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <div class="form-cancel-button__wrapper">
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
      <div>
        <el-card class="content-card">
          <el-row>
            <el-col
              :span="4"
              :offset="1"
              style="margin-top:0.5rem"
            >
              <span> {{ form.registerDate ? $_getFormattedMonthValue(form.registerDate, 'date6YearMonthKanji') : '-' }} </span>
            </el-col>
            <el-col
              :span="18"
              style="margin-top:0.5rem"
            >
              <span> {{ $_out(form.brokerName) }} </span>
            </el-col>
          </el-row>
          <el-row style="padding-top: 0.5rem;">
            <grid-table
              ref="ifaSelfInspectBlotterDetailGridTable"
              class="ifaSelfInspectBlotterDetailGridTable"
              :options="pqGridOption"
              :auto-refresh="false"
            ></grid-table>
          </el-row>
          <el-row
            v-if="form.memo"
            style="width:100%; margin-top:0.5rem"
          >
            <span>メモ</span>
          </el-row>
          <el-row
            v-if="form.memo"
            style="width:100%"
          >
            <ifa-input-text
              id="selfCheckMemo"
              v-model="form.memo"
              type="textarea"
              rows="3"
              clearable
              resize="none"
              input-class="textarea_input"
              :disabled="true"
              :domain="IfaText2000DomainModel"
            >
            </ifa-input-text>
          </el-row>
        </el-card>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import GridTable from '@/components/GridTable'
import { getDefaultOption } from '@/utils/pqgridHelper'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
import IfaText2000DomainModel from '@/resource/domain/IfaText2000DomainModel.json'
import { IfaSelfInspectBlotterDetailFormModel } from './js/IfaSelfInspectBlotterDetailFormModel.js'

export default {
  components: {
    GridTable,
    pageCaption
  },
  props: {
    isVisible: { type: Boolean, required: true },
    detailData: { type: Object, required: true }
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
      form: new IfaSelfInspectBlotterDetailFormModel(),
      IfaText2000DomainModel: IfaText2000DomainModel,
      pqGridOption: getDefaultOption(obj)
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.detailData)
      this.$nextTick(() => {
        this.pqGridOption.dataModel.data = this.form.selfAssessmentList
        this.$refs['ifaSelfInspectBlotterDetailGridTable'].refreshView()
      })
    },
    onDialogClose() {
      this.$emit('close-modal')
    }
  }
}

const obj = [
  {
    title: 'チェック項目',
    dataType: 'string',
    dataIndx: 'selfInspectItemName',
    width: '500',
    align: 'left',
    halign: 'center',
    render: function(ui) {
      const selfInspectItemName = ui.rowData.selfInspectItemName
      if (selfInspectItemName) {
        return ui.rowData.selfInspectItemName
      } else {
        return `<span>-</span>
        <style>
        .ifaSelfInspectBlotterDetailGridTable tr[pq-row-indx="` + ui.rowIndxPage + `"]>td[pq-col-indx="` + ui.colIndx + `"] {
          background-color: rgba(190, 190, 190, 0.35);
        }
        </style>`
      }
    }
  },
  { title: '確認',
    dataIndx: 'confirmation',
    width: '80',
    dataType: 'string',
    align: 'left',
    halign: 'center',
    render: function(ui) {
      const rowData = ui.rowData
      const confirmation = rowData.confirmation
      if (confirmation === '1') {
        return 'はい'
      } else if (confirmation === '0') {
        return 'いいえ'
      } else {
        return `<span>-</span>
        <style>
        .ifaSelfInspectBlotterDetailGridTable tr[pq-row-indx="` + ui.rowIndxPage + `"]>td[pq-col-indx="` + ui.colIndx + `"]  {
          background-color: rgba(190, 190, 190, 0.35);
        }
        </style>`
      }
    }
  },
  {
    title: '要確認',
    dataIndx: 'answerResult',
    width: '80',
    dataType: 'string',
    align: 'center',
    halign: 'center',
    render: function(ui) {
      const rowData = ui.rowData
      const confirmation = rowData.answerResult
      if (confirmation === '1') {
        return '<span style="color: red">〇</span>'
      } else if (confirmation === '0') {
        return '<span style="color: red; font-weight: bold">×</span>'
      } else {
        return `<span>-</span>
        <style>
        .ifaSelfInspectBlotterDetailGridTable tr[pq-row-indx="` + ui.rowIndxPage + `"]>td[pq-col-indx="` + ui.colIndx + `"]  {
          background-color: rgba(190, 190, 190, 0.35);
        }
        </style>`
      }
    },
    editor: {
      type: 'select',
      valueIndx: 'value',
      labelIndx: 'text',
      options: [{ 'value': '1', 'text': 'はい' }, { 'value': '0', 'text': 'いいえ' }]
    }
  },
  {
    title: '回答理由',
    dataType: 'string',
    dataIndx: 'answerReason',
    width: '170',
    editable: false,
    align: 'left',
    halign: 'center',
    render: function(ui) {
      const answerReason = ui.rowData.answerReason
      if (answerReason) {
        return ui.rowData.answerReason
      } else {
        return `<span>-</span>
        <style>
        .ifaSelfInspectBlotterDetailGridTable tr[pq-row-indx="` + ui.rowIndxPage + `"]>td[pq-col-indx="` + ui.colIndx + `"] {
          background-color: rgba(190, 190, 190, 0.35);
        }
        </style>`
      }
    }
  }
]

</script>
<style lang="scss" scoped>

:deep(.el-dialog__header) {
  padding: 0;
  margin: 0;
}
.form-cancel-button__wrapper {
  display: flex;
  justify-content: flex-end;
  padding: 0.5rem 0 0.2rem 0;
}

:deep(.textarea_input) {
  width: 830px;
}

.content-card {
  margin: 0.5rem 0 0.5rem 1rem;
}

.el-card:has(.pq-grid),
.el-card__body:has(.pq-grid),
.pq-grid {
  width: 845px !important
}
.ifaSelfInspectBlotterDetailGridTable {
  :deep(.pq-grid-cell),
  :deep(.pq-td-div) {
    white-space: pre-wrap;
}
}
</style>
