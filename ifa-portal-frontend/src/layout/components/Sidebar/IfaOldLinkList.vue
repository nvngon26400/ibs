<template>
  <div
    v-if="isVisibleSidbarLink"
    :class="{ 'collapse': collapse }"
  >
    <transition>
      <div
        v-if="collapse"
        class="el-menu-item submenu-title-noDropdown"
        @click="isVisible = true"
      >
        <el-tooltip
          content="旧IFAポータルへ"
          placement="right"
        >
          <i
            class="seeds-external svg-icon"
            style="margin-left: 20px;"
          ></i>
        </el-tooltip>
      </div>
      <div
        v-else
        class="el-menu-item submenu-title-noDropdown"
        @click="isVisible = true"
      >
        <i class="seeds-external svg-icon"></i>
        <span class="sub-el-title">旧IFAポータルへ</span>
      </div>
    </transition>

    <!-- ダイアログ -->
    <el-dialog
      v-model="isVisible"
      :append-to-body="true"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      @open="openDialog"
    >
      <template #header>
        <span>旧IFAポータルサイトメニュー</span>
      </template>
      <el-row style="margin-top: 1rem">
        <el-col
          span="20"
          class="close-button"
        >
          <ifa-button
            text="閉じる"
            width="90"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>

      <el-row
        v-for="(data, dataIndex) in accessibleMenuList"
        :key="dataIndex"
      >
        <el-col>{{ data.category }}</el-col>
        <el-col
          v-for="(item, itemIndex) in data.link"
          :key="itemIndex"
        >
          <el-link @click="requestRedirect(item.redirectTo)">
            {{ item.name }}
          </el-link>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import request from '@/utils/request'
import { isAccessible } from '@/utils/controlAuth'

