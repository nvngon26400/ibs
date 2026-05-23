<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.pageTitle.name"
      :show-close="false"
      :center="true"
      :before-close="backA004"
      :close-on-click-modal="false"
      width="1050px"
      @open="onShow"
    >
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <div style="width:97%; text-align: right; margin: 10px;">
            <!-- 戻るボタン -->
            <ifa-button
              text="戻る"
              width="90"
              color="secondary"
              style="padding-right: 0;"
              action-type="originalAction"
              @app-action-handler="backA004"
            ></ifa-button>
          </div>
        </el-col>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-form
            ref="form"
            :model="form"
            :rules="rules"
            :inline="true"
          > 
            <!-- 要望エリア -->
            <table class="input-table">
              <!-- タイトル -->
              <tr>
                <td class="table-label">
                  タイトル
                </td>
                <td class="suggestion-text">
                  {{ form.title }}
                </td>
              </tr>
              <!-- 登録日 -->
              <tr>
                <td class="table-label">
                  登録日
                </td>
                <td class="suggestion-text">
                  {{ this.$_getFormattedDateValue(form.registerDate) }}
                    </td>
              </tr>
              <!-- ステータス -->
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>ステータス
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-select
                      v-model="form.status"
                      prop="status"
                      size="small"
                      code-list-id="SUG_BOX_STATUS"
                      style="width: 200px;"
                      :disp-pattern="1"
                      :select-pattern="2"
                    >
                    </ifa-input-select>
                  </el-form-item>
                </td>
              </tr>
              <!-- 要望内容 -->
              <tr>
                <td class="table-label">
                  要望内容
                </td>
                <td class="suggestion-text">
                  <div style="width:100%; height:180px; overflow-y: auto; border: 2px solid #ccc; padding: 5px 11px;">
                    <span v-html="formattedContents"></span>
                  </div>
                </td>
              </tr>

              <!-- 要望エリア添付ファイル -->
              <tr v-if="form.attachFile1">
                <td class="table-label">
                  <span v-if="displayAttachFile1">添付ファイル</span>
                </td>
                <td
                  class="suggestion-text"
                  :style="{
                    'padding-bottom': form.attachFile2 !== null || form.attachFile3 !== null ? '0' : ''
                  }"
                >
                  <!-- 添付ファイル1 -->
                  <span style="border: 2px solid #ccc; padding: 5px 11px; width: 90%;">
                    {{ form.attachFile1 }}
                  </span>
                    <!-- DLリンク -->
                    <span
                    class="_link DL_link"
                    style="border-left: none;"
                    @click="downloadA002(form.attachFile1)"
                  >DL</span>
                </td>
              </tr>
              <tr v-if="form.attachFile2">
                <td class="table-label">
                  <span v-if="displayAttachFile2">添付ファイル</span>
                </td>
                <td
                  class="suggestion-text"
                  :style="{
                    'padding-top': form.attachFile1 !== null ? '0' : '',
                    'margin-top': form.attachFile1 !== null ? '-2px' : '',
                    'padding-bottom': form.attachFile3 !== null ? '0' : '',
                  }"
                >
                  <!-- 添付ファイル2 -->
                  <span style="border: 2px solid #ccc; padding: 5px 11px; width: 90%;">
                    {{ form.attachFile2 }}
                  </span>
                  <!-- DLリンク -->
                  <span
                    class="_link DL_link"
                    style="border-left: none;"
                    @click="downloadA002(form.attachFile2)"
                  >DL</span>
                </td>
              </tr>
              <tr v-if="form.attachFile3">
                <td class="table-label">
                  <span v-if="displayAttachFile3">添付ファイル</span>
                </td>
                <td
                  class="suggestion-text"
                  style="padding-top: 0;"
                  :style="{
                    'padding-top': form.attachFile1 !== null || form.attachFile2 !== null ? '0' : '',
                    'margin-top': form.attachFile1 !== null || form.attachFile2 !== null ? '-2px' : ''
                  }"
                >
                  <!-- 添付ファイル3 -->
                  <span style="border: 2px solid #ccc; padding: 5px 11px; width: 90%;">
                    {{ form.attachFile3 }}
                  </span>
                  <!-- DLリンク -->
                  <span
                    class="_link DL_link"
                    style="border-left: none;"
                    @click="downloadA002(form.attachFile3)"
                  >DL</span>
                </td>
              </tr>

              <!-- 登録済回答エリア -->
              <tr
                v-for="(item, index) in form.registeredAnswerList"
                :key="index"
              >
                <!-- ラベル （最初の回答にのみ表示） -->
                <td class="table-label"
                > {{ index == 0 ? 'SBI証券からの回答' :'' }} </td>
                
                <td class="table-body">
                  <!-- 回答登録日 -->
                  <div
                    class="register_textarea"
                  >
                  {{ registeredAnswerRegisterDateText(item) }}
                  </div>
                  <!-- 回答内容桁数 -->
                  <div
                    class="text_count_area"
                    :style="item.answerContents?.length !== 2000 ? 'color: #6e6e6e;' : 'color: red;'"
                  >
                    {{ item.answerContents?.length ?? '0' }}/2000
                  </div>
                  <!-- 回答内容 -->
                  <div class="textarea_container">
                    <el-form-item
                      class="text-box"
                    >
                      <ifa-input-text
                        id="contents"
                        v-model="item.answerContents"
                        :prop="`registeredAnswerList.${index}.answerContents`"
                        placeholder="回答を入力"
                        type="textarea"
                        rows="7"
                        style="width: 650px"
                        original-screen-id="SUB0511_01-02"
                        :domain="IfaText2000DomainModel"
                        :key="'answerContents-' + index + '-' + refreshKey"
                      ></ifa-input-text>
                    </el-form-item>
                  </div>
                </td>
              </tr>

              <!-- 新規回答エリア -->
              <tr
                v-for="(item, index) in form.newAnswerList"
                :key="index"
              >
                <!-- ラベル （最初の回答にのみ表示） -->
                <td class="table-label"
                > {{ index == 0 && (!this.form.registeredAnswerList || this.form.registeredAnswerList.length == 0) ? 'SBI証券からの回答' :'' }} </td>
                
                <td class="table-body">
                  <!-- 回答登録日 -->
                  <div
                    class="register_textarea"
                  >
                  {{ newAnswerRegisterDateText(item) }}
                  </div>
                  <!-- 回答内容桁数 -->
                  <div
                    class="text_count_area"
                    :style="item.answerContents?.length !== 2000 ? 'color: #6e6e6e;' : 'color: red;'"
                  >
                    {{ item.answerContents?.length ?? '0' }}/2000
                  </div>
                  <!-- 回答内容 -->
                  <div class="textarea_container">
                    <el-form-item
                      :class="{ 'text-box': true, 'answer_textarea': item.answerConfirmFlg === '0' }"
                    >
                      <ifa-input-text
                        id="contents"
                        v-model="item.answerContents"
                        :prop="`newAnswerList.${index}.answerContents`"
                        placeholder="回答を入力"
                        type="textarea"
                        :autosize="item.answerConfirmFlg === '0' ? { minRows: 1, maxRows: 7 } : false"
                        :rows="item.answerConfirmFlg === '0' ? '1' : '7'"
                        style="width: 650px"
                        original-screen-id="SUB0511_01-02"
                        :domain="IfaText2000DomainModel"
                        :key="'answerContents-' + index + '-' + refreshKey"
                      ></ifa-input-text>
                    </el-form-item>
                    <!-- 回答確定ボタン（回答確定フラグ=0：未確定の場合、表示） -->
                    <el-icon
                      v-if="item.answerConfirmFlg === '0'"
                      class="send_icon"
                      @click="answerConfirmedA003(item)"
                    ><Promotion /></el-icon>
                  </div>
                </td>
              </tr>
            </table>
          </el-form>
        </el-col>
        <!-- 登録ボタン -->
        <el-col
          :offset="1"
          style="padding: 10px"
        >
          <ifa-button
            text="登録"
            width="90"
            color="primary"
            style="padding-left: 0"
            :form="formRef"
            action-type="originalAction"
            @app-action-handler="comfirmHandlerA005"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 確認ダイアログ.更新確認 -->
    <ifa-ok-cancel-dialog
      :is-visible="confirmDialog.visible"
      :title="confirmDialog.titleMessage"
      :message="confirmDialog.confirmMessage"
      @close-modal-ok="registerA006"
      @close-modal-cancel="confirmDialog.visible = false"
    ></ifa-ok-cancel-dialog>
    <!-- A002.ダウンロード リクエスター -->
    <ifa-requester
      id="ifaSuggestionBoxPersonalDetailFromBrokerDonwloadA002"
      action-id="SUB0511_01-02#A002"
      action-type="outputPdfAction"
      :request-model="ifaSuggestionBoxPersonalDetailFromBrokerA002RequestModel"
    ></ifa-requester>
    <!-- A006.更新（DB更新）リクエスター -->
    <ifa-requester
      id="ifaSuggestionBoxPersonalDetailFromBrokerRegisterA006"
      action-id="SUB0511_01-02#A006"
      action-type="requestAction"
      :request-model="ifaSuggestionBoxPersonalDetailFromBrokerA006RequestModel"
      @response-handler="responseHandlerIfaSuggestionBoxPersonalDetailFromBrokerA006($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaText2000DomainModel from '@/resource/domain/IfaText2000DomainModel.json'
