<template>
  <!-- 投信詳細情報ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    class="custom_dialog"
    title="投信詳細情報"
    width="850px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    :append-to-body="appendToBody"
  >

    <!-- 閉じるボタン -->
    <el-row>
      <el-col style="text-align: right;">
        <ifa-button
          class="form-button__wrapper"
          text="閉じる"
          style="padding-right: 0;"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- 銘柄情報 -->
    <el-row>
      <el-card
        class="box-card"
        style="background-color: #eee; margin: 0.5rem 0;"
      >
        <el-row
          class="_bold_black_l"
          style="font-size: 20px;"
        >
          <el-col>
            <span>{{ $_out(info.fundOfficalName) }}</span>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
    <!-- 更新日時 -->
    <el-row>
      <el-col
        style="text-align: right;"
      >
        <span>更新日時：{{ $_out(formatDateTime12(info.updateTime)) }}</span>
      </el-col>
    </el-row>

    <!-- 基準価額/純資産 -->
    <el-row style="padding-top: 0.2rem;">
      <el-col>
        <table class="_table__body">
          <tbody>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >基準価額<br>
                <span>
                  （ {{ isNumber(info.price) && isDate(info.priceDate) ? formatDate(info.priceDate) + '現在）' : '----/--/--）' }}
                </span>
              </th>
              <td class="_table__data __left">
                <span>
                  {{ isNumber(info.price) ? ifaFormatUtils.withCommaInteger(info.price) : '-' }}
                </span>
                <span>
                  /{{ isNumber(info.price) && isNumber(info.basePriceUnit) ? ifaFormatUtils.withCommaInteger(info.basePriceUnit) : '-' }}
                </span>
                <span style="padding-left: 1rem; padding-right: 1rem;">
                  <span v-if="isNumber(info.price) && isNumber(info.diff)"
                        :class="fontColorClass(info.diff)"
                  >{{ ifaFormatUtils.signedWithCommaInteger(info.diff) }}
                  </span>
                  <span v-else>-</span>
                  <span v-if="isNumber(info.price) && isNumber(info.ratio)"
                        :class="fontColorClass(info.ratio)"
                  >（ {{ ifaFormatUtils.signedWithCommaZeroPadding(info.ratio, 2) }}%）</span>
                  <span v-else>（-）</span>
                </span>
              </td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >純資産</th>
              <td class="_table__data __left">
                <span>
                  {{ isNumber(info.junshisan) ? ifaFormatUtils.withCommaInteger(info.junshisan) : '-' }}
                </span>
              </td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

    <!-- 52週高値/安値 -->
    <el-row style="padding-top: 1rem;">
      <el-col>
        <table class="_table__body">
          <tbody>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >52週高値</th>
              <td class="_table__data __left">
                <span>
                  {{ isNumber(info.w52Takane) ? ifaFormatUtils.withCommaInteger(info.w52Takane) + '円' : '-' }}
                </span>
                <span>
                  （ {{ isNumber(info.w52Takane) && isDate(info.w52Takanedate) ? formatDate(info.w52Takanedate) : '----/--/--' }}）
                </span>
              </td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >52週安値</th>
              <td class="_table__data __left">
                <span>
                  {{ isNumber(info.w52Yasune) ? ifaFormatUtils.withCommaInteger(info.w52Yasune) + '円' : '-' }}
                </span>
                <span>
                  （ {{ isNumber(info.w52Yasune) && isDate(info.w52Yasunedate) ? formatDate(info.w52Yasunedate) : '----/--/--' }}）
                </span>
              </td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

    <!-- 休場日 -->
    <el-row style="padding-top: 1rem;">
      <el-col>
        <table class="_table__body _inner_table__bottom _inner_table__shadow">
          <tbody>
            <tr>
              <th
                class="_table__header __left _inner_table__bottom"
                style="padding-top: 5px"
              >休場日</th>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <table class="_table__body">
          <tbody>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >当月</th>
              <td class="_table__data __left">
                <span v-if="info.thisMonthClosedDayList.length">
                  <span v-for="(item, index) in info.thisMonthClosedDayList"
                        :key="item.closedDay"
                  ><span :class="{ '__red __bold': item.businessDayFlag === '1' }">{{ formatDate(item.closedDay).toString().substring(5) }}</span>
                    <span v-if="index < info.thisMonthClosedDayList.length - 1">、 </span>
                  </span>
                </span>
                <span v-else>-</span>
              </td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >翌月</th>
              <td class="_table__data __left">
                <span v-if="info.nextMonthClosedDayList.length">
                  <span v-for="(item, index) in info.nextMonthClosedDayList"
                        :key="item.closedDay"
                  ><span :class="{ '__red __bold': item.businessDayFlag === '1' }">{{ formatDate(item.closedDay).toString().substring(5) }}</span>
                    <span v-if="index < info.nextMonthClosedDayList.length - 1">、 </span>
                  </span>
                </span>
                <span v-else>-</span>
              </td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

    <el-row style="padding-top: 1rem;">
      <el-col>
        <table class="_table__body">
          <tbody>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >締切日時</th>
              <td class="_table__data __left">
                <div>{{ info.deadlineDateRecent ? formatDate(info.deadlineDateRecent) + ' ' + formatTime4(info.deadlines) : '-' }}</div>
                <div>{{ info.deadlineDateRecent && info.deadlineDateNext ? '※' + formatDate(info.deadlineDateRecent) + ' ' + formatTime4(info.deadlines) + '以降は、' + formatDate(info.deadlineDateNext) + ' ' + formatTime4(info.deadlines) + 'となります。' : '' }}</div>
              </td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

    <el-row
      v-show="info.shrineNotificationContents"
      style="padding-top: 1rem;"
    >
      <el-col>
        <table class="_table__body">
          <tbody>
            <tr>
              <th
                class="_table__header __left"
              >当社からのお知らせ（{{ $_out(formatDate6(info.shrineNotificationUpdateDate)) }}更新）</th>
            </tr>
            <tr>
              <td class="_table__data __left link_contents"
                  v-html="info.shrineNotificationContents"
              >
              </td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

    <el-row
      v-show="info.outsourcingCompanyNotificationContents"
      style="padding-top: 1rem;"
    >
      <el-col>
        <table class="_table__body">
          <tbody>
            <tr>
              <th
                class="_table__header __left"
              >委託会社からのお知らせ（{{ $_out(formatDate6(info.outsourcingCompanyNotificationUpdateDate)) }}更新）</th>
            </tr>
            <tr>
              <td class="_table__data __left link_contents"
                  v-html="info.outsourcingCompanyNotificationContents"
              >
              </td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

    <el-row
      v-show="info.wealthAdvisorCommentContents"
      style="padding-top: 1rem;"
    >
      <el-col>
        <table class="_table__body">
          <tbody>
            <tr>
              <th
                class="_table__header __left"
              >ウエルスアドバイザー社のコメント（{{ $_out(formatDate6(info.wealthAdvisorCommentUpdateDate)) }}更新）</th>
            </tr>
            <tr>
              <td class="_table__data __left link_contents"
                  v-html="info.wealthAdvisorCommentContents"
              >
              </td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

    <el-row style="padding-top: 1rem;">
      <el-col>
        <table class="_table__body">
          <tbody>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >取引コース</th>
              <td
                class="_table__data __left"
                style="width: 25%;"
              >{{ $_out(info.course) }}</td>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >協会コード</th>
              <td class="_table__data __left">{{ $_out(info.kyoukaiCd) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >定期売却</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.fundTypeName) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >運用方針</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.operationPolicy) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >買付単位</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.buyUnitWord) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >売却単位</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.sellSharesWord) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >当初一口当り元本</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(ifaFormatUtils.withCommaNoneZeroPadding(info.individualPrincipal)) }}</td>
            </tr>
            <td
              colspan="5"
              style="padding: 0px"
            >
              <table
                class="_table__body _inner_table__shadow"
                style="padding: 0px; border: none;"
              >
                <tbody>
                  <tr>
                    <th
                      class="_table__header __left _inner_table__top _inner_table__left"
                      style="width: 25%; padding-top: 4px"
                    >買付手数料 (税込)</th>
                    <th
                      class="_table__header __left _inner_table__top"
                      style="width: 37.5%; padding-top: 4px"
                    >インターネットコース</th>
                    <th
                      class="_table__header __left _inner_table__top _inner_table__right"
                      style="width: 37.5%; padding-top: 4px"
                    >ダイレクトコース／対面コース／<br>IFAコース</th>
                  </tr>
                  <tr>
                    <td
                      class="_table__header __left _inner_table__bottom _inner_table__left"
                      style="width: 25%;"
                    ></td>
                    <td
                      class="_table__data __left _inner_table__bottom"
                      style="width: 37.5%;"
                    >
                      <div>{{ $_out(info.buyCommLeft) }}</div>
                      <div style="padding-top: 1rem;"></div>
                      <div>{{ info.buyCommNisaLeft ? info.buyCommNisaLeft : '' }}</div>
                    </td>
                    <td
                      class="_table__data __left _inner_table__bottom _inner_table__right"
                      style="width: 37.5%;"
                    >
                      <div>{{ $_out(info.buyCommRight) }}</div>
                      <div style="padding-top: 1rem;">{{ info.buyCommNisaRight ? info.buyCommNisaRight : '' }}</div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </td>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >信託報酬 (税込)/年</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ info.trustFeeAmount ? ifaFormatUtils.withCommaNoneZeroPadding(info.trustFeeAmount) : '-' }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >信託財産留保額</th>
              <td
                class="_table__data __left"
                colspan="3"
              > {{ $_out(info.partialRedemptionCharge) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >解約手数料 (税込)</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.feeContent) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >償還優遇の適用</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.preferentialRedemptionApplication) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >当社締切時間</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.shrineDeadlines) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >約定日</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.tradeDate) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >受渡日</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.settlementDate) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >決算日</th>
              <td
                class="_table__data __left"
                style="width: 25%;"
              >{{ $_out(formatDate4(info.settleLastDay)) }}</td>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >分配金</th>
              <td class="_table__data __left">{{ $_out(info.dividendHandling) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >分配金受取方法</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.distributionReceiveMethodWord) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >設定日</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.settingDate) }}</td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
                style="width: 25%;"
              >償還日</th>
              <td
                class="_table__data __left"
                colspan="3"
              >{{ $_out(info.redemptionDate) }}</td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { getFormattedDateValue, getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
export default {
  props: {
    isVisible: { type: Boolean, required: true },
    info: { type: Object, required: true },
    appendToBody: { type: Boolean, required: false, default: false }
  },
  emits: ['close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      ifaFormatUtils: ifaFormatUtils
    }
  },
  methods: {
    // 閉じるボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 数値チェック
    isNumber(val) {
      return val !== null && !isNaN(val) && /^[+-]?[\d.]+$/.test(val)
    },
    // 日付有効チェック
    isDate(value) {
      const pattern = /^\d{8}$/
      if (value && pattern.test(value)) {
        // 年、月、日に分割
        const year = value.substring(0, 4)
        const month = value.substring(4, 6) - 1
        const day = value.substring(6)
        // Date オブジェクトを作成して、入力された年月日と一致するかチェック
        const date = new Date(year, month, day)
        return (date.getFullYear() === parseInt(year) &&
            date.getMonth() === parseInt(month) &&
            date.getDate() === parseInt(day))
      }
    },
    // 日付フォーマット編集
    // YYYYMMDD → YYYY/MM/DD
    formatDate(val) {
      return getFormattedDateValue(val)
    },
    // YYYYMMDD → YY/MM/DD
    formatDate6(val) {
      return getFormattedDateValue(val, 'date6')
    },
    // YYYYMMDDHHMMSS → YYYY/MM/DD HH:MM:SS
    formatDateTime12(val) {
      return getFormattedDateTimeValue(val, 'datetime12')
    },
    // HHMM → HH:MM
    formatTime4(val) {
      const pattern = /^\d{4}$/
      if (val && pattern.test(val)) {
        return val.substring(0, 2) + ':' + val.substring(2)
      }
      return val
    },
    // MMDD → MM/DD
    formatDate4(val) {
      const pattern = /^\d{4}$/
      if (val && pattern.test(val)) {
        return val.substring(0, 2) + '/' + val.substring(2)
      }
      return val
    },

    // フォントの色を判定
    fontColorClass(val) {
      const n = Number(val)
      return n > 0 ? 'font-color__plus bold' : n < 0 ? 'font-color__minus bold' : '__black'
    },

    // リストの要素をカンマで区切って連結
    concatenatedWithComma(list) {
      return list.map(item => item.iclosedDay).join('、 ')
    }
  }
}
</script>

