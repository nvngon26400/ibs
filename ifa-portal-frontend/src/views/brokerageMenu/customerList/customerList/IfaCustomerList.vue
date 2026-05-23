<template>
  <!-- 画面名：顧客一覧 -->
  <div>
    <screen-title :text="form.screenTitle"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <el-card
        class="content-card"
        shadow="always"
      >
        <div class="filter-container">
          <el-row>
            <el-col
              :span="23"
            >
              <ifa-common-search
                ref="commonSearchItem"
                :form="form"
                original-screen-id="SUB0201_01-01"
                display-pattern="pt1"
                list-pattern="pt2"
                :broker-code-validate="false"
                :emp-code-validate="false"
                :course-validate="true"
                :add-internet-to-courses="'on'"
                @mediate-user-privacy="mediateUserPrivacy"
              ></ifa-common-search>
            </el-col>
            <el-col
              :span="1"
            >
              <ifa-balloon-icon
                :show-message="isInfoMessage"
                icon-type="info"
                :message="form.ifaCustomerListComment[0]"
              >
              </ifa-balloon-icon>
            </el-col>
          </el-row>
        </div>
        <!-- Collapseタグ -->
        <el-collapse v-model="activeAttribute">
          <el-collapse-item>
            <template #title>
              <el-icon
                v-if="activeAttribute == 1"
                style="font-size:20px;"
              ><CirclePlus></CirclePlus></el-icon>
              <el-icon
                v-else
                style="font-size:20px;"
              ><Remove></Remove></el-icon>
              <span style="padding-left:10px;">属性</span>
            </template>
            <div
              class="filter-container"
              style="margin-left:28px; margin-bottom: 20px;"
            >
              <!-- ここにボタンより下段の検索条件用のコンテンツを記述する -->
              <el-row class="form_area_37">
                <el-col
                  :span="12"
                  class="adjust_position adjust_form_margin"
                >
                  <label
                    class="left-label"
                    style="margin-left: 20px;"
                  >Cランク</label>
                  <ifa-input-text
                    id="complianceLankFrom"
                    v-model="form.complianceLankFrom"
                    prop="complianceLankFrom"
                    size="small"
                    input-class="short_input"
                    label-class="not_margin_right"
                    :domain="IfaCRankDomainModel"
                  >
                  </ifa-input-text>
                  <span class="custom_space">～</span>
                  <ifa-input-text
                    id="complianceLankTo"
                    v-model="form.complianceLankTo"
                    prop="complianceLankTo"
                    size="small"
                    input-class="short_input"
                    :domain="IfaCRankDomainModel"
                  >
                  </ifa-input-text>
                </el-col>
                <el-col
                  :span="12"
                  class="adjust_position"
                >
                  <el-checkbox
                    v-model="form.tradeRestrictionCheckbox"
                    class="checkmark"
                  ></el-checkbox>
                  <label class="right-label">取引制限あり</label>
                </el-col>
              </el-row>

              <el-row
                class="form_area_37"
              >
                <el-col
                  :span="12"
                  class="adjust_position adjust_form_margin"
                >
                  <el-checkbox
                    v-model="form.adressDisplay"
                    class="checkmark"
                  ></el-checkbox>
                  <label class="left-label">住所</label>
                  <ifa-input-text
                    id="adress"
                    v-model="form.adress"
                    prop="adress"
                    original-screen-id="SUB0201_01-01"
                    size="small"
                    maxlength="172"
                    style="width: 220px;"
                    :domain="IfaAddressKanjiDomainModel"
                  >
                    <template #tail>
                      <el-select
                        v-model="form.adressConditionList"
                        size="small"
                        style="width: 110px;"
                      >
                        <el-option
                          v-for="item in searchOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                        ></el-option>
                      </el-select>
                    </template>
                  </ifa-input-text>
                </el-col>
                <el-col
                  :span="12"
                  class="adjust_position adjust_form_margin"
                >
                  <el-checkbox
                    v-model="form.phoneNumberDisplay"
                    class="checkmark"
                  ></el-checkbox>
                  <label class="right-label">電話番号</label>
                  <ifa-input-text
                    id="customerListPhoneNumber"
                    v-model="form.customerListPhoneNumber"
                    prop="customerListPhoneNumber"
                    input-class="search-form__item"
                    size="small"
                    style="margin-left: -0.01rem;"
                    maxlength="12"
                    minlength="12"
                    :domain="IfaPhoneNumber12DomainModel"
                  >
                  </ifa-input-text>
                </el-col>
              </el-row>

              <el-row
                class="form_area_37"
              >
                <el-col
                  :span="12"
                  class="adjust_position adjust_form_margin"
                >
                  <el-checkbox
                    v-model="form.ageDisplay"
                    class="checkmark"
                  ></el-checkbox>
                  <label class="left-label">年齢</label>
                  <ifa-input-text
                    id="ageFrom"
                    v-model="form.ageFrom"
                    prop="ageFrom"
                    size="small"
                    input-class="short_input"
                    label-class="not_margin_right"
                    :domain="IfaAgeDomainModel"
                  >
                  </ifa-input-text>
                  <span class="custom_space">～</span>
                  <ifa-input-text
                    id="ageTo"
                    v-model="form.ageTo"
                    prop="ageTo"
                    size="small"
                    input-class="short_input"
                    label-class="not_margin_right"
                    :domain="IfaAgeDomainModel"
                  >
                  </ifa-input-text>
                  <span class="custom_space">歳</span>
                </el-col>
                <el-col
                  :span="12"
                  class="adjust_position adjust_form_margin_calender"
                >
                  <el-checkbox
                    v-model="form.BirthDateDisplay"
                    class="checkmark"
                    style="margin-bottom: 10px;"
                  ></el-checkbox>
                  <label
                    class="right-label"
                    style="margin-bottom: 10px;"
                  >生年月日</label>
                  <ifa-date-range-picker
                    v-model="form.BirthDate"
                    size="small"
                    prop="BirthDate"
                    unlink-panels
                  ></ifa-date-range-picker>
                </el-col>
              </el-row>
              <el-row>
                <el-col
                  :span="23"
                >
                  <label
                    class="left-label"
                    style="margin-left: 20px; width: 102px"
                  >個人/法人区分</label>
                  <el-radio-group
                    v-model="form.personalCorporation"
                    @change="changePersonalCorporation"
                  >
                    <el-radio
                      :label="2"
                    >全て</el-radio>
                    <el-radio
                      :label="0"
                    >個人</el-radio>
                    <el-radio
                      :label="1"
                    >法人</el-radio>
                  </el-radio-group>
                </el-col>
                <el-col
                  :span="1"
                >
                  <ifa-balloon-icon-hide
                    :show-message="isInfoMessage"
                    icon-type="info"
                  >
                    <div v-html="form.ifaCustomerListComment[1]">
                    </div>
                  </ifa-balloon-icon-hide>
                </el-col>
              </el-row>
              <el-row
                class="form_area"
                style="margin-top: 10px; margin-bottom: 0;"
              >
                <el-col
                  :span="23"
                  style="display: flex; align-items: start; flex-wrap: wrap;"
                >
                  <div class="adjust_select">
                    <label
                      class="left-label"
                      style="margin-left: 20px;"
                    >投資の方針</label>
                    <personal-corporation-select
                      ref="investmentPlan"
                      :disabled="form.personalCorporation==2"
                      :list-kind="(form.personalCorporation==0) ? 'pt1' : 'pt2'"
                      id-string="investmentPlanSelect"
                      required
                      style="max-width:none;"
                      @change-selected="form.investmentPlan = $event"
                    ></personal-corporation-select>
                  </div>
                  <div class="adjust_select">
                    <label
                      class="left-label"
                      style="margin-left: 20px;"
                    >資金の性格</label>
                    <personal-corporation-select
                      ref="fundCharacter"
                      :disabled="form.personalCorporation==2"
                      :list-kind="(form.personalCorporation==0) ? 'pt3' : 'pt4'"
                      id-string="fundCharacterSelect"
                      required
                      style="max-width:none;"
                      @change-selected="form.fundCharacter = $event"
                    ></personal-corporation-select>
                  </div>
                  <div class="adjust_select">
                    <label
                      class="left-label"
                      style="margin-left: 20px;"
                    >主な収入源</label>
                    <personal-corporation-select
                      ref="incomeForm"
                      :disabled="form.personalCorporation!==0"
                      :list-kind="'pt5'"
                      id-string="incomeFormSelect"
                      required
                      style="max-width:none;"
                      @change-selected="form.incomeForm = $event"
                    ></personal-corporation-select>
                  </div>
                  <div class="adjust_select">
                    <label
                      class="left-label"
                      style="margin-left: 20px;"
                    >資産運用期間</label>
                    <personal-corporation-select
                      ref="employmentPeriod"
                      :disabled="form.personalCorporation==2"
                      :list-kind="'pt6'"
                      id-string="employmentPeriodSelect"
                      required
                      style="max-width:none;"
                      @change-selected="form.employmentPeriod = $event"
                    ></personal-corporation-select>
                  </div>
                </el-col>
              </el-row>
              <el-row
                class="form_area"
                style="margin-bottom: 0;"
              >
                <el-col
                  :span="23"
                  style="display: flex; align-items: start; flex-wrap: wrap;"
                >
                  <div class="adjust_select">
                    <label
                      class="left-label"
                      style="margin-left: 20px;"
                    >年収</label>
                    <personal-corporation-select
                      ref="annualIncome"
                      :disabled="form.personalCorporation!==0"
                      :list-kind="'pt7'"
                      id-string="annualIncomeSelect"
                      required
                      style="max-width:none;"
                      @change-selected="form.annualIncome = $event"
                    ></personal-corporation-select>
                  </div>
                  <div class="adjust_select">
                    <label
                      class="left-label"
                      style="margin-left: 20px;"
                    >金融資産</label>
                    <personal-corporation-select
                      ref="financialAssets"
                      :disabled="form.personalCorporation!==0"
                      :list-kind="'pt8'"
                      id-string="financialAssetsSelect"
                      required
                      style="max-width:none;"
                      @change-selected="form.financialAssets = $event"
                    ></personal-corporation-select>
                  </div>
                  <div class="adjust_select">
                    <label
                      class="left-label"
                      style="margin-left: 20px;"
                    >職業</label>
                    <personal-corporation-select
                      ref="occupation"
                      :disabled="form.personalCorporation==2"
                      :list-kind="(form.personalCorporation==0) ? 'pt9' : 'pt10'"
                      id-string="occupationSelect"
                      required
                      style="max-width:none;"
                      @change-selected="form.occupation = $event"
                    ></personal-corporation-select>
                  </div>
                  <div class="adjust_select">
                    <span style="width: 128px; display: inline-block; text-align: right;">
                      <el-checkbox
                        v-model="form.investmentExpDisplay"
                        class="checkmark"
                        style="margin-left: 12px; position: absolute;"
                        :disabled="form.personalCorporation==2"
                      ></el-checkbox>
                      <label
                        class="left-label"
                      >投資経験</label>
                    </span>
                    <personal-corporation-select
                      ref="investmentExp"
                      :disabled="form.personalCorporation==2"
                      :list-kind="'pt11'"
                      id-string="investmentExpSelect"
                      required
                      style="max-width:none;"
                      @change-selected="form.investmentExp = $event"
                    ></personal-corporation-select>
                  </div>
                </el-col>
              </el-row>
              <el-row
                class="form_area"
              >
                <el-col
                  :span="23"
                ></el-col>
                <el-col
                  :span="1"
                  style="margin-top: -45px;margin-left: -4px;"
                >
                  <ifa-balloon-icon-hide
                    :show-message="isInfoMessage"
                    icon-type="info"
                  >
                    <div v-html="form.ifaCustomerListComment[2]">
                    </div>
                  </ifa-balloon-icon-hide>
                </el-col>
              </el-row>
              <el-row class="form_area_37">
                <el-col
                  :span="12"
                  class="adjust_position adjust_form_margin_calender"
                >
                  <el-checkbox
                    v-model="form.openAccountDisplay"
                    class="checkmark"
                    style="margin-bottom: 10px;"
                  ></el-checkbox>
                  <label class="left-label"
                         style="margin-bottom: 10px;"
                  >口座開設日</label>
                  <ifa-date-range-picker
                    v-model="form.openAccount"
                    size="small"
                    prop="openAccount"
                  ></ifa-date-range-picker>
                </el-col>
                <el-col
                  :span="12"
                  class="adjust_position"
                >
                  <el-checkbox
                    v-model="form.foreignSecurityAccountDisplay"
                    class="checkmark"
                  ></el-checkbox>
                  <label class="right-label">外国証券取引口座</label>
                  <el-select
                    v-model="form.foreignSecurityAccountName"
                    size="small"
                    style="width: 180px; margin-right: 32px;"
                  >
                    <el-option
                      v-for="item in foreignAccountOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    ></el-option>
                  </el-select>
                </el-col>
              </el-row>

              <el-row class="form_area_37">
                <el-col
                  :span="6"
                  class="adjust_label"
                  style="display: flex; align-items: start; padding-top: 5px;"
                >
                  <el-checkbox
                    v-model="form.viewAblrButenDisplay"
                    class="checkmark"
                    style="padding-bottom: 3px;"
                  ></el-checkbox>
                  <el-tooltip
                    :disabled="form.butenCodeArray.length < 1"
                    :content="form.butenCodeArray"
                    placement="bottom"
                    effect="light"
                  >
                    <ifa-input-text
                      id="butenCode"
                      v-model="form.butenCodeArray"
                      prop="butenCodeArray"
                      class="search-form__item middle_input"
                      size="small"
                      label="閲覧可能部店"
                      :domain="IfaButenCodeArrayDomainModel"
                      style="margin-left: -0.01rem;"
                      @change="addCommaVisibleButenCode"
                    >
                    </ifa-input-text>
                  </el-tooltip>
                </el-col>
                <el-col
                  :span="6"
                  class="adjust_position"
                  style="margin-left: -5px;"
                >
                  <el-checkbox
                    v-model="form.nisaContractKbnDisplay"
                    class="checkmark"
                  ></el-checkbox>
                  <label
                    class="left-label"
                    style="margin-left: -25px;"
                  >NISA口座</label>
                  <el-select
                    v-model="form.nisaContractKbnName"
                    size="small"
                    style="width: 180px; margin-right: 32px;"
                  >
                    <el-option
                      v-for="item in nisaContractKbnOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    ></el-option>
                  </el-select>
                </el-col>
                <el-col
                  :span="12"
                  class="adjust_position adjust_form_margin_calender"
                  style="margin-left: 5px;"
                >
                  <el-checkbox
                    v-model="form.courseChangeFinishDateDisplay"
                    class="checkmark"
                    style="margin-bottom: 10px;"
                  ></el-checkbox>
                  <label class="right-label"
                         style="margin-bottom: 10px;"
                  >コース変更完了日</label>
                  <ifa-date-range-picker
                    v-model="form.courseChangeFinishDate"
                    size="small"
                    prop="courseChangeFinishDate"
                  ></ifa-date-range-picker>
                </el-col>
              </el-row>
            </div>
          </el-collapse-item>
        </el-collapse>

        <el-collapse v-model="activeBalanceTransaction">
          <el-collapse-item>
            <template #title>
              <el-icon
                v-if="activeBalanceTransaction == 1"
                style="font-size:20px;"
              ><CirclePlus></CirclePlus></el-icon>
              <el-icon
                v-else
                style="font-size:20px;"
              ><Remove></Remove></el-icon>
              <span style="padding-left:10px;">残高・取引</span>
            </template>
            <div
              class="filter-container"
              style="margin-left:28px;"
            >
              <!-- ここにボタンより下段の検索条件用のコンテンツを記述する -->
              <el-row class="form_area_37">
                <el-col
                  :span="24"
                  class="adjust_form_margin price_input"
                  style="display: flex; align-items: start;"
                >
                  <label
                    class="left-label_top"
                    style="margin-left: 20px; margin-top: 9px;"
                  >評価額</label>
                  <ifa-input-price
                    v-model="form.valuationFrom"
                    size="small"
                    label-class="not_margin_right"
                    input-class="ifa-input_base__wrapper"
                    prop="valuationFrom"
                    style="margin-right: 0;"
                    :min="'-999999999999999999'"
                    :max="'9999999999999999999'"
                    :digit="0"
                    :domain="IfaCurrency190DomainModel"
                  ></ifa-input-price>
                  <span class="custom_space">～</span>
                  <ifa-input-price
                    v-model="form.valuationTo"
                    size="small"
                    prop="valuationTo"
                    :min="'-999999999999999999'"
                    :max="'9999999999999999999'"
                    :digit="0"
                    :step="1"
                    :domain="IfaCurrency190DomainModel"
                  ></ifa-input-price>
                  <div style="margin-top: 1.3px;">
                    <el-select
                      v-model="form.valuationConditionList"
                      size="small"
                      style="width: 270px;"
                    >
                      <el-option
                        v-for="item in assessedValueOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                      ></el-option>
                    </el-select>
                  </div>
                </el-col>
              </el-row>

              <el-row class="form_area form_fee">
                <label
                  class="left-label_top"
                  style="margin-left: 20px; margin-top: 3px;"
                >手数料</label>
                <el-col
                  :span="20"
                  style="border: 1px solid black; padding-top: 20px;"
                >
                  <el-row class="form_area">
                    <el-col
                      :span="24"
                      class="adjust_position"
                    >
                      <label
                        class="left-label"
                      >累計期間</label>
                      <el-radio-group
                        v-model="form.commTotalList"
                        style="width: 490px;"
                      >
                        <el-radio
                          :label="1"
                        >全て</el-radio>
                        <el-radio
                          :label="2"
                        >当月</el-radio>
                        <el-radio
                          :label="3"
                        >前月</el-radio>
                        <el-radio
                          :label="4"
                        >前々月</el-radio>
                        <el-radio
                          :label="5"
                        >期間指定（年月）</el-radio>
                      </el-radio-group>
                      <span class="adjust_form_margin_calender">
                        <ifa-month-range-picker
                          v-model="form.commTotalPeriod"
                          :disabled="form.commTotalList!==5"
                          size="small"
                          prop="commTotalPeriod"
                        ></ifa-month-range-picker>
                      </span>
                    </el-col>
                  </el-row>
                  <el-row class="form_area">
                    <el-col
                      :span="24"
                      class="adjust_form_margin price_input"
                      style="display: flex; align-items: start;"
                    >
                      <label
                        class="left-label_top"
                        style="margin-top: 9px;"
                      >累計額</label>
                      <div style="width: 490px; display: inline-block">
                        <ifa-input-price
                          v-model="form.commTotalAmountFrom"
                          size="small"
                          label-class="not_margin_right"
                          prop="commTotalAmountFrom"
                          style="margin-right: 0;"
                          :min="'0'"
                          :max="'999999999999999999'"
                          :digit="0"
                          :domain="IfaCurrency180DomainModel"
                        ></ifa-input-price>
                        <span class="custom_space">～</span>
                        <ifa-input-price
                          v-model="form.commTotalAmountTo"
                          size="small"
                          label-class="not_margin_right"
                          prop="commTotalAmountTo"
                          :min="'0'"
                          :max="'999999999999999999'"
                          :digit="0"
                          :domain="IfaCurrency180DomainModel"
                        ></ifa-input-price>
                      </div>
                      <div style="margin-top: 1.3px;">
                        <el-select
                          v-model="form.commTotalAmountConditionList"
                          size="small"
                          style="width: 180px;"
                        >
                          <el-option
                            v-for="item in commissionExactOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                          ></el-option>
                        </el-select>
                      </div>
                    </el-col>
                  </el-row>
                </el-col>
              </el-row>
              <el-row
                class="form_area_37"
                style="margin-top: 20px;"
              >
                <el-col
                  :span="24"
                  class="adjust_position adjust_form_margin_calender"
                >
                  <el-checkbox
                    v-model="form.lastTradeDateDisplay"
                    class="checkmark"
                    style="margin-bottom: 10px;"
                  ></el-checkbox>
                  <label
                    class="left-label_top"
                    style="line-height: 35px; margin-bottom: 10px;"
                  >最終約定日</label>
                  <ifa-date-range-picker
                    v-model="form.lastTradeDate"
                    prop="lastTradeDate"
                    size="small"
                    unlink-panels
                  ></ifa-date-range-picker>
                </el-col>
              </el-row>
            </div>
          </el-collapse-item>
        </el-collapse>
        <el-row style="padding: 1rem 56px 0;">
          <ifa-button
            id="btnDisplay"
            name="btnDisplay"
            icon="Search"
            text="表示"
            width="90"
            small
            :request-model="IfaCustomerListA002RequestModel"
            :form="formRef"
            action-id="SUB0201_01-01#A002"
            action-type="requestAction"
            :pre-request-handler="addColumns1"
            @response-handler="responseHandlerDisplayA002($event)"
          ></ifa-button>
          <ifa-button
            id="btnTopInputClear"
            name="btnTopInputClear"
            text="クリア"
            width="90"
            color="white"
            small
            action-type="originalAction"
            @app-action-handler="clearA003"
          ></ifa-button>
        </el-row>
      </el-card>
      <el-row>
        <el-card class="content-card">
          <el-row>
            <div class="gridButtonArea">
              <ifa-button
                id="btmCustomerPortal"
                name="btmCustomerPortal"
                :disabled="activeBtn"
                text="顧客別メニュー"
                color="primary"
                small
                action-type="originalAction"
                @app-action-handler="customerMenuA004"
              ></ifa-button>
              <ifa-button
                id="btnCsvDownload"
                name="btnCsvDownload"
                class="btn-csv-donwload"
                text="CSV出力"
                width="90"
                color="primary"
                small
                :form="formRef"
                :disabled="csvbtn"
                :request-model="IfaCustomerListA005RequestModel"
                action-id="SUB0201_01-01#A005"
                action-type="outputCsvAction"
                csv-file-name="基本"
                :is-check-csv-download-allowed="true"
                :is-check-csv-download-privacy-confirmation="true"
                :pre-request-handler="preRequestHandlerA005"
                @response-handler="responseHandlerA005($event)"
                @response-error-handler="responseErrorHandlerA005($event)"
              ></ifa-button>
              <ifa-button
                id="btnBbApplication"
                text="BB申込"
                :disabled="activeBtn"
                small
                width="90"
                action-id="SUB0201_01-01#A006"
                action-type="requestAction"
                :request-model="IfaCustomerListA006RequestModel"
                :pre-request-handler="preRequestHandlerA006"
                @response-handler="bbApplicationA006($event)"
              ></ifa-button>
              <ifa-button
                id="btnImprintInquiry"
                text="印影書類"
                :disabled="activeInquiryBtn"
                small
                width="90"
                action-id="SUB0201_01-02#A001"
                action-type="requestAction"
                :request-model="IfaCustomerListA007RequestModel"
                @response-handler="sealInquiryA007($event)"
              ></ifa-button>
            </div>
          </el-row>
          <grid-table
            id="outputTable"
            ref="pqGrid"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>
    <!-- BB申込 -->
    <ifa-bb-apply-input
      ref="ifaBbApplyInput"
      :is-visible="bbApply"
      @close-modal="bbApply = false"
      @open-modal="bbApply = true"
    ></ifa-bb-apply-input>
    <ifa-imprint-inquiry
      ref="ifaImprintInquiry"
      :is-visible="imprintInquiry"
      @close-modal="imprintInquiry = false"
      @open-modal="imprintInquiry = true"
    ></ifa-imprint-inquiry>
  </div>
  <ifa-requester
    id="IfaCustomerListA001"
    action-id="SUB0201_01-01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerInitializeA001($event)"
    @response-error-handler="errorHandlerInitializeA001($event)"
  ></ifa-requester>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaBbApplyInput from '@/views/brokerageMenu/ipoPo/ipoPoBrandList/IfaBbApplyInput'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaCRankDomainModel from '@/resource/domain/IfaCRankDomainModel.json'
