<template>
  <div style="overflow-x: scroll;">
    <screen-title :text="form.screenTitle.name"></screen-title>
    <div
      id="contentAreaInput"
      style="min-width: 889px;"
    >
      <el-card>
        <el-menu
          default-active="0"
          mode="horizontal"
          class="ifa-menu-menubar"
          @select="searchDisplayX001"
        >
          <el-menu-item
            v-for="item in requireCategoryList"
            :key="item.t9nInfoCat"
            :index="item.t9nInfoCat"
          >{{ item.t9nName }}</el-menu-item>
        </el-menu>
        <el-form
          ref="form"
          :inline="true"
          :model="form"
          @submit.prevent
        >
          <div
            class="filter-container"
            style="margin-left: 1rem;"
          >
            <ifa-input-text
              id="infoListTitle"
              v-model="form.title"
              prop="title"
              size="small"
              style="width: 500px;"
              prefix-icon="Search"
              placeholder="タイトルを入力"
              original-screen-id="SUB0501_01-01"
              :domain="IfaText255DomainModel"
              @keydown.enter="titleSearchA010"
            ></ifa-input-text>

            <ifa-button
              text="検索"
              action-type="originalAction"
              small
              width="90"
              search
              class="search_button"
              @app-action-handler="titleSearchA010"
            ></ifa-button>

          </div>
        </el-form>
      </el-card>
    </div>
    <div
      id="paneBottom"
      style="min-width: 912px;"
    >
      <div id="outputPaneMargins">
        <el-card id="outputTable">
          <div
            id="gridButtonArea"
            name="gridButtonArea"
          >
            <div id="indicator-target">
              <ifa-button
                id="btnRegisterInfo"
                text="新規登録"
                width="110"
                color="primary"
                small
                action-type="requestAction"
                action-id="SUB0501_01-02_1#A001"
                @response-handler="newRegisterA004($event)"
              ></ifa-button>
            </div>
          </div>
          <grid-table
            ref="pqGrid"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </div>
    </div>

    <!-- 新規作成 ダイアログ -->
    <ifa-info-new-register
      ref="ifaInfoNewRegister"
      :is-visible="infoRegistInputVisible"
      :info="dialogInfo"
      @update-table="handleUpdateTable"
      @close-modal="infoRegistInputVisible = false"
    ></ifa-info-new-register>

    <!-- 更新 ダイアログ -->
    <ifa-info-update
      ref="ifaInfoRegistrationInput"
      :is-visible="infoRegistUpdateVisible"
      :selected-info="{notificationId: selectedInfo.notificationId}"
      :form-data="ifaInfoUpdateData"
      @update-table="handleUpdateTable"
      @close-modal="infoRegistUpdateVisible = false"
    ></ifa-info-update>

    <!-- 閲覧状況照会 ダイアログ -->
    <ifa-notification-view-status-lookup
      :is-visible="infoViewStatusVisible"
      :selected-info="viewStatusLookUpSelectedInfo"
      @close-modal="infoViewStatusVisible = false"
    ></ifa-notification-view-status-lookup>

    <!-- 削除確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="cancelComfirmVisible"
      title="削除の確認"
      :message="'選択されている『' + selectedInfo.title + '』を削除します。よろしいですか？'"
      @close-modal-ok="deleteA009"
      @close-modal-cancel="cancelComfirmVisible = false"
    ></ifa-ok-cancel-dialog>
    <ifa-requester
      id="IfaInfoRegisterLookupA001"
      action-id="SUB0501_01-01#A001"
      action-type="requestAction"
      @response-handler="initializeA001($event)"
      @response-error-handler="initializeErrorA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaInfoRegisterLookupX001"
      action-id="SUB0501_01-01#X001"
      action-type="requestAction"
      :request-model="IfaInfoRegisterLookupX001RequestModel"
      @response-handler="searchDisplayX001ResponseHandler($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaInfoRegisterLookupA009"
      action-id="SUB0501_01-01#A009"
      action-type="requestAction"
      :request-model="IfaInfoRegisterLookupA009RequestModel"
      @response-handler="deleteA009ResponseHandler($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaInfoUpdateInitializeA001"
      action-id="SUB0501_01-03_1#A001"
      action-type="requestAction"
      :request-model="IfaInfoUpdateA001RequestModel"
      @response-handler="responseHandlerIfaInfoUpdateA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaInfoNewRegister from './IfaInfoNewRegister'
