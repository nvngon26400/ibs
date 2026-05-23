/* Layout */
import Layout from '@/layout'

import { createRouter, createWebHashHistory } from 'vue-router'
/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/IfaLogin'),

    hidden: true,
    meta: {}
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true,
    meta: {}
  },
  {
    path: '/menu/:menuId',
    name: 'menu',
    component: () => import('@/components/Router/IfaRouter.vue'),
    hidden: true,
    meta: {}
  },
  {
    path: '/to',
    name: 'to',
    redirect: to => {
      // /to の場合､予め IfaRouter で設定した URL にリダイレクトする
      return to.query.url
    },
    hidden: true,
    meta: {}
  },
  {
    path: '/brokerageMenu/customerMenu/accountManage/assetsStatus/ifaPortfolio',
    name: 'Ifa-Portfolio',
    component: () => import('@/views/brokerageMenu/customerMenu/accountManage/assetsStatus/IfaPortfolio'),
    hidden: true,
    meta: {}
  },
  {
    path: '/navbar',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'suggestionBox',
        name: 'Navbar-SuggestionBox',
        component: () => import('@/views/suggestionBox/index'),
        meta: { title: '目安箱'}
      }
    ]
  },
  {
    path: '/faq',
    name: 'Ifa-Faq',
    component: () => import('@/views/faq/IfaFaq'),
    hidden: true,
    meta: {}
  },
  {
    path: '/',
    component: Layout,
    redirect: '/init',
    meta: { title: 'ホーム', icon: 'seeds-home', menuId: 'INIT00' },
    children: [
      {
        path: 'init',
        name: 'Init',
        hidden: true,
        component: () => import('@/components/Router/IfaInit'),
        meta: { title: 'ホーム', icon: 'seeds-home', menuId: 'INIT00', breadcrumb: false }
      },
      {
        path: 'wholePortal',
        name: 'WholePortal',
        component: () => import('@/views/wholePortal/IfaWholePortalHome'),
        meta: { title: 'ホーム', icon: 'seeds-home', menuId: 'SUB01-01', breadcrumb: false }
      }
    ]
  },
  {
    path: '/onLineAccountOpen',
    component: Layout,
    redirect: '/onLineAccountOpen',
    name: 'accountOpenMenu-Menu',
    meta: { title: 'オンライン口座開設', icon: 'seeds-window', menuId: 'MAIN08' },
    children: [
      {
        path: 'onLineAccountOpen',
        name: 'onLineAccountOpen',
        component: () => import('@/views/accountOpenMenu/onLineAccountOpen/index'),
        meta: { title: 'オンライン口座開設', icon: 'seeds-window', menuId: 'SUB0207_0201' }
      }
    ]
  },
  {
    path: '/newAccountOpenImcompleteStatus',
    component: Layout,
    redirect: '/newAccountOpenImcompleteStatus',
    name: 'newAccountOpenImcompleteStatus-Menu',
    meta: { title: '新規口座開設不備状況', icon: 'seeds-error', menuId: 'MAIN09' },
    children: [
      {
        path: 'newAccountOpenImcompleteStatus',
        name: 'newAccountOpenImcompleteStatus',
        component: () => import('@/views/accountOpenMenu/newAccountOpenImcompleteStatus/index'),
        meta: { title: '新規口座開設不備状況', icon: 'seeds-error', menuId: 'SUB020305' }
      }
    ]
  },
  {
    path: '/brokerageMenu',
    component: Layout,
    redirect: '/brokerageMenu/customerMenu',
    name: 'brokerageMenu-Menu',
    meta: { title: '仲介業メニュー', icon: 'seeds-launcher', menuId: 'MAIN02' },
    children: [
      {
        path: 'customerList',
        name: 'brokerageMenu-List',
        component: () => import('@/views/brokerageMenu/customerList/index'),
        meta: { title: '顧客一覧', icon: 'seeds-angle-right', menuId: 'SUB0201' }
      },
      {
        path: 'customerMenu',
        name: 'brokerageMenu-CustomerMenu',
        component: () => import('@/views/brokerageMenu/customerMenu/index'),
        meta: { title: '顧客別メニュー', icon: 'seeds-angle-right', menuId: 'SUB0202' }
      },
      {
        path: 'wholeCustomer',
        name: 'BrokerageMenu-WholeCustomer',
        component: () => import('@/views/brokerageMenu/wholeCustomer/index'),
        meta: { title: '全顧客メニュー', icon: 'seeds-angle-right', menuId: 'SUB0203' },
        children: [
          {
            path: 'alertInfo',
            name: 'alertInfo',
            component: () => import('@/views/brokerageMenu/wholeCustomer/alertInfo/index'),
            meta: { title: '　アラート情報', menuId: 'SUB020301' }
          },
          {
            path: 'tradeStatusBalanceLookupSearch',
            name: 'tradeStatusBalanceLookupSearch',
            component: () => import('@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/index'),
            meta: { title: '　取引状況/残高検索・照会', menuId: 'SUB020302' }
          },
          {
            path: 'customerDestinationBankAccount',
            name: 'customerDestinationBankAccount',
            component: () => import('@/views/brokerageMenu/wholeCustomer/customerDestinationBankAccount/index'),
            meta: { title: '　顧客振込先金融機関口座', menuId: 'SUB020303' }
          },
          {
            path: 'inquirySearchForManager',
            name: 'InquirySearchForManager',
            component: () => import('@/views/brokerageMenu/wholeCustomer/inquirySearchForManager/index'),
            meta: { title: '　接触履歴（入力）検索', menuId: 'SUB020304' }
          }
          // ph2
          // {
          //   path: 'courseChangeManagement',
          //   name: 'course-change-management',
          //   component: () => import('@/views/brokerageMenu/courseChangeManagement/index'),
          //   meta: { title: 'コース変更状況', icon: 'seeds-angle-right', menuId: 'SUB020306' }
          // }
        ]
      },
      {
        path: 'ipoPo',
        name: 'BrokerageMenu-IpoPo',
        component: () => import('@/views/brokerageMenu/ipoPo/index'),
        meta: { title: 'IPO/PO', icon: 'seeds-angle-right', menuId: 'SUB0204' }
      },
      {
        path: 'commFee',
        name: 'BrokerageMenu-CommFee',
        meta: { title: '手数料・報酬', icon: 'seeds-angle-right', menuId: 'SUB0205' },
        children: [
          {
            path: 'repCustomerCommList',
            name: 'RepCustomerCommList',
            component: () => import('@/views/brokerageMenu/commFee/repCustomerCommList/index'),
            meta: { title: '　担当顧客別手数料一覧', menuId: 'SUB020501' }
          },
          {
            path: 'commFee',
            name: 'CommFee',
            component: () => import('@/views/brokerageMenu/commFee/commFee/index'),
            meta: { title: '　手数料・報酬', menuId: 'SUB020502' }
          },
          {
            path: 'trustFee',
            name: 'TrustFee',
            component: () => import('@/views/brokerageMenu/commFee/trustFee/index'),
            meta: { title: '　信託報酬', menuId: 'SUB020503' }
          },
          {
            path: 'sbiWrapManageFee',
            name: 'SbiWrapManageFee',
            component: () => import('@/views/brokerageMenu/commFee/sbiWrapManageFee/index'),
            meta: { title: '　SBIラップ管理報酬', menuId: 'SUB020504' }
          },
          {
            path: 'levelFee',
            name: 'LevelFee',
            component: () => import('@/views/brokerageMenu/commFee/levelFee/index'),
            meta: { title: '　レベルフィー', menuId: 'SUB020505' }
          }
        ]
      },
      {
        path: 'allSortsApplyReport',
        name: 'BrokerageMenu-AllSortsApplyReport',
        component: () => import('@/views/brokerageMenu/allSortsApplyReport/index'),
        meta: { title: '各種申請・報告', icon: 'seeds-angle-right', menuId: 'SUB0209' }
      },
      // 共同募集共同店舗 追加 start ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
      {
        path: 'jointSubscript',
        name: 'BrokerageMenu-jointSubscript',
        component: () => import('@/views/brokerageMenu/jointSubscript/index'),
        meta: { title: '共同募集', icon: 'seeds-angle-right', menuId: 'SUB0206' }
      },
      {
        path: 'jointMarket',
        name: 'BrokerageMenu-JointMarket',
        component: () => import('@/views/brokerageMenu/jointMarket/index'),
        meta: { title: '共同店舗', icon: 'seeds-angle-right', menuId: 'SUB0208' }
      }
      // 共同募集共同店舗 追加 end   ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    ]
  },
  {
    path: '/complianceReport',
    component: Layout,
    redirect: '/complianceReport/complianceReport',
    name: 'complianceReport-Menu',
    meta: { title: 'コンプライアンス通信', icon: 'seeds-edit', menuId: 'MAIN03' },
    children: [
      {
        path: 'complianceReport',
        name: 'complianceReport',
        component: () => import('@/views/complianceReport/index'),
        meta: { title: 'コンプライアンス通信', icon: 'seeds-edit', menuId: 'SUB0302-01', breadcrumb: false }
      }
    ]
  },
  {
    path: '/internalAdminMenu',
    component: Layout,
    redirect: '/internalAdminMenu/monthlyImplementationItem',
    name: 'InternalAdminMenu-Menu',
    meta: { title: '内部管理責任者メニュー', icon: 'seeds-eye', menuId: 'MAIN04' },
    alwaysShow: true,
    children: [
      {
        path: 'monthlyImplementationItem',
        name: 'InternalAdminMenu-MonthlyImplementationItem',
        component: () => import('@/views/internalAdminMenu/monthlyImplementationItem/index'),
        meta: { title: 'マンスリー実施項目', icon: 'seeds-angle-right', menuId: 'SUB0401' }
      },
      {
        path: 'formAcquire',
        name: 'InternalAdminMenu-FormAcquire',
        component: () => import('@/views/internalAdminMenu/formAcquire/index'),
        meta: { title: '帳票取得', icon: 'seeds-angle-right', menuId: 'SUB0402' }
      },
      {
        path: 'byYearAccountQuantityFeeAmountLookup',
        name: 'InternalAdminMenu-ByYearAccountQuantityFeeAmountLookup',
        component: () => import('@/views/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/index'),
        meta: { title: '年度別口座数・報酬額照会', icon: 'seeds-angle-right', menuId: 'SUB0406' }
      },
      {
        path: 'personalInfoManage',
        name: 'InternalAdminMenu-PersonalInfoManage',
        component: () => import('@/views/internalAdminMenu/personalInfoManage/index'),
        meta: { title: '個人情報管理', icon: 'seeds-angle-right', menuId: 'SUB0403' }
      },
      {
        path: 'authMailAddressChange',
        name: 'InternalAdminMenu-AuthMailAddressChange',
        component: () => import('@/views/internalAdminMenu/authMailAddressChange/index'),
        meta: { title: '認証用メールアドレス変更', icon: 'seeds-angle-right', menuId: 'SUB0404' }
      },
      {
        path: 'payNotificationDocDownload',
        name: 'InternalAdminMenu-PayNotificationDocDownload',
        component: () => import('@/views/internalAdminMenu/payNotificationDocDownload/index'),
        meta: { title: '支払通知書ダウンロード', icon: 'seeds-angle-right', menuId: 'SUB0405' }
      },
      {
        path: 'complianceRelatedReport',
        name: 'InternalAdminMenu-ComplianceRelatedReport',
        component: () => import('@/views/internalAdminMenu/complianceRelatedReport/index'),
        meta: { title: '各種申請・報告(管理者向け)', icon: 'seeds-angle-right', menuId: 'SUB0408' }
      },
      {
        path: 'monthCustomerNum',
        name: 'InternalAdminMenu-MonthCustomerNum',
        component: () => import('@/views/internalAdminMenu/monthCustomerNum/index'),
        meta: { title: '月末口座数', icon: 'seeds-angle-right', menuId: 'SUB0407' }
      }
    ]
  },
  {
    path: '/companyEmployeeMenu',
    component: Layout,
    redirect: '/companyEmployeeMenu/infoRegister',
    name: 'CompanyEmployeeMenu-Menu',
    meta: { title: '社員用メニュー', icon: 'seeds-user-settings', menuId: 'MAIN05' },
    alwaysShow: true,
    children: [
      {
        path: 'infoRegister',
        name: 'CompanyEmployeeMenu-InfoRegister',
        component: () => import('@/views/companyEmployeeMenu/infoRegister/index'),
        meta: { title: 'インフォメーション登録', icon: 'seeds-angle-right', menuId: 'SUB0501' }
      },
      // ph2
      // {
      //   path: 'managePersonalyLedgerAdmin',
      //   name: 'ManagePersonalyLedgerAdmin',
      //   component: () => import('@/views/administrator/managePersonalyLedgerAdmin/index'),
      //   meta: { title: '個人情報管理', icon: 'DocumentChecked', menuId: 'SUB0502' }
      // },
      // {
      //   path: 'commissionReward',
      //   name: 'CommissionReward',
      //   component: () => import('@/views/administrator/commissionReward/index'),
      //   meta: { title: '手数料・報酬管理', icon: 'DocumentChecked', menuId: 'SUB0503' }
      // },
      {
        path: 'tradeDataManage',
        name: 'CompanyEmployeeMenu-TradeDataManage',
        component: () => import('@/views/companyEmployeeMenu/tradeDataManage/index'),
        meta: { title: '取引データ管理', icon: 'seeds-angle-right', menuId: 'SUB0504' }
      },
      {
        path: 'complianceReport',
        name: 'CompanyEmployeeMenu-ComplianceReport',
        component: () => import('@/views/companyEmployeeMenu/complianceReport/index'),
        meta: { title: 'コンプライアンス通信', icon: 'seeds-angle-right', menuId: 'SUB0505' }
      },
      {
        path: 'selfInspect',
        name: 'CompanyEmployeeMenu-SelfInspect',
        component: () => import('@/views/companyEmployeeMenu/selfInspect/index'),
        meta: { title: '自己点検', icon: 'seeds-angle-right', menuId: 'SUB0506' }
      },
      {
        path: 'suggestionBox',
        name: 'CompanyEmployeeMenu-SuggestionBox',
        component: () => import('@/views/suggestionBox/index'),
        meta: { title: '目安箱', icon: 'seeds-angle-right', menuId: 'SUB0511' }
      },
      {
        path: 'releaseNote',
        name: 'CompanyEmployeeMenu-ReleaseNote',
        component: () => import('@/views/companyEmployeeMenu/releaseNote/index'),
        meta: { title: 'リリースノート(社員用)', icon: 'seeds-angle-right', menuId: 'SUB0512' }
      },
      {
       path: 'jointContract',
       name: 'CompanyEmployeeMenu-JointContract',
       component: () => import('@/views/companyEmployeeMenu/jointContract/index'),
       meta: { title: '共同募集', icon: 'seeds-angle-right', menuId: 'SUB0513' }
      }
      // ph2
      // {
      //   path: 'entrustInvestment',
      //   name: 'entrust-Investment',
      //   component: () => import('@/views/brokerageMenu/entrustInvestment/index'),
      //   meta: { title: '一任契約登録・削除', icon: 'DocumentChecked', menuId: 'SUB0507' }
      // },
      // {
      //   path: 'foreignStocks',
      //   name: 'ForeignStocks',
      //   component: () => import('@/views/business/foreignStocks/index'),
      //   meta: { title: '外国株式', icon: 'ElementPlus', menuId: 'SUB0508' }
      // },
    ]
  },
  {
    path: '/systemManageMenu',
    component: Layout,
    redirect: '/systemManageMenu/loginUserManage',
    name: 'SystemManageMenu-Menu',
    meta: { title: 'システム管理メニュー', icon: 'seeds-settings', menuId: 'MAIN06' },
    alwaysShow: true,
    children: [
      {
        path: 'loginUserManage',
        name: 'SystemManageMenu-LoginUserManage',
        component: () => import('@/views/systemManageMenu/loginUserManage/loginUserManage/index'),
        meta: { title: 'ログイン者管理', icon: 'seeds-angle-right', menuId: 'SUB0601' }
      },
      {
        path: 'sbiSecurityNotificationRegister',
        name: 'SystemManageMenu-sbiSecurityNotificationRegister',
        component: () => import('@/views/systemManageMenu/sbiSecurityNotificationRegister/index'),
        meta: { title: 'SBI証券からのご連絡登録', icon: 'seeds-angle-right', menuId: 'SUB0602' }
      }
    ]
  },
  {
    path: '/sandbox',
    component: Layout,
    redirect: '/sandbox',
    name: 'sandbox-Menu',
    meta: { title: '※共通部品テスト※', icon: 'seeds-fire', menuId: 'DBG01' },
    children: [
      {
        path: 'sandBox',
        name: 'sandBox',
        component: () => import('@/views/sandbox/index'),
        meta: { title: '※共通部品テスト※', icon: 'seeds-fire', menuId: 'DBG01' }
      }
    ]
  }
]

// 404 page must be placed at the end !!!
constantRoutes.push({ path: '/:catchAll(.*)', redirect: '/404', hidden: true })

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = []

const createCustomRouter = () => {
  const router = createRouter({
    history: createWebHashHistory(),
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
  })

  router.afterEach((to, from) => {
    const appMain = document.getElementById('app-main')
    if (appMain) {
      appMain.scrollTo({ top: 0 })
    }
  })

  return router
}

const router = createCustomRouter()
// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createCustomRouter()
  router.matcher = newRouter.matcher // reset router
}

router.onError(error => {
  const alertErrorMsg = '最新バージョンが公開されました。ページを再読み込みしてください。'
  if (error.name === 'ChunkLoadError') {
    // エラーメッセージを表示
    console.error('Chunk load error detected.[JavaScript] Reloading the page.', error)
    alert(alertErrorMsg)

    window.location.reload()
  } else if (error.code === 'CSS_CHUNK_LOAD_FAILED') {
    console.error('Chunk load error detected.[CSS] Reloading the page.', error)
    alert(alertErrorMsg)

    window.location.reload()
  } else {
    // その他のエラーはコンソールに出力
    console.error('Router error:', error)
  }
})

export default router
