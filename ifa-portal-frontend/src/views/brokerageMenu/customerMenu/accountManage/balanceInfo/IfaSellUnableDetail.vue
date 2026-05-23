<template>
  <!-- 売却不可明細ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    class="custom-dialog"
    :title="form.title.name"
    width="1605px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    @open="onShow"
  >
    <!-- 戻るボタン -->
    <el-row>
      <ifa-button
        action-type="originalAction"
        class="close-button__wrapper"
        text="閉じる"
        color="secondary"
        @app-action-handler="onDialogClose"
      ></ifa-button>
    </el-row>
    <el-row
      v-if="form.sellUnableDetailList.length > 0"
      style="padding-top: 1rem;"
    >
      <el-col
        :span="22"
        :offset="1"
        style="overflow-x: auto;"
      >
        <table class="_table__horizontal _table__body">
          <thead>
            <tr>
              <th
                class="_table__header __center"
                style="width: 90px; min-width: 90px;"
              >商品区分</th>
              <th
                class="_table__header __center"
                style="width: auto; min-width: 260px;"
              >コード 銘柄名</th>
              <th
                class="_table__header __center"
                style="width: 160px; min-width: 160px;"
              >預り区分</th>
              <th
                class="_table__header __center"
                style="width: 85px; min-width: 85px;"
              >代用<br>適格区分</th>
              <th
                class="_table__header __center"
                style="width: 100px; min-width: 100px;"
              >制限数</th>
              <th
                class="_table__header __center"
                style="width: 120px; min-width: 120px;"
              >理由</th>
              <th
                class="_table__header __center"
                style="width: 210px; min-width: 210px;"
              >売却制限期間</th>
              <th
                class="_table__header __center"
                style="width: 210px; min-width: 210px;"
              >代用制限期間</th>
              <th
                class="_table__header __center"
                style="width: 120px; min-width: 120px;"
              >登録日</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(data, index) in form.sellUnableDetailList"
              :key="index"
            >
              <td class="_table__data __center">{{ $_out(data.securityType) }}</td>
              <td class="_table__data __left">{{ $_out(data.brandCodeBrandName) }}</td>
              <td class="_table__data __center"
                  v-html="formattedDepositType(data.depositType, data.fundDepositType)"
              ></td>
              <td class="_table__data __center">{{ $_out(data.collateralEligibleType) }}</td>
              <td class="_table__data __right">{{ $_out($_withCommaInteger(data.restrictionCount)) }}</td>
              <td class="_table__data __center">{{ $_out(data.reason) }}</td>
              <td class="_table__data __center">
                {{ $_getFormattedDateValue(data.limitedPeriodStart)
                  ? $_getFormattedDateValue(data.limitedPeriodStart)
                  : '----/--/--' }}～{{ $_getFormattedDateValue(data.limitedPeriodFinish)
                  ? $_getFormattedDateValue(data.limitedPeriodFinish)
                  : '----/--/--' }}
              </td>
              <td class="_table__data __center">{{ $_getFormattedDateValue(data.substituteStart)
                ? $_getFormattedDateValue(data.substituteStart)
                : '----/--/--' }}～{{ $_getFormattedDateValue(data.substituteFinish)
                ? $_getFormattedDateValue(data.substituteFinish)
                : '----/--/--' }}
              </td>
              <td class="_table__data __center"
                  v-html="$_getFormattedDateTimeValue(data.registeredDate, 'datetime12')
                    ? $_getFormattedDateTimeValue(data.registeredDate, 'datetime12').split(' ')[0] + '<br/>' + $_getFormattedDateTimeValue(data.registeredDate, 'datetime12').split(' ')[1]
                    : '----/--/--<br/>--:--'"
              ></td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>
    <div
      v-else
      class="caption_card"
      style="text-align: center;"
    >
      <div>{{ form.noDetailMsg }}</div>
    </div>

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaSellUnableDetailFormModel } from './js/IfaSellUnableDetailFormModel'
export default {
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true }
  },
  emits: ['close', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaSellUnableDetailFormModel()
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close')
    },
    formattedDepositType(depositType, fundDepositType) {
      const newFundDepositType = fundDepositType || ''
      if (depositType) {
        const newDpositTypeValue = depositType.replaceAll(' ', '<br>')
        return newDpositTypeValue + newFundDepositType
      } else {
        return '-' + newFundDepositType
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.custom-dialog {
  :deep(.el-dialog__header) {
    font-weight: 700;
    padding-top: 2rem;
    overflow-x: auto;
  }
  :deep(.el-dialog__body) {
    padding-top: 0;
  }
  :deep(.el-dialog__footer) {
    display: flex;
    justify-content: flex-start;
    padding: 0 0 2rem 4rem;
  }
}
.close-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
</style>