import IfaInfoUpdate from './IfaInfoUpdate'
import IfaNotificationViewStatusLookup from './IfaNotificationViewStatusLookup'
import GridTable from '@/components/GridTable'
import { getDefaultOption } from '@/utils/pqgridHelper'
import IfaButton from '@/components/Button/IfaButton.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaText255DomainModel from '@/resource/domain/IfaText255DomainModel.json'
import { IfaInfoRegisterLookupFormModel } from './js/IfaInfoRegisterLookupFormModel'
import { IfaInfoRegisterLookupX001RequestModel } from './js/IfaInfoRegisterLookupX001RequestModel'
import { IfaInfoRegisterLookupA009RequestModel } from './js/IfaInfoRegisterLookupA009RequestModel'
import { getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { IfaInfoUpdateA001RequestModel } from './js/IfaInfoUpdateA001RequestModel'
import IfaUtils from '@/utils/ifaUtils'

export default {
  components: {
    GridTable,
    IfaInfoNewRegister,
    IfaInfoUpdate,
    IfaNotificationViewStatusLookup,
    IfaButton,
    screenTitle,
    IfaOkCancelDialog
  },
  emits: ['initializeError'],
  data() {
    return {
      form: new IfaInfoRegisterLookupFormModel(),
      IfaText255DomainModel: IfaText255DomainModel,
      pqGridOption: getDefaultOption(colModel),
      selectedInfo: {},
      viewStatusLookUpSelectedInfo: {
        notificationId: '',
        corReferenceCondition: '',
        title: '',
        registerDayTime: ''
      },
      infoRegistInputVisible: false,
      infoRegistUpdateVisible: false,
      infoViewStatusVisible: false,
      cancelComfirmVisible: false,
      requireCategoryList: [],
      x001NotificationCategoryList: {},
      dialogInfo: [],
      ifaInfoUpdateData: {}
    }
  },
  computed: {
    IfaInfoRegisterLookupX001RequestModel() { return new IfaInfoRegisterLookupX001RequestModel({ notificationCategoryList: this.x001NotificationCategoryList }) },
    IfaInfoRegisterLookupA009RequestModel() { return new IfaInfoRegisterLookupA009RequestModel(this.selectedInfo) },
    IfaInfoUpdateA001RequestModel() { return new IfaInfoUpdateA001RequestModel(this.selectedInfo) }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  methods: {
    onShow(resume) {
      if (!resume) {
        // 初期表示時のみA001初期化処理を実行
        this.$nextTick(() => {
          document.getElementById('IfaInfoRegisterLookupA001').click()
        })
      } else {
      // 画面再表示時は再検索
        this.searchDisplayX001()
      }
    },
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      // お知らせカテゴリリストに「全て」カテゴリを追加
      this.form.notificationCategoryList.unshift({
        t9nInfoCat: '0',
        t9nName: '全て',
        requiredFlag: '1'
      })
      // お知らせカテゴリリストを必須フラグでfilter
      this.requireCategoryList = this.form.notificationCategoryList.filter(list => list.requiredFlag === '1')
      if (response.dataList[0].notificationList) {
        this.pqGridOption.dataModel.data = this.editListForGrid(this.form.notificationList)
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.selectedInfo = {}
      this.$refs['pqGrid'].refreshView()
    },
    initializeErrorA001(response) {
      const errorInfo = {
        title: this.form.screenTitle.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    // カテゴリ(大分類)orカテゴリを変更
    searchDisplayX001() {
      this.$refs['form']?.clearValidate()
      if (arguments.length === 3) { // el-menu-item押下による発火の場合
      // 選択済カテゴリ(大分類)の配列内のインデックスを取得
        const index = this.form.selectedNotificationCategoryList.findIndex(list => list.requiredFlag === '1')
        // 選択したカテゴリ(大分類)の情報を取得
        const newSelect = this.form.notificationCategoryList.filter(list => list.t9nInfoCat === arguments[0])
        // 選択したカテゴリ(大分類)で上書き
        this.form.selectedNotificationCategoryList[index] = newSelect[0]
      }
      // カテゴリ名はリクエストパラメータに含めない
      this.x001NotificationCategoryList = this.form.selectedNotificationCategoryList.map(({ t9nName, ...rest }) => rest)

      this.$nextTick(() => {
        document.getElementById('IfaInfoRegisterLookupX001').click()
      })
    },
    searchDisplayX001ResponseHandler(response) {
      if (response.dataList[0]?.notificationList) {
        Object.assign(this.form, response.dataList[0])
        this.pqGridOption.dataModel.data = this.editListForGrid(this.form.notificationList)
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.selectedInfo = {}
      this.$refs['pqGrid'].refreshView()
      this.form.title = ''
    },
    handleClick(ui) {
      Object.assign(this.selectedInfo, ui.rowData)
      for (const key in this.viewStatusLookUpSelectedInfo) {
        if (key in this.selectedInfo) {
          this.viewStatusLookUpSelectedInfo[key] = this.selectedInfo[key]
        }
      }
      if (ui.dataIndx === 'title') {
        // A005:更新
        this.$nextTick(() => {
          document.getElementById('ifaInfoUpdateInitializeA001').click()
        })
      } else if (ui.dataIndx === 'notificationViewStatusLookup') {
        // A006:閲覧状況照会
        if (ui.rowData.corReadFlg === '1') {
          this.infoViewStatusVisible = true
        }
      } else if (ui.dataIndx === 'delete') {
        // A007:削除
        this.cancelComfirmVisible = true
      }
    },
    // 新規登録
    newRegisterA004(event) {
      Object.assign(this.dialogInfo, event.dataList[0])
      this.infoRegistInputVisible = true
    },
    // 削除
    deleteA009() {
      this.cancelComfirmVisible = false
      this.$nextTick(() => {
        document.getElementById('IfaInfoRegisterLookupA009').click()
      })
    },
    deleteA009ResponseHandler() {
      this.searchDisplayX001()
    },
    // 子画面でデータが更新されたとき、再検索
    handleUpdateTable() {
      this.searchDisplayX001()
    },
    titleSearchA010() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.title === '' || this.form.title === null) {
            this.pqGridOption.dataModel.data = this.editListForGrid(this.form.notificationList)
          } else {
            this.pqGridOption.dataModel.data = []
            const notificationListTmp = this.editListForGrid(this.form.notificationList)
            notificationListTmp.forEach((data) => {
              if (data.title) {
                // 半角文字を全角に変換
                if (IfaUtils.hiraToKata(IfaUtils.kanaHalfToFull(IfaUtils.halfWidthtoFullWidth(data.title.toUpperCase()))).indexOf(
                  IfaUtils.hiraToKata(IfaUtils.kanaHalfToFull(IfaUtils.halfWidthtoFullWidth(this.form.title.toUpperCase())))) >= 0) {
                  // 該当するものは表示
                  this.pqGridOption.dataModel.data.push(data)
                }
              }
            }
            )
          }
          this.selectedInfo = {}
          this.$refs['pqGrid'].refreshView()
        } else {
          return false
        }
      })
    },
    responseHandlerIfaInfoUpdateA001(response) {
      this.ifaInfoUpdateData = response.dataList[0]
      // 更新モーダルが開く
      this.infoRegistUpdateVisible = true
    },
    editListForGrid(array) {
      const arraySorted = this.sortedNotifications(array)
      return arraySorted.map(item => ({
        ...item,
        lastUpdateDate: item.updateTime === null ? this.$_getFormattedDateValue(item.registerDayTime) : this.$_getFormattedDateValue(item.updateTime),
        updateTime: this.getGridColUpdateTime(item)
      }))
    },
    sortedNotifications(array) {
      return [...array].sort((a, b) => {
        // updateTime を比較し、降順にソート
        if (a.updateTime > b.updateTime) return -1
        if (a.updateTime < b.updateTime) return 1
        // title が同じ場合は title で昇順にソート
        if (a.title < b.title) return -1
        if (a.title > b.title) return 1

        // どちらも同じ場合は 0 を返す
        return 0
      })
    },
    getGridColUpdateTime(item) {
      if (item.updateUser !== null && item.updateTime !== null) {
        return item.updateTime ? getFormattedDateTimeValue(item.updateTime) : '-'
      } else {
        return item.registerDayTime ? getFormattedDateTimeValue(item.registerDayTime) : '-'
      }
    }
  }
}

