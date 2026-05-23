<template>
  <captionCard
    id="parentTarget"
    class="information-form"
    caption="インフォメーション"
    style="margin: 5px 10px 5px 10px;"
  >
    <el-card
      class="search-form"
    >
      <div style="display: flex; margin-left: 11px;">
        <ifa-button
          v-for="item in requireCategoryList"
          :id="item.categoryId + 'whole'"
          :key="item.categoryId"
          :text="getCategoryName(item.categoryName)"
          class="chcek_box"
          :class="requireCategoryList.length <=1? 'category_button_width': ''"
          :color="checkedCategoryList(item.categoryId)"
          action-id="SUB01-01#A021"
          action-type="requestAction"
          :pre-request-handler="searchDisplayA021.bind(this, item.categoryId, item.categoryName)"
          :request-model="ifaWholePortalHomeA021RequestModel"
          @response-handler="responseHandlerSearchDisplayA021"
        ></ifa-button>
      </div>
      <el-form
        ref="forms"
        :model="forms"
        :inline="true"
        @submit.prevent
      >
        <div
          class="filter-container"
          style="margin-left: 1rem;"
        >
          <span style="grid-column: span 2;">
            <ifa-input-text
              id="title"
              v-model="forms.title"
              prop="title"
              type="text"
              size="small"
              prefix-icon="Search"
              :placeholder="'タイトルを入力'"
              :domain="IfaText255DomainModel"
              @keydown.enter="titleSearchA022"
            ></ifa-input-text>
          </span>

          <ifa-button
            text="検索"
            action-type="originalAction"
            small
            width="90"
            search
            style="grid-column: span 3; padding-left: 3px;"
            class="search_button"
            @app-action-handler="titleSearchA022"
          ></ifa-button>

        </div>
      </el-form>
    </el-card>
    <el-card
      id="scrollRange"
    >
      <div v-if="pqGridOption"
           style="z-index: 0;"
      >
        <div
          class="pq-grid-title"
        >インフォメーション一覧</div>
        <grid-table
          id="outputTable"
          ref="pqGrid"
          :options="pqGridOption"
          :auto-refresh="false"
          @click="contentsDisplayA027"
        ></grid-table>
      </div>
    </el-card>
    <ifa-info-detail
      :is-visible="infoDetailIsVisible"
      :param="infoDetailData"
      @close-modal="handleCloseModal"
    >
    </ifa-info-detail>
    <ifa-requester
      id="ifaInfoDetailInitializeA001"
      action-id="SUB01_03#A001"
      action-type="requestAction"
      :request-model="IfaInfoDetailA001RequestModel"
      @response-handler="InitializeA001ResponseHandler($event)"
    ></ifa-requester>
  </captionCard>
</template>

<script>
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard.vue'
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import IfaText255DomainModel from '@/resource/domain/IfaText255DomainModel.json'
import { getDefaultOption } from '@/utils/pqgridHelper'
import { IfaWholePortalHomeA021RequestModel } from './js/IfaWholePortalHomeA021RequestModel'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import IfaInfoDetail from './IfaInfoDetail.vue'
import { IfaInfoDetailA001RequestModel } from './js/IfaInfoDetailA001RequestModel.js'
import IfaUtils from '@/utils/ifaUtils'

