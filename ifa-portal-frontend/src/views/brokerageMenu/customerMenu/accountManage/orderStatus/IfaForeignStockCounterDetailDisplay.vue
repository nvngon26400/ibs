<template>
  <el-dialog
    v-model="vmIsVisible"
    :title="rowData.title.name"
    :show-close="false"
    :center="true"
    :before-close="backA002"
    :close-on-click-modal="false"
    :class="rowData.tradeKbn === '3' ? 'buy-background-color' : rowData.tradeKbn === '1' ? 'sell-background-color' : ''"
    width="920px"
  >
    <el-row>
      <el-col
        :span="23"
        class="close-button"
      >
        <ifa-button
          text="戻る"
          width="90"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="backA002"
        ></ifa-button>
      </el-col>

      <!-- 顧客情報 -->
      <el-row
        style="font-weight: bold;"
      >
        <el-col>
          <span>{{ nullToEmpty(customerInfo.butenCode) }}{{ nullToEmpty(customerInfo.accountNumber) === '' ? '' : '-' + $_zeroPadding(customerInfo.accountNumber,7) }}</span>
        </el-col>
      </el-row>
      <el-row
        style="font-weight: bold; font-size: 18px;"
      >
        <el-col>
          <el-icon><Avatar></Avatar></el-icon>
          <span>{{ nullToEmpty(customerInfo.customerNameKanji) }}（{{ nullToEmpty(customerInfo.customerNameKana) }}）</span>
        </el-col>
      </el-row>

      <el-card class="orderInfo">
        <el-row
          class="_bold_black_l"
          style="font-size: 18px;"
        >
          <span>銘柄：[{{ rowData.brandCode }}] </span>
          <span>{{ rowData.brandName }}</span>
        </el-row>
        <el-row>
          <el-col
            :span="6"
            class="orderInfoContent"
          >
            <span class="_black_s_bold">取引価格：</span>
            <span>{{ rowData.tradePrice ? `${ifaFormatUtils.withCommaNoneZeroPadding(rowData.tradePrice)}USD`: '' }}</span>
          </el-col>
        </el-row>
        <el-row>
          <el-col
            :span="6.5"
            class="orderInfoContent"
          >
            <span class="_black_s_bold">管理番号：</span>
            <span>{{ nullToEmpty(rowData.manageNumber) }} </span>
          </el-col>
          <el-col
            :offset="1"
            :span="6"
            class="orderInfoContent"
          >
            <span class="_black_s_bold">注文日時：</span>
            <span>{{ nullToEmpty($_getFormattedDateTimeValue(rowData.orderDate, 'datetime14')) }}</span>
          </el-col>
          <el-col
            :offset="1"
            :span="6"
            class="orderInfoContent"
          >
            <span class="_black_s_bold">約定日時：</span>
            <span>{{ nullToEmpty($_getFormattedDateTimeValue(rowData.tradeDateTime, 'datetime14')) }}</span>
          </el-col>
        </el-row>
      </el-card>

      <el-card class="orderContent">
        <el-row class="_bold_black_m">
          <span>注文内容</span>
        </el-row>
        <hr>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>取引種別</span>
          </el-col>
          <el-col
            :span="8"
            :offset="1"
          >
            <span :class="rowData.tradeKbn === '3' ? 'font-color__plus bold' : rowData.tradeKbn === '1' ? 'font-color__minus bold' : ''">
              {{ rowData.tradeKbn === '3' ? $_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, '11') : rowData.tradeKbn === '1' ? $_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, '12') : '' }}
            </span>
          </el-col>
        </el-row>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>数量</span>
          </el-col>
          <el-col
            :span="8"
            :offset="1"
          >
            <span>{{ rowData.quantity ? `${ifaFormatUtils.withCommaInteger(rowData.quantity)}株` : '' }} </span>
          </el-col>
        </el-row>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>約定金額</span>
          </el-col>
          <el-col
            :span="8"
            :offset="1"
          >
            <span>{{ rowData.contractAmount ? `${ifaFormatUtils.withCommaNoneZeroPadding(rowData.contractAmount)}USD` : '' }}</span>
          </el-col>
        </el-row>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>預り区分</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty($_getCodeValue('FOREIGN_DEPOSIT_TYPE', 2, rowData.depositType)) }} </span>
          </el-col>
        </el-row>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>決済区分</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty($_getCodeValue('SETTLEMENT_TYPE', 1, rowData.settlementType)) }}</span>
          </el-col>
        </el-row>
      </el-card>

      <el-card class="orderContent">
        <el-row class="_bold_black_m">
          <span>その他注文内容</span>
        </el-row>
        <hr>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>勧誘区分</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty($_getCodeValue('FOREIGN_STOCK_INVITATION_TYPE', 1, rowData.kanyuKbn)) }}</span>
          </el-col>
        </el-row>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>受注方法</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty($_getCodeValue('FOREIGN_STOCK_ORDER_METHOD_TYPE', 1, rowData.orderMethod)) }}</span>
          </el-col>
        </el-row>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>余力</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ rowData.remainPower }}</span>
          </el-col>
        </el-row>
        <el-row
          v-if="rowData.tradeKbn === '3'"
          class="dotted_border"
        >
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>ワーニング申請取引</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty($_getCodeValue('WARNING_APPLICATION_TRAD', 2, rowData.warningApplyTrade)) }}</span>
          </el-col>
        </el-row>
        <el-row
          v-if="rowData.tradeKbn === '3'"
          class="dotted_border"
        >
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>重要事項の説明</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty($_getCodeValue('IMPORTANT_MATTERS_EXPLAIN', 1, rowData.importantMatterTypeText)) }}</span>
          </el-col>
        </el-row>
        <el-row v-if="rowData.tradeKbn === '3' && rowData.foreignStockYmd && rowData.documentDeliveryDate && rowData.informationDeliveredText"
                class="dotted_border"
        >
          <el-row>
            <el-col
              :span="3"
              class="_black_s_bold label"
            >
              <span>外国証券情報</span>
            </el-col>
            <el-col
              :span="3"
              class="_black_s_bold label"
            >
              <span>版番</span>
            </el-col>
            <el-col
              :span="15"
              :offset="1"
            >
              <span>{{ nullToEmpty($_getFormattedDateValue(rowData.foreignStockYmd, 'date8Kanji')) }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col
              :span="3"
            ></el-col>
            <el-col
              :span="3"
              class="_black_s_bold label"
            >
              <span>交付日</span>
            </el-col>
            <el-col
              :span="15"
              :offset="1"
            >
              <span>{{ nullToEmpty($_getFormattedDateValue(rowData.documentDeliveryDate, 'date8Kanji')) }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col
              :span="3"
            ></el-col>
            <el-col
              :span="3"
              class="_black_s_bold label"
            >
              <span>交付方法</span>
            </el-col>
            <el-col
              :span="15"
              :offset="1"
            >
              <span>{{ nullToEmpty($_getCodeValue('FOREIGN_SECURITY_INFO_ISSUE_METHOD', 1, rowData.informationDeliveredText)) }}</span>
            </el-col>
          </el-row>
        </el-row>

        <el-row
          v-if="rowData.tradeKbn === '3' && rowData.solicitationEtfText"
          class="dotted_border"
        >
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>乗換え勧誘(ETF)</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty($_getCodeValue('ETF_SOLICITING_TRANSFERS', 1, rowData.solicitationEtfText)) }}</span>
          </el-col>
        </el-row>
        <el-row
          v-if="rowData.tradeKbn === '3' && rowData.engPubText && rowData.engPubYmd"
          class="dotted_border"
        >
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>英文開示銘柄</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty($_getCodeValue('ISSUES_DISCLOSED_IN_ENGLISH_BRAND', 3, rowData.engPubText)) }}（説明日：{{ nullToEmpty($_getFormattedDateValue(rowData.engPubYmd, 'date8Kanji')) }}）</span>
          </el-col>
        </el-row>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>摘要(任意)</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty(rowData.summaryAny) }}</span>
          </el-col>
        </el-row>
      </el-card>

      <el-card class="orderContent">
        <el-row class="_bold_black_m">
          <span>注文状況</span>
        </el-row>
        <hr>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>ステータス</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty($_getCodeValue('FOREIGN_STOCK_COUNTER_ORDER_STATUS', 1, rowData.status)) }}</span>
          </el-col>
        </el-row>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>取消理由</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty(rowData.cancelReason) }}</span>
          </el-col>
        </el-row>
        <el-row class="dotted_border">
          <el-col
            :span="6"
            class="_black_s_bold label"
          >
            <span>ステータス訂正コメント</span>
          </el-col>
          <el-col
            :span="15"
            :offset="1"
          >
            <span>{{ nullToEmpty(rowData.comment) }}</span>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    rowData: {
      type: Object,
      required: true
    }
  },
  emits: ['close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      ifaFormatUtils: ifaFormatUtils
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  },
  methods: {
    backA002() {
      this.$emit('close-modal')
    },
    nullToEmpty(value) {
      return value || ''
    }
  }
}
</script>

