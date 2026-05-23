<template>
  <div>
    <div v-if="isVisible">
      <screen-title :text="detailForm.title.name"></screen-title>
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
            @app-action-handler="handleBackA006"
          ></ifa-button>
        </el-col>
      </el-row>
      <el-form
        :model="detailForm"
        :inline="true"
      >
        <el-row>
          <el-col
            :span="22"
            :offset="1"
            style="padding-top: 0.7rem;"
          >
            <el-card
              class="box-card"
              style="font-size: 16px;"
            >
              <el-row class="row_size">
                <el-col :span="3">
                  <span class="_bold_black_m">番号</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  {{ detailForm.toiawaseNo }}
                </el-col>
                <el-col :span="3">
                  <span class="_bold_black_m">クレーム</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  {{ detailForm.cream === '1' ? '※': '' }}
                </el-col>
              </el-row>
              <el-row class="row_size">
                <el-col :span="3">
                  <span class="_bold_black_m">入力日時</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  {{ detailForm.toiawaseNichiji }}
                </el-col>
                <el-col :span="3">
                  <span class="_bold_black_m">リクエスト</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  {{ detailForm.request === '1' ? '※': '' }}
                </el-col>
              </el-row>
              <el-row class="row_size">
                <el-col :span="3">
                  <span class="_bold_black_m">取扱者</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  {{ detailForm.userNm }}
                </el-col>
                <el-col :span="3">
                  <span class="_bold_black_m">重要度</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    code-list-id="JUUYOUDO"
                    :disp-pattern="1"
                    :code-key="detailForm.juuyoudo"
                    style="font-size: 16px;"
                  ></ifa-text>
                </el-col>
              </el-row>
              <el-row class="row_size">
                <el-col :span="3">
                  <span class="_bold_black_m">カテゴリ</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  {{ formatToiawaseMei(detailForm.toiawaseDMei, detailForm.toiawaseMei, detailForm.toiawaseSMei) }}
                </el-col>
                <el-col :span="3">
                  <span class="_bold_black_m">対応ステータス</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    code-list-id="TAIOU_STS"
                    :disp-pattern="1"
                    :code-key="detailForm.taiouSts"
                    style="font-size: 16px;"
                  ></ifa-text>
                </el-col>
              </el-row>
              <el-row class="row_size">
                <el-col :span="3">
                  <span class="_bold_black_m">接触経路</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    code-list-id="SESSYOKU_KEIRO"
                    :disp-pattern="1"
                    :code-key="detailForm.sessyokuKeiro"
                    style="font-size: 16px;"
                  ></ifa-text>
                </el-col>
                <el-col :span="3">
                  <span class="_bold_black_m">入電方向</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    code-list-id="NYUUDEN_HOUKOU"
                    :disp-pattern="1"
                    :code-key="detailForm.houkou"
                    style="font-size: 16px;"
                  ></ifa-text>
                </el-col>
              </el-row>
              <el-row class="row_size">
                <el-col :span="3">
                  <span class="_bold_black_m">訪問日</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  {{ $_getFormattedDateValue(detailForm.houmonbi) }}
                </el-col>
                <el-col :span="3">
                  <span class="_bold_black_m">次回訪問予定日</span>
                </el-col>
                <el-col
                  :span="5"
                  :offset="1"
                >
                  {{ $_getFormattedDateValue(detailForm.nextHoumonbi) }}
                </el-col>
                <el-col :span="3">
                  <ifa-button
                    text="管理項目修正"
                    width="110"
                    small
                    color="primary"
                    :disabled="detailForm.ifaSenyouFlg !== '1'"
                    style="padding-left: 0"
                    action-type="originalAction"
                    @app-action-handler="contactModifyA002()"
                  ></ifa-button>
                </el-col>
              </el-row>
            </el-card>
          </el-col>
        </el-row>
        <el-row>
          <el-col
            :span="22"
            :offset="1"
            style="padding-top: 0.7rem;"
          >
            <el-card
              class="box-card"
              style="font-size: 16px;"
            >
              <el-row
                class="inner-box-line"
              >
                <el-col>
                  <span class="_bold_black_m">内容</span>
                </el-col>
              </el-row>
              <el-row
                class="row_size"
              >
                <el-col :span="21">
                  <ifa-input-text
                    id="toiawaseNaiyouDetail"
                    v-model="detailForm.toiawaseNaiyou"
                    type="textarea"
                    :rows="6"
                    resize="vertical"
                    class="form_label ifa-input__text-default"
                    label-class="validation-error-width"
                    :disabled="true"
                    style="width: 100%;"
                  ></ifa-input-text>
                </el-col>
                <el-col :span="3">
                  <ifa-button
                    text="修正"
                    width="110"
                    small
                    color="primary"
                    :disabled="detailForm.ifaSenyouFlg !== '1'"
                    style="padding-left: 0"
                    action-type="originalAction"
                    @app-action-handler="contactCorrectContentA003()"
                  ></ifa-button>
                  <div
                    v-if="setContactCorrectHistoryVisible"
                    id="gridButtonArea"
                  >
                    <ifa-button
                      text="履歴表示"
                      width="110"
                      small
                      color="primary"
                      :disabled="detailForm.ifaSenyouFlg !== '1'"
                      style="padding-left: 0;"
                      action-type="originalAction"
                      @app-action-handler="contactCorrectContentA007()"
                    ></ifa-button>
                  </div>
                </el-col>
              </el-row>
            </el-card>
          </el-col>
        </el-row>
        <el-row>
          <el-col
            :span="22"
            :offset="1"
            style="padding-top: 0.7rem; padding-bottom: 0.3rem;"
          >
            <el-card
              class="box-card"
              style="font-size: 16px;"
            >
              <el-row
                class="inner-box-line"
              >
                <el-col :span="21">
                  <span class="_bold_black_m">追加入力</span>
                </el-col>
                <el-col :span="3">
                  <ifa-button
                    text="追加入力"
                    width="110"
                    small
                    color="primary"
                    :disabled="detailForm.ifaSenyouFlg !== '1'"
                    style="padding-left: 0"
                    action-type="originalAction"
                    @app-action-handler="contactAddReplyA004()"
                  ></ifa-button>
                </el-col>
              </el-row>
              <div
                v-for="(row, index) in detailForm.groupAnswerList"
                :key="index"
                class="row_size"
              >
                <el-row style="padding-bottom: 5px; font-size: 14px;">
                  <el-col :span="4">
                    <span class="_row_answer_info">日時　{{ row.tourokuNichiji }}</span>
                  </el-col>
                  <el-col :span="18">
                    <span class="_row_answer_info">入力者　{{ row.userNm }}</span>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="21">
                    <ifa-input-text
                      v-model="row.kaitouNaiyou"
                      type="textarea"
                      :rows="6"
                      resize="vertical"
                      input-class="kaitouNaiyouDetail"
                      label-class="validation-error-width"
                      :disabled="true"
                      style="width: 100%;"
                    ></ifa-input-text>
                  </el-col>
                  <el-col :span="3">
                    <ifa-button
                      text="修正"
                      width="110"
                      small
                      color="primary"
                      :disabled="detailForm.ifaSenyouFlg !== '1'"
                      style="padding-left: 0"
                      action-type="originalAction"
                      @app-action-handler="contactCorrectAnswerA005(row)"
                    ></ifa-button>
                    <div
                      v-if="setContactCorrectHistoryVisible"
                      id="gridButtonArea"
                    >
                      <ifa-button
                        text="履歴表示"
                        width="110"
                        small
                        color="primary"
                        :disabled="detailForm.ifaSenyouFlg !== '1'"
                        style="padding-left: 0"
                        action-type="originalAction"
                        @app-action-handler="contactCorrectContentA007(row)"
                      ></ifa-button>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-form>
      <!-- 接触履歴詳細初期化検索 イベント -->
      <ifa-requester
        id="ifaContactDetailInitializeA001"
        action-id="SUB0202_0106-06#A001"
        action-type="requestAction"
        :request-model="ifaContactDetailA001RequestModel"
        @response-handler="initializeResponse($event)"
      ></ifa-requester>
      <!-- 接触履歴入力 ダイアログ -->
      <ifa-contact-input
        :is-visible="contactInputVisible"
        :operation-type="operationType"
        :query-data="queryData"
        @close-modal="contactInputVisible = false"
        @research="handleResearch"
      ></ifa-contact-input>
      <!-- 接触履歴修正 ダイアログ -->
      <ifa-contact-correct
        :is-visible="contactCorrectVisible"
        :query-data="correctQueryData"
        @close-modal="contactCorrectVisible = false"
        @research="handleResearch"
      ></ifa-contact-correct>
      <!-- 接触履歴（入力）修正履歴 ダイアログ -->
      <ifa-contact-correct-history
        :is-visible="contactCorrectHistoryVisible"
        :query-data="correctQueryData"
        @close-modal="contactCorrectHistoryVisible = false"
      ></ifa-contact-correct-history>
    </div>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { formatToiawaseMei } from './js/IfaContactUtils'