import IfaAddressKanjiDomainModel from '@/resource/domain/IfaAddressKanjiDomainModel.json'
import IfaPhoneNumber12DomainModel from '@/resource/domain/IfaPhoneNumber12DomainModel.json'
import IfaAgeDomainModel from '@/resource/domain/IfaAgeDomainModel.json'
import IfaButenCodeDomainModel from '@/resource/domain/IfaButenCodeDomainModel.json'
import IfaDate8DomainModel from '@/resource/domain/IfaDate8DomainModel.json'
import IfaDate6YearMonthDomainModel from '@/resource/domain/IfaDate6YearMonthDomainModel.json'
import IfaButenCodeArrayDomainModel from '@/resource/domain/IfaButenCodeArrayDomainModel.json'
import IfaCurrency190DomainModel from '@/resource/domain/IfaCurrency190DomainModel.json'
import IfaCurrency180DomainModel from '@/resource/domain/IfaCurrency180DomainModel.json'
import { IfaCustomerListFormModel } from './js/IfaCustomerListFormModel'
import { IfaCustomerListA002RequestModel } from './js/IfaCustomerListA002RequestModel'
import { IfaCustomerListA005RequestModel } from './js/IfaCustomerListA005RequestModel'
import { IfaCustomerListA006RequestModel } from './js/IfaCustomerListA006RequestModel'
import { IfaCustomerListA007RequestModel } from './js/IfaCustomerListA007RequestModel'
import IfaImprintInquiry from './IfaImprintInquiry.vue'
import { getMessage, notifyMessage } from '@/utils/errorHandler'
import { isAccessible } from '@/utils/controlAuth'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import PersonalCorporationSelect from '@/components/MultiSelect/PersonalCorporationSelect'
import IfaBalloonIconHide from '@/components/icon/IfaBalloonIconHide'

