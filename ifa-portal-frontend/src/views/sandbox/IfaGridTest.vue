<template>
  <div class="main">
    <h3>●グリッドテスト</h3>

    <hr class="main">
    <h4>
      【GridTable (select, checkbox, input text)】
      <grid-table
        ref="grid2"
        :auto-refresh="false"
        :options="pqGridOption2"
      ></grid-table>
      <div style="margin-top: 20px;">
        <ifa-button
          text="codeList"
          small
          action-type="originalAction"
          @app-action-handler="getSelectedCodeList"
        ></ifa-button>
        <span class="label">チェック:</span><span class="value">{{ result.selectedCodeList }}</span>
      </div>
      <div style="margin-top: 20px;">
        <ifa-button
          text="checkbox"
          small
          action-type="originalAction"
          @app-action-handler="getCheckboxStates"
        ></ifa-button>
        <span class="label">チェック:</span><span class="value">{{ result.checkboxStates }}</span>
      </div>
    </h4>

    <hr class="main">
    <h4>
      【GridTable (表示負荷テスト)】
      <grid-table
        ref="grid"
        :options="pqGridOption"
      ></grid-table>
    </h4>

  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption, convertData } from '@/utils/pqgridHelper'

const testGridData = require('./resources/testGridData')
const testGridData2 = require('./resources/testGridData2')
export default {
  components: { GridTable },
  data() {
    return {
      result: {
        selectedCodeList: [],
        checkboxStates: []
      },
      pqGridOption: null,
      pqGridOption2: null
    }
  },
  created() {
    this.pqGridOption = getDefaultOption(columns1)
    this.pqGridOption2 = getDefaultOption(columns2, true)
  },
  methods: {
    onShow() {
      this.pqGridOption.dataModel.data = testGridData.get()
      this.pqGridOption2.dataModel.data = convertData(testGridData2.get(), this.pqGridOption2.colModel)
      this.$nextTick(() => {
        this.$refs['grid2'].refreshView()
      })
    },
    getSelectedCodeList() {
      this.result.selectedCodeList = this.$refs['grid2'].getData('codeList')
    },
    getCheckboxStates() {
      this.result.checkboxStates = this.$refs['grid2'].getData('checkbox')
    }
  }
}

const codeValue = {
  codeListId: 'INVITATION_TYPE',
  dispPattern: 1
}
const codeList = {
  codeListId: 'CURRENCY_CODE',
  dispPattern: 2,
  selectPattern: 1
}
const checkbox = {
  codeListId: 'MARGIN_BUYING_POWER_SHORTFALL_SECURITIES',
  dispPattern: 1,
  selectPattern: 1
}

const columns1 = [
  { title: 'Column 01', dataType: 'string', dataIndx: 'col01', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 02', dataType: 'string', dataIndx: 'col02', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 03', dataType: 'string', dataIndx: 'col03', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 04', dataType: 'string', dataIndx: 'col04', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 05', dataType: 'string', dataIndx: 'col05', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 06', dataType: 'string', dataIndx: 'col06', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 07', dataType: 'string', dataIndx: 'col07', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 08', dataType: 'string', dataIndx: 'col08', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 09', dataType: 'string', dataIndx: 'col09', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 10', dataType: 'string', dataIndx: 'col10', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 11', dataType: 'string', dataIndx: 'col11', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 12', dataType: 'string', dataIndx: 'col12', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 13', dataType: 'string', dataIndx: 'col13', minWidth: 80, halign: 'center', align: 'center', codeValue }
]

const columns2 = [
  { title: 'Parent', minWidth: 80, halign: 'center', align: 'center',
    colModel: [
      { title: 'codeValue', dataType: 'string', dataIndx: 'codeValue', minWidth: 80, halign: 'center', align: 'center', codeValue },
      { title: 'codeList', dataType: 'string', dataIndx: 'codeList', minWidth: 160, halign: 'center', align: 'center', editable: true, codeList }
    ]
  },
  { title: 'checkbox', dataIndx: 'checkbox', minWidth: 160, halign: 'center', align: 'center', editable: true, checkbox },
  { title: 'text', dataType: 'string', dataIndx: 'text', minWidth: 500, halign: 'center', align: 'left', editable: true }
]
</script>

<style lang="scss">
.main {
  margin: 2rem;
}
.label {
  padding-left: 6.5rem;
  padding-right: 1rem;
}
.value {
  padding: 0.4rem;
  background-color: white;
  border: 1px solid blue;
}
</style>
