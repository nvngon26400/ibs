<template>
  <div class="ifa-subscript-input__wrapper">
    <div class="main-dialog">
      <el-dialog
        :model-value="isVisible"
        :close-on-click-modal="false"
        :show-close="false"
        width="1444px"
        :before-close="handleDialogCloseA004"
        destroy-on-close
        @open="onDialogOpen"
      >
        <!-- 戻るボタン -->
        <div class="main-dialog__back-button__wrapper">
          <ifa-button
            class="main-dialog__back-button__content"
            color="secondary"
            text="戻る"
            action-type="originalAction"
            @app-action-handler="handleDialogCloseA004"
          ></ifa-button>
        </div>

        <!-- 募集入力 -->
        <caption-card
          :caption="form.title.name"
          text-size="20px"
          text-color="#0058a2"
          background-color="Menu"
          class="main-dialog__caption"
        >

          <!-- 銘柄情報 -->
          <el-row class="info-section__title">
            <div><label>銘柄情報</label></div>
          </el-row>
          <el-card>
            <el-form
              :inline="true"
              label-position="left"
              label-width="140px"
            >
              <el-row class="info-section">
                <el-row
                  type="flex"
                  class="info-section__content"
                >
                  <el-col
                    :offset="1"
                    :span="16"
                  >
                    <el-form-item
                      label="銘柄："
                    >
                      <span class="info-section__value">
                        <span>{{ $_out(form.brandName) }}</span>
                        <span style="margin-left: 0.5em;">({{ $_out($_noneWithCommaNoneZeroPadding(form.brandCode)) }})</span>
                      </span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="2">
                    <span
                      v-if="form.smokingCigarette === '1'"
                      class="info-section__value info-section__value--red"
                    >たばこ開示</span>
                  </el-col>
                  <el-col :span="2">
                    <span
                      v-if="form.onlyElectronicDelivery === '1'"
                      class="info-section__value info-section__value--red"
                    >電子交付のみ</span>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="info-section__content"
                >
                  <el-col
                    :offset="1"
                    :span="9"
                  >
                    <el-form-item
                      label="発行価格："
                      label-width="140px"
                    >
                      <div class="info-section__value">
                        <span>{{ $_out($_withCommaNoneZeroPadding(form.issueBbPrice)) }}</span>
                        <span>円</span>
                      </div>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="売買単位："
                      label-width="180px"
                    >
                      <span class="info-section__value">
                        <span>{{ $_out(unit) }}</span>
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="info-section__content"
                >
                  <el-col
                    :offset="1"
                    :span="9"
                  >
                    <el-form-item
                      label="募集期間："
                      label-width="140px"
                    >
                      <span class="info-section__value">
                        <span v-if="form.bbPeriodFrom">{{ $_out($_getFormattedDateValue(form.bbPeriodFrom)) }}</span>
                        <span>～</span>
                        <span v-if="form.bbPeriodTo">{{ $_out($_getFormattedDateValue(form.bbPeriodTo)) }}</span>
                      </span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="入金予定日（募集最終日）："
                      label-width="210px"
                    >
                      <span
                        class="info-section__value"
                        style="margin-left: -20px;"
                      >{{ $_out($_getFormattedDateValue(form.paymentDate)) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="手数料："
                      label-width="200px"
                    >
                      <span class="info-section__value">
                        <span>{{ $_out($_withCommaNoneZeroPadding(form.subscriptComm)) }}</span>
                        <span>円</span>
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-row>
            </el-form>
          </el-card>

          <!-- 顧客情報 -->
          <el-row class="info-section__title">
            <div><label>顧客情報</label></div>
          </el-row>

          <el-card>
            <el-form
              ref="form"
              :inline="true"
              label-position="left"
              label-width="100px"
            >
              <el-row class="info-section">
                <el-row
                  type="flex"
                  class="info-section__content"
                >
                  <el-col
                    :offset="1"
                    :span="9"
                  >
                    <el-form-item
                      label="部店-口座番号："
                      label-width="140px"
                    >
                      <div class="info-section__value">
                        <span>{{ $_out(form.butenCode) }}</span>
                        <span>-</span>
                        <span>{{ $_out($_zeroPadding(form.accountNumber, 7)) }}</span>
                      </div>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="顧客名："
                      label-width="180px"
                    >
                      <span class="info-section__value">{{ $_out(form.customerNameKanji) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7"></el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="info-section__content"
                >
                  <el-col
                    :offset="1"
                    :span="9"
                  >
                    <el-form-item
                      label="口座開設年月日："
                      label-width="140px"
                    >
                      <span>{{ $_out($_getFormattedDateValue(form.openAcctDate)) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="投資方針："
                      label-width="180px"
                    >
                      <ifa-text
                        code-list-id="INVESTMENT_POLICY_TYPE"
                        :disp-pattern="1"
                        :code-key="form.investmentPolicyType"
                      ></ifa-text>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="本年の年間裁量配分割当回数："
                      label-width="220px"
                      class="info-section_label"
                    >
                      <span
                        class="info-section__value"
                        style="margin-left: -20px;"
                      >
                        <span>{{ $_out($_withCommaNoneZeroPadding(form.discretionAlloCount)) }}</span>
                        <span>回</span>
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="info-section__content"
                >
                  <el-col
                    :offset="1"
                    :span="9"
                  >
                    <el-form-item
                      label="職業："
                      label-width="140px"
                    >
                      <ifa-text
                        code-list-id="PROFESSION"
                        :disp-pattern="1"
                        :code-key="form.profession"
                      ></ifa-text>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="投資経験年数(株式現物)："
                      label-width="200px"
                    >
                      <span
                        class="info-section__value"
                        style="margin-left: -20px;"
                      >
                        <span>{{ $_out($_withCommaNoneZeroPadding(form.stockSpotInvestExperienceYears)) }}</span>
                        <span>年</span>
                      </span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="本年の年間裁量配分可能回数："
                      label-width="220px"
                    >
                      <span
                        class="info-section_value"
                        style="margin-left: -20px;"
                      >
                        <span>{{ $_out($_withCommaNoneZeroPadding(form.discretionAllocateAbleTimes)) }}</span>
                        <span>回</span>
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="info-section__content"
                >
                  <el-col
                    :offset="1"
                    :span="9"
                  >
                    <el-form-item
                      label="勤務先名："
                      label-width="140px"
                    >
                      <span>{{ $_out(form.officeName) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="金融資産："
                      label-width="180px"
                    >
                      <!-- 個人の場合のみ表示､法人は '-' -->
                      <ifa-text
                        v-if="form.officeName !== '-'"
                        code-list-id="FINANCIAL_ASSETS"
                        :disp-pattern="1"
                        :code-key="form.financialAssetsType"
                      ></ifa-text>
                      <span v-else>-</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <!-- ■A001.買付余力が取得できない場合、非表示 -->
                    <el-form-item
                      label="買付余力："
                      label-width="200px"
                    >
                      <span class="info-section__value">
                        <span>{{ form.yenBuyingPowerGeneralAccount ? `${$_withCommaInteger(form.yenBuyingPowerGeneralAccount)}円`: '-' }}</span>
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="info-section__content"
                >
                  <el-col
                    :offset="1"
                    :span="9"
                  >
                    <el-form-item
                      label="コンプラランク："
                      label-width="140px"
                    >
                      <span>{{ $_out(form.complianceRank) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="預り資産額："
                      label-width="180px"
                    >
                      <span class="info-section__value">
                        <span>{{ $_out($_withCommaInteger(form.depositAssetAmount)) }}</span>
                      </span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <!-- ■A001.NISA買付可能額が取得できない場合、非表示 -->
                    <el-form-item
                      label="NISA買付可能額："
                      label-width="200px"
                    >
                      <span class="info-sction__value">
                        <span>{{ $_out($_withCommaInteger(form.nisaBuyPotentialAmount)) }}</span>
                        <span>円</span>
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="info-section__content"
                >
                  <el-col
                    :offset="1"
                    :span="9"
                  >
                    <el-form-item
                      label="電子交付同意："
                      label-width="140px"
                    >
                      <span>{{ $_out($_getFormattedDateValue(form.edelivAgreementDate)) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item
                      label="目論見書閲覧："
                      label-width="180px"
                    >
                      <span class="info-sction__value">
                        <span>{{ $_out($_getFormattedDateTimeValue(form.readTime, 'datetime12')) }}</span>
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-row>
            </el-form>
          </el-card>

          <!-- 募集入力(入力フォーム) -->
          <el-row class="input-section__title">
            <div><label>募集入力</label></div>
          </el-row>

          <el-card>
            <el-form
              ref="form"
              :model="inputField"
              :inline="true"
              label-position="left"
              label-width="140px"
              :disabled="disabledForm"
            >
              <el-row
                class="input-section"
              >
                <el-row
                  class="input-section__input"
                  justify="space-between"
                >
                  <el-col
                    :span="6"
                    class="lottery-result__wrapper"
                  >
                    <ifa-input-select
                      id="lotteryResult"
                      v-model="inputField.lotteryResult"
                      label="抽選結果"
                      code-list-id="SUBSCRIPT_BB_DRAWING_RESULT"
                      :disp-pattern="1"
                      :select-pattern="2"
                      :clearable="false"
                      size="small"
                      placeholder=" "
                      :disabled="isDisabledLotteryResult"
                      :required="form.sendCorrectLogicJudgeFlag === 'SBIINSERT'
                        || form.sendCorrectLogicJudgeFlag === 'SBIUPDATEONE'"
                      prop="lotteryResult"
                      @change="handleChangeLotteryResult"
                    ></ifa-input-select>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item
                      label="BB申込株数"
                      label-width="110px"
                    >
                      <div style="margin-left: 6px;"><span>{{ $_out($_withCommaInteger(form.bbQuantity)) }}</span></div>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="6"
                    class="bb-quantity-alloc__wrapper"
                  >
                    <ifa-input-quantity
                      id="bbQuantityAlloc"
                      v-model="inputField.bbQuantityAlloc"
                      label="当選株数"
                      :domain="IfaStocks10DomainModel"
                      :unit="sellBuyUnitType"
                      size="small"
                      support
                      :step="form.unit"
                      :initial-step="form.unit"
                      :max="9999999999"
                      :min="0"
                      :disabled="isDisabledBbQuantityAlloc"
                      :required="form.sendCorrectLogicJudgeFlag === 'SBIINSERT'
                        || form.sendCorrectLogicJudgeFlag === 'SBIUPDATEONE'"
                      prop="bbQuantityAlloc"
                    ></ifa-input-quantity>
                  </el-col>
                  <el-col
                    :span="6"
                    class="order-status__wrapper"
                  >
                    <ifa-input-select
                      id="orderStatus"
                      v-model="inputField.orderStatus"
                      label="注文状況"
                      code-list-id="SUBSCRIPT_ORDER_STATUS"
                      :disp-pattern="orderStatusListPattern.dispPattern"
                      :select-pattern="orderStatusListPattern.selectPattern"
                      :clearable="false"
                      size="small"
                      placeholder=" "
                      :disabled="isDisabledOrderStatus"
                      :required="form.sendCorrectLogicJudgeFlag === 'SBIINSERT'
                        || form.sendCorrectLogicJudgeFlag === 'SBIUPDATEONE'
                        || form.sendCorrectLogicJudgeFlag === 'SBIUPDATETWO'"
                      prop="orderStatus"
                    ></ifa-input-select>
                  </el-col>
                </el-row>
                <el-row
                  class="input-section__input"
                  justify="space-between"
                >
                  <el-col :span="6">
                    <!-- 注文がある場合のみ表示する -->
                    <ifa-input-quantity
                      id="Ammount"
                      v-model="inputField.quantity"
                      :domain="IfaVolume10DomainModel"
                      label="数量"
                      style="width: 120px;"
                      size="small"
                      support
                      :step="form.unit"
                      :initial-step="form.unit"
                      :max="9999999999"
                      :min="form.unit"
                      :disabled="isDisabledQuantity"
                      :required="form.sendCorrectLogicJudgeFlag === 'BROKERINSERT'
                        || form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE'"
                      prop="quantity"
                      @change="quantityBlur"
                    ></ifa-input-quantity>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item
                      label="約定金額"
                      label-width="110px"
                    >
                      <span>{{ $_out($_withCommaInteger(contractAmount)) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <!-- 注文がある場合のみ表示する -->
                    <el-form-item
                      label="募集受注日時"
                      label-width="110px"
                    >
                      <span>{{ $_out($_getFormattedDateTimeValue(form.recruitmentOrderDate)) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item
                      label="募集取消日時"
                      label-width="110px"
                    >
                      <span>{{ $_out($_getFormattedDateTimeValue(form.subscriptCancelDayTime)) }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row class="input-section__input">
                  <el-col>
                    <el-form-item label="銘柄コード">
                      <!-- 注文がある場合、A001.銘柄コード（対面）を表示  -->
                      <span v-if="form.brandCode12">{{ $_out(form.brandCode12) }}</span>
                      <!-- 注文がない場合、A001.銘柄コード（BB）を表示  -->
                      <span v-else>{{ $_out(form.brandCode) }}</span>

                      <!-- ※銘柄コード１付きが"1"の場合、銘柄コードの末尾に”1”を追加表示する -->
                      <span v-if="form.brandCodeWith1 === '1'">{{ $_out(form.brandCodeWith1) }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row class="input-section__input">
                  <el-col>
                    <ifa-input-radio
                      id="DepositType"
                      v-model="inputField.depositType"
                      label="預り区分"
                      code-list-id="FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE"
                      :disp-pattern="1"
                      :select-pattern="setDepositSelect"
                      :disabled="isDisabledDepositType"
                      size="small"
                      :required="form.sendCorrectLogicJudgeFlag === 'BROKERINSERT'
                        || form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE'"
                      prop="depositType"
                    ></ifa-input-radio>
                  </el-col>
                </el-row>
                <el-row class="input-section__input">
                  <el-col>
                    <ifa-input-select
                      id="solicitTypeList"
                      v-model="inputField.kanyuKbn"
                      label="勧誘区分"
                      code-list-id="INVITATION_TYPE"
                      :disp-pattern="1"
                      :select-pattern="1"
                      :disabled="isDisabledKanyuKbn"
                      :clearable="false"
                      placeholder=" "
                      :required="form.sendCorrectLogicJudgeFlag === 'BROKERINSERT'
                        || form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE'"
                      prop="kanyuKbn"
                    ></ifa-input-select>
                  </el-col>
                </el-row>
                <el-row class="input-section__input">
                  <el-col>
                    <ifa-input-select
                      id="receiveOrderType"
                      v-model="inputField.jutyuKbn"
                      label="受注方法"
                      code-list-id="ORDER_METHOD_TYPE"
                      :disp-pattern="1"
                      :select-pattern="2"
                      :disabled="isDisabledJutyuKbn"
                      :clearable="false"
                      placeholder=" "
                      :required="form.sendCorrectLogicJudgeFlag === 'BROKERINSERT'
                        || form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE'
                        || form.sendCorrectLogicJudgeFlag === 'BROKERMODIFY'"
                      prop="jutyuKbn"
                    ></ifa-input-select>
                  </el-col>
                </el-row>
                <el-row class="input-section__input">
                  <el-col>
                    <ifa-input-select
                      id="mokuromiKoufuKbn"
                      v-model="inputField.mokuromiKoufuKbn"
                      label="目論見書の交付方法"
                      code-list-id="PROSPECTUS_ISSUE_METHOD"
                      :disp-pattern="2"
                      :select-pattern="1"
                      :disabled="isDisabledMokuromiKoufuKbn"
                      label-class="input-section__label--nowrap"
                      :clearable="false"
                      placeholder=" "
                      :required="form.sendCorrectLogicJudgeFlag === 'BROKERINSERT'
                        || form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE'
                        || form.sendCorrectLogicJudgeFlag === 'BROKERMODIFY'"
                      prop="mokuromiKoufuKbn"
                    ></ifa-input-select>
                  </el-col>
                </el-row>
                <el-row class="input-section__input">
                  <el-col>
                    <ifa-input-radio
                      id="importantMatterType"
                      v-model="inputField.importantMatterType"
                      label="重要事項の説明"
                      code-list-id="IMPORTANT_MATTERS_EXPLAIN"
                      :disp-pattern="1"
                      :select-pattern="1"
                      :disabled="isDisabledImportantMatterType"
                      size="small"
                      :required="form.sendCorrectLogicJudgeFlag === 'BROKERINSERT'
                        || form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE'
                        || form.sendCorrectLogicJudgeFlag === 'BROKERMODIFY'"
                      prop="importantMatterType"
                    ></ifa-input-radio>
                  </el-col>
                </el-row>
                <el-row class="input-section__input">
                  <el-col :span="24">
                    <!-- ■注文がある場合、A001.備考を表示する -->
                    <ifa-input-text
                      id="bbRemark"
                      v-model="inputField.bbRemark"
                      :domain="IfaText200DomainModel"
                      label="備考"
                      type="textarea"
                      size="small"
                      input-class="bb-remark--width"
                      original-screen-id="SUB0204_02-04_1"
                      :autosize="{ minRows: 4, maxRows: 6 }"
                      :disabled="isDisabledBbRemark"
                      prop="bbRemark"
                    ></ifa-input-text>
                  </el-col>
                </el-row>
              </el-row>
            </el-form>
          </el-card>
          <el-row class="button__wrapper">
            <el-col :span="1">
              <!-- 確認 (アクションID: A002) -->
              <ifa-button
                text="確認"
                :form="formRef"
                :disabled="isDisabledBtnConfirm"
                :msg-title="form.title.name"
                action-type="requestAction"
                action-id="SUB0204_02-04_1#A002"
                :request-model="ifaSubscriptInputA002RequestModel"
                @response-handler="responseHandlerA002"
                @response-error-handler="responseErrorHandlerA002"
              ></ifa-button>
            </el-col>
            <el-col
              :offset="21"
              :span="1"
            >
              <!-- 取消確認 (アクションID: A003) -->
              <ifa-button
                text="取消確認"
                :form="formRef"
                :disabled="isDisabledBtnCancelConfirm"
                :msg-title="form.title.name"
                action-id="SUB0204_02-04_1#A003"
                action-type="requestAction"
                :request-model="ifaSubscriptInputA003RequestModel"
                @response-handler="responseHandlerA003"
                @response-error-handler="responseErrorHandlerA003"
              ></ifa-button>
            </el-col>
          </el-row>
        </caption-card>
      </el-dialog>
    </div>

    <!-- ダイアログ -->
    <!-- 募集入力確認 -->
    <ifa-subscript-input-confirm
      ref="IfaSubscriptInputConfirm"
      :is-visible="dialogSubscriptInputConfirmVisible"
      @order-finish="handleOrderFinish"
      @close-modal="handleCloseModal"
    ></ifa-subscript-input-confirm>
    <!-- 募集入力完了 -->
    <ifa-subscript-input-complete
      ref="IfaSubscriptInputComplete"
      :is-visible="dialogSubscriptInputCompleteVisible"
      @close-modal="handleDialogCloseA004"
    ></ifa-subscript-input-complete>
  </div>
  <ifa-requester
    id="ifaSubscriptInputInitializeA001"
    action-type="requestAction"
    action-id="SUB0204_02-04_1#A001"
    :request-model="ifaSubscriptInputA001RequestModel"
    @response-handler="responseHandlerA001($event)"
    @response-error-handler="responseErrorHandlerA001($event)"
  ></ifa-requester>
</template>

<script>
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard'
import IfaSubscriptInputConfirm from './IfaSubscriptInputConfirm'
import IfaSubscriptInputComplete from './IfaSubscriptInputComplete'
import { IfaSubscriptInputA001RequestModel } from './js/IfaSubscriptInputA001RequestModel'
import { IfaSubscriptInputA002RequestModel } from './js/IfaSubscriptInputA002RequestModel'
import { IfaSubscriptInputA003RequestModel } from './js/IfaSubscriptInputA003RequestModel'
import { IfaSubscriptInputFormModel } from './js/IfaSubscriptInputFormModel'
import IfaStocks10DomainModel from '@/resource/domain/IfaStocks10DomainModel.json'
import IfaVolume10DomainModel from '@/resource/domain/IfaVolume10DomainModel.json'
import IfaText200DomainModel from '@/resource/domain/IfaText200DomainModel.json'
import { getMessage } from '@/utils/errorHandler'
import { BigNumber } from 'bignumber.js'

export default {
  components: {
    captionCard,
    IfaSubscriptInputConfirm,
    IfaSubscriptInputComplete
  },
  props: {
    isVisible: { type: Boolean, required: true }
  },
  emits: ['closeModal', 'open-modal'],
  data() {
    return {
      IfaStocks10DomainModel: IfaStocks10DomainModel,
      IfaVolume10DomainModel: IfaVolume10DomainModel,
      IfaText200DomainModel: IfaText200DomainModel,
      form: new IfaSubscriptInputFormModel(),
      formRef: {},
      depositType: '',
      // 入力項目
      inputField: {
        lotteryResult: '', // 抽選結果
        bbQuantityAlloc: '', // 当選株数
        orderStatus: '', // 注文状況
        quantity: '', // 数量
        depositType: '', // 預り区分
        kanyuKbn: '', // 勧誘区分
        jutyuKbn: '', // 受注方法
        mokuromiKoufuKbn: '', // 目論見書の交付方法
        importantMatterType: '', // 重要事項の説明
        bbRemark: '' // 備考
      },
      // hidden 項目
      hidden: {
        brandCode: '', // 銘柄コード（BB）
        brandCode12: '', // 銘柄コード（対面）
        warningApply: '', // ワーニング申請
        butenCode: '', // 部店コード
        accountNumber: '', // 口座番号
        bookBuildingPresentationFrom: '', // ブックビルディング申込期間（開始）
        detailNumber: '', // 明細番号
        brokerCode: '', // 仲介業者コード
        brokerChargeCode: '', // 仲介業者営業員コード
        dealerNumber: '', // 扱者コード
        customerCode: '', // 顧客コード
        presentationDate: '', // 上場日
        updateTimeForOrderExclusivity: '', // 更新時間（注文排他用）
        unit: '', // 売買単位
        sellBuyUnitType: '', // 売買単位区分
        maxAllocation: '', // 配分上限株数
        tokuteiKouzaKbn: '', // 特定口座区分
        juniorNisaFlag: '', // ジュニアNISAフラグ
        accumulateNisaFlag: '', // つみたてNISAフラグ
        isaContractType: '', // ISA契約区分
        isaBuyAbleJudgeClassificationYear: '', // ISA買付可能判定区分（当年）
        sendCorrectLogicJudgeFlag: '', // 送信・訂正用ロジック処理判定フラグ
        brandCodeWith1: '', // 銘柄コード１付き
        kanyuKbn: '', // 訂正前_勧誘区分
        jutyuKbn: '', // 訂正前_受注方法
        mokuromiKoufuKbn: '', // 訂正前_目論見書の交付方法
        importantMatterType: '', // 訂正前_重要事項の説明
        bbRemark: '', // 訂正前_備考
        quantity: '', // 訂正前_数量
        contractAmount: '', // 訂正前_約定金額
        depositType: '', // 訂正前_預り区分
        lotteryResult: '', // 訂正前_抽選結果 (v1.04)
        bbQuantityAlloc: '', // 訂正前_当選株数 (v1.04)
        orderStatus: '' // 訂正前_注文状況 (v1.04)
      },
      disabledForm: false,
      disabled: {
        mokuromiKoufuKbn: false,
        bbQuantityAlloc: false
      },
      dialogSubscriptInputConfirmVisible: false,
      dialogSubscriptInputCompleteVisible: false,
      errorInquiryBuyingPower: false,
      ifaSubscriptInputA001RequestModel: {},
      quantityError: false
    }
  },
  computed: {
    ifaSubscriptInputA002RequestModel() {
      // リクエストID A001 のレスポンス､入力項目､Hiddenからリクエストモデルを生成する
      const model = new IfaSubscriptInputA002RequestModel(this.form)

      // 注文がある場合は､銘柄コード（対面）｡注文が無い場合は､銘柄コード（BB）
      model.brandCode = this.form.brandCode12 ? this.form.brandCode12 : this.form.brandCode
      // 注文が登録済みの場合は､約定金額｡注文が未登録の場合は､数量*発行価格
      model.contractAmount = this.contractAmount

      // 入力項目から値を取得する
      model.lotteryResult = this.inputField.lotteryResult // 抽選結果
      model.bbQuantityAlloc = this.inputField.bbQuantityAlloc // 当選株数
      model.orderStatus = this.inputField.orderStatus // 注文状況
      model.quantity = this.inputField.quantity // 数量
      model.depositType = this.inputField.depositType // 預り区分
      model.kanyuKbn = this.inputField.kanyuKbn // 勧誘区分
      model.jutyuKbn = this.inputField.jutyuKbn // 受注方法
      model.mokuromiKoufuKbn = this.inputField.mokuromiKoufuKbn // 目論見書の交付方法
      model.importantMatterType = this.inputField.importantMatterType // 重要事項の説明
      model.bbRemark = this.inputField.bbRemark // 備考

      // Hiddenから値を取得する
      model.solicitTypeName = this.hidden.kanyuKbn // 訂正前_勧誘区分
      model.receiveOrderTypeName = this.hidden.jutyuKbn // 訂正前_受注方法
      model.prospectusIssueMethodWord = this.hidden.mokuromiKoufuKbn // 訂正前_目論見書の交付方法
      model.importantMatterType2 = this.hidden.importantMatterType // 訂正前_重要事項の説明
      model.bbRemark2 = this.hidden.bbRemark // 訂正前_備考
      model.domesticQuantityInput = this.hidden.quantity // 訂正前_数量
      model.subscriptTradeAmount = this.hidden.contractAmount // 訂正前_約定金額
      model.depositType2 = this.hidden.depositType // 訂正前_預り区分
      return model
    },
    ifaSubscriptInputA003RequestModel() {
      // リクエストID A001 のレスポンス､入力項目からリクエストモデルを生成する
      const model = new IfaSubscriptInputA003RequestModel(this.form)

      // 注文がある場合は､銘柄コード（対面）｡注文が無い場合は､銘柄コード（BB）
      model.brandCode = this.form.brandCode12 ? this.form.brandCode12 : this.form.brandCode
      // 注文が登録済みの場合は､約定金額｡注文が未登録の場合は､数量*発行価格
      model.contractAmount = this.contractAmount

      // Hiddenから値を取得する (v1.04)
      model.lotteryResult = this.hidden.lotteryResult // 訂正前_抽選結果
      model.bbQuantityAlloc = this.hidden.bbQuantityAlloc // 訂正前_当選株数
      model.orderStatus = this.hidden.orderStatus // 訂正前_注文状況
      model.quantity = this.hidden.quantity // 訂正前_数量
      model.contractAmount = this.hidden.contractAmount // 訂正前_約定金額
      model.depositType = this.hidden.depositType // 訂正前_預り区分
      model.kanyuKbn = this.hidden.kanyuKbn // 訂正前_勧誘区分
      model.jutyuKbn = this.hidden.jutyuKbn // 訂正前_受注方法
      model.mokuromiKoufuKbn = this.hidden.mokuromiKoufuKbn // 訂正前_目論見書の交付方法
      model.importantMatterType = this.hidden.importantMatterType // 訂正前_重要事項の説明
      model.bbRemark = this.hidden.bbRemark // 訂正前_備考

      return model
    },
    unit() {
      return this.form.unit
        ? this.$_withCommaInteger(this.form.unit) + this.$_out(this.form.sellBuyUnitType)
        : '-'
    },
    sellBuyUnitType() {
      return this.$_out(this.form.sellBuyUnitType)
    },
    orderStatusListPattern() {
      switch (this.form.sendCorrectLogicJudgeFlag) {
        case 'SBIINSERT':
        case 'SBIUPDATEONE':
          // SBIINSERT:管理者新規注文またはSBIUPDATEONE:管理者更新注文の場合
          return { selectPattern: 2, dispPattern: 1 }
        case 'SBIUPDATETWO':
          // SBIUPDATETWO:管理者訂正注の場合
          return { selectPattern: 3, dispPattern: 1 }
        default:
          // 上記以外
          return { selectPattern: 4, dispPattern: 1 }
      }
    },
    contractAmount() {
      if (this.form.contractAmount) {
        // ■注文が登録済みの場合：A001.約定金額
        return this.form.contractAmount
      } else if (!this.quantityError && this.inputField.quantity) {
        // ■注文が未登録の場合、数量を入力した後で入力チェックエラーがない場合、約定金額を表示する
        // 約定金額=画面の数量*画面の発行価格
        const quantity = new BigNumber(this.inputField.quantity)
        const issueBbPrice = new BigNumber(this.form.issueBbPrice)
        return quantity.multipliedBy(issueBbPrice)
      } else {
        // 入力チェックエラーがある場合
        return '-'
      }
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    // 上場日≦システム日付の算出
    isAfterListedDate() {
      // システム日時取得
      // サーバー側から共通でレスポンスされるrequestedTimeを使用
      // Note: 年月日にセパレータ('\-')が含まれる場合は削除
      // Note: 日時の形式の場合は日付だけ(' ')分割してとりだして比較する
      const requestedTime = this.$store.getters.requestedTime
      let systemDate
      if (requestedTime) {
        const temp = requestedTime.replaceAll('\/', ' ').split(' ')
        temp.pop()
        if (temp[0].length !== 4) {
          temp[0] = '20' + temp[0]
        }
        systemDate = temp.join('')
      }
      return this.form.presentationDate <= systemDate
    },
    isDisabledLotteryResult() {
      // ■ 抽選結果非活性状態
      // ・上場日≦システム日付の場合
      // ・訂正用ロジック処理判定フラグが "SBIUPDATETWO:管理者訂正注文" 又は "OTHER:その他"の場合
      // ・注文状況が募集取消の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId !== '1' ||
          this.form.sendCorrectLogicJudgeFlag === 'SBIUPDATETWO' || this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus === '6'
    },
    isDisabledOrderStatus() {
      // ■ 注文状況非活性状態
      // ・上場日≦システム日付の場合
      // ・抽選結果が"1：当選、3：当選(繰上)、4：裁量配分"以外の場合
      // ・訂正用ロジック処理判定フラグが "OTHER:その他"の場合
      // ・注文状況が募集取消の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId !== '1' ||
          !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4') ||
          this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus === '6'
    },
    isDisabledBbQuantityAlloc() {
      // ■ 当選株数非活性状態
      // ・上場日≦システム日付の場合
      // ・抽選結果が"1：当選、3：当選(繰上)、4：裁量配分"以外の場合
      // ・訂正用ロジック処理判定フラグが"OTHER:その他"の場合
      // ・注文状況が募集取消の場合
      // ・抽選結果は落選へ変更の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId !== '1' ||
          !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4') ||
          this.form.sendCorrectLogicJudgeFlag === 'OTHER' || this.form.sendCorrectLogicJudgeFlag === 'SBIUPDATETWO' ||
          this.inputField.orderStatus === '6' ||
          this.disabled.bbQuantityAlloc
    },
    isDisabledQuantity() {
      // ■ 数量非活性状態
      // ・上場日≦システム日付の場合
      // ・抽選結果が「当選／当選(繰上)／裁量」以外の場合
      // ・訂正用ロジック処理判定フラグが"BROKERMODIFY:仲介業者訂正注文(募集入力済み)" 又は "OTHER:その他"の場合
      // ・注文状況が募集取消の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId === '1' ||
          !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4') ||
          this.form.sendCorrectLogicJudgeFlag === 'BROKERMODIFY' || this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus === '6'
    },
    isDisabledDepositType() {
      // ■ 預り区分非活性状態
      // ・上場日≦システム日付の場合
      // ・抽選結果が「当選／当選(繰上)／裁量」以外の場合
      // ・訂正用ロジック処理判定フラグが"BROKERMODIFY:仲介業者訂正注文(募集入力済み)" 又は "OTHER:その他"の場合
      // ・注文状況が募集取消の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId === '1' ||
          !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4') ||
          this.form.sendCorrectLogicJudgeFlag === 'BROKERMODIFY' || this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus === '6'
    },
    isDisabledKanyuKbn() {
      // ■ 勧誘区分
      // ・上場日≦システム日付の場合
      // ・抽選結果が「当選／当選(繰上)／裁量」以外の場合
      // ・訂正用ロジック処理判定フラグが"OTHER:その他"の場合
      // ・注文状況が受付済、約定済、不出来、手入力、募集取消の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId === '1' ||
          !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4') ||
          this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus === '2' || this.inputField.orderStatus === '3' || this.inputField.orderStatus === '4' ||
          this.inputField.orderStatus === '5' || this.inputField.orderStatus === '6'
    },
    isDisabledJutyuKbn() {
      // ■ 受注方法
      // ・上場日≦システム日付の場合
      // ・抽選結果が「当選／当選(繰上)／裁量」以外の場合
      // ・訂正用ロジック処理判定フラグが"OTHER:その他"の場合
      // ・注文状況が受付済、約定済、不出来、手入力、募集取消の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId === '1' ||
          !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4') ||
          this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus === '2' || this.inputField.orderStatus === '3' || this.inputField.orderStatus === '4' ||
          this.inputField.orderStatus === '5' || this.inputField.orderStatus === '6'
    },
    isDisabledMokuromiKoufuKbn() {
      // ■ 目論見書の交付方法
      // ・上場日≦システム日付の場合
      // ・抽選結果が「当選／当選(繰上)／裁量」以外の場合
      // ・訂正用ロジック処理判定フラグが"OTHER:その他"の場合
      // ・注文状況が受付済、約定済、不出来、手入力、募集取消の場合
      // ・A001.閲覧日時（目論見書閲覧の確認日時）が登録されている口座の場合、「電子交付」が選択された状態にする。他の「郵送」「訪問」「店頭」は選択不可（非活性）
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId === '1' ||
          !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4') ||
          this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus === '2' || this.inputField.orderStatus === '3' || this.inputField.orderStatus === '4' ||
          this.inputField.orderStatus === '5' || this.inputField.orderStatus === '6' ||
          this.disabled.mokuromiKoufuKbn
    },
    isDisabledImportantMatterType() {
      // ■ 重要事項の説明
      // ・上場日≦システム日付の場合
      // ・抽選結果が「当選／当選(繰上)／裁量」以外の場合
      // ・訂正用ロジック処理判定フラグが"OTHER:その他"の場合
      // ・注文状況が受付済、約定済、不出来、手入力、募集取消の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId === '1' ||
          !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4') ||
          this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus === '2' || this.inputField.orderStatus === '3' || this.inputField.orderStatus === '4' ||
          this.inputField.orderStatus === '5' || this.inputField.orderStatus === '6'
    },
    isDisabledBbRemark() {
      // ■ 備考
      // ・上場日≦システム日付の場合
      // ・抽選結果が「当選／当選(繰上)／裁量」以外の場合
      // ・訂正用ロジック処理判定フラグが"OTHER:その他"の場合
      // ・注文状況が受付済、約定済、不出来、手入力、募集取消の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId === '1' ||
          !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4') ||
          this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus === '2' || this.inputField.orderStatus === '3' || this.inputField.orderStatus === '4' ||
          this.inputField.orderStatus === '5' || this.inputField.orderStatus === '6'
    },
    isDisabledBtnConfirm() {
      // ■ 確認ボタン
      // ・コンプラランクが’Ｚ’の場合
      // ・上場日≦システム日付の場合
      // ・抽選結果が「当選／当選(繰上)／裁量」以外の場合
      // ・訂正用ロジック処理判定フラグが"OTHER:その他"の場合
      // ・訂正用ロジック処理判定フラグが"BROKERMODIFY:仲介業者訂正注文(募集入力済み)"以外かつ「買付余力照会のNRI_API」呼出エラー（A001の⑨）の場合
      // ・注文状況が募集取消の場合
      return this.form.complianceRank === 'Z' || this.isAfterListedDate ||
          (this.$store.getters.userAccount.medUsers.privId !== '1' && !(this.inputField.lotteryResult === '1' || this.inputField.lotteryResult === '3' || this.inputField.lotteryResult === '4')) ||
          this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          (this.form.sendCorrectLogicJudgeFlag !== 'BROKERMODIFY' && this.errorInquiryBuyingPower) ||
          this.inputField.orderStatus === '6'
    },
    isDisabledBtnCancelConfirm() {
      // ■ 取消確認ボタン
      // ・上場日≦システム日付の場合
      // ・訂正用ロジック処理判定フラグが"OTHER:その他"の場合
      // ・注文状況が募集入力済以外の場合
      return this.isAfterListedDate || this.$store.getters.userAccount.medUsers.privId === '1' ||
          this.form.sendCorrectLogicJudgeFlag === 'OTHER' ||
          this.inputField.orderStatus !== '1'
    },
    setDepositSelect() {
      // (A001.ISA買付可能判定区分（当年）="1"or "2") かつ A001.ISA契約区分="1" かつ A001.ISA買付可能枠(当年)が0より大きい場合
      if ((this.form.isaBuyAbleJudgeClassificationYear === '1' || this.form.isaBuyAbleJudgeClassificationYear === '2') &&
          this.form.isaContractType === '1' &&
          Number(this.form.nisaBuyPotentialAmount) > 0) {
        // A001.特定口座区分="1"or"2"の場合
        if (this.form.tokuteiKouzaKbn === '1' || this.form.tokuteiKouzaKbn === '2') {
          return 3
        } else {
          return 4
        }
      } else {
        // A001.特定口座区分="1"or"2"の場合
        if (this.form.tokuteiKouzaKbn === '1' || this.form.tokuteiKouzaKbn === '2') {
          return 5
        } else {
          return 6
        }
      }
    }
  },
  watch: {
    'inputField.lotteryResult'(newValue) {
      if (newValue === '2') {
        this.inputField.bbQuantityAlloc = '0'
      }
    }
  },
  methods: {
    onDialogOpen() {
      this.formRef = this.$refs['form']
      // 数量のバリデーションチェック
      if (this.form.quantity && !this.form.contractAmount) {
        this.quantityBlur()
      }
    },
    // A001: 初期化のレスポンス処理 (response は BB申込一覧の A006 で受信したもの)
    onShow(a001RequestModel) {
      this.ifaSubscriptInputA001RequestModel = new IfaSubscriptInputA001RequestModel(a001RequestModel)
      document.querySelector('#ifaSubscriptInputInitializeA001').click()
    },
    // 初期化
    init() {
      this.form = new IfaSubscriptInputFormModel()
      this.disabledForm = false
      this.disabled.mokuromiKoufuKbn = false
      this.disabled.bbQuantityAlloc = false

      this.dialogSubscriptInputConfirmVisible = false
      this.dialogSubscriptInputCompleteVisible = false

      this.clearInputField()
      this.clearHiddenData()
    },
    responseHandlerA001(response) {
      this.init()
      Object.assign(this.form, response.dataList[0])
      // 「買付余力照会のNRI_API」呼出エラー（A001の⑨）
      this.errorInquiryBuyingPower = response.message === getMessage('errors.nriQueryAccountBalance')
      this.setHiddenData()
      this.setInputField()
      this.formRef = this.$refs['form']
      this.$emit('open-modal')
    },
    responseErrorHandlerA001(response) {
      if (response.message === getMessage('errors.nriQueryAccountBalance')) {
        this.responseHandlerA001(response)
      } else {
        this.$_logDebug(response)
      }
    },
    // A002: 確認のレスポンス処理
    responseHandlerA002(response) {
      this.$refs['IfaSubscriptInputConfirm'].onShow(response.dataList[0])
      this.dialogSubscriptInputConfirmVisible = true
    },
    // A002: 確認のレスポンスエラー処理
    responseErrorHandlerA002(response) {
      this.$_logDebug(response)
    },
    // A003: 取消確認のレスポンス処理
    responseHandlerA003(response) {
      this.$refs['IfaSubscriptInputConfirm'].onShow(response.dataList[0])
      this.dialogSubscriptInputConfirmVisible = true
    },
    // A003: 取消確認のレスポンスエラー処理
    responseErrorHandlerA003(response) {
      this.$_logDebug(response)
    },
    // 初期化(A001)のレスポンスから入力項目を初期化する
    setInputField() {
      this.inputField.lotteryResult = this.form.lotteryResult
      this.inputField.bbQuantityAlloc = this.form.bbQuantityAlloc
      this.inputField.orderStatus = this.form.orderStatus
      this.inputField.quantity = this.form.quantity
      this.depositType = this.inputField.depositType = this.form.depositType
      // ■注文がある場合、A001.勧誘区分を初期選択
      if (this.form.kanyuKbn) { this.inputField.kanyuKbn = this.form.kanyuKbn }
      // ■注文がある場合、A001.受注方法を初期選択
      if (this.form.jutyuKbn) { this.inputField.jutyuKbn = this.form.jutyuKbn }
      // 注文がある場合、A001.最重要事項の説明を初期選択
      if (this.form.importantMatterType) { this.inputField.importantMatterType = this.form.importantMatterType }
      // ■注文がある場合、A001.備考を表示する
      if (this.form.bbRemark) { this.inputField.bbRemark = this.form.bbRemark }
      if (this.form.depositType) {
        // ■注文がある場合、A001.預り区分を初期選択
        this.inputField.depositType = this.form.depositType
      } else {
        // ■注文がない場合：
        if (this.form.tokuteiKouzaKbn === '1' || this.form.tokuteiKouzaKbn === '2') {
          // ・A001.特定口座区分="1"or"2"の場合："特定"を初期選択
          this.inputField.depositType = '2'
        } else {
          // ・A001.特定口座区分=上記以外の場合："一般"を初期選択、特定を非活性
          this.inputField.depositType = '1'
        }
      }
      // 目論見書の交付方法
      this.disabled.mokuromiKoufuKbn = false
      if (this.form.readTime) {
        // A001.閲覧日時（目論見書閲覧の確認日時）が登録されている口座の場合、「電子交付」が選択された状態にする。
        // 他の「郵送」「訪問」「店頭」は選択不可（非活性）
        this.inputField.mokuromiKoufuKbn = '3'
        this.disabled.mokuromiKoufuKbn = true
      } else if (this.form.prospectus) {
        // ■注文がある場合、A001.目論見書を初期選択
        this.inputField.mokuromiKoufuKbn = this.form.prospectus
      }
    },
    // hidden 項目についてはサンプル的実装
    // 他画面では FormModel に `hidden+物理名` で定義されているが､
    // FormModel にある Hidden 項目はツールを使って出力する時に画面定義書の画面項目定義の Hidden 項目までが
    // 出力の対象となってしまっているためで､物理名などが重複したりと問題を含んでいる｡
    // そのため､hidden を画面の data に持つ実装を参考として行う｡
    // その他のソリューションとして考えられるのは､FormModel の hidden 項目を `hidden+物理名` ではなく
    // hidden.物理名のような表現にすることで物理名の重複を避けることが可能｡
    setHiddenData() {
      this.hidden.brandCode = this.form.brandCode // 銘柄コード（BB）
      this.hidden.brandCode12 = this.form.brandCode12 // 銘柄コード（対面）
      this.hidden.warningApply = this.form.warningApply // ワーニング申請
      this.hidden.butenCode = this.form.butenCode // 部店コード
      this.hidden.accountNumber = this.form.accountNumber // 口座番号
      this.hidden.bookBuildingPresentationFrom = this.form.bookBuildingPresentationFrom // ブックビルディング申込期間（開始）
      this.hidden.detailNumber = this.form.detailNumber // 明細番号
      this.hidden.brokerCode = this.form.brokerCode // 仲介業者コード
      this.hidden.brokerChargeCode = this.form.brokerChargeCode // 仲介業者営業員コード
      this.hidden.dealerNumber = this.form.dealerNumber // 扱者コード
      this.hidden.customerCode = this.form.customerCode // 顧客コード
      this.hidden.customerCode = this.form.customerCode // 上場日
      this.hidden.updateTimeForOrderExclusivity = this.form.updateTimeForOrderExclusivity // 更新時間（注文排他用）
      this.hidden.unit = this.form.unit // 売買単位
      this.hidden.sellBuyUnitType = this.form.sellBuyUnitType // 売買単位区分
      this.hidden.maxAllocation = this.form.maxAllocation // 配分上限株数
      this.hidden.maxAllocation = this.form.maxAllocation // 特定口座区分
      this.hidden.juniorNisaFlag = this.form.juniorNisaFlag // ジュニアNISAフラグ
      this.hidden.accumulateNisaFlag = this.form.accumulateNisaFlag // つみたてNISAフラグ
      this.hidden.isaContractType = this.form.isaContractType // ISA契約区分
      this.hidden.isaBuyAbleJudgeClassificationYear = this.form.isaBuyAbleJudgeClassificationYear // ISA買付可能判定区分（当年）
      this.hidden.sendCorrectLogicJudgeFlag = this.form.sendCorrectLogicJudgeFlag // 送信・訂正用ロジック処理判定フラグ
      this.hidden.brandCodeWith1 = this.form.brandCodeWith1 // 銘柄コード１付き
      this.hidden.kanyuKbn = this.form.kanyuKbn // 訂正前_勧誘区分
      this.hidden.jutyuKbn = this.form.jutyuKbn // 訂正前_受注方法
      this.hidden.mokuromiKoufuKbn = this.form.prospectus // 訂正前_目論見書の交付方法
      this.hidden.importantMatterType = this.form.importantMatterType // 訂正前_重要事項の説明
      this.hidden.bbRemark = this.form.bbRemark // 訂正前_備考
      this.hidden.quantity = this.form.quantity // 訂正前_数量
      this.hidden.contractAmount = this.form.contractAmount // 訂正前_約定金額
      this.hidden.depositType = this.form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE' ? '' : this.form.depositType // 訂正前_預り区分
      this.hidden.lotteryResult = this.form.lotteryResult // 訂正前_抽選結果 (v1.04)
      this.hidden.bbQuantityAlloc = this.form.bbQuantityAlloc // 訂正前_当選株数 (v1.04)
      this.hidden.orderStatus = this.form.orderStatus // 訂正前_注文状況 (v1.04)
    },
    // 入力項目をクリアする
    clearInputField() {
      Object.assign(this.inputField, {
        lotteryResult: '', // 抽選結果
        bbQuantityAlloc: '', // 当選株数
        orderStatus: '', // 注文状況
        quantity: '', // 数量
        depositType: '', // 預り区分
        kanyuKbn: '', // 勧誘区分
        jutyuKbn: '', // 受注方法
        mokuromiKoufuKbn: '', // 目論見書の交付方法
        importantMatterType: '', // 重要事項の説明
        bbRemark: '' // 備考
      })
    },
    // hidden 項目をクリアする
    clearHiddenData() {
      Object.assign(this.hidden, {
        brandCode: '', // 銘柄コード（BB）
        brandCode12: '', // 銘柄コード（対面）
        warningApply: '', // ワーニング申請
        butenCode: '', // 部店コード
        accountNumber: '', // 口座番号
        bookBuildingPresentationFrom: '', // ブックビルディング申込期間（開始）
        detailNumber: '', // 明細番号
        brokerCode: '', // 仲介業者コード
        brokerChargeCode: '', // 仲介業者営業員コード
        dealerNumber: '', // 扱者コード
        customerCode: '', // 顧客コード
        presentationDate: '', // 上場日
        updateTimeForOrderExclusivity: '', // 更新時間（注文排他用）
        unit: '', // 売買単位
        sellBuyUnitType: '', // 売買単位区分
        maxAllocation: '', // 配分上限株数
        tokuteiKouzaKbn: '', // 特定口座区分
        juniorNisaFlag: '', // ジュニアNISAフラグ
        accumulateNisaFlag: '', // つみたてNISAフラグ
        isaContractType: '', // ISA契約区分
        isaBuyAbleJudgeClassificationYear: '', // ISA買付可能判定区分（当年）
        sendCorrectLogicJudgeFlag: '', // 送信・訂正用ロジック処理判定フラグ
        brandCodeWith1: '', // 銘柄コード１付き
        kanyuKbn: '', // 訂正前_勧誘区分
        jutyuKbn: '', // 訂正前_受注方法
        mokuromiKoufuKbn: '', // 訂正前_目論見書の交付方法
        importantMatterType: '', // 訂正前_重要事項の説明
        bbRemark: '', // 訂正前_備考
        quantity: '', // 訂正前_数量
        contractAmount: '', // 訂正前_約定金額
        depositType: '', // 訂正前_預り区分
        lotteryResult: '', // 訂正前_抽選結果 (v1.04)
        bbQuantityAlloc: '', // 訂正前_当選株数 (v1.04)
        orderStatus: '' // 訂正前_注文状況 (v1.04)
      })
    },
    // A004: 戻るボタン / 募集入力完了画面からの戻り
    handleDialogCloseA004() {
      this.init()
      this.$emit('closeModal')
    },
    // A005: 確認/取消確認からの戻り初期化 (response は募集入力確認の A008 で受信したもの)
    handleCloseModal(response) {
      this.dialogSubscriptInputConfirmVisible = false

      // A001と同じ処理を行う
      Object.assign(this.form, response.dataList[0])

      // 「買付余力照会のNRI_API」呼出エラー（A001の⑨）
      this.errorInquiryBuyingPower = response.message === getMessage('errors.nriQueryAccountBalance')

      // Hidden や入力済みの項目は変更しない
    },
    // 募集入力確認完了処理
    handleOrderFinish(form) {
      this.$refs['IfaSubscriptInputComplete'].onShow(form)
      this.dialogSubscriptInputConfirmVisible = false
      this.dialogSubscriptInputCompleteVisible = true
    },
    // 抽選結果の選択変更処理
    handleChangeLotteryResult(newValue) {
      // 抽選結果は落選へ変更の場合、当選株数を"0"を設定する。非活性にする。
      if (newValue === 2) {
        this.inputField.bbQuantityAlloc = 0
        this.disabled.bbQuantityAlloc = true
      } else {
        this.disabled.bbQuantityAlloc = false
      }
    },
    quantityBlur() {
      if (this.$refs['form']) {
        this.$refs['form'].validateField('quantity').catch(() => {
          this.quantityError = true
          return
        })
        this.quantityError = false
        return
      }
    }
  }
}

</script>

<style lang="scss" scoped>
.ifa-subscript-input__wrapper {
  .main-dialog {
    :deep(.el-dialog) {
      background-color: #fef0f0;
    }
    :deep(.el-dialog__header) {
      display: none;
    }
    :deep(.el-dialog__body) {
      background-color: #fef0f0;
      padding: 0;
    }
    &__back-button {
      &__wrapper {
        display: relative;
      }
      &__content {
        position: absolute;
        top: calc(1.5rem - 1px);
        right: 2rem;
      }
    }
    :deep(.el-card.box-card) {
        background-color: #fef0f0;
        margin: 0;
        padding: 1rem;
    }
  }
  .info-section {
    margin: 0 1rem;
    :deep(.el-form-item__label) {
      font-weight: bold;
    }
    &__content {
      line-height: 24px;
    }
    &__title {
      font-size: 18px;
      margin: 0.5rem 0 0.2rem 0.5rem;
    }
    &__label {
      line-height: 24px;
      justify-content: flex-start;
    }
    &__value {
      line-height: 24px;
      display: flex;
      white-space: nowrap;
      &--red {
        margin-left: 1.25rem;
        color: red;
        font-weight: bold;
      }
    }
  }
  .input-section {
    :deep(.el-form-item__label) {
      font-weight: bold;
      line-height: 24px;
    }
    &__title {
      font-size: 18px;
      margin: 0.5rem 0 0.2rem 0.5rem;
    }
    &__input {
      margin: 10px;
      border-bottom: 1px solid #eee;
    }
    &__label--nowrap {
      white-space: nowrap;
    }
  }
  .lottery-result__wrapper {
    :deep(.el-form-item__content) {
      width: 200px;
    }
  }
  .order-status__wrapper {
    :deep(.el-form-item__content) {
      margin-left: -2.5rem;
      width: 200px;
    }
  }
  .bb-quantity-alloc__wrapper {
    :deep(.el-form-item__content) {
      margin-left: -2rem;
    }
    :deep(.ifa-input_base__control_wrapper) {
      margin-left: 40px;
    }
    :deep(.el-input__wrapper) {
      width: 135px;
      height: 28px;
    }
  }
  :deep(.bb-remark--width.el-textarea) {
    width: 100em !important;
  }
  .button__wrapper {
    margin-top: 1rem;
  }
}
</style>
