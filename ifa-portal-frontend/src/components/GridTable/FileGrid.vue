<template>
  <div id="attachFiles">
    <grid-table :options="pqGridOption"></grid-table>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'

export default {
  components: { GridTable },
  data() {
    return {
      pqGridOption: null,
      columns: []

    }
  },
  mounted() {
  },
  created() {
    const obj = {
      width: 1800,
      height: 118,
      flexHeight: false,
      collapsible: false,
      showTitle: false,
      showBottom: false,
      numberCell: { show: false },
      scrollModel: { horizontal: false, autoFit: false },
      topVisible: false,
      wrap: false,
      selectionModel: { type: null },
      dragColumns: { enabled: false },
      hoverMode: 'none',
      resizable: false
    }

    obj.colModel = [
      { title: '添付資料', minWidth: 180, maxWidth: 180, dataType: 'string', dataIndx: 'file', align: 'left', halign: 'center', editable: false, sortable: false, resizable: true,
        render: function(ui) {
          if (!ui.rowData.file) {
            return ''
          }
          const url = ui.rowData.file
          const disclaimer = ui.rowData.disclaimer
          if (ui.rowData.type === 1) {
            return '<a href="#" onclick="recordAction();">リンク先表示</a>'
          }
          return '<a href="#" onclick="fileDownLoad($(this).text(), ' + disclaimer + ');">' + url + '</a>'
        }
      },
      { title: 'コメント', minWidth: 1620, dataType: 'string', dataIndx: 'cmnt', align: 'left', halign: 'center', editable: false, sortable: false, resizable: false }
    ]

    this.pqGridOption = obj
  }
}
</script>

<style>
#attachFiles {
    width: 1000px;
}
</style>