import IfaContactInput from './IfaContactInput'
import IfaContactCorrect from './IfaContactCorrect'
import IfaContactCorrectHistory from './IfaContactCorrectHistory'
import { IfaContactDetailFormModel } from './js/IfaContactDetailFormModel'

export default {
  components: {
    screenTitle,
    IfaContactInput,
    IfaContactCorrect,
    IfaContactCorrectHistory
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    queryData: { type: Object, required: true }
  },
  emits: ['back-modal', 'research'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      operationType: '', // 処理区分
      contactInputVisible: false, // 接触履歴入力ポップアップ画面表示/非表示
      contactCorrectVisible: false, // 接触履歴修正ポップアップ画面表示/非表示
      contactCorrectHistoryVisible: false, // 接触履歴（入力）修正履歴ポップアップ画面表示/非表示
      detailForm: new IfaContactDetailFormModel(),
      ifaContactDetailA001RequestModel: {},
      correctQueryData: {}, // 接触履歴修正ポップアップ画面パラメータ
      isUpdate: false // 該当レコードは更新されましたか
    }
  },
  computed: {
    // 接触履歴修正履歴は権限1(SBI証券本店)のみ参照可能
    setContactCorrectHistoryVisible() {
      if (this.$store.getters.userAccount.medUsers.privId === '1') {
        return true
      } else {
        return false
      }
    }
  },
  watch: {
    isVisible: function() {
      if (this.isVisible) {
        this.onShow()
      }
    }
  },
  methods: {
    onShow() {
      // 詳細エリア初期化
      this.detailForm = new IfaContactDetailFormModel()
      this.isUpdate = false
      // コンポーネントパラメータから取得するための初期化表示用パラメータを設定する
      Object.assign(this.ifaContactDetailA001RequestModel, this.queryData)
      this.$nextTick(() => {
        // 初期化表示イベント
        document.getElementById('ifaContactDetailInitializeA001').click()
      })
    },
    initializeResponse(response) {
      // 初期化検索結果に基づいて表示用フォームを設定する
      Object.assign(this.detailForm, response.dataList[0])
      this.detailForm.sessyokuKeiro = this.detailForm.sessyokuKeiro ?? ''
      if (response.dataList[0].answerList) {
        let groupAnswerList = this.groupAnswerList(response.dataList[0].answerList)
        if (groupAnswerList?.length > 0 && groupAnswerList[0].tourokuGroup === '0') {
          // 内容：問合せ内容、回答情報リスト.回答内容の登録グループ=0を結合して表示
          this.detailForm.toiawaseNaiyou = (this.detailForm.toiawaseNaiyou || '') + groupAnswerList[0].kaitouNaiyou
          // 追加入力情報一覧.内容：登録グループ毎に結合して表示(グループ=0以外)
          groupAnswerList = groupAnswerList.slice(1)
        }
        Object.assign(this.detailForm.groupAnswerList, groupAnswerList)
      }
    },
    // 管理項目修正イベント(接触履歴入力画面へ)
    contactModifyA002() {
      // 処理区分が'2'(管理項目修正)を設定する
      this.operationType = '2'
      this.contactInputVisible = true
    },
    // 内容修正イベント(接触履歴修正画面へ)
    contactCorrectContentA003() {
      this.correctQueryData = { ifaToiawaseNo: this.queryData.ifaToiawaseNo, tourokuGroup: '0' }
      this.contactCorrectVisible = true
    },
    // 回答内容追加入力イベント(接触履歴入力画面へ)
    contactAddReplyA004() {
      // 処理区分が'3'(追加入力)を設定する
      this.operationType = '3'
      this.contactInputVisible = true
    },
    // 回答内容修正イベント(接触履歴修正画面へ)
    contactCorrectAnswerA005(obj) {
      this.correctQueryData = { ifaToiawaseNo: obj.ifaToiawaseNo, tourokuGroup: obj.tourokuGroup }
      this.contactCorrectVisible = true
    },
    // 「戻る」ボタン押下
    handleBackA006() {
      if (this.queryData.screenId === 'SUB020304') {
        // 遷移元は【接触履歴（入力）検索】画面の場合
        this.$_startShowMenu('SUB020304')
      } else {
        // 遷移元は【接触履歴一覧】画面の場合
        this.$emit('back-modal')
        if (this.isUpdate) {
          this.$emit('research')
        }
      }
    },
    // 履歴表示イベント(接触履歴（入力）修正履歴画面を表示する)
    contactCorrectContentA007(obj) {
      // 「内容」の「履歴表示」ボタンを押すと、登録グループを「0」で設定する
      // 「追加入力」の「履歴表示」ボタンを押すと、該当レコードの「登録グループ」を設定する
      this.correctQueryData = { ifaToiawaseNo: this.queryData.ifaToiawaseNo, tourokuGroup: (obj ? obj.tourokuGroup : '0') }
      this.contactCorrectHistoryVisible = true
    },
    // 接触履歴入力と接触履歴修正画面から戻った、再検索イベント
    handleResearch() {
      this.isUpdate = true
      this.$nextTick(() => {
        document.getElementById('ifaContactDetailInitializeA001').click()
      })
    },
    // 接触履歴データ構造の調整、回答リストは登録グループ毎に結合する
    groupAnswerList(answerList) {
      const answerGroups = {}
      // グループ化
      answerList.forEach(item => {
        if (!answerGroups[item.tourokuGroup]) {
          answerGroups[item.tourokuGroup] = []
        }
        answerGroups[item.tourokuGroup].push(item)
      })

      const groupResult = Object.keys(answerGroups).map(group => {
        const items = answerGroups[group]

        // 登録グループ毎に結合して表示
        const combinedKaitouNaiyou = items.map(i => i.kaitouNaiyou).join(``)

        // 登録日時と入力者取得
        const latestItem = items.reduce((latest, current) => {
          return new Date(current.tourokuNichiji) < new Date(latest.tourokuNichiji)
            ? current
            : latest
        })

        return {
          tourokuGroup: group,
          kaitouNaiyou: combinedKaitouNaiyou,
          toiawaseNo: latestItem.toiawaseNo,
          ifaToiawaseNo: latestItem.ifaToiawaseNo,
          kaitouNo: latestItem.kaitouNo,
          ifaKaitouNo: latestItem.ifaKaitouNo,
          tourokuNichiji: latestItem.tourokuNichiji,
          userNm: latestItem.userNm
        }
      })
      return groupResult
    },
    formatToiawaseMei
  }
}
</script>
<style lang="scss" scoped>
#gridButtonArea {
  margin-top: 0.5rem;
}
.inner-box-line {
  display: flex;
  align-items: center;
  padding: 0.2rem;
}
.row_size {
  padding: 0.5rem 0.2rem 0.2rem 0.5rem;
}
:deep(.el-textarea__inner) {
  min-height: 136px !important;
}
</style>
