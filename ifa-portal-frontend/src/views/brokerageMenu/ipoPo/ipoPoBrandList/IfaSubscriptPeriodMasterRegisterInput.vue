<template>
  <div>
    <!-- A004 OKのリクエスト -->
    <ifa-requester
      id="IfaSubscriptPeriodMasterRegisterInputOkA004"
      action-id="SUB0204_01-04_1#A004"
      action-type="requestAction"
      :request-model="IfaSubscriptPeriodMasterRegisterInputA004RequestModel"
      msg-title="BB募集期間情報マスタ"
      @response-handler="responseHandlerA004($event)"
      @response-error-handler="responseErrorHandlerA004"
    >
    </ifa-requester>
    <el-dialog
      v-model="vmIsVisible"
      :close-on-click-modal="false"
      :show-close="false"
      width="1600px"
      :before-close="onDialogClose"
      @open="defaultSet"
    >
      <!-- 戻るボタン -->
      <el-row>
        <ifa-button
          class="form-button__wrapper"
          color="secondary"
          text="戻る"
          padding="100px 10px"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-row>
      <!-- BB訂正・取消  -->
      <caption-card
        :caption="form.title.name"
        text-size="20px"
        text-color="#0058a2"
        background-color="Menu"
      >
        <el-form
          label-position="right"
          label-width="150px"
        >
          <el-row>
            <!-- 銘柄情報 -->
            <el-row class="info_section">
              <el-row class="section_title">
                <div><label>銘柄情報</label></div>
              </el-row>
              <el-card style="padding-top: 15px; box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;">
                <el-row
                  type="flex"
                  class="brand_info"
                  justify="space-around"
                >
                  <el-col>
                    <el-form-item
                      label="銘柄："
                    >
                      <span>{{ $_out(form.brand) }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="brand_info"
                  justify="space-around"
                >
                  <el-col :span="8">
                    <el-form-item
                      label="申込単位："
                    >
                      <span>{{ $_out(form.applyUnit) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item
                      label="仮条件： "
                    >
                      <span>{{ $_out(form.assumeCondition) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4"></el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="brand_info"
                  justify="space-around"
                >
                  <el-col :span="9">
                    <el-form-item
                      label="BB申込期間："
                    >
                      <div style="overflow-wrap: break-word; width: 16rem;"> {{ $_out(form.bookBuildingApplyPeriod) }}</div>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="8"
                    :pull="1"
                  >
                    <el-form-item
                      label="抽選日："
                    >
                      <span>{{ form.bbLotDate !== '' && form.bbLotDate !== null ? $_out($_getFormattedDateTimeValue(form.bbLotDate, 'datetime12')) + '～': '' }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7"></el-col>
                </el-row>
              </el-card>
            </el-row>
          </el-row>
        </el-form>
        <el-form
          ref="form"
          :model="form"
          :inline="true"
          :rules="rules"
          label-position="left"
          label-width="203px"
        >
          <!-- 募集期間情報(入力フォーム) -->
          <el-row
            class="info_section"
            style="margin-top: 0px; width: initial;"
          >
            <el-row class="section_title">
              <div><label>募集期間情報</label></div>
            </el-row>
            <el-card style="box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;">
              <el-row class="input_field">
                <el-col :span="5">
                  <el-form-item
                    label="銘柄コード１付き"
                    prop="brandCodeWith1Toggle"
                    label-width="150px"
                  >
                    <el-switch v-model="inputField.brandCodeWith1Toggle"></el-switch>
                  </el-form-item>
                </el-col>
                <el-col :span="4">
                  <el-form-item
                    label="たばこ開示"
                    prop="smokingCigaretteToggle"
                    label-width="100px"
                  >
                    <el-switch v-model="inputField.smokingCigaretteToggle"></el-switch>
                  </el-form-item>
                </el-col>
                <el-col :span="3">
                  <el-form-item
                    label="電子交付のみ"
                    prop="onlyElectronicDelivery"
                    label-width="107px"
                  >
                    <el-switch
                      v-model="inputField.onlyElectronicDeliveryToggle"
                      :disabled="disabled.onlyElectronicDeliveryDisabled"
                    ></el-switch>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <ifa-input-quantity
                    id="maxAllocation"
                    v-model="inputField.maxAllocation"
                    size="small"
                    support
                    :max="9999999999"
                    :min="step.maxAllocationStep"
                    :domain="IfaVolume10DomainModel"
                    :step="step.maxAllocationStep"
                    :initial-step="step.maxAllocationStep"
                    style="width:15.75rem;"
                    label="配分上限株数"
                    prop="maxAllocation"
                  ></ifa-input-quantity>
                  <el-tag
                    class="max_allocation_form"
                    type="danger"
                  >※上限なしの場合、未入力
                  </el-tag>
                </el-col>
              </el-row>
              <el-row class="input_field">
                <el-col :span="12">
                  <ifa-date-picker
                    v-model="inputField.bbPeriodFrom"
                    class="bb_period_from_form"
                    unlink-panels=""
                    size="small"
                    label="募集期間(FROM-TO)"
                    prop="bbPeriodFrom"
                  ></ifa-date-picker>
                  <span> ～ </span>
                  <ifa-date-picker
                    v-model="inputField.bbPeriodTo"
                    unlink-panels=""
                    size="small"
                    prop="bbPeriodTo"
                    @input="bbPeriodToUpdate()"
                  ></ifa-date-picker>
                </el-col>
                <el-col :span="6">
                  <ifa-input-amount
                    id="issueBbPrice"
                    v-model="inputField.issueBbPrice"
                    size="small"
                    support
                    :max="9999999999"
                    :min="0.01"
                    :disabled="disabled.issueBbPriceDisabled"
                    :domain="IfaCurrency122DigitsBDomainModel"
                    :step="0.01"
                    :initial-step="0.01"
                    label="発行価格"
                    prop="issueBbPrice"
                  >
                  </ifa-input-amount>
                </el-col>
              </el-row>
              <el-row class="input_field">
                <el-col :span="12">
                  <ifa-date-picker
                    v-model="inputField.depositScheduleDate"
                    size="small"
                    label="入金予定日(募集最終日)"
                    prop="depositScheduleDate"
                  ></ifa-date-picker>
                </el-col>
                <el-col :span="6">
                  <ifa-input-amount
                    id="comm"
                    v-model="inputField.comm"
                    size="small"
                    support
                    :max="9999999999999"
                    :min="0"
                    :domain="IfaCurrency163DigitsBDomainModel"
                    :step="0.001"
                    :initial-step="0"
                    label="手数料"
                    prop="comm"
                  >
                  </ifa-input-amount>
                </el-col>
              </el-row>
              <el-row class="input_field">
                <el-col>
                  <ifa-date-picker
                    v-model="inputField.listedDate"
                    size="small"
                    label="上場日(受渡期日)"
                    prop="listedDate"
                  ></ifa-date-picker>
                </el-col>
              </el-row>
              <el-row>
                <el-col>
                  <ifa-date-time-picker
                    v-model="inputField.ifaBookBuildingPresentationFrom"
                    class="ifa_book_building_presentation_from_form"
                    unlink-panels=""
                    size="small"
                    label="IFA・BB申込期間(FROM-TO)"
                    prop="ifaBookBuildingPresentationFrom"
                    :domain="IfaDatetime12DomainModel"
                    :disabled="true"
                  ></ifa-date-time-picker>
                  <span> ～ </span>
                  <ifa-date-time-picker
                    v-model="inputField.ifaBookBuildingPresentationTo"
                    class="ifa_book_building_presentation_from_form"
                    unlink-panels=""
                    size="small"
                    prop="ifaBookBuildingPresentationTo"
                    :domain="IfaDatetime12DomainModel"
                  ></ifa-date-time-picker>
                </el-col>
              </el-row>
              <el-row>
                <el-col
                  :span="3"
                  :offset="1"
                >
                  期間変更
                  <el-switch
                    v-model="inputField.changeBbPeriodFlag"
                    class="ml-2"
                    active-value="1"
                    inactive-value="0"
                  >
                  </el-switch>
                </el-col>
                <el-col :span="20">
                  <ifa-input-text
                    v-model="inputField.changeBbPeriodMsg"
                    name="summaryAny"
                    type="textarea"
                    size="small"
                    :disabled="changeBbPeriodMsgDisable"
                    style="width: 83em;"
                    :domain="IfaText400DomainModel"
                  >
                  </ifa-input-text>
                </el-col>
              </el-row>
              <el-row>
                <el-col
                  :span="3"
                  :offset="1"
                >
                  価格変更
                  <el-switch
                    v-model="inputField.changePriceFlag"
                    class="ml-2"
                    active-value="1"
                    inactive-value="0"
                  >
                  </el-switch>
                </el-col>
                <el-col :span="20">
                  <ifa-input-text
                    v-model="inputField.changePriceMsg"
                    name="summaryAny"
                    type="textarea"
                    size="small"
                    :disabled="changePriceMsgDisable"
                    style="width: 83em;"
                    :domain="IfaText400DomainModel"
                  >
                  </ifa-input-text>
                </el-col>
              </el-row>
            </el-card>
          </el-row>
          <el-row>
            <ifa-button
              text="登録"
              action-type="originalAction"
              @app-action-handler="btnAdd('form')"
            ></ifa-button>
          </el-row>
        </el-form>
      </caption-card>
    </el-dialog>
    <!-- 確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="dialogConfirmVisible"
      title="登録確認"
      message="募集期間情報を登録しますか？"
      @close-modal-ok="okA004"
      @close-modal-cancel="dialogConfirmVisible = false"
    ></ifa-ok-cancel-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard'
import IfaVolume10DomainModel from '@/resource/domain/IfaVolume10DomainModel.json'
import IfaCurrency122DigitsBDomainModel from '@/resource/domain/IfaCurrency122DigitsBDomainModel.json'
import IfaCurrency163DigitsBDomainModel from '@/resource/domain/IfaCurrency163DigitsBDomainModel.json'
import IfaDatetime12DomainModel from '@/resource/domain/IfaDatetime12DomainModel.json'
import { IfaSubscriptPeriodMasterRegisterInputFormModel } from './js/IfaSubscriptPeriodMasterRegisterInputFormModel.js'
import { IfaSubscriptPeriodMasterRegisterInputA004RequestModel } from './js/IfaSubscriptPeriodMasterRegisterInputA004RequestModel.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction'
import { getMessage } from '@/utils/errorHandler'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import IfaText400DomainModel from '@/resource/domain/IfaText400DomainModel.json'

export default {
  components: {
    captionCard,
    IfaOkCancelDialog
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    param: {
      type: Object,
      required: true
    }
  },
  emits: ['close-modal', 'update:isVisible', 'update-brand-list'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      IfaVolume10DomainModel: IfaVolume10DomainModel,
      IfaCurrency122DigitsBDomainModel: IfaCurrency122DigitsBDomainModel,
      IfaCurrency163DigitsBDomainModel: IfaCurrency163DigitsBDomainModel,
      IfaDatetime12DomainModel: IfaDatetime12DomainModel,
      dialogConfirmVisible: false,
      IfaText400DomainModel,
      form: new IfaSubscriptPeriodMasterRegisterInputFormModel(),
      step: {
        maxAllocationStep: '1' // 配分上限株数　増加値
      },
      disabled: {
        issueBbPriceDisabled: true, // 発行価格　活性非活性
        onlyElectronicDeliveryDisabled: true // 電子交付のみ　活性非活性
      },
      inputField: {
        brandCodeWith1Toggle: false, // 銘柄コード１付き
        smokingCigaretteToggle: false, // たばこ開示
        onlyElectronicDeliveryToggle: false, // 電子交付のみ
        maxAllocation: '', // 配分上限株数
        issueBbPrice: '', // 発行価格
        comm: '', // 手数料
        listedDate: '', // 上場日
        depositScheduleDate: '', // 入金予定日
        updateDate: '', // 更新日
        bbPeriodFrom: '', // 募集期間FROM
        bbPeriodTo: '', // 募集期間To
        ifaBookBuildingPresentationFrom: '', // IFAのブックビルディング申込期間（開始）
        ifaBookBuildingPresentationTo: '', // IFAのブックビルディング申込期間（終了）
        changePriceFlag: '0',
        changePriceMsg: '',
        changeBbPeriodFlag: '0',
        changeBbPeriodMsg: ''
      },
      rules: {
        bbPeriodFrom: [{ type: 'date', required: true, validator: this.bbPeriodFromValidator, trigger: 'change' }],
        bbPeriodTo: [{ type: 'date', required: true, validator: this.bbPeriodToValidator, trigger: 'change' }],
        depositScheduleDate: [{ type: 'date', required: false, validator: this.depositScheduleDateValidator, trigger: 'change' }],
        listedDate: [{ type: 'date', required: false, validator: this.listedDateValidator, trigger: 'change' }],
        ifaBookBuildingPresentationFrom: [{ type: 'date', required: true, validator: this.ifaBookBuildingPresentationFromValidator, trigger: 'change' }],
        ifaBookBuildingPresentationTo: [{ type: 'date', required: true, validator: this.ifaBookBuildingPresentationToValidator, trigger: 'change' }]
      }
    }
  },
  computed: {
    IfaSubscriptPeriodMasterRegisterInputA004RequestModel() {
      return new IfaSubscriptPeriodMasterRegisterInputA004RequestModel(
        {
          'brandCode': this.form.brandCode, // 銘柄コード（hidden項目）
          'bookBuildingPresentationFrom': this.form.bookBuildingPresentationFromForKeep, // ブックビルディング申込期間（開始）（hidden項目）
          'brandCodeWith1': this.inputField.brandCodeWith1Toggle ? '1' : null, // 銘柄コード１付き
          'smokingCigarette': this.inputField.smokingCigaretteToggle ? '1' : null, // たばこ開示
          'onlyElectronicDelivery': this.inputField.onlyElectronicDeliveryToggle ? '1' : null, // 電子交付のみ
          'maxAllocation': this.inputField.maxAllocation, // 配分上限株数
          'depositScheduleDate': this.getYYYYMMDDFormat(this.inputField.depositScheduleDate), // 入金予定日
          'bbPeriodFrom': this.getYYYYMMDDFormat(this.inputField.bbPeriodFrom), // 募集期間FROM
          'bbPeriodTo': this.getYYYYMMDDFormat(this.inputField.bbPeriodTo), // 募集期間TO
          'issueBbPrice': this.inputField.issueBbPrice, // 発行価格
          'listedDate': this.getYYYYMMDDFormat(this.inputField.listedDate), // 上場日
          'ifaBookBuildingPresentationFrom': getFormattedDateTimeValue(this.inputField.ifaBookBuildingPresentationFrom, 'datetime12'), //  IFAのブックビルディング申込期間（開始）
          'ifaBookBuildingPresentationTo': getFormattedDateTimeValue(this.inputField.ifaBookBuildingPresentationTo, 'datetime12'), //  IFAのブックビルディング申込期間（終了）
          'comm': this.inputField.comm, // 手数料
          'updateDate': this.inputField.updateDate, // 更新日
          'issueBbPriceHiddenItem': this.form.issueBbPrice, // 発行価格（hidden項目）
          'issuePriceType': this.form.issuePriceType, // 発行価格区分（hidden項目）
          'onlyElectronicDeliveryHiddenItem': this.form.onlyElectronicDelivery, // 電子交付のみ（hidden項目）
          'deliveryDueDate': this.form.deliveryDueDate, // 受渡期日(hidden)
          'changePriceFlag': this.inputField.changePriceFlag === '1' ? this.inputField.changePriceFlag : null,
          'changePriceMsg': this.inputField.changePriceMsg,
          'changeBbPeriodFlag': this.inputField.changeBbPeriodFlag === '1' ? this.inputField.changeBbPeriodFlag : null,
          'changeBbPeriodMsg': this.inputField.changeBbPeriodMsg
        }
      )
    },
    changePriceMsgDisable() {
      return this.inputField.changePriceFlag === '0'
    },
    changeBbPeriodMsgDisable() {
      return this.inputField.changeBbPeriodFlag === '0'
    }
  },
  watch: {
    changePriceMsgDisable(newValue) {
      if (newValue) {
        this.inputField.changePriceMsg = ''
      }
    },
    changeBbPeriodMsgDisable(newValue) {
      if (newValue) {
        this.inputField.changeBbPeriodMsg = ''
      }
    }
  },
  methods: {
    onDialogClose() {
      this.$refs['form'].clearValidate()
      this.$emit('close-modal')
    },
    defaultSet() {
      Object.assign(this.form, this.param)
      // 銘柄コード１付き
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0 && this.form.brandCodeWith1 === '1') {
        this.inputField.brandCodeWith1Toggle = true
      } else {
        this.inputField.brandCodeWith1Toggle = false
      }
      // たばこ開示
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0 && this.form.smokingCigarette === '1') {
        this.inputField.smokingCigaretteToggle = true
      } else {
        this.inputField.smokingCigaretteToggle = false
      }
      // 電子交付のみ
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0 && this.form.onlyElectronicDelivery === '1') {
        this.inputField.onlyElectronicDeliveryToggle = true
      } else {
        this.inputField.onlyElectronicDeliveryToggle = false
      }
      if (Number(this.form.subscriptInputCount) > 0) {
        this.disabled.onlyElectronicDeliveryDisabled = true
      } else {
        this.disabled.onlyElectronicDeliveryDisabled = false
      }
      // 配分上限株数
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0) {
        this.inputField.maxAllocation = this.form.maxAllocation || ''
      } else {
        this.inputField.maxAllocation = ''
      }
      // 募集期間FROM
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0) {
        this.inputField.bbPeriodFrom = getFormattedDateValue(this.form.bbPeriodFrom, 'date8')
      } else {
        this.inputField.bbPeriodFrom = ''
      }
      // 募集期間TO
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0) {
        this.inputField.bbPeriodTo = getFormattedDateValue(this.form.bbPeriodTo, 'date8')
      } else {
        this.inputField.bbPeriodTo = ''
      }
      if (this.form.unit !== '' && this.form.unit !== null) {
        this.step.maxAllocationStep = this.form.unit
      }
      // 発行価格
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0) {
        this.inputField.issueBbPrice = this.form.issueBbPrice || ''
      } else {
        this.inputField.issueBbPrice = ''
      }
      if (
        (this.form.issuePriceType === '2' && Number(this.form.subscriptInputCount) > 0) ||
        this.form.issuePriceType === '1') {
        this.disabled.issueBbPriceDisabled = true
      } else {
        this.disabled.issueBbPriceDisabled = false
      }
      // 入金予定日（募集最終日）
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0) {
        this.inputField.depositScheduleDate = getFormattedDateValue(this.form.depositScheduleDate, 'date8')
        if (this.inputField.depositScheduleDate === '' || this.inputField.depositScheduleDate === null) {
          this.inputField.depositScheduleDate = getFormattedDateValue(this.form.bbPeriodTo, 'date8')
        }
      } else {
        this.inputField.depositScheduleDate = ''
      }
      // 手数料
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0) {
        this.inputField.comm = this.form.comm || ''
      } else {
        this.inputField.comm = ''
      }
      // 上場日（受渡期日）
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0) {
        this.inputField.listedDate = getFormattedDateValue(this.form.listedDate, 'date8')
      } else {
        this.inputField.listedDate = ''
      }
      // IFAのブックビルディング申込期間（開始）
      this.inputField.ifaBookBuildingPresentationFrom = getFormattedDateTimeValue(this.form.bookBuildingPresentationFrom, 'datetime14')

      // IFAのブックビルディング申込期間（終了）
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0) {
        this.inputField.ifaBookBuildingPresentationTo = getFormattedDateTimeValue(this.form.ifaBookBuildingPresentationTo, 'datetime14')
      } else {
        this.inputField.ifaBookBuildingPresentationTo = ''
      }
      // 更新日
      if (Number(this.form.subscriptPeriodInfoRegisterCount) > 0) {
        this.inputField.updateDate = this.form.updateDate
      } else {
        this.inputField.updateDate = ''
      }
      // 期間変更情報（フラグ）
      // 価格変更情報（フラグ）
      if (this.form.subscriptPeriodInfoRegisterCount && this.form.subscriptPeriodInfoRegisterCount !== '0') {
        this.inputField.changeBbPeriodFlag = this.form.changeBbPeriodFlag || '0'
        this.inputField.changePriceFlag = this.form.changePriceFlag || '0'
      }
      // 期間変更情報（メッセージ）
      // 価格変更情報（メッセージ）
      this.inputField.changePriceMsg = this.inputField.changePriceFlag === '1' ? this.form.changePriceMsg : ''
      this.inputField.changeBbPeriodMsg = this.inputField.changeBbPeriodFlag === '1' ? this.form.changeBbPeriodMsg : ''
    },
    // 登録ボタン押下
    btnAdd(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.dialogConfirmVisible = true
        } else {
          return false
        }
      })
    },
    bbPeriodFromValidator(rule, value, callback) {
      if (this.inputField.bbPeriodFrom.length === 0) {
        callback('募集期間FROMを入力してください。')
      }
      const dateFrom = new Date(this.inputField.bbPeriodFrom) // 募集期間FROMをパース
      const dateTo = new Date(this.inputField.bbPeriodTo) // 募集期間TOをパース
      // 募集期間FROM > 募集期間TO
      if (dateFrom > dateTo) {
        callback(new Error(getMessage('errors.dateOverRange', ['募集期間（FROM－TO）'])))
      }
      // OK
      callback()
    },
    bbPeriodToValidator(rule, value, callback) {
      if (this.inputField.bbPeriodTo.length === 0) {
        callback('募集期間TOを入力してください。')
      }
      const dateFrom = new Date(this.inputField.bbPeriodFrom) // 募集期間FROMをパース
      const dateTo = new Date(this.inputField.bbPeriodTo) // 募集期間TOをパース
      // 募集期間FROM > 募集期間TO
      if (dateFrom > dateTo) {
        callback(new Error(getMessage('errors.dateOverRange', ['募集期間（FROM－TO）'])))
      }
      // OK
      callback()
    },
    depositScheduleDateValidator(rule, value, callback) {
      const depositScheduleDate = new Date(this.inputField.depositScheduleDate) // 入金予定日(募集最終日)
      const listedDate = new Date(this.inputField.listedDate) // 上場日(受渡期日)
      // 入金予定日(募集最終日) >= 上場日(受渡期日)
      if (depositScheduleDate >= listedDate) {
        callback(new Error(getMessage('errors.dateOverRange', ['入金予定日（募集最終日）と上場日（受渡期日）'])))
      }
      // 入金予定日(募集最終日) > 受渡期日
      if (this.form.deliveryDueDate !== null && this.form.deliveryDueDate !== '') {
        const deliveryDueDate = new Date(getFormattedDateValue(this.form.deliveryDueDate, 'date8')) // 受渡期日
        if (depositScheduleDate > deliveryDueDate) {
          callback(new Error(getMessage('errors.beforeDate', ['入金予定日（募集最終日）', '受渡期日'])))
        }
      }
      // OK
      callback()
    },
    listedDateValidator(rule, value, callback) {
      const depositScheduleDate = new Date(this.inputField.depositScheduleDate) // 入金予定日(募集最終日)
      const listedDate = new Date(this.inputField.listedDate) // 上場日(受渡期日)
      // 入金予定日(募集最終日) >= 上場日(受渡期日)
      if (depositScheduleDate >= listedDate) {
        callback(new Error(getMessage('errors.dateOverRange', ['入金予定日（募集最終日）と上場日（受渡期日）'])))
      }
      // 受渡期日 > 上場日(受渡期日)
      if (this.form.deliveryDueDate !== null && this.form.deliveryDueDate !== '') {
        const deliveryDueDate = new Date(getFormattedDateValue(this.form.deliveryDueDate, 'date8')) // 受渡期日
        if (deliveryDueDate > listedDate) {
          callback(new Error(getMessage('errors.dateOverRange', ['受渡期日と上場日（受渡期日）'])))
        }
      }
      // OK
      callback()
    },
    ifaBookBuildingPresentationFromValidator(rule, value, callback) {
      if (this.inputField.ifaBookBuildingPresentationFrom.length === 0) {
        callback('IFA・BB申込期間（FROM）を入力してください。')
      }
      // IFA・BB申込期間（FROM） > IFA・BB申込期間（TO）
      const dateFrom = new Date(this.inputField.ifaBookBuildingPresentationFrom) // IFA・BB申込期間（FROM）をパース
      const dateTo = new Date(this.inputField.ifaBookBuildingPresentationTo) // IFA・BB申込期間（TO）をパース
      if (dateFrom > dateTo) {
        callback(new Error(getMessage('errors.dateOverRange', ['IFA・BB申込期間（FROM－TO）'])))
      }
      // OK
      callback()
    },
    ifaBookBuildingPresentationToValidator(rule, value, callback) {
      if (this.inputField.ifaBookBuildingPresentationTo.length === 0) {
        callback('IFA・BB申込期間（TO）を入力してください。')
      }
      // IFA・BB申込期間（FROM） > IFA・BB申込期間（TO）
      const dateFrom = new Date(this.inputField.ifaBookBuildingPresentationFrom) // IFA・BB申込期間（FROM）をパース
      const dateTo = new Date(this.inputField.ifaBookBuildingPresentationTo) // IFA・BB申込期間（TO）をパース
      if (dateFrom > dateTo) {
        callback(new Error(getMessage('errors.dateOverRange', ['IFA・BB申込期間（FROM－TO）'])))
      }
      // OK
      callback()
    },
    okA004() {
      this.$nextTick(() => {
        document.getElementById('IfaSubscriptPeriodMasterRegisterInputOkA004').click()
      })
    },
    responseHandlerA004(data) {
      Object.assign(this.form, data[0])
      this.dialogConfirmVisible = false
      this.onDialogClose()
      this.$emit('update-brand-list')
    },
    responseErrorHandlerA004() {
      this.dialogConfirmVisible = false
    },
    // 日付のフォーマットをYYYY/MM/DD → YYYYMMDDに変更
    getYYYYMMDDFormat(date) {
      return (date === '' || date === null ? '' : date.split('/').join(''))
    },
    bbPeriodToUpdate() {
      // 入金予定日（募集最終日）
      if (this.inputField.depositScheduleDate === '' || this.inputField.depositScheduleDate === null) {
        this.inputField.depositScheduleDate = getFormattedDateValue(this.inputField.bbPeriodTo, 'date8')
      }
    }
  }
}

</script>

<style lang="scss" scoped>
@import "@/styles/ipopobbInputForm.scss";

:deep(.ifa-date-picker.small.el-range-editor.el-input__inner) {
  position:unset;
}

:deep(.bb_period_from_form.el-form-item){
  margin-right: 0px;
}

:deep(.ifa_book_building_presentation_from_form.el-form-item) {
  margin-right: 0;
  .el-input__inner {
    width: 100% !important;
  }
}

:deep(.el-col-6){
  max-width: initial;
  flex: initial;
}

.max_allocation_form{
  margin-left: -35px;
}
:deep(.el-date-editor.el-input, .el-date-editor.el-input__wrapper) {
  height: auto
}
</style>
