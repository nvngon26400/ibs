<template>
  <!-- 国内投信注文受付確認ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.Title"
    :style="{ 'background-color': bgColor }"
    class="status_change"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="backA002"
    :close-on-click-modal="false"
  >

    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          id="btnBack"
          text="戻る"
          color="secondary"
          style="padding-right: 0;"
          action-type="originalAction"
          @app-action-handler="backA002"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :key="messageKey"
      :main-messages="messages.mains"
      :error-messages="messages.errors"
      :warning-messages="messages.warnings"
      :info-messages="messages.infos"
    ></ifa-message-area>

    <!-- ヘッダ -->
    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
      >
        <span>{{ $_out(accountNumber) }}</span>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        class="_bold_black_l"
        style="padding-top: 0.5rem;; font-size: 20px;"
      >
        <el-icon style="position: relative; top: 3px;"><component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
        <span>{{ $_out(customerName) }}</span>
        <ifa-notice-info
          :notice-info-level="customerInfo.noticeInfoLevel"
          :customer-code="customerInfo.customerCode"
          :buten-code="customerInfo.butenCode"
          :account-number="customerInfo.accountNumber"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>

    <!-- 銘柄情報 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="background-color: #eee; margin: 0.5rem 0;"
        >
          <el-row
            class="_bold_black_l"
            style="font-size: 20px; display: flex; align-items: flex-start;"
          >
            <div style="display: inline-block; width: auto; margin-right: 5px;">
              <span>［{{ $_out(form.a010ApiRequest.brandCode) }}］</span>
            </div>
            <div style="display: inline-block; flex-grow: 1; flex-basis: 0;">
              <span>{{ $_out(form.a010ApiRequest.brandName) }}</span>
            </div>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文内容(復唱項目) -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>ご注文内容（復唱項目）</span>
          </el-row>
          <hr>
          <!-- 取引種別 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :class="(form.a010ApiRequest.tradeCd === '0' || form.a010ApiRequest.tradeCd === '1') ? 'font-color__plus bold' : 'font-color__minus bold'"
                  :code-list-id="'DOMESTIC_MUTUAL_FUND_TRADE_CLASS'"
                  :disp-pattern="2"
                  :code-key="form.a010ApiRequest.tradeCd"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.tradeCd">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 売却指定 -->
          <el-row
            v-if="sellDesignatedDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>売却指定</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'DESIGNATED_FOR_SALE'"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.sellDesignated"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.sellDesignated">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 金額: 購入累投､売却累投 -->
          <el-row
            v-if="amountDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>金額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ amount() }}</span>
            </el-col>
          </el-row>

          <!-- 口数: 購入一般､売却一般 -->
          <el-row
            v-if="unitDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>口数</span>
            </el-col>
            <el-col :span="16">
              <span>{{ unit() }}</span>
            </el-col>
          </el-row>

          <!-- 預り区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>預り区分</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'MUTUAL_FUND_DEPOSIT_TYPE'"
                  :disp-pattern="depositTypeDispPattern"
                  :code-key="form.a010ApiRequest.depositType"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.depositType">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 約定予定日 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>約定予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.contractDate ? formatDate(form.contractDate) : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 受渡予定日 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受渡予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.deliveryDate ? formatDate(form.deliveryDate) : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 手数料/諸費用 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>手数料/諸費用</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.charge ? $_withCommaInteger(form.charge) + '円' : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 消費税 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>消費税</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.consumptionTax ? $_withCommaInteger(form.consumptionTax) + '円' : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 精算金額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>精算金額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.settlementAmount ? $_withCommaInteger(form.settlementAmount) + '円' : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 分配金受取方法 -->
          <el-row
            v-if="distributionReceiveMethodWordDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>分配金受取方法</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'DISTRIBUTION_RECEIVE_METHOD'"
                  :disp-pattern="4"
                  :code-key="form.a010ApiRequest.distributionReceiveMethodWord"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.distributionReceiveMethodWord">-</span>
              </span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- その他注文内容 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>

          <!-- 選択口座 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>選択口座</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  code-list-id="ACCOUNT_TYPE"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.accountType"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.accountType">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 見積単価 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>見積単価</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.quoteUnitPrice ? $_withCommaInteger(form.quoteUnitPrice) + '円' : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 受注数量 -->
          <el-row
            v-if="amountOrderDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ amountOrder() }}</span>
            </el-col>
          </el-row>

          <!-- 乗換優遇 -->
          <el-row v-if="norikaeYuguDisplay"
                  class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>償還優遇</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'TRANSFERS_PREFERENTIAL_QUOTA_APPLICATION'"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.norikaeYuguKbn"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.norikaeYuguKbn">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 約定金額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>約定金額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.contractAmount ? $_withCommaInteger(form.contractAmount) + '円' : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 利用ポイント -->
          <el-row
            v-if="pointDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>利用ポイント</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_withCommaInteger(form.point) }}
                <ifa-text
                  :code-list-id="'POINT_TYPE'"
                  :disp-pattern="2"
                  :code-key="form.a010ApiRequest.pointType"
                  style="font-size: 16px;"
                ></ifa-text>
              </span>
            </el-col>
          </el-row>

          <!-- 譲渡益税 -->
          <el-row
            v-if="yieldTaxDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>譲渡益税</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.yieldTax ? $_withCommaInteger(form.yieldTax) + '円' : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 注文後のNISA投資可能枠 -->
          <el-row
            v-if="nisaInvestableQuoteDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ `注文後のNISA投資可能枠（ ${getYearString(form.deliveryDate)}年）` }}</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.nisaInvestableQuote ? $_withCommaInteger(form.nisaInvestableQuote) + '円' : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 受注日時 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注日時</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(formatDateTime(form.orderDate, form.orderDayTime)) }}</span>
            </el-col>
          </el-row>

          <!-- 勧誘区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'INVITATION_TYPE'"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.kanyuKbn"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.kanyuKbn">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 受注方法 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'ORDER_METHOD_TYPE'"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.receiveOrderType"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.receiveOrderType">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- レバレッジ投資信託 -->
          <el-row
            v-if="leverageInvestTrustDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>レバレッジ投資信託</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'LEVERAGED_INVESTMENT_TRUST'"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.leverageInvestTrust"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.leverageInvestTrust">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 乗換勧誘 -->
          <el-row
            v-if="norikaeKanyuKbnDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>乗換勧誘</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'SOLICITING_TRANSFERS'"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.norikaeKanyuKbn"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.norikaeKanyuKbn">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 同一通貨/同一国の乗換 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>同一通貨/同一国の乗換</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'SAME_CURRENCY_SAME_COUNTRY_TRANSFERS'"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.douitsuTukaKuniKbn"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.douitsuTukaKuniKbn">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 他社間投信乗換勧誘 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>他社間投信乗換勧誘</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'INTERCOMPANY_MUTUAL_FUND_TRANSFER_SOLICITATION'"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.tashaNorikaeKbn"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.tashaNorikaeKbn">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 目論見書の交付方法 -->
          <el-row
            v-if="mokuromiKoufuDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>目論見書の交付方法</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'PROSPECTUS_ISSUE_METHOD'"
                  :disp-pattern="1"
                  :code-key="form.a010ApiRequest.mokuromiKoufuKbn"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.mokuromiKoufuKbn">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 短期売却確認 -->
          <el-row
            v-if="tankiSellDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>短期売却確認</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'SHORT_TERM_SALE_CONFIRM'"
                  :disp-pattern="2"
                  :code-key="form.a010ApiRequest.tankiSellKbn"
                  style="font-size: 16px;"
                ></ifa-text>
                {{ form.a010ApiRequest.tankiSellKbn === '1' ?`（${form.a010ApiRequest.shortTermSaleConfirm}）` : '' }}
              </span>
            </el-col>
          </el-row>

          <!-- 償還前売却確認 -->
          <el-row
            v-if="shokanMaeDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>償還前売却確認</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'PRE_REDEMPTION_SELL_CONFIRM'"
                  :disp-pattern="2"
                  :code-key="form.a010ApiRequest.shokanMaeKbn"
                  style="font-size: 16px;"
                ></ifa-text>
                {{ form.a010ApiRequest.shokanMaeKbn === '1' ? `（${form.a010ApiRequest.preRedemptionSellConfirmSelect}）` : '' }}
              </span>
            </el-col>
          </el-row>

          <!-- 利益相反可能性の説明 -->
          <el-row
            v-if="confirmAreaDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>利益相反可能性の説明</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'CONFLICT_OF_INTEREST_EXPLAIN'"
                  :disp-pattern="2"
                  :code-key="form.a010ApiRequest.conflictOfInterestExplain"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.conflictOfInterestExplain">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 確認項目 -->
          <el-row
            v-if="confirmAreaDisplay"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>確認項目</span>
            </el-col>
            <el-col
              :span="16"
              style="line-height: 160%;"
            >
              <!-- 目論見書補完書面の確認 -->
              <span>
                <ifa-text
                  :code-list-id="'PROSPECTUS_SUPPLEMENTARY_DOC_CONFIRM'"
                  :disp-pattern="2"
                  :code-key="form.a010ApiRequest.checkMokuromi"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.checkMokuromi">-</span>
                <br>
              </span>
              <span v-if="confirmItemMadoAkiDisplay">
                <!-- 窓空きファンドの注意事項に同意 -->
                <ifa-text
                  :code-list-id="'WINDOW_SPACE_FUND_PRECAUTIONS_CONSENT'"
                  :disp-pattern="2"
                  :code-key="form.a010ApiRequest.checkMadoAki"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.checkMadoAki">-</span>
                <br>
              </span>
              <span>
                <!-- 費用について説明済 -->
                <ifa-text
                  :code-list-id="'COST_EXPLAINED'"
                  :disp-pattern="2"
                  :code-key="form.a010ApiRequest.checkHiyou"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.checkHiyou">-</span>
                <br>
              </span>
              <span>
                <!-- 複数取引業者での手数料等明示済 -->
                <ifa-text
                  :code-list-id="'MULTIPLE_TRADE_CLEARLY_COMM_STATED'"
                  :disp-pattern="2"
                  :code-key="form.a010ApiRequest.checkFukusu"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!form.a010ApiRequest.checkFukusu">-</span>
              </span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row v-if="form.complianceRankCheck.message ||
      form.complianceRankCheck.startCriteriaConfirmMsg ||
      form.noticeInfoAlert ||
      form.noticeAlert ||
      form.shortTermSellConfirmMsg ||
      form.preRedemptionSellConfirmAlertMsg"
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          :class="(form.a010ApiRequest.tradeCd === '0' || form.a010ApiRequest.tradeCd === '1') ? 'buy-background-color_card' : 'sell-background-color_card'"
          class="box-card alert_content"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <!-- コンプラランクチェック -->
          <el-row
            v-if="form.complianceRankCheck.message"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div>
              <span style="color: #f00;">
                <ifa-text
                  style="color: #f00; font-size: 16px;"
                  code-list-id="COMPLA_CHECK_BOX_WORDING"
                  :disp-pattern="3"
                  :code-key="form.complianceRankCheck.invitationCheck"
                ></ifa-text>
              </span>
            </el-col>
            <el-col :span="12">
              <el-checkbox
                v-if="form.complianceRankCheck.invitationCheck === '1'"
                id="warningCheck"
                v-model="form.alert.complianceRankCheckConfirmCheck"
                name="warningCheck"
                label="△・◇ワーニング申請は「申請・承認済」であることを確認済"
                style="margin-left: auto; color: #f00;"
              ></el-checkbox>
              <el-checkbox
                v-if="form.complianceRankCheck.invitationCheck === '2'"
                id="warningCheck"
                v-model="form.alert.complianceRankCheckConfirmCheck"
                name="warningCheck"
                label="勧誘なし"
                style="margin-left: auto; color: #f00;"
              ></el-checkbox>
            </el-col>
          </el-row>

          <!-- コンプラランクチェック開始基準確認 -->
          <el-row
            v-if="form.complianceRankCheck.startCriteriaConfirmMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">開始基準の確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.alert.startCriteriaConfirmCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="COMPLIANCE_START_CRITERIA_CONFIRM_CHECK_BOX_WORDING"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!--  短期売却確認アラート-->
          <el-row
            v-if="form.shortTermSellConfirmMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">短期売却確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.alert.shortTermSaleConfirmCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="SHORT_TERM_SELL_CONFIRM_ALERT_CHECKBOX_WORD"
                :disp-pattern="2"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- 償還前売却確認アラート確認 -->
          <el-row
            v-if="form.preRedemptionSellConfirmAlertMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">償還前売却確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.alert.preRedemptionSellConfirmSelectCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="PRE_REDEMPTION_SELL_CONFIRM_ALERT_CHECKBOX_WORD"
                :disp-pattern="2"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- 注意情報アラート確認 -->
          <el-row
            v-if="form.noticeInfoAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.alert.noticeInfoAlertCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- お知らせ情報アラート確認 -->
          <el-row
            v-if="form.noticeAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.alert.noticeAlertCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

        </el-card>
      </el-col>
    </el-row>

    <!-- 注文発注ボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnOrderRegister"
          text="注文発注"
          :disabled="buttonDisabled"
          style="padding-left: 0;"
          action-type="requestAction"
          action-id="SUB0202_0401-02_2#A001"
          :request-model="A001RequestModel"
          :pre-request-handler="preConfirmA001"
          @response-handler="responseHandlerOnOrderFinishA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaDomesticMutualFundOrderConfirmFormModel } from './js/IfaDomesticMutualFundOrderConfirmFormModel'
