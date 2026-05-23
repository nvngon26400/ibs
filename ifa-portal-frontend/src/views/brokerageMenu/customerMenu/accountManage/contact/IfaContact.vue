<template>
  <div>
    <div v-show="!contactDetailVisible">
      <screen-title :text="form.screenTitle.name"></screen-title>
      <div id="cardArea">
        <el-card>
          <el-form
            ref="formRef"
            :rules="rules"
            :inline="true"
            :model="form"
            @submit.prevent
          >
            <div class="filter-container">
              <el-row>
                <el-form-item
                  label="大分類"
                  prop="selectBigclass"
                >
                  <ifa-input-multi-select
                    ref="multiSelectRef"
                    v-model="form.selectBigclass"
                    code-list-id="HISTORY_BIGCLASS"
                    :disp-pattern="1"
                    :select-pattern="1"
                    @change="multiSelectChange"
                    @focus="handleFocus"
                    @visible-change="visibleChange"
                    @blur="handleBlur"
                  ></ifa-input-multi-select>
                </el-form-item>
              </el-row>
            </div>
          </el-form>
        </el-card>
      </div>
      <div id="cardArea">
        <el-card>
          <grid-table
            ref="pqGrid"
            :options="pqGridOption"
            :auto-refresh="false"
          ></grid-table>
        </el-card>
        <div id="gridButtonArea">
          <ifa-button
            id="btnRegisterInfo"
            text="接触履歴入力"
            width="110"
            color="primary"
            small
            action-type="originalAction"
            @app-action-handler="newRegisterA003()"
          ></ifa-button>
        </div>
      </div>
      <!-- 接触履歴初期化 イベント -->
      <ifa-requester
        id="IfaContactInitializeA001"
        action-id="SUB0202_0106-01#A001"
        action-type="requestAction"
        @response-handler="handleDisplay($event)"
        @response-error-handler="responseErrorHandlerA002($event)"
      ></ifa-requester>
      <!-- カテゴリ個別選択 イベント -->
      <ifa-requester
        id="IfaContactDisplay002"
        action-id="SUB0202_0106-01#A002"
        action-type="requestAction"
        :request-model="ifaContactA002RequestModel"
        @response-handler="handleDisplay($event)"
        @response-error-handler="responseErrorHandlerA002($event)"
      ></ifa-requester>
      <button
        id="contact-detail-button"
        type="button"
        value=""
        hidden="true"
        @click="handleClick"
      ></button>
      <!-- 接触履歴受付詳細 ダイアログ -->
      <ifa-contact-accept-detail
        :is-visible="contactAcceptDetailVisible"
        :selected-info="ifaContactAcceptDetailA001RequestModel"
        @close-modal="contactAcceptDetailVisible = false"
      ></ifa-contact-accept-detail>
    </div>
    <div v-if="!contactDetailVisible">
      <!-- 接触履歴新規作成 ダイアログ -->
      <ifa-contact-input
        ref="ifaContactInput"
        :is-visible="contactInputVisible"
        :operation-type="operationType"
        @close-modal="contactInputVisible = false"
        @research="handleResearch"
      ></ifa-contact-input>
    </div>
    <!-- 接触履歴詳細 画面 -->
    <ifa-contact-detail
      ref="ifaContactDetail"
      :is-visible="contactDetailVisible"
      :query-data="ifaContactDetailA001RequestModel"
      @back-modal="contactDetailVisible = false"
      @research="handleResearch"
    ></ifa-contact-detail>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getDefaultOption } from '@/utils/pqgridHelper'
import IfaButton from '@/components/Button/IfaButton.vue'
import { getMessage } from '@/utils/errorHandler'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaContactA002RequestModel } from './js/IfaContactA002RequestModel'
import { IfaContactAcceptDetailA001RequestModel } from './js/IfaContactAcceptDetailA001RequestModel'
import { IfaContactDetailA001RequestModel } from './js/IfaContactDetailA001RequestModel'
import { IfaContactFormModel } from './js/IfaContactFormModel'
import IfaContactAcceptDetail from './IfaContactAcceptDetail'
import IfaContactInput from './IfaContactInput'
import IfaContactDetail from './IfaContactDetail'

