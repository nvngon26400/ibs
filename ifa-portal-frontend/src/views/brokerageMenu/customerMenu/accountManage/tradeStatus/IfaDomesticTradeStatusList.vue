<template>
  <!-- 国内約定状況一覧 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <el-row justify="end">
        <el-col v-if="(customerInfo.jrIsaContractType === '1' && customerInfo.withdrawalRestrictionCancelFlag === '1') &&
                  (form.spotMarginTradeList.length != 0 || form.receiptDeliveryTradeList.length != 0)"
                :span="6"
        >
          <!-- 口座種別選択 -->
          <ifa-input-radio
            :model-value="form.accountTypeRadio"
            :code-list-id="'ACCOUNT_TYPE'"
            :disp-pattern="1"
            :select-pattern="1"
            @change="a003Action($event)"
          ></ifa-input-radio>
        </el-col>
        <el-col v-if="form.spotMarginTradeList.length != 0 || form.receiptDeliveryTradeList.length != 0"
                :span="6"
        >
          <ifa-button
            v-if="isExpand() && !isNoData()"
            id="btnCloseAll"
            action-type="originalAction"
            text="全て閉じる"
            color="secondary"
            icon="Remove"
            small
            @app-action-handler="expandCollapseAll()"
          ></ifa-button>
          <ifa-button
            v-if="!isExpand() && !isNoData()"
            id="btnOpenAll"
            action-type="originalAction"
            text="全て開く"
            color="secondary"
            icon="CirclePlus"
            small
            @app-action-handler="expandCollapseAll()"
          ></ifa-button>
        </el-col>
        <el-col :span="12">
          <div class="update-button">
            <ifa-button
              id="btnRefresh"
              action-type="requestAction"
              text="更新"
              color="primary"
              icon="RefreshRight"
              :action-id="'SUB0202_0105-01#A001'"
              @response-handler="a001ActionHandler"
            ></ifa-button>
          </div>
        </el-col>
      </el-row>
      <!-- 現物・信用 -->
      <el-row v-if="spotMarginTradeList.length != 0">
        <el-row>
          <el-col :span="24">
            <div
              class="clickable"
              @click="visible.executedCtMt = !visible.executedCtMt"
            >
              <el-icon
                v-if="visible.executedCtMt"
                class="header-icon"
              ><Remove></Remove></el-icon>
              <el-icon
                v-else
                class="header-icon"
              ><CirclePlus></CirclePlus></el-icon>
              <span class="list-font">現物・信用</span>
              <el-badge
                :value="$_out($_withCommaInteger(spotMarginTradeList.length))"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-if="visible.executedCtMt"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <ifa-executed-stocks-margins-order-table
              :table-data="spotMarginTradeList"
            ></ifa-executed-stocks-margins-order-table>
          </el-col>
        </el-row>
      </el-row>
      <!-- /現物・信用 -->

      <!-- 現引・現渡 -->
      <el-row v-if="receiptDeliveryTradeList.length != 0">
        <el-row>
          <el-col :span="24">
            <div
              class="clickable"
              @click="visible.executedArAd = !visible.executedArAd"
            >
              <el-icon
                v-if="visible.executedArAd"
                class="header-icon"
              ><Remove></Remove></el-icon>
              <el-icon
                v-else
                class="header-icon"
              ><CirclePlus></CirclePlus></el-icon>
              <span class="list-font">現引・現渡</span>
              <el-badge
                :value="$_out($_withCommaInteger(receiptDeliveryTradeList.length))"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-if="visible.executedArAd"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <ifa-executed-actual-receipt-actual-delivery-order-table
              :table-data="receiptDeliveryTradeList"
            ></ifa-executed-actual-receipt-actual-delivery-order-table>
          </el-col>
        </el-row>
      </el-row>
      <el-row v-if="form.spotMarginTradeList.length === 0 && form.receiptDeliveryTradeList.length === 0">
        <el-col
          :span="24"
          style="text-align: center;"
        >
          {{ form.noDetailMsg }}
        </el-col>
      </el-row>
      <!-- /現引・現渡 -->
    </div>
    <ifa-requester
      id="IfaDomesticTradeStatusListInitializeA001"
      action-id="SUB0202_0105-01#A001"
      action-type="requestAction"
      @response-handler="a001ActionHandler"
      @response-error-handler="a001ActionErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaExecutedStocksMarginsOrderTable from './tradeStatusTable/IfaExecutedStocksMarginsOrderTable'
