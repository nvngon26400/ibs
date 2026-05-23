<template>
  <div>
    <el-dialog
      :model-value="isVisible"
      :show-close="false"
      :center="true"
      title="インフォメーション登録閲覧者設定"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      destroy-on-close
      @open="onShow"
    >
      <!-- メイン -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          class="close-button"
          style="padding-right: 10px;"
        >
          <ifa-button
            text="リセット"
            width="110"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="resetAll"
          ></ifa-button>
          <ifa-button
            text="戻る"
            width="90"
            color="secondary"
            style="padding-right: 0px;"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-form
            ref="formModel"
            :model="formModel"
            :rules="rules"
            :inline="true"
          >
            <ifa-input-radio
              id="corReferenceCondition"
              v-model="formModel.corReferenceCondition"
              :disabled="formModel.isUpdate"
              :code-list-id="'PORTAL_USER_NOTIFICATION_REFERENCE_SCOPE'"
              :disp-pattern="2"
              prop="corReferenceCondition"
            >
            </ifa-input-radio>
            <!-- 権限設定 選択時 -->
            <table
              v-if="formModel.corReferenceCondition === '2'"
              class="authority-setting input-table"
            >
              <tr>
                <td class="table-label authority_error">
                  <el-form-item prop="authority">
                    権限選択
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="broker-input">
                    <el-checkbox-group>
                      <ifa-input-check
                        v-model="formModel.selectedAuthority"
                        :code-list-id="'INFO_REGISTER_VIEWER_AUTHORITY_SELECT'"
                        :disp-pattern="1"
                        :disabled-items="formModel.registeredNotificationReferenceAuthorityList"
                      ></ifa-input-check>
                    </el-checkbox-group>
                  </el-form-item><br>
                  <el-form-item>
                    <ifa-button
                      text="本店全選択"
                      width="130"
                      small
                      action-type="originalAction"
                      @app-action-handler="selectAllHead"
                    ></ifa-button>
                    <ifa-button
                      text="支店全選択"
                      width="130"
                      small
                      action-type="originalAction"
                      @app-action-handler="selectAllBranch"
                    ></ifa-button>
                  </el-form-item>
                </td>
              </tr>
            </table>
            <!-- 個別担当者設定 選択時 -->
            <table
              v-else-if="formModel.corReferenceCondition === '3'"
              class="input-table"
            >
              <tr>
                <td class="table-label">仲介業者(検索)</td>
                <td class="table-body">
                  <el-form
                    ref="brokerCodeForm"
                    :model="brokerCodeForm"
                  >
                    <el-row>
                      <ifa-input-text
                        id="brokerCodeFuzzySearch"
                        v-model="brokerCodeForm.brokerCodeFuzzySearch"
                        prop="brokerCodeFuzzySearch"
                        size="small"
                        style="width: 400px;"
                        original-screen-id="SUB0501_01-04"
                        :domain="IfaBrokerNameDomainModel"
                        class="ifa-input__text-default"
                        @keydown.enter.prevent
                      ></ifa-input-text>
                      <ifa-button
                        id="btnSearch"
                        text="検索"
                        width="90"
                        small
                        color="primary"
                        action-id="SUB0501_01-04#A009"
                        action-type="requestAction"
                        :form="$refs.brokerCodeForm"
                        :request-model="IfaInfoRegisterViewerSettingA009RequestModel"
                        @response-handler="responseHandlerA009($event)"
                      ></ifa-button>
                    </el-row>
                  </el-form>
                  <el-row>
                    <el-form-item class="manager-input">
                      <el-checkbox-group>
                        <ifa-input-check
                          id="broker"
                          v-model="formModel.checkedBroker"
                          :code-list-id="'original'"
                          :original-list="brokerSearchList"
                          @change="changeBroker(formModel.checkedBroker)"
                        >
                        </ifa-input-check>
                      </el-checkbox-group>
                    </el-form-item>
                  </el-row>
                </td>
              </tr>
              <tr>
                <td class="table-label">仲介業者選択</td>
                <td class="table-body">
                  <el-row>
                    <el-col :span="15">
                      <el-form-item class="manager-input">
                        <el-checkbox-group>
                          <ifa-input-check
                            id="brokerSelect"
                            v-model="formModel.brokerSelect"
                            :code-list-id="'original'"
                            :original-list="brokerSelectList"
                          >
                          </ifa-input-check>
                        </el-checkbox-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="9">
                      <el-form-item>
                        <ifa-input-radio
                          id="brokerClear"
                          v-model="formModel.brokerClear"
                          :code-list-id="'original'"
                          :original-list="brokerClearOption"
                        >
                        </ifa-input-radio>
                        <ifa-button
                          text="仲介業者一覧クリア"
                          width="150"
                          small
                          color="secondary"
                          style="margin-top: 0.5rem"
                          action-type="originalAction"
                          @app-action-handler="clearBrokerList"
                        ></ifa-button>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-form-item>
                      <ifa-button
                        text="担当者一覧表示"
                        width="130"
                        small
                        :disabled="!(formModel.brokerSelect.length > 0)"
                        action-id="SUB0501_01-04#A012"
                        action-type="requestAction"
                        :request-model="IfaInfoRegisterViewerSettingA012RequestModel"
                        @response-handler="responseHandlerA012($event)"
                      ></ifa-button>
                    </el-form-item>
                  </el-row>
                </td>
              </tr>
              <tr>
                <td class="table-label">担当者(検索)</td>
                <td class="table-body">
                  <el-form
                    ref="repSearchForm"
                    :model="repSearchForm"
                  >
                    <el-row>
                      <ifa-input-text
                        id="repSearch"
                        v-model="repSearchForm.repSearch"
                        prop="repSearch"
                        size="small"
                        style="width: 400px;"
                        original-screen-id="SUB0501_01-04"
                        :domain="IfaBrokerChargeNameDomainModel"
                        @keydown.enter.prevent
                      ></ifa-input-text>
                      <ifa-button
                        id="btnSearch"
                        text="検索"
                        width="90"
                        small
                        color="primary"
                        action-id="SUB0501_01-04#A016"
                        action-type="requestAction"
                        :form="$refs.repSearchForm"
                        :request-model="IfaInfoRegisterViewerSettingA016RequestModel"
                        @response-handler="responseHandlerA016($event)"
                      ></ifa-button>
                    </el-row>
                  </el-form>
                  <el-row>
                    <el-form-item class="manager-input">
                      <el-checkbox-group>
                        <ifa-input-check
                          id="rep"
                          v-model="formModel.checkedRep"
                          :code-list-id="'original'"
                          :original-list="repSearchList"
                          @change="changeRep(formModel.checkedRep)"
                        >
                        </ifa-input-check>
                      </el-checkbox-group>
                    </el-form-item>
                  </el-row>
                </td>
              </tr>
              <tr>
                <td class="table-label manager_error">
                  <el-form-item prop="manager">
                    担当者選択
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-row>
                    <el-col :span="15">
                      <el-form-item class="manager-input">
                        <el-checkbox-group>
                          <ifa-input-check
                            id="repSelect"
                            v-model="formModel.selectedManager"
                            :code-list-id="'original'"
                            :original-list="repSelectList"
                            :disabled-items="formModel.registeredIndividualRepList"
                          ></ifa-input-check>
                        </el-checkbox-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="9">
                      <el-form-item>
                        <ifa-input-radio
                          id="repClear"
                          v-model="formModel.repClear"
                          :code-list-id="'original'"
                          :original-list="repClearOption"
                        >
                        </ifa-input-radio>
                        <ifa-button
                          text="担当者一覧クリア"
                          width="150"
                          small
                          color="secondary"
                          style="margin-top: 0.5rem"
                          action-type="originalAction"
                          @app-action-handler="clearManagerList"
                        ></ifa-button>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-form-item>
                      <ifa-button
                        text="全担当者選択"
                        width="130"
                        small
                        :disabled="!(repSelectList.length > 0)"
                        action-type="originalAction"
                        @app-action-handler="selectAllManager"
                      ></ifa-button>
                    </el-form-item>
                  </el-row>
                </td>
              </tr>
            </table>
          </el-form>
        </el-col>

        <el-col
          :offset="1"
          style="padding-left: 10px; margin-top: 0.5rem;"
        >
          <ifa-button
            text="設定"
            width="90"
            color="primary"
            style="padding-left: 0;"
            action-type="originalAction"
            @app-action-handler="registHandler"
          ></ifa-button>
        </el-col>
      </el-row>
      <ifa-requester
        id="IfaInfoRegisterViewerSettingA002"
        action-id="SUB0501_01-04#A002"
        action-type="requestAction"
        :request-model="IfaInfoRegisterViewerSettingA002RequestModel"
        @response-handler="responseHandlerA002($event)"
        @response-error-handler="responseErrorHandlerA002()"
      ></ifa-requester>
    </el-dialog>
  </div>
