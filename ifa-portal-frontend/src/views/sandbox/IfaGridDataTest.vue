<template>
  <div>
    <el-row>
      <ifa-button
        text="テスト"
        action-type="originalAction"
        @app-action-handler="runTest"
      ></ifa-button>
    </el-row>
    <el-row>
      <grid-table
        ref="grid"
        :options="pqGridOption"
      ></grid-table>
    </el-row>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption } from '@/utils/pqgridHelper'
const testGridData = require('@/resource/test/testGridData')
export default {
  components: { GridTable },
  data() {
    return {
      pqGridOption: null
    }
  },
  created() {
    this.pqGridOption = getDefaultOption(columns1)
  },
  methods: {
    runTest() {
      this.pqGridOption.dataModel.data = testGridData.get()
    }
  }
}

const codeValue = {
  codeListId: 'INVITATION_TYPE',
  dispPattern: 1
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
</script>