const obj = {
  width: 0,
  height: 0,
  title: '顧客一覧',
  flexHeight: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  scrollModel: { autoFit: true },
  topVisible: false,
  wrap: false,
  postRenderInterval: 100
}
obj.colModel = [
  { title: '仲介業者名', dataType: 'string', dataIndx: 'branchNameOfBroker', width: '150', editable: false, halign: 'center' },
  { title: '営業員コード', dataType: 'string', dataIndx: 'brokerChargeCode', width: '100', halign: 'center' },
  { title: '営業員名', dataType: 'string', dataIndx: 'chargeName', width: '100', halign: 'center' },
  { title: '部店', dataType: 'string', dataIndx: 'butenCode', width: '100', halign: 'center' },
  { title: '口座番号', dataType: 'string', dataIndx: 'accountNumber', width: '100', halign: 'center' },
  { title: '取引コース', dataType: 'string', dataIndx: 'customerAttributeName', width: '180', halign: 'center' },
  { title: '顧客名(漢字)', dataType: 'string', dataIndx: 'customerNameKanji', width: '250', halign: 'center' },
  { title: '顧客名(カナ)', dataType: 'string', dataIndx: 'customerNameKana', width: '120', halign: 'center' },
  { title: '取引制限', dataType: 'string', dataIndx: 'tradeRestriction', width: '100', halign: 'center', align: 'center' },
  { title: '年齢', dataType: 'string', dataIndx: 'age', width: '100', halign: 'center' },
  { title: '生年月日', dataType: 'string', dataIndx: 'corBirthFlg', width: '100', halign: 'center' },
  { title: '職業', dataType: 'string', dataIndx: 'occupation', width: '100', halign: 'center' },
  { title: 'Cランク', dataType: 'string', dataIndx: 'tcCompRank', width: '100', halign: 'center' },
  { title: '最終約定日', dataType: 'string', dataIndx: 'lastTradeDate', width: '100', halign: 'center' },
  { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '120', halign: 'center' },
  { title: '支店コード', dataType: 'string', dataIndx: 'subBrokerId', width: '100', halign: 'center' },
  { title: '支店名', dataType: 'string', dataIndx: 'branchNameOfBranch', width: '200', halign: 'center' },
  { title: '電子交付同意', dataType: 'string', dataIndx: 'electronicDocConsentDate', width: '100', halign: 'center' },
  { title: '住所', dataType: 'string', dataIndx: 'adressKanji', width: '200', halign: 'center' },
  { title: '口座開設日', dataType: 'string', dataIndx: 'openAcctDate', width: '100', halign: 'center' },
  { title: '電話番号', dataType: 'string', dataIndx: 'corporatePhoneNumber', width: '100', halign: 'center' },
  { title: '預り金額', dataType: 'string', dataIndx: 'totalAssets', width: '170', halign: 'center', align: 'right',
    render: function(ui) {
      if (ui.rowData.totalAssets) {
        return addCommasToNumber(ui.rowData.totalAssets)
      } else {
        return '-'
      }
    }
  },
  { title: '評価額', dataType: 'string', dataIndx: 'valuationTotalJpyAmount', width: '170', halign: 'center', align: 'right',
    render: function(ui) {
      if (ui.rowData.valuationTotalJpyAmount) {
        return addCommasToNumber(ui.rowData.valuationTotalJpyAmount)
      } else {
        return '-'
      }
    }
  },
  { title: '評価損益', dataType: 'string', dataIndx: 'customerListProfitAndLoss', width: '170', halign: 'center', align: 'right',
    render: function(ui) {
      const grid = this
      const data = ui.rowData.customerListProfitAndLoss
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data)
      } else {
        return '-'
      }
    }
  },
  { title: '金融資産', dataType: 'string', dataIndx: 'financialAssets', width: '150', halign: 'center' },
  { title: '年間手数料', dataType: 'string', dataIndx: 'commTotalAmountOfYear', width: '170', halign: 'center', align: 'right',
    render: function(ui) {
      if (ui.rowData.commTotalAmountOfYear) {
        return addCommasToNumber(ui.rowData.commTotalAmountOfYear)
      } else {
        return '-'
      }
    }
  },
  { title: '累計手数料', dataType: 'string', dataIndx: 'commTotalAmount', width: '170', halign: 'center', align: 'right',
    render: function(ui) {
      if (ui.rowData.commTotalAmount) {
        return addCommasToNumber(ui.rowData.commTotalAmount)
      } else {
        return '-'
      }
    }
  },
  { title: '閲覧可能部店', dataType: 'string', dataIndx: 'viewAblrButenCode', width: '150', halign: 'center' },
  { title: 'NISA口座', dataType: 'string', dataIndx: 'nisaContractKbnViewInfo', width: '150', halign: 'center' },
  { title: 'コース変更完了日', dataType: 'string', dataIndx: 'changeFinishDateTime', width: '150', halign: 'center' },
  { title: '外国証券取引口座', dataType: 'string', dataIndx: 'foreignSecurityAccountViewInfo', width: '150', halign: 'center' },
  { title: '投資の方針', dataType: 'string', dataIndx: 'investmentPlan', width: '150', halign: 'center' },
  { title: '資金の性格', dataType: 'string', dataIndx: 'fundCharacter', width: '150', halign: 'center' },
  { title: '主な収入源', dataType: 'string', dataIndx: 'incomeForm', width: '150', halign: 'center' },
  { title: '資産運用期間', dataType: 'string', dataIndx: 'employmentPeriod', width: '150', halign: 'center' },
  { title: '年収', dataType: 'string', dataIndx: 'annualIncome', width: '150', halign: 'center' },
  { title: '投資経験有無（株式現物）', dataType: 'string', dataIndx: 'stockExpKbn', width: '110', halign: 'center', align: 'center' },
  { title: '投資経験年数（株式現物）', dataType: (v1, v2) => { return nullSortingExp(v1, v2) }, sortOptions: { disabled: 'true' }, dataIndx: 'stockExp', width: '110', halign: 'center' },
  { title: '投資経験有無（債券）', dataType: 'string', dataIndx: 'debentureExpKbn', width: '110', halign: 'center', align: 'center' },
  { title: '投資経験年数（債券）', dataType: (v1, v2) => { return nullSortingExp(v1, v2) }, sortOptions: { disabled: 'true' }, dataIndx: 'debentureExp', width: '110', halign: 'center' },
  { title: '投資経験有無（転換社債）', dataType: 'string', dataIndx: 'cbExpKbn', width: '110', halign: 'center', align: 'center' },
  { title: '投資経験年数（転換社債）', dataType: (v1, v2) => { return nullSortingExp(v1, v2) }, sortOptions: { disabled: 'true' }, dataIndx: 'cbExp', width: '110', halign: 'center' },
  { title: '投資経験有無（信用）', dataType: 'string', dataIndx: 'marginExpKbn', width: '110', halign: 'center', align: 'center' },
  { title: '投資経験年数（信用）', dataType: (v1, v2) => { return nullSortingExp(v1, v2) }, sortOptions: { disabled: 'true' }, dataIndx: 'marginExp', width: '110', halign: 'center' },
  { title: '投資経験有無（ワラント）', dataType: 'string', dataIndx: 'warrantExpKbn', width: '110', halign: 'center', align: 'center' },
  { title: '投資経験年数（ワラント）', dataType: (v1, v2) => { return nullSortingExp(v1, v2) }, sortOptions: { disabled: 'true' }, dataIndx: 'warrantExp', width: '110', halign: 'center' },
  { title: '投資経験有無（先物OP）', dataType: 'string', dataIndx: 'futureopExpKbn', width: '110', halign: 'center', align: 'center' },
  { title: '投資経験年数（先物OP）', dataType: (v1, v2) => { return nullSortingExp(v1, v2) }, sortOptions: { disabled: 'true' }, dataIndx: 'futureopExp', width: '110', halign: 'center' },
  { title: '投資経験有無（貯蓄型投信）', dataType: 'string', dataIndx: 'savedtypefundExpKbn', width: '120', halign: 'center', align: 'center' },
  { title: '投資経験年数（貯蓄型投信）', dataType: (v1, v2) => { return nullSortingExp(v1, v2) }, sortOptions: { disabled: 'true' }, dataIndx: 'savedtypefundExp', width: '120', halign: 'center' },
  { title: '投資経験有無（その他投信）', dataType: 'string', dataIndx: 'otherfundExpKbn', width: '120', halign: 'center', align: 'center' },
  { title: '投資経験年数（その他投信）', dataType: (v1, v2) => { return nullSortingExp(v1, v2) }, sortOptions: { disabled: 'true' }, dataIndx: 'otherfundExp', width: '120', halign: 'center' },
  { title: '投資経験有無（外国証券）', dataType: 'string', dataIndx: 'foreignExpKbn', width: '110', halign: 'center', align: 'center' },
  { title: '投資経験年数（外国証券）', dataType: (v1, v2) => { return nullSortingExp(v1, v2) }, sortOptions: { disabled: 'true' }, dataIndx: 'foreignExp', width: '110', halign: 'center' }

]
// 投資経験年数ソート
function nullSortingExp(val1, val2) {
  const num1 = parseInt(val1.slice(0, -1))
  const num2 = parseInt(val2.slice(0, -1))
  if (num1 > num2) {
    return 1
  } else if (num1 < num2) {
    return -1
  } else {
    return 0
  }
}

