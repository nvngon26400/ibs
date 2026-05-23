<template>
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">

      <!-- 売却不可明細 -->
      <el-row
        v-if="form.sellUnableDetailDisplayClassification === '1'"
        style="margin-bottom: 0.5rem;"
      >
        <ifa-button
          id="btnDetail"
          text="売却不可明細"
          icon="Document"
          small
          action-type="requestAction"
          action-id="SUB0202_010201-03#A001"
          @response-handler="sellUnableDetailA002($event)"
        ></ifa-button>
      </el-row>

      <!-- 口座種別選択 -->
      <el-row style="padding-bottom: 1rem;">
        <el-col
          :span="12"
          style="display: flex;"
        >
          <ifa-input-radio
            v-if="customerInfo.jrIsaContractType !== ' ' && customerInfo.jrIsaContractType !== '9'"
            v-model="form.accountClassification"
            code-list-id="original"
            :original-list="accountTypeList"
            @change="accountSelectionA012($event)"
          >
          </ifa-input-radio>
          <ifa-button
            v-if="isExpand()"
            action-type="originalAction"
            text="全て閉じる"
            color="secondary"
            icon="Remove"
            small
            style="margin-left: 20px;"
            @app-action-handler="wholeDetailToggleSwitchA014()"
          ></ifa-button>
          <ifa-button
            v-else
            action-type="originalAction"
            text="全て開く"
            color="secondary"
            icon="CirclePlus"
            small
            style="margin-left: 20px;"
            @app-action-handler="wholeDetailToggleSwitchA014()"
          ></ifa-button>
        </el-col>
        <el-col :span="6">
          <table class="_table__body">
            <tbody>
              <tr>
                <th
                  class="_table__header __center"
                  style="width: 50%;"
                >評価額総合計</th>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(form.getTotalAssessedValueAll)) }}</td>
              </tr>
            </tbody>
          </table>
        </el-col>
        <el-col :span="6">
          <table class="_table__body">
            <tbody>
              <tr>
                <th
                  class="_table__header __center"
                  style="width: 50%;"
                >評価損益総合計</th>
                <td class="_table__data __right">
                  <span :class="[getProfitColor(form.getTotalProfitAll), '__right']">
                    {{ $_signedWithCommaNoneZeroPadding($_out(form.getTotalProfitAll)) }}
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </el-col>
      </el-row>

      <!-- 商品種別選択 -->
      <el-row
        v-if="existProductList.length > 2"
        style="padding-bottom: 1rem;"
      >
        <ifa-input-radio
          v-model="securitySelect"
          code-list-id="original"
          :original-list="existProductList"
          @change="productSelectionA013($event)"
        >
        </ifa-input-radio>
      </el-row>

      <!-- 国内株式 -->
      <el-row
        v-for="table in form.domesticStockList"
        :key="table.index"
      >
        <el-row
          v-if="table.numberOfDepositedIssues > 0"
          style="margin-top: 10px;"
        >
          <el-col :span="12">
            <div>
              <div
                class="clickable"
                @click="detailToggleSwitchA016('stock', table.depositBalanceAccountTypeName, table.depositType)"
              >
                <el-icon
                  v-if="checkVisible('stock', table.depositBalanceAccountTypeName, table.depositType)"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-else
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
              </div>
              <span
                class="_bold_black_s"
                style="margin-left: 0.5rem;"
              >株式</span>
              <el-tag
                v-if="hasJrNisaAccount"
                size="small"
                :type="table.depositBalanceAccountTypeName === '2' ? 'success' : ''"
                style="margin-left: 0.5rem;"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_ACCOUNT_TYPE', 1, table.depositBalanceAccountTypeName) }}</el-tag>
              <el-tag
                size="small"
                :type="getTagType(table.depositType)"
                :style="hasJrNisaAccount ? '' : 'margin-left: 0.5rem;'"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_SPECIFIC_DEPOSIT_TYPE', 1, table.depositType) }}</el-tag>
              <el-badge
                :value="$_withCommaInteger(table.numberOfDepositedIssues)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価額合計</th>
                  <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(table.valuationTotal)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価損益合計</th>
                  <td class="_table__data __right">
                    <span :class="[getProfitColor(table.getProfitAll), '__right']">
                      {{ $_signedWithCommaNoneZeroPadding($_out(table.getProfitAll)) }}
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
        <el-row
          v-if="checkVisible('stock', table.depositBalanceAccountTypeName, table.depositType)"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <table class="_table__horizontal _table__body">
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 5rem;"
                  >銘柄コード</th>
                  <th
                    class="_table__header __center"
                    style="width: 36%; min-width: 10rem;"
                  >銘柄名</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >保有株数<br>（売却注文中）</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >
                    <span v-if="table.depositType === '3'">参考単価</span>
                    <span v-else>取得単価</span>
                    <br>現在値</th>
                  <th
                    class="_table__header __center"
                    style="width: 12%; min-width: 8rem;"
                  >
                    <span v-if="table.depositType === '3'">参考金額</span>
                    <span v-else>取得金額</span>
                    <br>評価額</th>
                  <th
                    class="_table__header __center"
                    style="width: 12%; min-width: 8rem;"
                  >評価損益</th>
                  <th
                    class="_table__header __center"
                    colspan="2"
                    style="width: 10%; min-width: 135px;"
                  >取引</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(asset, index) in table.depositDetailList"
                  :key="index"
                >
                  <td class="_table__data __center">{{ asset.brandCode }}</td>
                  <td class="_table__data __left">{{ asset.brandName }}</td>
                  <td
                    class="_table__data __right"
                  >
                    <el-link
                      :underline="false"
                      disable-transitions
                      class="__link_text"
                      @click="depositBalanceDetailA018('国内株式', asset, table.depositBalanceAccountTypeName)"
                    >{{ $_withCommaInteger($_out(asset.holdingStock)) }}</el-link>
                    <div>({{ $_withCommaInteger($_out(asset.sellingVolume)) }})</div>
                  </td>
                  <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(asset.acquirePriceReferencePrice)) }}<br>({{ $_withCommaNoneZeroPadding($_out(asset.currentPrice)) }})</td>
                  <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(asset.acquireAmountReferenceAmount)) }}<br>({{ $_withCommaNoneZeroPadding($_out(asset.valuation)) }})</td>
                  <td class="_table__data __right">
                    <span :class="[asset.domesticStockListValuation < 0 ? 'font-color__minus' : asset.domesticStockListValuation > 0 ? 'font-color__plus' : '', '__right bold']">
                      {{ $_signedWithCommaNoneZeroPadding($_out(asset.domesticStockListValuation)) }}
                    </span>
                  </td>
                  <td
                    v-if="asset.buyDisplayClassification !== '0'"
                    class="_table__data2 __center"
                  >
                    <ifa-button
                      text="現買"
                      color="buy"
                      small
                      action-type="originalAction"
                      @app-action-handler="domesticStockTradingA003('1', asset)"
                    ></ifa-button>
                  </td>
                  <td
                    v-if="asset.saleDisplayClassification !== '0'"
                    class="_table__data2 __center"
                  >
                    <ifa-button
                      text="現売"
                      color="sell"
                      small
                      :disabled="asset.saleDisplayClassification === '3'"
                      action-type="originalAction"
                      @app-action-handler="domesticStockTradingA003('2', asset)"
                    ></ifa-button>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-row>

      <!-- 投資信託 -->
      <el-row
        v-if="form.investmentTrustList.length > 0"
        style="margin-top: 10px;"
      >
        <el-col
          :span="24"
          class="__right"
        >
          <ifa-link
            ref="ifaLinkTotalReturn"
            :url-id="1"
            :pattern-id="3"
            http-method="GET"
            disp-name="投信トータルリターン（サマリー）"
            manual-init
          >
          </ifa-link>
        </el-col>
      </el-row>
      <el-row
        v-for="table in form.investmentTrustList"
        :key="table.index"
      >
        <el-row
          v-if="table.numberOfDepositedIssues > 0"
          style="margin-top: 10px;"
        >
          <el-col :span="12">
            <div>
              <div
                class="clickable"
                @click="detailToggleSwitchA016('trust', table.depositBalanceAccountTypeName, table.depositType, table.reinvest)"
              >
                <el-icon
                  v-if="checkVisible('trust', table.depositBalanceAccountTypeName, table.depositType, table.reinvest)"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-else
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
              </div>
              <span
                class="_bold_black_s"
                style="margin-left: 0.5rem;"
              >投資信託</span>
              <el-tag
                size="small"
                :type="table.depositBalanceAccountTypeName === '2' ? 'success' : ''"
                style="margin-left: 0.5rem;"
              >
                <span v-if="table.reinvest === '1'">口数</span>
                <span v-else>金額</span>
                <span v-if="hasJrNisaAccount">/{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_ACCOUNT_TYPE', 1, table.depositBalanceAccountTypeName) }}</span>
              </el-tag>
              <el-tag
                size="small"
                :type="getTagType(table.depositType)"
                :style="hasJrNisaAccount ? '' : 'margin-left: 0.5rem;'"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_SPECIFIC_DEPOSIT_TYPE', 1, table.depositType) }}</el-tag>
              <el-badge
                :value="$_withCommaNoneZeroPadding(table.numberOfDepositedIssues)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価額合計</th>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(table.valuationTotal)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価損益合計</th>
                  <td class="_table__data __right">
                    <span :class="[getProfitColor(table.getProfitAll), '__right']">
                      {{ $_signedWithCommaInteger($_out(table.getProfitAll)) }}
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
        <el-row
          v-if="checkVisible('trust', table.depositBalanceAccountTypeName, table.depositType, table.reinvest)"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <table class="_table__horizontal _table__body">
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 5rem;"
                  >銘柄コード</th>
                  <th
                    class="_table__header __center"
                    style="width: 36%; min-width: 10rem;"
                  >ファンド名</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >保有口数<br>（売却注文中）</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >
                    <span v-if="table.depositType === '3'">参考単価</span>
                    <span v-else>取得単価</span>
                    <br>（基準価格）</th>
                  <th
                    class="_table__header __center"
                    style="width: 12%; min-width: 8rem;"
                  >
                    <span v-if="table.depositType === '3'">参考金額</span>
                    <span v-else>取得金額</span>
                    <br>（評価額）</th>
                  <th
                    class="_table__header __center"
                    style="width: 12%; min-width: 8rem;"
                  >評価損益<br>個別元本</th>
                  <th
                    class="_table__header __center"
                    colspan="2"
                    style="width: 10%; min-width: 135px;"
                  >取引</th>
                  <th
                    v-if="table.reinvest === '2'"
                    class="_table__header __center"
                    style="width: 6%; min-width: 6rem;"
                  >分配金<br>受取方法<br>
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(asset, index) in table.depositDetailList"
                  :key="index"
                >
                  <td class="_table__data __center">{{ asset.brandCode }}</td>
                  <td class="_table__data __left">{{ asset.fundName }}</td>
                  <td
                    class="_table__data __right"
                  >
                    <el-link
                      :underline="false"
                      disable-transitions
                      class="__link_text"
                      @click="depositBalanceDetailA018(table.reinvest === '1' ? '投資信託口数' : '投資信託金額', asset, table.depositBalanceAccountTypeName)"
                    >{{ $_withCommaInteger($_out(asset.unitVolume)) }}</el-link>
                    <div>({{ $_withCommaInteger($_out(asset.sellingVolume)) }})</div>
                  </td>
                  <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(asset.acquirePriceReferencePrice)) }}<br>({{ asset.basePrice8 !== '0' ? $_withCommaInteger($_out(asset.basePrice8)) : '-' }})</td>
                  <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(asset.acquireAmountReferenceAmount)) }}<br>({{ $_withCommaInteger($_out(asset.valuation)) }})</td>
                  <td class="_table__data __right">
                    <span :class="[getProfitColor(asset.mutualFundListProfitLossTotal), '__right']">
                      {{ $_signedWithCommaNoneZeroPadding($_out(asset.mutualFundListProfitLossTotal)) }}
                    </span>
                    <br>{{ $_withCommaNoneZeroPadding($_out(asset.individualPrincipal)) }}
                  </td>
                  <td class="_table__data2 __center"
                      colspan="2"
                      style="width: 10%; min-width: 7rem;"
                  >
                    <ifa-button
                      v-if="asset.accumulationLink !== '0'"
                      ref="ifaLinkMyAssset"
                      text="積立"
                      class="accumulation-class"
                      small
                      action-type="originalAction"
                      @app-action-handler="handleRedirectAccumulate(asset)"
                    ></ifa-button>
                    <ifa-button
                      v-if="asset.buyDisplayClassification !== '0'"
                      text="買付"
                      color="buy"
                      small
                      action-type="originalAction"
                      @app-action-handler="mutualFundTradingA005('0', asset)"
                    ></ifa-button>
                    <ifa-button
                      v-if="asset.saleDisplayClassification !== '0'"
                      text="売却"
                      color="sell"
                      small
                      :disabled="asset.saleDisplayClassification === '3'"
                      action-type="originalAction"
                      @app-action-handler="mutualFundTradingA005('1', asset)"
                    ></ifa-button>
                  </td>
                  <td
                    v-if="table.reinvest === '2'"
                    class="_table__data __center"
                  >{{ asset.distributionReceiveMethod }}<br>
                    <ifa-button
                      v-if="asset.distributionreceiveMethodchangedisplayclassification !== '0'"
                      text="変更"
                      small
                      action-type="originalAction"
                      @app-action-handler="distributionReceiveMethodChangeA007(asset)"
                    ></ifa-button>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-row>

      <!-- 国内債券 -->
      <el-row
        v-for="table in form.domesticBondsList"
        :key="table.index"
      >
        <el-row
          v-if="table.numberOfDepositedIssues > 0"
          style="margin-top: 10px;"
        >
          <el-col :span="12">
            <div>
              <div
                class="clickable"
                @click="detailToggleSwitchA016('bond', table.depositBalanceAccountTypeName, table.depositType)"
              >
                <el-icon
                  v-if="checkVisible('bond', table.depositBalanceAccountTypeName, table.depositType)"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-else
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
              </div>
              <span
                class="_bold_black_s"
                style="margin-left: 0.5rem;"
              >国内債券</span>
              <el-tag
                v-if="hasJrNisaAccount"
                size="small"
                :type="table.depositBalanceAccountTypeName === '2' ? 'success' : ''"
                style="margin-left: 0.5rem;"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_ACCOUNT_TYPE', 1, table.depositBalanceAccountTypeName) }}</el-tag>
              <el-tag
                size="small"
                :type="getTagType(table.depositType)"
                :style="hasJrNisaAccount ? '' : 'margin-left: 0.5rem;'"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_SPECIFIC_DEPOSIT_TYPE', 1, table.depositType) }}</el-tag>
              <el-badge
                :value="$_withCommaInteger(table.numberOfDepositedIssues)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価額合計</th>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(table.valuationTotal)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
        <el-row
          v-if="checkVisible('bond', table.depositBalanceAccountTypeName, table.depositType)"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <table class="_table__horizontal _table__body">
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 5rem;"
                  >銘柄コード</th>
                  <th
                    class="_table__header __center"
                    style="width: 36%; min-width: 10rem;"
                  >銘柄名</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 6rem;"
                  >利率(%)</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >償還日</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >利払日</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >保有額面</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >
                    <span v-if="table.depositType === '3'">参考単価</span>
                    <span v-else>取得単価</span>
                  </th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >約定為替</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >参考為替</th>
                  <th
                    class="_table__header __center"
                    style="width: 12%; min-width: 8rem;"
                  >評価額</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(asset, index) in table.depositDetailList"
                  :key="index"
                >
                  <td class="_table__data __center">{{ $_out(asset.brandCode) }}</td>
                  <td class="_table__data __left">{{ $_out(asset.brandName) }}</td>
                  <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(asset.compoundInterestPercent), 3) }}</td>
                  <td class="_table__data __right">{{ $_getFormattedDateValue($_out(asset.redemptionDate)) }}</td>
                  <td class="_table__data __right">{{ $_out(asset.interestPaymentDate) }}</td>
                  <td
                    class="_table__data __right"
                  >
                    <el-link
                      :underline="false"
                      disable-transitions
                      class="__link_text"
                      @click="depositBalanceDetailA018('国内債券', asset, table.depositBalanceAccountTypeName)"
                    >{{ $_withCommaInteger($_out(asset.volumeYen)) }}</el-link>
                  </td>
                  <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(asset.acquirePriceReferencePrice)) }}</td>
                  <td class="_table__data __right">{{ $_out(asset.contractExchange) }}</td>
                  <td class="_table__data __right">{{ $_out(asset.exchangeRate) }}</td>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(asset.valuation)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-row>

      <!-- 外国債券 -->
      <el-row
        v-for="table in form.foreignBondsList"
        :key="table.index"
      >
        <el-row
          v-if="table.numberOfDepositedIssues > 0"
          style="margin-top: 10px;"
        >
          <el-col :span="12">
            <div>
              <div
                class="clickable"
                @click="detailToggleSwitchA016('foreBond', table.depositBalanceAccountTypeName, table.depositType)"
              >
                <el-icon
                  v-if="checkVisible('foreBond', table.depositBalanceAccountTypeName, table.depositType)"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-else
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
              </div>
              <span
                class="_bold_black_s"
                style="margin-left: 0.5rem;"
              >外国債券</span>
              <el-tag
                v-if="hasJrNisaAccount"
                size="small"
                :type="table.depositBalanceAccountTypeName === '2' ? 'success' : ''"
                style="margin-left: 0.5rem;"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_ACCOUNT_TYPE', 1, table.depositBalanceAccountTypeName) }}</el-tag>
              <el-tag
                size="small"
                :type="getTagType(table.depositType)"
                :style="hasJrNisaAccount ? '' : 'margin-left: 0.5rem;'"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_SPECIFIC_DEPOSIT_TYPE', 1, table.depositType) }}</el-tag>
              <el-badge
                :value="$_withCommaInteger(table.numberOfDepositedIssues)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価額合計</th>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(table.valuationTotal)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
        <el-row
          v-if="checkVisible('foreBond', table.depositBalanceAccountTypeName, table.depositType)"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <table class="_table__horizontal _table__body">
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 5rem;"
                  >銘柄コード</th>
                  <th
                    class="_table__header __center"
                    style="width: 36%; min-width: 10rem;"
                  >銘柄名</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >保有額面</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >買付単価</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >外貨建評価額</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >参考為替</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 4rem;"
                  >円換算評価額</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(asset, index) in table.depositDetailList"
                  :key="index"
                >
                  <td class="_table__data __center">{{ asset.brandCode }}</td>
                  <td class="_table__data __left">{{ asset.brandName }}</td>
                  <td
                    class="_table__data __right"
                  >
                    <el-link
                      :underline="false"
                      disable-transitions
                      class="__link_text"
                      @click="depositBalanceDetailA018('外国債券', asset, table.depositBalanceAccountTypeName)"
                    >{{ $_withCommaInteger($_out(asset.volumeForeign)) }} {{ asset.currencyCode }}</el-link>
                  </td>
                  <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(asset.unitPrice)) }} {{ asset.currencyCode }}</td>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(asset.foreignValuation17)) }} {{ asset.currencyCode }}</td>
                  <td class="_table__data __right">{{ asset.currencyCode !== 'INR' ? $_withCommaZeroPadding($_out(asset.exchangeRate), 4) : asset.currencyCode !== 'IDR' ? $_withCommaZeroPadding($_out(asset.exchangeRate), 6) : $_withCommaZeroPadding($_out(asset.exchangeRate), 2) }}
                    <span v-if="asset.currencyCode !== 'JPY'">円/{{ asset.currencyCode }}</span></td>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(asset.yenConversionValuation)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-row>

      <!-- 外国株式 -->
      <el-row
        v-for="table in form.foreignStocksList"
        :key="table.index"
      >
        <el-row
          v-if="table.numberOfDepositedIssues > 0"
          style="margin-top: 10px;"
        >
          <el-col :span="12">
            <div>
              <div
                class="clickable"
                @click="detailToggleSwitchA016('foreStock', table.depositBalanceAccountTypeName, table.depositType)"
              >
                <el-icon
                  v-if="checkVisible('foreStock', table.depositBalanceAccountTypeName, table.depositType)"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-else
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
              </div>
              <span
                class="_bold_black_s"
                style="margin-left: 0.5rem;"
              >外国株式</span>
              <el-tag
                v-if="hasJrNisaAccount"
                size="small"
                :type="table.depositBalanceAccountTypeName === '2' ? 'success' : ''"
                style="margin-left: 0.5rem;"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_ACCOUNT_TYPE', 1, table.depositBalanceAccountTypeName) }}</el-tag>
              <el-tag
                size="small"
                :type="getTagType(table.depositType)"
                :style="hasJrNisaAccount ? '' : 'margin-left: 0.5rem;'"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_SPECIFIC_DEPOSIT_TYPE', 1, table.depositType) }}</el-tag>
              <el-badge
                :value="$_withCommaInteger(table.numberOfDepositedIssues)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価額合計</th>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(table.valuationTotal)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価損益合計</th>
                  <td class="_table__data __right">
                    <span :class="[getProfitColor(table.getProfitAll), '__right']">
                      {{ $_signedWithCommaInteger($_out(table.getProfitAll)) }}
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
        <el-row
          v-if="checkVisible('foreStock', table.depositBalanceAccountTypeName, table.depositType)"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <table class="_table__horizontal _table__body">
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 5rem;"
                  >ティッカー/<br>銘柄コード</th>
                  <th
                    class="_table__header __center"
                    style="width: 36%; min-width: 10rem;"
                  >銘柄名</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >保有株数<br>（注文中）</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >保護区分</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >取得単価<br>現在値</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >外貨建評価損益</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >評価為替レート</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >評価額<br>（円貨）</th>
                  <th
                    class="_table__header __center"
                    style="width: 20%; min-width: 7rem;"
                  >評価損益（円貨）</th>
                  <th
                    class="_table__header __center"
                    colspan="2"
                    style="width: 10%; min-width: 135px;"
                  >取引</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(asset, index) in table.depositDetailList"
                  :key="index"
                >
                  <td class="_table__data __center">{{ asset.brandCode }}</td>
                  <td class="_table__data __left">{{ asset.brandName }}</td>
                  <td
                    class="_table__data __right"
                  >
                    <el-link
                      :underline="false"
                      disable-transitions
                      class="__link_text"
                      @click="depositBalanceDetailA018('外国株式', asset, table.depositBalanceAccountTypeName)"
                    >{{ $_withCommaInteger($_out(asset.holdingQuantity)) }}</el-link>
                    <div>({{ $_withCommaInteger($_out(asset.unactualQuantity)) }})</div>
                  </td>
                  <td class="_table__data __right">
                    <ifa-text
                      code-list-id="DEPOSIT_TYPE"
                      :disp-pattern="1"
                      :code-key="asset.protectType"
                    ></ifa-text></td>
                  <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(asset.openPrice)) }} {{ asset.currencyCode }}<br>{{ $_withCommaNoneZeroPadding($_out(asset.currentPrice)) }} {{ asset.currencyCode }}</td>
                  <td class="_table__data __right">
                    <span :class="getProfitColor(asset.foreignProfitAndLoss)">
                      {{ $_signedWithCommaNoneZeroPadding($_out(asset.foreignProfitAndLoss)) }} {{ asset.currencyCode }}
                    </span>
                  </td>
                  <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(asset.fxValuationRate), 0) }}円/{{ asset.currencyCode }}</td>
                  <td class="_table__data __right">
                    <span>
                      {{ $_withCommaInteger($_out(asset.valuation)) }}
                    </span>
                  </td>
                  <td class="_table__data __right">
                    <span :class="[getProfitColor(asset.yenProfitLoss), ' __right']">
                      {{ $_signedWithCommaInteger($_out(asset.yenProfitLoss)) }}
                    </span>
                  </td>
                  <td
                    v-if="asset.buyDisplayClassification !== '0'"
                    class="_table__data2 __center"
                  >
                    <ifa-button
                      text="現買"
                      color="buy"
                      small
                      action-type="originalAction"
                      @app-action-handler="foreignStockTradingA008('0', asset)"
                    ></ifa-button>
                  </td>
                  <td
                    v-if="asset.saleDisplayClassification !== '0'"
                    class="_table__data2 __center"
                  >
                    <ifa-button
                      text="現売"
                      color="sell"
                      small
                      :disabled="asset.saleDisplayClassification === '3'"
                      action-type="originalAction"
                      @app-action-handler="foreignStockTradingA008('1', asset)"
                    ></ifa-button>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-row>

      <!-- 外貨建MMF -->
      <el-row
        v-for="table in form.foreignCurrencyMmfList"
        :key="table.index"
      >
        <el-row
          v-if="table.numberOfDepositedIssues > 0"
          style="margin-top: 10px;"
        >
          <el-col :span="12">
            <div>
              <div
                class="clickable"
                @click="detailToggleSwitchA016('mmf', table.depositBalanceAccountTypeName, table.depositType)"
              >
                <el-icon
                  v-if="checkVisible('mmf', table.depositBalanceAccountTypeName, table.depositType)"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-else
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
              </div>
              <span
                class="_bold_black_s"
                style="margin-left: 0.5rem;"
              >外貨建MMF</span>
              <el-tag
                v-if="hasJrNisaAccount"
                size="small"
                :type="table.depositBalanceAccountTypeName === '2' ? 'success' : ''"
                style="margin-left: 0.5rem;"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_ACCOUNT_TYPE', 1, table.depositBalanceAccountTypeName) }}</el-tag>
              <el-tag
                size="small"
                :type="getTagType(table.depositType)"
                :style="hasJrNisaAccount ? '' : 'margin-left: 0.5rem;'"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_SPECIFIC_DEPOSIT_TYPE', 1, table.depositType) }}</el-tag>
              <el-badge
                :value="$_withCommaInteger(table.numberOfDepositedIssues)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価額合計</th>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(table.valuationTotal)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価損益合計</th>
                  <td class="_table__data __right">
                    <span :class="[getProfitColor(table.getProfitAll), '__right']">
                      {{ $_signedWithCommaInteger($_out(table.getProfitAll)) }}
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
        <el-row
          v-if="checkVisible('mmf', table.depositBalanceAccountTypeName, table.depositType)"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <table class="_table__horizontal _table__body">
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 5rem;"
                  >銘柄コード</th>
                  <th
                    class="_table__header __center"
                    style="width: 36%; min-width: 10rem;"
                  >ファンド名</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >保有口数<br>(注文中)</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >評価額（外貨）</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >評価為替レート</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >評価額<br>（円貨）</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >評価損益（円貨）</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(asset, index) in table.depositDetailList"
                  :key="index"
                >
                  <td class="_table__data __center">{{ asset.brandCode }}</td>
                  <td class="_table__data __left">{{ asset.fundName }}</td>
                  <td
                    class="_table__data __right"
                  >
                    <el-link
                      :underline="false"
                      disable-transitions
                      class="__link_text"
                      @click="depositBalanceDetailA018('外貨建MMF', asset, table.depositBalanceAccountTypeName)"
                    >{{ $_withCommaInteger($_out(asset.unitVolume)) }}</el-link>
                    <div>({{ $_withCommaInteger($_out(asset.unactualQuantity)) }})</div>
                  </td>
                  <td class="_table__data __right">
                    <span>{{ $_withCommaNoneZeroPadding($_out(asset.foreignValuation)) }}{{ asset.currencyCode }}</span>
                  </td>
                  <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(asset.fxValuationRate), 2) }}円/{{ asset.currencyCode }}</td>
                  <td class="_table__data __right">
                    <span>
                      {{ $_withCommaInteger($_out(asset.valuation)) }}
                    </span>
                  </td>
                  <td class="_table__data __right">
                    <span :class="[asset.yenProfitLoss < 0 ? 'font-color__minus' : asset.yenProfitLoss > 0 ? 'font-color__plus' : '', '__right bold']">
                      {{ $_signedWithCommaInteger($_out(asset.yenProfitLoss)) }}
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-row>

      <!-- ST（セキュリティ・トークン） -->
      <el-row
        v-for="table in form.securityTokenList"
        :key="table.index"
      >
        <el-row
          v-if="table.numberOfDepositedIssues > 0"
          style="margin-top: 10px;"
        >
          <el-col :span="12">
            <div>
              <div
                class="clickable"
                @click="detailToggleSwitchA016('st', table.depositBalanceAccountTypeName, table.depositType)"
              >
                <el-icon
                  v-if="checkVisible('st', table.depositBalanceAccountTypeName, table.depositType)"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-else
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
              </div>
              <span
                class="_bold_black_s"
                style="margin-left: 0.5rem;"
              >ST（セキュリティ・トークン）</span>
              <el-tag
                v-if="hasJrNisaAccount"
                size="small"
                :type="table.depositBalanceAccountTypeName === '2' ? 'success' : ''"
                style="margin-left: 0.5rem;"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_ACCOUNT_TYPE', 1, table.depositBalanceAccountTypeName) }}</el-tag>
              <el-tag
                size="small"
                :type="getTagType(table.depositType)"
                :style="hasJrNisaAccount ? '' : 'margin-left: 0.5rem;'"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_SPECIFIC_DEPOSIT_TYPE', 1, table.depositType) }}</el-tag>
              <el-badge
                :value="$_withCommaInteger(table.numberOfDepositedIssues)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価額合計</th>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(table.valuationTotal)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
          <el-col :span="6">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 50%;"
                  >評価損益合計</th>
                  <td class="_table__data __right">
                    <span :class="[getProfitColor(table.getProfitAll), '__right']">
                      {{ $_signedWithCommaInteger($_out(table.getProfitAll)) }}
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
        <el-row
          v-if="checkVisible('st', table.depositBalanceAccountTypeName, table.depositType)"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <table class="_table__horizontal _table__body">
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 5rem;"
                  >銘柄コード</th>
                  <th
                    class="_table__header __center"
                    style="width: 36%; min-width: 10rem;"
                  >銘柄名</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >保有株数</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >平均取得価額<br>「円」</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 7rem;"
                  >現在値<br>「円」</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >評価額<br>「円」</th>
                  <th
                    class="_table__header __center"
                    style="width: 10%; min-width: 8rem;"
                  >評価損益<br>「円」</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(asset, index) in table.depositDetailList"
                  :key="index"
                >
                  <td class="_table__data __center">{{ $_out(asset.brandCode) }}</td>
                  <td class="_table__data __left">{{ $_out(asset.brandName) }}</td>
                  <td
                    class="_table__data __right"
                  >
                    {{ $_withCommaInteger($_out(asset.contractStandardDeposit)) }}
                  </td>
                  <td class="_table__data __right">
                    <span>{{ $_withCommaNoneZeroPadding($_out(asset.openPrice)) }}</span>
                  </td>
                  <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(asset.price)) }}</td>
                  <td class="_table__data __right">
                    <span>
                      {{ $_withCommaNoneZeroPadding($_out(asset.valuation)) }}
                    </span>
                  </td>
                  <td class="_table__data __right">
                    <span :class="[asset.yenProfitLoss < 0 ? 'font-color__minus' : asset.yenProfitLoss > 0 ? 'font-color__plus' : '', '__right bold']">
                      {{ $_signedWithCommaInteger($_out(asset.yenProfitLoss)) }}
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-row>

      <!-- その他商品 -->
      <el-row
        v-for="table in form.otherSecurityList"
        :key="table.index"
      >
        <el-row
          v-if="table.numberOfDepositedIssues > 0"
          style="margin-top: 10px;"
        >
          <el-col :span="12">
            <div>
              <div
                class="clickable"
                @click="detailToggleSwitchA016('other', table.depositBalanceAccountTypeName, table.depositType)"
              >
                <el-icon
                  v-if="checkVisible('other', table.depositBalanceAccountTypeName, table.depositType)"
                  class="header-icon"
                ><Remove></Remove></el-icon>
                <el-icon
                  v-else
                  class="header-icon"
                ><CirclePlus></CirclePlus></el-icon>
              </div>
              <span
                class="_bold_black_s"
                style="margin-left: 0.5rem;"
              >その他商品</span>
              <el-tag
                v-if="hasJrNisaAccount"
                size="small"
                :type="table.depositBalanceAccountTypeName === '2' ? 'success' : ''"
                style="margin-left: 0.5rem;"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_ACCOUNT_TYPE', 1, table.depositBalanceAccountTypeName) }}</el-tag>
              <el-tag
                size="small"
                :type="getTagType(table.depositType)"
                :style="hasJrNisaAccount ? '' : 'margin-left: 0.5rem;'"
              >{{ $_getCodeValue('DEPOSIT_BALANCE_LIST_SPECIFIC_DEPOSIT_TYPE', 1, table.depositType) }}</el-tag>
              <el-badge
                :value="$_withCommaInteger(table.numberOfDepositedIssues)"
                class="badge-item"
                type="primary"
              ></el-badge>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-if="checkVisible('other', table.depositBalanceAccountTypeName, table.depositType)"
          style="margin-bottom: 0.6rem;"
        >
          <el-col :span="24">
            <table class="_table__horizontal _table__body">
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 25%; min-width: 5rem;"
                  >商品分類</th>
                  <th
                    class="_table__header __center"
                    style="width: 50%; min-width: 10rem;"
                  >銘柄名/ファンド名</th>
                  <th
                    class="_table__header __center"
                    style="width: 25%; min-width: 6rem;"
                  >数量</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(asset, index) in table.depositDetailList"
                  :key="index"
                >
                  <td class="_table__data __center">{{ asset.securityClass }}</td>
                  <td class="_table__data __left">{{ asset.brandNameFundName }}</td>
                  <td class="_table__data __right">{{ $_withCommaInteger($_out(asset.quantity)) }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-row>
      <div
        v-if="!existDetail"
        style="text-align: center;"
      >{{ form.noDetailMsg }}</div>

    </div>

    <!-- ダイアログ -->
    <ifa-distribution-receive-method-change-input
      ref="ifaDistributionReceiveMethodChangeInput"
      :is-visible="dialogVisible.changeDialogVisible"
      :dialog-data="dialogData"
      @close="handleClose"
      @change-complete="handleChangeComplete"
    ></ifa-distribution-receive-method-change-input>
    <ifa-distribution-receive-method-change-complete
      ref="ifaDistributionReceiveMethodChangeComplete"
      :is-visible="dialogVisible.completeDialogVisible"
      :dialog-data="dialogData"
      @close="handleClose"
      @display-update="redraw"
    ></ifa-distribution-receive-method-change-complete>
    <ifa-sell-unable-detail
      :is-visible="dialogVisible.cannotSellDetailVisible"
      :form-data="sellUnableDetailForm"
      @close="handleClose"
    ></ifa-sell-unable-detail>
    <ifa-deposit-balance-detail
      ref="IfaDepositBalanceDetail"
      :param="a018RequestModel"
      :customer-info="customerInfo"
      :is-visible="dialogVisible.balanceDetailVisible"
      @close="handleClose"
    >
    </ifa-deposit-balance-detail>

    <!-- Ifa-requester -->
    <ifa-requester
      id="IfaHoldingSecurityListInitializeA001"
      action-type="requestAction"
      action-id="SUB0202_010201-01#A001"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="errorHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaHoldingSecurityListAccountSelectionA012"
      action-type="requestAction"
      action-id="SUB0202_010201-01#A012"
      :request-model="a012RequestModel"
      @response-handler="responseHandlerA012A013($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaHoldingSecurityListProductSelectionA013"
      action-type="requestAction"
      action-id="SUB0202_010201-01#A013"
      :request-model="a013RequestModel"
      @response-handler="responseHandlerA012A013($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDepositBalanceDetailInitializeA001"
      action-type="requestAction"
      action-id="SUB0202_010201-04#A001"
      :request-model="a018RequestModel"
      @response-handler="responseHandlerA018($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDomesticMutualFundDocAccumulationSettingsA019"
      action-type="requestAction"
      action-id="SUB0202_0403-02_1#A019"
      :request-model="IfaDomesticMutualFundDocAccumulationSettingsA019RequestModel"
      @response-handler="responseHandlerA019($event)"
      @response-error-handler="responseErrorHandlerA019($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDistributionReceiveMethodChangeInputA001"
      action-type="requestAction"
      action-id="SUB0202_010201-02_1#A001"
      :request-model="IfaDistributionReceiveMethodChangeInputA001RequestModel"
      @response-handler="responseHandlerDistributionReceiceMethodChangeInputA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaDomesticMutualFundOrderInputInitializeHoldingSecurityA001"
      action-id="SUB0202_0401-02_1#A001"
      action-type="requestAction"
      :request-model="orderA001RequestModel"
      @response-handler="responseHandlerInputInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaHoldingSecurityListDomesticStockTradingA003"
      action-type="requestAction"
      action-id="SUB0202_010201-01#A003"
      :request-model="a003RequestModel"
      @response-handler="responseHandlerDomesticStockTradingA003($event)"
      @response-error-handler="errorHandlerDomesticStockTradingA003($event)"
    ></ifa-requester>

    <!-- 積立設定入力 init -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingInputRedirect"
      action-id="SUB0202_0403-02_1#A001"
      action-type="requestAction"
      :disable-notification="disableNotification"
      :request-model="settingInputRedirectRequestModel"
      @response-handler="settingInputRedirectHandler($event)"
      @response-error-handler="settingInputRedirectErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaDistributionReceiveMethodChangeComplete from '@/views/brokerageMenu/customerMenu/accountManage/balanceInfo/IfaDistributionReceiveMethodChangeComplete.vue'
import ScreenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import { getMessage, notifyMessage, notifyWrapper } from '@/utils/errorHandler'
import IfaDistributionReceiveMethodChangeInput from './IfaDistributionReceiveMethodChangeInput.vue'
import IfaSellUnableDetail from './IfaSellUnableDetail.vue'
import IfaDepositBalanceDetail from './IfaDepositBalanceDetail.vue'
import { IfaHoldingSecurityListFormModel } from './js/IfaHoldingSecurityListFormModel'
import { IfaHoldingSecurityListA003RequestModel } from './js/IfaHoldingSecurityListA003RequestModel'
import { IfaHoldingSecurityListA005RequestModel } from './js/IfaHoldingSecurityListA005RequestModel'
import { IfaHoldingSecurityListA008RequestModel } from './js/IfaHoldingSecurityListA008RequestModel'
import { IfaHoldingSecurityListA012RequestModel } from './js/IfaHoldingSecurityListA012RequestModel'
import { IfaHoldingSecurityListA013RequestModel } from './js/IfaHoldingSecurityListA013RequestModel'
import { IfaHoldingSecurityListA018RequestModel } from './js/IfaHoldingSecurityListA018RequestModel'
import { IfaHoldingSecurityListA019RequestModel } from './js/IfaHoldingSecurityListA019RequestModel'
import { IfaDistributionReceiveMethodChangeInputFormModel } from './js/IfaDistributionReceiveMethodChangeInputFormModel'
import { IfaDistributionReceiveMethodChangeInputA001RequestModel } from './js/IfaDistributionReceiveMethodChangeInputA001RequestModel'
import { IfaMutualFundAccumulateSettingInputInitA001RequestModel } from '@/views/brokerageMenu/customerMenu/investmentTrust/mutualFundAccumulate/js/IfaMutualFundAccumulateSettingInputInitA001RequestModel'
import { IfaDomesticMutualFundOrderInputA001RequestModel } from '@/views/brokerageMenu/customerMenu/investmentTrust/domesticMutualFund/js/IfaDomesticMutualFundOrderInputA001RequestModel'

export default {
  components: {
    IfaDistributionReceiveMethodChangeInput,
    IfaDistributionReceiveMethodChangeComplete,
    IfaSellUnableDetail,
    IfaDepositBalanceDetail,
    ScreenTitle,
    IfaRequester
  },
  emits: [
    'show-domestic-stock-order',
    'show-investment-trust-order',
    'show-foreign-spot-order',
    'show-domestic-bond-order',
    'show-foreign-bond-order',
    'show-foreign-currency-mmf-order',
    'initializeError',
    'update-customer-portal'
  ],
  data() {
    return {
      visible: {
        stockGenOldNisa: false, // 株式（総合口座 - 旧NISA預り）
        stockGenSp: false, // 株式（総合口座 - 特定預り）
        stockGenGen: false, // 株式（総合口座 - 一般預り）
        stockGenNisa: false, // 株式（総合口座 - NISA(成長投資枠))
        stockNisaOldNisa: false, // 株式（ジュニアNISA口座 - 旧NISA預り）
        stockNisaSp: false, // 株式（ジュニアNISA口座 - 特定預り）
        stockNisaGen: false, // 株式（ジュニアNISA口座 - 一般預り）
        stockNisaNisa: false, // 株式（ジュニアNISA口座 - NISA預り（継続管理勘定）)
        invGenUniOldNisa: false, // 投資信託（口数/総合口座 - 旧Nisa預り）
        invGenPriOldNisa: false, // 投資信託（金額/総合口座 - 旧Nisa預り）
        invGenUniNisa: false, // 投資信託（口数/総合口座 - NISA(成長投資枠)）
        invGenPriNisa: false, // 投資信託（金額/総合口座 - NISA(成長投資枠)）
        invGenUniSp: false, // 投資信託（口数/総合口座 - 特定預り）
        invGenPriSp: false, // 投資信託（金額/総合口座 - 特定預り）
        invGenUniGen: false, // 投資信託（口数/総合口座 - 一般預り）
        invGenPriGen: false, // 投資信託（金額/総合口座 - 一般預り）
        invGenUniOldSave: false, // 投資信託（口数/総合口座 - 旧つみたてNISA預り）
        invGenPriOldSave: false, // 投資信託（金額/総合口座 - 旧つみたてNISA預り）
        invGenUniSave: false, // 投資信託（口数/総合口座 - NISA預り（つみたて投資枠））
        invGenPriSave: false, // 投資信託（金額/総合口座 - NISA預り（つみたて投資枠））
        invNisaUniOldNisa: false, // 投資信託（口数/ジュニアNISA口座－旧NISA預り）
        invNisaPriOldNisa: false, // 投資信託（金額/ジュニアNISA口座－旧NISA預り）
        invNisaUniNisa: false, // 投資信託（口数/ジュニアNISA口座－NISA預り（継続管理勘定））
        invNisaPriNisa: false, // 投資信託（金額/ジュニアNISA口座－NISA預り（継続管理勘定））
        invNisaUniSp: false, // 投資信託（口数/ジュニアNISA口座－特定預り）
        invNisaPriSp: false, // 投資信託（金額/ジュニアNISA口座－特定預り）
        invNisaUniGen: false, // 投資信託（口数/ジュニアNISA口座－一般預り）
        invNisaPriGen: false, // 投資信託（金額/ジュニアNISA口座－一般預り）
        domBondGenSp: false, // 国内債券（総合口座－特定預り）
        domBondGenGen: false, // 国内債券（総合口座－一般預り）
        domBondNisaSp: false, // 国内債券（ジュニアNISA口座－特定預り）
        domBondNisaGen: false, // 国内債券（ジュニアNISA口座－一般預り）
        foreStockGenSp: false, // 外国株式（総合口座－特定預り）
        foreStockGenGen: false, // 外国株式（総合口座－一般預り）
        foreStockGenOldNisa: false, // 外国株式（総合口座-旧NISA預り）
        foreStockGenNisa: false, // 外国株式（総合口座-NISA(成長投資枠)）
        foreStockNisaSp: false, // 外国株式（ジュニアNISA口座－特定預り）
        foreStockNisaGen: false, // 外国株式（ジュニアNISA口座－一般預り）
        foreStockNisaOldNisa: false, // 外国株式（ジュニアNISA口座－旧NISA預り）
        foreStockNisaNisa: false, // 外国株式（ジュニアNISA口座－NISA預り（継続管理勘定））
        foreBondGenSp: false, // 外国債券（総合口座－特定預り）
        foreBondGenGen: false, // 外国債券（総合口座－一般預り）
        foreBondNisaSp: false, // 外国債券（ジュニアNISA口座－特定預り）
        foreBondNisaGen: false, // 外国債券（ジュニアNISA口座－一般預り）
        fcGenSp: false, // 外貨建MMF (総合口座－特定預り)
        fcGenGen: false, // 外貨建MMF (総合口座－一般預り)
        fcNisaSp: false, // 外貨建MMF (ジュニアNISA口座－特定預り)
        fcNisaGen: false, // 外貨建MMF (ジュニアNISA口座－一般預り)
        stGenSp: false, // ST (総合口座－特定預り)
        stGenGen: false, // ST (総合口座－一般預り)
        stNisaSp: false, // ST (ジュニアNISA口座－特定預り)
        stNisaGen: false, // ST (ジュニアNISA口座－一般預り)
        otherProGenSp: false, // その他 (総合口座－特定預り)
        otherProGenGen: false, // その他 (総合口座－一般預り)
        otherProNisaSp: false, // その他 (ジュニアNISA口座－特定預り)
        otherProNisaGen: false // その他 (ジュニアNISA口座－一般預り)
      },
      form: new IfaHoldingSecurityListFormModel(),
      formDistributionReceiveMethodChangeInput: new IfaDistributionReceiveMethodChangeInputFormModel(),
      a018RequestModel: {},
      a019RequestModel: {},
      accumulateSettingParams: {},
      settingInputInitA001Info: null,
      disableNotification: false,
      existProductList: [],
      securitySelect: '',
      dialogVisible: {
        changeDialogVisible: false,
        completeDialogVisible: false,
        cannotSellDetailVisible: false,
        balanceDetailVisible: false
      },
      dialogData: {},
      existDetail: true,
      existInvestmentTrust: false,
      sellUnableDetailForm: {},
      orderData: {},
      btnFlag: false,
      orderA001RequestObj: {},
      mutualFundParam: {},
      a003RequestModel: {}
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    userAccount() {
      return this.$store.getters.userAccount
    },
    accountTypeList() {
      return this.$_getCodeList('ACCOUNT_TYPE', 1, 1)
    },
    a012RequestModel() {
      return new IfaHoldingSecurityListA012RequestModel(this.form)
    },
    a013RequestModel() {
      return new IfaHoldingSecurityListA013RequestModel(this.form)
    },
    // ジュニアNISA口座を持っているか
    hasJrNisaAccount() {
      return this.customerInfo.jrIsaContractType === '1'
    },
    IfaDistributionReceiveMethodChangeInputA001RequestModel() {
      return new IfaDistributionReceiveMethodChangeInputA001RequestModel(this.formDistributionReceiveMethodChangeInput)
    },
    orderA001RequestModel() {
      return new IfaDomesticMutualFundOrderInputA001RequestModel(this.orderA001RequestObj, false, this.btnFlag)
    },
    IfaDomesticMutualFundDocAccumulationSettingsA019RequestModel() {
      return new IfaHoldingSecurityListA019RequestModel(this.a019RequestModel)
    },
    settingInputRedirectRequestModel() {
      return new IfaMutualFundAccumulateSettingInputInitA001RequestModel(this.settingInputInitA001Info)
    }
  },
  methods: {
    redraw() {
      this.onShow()
      this.$nextTick(() => {
        this.$emit('update-customer-portal')
      })
    },
    onShow() {
      this.settingInputInitA001Info = null
      this.disableNotification = false
      this.form.accountClassification = '2'
      this.securitySelect = '7'
      this.initialize()
      document.getElementById('IfaHoldingSecurityListInitializeA001').click()
      this.expandAll()

      const params = this.$store.getters.pageInfo.params
      if (params && params.source === 'SUB0202_0403' && params?.errorMsg) {
        notifyMessage(params?.errorLevel, params?.errorMsg, this.form.title.name)
      }
    },
    initialize() {
      this.form.domesticStockList = []
      this.form.investmentTrustList = []
      this.form.domesticBondsList = []
      this.form.foreignBondsList = []
      this.form.foreignStocksList = []
      this.form.foreignCurrencyMmfList = []
      this.form.otherSecurityList = []
    },
    responseHandlerInitializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.setExistProduct()
      this.setExistDetail(response.dataList[0])
      this.$nextTick(() => {
        if (this.$refs['ifaLinkTotalReturn']) { this.$refs['ifaLinkTotalReturn'].trigger() }
        if (this.$refs['ifaLinkMyAssset']) { this.$refs['ifaLinkMyAssset'].forEach(self => { self.trigger() }) }
      })
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    responseHandlerDomesticStockTradingA003() {
      this.$_startShowMenu('SUB0202_0208', this.a003RequestModel)
    },
    errorHandlerDomesticStockTradingA003(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    async sellUnableDetailA002(response) {
      this.sellUnableDetailForm = response.dataList[0]
      this.dialogVisible.cannotSellDetailVisible = true
    },
    // 国内株式の注文入力画面に遷移する
    domesticStockTradingA003(tradeType, asset) {
      this.a003RequestModel = new IfaHoldingSecurityListA003RequestModel({
        brandCode: asset.brandCode,
        tradeKbn: tradeType,
        depositType: asset.depositBalanceListSpecificDepositType,
        holdingStock: asset.holdingStock,
        sellingVolume: asset.sellingVolume
      })
      document.querySelector('#ifaHoldingSecurityListDomesticStockTradingA003').click()
    },
    responseHandlerInputInitializeA001(response) {
      this.orderData = Object.assign(this.orderData, response.dataList[0])
      this.$_startShowMenu('SUB0202_0401', this.mutualFundParam)
    },
    // 国内投信の注文入力画面に遷移する(国内投信購入可能一覧画面経由)
    mutualFundTradingA005(tradeType, asset) {
      if (tradeType === '0') {
        this.btnFlag = true
      } else {
        this.btnFlag = false
      }
      this.mutualFundParam = {
        source: 'IfaHoldingSecurityList',
        data: new IfaHoldingSecurityListA005RequestModel({
          times: asset.times,
          issue1: asset.issue1,
          issue2: asset.issue2,
          tradeKbn: tradeType,
          depositType: asset.depositBalanceListSpecificDepositType,
          dispatchId: ' '
        }),
        orderData: this.orderData
      }
      this.orderA001RequestObj = this.mutualFundParam.data
      this.$nextTick(() => {
        document.getElementById('ifaDomesticMutualFundOrderInputInitializeHoldingSecurityA001').click()
      })
    },
    // 分配金受取方法変更入力の初期化を実行
    async distributionReceiveMethodChangeA007(asset) {
      // フォームを更新
      this.formDistributionReceiveMethodChangeInput = {
        ...this.formDistributionReceiveMethodChangeInput,
        times: asset.times,
        issue1: asset.issue1,
        issue2: asset.issue2,
        depositType: asset.depositBalanceListSpecificDepositType
      }

      await this.$nextTick()

      // A001 初期化を実行
      document.getElementById('IfaDistributionReceiveMethodChangeInputA001').click()
    },
    // 分配金受取方法変更入力画面に遷移
    responseHandlerDistributionReceiceMethodChangeInputA001(event) {
      this.dialogVisible.changeDialogVisible = true
      this.$nextTick(() => {
        this.$refs.ifaDistributionReceiveMethodChangeInput.onShow(event)
      })
    },
    // 外国株式の注文入力画面に遷移する
    foreignStockTradingA008(tradeType, asset) {
      if (tradeType === '0' && asset.brandListedStatus !== 'TRADABLE' && asset.brandListedStatus !== 'SELL_STOP') {
        notifyWrapper({
          title: this.form.title.name,
          message: getMessage('errors.frs.listedSecuritiesStatus.buyStop'),
          type: 'error'
        })
        return
      } else if (tradeType === '1' && asset.brandListedStatus !== 'TRADABLE' && asset.brandListedStatus !== 'BUY_STOP') {
        notifyWrapper({
          title: this.form.title.name,
          message: getMessage('errors.frs.listedSecuritiesStatus.sellStop'),
          type: 'error'
        })
        return
      } else {
        const a008RequestModel = new IfaHoldingSecurityListA008RequestModel({
          brandCode: asset.brandCode,
          tradeKbn: tradeType,
          depositType: asset.depositType,
          countryCode: asset.countryCode
        })
        this.$_startShowMenu('SUB0202_0301', a008RequestModel)
      }
    },
    accountSelectionA012() {
      document.getElementById('IfaHoldingSecurityListAccountSelectionA012').click()
    },
    productSelectionA013(key) {
      this.form.securitySelect = this.existProductList.find(el => el.key === key).value
      document.getElementById('IfaHoldingSecurityListProductSelectionA013').click()
    },
    responseHandlerA012A013(response) {
      this.initialize()
      Object.assign(this.form, response.dataList[0])
      this.setExistDetail(response.dataList[0])
    },
    // 全てのテーブルの折りたたみと展開をトグルする
    wholeDetailToggleSwitchA014() {
      this.isExpand() ? this.collapseAll() : this.expandAll()
    },
    // 個別テーブル折りたたみ・展開
    detailToggleSwitchA016(product, accountType, depositType, unitPrice) {
      this.changeVisible(product, accountType, depositType, unitPrice)
    },
    // 預り残高明細を表示する
    depositBalanceDetailA018(product, asset, accountType) {
      let setDetailParams = {}
      if (product === '国内株式' || product === '投資信託口数' || product === '投資信託金額' || product === '国内債券' || (product === '外国債券' && asset.depositType === null)) {
        setDetailParams = {
          productName: product,
          brandCode: asset.brandCode,
          securityType: asset.securityType,
          kokunaiGaiKbn: asset.kokunaiGaiKbn,
          securityClass1: asset.securityClass1,
          securityClass2: asset.securityClass2,
          companyCode: asset.companyCode,
          rightType: asset.rightType,
          newOldType: asset.newOldType,
          times: asset.times,
          issue1: asset.issue1,
          issue2: asset.issue2,
          listedCountryCode: asset.listedCountryCode,
          depositBalanceListSpecificDepositType: asset.depositBalanceListSpecificDepositType,
          depositBalanceAccountTypeName: this.hasJrNisaAccount ? accountType === '2' ? '1' : ' ' : ' '
        }
        switch (product) {
          case '国内株式':
          case '国内債券':
          case '外国債券':
            setDetailParams.brandName = asset.brandName
            break
          case '投資信託口数':
          case '投資信託金額':
            setDetailParams.brandName = asset.fundName
            break
          default:
            break
        }
      // 外国債券（外貨建）
      } else if (product === '外国債券' && asset.depositType !== null) {
        setDetailParams = {
          productName: '外国債券（外貨建）',
          brandCode: asset.brandCode,
          brandName: asset.brandName,
          securityClass: asset.securityClass,
          countryCode: asset.countryCode,
          currencyCode: asset.currencyCode,
          depositType: asset.depositType
        }
      } else {
        setDetailParams = {
          productName: product,
          brandCode: asset.brandCode,
          securityClass: asset.securityClass,
          countryCode: asset.countryCode,
          currencyCode: asset.currencyCode,
          depositType: asset.depositType
        }
        switch (product) {
          case '外国株式':
            setDetailParams.brandName = asset.brandName
            break
          case '外貨建MMF':
            setDetailParams.brandName = asset.fundName
            break
          default:
            break
        }
      }
      this.a018RequestModel = new IfaHoldingSecurityListA018RequestModel(setDetailParams)
      document.getElementById('IfaDepositBalanceDetailInitializeA001').click()
    },
    responseHandlerA018(response) {
      this.$refs['IfaDepositBalanceDetail'].onShow(response.dataList[0])
      this.dialogVisible.balanceDetailVisible = true
    },

    // 投信積立設定入力に遷移する
    handleRedirectAccumulate(asset) {
      this.a019RequestModel = {
        fundCode: asset.kyoukaiCd
      }
      this.accumulateSettingParams = {
        fundCodeTimes: asset.times,
        fundCodeIssues: `${asset.issue1}${asset.issue2}`,
        kyoukaiCd: asset.kyoukaiCd
      }
      this.$nextTick(() => {
        document.getElementById('IfaDomesticMutualFundDocAccumulationSettingsA019').click()
      })
    },

    responseHandlerA019(_res) {
      const { kyoukaiCd, fundCodeIssues, fundCodeTimes } = this.accumulateSettingParams

      this.settingInputInitA001Info = {
        fundCode: kyoukaiCd,
        mfgo: fundCodeIssues,
        mfkaisu: fundCodeTimes,
        source: 'SUB0202_010201',
        step: 'SUB0202_0403'
      }

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingInputRedirect').click()
      })
    },

    responseErrorHandlerA019(error) {
      this.$_logError(error)
    },

    settingInputRedirectHandler(res) {
      this.$_logDebug(res)

      const { dataList, ...rest } = res

      // warning msg was not display in the current page
      if (rest?.errorLevel === 2) {
        this.disableNotification = true
      }

      this.$_startShowMenu('SUB0202_0403', {
        source: 'SUB0202_010201',
        data: {
          data: dataList?.[0] ?? {},
          ...rest,
          ...this.accumulateSettingParams
        }
      })
    },

    settingInputRedirectErrorHandler(error) {
      this.$_logError(error)
    },

    setExistProduct() {
      this.existProductList = []
      if (this.form.domesticStockList.some(list => {
        return list.numberOfDepositedIssues > 0
      })) {
        this.existProductList.push({ key: '1', value: '国内株式' })
      }
      if (this.form.investmentTrustList.some(list => {
        return list.numberOfDepositedIssues > 0
      })) {
        this.existProductList.push({ key: '2', value: '投資信託' })
      }
      if (this.form.domesticBondsList.some(list => {
        return list.numberOfDepositedIssues > 0
      })) {
        this.existProductList.push({ key: '3', value: '国内債券' })
      }
      if (this.form.foreignBondsList.some(list => {
        return list.numberOfDepositedIssues > 0
      })) {
        this.existProductList.push({ key: '4', value: '外国債券' })
      }
      if (this.form.foreignStocksList.some(list => {
        return list.numberOfDepositedIssues > 0
      })) {
        this.existProductList.push({ key: '5', value: '外国株式' })
      }
      if (this.form.foreignCurrencyMmfList.some(list => {
        return list.numberOfDepositedIssues > 0
      })) {
        this.existProductList.push({ key: '6', value: '外貨建MMF' })
      }
      if (this.form.securityTokenList.some(list => {
        return list.numberOfDepositedIssues > 0
      })) {
        this.existProductList.push({ key: '8', value: 'ST（セキュリティ・トークン）' })
      }
      this.existProductList.push({ key: '7', value: '全て' })
    },
    // 評価損益の表示色を返す
    getProfitColor(value) {
      return value < 0 ? 'font-color__minus bold' : value > 0 ? 'font-color__plus bold' : ''
    },
    getTagType(depositType) {
      if (depositType === '1') {
        return 'warning'
      } else if (depositType === '2') {
        return 'danger'
      } else if (depositType === '3') {
        return 'info'
      } else if (depositType === '4') {
        return 'purple'
      } else if (depositType === '5') {
        return 'yellow'
      } else if (depositType === '6') {
        return 'red'
      } else if (depositType === '7') {
        return ''
      }
    },
    // 明細が存在するかを返す
    setExistDetail(dataList) {
      const existDomesticStock = dataList.domesticStockList.some(list => {
        return list.numberOfDepositedIssues > 0
      })
      const existInvestmentTrust = dataList.investmentTrustList.some(list => {
        return list.numberOfDepositedIssues > 0
      })
      const existDomesticBonds = dataList.domesticBondsList.some(list => {
        return list.numberOfDepositedIssues > 0
      })
      const existForeignBonds = dataList.foreignBondsList.some(list => {
        return list.numberOfDepositedIssues > 0
      })
      const existForeignStocks = dataList.foreignStocksList.some(list => {
        return list.numberOfDepositedIssues > 0
      })
      const existForeignCurrencyMmf = dataList.foreignCurrencyMmfList.some(list => {
        return list.numberOfDepositedIssues > 0
      })
      const existSecurityTokenList = dataList.securityTokenList.some(list => {
        return list.numberOfDepositedIssues > 0
      })
      const existOtherSecurity = dataList.otherSecurityList.some(list => {
        return list.numberOfDepositedIssues > 0
      })
      if (existInvestmentTrust) {
        this.existInvestmentTrust = true
      } else {
        this.existInvestmentTrust = false
      }
      if (existDomesticStock ||
          existInvestmentTrust ||
          existDomesticBonds ||
          existForeignBonds ||
          existForeignStocks ||
          existForeignCurrencyMmf ||
          existSecurityTokenList ||
          existOtherSecurity) {
        this.existDetail = true
      } else {
        this.existDetail = false
      }
    },
    // テーブルが展開されているか状態を返す
    isExpand() {
      return this.form.accountClassification !== '1' &&
            (this.visible.stockGenOldNisa || this.visible.stockGenSp || this.visible.stockGenGen || this.visible.stockGenNisa ||
             this.visible.invGenUniOldNisa || this.visible.invGenUniNisa || this.visible.invGenUniSp || this.visible.invGenUniGen ||
             this.visible.invGenPriOldNisa || this.visible.invGenPriNisa || this.visible.invGenPriSp || this.visible.invGenPriGen ||
             this.visible.invGenPriOldSave || this.visible.invGenUniOldSave || this.visible.invGenPriSave || this.visible.invGenUniSave ||
             this.visible.foreBondGenSp || this.visible.foreBondGenGen ||
             this.visible.otherProGenSp || this.visible.otherProGenGen ||
             this.visible.foreStockGenOldNisa || this.visible.foreStockGenNisa || this.visible.foreStockGenSp || this.visible.foreStockGenGen ||
             this.visible.domBondGenSp || this.visible.domBondGenGen || this.visible.fcGenGen || this.visible.fcGenSp ||
             this.visible.stGenSp || this.visible.stGenGen) ||
             this.form.accountClassification !== '0' &&
             (this.visible.stockNisaOldNisa || this.visible.stockNisaSp || this.visible.stockNisaGen || this.visible.stockNisaNisa ||
             this.visible.invNisaUniOldNisa || this.visible.invNisaUniNisa || this.visible.invNisaUniSp || this.visible.invNisaUniGen ||
             this.visible.invNisaPriOldNisa || this.visible.invNisaPriNisa || this.visible.invNisaPriSp || this.visible.invNisaPriGen ||
             this.visible.fcNisaGen || this.visible.fcNisaSp ||
             this.visible.foreBondNisaSp || this.visible.foreBondNisaGen ||
             this.visible.domBondNisaSp || this.visible.domBondNisaGen ||
             this.visible.otherProNisaSp || this.visible.otherProNisaGen ||
             this.visible.foreStockNisaSp || this.visible.foreStockNisaGen || this.visible.foreStockNisaOldNisa || this.visible.foreStockNisaNisa ||
             this.visible.stNisaSp || this.visible.stNisaGen)
    },
    // 全てのテーブルを折りたたむ
    collapseAll() {
      if (this.form.accountClassification !== '1') {
        this.visible.stockGenOldNisa = false
        this.visible.stockGenSp = false
        this.visible.stockGenGen = false
        this.visible.stockGenNisa = false
        this.visible.invGenUniOldNisa = false
        this.visible.invGenUniNisa = false
        this.visible.invGenUniSp = false
        this.visible.invGenUniGen = false
        this.visible.invGenPriOldNisa = false
        this.visible.invGenPriNisa = false
        this.visible.invGenPriSp = false
        this.visible.invGenPriGen = false
        this.visible.invGenPriOldSave = false
        this.visible.invGenUniOldSave = false
        this.visible.invGenUniSave = false
        this.visible.invGenPriSave = false
        this.visible.domBondGenSp = false
        this.visible.domBondGenGen = false
        this.visible.foreStockGenOldNisa = false
        this.visible.foreStockGenNisa = false
        this.visible.foreStockGenSp = false
        this.visible.foreStockGenGen = false
        this.visible.foreBondGenSp = false
        this.visible.foreBondGenGen = false
        this.visible.fcGenGen = false
        this.visible.fcGenSp = false
        this.visible.otherProGenSp = false
        this.visible.otherProGenGen = false
        this.visible.stGenGen = false
        this.visible.stGenSp = false
      }
      if (this.form.accountClassification !== '0') {
        this.visible.stockNisaOldNisa = false
        this.visible.stockNisaSp = false
        this.visible.stockNisaGen = false
        this.visible.stockNisaNisa = false
        this.visible.invNisaUniOldNisa = false
        this.visible.invNisaUniNisa = false
        this.visible.invNisaUniSp = false
        this.visible.invNisaUniGen = false
        this.visible.invNisaPriOldNisa = false
        this.visible.invNisaPriNisa = false
        this.visible.invNisaPriSp = false
        this.visible.invNisaPriGen = false
        this.visible.fcNisaGen = false
        this.visible.fcNisaSp = false
        this.visible.foreStockNisaSp = false
        this.visible.foreStockNisaGen = false
        this.visible.foreStockNisaOldNisa = false
        this.visible.foreStockNisaNisa = false
        this.visible.foreBondNisaSp = false
        this.visible.foreBondNisaGen = false
        this.visible.domBondNisaSp = false
        this.visible.domBondNisaGen = false
        this.visible.otherProNisaSp = false
        this.visible.otherProNisaGen = false
        this.visible.stNisaGen = false
        this.visible.stNisaSp = false
      }
    },
    // 全てのテーブルを展開する
    expandAll() {
      if (this.form.accountClassification !== '1') {
        this.visible.stockGenOldNisa = true
        this.visible.stockGenSp = true
        this.visible.stockGenGen = true
        this.visible.stockGenNisa = true
        this.visible.invGenUniOldNisa = true
        this.visible.invGenPriOldNisa = true
        this.visible.invGenUniNisa = true
        this.visible.invGenPriNisa = true
        this.visible.invGenUniSp = true
        this.visible.invGenPriSp = true
        this.visible.invGenUniGen = true
        this.visible.invGenPriGen = true
        this.visible.invGenUniOldSave = true
        this.visible.invGenPriOldSave = true
        this.visible.invGenUniSave = true
        this.visible.invGenPriSave = true
        this.visible.domBondGenSp = true
        this.visible.domBondGenGen = true
        this.visible.foreStockGenOldNisa = true
        this.visible.foreStockGenNisa = true
        this.visible.foreStockGenSp = true
        this.visible.foreStockGenGen = true
        this.visible.foreBondGenSp = true
        this.visible.foreBondGenGen = true
        this.visible.fcGenGen = true
        this.visible.fcGenSp = true
        this.visible.otherProGenSp = true
        this.visible.otherProGenGen = true
        this.visible.stGenGen = true
        this.visible.stGenSp = true
      }
      if (this.form.accountClassification !== '0') {
        this.visible.stockNisaOldNisa = true
        this.visible.stockNisaSp = true
        this.visible.stockNisaGen = true
        this.visible.stockNisaNisa = true
        this.visible.invNisaUniOldNisa = true
        this.visible.invNisaPriOldNisa = true
        this.visible.invNisaUniNisa = true
        this.visible.invNisaPriNisa = true
        this.visible.invNisaUniSp = true
        this.visible.invNisaPriSp = true
        this.visible.invNisaUniGen = true
        this.visible.invNisaPriGen = true
        this.visible.fcNisaGen = true
        this.visible.fcNisaSp = true
        this.visible.foreStockNisaSp = true
        this.visible.foreStockNisaGen = true
        this.visible.foreStockNisaOldNisa = true
        this.visible.foreStockNisaNisa = true
        this.visible.foreBondNisaSp = true
        this.visible.foreBondNisaGen = true
        this.visible.domBondNisaSp = true
        this.visible.domBondNisaGen = true
        this.visible.otherProNisaSp = true
        this.visible.otherProNisaGen = true
        this.visible.stNisaGen = true
        this.visible.stNisaSp = true
      }
    },
    changeVisible(product, accountType, depositType, unitPrice) {
      switch (product) {
        case 'stock':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  this.visible.stockGenSp = !this.visible.stockGenSp
                  break
                case '3':
                  this.visible.stockGenGen = !this.visible.stockGenGen
                  break
                case '5':
                  this.visible.stockGenNisa = !this.visible.stockGenNisa
                  break
                case '1':
                  this.visible.stockGenOldNisa = !this.visible.stockGenOldNisa
                  break
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  this.visible.stockNisaSp = !this.visible.stockNisaSp
                  break
                case '3':
                  this.visible.stockNisaGen = !this.visible.stockNisaGen
                  break
                case '7':
                  this.visible.stockNisaNisa = !this.visible.stockNisaNisa
                  break
                case '1':
                  this.visible.stockNisaOldNisa = !this.visible.stockNisaOldNisa
                  break
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'trust':
          switch (unitPrice) {
            case '1':
              switch (accountType) {
                case '1':
                case '3':
                  switch (depositType) {
                    case '2':
                      this.visible.invGenUniSp = !this.visible.invGenUniSp
                      break
                    case '3':
                      this.visible.invGenUniGen = !this.visible.invGenUniGen
                      break
                    case '5':
                      this.visible.invGenUniNisa = !this.visible.invGenUniNisa
                      break
                    case '6':
                      this.visible.invGenUniSave = !this.visible.invGenUniSave
                      break
                    case '1':
                      this.visible.invGenUniOldNisa = !this.visible.invGenUniOldNisa
                      break
                    case '4':
                      this.visible.invGenUniOldSave = !this.visible.invGenUniOldSave
                      break
                    default:
                      break
                  }
                  break
                case '2':
                  switch (depositType) {
                    case '2':
                      this.visible.invNisaUniSp = !this.visible.invNisaUniSp
                      break
                    case '3':
                      this.visible.invNisaUniGen = !this.visible.invNisaUniGen
                      break
                    case '7':
                      this.visible.invNisaUniNisa = !this.visible.invNisaUniNisa
                      break
                    case '1':
                      this.visible.invNisaUniOldNisa = !this.visible.invNisaUniOldNisa
                      break
                    default:
                      break
                  }
                  break
                default:
                  break
              }
              break
            case '2':
              switch (accountType) {
                case '1':
                case '3':
                  switch (depositType) {
                    case '2':
                      this.visible.invGenPriSp = !this.visible.invGenPriSp
                      break
                    case '3':
                      this.visible.invGenPriGen = !this.visible.invGenPriGen
                      break
                    case '5':
                      this.visible.invGenPriNisa = !this.visible.invGenPriNisa
                      break
                    case '6':
                      this.visible.invGenPriSave = !this.visible.invGenPriSave
                      break
                    case '1':
                      this.visible.invGenPriOldNisa = !this.visible.invGenPriOldNisa
                      break
                    case '4':
                      this.visible.invGenPriOldSave = !this.visible.invGenPriOldSave
                      break
                    default:
                      break
                  }
                  break
                case '2':
                  switch (depositType) {
                    case '2':
                      this.visible.invNisaPriSp = !this.visible.invNisaPriSp
                      break
                    case '3':
                      this.visible.invNisaPriGen = !this.visible.invNisaPriGen
                      break
                    case '7':
                      this.visible.invNisaPriNisa = !this.visible.invNisaPriNisa
                      break
                    case '1':
                      this.visible.invNisaPriOldNisa = !this.visible.invNisaPriOldNisa
                      break
                    default:
                      break
                  }
                  break
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'bond':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  this.visible.domBondGenSp = !this.visible.domBondGenSp
                  break
                case '3':
                  this.visible.domBondGenGen = !this.visible.domBondGenGen
                  break
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  this.visible.domBondNisaSp = !this.visible.domBondNisaSp
                  break
                case '3':
                  this.visible.domBondNisaGen = !this.visible.domBondNisaGen
                  break
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'foreBond':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  this.visible.foreBondGenSp = !this.visible.foreBondGenSp
                  break
                case '3':
                  this.visible.foreBondGenGen = !this.visible.foreBondGenGen
                  break
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  this.visible.foreBondNisaSp = !this.visible.foreBondNisaSp
                  break
                case '3':
                  this.visible.foreBondNisaGen = !this.visible.foreBondNisaGen
                  break
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'foreStock':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  this.visible.foreStockGenSp = !this.visible.foreStockGenSp
                  break
                case '3':
                  this.visible.foreStockGenGen = !this.visible.foreStockGenGen
                  break
                case '5':
                  this.visible.foreStockGenNisa = !this.visible.foreStockGenNisa
                  break
                case '1':
                  this.visible.foreStockGenOldNisa = !this.visible.foreStockGenOldNisa
                  break
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  this.visible.foreStockNisaSp = !this.visible.foreStockNisaSp
                  break
                case '3':
                  this.visible.foreStockNisaGen = !this.visible.foreStockNisaGen
                  break
                case '7':
                  this.visible.foreStockNisaNisa = !this.visible.foreStockNisaNisa
                  break
                case '1':
                  this.visible.foreStockNisaOldNisa = !this.visible.foreStockNisaOldNisa
                  break
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'mmf':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  this.visible.fcGenSp = !this.visible.fcGenSp
                  break
                case '3':
                  this.visible.fcGenGen = !this.visible.fcGenGen
                  break
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  this.visible.fcNisaSp = !this.visible.fcNisaSp
                  break
                case '3':
                  this.visible.fcNisaGen = !this.visible.fcNisaGen
                  break
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'st':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  this.visible.stGenSp = !this.visible.stGenSp
                  break
                case '3':
                  this.visible.stGenGen = !this.visible.stGenGen
                  break
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  this.visible.stNisaSp = !this.visible.stNisaSp
                  break
                case '3':
                  this.visible.stNisaGen = !this.visible.stNisaGen
                  break
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'other':
          switch (accountType) {
            case '1':
              switch (depositType) {
                case '2':
                  this.visible.otherProGenSp = !this.visible.otherProGenSp
                  break
                case '3':
                  this.visible.otherProGenGen = !this.visible.otherProGenGen
                  break
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  this.visible.otherProNisaSp = !this.visible.otherProNisaSp
                  break
                case '3':
                  this.visible.otherProNisaGen = !this.visible.otherProNisaGen
                  break
                default:
                  break
              }
              break
            default:
              break
          }
          break
        default:
          break
      }
    },
    checkVisible(product, accountType, depositType, unitPrice) {
      switch (product) {
        case 'stock':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  return this.visible.stockGenSp
                case '3':
                  return this.visible.stockGenGen
                case '5':
                  return this.visible.stockGenNisa
                case '1':
                  return this.visible.stockGenOldNisa
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  return this.visible.stockNisaSp
                case '3':
                  return this.visible.stockNisaGen
                case '7':
                  return this.visible.stockNisaNisa
                case '1':
                  return this.visible.stockNisaOldNisa
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'trust':
          switch (unitPrice) {
            case '1':
              switch (accountType) {
                case '1':
                case '3':
                  switch (depositType) {
                    case '2':
                      return this.visible.invGenUniSp
                    case '3':
                      return this.visible.invGenUniGen
                    case '5':
                      return this.visible.invGenUniNisa
                    case '6':
                      return this.visible.invGenUniSave
                    case '1':
                      return this.visible.invGenUniOldNisa
                    case '4':
                      return this.visible.invGenUniOldSave
                    default:
                      break
                  }
                  break
                case '2':
                  switch (depositType) {
                    case '2':
                      return this.visible.invNisaUniSp
                    case '3':
                      return this.visible.invNisaUniGen
                    case '7':
                      return this.visible.invNisaUniNisa
                    case '1':
                      return this.visible.invNisaUniOldNisa
                    default:
                      break
                  }
                  break
                default:
                  break
              }
              break
            case '2':
              switch (accountType) {
                case '1':
                case '3':
                  switch (depositType) {
                    case '2':
                      return this.visible.invGenPriSp
                    case '3':
                      return this.visible.invGenPriGen
                    case '5':
                      return this.visible.invGenPriNisa
                    case '6':
                      return this.visible.invGenPriSave
                    case '1':
                      return this.visible.invGenPriOldNisa
                    case '4':
                      return this.visible.invGenPriOldSave
                    default:
                      break
                  }
                  break
                case '2':
                  switch (depositType) {
                    case '2':
                      return this.visible.invNisaPriSp
                    case '3':
                      return this.visible.invNisaPriGen
                    case '7':
                      return this.visible.invNisaPriNisa
                    case '1':
                      return this.visible.invNisaPriOldNisa
                    default:
                      break
                  }
                  break
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'bond':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  return this.visible.domBondGenSp
                case '3':
                  return this.visible.domBondGenGen
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  return this.visible.domBondNisaSp
                case '3':
                  return this.visible.domBondNisaGen
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'foreBond':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  return this.visible.foreBondGenSp
                case '3':
                  return this.visible.foreBondGenGen
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  return this.visible.foreBondNisaSp
                case '3':
                  return this.visible.foreBondNisaGen
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'foreStock':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  return this.visible.foreStockGenSp
                case '3':
                  return this.visible.foreStockGenGen
                case '5':
                  return this.visible.foreStockGenNisa
                case '1':
                  return this.visible.foreStockGenOldNisa
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  return this.visible.foreStockNisaSp
                case '3':
                  return this.visible.foreStockNisaGen
                case '7':
                  return this.visible.foreStockNisaNisa
                case '1':
                  return this.visible.foreStockNisaOldNisa
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'mmf':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  return this.visible.fcGenSp
                case '3':
                  return this.visible.fcGenGen
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  return this.visible.fcNisaSp
                case '3':
                  return this.visible.fcNisaGen
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'st':
          switch (accountType) {
            case '1':
            case '3':
              switch (depositType) {
                case '2':
                  return this.visible.stGenSp
                case '3':
                  return this.visible.stGenGen
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  return this.visible.stNisaSp
                case '3':
                  return this.visible.stNisaGen
                default:
                  break
              }
              break
            default:
              break
          }
          break
        case 'other':
          switch (accountType) {
            case '1':
              switch (depositType) {
                case '2':
                  return this.visible.otherProGenSp
                case '3':
                  return this.visible.otherProGenGen
                default:
                  break
              }
              break
            case '2':
              switch (depositType) {
                case '2':
                  return this.visible.otherProNisaSp
                case '3':
                  return this.visible.otherProNisaGen
                default:
                  break
              }
              break
            default:
              break
          }
          break
        default:
          break
      }
    },
    // 以下、保有商品一覧画面外ロジック
    // 分配金受取方法の変更を適用する
    async handleChangeComplete(newValue) {
      this.dialogVisible.changeDialogVisible = false
      this.dialogData = newValue

      await this.$nextTick()
      this.$refs.ifaDistributionReceiveMethodChangeComplete.onShow()
      this.dialogVisible.completeDialogVisible = true
    },
    // ダイアログを閉じる
    handleClose() {
      this.dialogVisible.changeDialogVisible = false
      this.dialogVisible.completeDialogVisible = false
      this.dialogVisible.cannotSellDetailVisible = false
      this.dialogVisible.balanceDetailVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/table.scss";

.el-row .el-row {
  width: 100%;
}
._bold_black_s {
  font-size: 14px;
  font-weight: 700;
  color: #606266;
}
.badge-item {
  padding-top: 0.4rem;
  padding-left: 0.4rem;
}
.clickable {
  display: inline-block;
  cursor: pointer;
  &:hover {
    background: rgba(0, 0, 0, .066)
  }
}
.__link_text {
  text-decoration:underline;
  text-underline-offset:0.1em;
  color: blue;
}
.__right {
 text-align: right;
}
:deep(.el-tag--purple) {
  background-color: #fcf0fe;
  border-color: #f8e2fd;
  color: #ce6cf5;
}
:deep(.el-tag--yellow) {
  background-color: #ffffcc;
  border-color: #d9ca96;
  color: #d78950;
}
:deep(.el-tag--red) {
  background-color: #fedef0;
  border-color: #fec0d7;
  color: #ff4650;
}
:deep(.el-tag--dark.el-tag--warning) {
  background-color: #f0f2fe;
  border-color: #e2e6fd;
  color: #6c81f5;
}
:deep(.el-button.is-disabled) {
  color: #C0C4CC;
  cursor: not-allowed;
  background-image: none;
  background-color: #FFF;
  border-color: #EBEEF5;
}
.caption_card {
  padding: 5px 15px 20px 15px;
  overflow-x: scroll;
}
::v-deep .accumulation-class .el-button {
  background-color: #70ad47 !important;
  border-color: #70ad47 !important;
}
</style>