<style lang="scss" scoped>
// 共通変数定義のimport。
@import '@/styles/variables.scss';

.orderInfo {
  margin: 0.5rem 0;
  background-color: #eee;
  width: 100%;
}
.orderContent {
  width: 100%;
  margin-bottom: 0.5rem;
}
.dotted_border {
  border-bottom: 1px dotted #dddddd;
  padding: 0.5rem 0.2rem 0.2rem 0.5rem;
}
:deep(.el-dialog__header) {
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
.buy-background-color {
  :deep(.el-dialog__header) {
    background-color: #fef0f0;
    font-weight: 700;
  }
  :deep(.el-dialog__body) {
    background-color: #fef0f0;
  }
  :deep(.el-dialog__footer) {
    background-color: #fef0f0;
    display: flex;
  }
}
.sell-background-color {
  :deep(.el-dialog__header) {
    background-color: #ecf5ff;
    font-weight: 700;
  }
  :deep(.el-dialog__body) {
    background-color: #ecf5ff;
  }
  :deep(.el-dialog__footer) {
    background-color: #ecf5ff;
    display: flex;
  }
}
.font-color__plus {
  &.border {
    font-size: 15px;
    padding: 0 0.7rem;
    border: 1px solid $buyOrPlusColor;
  }
}
.font-color__minus {
  &.border {
    font-size: 15px;
    padding: 0 0.7rem;
    border: 1px solid $sellOrMinusColor;
  }
}
._bold_black_l {
  font-size: 16px;
  font-weight: bold;
  padding: 0.4rem 0.4rem;
  color: #313131;
}
._bold_black_m {
  font-size: 15px;
  font-weight: bold;
  padding: 0.4rem 0 0 0.4rem;
  color: #4a4e55;
}
._black_s_bold {
  font-size: 14px;
  font-weight: bold;
  color: #4a4e55;

}
.label {
  padding-left: 1rem;
}
.orderInfoContent {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
  display: flex;
  justify-content: space-between;
  margin: 0 1.2rem;
  padding: 0 0.25rem;
  white-space: nowrap;
}
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
</style>
