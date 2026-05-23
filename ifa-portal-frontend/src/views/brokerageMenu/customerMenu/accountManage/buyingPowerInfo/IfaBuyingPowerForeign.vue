<template>
  <!-- 買付余力(外国) -->
  <div>
    <!--初期表示時のリクエスト-->
    <ifa-requester
      id="IfaBuyingPowerForeignInitializeA001"
      action-id="SUB0202_010303-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA001"
      @response-error-handler="responseErrorHandlerA001"
    ></ifa-requester>

    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <!-- 口座種別選択 -->
      <el-row v-if="hasJrNisaAccount">
        <el-col :span="24">
          <ifa-input-radio
            v-model="form.selectedAccountType"
            code-list-id="ACCOUNT_TYPE"
            :disp-pattern="1"
            :select-pattern="1"
            style="margin-top: 1rem; padding-right: 2.5rem"
            @change="handleChangeAccountType"
          ></ifa-input-radio>
        </el-col>
      </el-row>

      <el-card
        class="box-card"
        style="overflow-x: auto;"
      >
        <el-row style="margin-top: 1rem;">
          <el-col
            :span="21"
          >
            <!-- 国を選択(余力情報が存在する場合) -->
            <el-radio-group
              v-if="hasData"
              v-model="form.selectedCurrency"
              @change="handleChangeCurrency"
            >
              <div
                v-for="(currency, index) in currencyListSelectedAccountType"
                :key="index"
                :class="{ radio_flag: true, radio_flag_no_img: !currency.flagImg }"
                @click="handleChangeCurrency(currency.key)"
              >
                <img :src="currency.flagImg">
                <br>
                <el-radio :label="currency.key">{{ currency.flagName }}</el-radio>
              </div>
            </el-radio-group>

            <!-- 余力情報なし -->
            <div
              v-else
              style="margin: 16px auto 1rem 16px"
            >余力情報がありません。</div>
          </el-col>

          <!-- 更新ボタン -->
          <el-col :span="3">
            <div class="update-button">
              <ifa-button
                id="btnUpdate"
                text="更新"
                color="primary"
                icon="RefreshRight"
                :parent-ref="$refs"
                action-id="SUB0202_010303-01#A002"
                action-type="requestAction"
                @response-handler="responseHandlerA002($event)"
              ></ifa-button>
            </div>
          </el-col>
        </el-row>

        <!--国別の買付余力を表示-->
        <ifa-buying-power-foreign-sub-spot-detail
          v-for="(currency, index) in buyingPowerList"
          :key="index"
          :buying-powers-whole-account="currency.buyingPowersWholeAccount"
          :buying-powers-jr-nisa-account="currency.buyingPowersJrNisaAccount"
          :currency-name="currency.currencyName"
          :currency-unit="currency.key"
          :flag-img="currency.flagImg"
          :show-whole-account="showWholeAccount"
          :show-jr-nisa-account="showJrNisaAccount"
          :has-jr-nisa-account="hasJrNisaAccount"
          :has-margin-account="hasMarginAccount"
        >
        </ifa-buying-power-foreign-sub-spot-detail>
      </el-card>
    </div>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBuyingPowerForeignSubSpotDetail from './components/IfaBuyingPowerForeignSubSpotDetail.vue'
import { IfaBuyingPowerForeignFormModel } from './js/IfaBuyingPowerForeignFormModel.js'
import { IfaBuyingPowerForeignA001RequestModel } from './js/IfaBuyingPowerForeignA001RequestModel.js'
import { IfaBuyingPowerForeignA002RequestModel } from './js/IfaBuyingPowerForeignA002RequestModel.js'
import flagImgUSD from '@/assets/png/flags/32/us.png'
import flagImgHKD from '@/assets/png/flags/32/hk.png'
import flagImgEUR from '@/assets/png/flags/32/eu.png'
import flagImgAUD from '@/assets/png/flags/32/au.png'
import flagImgNZD from '@/assets/png/flags/32/nz.png'
import flagImgCAD from '@/assets/png/flags/32/ca.png'
import flagImgZAR from '@/assets/png/flags/32/za.png'
import flagImgMXN from '@/assets/png/flags/32/mx.png'
import flagImgTRY from '@/assets/png/flags/32/tr.png'
import flagImgSGD from '@/assets/png/flags/32/sg.png'
import flagImgKRW from '@/assets/png/flags/32/kr.png'
import flagImgRUB from '@/assets/png/flags/32/ru.png'
import flagImgVND from '@/assets/png/flags/32/vn.png'
import flagImgIDR from '@/assets/png/flags/32/id.png'
import flagImgTHB from '@/assets/png/flags/32/th.png'
import flagImgMYR from '@/assets/png/flags/32/my.png'
import flagImgCNY from '@/assets/png/flags/32/cn.png'

