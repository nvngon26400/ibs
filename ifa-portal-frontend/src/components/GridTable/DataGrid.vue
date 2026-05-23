<template>
  <div :options="localOptions"></div>
</template>

<script>
import pq from 'pqgridf'
import 'pqgridf/localize/pq-localize-en.js'
import 'jquery-ui-pack/jquery-ui.css'
import 'jquery-ui-pack/jquery-ui.structure.css'
import 'jquery-ui-pack/jquery-ui.theme.css'

export default {
  name: 'PqGrid',
  props: {
    options: {
      type: Object,
      required: true
    },
    updateFlag: {
      type: Boolean,
      required: true,
      default: false
    }
  },
  emits: ['updated'],
  data() {
    return {
      localOptions: null
    }
  },
  watch: {
    options: {
      handler: function(newOption) {
        if (this.updateFlag) {
          this.localOptions.dataModel.data = newOption.dataModel.data
          this.grid.refreshView()
          this.$emit('updated')
        }
      },
      deep: true
    }
  },
  mounted: function() {
    this.localOptions = this.options
    this.localOptions.cellClick = handleCellClick.bind(this)
    this.grid = pq.grid(this.$el, this.localOptions)
  },
  beforeUpdate: function() {
    pq.grid(this.$el, this.localOptions)
  }
}
const handleCellClick = function(event, ui) {
  const { rowData, rowIndx, dataIndx, colIndx } = ui
  const param = {
    rowData: rowData,
    rowIndx: rowIndx,
    dataIndx: dataIndx,
    colIndx: colIndx
  }
  this.$emit('click', param)
}
</script>

<style scoped>
  @import '~pqgridf/pqgrid.min.css';
  @import '~pqgridf/pqgrid.ui.min.css';
  @import '../../styles/pqgrid/ifa/pqgrid.css';
</style>
