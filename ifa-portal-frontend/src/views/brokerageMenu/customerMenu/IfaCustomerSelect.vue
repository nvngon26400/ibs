<template>
  <div>
    <ifa-requester
      id="ifaCustomerSelectInitializeA001"
      action-id="SUB0202_00-01#A001"
      action-type="requestAction"
      msg-title="顧客検索"
      :pre-request-handler="searchHandler"
      @response-handler="responseHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaCustomerSelectFavoriteRegisterUnregisterA004"
      action-id="SUB0202_00-01#A004"
      action-type="requestAction"
      :request-model="A004RequestModel"
      @response-handler="favoriteRegisterUnregisterA004"
    ></ifa-requester>
    <!-- 顧客検索 -->
    <transition
      name="search-area-opened"
      mode="out-in"
      @after-leave="afterLeave"
      @enter="enter"
    >
      <div
        v-show="searchBoxVisible"
        class="customer-search-area"
      >
        <el-form
          ref="searchForm"
          :model="form"
        >
          <!-- 検索フォーム -->
          <el-card class="box-card">
            <el-row style="display: block;">
              <el-icon
                style="margin-left: 0.5rem;"
                @click="customerSearchAccordionA006"
              ><Search></Search></el-icon>
              <span
                style="margin-left: 0.5rem"
                @click="customerSearchAccordionA006"
              >{{ form.screenTitle }}</span>
              <el-button
                type="text"
                icon="ArrowLeft"
                style="float: right; padding: 0 0.5rem 0.5rem 0;"
                class="no-border"
                @click="customerSearchAccordionA006"
              ></el-button>
            </el-row>
            <el-row>
              <el-card class="box-card">
                <ifa-input-text
                  id="butenCode"
                  v-model="form.butenCode"
                  name="buten-code"
                  prop="butenCode"
                  placeholder="部店"
                  maxlength="3"
                  minlength="3"
                  style="width: 100%; margin-right: 1rem"
                  label-class="buten_input"
                  :domain="IfaButenCodeDomainModel"
                  @change="searchTermsTextBoxBlurA002(true)"
                  @input="validateButenCode"
                  @keyup.enter="keyupEnter"
                >
                </ifa-input-text>
                <ifa-input-text
                  id="accountNumber"
                  v-model="form.accountNumber"
                  prop="accountNumber"
                  name="account-number"
                  placeholder="口座番号"
                  maxlength="7"
                  minlength="7"
                  class="search-item_account"
                  label-class="account_input"
                  :domain="IfaAccountNumberDomainModel"
                  @change="searchTermsTextBoxBlurA002(false)"
                  @input="validateAccountNumber"
                  @keyup.enter="keyupEnter"
                >
                </ifa-input-text>
                <ifa-input-text
                  id="customerId"
                  v-model="form.customerId"
                  :domain="IfaCustomerIdDomainModel"
                  prop="customerId"
                  name="customer_id"
                  placeholder="顧客ID"
                  maxlength="9"
                  style="width: calc(96% + 1rem);"
                  @change="searchTermsTextBoxBlurA002(false)"
                  @input="validateCustomerId"
                  @keyup.enter="keyupEnter"
                ></ifa-input-text>
                <ifa-input-text
                  id="customerNameKanjiKana"
                  v-model="form.customerNameKanjiKana"
                  prop="customerNameKanjiKana"
                  name="customer-name"
                  placeholder="顧客名(漢字・カナ)"
                  maxlength="72"
                  style="width: calc(95% + 1rem);"
                  original-screen-id="SUB0202_00-01"
                  :domain="IfaCustNameKanjikanaInputDomainModel"
                  @change="searchTermsTextBoxBlurA002(false)"
                  @keyup.enter="keyupEnter"
                >
                  <template #tail>
                    <div style="margin-right: 5px;">
                      <el-select
                        v-model="form.customerNameConditionList"
                        size="small"
                        style="width: 110px;"
                      >
                        <el-option
                          v-for="item in searchOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                        ></el-option>
                      </el-select>
                    </div>
                  </template>
                </ifa-input-text>
                <el-row>
                  <el-col
                    :span="7"
                    :offset="1"
                  >
                    <el-checkbox
                      v-model="form.favoriteCheckbox"
                      label="お気に入り"
                      class="checkbox-wrapper"
                    ></el-checkbox>
                  </el-col>
                  <el-col :span="7">
                    <el-checkbox
                      v-model="form.tradeRestrictionCheckbox"
                      label="取引制限あり"
                      class="checkbox-wrapper"
                    ></el-checkbox>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col
                    :span="7"
                    :offset="1"
                  >
                    <ifa-button
                      id="btnSearch"
                      text="検索"
                      icon="Search"
                      class="search-button"
                      :form="formRef"
                      :request-model="X003RequestModel"
                      action-id="SUB0202_00-01#X003"
                      action-type="requestAction"
                      :pre-request-handler="searchHandler"
                      @response-handler="searchX003($event)"
                    ></ifa-button>
                  </el-col>
                  <el-col
                    :span="7"
                    :offset="9"
                  >
                    <ifa-button
                      id="btnReset"
                      text="リセット"
                      color="secondary"
                      class="search-button"
                      action-type="originalAction"
                      @app-action-handler="resetA007"
                    ></ifa-button>
                  </el-col>
                </el-row>
              </el-card>
            </el-row>
            <!-- 検索結果 -->
            <el-card class="box-card customer-table-wrapper">
              <div
                class="table-wrapper"
                :class="{
                  'content-locked': isLocked,
                  'content-unlocked': !isLocked
                }"
              >
                <grid-table
                  ref="gridTable"
                  :options="pqGridOption"
                  :auto-refresh="false"
                  @click="customerSelectA005"
                ></grid-table>
              </div>
            </el-card>
          </el-card>
        </el-form>
      </div>
    </transition>
    <div
      v-show="!searchBoxVisible"
      style="width: 2.5rem; margin-right: 0.5rem;"
    >
      <el-card
        class="box-card customer-search-area__closed"
      >
        <el-row>
          <el-icon
            style="margin-bottom: 0.5rem;"
            @click="customerSearchAccordionA006"
          ><Search></Search></el-icon>
          <span @click="customerSearchAccordionA006">顧客検索</span>
          <el-button
            type="text"
            icon="ArrowRight"
            class="no-border"
            @click="customerSearchAccordionA006"
          ></el-button>
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import IfaAccountNumberDomainModel from '@/resource/domain/IfaAccountNumberDomainModel.json'
import IfaCustomerIdDomainModel from '@/resource/domain/IfaCustomerIdDomainModel.json'
import IfaButenCodeDomainModel from '@/resource/domain/IfaButenCodeDomainModel.json'
import IfaCustNameKanjikanaInputDomainModel from '@/resource/domain/IfaCustNameKanjikanaInputDomainModel.json'
import { IfaCustomerSelectFormModel } from './js/IfaCustomerSelectFormModel'
import { IfaCustomerSelectX003RequestModel } from './js/IfaCustomerSelectX003RequestModel'
import { IfaCustomerSelectA004RequestModel } from './js/IfaCustomerSelectA004RequestModel'
import { IfaCustomerSelectA005RequestModel } from './js/IfaCustomerSelectA005RequestModel'
import { notifyWrapper } from '@/utils/errorHandler'