export default {
  components: {
    screenTitle,
    IfaBuyingPowerForeignSubSpotDetail
  },
  emits: ['initializeError'],
  data() {
    return {
      form: new IfaBuyingPowerForeignFormModel()
    }
  },
  computed: {
    // A001リクエストモデル
    IfaBuyingPowerForeignA001RequestModel() {
      return new IfaBuyingPowerForeignA001RequestModel()
    },

    // A002リクエストモデル
    IfaBuyingPowerForeignA002RequestModel() {
      return new IfaBuyingPowerForeignA002RequestModel()
    },

    // 顧客共通情報
    customerInfo() {
      return this.$store.getters.customerInfo
    },

    // 総合口座を表示するか否か
    showWholeAccount() {
      return (
        !this.form.selectedAccountType == null || // 未設定
        this.form.selectedAccountType === ' ' || // 総合口座
        this.form.selectedAccountType === '2' // 全て
      )
    },

    // ジュニアNISA口座を表示するか否か
    showJrNisaAccount() {
      return (
        this.form.selectedAccountType != null && // 未設定ではない
        (
          this.form.selectedAccountType === '1' || // ジュニアNISA口座
          this.form.selectedAccountType === '2' // 全て
        )
      )
    },

    // ジュニアNISA対象の口座か否か
    hasJrNisaAccount() {
      return this.customerInfo.jrIsaContractType === '1' && this.customerInfo.withdrawalRestrictionCancelFlag === '1'
    },

    // 顧客共通情報.信用口座区分（外国）= 1(信用口座)か否か
    hasMarginAccount() {
      return this.customerInfo.foreignMarginAccountType === '1'
    },

    // データが1件以上あるか否か
    hasData() {
      return this.buyingPowerList.length > 0
    },

    // 通貨ごとのデータ一覧
    currencyList() {
      // 通貨に関する一覧を取得/定義
      const currencyCodeList1 = this.$_getCodeList('CURRENCY_CODE', 2, 1) // 余力の上に表示する国名
      const currencyCodeList2 = this.$_getCodeList('CURRENCY_CODE', 5, 1) // 国旗の下に表示する国名
      const flagImgs = [
        { key: 'USD', img: flagImgUSD },
        { key: 'HKD', img: flagImgHKD },
        { key: 'EUR', img: flagImgEUR },
        { key: 'AUD', img: flagImgAUD },
        { key: 'NZD', img: flagImgNZD },
        { key: 'CAD', img: flagImgCAD },
        { key: 'ZAR', img: flagImgZAR },
        { key: 'MXN', img: flagImgMXN },
        { key: 'TRY', img: flagImgTRY },
        { key: 'SGD', img: flagImgSGD },
        { key: 'KRW', img: flagImgKRW },
        { key: 'RUB', img: flagImgRUB },
        { key: 'VND', img: flagImgVND },
        { key: 'IDR', img: flagImgIDR },
        { key: 'THB', img: flagImgTHB },
        { key: 'MYR', img: flagImgMYR },
        { key: 'CNY', img: flagImgCNY }
      ]

      const currencyList = currencyCodeList1
        .map(currencyCode1 => { // リストを1つにまとめる
          const currencyCode2 = currencyCodeList2.find(val => val.key === currencyCode1.key)
          const flagImg = flagImgs.find(val => val.key === currencyCode1.key)

          const byCountryData = this.form.byCountryList
            ? this.form.byCountryList
              .find(val =>
                (
                  val.buyingPowerWholeAccountList &&
                  val.buyingPowerWholeAccountList[0].currencyCode === currencyCode1.key
                ) ||
                (
                  val.buyingPowerJuniorAccountList &&
                  val.buyingPowerJuniorAccountList[0].currencyCode === currencyCode1.key
                )
              )
            : []

          const buyingPowerTemplate = [
            { settlementDateAfterBusinessDay: '当日' }
          ]
          for (let i = 1; i <= 5; i++) {
            buyingPowerTemplate.push({
              settlementDateAfterBusinessDay: i + '営業日後'
            })
          }

          const buyingPowersWholeAccount = (
            byCountryData && byCountryData.buyingPowerWholeAccountList
              ? buyingPowerTemplate.map(template => {
                const buyingPowerWholeAccount = byCountryData.buyingPowerWholeAccountList.find(data =>
                  data.settlementDateAfterBusinessDay === template.settlementDateAfterBusinessDay
                )

                return { ...template, ...buyingPowerWholeAccount }
              })
              : []
          )

          const buyingPowersJrNisaAccount = (
            this.hasJrNisaAccount && byCountryData && byCountryData.buyingPowerJuniorAccountList
              ? buyingPowerTemplate.map(template => {
                const buyingPowerJrNisaAccount = byCountryData.buyingPowerJuniorAccountList.find(data =>
                  data.settlementDateAfterBusinessDay === template.settlementDateAfterBusinessDay
                )

                return { ...template, ...buyingPowerJrNisaAccount }
              })
              : []
          )

          return {
            key: currencyCode1.key,
            currencyName: currencyCode1.value,
            flagName: currencyCode2.value,
            flagImg: flagImg.img,
            buyingPowersWholeAccount: buyingPowersWholeAccount,
            buyingPowersJrNisaAccount: buyingPowersJrNisaAccount
          }
        })

      return currencyList
    },

    // currencyListのうち、選択された通貨区分のあるデータ一覧
    currencyListSelectedAccountType() {
      const filterdCurrencyList = this.currencyList.filter((currency) => { // 預り金が0の通貨を除外する
        if (
          this.form.selectedAccountType === ' ' ||
          this.hasJrNisaAccount === false
        ) { // 総合口座が選択されているか、ジュニアNISA口座を表示しないアカウント
          return (
            currency.buyingPowersWholeAccount &&
            currency.buyingPowersWholeAccount.some(val => {
              return (
                val.yenDeposit &&
                !Number.isNaN(parseFloat(val.yenDeposit)) &&
                parseFloat(val.yenDeposit) !== 0.0
              )
            })
          )
        } else if (this.form.selectedAccountType === '1') { // ジュニアNISA口座
          return (
            currency.buyingPowersJrNisaAccount &&
            currency.buyingPowersJrNisaAccount.some(val => {
              return (
                val.yenDeposit &&
                !Number.isNaN(parseFloat(val.yenDeposit)) &&
                parseFloat(val.yenDeposit) !== 0.0
              )
            })
          )
        } else { // 口座区分：すべて
          return (
            (
              currency.buyingPowersWholeAccount &&
              currency.buyingPowersWholeAccount.some(val => {
                return (
                  val.yenDeposit &&
                  !Number.isNaN(parseFloat(val.yenDeposit)) &&
                  parseFloat(val.yenDeposit) !== 0.0
                )
              })
            ) || (
              currency.buyingPowersJrNisaAccount &&
              currency.buyingPowersJrNisaAccount.some(val => {
                return (
                  val.yenDeposit &&
                  !Number.isNaN(parseFloat(val.yenDeposit)) &&
                  parseFloat(val.yenDeposit) !== 0.0
                )
              })
            )
          )
        }
      })

      // 通貨一覧に「全て」を追加
      if (filterdCurrencyList.length > 0) {
        filterdCurrencyList.push({
          'key': 'ALL', flagName: '全て', img: ''
        })
      }

      return filterdCurrencyList
    },

    // currencyListSelectedAccountTypeのうち、選択された通貨のみのデータ
    buyingPowerList() {
      const buyingPowerList = this.currencyListSelectedAccountType.filter(currency => {
        const isSelected = this.form.selectedCurrency && (
          (
            this.form.selectedCurrency === currency.key ||
            this.form.selectedCurrency === 'ALL'
          ) && currency.key !== 'ALL'
        )
        return isSelected
      })

      return buyingPowerList
    }
  },
  methods: {
    onShow() {
      // フォームの初期化
      this.form = new IfaBuyingPowerForeignFormModel()

      // 初期化情報取得
      this.$nextTick(() => {
        document.getElementById('IfaBuyingPowerForeignInitializeA001').click()
      })
    },

    userInfo() {
      return this.$store.getters.customerInfo
    },

    handleChangeCurrency(selected) {
      this.form.selectedCurrency = selected
    },

    handleChangeAccountType() {
      this.form.selectedCurrency = 'ALL'
    },

    responseHandlerA001(response) {
      this.form = Object.assign(this.form, response.dataList[0])
    },

    responseErrorHandlerA001(error) {
      const errorInfo = {
        title: this.form.title.name,
        message: error.message
      }
      this.$emit('initializeError', errorInfo)
    },

    // 更新ボタン
    responseHandlerA002(response) {
      this.form = Object.assign(this.form, response.dataList[0])
    }
  }
}
</script>

<style lang="scss" scoped="">
  @import '@/styles/orderStatusList.scss';
  @import "@/styles/table.scss";
  :deep(.el-radio-group) {
    width: 100%;
  }
  .caption_card {
    padding: 5px 15px 20px 15px;
  }
  .radio_flag {
    text-align: center;
    width: 130px;
  }
  .radio_flag_no_img {
    padding-top: 32px;
  }
.box-card {
  margin: 10px 10px 15px 10px;
}
</style>
