<template>
  <!-- カテゴリ一覧 -->
  <div>
    <screen-title text="カテゴリ一覧"></screen-title>
    <div class="caption_card">
      <el-form
        ref="formAdd"
        :model="form"
        :rules="rules"
        :inline="true"
      >
        <el-row>
          <el-col
            :span="7"
            :pull="1"
          >
            <ifa-input-text
              id="documentKindInput"
              v-model="form.category"
              prop="category"
              label="カテゴリ"
              size="small"
              original-screen-id="SUB0501_02-01"
              :domain="IfaText127DomainModel"
            ></ifa-input-text>
          </el-col>
          <el-col
            :span="2"
            :pull="1"
          >
            <ifa-button
              id="btnAdd"
              name="btnAdd"
              text="追加"
              width="90"
              small
              :form="formRefAdd"
              :request-model="A002RequestModel"
              action-id="SUB0501_02#A002"
              action-type="requestAction"
              @response-handler="handleAdd($event)"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-form>
      <hr>
      <el-form
        ref="formUpdateDelete"
        :model="form"
        :rules="rules"
        :inline="true"
      >
        <el-row style="margin:1rem 0">
          <el-col
            :span="24"
            style="margin-left: -1.5rem;height:2rem"
          >
            <el-form-item
              label="登録済カテゴリ"
              class="form_label"
            ></el-form-item>
          </el-col>
          <el-row>
            <el-col
              v-for="(item) in form.infoCategoryList"
              :key="item.t9nInfoCat"
              :span="5"
              style="margin: 0.5rem 0 0 1rem"
            >
              <ifa-document-kind
                ref="ifaDocumentKind"
                :document-kind-name="item.t9nName"
                :value="item.t9nInfoCat"
                :is-delete-btn="isDeleteBtn"
                @check-status-changed="handleCheckStatusChanged(item.t9nInfoCat, item.t9nName, $event)"
                @change-document-kind-name="handleDocumentKindNameChanged(item.t9nInfoCat, $event)"
              ></ifa-document-kind>
            </el-col>
          </el-row>
        </el-row>
        <hr>
        <el-row style="margin-top:1rem">
          <el-col :span="10">
            <ifa-button
              id="btnNameChange"
              name="btnNameChange"
              text="名前変更"
              width="90"
              small
              :form="formRefUpdateDelete"
              action-type="originalAction"
              @app-action-handler="handleNameChange()"
            ></ifa-button>
            <ifa-button
              id="btnDelete"
              name="btnDelete"
              text="削除"
              width="90"
              small
              :form="formRefUpdateDelete"
              action-type="originalAction"
              @app-action-handler="handleDelete($event)"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-form>
      <ifa-ok-cancel-dialog
        :is-visible="isVisible"
        :title="title"
        :message="message"
        @close-modal-ok="closeModalOk()"
        @close-modal-cancel="handleCancel()"
      ></ifa-ok-cancel-dialog>
    </div>
    <ifa-requester
      id="IfaDocClassListCategoryListA001"
      action-id="SUB0501_02#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA001($event)"
      @response-error-handler="errorHandlerA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDocClassListCategoryRegistrationA003"
      action-id="SUB0501_02#A003"
      action-type="requestAction"
      :request-model="A003RequestModel"
      @response-handler="responseHandlerA003($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDocClassListCategoryUpdateConfirmA004"
      :request-model="A004RequestModel"
      action-id="SUB0501_02#A004"
      action-type="requestAction"
      @response-handler="responseHandlerA004($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDocClassListCategoryUpdateA005"
      action-id="SUB0501_02#A005"
      action-type="requestAction"
      :request-model="A005RequestModel"
      @response-handler="responseHandlerA005($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDocClassListCategoryDeletionConfirmA006"
      :request-model="A006RequestModel"
      action-id="SUB0501_02#A006"
      action-type="requestAction"
      @response-handler="categoryDeletionConfirmA006($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDocClassListCategoryDeletionA007"
      action-id="SUB0501_02#A007"
      action-type="requestAction"
      :request-model="A007RequestModel"
      @response-handler="responseHandlerA007($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaDocumentKind from '@/views/companyEmployeeMenu/infoRegister/categoryList/IfaDocumentKind.vue'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'
