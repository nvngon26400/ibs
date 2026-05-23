<template>
  <div class="main">
    <h3>●共通検索テスト</h3>

    <hr>
    <h4>【IfaBaloonIcon】</h4>
    <div class="result">
      <ifa-balloon-icon
        icon-type="info"
        message="単体テストメッセージ"
        title="単体テスト"
      ></ifa-balloon-icon>
    </div>

    <hr>
    <h4>【IfaHelpIcon】</h4>
    <div class="result">
      <ifa-balloon-icon
        message="単体テストメッセージ。"
        :small="false"
      ></ifa-balloon-icon>
    </div>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>【IfaLink】</h4>
    <div
      v-if="!disabled"
      class="result"
    >
      <ifa-link
        :url-id="99"
        :pattern-id="9"
        :param-object="{
          _ControlID: 'WPLETlgR001Control',
          _PageID: 'WPLETlgR001Rlgn50',
          _DataStoreID: 'DSWPLETlgR001Control',
          _ActionID: 'login',
          getFlg: 'on'
        }"
      ></ifa-link>
    </div>

    <hr>
    <h4 style="display: flex;">
      【IfaOkCancelDialog】
      <ifa-input-text
        v-model="okCancelDialog.title"
        label="タイトル"
        size="small"
        required
      ></ifa-input-text>
      <ifa-input-text
        v-model="okCancelDialog.message"
        label="メッセージ"
        size="small"
        required
      ></ifa-input-text>
    </h4>
    <div
      class="result"
      style="margin-bottom: 20px;"
    >
      <ifa-button
        text="Show Dialog"
        color="primary"
        small
        action-type="originalAction"
        @app-action-handler="okCancelDialog.visible = true"
      ></ifa-button>
      <ifa-ok-cancel-dialog
        :is-visible="okCancelDialog.visible"
        :title="okCancelDialog.title"
        :message="okCancelDialog.message"
        @close-modal-ok="closeOkCancelDialog('OK')"
        @close-modal-cancel="closeOkCancelDialog('Cancel')"
      ></ifa-ok-cancel-dialog>
      <span class="label">チェック:</span><span class="value">{{ okCancelDialog.okCancelResult }}</span>
    </div>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4 style="display: flex;">【IfaBrandSearch】</h4>
    <div
      v-if="!disabled"
      class="result"
    >
      <ifa-brand-search
        ref="brandSearch"
        :is-code-lock="isCodeLock"
        :trading-type="brandSearch.tradingType"
        :market-list="[]"
        @change="handleBrandSearchEvent($event)"
      ></ifa-brand-search>
      <div style="margin: 20px 0;">
        <span class="label">イベント:</span><span class="value">{{ brandSearch.event }}</span>
        <span class="label">市場:</span><span class="value">{{ brandSearch.market }}</span>
        <span class="label">詳細:</span><span class="value">{{ brandSearch.detail }}</span>
      </div>
      <div style="display: flex; margin: 20px 0;">
        <div style="display: flex;">
          <ifa-input-radio
            id="codeLock"
            v-model="brandSearch.codeLock"
            label="コードロック"
            code-list-id="original"
            :original-list="[{ key: '0', value: 'Unlock' }, { key: '1', value: 'Lock' }]"
            is-button
            @change="handleChangeCodeLock"
          ></ifa-input-radio>
          <span
            class="value"
            style="margin-left: 16px;"
          >{{ brandSearch.codeLock }}</span>
        </div>
        <div style="display: flex;">
          <ifa-input-radio
            id="tradingType"
            v-model="brandSearch.tradingType"
            label="トレード種別"
            code-list-id="original"
            :original-list="[{ key: '0', value: '現物' }, { key: '1', value: '信用' }]"
            is-button
          ></ifa-input-radio>
          <span
            class="value"
            style="margin-left: 16px;"
          >{{ brandSearch.tradingType }}</span>
        </div>
      </div>
    </div>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4 style="display: flex;">
      【IfaBrandPriceInfo】
      <ifa-input-text
        v-model="brandPriceInfo.brandCode"
        label="銘柄コード"
        size="small"
        required
        :disabled="disabled"
        @change="updateRequest"
      ></ifa-input-text>
      <ifa-input-select
        v-model="brandPriceInfo.market"
        code-list-id="SELECT_MARKET"
        :disp-pattern="1"
        :select-pattern="1"
        label="市場"
        required
        :disabled="disabled"
        @change="updateRequest"
      ></ifa-input-select>
    </h4>
    <div class="result">
      <ifa-brand-price-info
        ref="brandPriceInfo"
        :brand-code="brandPriceInfo.brandCode"
        :market="brandPriceInfo.market"
      ></ifa-brand-price-info>
    </div>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【IfaCommonSearch】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        :disabled="disabled"
        @app-action-handler="submitForm('formCommonSearch')"
      ></ifa-button>
    </h4>
    <el-form
      v-if="!disabled"
      ref="formCommonSearch"
      :model="formCommonSearch"
      :inline="true"
      label-position="left"
    >
      <div class="result">
        <ifa-common-search
          ref="commonSearch"
          display-pattern="pt1"
          list-pattern="pt1"
          :form="formCommonSearch"
          :broker-code-validate="true"
          :emp-code-validate="true"
          :course-validate="true"
        ></ifa-common-search>
      </div>
      <div style="margin-top: 20px;">
        <div class="row"><span class="label">仲介者除外:</span><span class="value">{{ formCommonSearch.chkBrokerCodeExclude }}</span></div>
        <div class="row"><span class="label">仲介者コード:</span><span class="value">{{ formCommonSearch.brokerCode }}</span></div>
        <div class="row"><span class="label">支店コード:</span><span class="value">{{ formCommonSearch.branchCode }}</span></div>
        <div class="row"><span class="label">営業員コード:</span><span class="value">{{ formCommonSearch.empCode }}</span></div>
        <div class="row"><span class="label">部店コード:</span><span class="value">{{ formCommonSearch.butenCode }}</span></div>
        <div class="row"><span class="label">口座番号:</span><span class="value">{{ formCommonSearch.accountNumber }}</span></div>
        <div class="row"><span class="label">顧客名(漢字/カナ):</span><span class="value">{{ formCommonSearch.customerNameKanjiKana }}</span></div>
        <div class="row"><span class="label">顧客名選択方法:</span><span class="value">{{ formCommonSearch.customerNameKanjiKanaTerms }}</span></div>
        <div class="row"><span class="label">取引コース:</span><span class="value">{{ courseSelected }}</span></div>
      </div>
    </el-form>

    <hr>
    <h4>【IfaMessageArea】</h4>
    <div class="result">
      <ifa-message-area
        :main-messages="messageArea.mainMessages"
        :error-messages="messageArea.errorMessages"
        :warning-messages="messageArea.warningMessages"
        :info-messages="messageArea.infoMessages"
      ></ifa-message-area>
    </div>

  </div>