const changeColorBorderBottom = (item) => {
  return `<span class='grid-link'><a>` + item + `</a></span> 
          <style>
        .grid-link a {
          color: #092987;
          text-decoration: underline;
         &:focus, &:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
         }
        }
        </style>`
}

const colModel = [
  {
    title: 'カテゴリ',
    width: 200,
    minWidth: 100,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    dataIndx: 't9nName',
    editable: false
  },
  {
    title: '最終更新日',
    width: 120,
    minWidth: 100,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    dataIndx: 'lastUpdateDate',
    editable: false
  },
  {
    title: 'タイトル',
    minWidth: 550,
    width: 600,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    dataIndx: 'title',
    editable: false,
    render: function(ui) {
      return ui.rowData.title ? changeColorBorderBottom(ui.rowData.title) : '-'
    }
  },
  {
    title: '閲覧状況照会',
    minWidth: 130,
    width: 100,
    dataType: 'string',
    dataIndx: 'notificationViewStatusLookup',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      // 既読管理フラグ＝"1"（必要）の場合、表示
      if (ui.rowData.corReadFlg === '1') {
        return changeColorBorderBottom('閲覧状況照会')
      } else {
        return ''
      }
    }
  },
  {
    title: '削除',
    width: 80,
    dataType: 'string',
    editable: false,
    dataIndx: 'delete',
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return changeColorBorderBottom('削除')
    }
  },
  {
    title: '作成・更新者',
    width: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'userName'
  },
  {
    title: '作成・更新日時',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'updateTime'
  },
  {
    title: '',
    minWidth: 150,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    dataIndx: 'notificationId',
    hidden: true
  },
  {
    title: '',
    minWidth: 150,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    dataIndx: 'corReferenceCondition',
    hidden: true
  },
  {
    title: '',
    minWidth: 150,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    dataIndx: 'registerDayTime',
    hidden: true
  },
  {
    title: '',
    minWidth: 150,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    dataIndx: 'attachFile1',
    hidden: true
  },
  {
    title: '',
    minWidth: 150,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    dataIndx: 'attachFile2',
    hidden: true
  },
  {
    title: '',
    minWidth: 150,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    dataIndx: 'attachFile3',
    hidden: true
  },
  {
    title: '',
    minWidth: 850,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    render: function(ui) {
      return ''
    }
  }
]

