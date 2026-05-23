<template>
  <div>

    <div class="message">別ウィンドウで画面が起動しております｡</div>

    <!-- パスワード設定ダイアログ -->
    <el-dialog
      v-model="isPasswordInputVisible"
      title="CCSログインユーザ情報登録"
      :show-close="false"
      :center="true"
      :before-close="handleCloseDialog"
      :close-on-click-modal="false"
      width="600px"
    >
      <div
        class="input-password"
        style="margin: 1rem 0"
      >
        <el-form
          ref="passwordInputForm"
          :model="form"
          :inline="true"
        >
          <el-row>
            <ifa-input-text
              id="ccsUserId"
              v-model="form.ccsUserId"
              label="ユーザID"
              prop="ccsUserId"
              size="small"
              type="text"
              name="ccsUserId"
              :domain="IfaCcsIdDomainModel"
              disabled
            ></ifa-input-text>
          </el-row>
          <el-row>
            <ifa-input-text
              id="ccsUserPw"
              v-model="form.ccsUserPw"
              label="パスワード"
              prop="ccsUserPw"
              size="small"
              type="password"
              name="ccsUserPw"
              :domain="IfaCcsPasswordDomainModel"
              required
            ></ifa-input-text>
          </el-row>
        </el-form>
      </div>
      <div>
        <!-- A003: CCSログイン情報登録 -->
        <ifa-button
          text="登録"
          width="100"
          color="primary"
          small
          action-type="requestAction"
          action-id="IfaCcsLink#A003"
          :form="formRef"
          :request-model="ifaCcsLinkRequestModelA003"
          @response-handler="handleResponseA003"
          @response-error-handler="handleErrorResponseA003"
        ></ifa-button>
        <!-- A004: キャンセル -->
        <ifa-button
          text="キャンセル"
          width="100"
          color="secondary"
          small
          action-type="originalAction"
          @app-action-handler="registerPasswordCancelA004"
        ></ifa-button>
      </div>
    </el-dialog>

    <!-- リクエスト -->
    <!-- A001: CCSログイン情報チェック -->
    <ifa-requester
      id="ifaCcsLinkA001"
      ref="ifaCcsLinkA001"
      action-type="requestAction"
      action-id="IfaCcsLink#A001"
      @response-handler="handleResponseA001"
    ></ifa-requester>
    <!-- A002_1: CCSログイン: CCS画面IDが "main" の場合 -->
    <ifa-requester
      id="ifaCcsLinkA002_1"
      ref="ifaCcsLinkA002_1"
      action-type="requestAction"
      action-id="IfaCcsLink#A002_1"
      :request-model="ifaCcsLinkRequestModelA002_1"
      @response-handler="handleResponseA002_1"
    ></ifa-requester>
    <!-- A002_2: CCSログイン: CCS画面IDが "main" 以外の場合 -->
    <ifa-requester
      id="ifaCcsLinkA002_2"
      ref="ifaCcsLinkA002_2"
      action-type="requestAction"
      action-id="IfaCcsLink#A002_2"
      :request-model="ifaCcsLinkRequestModelA002_2"
      @response-handler="handleResponseA002_2"
    ></ifa-requester>

  </div>
</template>