function addCommasToNumber(numberString) {
  return ifaFormatUtils.withCommaNoneZeroPadding(numberString)
}
obj.pageModel = {
  type: 'local',
  rPP: 30,
  rPPOptions: []
}
export default {
  components: {
    GridTable,
    IfaBbApplyInput,
    IfaCommonSearch,
    screenTitle,
    IfaImprintInquiry,
    PersonalCorporationSelect,
    IfaBalloonIconHide
  },
  emits: ['initializeError'],
  data() {
    return {
      form: new IfaCustomerListFormModel(),
      requestedTime: '',
      IfaCRankDomainModel: IfaCRankDomainModel,
      IfaAddressKanjiDomainModel: IfaAddressKanjiDomainModel,
      IfaPhoneNumber12DomainModel: IfaPhoneNumber12DomainModel,
      IfaButenCodeDomainModel: IfaButenCodeDomainModel,
      IfaAgeDomainModel,
      IfaDate8DomainModel,
      IfaDate6YearMonthDomainModel,
      IfaButenCodeArrayDomainModel: IfaButenCodeArrayDomainModel,
      IfaCurrency190DomainModel: IfaCurrency190DomainModel,
      IfaCurrency180DomainModel: IfaCurrency180DomainModel,
      formRef: {},
      searchOptions: [
        { value: '1', label: 'と等しい' },
        { value: '2', label: 'で始まる' },
        { value: '3', label: 'を含む' }
      ],
      foreignAccountOptions: [
        { value: '', label: '' },
        { value: '1', label: '外国株式' },
        { value: '2', label: '米国株式（信用）' }
      ],
      nisaContractKbnOptions: [
        { value: '', label: '' },
        { value: '1', label: '開設済' },
        { value: '2', label: '未開設' }
      ],
      commissionExactOptions: [
        { value: '1', label: '全商品' },
        { value: '2', label: '国内株式全般' },
        { value: '3', label: '国内株式現物' },
        { value: '4', label: '国内株式信用' },
        { value: '5', label: '国内投信' },
        { value: '6', label: '円貨建債券' },
        { value: '7', label: '外国株式全般' },
        { value: '8', label: '外国株式現物' },
        { value: '9', label: '米国株式信用' },
        { value: '10', label: '外国投信' },
        { value: '11', label: '外貨建債券' }
      ],
      assessedValueOptions: [
        { value: '', label: '全商品' },
        { value: '01', label: '国内株式現物' },
        { value: '15', label: '外国株式現物' },
        { value: '06', label: '国内投信' },
        { value: '08', label: '外国投信' },
        { value: '16', label: '外貨建MMF' },
        { value: '12', label: '国内債券' },
        { value: '13', label: '外国債券(円建)' },
        { value: '14', label: '外国債券(外貨建)' },
        { value: '33', label: 'ST' },
        { value: '50', label: 'MRF' },
        { value: '100', label: '金銭(ハイブリッド口座)' },
        { value: '99', label: '現金（円貨）' },
        { value: '98', label: '現金（外貨）' }
      ],
      pqGridOption: getConvertedOption(obj),
      pqGridSelectedInfo: {},
      activeAttribute: 1,
      activeBalanceTransaction: 1,
      bbApply: false,
      imprintInquiry: false,
      csvbtn: true,
      activeBtn: true,
      activeInquiryBtn: true,
      isInfoMessage: false,
      rules: {
        butenCodeArray: [
          { trigger: 'blur', validator: this.butenCodeArrayValidator }
        ],
        valuationFrom: [
          { trigger: 'blur', validator: this.valuationFromValidator }
        ],
        valuationTo: [
          { trigger: 'blur', validator: this.valuationToValidator }
        ],
        commTotalAmountFrom: [
          { trigger: 'blur', validator: this.commTotalAmountFromValidator }
        ],
        commTotalAmountTo: [
          { trigger: 'blur', validator: this.commTotalAmountToValidator }
        ],
        openAccount: [
          { trigger: 'blur', validator: this.openAccountValidator }
        ],
        lastTradeDate: [
          { trigger: 'blur', validator: this.lastTradeDateValidator }
        ],
        courseChangeFinishDate: [
          { trigger: 'blur', validator: this.courseChangeFinishDateValidator }
        ]
      }
    }
  },
  computed: {
    IfaCustomerListA002RequestModel() {
      return new IfaCustomerListA002RequestModel(this.form)
    },
    IfaCustomerListA005RequestModel() {
      return new IfaCustomerListA005RequestModel(this.form)
    },
    IfaCustomerListA006RequestModel() {
      return new IfaCustomerListA006RequestModel(this.form)
    },
    IfaCustomerListA007RequestModel() {
      return new IfaCustomerListA007RequestModel(this.form)
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  watch: {
    'form.brokerCode': function(newBrokerCode, oldBrokerCode) {
      this.form.brokerCodeList = newBrokerCode.split(',')
    },
    'form.butenCodeArray': function(newButenCode, oldButenCode) {
      this.form.selectedButenCodeArray = newButenCode.split(',')
    },
    'form.BirthDate': {
      handler(newValue) {
        this.form.BirthDateFrom = newValue[0]
        this.form.BirthDateTo = newValue[1]
      }
    },
    'form.openAccount': {
      handler(newValue) {
        this.form.openAccountFrom = newValue[0]
        this.form.openAccountTo = newValue[1]
      }
    },
    'form.commTotalPeriod': {
      handler(newValue) {
        this.form.commTotalPeriodFrom = newValue[0]
        this.form.commTotalPeriodTo = newValue[1]
      }
    },
    'form.courseChangeFinishDate': {
      handler(newValue) {
        this.form.courseChangeFinishDateFrom = newValue[0]
        this.form.courseChangeFinishDateTo = newValue[1]
      }
    },
    'form.lastTradeDate': {
      handler(newValue) {
        this.form.lastTradeDateFrom = newValue[0]
        this.form.lastTradeDateTo = newValue[1]
      }
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.personalCorporationCheckAll()
      this.pqGridOption.dataModel.data = []
      this.isInfoMessage = true
      this.csvbtn = true
      this.form.personalCorporation = 2
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
        document.getElementById('IfaCustomerListA001').click()
        // sessionから「前回入力値」を取得する。
        if (sessionStorage.getItem('IfaCustomerForm')) {
          this.form = JSON.parse(sessionStorage.getItem('IfaCustomerForm'))
          this.$refs['commonSearchItem'].$refs['courseSelect'].handleChangeAny(this.form.courseSelected) // 取引コース【前回入力値】
          this.$refs['investmentPlan'].handleChangeAny(this.form.investmentPlan) // 投資の方針【前回入力値】
          this.$refs['fundCharacter'].handleChangeAny(this.form.fundCharacter) // 資金の性格【前回入力値】
          this.$refs['incomeForm'].handleChangeAny(this.form.incomeForm) // 主な収入源【前回入力値】
          this.$refs['employmentPeriod'].handleChangeAny(this.form.employmentPeriod) // 資産運用期間【前回入力値】
          this.$refs['annualIncome'].handleChangeAny(this.form.annualIncome) // 年収【前回入力値】
          this.$refs['financialAssets'].handleChangeAny(this.form.financialAssets) // 金融資産【前回入力値】
          this.$refs['occupation'].handleChangeAny(this.form.occupation) // 職業【前回入力値】
          this.$refs['investmentExp'].handleChangeAny(this.form.investmentExp) // 投資経験【前回入力値】
          // 顧客別メニュー画面から遷移した場合、表示ボタンを押す
          const params = this.$_getMenuParams()
          if (params && params.backFlg) {
            document.getElementById('btnDisplay').click()
          }
        }
      })
      setTimeout(() => {
        this.isInfoMessage = false
      }
      , 3000
      )
    },
    butenCodeArrayValidator(rule, value, callback) {
      // 閲覧可能部店が未入力の場合、バリデーションをパス
      if (!value) {
        callback()
        return
      }
      // カンマで区切られた数列を配列に分割
      const numbers = value.split(',').map(Number)

      // 重複をチェックするためにSetを使用
      const numberSet = new Set(numbers)

      // 元の配列とSetの要素数が異なる場合、重複があるとみなす
      if (numbers.length !== numberSet.size) {
        callback(new Error(getMessage('errors.codeDuplicate', ['閲覧可能部店', '閲覧可能部店', value])))
      } else {
        callback()
      }
    },
    valuationFromValidator(rule, value, callback) {
      // 開始日が未入力の場合、バリデーションをパス
      if (!value) {
        callback()
        return
      }
      callback()
    },
    valuationToValidator(rule, value, callback) {
      // 開始日が未入力の場合、バリデーションをパス
      if (!value) {
        callback()
        return
      }
      callback()
    },
    commTotalAmountFromValidator(rule, value, callback) {
      // 開始日が未入力の場合、バリデーションをパス
      if (!value) {
        callback()
        return
      }
      callback()
    },
    commTotalAmountToValidator(rule, value, callback) {
      // 開始日が未入力の場合、バリデーションをパス
      if (!value) {
        callback()
        return
      }
      callback()
    },
    openAccountValidator(rule, value, callback) {
      // 口座開設日(FROM)のシステム日付 - 1チェック
      if (this.checkDateSpecifyBeforeYesterday(value[0]) === false) {
        callback(getMessage('errors.date.specifyBeforeYesterday', ['口座開設日From']))
        return
      }

      // 口座開設日(TO)のシステム日付 - 1チェック
      if (this.checkDateSpecifyBeforeYesterday(value[1]) === false) {
        callback(getMessage('errors.date.specifyBeforeYesterday', ['口座開設日To']))
        return
      }

      callback()
    },
    lastTradeDateValidator(rule, value, callback) {
      // 最終約定日(FROM)のシステム日付 - 1チェック
      if (this.checkDateSpecifyBeforeYesterday(value[0]) === false) {
        callback(getMessage('errors.date.specifyBeforeYesterday', ['最終約定日From']))
        return
      }

      // 最終約定日(TO)のシステム日付 - 1チェック
      if (this.checkDateSpecifyBeforeYesterday(value[1]) === false) {
        callback(getMessage('errors.date.specifyBeforeYesterday', ['最終約定日To']))
        return
      }

      callback()
    },
    courseChangeFinishDateValidator(rule, value, callback) {
      // コース変更完了日(FROM)のシステム日付 - 1チェック
      if (this.checkDateSpecifyBeforeToday(value[0]) === false) {
        callback(getMessage('errors.date.specifyBeforeToday', ['コース変更完了日From']))
        return
      }

      // コース変更完了日(TO)のシステム日付 - 1チェック
      if (this.checkDateSpecifyBeforeToday(value[1]) === false) {
        callback(getMessage('errors.date.specifyBeforeToday', ['コース変更完了日To']))
        return
      }

      callback()
    },
    checkDateSpecifyBeforeYesterday(value) {
      if (value) {
        const yesterday = new Date(this.$store.getters.requestedTime)
        yesterday.setDate(yesterday.getDate() - 1)
        yesterday.setHours(23, 59, 59, 999)

        const targetDate = new Date(value)

        if (yesterday < targetDate) {
          return false
        }
      }

      return true
    },
    checkDateSpecifyBeforeToday(value) {
      if (value) {
        const today = new Date(this.$store.getters.requestedTime)
        today.setHours(23, 59, 59, 999)

        const targetDate = new Date(value)

        if (today < targetDate) {
          return false
        }
      }

      return true
    },
    responseHandlerInitializeA001(response) {
      const displayComment = []
      displayComment.push(response.dataList[0]['ifaCustomerListComment'])
      displayComment.push(response.dataList[1]['ifaCustomerListComment'])
      displayComment.push(response.dataList[2]['ifaCustomerListComment'])
      this.form = Object.assign(this.form, { ifaCustomerListComment: displayComment })
      this.requestedTime = response.requestedTime.split(' ')[0]
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    responseHandlerDisplayA002(data) {
      this.activeBtn = true
      this.activeInquiryBtn = true
      if (data.dataList.length) {
        this.csvbtn = false
      } else {
        this.csvbtn = true
      }
      this.pqGridOption.dataModel.data = data.dataList.map(item => ({
        ...item,
        tradeRestriction: item.tradeRestriction === '1' ? '○' : '',
        financialAssetsType: this.$_getCodeValue('FINANCIAL_ASSETS', 1, item.financialAssetsType),
        corBirthFlg: this.$_getFormattedDateValue(item.corBirthFlg),
        lastTradeDate: this.$_getFormattedDateValue(item.lastTradeDate),
        electronicDocConsentDate: this.$_getFormattedDateValue(item.electronicDocConsentDate),
        openAcctDate: this.$_getFormattedDateValue(item.openAcctDate),
        changeFinishDateTime: this.$_getFormattedDateValue(item.changeFinishDateTime),
        adressKanji: (item.zipCodeBeforeAndAfter === '' && item.adressKanji === '') ? '-' : `${item.zipCodeBeforeAndAfter} ${item.adressKanji}`
      }))
      this.$refs['pqGrid'].refreshView(true)
      // セッションへ保存
      sessionStorage.setItem('IfaCustomerForm', JSON.stringify(this.form))
    },
    clearA003() {
      sessionStorage.removeItem('IfaCustomerForm')
      this.$refs.form.clearValidate()
      this.$refs.commonSearchItem.formClear()
      this.form.adress = ''
      this.form.customerListPhoneNumber = ''
      this.form.ageFrom = ''
      this.form.ageTo = ''
      this.form.complianceLankFrom = ''
      this.form.complianceLankTo = ''
      this.form.BirthDateDisplay = false
      this.form.BirthDate = []
      this.form.BirthDateFrom = ''
      this.form.BirthDateTo = ''
      this.form.openAccountDisplay = false
      this.form.openAccount = []
      this.form.openAccountFrom = ''
      this.form.openAccountTo = ''
      this.form.lastTradeDateDisplay = false
      this.form.lastTradeDate = []
      this.form.lastTradeDateFrom = ''
      this.form.lastTradeDateTo = ''
      this.form.foreignSecurityAccountDisplay = false
      this.form.foreignSecurityAccountName = ''
      this.form.nisaContractKbnDisplay = false
      this.form.nisaContractKbnName = ''
      this.form.courseChangeFinishDateDisplay = false
      this.form.courseChangeFinishDate = []
      this.form.courseChangeFinishDateFrom = ''
      this.form.courseChangeFinishDateTo = ''
      this.form.valuationFrom = ''
      this.form.valuationTo = ''
      this.form.commTotalList = 1
      this.form.commTotalPeriod = []
      this.form.commTotalPeriodFrom = ''
      this.form.commTotalPeriodTo = ''
      this.form.commTotalAmountFrom = ''
      this.form.commTotalAmountTo = ''
      this.form.viewAblrButenDisplay = false
      this.form.butenCodeArray = ''
      this.form.selectedButenCodeArray = ['']
      this.form.tradeRestrictionCheckbox = false
      this.form.adressDisplay = false
      this.form.phoneNumberDisplay = false
      this.form.ageDisplay = false
      this.form.investmentExpDisplay = false
      this.form.adressConditionList = '3'
      this.form.valuationConditionList = ''
      this.form.commTotalAmountConditionList = '1'
      this.form.personalCorporation = 2
      this.personalCorporationCheckAll()
    },
    changePersonalCorporation() {
      this.personalCorporationCheckAll()
    },
    personalCorporationCheckAll() {
      this.$refs['investmentPlan'].handleCheckAll(false) // 投資の方針【初期値】未選択
      this.$refs['fundCharacter'].handleCheckAll(false) // 資金の性格【初期値】未選択
      this.$refs['incomeForm'].handleCheckAll(false) // 主な収入源【初期値】未選択
      this.$refs['employmentPeriod'].handleCheckAll(false) // 資産運用期間【初期値】未選択
      this.$refs['annualIncome'].handleCheckAll(false) // 年収【初期値】未選択
      this.$refs['financialAssets'].handleCheckAll(false) // 金融資産【初期値】未選択
      this.$refs['occupation'].handleCheckAll(false) // 職業【初期値】未選択
      this.$refs['investmentExp'].handleCheckAll(false) // 投資経験【初期値】未選択
      this.form.investmentExpDisplay = false // 投資経験（チェック） 【初期値】OFF
    },
    // 一覧選択
    handleClick(ui) {
      this.pqGridSelectedInfo = ui
      this.activeBtn = false
      if (this.userInfo.medUsers.privId < 11 || this.userInfo.medUsers.privId === 13 || this.userInfo.medUsers.privId === 14) {
        this.activeInquiryBtn = false
      }
      this.form.accountNumberSelected = ui.rowData.accountNumber
      this.form.butenCodeSelected = ui.rowData.butenCode
      this.form.brokerCodeSelected = ui.rowData.brokerCode
      this.form.empCodeSelected = ui.rowData.brokerChargeCode
    },
    preRequestHandlerA006() {
      if (this.pqGridSelectedInfo.rowData.tcCompRank === 'Z') {
        notifyMessage(-1, getMessage('errors.cmn.complianceRank.suspendedAccount'), '顧客一覧・基本')
        throw new Error()
      }
      return
    },
    bbApplicationA006() {
      this.bbApply = true
      this.$refs['ifaBbApplyInput'].setupFromCustomerList(this.IfaCustomerListA006RequestModel)
    },
    sealInquiryA007(response) {
      this.imprintInquiry = true
      this.$refs['ifaImprintInquiry'].setupFromCustomerList(response)
    },
    addCommaBrokerCode(el) {
      const array = this.form.brokerCode.replace(/,/g, '').split('')
      for (let i = 0; i < array.length; i++) {
      // リストの5th中に","を追加する
        if ((i + 1) % 5 === 0) {
          array.splice(i, 0, ',')
        }
      }
      this.form.brokerCode = array.join('')
    },
    addCommaVisibleButenCode() {
      const array = this.form.butenCodeArray.replace(/,/g, '').split('')
      for (let i = 0; i < array.length; i++) {
      // リストの4th中に","を追加する
        if ((i + 1) % 4 === 0) {
          array.splice(i, 0, ',')
        }
      }
      this.form.butenCodeArray = array.join('')
    },
    addCommaValuationFrom() {
      const array = this.form.valuationFrom.replace(/,/g, '').split('')
      for (let i = 0; i < array.length; i++) {
      // リストの4th中に","を追加する
        if ((i + 1) % 4 === 0) {
          array.splice(i, 0, ',')
        }
      }
      this.form.valuationFrom = array.join('')
    },
    clickShowMessage() {
      this.isInfoMessage = !this.isInfoMessage
    },
    addColumns1() {
      const gridCols = this.$refs['pqGrid'].grid.columns
      const shouldDisplayAddress = this.form.adressDisplay || this.form.adress !== ''
      const shouldDisplayPhoneNumber = this.form.phoneNumberDisplay || this.form.customerListPhoneNumber !== ''
      const shouldDisplayBirthDate = this.form.BirthDateDisplay || this.form.BirthDateFrom || this.form.BirthDateTo
      const shouldDisplayOpenAccount = this.form.openAccountDisplay || this.form.openAccountFrom || this.form.openAccountTo
      const shouldDisplayViewableButen = this.form.viewAblrButenDisplay || this.form.butenCodeArray !== ''
      const shouldDisplayforeignSecurityAccount = this.form.foreignSecurityAccountDisplay || this.form.foreignSecurityAccountName !== ''
      const shouldDisplayCourseChangeFinishDateDisplay = this.form.courseChangeFinishDateDisplay || this.form.courseChangeFinishDateFrom || this.form.courseChangeFinishDateTo
      const shouldDisplayLastTradeDate = this.form.lastTradeDateDisplay || this.form.lastTradeDateFrom || this.form.lastTradeDateTo
      const shouldDisplaynisaContractKbn = this.form.nisaContractKbnDisplay || this.form.nisaContractKbnName !== ''

      this.toggleColumnVisibility(gridCols, 'adressKanji', shouldDisplayAddress)
      this.toggleColumnVisibility(gridCols, 'corporatePhoneNumber', shouldDisplayPhoneNumber)
      gridCols['age'].hidden = !this.form.ageDisplay
      this.toggleColumnVisibility(gridCols, 'corBirthFlg', shouldDisplayBirthDate)
      this.toggleColumnVisibility(gridCols, 'openAcctDate', shouldDisplayOpenAccount)
      this.toggleColumnVisibility(gridCols, 'viewAblrButenCode', shouldDisplayViewableButen)
      this.toggleColumnVisibility(gridCols, 'foreignSecurityAccountViewInfo', shouldDisplayforeignSecurityAccount)
      this.toggleColumnVisibility(gridCols, 'changeFinishDateTime', shouldDisplayCourseChangeFinishDateDisplay)
      this.toggleColumnVisibility(gridCols, 'lastTradeDate', shouldDisplayLastTradeDate)
      this.multiColumnVisibility(gridCols, 'investmentPlan', this.form.investmentPlan, false)
      this.multiColumnVisibility(gridCols, 'fundCharacter', this.form.fundCharacter, false)
      this.multiColumnVisibility(gridCols, 'incomeForm', this.form.incomeForm, false)
      this.multiColumnVisibility(gridCols, 'employmentPeriod', this.form.employmentPeriod, false)
      this.multiColumnVisibility(gridCols, 'annualIncome', this.form.annualIncome, false)
      this.multiColumnVisibility(gridCols, 'financialAssets', this.form.financialAssets, false)
      this.multiColumnVisibility(gridCols, 'occupation', this.form.occupation, false)
      this.investmentExpColumnVisibility(gridCols, this.form.investmentExp, this.form.investmentExpDisplay)
      this.toggleColumnVisibility(gridCols, 'nisaContractKbnViewInfo', shouldDisplaynisaContractKbn)
    },
    multiColumnVisibility(gridCols, columnName, dom, chk) {
      if (chk === false) {
        for (let i = 0; i < dom.length; i++) {
          if (dom[i].isSelected === true) {
            gridCols[columnName].hidden = false
            break
          }
          gridCols[columnName].hidden = true
        }
      } else {
        gridCols[columnName].hidden = false
      }
    },
    investmentExpColumnVisibility(gridCols, dom, investmentExpChk) {
      let hiddenFlg = false
      for (let i = 0; i < dom.length; i++) {
        if (dom[i].isSelected === true) {
          hiddenFlg = true
        }
      }
      if (investmentExpChk && !hiddenFlg) {
        this.investmentExpHidden(gridCols, false)
      } else {
        const selectArray = []
        for (let i = 0; i < dom.length; i++) {
          if (dom[i].isSelected === true) {
            selectArray.push(dom[i].id)
          }
        }
        if (selectArray.length === 0) {
          this.investmentExpHidden(gridCols, true)
        } else {
          for (let i = 0; i < selectArray.length; i++) {
            if (selectArray.indexOf('01') === -1 && selectArray.indexOf('02') === -1) {
              gridCols['stockExpKbn'].hidden = true
              gridCols['stockExp'].hidden = true
            } else {
              gridCols['stockExpKbn'].hidden = false
              gridCols['stockExp'].hidden = false
            }
            if (selectArray.indexOf('03') === -1 && selectArray.indexOf('04') === -1) {
              gridCols['debentureExpKbn'].hidden = true
              gridCols['debentureExp'].hidden = true
            } else {
              gridCols['debentureExpKbn'].hidden = false
              gridCols['debentureExp'].hidden = false
            }
            if (selectArray.indexOf('05') === -1 && selectArray.indexOf('06') === -1) {
              gridCols['cbExpKbn'].hidden = true
              gridCols['cbExp'].hidden = true
            } else {
              gridCols['cbExpKbn'].hidden = false
              gridCols['cbExp'].hidden = false
            }
            if (selectArray.indexOf('07') === -1 && selectArray.indexOf('08') === -1) {
              gridCols['marginExpKbn'].hidden = true
              gridCols['marginExp'].hidden = true
            } else {
              gridCols['marginExpKbn'].hidden = false
              gridCols['marginExp'].hidden = false
            }
            if (selectArray.indexOf('09') === -1 && selectArray.indexOf('10') === -1) {
              gridCols['warrantExpKbn'].hidden = true
              gridCols['warrantExp'].hidden = true
            } else {
              gridCols['warrantExpKbn'].hidden = false
              gridCols['warrantExp'].hidden = false
            }
            if (selectArray.indexOf('11') === -1 && selectArray.indexOf('12') === -1) {
              gridCols['futureopExpKbn'].hidden = true
              gridCols['futureopExp'].hidden = true
            } else {
              gridCols['futureopExpKbn'].hidden = false
              gridCols['futureopExp'].hidden = false
            }
            if (selectArray.indexOf('13') === -1 && selectArray.indexOf('14') === -1) {
              gridCols['savedtypefundExpKbn'].hidden = true
              gridCols['savedtypefundExp'].hidden = true
            } else {
              gridCols['savedtypefundExpKbn'].hidden = false
              gridCols['savedtypefundExp'].hidden = false
            }
            if (selectArray.indexOf('15') === -1 && selectArray.indexOf('16') === -1) {
              gridCols['otherfundExpKbn'].hidden = true
              gridCols['otherfundExp'].hidden = true
            } else {
              gridCols['otherfundExpKbn'].hidden = false
              gridCols['otherfundExp'].hidden = false
            }
            if (selectArray.indexOf('17') === -1 && selectArray.indexOf('18') === -1) {
              gridCols['foreignExpKbn'].hidden = true
              gridCols['foreignExp'].hidden = true
            } else {
              gridCols['foreignExpKbn'].hidden = false
              gridCols['foreignExp'].hidden = false
            }
          }
        }
      }
    },
    investmentExpHidden(gridCols, flg) {
      gridCols['stockExpKbn'].hidden = flg
      gridCols['stockExp'].hidden = flg
      gridCols['debentureExpKbn'].hidden = flg
      gridCols['debentureExp'].hidden = flg
      gridCols['cbExpKbn'].hidden = flg
      gridCols['cbExp'].hidden = flg
      gridCols['marginExpKbn'].hidden = flg
      gridCols['marginExp'].hidden = flg
      gridCols['warrantExpKbn'].hidden = flg
      gridCols['warrantExp'].hidden = flg
      gridCols['futureopExpKbn'].hidden = flg
      gridCols['futureopExp'].hidden = flg
      gridCols['savedtypefundExpKbn'].hidden = flg
      gridCols['savedtypefundExp'].hidden = flg
      gridCols['otherfundExpKbn'].hidden = flg
      gridCols['otherfundExp'].hidden = flg
      gridCols['foreignExpKbn'].hidden = flg
      gridCols['foreignExp'].hidden = flg
    },
    toggleColumnVisibility(gridCols, columnName, shouldDisplay) {
      gridCols[columnName].hidden = !shouldDisplay
    },
    onHide() {
      this.isInfoMessage = false
    },
    customerMenuA004() {
      const query = Object.assign({}, this.$route.query)
      query.userName = this.pqGridSelectedInfo.rowData.customerNameKanji
      // TODOパラメータ固定値
      const params = {
        userName: this.pqGridSelectedInfo.rowData.customerNameKanji,
        accountNumber: this.pqGridSelectedInfo.rowData.accountNumber,
        butenCode: this.pqGridSelectedInfo.rowData.butenCode,
        tradeRestrictionCheckbox: this.pqGridSelectedInfo.rowData.tradeRestriction,
        menuId: this.$store.getters.pageInfo.menuId,
        label: this.$store.getters.pageInfo.label
      }
      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        this.$_startShowMenu(this.$customerMenuInitialScreenId, params)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          this.form.screenTitle
        )
      }
    },
    // 自動入力フラグを受取り、カラムの表示制御を行う
    mediateUserPrivacy(data) {
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('branchNameOfBroker', false)
        this.setHidden('brokerChargeCode', false)
        this.setHidden('chargeName', false)
        this.setHidden('brokerCode', false)
        this.setHidden('subBrokerId', false)
        this.setHidden('branchNameOfBranch', false)
      } else {
        this.setHidden('branchNameOfBroker', true)
        this.setHidden('brokerChargeCode', true)
        this.setHidden('chargeName', true)
        this.setHidden('brokerCode', true)
        this.setHidden('subBrokerId', true)
        this.setHidden('branchNameOfBranch', true)
      }
      this.$refs['pqGrid'].refreshView(true)
    },
    preRequestHandlerA005() {
      this.$_logDebug('preRequestHandlerA005-OK')
    },
    responseHandlerA005() {
      this.$_logDebug('responseHandlerA005-OK')
    },
    responseErrorHandlerA005(error) {
      this.$_logError('responseErrorHandlerA002-Error', error)
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    }
  }
}