</template>

<script>
import IfaBrokerNameDomainModel from '@/resource/domain/IfaBrokerNameDomainModel.json'
import IfaBrokerChargeNameDomainModel from '@/resource/domain/IfaBrokerChargeNameDomainModel.json'
import { IfaInfoRegisterViewerSettingFormModel } from './js/IfaInfoRegisterViewerSettingFormModel'
import { IfaInfoRegisterViewerSettingA002RequestModel } from './js/IfaInfoRegisterViewerSettingA002RequestModel'
import { IfaInfoRegisterViewerSettingA009RequestModel } from './js/IfaInfoRegisterViewerSettingA009RequestModel'
import { IfaInfoRegisterViewerSettingA012RequestModel } from './js/IfaInfoRegisterViewerSettingA012RequestModel'
import { IfaInfoRegisterViewerSettingA016RequestModel } from './js/IfaInfoRegisterViewerSettingA016RequestModel'
import { getMessage } from '@/utils/errorHandler'
export default {
  components: {
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    operation: {
      type: String,
      required: false,
      default: 'insert'
    },
    selectedInfo: {
      type: Object,
      required: false,
      default: null
    },
    paramData: {
      type: Object,
      required: false,
      default: null
    }
  },
  emits: ['regist-viewer-setting', 'close-modal', 'update:isVisible'],
  data() {
    return {
      IfaBrokerNameDomainModel: IfaBrokerNameDomainModel,
      IfaBrokerChargeNameDomainModel: IfaBrokerChargeNameDomainModel,
      formModel: new IfaInfoRegisterViewerSettingFormModel(),
      brokerCodeForm: {
        brokerCodeFuzzySearch: ''
      },
      repSearchForm: {
        repSearch: ''
      },
      brokerClearOption: [
        { key: true, value: '全てクリア' },
        { key: false, value: '選択以外クリア' }
      ],
      repClearOption: [
        { key: true, value: '全てクリア' },
        { key: false, value: '選択以外クリア' }
      ],
      rules: {
        corReferenceCondition: { required: true, message: getMessage('errors.accurately', ['閲覧者']), trigger: 'blur' },
        authority: { required: true, trigger: 'blur', validator: this.authorityValidator },
        manager: { required: true, trigger: 'blur', validator: this.managerValidator }
      }
    }
  },
  computed: {
    IfaInfoRegisterViewerSettingA002RequestModel() {
      if (this.operation === 'update') {
        return new IfaInfoRegisterViewerSettingA002RequestModel(this.paramData)
      }
      return null
    },
    IfaInfoRegisterViewerSettingA009RequestModel() {
      return new IfaInfoRegisterViewerSettingA009RequestModel(this.brokerCodeForm)
    },
    IfaInfoRegisterViewerSettingA012RequestModel() {
      return new IfaInfoRegisterViewerSettingA012RequestModel(this.formModel)
    },
    IfaInfoRegisterViewerSettingA016RequestModel() {
      return new IfaInfoRegisterViewerSettingA016RequestModel(this.repSearchForm)
    },
    // 仲介業者（検索）のリスト
    brokerSearchList() {
      const brokerSearchList = []
      this.formModel.broker.forEach((item, index) => {
        brokerSearchList.push({
          key: item.brokerCode,
          value: item.brokerName
        })
      })
      return brokerSearchList
    },
    // 仲介業者選択のリスト
    brokerSelectList() {
      const brokerSelectList = []
      this.formModel.broker.forEach((item, index) => {
        if (this.formModel.checkedBroker.includes(item.brokerCode)) {
          brokerSelectList.push({
            key: item.brokerCode,
            value: item.brokerName
          })
        }
      })
      return brokerSelectList
    },
    // 担当者（検索）のリスト
    repSearchList() {
      const repSearchList = []
      this.formModel.rep.forEach((item, index) => {
        repSearchList.push({
          key: item.userId,
          value: item.employeeName
        })
      })
      return repSearchList
    },
    // 担当者選択のリスト
    repSelectList() {
      const repSelectList = []
      // 「担当者一覧表示」で取得したデータをリストに追加
      this.formModel.repList.forEach((item, index) => {
        repSelectList.push({
          key: item.userId,
          value: item.employeeName
        })
      })
      // 担当者検索で取得したデータをリストに追加
      this.formModel.rep.forEach((item, index) => {
        if (this.formModel.checkedRep.includes(item.userId)) {
          repSelectList.push({
            key: item.userId,
            value: item.employeeName
          })
        }
      })
      // A001、A002の場合：個別担当者リストを追加
      this.formModel.individualRepList.forEach((item, index) => {
        repSelectList.push({
          key: item.userId,
          value: item.employeeName
        })
      })
      // 重複を排除してリストを返却
      return Array.from(new Map(repSelectList.map((rep) => [rep.key, rep])).values())
    }
  },
  methods: {
    onShow() {
      this.refresh()
      if (this.operation === 'insert') {
        // 新規登録の場合
        this.restore()
      } else if (this.operation === 'update') {
        // 更新の場合
        this.setUpdateForm()
      }
    },
    // 設定後に再表示する場合、表示を復元する
    restore() {
      if (this.selectedInfo.settingFlag) {
        this.formModel.corReferenceCondition = this.selectedInfo.viewerSetting
        if (this.selectedInfo.viewerSetting === '2') {
          // 権限設定の場合
          this.selectedInfo.notificationReferenceAuthorityList.forEach((item, index) => {
            this.formModel.selectedAuthority.push(item)
          })
        }
        if (this.selectedInfo.viewerSetting === '3') {
          // 個別担当者設定の場合
          this.selectedInfo.individualRepList.forEach((item, index) => {
            this.formModel.selectedManager.push(item.userId)
            this.formModel.individualRepList.push(item)
          })
        }
      }
    },
    refresh() {
      this.formModel = new IfaInfoRegisterViewerSettingFormModel()
      this.brokerCodeForm.brokerCodeFuzzySearch = ''
      this.repSearchForm.repSearch = ''
    },
    setUpdateForm() {
      this.$nextTick(() => {
        document.getElementById('IfaInfoRegisterViewerSettingA002').click()
      })
    },
    // A003:リセット
    resetAll() {
      this.onShow()
      if (this.$refs.formModel) { this.$refs.formModel.clearValidate() }
      if (this.$refs.brokerCodeForm) { this.$refs.brokerCodeForm.clearValidate() }
      if (this.$refs.repSearchForm) { this.$refs.repSearchForm.clearValidate() }
    },
    // A004:戻る
    onDialogClose() {
      this.$emit('close-modal')
    },
    // A006:本店全選択
    selectAllHead() {
      this.formModel.selectedAuthority.push('3', '4', '5')
      this.formModel.selectedAuthority = [...new Set(this.formModel.selectedAuthority)]
    },
    // A007:支店全選択
    selectAllBranch() {
      this.formModel.selectedAuthority.push('6', '7', '8')
      this.formModel.selectedAuthority = [...new Set(this.formModel.selectedAuthority)]
    },
    // A010:仲介業者選択
    changeBroker(value) {
      let brokerSelect = []
      const newItem = value.filter(item => !this.formModel.checkedBrokerBuf.includes(item))
      if (newItem != null && newItem.length > 0) {
        // 新しくチェックした場合
        brokerSelect = newItem.concat(this.formModel.brokerSelect)
      } else {
        // チェックを外した場合
        brokerSelect = this.formModel.brokerSelect.filter(item => value.includes(item))
      }
      this.formModel.brokerSelect = [...new Set(brokerSelect)]
      // チェック情報をバッファしておく
      this.formModel.checkedBrokerBuf = value
    },
    // A011:仲介業者クリア
    clearBrokerList() {
      if (this.formModel.brokerClear) {
        // 全てクリアの場合
        this.formModel.brokerSelect = []
        this.formModel.checkedBroker = []
      } else {
        // 選択以外クリアの場合
        this.formModel.checkedBroker = this.formModel.brokerSelect
      }
      this.formModel.checkedBrokerBuf = this.formModel.checkedBroker
    },
    // A013:担当者クリア
    clearManagerList() {
      if (this.formModel.repClear) {
        // 全てクリアの場合
        this.formModel.selectedManager = []
        this.formModel.checkedRep = []
        this.formModel.repList = []
        // Information更新の場合、登録済み担当者は選択状態にする
        this.formModel.registeredIndividualRepList.forEach((item, index) => {
          this.formModel.selectedManager.push(item)
        })
      } else {
        // 選択以外クリアの場合
        this.formModel.checkedRep = this.formModel.selectedManager
        const newRepList = []
        this.formModel.repList.forEach((item, index) => {
          if (this.formModel.selectedManager.includes(item.userId)) {
            newRepList.push(item)
          }
        })
        this.formModel.rep.forEach((item, index) => {
          if (this.formModel.selectedManager.includes(item.userId)) {
            newRepList.push(item)
          }
        })
        this.formModel.repList = newRepList
      }
      this.formModel.checkedRepBuf = this.formModel.checkedRep
    },
    // A014:全担当者選択
    selectAllManager() {
      this.formModel.selectedManager = []
      for (const item of this.repSelectList) {
        this.formModel.selectedManager.push(item.key)
      }
    },
    // A015:設定
    registHandler() {
      this.$refs['formModel'].validate(valid => {
        if (valid) {
          this.formModel.viewerSetting = this.formModel.corReferenceCondition
          this.formModel.settingFlag = true
          // 再表示用の個別担当者リストを作成
          this.formModel.repSelectList = this.repSelectList.map(
            item => { return { userId: item.key, employeeName: item.value } }
          ).filter(item => this.formModel.selectedManager.includes(item.userId))
          this.$emit('regist-viewer-setting', this.formModel)
          this.onDialogClose()
        } else {
          return false
        }
      })
    },
    // A017:担当者選択
    changeRep(value) {
      let repSelect = []
      const newItem = value.filter(item => !this.formModel.checkedRepBuf.includes(item))
      if (newItem != null) {
        // 新しくチェックした場合
        repSelect = this.formModel.registeredIndividualRepList.concat(newItem).concat(this.formModel.selectedManager)
      } else {
        // チェックを外した場合
        repSelect = this.formModel.registeredIndividualRepList.concat(value)
      }
      this.formModel.selectedManager = [...new Set(repSelect)]
      // チェック情報をバッファしておく
      this.formModel.checkedRepBuf = value
    },
    authorityValidator(rule, value, callback) {
      if (this.formModel.corReferenceCondition === '2') {
        if (this.formModel.selectedAuthority.length === 0) {
          callback(getMessage('errors.selected', ['権限']))
          return
        }
      }
      // OK
      callback()
    },
    managerValidator(rule, value, callback) {
      if (this.formModel.corReferenceCondition === '3') {
        if (this.formModel.selectedManager.length === 0) {
          callback(getMessage('errors.selected', ['担当者']))
          return
        }
      }
      // OK
      callback()
    },
    responseHandlerA002(data) {
      this.formModel.isUpdate = true

      this.formModel.viewerSetting = data.dataList[0].viewerSetting
      this.formModel.corReferenceCondition = data.dataList[0].viewerSetting
      this.formModel.selectedAuthority = data.dataList[0].registeredNotificationReferenceAuthorityList ? data.dataList[0].registeredNotificationReferenceAuthorityList : []
      this.formModel.individualRepList = data.dataList[0].registeredIndividualRepList ? data.dataList[0].registeredIndividualRepList : []
      if (this.formModel.individualRepList) {
        this.formModel.individualRepList.forEach((item, index) => {
          this.formModel.selectedManager.push(item.userId)
        })
      }
      this.formModel.registeredNotificationReferenceAuthorityList = [...new Set(data.dataList[0].registeredNotificationReferenceAuthorityList)]
      if (data.dataList[0].registeredIndividualRepList) {
        this.formModel.registeredIndividualRepList = [...new Set(data.dataList[0].registeredIndividualRepList.map(item => item['userId']))]
      } else {
        this.formModel.registeredIndividualRepList = []
      }

      this.restore()
    },
    responseErrorHandlerA002() {
      // A002：エラーの場合は初期化せず画面を閉じる
      this.onDialogClose()
    },
    responseHandlerA009(data) {
      this.formModel.broker = data.dataList[0].brokerList
    },
    responseHandlerA012(data) {
      if (data.dataList.length > 0) {
        this.formModel.repList = data.dataList[0].individualRepList
      } else {
        // 0件の場合：選択済み担当者と担当者リストをクリア
        this.formModel.selectedManager = this.formModel.selectedManager.filter(
          item => !this.formModel.repList.map(item => item['userId']).includes(item) || this.formModel.checkedRep.includes(item))
        this.formModel.repList = []
      }
    },
    responseHandlerA016(data) {
      this.formModel.rep = data.dataList[0].individualRepList
    }
  }
}

