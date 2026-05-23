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
    autoRefresh: {
      type: Boolean,
      required: false,
      default: true
    }
  },
  emits: ['click'],
  data() {
    return {
      localOptions: null
    }
  },
  watch: {
    options: {
      handler: function(newOption) {
        this.localOptions.dataModel.data = newOption.dataModel.data
        if (this.autoRefresh) {
          this.refreshView()
        }
      },
      deep: true
    }
  },
  mounted: function() {
    this.localOptions = this.options
    this.localOptions.cellClick = this.handleCellClick
    this.grid = pq.grid(this.$el, this.localOptions)

    this.grid.on('cellKeyDown', (event, ui) => {
      const key = event.key
      const preventKeysList = ['ArrowUp', 'ArrowDown', 'PageUp', 'PageDown', 'Home', 'End']
      if (preventKeysList.some((preventKey) => preventKey === key)) {
        event.preventDefault()
      }
    })

    this.grid.on('editorKeyDown', (event, ui) => {
      const key = event.key
      const preventKeysList = ['ArrowUp', 'ArrowDown', 'PageUp', 'PageDown']
      if (preventKeysList.some((preventKey) => preventKey === key)) {
        event.preventDefault()
      }
    })

    // ページングイベントキャッチ時、スクロールバーをトップに設定する
    // ページングイベント：
    //   左右のページングボタン押下時
    //   ページ番号を指定するテキストエリアへの値入力時
    //   表示件数/1ページのドロップダウンリスト変更時
    this.grid.on('refresh', (event, ui) => {
      if (ui.source === 'pager') {
        this.$nextTick(() => {
            this.grid.scrollRow({ rowIndxPage: 0 })
        })
      }
    })
  },
  beforeUpdate: function() {
    pq.grid(this.$el, this.localOptions)
  },
  methods: {
    refreshView(updateOptions = false) {
      if (updateOptions) {
        this.localOptions = this.options
        this.localOptions.cellClick = this.handleCellClick
        this.grid = pq.grid(this.$el, this.localOptions)
      }
      this.grid.sort({
        sorter: []
      })
      this.grid.option('pageModel.curPage', 1)
      this.grid.refreshView()
      const header = this.grid.$header
      if (header) {
        header.find('.pq-grid-col').removeClass('pq-col-sort-asc pq-col-sort-desc')
        header.find('.pq-col-sort-icon').removeClass('ui-icon ui-icon-triangle-1-s')
      }
    },
    refresh(rowIndx) {
      this.grid.refresh(rowIndx)
    },
    getData(indx) {
      function findCodeList(data, colModel) {
        for (const column of colModel) {
          if (column.dataIndx === indx && column.editor && column.editor.type === 'select') {
            return column
          } else if (column.colModel) {
            const ret = findCodeList(data, column.colModel)
            if (ret) return ret
          }
        }
        return null
      }

      // データを読み込む
      const colData = this.grid.getData()
      // データから指定のカラムだけ抽出する
      const data = colData.map(item => item[indx])
      const codeList = findCodeList(data, this.grid.options.colModel)
      if (codeList) {
        // select の場合､ラベルから区分値に変換する
        const keys = []
        data.forEach(value => {
          const c = codeList.cl.find(item => item.value === value)
          if (c) keys.push(c.key)
        })
        return keys
      } else {
        return data
      }
    },
    handleCellClick(event, ui) {
      const { rowData, rowIndx, dataIndx, colIndx } = ui
      const param = {
        rowData: rowData,
        rowIndx: rowIndx,
        dataIndx: dataIndx,
        colIndx: colIndx
      }
      this.$emit('click', param)
    }
  }
}
</script>

<style scoped>
  @import '~pqgridf/pqgrid.min.css';
  @import '~pqgridf/pqgrid.ui.min.css';
  @import '../../styles/pqgrid/ifa/pqgrid.css';
</style>
