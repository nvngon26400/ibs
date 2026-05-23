<template>
  <div>
    <!--国内株式-->
    <el-row v-if="portfolioData.holdingSecurityDomesticStock && portfolioData.holdingSecurityDomesticStock.length > 0">
      <table
        id="holdingSecurityDomesticStock"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr class="table-normal-font">
            <th
              class="_table__header"
              colspan="8"
            >国内株式
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px'}"
            >
              銘柄コード</th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '19% !important' : '280px'}"
            >
              銘柄名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8.5% !important' : '120px'}"
            >
              預り区分
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13% !important' : '165px'}"
            >
              保有株数
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13% !important' : '165px'}"
            >
              平均取得価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13% !important' : '165px'}"
            >
              前日終値<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '14% !important' : '165px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '175px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityDomesticStock"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td
              class="_table__data"
            >{{ $_out(item.brandName) }}</td>
            <td
              class="_table__data"
              v-html="addLineBreakHintsForDepositType($_out($_getCodeValue('ASSET_DEPOSIT_TYPE', 1, item.depositType)))"
            ></td>
            <td
              class="_table__data _right"
            >{{ $_out($_withCommaInteger(item.contractStandardDeposit, 7)) }}</td>
            <td
              class="_table__data _right"
            >{{ $_out($_withCommaNoneZeroPadding(item.openPrice, 7)) }} </td>
            <td
              class="_table__data _right"
            >{{ $_out($_withCommaNoneZeroPadding(item.price, 4)) }}</td>
            <td
              class="_table__data _right"
            >{{ $_out($_withCommaNoneZeroPadding(item.valuation)) }}</td>
            <td
              class="_table__data _right"
            >
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaNoneZeroPadding(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaNoneZeroPadding(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="7"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.domesticStockProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaNoneZeroPadding(portfolioData.domesticStockProfitAndLossTotal)) !== '-' && Number(portfolioData.domesticStockProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaNoneZeroPadding(portfolioData.domesticStockProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--国内債券-->
    <el-row
      v-if="portfolioData.holdingSecurityListDomesticClaim && portfolioData.holdingSecurityListDomesticClaim.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListDomesticClaim"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="12"
            >国内債券
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7.5% !important' : '110px'}"
            >
              銘柄コード</th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '16% !important' : '215px'}"
            >
              銘柄名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8% !important' : '85px'}"
            >
              預り区分</th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '6% !important' : '85px'}"
            >
              利率（％）
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '6% !important' : '103px'}"
            >
              参考為替
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '105px'}"
            >
              償還日
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '14% !important' : '195px'}"
            >
              利払日
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '108px'}"
            >
              取得単価
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '6% !important' : '108px'}"
            >
              約定為替
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8% !important' : '115px'}"
            >
              保有額面
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8% !important' : '115px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '6.5% !important' : '85px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListDomesticClaim"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td
              class="_table__data"
              v-html="addLineBreakHintsForDepositType($_out($_getCodeValue('ASSET_DEPOSIT_TYPE', 1, item.depositType)))"
            ></td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.compoundInterest,8)) }}</td>
            <td class="_table__data _right">{{ item.fxRate ? $_withCommaZeroPadding(item.fxRate) + '円/' + item.currency : '-' }}</td>
            <td class="_table__data">{{ $_out($_getFormattedDateValue(item.redemptionDate, 'date8')) }}</td>
            <td class="_table__data">{{ $_out(breakAfterComma(item.interestPaymentDate)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.openPrice, 7)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.acquireRate, 2)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="11"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.domesticClaimProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.domesticClaimProfitAndLossTotal)) !== '-' && Number(portfolioData.domesticClaimProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.domesticClaimProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--投資信託-->
    <el-row
      v-if="portfolioData.holdingSecurityListMutualFund && portfolioData.holdingSecurityListMutualFund.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListMutualFund"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="8"
            >投資信託
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px'}"
            >
              銘柄コード
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '19% !important' : '300px'}"
            >
              ファンド名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8.5% !important' : '120px'}"
            >
              預り区分
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13.25% !important' : '150px'}"
            >
              保有口数
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13.25% !important' : '170px'}"
            >
              平均取得価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13.25% !important' : '170px'}"
            >
              基準価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13.25% !important' : '170px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '175px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListMutualFund"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td
              class="_table__data"
              v-html="addLineBreakHintsForDepositType($_out($_getCodeValue('ASSET_DEPOSIT_TYPE', 1, item.depositType)))"
            ></td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.openPrice, 7)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.price, 7)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="7"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.mutualFundProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.mutualFundProfitAndLossTotal)) !== '-' && Number(portfolioData.mutualFundProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.mutualFundProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--外国債券（円貨）-->
    <el-row
      v-if="portfolioData.holdingSecurityListForeignClaimYenBase && portfolioData.holdingSecurityListForeignClaimYenBase.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListForeignClaimYenBase"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="11"
            >外国債券（円貨）
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px'}"
            >
              銘柄コード</th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '16% !important' : '265px'}"
            >
              銘柄名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8.5% !important' : '120px'}"
            >
              預り区分</th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '85px'}"
            >
              利率（％）
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '85px'}"
            >
              参考為替
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '105px'}"
            >
              償還日
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '14% !important' : '185px'}"
            >
              利払日
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '85px'}"
            >
              買付単価
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '10% !important' : '85px'}"
            >
              保有額面
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '10% !important' : '90px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '6.5% !important' : '130px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListForeignClaimYenBase"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td
              class="_table__data"
              v-html="addLineBreakHintsForDepositType($_out($_getCodeValue('ASSET_DEPOSIT_TYPE', 1, item.depositType)))"
            ></td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.compoundInterest,8)) }}</td>
            <td class="_table__data _right">{{ item.fxRate ? $_withCommaZeroPadding(item.fxRate) + '円/' + item.currency : '-' }}</td>
            <td class="_table__data">{{ $_out($_getFormattedDateValue(item.redemptionDate, 'date8')) }}</td>
            <td class="_table__data">{{ $_out(breakAfterComma(item.interestPaymentDate)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.openPrice, 7)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="10"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.foreignClaimYenBaseProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.foreignClaimYenBaseProfitAndLossTotal)) !== '-' && Number(portfolioData.foreignClaimYenBaseProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.foreignClaimYenBaseProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--外国株式-->
    <el-row
      v-if="portfolioData.holdingSecurityListForeignStock && portfolioData.holdingSecurityListForeignStock.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListForeignStock"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="9"
            >外国株式
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px', 'fontSize': isPrint ? '7px' : '14px' }"
            >
              ティッカー/<br>銘柄コード
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '19% !important' : '280px' }"
            >
              銘柄名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8.5% !important' : '120px' }"
            >
              預り区分
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '10% !important' : '120px' }"
            >
              保有株数
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '9% !important' : '135px' }"
            >
              参考為替
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '11% !important' : '135px' }"
            >
              取得単価<br>「現地通貨」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '11% !important' : '135px' }"
            >
              前日終値<br>「現地通貨」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12% !important' : '135px' }"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '150px' }"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListForeignStock"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td
              class="_table__data"
              v-html="addLineBreakHintsForDepositType($_out($_getCodeValue('ASSET_DEPOSIT_TYPE', 1, item.depositType)))"
            ></td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ item.fxRate ? $_withCommaZeroPadding(item.fxRate) + '円/' + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ item.openPrice ? $_withCommaNoneZeroPadding(item.openPrice, 7) + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ item.marketValueForeign ? $_withCommaNoneZeroPadding(item.marketValueForeign, 7) + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="8"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.foreignStockProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.foreignStockProfitAndLossTotal)) !== '-' && Number(portfolioData.foreignStockProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.foreignStockProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--外貨建MMF-->
    <el-row
      v-if="portfolioData.holdingSecurityListForeignMmf && portfolioData.holdingSecurityListForeignMmf.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListForeignMmf"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="6"
            >外貨建MMF
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px'}"
            >
              銘柄コード
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '19% !important' : '500px'}"
            >
              ファンド名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '20% !important' : '185px'}"
            >
              保有口数
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '20% !important' : '185px'}"
            >
              参考為替
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '21.5% !important' : '190px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '175px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListForeignMmf"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ item.fxRate ? $_withCommaZeroPadding(item.fxRate) + '円/' + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>

          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="5"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.foreignMmfProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.foreignMmfProfitAndLossTotal)) !== '-' && Number(portfolioData.foreignMmfProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.foreignMmfProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--外国債券（外貨建）-->
    <el-row
      v-if="portfolioData.holdingSecurityListForeignClaimForeign && portfolioData.holdingSecurityListForeignClaimForeign.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListForeignClaimForeign"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="7"
            >外国債券（外貨建）
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px'}"
            >
              銘柄コード
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '19% !important' : '500px'}"
            >
              銘柄名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '15.5% !important' : '140px'}"
            >
              保有額面
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '15.5% !important' : '140px'}"
            >
              取得単価<br>「現地通貨」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '15% !important' : '140px'}"
            >
              参考為替
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '15.5% !important' : '140px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '175px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListForeignClaimForeign"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ item.openPrice ? $_withCommaNoneZeroPadding(item.openPrice, 7) + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ item.fxRate ? $_withCommaZeroPadding(item.fxRate) + '円/' + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="6"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.foreignClaimForeignProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.foreignClaimForeignProfitAndLossTotal)) !== '-' && Number(portfolioData.foreignClaimForeignProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.foreignClaimForeignProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--外国債券（外貨建仕組債）-->
    <el-row
      v-if="portfolioData.holdingSecurityListForeignClaimForeignStructuredBond && portfolioData.holdingSecurityListForeignClaimForeignStructuredBond.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListForeignClaimForeignStructuredBond"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="7"
            >外国債券（外貨建仕組債）
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px'}"
            >
              銘柄コード
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '19% !important' : '500px'}"
            >
              銘柄名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '15.5% !important' : '140px'}"
            >
              保有額面
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '15.5% !important' : '140px'}"
            >
              取得単価<br>「現地通貨」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '15% !important' : '140px'}"
            >
              参考為替
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '15.5% !important' : '140px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '175px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListForeignClaimForeignStructuredBond"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ item.openPrice ? $_withCommaNoneZeroPadding(item.openPrice, 7) + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ item.fxRate ? $_withCommaZeroPadding(item.fxRate) + '円/' + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="6"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.foreignClaimForeignStructuredBondProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.foreignClaimForeignStructuredBondProfitAndLossTotal)) !== '-' && Number(portfolioData.foreignClaimForeignStructuredBondProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.foreignClaimForeignStructuredBondProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--STリスト(セキュリティ・トークン)-->
    <el-row
      v-if="portfolioData.holdingSecurityListSecurityToken && portfolioData.holdingSecurityListSecurityToken.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListSecurityToken"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="8"
            >ST（セキュリティ・トークン）
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px'}"
            >
              銘柄コード
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '19% !important' : '280px'}"
            >
              銘柄名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8.5% !important' : '120px'}"
            >
              預り区分
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13% !important' : '165px'}"
            >
              保有株数
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13% !important' : '165px'}"
            >
              平均取得価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13% !important' : '165px'}"
            >
              現在値<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '14% !important' : '165px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '175px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListSecurityToken"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td
              class="_table__data"
              v-html="addLineBreakHintsForDepositType($_out($_getCodeValue('ASSET_DEPOSIT_TYPE', 1, item.depositType)))"
            ></td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.openPrice, 7)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.price, 7)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="7"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.securityTokenProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.securityTokenProfitAndLossTotal)) !== '-' && Number(portfolioData.securityTokenProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.securityTokenProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--現金-->
    <el-row
      v-if="portfolioData.holdingSecurityListCash && portfolioData.holdingSecurityListCash.length > 0 ||
        portfolioData.holdingSecurityListSbiRapAccountCash && portfolioData.holdingSecurityListSbiRapAccountCash.length > 0 ||
        portfolioData.holdingSecurityListForeignDeposit && portfolioData.holdingSecurityListForeignDeposit.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListCash"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="5"
            >現金
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '26% !important' : '500px'}"
            >
              種類
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '20.5% !important' : '235px'}"
            >
              参考為替
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '20.5% !important' : '235px'}"
            >
              残高<br>「現地通貨」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '20.5% !important' : '140px'}"
            >
              残高<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '175px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(item, idx) in portfolioData.holdingSecurityListCash"
            :key="idx"
          >
            <td class="_table__data"> {{ $_out(item.name) }}</td>
            <td class="_table__data _right">{{ $_out(item.fxRate) }}</td>
            <td class="_table__data _right">{{ $_out(item.foreignValuation) }} </td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuationTotal,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>

          <tr
            v-for="(item, idx) in portfolioData.holdingSecurityListSbiRapAccountCash"
            :key="idx"
          >
            <td class="_table__data">{{ $_out(item.securityClassCode) }}</td>
            <td class="_table__data _right">{{ $_out(item.fxRate) }}</td>
            <td class="_table__data _right">{{ $_out(item.foreignValuation) }} </td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
          <tr
            v-for="(item, idx) in portfolioData.holdingSecurityListForeignDeposit"
            :key="idx"
          >
            <td
              class="_table__data"
            >{{ $_out($_getCodeValue('ASSET_CURRENCY_CODE', 1, item.currency)) }}</td>
            <td class="_table__data _right">{{ item.fxRate ? $_withCommaZeroPadding(item.fxRate) + '円/' + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ item.foreignValuation ? $_withCommaNoneZeroPadding(item.foreignValuation,4) + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="4"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.cashProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.cashProfitAndLossTotal)) !== '-' && Number(portfolioData.cashProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.cashProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--信用建玉-->
    <el-row
      v-if="portfolioData.holdingSecurityListMarginPosition && portfolioData.holdingSecurityListMarginPosition.length > 0"
      class="table_space"
    >
      <el-col
        :span="24"
        style="break-after: avoid;"
      >
        <span>●建玉一覧(維持率:{{ portfolioData.domesticMarginActualGrntRate ? $_withCommaZeroPadding(portfolioData.domesticMarginActualGrntRate,2) + '%' : '-' }})</span>
      </el-col>
      <table
        id="holdingSecurityListMarginPosition"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="9"
            >信用建玉
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px'}"
            >
              銘柄コード
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '19% !important' : '300px'}"
            >
              銘柄名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '9.5% !important' : '110px'}"
            >
              建区分<br>期限
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '155px'}"
            >
              返済期限
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '11% !important' : '110px'}"
            >
              建株数
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '11% !important' : '110px'}"
            >
              建単価
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '11% !important' : '110px'}"
            >
              前日終値
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12% !important' : '105px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '175px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListMarginPosition"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td class="_table__data">
              <span :class="[item.tradeTypeName === '売建' ? 'font-color__minus' : $_out(item.tradeTypeName) !== '-' && item.tradeTypeName === '買建' ? 'font-color__plus' : '', 'bold']">
                {{ $_out(item.tradeTypeName) }}
              </span><br>{{ $_out($_getCodeValue('APPOINTMENT_KBN', 1, item.designationDealtClassification)) }}
            </td>
            <td class="_table__data">{{ item.designationDealtClassification !== '9' ? $_out($_getFormattedDateValue(item.lastTradeDate, 'date8')) : '----/--/--' }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.openPrice, 7)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.price, 7)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="8"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.marginPositionProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.marginPositionProfitAndLossTotal)) !== '-' && Number(portfolioData.marginPositionProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.marginPositionProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--米株信用建玉-->
    <el-row
      v-if="portfolioData.holdingSecurityListUsStockMarginPositionList && portfolioData.holdingSecurityListUsStockMarginPositionList.length > 0"
      class="table_space"
    >
      <el-col
        :span="24"
        style="break-after: avoid;"
      >
        <span>●建玉一覧(維持率:{{ portfolioData.americaMarginActualGrntRate ? $_withCommaZeroPadding(portfolioData.americaMarginActualGrntRate,2) + '%' : '-' }})</span>
      </el-col>
      <table
        id="holdingSecurityListUsStockMarginPositionList"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="10"
            >米株信用建玉
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '150px'}"
            >
              ティッカー
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '19% !important' : '300px'}"
            >
              銘柄名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '5% !important' : '95px'}"
            >
              建区分<br>期限
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '7% !important' : '155px'}"
            >
              返済期限
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '9.375% !important' : '95px'}"
            >
              建株数
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '9.375% !important' : '95px'}"
            >
              参考為替
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '9.375% !important' : '110px'}"
            >
              建単価<br>「現地通貨」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '9.375% !important' : '115px'}"
            >
              前日終値<br>「現地通貨」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12% !important' : '110px'}"
            >
              評価額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '12.5% !important' : '175px'}"
            >
              評価損益<br>「円」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListUsStockMarginPositionList"
          :key="idx"
        >
          <tr>
            <td
              class="_table__data"
            >{{ $_out(item.brandCode) }}</td>
            <td class="_table__data">{{ $_out(item.brandName) }}</td>
            <td class="_table__data">
              <span :class="[item.tradeTypeName === '売建' ? 'font-color__minus' : $_out(item.tradeTypeName) !== '-' && item.tradeTypeName === '買建' ? 'font-color__plus' : '', 'bold']">
                {{ $_out(item.tradeTypeName) }}
              </span><br>{{ $_out($_getCodeValue('APPOINTMENT_KBN', 1, item.designationDealtClassification)) }}
            </td>
            <td class="_table__data">{{ item.designationDealtClassification !== '9' ? $_out($_getFormattedDateValue(item.lastTradeDate, 'date8')) : '----/--/--' }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.contractStandardDeposit)) }}</td>
            <td class="_table__data _right">{{ item.fxRate ? $_withCommaZeroPadding(item.fxRate) + '円/' + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ item.openPrice ? $_withCommaNoneZeroPadding(item.openPrice, 7) + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ item.price ? $_withCommaNoneZeroPadding(item.price, 7) + item.currency : '-' }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaNoneZeroPadding(item.valuation,4)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.profitAndLoss)) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="9"
            >評価損益合計
            </th>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.usStockMarginPositionProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.usStockMarginPositionProfitAndLossTotal)) !== '-' && Number(portfolioData.usStockMarginPositionProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.usStockMarginPositionProfitAndLossTotal)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <!--投資信託トータルリターン-->
    <el-row
      v-if="portfolioData.holdingSecurityListMutualFundTotalReturnList && portfolioData.holdingSecurityListMutualFundTotalReturnList.length > 0"
      class="table_space"
    >
      <table
        id="holdingSecurityListMutualFundTotalReturnList"
        class="_table__horizontal _table__body"
        :style="{ 'width': isPrint ? '330mm' : '100%' }"
      >
        <thead>
          <tr>
            <th
              class="_table__header"
              colspan="10"
            >投資信託トータルリターン
            </th>
          </tr>
          <tr>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8% !important' : '120px'}"
            >
              保有状況
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '8% !important' : '160px'}"
            >
              銘柄コード
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '20% !important' : '265px'}"
            >
              ファンド名
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '9.5% !important' : '140px'}"
            >
              預り区分
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '10.2% !important' : '195px'}"
            >
              評価金額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '10.2% !important' : '195px'}"
            >
              売却金額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '10.2% !important' : '195px'}"
            >
              分配金額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '10.2% !important' : '140px'}"
            >
              買付金額<br>「円」
            </th>
            <th
              class="_table__light_header"
              :style="{ 'width': isPrint ? '13.7% !important' : '195px'}"
            >
              トータルリターン<br>「円/率」
            </th>
          </tr>
        </thead>
        <tbody
          v-for="(item, idx) in portfolioData.holdingSecurityListMutualFundTotalReturnList"
          :key="idx"
        >
          <tr>
            <td class="_table__data">{{ $_out($_getCodeValue('HOLDING_STATUS', 1, item.holdingStatus)) }}</td>
            <td class="_table__data">{{ $_out(item.nriCd) }}</td>
            <td class="_table__data">{{ $_out(item.fundName) }}</td>
            <td
              class="_table__data"
              v-html="addLineBreakHintsForDepositType($_out($_getCodeValue('TOTAL_RETURN_SPECIFIC_DEPOSIT_TYPE', 1, item.nonSpecificDepositCategory)))"
            ></td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.depositTransferMarketValueToday)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.amountSellRedemptionDeliverOutTotal)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.couponRevenueTotal)) }}</td>
            <td class="_table__data _right">{{ $_out($_withCommaInteger(item.amountBuyReinvestSubscriptDeliverInTotal)) }}</td>
            <td class="_table__data _right">
              <span :class="[Number(item.totalReturnYen) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.totalReturnYen)) !== '-' && Number(item.totalReturnYen) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(item.totalReturnYen)) }}
              </span>
              <br>
              <span :class="[Number(item.totalReturnRate) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(item.totalReturnRate)) !== '-' && Number(item.totalReturnRate) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ item.totalReturnRate ? $_signedWithCommaZeroPadding(item.totalReturnRate, 2) + '%' : '' }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr>
            <th
              class="_table__light_header _right"
              colspan="4"
            >合計
            </th>
            <td class="_table__data _right">
              {{ $_out($_withCommaInteger(portfolioData.mutualFundTotalReturnValuationTotal)) }}
            </td>
            <td class="_table__data _right">
              {{ $_out($_withCommaInteger(portfolioData.sellPriceTotal)) }}
            </td>
            <td class="_table__data _right">
              {{ $_out($_withCommaInteger(portfolioData.dividendTotal)) }}
            </td>
            <td class="_table__data _right">
              {{ $_out($_withCommaInteger(portfolioData.buyPriceTotal)) }}
            </td>
            <td class="_table__data _right">
              <span :class="[Number(portfolioData.totalReturnYenTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.totalReturnYenTotal)) !== '-' && Number(portfolioData.totalReturnYenTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ $_out($_signedWithCommaInteger(portfolioData.totalReturnYenTotal)) }}
              </span>
              <br>
              <span :class="[Number(portfolioData.totalReturnRateTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaInteger(portfolioData.totalReturnRateTotal)) !== '-' && Number(portfolioData.totalReturnRateTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
                {{ portfolioData.totalReturnRateTotal ? $_signedWithCommaZeroPadding(portfolioData.totalReturnRateTotal, 2) + '%' : '' }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>

    <el-row
      class="table_space"
      style="margin-bottom: 1rem;"
    >
      <el-row>
        <span><strong>ご注意事項</strong></span>
      </el-row>
      <table>
        <tr>
          <td style="width: 1.5rem; vertical-align: top;">・</td>
          <td>
            <span>上記の預かり、価格は全て前営業日基準です。</span>
          </td>
        </tr>
        <tr>
          <td style="width: 1.5rem; vertical-align: top;">・</td>
          <td>
            <span>株式分割などのコーポレートアクションがある場合、権利付き最終日の引け後から、権利落ち日の寄付きまでは、預かりや価格が正しく反映されない可能性があります。</span>
          </td>
        </tr>
        <tr>
          <td style="width: 1.5rem; vertical-align: top;">・</td>
          <td>
            <span>上記の残高明細は、お客様の便宜のために作成され提供する暫定的かつ非公式ものです。残高について正式な詳細は、ＳＢＩ証券にお問い合わせいただき「取引残高報告書」等をご利用いただきますよう宜しくお願い申し上げます。 </span>
          </td>
        </tr>
        <tr>
          <td style="width: 1.5rem; vertical-align: top;">・</td>
          <td>
            <span>外国債券は原則、月末時点の時価評価ですが、一部対象外の商品もございます。</span>
          </td>
        </tr>
        <tr>
          <td style="width: 1.5rem; vertical-align: top;">・</td>
          <td>
            <span>資産状況の現金(円貨)は、預り金、信用保証金、スィープ専用銀行口座の合計を表示します。</span>
          </td>
        </tr>
        <tr>
          <td style="width: 1.5rem; vertical-align: top;">・</td>
          <td>
            <span>資産状況の現金(外貨)は、外貨預り金、米ドル信用保証金の合計を表示します。</span>
          </td>
        </tr>
        <tr>
          <td style="width: 1.5rem; vertical-align: top;">・</td>
          <td>
            <span>ＳＴの現在値は前月末時点の参考値になります。当該価格での買取を保証するものではございません。</span>
          </td>
        </tr>
      </table>
    </el-row>
  </div>
</template>

<script>
export default {
  props: {
    portfolioData: { type: Object, required: true, default: () => ({}) },
    isPrint: { type: Boolean, required: true, default: false }
  },
  methods: {
    breakAfterComma(val) {
      if (!val) {
        return val
      }

      let result = val
      if (val.includes('年4回(四半期1回)') || val.includes('年3回(4ヶ月毎)')) {
        result = result.replaceAll('：', '：\n')
      } else if ((val.includes('年1回') || val.includes('年2回')) && val.split(',').length > 2) {
        result = result.replaceAll('：', '：\n')
      }
      return result
    },
    addLineBreakHintsForDepositType: (val) => {
      if (!val) {
        return val
      }

      let result = '<span style="display: inline-block">' + val + '</span>'
      const patternBrackets = /(.*)([\(（].*?[\)）])(.*)/
      result = result.replace(patternBrackets, '$1</span><span style="display: inline-block">$2</span><span style="display: inline-block">$3')

      const patternJunior = /(.*)(ジュニア)(.*)/
      result = result.replace(patternJunior, '$1</span><span style="display: inline-block">$2</span><span style="display: inline-block">$3')

      const patternOldTusmitate = /(.*)(旧つみたて)(.*)/
      result = result.replace(patternOldTusmitate, '$1</span><span style="display: inline-block">$2</span><span style="display: inline-block">$3')

      return result
    }
  }
}
</script>

<style lang="scss"  scoped>
@import "@/styles/variables.scss";
@import "@/styles/table.scss";
.notice-unchecked {
  color:red;
}
._left {
  text-align: left;
}
._right {
  text-align: right;
}
._center {
  text-align: center;
}
._table__light_header {
  color: #18181a;
  background-color: #E6E8F0;
  border: 1px solid #a7b1c3;
  padding: 2px 10px 2px 10px;
}
.table_space {
  padding-top: 1.5rem;
}
:deep(._table__horizontal) ._table__data {
  padding: 2px 10px 2px 10px;
  word-break: break-word;
  box-sizing: border-box;
}
th, td {
  page-break-inside: avoid;
}
td {
  white-space: pre-wrap;
}
.portfolio_print th._table__light_header,
.portfolio_print td._table__data {
  padding: 2px;
}

</style>