export default {
  components: {
    GridTable
  },
  props: {
    isLocked: { type: Boolean, required: true }
  },
  emits: ['search', 'display', 'enter', 'reset', 'after-leave', 'initializeError', 'delete'],
  data() {
    return {
      form: new IfaCustomerSelectFormModel(),
      formRef: {},
      searchBoxVisible: true,
      pqGridOption: obj,
      IfaAccountNumberDomainModel: IfaAccountNumberDomainModel,
      IfaCustomerIdDomainModel: IfaCustomerIdDomainModel,
      IfaButenCodeDomainModel: IfaButenCodeDomainModel,
      IfaCustNameKanjikanaInputDomainModel: IfaCustNameKanjikanaInputDomainModel,
      searchOptions: [
        { value: '1', label: 'と等しい' },
        { value: '2', label: 'で始まる' },
        { value: '3', label: 'を含む' }
      ]
    }
  },
  computed: {
    X003RequestModel() {
      return new IfaCustomerSelectX003RequestModel(this.form)
    },
    A004RequestModel() {
      return new IfaCustomerSelectA004RequestModel(this.form)
    },
    A005RequestModel() {
      return new IfaCustomerSelectA005RequestModel(this.form)
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  watch: {
    searchBoxVisible() {
      this.dispatchResizeEvent()
    }
  },
  created() {
    this.pqGridOption.dataModel.data = []
  },
  mounted() {
    this.$emit('delete', true)
  },
  methods: {
    onShow() {
    // お気に入りの検索結果をグリッドテーブルの初期状態とする
    // 他メニューから顧客別メニュー配下の画面へ遷移した時A001処理を行わない
      const params = this.$_getMenuParams()
      if (!params || !params.accountNumber) {
        this.$nextTick(() => {
          document.getElementById('ifaCustomerSelectInitializeA001').click()
        })
      }
      this.formRef = this.$refs.searchForm
    },
    responseHandlerInitializeA001(response) {
      if (response.dataList.length === 0) {
        this.form.customerList = []
        this.pqGridOption.dataModel.data = []
      } else {
        Object.assign(this.form, response.dataList[0])
        for (let i = 0; i < response.dataList[0].customerList.length; i++) {
          const customer = response.dataList[0].customerList[i]
          // 特定のプロパティ名を追加
          customer.name = customer.customerNameKanji
          customer.nameKana = customer.customerNameKana
          customer.note = customer.tradeRestrictionSelect
          customer.accountOpenDate = customer.openAcctDate
        }
        this.pqGridOption.dataModel.data = response.dataList[0].customerList.map(item => ({
          ...item,
          accountOpenDate: this.$_getFormattedDateValue(item.openAcctDate),
          accountNumber: this.getGridcolAccountNumber(item)
        }))
        this.pqGridOption.dataModel.data = this.pqGridOption.dataModel.data.sort((a, b) =>
          a.accountOpenDate > b.accountOpenDate ? -1 : a.accountOpenDate < b.accountOpenDate ? 1 : 0
        )
      }
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView()
      })
    },
    getGridcolAccountNumber(item) {
      if (!item.accountNumber) {
        return '-'
      } else {
        return item.butenCode + '-' + item.accountNumber
      }
    },
    searchX003(data) {
      if (!data.dataList.length) {
        this.form.customerList = []
        this.pqGridOption.dataModel.data = []
      } else {
        Object.assign(this.form, data.dataList[0])
        for (let i = 0; i < data.dataList[0].customerList.length; i++) {
          const customer = data.dataList[0].customerList[i]
          // 特定のプロパティ名を追加
          customer.name = customer.customerNameKanji
          customer.nameKana = customer.customerNameKana
          customer.note = customer.tradeRestrictionSelect
          customer.accountOpenDate = customer.openAcctDate
        }
        this.pqGridOption.dataModel.data = data.dataList[0].customerList.map(item => ({
          ...item,
          accountOpenDate: this.$_getFormattedDateValue(item.openAcctDate),
          accountNumber: this.getGridcolAccountNumber(item)
        }))
        this.pqGridOption.dataModel.data = this.pqGridOption.dataModel.data.sort((a, b) =>
          a.accountOpenDate > b.accountOpenDate ? -1 : a.accountOpenDate < b.accountOpenDate ? 1 : 0
        )
      }
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView()
      })
    },
    favoriteRegisterUnregisterA004() {
      this.form.customerList.forEach((item, index) => {
      // お気に入りをトグルする
        if (item.butenCode === this.form.butenCodeSelected && item.accountNumber === this.form.accountNumberSelected && item.customerCode === this.form.customerCodeSelected) {
          this.form.customerList[index].favoRegStatus = this.form.favoRegStatusSelected === '0' ? '1' : this.form.favoRegStatusSelected === '1' ? '0' : '' // お気に入り登録状況
        }
        this.pqGridOption.dataModel.data = this.form.customerList.map(item => ({
          ...item,
          accountOpenDate: this.$_getFormattedDateValue(item.openAcctDate),
          accountNumber: this.getGridcolAccountNumber(item)
        }))
        this.pqGridOption.dataModel.data = this.pqGridOption.dataModel.data.sort((a, b) =>
          a.accountOpenDate > b.accountOpenDate ? -1 : a.accountOpenDate < b.accountOpenDate ? 1 : 0
        )
        this.$nextTick(() => {
          this.$refs['gridTable'].refreshView()
        })
      })
    },
    customerSelectA005(param) {
      this.form.butenCodeSelected = param.rowData.butenCode
      this.form.customerCodeSelected = param.rowData.customerCode
      this.form.accountNumberSelected = param.rowData.accountNumber?.split('-')?.[1] || ''
      this.form.favoRegStatusSelected = param.rowData.favoRegStatus
      if (param.dataIndx === 'favoRegStatus') {
        document.getElementById('ifaCustomerSelectFavoriteRegisterUnregisterA004').click()
      } else {
        const reqParam = JSON.parse(JSON.stringify(param))
        reqParam.rowData.accountNumber = this.form.accountNumberSelected
        this.$emit('display', reqParam)

        // この処理を削除しないでください。
        // 顧客選択の検索ボタンを押下したのちに顧客を選択すると
        // ローディングが解除されない不具合の抑止に必要です。
        this.searchBoxVisible = false
      }
    },
    customerSearchAccordionA006() {
      this.collapse(this.searchBoxVisible)
    },
    enter() {
      this.$emit('enter')
    },
    keyupEnter() {
      document.getElementById('btnSearch').click()
    },
    afterLeave() {
      this.$emit('after-leave')
    },
    searchHandler() {
      if (Number(this.userInfo.medUsers.privId) < 6 || Number(this.userInfo.medUsers.privId) > 10) {
        if (!this.form.butenCode && !this.form.accountNumber && !this.form.customerNameKanjiKana && !this.form.customerId && !this.form.tradeRestrictionCheckbox && !this.form.favoriteCheckbox) {
          notifyWrapper({
            title: '顧客ポータル',
            message: '検索条件がありません｡1つ以上の検索条件を設定してください｡',
            type: 'info',
            duration: 10000
          })
          throw new Error()
        }
      }
      this.$emit('search')
    },
    resetA007() {
      this.$emit('reset')
    },
    searchTermsTextBoxBlurA002(isButenCode) {
      // 部店､口座番号､顧客名､顧客コードに何かが入力された場合､強制お気に入りがOFFならお気に入りはOFFに変更する
      if (this.form.butenCode || this.form.accountNumber || this.form.customerNameKanjiKana || this.form.customerId) {
        this.form.favoriteCheckbox = false
      }
      // 部店テキストボックスの入力値に英字の小文字がある場合、大文字に変換する
      if (isButenCode) {
        const str = this.form.butenCode
        this.form.butenCode = str.toUpperCase()
      }
    },
    validateButenCode() {
      // 部店は英数のみ許容する
      this.form.butenCode = this.form.butenCode.replace(/[^\da-zA-Z]/g, '')
    },
    validateAccountNumber() {
      // 口座番号は数字のみ許容する
      this.form.accountNumber = this.form.accountNumber.replace(/[^\d\uFF10-\uFF19]/g, '')
    },
    validateCustomerId() {
      // 顧客コードは数字のみ許容する
      this.form.customerId = this.form.customerId.replace(/[^\d\uFF10-\uFF19]/g, '')
    },
    collapse(state) {
      if (state) {
        this.searchBoxVisible = false
      } else {
        this.searchBoxVisible = true
      }
    },
    /**
     * サイドバーの開閉に連動してwindow に resize イベントを発行する
     * resize イベントは､ GridTable を再描画させるために使用される
     * サイドバーのアニメーションが 280ms なので､イベント発行は 340ms 遅延させる
     * ※sidebar は 300ms で GridTable が再描画されたが､顧客選択では 340ms まで遅延させないと再描画されなかった
     * 参考: 280ms は @/styles/sidebar.scss の .main-container と .sidebar-container の transition で指定される値
     */
    dispatchResizeEvent() {
      setTimeout(() => {
        window.dispatchEvent(new Event('resize'))
      }, 340)
    }
  }
}
const nullSorting = (val1, val2) => {
  if (val1 === null && val2 === null) {
    return 0
  } else if (val1 === null && val2 !== null) {
    return 1
  } else if (val1 !== null && val2 === null) {
    return -1
  } else { // if(val1 !== null && val2 !== null){
    if (val1 > val2) {
      return 1
    } else if (val1 < val2) {
      return -1
    } else {
      return 0
    }
  }
}
const favoriteSorting = (val1, val2) => {
  if (val1 === null && val2 === null) {
    return 0
  } else if (val1 === null && val2 !== null) {
    return 1
  } else if (val1 !== null && val2 === null) {
    return -1
  } else { // if(val1 !== null && val2 !== null){
    return val1 ? -1 : val2 ? 1 : 0
  }
}
const customerNameSorting = (rowData1, rowData2, dataIndx) => {
  if (rowData1 === null && rowData2 === null) {
    return 0
  } else if (rowData1 === null && rowData2 !== null) {
    return 1
  } else if (rowData1 !== null && rowData2 === null) {
    return -1
  } else { // if(val1 !== null && val2 !== null){
    if (rowData1.nameKana > rowData2.nameKana) {
      return 1
    } else if (rowData1.nameKana < rowData2.nameKana) {
      return -1
    } else {
      return 0
    }
  }
}
const obj = {
  collapsible: false,
  columnBorders: false,
  dataModel: {
    data: []
  },
  height: 590,
  locale: 'en',
  maxHeight: 590,
  minWidth: 500,
  numberCell: { show: false },
  reactive: true,
  resizable: false,
  rowHeight: 28,
  rowInit: function(ui) { return { style: 'font-size: 12px;' } },
  scrollModel: { autoFit: true, horizontal: false },
  selectionModel: { type: 'row', mode: 'single' },
  showTitle: false,
  showTop: true,
  topVisible: false,
  Width: 505,
  wrap: false
}
obj.colModel = [
  { title: '口座番号', dataIndx: 'accountNumber', dataType: 'string', width: '17%', editable: false, nodrag: true, halign: 'center', align: 'center'
  },
  { title: '顧客ID', dataIndx: 'customerCode', dataType: 'integer', width: '15%', editable: false, nodrag: true, halign: 'center', align: 'left'
  },
  { title: ' ', dataIndx: 'note', minWidth: '4%', dataType: function(val1, val2) { return nullSorting(val1, val2) }, resizable: false, sortable: false, editable: false, nodrag: true, render: function(ui) {
    return '<span style="display: flex; justify-content: center; height: 20px; line-height: 20px;">' +
      (
        ui.rowData.note === '1'
          ? '<i class="el-icon" style="font-size: 18px; color: #666"><svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-ea893728=""><path fill="currentColor" d="M512 64a448 448 0 1 1 0 896 448 448 0 0 1 0-896zm0 192a58.432 58.432 0 0 0-58.24 63.744l23.36 256.384a35.072 35.072 0 0 0 69.76 0l23.296-256.384A58.432 58.432 0 0 0 512 256zm0 512a51.2 51.2 0 1 0 0-102.4 51.2 51.2 0 0 0 0 102.4z"></path></svg></i>'
          : ''
      ) +
      '</span>'
  } },
  { title: '顧客名（漢字）', dataType: 'string', dataIndx: 'name', width: '22%', editable: false, nodrag: true, halign: 'center', align: 'left', sortType: function(rowData1, rowData2, dataIndx) { return customerNameSorting(rowData1, rowData2, dataIndx) }, render: function(ui) {
    if (ui.rowData.name?.length > 7) {
      return '<div class="custom-tooltip" title="' + ui.rowData.name + '">' + ui.rowData.name.substring(0, 7) + '...</div>'
    }
    if (!ui.rowData.name) {
      return '-'
    }
    return ui.rowData.name
  } },
  { title: '顧客名（カナ）', dataType: 'string', dataIndx: 'nameKana', width: '22%', editable: false, nodrag: true, halign: 'center', align: 'left', sortType: function(rowData1, rowData2, dataIndx) { return customerNameSorting(rowData1, rowData2, dataIndx) }, render: function(ui) {
    if (ui.rowData.nameKana?.length > 7) {
      return '<div class="custom-tooltip" title="' + ui.rowData.nameKana + '">' + ui.rowData.nameKana.substring(0, 7) + '...</div>'
    }
    if (!ui.rowData.nameKana) {
      return '-'
    }
    return ui.rowData.nameKana
  } },
  { title: '開設日', dataType: 'string', dataIndx: 'accountOpenDate', width: '16%', editable: false, nodrag: true, halign: 'center', align: 'center', render: function(ui) {
    if (!ui.rowData.accountOpenDate) {
      return '-'
    }
  } },
  { title: ' ', dataIndx: 'favoRegStatus', minWidth: '4%', dataType: function(val1, val2) { return favoriteSorting(val1, val2) }, resizable: false, editable: false, nodrag: true, render: function(ui) {
    return '<span style="display: flex; justify-content: center; height: 20px; line-height: 20px;">' +
      (
        ui.rowData.favoRegStatus === '0'
          ? '<i class="el-icon" style="font-size: 16px; color: #666"><svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-ea893728=""><path fill="currentColor" d="m512 747.84 228.16 119.936a6.4 6.4 0 0 0 9.28-6.72l-43.52-254.08 184.512-179.904a6.4 6.4 0 0 0-3.52-10.88l-255.104-37.12L517.76 147.904a6.4 6.4 0 0 0-11.52 0L392.192 379.072l-255.104 37.12a6.4 6.4 0 0 0-3.52 10.88L318.08 606.976l-43.584 254.08a6.4 6.4 0 0 0 9.28 6.72L512 747.84zM313.6 924.48a70.4 70.4 0 0 1-102.144-74.24l37.888-220.928L88.96 472.96A70.4 70.4 0 0 1 128 352.896l221.76-32.256 99.2-200.96a70.4 70.4 0 0 1 126.208 0l99.2 200.96 221.824 32.256a70.4 70.4 0 0 1 39.04 120.064L774.72 629.376l37.888 220.928a70.4 70.4 0 0 1-102.144 74.24L512 820.096l-198.4 104.32z"></path></svg></i>'
          : ui.rowData.favoRegStatus === '1'
            ? '<i class="el-icon" style="font-size: 20px; color: #666"><svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-ea893728=""><path fill="currentColor" d="M283.84 867.84 512 747.776l228.16 119.936a6.4 6.4 0 0 0 9.28-6.72l-43.52-254.08 184.512-179.904a6.4 6.4 0 0 0-3.52-10.88l-255.104-37.12L517.76 147.904a6.4 6.4 0 0 0-11.52 0L392.192 379.072l-255.104 37.12a6.4 6.4 0 0 0-3.52 10.88L318.08 606.976l-43.584 254.08a6.4 6.4 0 0 0 9.28 6.72z"></path></svg></i>'
            : ''
      ) +
      '</span>'
  } }
]
obj.pageModel = { type: 'local', rPP: 50, rPPOptions: [5, 10, 50] }
</script>