</script>
<style scoped>
 :deep(.search-form__item) {
   margin: 0 0.2rem 0 0.2rem;
   width: 180px;
 }
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
  margin-left: 0.5rem;
}
.content-card {
    margin: 0.5rem 1rem;
  }

.checkmark {
  margin-right: 5px;
}
 :deep(.checkmarkClass) {
  text-align : right;
  padding-right: 10px;
  font-weight: bold;
}

 .right-label {
  display: inline-block;
  width: 130px;
  margin-right: 8px;
  text-align: end;
  padding-right: 12px;
  line-height: 1;
  font-weight: bold;
  font-size: 14px;
  padding-top: 10px;
 }

 .left-label {
  display: inline-block;
  width: 100px;
  margin-right: 8px;
  text-align: end;
  padding-right: 12px;
  line-height: 1;
  font-weight: bold;
  font-size: 14px;
  padding-top: 10px;
}

.left-label_top {
  display: inline-block;
  width: 100px;
  margin-right: 8px;
  text-align: end;
  padding-right: 12px;
  line-height: 1;
  font-weight: bold;
  font-size: 14px;
}

.middle_input {
  width: 120px;
}
:deep(.short_input) {
  width: 50px;
  text-align : right;
}
 :deep(.not_margin_right) {
   margin-right: 0;
 }
