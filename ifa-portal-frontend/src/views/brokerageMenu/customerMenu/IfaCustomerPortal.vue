<template>
  <div>
    <el-card
      id="customerInfoCard"
      shadow="never"
      class="card-content customer-info__wrapper __word-break"
    >
      <!-- 顧客情報 -->
      <!-- 常に表示状態の項目 -->
      <div style="padding: 0 1.3125rem 0 0">
        <div class="customer-info">
          <div class="customer-info-left">
            <el-icon class="custom-icon-style">
              <OfficeBuilding
                v-if="customerInfo.corporationType === '1'"
              ></OfficeBuilding>
              <Avatar v-else></Avatar>
            </el-icon>
          </div>
          <div class="customer-info-right">
            <table class="reset_style"
                   style="width: 100%; table-layout: fixed"
            >
              <colgroup>
                <col style="width: 21%; min-width: 200px;">
                <col style="width: 4%; min-width: 30px;">
                <col style="width: 20%; min-width: 218px;">
                <col style="width: 1.5%; min-width: 15px;">
                <col style="width: 25%; min-width: 252px;">
                <col style="width: 1.5%; min-width: 15px;">
                <col style="width: 16%;">
                <col style="width: 1.5%; min-width: 15px;">
                <col style="width: 19%; min-width: 190px;">

              </colgroup>
              <tr>
                <td style="width: 20%; min-width: 200px; text-align: left">
                  <span class="user-name__wrapper">
                    <span class="user-name">{{
                      $_out(customerInfo.customerNameKanji)
                    }}</span>
                    <span class="user-name-kana">（{{ $_out(customerInfo.customerNameKana) }}）</span>
                  </span>
                </td>
                <td style="width: 4%; min-width: 40px">
                  <div style="text-align: center;">
                    <ifa-notice-info
                      :notice-info-level="customerInfo.noticeInfoLevel"
                      :customer-code="customerInfo.customerCode"
                      :buten-code="customerInfo.butenCode"
                      :account-number="customerInfo.accountNumber"
                    ></ifa-notice-info>
                    <el-tooltip
                      content="My資産"
                      placement="bottom"
                      effect="light"
                    >
                      <span>
                        <ifa-link
                          ref="ifaLinkMyAssset"
                          class="data-icon clickable-icon"
                          style="width: 18px; height: 18px; font-size: 16px"
                          :url-id="12"
                          :disp-link="false"
                          link-icon-image="Histogram"
                          manual-init
                        ></ifa-link>
                      </span>
                    </el-tooltip>
                  </div>
                </td>
                <!-- 法人で表示する項目 -->
                <td v-if="customerInfo.corporationType === '1'"
                    colspan="5"
                >
                  <div>
                    <div class="info_l info-item__wrapper info_xxxl">
                      <span class="info-item__header __left">代表者名</span>
                      <span class="__right">：</span>
                      <span v-if="customerInfo.representativeName && customerInfo.representativeNameKana"
                            class="__left"
                            style="position: relative; bottom: 0.25rem;"
                      >
                        {{ $_out($_getCodeValue('TITLE_OF_AGENT_TYPE', 1, customerInfo.titleOfDaihyo)) }}
                        <ruby>
                          {{
                            $_out(customerInfo.representativeName)
                          }}
                          <rt>
                            {{
                              customerInfo.representativeNameKana
                            }}
                          </rt>
                        </ruby>
                      </span>
                      <span v-else
                            class="__left"
                      >
                        {{ $_out($_getCodeValue('TITLE_OF_AGENT_TYPE', 1, customerInfo.titleOfDaihyo)) }}
                        <ruby>
                          {{
                            $_out(customerInfo.representativeName)
                          }}
                          <rt>
                            {{
                              customerInfo.representativeNameKana
                            }}
                          </rt>
                        </ruby>
                      </span>
                    </div>

                    <div class="info_l info-item__wrapper info_xxxl">
                      <span class="info-item__header __left">代理人名</span>
                      <span class="info-item__header __right">：</span>
                      <span v-if="customerInfo.agentName && customerInfo.agentNameKana"
                            class="__left"
                            style="position: relative; bottom: 0.25rem;"
                      >
                        {{
                          $_out($_getCodeValue('TITLE_OF_AGENT_TYPE', 1, customerInfo.titleOfAgent))
                        }}
                        <ruby>
                          {{
                            $_out(customerInfo.agentName)
                          }}
                          <rt>
                            {{ customerInfo.agentNameKana }}
                          </rt>
                        </ruby>
                      </span>
                      <span v-else
                            class="__left"
                      >
                        {{
                          $_out($_getCodeValue('TITLE_OF_AGENT_TYPE', 1, customerInfo.titleOfAgent))
                        }}
                        <ruby>
                          {{
                            $_out(customerInfo.agentName)
                          }}
                          <rt>
                            {{ customerInfo.agentNameKana }}
                          </rt>
                        </ruby>
                      </span>
                    </div>

                  </div></td>
              </tr>
              <!-- 個人で表示する項目 -->
              <template v-if="customerInfo.corporationType === '0'">
                <tr>
                  <td
                    v-if="
                      customerInfo.jrNisaAccountNumber != null &&
                        customerInfo.jrNisaAccountNumber != '' &&
                        customerInfo.jrNisaAccountNumber != '0000000'
                    "
                    class="info-item__wrapper info_width"
                  >
                    <div class="info_xxl">
                      <span class="info-item__header __left">口座番号</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">
                        {{ $_out(customerInfo.butenCode) }}-{{ $_out(customerInfo.accountNumber) }}
                      </span>
                    </div>
                  </td>
                  <td v-else>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td
                    colspan="3"
                    class="info-item__wrapper"
                    style="width: 40%; min-width: 414px;"
                  >
                    <div class="info_xxxl">
                      <span class="info-item__header __left">住所</span>
                      <span class="info-item__header __right">：</span>
                      <span v-if="customerInfo.addressKanji1 && customerInfo.addressKana1"
                            class="__left"
                            style="position: relative; bottom: 0.25rem; overflow: hidden;"
                      >
                        {{ $_out(customerInfo.zipCode) }}&nbsp;
                        <ruby>
                          {{ $_out(customerInfo.addressKanji1) }}
                          <rt>{{ customerInfo.addressKana1 }}</rt>
                        </ruby>
                      </span>
                      <span v-else
                            class="__left"
                      >
                        {{ $_out(customerInfo.zipCode) }}&nbsp;
                        <ruby>
                          {{ $_out(customerInfo.addressKanji1) }}
                          <rt>{{ customerInfo.addressKana1 }}</rt>
                        </ruby>
                      </span>
                    </div>
                  </td>
                  <td class="space_width">&nbsp;</td>

                  <td
                    class="info-item__wrapper"
                    style="width: 23.5%; min-width: 235px"
                  >
                    <div class="info_m">
                      <span class="info-item__header __left">電話番号</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.homePhoneNumbar)
                      }}</span>
                    </div>
                  </td>
                  <td class="space_width">&nbsp;</td>
                  <td class="info-item__wrapper info_width">
                    <div class="info_m">
                      <span class="info-item__header __left">携帯電話</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.phoneNumber2)
                      }}</span>
                    </div>
                  </td>
                  <td class="space_width">&nbsp;</td>
                </tr>
              </template>
              <tr>
                <!-- 個人で表示する項目 ジュニアNISA口座番号がNULLと'0000000'でない場合に項目表示 -->
                <td
                  v-if="
                    customerInfo.corporationType === '0' &&
                      customerInfo.jrNisaAccountNumber != null &&
                      customerInfo.jrNisaAccountNumber != '' &&
                      customerInfo.jrNisaAccountNumber != '0000000'
                  "
                  class="info-item__wrapper info_width"
                >
                  <div class="info_xxl">
                    <span class="info-item__header __left">ジュニアNISA</span>
                    <span class="info-item__header __right">：</span>
                    <span class="__left">
                      {{ $_out(customerInfo.butenCode) }}-{{ $_out(customerInfo.jrNisaAccountNumber) }}
                    </span>
                  </div>
                </td>
                <td v-else
                    class="info-item__wrapper info_width"
                >
                  <div class="info_xxl">
                    <span class="info-item__header __left">口座番号</span>
                    <span class="info-item__header __right">：</span>
                    <span class="__left">
                      {{ $_out(customerInfo.butenCode) }}-{{ $_out(customerInfo.accountNumber) }}
                    </span>
                  </div>
                </td>
                <td class="space_width">&nbsp;</td>
                <!-- 法人で表示する項目 -->
                <template v-if="customerInfo.corporationType === '1'">
                  <td
                    colspan="3"
                    class="info-item__wrapper"
                    style="width: 40%; min-width: 414px"
                  >
                    <div class="info_xxxl">
                      <span class="info-item__header __left">住所</span>
                      <span class="info-item__header __right">：</span>
                      <span v-if="customerInfo.addressKanji1 && customerInfo.addressKana1"
                            class="__left"
                            style="position: relative; bottom: 0.25rem; overflow: hidden;"
                      >
                        {{ $_out(customerInfo.zipCode) }}&nbsp;
                        <ruby>
                          {{ $_out(customerInfo.addressKanji1) }}
                          <rt>{{ customerInfo.addressKana1 }}</rt>
                        </ruby>
                      </span>
                      <span v-else
                            class="__left"
                      >
                        {{ $_out(customerInfo.zipCode) }}&nbsp;
                        <ruby>
                          {{ $_out(customerInfo.addressKanji1) }}
                          <rt>{{ customerInfo.addressKana1 }}</rt>
                        </ruby>
                      </span>
                    </div>
                  </td>
                  <td class="space_width">&nbsp;</td>
                  <!-- 法人で表示する項目 -->
                  <td
                    class="info-item__wrapper"
                    style="width: 23.5%; min-width: 235px"
                  >
                    <div class="info_m">
                      <span class="info-item__header __left">電話番号</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.homePhoneNumbar)
                      }}</span>
                    </div>
                  </td>
                  <td class="space_width">&nbsp;</td>
                </template>
                <!-- 個人で表示する項目 -->
                <template v-if="customerInfo.corporationType === '0'">
                  <td class="info-item__wrapper">
                    <div class="info_xxxl">
                      <span class="info-item__header __left">買付余力(国内)</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <span
                          class="clickable"
                          style="padding: 0"
                          @click="$_startShowMenu('SUB0202_010301')"
                        >
                          <span>{{
                            $_out(
                              $_withCommaInteger(customerInfo.yenBuyingPower)
                            )
                          }}
                            円</span>
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td class="info-item__wrapper">
                    <div class="info_xl">
                      <span class="info-item__header __left">信用余力(国内)</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <span
                          class="clickable"
                          style="padding: 0"
                          @click="$_startShowMenu('SUB0202_010302')"
                        >
                          <span>{{ newBuildingCapacity }} 円</span>
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td class="info-item__wrapper">
                    <div
                      class="info_xxxl"
                    >
                      <span class="info-item__header __left"
                            style="position: relative;"
                      >
                        <!--信用口座区分(国内)が"信用口座"の場合のみリンクを表示-->
                        <ifa-help-icon
                          v-if="
                            customerInfo.domesticMarginAccountType === '1' &&
                              maintenanceRateJpyAmountDescriptionMessage
                          "
                          style="position: absolute; top: 0.125rem; left: -0.125rem;"
                          :message="maintenanceRateJpyAmountDescriptionMessage"
                        ></ifa-help-icon>
                        <template v-if="
                          customerInfo.domesticMarginAccountType === '1' &&
                            maintenanceRateJpyAmountDescriptionMessage
                        "
                        >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</template>
                        維持率(国内)
                      </span>
                      <span class="info-item__header __right">：</span>
                      <span
                        class="__right"
                      >
                        <span
                          v-if="customerInfo.domesticMarginAccountType === '1'"
                          class="clickable"
                          style="padding: 0"
                          @click="realTimeMarginRateJpyAmountCurrencyLinkA006"
                        >
                          <span>{{
                            $_out(
                              $_withCommaZeroPadding(
                                customerInfo.yenCustomerInfoDetentionRate,
                                2
                              )
                            )
                          }}
                            %</span>
                        </span>
                        <span v-else>
                          {{
                            $_out(
                              $_withCommaZeroPadding(
                                customerInfo.yenCustomerInfoDetentionRate,
                                2
                              )
                            )
                          }}
                          %
                        </span>
                      </span>
                    </div>
                  </td>
                  <td class="space_width">&nbsp;</td>
                </template>
                <template
                  v-if="
                    (growthInvestmentLimitYear !== ' ' ||
                      accumulateInvestmentLimitYear !== ' ') &&
                      customerInfo.corporationType === '0'
                  "
                >
                  <td class="info-item__wrapper">
                    <div
                      v-if="growthInvestmentLimitYear !== ' '"
                      class="info_nisa"
                    >
                      <span class="info-item__header __left">NISA成長投資枠</span>
                      <span class="info-item__header __right">：</span>
                      <div class="__right">
                        <span class="__right">{{
                          $_out($_withCommaInteger(growthInvestmentLimitYear))
                        }}
                          円</span>
                      </div>
                    </div>
                  </td>
                </template>
                <template v-else>
                  <td style="min-width: 214px">&nbsp;</td>
                </template>
              </tr>
              <tr>
                <td
                  class="info-item__wrapper info_width"
                >
                  <div class="info_xxl">
                    <span class="info-item__header __left">Cランク</span>
                    <span class="info-item__header __right">：</span>
                    <!-- 条件を満たす場合、赤字で点滅表示する -->
                    <span :class="['__left', { 'compliance-lank-attention': blinkComplianceLank }]">{{
                      $_out(customerInfo.complianceLank)
                    }}</span>
                  </div>
                </td>
                <td class="space_width">&nbsp;</td>
                <!-- 法人で表示する項目 -->
                <template v-if="customerInfo.corporationType === '1'">
                  <td class="info-item__wrapper">
                    <div class="info_xxxl">
                      <span class="info-item__header __left">買付余力(国内)</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <span
                          class="clickable"
                          style="padding: 0"
                          @click="$_startShowMenu('SUB0202_010301')"
                        >
                          <span>{{
                            $_out(
                              $_withCommaInteger(customerInfo.yenBuyingPower)
                            )
                          }}
                            円</span>
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td class="info-item__wrapper">
                    <div class="info_xl">
                      <span class="info-item__header __left">信用余力(国内)</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <span
                          class="clickable"
                          style="padding: 0"
                          @click="$_startShowMenu('SUB0202_010302')"
                        >
                          <span>{{ newBuildingCapacity }} 円</span>
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td class="info-item__wrapper">
                    <div
                      class="info_xxxl"
                    >
                      <span class="info-item__header __left"
                            style="position: relative;"
                      >
                        <!--信用口座区分(国内)が"信用口座"の場合のみリンクを表示-->
                        <ifa-help-icon
                          v-if="
                            customerInfo.domesticMarginAccountType === '1' &&
                              maintenanceRateJpyAmountDescriptionMessage
                          "
                          style="position: absolute; top: 0.125rem; left: -0.125rem;"
                          :message="maintenanceRateJpyAmountDescriptionMessage"
                        >
                        </ifa-help-icon>
                        <template v-if="
                          customerInfo.domesticMarginAccountType === '1' &&
                            maintenanceRateJpyAmountDescriptionMessage
                        "
                        >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</template>
                        維持率(国内)
                      </span>
                      <span class="info-item__header __right">：</span>
                      <span
                        class="__right"
                      >
                        <!--信用口座区分(国内)が"信用口座"の場合のみリンクを表示-->
                        <span
                          v-if="customerInfo.domesticMarginAccountType === '1'"
                          class="clickable"
                          style="padding: 0"
                          @click="realTimeMarginRateJpyAmountCurrencyLinkA006"
                        >
                          <span>{{
                            $_out(
                              $_withCommaZeroPadding(
                                customerInfo.yenCustomerInfoDetentionRate,
                                2
                              )
                            )
                          }}
                            %</span>
                        </span>
                        <span v-else>
                          {{
                            $_out(
                              $_withCommaZeroPadding(
                                customerInfo.yenCustomerInfoDetentionRate,
                                2
                              )
                            )
                          }}
                          %
                        </span>
                      </span>
                    </div>
                  </td>
                </template>
                <!-- 個人で表示する項目 -->
                <template v-if="customerInfo.corporationType === '0'">
                  <td class="info-item__wrapper">
                    <div class="info_xxxl">
                      <span class="info-item__header __left">買付余力(外国)</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <span
                          class="clickable"
                          style="padding: 0"
                          @click="$_startShowMenu('SUB0202_010303')"
                        >
                          <span>{{ $_out(foreignBuyPower) }}</span>
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td class="info-item__wrapper">
                    <div class="info_xl">
                      <span class="info-item__header __left">信用余力(米国)</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <span
                          class="clickable"
                          style="padding: 0"
                          @click="$_startShowMenu('SUB0202_010304')"
                        >
                          <span>{{ foreignNewBuildingCapacity }}</span><span> USD</span>
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td class="info-item__wrapper">
                    <div
                      class="info_xxxl"
                    >
                      <span class="info-item__header __left">
                        維持率(米国)
                      </span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <!--信用口座区分(外国)が"信用口座"の場合のみリンクを表示-->
                        <span
                          v-if="customerInfo.foreignMarginAccountType === '1'"
                          class="clickable"
                          style="padding: 0"
                          @click="realTimeMarginRateForeignCurrencyLinkA009"
                        >
                          {{
                            $_out(
                              $_withCommaZeroPadding(
                                customerInfo.foreignCustomerInfoDetentionRate,
                                2
                              )
                            )
                          }}
                          %
                        </span>
                        <span v-else>
                          {{
                            $_out(
                              $_withCommaZeroPadding(
                                customerInfo.foreignCustomerInfoDetentionRate,
                                2
                              )
                            )
                          }}
                          %
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                </template>
                <!-- 個人で表示する項目 -->
                <template
                  v-if="
                    (growthInvestmentLimitYear !== ' ' ||
                      accumulateInvestmentLimitYear !== ' ') &&
                      customerInfo.corporationType === '0'
                  "
                >
                  <td class="info-item__wrapper">
                    <div
                      v-if="growthInvestmentLimitYear !== ' '"
                      class="info_nisa"
                    >
                      <span class="info-item__header __left">NISAつみたて投資枠</span>
                      <span class="info-item__header __right">：</span>
                      <div class="__right">
                        <span class="__right">{{
                          $_out(
                            $_withCommaInteger(accumulateInvestmentLimitYear)
                          )
                        }}
                          円</span>
                      </div>
                    </div>
                  </td>
                </template>
                <template v-else>
                  <td style="min-width: 214px">&nbsp;</td>
                </template>
              </tr>
              <tr>
                <td class="info-item__wrapper">
                  <div class="info_xxl">
                    <span class="info-item__header __left">取引コース名</span>
                    <span class="info-item__header __right">：</span>
                    <span class="__left">{{
                      $_out(customerInfo.customerAttributeName)
                    }}</span>
                  </div>
                </td>
                <td>&nbsp;</td>
                <!-- 法人で表示する項目 -->
                <template v-if="customerInfo.corporationType === '1'">
                  <td class="info-item__wrapper">
                    <div class="info_xxxl">
                      <span class="info-item__header __left">買付余力(外国)</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <span
                          class="clickable"
                          style="padding: 0"
                          @click="$_startShowMenu('SUB0202_010303')"
                        >
                          <span>{{ $_out(foreignBuyPower) }}</span>
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td class="info-item__wrapper">
                    <div class="info_xl">
                      <span class="info-item__header __left">信用余力(米国)</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <span
                          class="clickable"
                          style="padding: 0"
                          @click="$_startShowMenu('SUB0202_010304')"
                        >
                          <span>{{ foreignNewBuildingCapacity }}</span><span> USD</span>
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td class="info-item__wrapper">
                    <div
                      class="info_xxxl"
                    >
                      <span class="info-item__header __left">
                        維持率(米国)
                      </span>
                      <span class="info-item__header __right">：</span>
                      <span class="__right">
                        <!--信用口座区分(外国)が"信用口座"の場合のみリンクを表示-->
                        <span
                          v-if="customerInfo.foreignMarginAccountType === '1'"
                          class="clickable"
                          style="padding: 0"
                          @click="realTimeMarginRateForeignCurrencyLinkA009"
                        >
                          {{
                            $_out(
                              $_withCommaZeroPadding(
                                customerInfo.foreignCustomerInfoDetentionRate,
                                2
                              )
                            )
                          }}
                          %
                        </span>
                        <span v-else>
                          {{
                            $_out(
                              $_withCommaZeroPadding(
                                customerInfo.foreignCustomerInfoDetentionRate,
                                2
                              )
                            )
                          }}
                          %
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                </template>
              </tr>
            </table>
          </div>
        </div>
        <div class="customer-info">
          <div class="customer-info-left"></div>
          <div class="customer-info-right"
               style="margin-bottom: 0.5rem;"
          >
            <table class="reset_style"
                   style="width: 100%; table-layout: fixed"
            >
              <colgroup>
                <col style="width: 5%; min-width: 50px;">
                <col style="width: 0.5%; min-width: 5px;">
                <col style="width: 15.5%; min-width: 155x;">
                <col style="width: 4%; min-width: 40px;">
                <col style="width: 20%; min-width: 218px;">
                <col style="width: 1.5%; min-width: 15px;">
                <col style="width: 15%; min-width: 150px;">
                <col style="width: 10%; min-width: 100px;">
                <col style="width: 1.5%; min-width: 15px;">
                <col style="width: 16%;">
                <col style="width: 1.5%; min-width: 15px;">
                <col style="width: 19%; min-width: 190px;">
              </colgroup>
              <tr>
                <template v-if="customerInfo.corporationType === '0'">
                  <td class="info-item__wrapper">
                    <div class="info_s">
                      <span class="info-item__header __left">性別</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out($_getCodeValue('SEX_KBN', 1, customerInfo.sexKbn))
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td class="info-item__wrapper">
                    <div class="info_m">
                      <span class="info-item__header __left">生年月日</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(
                          $_getFormattedDateValue(customerInfo.corBirthFlg)
                        )
                      }}({{ $_out(customerInfo.age) }}歳)</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                </template>
                <!-- 法人で表示する項目 -->
                <template v-else>
                  <td colspan="3"
                      class="info-item__wrapper"
                  >
                    <div class="info_xxl">
                      <span class="info-item__header __left">決算日</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(
                          customerInfo.uaiCorpSettlementTerm1
                        )
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                </template>
                <td class="info-item__wrapper"
                    colspan="5"
                    style="text-align: right; padding-right: 0; vertical-align: bottom;"
                >
                  <button v-if="state"
                          class="retract"
                          style="color:#223a70"
                          @click="collapse(state)"
                  >
                    &nbsp;<el-icon class="retractIcon"><Minus></Minus></el-icon>&nbsp;詳細画面を閉じる
                  </button>
                  <button v-else
                          class="retract"
                          style="color:#223a70"
                          @click="collapse(state)"
                  >
                    &nbsp;<el-icon class="retractIcon"><Plus></Plus></el-icon>&nbsp;詳細画面を開く
                  </button>
                </td>
                <td class="info-item__wrapper"
                    colspan="3"
                ></td>
              </tr>
            </table>
          </div>
        </div>
      </div>

      <el-collapse v-model="customerInfoActiveName">
        <el-collapse-item name="customer-info">
          <!-- 折り畳み表示状態で非表示になる項目 -->
          <div class="customer-info">
            <div class="customer-info-left">
            </div>
            <div class="customer-info-right">
              <table
                class="reset_style"
                style="width: 100%; table-layout: fixed;"
              >
                <colgroup>
                  <col style="width: 23.5%; min-width: 235px;">
                  <col style="width: 1.5%; min-width: 15px;">
                  <col style="width: 20%; min-width: 218px;">
                  <col style="width: 1.5%; min-width: 15px;">
                  <col style="width: 25%; min-width: 252px;">
                  <col style="width: 1.5%; min-width: 15px;">
                  <col style="width: 16%;">
                  <col style="width: 1.5%; min-width: 15px;">
                  <col style="width: 19%; min-width: 190px;">
                </colgroup>

                <tr>
                  <td class="info-item__wrapper">
                    <div class="info_xxl">
                      <span class="info-item__header __left">顧客ID</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.customerCode)
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td colspan="3"
                      class="info-item__wrapper"
                  >
                    <div class="info_xxxl">
                      <span class="info-item__header __left">ユーザーネーム</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">
                        <span>{{ $_out(customerInfo.userName) }}</span>
                        <span
                          style="float: inline-end; padding: 0"
                        >
                          <ifa-link
                            v-if="customerInfo.tenGunId"
                            ref="ifalinkUsername"
                            class="userNameIcon"
                            :url-id="urlLink"
                            :pattern-id="1"
                            disp-name="ユーザーネーム・パスワード変更履歴参照"
                            link-icon-image="externalLink"
                            http-method="POST"
                            :param-object="{
                              ccs_op_id: customerInfo.ccsOpId,
                              _TENGUN_ID: customerInfo.tenGunId
                            }"
                            :manual-init="true"
                          ></ifa-link>
                        </span>
                      </span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td colspan="3"
                      class="info-item__wrapper"
                  >
                    <div
                      class="info_xxxl"
                    >
                      <span class="info-item__header __left">コース名</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.customerInfoCourse)
                      }}</span>
                    </div>
                  </td>
                </tr>
                <template v-if="customerInfo.corporationType == '0'">
                  <tr>
                    <td class="info-item__wrapper">
                      <div class="info_xxl">
                        <span class="info-item__header __left">勤務先</span>
                        <span class="info-item__header __right">：</span>
                        <span class="__left">{{
                          $_out(customerInfo.uaiOfficeName)
                        }}</span>
                      </div>
                    </td>
                    <td>&nbsp;</td>
                    <td colspan="3"
                        class="info-item__wrapper"
                    >
                      <div class="info_xxxl">
                        <span class="info-item__header __left">職業</span>
                        <span class="info-item__header __right">：</span>
                        <span class="__left">{{
                          $_out($_getCodeValue('PROFESSION','1',customerInfo.uaiOccupation))
                        }}</span>
                      </div>
                    </td>
                    <td>&nbsp;</td>
                    <td colspan="3"
                        class="info-item__wrapper"
                    >
                      <div
                        class="info_xxxl"
                      >
                        <span class="info-item__header __left">勤務先TEL</span>
                        <span class="info-item__header __right">：</span>
                        <span class="__left">{{ $_out(customerInfo.uaiOfficePhoneNumber) }}</span>
                      </div>
                    </td>
                  </tr>
                </template>
                <tr>
                  <td class="info-item__wrapper">
                    <div class="info_xxl">
                      <span class="info-item__header __left">手数料プラン</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.commission)
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td colspan="3"
                      class="info-item__wrapper"
                  >
                    <div class="info_xxxl">
                      <span class="info-item__header __left">口座開設日</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out($_getFormattedDateValue(customerInfo.uaiOpenAcctDate))
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td colspan="3"
                      class="info-item__wrapper"
                  >
                    <div
                      class="info_xxxl"
                    >
                      <span class="info-item__header __left">閲覧可能部店</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.visibleButenCode)
                      }}</span>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td class="info-item__wrapper">
                    <div class="info_xxl">
                      <span class="info-item__header __left">ID/PW交付状況</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.idPwFlg)
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <template v-if="accountInfo.length > 0">
                    <td
                      colspan="3"
                      class="info-item__wrapper"
                      style="width: 40%; min-width: 414px"
                    >
                      <div class="info_xxxl">
                        <span class="info-item__header __left">口座情報</span>
                        <span class="info-item__header __right">：</span>
                        <span class="__left">
                          <span v-for="item in accountInfo"
                                :key="item"
                          >
                            <span v-if="item !== ' ' && item"
                                  :key="item"
                            >
                              <el-badge
                                type="success"
                                size="small"
                                :value="item"
                                class="account-info"
                                style="padding: 0.2rem 0.2rem 0 0"
                              ></el-badge>
                            </span>
                          </span>
                        </span>
                      </div>
                    </td>
                  </template>
                  <template v-else>
                    <td>&nbsp;</td>
                  </template>
                  <td>&nbsp;</td>
                  <!-- 個人で表示する項目 ジュニアNISA口座番号がNULLと'0000000'でない場合に項目表示 -->
                  <td
                    v-if="
                      customerInfo.corporationType === '0' &&
                        customerInfo.jrNisaAccountNumber != null &&
                        customerInfo.jrNisaAccountNumber != '' &&
                        customerInfo.jrNisaAccountNumber != '0000000'
                    "
                    colspan="3"
                    class="info-item__wrapper"
                  >
                    <div class="info_xxxl">
                      <span class="info-item__header __left">払出制限解除日</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out($_getFormattedDateValue(customerInfo.uaiJnisaSeigenShuryoYmd))
                      }}</span>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td class="info-item__wrapper">
                    <div class="info_xxl">
                      <span class="info-item__header __left">CR営業員名</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.empCrName)
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td colspan="3"
                      class="info-item__wrapper"
                  >
                    <div class="info_xxxl">
                      <span class="info-item__header __left">CS営業員名</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.empCsName)
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td
                    colspan="3"
                    class="info-item__wrapper"
                  >
                    <div
                      class="info_xxxl"
                    >
                      <span class="info-item__header __left">仲介業者名</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.brokerName)
                      }}</span>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td
                    class="info-item__wrapper"
                  >
                    <div class="info_xxl">
                      <span class="info-item__header __left">支店名</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.brokerBranchName)
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td
                    colspan="3"
                    class="info-item__wrapper"
                  >
                    <div class="info_xxxl">
                      <span class="info-item__header __left">営業員名</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.brokerChargeName)
                      }}</span>
                    </div>
                  </td>
                  <td>&nbsp;</td>
                  <td
                    colspan="3"
                    class="info-item__wrapper"
                  >
                    <div
                      class="info_xxxl"
                    >
                      <span class="info-item__header __left">特記事項</span>
                      <span class="info-item__header __right">：</span>
                      <span class="__left">{{
                        $_out(customerInfo.ccsMemo)
                      }}</span>
                    </div>
                  </td>
                </tr>
              </table>
              <div class="memo-info">
                <span>メモ(IFA専用)</span>
                <span
                  style="padding-left: 1rem;"
                >更新日時: {{ $_out($_getFormattedDateTimeValue(form.updateTime)) }}</span>
                <template v-if="!editMode && $store.getters.userAccount && $store.getters.userAccount.medUsers.privId === '9'">
                  <el-icon
                    class="edit-icon"
                    @click="memoIfaEditStartA010"
                  ><EditPen></EditPen></el-icon>
                </template>
                <template v-if="editMode">
                  <el-icon
                    class="save-icon"
                    @click="memoIFAUpdateA011"
                  ><Check></Check></el-icon>
                  <el-icon
                    class="reset-icon"
                    @click="memoIfaEditCancelA012"
                  ><Close></Close></el-icon>
                </template>
              </div>
              <div>
                <el-form
                  ref="form"
                  :model="form"
                >
                  <ifa-input-text
                    id="ifaMemoContent"
                    v-model="form.ifaMemoContent"
                    name="customerMemo"
                    type="textarea"
                    :rows="4"
                    placeholder="顧客メモ情報を入力してください｡"
                    maxlength="750"
                    show-word-limit
                    resize="none"
                    prop="ifaMemoContent"
                    original-screen-id="SUB0202_00-02"
                    :readonly="!editMode"
                    :style="memoTextStyles"
                    :domain="IfaText750DomainModel"
                    @dblclick="memoIfaEditStartA010"
                  ></ifa-input-text>
                </el-form>
              </div>
            </div>
          </div>
        </el-collapse-item>
      </el-collapse>
    </el-card>

    <!--リアル委託保証金率（国内）-->
    <ifa-real-entrust-deposit-rate-domestic
      :is-visible="realMarginDepositRateDomesticisVisible"
      :real-entrust-deposit-rate-form-model="realEntrustDepositRateDomesticFormModel"
      @close-modal="handleCloseModalrealMarginDepositRateDomestic"
    >
    </ifa-real-entrust-deposit-rate-domestic>

    <!--リアル委託保証金率（米株）-->
    <ifa-real-entrust-deposit-rate
      :is-visible="realMarginDepositRateisVisible"
      :real-entrust-deposit-rate-form-model="realEntrustDepositRateFormModel"
      @close-modal="handleCloseModalrealMarginDepositRate"
    >
    </ifa-real-entrust-deposit-rate>

    <ifa-requester
      id="memoIFAUpdateA011"
      action-id="SUB0202_00-02#A011"
      action-type="requestAction"
      msg-title="顧客ポータル_顧客情報"
      :request-model="IfaCustomerPortalA011RequestModel"
      @response-handler="responseHandlerMemoIFAUpdateA011($event)"
    ></ifa-requester>

    <ifa-requester
      id="ifaCustomerPortalRealTimeMarginRateJpyAmountCurrencyLinkA006"
      action-id="SUB0202_010302-03#X001"
      action-type="requestAction"
      :request-model="IfaRealEntrustDepositRateDomesticX001RequestModel"
      @response-handler="responseHandlerA006"
    ></ifa-requester>

    <ifa-requester
      id="ifaCustomerPortalRealTimeMarginRateForeignCurrencyLinkA009"
      action-id="SUB0202_010304-02#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA009"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaText750DomainModel from '@/resource/domain/IfaText750DomainModel.json'