import { IfaDocClassListA002RequestModel } from './js/IfaDocClassListA002RequestModel'
import { IfaDocClassListA003RequestModel } from './js/IfaDocClassListA003RequestModel'
import { IfaDocClassListA004RequestModel } from './js/IfaDocClassListA004RequestModel'
import { IfaDocClassListA005RequestModel } from './js/IfaDocClassListA005RequestModel'
import { IfaDocClassListA006RequestModel } from './js/IfaDocClassListA006RequestModel'
import { IfaDocClassListA007RequestModel } from './js/IfaDocClassListA007RequestModel'
import { IfaDocClassListFormModel } from './js/IfaDocClassListFormModel'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    screenTitle,
    IfaDocumentKind,
    IfaOkCancelDialog
  },
  emits: ['initializeError'],
  data() {
    return {
      IfaText127DomainModel: IfaText127DomainModel,
      isVisible: false,
      title: '',
      message: '',
      form: new IfaDocClassListFormModel(),
      refreshItemBgColor: false,
      rules: {
        category: [{ required: false, type: 'string', trigger: 'blur', validator: this.documentKindInputValidator }],
        t9nName: [{ required: false, type: 'string', trigger: 'blur', validator: this.deleteCheckboxValidator }]
      },
      type: '',
      formRefAdd: {},
      formRefUpdateDelete: {},
      isUpdateBtn: false,
      isDeleteBtn: false,
      updatedDocumentList: [],
      initialObjectList: [],
      updateValueList: []
    }
  },
  computed: {
    A002RequestModel() {
      return new IfaDocClassListA002RequestModel(this.form)
    },
    A003RequestModel() {
      return new IfaDocClassListA003RequestModel(this.form)
    },
    A004RequestModel() {
      return new IfaDocClassListA004RequestModel(this.form)
    },
    A005RequestModel() {
      return new IfaDocClassListA005RequestModel(this.form)
    },
    A006RequestModel() {
      return new IfaDocClassListA006RequestModel(this.form)
    },
    A007RequestModel() {
      return new IfaDocClassListA007RequestModel(this.form)
    },
    formattedCheckedCategories() {
      return this.form.registerCategoryList.map(obj => `『${obj.name}』`)
    },
    formattedCheckedUpdateCategories() {
      return this.updateValueList.map(obj => `『${obj.t9nName}』`)
    }
  },
  mounted() {
    this.formRefAdd = this.$refs.formAdd
    this.formRefUpdateDelete = this.$refs.formUpdateDelete
  },
  methods: {
    onShow() {
      this.$refs['formAdd'].clearValidate()
      this.$refs['formUpdateDelete'].clearValidate()
      document.getElementById('IfaDocClassListCategoryListA001').click()
      this.form.registerCategoryList = this.form.registerCategoryList
        .filter(obj => obj.infoCat !== '')
    },
    responseHandlerA001(response) {
      this.initialObjectList = JSON.parse(JSON.stringify(response.dataList[0].infoCategoryList))
      this.form = Object.assign(this.form, response.dataList[0])
    },
    errorHandlerA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    handleAdd() {
      this.title = '登録の確認'
      this.message = '『' + this.form.category + '』を登録します。よろしいですか？'
      this.isVisible = true
      this.type = 'add'
    },
    handleNameChange() {
      this.isUpdateBtn = true
      this.updateValueList = this.updatedDocumentList
        .filter(obj => !this.initialObjectList
          .some(obj2 => obj.t9nInfoCat === obj2.t9nInfoCat && obj.t9nName === obj2.t9nName))

      // チェックが付いたカテゴリに絞り込む
      this.updateValueList = this.updateValueList.filter(item => this.form.checkedDocumentId.includes(item.t9nInfoCat))
      this.form.updateCategoryList = this.updateValueList.map(obj => {
        return {
          infoCat: obj.t9nInfoCat,
          name: obj.t9nName
        }
      })
      setTimeout(() => {
        this.$refs['formUpdateDelete'].validate(valid => {
          if (valid) {
            document.getElementById('IfaDocClassListCategoryUpdateConfirmA004').click()
          } else {
            return false
          }
        })
      }, 100)
    },
    responseHandlerA003(response) {
      this.form = Object.assign(this.form, response.dataList[0])
    },
    responseHandlerA005(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      this.updateValueList = []
      this.form.updateCategoryList = []
      this.updatedDocumentList = []
    },
    responseHandlerA007(response) {
      this.form = Object.assign(this.form, response.dataList[0])
    },
    handleDelete() {
      this.isDeleteBtn = true
      // お知らせカテゴリリストに含まれる かつ チェックの付いた項目のみを削除対象とする。
      const infoCategoryListCats = this.form.infoCategoryList.map(item => item.t9nInfoCat)
      this.form.registerCategoryList = this.form.registerCategoryList.filter(item => infoCategoryListCats.includes(item.infoCat))
      setTimeout(() => {
        this.$refs['formUpdateDelete'].validate(valid => {
          if (valid) {
            document.getElementById('IfaDocClassListCategoryDeletionConfirmA006').click()
          } else {
            return false
          }
        })
      }, 100)
    },
    categoryDeletionConfirmA006() {
      this.title = '削除の確認'
      this.message = '選択されている' + this.formattedCheckedCategories + 'を削除します。よろしいですか？'
      this.isVisible = true
      this.type = 'delete'
      this.isDeleteBtn = false
    },
    closeModalOk() {
      this.isVisible = false
      if (this.type === 'delete') {
        document.getElementById('IfaDocClassListCategoryDeletionA007').click()
      } else if (this.type === 'add') {
        document.getElementById('IfaDocClassListCategoryRegistrationA003').click()
      } else if (this.type === 'change') {
        document.getElementById('IfaDocClassListCategoryUpdateA005').click()
      }
    },
    handleCheckStatusChanged(id, category, checkValue) {
      if (checkValue) {
        this.form.registerCategoryList.push({ infoCat: id, name: category })
        this.form.checkedDocumentId.push(id)
        this.form.checkedDocumentCategory.push(category)
      } else {
        this.form.registerCategoryList = this.form.registerCategoryList
          .filter(obj => obj.infoCat !== id)
        this.form.checkedDocumentId = this.form.checkedDocumentId.filter(value => value !== id)
        this.form.checkedDocumentCategory = this.form.checkedDocumentCategory.filter(value => value !== category)
      }
    },
    handleDocumentKindNameChanged(index, updateInfo) {
      const categoryToUpdate = this.form.infoCategoryList.find(val => val.t9nInfoCat === updateInfo.value)
      if (categoryToUpdate) {
        categoryToUpdate.t9nName = updateInfo.newName
      }
      const existingCategory = this.updatedDocumentList.find(val => val.t9nInfoCat === updateInfo.value)
      if (existingCategory) {
        const updatedCategoryList = this.updatedDocumentList.map(val => {
          if (val.t9nInfoCat === updateInfo.value) {
            return {
              t9nInfoCat: val.t9nInfoCat,
              t9nName: updateInfo.newName
            }
          }
          return val
        })
        this.updatedDocumentList = updatedCategoryList
      } else {
        this.updatedDocumentList.push({
          t9nInfoCat: updateInfo.value,
          t9nName: updateInfo.newName
        })
      }
    },
    handleCancel() {
      this.isVisible = false
      this.isUpdateBtn = false
      this.isDeleteBtn = false
    },
    responseHandlerA004() {
      this.title = '登録の確認'
      this.message = this.formattedCheckedUpdateCategories + 'を登録します。よろしいですか？'
      this.isVisible = true
      this.type = 'change'
      this.isUpdateBtn = false
    },
    // バリデーション
    documentKindInputValidator(rule, value, callback) {
      if (!this.isUpdateBtn && !this.isDeleteBtn) {
        if (this.form.category === '') {
          callback(getMessage('errors.required', ['カテゴリ']))
          return
        }
        if (this.form.category.length > 127) {
          callback(getMessage('errors.maxSize', ['カテゴリ', '127']))
          return
        }
      }
      // OK
      callback()
    },
    deleteCheckboxValidator(rule, value, callback) {
      if (this.isDeleteBtn) {
        if (this.form.checkedDocumentId.length === 0) {
          callback(getMessage('errors.selected', ['カテゴリ']))
          return
        }
      }
      if (this.isUpdateBtn) {
        this.form.infoCategoryList.forEach((obj) => {
          if (this.form.checkedDocumentId.length === 0) {
            callback(getMessage('errors.selected', ['カテゴリ']))
            return
          }
        })
      }
      // OK
      callback()
    }
  }
}

</script>

<style scoped>
.chcek_box {
  width:250px;
  margin-top:1rem;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
  line-height: 2
}
:deep(.el-form-item) {
  margin-bottom: 1.5rem;
}
:deep(.el-card__body) {
  padding: 30px;
}
:deep(.el-checkbox.is-bordered) {
  margin-left: 10px;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
</style>
