<template>
  <el-card
    class="box-card _height"
    :style="(title==='国内債券・外国債券' || title==='入出金・入出庫')? 'height: 134px;': ''"
    style="position: relative;"
  >
    <el-row class=" _title">{{ title }}</el-row>
    <el-row class=" _title-date">発生日</el-row>
    <table
      v-if="title==='国内担保不足・信用期日アラート'"
      class="_table__body "
    >
      <tbody>
        <tr>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '1')"
            class="_table__header _left _cell"
          >赤残
          </th>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '1')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA002"
              >赤残
              </span>
            </a>
          </th>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '1')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null) }}
          </td>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '1')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(domesticSecurityDeficientMarginDueDateAlertList.filter((element) => element.alertId === '1')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '2')"
            class="_table__header _left _cell"
          >最低保証金30万割れ
          </th>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '2')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA003"
              >最低保証金30万割れ
              </span>
            </a>
          </th>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '2')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null) }}
          </td>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '2')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(domesticSecurityDeficientMarginDueDateAlertList.filter((element) => element.alertId === '2')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '3')"
            class="_table__header _left _cell"
          >維持率31%未満
          </th>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '3')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA003"
              >維持率31%未満
              </span>
            </a>
          </th>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '3')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '3')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(domesticSecurityDeficientMarginDueDateAlertList.filter((element) => element.alertId === '3')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '4')"
            class="_table__header _left _cell"
          >維持率25%未満
          </th>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '4')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA003"
              >維持率25%未満
              </span>
            </a>
          </th>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '4')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null) }}
          </td>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '4')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(domesticSecurityDeficientMarginDueDateAlertList.filter((element) => element.alertId === '4')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '5')"
            class="_table__header _left _cell"
          >追加保証金発生
          </th>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '5')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA003"
              >追加保証金発生
              </span>
            </a>
          </th>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '5')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '5')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(domesticSecurityDeficientMarginDueDateAlertList.filter((element) => element.alertId === '5')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '6')"
            class="_table__header _left _cell"
          >信用決済期日
          </th>
          <th
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '6')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA004"
              >信用決済期日
              </span>
            </a>
          </th>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.every((element) => element.alertId !== '6')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="domesticSecurityDeficientMarginDueDateAlertList.some((element) => element.alertId === '6')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(domesticSecurityDeficientMarginDueDateAlertList.filter((element) => element.alertId === '6')[0].alertDate,'date8')) }}
          </td>
        </tr>
      </tbody>
    </table>
    <table
      v-if="title==='外国担保不足・信用期日アラート'"
      class="_table__body "
    >
      <tbody>
        <tr>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '7')"
            class="_table__header _left _cell"
          >外貨預り金赤残
          </th>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '7')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA005"
              >外貨預り金赤残
              </span>
            </a>
          </th>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '7')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '7')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(ForeignSecurityDeficientMargindueDateAlertList.filter((element) => element.alertId === '7')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '8')"
            class="_table__header _left _cell"
          >米株信用最低保証金2,500ドル割れ
          </th>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '8')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA006"
              >米株信用最低保証金2,500ドル割れ
              </span>
            </a>
          </th>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '8')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '8')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(ForeignSecurityDeficientMargindueDateAlertList.filter((element) => element.alertId === '8')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '9')"
            class="_table__header _left _cell"
          >米株信用維持率51%未満
          </th>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '9')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA006"
              >米株信用維持率51%未満
              </span>
            </a>
          </th>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '9')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '9')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(ForeignSecurityDeficientMargindueDateAlertList.filter((element) => element.alertId === '9')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '10')"
            class="_table__header _left _cell"
          >米株信用維持率35%未満
          </th>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '10')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA006"
              >米株信用維持率35%未満
              </span>
            </a>
          </th>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '10')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '10')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(ForeignSecurityDeficientMargindueDateAlertList.filter((element) => element.alertId === '10')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '11')"
            class="_table__header _left _cell"
          >米株信用追加保証金発生
          </th>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '11')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA006"
              >米株信用追加保証金発生
              </span>
            </a>
          </th>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '11')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '11')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(ForeignSecurityDeficientMargindueDateAlertList.filter((element) => element.alertId === '11')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '12')"
            class="_table__header _left _cell"
          >米株信用決済期日
          </th>
          <th
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '12')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA007"
              >米株信用決済期日
              </span>
            </a>
          </th>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.every((element) => element.alertId !== '12')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="ForeignSecurityDeficientMargindueDateAlertList.some((element) => element.alertId === '12')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(ForeignSecurityDeficientMargindueDateAlertList.filter((element) => element.alertId === '12')[0].alertDate,'date8')) }}
          </td>
        </tr>
      </tbody>
    </table>
    <table
      v-if="title==='投信'"
      class="_table__body "
    >
      <tbody>
        <tr>
          <th
            v-if="mutualFundList.every((element) => element.alertId !== '13')"
            class="_table__header _left _cell"
          >投信基準価額変動
          </th>
          <th
            v-if="mutualFundList.some((element) => element.alertId === '13')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA008(mutualFundList.filter((element) => element.alertId === '13')[0].alertDate,'date8')"
              >投信基準価額変動
              </span>
            </a>
          </th>
          <td
            v-if="mutualFundList.every((element) => element.alertId !== '13')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="mutualFundList.some((element) => element.alertId === '13')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(mutualFundList.filter((element) => element.alertId === '13')[0].alertDate,'date8')) }}
          </td>
        </tr>
      </tbody>
    </table>
    <table
      v-if="title==='国内債券・外国債券'"
      class="_table__body "
    >
      <tbody>
        <tr>
          <th
            v-if="DomesticClaimForeignClaimList.every((element) => element.alertId !== '14')"
            class="_table__header _left _cell"
          >ノックイン
          </th>
          <th
            v-if="DomesticClaimForeignClaimList.some((element) => element.alertId === '14')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA009"
              >ノックイン
              </span>
            </a>
          </th>
          <td
            v-if="DomesticClaimForeignClaimList.every((element) => element.alertId !== '14')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="DomesticClaimForeignClaimList.some((element) => element.alertId === '14')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(DomesticClaimForeignClaimList.filter((element) => element.alertId === '14')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="DomesticClaimForeignClaimList.every((element) => element.alertId !== '15')"
            class="_table__header _left _cell"
          >ノックアウト
          </th>
          <th
            v-if="DomesticClaimForeignClaimList.some((element) => element.alertId === '15')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA010"
              >ノックアウト
              </span>
            </a>
          </th>
          <td
            v-if="DomesticClaimForeignClaimList.every((element) => element.alertId !== '15')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="DomesticClaimForeignClaimList.some((element) => element.alertId === '15')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(DomesticClaimForeignClaimList.filter((element) => element.alertId === '15')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="DomesticClaimForeignClaimList.every((element) => element.alertId !== '16')"
            class="_table__header _left _cell"
          >国内債券の満期償還
          </th>
          <th
            v-if="DomesticClaimForeignClaimList.some((element) => element.alertId === '16')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="clickA011(DomesticClaimForeignClaimList.filter((element) => element.alertId === '16')[0].alertDate)"
              >国内債券の満期償還
              </span>
            </a>
          </th>
          <td
            v-if="DomesticClaimForeignClaimList.every((element) => element.alertId !== '16')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            {{ $_out(null ) }}
          </td>
          <td
            v-if="DomesticClaimForeignClaimList.some((element) => element.alertId === '16')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(DomesticClaimForeignClaimList.filter((element) => element.alertId === '16')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="DomesticClaimForeignClaimList.every((element) => element.alertId !== '17')"
            class="_table__header _left _cell"
          >外国債券の満期償還
          </th>
          <th
            v-if="DomesticClaimForeignClaimList.some((element) => element.alertId === '17')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="clickA011(DomesticClaimForeignClaimList.filter((element) => element.alertId === '17')[0].alertDate)"
              >外国債券の満期償還
              </span>
            </a>
          </th>
          <td
            v-if="DomesticClaimForeignClaimList.every((element) => element.alertId !== '17')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            -
          </td>
          <td
            v-if="DomesticClaimForeignClaimList.some((element) => element.alertId === '17')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(DomesticClaimForeignClaimList.filter((element) => element.alertId === '17')[0].alertDate,'date8')) }}
          </td>
        </tr>
      </tbody>
    </table>
    <table
      v-if="title==='入出金・入出庫'"
      class="_table__body "
    >
      <tbody>
        <tr>
          <th
            v-if="DepositWithDrawDeliverInOutList.every((element) => element.alertId !== '18')"
            class="_table__header _left _cell"
          >入出金
          </th>
          <th
            v-if="DepositWithDrawDeliverInOutList.some((element) => element.alertId === '18')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA012"
              >入出金
              </span>
            </a>
          </th>
          <td
            v-if="DepositWithDrawDeliverInOutList.every((element) => element.alertId !== '18')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            -
          </td>
          <td
            v-if="DepositWithDrawDeliverInOutList.some((element) => element.alertId === '18')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >
            {{ $_out($_getFormattedDateValue(DepositWithDrawDeliverInOutList.filter((element) => element.alertId === '18')[0].alertDate,'date8')) }}
          </td>
        </tr>
        <tr>
          <th
            v-if="DepositWithDrawDeliverInOutList.every((element) => element.alertId !== '19')"
            class="_table__header _left _cell"
          >入出庫
          </th>
          <th
            v-if="DepositWithDrawDeliverInOutList.some((element) => element.alertId === '19')"
            class="_table__data _left _bold _cell"
          >
            <a>
              <span
                class="_link"
                @click="transitionA013"
              >入出庫
              </span>
            </a>
          </th>
          <td
            v-if="DepositWithDrawDeliverInOutList.every((element) => element.alertId !== '19')"
            class="_table__header _left _cell"
            style="width: 20%;"
          >
            -
          </td>
          <td
            v-if="DepositWithDrawDeliverInOutList.some((element) => element.alertId === '19')"
            class="_table__data _left _bold _cell"
            style="width: 20%;"
          >{{ $_out($_getFormattedDateValue(DepositWithDrawDeliverInOutList.filter((element) => element.alertId === '19')[0].alertDate,'date8')) }}
          </td>
        </tr>
      </tbody>
    </table>
  </el-card>
</template>

<script>
export default {
  props: {
    title: {
      required: true,
      type: String
    },
    customerAlertList: {
      required: true,
      type: Object
    }
  },
  emits: ['transition-a011'],
  data() {
    return {
      sortCustomerAlertList: [],
      domesticSecurityDeficientMarginDueDateAlertList: [],
      ForeignSecurityDeficientMargindueDateAlertList: [],
      mutualFundList: [],
      DomesticClaimForeignClaimList: [],
      DepositWithDrawDeliverInOutList: []
    }
  },
  computed: {
    previousBusinessDay() {
      return this.$store.getters.previousBusinessDay
    }
  },
  watch: {
    customerAlertList: {
      handler: function() {
        this.sortCustomerAlertList = this.customerAlertList.filter(list => Number(list.alertId))
        this.sortCustomerAlertList.sort(function(a, b) {
          if (Number(a.alertId) > Number(b.alertId)) return 1
          if (Number(a.alertId) < Number(b.alertId)) return -1
          return 0
        })
        this.domesticSecurityDeficientMarginDueDateAlertList = this.sortCustomerAlertList.filter(list => Number(list.alertId) >= 1 && Number(list.alertId) <= 6).length === 0 ? [{ alertId: '', alertDate: '' }] : this.sortCustomerAlertList.filter(list => Number(list.alertId) >= 1 && Number(list.alertId) <= 6)

        this.ForeignSecurityDeficientMargindueDateAlertList = this.sortCustomerAlertList.filter(list => Number(list.alertId) >= 7 && Number(list.alertId) <= 12).length === 0 ? [{ alertId: '', alertDate: '' }] : this.sortCustomerAlertList.filter(list => Number(list.alertId) >= 7 && Number(list.alertId) <= 12)

        this.mutualFundList = this.sortCustomerAlertList.filter(list => Number(list.alertId) === 13).length === 0 ? [{ alertId: '', alertDate: '' }] : this.sortCustomerAlertList.filter(list => Number(list.alertId) === 13)

        this.DomesticClaimForeignClaimList = this.sortCustomerAlertList.filter(list => Number(list.alertId) >= 14 && Number(list.alertId) <= 17).length === 0 ? [{ alertId: '', alertDate: '' }] : this.sortCustomerAlertList.filter(list => Number(list.alertId) >= 14 && Number(list.alertId) <= 17)

        this.DepositWithDrawDeliverInOutList = this.sortCustomerAlertList.filter(list => Number(list.alertId) >= 18 && Number(list.alertId) <= 19).length === 0 ? [{ alertId: '', alertDate: '' }] : this.sortCustomerAlertList.filter(list => Number(list.alertId) >= 18 && Number(list.alertId) <= 19)
        return
      },
      deep: true
    }
  },
  methods: {
    transitionA002() {
      // 円貨未入金・赤残アラート一覧に遷移
      this.$_startShowMenu('SUB020301_01-01')
    },
    transitionA003() {
      // 国内信用担保不足アラート一覧に遷移
      this.$_startShowMenu('SUB020301_01-02')
    },
    transitionA004() {
      // 国内信用建玉期日アラート一覧に遷移
      this.$_startShowMenu('SUB020301_02-01')
    },
    transitionA005() {
      // 外貨未入金・赤残アラート一覧に遷移
      this.$_startShowMenu('SUB020301_01-03')
    },
    transitionA006() {
      // 米株信用担保不足アラート一覧に遷移
      this.$_startShowMenu('SUB020301_01-04')
    },
    transitionA007() {
      // 米株信用建玉期日アラート一覧に遷移
      this.$_startShowMenu('SUB020301_02-02')
    },
    transitionA008(alertDate) {
      // 期間指定
      const dateRange = [this.$_getFormattedDateValue(alertDate, 'date8'), this.$_getFormattedDateValue(this.previousBusinessDay, 'date8')]
      // 投信基準価額変動の銘柄保有一覧に遷移
      this.$_startShowMenu('SUB020301_03-01', dateRange)
    },
    transitionA009() {
      // ノックイン銘柄保有一覧に遷移
      this.$_startShowMenu('SUB020301_03-02')
    },
    transitionA010() {
      // ノックアウト銘柄保有一覧に遷移
      this.$_startShowMenu('SUB020301_03-03')
    },
    clickA011(alertDate) {
      // A011リクエスト
      this.$emit('transition-a011', alertDate)
    },
    transitionA012() {
      // 入出金明細に遷移
      this.$_startShowMenu('SUB020302_0203')
    },
    transitionA013() {
      // 入出庫明細に遷移
      this.$_startShowMenu('SUB020302_0204')
    }
  }
}
</script>
<style lang="scss"  scoped>
@import "@/styles/variables.scss";
.notice-unchecked {
        color:red;
}
._height{
  height: 184px;
}
._left {
  width: 80%;
  text-align: left;
}
._title{
  text-align: left;
  font-size: 15px;
  font-weight: bold;
  color: #18181a;
  padding: 2px 14px 2px 14px;
  position: relative;
}
._title-date{
  display: block;
  text-align: right;
  font-size: 13px;
  font-weight: bold;
  color: #18181a;
  padding: 2px 24px 2px 14px;
  position: absolute;
  top: 8px;
}
._cell{
  height: 25px;
}
._link{
  color:#409EFF;
  text-decoration: underline;
  text-underline-offset:0.2em;
  cursor: pointer;
}
._noborder {
  border: 0px none;
}
.box-card {
  margin: 10px 10px 0 10px;
}
</style>
