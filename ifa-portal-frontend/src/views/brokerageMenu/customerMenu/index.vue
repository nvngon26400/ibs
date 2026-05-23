<template>
  <div class="customer-menu-wrapper">
    <!-- 顧客検索 -->
    <ifa-customer-select
      ref="ifaCustomerSelect"
      :is-locked="isLocked"
      @reset="handleResetCustomerSelect"
      @search="handleSearch"
      @display="handleDisplay"
      @delete="removeCustomerInfo"
    ></ifa-customer-select>

    <!-- ポータルコンテンツ -->
    <div
      v-show="isVisible"
      class="width-style"
    >
      <!-- 顧客情報 -->
      <ifa-customer-portal
        ref="ifaCustomerPortal"
        :broker-charge-list-count="brokerChargeListCount"
        :growth-investment-limit-year="growthInvestmentLimitYear"
        :accumulate-investment-limit-year="accumulateInvestmentLimitYear"
        :maintenance-rate-jpy-amount-description-message="maintenanceRateJpyAmountDescriptionMessage"
      ></ifa-customer-portal>

      <!-- メニューエリア -->
      <ifa-menu
        ref="ifaMenu"
        :menu-list="menuList"
        :manual="!isVisible"
        @show="handleShow"
        @update-customer-portal="updatePortal"
      ></ifa-menu>
      <div
        v-if="backMenuId"
        id="backDiv"
        :class="{ 'fixed-button': scrollFlg, 'floating-button': !scrollFlg, 'floating-button-hidden': isButtonHidden }"
      >
        <ifa-button
          id="backToPreviousScreen"
          name="backToPreviousScreen"
          :text="buttonLabel"
          color="secondary"
          small
          action-type="originalAction"
          @app-action-handler="backToPreviousScreen"
        ></ifa-button>
      </div>
    </div>

    <transition name="el-zoom-in-bottom">
      <div
        v-if="!isVisible"
        class="scrollbar-style"
      >
        <el-icon class="empty-icon"><Avatar></Avatar></el-icon>
        <p style="margin-left:2.5rem">顧客を選択してください。</p>
      </div>
    </transition>

    <!-- This component is invisible -->
    <ifa-requester
      id="customerPortalA001"
      action-id="SUB0202_00-02#A001"
      action-type="requestAction"
      msg-title="顧客ポータル_顧客情報"
      :request-model="requestModelA001"
      :disable-notification="true"
      @response-handler="handleResponse($event)"
      @response-error-handler="handleErrorResponse($event)"
    ></ifa-requester>

    <ifa-requester
      id="customerPortalA099"
      action-id="SUB0202_00-02#A099"
      action-type="requestAction"
      :request-model="requestModelA099"
      @response-handler="handleResponseA099($event)"
      @response-error-handler="handleErrorResponseA099($event)"
    ></ifa-requester>

  </div>
</template>

<script>
import { notifyMessage } from '@/utils/errorHandler'
import { getInitializeErrorConfig, getPortalMenuConfig } from '@/views/brokerageMenu/_config/portalMenuConfig.js'
import { getInquiryWrapperViewNames, setInquiryWrapperView } from '@/views/brokerageMenu/customerMenu/commonProcess/inquiry.js'
import IfaCustomerPortal from '@/views/brokerageMenu/customerMenu/IfaCustomerPortal'
import IfaCustomerSelect from '@/views/brokerageMenu/customerMenu/IfaCustomerSelect'
import { IfaCustomerPortalFormModel } from './js/IfaCustomerPortalFormModel'
import { CustomerInfoResponseModel } from './js/CustomerInfoResponseModel'
import { debounce } from 'lodash'