<style lang="scss">
.custom_dialog {
  .el-dialog__title{
    font-weight: bold;
  }
  }
</style>
<style lang="scss" scoped>
.link_contents ::v-deep a,
.link_contents ::v-deep a:focus,
.link_contents ::v-deep a:hover {
 text-decoration: underline; color: blue;
}
._bold_black_s {
  font-size: 14px;
  font-weight: bold;
  color: #606266;
  padding-bottom: 0.5rem;
}
._inner_table__top {
  border-top-style: none;
}
._inner_table__right {
  border-right-style: none;
}
._inner_table__left {
  border-left-style: none;
}
._inner_table__bottom {
  border-bottom-style: none;
}
._inner_table__shadow {
  box-shadow: none;
}
:deep(.el-form-item__content) ._blue {
    color: #00f;
    padding-left: 1rem;
  }
.custom_dialog {
  :deep(.el-dialog) {
      top: -10vh;
    }
  :deep(.el-dialog__header) {
      font-weight: bold;
      padding-top: 2rem;
    }
  :deep(.el-dialog__body) {
      padding-top: 0;
    }
  :deep(.el-card__body) {
    padding-bottom: 0.5rem;
    }
  }
.form-button__wrapper {
  margin: -30px 0 0 auto;
}
._bold_black_l {
  font-size: 16.5px;
  font-weight: 700;
  color: #18181A;
}
.__red {
  color: red;
}
</style>