import { IfaDomesticMutualFundOrderConfirmA001RequestModel } from './js/IfaDomesticMutualFundOrderConfirmA001RequestModel'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import { getFormattedDateValue, getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
    customerInfo: { type: Object, required: true }
  // appendToBody: { type: Boolean, required: true }
  },
  emits: ['dialog-close', 'order-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
    //  dialogStyle: '',
      form: new IfaDomesticMutualFundOrderConfirmFormModel(),
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      },
      forms: {
        complianceRankCheck: false,
        shortTermSellConfirmMsg: false,
        preRedemptionSellConfirmAlertMsg: false,
        noteInfoCheck: false,
        noteLimitCheck: false
      },
      messageKey: 0
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.userAccount
    },
    A001RequestModel() {
      return new IfaDomesticMutualFundOrderConfirmA001RequestModel(this.form)
    },
    // 背景色
    bgColor() {
      if (this.form.tradeCd === '0' || this.form.tradeCd === '1') {
        return '#fef0f0'
      } else {
        return '#ecf5ff'
      }
    },
    buttonDisabled() {
      if (this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2' || this.userInfo.medUsers.privId === '11' || this.userInfo.medUsers.privId === '12') {
        return true
      } else if ((this.form.complianceRankCheck.message ? this.form.alert.complianceRankCheckConfirmCheck : true) &&
        (this.form.complianceRankCheck.startCriteriaConfirmMsg ? this.form.alert.startCriteriaConfirmCheck === '1' : true) &&
        (this.form.shortTermSellConfirmMsg ? this.form.alert.shortTermSaleConfirmCheck === '1' : true) &&
        (this.form.preRedemptionSellConfirmAlertMsg ? this.form.alert.preRedemptionSellConfirmSelectCheck === '1' : true) &&
        (this.form.noticeInfoAlert ? this.form.alert.noticeInfoAlertCheck === '1' : true) &&
        (this.form.noticeAlert ? this.form.alert.noticeAlertCheck === '1' : true)) {
        return false
      } else {
        return true
      }
    },
    /** 「金額」表示可否チェック */
    amountDisplay() {
      if (this.form.tradeCd === '1' ||
       (this.form.tradeCd === '4' || this.form.tradeCd === '8') && this.form.a010ApiRequest.sellDesignated === '1' ||
       this.form.tradeCd === '6' || this.form.tradeCd === '10') {
        return true
      }
      return false
    },
    /** 「口数」表示可否チェック */
    unitDisplay() {
      if (this.form.tradeCd === '0' ||
       this.form.tradeCd === '3' ||
       this.form.tradeCd === '7' ||
       (this.form.tradeCd === '4' || this.form.tradeCd === '8') && this.form.a010ApiRequest.sellDesignated === '2') {
        return true
      }
      return false
    },
    /** 「売却指定」表示可否チェック */
    sellDesignatedDisplay() {
      if (this.form.tradeCd === '4' ||
       this.form.tradeCd === '6' ||
       this.form.tradeCd === '8' ||
       this.form.tradeCd === '10') {
        return true
      }
      return false
    },
    /** 「預り区分」表示パターン判別 */
    depositTypeDispPattern() {
      // 顧客共通情報.ジュニアISA契約区分=1：契約　の場合
      if (this.customerInfo.jrIsaContractType === '1') {
        // 取引種別=購入（累投）　または　購入（一般）　の場合
        if (this.form.tradeCd === '1' || this.form.tradeCd === '0') {
          // ジュニアNISA口座を選択　かつ (顧客共通情報.ジュニア特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
          if (this.form.a010ApiRequest.accountType === '1' && !(this.customerInfo.jrTokuteiKouzaKbn === '1' || this.customerInfo.jrTokuteiKouzaKbn === '2')) {
            return 6
          // 総合口座を選択　かつ (顧客共通情報.特定口座区分= (1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
          } else if (this.form.a010ApiRequest.accountType === ' ' && !(this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2')) {
            return 6
          // 上記以外
          } else {
            return 3
          }
        // 取引種別=上記以外　の場合
        } else {
          return 4
        }
      // 上記以外
      } else {
        // 顧客共通情報.特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない場合
        if (!(this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2')) {
          return 7
        // 上記以外
        } else {
          return 5
        }
      }
    },
    /** 「分配金受取方法」表示可否チェック */
    distributionReceiveMethodWordDisplay() {
      if (this.form.tradeCd === '1') {
        return true
      }
      return false
    },
    /** 「受注数量」表示可否チェック */
    amountOrderDisplay() {
      /** 「受注数量（金額指定）」表示可否チェック */
      if (this.form.tradeCd === '1' ||
       (this.form.tradeCd === '4' || this.form.tradeCd === '8') && this.form.a010ApiRequest.sellDesignated === '1' ||
       this.form.tradeCd === '6' || this.form.tradeCd === '10') {
        return true
      /** 「受注数量（口数指定）」表示可否チェック */
      } else if (this.form.tradeCd === '0' ||
       this.form.tradeCd === '3' ||
       this.form.tradeCd === '7' ||
       (this.form.tradeCd === '4' || this.form.tradeCd === '8') && this.form.a010ApiRequest.sellDesignated === '2') {
        return true
      }
      return false
    },
    /** 「乗換優遇」表示可否チェック */
    norikaeYuguDisplay() {
      if (this.form.tradeCd === '0' ||
       this.form.tradeCd === '1') {
        return true
      } else {
        return false
      }
    },
    /** 「利用ポイント」表示可否チェック */
    pointDisplay() {
      if (this.form.tradeCd === '1') {
        return true
      } else {
        return false
      }
    },
    /** 「譲渡損益」表示可否チェック */
    yieldTaxDisplay() {
      if (this.form.tradeCd === '3' ||
       this.form.tradeCd === '4' ||
       this.form.tradeCd === '6' ||
       this.form.tradeCd === '7' ||
       this.form.tradeCd === '8' ||
       this.form.tradeCd === '10') {
        return true
      } else {
        return false
      }
    },
    /** 「注文後のNISA投資可能枠」表示可否チェック */
    nisaInvestableQuoteDisplay() {
      if (this.form.tradeCd === '0' ||
       this.form.tradeCd === '1') {
        if (this.form.a010ApiRequest.depositType === 'H') {
          return true
        } else {
          return false
        }
      } else {
        return false
      }
    },
    /** 「レバレッジ投資信託」表示可否チェック */
    leverageInvestTrustDisplay() {
      if (this.form.tradeCd === '0' ||
       this.form.tradeCd === '1') {
        return true
      } else {
        return false
      }
    },
    /** 「乗換勧誘」表示可否チェック */
    norikaeKanyuKbnDisplay() {
      if (this.form.tradeCd === '0' ||
       this.form.tradeCd === '1') {
        return true
      } else {
        return false
      }
    },
    /** 「目論見書の交付方法」表示可否チェック */
    mokuromiKoufuDisplay() {
      if (this.form.tradeCd === '0' ||
       this.form.tradeCd === '1') {
        return true
      } else {
        return false
      }
    },
    /** 「短期売却確認」表示可否チェック */
    tankiSellDisplay() {
      if (this.form.tradeCd === '3' ||
       this.form.tradeCd === '4' ||
       this.form.tradeCd === '6' ||
       this.form.tradeCd === '7' ||
       this.form.tradeCd === '8' ||
       this.form.tradeCd === '10') {
        return true
      } else {
        return false
      }
    },
    /** 「償還前売却確認」表示可否チェック */
    shokanMaeDisplay() {
      if (this.form.tradeCd === '3' ||
       this.form.tradeCd === '4' ||
       this.form.tradeCd === '6' ||
       this.form.tradeCd === '7' ||
       this.form.tradeCd === '8' ||
       this.form.tradeCd === '10') {
        return true
      } else {
        return false
      }
    },
    /** 「確認項目エリア」、「利益相反可能性の説明」表示可否チェック */
    confirmAreaDisplay() {
      if (this.form.tradeCd === '0' ||
       this.form.tradeCd === '1') {
        return true
      } else {
        return false
      }
    },
    /** 「確認内容.窓空きファンドの注意事項確認」表示可否チェック */
    confirmItemMadoAkiDisplay() {
      if (this.form.a010ApiRequest.brandSpecialClassification === '1') {
        return true
      } else {
        return false
      }
    },
    customerName() {
      return this.customerInfo.customerNameKanji + '（' + this.customerInfo.customerNameKana + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    hasJrNisaAccount() {
      return this.customerInfo.jrNisaAccountNumber !== ''
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)
      this.setMsg()
    },
    // 戻るボタン
    backA002() {
      this.$emit('dialog-close')
      this.form.alert.complianceRankCheckConfirmCheck = false
      this.form.alert.startCriteriaConfirmCheck = '0'
      this.form.alert.shortTermSaleConfirmCheck = '0'
      this.form.alert.preRedemptionSellConfirmSelectCheck = '0'
      this.form.alert.noticeInfoAlertCheck = '0'
      this.form.alert.noticeAlertCheck = '0'
    },
    // 注文発注ボタン
    responseHandlerOnOrderFinishA001(data) {
      this.$emit('order-finish', data.dataList[0])
      this.form.alert.complianceRankCheckConfirmCheck = false
      this.form.alert.startCriteriaConfirmCheck = '0'
      this.form.alert.shortTermSaleConfirmCheck = '0'
      this.form.alert.preRedemptionSellConfirmSelectCheck = '0'
      this.form.alert.noticeInfoAlertCheck = '0'
      this.form.alert.noticeAlertCheck = '0'
    },
    showWarning() {
      // 文字列に「w」が含まれているかチェック
      if (this.form.code.includes('W')) {
        return true
      } else {
        return false
      }
    },
    formatDate(val) {
      return getFormattedDateValue(val)
    },
    formatDateTime(date, time) {
      const dateTime = date + ' ' + time
      return getFormattedDateTimeValue(dateTime, 'datetime12')
    },
    /** 年の取得 */
    getYearString(str) {
      return str.substring(0, 4)
    },
    /** コンプラランクチェック確認取得 */
    getCompChkBoxLabel(form) {
      return getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, form.complianceRankCheck[0].chkBoxLabel)
    },
    /** 「金額」編集 */
    amount() {
      if (this.form.tradeCd === '1' ||
       (this.form.tradeCd === '4' || this.form.tradeCd === '8') && this.form.a010ApiRequest.sellDesignated === '1') {
        if (this.form.a010ApiRequest.amount) {
          return this.$_withCommaZeroPadding(this.form.a010ApiRequest.amount) + '円'
        } else {
          return '-'
        }
      } else if (this.form.tradeCd === '6' || this.form.tradeCd === '10') {
        return '-'
      }
    },
    /** 「口数」編集 */
    unit() {
      if (this.form.tradeCd === '0' ||
       this.form.tradeCd === '3' ||
       this.form.tradeCd === '7' ||
       (this.form.tradeCd === '4' || this.form.tradeCd === '8') && this.form.a010ApiRequest.sellDesignated === '2') {
        if (this.form.a010ApiRequest.unit) {
          return this.$_withCommaZeroPadding(this.form.a010ApiRequest.unit) + '口'
        } else {
          return '-'
        }
      } else {
        return '-'
      }
    },
    /** 「受注数量」編集 */
    amountOrder() {
      /** 「受注数量（金額指定）」編集 */
      if (this.form.tradeCd === '1' ||
      (this.form.tradeCd === '4' || this.form.tradeCd === '8') && this.form.a010ApiRequest.sellDesignated === '1' ||
      this.form.tradeCd === '6' || this.form.tradeCd === '10') {
        if (this.form.orderQuantity) {
          return this.$_withCommaZeroPadding(this.form.orderQuantity) + '口'
        } else {
          return '-'
        }
      /** 「受注数量（口数指定）」編集 */
      } else if (this.form.tradeCd === '0' ||
      this.form.tradeCd === '3' ||
      this.form.tradeCd === '7' ||
      (this.form.tradeCd === '4' || this.form.tradeCd === '8') && this.form.a010ApiRequest.sellDesignated === '2') {
        if (this.form.a010ApiRequest.unit) {
          return this.$_withCommaZeroPadding(this.form.a010ApiRequest.unit) + '口'
        } else {
          return '-'
        }
      } else {
        return '-'
      }
    },
    preConfirmA001() {
      this.A001RequestModel.amount = this.form.a010ApiRequest.sellDesignated === '3' ? '0000000000' : this.form.a010ApiRequest.amount
      this.A001RequestModel.norikaeYuguKbn = this.form.a010ApiRequest.norikaeYuguKbn === '1' ? '1' : '0'
    },
    setMsg() {
      this.messages.mains = []
      this.messages.errors = []
      this.messages.warnings = []
      this.messages.infos = []

      this.messages.mains.push('注文はまだ完了していません｡画面下の注文発注ボタンを押下してください｡')

      if (this.form.complianceRankCheck.message && this.form.complianceRankCheck.message.length > 0) {
        if (Array.isArray(this.form.complianceRankCheck.message)) {
          this.messages.errors.push(...this.form.complianceRankCheck.message)
        } else {
          this.messages.errors.push(this.form.complianceRankCheck.message)
        }
      }
      if (this.form.complianceRankCheck.startCriteriaConfirmMsg && this.form.complianceRankCheck.startCriteriaConfirmMsg.length > 0) {
        if (Array.isArray(this.form.complianceRankCheck.startCriteriaConfirmMsg)) {
          this.messages.errors.push(...this.form.complianceRankCheck.startCriteriaConfirmMsg)
        } else {
          this.messages.errors.push(this.form.complianceRankCheck.startCriteriaConfirmMsg)
        }
      }
      if (this.form.shortTermSellConfirmMsg && this.form.shortTermSellConfirmMsg.length > 0) {
        if (Array.isArray(this.form.shortTermSellConfirmMsg)) {
          this.messages.errors.push(...this.form.shortTermSellConfirmMsg)
        } else {
          this.messages.errors.push(this.form.shortTermSellConfirmMsg)
        }
      }
      if (this.form.preRedemptionSellConfirmAlertMsg && this.form.preRedemptionSellConfirmAlertMsg.length > 0) {
        if (Array.isArray(this.form.preRedemptionSellConfirmAlertMsg)) {
          this.messages.errors.push(
            ...this.form.preRedemptionSellConfirmAlertMsg.map(msg => msg.replace(/<br>/g, '\n'))
          )
        } else {
          this.messages.errors.push(this.form.preRedemptionSellConfirmAlertMsg.replace(/<br>/g, '\n'))
        }
      }
      if (this.form.noticeInfoAlert && this.form.noticeInfoAlert.length > 0) {
        if (Array.isArray(this.form.noticeInfoAlert)) {
          this.messages.errors.push(...this.form.noticeInfoAlert)
        } else {
          this.messages.errors.push(this.form.noticeInfoAlert)
        }
      }
      if (this.form.noticeAlert && this.form.noticeAlert.length > 0) {
        if (Array.isArray(this.form.noticeAlert)) {
          this.messages.errors.push(...this.form.noticeAlert)
        } else {
          this.messages.errors.push(this.form.noticeAlert)
        }
      }
      if (this.showWarning()) {
        const EditErrMessage = this.form.errMessage + '（' + this.form.code + '）'
        this.messages.infos.push(EditErrMessage)
      }
      this.messageKey++
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
._bold_red_m {
  font-size: 15px;
  font-weight: bold;
  color: #f00;
}
.error-message_title {
  margin: 0.5rem 0 0;
  padding: 0 3rem;
  width: 100%;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  width: 100%;
  color: red;
  font-size: 14px;
  font-weight: bold;
  white-space: pre-wrap;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  width: 100%;
  font-size: 14px;
  white-space: pre-wrap;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: #000;
  font-size: 14px;
  white-space: pre-wrap;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}
:deep(.el-checkbox__label) {
  padding-bottom: 0px;
  color: #f00;
  font-size: 16px;
  font-weight: bold;
}
:deep(.el-checkbox__input.is-checked) + .el-checkbox__label {
    color: #f00;
}
.buy-background-color_card {
  background-color: #fef0f0;
}
.sell-background-color_card {
  background-color: #ecf5ff;
}
.footer_button {
  text-align: left;
}
:deep(.other-order-contens) .el-row {
  padding: 0px 8px;
}
.status_change{
  .el-dialog__header{
    .el-dialog__title{
      font-weight: bold;
    }
  }
}
._bold_red_alart {
  font-size: 16px;
  font-weight: bold;
  padding-right: 0.5rem;
  color: red;
}
.alert_content {
  :deep(.el-form-item) {
    margin-bottom: 0;
  }
  :deep(.el-form-item__content) {
    line-height: normal;
  }
}
</style>
