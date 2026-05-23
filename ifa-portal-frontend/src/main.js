import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

// import '/styles/element-variables.scss'
import jaLang from 'element-plus/lib/locale/lang/ja'

import '@/styles/index.scss' // global css
import '@/styles/ifa-portal-common.scss' // global css

import App from './App.vue'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control
import SvgIcon from '@/components/SvgIcon'// svg component

import IfaButton from '@/components/Button/IfaButton'
import IfaFileSelect from '@/components/Button/IfaFileSelect.vue'
import IfaInputRate from '@/components/Input/IfaInputRate'
import IfaInputPrice from '@/components/Input/IfaInputPrice'
import IfaInputAmount from '@/components/Input/IfaInputAmount'
import IfaInputQuantity from '@/components/Input/IfaInputQuantity'
import IfaInputText from '@/components/Input/IfaInputText'
import IfaText from '@/components/Input/IfaText'
import IfaDatePicker from '@/components/Date/IfaDatePicker.vue'
import IfaDateRangePicker from '@/components/Date/IfaDateRangePicker.vue'
import IfaMonthPicker from '@/components/Date/IfaMonthPicker.vue'
import IfaDateTimePicker from '@/components/Date/IfaDateTimePicker'
import IfaMonthRangePicker from '@/components/Date/IfaMonthRangePicker'
import IfaPeriodSelector from '@/components/Date/IfaPeriodSelector'
import IfaExternalTextLink from '@/components/Link/IfaExternalTextLink'
import IfaLink from '@/components/Link/IfaLink'
import IfaBalloonIcon from '@/components/icon/IfaBalloonIcon'
import IfaHelpIcon from '@/components/icon/IfaHelpIcon'
import IfaUtils from '@/utils/ifaUtils'
import Logger from '@/utils/ifaLog'
import IfaInputSelect from '@/components/Input/IfaInputSelect'
import IfaInputMultiSelect from '@/components/Input/IfaInputMultiSelect'
import IfaInputRadio from '@/components/Input/IfaInputRadio'
import IfaInputCheck from '@/components/Input/IfaInputCheck'
import IfaRouter from '@/components/Router/ifaRouter'
import IfaMenu from '@/components/Router/IfaMenu.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'
// import IfaInputBase from '@/components/Input/IfaInputBase'

import { getCodeList, getCodeValue } from '@/components/Input/js/IfaCodeListFunction.js'
import { req } from '@/utils/requestHelper.js'
import { techTouch } from '@/utils/techTouch.js'
import { getFormattedDateValue, getFormattedTimeValue, getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getFormattedMonthValue } from '@/components/Date/js/IfaMonthPickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'

window.$ = window.jQuery = require('jquery')

const app = createApp(App)

// register globally
app.component('SvgIcon', SvgIcon)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