export default {
  props: {
    collapse: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      isVisible: false,
      accessibleMenuList: [],
      loading: '',
      isVisibleSidbarLink: false,
      linkData: [
        {
          category: '個人情報管理',
          link: [
            { name: 'ダウンロード・印刷履歴管理', id: 'SUB024', redirectTo: '/administrator/downloadHistoryManagement' },
            { name: '個人情報管理台帳照会', id: 'SUB026', redirectTo: '/administrator/dispAdminPersonalyLedger' }
          ]
        },
        {
          category: '手数料・報酬管理',
          link: [
            { name: '収受料率等マスタ', id: 'SUB031', redirectTo: '/broker/inpFeeRate' },
            { name: 'IFA収益表', id: 'SUB039', redirectTo: '/broker/inpIfaProfit' },
            { name: '評価用時価補正', id: 'SUB045', redirectTo: '/administrator/appraiseMarketPriceFix' },
            { name: 'その他報酬・その他手数料', id: 'SUB046', redirectTo: '/administrator/extraRewardAndFee' },
            { name: '共同店舗共募報酬管理', id: 'SUB061', redirectTo: '/administrator/jointRateManagement' },
            { name: '仲介業者支払通知書管理', id: 'SUB069', redirectTo: '/administrator/paymentNoticeManagement' }
          ]
        },
        {
          category: '取引データ管理',
          link: [
            { name: 'STARアップロードファイル再取得', id: 'SUB060', redirectTo: '/administrator/starUploadFileList' },
            { name: '電子交付同意データ登録', id: 'SUB063', redirectTo: '/administrator/ipopoElecApprovalAgreementUpload' },
            { name: '目論見書閲覧データ登録', id: 'SUB066', redirectTo: '/administrator/ipopoProspectusUpload' },
            { name: '外債買付代金入力', id: 'SUB067', redirectTo: '/administrator/inpForeignBondBuyAmount' }
          ]
        },
        {
          category: ' 一任契約登録・削除',
          link: [
            { name: ' 一任契約登録・削除', id: 'SUB062', redirectTo: '/broker/entrustInvestment' }
          ]
        },
        {
          category: '外国株式',
          link: [
            { name: '店頭取引銘柄マスタ一覧', id: 'SUB052', redirectTo: '/counter/counterBrandMasterInfoList' },
            { name: '店頭取引販売枠設定（銘柄毎）一覧', id: 'SUB053', redirectTo: '/counter/counterBrandSalesInfoList' },
            { name: '店頭取引販売枠管理（仲介業者毎）一覧', id: 'SUB054', redirectTo: '/counter/counterBrokerSalesInfoList' },
            { name: '店頭取引注文ステータス選択', id: 'SUB055', redirectTo: '/counter/orderStatusList' }
          ]
        },
        {
          category: '共同募集',
          link: [
            { name: '共同募集　顧客管理', id: 'SUB035', redirectTo: '/broker/jointUser' },
            { name: '共同募集　取引検索', id: 'SUB034', redirectTo: '/broker/inpJointTradeHist' },
            { name: '共同募集　信託報酬', id: 'SUB040', redirectTo: '/broker/inpJointTrustFee' },
            { name: '共同募集　証券・金銭 残高照会', id: 'SUB038', redirectTo: '/broker/jointUserBalance' }
          ]
        },
        {
          category: '共同店舗',
          link: [
            { name: '共同店舗取引検索', id: 'SUB064', redirectTo: '/broker/jointStoreTradeHist' },
            { name: '共同店舗信託報酬', id: 'SUB065', redirectTo: '/broker/jointStoreTrustFee' }
          ]
        },
        {
          category: 'ログイン者管理',
          link: [
            { name: '二要素認証管理', id: 'SUB057', redirectTo: '/administrator/cancelMultiplyCertify' }
          ]
        },
        {
          category: '業者メンテナンス',
          link: [
            { name: '仲介業者支店情報メンテナンス', id: 'SUB048', redirectTo: '/administrator/mediateBranchInfo' },
            { name: '支店管理仲介業者情報メンテナンス', id: 'SUB049', redirectTo: '/administrator/mediateBranchManagement' },
            { name: '仲介業者営業員情報メンテナンス', id: 'SUB050', redirectTo: '/administrator/mediateChargeInfo' }
          ]
        }
      ]
    }
  },
  mounted() {
    this.isVisibleSidbarLink = isAccessible('SUB99-99')
  },
  methods: {
    openDialog() {
      if (this.accessibleMenuList.length === 0) {
        this.getAccessibleMenuList()
      }
    },
    onDialogClose() {
      this.isVisible = false
    },
    requestRedirect(redirectTo) {
      request.post('/common/redirectPreviousSystem', {
        redirectTo: redirectTo
      }).then((data) => {
        const newWindow = window.open('about:blank', '_blank')
        newWindow.document.body.innerHTML = data // レスポンスで返ってきた画面を新しいWindowに設定する。
        newWindow.document.getElementById('form').submit() // 新しい画面のSubmitを実行しリダイレクトさせる。
      })
    },
    getAccessibleMenuList() {
      this.loading = this.$loading({
        lock: false,
        text: '処理中',
        background: 'rgba(0, 0, 0, 0.4)'
      })
      // 現行メニュー認可情報を取得する。
      request.post('/common/previousSystemMenu', {}).then((data) => {
        // メニューの認可制御を行う。
        this.accessibleMenuList = this.controlAuth(data.dataList)
      }).finally(() => {
        this.loading.close()
      })
    },
    controlAuth(accessibleViewList) {
      const accessibleMenuList = []
      // 全体のメニューから、認可情報をもとにアクセス可能なメニューのみに絞り込む。
      this.linkData.forEach(menu => {
        const accessibleMenu = menu
        const accessibleMenuItems = []
        // 2階層目
        menu.link.forEach(link => {
          if (accessibleViewList.includes(link.id)) {
            accessibleMenuItems.push(link)
          }
        })
        if (accessibleMenuItems.length > 0) {
          accessibleMenu.link = accessibleMenuItems
          accessibleMenuList.push(accessibleMenu)
        }
      })
      return accessibleMenuList
    }
  }
}
</script>

<style lang="scss" scoped>
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}

:deep(.el-dialog) {
  width: 900px;
  padding: 30px 10px;
}
:deep(.el-dialog__header) {
  color: #18181A;
  padding: 0px;
}
:deep(.el-dialog__header) span {
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
}
:deep(.el-dialog__title) {
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
  padding: 0px;
}
:deep(.el-dialog__body) {
  padding: 0px;
  margin-bottom: 1rem;
}
:deep(.el-dialog--center) {
  z-index: 1000;
}
.el-link {
  margin-left: 50px;
}
#app .sidebar-container a {
  width: auto;
}
.el-col {
  margin: 10px 0 5px 80px;
}
</style>