export default {
  components: {
    IfaCustomerSelect,
    IfaCustomerPortal
  },
  beforeRouteLeave(to, from) {
    // 顧客別メニュー以外に遷移するときは顧客情報を消去する
    if (to.path !== '/login') {
      this.removeCustomerInfo()
    } else {
      // ログアウト時には画面側の顧客情報のみを削除し、サーバ側の削除処理は不要
      this.$store.dispatch('page/removeCustomerInfo')
    }
    // スクロールのリスナーイベントを削除する
    if (this.backMenuId) {
      this.removeListener()
    }
  },
  data() {
    return {
      menuList: [],
      brokerChargeListCount: 0,
      growthInvestmentLimitYear: '',
      accumulateInvestmentLimitYear: '',
      maintenanceRateJpyAmountDescriptionMessage: '',
      initializeErrorList: [],
      isVisible: false,
      isLocked: false,
      requestModelA001: null,
      requestModelA099: null,
      customerPortalErrorList: [],
      errorFlag: false,
      onShowFlag: false,
      updatePortalFlag: true,
      backMenuId: '',
      buttonLabel: '',
      scrollFlg: false,
      isButtonHidden: false,
      scrollDistance: 0,
      scrollContainerInit: 0,
      resizeObserver: null,
      resizeObserverPortal: null,
      removingCustomerInfo: false,
      displayDirectParams: null
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  },
  watch: {
    $route: {
      handler(to, from) {
        if (to.name === from.name) {
          const params = this.$_getMenuParams()
          if (params && params.accountNumber) {
            let butenCode = params.butenCode
            let accountNumber = params.accountNumber
            if (!butenCode && accountNumber.includes('-')) {
              // @deprecated
              // パラメータに butenCode が含まれず､accountNumber にハイフン('-')で｢部店コード｣と｢口座番号｣が連結されている
              // デモ画面の古い形式で指定された場合
              const account = accountNumber.split('-')
              butenCode = account[0]
              accountNumber = account[1]
            }
            if (!this.isVisible || this.customerInfo.butenCode !== butenCode || this.customerInfo.accountNumber !== accountNumber) {
              // IfaRouterを経由して顧客が変更されたら顧客情報を消去する
              this.removeCustomerInfo()
              this.isVisible = false
              this.isLocked = true
              // 顧客情報消去後に部店コードと口座番号で遷移先の表示を行う(遅延実行する)
              this.displayDirectParams = {
                params: params,
                target: to.query.target
              }
            } else {
              this.$_finishShowMenu()
            }
          }
        }
      },
      deep: true
    },
    'scrollDistance': {
      // スクロールバーの位置を監視し、ボタンのスタイルを設定する
      handler() {
        if (this.scrollDistance > 10) {
          this.scrollFlg = false
        } else {
          this.scrollFlg = true
        }
      },
      deep: true
    }
  },
  created() {
    const getter = getPortalMenuConfig.bind(this)
    this.menuList = getter()
    const errorGetter = getInitializeErrorConfig.bind(this)
    this.initializeErrorList = errorGetter()
  },
  mounted() {
    // 顧客選択中は画面が出ていないので pageInfo を｢顧客検索｣する
    this.$store.dispatch('page/setPageInfo', {
      menuId: 'SUB0202',
      label: '顧客検索'
    })

    const params = this.$_getMenuParams()
    if (params && params.accountNumber) {
      this.isLocked = true
      if (params.menuId) {
        this.backMenuId = params.menuId
        this.buttonLabel = params.label + 'へ戻る'
      }
      if (this.removingCustomerInfo) {
        // 顧客情報消去中の場合､顧客情報消去後に部店コードと口座番号で遷移先の表示を行う(遅延実行する)
        this.displayDirectParams = {
          params: params,
          target: this.$route.query.target
        }
      } else {
        this.handleDisplayDirect(params, this.$route.query.target)
      }
    } else {
      this.$_finishShowMenu()
    }
    // スクロールのリスナーイベントを追加する
    if (this.backMenuId) {
      const scrollContainer = document.getElementsByClassName('scrollbar-style')[0]
      scrollContainer.addEventListener('scroll', this.handleScroll)
    }
  },
  methods: {
    handleShow(component) {
      // 業務エラー以外のエラーがあれば表示
      if (this.errorFlag) {
        this.customerPortalErrorList.forEach(err => {
          notifyMessage(
            err.errorLevel,
            err.message,
            err.title,
            err.requestedTime
          )
        })

        this.errorFlag = false
        this.customerPortalErrorList = []
      }

      this.$refs['ifaCustomerSelect']?.collapse(true)
      if (!this.isLocked && this.$store.getters.pageInfo.menuId === 'SUB0202_0101-01') { // 'SUB0202_0101-01': '資産状況'
        this.$refs['ifaCustomerPortal']?.collapse(false)
      } else {
        this.$refs['ifaCustomerPortal']?.collapse(true)
      }
      this.isLocked = true
      if (this.$store.getters.pageInfo.componentName !== 'customer-management') setInquiryWrapperView(this, getInquiryWrapperViewNames().inquiryList, {})
      if (this.$store.getters.pageInfo.componentName === 'ifa-portfolio') {
        component.setIsPrint(false)
      }
      // card高さの変更を監視するイベントリスナーを追加する
      if (this.backMenuId) {
        // this.$refs['ifaCustomerPortal']
        this.observeContainerResizePortal()
        // this.$refs['ifaMenu']
        this.observeContainerResize()
      }
    },
    /**
     * スクロールバーをスクロールするとき
     */
    handleScroll: debounce(function(event) {
      if (this.backMenuId) {
        // スクロールバーの位置を計算する
        const scrollbar = document.getElementsByClassName('scrollbar-style')[0]
        if (!scrollbar) {
          return
        }
        let currentHeight = scrollbar.scrollHeight
        // 40：ボタン「戻し」のデフォルト高さ
        if (Math.abs(this.scrollContainerInit - currentHeight) <= 40) {
          currentHeight = Math.min(this.scrollContainerInit, currentHeight)
        }
        if (currentHeight > scrollbar.clientHeight) {
          this.scrollDistance = currentHeight - scrollbar.scrollTop - scrollbar.clientHeight
        } else {
          this.scrollDistance = 0
        }
        this.scrollContainerInit = currentHeight
        this.resetButton()
      }
    }, 200),
    /**
     * this.$refs['ifaMenu']の高さの変更を監視するイベントリスナー
     */
    observeContainerResize() {
      if (this.backMenuId) {
        const container = this.$refs['ifaMenu']?.$el
        if (!container) {
          return
        }
        // 前回の高さ
        const offsetHeight = container.offsetHeight
        this.resizeObserver = new ResizeObserver((entries) => {
          for (const entry of entries) {
            // 現在の高さ
            const currentHeight = entry.contentRect.height
            // 高さの変化
            const heightChange = Math.abs(currentHeight - offsetHeight)
            // 高さの変化が40を超える場合、スクロールバーの位置を計算する
            if (heightChange > 40) {
              this.handleScroll()
            }
          }
        })
        this.resizeObserver.observe(container)
      }
    },
    /**
     * this.$refs['ifaCustomerPortal']の高さの変更を監視するイベントリスナー
     */
    observeContainerResizePortal() {
      if (this.backMenuId) {
        const container = this.$refs['ifaCustomerPortal']?.$el
        if (!container) return
        this.resizeObserverPortal = new ResizeObserver((entries) => {
          // / 高さの変化、スクロールバーの位置を計算する
          this.handleScroll()
        })
        this.resizeObserverPortal.observe(container)
      }
    },
    resetButton() {
      const backButton = document.getElementById('backDiv')
      // 対象のボタンが存在しない場合は処理を終了
      if (!backButton) return
      // ボタンが非表示であれば表示する
      if (this.isButtonHidden) this.isButtonHidden = false

      // 現在表示されている div (style 属性が display: none でないもの) を抽出します。
      const children = Array.from(document.querySelector('.el-tabs__content')?.children)?.find(div => {
        const style = div.getAttribute('style')
        return !(style && style.includes('display: none'))
      })
      // テキストに "確認/入力/変更" を含むボタンを探す
      const keywords = ['確認', '入力', '変更']
      // ボタンの位置情報を取得
      const floatingRect = backButton.getBoundingClientRect()
      children?.querySelectorAll('button')?.forEach(button => {
        const buttonText = button.textContent || button.innerText
        // テキストに "確認/入力/変更" を含むボタンを探す
        if (keywords.some(keyword => buttonText.includes(keyword))) {
          // ボタンの位置情報を取得
          const fixedRect = button.getBoundingClientRect()
          // 重複しているかどうかを判定
          if (!(floatingRect.right < fixedRect.left ||
                  floatingRect.left > fixedRect.right ||
                  floatingRect.bottom < fixedRect.top ||
                  floatingRect.top > fixedRect.bottom)) {
            // 重複があれば非表示に、そうでなければ表示する
            this.isButtonHidden = true
            return
          }
        }
      })
    },
    removeListener() {
      // スクロールのリスナーイベントを削除する
      const scrollContainer = document.getElementsByClassName('scrollbar-style')[0]
      if (scrollContainer) {
        scrollContainer.removeEventListener('scroll', this.handleScroll)
      }
      // リスナーイベントを削除する
      if (this.resizeObserver) this.resizeObserver.disconnect()
      if (this.resizeObserverPortal) this.resizeObserverPortal.disconnect()
    },
    handleSearch() {
      // 顧客検索A001のプレリクエストハンドラー
      this.$store.dispatch('customerPortalMenuList/setMenuList', {
        menuList: []
      })
      // 顧客選択中は画面が出ていないので pageInfo を｢顧客検索｣する
      this.$store.dispatch('page/setPageInfo', {
        menuId: 'SUB0202',
        label: '顧客検索'
      })

      this.isVisible = false
      this.isLocked = false
      this.$refs['ifaMenu'].reset()
      this.$refs['ifaCustomerSelect']?.collapse(false)
      this.$refs['ifaCustomerPortal']?.collapse(false)
    },
    handleDisplay(param) {
      this.isVisible = false
      this.backMenuId = ''
      this.buttonLabel = ''
      this.removeListener()
      this.getCustomerInfo(param.rowData.butenCode, param.rowData.accountNumber)
      // 顧客選択後に初期表示される際、2回描画を防ぐ設定
      this.updatePortalFlag = false
    },
    handleDisplayDirect(param, target) {
      history.replaceState('', '', window.location.href.replace(/\?.+$/, ''))
      if (param.butenCode) {
        // パラメータに butenCode が含まれるなら｢部店コード｣と｢口座番号｣を分けて指定されている
        this.getCustomerInfo(param.butenCode, param.accountNumber)
      } else if (param.accountNumber.includes('-')) {
        // @deprecated
        // パラメータに butenCode が含まれず､accountNumber にハイフン('-')で｢部店コード｣と｢口座番号｣が連結されている
        // デモ画面の古い形式で指定された場合
        const account = param.accountNumber.split('-')
        this.getCustomerInfo(account[0], account[1])
      } else {
        // パラメータが不正なため､顧客選択の状態で終了させる
        this.$_finishShowMenu()
      }
      // 顧客選択後に初期表示される際、2回描画を防ぐ設定
      this.updatePortalFlag = false
    },
    async getCustomerInfo(butenCode, accountNumber) {
      this.requestModelA001 = { butenCode, accountNumber }
      // IfaRquester 内の IfaButton が連打しないようになっているため､ここでは特に連打対策は行わない｡
      this.$nextTick(() => {
        document.getElementById('customerPortalA001').click()
      })
    },
    /**
     * 顧客共通情報受信処理
     * IfaRequestor で受信した顧客共通情報を処理する｡
     * response-handler / response-error-handler 共通処理
     * @param data サーバレスポンス
     */
    handleResponse(data) {
      this.requestModelA001 = null
      // 顧客共通情報を store に格納する
      this.storeCustomerInfo(data.dataList[0])
      // IfaCustomerPortal 連携用に仲介業者営業員リスト件数、成長投資枠(当年)、つみたて投資枠(当年)、維持率(円貨)説明文言を保存する
      this.setOtherCustomerInfo(data.dataList[0])
      // 顧客情報取得エラーリストを処理する
      this.handleCustomerInfoAcquireErrorList(data.dataList[0].customerInfoAcquireErrorList[0], data.requestedTime)
      this.isVisible = true
      this.$nextTick(() => {
        if (this.backMenuId) {
          const div = document.querySelector('.el-tabs__content')
          const button = document.querySelector('#backDiv')
          div.appendChild(button)
        }
        this.$refs['ifaCustomerPortal'].onShow()
      })
    },
    handleErrorResponse(response) {
      notifyMessage(
        response.errorLevel,
        response.message,
        IfaCustomerPortalFormModel.title,
        response.requestedTime
      )
      this.requestModelA001 = null

      // 不具合抑止のための処理。
      // エラー発生時に一度顧客検索画面を閉じない場合
      // ローディングが消えない不具合が発生する。
      this.$refs['ifaCustomerSelect']?.collapse(false)
      this.$_finishShowMenu()
    },
    handleResetCustomerSelect() {
      this.removeCustomerInfo(false)
      this.isLocked = false
      this.isVisible = false
    },
    storeCustomerInfo(customerInfo) {
      const ci = new CustomerInfoResponseModel(customerInfo)
      this.$store.dispatch('page/setCustomerInfo', ci)
    },
    setOtherCustomerInfo(data) {
      if (data) {
        this.brokerChargeListCount = Number(data.brokerChargeListCount ?? 0)
        this.growthInvestmentLimitYear = data.growthInvestmentLimitYear ?? ''
        this.accumulateInvestmentLimitYear = data.accumulateInvestmentLimitYear ?? ''
        this.maintenanceRateJpyAmountDescriptionMessage = data.maintenanceRateJpyAmountDescriptionMessage ?? ''
      }
    },
    async handleCustomerInfoAcquireErrorList(errorList, timeStamp) {
      this.customerPortalErrorList = []

      // メッセージダイアログのタイトルをIfaRouterの情報から設定
      const title = this.$store.getters.pageInfo?.label ?? ''

      // 顧客情報取得エラーリスト.ISA買付可能枠の取得失敗メッセージ
      if (errorList.isaBuyLimitAcquireFailureErrorLevel) {
        const error = {
          errorLevel: errorList.isaBuyLimitAcquireFailureErrorLevel,
          message: errorList.isaBuyLimitAcquireFailureMsg,
          title: title,
          requestedTime: timeStamp
        }

        this.customerPortalErrorList.push(error)
      }

      // 買付余力情報（円貨）・信用余力情報（円貨）取得失敗メッセージ
      if (errorList.marginPowerInfoYenAcquireFailureErrorLevel) {
        const error = {
          errorLevel: errorList.marginPowerInfoYenAcquireFailureErrorLevel,
          message: errorList.marginPowerInfoYenAcquireFailureMsg,
          title: title,
          requestedTime: timeStamp
        }

        this.customerPortalErrorList.push(error)
      }

      // 外貨建取引口座開設状況取得失敗メッセージ
      if (errorList.foreignTradeAccountOpenStatusAcquireFailureErrorLevel) {
        const error = {
          errorLevel: errorList.foreignTradeAccountOpenStatusAcquireFailureErrorLevel,
          message: errorList.foreignTradeAccountOpenStatusAcquireFailureMsg,
          title: title,
          requestedTime: timeStamp
        }

        this.customerPortalErrorList.push(error)
      }

      // 買付余力情報（外貨）取得失敗メッセージ
      if (errorList.buyReservePowerInfoForeignAcquireFailureErrorLevel) {
        const error = {
          errorLevel: errorList.buyReservePowerInfoForeignAcquireFailureErrorLevel,
          message: errorList.buyReservePowerInfoForeignAcquireFailureMsg,
          title: title,
          requestedTime: timeStamp
        }

        this.customerPortalErrorList.push(error)
      }

      // 米株信用口座開設状況取得失敗メッセージ
      if (errorList.usStockMarginAccountOpenStatusAcquireFailureErrorLevel) {
        const error = {
          errorLevel: errorList.usStockMarginAccountOpenStatusAcquireFailureErrorLevel,
          message: errorList.usStockMarginAccountOpenStatusAcquireFailureMsg,
          title: title,
          requestedTime: timeStamp
        }

        this.customerPortalErrorList.push(error)
      }

      // 信用余力情報（外貨）の取得失敗メッセージ
      if (errorList.marginPowerInfoForeignAcquireFailureErrorLevel) {
        const error = {
          errorLevel: errorList.marginPowerInfoForeignAcquireFailureErrorLevel,
          message: errorList.marginPowerInfoForeignAcquireFailureMsg,
          title: title,
          requestedTime: timeStamp
        }

        this.customerPortalErrorList.push(error)
      }

      // 顧客情報取得エラーリストの有無を設定
      if (this.customerPortalErrorList.length > 0) {
        this.errorFlag = true
      } else {
        this.errorFlag = false
      }
    },
    removeCustomerInfo(onShowFlag) {
      // 顧客情報の削除前に部店コードと口座番号からリクエストモデルを生成する
      this.requestModelA099 = {
        butenCode: this.customerInfo.butenCode,
        accountNumber: this.customerInfo.accountNumber
      }

      // 画面側の顧客情報を削除する
      this.$store.dispatch('page/removeCustomerInfo')

      // サーバ側の顧客情報を削除する
      // 画面定義書にないアクションのため､アクションID を仮に A099 とする
      if (onShowFlag) {
        this.onShowFlag = onShowFlag
      } else {
        this.onShowFlag = false
      }
      // 顧客共通情報削除中にする
      this.removingCustomerInfo = true
      document.getElementById('customerPortalA099').click()
    },
    handleResponseA099(response) {
      this.requestModelA099 = null
      // 顧客共通情報削除中を解除する
      this.removingCustomerInfo = false
      if (this.displayDirectParams) {
        // 顧客共通情報削除中に顧客選択された場合は､画面遷移を行う｡
        this.handleDisplayDirect(this.displayDirectParams.params, this.displayDirectParams.target)
        this.displayDirectParams = null
      }
      if (this.onShowFlag) {
        this.$nextTick(() => {
          this.$refs['ifaCustomerSelect'].onShow()
        })
      }
    },
    handleErrorResponseA099(response) {
      // /common/ifaCacheManageRemoveCustomerInfo のレスポンスは常に SUCCESS を返すのでこの処理は呼ばれない
      this.$_logDebug(response)
      this.requestModelA099 = null
    },
    updatePortal() {
      if (this.updatePortalFlag) {
        this.getCustomerInfo(this.customerInfo.butenCode, this.customerInfo.accountNumber)
      }
      this.updatePortalFlag = true
    },
    backToPreviousScreen() {
      // 前の画面に戻る
      const params = { backFlg: true }
      this.$_startShowMenu(this.backMenuId, params)
    }
  }
}
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";
@import "@/styles/customerMenu.scss";
.el-sub-menu__title {
  width: 76px;
}
.empty-icon {
  font-size: 15rem;
  color: #eee;
}
.customer-menu-wrapper {
  display: flex;
  margin: 0.2rem 0.5rem 0 0.5rem;
}
:deep(.el-tabs__item.is-top.is-active) {
  background-color: #f39800;
  color: #fff;
}
.scrollbar-style {
  overflow-x: auto;
}
.width-style {
  min-width: 1300px;
  width: 100%;
}
.customer-potal-menubar {
  display: flex;
}
#customer-portal-menu :deep(.el-sub-menu__title) {
  padding: 0 20px !important;
}
.menuItems_un_visible {
  padding-left: 0px;
  padding-right: 0px;
  height: 0px;
  border-bottom-width: 0px;
}
.fixed-button {
  margin-left: 15px;
  margin-bottom: 10px;
}
.floating-button {
  position: fixed;
  margin-left: 15px;
  bottom: 50px;
  transition: all 0.3s;
  z-index: 2000;
}
.floating-button-hidden {
  visibility: hidden;
}
</style>