</script>

<style lang="scss" scoped>
._bold_black_s {
  font-size: 14px;
  font-weight: 700;
  color: #606266;
  margin-left: 1rem;
}
.clickable {
  cursor: pointer;
  &:hover {
    background: rgba(0, 0, 0, .066)
  }
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
  line-height: 2
}
:deep(.el-card__body) {
  padding-top: 0.8rem
}
.form_button {
  margin: 0 2rem 0.8rem 2.5rem;
  padding: 0;
}
.item_label {
  color:#18181A;
  font-size: 14px;
  font-weight: bold;
}
#gridButtonArea {
  margin-bottom: 0.5rem;
}
#contentAreaInput, #outputPaneMargins {
  margin: 0.5rem 0.8rem;
}
:deep(.el-form-item__error) {
  white-space: nowrap
}
:deep(.el-form-item) {
  margin-bottom: 1.5rem;
}
:deep(.el-dialog) {
  margin-top: 5vh !important;
  padding: 30px 10px;
}
:deep(.el-dialog__header) {
  color: #18181A;
  padding: 0px;
}
:deep(.el-dialog__header) span{
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
}
:deep(.el-dialog__title) {
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
  padding: 0px;
}
:deep(.el-dialog__body) {
  padding: 0px;
  margin-bottom: 1rem;
}
.chcek_box {
  width:250px;
  margin-top:1rem;
}
:deep(.el-checkbox.is-bordered) {
  margin-left: 10px;
}
:deep(.el-collapse-item__header) {
  border: none;
  background-color: white;
}
.select-form-area {
 display: inline-block;
 max-width: 1350px;
 vertical-align: top;
 width: 90%;
//  width: 85%;
}
:deep(.select-form-area) .el-form-item {
  margin: 0;
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
:deep(.el-form-item) {
  margin-bottom: 0.5rem;
}
:deep(.el-checkbox.is-bordered) {
  margin-left: 10px;
}
:deep(.el-collapse-item__header) {
  border: none;
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
.ifa-menu-menubar {
  display: flex;
}
.chcek_box {
  width:200px;
  margin-top:1rem;
}
:deep(.adjust_font_size_10) .ifa-button {
  font-size: 10px;
}
:deep(.adjust_font_size_11) .ifa-button {
  font-size: 11px;
}
:deep(.adjust_font_size_12) .ifa-button {
  font-size: 12px;
}
:deep(.adjust_font_size_13) .ifa-button {
  font-size: 13px;
}
:deep(.adjust_font_size_14) .ifa-button {
  font-size: 14px;
}
.filter-container {
  margin-top:1.2rem;
  :deep(.el-form-item) {
    margin-right: 3px;
  }
}
:deep(.el-input__prefix) {
  font-size: 18px;
}
.search_button {
  vertical-align: top;
  :deep(.el-button) {
    margin-top: 1px;
  }
}
</style>