<style lang="scss" scoped>
:deep(.el-form-item__content) {
  display: inline-block;
}
.customer-search-area {
  min-width: 550px;
  max-width: 550px;
  margin-right: 0.5rem;
}
.customer-search-area__closed {
  height: 91vh;
  padding-left: 0.5rem;
  padding-right: 0.5rem;
}
.no-border {
  border: unset;
}
.checkbox-wrapper {
  padding-top: 0.5rem;
}
.search-button {
  margin: 0.25rem 0;
}
.customer-table-wrapper {
  margin-top: 1rem;
  margin-bottom: 0.5rem;
  // height: 65.5vh;
  height: 65.5%;
}
.content-unlocked {
  cursor: pointer;
}
.content-locked {
  cursor: not-allowed;
  pointer-events: none;
  // position: absolute;
  background-color: #e6e8f0;
  width: 530px;
  height: 625x;
  z-index: 100;
  opacity: 0.6;
}
.search-area-opened-enter-active {
  animation: slide-in-out .3s reverse;
}
.search-area-opened-leave-active {
  animation: slide-in-out .3s;
}
.buten_input {
  width: 28.5%;
  display: inline-block;
}
.account_input {
  display: inline-block;
}
:deep(.search-item_account) {
  width: 371px;
}
@keyframes slide-in-out {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-85%);
  }
}
</style>
