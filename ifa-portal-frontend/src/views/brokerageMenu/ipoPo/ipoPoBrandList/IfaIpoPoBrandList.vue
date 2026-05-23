<template>
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="ifa-search-view__main-default">
      <div>
        <el-card>
          <el-form
            id="ipopoBrandInfoListForm"
            name="ipopoBrandInfoListForm"
          >
            <el-row class="btn_area">
              <ifa-button
                id="btnBbApplyList"
                name="btnIpopoBBApplyList"
                text="BB申込一覧"
                small
                action-type="originalAction"
                @app-action-handler="toIpopoBBApplyList"
              ></ifa-button>
              <ifa-button
                id="btnIpopoBBApply"
                name="btnIpopoBBApply"
                text="BB申込"
                small
                :disabled="!isTableRowSelected"
                action-type="requestAction"
                action-id="SUB0204_01-01#A002"
                :request-model="IfaIpoPoBrandListA002RequestModel"
                @response-handler="bbApplicationA002ResponseHandler"
              ></ifa-button>
              <ifa-button
                id="btnAcceptByCsv"
                name="btnAcceptByCsv"
                text="BB申込(一括登録)"
                small
                action-type="requestAction"
                action-id="SUB0204_01-03_1#A001"
                :request-model="IfaBbApplyCsvMassRegisterA001RequestModel"
                @response-handler="bbApplyCsvMassRegisterA002ResponseHandler"
              ></ifa-button>
              <ifa-button
                v-if="showSubscriptPeriodMasterRegisterInputButton"
                id="btnPeriodMaster"
                name="btnPeriodMaster"
                text="BB募集期間マスタ登録"
                small
                :disabled="!isTableRowSelected"
                action-type="requestAction"
                action-id="SUB0204_01-01#A004"
                :request-model="IfaIpoPoBrandListA004RequestModel"
                @response-handler="bbSolicitationPeriodMasterRegistrationA004ResponseHandler"
              ></ifa-button>
            </el-row>
          </el-form>
          <grid-table
            id="ipopoGridTable"
            ref="ipopoGridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </div>

      <!--
        ========== ポップアップ画面 ==========
      -->
      <!-- BB募集期間マスタ -->
      <ifa-subscript-period-master-register-input
        id="ifaSubscriptPeriodMasterRegisterInput"
        ref="ifaSubscriptPeriodMasterRegisterInput"
        :is-visible="dialogipopoBBPeriodMasterVisible"
        :param="subscriptPeriodMasterRegisterInputResponseData"
        @close-modal="closeModal"
        @update-brand-list="updateBrandList"
      ></ifa-subscript-period-master-register-input>
      <!-- BB申込 -->
      <ifa-bb-apply-input
        id="ifaBbApplyInput"
        ref="ifaBbApplyInput"
        :is-visible="dialogbbApplyNewVisible"
        @close-modal="closeModal"
        @open-modal="dialogbbApplyNewVisible = true"
      ></ifa-bb-apply-input>
      <!-- BB申込 (一括登録)-->
      <ifa-bb-apply-csv-mass-register
        id="ifaBbApplyCsvMassRegister"
        ref="ifaBbApplyCsvMassRegister"
        :is-visible="dialogbbApplyCsvBatchUploadVisible"
        :comment="ifaBbApplyCsvMassRegisterComment"
        @close-modal="closeModal"
      ></ifa-bb-apply-csv-mass-register>
    </div>

    <!--
      ========== IfaRequester ==========
    -->
    <!-- A001 初期化 -->
    <ifa-requester
      id="IfaIpoPoBrandListInitializeA001"
      action-type="requestAction"
      action-id="SUB0204_01-01#A001"
      @response-handler="initializeA001ResponseHandler($event)"
      @response-error-handler="initializeA001ResponseErrorHandler($event)"
    ></ifa-requester>

    <!-- SUB0204_02-01_BB申込一覧 X002-->
    <!-- TODO: BB申込一覧を作成した際に実装してください。-->
    <!--
    <ifa-requester
      id=""
      action-type="requestAction"
      action-id="SUB0204_02-01#X002"
      @response-handler="bbApplyListInitilizeX002ResponseHandler($event)"
    ></ifa-requester>
    -->

    <!-- SUB00204_01-02_1_BB申込入力 A001 -->
    <ifa-requester
      id="IfaBbApplyInputInitializeA001"
      action-type="requestAction"
      action-id="SUB0204_01-02_1#A001"
      @response-handler="bbApplyInputInitializeA001ResponseHandler($event)"
    ></ifa-requester>

    <!-- SUB0204_01-04_1_募集期間マスタ登録 A001 -->
    <ifa-requester
      id="IfaSubscriptPeriodMasterRegisterInputInitializeA001"
      action-type="requestAction"
      action-id="SUB0204_01-04_1#A001"
      :request-model="IfaSubscriptPeriodMasterRegisterInputA001RequestModel"
      @response-handler="subscriptPeriodMasterRegisterInputInitializeA001($event)"
    ></ifa-requester>
  </div>