import IfaExecutedActualReceiptActualDeliveryOrderTable from './tradeStatusTable/IfaExecutedActualReceiptActualDeliveryOrderTable'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { IfaDomesticTradeStatusListFormModel } from './js/IfaDomesticTradeStatusListFormModel'

export default {
  components: {
    IfaExecutedStocksMarginsOrderTable,
    IfaExecutedActualReceiptActualDeliveryOrderTable,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      visible: {
        executedCtMt: false, // 現物・信用
        executedArAd: false // 現引・現渡
      },
      form: new IfaDomesticTradeStatusListFormModel(),
      spotMarginTradeList: [],
      receiptDeliveryTradeList: []
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  },
  methods: {
    a001ActionHandler(data) {
      this.form = Object.assign(this.form, data.dataList[0])
      this.form.spotMarginTradeList = this.form.spotMarginTradeList ? this.form.spotMarginTradeList : []
      this.form.receiptDeliveryTradeList = this.form.receiptDeliveryTradeList ? this.form.receiptDeliveryTradeList : []
      this.spotMarginTradeList = this.form.spotMarginTradeList
      this.receiptDeliveryTradeList = this.form.receiptDeliveryTradeList
    },
    a001ActionErrorHandler(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    a003Action(radio) {
      if (radio === ' ') { // 総合口座
        this.spotMarginTradeList = this.form.spotMarginTradeList.filter(item => this.selectSougou(item))
        this.receiptDeliveryTradeList = this.form.receiptDeliveryTradeList.filter(item => this.selectSougou(item))
      } else if (radio === '1') { // ジュニアNISA口座
        this.spotMarginTradeList = this.form.spotMarginTradeList.filter(item => this.selectJrNisa(item))
        this.receiptDeliveryTradeList = this.form.receiptDeliveryTradeList.filter(item => this.selectJrNisa(item))
      } else if (radio === '2') { // 全て
        this.spotMarginTradeList = this.form.spotMarginTradeList
        this.receiptDeliveryTradeList = this.form.receiptDeliveryTradeList
      }
    },
    // タブが表示された
    onShow(resume) {
      this.$nextTick(() => {
        document.getElementById('IfaDomesticTradeStatusListInitializeA001').click()
        this.expandAll()
      })
      if (!resume) {
        this.expandAll()
      }
    },
    // テーブルが展開されているか状態を返す
    isExpand() {
      return this.visible.executedCtMt || this.visible.executedArAd
    },
    isNoData() {
      return this.form.spotMarginTradeList.length === 0 && this.form.receiptDeliveryTradeList.length === 0
    },
    // 全てのテーブルを折りたたむ
    collapseAll() {
      this.visible.executedCtMt = false
      this.visible.executedArAd = false
    },
    // 全てのテーブルを展開する
    expandAll() {
      this.visible.executedCtMt = true
      this.visible.executedArAd = true
    },
    // 全てのテーブルの折りたたみと展開をトグルする
    expandCollapseAll() {
      this.isExpand() ? this.collapseAll() : this.expandAll()
    },
    selectSougou(item) {
      if (item.depositType === '0' ||
      item.depositType === '1' ||
      item.depositType === '4' ||
      item.depositType === 'H') {
        return true
      }
    },
    selectJrNisa(item) {
      if (item.depositType === '5' ||
      item.depositType === '6' ||
      item.depositType === '7' ||
      item.depositType === 'J') {
        return true
      }
    }
  }
}

</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
@import "@/styles/table.scss";
.caption_card {
  padding: 5px 15px 20px 15px;
}
</style>