import { IfaSuggestionBoxPersonalDetailFromBrokerA002RequestModel } from './js/IfaSuggestionBoxPersonalDetailFromBrokerA002RequestModel'
import { IfaSuggestionBoxPersonalDetailFromBrokerA006RequestModel } from './js/IfaSuggestionBoxPersonalDetailFromBrokerA006RequestModel'
import { IfaSugBoxPersonalDetailFromBrokerFormModel } from './js/IfaSugBoxPersonalDetailFromBrokerFormModel'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { getMessage } from '@/utils/errorHandler'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaOkCancelDialog
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    formData: {
      type: Object,
      required: true
    }
  },
  emits: ['close-modal', 'update-table', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      IfaText2000DomainModel: IfaText2000DomainModel,
      formRef: {},
      form: new IfaSugBoxPersonalDetailFromBrokerFormModel(),
      ifaSuggestionBoxPersonalDetailFromBrokerA002RequestModel: {},
      ifaSuggestionBoxPersonalDetailFromBrokerA006RequestModel: {},
      confirmDialog: { // 確認ダイアログ
        visible: false,
        titleMessage: '登録の確認',
        confirmMessage: ''
      },
      processing: false, // 「確定」ボタン 連打防止フラグ
      rules: {
        status: { required: true, trigger: 'blur', validator: this.statusValidator },
      },
      refreshKey: 0, // テキストエリア再描画用キー
      statusBeforeCorrect: ''
    }
  },
  computed: {
    // 添付ファイルラベルの表示制御
    displayAttachFile1() {
      if (this.form.attachFile1) {
        return true
      } else {
        return false
      }
    },
    // 添付ファイルラベルの表示制御
    displayAttachFile2() {
      if (!this.form.attachFile1 && this.form.attachFile2) {
        return true
      } else {
        return false
      }
    },
    // 添付ファイルラベルの表示制御
    displayAttachFile3() {
      if (!this.form.attachFile1 && !this.form.attachFile2 && this.form.attachFile3) {
        return true
      } else {
        return false
      }
    },
    formattedContents() {
      return this.form.suggestion?.replace(/\\n/g, '<br>').replace(/\n/g, '<br>')
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    /* A001.初期化 */
    onShow() {
      this.form = Object.assign(this.form, this.formData)

      // 修正前ステータスをセット
      this.statusBeforeCorrect = this.form.status

      // 回答一覧の事前処理
      this.preAnswerList()

      // 回答確定フラグ＝0(未確定)の回答を追加する
      this.addAnwser()

      this.$refs['form'].clearValidate()
    },
    /* A002.ダウンロード */
    downloadA002(attachFile) {
      if (this.processing) return // 連打防止
      
      this.processing = true
      if (attachFile) {
        // DL対象のファイル名をセットしてA002.ダウンロード.リクエスターを呼び出す
        this.ifaSuggestionBoxPersonalDetailFromBrokerA002RequestModel = new IfaSuggestionBoxPersonalDetailFromBrokerA002RequestModel({attachFile: attachFile})
        this.$nextTick(() => {
          document.getElementById('ifaSuggestionBoxPersonalDetailFromBrokerDonwloadA002').click()
        })
      }
      this.processing = false
    },
    /* A003.新規回答確定 */
    answerConfirmedA003(item) {
      // 入力がない場合は確定しない（非活性）
      if (!item.answerContents) {
        return
      }
      
      // 回答一覧の対象明細の回答確定フラグを1(確定)に変更
      item.answerConfirmFlg = '1'
      
      // テキストエリアの再描画のためキーを更新
      this.refreshKey++

      // 回答確定フラグ＝0(未確定)の回答を追加する
      this.addAnwser()
    },
    /* A004.戻る */
    backA004() {
      // テキストエリアの再描画のためキーを更新
      this.refreshKey++

      this.$refs['form'].clearValidate()
      this.$emit('close-modal')
    },
    /* A005.登録確認 */
    comfirmHandlerA005() {
      // ステータス=0(回答待ち)の場合
      if (this.form.status === '0') {
        // エラーメッセージを表示して処理終了。
        notifyMessage(
          -1,
          getMessage('errors.emp.sugBoxStatus.outOfRange'),
          this.form.pageTitle.name,
          Date.now()
        )
        return
      }

      let modify = false
      // 登録済回答一覧の変更有無チェック
      if (
        this.form.registeredAnswerList &&
        this.form.registeredAnswerList.find(item => item.answerContents !== item.answerContentsBeforeCorrect)
      ) {
        modify = true;
      }
      // 確定済回答有無チェック
      if (
        this.form.newAnswerList &&
        this.form.newAnswerList.find(item => item.answerConfirmFlg === '1' && item.answerContents)
      ) {
        modify = true;
      }
      // ステータス変更有無チェック
      if (this.form.status !== this.statusBeforeCorrect) {
        modify = true;
      }
      // 変更がない場合
      if (!modify) {
        // エラーメッセージを表示して処理終了。
        notifyMessage(
          -1,
          getMessage('errors.emp.sugBox.noModify'),
          this.form.pageTitle.name,
          Date.now()
        )
        return
      }

      // バリデーションチェック
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 確認ダイアログを表示する。
          // 回答確定フラグ＝0かつ回答内容入力あり　の回答がある場合
          if (
            this.form.newAnswerList &&
            this.form.newAnswerList.find(item => item.answerConfirmFlg === '0' && item.answerContents)
          ) {
            this.confirmDialog.confirmMessage = '未確定の回答があります。確定済の回答のみ登録します。よろしいですか？'
          // 上記以外の場合
          } else {
            this.confirmDialog.confirmMessage = '入力内容で登録します。よろしいですか？'
          }
          this.confirmDialog.visible = true
        } else {
          return
        }
      })
    },
    /* A006.登録 */
    registerA006() {
        this.$nextTick(() => {
          this.confirmDialog.visible = false
          // A006.登録リクエスターを呼び出す
          this.ifaSuggestionBoxPersonalDetailFromBrokerA006RequestModel = this.setIfaSuggestionBoxPersonalDetailFromBrokerA006RequestModel()
          document.getElementById('ifaSuggestionBoxPersonalDetailFromBrokerRegisterA006').click()
        })
    },
    /* A006.登録リクエストモデルセット*/
    setIfaSuggestionBoxPersonalDetailFromBrokerA006RequestModel() {
      // 登録済回答一覧から作成日時と更新日時を除外
      const exDateAnswerList = this.form.registeredAnswerList && this.form.registeredAnswerList.length > 0 ? this.form.registeredAnswerList.map(({ answerRegisterDate, answerUpdateDate, ...item }) => item) : []
      // 新規回答一覧から回答確定フラグ＝0：未確定の回答を除外
      const exConfirmFlgZeroAnswerList = this.form.newAnswerList.filter(item => item.answerConfirmFlg !== '0')
      // 新規回答一覧から作成日時を除外
      const exDateNewAnswerList = exConfirmFlgZeroAnswerList && exConfirmFlgZeroAnswerList.length > 0 ? exConfirmFlgZeroAnswerList.map(({ answerRegisterDate, ...item }) => item) : []

      return new IfaSuggestionBoxPersonalDetailFromBrokerA006RequestModel( 
        {
          ...this.form,
          registeredAnswerList: exDateAnswerList,
          newAnswerList: exDateNewAnswerList
        }
      )
    },
    /* A006.登録レスポンスハンドラー */
    responseHandlerIfaSuggestionBoxPersonalDetailFromBrokerA006() {
      // 自画面（ポップアップ）を閉じる
      this.backA004()
      this.$emit('update-table')
    },
    /* 回答一覧の事前処理 */
    preAnswerList() {
      // 登録済回答一覧へ回答内容(修正前)をセット
      if (this.form.registeredAnswerList && this.form.registeredAnswerList.length > 0) {
        this.form.registeredAnswerList = this.form.registeredAnswerList.map(item => ({
          ...item,
          answerContentsBeforeCorrect: item.answerContents // 回答内容(修正前)
        }))
      } else {
        this.form.registeredAnswerList = []
      }
      this.form.newAnswerList = []
    },
    /* 新規回答の追加 */
    addAnwser() {
      // 現在日付をYYYYMMDD形式で取得
      const requestedTime = this.$store.getters.requestedTime.split(' ')[0].replaceAll('/', '')
      
      // 回答一覧へ0：未確定の回答を追加
      this.form.newAnswerList.push(
        {
          answerConfirmFlg: '0', // 回答確定フラグ：0：未確定
          answerRegisterDate: requestedTime, // 回答登録日：システム日付（YYYY/MM/DD）
          answerContents: '', // 回答内容
        }
      )
    },
    /* 回答登録日 算出処理(登録済回答一覧) */
    registeredAnswerRegisterDateText(item) {
      let registerDateText = ''
      
      if (item.answerRegisterDate === item.answerUpdateDate) { // 回答一覧.回答登録日=回答一覧.回答更新日 の場合
        // 回答一覧.回答登録日(yyyy/MM/dd形式)+" SBI証券"
        registerDateText = this.$_getFormattedDateValue(item.answerRegisterDate) + ' SBI証券'

      } else { // 回答一覧.回答登録日≠回答一覧.回答更新日 の場合
        // 回答一覧.回答登録日(yyyy/MM/dd形式)+"（"+回答一覧.回答更新日(yyyy/MM/dd形式)＋"更新） SBI証券"
        registerDateText = this.$_getFormattedDateValue(item.answerRegisterDate) + '（' + this.$_getFormattedDateValue(item.answerUpdateDate) + '更新） SBI証券'
      }
      return registerDateText
    },
    /* 回答登録日 算出処理(新規回答一覧) */
    newAnswerRegisterDateText(item) {
       // 回答確定フラグ＝0：未確定の場合、非表示
      let registerDateText = ''
      
      if (item.answerConfirmFlg === '1') { // 回答確定フラグ＝1：確定の場合
        // 確定ボタンが押された日(yyyy/MM/dd形式)＋" SBI証券"
        registerDateText = this.$_getFormattedDateValue(item.answerRegisterDate) + ' SBI証券'

      }
      return registerDateText
    },
    statusValidator(rule, value, callback) {
      if (this.form.status === '') {
        callback('ステータスを選択してください。')
        return
      }
      callback()
    }
  }
}
</script>

<style lang="scss" scoped>
  @import '@/styles/suggestionBox.scss';

  .suggestion-text {
    font-size: 16px;
    color: #18181A;
    padding: 8px 0;
    display: flex;
    width: 650px;
    margin-left: 20px;
  }
  .DL_link {
    border: 2px solid #ccc;
    padding: 5px 11px;
    font-size: 16px;
    width: 10%;
    text-align: center;
  }
</style>