export default {
  components: {
    captionCard,
    GridTable,
    IfaInfoDetail
  },
  props: {
    form: { type: Object, required: true },
    notificationList: { type: Object, required: true }
  },
  data() {
    return {
      processing: false,
      ifaWholePortalHomeA021RequestModel: new IfaWholePortalHomeA021RequestModel({}),
      IfaInfoDetailA001RequestModel: new IfaInfoDetailA001RequestModel({}),
      pqGridOption: { title: 'インフォメーション一覧' },
      IfaText255DomainModel: IfaText255DomainModel,
      forms: {
        title: ''
      },
      gridData: {},
      rowData: {},
      selectedUrl: '',
      checkedDocumentKind: '',
      isIfaButton: false,
      isFirst: true,
      infoDetailIsVisible: false,
      infoDetailData: {},
      categoryAll: {
        categoryId: '',
        categoryName: 'すべて',
        requiredFlag: '1'
      }
    }
  },
  computed: {
    // カテゴリ（大分類）
    requireCategoryList() {
      const requireCategoryList = this.form.notificationCategoryList.filter(list => list.requiredFlag === '1')
      requireCategoryList.unshift(this.categoryAll)
      return requireCategoryList
    },
    // カテゴリ
    optionalCategoryList() {
      const optionalCategoryList = this.form.notificationCategoryList.filter(list => list.requiredFlag === '0')
      return optionalCategoryList
    }
  },
  watch: {
    notificationList: {
      handler(newList) {
        // 親コンポーネント側でリクエストを発行するため、watchで親コンポーネントからのレスポンスを検知
        // A001の初期表示時のみ実行
        if (this.isFirst && newList && newList.length > 0) {
          this.$nextTick(() => {
            this.pqGridOption.dataModel.data = newList.map(item => ({
              ...item,
              updateTime: this.getGridcolUpdateTime(item),
              categoryName: this.getGridcolCategoryName(item)
            }))
            this.$refs['pqGrid'].refreshView(true)
          })
          this.isFirst = false
        }
      },
      immediate: true, // コンポーネントのマウント時にも呼び出し
      deep: true // オブジェクトの内部変更も監視
    }
  },
  created() {
    this.pqGridOption = getDefaultOption(columns)
    this.pqGridOption.maxHeight = 215
    this.pqGridOption.scrollModel = {
      autoFit: true,
      vertical: true,
      horizontal: false
    }
    this.pqGridOption.wrap = true
  },
  mounted() {
    this.ifaWholePortalHomeA021RequestModel.categoryIdList[0].categoryId = ''
    this.ifaWholePortalHomeA021RequestModel.categoryIdList[0].requiredFlag = '1'
    this.ifaWholePortalHomeA021RequestModel.categoryIdList[0].category = 'すべて'
  },
  methods: {
    titleSearchA022() {
      this.$refs['forms'].validate(valid => {
        if (valid) {
          // ①リクエスト.タイトルの入力有無をチェックする。
          if (this.forms.title === '' || this.forms.title === null) {
            this.pqGridOption.dataModel.data = this.form.notificationList.map(item => ({
              ...item,
              updateTime: this.getGridcolUpdateTime(item),
              categoryName: this.getGridcolCategoryName(item)
            }))
          } else {
            this.pqGridOption.dataModel.data = []
            this.form.notificationList.forEach((data) => {
              if (data.title) {
                // 半角文字を全角に変換
                if (IfaUtils.hiraToKata(IfaUtils.kanaHalfToFull(IfaUtils.halfWidthtoFullWidth(data.title.toUpperCase()))).indexOf(
                  IfaUtils.hiraToKata(IfaUtils.kanaHalfToFull(IfaUtils.halfWidthtoFullWidth(this.forms.title.toUpperCase())))) >= 0) {
                  // 該当するものは表示
                  this.pqGridOption.dataModel.data.push(
                    {
                      ...data,
                      updateTime: this.getGridcolUpdateTime(data),
                      categoryName: this.getGridcolCategoryName(data)
                    }
                  )
                }
              }
            }
            )
          }
          this.$refs['pqGrid'].refreshView(true)
        } else {
          return false
        }
      })
    },
    searchDisplayA021(categoryId, categoryName) {
      this.ifaWholePortalHomeA021RequestModel.categoryIdList[0].categoryId = categoryId
      this.ifaWholePortalHomeA021RequestModel.categoryIdList[0].requiredFlag = '1'
      this.ifaWholePortalHomeA021RequestModel.categoryIdList[0].category = categoryName
      this.checkedDocumentKind = categoryId
    },

    responseHandlerSearchDisplayA021(response) {
      this.$refs['forms']?.clearValidate()
      if (response.dataList[0]) {
        Object.assign(this.form, response.dataList[0])
        this.pqGridOption.dataModel.data = this.form.notificationList.map(item => ({
          ...item,
          updateTime: this.getGridcolUpdateTime(item),
          categoryName: this.getGridcolCategoryName(item)
        }))
        this.$refs['pqGrid'].refreshView(true)
      } else {
        this.pqGridOption.dataModel.data = []
        this.$refs['pqGrid'].refreshView(true)
        this.forms.title = ''
        return
      }

      this.$refs['pqGrid'].refreshView(true)
      this.forms.title = ''
    },
    contentsDisplayA027(row) {
      this.rowData = row.rowData
      if (row.dataIndx === 'title') {
        // information詳細
        this.IfaInfoDetailA001RequestModel = new IfaInfoDetailA001RequestModel({
          notificationId: row.rowData.notificationId, // お知らせリスト.お知らせID
          fileDirectory: this.form.fileDirectory // ファイルディレクトリ
        })
        this.$nextTick(() => {
          document.getElementById('ifaInfoDetailInitializeA001').click()
        })
      }
    },
    InitializeA001ResponseHandler(response) {
      this.infoDetailData = response.dataList[0]
      this.infoDetailIsVisible = true
    },
    // ボタン色制御
    checkedCategoryList(id) {
      if (this.checkedDocumentKind === id) {
        return 'primary'
      } else {
        return 'secondary'
      }
    },
    // カテゴリ名取得
    getCategoryName(str) {
      return this.$_out(str)
    },
    getGridcolUpdateTime(item) {
      // 更新日を表示する
      const rowData = item
      if (rowData.updateTime === null) {
        return rowData.registerDayTime ? getFormattedDateValue(rowData.registerDayTime) : '-'
      } else {
        return rowData.updateTime ? getFormattedDateValue(rowData.updateTime) : '-'
      }
    },
    getGridcolCategoryName(item) {
      // カテゴリを表示する
      const categoryName = item.categoryName ? item.categoryName : '-'
      let backColor = ''
      switch (categoryName) {
        case this.requireCategoryList[1].categoryName:
          backColor = '#D0E2F4'
          break
        case this.requireCategoryList[2].categoryName:
          backColor = '	#FFF2DD'
          break
        case this.requireCategoryList[3].categoryName:
          backColor = '#DAF6D9'
          break
        case this.requireCategoryList[4].categoryName:
          backColor = '	#E1E1E1'
          break
        default:
          backColor = ''
          break
      }
      return `<div style="width: 100%; background-color: ` + backColor + `;">` + categoryName + `</div>`
    },
    handleCloseModal() {
      this.infoDetailIsVisible = false
    }
  }
}

