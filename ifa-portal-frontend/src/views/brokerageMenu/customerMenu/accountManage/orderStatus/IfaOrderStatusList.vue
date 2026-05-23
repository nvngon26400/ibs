<template>
  <!-- 画面名：注文状況 > 注文状況一覧 -->
  <div>
    <ifa-requester
      id="IfaOrderStatusListInitializeX001"
      action-id="SUB0202_0104-01#X001"
      action-type="requestAction"
      @response-handler="responseHandlerX001"
      @response-error-handler="responseErrorHandler"
    ></ifa-requester>
    <ifa-requester
      id="IfaOrderStatusListInitializeOrderListA002"
      action-id="SUB0202_0104-01#A002"
      action-type="requestAction"
      :request-model="IfaOrderStatusListA002RequestModel"
      @response-handler="responseHandlerA002"
      @response-error-handler="responseErrorHandler"
    ></ifa-requester>
    <ifa-requester
      id="IfaForeignStockEntrustOrderTradeInfoInitializeA001"
      action-id="SUB0202_0104-02#A001"
      action-type="requestAction"
      :request-model="IfaForeignStockEntrustOrderTradeInfoA001RequestModel"
      @response-handler="responseHandlerIfaForeignStockEntrustOrderTradeInfoInitializeA001"
    >
    </ifa-requester>
    <screen-title :text="form.title"></screen-title>
    <div v-if="domesticStockOrderCount !== '0' ||
           domesticMutualFundOrderCount !== '0' ||
           subscriptOrderStatusCount !== '0' ||
           fsEntrustOrderStatusTotalCount !== '0' ||
           foreignStockCounterOrderCount !== '0'"
         class="caption_card"
    >
      <el-row>
        <template v-if="customerInfo.jrIsaContractType === '1' && customerInfo.withdrawalRestrictionCancelFlag === '1'">
          <el-col :span="6">
            <!-- 口座区分 -->
            <ifa-input-radio
              :model-value="form.accountTypeRadio"
              :code-list-id="'ACCOUNT_TYPE'"
              :disp-pattern="'1'"
              :select-pattern="'1'"
              @change="a018Action($event)"
            ></ifa-input-radio>
          </el-col>
          <el-col :span="6">
            <span>
              <ifa-button
                v-if="isExpand()"
                id="btnCloseAll"
                action-type="originalAction"
                text="全て閉じる"
                color="secondary"
                icon="Remove"
                small
                @app-action-handler="expandCollapseAll()"
              ></ifa-button>
              <ifa-button
                v-else
                id="btnOpenAll"
                action-type="originalAction"
                text="全て開く"
                color="secondary"
                icon="CirclePlus"
                small
                @app-action-handler="expandCollapseAll()"
              ></ifa-button>
            </span>
          </el-col>
        </template>
        <template v-else>
          <el-col :span="12">
            <span>
              <ifa-button
                v-if="isExpand()"
                id="btnCloseAll"
                action-type="originalAction"
                text="全て閉じる"
                color="secondary"
                icon="Remove"
                small
                @app-action-handler="expandCollapseAll()"
              ></ifa-button>
              <ifa-button
                v-else
                id="btnOpenAll"
                action-type="originalAction"
                text="全て開く"
                color="secondary"
                icon="CirclePlus"
                small
                @app-action-handler="expandCollapseAll()"
              ></ifa-button>
            </span>
          </el-col>
        </template>
        <el-col :span="12">
          <div class="update-button">
            <ifa-button
              id="btnUpdate"
              action-type="originalAction"
              text="更新"
              color="primary"
              icon="RefreshRight"
              @app-action-handler="onShow()"
            ></ifa-button>
          </div>
        </el-col>
      </el-row>

      <!-- 現物・信用・現引・現渡 -->
      <el-row v-show="domesticStockOrderCount !== '0'">
        <el-row>
          <el-col :span="24">
            <div
              class="clickable"
              @click="visible.domesticStock = !visible.domesticStock"
            >
              <el-icon
                v-show="visible.domesticStock"
                class="header-icon"
              ><Remove></Remove></el-icon>
              <el-icon
                v-show="!visible.domesticStock"
                class="header-icon"
              ><CirclePlus></CirclePlus></el-icon>
              <span class="list-font">現物・信用・現引・現渡</span>
              <el-badge
                :value="ifaFormatUtils.withCommaInteger(domesticStockOrderCount)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-show="visible.domesticStock"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <ifa-unexecuted-domestic-stock-order-table
              :table-data="domesticStockOrderList"
              :trade-suspend-flag="form.tradeSuspendFlag"
              :intermediary-value-list="form.intermediaryValueList"
              @initialize-order-status-list="redraw()"
            ></ifa-unexecuted-domestic-stock-order-table>
          </el-col>
        </el-row>
      </el-row>
      <!-- /現物・信用・現引・現渡 -->

      <!-- 国内投信 -->
      <el-row v-show="domesticMutualFundOrderCount !== '0'">
        <el-row>
          <el-col :span="24">
            <div
              class="clickable"
              @click="visible.investmentTrust = !visible.investmentTrust"
            >
              <el-icon
                v-show="visible.investmentTrust"
                class="header-icon"
              ><Remove></Remove></el-icon>
              <el-icon
                v-show="!visible.investmentTrust"
                class="header-icon"
              ><CirclePlus></CirclePlus></el-icon>
              <span class="list-font">国内投信</span>
              <el-badge
                :value="ifaFormatUtils.withCommaInteger(domesticMutualFundOrderCount)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-show="visible.investmentTrust"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <ifa-unexecuted-investment-trust-order-table
              :customer-info="customerInfo"
              :table-data="domesticMutualFundOrderStatusList"
              :trade-suspend-flag="form.tradeSuspendFlag"
              :intermediary-value-list="form.intermediaryValueList"
              @initialize-order-status-list="redraw()"
            ></ifa-unexecuted-investment-trust-order-table>
          </el-col>
        </el-row>
      </el-row>
      <!-- /国内投信 -->

      <!-- 募集 -->
      <el-row v-show="subscriptOrderStatusCount !== '0'">
        <el-row>
          <el-col :span="24">
            <div
              class="clickable"
              @click="visible.recruitmentOrder = !visible.recruitmentOrder"
            >
              <el-icon
                v-show="visible.recruitmentOrder"
                class="header-icon"
              ><Remove></Remove></el-icon>
              <el-icon
                v-show="!visible.recruitmentOrder"
                class="header-icon"
              ><CirclePlus></CirclePlus></el-icon>
              <span class="list-font">国内株式（IPO/PO）</span>
              <el-badge
                :value="ifaFormatUtils.withCommaInteger(subscriptOrderStatusCount)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-show="visible.recruitmentOrder"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <ifa-recruitment-order-table
              :table-data="subscriptOrderStatusList"
            ></ifa-recruitment-order-table>
          </el-col>
        </el-row>
      </el-row>
      <!-- /募集 -->

      <!-- 外国株式（委託注文） -->
      <el-row v-show="fsEntrustOrderStatusTotalCount !== '0'">
        <el-row>
          <el-col :span="24">
            <div
              class="clickable"
              @click="visible.foreignStock = !visible.foreignStock"
            >
              <el-icon
                v-show="visible.foreignStock"
                class="header-icon"
              ><Remove></Remove></el-icon>
              <el-icon
                v-show="!visible.foreignStock"
                class="header-icon"
              ><CirclePlus></CirclePlus></el-icon>
              <span class="list-font">外国株式（委託注文）</span>
              <el-badge
                :value="ifaFormatUtils.withCommaInteger(fsEntrustOrderStatusTotalCount)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-show="getCountryData('US') ? getCountryData('US').fsEntrustOrderStatusCount !== '0' && visible.foreignStock: false"
          style="padding-left: 2rem;"
        >
          <el-row>
            <el-col :span="24">
              <div
                class="clickable"
                @click="visible.americaOrder = !visible.americaOrder"
              >
                <el-icon
                  v-show="visible.americaOrder"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-show="!visible.americaOrder"
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
                <span class="list-font">
                  {{ $_getCodeValue('NATIONALITY_CODE', 2, 'US') }}
                </span>
                <el-badge
                  :value="getCountryData('US') ? ifaFormatUtils.withCommaInteger(getCountryData('US').fsEntrustOrderStatusCount) : 0"
                  class="badge-item"
                  type="primary"
                ></el-badge>
              </div>
            </el-col>
          </el-row>
          <el-row
            v-show="visible.americaOrder"
            style="margin-bottom: 0.6rem;"
          >
            <el-col :span="24">
              <ifa-foreign-order-table
                :table-data="getCountryData('US') ? getCountryData('US').fsEntrustOrderStatusList : []"
                :customer-info="customerInfo"
                :trade-suspend-flag="form.tradeSuspendFlag"
                :intermediary-value-list="form.intermediaryValueList"
                :country-cd="'US'"
                @initialize-order-status-list="redraw()"
                @entrust-order-trade-info="displayA003Action"
              ></ifa-foreign-order-table>
            </el-col>
          </el-row>
        </el-row>
        <el-row
          v-show="getCountryData('HK') ? getCountryData('HK').fsEntrustOrderStatusCount !== '0' && visible.foreignStock: false"
          style="padding-left: 2rem;"
        >
          <el-row>
            <el-col :span="24">
              <div
                class="clickable"
                @click="visible.chinaOrder = !visible.chinaOrder"
              >
                <el-icon
                  v-show="visible.chinaOrder"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-show="!visible.chinaOrder"
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
                <span class="list-font">
                  {{ $_getCodeValue('NATIONALITY_CODE', 2, 'HK') }}
                </span>
                <el-badge
                  :value="getCountryData('HK') ? ifaFormatUtils.withCommaInteger(getCountryData('HK').fsEntrustOrderStatusCount) : 0"
                  class="badge-item"
                  type="primary"
                ></el-badge>
              </div>
            </el-col>
          </el-row>
          <el-row
            v-show="visible.chinaOrder"
            style="margin-bottom: 0.6rem;"
          >
            <el-col :span="24">
              <ifa-foreign-order-table
                :table-data="getCountryData('HK') ? getCountryData('HK').fsEntrustOrderStatusList : []"
                :customer-info="customerInfo"
                :trade-suspend-flag="form.tradeSuspendFlag"
                :intermediary-value-list="form.intermediaryValueList"
                :country-cd="'HK'"
                @initialize-order-status-list="redraw()"
                @entrust-order-trade-info="displayA003Action"
              ></ifa-foreign-order-table>
            </el-col>
          </el-row>
        </el-row>
        <el-row
          v-show="getCountryData('KR') ? getCountryData('KR').fsEntrustOrderStatusCount !== '0' && visible.foreignStock: false"
          style="padding-left: 2rem;"
        >
          <el-row>
            <el-col :span="24">
              <div
                class="clickable"
                @click="visible.koreaOrder = !visible.koreaOrder"
              >
                <el-icon
                  v-show="visible.koreaOrder"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-show="!visible.koreaOrder"
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
                <span class="list-font">
                  {{ $_getCodeValue('NATIONALITY_CODE', 2, 'KR') }}
                </span>
                <el-badge
                  :value="getCountryData('KR') ? ifaFormatUtils.withCommaInteger(getCountryData('KR').fsEntrustOrderStatusCount) : 0"
                  class="badge-item"
                  type="primary"
                ></el-badge>
              </div>
            </el-col>
          </el-row>
          <el-row
            v-show="visible.koreaOrder"
            style="margin-bottom: 0.6rem;"
          >
            <el-col :span="24">
              <ifa-foreign-order-table
                :table-data="getCountryData('KR') ? getCountryData('KR').fsEntrustOrderStatusList : []"
                :customer-info="customerInfo"
                :trade-suspend-flag="form.tradeSuspendFlag"
                :intermediary-value-list="form.intermediaryValueList"
                :country-cd="'KR'"
                @initialize-order-status-list="redraw()"
                @entrust-order-trade-info="displayA003Action"
              ></ifa-foreign-order-table>
            </el-col>
          </el-row>
        </el-row>
        <el-row
          v-show="getCountryData('RU') ? getCountryData('RU').fsEntrustOrderStatusCount !== '0' && visible.foreignStock: false"
          style="padding-left: 2rem;"
        >
          <el-row>
            <el-col :span="24">
              <div
                class="clickable"
                @click="visible.russiaOrder = !visible.russiaOrder"
              >
                <el-icon
                  v-show="visible.russiaOrder"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-show="!visible.russiaOrder"
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
                <span class="list-font">
                  {{ $_getCodeValue('NATIONALITY_CODE', 2, 'RU') }}
                </span>
                <el-badge
                  :value="getCountryData('RU') ? ifaFormatUtils.withCommaInteger(getCountryData('RU').fsEntrustOrderStatusCount) : 0"
                  class="badge-item"
                  type="primary"
                ></el-badge>
              </div>
            </el-col>
          </el-row>
          <el-row
            v-show="visible.russiaOrder"
            style="margin-bottom: 0.6rem;"
          >
            <el-col :span="24">
              <ifa-foreign-order-table
                :table-data="getCountryData('RU') ? getCountryData('RU').fsEntrustOrderStatusList : []"
                :customer-info="customerInfo"
                :trade-suspend-flag="form.tradeSuspendFlag"
                :intermediary-value-list="form.intermediaryValueList"
                :country-cd="'RU'"
                @initialize-order-status-list="redraw()"
                @entrust-order-trade-info="displayA003Action"
              ></ifa-foreign-order-table>
            </el-col>
          </el-row>
        </el-row>
        <el-row
          v-show="getCountryData('VN') ? getCountryData('VN').fsEntrustOrderStatusCount !== '0' && visible.foreignStock: false"
          style="padding-left: 2rem;"
        >
          <el-row>
            <el-col :span="24">
              <div
                class="clickable"
                @click="visible.vietnamOrder = !visible.vietnamOrder"
              >
                <el-icon
                  v-show="visible.vietnamOrder"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-show="!visible.vietnamOrder"
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
                <span class="list-font">
                  {{ $_getCodeValue('NATIONALITY_CODE', 2, 'VN') }}
                </span>
                <el-badge
                  :value="getCountryData('VN') ? ifaFormatUtils.withCommaInteger(getCountryData('VN').fsEntrustOrderStatusCount) : 0"
                  class="badge-item"
                  type="primary"
                ></el-badge>
              </div>
            </el-col>
          </el-row>
          <el-row
            v-show="visible.vietnamOrder"
            style="margin-bottom: 0.6rem;"
          >
            <el-col :span="24">
              <ifa-foreign-order-table
                :table-data="getCountryData('VN') ? getCountryData('VN').fsEntrustOrderStatusList : []"
                :customer-info="customerInfo"
                :trade-suspend-flag="form.tradeSuspendFlag"
                :intermediary-value-list="form.intermediaryValueList"
                :country-cd="'VN'"
                @initialize-order-status-list="redraw()"
                @entrust-order-trade-info="displayA003Action"
              ></ifa-foreign-order-table>
            </el-col>
          </el-row>
        </el-row>
        <el-row
          v-show="getCountryData('ID') ? getCountryData('ID').fsEntrustOrderStatusCount !== '0' && visible.foreignStock: false"
          style="padding-left: 2rem;"
        >
          <el-row>
            <el-col :span="24">
              <div
                class="clickable"
                @click="visible.indonesiaOrder = !visible.indonesiaOrder"
              >
                <el-icon
                  v-show="visible.indonesiaOrder"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-show="!visible.indonesiaOrder"
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
                <span class="list-font">
                  {{ $_getCodeValue('NATIONALITY_CODE', 2, 'ID') }}
                </span>
                <el-badge
                  :value="getCountryData('ID') ? ifaFormatUtils.withCommaInteger(getCountryData('ID').fsEntrustOrderStatusCount) : 0"
                  class="badge-item"
                  type="primary"
                ></el-badge>
              </div>
            </el-col>
          </el-row>
          <el-row
            v-show="visible.indonesiaOrder"
            style="margin-bottom: 0.6rem;"
          >
            <el-col :span="24">
              <ifa-foreign-order-table
                :table-data="getCountryData('ID') ? getCountryData('ID').fsEntrustOrderStatusList : []"
                :customer-info="customerInfo"
                :trade-suspend-flag="form.tradeSuspendFlag"
                :intermediary-value-list="form.intermediaryValueList"
                :country-cd="'ID'"
                @initialize-order-status-list="redraw()"
                @entrust-order-trade-info="displayA003Action"
              ></ifa-foreign-order-table>
            </el-col>
          </el-row>
        </el-row>
        <el-row
          v-show="getCountryData('SG') ? getCountryData('SG').fsEntrustOrderStatusCount !== '0' && visible.foreignStock: false"
          style="padding-left: 2rem;"
        >
          <el-row>
            <el-col :span="24">
              <div
                class="clickable"
                @click="visible.singaporeOrder = !visible.singaporeOrder"
              >
                <el-icon
                  v-show="visible.singaporeOrder"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-show="!visible.singaporeOrder"
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
                <span class="list-font">
                  {{ $_getCodeValue('NATIONALITY_CODE', 2, 'SG') }}
                </span>
                <el-badge
                  :value="getCountryData('SG') ? ifaFormatUtils.withCommaInteger(getCountryData('SG').fsEntrustOrderStatusCount) : 0"
                  class="badge-item"
                  type="primary"
                ></el-badge>
              </div>
            </el-col>
          </el-row>
          <el-row
            v-show="visible.singaporeOrder"
            style="margin-bottom: 0.6rem;"
          >
            <el-col :span="24">
              <ifa-foreign-order-table
                :table-data="getCountryData('SG') ? getCountryData('SG').fsEntrustOrderStatusList : []"
                :customer-info="customerInfo"
                :trade-suspend-flag="form.tradeSuspendFlag"
                :intermediary-value-list="form.intermediaryValueList"
                :country-cd="'SG'"
                @initialize-order-status-list="redraw()"
                @entrust-order-trade-info="displayA003Action"
              ></ifa-foreign-order-table>
            </el-col>
          </el-row>
        </el-row>
        <el-row
          v-show="getCountryData('TH') ? getCountryData('TH').fsEntrustOrderStatusCount !== '0' && visible.foreignStock: false"
          style="padding-left: 2rem;"
        >
          <el-row>
            <el-col :span="24">
              <div
                class="clickable"
                @click="visible.thailandOrder = !visible.thailandOrder"
              >
                <el-icon
                  v-show="visible.thailandOrder"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-show="!visible.thailandOrder"
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
                <span class="list-font">
                  {{ $_getCodeValue('NATIONALITY_CODE', 2, 'TH') }}
                </span>
                <el-badge
                  :value="getCountryData('TH') ? ifaFormatUtils.withCommaInteger(getCountryData('TH').fsEntrustOrderStatusCount) : 0"
                  class="badge-item"
                  type="primary"
                ></el-badge>
              </div>
            </el-col>
          </el-row>
          <el-row
            v-show="visible.thailandOrder"
            style="margin-bottom: 0.6rem;"
          >
            <el-col :span="24">
              <ifa-foreign-order-table
                :table-data="getCountryData('TH') ? getCountryData('TH').fsEntrustOrderStatusList : []"
                :customer-info="customerInfo"
                :trade-suspend-flag="form.tradeSuspendFlag"
                :intermediary-value-list="form.intermediaryValueList"
                :country-cd="'TH'"
                @initialize-order-status-list="redraw()"
                @entrust-order-trade-info="displayA003Action"
              ></ifa-foreign-order-table>
            </el-col>
          </el-row>
        </el-row>
        <el-row
          v-show="getCountryData('MY') ? getCountryData('MY').fsEntrustOrderStatusCount !== '0' && visible.foreignStock: false"
          style="padding-left: 2rem;"
        >
          <el-row>
            <el-col :span="24">
              <div
                class="clickable"
                @click="visible.malaysiaOrder = !visible.malaysiaOrder"
              >
                <el-icon
                  v-show="visible.malaysiaOrder"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-show="!visible.malaysiaOrder"
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
                <span class="list-font">
                  {{ $_getCodeValue('NATIONALITY_CODE', 2, 'MY') }}
                </span>
                <el-badge
                  :value="getCountryData('MY') ? ifaFormatUtils.withCommaInteger(getCountryData('MY').fsEntrustOrderStatusCount) : 0"
                  class="badge-item"
                  type="primary"
                ></el-badge>
              </div>
            </el-col>
          </el-row>
          <el-row
            v-show="visible.malaysiaOrder"
            style="margin-bottom: 0.6rem;"
          >
            <el-col :span="24">
              <ifa-foreign-order-table
                :table-data="getCountryData('MY') ? getCountryData('MY').fsEntrustOrderStatusList : []"
                :customer-info="customerInfo"
                :trade-suspend-flag="form.tradeSuspendFlag"
                :intermediary-value-list="form.intermediaryValueList"
                :country-cd="'MY'"
                @initialize-order-status-list="redraw()"
                @entrust-order-trade-info="displayA003Action"
              ></ifa-foreign-order-table>
            </el-col>
          </el-row>
        </el-row>
      </el-row>
      <!-- 外国株式（委託注文） -->

      <!-- 外国株式（店頭注文） -->
      <el-row v-show="foreignStockCounterOrderCount !== '0'">
        <el-row>
          <el-col :span="24">
            <div
              class="clickable"
              @click="visible.counterOrder = !visible.counterOrder"
            >
              <el-icon
                v-show="visible.counterOrder"
                class="header-icon"
              ><Remove></Remove></el-icon>
              <el-icon
                v-show="!visible.counterOrder"
                class="header-icon"
              ><CirclePlus></CirclePlus></el-icon>
              <span class="list-font">外国株式（店頭注文）</span>
              <el-badge
                :value="ifaFormatUtils.withCommaInteger(foreignStockCounterOrderCount)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-show="visible.counterOrder"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <ifa-counter-order-table
              :table-data="fsStoreOrderStatusList"
              :customer-info="customerInfo"
            ></ifa-counter-order-table>
          </el-col>
        </el-row>
      </el-row>
      <!-- 外貨建MMF -->
      <!-- <el-row v-if="foreignMmfOrderData.length != 0">
        <el-row>
          <el-col :span="24">
            <div
              class="clickable"
              @click="visible.foreignMmfOrder = !visible.foreignMmfOrder"
            >
              <el-icon
                v-if="visible.foreignMmfOrder"
                class="header-icon"
              ><Remove></Remove></el-icon>
              <el-icon
                v-else
                class="header-icon"
              ><CirclePlus></CirclePlus></el-icon>
              <span class="list-font">外貨建MMF</span>
              <el-badge
                :value="foreignMmfOrderData.length"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-if="visible.foreignMmfOrder"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <ifa-foreign-mmf-order-table
              :table-data="foreignMmfOrderData"
              :customer-info="customerInfo"
            ></ifa-foreign-mmf-order-table>
          </el-col>
        </el-row>
      </el-row> -->
      <!-- 外貨建MMF -->
    </div>
    <div
      v-else-if="!isLoading"
      class="caption_card"
    >
      <el-row>
        <el-col :span="12">
          {{ form.noDetailMsg }}
        </el-col>
        <el-col :span="12">
          <div class="update-button">
            <ifa-button
              id="btnUpdate"
              action-type="originalAction"
              text="更新"
              color="primary"
              icon="RefreshRight"
              @app-action-handler="onShow()"
            ></ifa-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-row
      v-if="customerInfo.foreignSecurityTradeAccountOpenStatus === '1' && form.ccsAuthId"
      class="caption_card"
    >
      <!-- 外貨建MMFリンク -->
      <el-col :span="24">
        <ifa-link
          ref="ifaLinkMyAssset"
          :url-id="37"
          :disp-name="'外貨建MMF'"
          manual-init
        ></ifa-link>
      </el-col>
    </el-row>
  </div>
  <!-- 委託注文約定情報-->
  <ifa-foreign-stock-entrust-order-trade-info
    :is-visible="infoDialogVisible"
    :info-data="foreignStockEntrustOrderTradeInfoData"
    @close-modal="infoDialogVisible = false"
  ></ifa-foreign-stock-entrust-order-trade-info>