</script>

<style lang="scss" scoped>
.ifa-input__text-default {
  width: 700px;
}
.input-table {
  width:98%;
  margin: 10px;
  border-collapse: collapse;
  color: rgb(72,116,173);
  text-shadow:0 1px 0 #fff;
  border:1px solid #d8e8fa
}
.table-label {
  width:180px;
  font-size: 14px;
  font-weight: bold;
  color: #18181A;
  padding: 0 1rem;
  background-color: #dfdfdf;
  border: 1px solid #c5c5c5;
  text-shadow: none;
  text-align: right;
}
.table-body {
  border: 1px solid #c5c5c5;
  background-color: rgb(252, 252, 252);
  padding: 0.7rem 0;
}
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
:deep(.table-label) .el-form-item {
  margin: 0;
}
:deep(.table-body) .el-form-item {
  margin: 0.3rem 1rem;
}
:deep(.table-body) .ifa-button {
  margin: 0.3rem 1rem 0.3rem 0.5rem;
}
:deep(.el-dialog) {
  margin-top: 5vh !important;
  width: 1200px;
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
// 確認ダイアログ
:deep(.confirm-dialog) .el-dialog{
  padding: 30px 30px 10px 30px;
}
:deep(.confirm-dialog) .el-dialog__header span{
  font-size: 16px;
  margin: 0px;
  font-weight: bold;
}
:deep(.confirm-dialog) .dialog-body{
  padding: 25px 0 25px 0;
}
:deep(.confirm-dialog) .dialog-footer{
  margin-left: 120px
}
// 完了ダイアログ
:deep(.finish-dialog) .el-dialog{
  padding: 30px 30px 10px 30px;
}
:deep(.finish-dialog) .el-dialog__header span{
  font-size: 16px;
  margin: 0px;
  font-weight: bold;
}
:deep(.finish-dialog) .dialog-body{
  padding: 25px 0 15px 0;
}
:deep(.finish-dialog) .dialog-footer{
  margin-left: 220px
}
.h_label {
  font-size: 14px;
  margin-left: 1rem;
}
.required-mark {
  color: #ff2b2b;
  margin-right: 2px;
}
:deep(.el-form-item__error) {
  position: absolute !important;
}
.authority_error :deep(.el-form-item__error) {
  left: -140px;
}
.manager_error :deep(.el-form-item__error) {
  left: -120px;
}
:deep(.el-switch.is-checked) .el-switch__core::after {
  background-color: #FFF
}
:deep(.el-switch__label) {
  color: #18181A;
}
:deep(.el-switch__label.is-active) {
  color: #005FCC;
}
:deep(.el-checkbox) {
  display: flex;
  margin: 0.3rem 0.8rem;
}
:deep(.el-radio__label) {
  font-size: 15px;
}
.broker-input {
  border: 1px solid #E4E7ED;
  border-radius: 4px;
  width: 300px
}
.manager-input {
  border: 1px solid #E4E7ED;
  border-radius: 4px;
  width: 500px;
  overflow-x: auto;
  overflow-y: scroll;
  min-height:50px;
  max-height: 300px;
}
</style>