export default {
  components: {
    GridTable,
    IfaContactAcceptDetail,
    IfaContactInput,
    IfaContactDetail,
    IfaButton,
    screenTitle
  },
  data() {
    return {
      rules: {
        selectBigclass: [
          { required: true, message: getMessage('errors.selected', ['大分類']) },
          {
            validator: (_, value, callback) => {
              if (value.length === 0) {
                callback(getMessage('errors.selected', ['大分類']))
              } else {
                callback()
              }
            }, trigger: 'blur'
          }
        ]
      },
      pqGridOption: getDefaultOption(colModel),
      form: new IfaContactFormModel(),
      ifaContactA002RequestModel: {},
      ifaContactAcceptDetailA001RequestModel: {},
      ifaContactDetailA001RequestModel: {},
      contactAcceptDetailVisible: false,
      contactInputVisible: false,
      contactDetailVisible: false,
      changeValueBeforeFocus: false,
      originCategoryList: [],
      operationType: '',
      categoryList: [],
      categoryListMaxSize: 0
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  },
  created() {
    this.categoryList = this.$_getCodeList('HISTORY_BIGCLASS', '1', '1')
    this.categoryListMaxSize = this.categoryList.length
    this.pqGridOption.wrap = true
    this.pqGridOption.scrollModel = {
      autoFit: true
    }
    this.pqGridOption.maxHeight = 342
  },
  methods: {
    onShow() {
      const params = this.$store.getters.pageInfo.params
      if (params && params.nextActionId === 'SUB020304-01#A007') {
        // 【接触履歴（入力）検索】画面選択された明細の項目「接触履歴詳細へ」ボタン押下の場合、【接触履歴受付詳細】画面へ遷移する（※ 詳細画面遷移）
        this.ifaContactDetailA001RequestModel = new IfaContactDetailA001RequestModel({ ifaToiawaseNo: params.ifaToiawaseNo, screenId: 'SUB020304' })
        this.contactDetailVisible = true
      } else {
        // 一覧エリア初期化
        this.pqGridOption.dataModel.data = []
        this.$refs['pqGrid'].refreshView()
        this.contactDetailVisible = false
        // バリデーションクリア
        this.$refs['formRef']?.clearValidate()
        const bigclassKbnList = this.categoryList.map(item => item.key)
        this.originCategoryList = bigclassKbnList
        // 接触履歴初期化の時、大分類の初期値は「ALL」設定
        this.form.selectBigclass = bigclassKbnList
        this.ifaContactA002RequestModel = new IfaContactA002RequestModel({
          bigClassList: this.getSelectedBigclass(this.categoryList, bigclassKbnList)
        })
        // 接触履歴初期化表示イベント
        this.$nextTick(() => {
          document.getElementById('IfaContactInitializeA001').click()
        })
      }
    },
    // 接触履歴初期化表示
    handleDisplay(response) {
      if (response.dataList[0].contactInfoList) {
        this.pqGridOption.dataModel.data = response.dataList[0].contactInfoList
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['pqGrid'].refreshView()
    },
    responseErrorHandlerA002(response) {
      this.pqGridOption.dataModel.data = response?.dataList?.[0]?.contactInfoList ?? []
      this.$refs['pqGrid'].refreshView()
    },
    // 【接触履歴入力完了】から戻り、表示される場合、大分類を「ALL」に再設定し、A002の検索を実行する
    handleResearch() {
      // バリデーションクリア
      this.$refs['formRef']?.clearValidate()
      const bigclassKbnList = this.categoryList.map(item => item.key)
      this.originCategoryList = bigclassKbnList
      // 大分類を「ALL」に設定
      this.form.selectBigclass = bigclassKbnList
      this.ifaContactA002RequestModel = new IfaContactA002RequestModel({
        bigClassList: this.getSelectedBigclass(this.categoryList, bigclassKbnList)
      })
      // A002の検索を実行する
      this.$nextTick(() => {
        document.getElementById('IfaContactDisplay002').click()
      })
    },
    multiSelectChange() {
      this.changeValueBeforeFocus = true
    },
    // 【全選択】または【全選択解除】後、フォーカスを失うと接触履歴を検索する
    visibleChange(visible) {
      if (!visible) {
        const selectElement = this.$refs.multiSelectRef.$el
        const activeElement = document.activeElement
        if (!selectElement.contains(activeElement) && (!this.form.selectBigclass || this.form.selectBigclass.length === 0 || this.form.selectBigclass.length === this.categoryListMaxSize) && !this.arrayEquals(this.originCategoryList, this.form.selectBigclass)) {
          this.originCategoryList = this.form.selectBigclass
          Object.assign(this.ifaContactA002RequestModel, new IfaContactA002RequestModel({
            bigClassList: this.getSelectedBigclass(this.categoryList, this.form.selectBigclass)
          }))
          this.$refs['formRef'].validate(valid => {
            if (valid) {
              // 接触履歴を検索する
              this.$nextTick(() => {
                document.getElementById('IfaContactDisplay002').click()
              })
            }
          })
        }
      }
    },
    handleFocus() {
      if (this.changeValueBeforeFocus) {
        this.changeValueBeforeFocus = false
      } else {
        this.originCategoryList = this.form.selectBigclass
      }
    },
    // チェックボックスクリック後、フォーカスを失うと接触履歴を検索する
    handleBlur() {
      if (!this.arrayEquals(this.originCategoryList, this.form.selectBigclass)) {
        this.originCategoryList = this.form.selectBigclass
        Object.assign(this.ifaContactA002RequestModel, new IfaContactA002RequestModel({
          bigClassList: this.getSelectedBigclass(this.categoryList, this.form.selectBigclass)
        }))
        this.$refs['formRef'].validate(valid => {
          if (valid) {
            // 接触履歴を検索する
            this.$nextTick(() => {
              document.getElementById('IfaContactDisplay002').click()
            })
          }
        })
      }
    },
    // 選択した大分類リストを返す
    getSelectedBigclass(jsonArray, selectedIds) {
      return jsonArray.map(item => {
        const idStr = item.key
        const isSelected = selectedIds.includes(idStr)
        return {
          id: idStr,
          isSelected: isSelected
        }
      })
    },
    // 選択したカテゴリが変更されたかどうかを判断する
    arrayEquals(a, b) {
      if (a === b) return true
      if (a == null || b == null) return false
      if (a.length !== b.length) return false
      const sortA = [...a].sort()
      const sortB = [...b].sort()
      for (let i = 0; i < sortA.length; ++i) {
        if (sortA[i] !== sortB[i]) return false
      }
      return true
    },
    // 新規登録
    newRegisterA003() {
      // 処理区分が'1'(接触履歴入力)を設定する
      this.operationType = '1'
      this.contactInputVisible = true
    },
    // 【A004:詳細】リンク
    handleClick() {
      const selRowData = JSON.parse(decodeURIComponent(document.getElementById('contact-detail-button').value))
      // A004:詳細リンクをクリックして、詳細画面を開く
      if (selRowData.details === '1') {
        if (selRowData.referenceKbn === '2') {
          // 押下された明細の項目「参照元履歴区分」＝"2"(接触履歴)の場合、【接触履歴受付詳細】画面へ遷移する（※ 詳細画面遷移）
          this.ifaContactDetailA001RequestModel = new IfaContactDetailA001RequestModel({ ifaToiawaseNo: selRowData.ifaToiawaseNo, screenId: 'SUB0202_0106' })
          this.contactDetailVisible = true
        } else if (selRowData.referenceKbn === '3') {
          // 押下された明細の項目「参照元履歴区分」＝"3"(書類請求当日履歴)の場合、【書類請求一覧】画面へ遷移する（※ 一覧画面遷移）
          this.$_startShowMenu('SUB0202_0704')
        } else {
          // 上記以外の明細の場合、【接触履歴詳細】画面へ遷移する（※ 詳細ポップアップ遷移）
          Object.assign(this.ifaContactAcceptDetailA001RequestModel, new IfaContactAcceptDetailA001RequestModel(
            { referenceKbn: selRowData.referenceKbn,
              bigClass: selRowData.bigClass,
              recordDate: selRowData.recordDate,
              contents: selRowData.contents,
              createUser: selRowData.createUser,
              shousaiKbn: selRowData.shousaiKbn,
              edaban: selRowData.edaban
            }))
          this.contactAcceptDetailVisible = true
        }
      }
    }
  }
}

const colModel = [
  {
    title: '大分類',
    width: 145,
    align: 'center',
    dataIndx: 'bigClass'
  },
  {
    title: '中分類',
    width: 160,
    align: 'center',
    dataIndx: 'midClass'
  },
  {
    title: '記録日時',
    width: 160,
    align: 'center',
    dataIndx: 'recordDate'
  },
  {
    title: 'ステータス',
    width: 120,
    dataIndx: 'status',
    align: 'center'
  },
  {
    title: '内容',
    width: 700,
    halign: 'center',
    align: 'left',
    dataIndx: 'contents',
    render: (item) => {
      if (item.rowData.ansIfaToiawaseNo && item.rowData.contents) {
        const rowData = encodeURIComponent(JSON.stringify(item.rowData))
        return `<span style="white-space: pre-wrap">${item.rowData.contents || '-'}</span>
          </br>
          <span style="text-align:right; margin-top:1px;display:inline-block;width: 98%;">
            <a href="javascript:void(0);" 
                onclick="const btn = document.getElementById('contact-detail-button');btn.value ='${rowData}';btn.click();this.blur();"
                style="color: blue; text-decoration: underline;">
              全文表示
            </a>
          </span>
        `
      }
    }
  },
  {
    title: '詳細',
    width: 80,
    dataIndx: 'detail',
    align: 'center',
    render: function(ui) {
      if (ui.rowData.details === '1') {
        const v = JSON.stringify(ui.rowData)
        return "<button type='button' class='el-button ifa-button el-button--default el-button--mini secondary-class' onclick='const btn = document.getElementById(\"contact-detail-button\"); btn.value = \"" + encodeURIComponent(v) + "\"; btn.click(); this.blur();'><span class='__adjust_button_text'>詳細</span></button>"
      }
    }
  },
  {
    title: '担当者名',
    width: 150,
    align: 'center',
    dataIndx: 'chargeName'
  },
  { title: '', dataIndx: 'callNo', hidden: true },
  { title: '', dataIndx: 'referenceKbn', hidden: true }
]

</script>

<style lang="scss" scoped>
#gridButtonArea {
  margin-top: 0.5rem;
}
#cardArea {
  margin: 0.5rem 0.8rem;
}
.filter-container {
  margin-top:1rem;
  margin-bottom: 1rem;
}
:deep(.el-row) .el-form-item {
  align-items: baseline !important;
}
:deep(.el-row) .el-form-item__label {
  width: auto;
  font-weight: bold;
  margin: 0px 15px 0px 5px;
  align-self: auto !important;
}
:deep(.el-row) .el-form-item__error {
  padding-top: 3px;
}
</style>
