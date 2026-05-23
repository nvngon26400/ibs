<template>
  <el-dialog
    :model-value="isVisible"
    width="1200px"
    :center="true"
    class="order-status-list__el-dialog--disabled"
    style="background-color: #fef0f0; padding-top: 1rem;"
    :show-close="false"
    :close-on-click-modal="false"
  >
    <!-- タイトル -->
    <template #header>
      <span class="title">{{ form.title.name }}</span>
    </template>

    <ifa-message-area
      :key="refreshMessage"
      :main-messages="['下記の内容で' + orderStatus + 'を受け付けました｡']"
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
          :buten-code="form.butenCode"
          :customer-code="form.customerCode"
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

          <el-row class="brand-area__wrapper">
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
          ><span>ご注文内容</span></el-row>

          <hr>

          <!-- 数量 -->
          <el-row class="dotted_border">
            <el-col
              class="_bold_black_m"
              :span="isCorrectionOrder ? 6 : 8"
            ><span>数量</span></el-col>

            <!-- 数量を表示 -->
            <el-col :span="7">
              <span>{{ $_out($_withCommaInteger(form.quantity)) }}</span>
              <span>{{ $_out(form.sellBuyUnitType) }}</span>
            </el-col>
          </el-row>

          <!-- 約定金額 -->
          <el-row class="dotted_border">
            <el-col
              class="_bold_black_m"
              :span="isCorrectionOrder ? 6 : 8"
            ><span>約定金額</span></el-col>

            <!-- 約定金額を表示 -->
            <el-col :span="7">
              <span>{{ $_out($_withCommaInteger(form.contractAmount)) }}</span>
              <span>円</span>
            </el-col>
          </el-row>

          <!-- 預り区分 -->
          <el-row class="dotted_border">
            <el-col
              class="_bold_black_m"
              :span="isCorrectionOrder ? 6 : 8"
            ><span>預り区分</span></el-col>

            <!-- 預り区分を表示 -->
            <el-col :span="7">
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
          <el-row class="dotted_border">
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
            <el-col :span="7">
              <ifa-text
                code-list-id="INVITATION_TYPE"
                :disp-pattern="1"
                :code-key="form.kanyuKbn"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 受注方法 -->
          <el-row class="dotted_border">
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
            <el-col :span="7">
              <ifa-text
                code-list-id="ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :code-key="form.jutyuKbn"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 目論見書の交付方法 -->
          <el-row class="dotted_border">
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
            <el-col :span="7">
              <ifa-text
                code-list-id="PROSPECTUS_ISSUE_METHOD"
                :disp-pattern="2"
                :code-key="form.mokuromiKoufuKbn"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 重要事項の説明 -->
          <el-row class="dotted_border">
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
            <el-col :span="7">
              <ifa-text
                code-list-id="IMPORTANT_MATTERS_EXPLAIN"
                :disp-pattern="1"
                :code-key="form.importantMatterType"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 備考 -->
          <el-row class="dotted_border">
            <el-col :span="isCorrectionOrder ? 6 : 8"><span class="_bold_black_m">備考</span></el-col>

            <!-- 注文フラグが"3"(訂正注文)の場合､訂正前_備考と備考を両方表示 -->
            <template v-if="isCorrectionOrder">
              <el-col :span="7"><span class="bbremark__textarea">{{ $_out(form.bbRemark2) }}</span></el-col>
              <!-- 備考を表示 -->
              <el-col
                :span="7"
                :offset="3"
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
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row style="padding: 0.5rem 0 0 0.5rem;">
            <span class="_bold_black_m">アラート内容確認</span>
          </el-row>
          <hr>

          <!-- コンプラランクチェック.チェックボックス文言 -->
          <el-row
            v-if="form.complianceRankCheck.message"
            class="dotted_border"
          >
            <el-col
              :span="isCorrectionOrder ? 6 : 8"
              class="_bold_black_m"
            >{{ invitationCheck }}</el-col>
            <el-col :span="16">{{ invitationCheckOptions }}</el-col>
          </el-row>

          <!-- 注意情報アラート確認 -->
          <el-row
            v-if="form.noticeInfoAlert"
            class="dotted_border"
          >
            <el-col
              :span="isCorrectionOrder ? 6 : 8"
              class="_bold_black_m"
            >注意情報の確認</el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                code-key="1"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- お知らせアラート確認 -->
          <el-row
            v-if="form.noticeAlert"
            class="dotted_border"
          >
            <el-col
              :span="isCorrectionOrder ? 6 : 8"
              class="_bold_black_m"
            >重要なお知らせの確認</el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                code-key="1"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 預り区分アラート確認 -->
          <el-row
            v-if="form.depositTypeConfirm"
            class="dotted_border"
          >
            <el-col
              :span="isCorrectionOrder ? 6 : 8"
              class="_bold_black_m"
            >預り区分の確認</el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                code-key="1"
              ></ifa-text>
            </el-col>
          </el-row>

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
          text="BB申込一覧へ"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="handleDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaSubscriptInputCompleteFormModel } from './js/IfaSubscriptInputCompleteFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true } // 募集入力完了表示/非表示
  },
  emits: ['close-modal'],
  data() {
    return {
      form: new IfaSubscriptInputCompleteFormModel(),
      refreshMessage: Date.now()
    }
  },
  computed: {
    // コンプラランクチェック.チェックボックス文言を区分値として表示する文言を取得する
    invitationCheck() {
      return this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, this.form.complianceRankCheck.chkBoxLabel)
    },
    invitationCheckOptions() {
      return this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.form.complianceRankCheck.chkBoxLabel)
    },
    // アラート内容確認を表示するか
    isDisplayAlertConfirmation() {
      const complianceCheckMsg = Array.isArray(this.form.complianceCheckMsg) ? this.form.complianceCheckMsg.length > 0 : !!this.form.complianceCheckMsg
      const noticeInfoAlert = Array.isArray(this.form.noticeInfoAlert) ? this.form.noticeInfoAlert.length > 0 : !!this.form.noticeInfoAlert
      const noticeAlert = Array.isArray(this.form.noticeAlert) ? this.form.noticeAlert.length > 0 : !!this.form.noticeAlert
      const depositTypeConfirm = Array.isArray(this.form.depositTypeConfirm) ? this.form.depositTypeConfirm.length > 0 : !!this.form.depositTypeConfirm
      return (complianceCheckMsg || noticeInfoAlert || noticeAlert || depositTypeConfirm) && (this.form.orderFlag === '1' || this.form.orderFlag === '3')
    },
    // 訂正注文を判定する
    isCorrectionOrder() {
      return this.form.orderFlag === '3'
    },
    // 送信・訂正用ロジック処理判定フラグが"BROKERUPDATE:仲介業者更新注文"を判定する
    isBrokerUpdate() {
      return this.form.sendCorrectLogicJudgeFlag === 'BROKERUPDATE'
    },
    // 登録完了メッセージに表示する文言
    orderStatus() {
      return ['注文', '更新', '訂正', '取消'][this.form.orderFlag - 1]
    }
  },
  methods: {
    onShow(form) {
      // 確認画面のフォーム内容をコピーする
      // title もコピーされて上書きされるため書き戻す
      const completeTitle = { ...this.form.title }
      const confirmTitle = { ...form.title }
      Object.assign(this.form, form)
      this.form.title = completeTitle
      form.title = confirmTitle
      this.form.title.name = `募集${this.form.orderFlag === '3' ? '注文' : '入力'}完了`
      this.refreshMessage = Date.now()
    },
    handleDialogClose() {
      this.$emit('close-modal')
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
.bbremark__textarea {
  word-break: break-all;
}
.top-m_card {
  margin-top: 0.75rem;
}
:deep(.el-text){
  font-size: 16px;
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
</style>