const columns = [
  { title: '更新日', width: 600, minWidth: 150, dataType: 'string', halign: 'center', align: 'left', dataIndx: 'updateTime', editable: false, className: 'dateTypeColumn' },
  { title: 'カテゴリ', width: 600, minWidth: 150, dataType: 'string', halign: 'center', align: 'center', dataIndx: 'categoryName', editable: false },
  { title: 'タイトル', width: 2000, minWidth: 500, dataType: 'string', halign: 'center', align: 'left', dataIndx: 'title', editable: false,
    render: function(ui) {
      const rowData = ui.rowData
      const title = rowData.title
      return title ? changeColorBorderBottom(title, 'blue') : '-'
    }, className: 'TitleColumn'
  },
  { title: 'お知らせID', width: 120, minWidth: 100, dataType: 'string', dataIndx: 'notificationId', hidden: true, editable: false, resizable: false, halign: 'center' }
]

function changeColorBorderBottom(val, color) {
  let result = "<span style='color:"
  result += color
  result += '; border-bottom:solid 1px '
  result += color
  result += ";'>"
  result += val
  result += '</a></span>'
  return result
}
</script>

<style lang="scss" scoped>
.contents :deep(a){
  color: blue;
}
.contents-card {
  margin: 0.5rem 0rem;
}
.filter-container {
  margin-top:10px;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  :deep(.el-form-item) {
    margin-bottom: 5px;
    width: 100%;
    padding-right: 7px;
  }
}
:deep(.el-collapse-item) {
  border : solid 1px rgb(189, 189, 189) ;
}
:deep(.el-collapse-item__header) {
  border-bottom: none;
}
:deep(.el-collapse-item__content) {
    padding-bottom: 10px;
}
:deep(.el-checkbox__label) {
   font-weight: 600;
}
:deep(.el-icon-arrow-right) {
  content: none;
}
:deep(.el-form-item__error) {
  white-space: nowrap
}
:deep(.el-form-item) {
  margin-bottom: 0.5rem;
  max-width: 2210px;
}
:deep(.pq-grid-link) {
  color:#409EFF;
  text-decoration: underline;
  text-underline-offset:0.2em;
  cursor: pointer;
}
.chcek_box {
  flex: 1;
  :deep(.el-button) {
    width: 100%;
    margin-top: 5px;
    height: 32px;
  }
}
.search_button {
  vertical-align: top;
  :deep(.el-button) {
    margin-top: 1px;
  }
}
.category_button_width {
  :deep(.el-button) {
    width: 300px !important;
  }
}
:deep(.el-checkbox.is-bordered) {
  margin-left: 10px;
}
:deep(.el-collapse-item__header) {
  border: none;
}
:deep(.el-button) {
  margin-top: 1rem;
}
.ifa-menu-menubar {
  display: flex;
}
:deep(.el-collapse-item__content) {
  padding-bottom: 0;
}
:deep(.el-collapse-item__header){
  padding-left: 20px;
}
:deep(.el-collapse-item__header:hover){
  color: #409eff;
  background-color: #f9fafc;
}
:deep(.el-collapse-item) {
  border: none;
}
:deep(.el-collapse-item__arrow) {
  width: 0px;
  height: 0px;
}
.pq-grid-contents {
  position: sticky;
}
.information-form {
  .search-form{
    position: sticky;
    top: 25px;
    z-index: 1;
  }
  width: auto !important;
}
:deep(.el-card__body) {
      header {
        position: sticky;
        top: 0px;
        z-index: 1;
      }

}
:deep(.el-input__prefix) {
  font-size: 18px;
}
</style>