// mixin でグローバル変数をラッピングする
app.mixin({
  methods: {
    // 各コンポーネント内の<script>タグ内でグローバル変数を参照するための関数
    $_addComma(value) {
      return (IfaUtils.addComma(value))
    },
    $_nullSorting(val1, val2) {
      return (IfaUtils.nullSorting(val1, val2))
    },
    $_copyModel(val1, val2) {
      return (IfaUtils.copyModel(val1, val2))
    },
    $_out(value) {
      return value || '-'
    },
    $_format(row, col, value, index) {
      return value || '-'
    },
    $_startShowMenu(menuId, params) {
      return (IfaRouter.start(this, menuId, params))
    },
    $_isRooting() {
      return (IfaRouter.isRooting())
    },
    $_getMenuParams() {
      return (IfaRouter.getParams(this))
    },
    $_finishShowMenu(info) {
      return (IfaRouter.commitPageInfo(this, info))
    },
    $_techTouch(param) {
      return techTouch(param)
    },
    $_getCodeList(codeListId, dispPattern, selectPattern, isSingleCheckbox = false) {
      return getCodeList(codeListId, dispPattern, selectPattern, isSingleCheckbox)
    },
    $_request(actionId, data, index = 0, actionType = '', header = 'application/json; charset=utf-8') {
      return req(actionId, data, index, actionType, header)
    },
    $_getCodeValue(codeListId, dispPattern, codeKey) {
      return getCodeValue(codeListId, dispPattern, codeKey)
    },
    $_getFormattedDateValue(value, domainFormat) {
      return getFormattedDateValue(value, domainFormat)
    },
    $_getFormattedTimeValue(value, domainFormat) {
      return getFormattedTimeValue(value, domainFormat)
    },
    $_getFormattedDateTimeValue(value, domainFormat, timeZone) {
      return getFormattedDateTimeValue(value, domainFormat, timeZone)
    },
    $_getFormattedMonthValue(value, domainFormat) {
      return getFormattedMonthValue(value, domainFormat)
    },
    $_withCommaZeroPadding(value, digitSpec) {
      return ifaFormatUtils.withCommaZeroPadding(value, digitSpec)
    },
    $_withCommaNoneZeroPadding(value) {
      return ifaFormatUtils.withCommaNoneZeroPadding(value)
    },
    $_noneWithCommaZeroPadding(value, digitSpec) {
      return ifaFormatUtils.noneWithCommaZeroPadding(value, digitSpec)
    },
    $_noneWithCommaNoneZeroPadding(value) {
      return ifaFormatUtils.noneWithCommaNoneZeroPadding(value)
    },
    $_signedWithCommaZeroPadding(value, digitSpec) {
      return ifaFormatUtils.signedWithCommaZeroPadding(value, digitSpec)
    },
    $_signedWithCommaNoneZeroPadding(value) {
      return ifaFormatUtils.signedWithCommaNoneZeroPadding(value)
    },
    $_withCommaInteger(value) {
      return ifaFormatUtils.withCommaInteger(value)
    },
    $_signedWithCommaInteger(value) {
      return ifaFormatUtils.signedWithCommaInteger(value)
    },
    $_zeroPadding(value, precision) {
      return ifaFormatUtils.zeroPadding(value, precision)
    },
    $_logDebug(...message) {
      return Logger.debug(message)
    },
    $_logInfo(...message) {
      return Logger.info(message)
    },
    $_logWarn(...message) {
      return Logger.warn(message)
    },
    $_logError(...message) {
      return Logger.error(message)
    }
  }
})

app.use(ElementPlus, {
  locale: jaLang
})

// 「顧客別メニュー」ボタン押下時のデフォルト遷移画面ID（保有商品一覧タブ）
app.config.globalProperties.$customerMenuInitialScreenId = 'SUB0202_010201' 
// 「顧客別メニュー」ボタン押下時のアクセス権限チェック用画面ID（保有商品一覧）
app.config.globalProperties.$customerMenuAccessCheckScreenId  = 'SUB0202_010201-01' 

app.config.productionTip = false
app.component('IfaButton', IfaButton)
app.component('IfaFileSelect', IfaFileSelect)
app.component('IfaInputRate', IfaInputRate)
app.component('IfaInputPrice', IfaInputPrice)
app.component('IfaInputAmount', IfaInputAmount)
app.component('IfaInputQuantity', IfaInputQuantity)
app.component('IfaInputText', IfaInputText)
app.component('IfaText', IfaText)
app.component('IfaDatePicker', IfaDatePicker)
app.component('IfaDateRangePicker', IfaDateRangePicker)
app.component('IfaMonthPicker', IfaMonthPicker)
app.component('IfaDateTimePicker', IfaDateTimePicker)
app.component('IfaMonthRangePicker', IfaMonthRangePicker)
app.component('IfaPeriodSelector', IfaPeriodSelector)
app.component('IfaExternalTextLink', IfaExternalTextLink)
app.component('IfaLink', IfaLink)
app.component('IfaBalloonIcon', IfaBalloonIcon)
app.component('IfaHelpIcon', IfaHelpIcon)
app.component('IfaInputSelect', IfaInputSelect)
app.component('IfaInputMultiSelect', IfaInputMultiSelect)
app.component('IfaInputRadio', IfaInputRadio)
app.component('IfaInputCheck', IfaInputCheck)
app.component('IfaMenu', IfaMenu)
app.component('IfaRequester', IfaRequester)

app.use(store)
app.use(router)
app.mount('#app')

export default app