.num_input {
  width: 215px;
}

.date_input {
  width: 120px;
}

:deep(.checkmarkClass) .el-col-2  {
  text-align : right;
}
:deep(.num_input) .el-input__inner  {
  text-align : right;
}

:deep(.checkmark) .el-switch__label * {
  font-weight:bold;
  text-align : right;
}

:deep(.el-collapse-item__content) {
  padding-bottom: 0%;
}

.gridButtonArea {
  margin-bottom: 10px;
}

:deep(.el-form-item__label) {
  font-weight: bold;
}

:deep(.el-collapse-item__header){
  padding-left: 20px;
  background-color: transparent;
}
:deep(.el-collapse-item__header:hover){
  color: #409eff;
  background-color: #f9fafc;
}
:deep(.el-collapse) {
  border-bottom: none;
}
.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 10px;
}

:deep(.el-icon-arrow-right) {
  content: none;
}

:deep(.customer-name-label) .el-form-item__label {
  margin-right: 0px;
  padding-right: 0px;
}

:deep(.el-form-item__error) {
  white-space: nowrap;
}

.form_area_37 {
  margin-bottom:10px;
  margin-left: 0.25rem;
  height: 37px;
}
.form_area {
  margin-bottom:10px;
  margin-left: 0.25rem;
}
.form_fee {
  padding-top: 10px;
}
.price_input :deep(.el-input__wrapper) {
  width: 220px;
}