<script>
import IfaCcsIdDomainModel from '@/resource/domain/IfaCcsIdDomainModel.json'
import IfaCcsPasswordDomainModel from '@/resource/domain/IfaCcsPasswordDomainModel.json'
import { getCcsSsoHtmlContent } from './js/ccsLogin'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  name: 'IfaCcsLink',
  data() {
    return {
      formRef: null,
      isPasswordInputVisible: false,
      form: {
        ccsUserId: '',
        ccsUserPw: ''
      },
      IfaCcsIdDomainModel: IfaCcsIdDomainModel,
      IfaCcsPasswordDomainModel: IfaCcsPasswordDomainModel
    }
  },
  computed: {
    // ccsDispIdTable からメニューIDに一致する項目を取得する
    page() {
      const menuId = this.$store.getters.pageInfo.menuId
      const ccsDispId = ccsDispIdTable.find(item => item.menuId === menuId)
      return ccsDispId
    },
    // A002_1: CCSログインリクエストモデル (CCS画面ID が "main")
    // eslint-disable-next-line camelcase
    ifaCcsLinkRequestModelA002_1() {
      return {
        urlId: this.page?.urlId,
        patternId: 1,
        httpMethod: 'GET'
      }
    },
    // A002_1: CCSログインリクエストモデル (CCS画面ID が "main" 以外)
    // eslint-disable-next-line camelcase
    ifaCcsLinkRequestModelA002_2() {
      return {
        ccsDispId: this.page?.ccsDispId
      }
    },
    // AA003: CCSログイン情報登録リクエストモデル
    ifaCcsLinkRequestModelA003() {
      return {
        ccsUserPw: this.form.ccsUserPw
      }
    }
  },
  methods: {
    // 画面(コンポーネント)が表示状態になった
    onShow() {
      this.startCcsLinkA001()
    },
    // A001: CCSログイン情報チェック
    startCcsLinkA001() {
      if (this.page) {
        // ccsDispIdTable に登録ありの画面のみ処理
        this.$nextTick(() => {
          document.getElementById('ifaCcsLinkA001').click()
        })
      }
    },
    // フォームを初期化する
    resetPasswordInputForm() {
      this.formRef = this.$refs['passwordInputForm']
      this.$refs['passwordInputForm'].clearValidate()
      // CCSログインユーザ情報登録のユーザIDはstore情報から取得
      this.form.ccsUserId = this.$store.getters.userAccount.medUsers.ccsUserId
      this.form.ccsPassword = ''
    },
    // A001: CCSログイン情報チェックレスポンス処理
    handleResponseA001(response) {
      // サーバが結果を返さない場合は､終了する (通常はありえないが､次の hasCcsLink が unknown でエラーになるのを防ぐ)
      if (!Array.isArray(response.dataList) || response.dataList.length === 0) return

      if (response.dataList[0].hasCcsData === 'false') {
        // CCSユーザパスワードが未設定の場合は､パスワード入力ダイアログを表示
        this.isPasswordInputVisible = true
        this.$nextTick(() => {
          // clearValidate() がうまく機能しないので､次の描画タイミングでフォームをクリアさせる
          this.resetPasswordInputForm()
        })
      } else {
        // CCSユーザパスワードが設定済みの場合は､リンクを取得して開く
        this.openWindow()
      }
    },
    // A002_1: CCSログインレスポンス処理 (CCS画面ID が "main")
    // eslint-disable-next-line camelcase
    handleResponseA002_1(response) {
      if (response && response.linkUrl) {
        // URLが取得できたらメインサイト画面を別タブで表示させる
        this.openMainSiteWindow(response.linkUrl)
      }
    },
    // A002_2: CCSログインレスポンス処理 (CCS画面ID が "main" 以外)
    // eslint-disable-next-line camelcase
    handleResponseA002_2(response) {
      if (Array.isArray(response.dataList) && response.dataList.length > 0) {
        // URLが取得できたら CCS 画面をポップアップで表示させる
        this.openCcsWindow(response.dataList[0])
      }
    },
    // A003: CCSログイン情報登録レスポンス処理
    handleResponseA003(response) {
      // 登録したCCSユーザパスワードをストアに保存する
      this.$store.commit('user/setCcsUserPw', this.form.ccsUserPw)
      this.handleCloseDialog()
      // リンクを取得して開く
      this.openWindow()
    },
    // A003: CCSログイン情報登録エラーレスポンス処理
    handleErrorResponseA003(response) {
      this.$_logError(response)
      // 登録がエラーなら処理は終了
      this.handleCloseDialog()
    },
    // A004: キャンセル処理
    registerPasswordCancelA004() {
      this.handleCloseDialog()
    },
    // CCSログインユーザ情報登録ダイアログを非表示にする
    handleCloseDialog() {
      this.isPasswordInputVisible = false
    },
    // ccsDispIdTable に従い表示先のURL取得を行う
    openWindow() {
      const ccsDispId = this.page.ccsDispId
      if (ccsDispId === 'main') {
        // ccsDispIdd が 'main' のときは､メインサイトを開く
        document.getElementById('ifaCcsLinkA002_1').click()
      } else {
        // ccsDispIdd が 'main' 以外のときは､CCSを開く
        document.getElementById('ifaCcsLinkA002_2').click()
      }
    },
    // メインサイト画面表示
    openMainSiteWindow(linkUrl) {
      const features = 'left=30000, top=30000, menubar=no, toolbar=no, scrollbars=no, resizable=yes'
      window.open(linkUrl, 'mainSiteWin', features)
    },
    // CCS 画面表示
    async openCcsWindow(params) {
      const features = 'top=30000, left=30000, height=150, width=350, location=no, menubar=no, toolbar=no, status=no, scrollbars=no, resizable=no'
      const userAccount = {
        ccsUserId: this.$store.getters.userAccount.medUsers.ccsUserId,
        ccsUserPw: this.$store.getters.userAccount.medUsers.ccsUserPw
      }
      const content = getCcsSsoHtmlContent(userAccount, params)

      // 新しいウィンドウを開く
      const win = window.open('', 'parentWindow', features)
      if (win) {
        // 新しいウィンドウのドキュメントにアクセス
        const doc = win.document

        // 新しいドキュメントのコンテンツを設定
        doc.open()
        doc.write(content)
        doc.close()
      } else {
        const label = this.$store.getters.pageInfo.label
        const timeStamp = this.$store.getters.requestedTime
        notifyMessage(2, label, 'ポップアップを許可してください｡', timeStamp)
      }
    }
  }
}
const ccsDispIdTable = [
  { menuId: 'SUB0202_0108', ccsDispId: 'main', urlId: 43 }, // 資産状況 > 外貨入出金 (メインサイト)
  { menuId: 'SUB0202_0106', ccsDispId: 'CCS_DETAIL_CUSTOMER_PROPERTY' }, // 口座管理 > 接触履歴
  { menuId: 'SUB0202_0107', ccsDispId: 'CCS_CONTACT_HISTORY' }, // 口座管理 > 問合せ
  { menuId: 'SUB0202_0209', ccsDispId: 'CCS_INPUT_STOCK_ORDER' }, // 国内株式 > 立会外分売
  { menuId: 'SUB0202_0210', ccsDispId: 'CCS_INPUT_STOCK_ORDER' }, // 国内株式 > 立会外TRD
  { menuId: 'SUB0202_0211', ccsDispId: 'CCS_INPUT_STOCK_ORDER' }, // 国内株式 > 単元未満
  { menuId: 'SUB0202_0403', ccsDispId: 'CCS_SHOW_ACCOUNT_SUMMARY_BY_BATCH' }, // 投資信託 > 定期積立
  { menuId: 'SUB0202_0404', ccsDispId: 'main', urlId: 46 }, // 投資信託 > 外貨建MMF
  { menuId: 'SUB0202_0703', ccsDispId: 'CCS_DETAIL_CUSTOMER_PROPERTY' }, // 顧客管理 > 受発信状況
  { menuId: 'SUB0202_0704', ccsDispId: 'CCS_DOCUMENT_REAUEST_LIST' }, // 顧客管理 > 書類請求
  { menuId: 'SUB0202_08', ccsDispId: 'CCS_SHOW_ACCOUNT_SUMMARY_BY_BATCH' }, // 外部リンク
  { menuId: 'SUB0202_09', ccsDispId: 'CCS_DETAIL_CUSTOMER_PROPERTY' }, // CCS
  { menuId: 'SUB0207', ccsDispId: 'CCS_ACCOUNT_OPEN_PRE_PRINT' } // 口座開設メニュー > 口座開設申込書プレ印字
]
</script>

<style lang="scss" scoped>
.message {
  padding: 2rem;
  font-size: 18px;
}
:deep(.input-password) {
  .el-form-item {
    margin: 0.6rem 0;
  }
  .el-form-item__label {
    width: 180px;
    white-space: nowrap;
    line-height: 2;
  }
  .el-form-item__error {
    white-space: nowrap
  }
}
</style>