</template>
<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaSubscriptPeriodMasterRegisterInput from './IfaSubscriptPeriodMasterRegisterInput'
import IfaBbApplyInput from '@/views/brokerageMenu/ipoPo/ipoPoBrandList/IfaBbApplyInput'
import IfaBbApplyCsvMassRegister from './IfaBbApplyCsvMassRegister'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import ifaUtils from '@/utils/ifaUtils.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction.js'
import { IfaIpoPoBrandListA002RequestModel } from './js/IfaIpoPoBrandListA002RequestModel'
import { IfaIpoPoBrandListA004RequestModel } from './js/IfaIpoPoBrandListA004RequestModel'
import { IfaIpoPoBrandListFormModel } from './js/IfaIpoPoBrandListFormModel'
import { IfaBbApplyInputA001RequestModel } from './js/IfaBbApplyInputA001RequestModel'
import { IfaBbApplyCsvMassRegisterA001RequestModel } from './js/IfaBbApplyCsvMassRegisterA001RequestModel'
import { IfaSubscriptPeriodMasterRegisterInputA001RequestModel } from './js/IfaSubscriptPeriodMasterRegisterInputA001RequestModel'

export default {
  components: {
    GridTable,
    IfaSubscriptPeriodMasterRegisterInput,
    IfaBbApplyInput,
    IfaBbApplyCsvMassRegister,
    screenTitle,
    IfaRequester
  },
  emits: ['initializeError'],
  data() {
    return {
      dialogbbApplyCsvBatchUploadVisible: false,
      dialogbbApplyNewVisible: false,
      dialogipopoBBPeriodMasterVisible: false,
      showSubscriptPeriodMasterRegisterInputButton: false,
      isTableRowSelected: false,
      subscriptPeriodMasterRegisterInputResponseData: null,
      ifaBbApplyCsvMassRegisterComment: null,
      pqGridOption: getConvertedOption(obj),
      form: new IfaIpoPoBrandListFormModel()
    }
  },
  computed: {
    IfaIpoPoBrandListA002RequestModel() {
      return new IfaIpoPoBrandListA002RequestModel(this.form)
    },
    IfaIpoPoBrandListA004RequestModel() {
      return new IfaIpoPoBrandListA004RequestModel(this.form)
    },
    IfaBbApplyCsvMassRegisterA001RequestModel() {
      return new IfaBbApplyCsvMassRegisterA001RequestModel()
    },
    IfaSubscriptPeriodMasterRegisterInputA001RequestModel() {
      return new IfaSubscriptPeriodMasterRegisterInputA001RequestModel(
        {
          'brandCode12': this.form.brandCode12,
          'bbPresentationFrom': this.form.bookBuildingPresentationFrom
        }
      )
    }
  },
  methods: {
    onShow() {
      document.getElementById('IfaIpoPoBrandListInitializeA001').click()
    },

    // 一覧選択
    handleClick(ui) {
      this.isTableRowSelected = true
      this.form = { ...this.form, ...ui.rowData }
    },

    // BB申込一覧ボタン押下
    toIpopoBBApplyList() {
      if (this.isTableRowSelected) {
        const param = { brandCode12: this.form.brandCode12 }
        this.$_startShowMenu('SUB0204_02', param)
      } else {
        this.$_startShowMenu('SUB0204_02')
      }
    },

    // モーダルのクローズ
    closeModal() {
      this.dialogbbApplyCsvBatchUploadVisible = false
      this.dialogbbApplyNewVisible = false
      this.dialogipopoBBPeriodMasterVisible = false
    },
    // IPO/PO銘柄一覧の更新処理
    updateBrandList() {
      // A001初期化処理を再呼び出し
      this.$nextTick(() => {
        document.getElementById('IfaIpoPoBrandListInitializeA001').click()
      })
    },
    // A001(初期化) レスポンスハンドラー
    initializeA001ResponseHandler(event) {
      // 権限チェック(BB募集期間マスタ登録の表示・非表示切り替え)
      const SBI_HEAD_OFFICE_PRIV_ID = '1'
      const privId = this.$store.getters.userAccount.medUsers.privId
      this.showSubscriptPeriodMasterRegisterInputButton = (privId === SBI_HEAD_OFFICE_PRIV_ID)

      // ボタンの活性状態の初期化
      this.isTableRowSelected = false

      // Grid Tableの初期化
      if (event.dataList[0].ipoPoBrandList != null) {
        this.pqGridOption.dataModel.data = event.dataList[0].ipoPoBrandList
          .map(ipoPoBrand => {
            return {
              ipoPoTypeName: ipoPoBrand.bbIpoPoKbn,
              brandCode12: ipoPoBrand.bbProductCode,
              brandCodeWith1Name: ipoPoBrand.attachedBrand,
              smokingCigarette: ipoPoBrand.cigaretteShowFlag,
              onlyElectronicDelivery: ipoPoBrand.edelivOnlyFlag,
              changeBbPeriodFlag: ipoPoBrand.changeBbPeriodFlag,
              changePriceFlag: ipoPoBrand.changePriceFlag,
              brandName: ipoPoBrand.bbProductName,
              maxAllocation: ipoPoBrand.maxAllocation,
              unit: ipoPoBrand.bbStock,
              bbPresentationFromTo: ipoPoBrand.bbPresentation,
              acceptStatus: ipoPoBrand.status,
              recruitmentPeriodFromTo: ipoPoBrand.bbPeriod,
              paymentDate: ipoPoBrand.paymentDate,
              bookBuildingPresentationFrom: ipoPoBrand.bbPresentationFrom,
              sellBuyUnitType: ipoPoBrand.bbUnitKbn
            }
          }).sort((val1, val2) => {
            if (val1.bookBuildingPresentationFrom === val2.bookBuildingPresentationFrom) {
              return val1.brandCode12 - val2.brandCode12
            } else {
              return val2.bookBuildingPresentationFrom - val1.bookBuildingPresentationFrom
            }
          })
      } else {
        this.pqGridOption.dataModel.data = []
      }

      this.$nextTick(() => {
        this.$refs['ipopoGridTable'].refreshView()
      })
    },

    // A001(初期化) レスポンスエラーハンドラー
    initializeA001ResponseErrorHandler(event) {
      const errorInfo = {
        title: this.form.title.name,
        message: event.message
      }
      this.$emit('initializeError', errorInfo)
    },

    // A002(BB申込) レスポンスハンドラー
    bbApplicationA002ResponseHandler() {
      document.getElementById('IfaBbApplyInputInitializeA001').click()
    },

    // A002(BB申込) 遷移先レスポンスハンドラー
    bbApplyInputInitializeA001ResponseHandler() {
      const bbApplyInputRequest = new IfaBbApplyInputA001RequestModel(this.form)
      bbApplyInputRequest.brandCode = this.form.brandCode12
      this.dialogbbApplyNewVisible = true
      this.$nextTick(() => {
        this.$refs['ifaBbApplyInput'].setupFromIpopoList(bbApplyInputRequest)
      })
    },

    // A003(BB申込(一括登録)) 遷移先レスポンスハンドラー
    // TODO: SUB0204_01-03_1BB申込(一括登録)を作成する際に実装してください。
    bbApplyCsvMassRegisterA002ResponseHandler(response) {
      this.ifaBbApplyCsvMassRegisterComment = response.dataList[0]
      this.dialogbbApplyCsvBatchUploadVisible = true
      this.$nextTick(() => {
        this.$refs['ifaBbApplyCsvMassRegister'].firstShowMessage()
      })
    },

    // A004(BB募集期間マスタ登録) レスポンスハンドラー
    bbSolicitationPeriodMasterRegistrationA004ResponseHandler() {
      this.$nextTick(() => {
        document.getElementById('IfaSubscriptPeriodMasterRegisterInputInitializeA001').click()
      })
    },
    // BB募集期間マスタ登録 A001(初期化) レスポンスハンドラー
    subscriptPeriodMasterRegisterInputInitializeA001(event) {
      this.dialogipopoBBPeriodMasterVisible = true
      this.subscriptPeriodMasterRegisterInputResponseData =
      {
        ...event.dataList[0],
        'brandCode': this.form.brandCode12,
        'bookBuildingPresentationFromForKeep': this.form.bookBuildingPresentationFrom
      }
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: 'IPO/PO銘柄一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  selectionModel: { type: 'row', mode: 'single' },
  topVisible: false,
  wrap: false
}
obj.colModel = [
  {
    title: '区分',
    dataIndx: 'ipoPoTypeName',
    width: 70,
    editable: false,
    halign: 'center',
    align: 'center',
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    render: function(ui) {
      if (ui.rowData.ipoPoTypeName === '' || ui.rowData.ipoPoTypeName === null) {
        return '-'
      }
      return getCodeValue('IPO_PO_TYPE', 1, ui.rowData.ipoPoTypeName) || '-'
    }
  },
  {
    title: '銘柄コード',
    dataIndx: 'brandCode12',
    width: 110,
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '1付き',
    dataIndx: 'brandCodeWith1Name',
    width: 70,
    editable: false,
    halign: 'center',
    align: 'center',
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    render: function(ui) {
      if (ui.rowData.brandCodeWith1Name === '' || ui.rowData.brandCodeWith1Name === null) {
        return '-'
      }
      return getCodeValue('BRAND_CODE_WITH_1', 2, ui.rowData.brandCodeWith1Name) || '-'
    }
  },
  {
    title: '開示有無',
    dataIndx: 'smokingCigarette',
    width: 90,
    editable: false,
    halign: 'center',
    align: 'center',
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    render: function(ui) {
      if (ui.rowData.smokingCigarette === '' || ui.rowData.smokingCigarette === null) {
        return '-'
      }
      return getCodeValue('CIGARETTE_DISCLOSE_FLAG', 2, ui.rowData.smokingCigarette) || '-'
    }
  },
  {
    title: '電子交付のみ',
    dataIndx: 'onlyElectronicDelivery',
    width: 100,
    editable: false,
    halign: 'center',
    align: 'center',
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    render: function(ui) {
      if (ui.rowData.onlyElectronicDelivery === '' || ui.rowData.onlyElectronicDelivery === null) {
        return '-'
      }
      return getCodeValue('ONLY_ELECTRONIC_DOC_FLAG', 2, ui.rowData.onlyElectronicDelivery) || '-'
    }
  },
  {
    title: '期間変更',
    dataIndx: 'changeBbPeriodFlag',
    width: 100,
    editable: false,
    halign: 'center',
    align: 'center',
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    render: function(ui) {
      if (ui.rowData.changeBbPeriodFlag === '' || ui.rowData.changeBbPeriodFlag === null) {
        return '-'
      }
      return getCodeValue('PERIOD_CHANGE_INFO', 2, ui.rowData.changeBbPeriodFlag) || '-'
    }
  },
  {
    title: '価格変更',
    dataIndx: 'changePriceFlag',
    width: 100,
    editable: false,
    halign: 'center',
    align: 'center',
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    render: function(ui) {
      if (ui.rowData.changePriceFlag === '' || ui.rowData.changePriceFlag === null) {
        return '-'
      }
      return getCodeValue('PRICE_CHANGE_INFO', 2, ui.rowData.changePriceFlag) || '-'
    }
  },
  {
    title: '銘柄名',
    dataIndx: 'brandName',
    width: 435,
    editable: false,
    halign: 'center',
    align: 'left',
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    }
  },
  {
    title: '配分上限株数',
    dataIndx: 'maxAllocation',
    width: 190,
    editable: false,
    halign: 'center',
    align: 'center',
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    render: (ui) => {
      const rowData = ui.rowData
      const maxAllocation = ifaFormatUtils.withCommaInteger(rowData.maxAllocation)
      if (maxAllocation !== '' && maxAllocation !== null) {
        return maxAllocation + rowData.sellBuyUnitType
      }
      return '上限なし'
    }
  },
  {
    title: '売買単位',
    dataIndx: 'unit',
    width: 120,
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const rowData = ui.rowData
      const unit = ifaFormatUtils.withCommaInteger(rowData.unit ? rowData.unit : '-')
      if (unit !== '-') {
        return unit + rowData.sellBuyUnitType
      }
      return unit
    }
  },
  {
    title: 'BB申込期間',
    dataIndx: 'bbPresentationFromTo',
    width: 280,
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: 'ステータス',
    dataIndx: 'acceptStatus',
    width: 100,
    editable: false,
    halign: 'center',
    align: 'center',
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    render: function(ui) {
      const acceptStatus = ui.rowData.acceptStatus
      if (!acceptStatus || acceptStatus === ' ') {
        return '-'
      } else {
        return acceptStatus
      }
    }
  },
  {
    title: '募集期間',
    dataIndx: 'recruitmentPeriodFromTo',
    width: 200,
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      const recruitmentPeriodFromTo = ui.rowData.recruitmentPeriodFromTo ? ui.rowData.recruitmentPeriodFromTo : '-'
      if (recruitmentPeriodFromTo === ' ～ ') {
        return '-'
      }
      return recruitmentPeriodFromTo
    }
  },
  {
    title: '入金予定日<br>（募集最終日）',
    dataIndx: 'paymentDate',
    width: 110,
    dataType: function(val1, val2) {
      return ifaUtils.nullSorting(val1, val2)
    },
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: 'ブックビルディング申込期間（開始）',
    dataIndx: 'bookBuildingPresentationFrom',
    dataType: 'string',
    hidden: true
  }
]
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>
<style scoped>
.btn_area {
  margin: 10px;
}
</style>