import { IfaCustomerPortalA011RequestModel } from './js/IfaCustomerPortalA011RequestModel.js'
import { IfaCustomerPortalFormModel } from './js/IfaCustomerPortalFormModel.js'
import IfaRealEntrustDepositRate from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/IfaRealEntrustDepositRate.vue'
import IfaRealEntrustDepositRateDomestic from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/IfaRealEntrustDepositRateDomestic.vue'
import { IfaRealEntrustDepositRateFormModel } from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/js/IfaRealEntrustDepositRateFormModel.js'
import { IfaRealEntrustDepositRateDomesticFormModel } from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/js/IfaRealEntrustDepositRateDomesticFormModel.js'
import { IfaRealEntrustDepositRateDomesticX001RequestModel } from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/js/IfaRealEntrustDepositRateDomesticX001RequestModel.js'

export default {
  components: {
    IfaNoticeInfo,
    IfaRealEntrustDepositRate,
    IfaRealEntrustDepositRateDomestic
  },
  props: {
    brokerChargeListCount: { type: Number, required: true },
    growthInvestmentLimitYear: { type: String, required: true },
    accumulateInvestmentLimitYear: { type: String, required: true },
    maintenanceRateJpyAmountDescriptionMessage: { type: String, required: true }
  },
  data() {
    return {
      customerInfoActiveName: [],
      editMode: false,
      realMarginDepositRateisVisible: false,
      realMarginDepositRateDomesticisVisible: false,
      IfaText750DomainModel: IfaText750DomainModel,
      beforeEditParam: {
        updateTime: '',
        ifaMemoContent: ''
      },
      form: new IfaCustomerPortalFormModel(),
      realEntrustDepositRateFormModel: new IfaRealEntrustDepositRateFormModel(),
      realEntrustDepositRateDomesticFormModel: new IfaRealEntrustDepositRateDomesticFormModel()
    }
  },
  computed: {
    IfaCustomerPortalA011RequestModel() { return new IfaCustomerPortalA011RequestModel(this.form) },
    IfaRealEntrustDepositRateDomesticX001RequestModel() {
      return new IfaRealEntrustDepositRateDomesticX001RequestModel(
        {
          butenCode: this.customerInfo.butenCode,
          accountNumber: this.customerInfo.accountNumber
        }
      )
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    newBuildingCapacity() {
      let newBuildingCapacity = this.customerInfo.newBuildingCapacity
      newBuildingCapacity = +newBuildingCapacity < 0 ? '0' : newBuildingCapacity
      return this.$_out(this.$_withCommaInteger(newBuildingCapacity))
    },
    foreignNewBuildingCapacity() {
      let foreignNewBuildingCapacity = this.customerInfo.foreignNewBuildingCapacity
      foreignNewBuildingCapacity = +foreignNewBuildingCapacity < 0 ? '0' : foreignNewBuildingCapacity
      return this.$_out(this.$_withCommaZeroPadding(foreignNewBuildingCapacity, 2))
    },
    memoTextStyles() {
      return {
        '--memo-text-color': this.editMode ? 'black' : 'red'
      }
    },
    foreignBuyPower() {
      // 買付余力（外貨）の表示内容を制御

      // 買付余力(外貨)有無
      let str = this.$_getCodeValue('FOREIGN_BUY_POWER_FLAG', 1, this.customerInfo.foreignBuyPowerFlag)

      // 買付余力(外貨)あり国数
      // 買付余力(外貨)有無が「あり」の場合に表示
      if (Number(this.customerInfo.foreignBuyPowerFlag) === 1) {
        str += `(${this.customerInfo.foreignBuyingPowerCountryCount}カ国)`
      }
      return str
    },
    accountInfo() {
      // 口座情報エリアの表示内容を制御
      const ai = []

      // 信用口座区分(国内)
      ai.push(this.$_getCodeValue('DOMESTIC_MARGIN_ACCOUNT_TYPE', 2, this.customerInfo.domesticMarginAccountType))

      // 外国株式取引口座開設状況が未開設かつ信用口座区分(外国)が現物口座の場合のみ表示する項目
      if (this.customerInfo.foreignStockTradeAccountOpenStatus === '0' && this.customerInfo.foreignMarginAccountType === ' ') {
        // 外貨建商品取引口座開設状況
        ai.push(this.$_getCodeValue('FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS',
          2, this.customerInfo.foreignSecurityTradeAccountOpenStatus))
      }

      // 外国株式取引口座開設状況が開設済かつ信用口座区分(外国)が現物口座の場合のみ表示する項目
      if (this.customerInfo.foreignStockTradeAccountOpenStatus === '1' && this.customerInfo.foreignMarginAccountType === ' ') {
        // 外国株式取引口座開設状況
        ai.push(this.$_getCodeValue('FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS',
          2, this.customerInfo.foreignStockTradeAccountOpenStatus))
      }

      // 信用口座区分(外国)
      ai.push(this.$_getCodeValue('FOREIGN_MARGIN_ACCOUNT_TYPE', 2, this.customerInfo.foreignMarginAccountType))

      // 特定口座区分
      ai.push(this.$_getCodeValue('SPECIFIC_ACCOUNT', 2, this.customerInfo.specificAccountType))

      // 配受無区分
      ai.push(this.$_getCodeValue('DIVIDEND_RECEIPT_TYPE', 2, this.customerInfo.dividendReceiptType))

      // マル優適格者区分
      ai.push(this.$_getCodeValue('TAX_EXEMPT_QUALIFIED_PERSON_TYPE', 2, this.customerInfo.taxExemptQualifiedPersonType))

      // NISA区分（当年）
      ai.push(this.$_getCodeValue('NISA_TYPE', 2, this.customerInfo.nisaType))

      // 配列からヌル文字列を削除して返す
      return ai.filter(item => item && item.length > 0)
    },
    realTimeEntrustDepositRateUrlId() {
      // リアルタイム委託保証金率リンク(円貨)のuriId
      switch (this.customerInfo.butenCode.slice(0, 2)) {
        case 'Z1':
        case 'Z2':
          return 13
        case 'Z3':
        case 'Z4':
          return 14
        case 'Z5':
        case 'Z6':
          return 15
        default:
          return 0
      }
    },
    realTimeEntrustDepositRateObject() {
      // リアルタイム委託保証金率リンク(円貨)のパラメータオブジェクト
      return {
        // eslint-disable-next-line camelcase
        _TENGUN_ID: this.customerInfo.butenCode.slice(0, 2)
      }
    },
    urlLink() {
      // 店群によるURLの切り替える
      switch (this.customerInfo.tenGunId) {
        case 'Z01':
        case 'Z02':
          return 201
        case 'Z03':
        case 'Z04':
          return 202
        case 'Z05':
        case 'Z06':
          return 203
        case 'Z07':
        case 'Z08':
          return 204
        default:
          return 0
      }
    },
    blinkComplianceLank(){
      return ['A', 'B', 'C', 'F', 'G', 'Z'].includes(this.customerInfo.complianceLank)
    }
  },
  methods: {
    onShow() {
      // 画面の初期表示を行う
      this.form.updateTime = this.customerInfo.ifaMemoUpdateDateTime
      this.form.ifaMemoContent = this.customerInfo.ifaMemoContent
      this.$refs['ifaLinkMyAssset'].trigger()
      this.$refs['ifalinkUsername'].trigger()
    },
    realTimeMarginRateForeignCurrencyLinkA009() {
      this.$nextTick(() => {
        document.getElementById('ifaCustomerPortalRealTimeMarginRateForeignCurrencyLinkA009').click()
      })
    },
    realTimeMarginRateJpyAmountCurrencyLinkA006() {
      this.$nextTick(() => {
        document.getElementById('ifaCustomerPortalRealTimeMarginRateJpyAmountCurrencyLinkA006').click()
      })
    },
    responseHandlerA006(response) {
      this.realEntrustDepositRateDomesticFormModel = Object.assign(this.realEntrustDepositRateDomesticFormModel, response.dataList[0])
      // リアル委託保証金率（国内）画面を開く
      this.realMarginDepositRateDomesticisVisible = true
    },
    responseHandlerA009(response) {
      this.realEntrustDepositRateFormModel = Object.assign(this.realEntrustDepositRateFormModel, response.dataList[0])
      // リアル委託保証金率（米株）画面を開く
      this.realMarginDepositRateisVisible = true
    },
    memoIfaEditStartA010() {
      if (this.$store.getters.userAccount && this.$store.getters.userAccount.medUsers.privId === '9') {
        // メモ(IFA専用)欄の項目を制御する。
        this.editMode = true
        this.$nextTick(() => {
          document.getElementsByName('customerMemo')[0].focus()
        })
        // 編集前の状態をセット
        this.beforeEditParam.updateTime = this.form.updateTime
        this.beforeEditParam.ifaMemoContent = this.form.ifaMemoContent
      } else {
        return
      }
    },
    memoIFAUpdateA011() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          document.getElementById('memoIFAUpdateA011').click()
        } else {
          return false
        }
      })
    },
    responseHandlerMemoIFAUpdateA011(data) {
      // 画面のメモ(IFA専用)エリアを更新し、表示/非表示、活性/非活性は初期状態にする。
      this.beforeEditParam.updateTime = ''
      this.beforeEditParam.ifaMemoContent = ''
      this.form.updateTime = data.dataList[0].ifaMemoUpdateDateTime
      this.form.ifaMemoContent = data.dataList[0].ifaMemoContent
      this.editMode = false
    },
    memoIfaEditCancelA012() {
      // メモ(IFA専用)欄の項目を制御する。
      // メモ(IFA専用)内容欄：入力内容を編集前の状態に戻し、非活性状態にして編集不可とする
      this.$refs.form.clearValidate()
      this.form.updateTime = this.beforeEditParam.updateTime
      this.form.ifaMemoContent = this.beforeEditParam.ifaMemoContent
      this.editMode = false
    },
    handleCloseModalrealMarginDepositRate() {
      // リアル委託保証金率（米株）画面を閉じる
      this.realMarginDepositRateisVisible = false
    },
    handleCloseModalrealMarginDepositRateDomestic() {
      // リアル委託保証金率（国内）画面を閉じる
      this.realMarginDepositRateDomesticisVisible = false
    },
    collapse(state) {
      // アコーディオンの開閉を制御している
      if (state) {
        this.customerInfoActiveName = []
        this.state = false
      } else {
        this.customerInfoActiveName = ['customer-info']
        this.state = true
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/variables.scss";
:deep(.el-form-item__content) {
  display: block;
}
:deep(.external-link) {
  padding: 0;
}
.data-icon :deep(.external-link-icon) {
  vertical-align: baseline;
}
.userNameIcon :deep(.external-link-icon) {
  vertical-align: middle;
}
:deep(.el-collapse-item__header) {
  visibility: hidden;
  height: 0px;
}
:deep(.el-collapse-item__wrap) {
  line-height: 24px;
  background-color: $mainBackGroundColor;
  height: auto;
  margin-right: 21px;
}
:deep(.el-collapse-item__content) {
  line-height: 24px;
  background-color: $mainBackGroundColor;
  height: auto;
}
.customer-info__wrapper {
  padding: 0.2rem 0.25rem 0 0.25rem;
}
.customer-info {
  width: 100%;
  .customer-info-left {
    float: left;
    width: 2rem;
    height: 100%;
  }
  .customer-info-right {
    float: right;
    width: calc(100% - 3.5rem);
    height: auto;
    margin-right: 1rem;
    margin-left: 0.5rem;
  }
  .custom-icon-style {
    font-size: 2rem;
    color: $subTextColor;
}
}
.user-name__wrapper {
  color: $subTextColor;
  padding-top: 0.3rem;
  font-size: 16px;
  line-height: 16px;
  text-align: left;
}
.user-name {
  font-weight: 700;
}
.user-name-kana {
  padding-left: 0.5rem;
}
.reset_style {
  table {
    border-collapse: initial;
    border-spacing: initial;
    border-width: initial;
    border-color: initial;
  }
  tr {
    border-bottom: initial;
  }
  td {
    vertical-align: initial;
  }
}
.data-icon {
  border: 1px solid $subTextColor;
  border-radius: 50%;
  margin-left: 3px;
}
@keyframes poyopoyo {
  0%, 40%, 60%, 80% {
    transform: scale(1.0);
  }
  50%, 70% {
    transform: scale(0.80);
  }
}
.info_s {
  display: grid;
  width: 100%;
  grid-template-columns: 28px 12px 1fr;
}
.info_m {
  display: grid;
  width: 100%;
  grid-template-columns: 56px 12px 1fr;
}
.info_l {
  display: grid;
  width: 100%;
  grid-template-columns: 87px 12px 1fr;
}
.info_xl {
  display: grid;
  width: 100%;
  grid-template-columns: 94px 12px 1fr;
}
.info_xxl {
  display: grid;
  width: 100%;
  grid-template-columns: 97px 12px 1fr;
}
.info_xxxl {
  display: grid;
  width: 100%;
  grid-template-columns: 101px 12px 1fr;
}
.info_nisa {
  display: grid;
  width: 100%;
  grid-template-columns: 130px 12px 1fr;
}
.info-item__wrapper {
  resize: none;
  border: none;
  color: $subTextColor;
  font-size: 14px;
  line-height: 25px;
  padding: 0.3rem 0.3rem 0 0.3rem;
  border-bottom: groove;
}
.info-item__ifa-text {
  color: $subTextColor;
  font-size: 14px;
  line-height: 25px;
}
.info-item__header {
  font-weight: 700;
}
.space_width {
  width: 1.5%;
  min-width: 15px;
}
.info_width {
  width: 20%;
  min-width: 223px;
}
.clickable {
  color: $mainBlueColor;
  cursor: pointer;
  text-decoration: underline;
  text-decoration-skip-ink: none;
  &:hover {
    background: rgba(0, 0, 0, .066)
  }
}
.clickable-icon {
  font-size: 18px;
  color: $mainBlueColor;
  cursor: pointer;
  &:hover {
    background: rgba(0, 0, 0, .066)
  }
}
.memo-info {
  color: $subTextColor;
  font-size: 14px;
  height: 25px;
  line-height: 25px;
  margin-left: 1rem;
  margin-right: 20px;
  margin-top: 0.5rem;
}
.edit-icon {
  height: 24px;
  width: 24px;
  padding-top: 2px;
  padding-left: 2px;
  font-size: 18px;
  color: white;
  background-color: $subBlueColor;
  border: 1px solid $subTextColor;
  border-radius: 20%;
  margin-left: 1rem;;
}
.save-icon {
  height: 24px;
  width: 24px;
  padding-top: 2px;
  padding-left: 2px;
  font-size: 18px;
  color: white;
  background-color: $sellOrMinusColor;
  border: 1px solid $subTextColor;
  border-radius: 20%;
  margin-left: 1rem;;
}
.reset-icon {
  height: 24px;
  width: 24px;
  padding-top: 2px;
  padding-left: 2px;
  font-size: 18px;
  color: white;
  background-color: $buyOrPlusColor;
  border: 1px solid $subTextColor;
  border-radius: 20%;
  margin-left: 1rem;;
}
:deep(.el-textarea__inner) {
  color: var(--memo-text-color);
}
.account-info :deep(.el-badge__content) {
  background-color:#00847f
}
.__nowrap {
  white-space: nowrap
}
.__word-break {
  word-break: break-all;
}
.retract {
  background-color: #f5f6f7;
  border-style: none;
  position: relative;
  top: 0.875rem;
  cursor: pointer;
}
.retractIcon {
  position: relative;
  top: 0.125rem;
}
.compliance-lank-attention {
  color: #E5004C;
  font-weight: bold;
  animation: compliance-lank-opacity 1.0s ease-in-out infinite;
}
@keyframes compliance-lank-opacity {
  0%, 100% { opacity: 1; }
  30% { opacity: 0; }
}
</style>
