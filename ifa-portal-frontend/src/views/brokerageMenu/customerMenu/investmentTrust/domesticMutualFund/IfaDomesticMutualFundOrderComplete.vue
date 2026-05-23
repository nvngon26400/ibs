<template>
  <!-- 国内投信注文受付完了ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title"
    width="1200px"
    :center="true"
    :append-to-body="appendToBody"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    :style="{ 'background-color': bgColor }"
    class="status_change"
  >

    <!-- ヘッダ -->
    <ifa-message-area
      :main-messages="['下記の内容で注文を受け付けました｡']"
    ></ifa-message-area>

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
    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >
      <el-col
        :offset="1"
        :span="22"
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
              <span>［{{ $_out(formData.a001aRequest.brandCode) }}］</span>
            </div>
            <div style="display: inline-block; flex-grow: 1; flex-basis: 0;">
              <span>{{ $_out(formData.a001aRequest.brandName) }}</span>
            </div>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文受付内容 -->
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
            <span>ご注文内容</span>
          </el-row>
          <hr>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :style="(formData.a001aRequest.tradeCd === '0' || formData.a001aRequest.tradeCd === '1') ? 'color: #E5004C; font-weight: bold' : 'color: #00847F; font-weight: bold'"
                code-list-id="DOMESTIC_MUTUAL_FUND_TRADE_CLASS"
                :disp-pattern="2"
                :select-pattern="1"
                :code-key="formData.a001aRequest.tradeCd"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 売却指定: 売却累投 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '4' || formData.a001aRequest.tradeCd === '6'
              || formData.a001aRequest.tradeCd === '8' || formData.a001aRequest.tradeCd === '10'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>売却指定</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="DESIGNATED_FOR_SALE"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.sellDesignated"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 金額: 購入累投､売却累投 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '1'
              || ((formData.a001aRequest.tradeCd === '4' || formData.a001aRequest.tradeCd === '8') && formData.a001aRequest.sellDesignated === '1')
              || (formData.a001aRequest.tradeCd === '6' || formData.a001aRequest.tradeCd === '10')"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>金額</span>
            </el-col>
            <el-col :span="16">
              <span v-if="(formData.a001aRequest.tradeCd === '6' || formData.a001aRequest.tradeCd === '10')">-</span>
              <span v-else>{{ $_out($_withCommaInteger(formData.a001aRequest.amount)) }}円</span>
            </el-col>
          </el-row>

          <!-- 口数: 購入一般､売却一般 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '7'
              || formData.a001aRequest.tradeCd === '3'
              || formData.a001aRequest.tradeCd === '0'
              || ((formData.a001aRequest.tradeCd === '4' || formData.a001aRequest.tradeCd === '8') && formData.a001aRequest.sellDesignated === '2')"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>口数</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_withCommaInteger(formData.a001aRequest.unit)) }}口</span>
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
              <ifa-text
                code-list-id="MUTUAL_FUND_DEPOSIT_TYPE"
                  :disp-pattern="depositTypeDispPattern"
                :select-pattern="1"
                :code-key="formData.a001aRequest.depositType"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!formData.a001aRequest.depositType">-</span>
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
              <span>{{ $_out($_getFormattedDateValue(formData.contractDate)) }}</span>
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
              <span>{{ $_out($_getFormattedDateValue(formData.deliveryDate)) }}</span>
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
              <span>{{ $_out($_withCommaInteger(formData.charge)) }}円</span>
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
              <span>{{ $_out($_withCommaInteger(formData.consumptionTax)) }}円</span>
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
              <span>{{ $_out($_withCommaInteger(formData.settlementAmount)) }}円</span>
            </el-col>
          </el-row>

          <!-- 分配金受取方法: 購入累投 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>分配金受取方法</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="DISTRIBUTION_RECEIVE_METHOD"
                :disp-pattern="4"
                :select-pattern="1"
                :code-key="formData.a001aRequest.distributionReceiveMethodWord"
                style="font-size: 16px;"
              ></ifa-text>
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
              <ifa-text
                code-list-id="ACCOUNT_TYPE"
                :disp-pattern="1"
                :select-pattern="2"
                :code-key="formData.a001aRequest.accountType"
                style="font-size: 16px;"
              ></ifa-text>
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
              <span>{{ $_out($_withCommaInteger(formData.quoteUnitPrice)) }}円</span>
            </el-col>
          </el-row>

          <!-- 受注数量 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注数量</span>
            </el-col>
            <el-col :span="16">
              <span v-if="formData.a001aRequest.tradeCd === '1'
                || ((formData.a001aRequest.tradeCd === '4' || formData.a001aRequest.tradeCd === '8') && formData.a001aRequest.sellDesignated === '1')
                || (formData.a001aRequest.tradeCd === '6' || formData.a001aRequest.tradeCd === '10')"
              >{{ $_out($_withCommaInteger(formData.orderQuantity)) }}口</span>
              <span v-if="formData.a001aRequest.tradeCd === '7'
                || formData.a001aRequest.tradeCd === '3'
                || formData.a001aRequest.tradeCd === '0'
                || ((formData.a001aRequest.tradeCd === '4' || formData.a001aRequest.tradeCd === '8') && formData.a001aRequest.sellDesignated === '2')"
              >{{ $_out($_withCommaInteger(formData.a001aRequest.unit)) }}口</span>
            </el-col>
          </el-row>

          <!-- 償還優遇: 購入 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '0' || formData.a001aRequest.tradeCd === '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>償還優遇</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="TRANSFERS_PREFERENTIAL_QUOTA_APPLICATION"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.norikaeYuguKbn"
                style="font-size: 16px;"
              ></ifa-text>
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
              <span>{{ $_out($_withCommaInteger(formData.contractAmount)) }}円</span>
            </el-col>
          </el-row>

          <!-- 利用ポイント: 購入累投 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>利用ポイント</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_withCommaInteger(formData.a001aRequest.point) }}</span>
              <ifa-text
                code-list-id="POINT_TYPE"
                :disp-pattern="2"
                :code-key="formData.a001aRequest.pointType"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 譲渡益税: 売却 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '3' || formData.a001aRequest.tradeCd === '7'
              || formData.a001aRequest.tradeCd === '4' || formData.a001aRequest.tradeCd === '8'
              || formData.a001aRequest.tradeCd === '6' || formData.a001aRequest.tradeCd === '10'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>譲渡益税</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_withCommaInteger(formData.yieldTax)) }}円</span>
            </el-col>
          </el-row>

          <!-- 注文後のNISA投資可能額(2021年): 購入累投 -->
          <el-row v-if="(formData.a001aRequest.tradeCd === '0' || formData.a001aRequest.tradeCd === '1')
                    && formData.a001aRequest.depositType === 'H'"
                  class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ `注文後のNISA投資可能枠（${getDeliveryDateYear(formData.deliveryDate)}年）` }}</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_withCommaInteger(formData.nisaInvestableQuote)) }}円</span>
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
              <span>{{ formatDateTime(formData.orderDate, formData.orderDayTime) }}</span>
            </el-col>
          </el-row>

          <!-- 勧誘区分: 購入 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="INVITATION_TYPE"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.kanyuKbn"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!formData.a001aRequest.kanyuKbn">-</span>
            </el-col>
          </el-row>

          <!-- 受注方法: 購入 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.receiveOrderType"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!formData.a001aRequest.receiveOrderType">-</span>
            </el-col>
          </el-row>

          <!-- レバレッジ投資信託 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '0' || formData.a001aRequest.tradeCd === '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>レバレッジ投資信託</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="LEVERAGED_INVESTMENT_TRUST"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.leverageInvestTrust"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 乗換勧誘 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '0' || formData.a001aRequest.tradeCd === '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>乗換勧誘</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="SOLICITING_TRANSFERS"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.norikaeKanyuKbn"
                style="font-size: 16px;"
              ></ifa-text>
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
              <ifa-text
                code-list-id="SAME_CURRENCY_SAME_COUNTRY_TRANSFERS"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.douitsuTukaKuniKbn"
                style="font-size: 16px;"
              ></ifa-text>
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
              <ifa-text
                code-list-id="INTERCOMPANY_MUTUAL_FUND_TRANSFER_SOLICITATION"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.tashaNorikaeKbn"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 目論見書の交付方法 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '0' || formData.a001aRequest.tradeCd === '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>目論見書の交付方法</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="PROSPECTUS_ISSUE_METHOD"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.mokuromiKoufuKbn"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 短期売却確認 -->
          <el-row v-if="formData.a001aRequest.tradeCd === '3'||
                    formData.a001aRequest.tradeCd === '4'||
                    formData.a001aRequest.tradeCd === '6' ||
                    formData.a001aRequest.tradeCd === '7'||
                    formData.a001aRequest.tradeCd === '8' ||
                    formData.a001aRequest.tradeCd === '10'"
                  class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>短期売却確認</span>
            </el-col>
            <el-col :span="16">
              <span v-if="formData.a001aRequest.tankiSellKbn === '1'">
                該当する（{{ formData.a001aRequest.shortTermSaleConfirm }}）
              </span>
              <span v-else>
                該当しない
              </span>
            </el-col>
          </el-row>

          <!-- 償還前売却確認 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '3' || formData.a001aRequest.tradeCd === '4'
              || formData.a001aRequest.tradeCd === '6' || formData.a001aRequest.tradeCd === '7'
              || formData.a001aRequest.tradeCd === '8' || formData.a001aRequest.tradeCd === '10'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>償還前売却確認</span>
            </el-col>
            <el-col :span="16">
              <span v-if="formData.a001aRequest.shokanMaeKbn === '1'">
                該当する（{{ formData.a001aRequest.preRedemptionSellConfirmSelect }}）
              </span>
              <span v-else>
                該当しない
              </span>
            </el-col>
          </el-row>

          <!-- 目論見書の交付方法 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '0' || formData.a001aRequest.tradeCd === '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>利益相反可能性の説明</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="CONFLICT_OF_INTEREST_EXPLAIN"
                :disp-pattern="2"
                :code-key="formData.a001aRequest.conflictOfInterestExplain"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!formData.a001aRequest.conflictOfInterestExplain">-</span>
            </el-col>
          </el-row>

          <!-- 確認項目 -->
          <el-row
            v-if="formData.a001aRequest.tradeCd === '0' || formData.a001aRequest.tradeCd === '1'"
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
              <span>
                <ifa-text
                  code-list-id="PROSPECTUS_SUPPLEMENTARY_DOC_CONFIRM"
                  :disp-pattern="2"
                  :select-pattern="2"
                  :code-key="formData.a001aRequest.checkMokuromi"
                  style="font-size: 16px;"
                ></ifa-text>
                <br>
              </span>
              <span v-if="formData.a001aRequest.brandSpecialClassification === '1'">
                <ifa-text
                  code-list-id="WINDOW_SPACE_FUND_PRECAUTIONS_CONSENT"
                  :disp-pattern="2"
                  :select-pattern="2"
                  style="margin-top: 8px; font-size: 16px;"
                  :code-key="formData.a001aRequest.checkMadoAki"
                ></ifa-text>
                <br>
              </span>
              <span>
                <ifa-text
                  code-list-id="COST_EXPLAINED"
                  :disp-pattern="2"
                  :select-pattern="2"
                  style="margin-top: 8px; font-size: 16px;"
                  :code-key="formData.a001aRequest.checkHiyou"
                ></ifa-text>
                <br>
              </span>
              <span>
                <ifa-text
                  code-list-id="MULTIPLE_TRADE_CLEARLY_COMM_STATED"
                  :disp-pattern="2"
                  :select-pattern="2"
                  style="margin-top: 8px; font-size: 16px;"
                  :code-key="formData.a001aRequest.checkFukusu"
                ></ifa-text>
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
    <el-row v-if="formData.a001aRequest.message || // コンプラランクチェック.メッセージ
      formData.a001aRequest.startCriteriaConfirmMsg || // コンプラランクチェック.開始基準確認メッセージ
      formData.a001aRequest.shortTermSellConfirmMsg || // 短期売却確認アラートメッセージ
      formData.a001aRequest.preRedemptionSellConfirmAlertMsg || // 償還前売却確認アラートメッセージ
      formData.a001aRequest.noticeInfoAlert || // 注意情報アラート
      formData.a001aRequest.noticeAlert // お知らせアラート
    ">
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
            <span>アラート内容確認</span>
          </el-row>
          <hr>

          <el-row
            v-if="formData.a001aRequest.message"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <ifa-text
                code-list-id="COMPLA_CHECK_BOX_WORDING"
                :disp-pattern="3"
                :select-pattern="1"
                :code-key="formData.a001aRequest.invitationCheck"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="COMPLA_CHECK_BOX_WORDING"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="formData.a001aRequest.invitationCheck"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.a001aRequest.startCriteriaConfirmMsg"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>開始基準の確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="COMPLIANCE_START_CRITERIA_CONFIRM_CHECK_BOX_WORDING"
                :disp-pattern="1"
                :select-pattern="2"
                :code-key="formData.a001aRequest.complianceRankCheckStartBaseConfirm"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.a001aRequest.shortTermSellConfirmMsg"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>短期売却確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="SHORT_TERM_SELL_CONFIRM_ALERT_CHECKBOX_WORD"
                :disp-pattern="2"
                :select-pattern="2"
                :code-key="formData.a001aRequest.shortTermSellAlertConfirm"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.a001aRequest.preRedemptionSellConfirmAlertMsg"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>償還前売却確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="PRE_REDEMPTION_SELL_CONFIRM_ALERT_CHECKBOX_WORD"
                :disp-pattern="2"
                :select-pattern="2"
                :code-key="formData.a001aRequest.preRedemptionSellConfirmAlertConfirm"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.a001aRequest.noticeInfoAlert"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
                :code-key="formData.a001aRequest.noticeInfoAlertConfirm"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.a001aRequest.noticeAlert"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
                :code-key="formData.a001aRequest.noticeAlertConfirm"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文状況一覧へボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnToOrderStatusList"
          text="注文状況一覧へ"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="handleClickA001"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaDomesticMutualFundOrderCompleteFormModel } from './js/IfaDomesticMutualFundOrderCompleteFormModel'
