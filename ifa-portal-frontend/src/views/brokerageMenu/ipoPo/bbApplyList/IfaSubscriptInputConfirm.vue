<template>
  <div>
    <el-dialog
      :model-value="isVisible"
      :center="true"
      width="1200px"
      class="order-status-list__el-dialog--disabled"
      style="background-color: #fef0f0; padding-top: 1rem;"
      :show-close="false"
      :close-on-click-modal="false"
    >
      <!-- タイトル -->
      <template #header>
        <span class="title">{{ form.title.name }}</span>
      </template>

      <!-- 戻るボタン -->
      <el-row>
        <el-col
          :offset="1"
          :span="22"
          style="text-align: right;"
        >
          <ifa-button
            text="戻る"
            color="secondary"
            style="padding-right: 0;"
            action-type="originalAction"
            @app-action-handler="handleBack"
          ></ifa-button>
        </el-col>
      </el-row>

      <!-- エラー/警告表示 -->
      <ifa-message-area
        :key="refreshMessage"
        :main-messages="messages.mains"
        :error-messages="messages.errors"
        :warning-messages="messages.warnings"
        :info-messages="messages.infos"
      ></ifa-message-area>

      <!-- 顧客情報 -->
      <el-row>
        <el-col
          :offset="1"
          class="_bold_black_l"
        >
          <span>{{ $_out(form.butenCode) }}</span>
          <span>-</span>
          <span>{{ $_out($_zeroPadding(form.accountNumber, 7)) }}</span>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          style="font-size: 20px;"
          class="_bold_black_l"
          :offset="1"
          :span="22"
        >
          <el-icon style="position: relative; top: 3px;">
            <!--  ■顧客共通情報.法人区分　＝　1（法人）の場合　法人アイコン
                    ■上記以外の場合　個人アイコン -->
            <OfficeBuilding v-if="form.officeName === '-'"></OfficeBuilding>
            <Avatar v-else></Avatar>
          </el-icon>
          <span>{{ $_out(form.customerNameKanji) }}</span>
          <span>（{{ $_out(form.customerNameKana) }}）</span>
          <!-- A009: 注意情報 -->
          <ifa-notice-info
            :notice-info-level="form.noticeInfoLevel"
            :customer-code="form.customerCode"
            :buten-code="form.butenCode"
            :account-number="`${$_zeroPadding(form.accountNumber, 7)}`"
            style="position: relative; top: 4px;"
          ></ifa-notice-info>
        </el-col>
      </el-row>

      <!-- 銘柄情報 -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="padding: 0.3rem 0 0.6rem 0"
        >
          <el-card
            class="box-card"
            style="background-color: #eee;"
          >
            <el-row
              class="_bold_black_l"
              style="font-size: 20px;"
            >
              <el-col
                :span="24"
                style="display: flex; align-items: flex-start;"
              >
                <span style="display: inline-block; white-space: nowrap;">［{{ $_out(form.brandCode) }}］</span>
                <span style="display: inline-block;">{{ $_out(form.brandName) }}</span>
              </el-col>
            </el-row>

            <el-row
              class="brand-area__wrapper"
            >
              <el-col :span="8">
                <span>抽選結果：</span>
                <ifa-text
                  code-list-id="SUBSCRIPT_BB_DRAWING_RESULT"
                  :disp-pattern="2"
                  :code-key="form.lotteryResult"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
              <el-col :span="8">
                <span>当選株数：</span>
                <span>{{ $_out($_withCommaInteger(form.bbQuantityAlloc)) }}</span>
                <span>{{ $_out(form.sellBuyUnitType) }}</span>
              </el-col>
              <el-col :span="8">
                <span>注文状況：</span>
                <ifa-text
                  code-list-id="SUBSCRIPT_ORDER_STATUS"
                  :disp-pattern="1"
                  :code-key="form.orderStatus"
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
              style="padding: 0.5rem; padding-left: 1rem;"
            ><span>ご注文内容（復唱項目）</span></el-row>

            <hr>

            <!-- 数量 -->
            <el-row
              class="dotted_border"
            >
              <el-col
                class="_bold_black_m"
                :span="isCorrectionOrder ? 6 : 8"
              ><span>数量</span></el-col>

              <!-- 数量を表示 -->
              <el-col
                :span="7"
              >
                <span>{{ $_out($_withCommaInteger(form.quantity)) }}</span>
                <span>{{ $_out(form.sellBuyUnitType) }}</span>
              </el-col>
            </el-row>

            <!-- 約定金額 -->
            <el-row
              class="dotted_border"
            >
              <el-col
                class="_bold_black_m"
                :span="isCorrectionOrder ? 6 : 8"
              ><span>約定金額</span></el-col>

              <!-- 約定金額を表示 -->
              <el-col
                :span="7"
              >
                <span>{{ $_out($_withCommaInteger(form.contractAmount)) }}</span>
                <span>円</span>
              </el-col>
            </el-row>

            <!-- 預り区分 -->
            <el-row
              class="dotted_border"
            >
              <el-col
                class="_bold_black_m"
                :span="isCorrectionOrder ? 6 : 8"
              ><span>預り区分</span></el-col>

              <!-- 預り区分を表示 -->
              <el-col
                :span="7"
              >
                <ifa-text
                  code-list-id="FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE"
                  :disp-pattern="1"
                  :code-key="form.depositType"
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
              style="padding: 0.5rem 0 0 0.5rem; padding-left: 1rem;"
            ><span>その他注文内容</span></el-row>

            <!-- 注文フラグが"3"(訂正注文)の場合のみ表示 -->
            <el-row
              v-if="isCorrectionOrder"
              class="_bold_black_m"
              style="padding: 0.5rem;"
            >
              <el-col
                :span="5"
                :offset="6"
              ><span>訂正前</span></el-col>
              <el-col :span="5"><span class="el-icon-right arrow">→</span></el-col>
              <el-col :span="7"><span>訂正後</span></el-col>
            </el-row>

            <hr>

            <!-- 勧誘区分 -->
            <el-row
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  isCorrectionOrder &&
                  form.solicitTypeName != form.kanyuKbn
              }"
            >
              <el-col :span="isCorrectionOrder ? 6 : 8"><span class="_bold_black_m">勧誘区分</span></el-col>

              <!-- 注文フラグが"3"(訂正注文)の場合のみ､訂正前_勧誘区分を表示 -->
              <el-col
                v-if="isCorrectionOrder"
                :span="10"
              >
                <ifa-text
                  code-list-id="INVITATION_TYPE"
                  :disp-pattern="1"
                  :code-key="form.solicitTypeName"
                ></ifa-text>
              </el-col>
              <!-- 勧誘区分を表示 -->
              <el-col
                :span="7"
                :class="{
                  '__bold':
                    isCorrectionOrder &&
                    form.solicitTypeName != form.kanyuKbn
                }"
              >
                <ifa-text
                  code-list-id="INVITATION_TYPE"
                  :disp-pattern="1"
                  :code-key="form.kanyuKbn"
                ></ifa-text>
              </el-col>
            </el-row>

            <!-- 受注方法 -->
            <el-row
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  isCorrectionOrder &&
                  form.receiveOrderTypeName != form.jutyuKbn
              }"
            >
              <el-col :span="isCorrectionOrder ? 6 : 8"><span class="_bold_black_m">受注方法</span></el-col>

              <!-- 注文フラグが"3"(訂正注文)の場合のみ､訂正前_受注方法を表示 -->
              <el-col
                v-if="isCorrectionOrder"
                :span="10"
              >
                <ifa-text
                  code-list-id="ORDER_METHOD_TYPE"
                  :disp-pattern="1"
                  :code-key="form.receiveOrderTypeName"
                ></ifa-text>
              </el-col>
              <!-- 受注方法を表示 -->
              <el-col
                :span="7"
                :class="{
                  '__bold':
                    isCorrectionOrder &&
                    form.receiveOrderTypeName != form.jutyuKbn
                }"
              >
                <ifa-text
                  code-list-id="ORDER_METHOD_TYPE"
                  :disp-pattern="1"
                  :code-key="form.jutyuKbn"
                ></ifa-text>
              </el-col>
            </el-row>

            <!-- 目論見書の交付方法 -->
            <el-row
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  isCorrectionOrder &&
                  form.prospectusIssueMethodWord != form.mokuromiKoufuKbn
              }"
            >
              <el-col :span="isCorrectionOrder ? 6 : 8"><span class="_bold_black_m">目論見書の交付方法</span></el-col>

              <!-- 注文フラグが"3"(訂正注文)の場合のみ､訂正前_目論見書の交付方法を表示 -->
              <el-col
                v-if="isCorrectionOrder"
                :span="10"
              >
                <ifa-text
                  code-list-id="PROSPECTUS_ISSUE_METHOD"
                  :disp-pattern="2"
                  :code-key="form.prospectusIssueMethodWord"
                ></ifa-text>
              </el-col>
              <!-- 目論見書の交付方法を表示 -->
              <el-col
                :span="7"
                :class="{
                  '__bold':
                    isCorrectionOrder &&
                    form.prospectusIssueMethodWord != form.mokuromiKoufuKbn
                }"
              >
                <ifa-text
                  code-list-id="PROSPECTUS_ISSUE_METHOD"
                  :disp-pattern="2"
                  :code-key="form.mokuromiKoufuKbn"
                ></ifa-text>
              </el-col>
            </el-row>

            <!-- 重要事項の説明 -->
            <el-row
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  isCorrectionOrder &&
                  form.importantMatterType2 != form.importantMatterType
              }"
            >
              <el-col :span="isCorrectionOrder ? 6 : 8"><span class="_bold_black_m">重要事項の説明</span></el-col>

              <!-- 注文フラグが"3"(訂正注文)の場合のみ､訂正前_重要事項の説明の交付方法を表示 -->
              <el-col
                v-if="isCorrectionOrder"
                :span="10"
              >
                <ifa-text
                  code-list-id="IMPORTANT_MATTERS_EXPLAIN"
                  :disp-pattern="1"
                  :code-key="form.importantMatterType2"
                ></ifa-text>
              </el-col>
              <!-- 重要事項の説明を表示 -->
              <el-col
                :span="7"
                :class="{
                  '__bold':
                    isCorrectionOrder &&
                    form.importantMatterType2 != form.importantMatterType
                }"
              >
                <ifa-text
                  code-list-id="IMPORTANT_MATTERS_EXPLAIN"
                  :disp-pattern="1"
                  :code-key="form.importantMatterType"
                ></ifa-text>
              </el-col>
            </el-row>

            <!-- 備考 -->
            <el-row
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  isCorrectionOrder &&
                  $_out(form.bbRemark2) != $_out(form.bbRemark)
              }"
            >
              <el-col :span="isCorrectionOrder ? 6 : 8"><span class="_bold_black_m">備考</span></el-col>

              <!-- 注文フラグが"3"(訂正注文)の場合､訂正前_備考と備考を両方表示 -->
              <template v-if="isCorrectionOrder">
                <el-col :span="7"><span class="bbremark__textarea">{{ $_out(form.bbRemark2) }}</span></el-col>
                <!-- 備考を表示 -->
                <el-col
                  :span="7"
                  :offset="3"
                  :class="{
                    '__bold':
                      isCorrectionOrder &&
                      $_out(form.bbRemark2) != $_out(form.bbRemark)
                  }"
                ><span class="bbremark__textarea">{{ $_out(form.bbRemark) }}</span></el-col>
              </template>
              <!-- 注文フラグが"3"(訂正注文)以外の場合､備考のみ表示 -->
              <template v-else>
                <!-- 備考を表示 -->
                <el-col :span="15"><span class="bbremark__textarea">{{ $_out(form.bbRemark) }}</span></el-col>
              </template>
            </el-row>
          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <div style="margin-bottom: 0.5rem;"></div>
      </el-row>

      <!-- アラート内容確認 -->
      <!-- アラートが1件もない場合はエリアを非表示 -->
      <el-row v-if="isDisplayAlertConfirmation">
        <el-col
          :span="22"
          :offset="1"
        >
          <el-card
            class="box-card alert_content"
            body-style="background-color: #fef0f0;"
            style="font-size: 16px;"
          >
            <el-form
              ref="inputField"
              :model="inputField"
              :inline="true"
              label-position="left"
              label-width="194px"
              class="input-section"
            >
              <el-row
                style="padding: 0.5rem 0 0 0.5rem; padding-left: 1rem;"
              ><span class="_bold_red_m">アラート内容確認</span></el-row>
              <hr>

              <!-- コンプラランクチェック.チェックボックス文言 -->
              <el-row
                v-if="form.complianceRankCheck.message"
                class="dotted_border"
                style="align-items: center;"
              >
                <el-col
                  :span="isCorrectionOrder ? 6 : 8"
                  class="_bold_red_m"
                >
                  <div class="required-mark">*</div><span style="color: #f00;">{{ invitationCheck }}</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-input-check
                    v-model="inputField.invitationCheck"
                    prop="invitationCheck"
                    code-list-id="original"
                    :original-list="invitationCheckOptions"
                    required
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
                  :span="isCorrectionOrder ? 6 : 8"
                  class="_bold_red_m"
                >
                  <div class="required-mark">*</div><span style="color: #f00;">注意情報の確認</span>
                </el-col>
                <el-col :span="16">
                  <ifa-input-check
                    v-model="inputField.noticeInfoAlert"
                    prop="noticeInfoAlert"
                    code-list-id="NOTICE_INFO_CONFIRM"
                    :select-pattern="2"
                    :disp-pattern="1"
                    required
                  ></ifa-input-check>
                </el-col>
              </el-row>

              <!-- お知らせアラート確認 -->
              <el-row
                v-if="form.noticeAlert"
                class="dotted_border"
                style="align-items: center;"
              >
                <el-col
                  :span="isCorrectionOrder ? 6 : 8"
                  class="_bold_red_m"
                >
                  <div class="required-mark">*</div><span style="color: #f00;">重要なお知らせの確認</span>
                </el-col>
                <el-col :span="16">
                  <ifa-input-check
                    v-model="inputField.noticeAlert"
                    prop="noticeAlert"
                    code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                    :select-pattern="2"
                    :disp-pattern="1"
                    required
                  ></ifa-input-check>
                </el-col>
              </el-row>

              <!-- 預り区分アラート確認 -->
              <el-row
                v-if="form.depositTypeAlert"
                class="dotted_border"
                style="align-items: center;"
              >
                <el-col
                  :span="isCorrectionOrder ? 6 : 8"
                  class="_bold_red_m"
                >
                  <div class="required-mark">*</div><span style="color: #f00;">預り区分の確認</span>
                </el-col>
                <el-col :span="16">
                  <ifa-input-check
                    v-model="inputField.depositTypeAlert"
                    prop="depositTypeAlert"
                    code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                    :select-pattern="2"
                    :disp-pattern="1"
                    required
                  ></ifa-input-check>
                </el-col>
              </el-row>
            </el-form>

          </el-card>
        </el-col>
      </el-row>

      <el-row style="margin-top: 1.5rem;">
        <el-col
          :offset="1"
          :span="22"
          style="text-align: left;"
        >
          <ifa-button
            :text="$_out(btnLabel)"
            :disabled="buttonDisabled"
            style="padding-left: 0;"
            action-type="originalAction"
            @app-action-handler="handleSubmitOrder"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- リクエスタ -->
    <!-- 注文（仲介業者新規） (アクションID: A001) -->
    <ifa-requester
      id="ifaSubscriptInputConfirmA001"
      action-id="SUB0204_02-04_2#A001"
      action-type="requestAction"
      :request-model="ifaSubscriptInputConfirmA001RequestModel"
      @response-handler="responseHandlerA001"
      @response-error-handler="responseErrorHandlerA001"
    ></ifa-requester>
    <!-- 更新（管理者新規） (アクションID: A002) -->
    <ifa-requester
      id="ifaSubscriptInputConfirmA002"
      action-id="SUB0204_02-04_2#A002"
      action-type="requestAction"
      :request-model="ifaSubscriptInputConfirmA002RequestModel"
      @response-handler="responseHandlerA002"
      @response-error-handler="responseErrorHandlerA002"
    ></ifa-requester>
    <!-- 更新（管理者更新） (アクションID: A003) -->
    <ifa-requester
      id="ifaSubscriptInputConfirmA003"
      action-id="SUB0204_02-04_2#A003"
      action-type="requestAction"
      :request-model="ifaSubscriptInputConfirmA003RequestModel"
      @response-handler="responseHandlerA003"
      @response-error-handler="responseErrorHandlerA003"
    ></ifa-requester>
    <!-- 更新（管理者訂正） (アクションID: A004) -->
    <ifa-requester
      id="ifaSubscriptInputConfirmA004"
      action-id="SUB0204_02-04_2#A004"
      action-type="requestAction"
      :request-model="ifaSubscriptInputConfirmA004RequestModel"
      @response-handler="responseHandlerA004"
      @response-error-handler="responseErrorHandlerA004"
    ></ifa-requester>
    <!-- 訂正（仲介業者更新） (アクションID: A005) -->
    <ifa-requester
      id="ifaSubscriptInputConfirmA005"
      action-id="SUB0204_02-04_2#A005"
      action-type="requestAction"
      :request-model="ifaSubscriptInputConfirmA005RequestModel"
      @response-handler="responseHandlerA005"
      @response-error-handler="responseErrorHandlerA005"
    ></ifa-requester>
    <!-- 訂正（仲介業者訂正） (アクションID: A006) -->
    <ifa-requester
      id="ifaSubscriptInputConfirmA006"
      action-id="SUB0204_02-04_2#A006"
      action-type="requestAction"
      :request-model="ifaSubscriptInputConfirmA006RequestModel"
      @response-handler="responseHandlerA006"
      @response-error-handler="responseErrorHandlerA006"
    ></ifa-requester>
    <!-- 取消 (アクションID: A007) -->
    <ifa-requester
      id="ifaSubscriptInputConfirmA007"
      action-id="SUB0204_02-04_2#A007"
      action-type="requestAction"
      :request-model="ifaSubscriptInputConfirmA007RequestModel"
      @response-handler="responseHandlerA007"
      @response-error-handler="responseErrorHandlerA007"
    ></ifa-requester>
    <!-- 戻り初期化 (アクションID: A008) -->
    <ifa-requester
      id="ifaSubscriptInputConfirmA008"
      action-id="SUB0204_02-04_2#A008"
      action-type="requestAction"
      :request-model="ifaSubscriptInputConfirmA008RequestModel"
      @response-handler="responseHandlerA008"
      @response-error-handler="responseErrorHandlerA008"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaSubscriptInputConfirmFormModel } from './js/IfaSubscriptInputConfirmFormModel'