</template>

<script>
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { IfaOrderStatusListA002RequestModel } from './js/IfaOrderStatusListA002RequestModel'
import { IfaOrderStatusListFormModel } from './js/IfaOrderStatusListFormModel'
import IfaRecruitmentOrderTable from './orderStatusTable/IfaRecruitmentOrderTable'
import IfaUnexecutedDomesticStockOrderTable from './orderStatusTable/IfaUnexecutedDomesticStockOrderTable'
import IfaUnexecutedInvestmentTrustOrderTable from './orderStatusTable/IfaUnexecutedInvestmentTrustOrderTable'
import IfaCounterOrderTable from './orderStatusTable/foreignOrderStatusTable/IfaCounterOrderTable'
import IfaForeignOrderTable from './orderStatusTable/foreignOrderStatusTable/IfaForeignOrderTable'
import { IfaForeignStockEntrustOrderTradeInfoA001RequestModel } from '@/views/brokerageMenu/customerMenu/accountManage/orderStatus/js/IfaForeignStockEntrustOrderTradeInfoA001RequestModel'
import IfaForeignStockEntrustOrderTradeInfo from '@/views/brokerageMenu/customerMenu/accountManage/orderStatus/IfaForeignStockEntrustOrderTradeInfo.vue'

export default {
  components: {
    IfaUnexecutedDomesticStockOrderTable,
    IfaUnexecutedInvestmentTrustOrderTable,
    IfaRecruitmentOrderTable,
    IfaForeignOrderTable,
    IfaCounterOrderTable,
    screenTitle,
    IfaForeignStockEntrustOrderTradeInfo
  },
  emits: ['initializeError', 'update-customer-portal'],
  data() {
    return this.defaultData()
  },
  computed: {
    IfaOrderStatusListA002RequestModel() {
      return new IfaOrderStatusListA002RequestModel(this.a002RequestModel)
    },
    IfaForeignStockEntrustOrderTradeInfoA001RequestModel() {
      return new IfaForeignStockEntrustOrderTradeInfoA001RequestModel(this.ifaForeignStockEntrustOrderTradeInfoA001RequestModel)
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    // ジュニアNISA口座を持っているか
    hasJrNisaAccount() {
      return this.customerInfo.jrNisaAccountNumber !== ''
    }
  },
  methods: {
    redraw() {
      this.onShow()
      this.$nextTick(() => {
        this.$emit('update-customer-portal')
      })
    },
    onShow(resume, isRouting) {
      Object.assign(this.$data, this.defaultData())
      this.isLoading = true
      const params = this.$store.getters.pageInfo.params
      if (params && params.nextActionId === 'A002') {
        // 注文一覧からのパラメータ
        this.a002RequestModel = params
        this.$nextTick(() => {
          document.getElementById('IfaOrderStatusListInitializeOrderListA002').click()
        })
      } else {
        this.$nextTick(() => {
          document.getElementById('IfaOrderStatusListInitializeX001').click()
        })
      }
    },
    defaultData() {
      return {
        form: new IfaOrderStatusListFormModel(),
        radio: 0,
        visible: {
          domesticStock: true, // 現物・信用・現引・現渡
          investmentTrust: true, // 投資信託
          recruitmentOrder: true, // 募集取引
          foreignStock: true, // 外国株式委託
          americaOrder: true, // 米国株
          koreaOrder: true, // 韓国
          chinaOrder: true, // 中国
          vietnamOrder: true, // ベトナム
          thailandOrder: true, // タイ
          singaporeOrder: true, // シンガポール
          malaysiaOrder: true, // マレーシア
          indonesiaOrder: true, // インドネシア
          russiaOrder: true, // ロシア
          counterOrder: true // 店頭
        },
        ifaFormatUtils: ifaFormatUtils,
        domesticStockOrderList: [], // 国内株式注文状況一覧
        domesticMutualFundOrderStatusList: [], // 国内投信注文状況一覧
        subscriptOrderStatusList: [], // 募集注文状況一覧
        foreignStockEntrustOrder: [], // 外株委託注文状況一覧
        fsStoreOrderStatusList: [], // 外株店頭注文状況一覧
        domesticStockOrderCount: '0', // 国内株式注文件数
        domesticMutualFundOrderCount: '0', // 国内投信注文件数
        subscriptOrderStatusCount: '0', // 募集注文件数
        fsEntrustOrderStatusTotalCount: '0', // 外株委託注文総件数
        foreignStockCounterOrderCount: '0', // 外株店頭注文件数
        a002RequestModel: {},
        ifaForeignStockEntrustOrderTradeInfoA001RequestModel: {},
        cancelCompleteDialogVisible: false,
        infoDialogVisible: false,
        foreignStockEntrustOrderTradeInfoData: {},
        isLoading: false
      }
    },
    responseHandlerX001(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      this.initList()
      this.isLoading = false
    },
    responseHandlerA002(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      this.initList()
      this.isLoading = false
    },
    initList() {
      this.domesticStockOrderList = this.form.domesticStockOrderList // 国内株式注文状況一覧
      this.domesticMutualFundOrderStatusList = this.form.domesticMutualFundOrderStatusList // 国内投信注文状況一覧
      this.subscriptOrderStatusList = this.form.subscriptOrderStatusList // 募集注文状況一覧
      this.foreignStockEntrustOrder = this.form.foreignStockEntrustOrder // 外株委託注文状況一覧
      this.fsStoreOrderStatusList = this.form.fsStoreOrderStatusList // 外株店頭注文状況一覧
      this.domesticStockOrderCount = this.form.domesticStockOrderCount // 国内株式注文件数
      this.domesticMutualFundOrderCount = this.form.domesticMutualFundOrderCount // 国内投信注文件数
      this.subscriptOrderStatusCount = this.form.subscriptOrderStatusCount // 募集注文件数
      this.fsEntrustOrderStatusTotalCount = this.form.fsEntrustOrderStatusTotalCount // 外株委託注文総件数
      this.foreignStockCounterOrderCount = this.form.foreignStockCounterOrderCount // 外株店頭注文件数
      this.$nextTick(() => {
        if (this.$refs['ifaLinkMyAssset']) { this.$refs['ifaLinkMyAssset'].trigger() }
      })
    },
    setListLength() {
      this.domesticStockOrderCount = this.domesticStockOrderList.length.toString()
      this.domesticMutualFundOrderCount = this.domesticMutualFundOrderStatusList.length.toString()
      this.subscriptOrderStatusCount = this.subscriptOrderStatusList.length.toString()
      this.foreignStockCounterOrderCount = this.fsStoreOrderStatusList.length.toString()
      this.foreignStockEntrustOrder = this.foreignStockEntrustOrder.map(item => ({
        ...item,
        fsEntrustOrderStatusCount: item.fsEntrustOrderStatusList.length.toString()
      }))
      let totalCount = 0
      this.foreignStockEntrustOrder.forEach(item => {
        totalCount += item.fsEntrustOrderStatusList.length
      })
      this.fsEntrustOrderStatusTotalCount = totalCount.toString()
    },
    responseErrorHandler(error) {
      if (error.errorLevel === -1) {
        const errorInfo = {
          title: this.form.title,
          message: error.message
        }
        this.$emit('initializeError', errorInfo)
      }
      this.isLoading = false
    },
    // テーブルが展開されているか状態を返す
    isExpand() {
      return this.domesticStockOrderList.length !== 0 && this.visible.domesticStock ||
        this.domesticMutualFundOrderStatusList.length !== 0 && this.visible.investmentTrust ||
        this.subscriptOrderStatusList.length !== 0 && this.visible.recruitmentOrder ||
        this.foreignStockEntrustOrder.length !== 0 && this.visible.foreignStock ||
        this.fsStoreOrderStatusList.length !== 0 && this.visible.counterOrder
    },
    // 全てのテーブルを折りたたむ
    collapseAll() {
      this.visible.domesticStock = false
      this.visible.investmentTrust = false
      this.visible.recruitmentOrder = false
      this.visible.foreignStock = false
      this.visible.americaOrder = false
      this.visible.koreaOrder = false
      this.visible.chinaOrder = false
      this.visible.vietnamOrder = false
      this.visible.thailandOrder = false
      this.visible.singaporeOrder = false
      this.visible.malaysiaOrder = false
      this.visible.indonesiaOrder = false
      this.visible.russiaOrder = false
      this.visible.counterOrder = false
    },
    // 全てのテーブルを展開する
    expandAll() {
      this.visible.domesticStock = true
      this.visible.investmentTrust = true
      this.visible.recruitmentOrder = true
      this.visible.foreignStock = true
      this.visible.americaOrder = true
      this.visible.koreaOrder = true
      this.visible.chinaOrder = true
      this.visible.vietnamOrder = true
      this.visible.thailandOrder = true
      this.visible.singaporeOrder = true
      this.visible.malaysiaOrder = true
      this.visible.indonesiaOrder = true
      this.visible.russiaOrder = true
      this.visible.counterOrder = true
      this.visible.counterOrder = true
    },
    // 全てのテーブルの折りたたみと展開をトグルする
    expandCollapseAll() {
      this.isExpand() ? this.collapseAll() : this.expandAll()
    },
    getCountryData(countryCode) {
      if (this.foreignStockEntrustOrder && this.foreignStockEntrustOrder.length > 0) {
        return this.foreignStockEntrustOrder.filter((item) => item.countryCd === countryCode)[0]
      }
    },
    displayA003Action(item) {
      this.ifaForeignStockEntrustOrderTradeInfoA001RequestModel = item
      this.$nextTick(() => {
        // 外国株式委託注文約定情報のA001を発火
        document.getElementById('IfaForeignStockEntrustOrderTradeInfoInitializeA001').click()
      })
    },
    // 外国株式委託注文約定情報への遷移
    responseHandlerIfaForeignStockEntrustOrderTradeInfoInitializeA001(response) {
      this.foreignStockEntrustOrderTradeInfoData = response.dataList[0]
      this.infoDialogVisible = true
    },
    a018Action(radio) {
      if (radio === ' ') { // 総合口座
        this.domesticStockOrderList = this.form.domesticStockOrderList.filter(item => this.selectSougouNotSpecificDepositTradeType(item))
        this.domesticMutualFundOrderStatusList = this.form.domesticMutualFundOrderStatusList.filter(item => this.selectSougouNotSpecificDepositTradeType(item))
        this.subscriptOrderStatusList = this.form.subscriptOrderStatusList.filter(item => this.selectSougouDepositType(item))
        this.foreignStockEntrustOrder = this.form.foreignStockEntrustOrder.map(item => ({
          ...item,
          fsEntrustOrderStatusList: item.fsEntrustOrderStatusList.filter(subItem => this.selectSougouDepositType(subItem))
        }))
        this.fsStoreOrderStatusList = this.form.fsStoreOrderStatusList.filter(item => this.selectSougouDepositType(item))
        this.setListLength()
      } else if (radio === '1') { // ジュニアNISA口座
        this.domesticStockOrderList = this.form.domesticStockOrderList.filter(item => this.selectJrNisaNotSpecificDepositTradeType(item))
        this.domesticMutualFundOrderStatusList = this.form.domesticMutualFundOrderStatusList.filter(item => this.selectJrNisaNotSpecificDepositTradeType(item))
        this.subscriptOrderStatusList = this.form.subscriptOrderStatusList.filter(item => this.selectJrNisaDepositType(item))
        this.foreignStockEntrustOrder = this.form.foreignStockEntrustOrder.map(item => ({
          ...item,
          fsEntrustOrderStatusList: item.fsEntrustOrderStatusList.filter(subItem => this.selectJrNisaDepositType(subItem))
        }))
        this.fsStoreOrderStatusList = this.form.fsStoreOrderStatusList.filter(item => this.selectJrNisaDepositType(item))
        this.setListLength()
      } else if (radio === '2') { // 全て
        this.initList()
      }
    },
    selectSougouDepositType(item) {
      if (item.depositType === '1' ||
      item.depositType === '2' ||
      item.depositType === '3' ||
      item.depositType === '4' ||
      item.depositType === 'H') {
        return true
      }
    },
    selectJrNisaDepositType(item) {
      if (item.depositType === '5' ||
      item.depositType === '6' ||
      item.depositType === '7' ||
      item.depositType === 'J') {
        return true
      }
    },
    selectSougouNotSpecificDepositTradeType(item) {
      if (item.notSpecificDepositTradeType === '0' ||
      item.notSpecificDepositTradeType === '1' ||
      item.notSpecificDepositTradeType === '4' ||
      item.notSpecificDepositTradeType === 'H' ||
      item.notSpecificDepositTradeType === 'I' ||
      item.notSpecificDepositTradeType === ' ') {
        return true
      }
    },
    selectJrNisaNotSpecificDepositTradeType(item) {
      if (item.notSpecificDepositTradeType === '5' ||
      item.notSpecificDepositTradeType === '6' ||
      item.notSpecificDepositTradeType === '7' ||
      item.notSpecificDepositTradeType === 'J') {
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