import { getFormattedTimeValue, getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import IfaMessageArea from '@/components/Message/IfaMessageArea'

export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
    customerInfo: { type: Object, required: true },
    appendToBody: { type: Boolean, required: true }
  },
  emits: ['dialog-close', 'move-to-order-list', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaDomesticMutualFundOrderCompleteFormModel()
    }
  },
  computed: {
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.customerInfo.accountNumber}`
    },
    customerName() {
      return this.customerInfo.customerNameKanji + '（' + this.customerInfo.customerNameKana + '）'
    },
    bgColor() {
      if (this.formData.a001aRequest.tradeCd === '0' || this.formData.a001aRequest.tradeCd === '1') {
        return '#fef0f0'
      } else {
        return '#ecf5ff'
      }
    },
    /** 「預り区分」表示パターン判別 */
    depositTypeDispPattern() {
      // 顧客共通情報.ジュニアISA契約区分=1：契約　の場合
      if (this.customerInfo.jrIsaContractType === '1') {
        // 取引種別=購入（累投）　または　購入（一般）　の場合
        if (this.formData.a001aRequest.tradeCd === '1' || this.formData.a001aRequest.tradeCd === '0') {
          // ジュニアNISA口座を選択　かつ (顧客共通情報.ジュニア特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
          if (this.formData.a001aRequest.accountType === '1' && !(this.customerInfo.jrTokuteiKouzaKbn === '1' || this.customerInfo.jrTokuteiKouzaKbn === '2')) {
            return 6
          // 総合口座を選択　かつ (顧客共通情報.特定口座区分= (1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
          } else if (this.formData.a001aRequest.accountType === ' ' && !(this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2')) {
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
    }
  },
  methods: {
    getDeliveryDateYear(deliveryDate) {
      return deliveryDate.substring(0, 4)
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('dialog-close')
    },
    // 注文状況一覧へボタン
    handleClickA001() {
      this.$emit('move-to-order-list')
    },
    formatDateTime(date, time) {
      const dateTime = date + ' ' + getFormattedTimeValue(time, 'time4')
      return getFormattedDateTimeValue(dateTime, 'datetime12')
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
.formData-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
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
</style>