import { IfaSubscriptInputConfirmA001RequestModel } from './js/IfaSubscriptInputConfirmA001RequestModel'
import { IfaSubscriptInputConfirmA002RequestModel } from './js/IfaSubscriptInputConfirmA002RequestModel'
import { IfaSubscriptInputConfirmA003RequestModel } from './js/IfaSubscriptInputConfirmA003RequestModel'
import { IfaSubscriptInputConfirmA004RequestModel } from './js/IfaSubscriptInputConfirmA004RequestModel'
import { IfaSubscriptInputConfirmA005RequestModel } from './js/IfaSubscriptInputConfirmA005RequestModel'
import { IfaSubscriptInputConfirmA006RequestModel } from './js/IfaSubscriptInputConfirmA006RequestModel'
import { IfaSubscriptInputConfirmA007RequestModel } from './js/IfaSubscriptInputConfirmA007RequestModel'
import { IfaSubscriptInputConfirmA008RequestModel } from './js/IfaSubscriptInputConfirmA008RequestModel'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true } // 募集入力確認表示/非表示
  },
  emits: ['close-modal', 'order-finish'],
  data() {
    return {
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      },
      form: new IfaSubscriptInputConfirmFormModel(),
      inputField: {
        invitationCheck: '',
        noticeInfoAlert: '',
        noticeAlert: '',
        depositTypeAlert: ''
      },
      refreshMessage: Date.now()
    }
  },
  computed: {
    ifaSubscriptInputConfirmA001RequestModel() {
      return new IfaSubscriptInputConfirmA001RequestModel(this.form)
    },
    ifaSubscriptInputConfirmA002RequestModel() {
      return new IfaSubscriptInputConfirmA002RequestModel(this.form)
    },
    ifaSubscriptInputConfirmA003RequestModel() {
      return new IfaSubscriptInputConfirmA003RequestModel(this.form)
    },
    ifaSubscriptInputConfirmA004RequestModel() {
      return new IfaSubscriptInputConfirmA004RequestModel(this.form)
    },
    ifaSubscriptInputConfirmA005RequestModel() {
      return new IfaSubscriptInputConfirmA005RequestModel(this.form)
    },
    ifaSubscriptInputConfirmA006RequestModel() {
      return new IfaSubscriptInputConfirmA006RequestModel(this.form)
    },
    ifaSubscriptInputConfirmA007RequestModel() {
      return new IfaSubscriptInputConfirmA007RequestModel(this.form)
    },
    ifaSubscriptInputConfirmA008RequestModel() {
      return new IfaSubscriptInputConfirmA008RequestModel(this.form)
    },
    // コンプラランクチェック.チェックボックス文言を区分値として表示する文言を取得する
    invitationCheck() {
      return this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, this.form.complianceRankCheck.chkBoxLabel)
    },
    invitationCheckOptions() {
      const codeValue = this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.form.complianceRankCheck.chkBoxLabel)
      return [{ key: '0', value: '' }, { key: '1', value: codeValue }]
    },
    // 注文ボタンの活性/非活性状態
    buttonDisabled() {
      if (!this.isDisplayAlertConfirmation) {
        // アラート内容確認非表示なら活性
        return false
      }

      const complianceCheckMsg = Array.isArray(this.form.complianceRankCheck.message) ? this.form.complianceRankCheck.message.length > 0 : !!this.form.complianceRankCheck.message
      if (complianceCheckMsg && this.inputField.invitationCheck !== '1') {
        // コンプラアラートメッセージチェックボックスが表示状態でチェックボックスがチェック以外の状態なら非活性
        return true
      }

      const noticeInfoAlert = Array.isArray(this.form.noticeInfoAlert) ? this.form.noticeInfoAlert.length > 0 : !!this.form.noticeInfoAlert
      if (noticeInfoAlert && this.inputField.noticeInfoAlert !== '1') {
        // 注意情報アラート確認チェックボックスが表示状態でチェックボックスがチェック以外の状態なら非活性
        return true
      }

      const noticeAlert = Array.isArray(this.form.noticeAlert) ? this.form.noticeAlert.length > 0 : !!this.form.noticeAlert
      if (noticeAlert && this.inputField.noticeAlert !== '1') {
        // お知らせアラート確認チェックボックスが表示状態でチェックボックスがチェック以外の状態なら非活性
        return true
      }

      const depositTypeAlert = Array.isArray(this.form.depositTypeAlert) ? this.form.depositTypeAlert.length > 0 : !!this.form.depositTypeAlert
      if (depositTypeAlert && this.inputField.depositTypeAlert !== '1') {
        // 預り区分アラート確認チェックボックスが表示状態でチェックボックスがチェック以外の状態なら非活性
        return true
      }

      // 上記以外(表示状態のチェックボックスが全てチェックされた状態)なら活性
      return false
    },
    // アラート内容確認を表示するか
    isDisplayAlertConfirmation() {
      const complianceCheckMsg = Array.isArray(this.form.complianceRankCheck.message) ? this.form.complianceRankCheck.message.length > 0 : !!this.form.complianceRankCheck.message
      const noticeInfoAlert = Array.isArray(this.form.noticeInfoAlert) ? this.form.noticeInfoAlert.length > 0 : !!this.form.noticeInfoAlert
      const noticeAlert = Array.isArray(this.form.noticeAlert) ? this.form.noticeAlert.length > 0 : !!this.form.noticeAlert
      const depositTypeAlert = Array.isArray(this.form.depositTypeAlert) ? this.form.depositTypeAlert.length > 0 : !!this.form.depositTypeAlert
      return (complianceCheckMsg || noticeInfoAlert || noticeAlert || depositTypeAlert) && (this.form.orderFlag === '1' || this.form.orderFlag === '3')
    },
    // 訂正注文を判定する
    isCorrectionOrder() {
      return this.form.orderFlag === '3'
    },
    // 送信・訂正用ロジック処理判定フラグが"BROKERUPDATE:仲介業者更新注文"を判定する
    isBrokerUpdate() {
      return this.form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE'
    },
    // 注文ボタンに表示する文言
    btnLabel() {
      return ['注文発注', '更新', '注文訂正', '注文取消'][this.form.orderFlag - 1]
    }
  },
  watch: {
    isVisible(newValue) {
      if (newValue) {
        // ダイアログが表示状態になった時にバリデーションエラーをクリアする
        // isVisible が true になった瞬間ではまだDOMが表示されないので次の画面更新時を待ってから実行する
        this.$nextTick(() => {
          this.$refs['inputField'].resetFields()
        })
      }
    },
    inputField: {
      deep: true,
      handler(newValue) {
        this.form.depositTypeConfirm = newValue.depositTypeAlert
        this.form.noteInfoCheckbox = newValue.noticeInfoAlert
        this.form.noteLimitCheck = newValue.noticeAlert
        this.form.invitationCheck = newValue.invitationCheck
      }
    }
  },
  methods: {
    onShow(response) {
      // リクエスト内容
      Object.assign(this.form, response)
      this.form.complianceCheckMsg = this.form.complianceRankCheck?.chkBoxLabel

      this.inputField.invitationCheck = ''
      this.inputField.noticeInfoAlert = ''
      this.inputField.noticeAlert = ''
      this.inputField.depositTypeAlert = ''

      this.setMessages()
      this.refreshMessage = Date.now()
    },
    // エラー/警告/情報の更新
    setMessages() {
      this.messages.mains = []
      this.messages.errors = []
      this.messages.warnings = []
      this.messages.infos = []
      // メインメッセージ
      const str = [
        '注文はまだ完了していません。画面下の注文発注ボタンを押下してください。',
        '更新はまだ完了していません。画面下の更新ボタンを押下してください。',
        '訂正はまだ完了していません。画面下の注文訂正ボタンを押下してください。',
        '取消はまだ完了していません。画面下の注文取消ボタンを押下してください。'
      ][this.form.orderFlag - 1]
      if (str) this.messages.mains.push(str)

      // コンプラアラートメッセージ
      this.form.complianceRankCheck = this.form.complianceRankCheck || {}
      if (this.form.complianceRankCheck.message) {
        this.messages.errors = this.messages.errors.concat(getMessage(this.form.complianceRankCheck.message))
      }
      // 注意情報アラートメッセージ
      if (this.form.noticeInfoAlert) {
        this.messages.errors = this.messages.errors.concat(this.form.noticeInfoAlert)
      }
      // お知らせアラートメッセージ
      if (this.form.noticeAlert) {
        this.messages.errors = this.messages.errors.concat(this.form.noticeAlert)
      }
      // 預り区分アラートメッセージ
      if (this.form.depositTypeAlert) {
        this.messages.errors = this.messages.errors.concat(this.form.depositTypeAlert)
      }
    },
    // A008: 戻るボタン
    handleBack() {
      document.getElementById('ifaSubscriptInputConfirmA008').click()
    },
    // 注文発注ボタン
    handleSubmitOrder() {
      switch (this.form.orderFlag) {
        case '1': // 注文発注
          // 送信・訂正用ロジック処理判定フラグによりActionを呼び出す
          if (this.form.sendCorrectLogicJudgeFlag === 'BROKERINSERT') {
            // BROKERINSERT:仲介業者新規注文   → A001
            document.getElementById('ifaSubscriptInputConfirmA001').click()
          } else if (this.form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE') {
            // BROKERUPDATE:仲介業者更新注文 → A005
            document.getElementById('ifaSubscriptInputConfirmA005').click()
          }
          return
        case '2': // 更新
          // 送信・訂正用ロジック処理判定フラグによりActionを呼び出す (v1.06)
          if (this.form.sendCorrectLogicJudgeFlag === 'SBIINSERT') {
            // SBIINSERT:管理者新規注文　→　A002
            document.getElementById('ifaSubscriptInputConfirmA002').click()
          } else if (this.form.sendCorrectLogicJudgeFlag === 'SBIUPDATEONE') {
            // SBIUPDATEONE:管理者更新注文　→　A003
            document.getElementById('ifaSubscriptInputConfirmA003').click()
          } else if (this.form.sendCorrectLogicJudgeFlag === 'SBIUPDATETWO') {
            // SBIUPDATETWO:管理者訂正注文(注文状況のみ更新可能)　→　A004
            document.getElementById('ifaSubscriptInputConfirmA004').click()
          }
          return
        case '3': // 注文訂正
          document.getElementById('ifaSubscriptInputConfirmA006').click()
          return
        case '4': // 注文取消
          document.getElementById('ifaSubscriptInputConfirmA007').click()
          return
      }
    },
    // A001: 注文（仲介業者新規）のレスポンス処理
    responseHandlerA001(response) {
      // 完了画面を表示するために必要な情報として this.form を返す
      this.$emit('order-finish', this.form)
    },
    // A001: 注文（仲介業者新規）のレスポンスエラー処理
    responseErrorHandlerA001(response) {
      this.$_logDebug(response)
    },
    // A002: 更新（管理者新規）のレスポンス処理
    responseHandlerA002(response) {
      // 完了画面を表示するために必要な情報として this.form を返す
      this.$emit('order-finish', this.form)
    },
    // A002: 更新（管理者新規）のレスポンスエラー処理
    responseErrorHandlerA002(response) {
      this.$_logDebug(response)
    },
    // A003: 更新（管理者更新）のレスポンス処理
    responseHandlerA003(response) {
      // 完了画面を表示するために必要な情報として this.form を返す
      this.$emit('order-finish', this.form)
    },
    // A003: 更新（管理者更新）のレスポンスエラー処理
    responseErrorHandlerA003(response) {
      this.$_logDebug(response)
    },
    // A004: 更新（管理者訂正）のレスポンス処理
    responseHandlerA004(response) {
      // 完了画面を表示するために必要な情報として this.form を返す
      this.$emit('order-finish', this.form)
    },
    // A004: 更新（管理者訂正）のレスポンスエラー処理
    responseErrorHandlerA004(response) {
      this.$_logDebug(response)
    },
    // A005: 訂正（仲介業者更新）のレスポンス処理
    responseHandlerA005(response) {
      // 完了画面を表示するために必要な情報として this.form を返す
      this.$emit('order-finish', this.form)
    },
    // A005: 訂正（仲介業者更新）のレスポンスエラー処理
    responseErrorHandlerA005(response) {
      this.$_logDebug(response)
    },
    // A006: 訂正（仲介業者訂正）のレスポンス処理
    responseHandlerA006(response) {
      // 完了画面を表示するために必要な情報として this.form を返す
      this.$emit('order-finish', this.form)
    },
    // A006: 訂正（仲介業者訂正）のレスポンスエラー処理
    responseErrorHandlerA006(response) {
      this.$_logDebug(response)
    },
    // A007: 取消のレスポンス処理
    responseHandlerA007(response) {
      // 完了画面を表示するために必要な情報として this.form を返す
      this.$emit('order-finish', this.form)
    },
    // A007: 取消のレスポンスエラー処理
    responseErrorHandlerA007(response) {
      this.$_logDebug(response)
    },
    // A008: 戻り初期化のレスポンス処理
    responseHandlerA008(response) {
      // 完了画面を表示するために必要な情報として this.form を返す
      this.$emit('close-modal', response)
    },
    // A008: 戻り初期化のレスポンスエラー処理
    responseErrorHandlerA008(response) {
      this.$_logDebug(response)
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.title {
  margin-top: 1rem;
  font-size: 20px;
  font-weight: bold;
}
.brand-area__wrapper {
  font-size: 16px;
  height: 20px;
  margin-top: 0.5rem;
  margin-left: 1rem;
  :deep(.el-form-item__label) {
    margin-left: 0;
    line-height: 25px;
  }
}
._bold_red_m {
  font-size: 16px;
  font-weight: bold;
  color: #f00;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -65px 2rem 0 auto;
}
.input-section :deep(.el-form-item) {
  .el-form-item__label {
    color: #f00;
    margin-left: 0;
    font-size: 16px;
  }
  .el-form-item__error {
    width: 500px;
  }
  .el-checkbox__label {
    color: #f00;
    font-size: 16px;
  }
}
.bbremark__textarea {
  word-break: break-all;
}
.message-area--no-padding {
  :deep(.main-message) {
    padding-left: 0;
  }
  :deep(.error-message), :deep(.warning-message), :deep(.info-message) {
    padding-left: 1.5rem;
  }
}
.top-m_card {
  margin-top: 0.75rem;
}
:deep(.el-text){
  font-size: 16px;
}
.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}
.__bold {
  font-weight: bold;
}
  .arrow {
      font-weight: 700;
      font-size: 22px;
      margin-top: -2px;
      -webkit-transform: scaleX(1.4);
      transform: scaleX(1.4);
      color: #000;
  }
  .el-icon-right {
  font-family: element-icons!important;
  font-style: normal;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  vertical-align: baseline;
  display: inline-block;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
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