</template>

<script>
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import { IfaCommonSearchTestFormModel } from './IfaCommonSearchTestFormModel.js'
import IfaMessageArea from '@/components/Message/IfaMessageArea.vue'
import IfaBrandPriceInfo from '@/components/Info/IfaBrandPriceInfo.vue'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import IfaBrandSearch from '@/components/SearchCondition/IfaBrandSearch.vue'
export default {
  name: 'IfaCompositePartsTest',
  components: {
    IfaCommonSearch,
    IfaMessageArea,
    IfaBrandPriceInfo,
    IfaOkCancelDialog,
    IfaBrandSearch
  },
  data() {
    return {
      formCommonSearch: new IfaCommonSearchTestFormModel(),
      messageArea: {
        mainMessages: [
          'メインメッセージ1',
          'メインメッセージ2 メインメッセージ2 メインメッセージ2 メインメッセージ2 メインメッセージ2',
          'メインメッセージ3 メインメッセージ3 メインメッセージ3 メインメッセージ3 メインメッセージ3 メインメッセージ3 メインメッセージ3 メインメッセージ3 メインメッセージ3 メインメッセージ3'
        ],
        errorMessages: [
          'エラーメッセージ1',
          'エラーメッセージ2 エラーメッセージ2 エラーメッセージ2 エラーメッセージ2 エラーメッセージ2',
          'エラーメッセージ3 エラーメッセージ3 エラーメッセージ3 エラーメッセージ3 エラーメッセージ3 エラーメッセージ3 エラーメッセージ3 エラーメッセージ3 エラーメッセージ3 エラーメッセージ3'
        ],
        warningMessages: [
          'ワーニングメッセージ1',
          'ワーニングメッセージ2 ワーニングメッセージ2 ワーニングメッセージ2 ワーニングメッセージ2 ワーニングメッセージ2',
          'ワーニングメッセージ3 ワーニングメッセージ3 ワーニングメッセージ3 ワーニングメッセージ3 ワーニングメッセージ3 ワーニングメッセージ3 ワーニングメッセージ3 ワーニングメッセージ3 ワーニングメッセージ3 ワーニングメッセージ3'
        ],
        infoMessages: [
          'インフォメッセージ1',
          'インフォメッセージ2 インフォメッセージ2 インフォメッセージ2 インフォメッセージ2 インフォメッセージ2',
          'インフォメッセージ3 インフォメッセージ3 インフォメッセージ3 インフォメッセージ3 インフォメッセージ3 インフォメッセージ3 インフォメッセージ3 インフォメッセージ3 インフォメッセージ3 インフォメッセージ3'
        ]
      },
      brandPriceInfo: {
        brandCode: '',
        market: ''
      },
      okCancelDialog: {
        visible: false,
        title: '',
        message: '',
        okCancelResult: ''
      },
      brandSearch: {
        event: '',
        detail: '',
        marketList: [],
        market: '',
        codeLock: '0',
        tradingType: '0'
      }
    }
  },
  computed: {
    courseSelected() {
      return this.formCommonSearch.courseSelected.filter(item => item.isSelected).map(item => item.id)
    },
    isCodeLock() {
      return this.brandSearch.codeLock === '1'
    },
    disabled() {
      return process.env.NODE_ENV !== 'development'
    }
  },
  methods: {
    submitForm(formName) {
      // フォームのサブミット前に入力値チェックを行う
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$_logDebug('validation success')
        } else {
          this.$_logDebug('validation fail')
          return false
        }
      })
    },
    closeOkCancelDialog(reason) {
      this.okCancelDialog.okCancelResult = reason
      this.okCancelDialog.visible = false
    },
    handleBrandSearchEvent(param) {
      switch (param.id) {
        case '1':
          this.brandSearch.event = '銘柄詳細'
          this.brandSearchDetail(param.data)
          break
        case '2':
          this.brandSearch.event = '市場選択'
          this.brandSearch.market = this.$_getCodeValue('SELECT_MARKET', 1, param.data) ?? '--'
          break
        case '3':
          this.brandSearch.event = 'リセット'
          this.brandSearch.marketList = []
          this.brandSearch.market = ''
          this.brandSearch.detail = ''
          break
        default:
          this.brandSearch.event = '未知のイベント'
          break
      }
    },
    brandSearchDetail(info) {
      const str = []
      str.push(`銘柄: ${info.brandName} (${info.brandCode})`)
      str.push(info.selectMarketList.map(m => {
        const ur = m.key === info.upperRankMkt ? '*' : ''
        const mkt = m.value
        return ur + mkt
      }).join('/'))
      str.push(info.topixFlg === '1' ? 'TOPIX100' : '')
      str.push(info.regKbn === '1' ? '規制情報' : '')
      this.brandSearch.detail = str.join(', ')
      this.brandSearch.marketList = [...info.selectMarketList]
      const market = info.selectMarketList.length > 0 ? info.selectMarketList[0].key : ''
      this.brandSearch.market = this.$_getCodeValue('SELECT_MARKET', 1, market) ?? '--'
    },
    handleChangeCodeLock(newState) {
      if (newState) {
        this.$refs['brandSearch'].handleRowClick({ brandCode: '9999' })
      }
    },
    updateRequest() {
      this.$nextTick(() => {
        if (this.brandPriceInfo.brandCode && this.brandPriceInfo.market) {
          this.$refs['brandPriceInfo'].updateRequest()
        } else {
          this.$refs['brandPriceInfo'].initData()
        }
      })
    }
  }
}
</script>

<style scope='scss'>
.main {
  margin: 2rem;
}
.result {
  height: 100%;
  vertical-align: middle;
}
.row {
  height: 40px;
}
.label {
  padding-left: 6.5rem;
  padding-right: 1rem;
}
.value {
  padding: 0.4rem;
  background-color: white;
  border: 1px solid blue;
}
</style>