.m-left-15 {
  margin-left: 15px;
}
:deep(.ifa-input_base__controll_wrapper.without-unit[data-v-96c11ca8]) {
  margin-left: 0px
}
:deep(.ifa-input_base__controll_wrapper.small[data-v-96c11ca8]) {
  width: 65px;
}

.custom_space {
  padding: 7px;
}
:deep(.ifa-input_base__wrapper) {
  min-width: 0;
}
.adjust_position {
  display: flex;
  align-items: start;
}
.adjust_form_margin :deep(.el-form-item) {
  margin-bottom: 0;
}
.adjust_form_margin_calender :deep(.el-form-item) {
  margin-bottom: 10px;
}
.adjust_label :deep(.el-form-item__label) {
  width: 100px;
  margin-left: 0px;
}
.btn-csv-donwload:not(:has(> button)) {
  padding-left: 0px;
  padding-right: 0px;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
:deep(.el-form-item__label) {
  width: 100px;
}
.corporation_classs :deep(.el-input) {
  --el-input-width: 180px;
}
.corporation_classs :deep(.el-input__inner) {
  background-color: transparent;
}
</style>

<style scoped lang="scss">
.adjust_select {
  width: 24%;
  min-width: 330px;
  margin-bottom: 10px;
  display: inline-block;
  :deep(.el-form-item) {
    margin-right: 0;
  }
}
</style>
