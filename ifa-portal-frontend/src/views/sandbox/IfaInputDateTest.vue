<template>
  <div class="main">
    <h3 style="display: flex;">
      ●入力テスト
      <ifa-button
        text="RESET"
        small
        action-type="originalAction"
        @app-action-handler="resetAll"
      ></ifa-button>
      <span style="margin-left: 0.5rem;"></span>
      <ifa-input-check
        v-model="labelPosition"
        code-list-id="original"
        :original-list="[
          { key: 'right', value: '' },
          { key: 'left', value: '左寄せ' }
        ]"
      ></ifa-input-check>
    </h3>

    <hr>
    <h4>
      【IfaDatePicker】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formDate')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formDate"
      :model="formDate"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-date-picker
          v-model="formDate.date"
          :domain="{
            domainId: 'DATE8',
            domainName: '西暦年月日8',
            dataType: '日付',
            precision: 10,
            digit: null,
            lengthFixed: true,
            validate: null,
            javaType: 'String',
            oracleType: 'DATE',
            format: 'date8',
            formattedDigit: 10,
            verticalAlign: 'M',
            horizontalAlign: 'L',
            unit: null,
            htmlTag: null
          }"
          prop="date"
          label="日付"
          size="small"
          required
        ></ifa-date-picker>
        <span class="label">入力結果:</span><span class="value">{{ formDate.date }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaDateRangePicker】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formDateRange')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formDateRange"
      :model="formDateRange"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-date-range-picker
          v-model="formDateRange.dateRange"
          :domain="{
            domainId: 'DATE8_RANGE',
            domainName: '西暦年月日期間',
            dataType: '日付',
            precision: null,
            digit: null,
            lengthFixed: null,
            validate: null,
            format: 'date8Kanji',
            javaType: 'Date',
            oracleType: 'DATE',
            verticalAlign: 'M',
            horizontalAlign: 'L',
            unit: null,
            htmlTag: null
          }"
          prop="dateRange"
          label="日付(期間)"
          size="small"
          required
        ></ifa-date-range-picker>
        <span class="label">入力結果:</span><span class="value">{{ formDateRange.dateRange }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaMonthPicker】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formMonth')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formMonth"
      :model="formMonth"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-month-picker
          v-model="formMonth.month"
          :domain="{
            domainId: 'DATE6_YEAR_MONTH',
            domainName: '西暦年月',
            dataType: '年月',
            precision: null,
            digit: null,
            lengthFixed: null,
            validate: null,
            format: 'date6YearMonthKanji',
            javaType: 'Date',
            oracleType: 'DATE',
            verticalAlign: 'M',
            horizontalAlign: 'L',
            unit: null,
            htmlTag: null
          }"
          prop="month"
          label="年月"
          size="small"
          required
        ></ifa-month-picker>
        <span class="label">入力結果:</span><span class="value">{{ formMonth.month }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaMonthRangePicker】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formMonthRangd')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formMonthRangd"
      :model="formMonthRange"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-month-range-picker
          v-model="formMonthRange.monthRange"
          :domain="{
            domainId: 'DATE6_YEAR_MONTH_RANGE',
            domainName: '西暦年月期間',
            dataType: '年月(期間)',
            precision: null,
            lengthFixed: null,
            validate: null,
            format: 'date6YearMonthKanji',
            javaType: 'Date',
            oracleType: 'DATE',
            verticalAlign: 'M',
            horizontalAlign: 'L',
            unit: null,
            htmlTag: null
          }"
          prop="monthRange"
          label="年月(期間)"
          size="small"
          required
        ></ifa-month-range-picker>
        <span class="label">入力結果:</span><span class="value">{{ formMonthRange.monthRange }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaDateTimePicker】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formDateTime')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formDateTime"
      :model="formDateTime"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-date-time-picker
          v-model="formDateTime.datetime"
          :domain="{
            domainId: 'DATETIME12',
            domainName: '西暦日付時間',
            dataType: '日付時間',
            precision: null,
            lengthFixed: null,
            validate: null,
            format: 'datetime14',
            javaType: 'Date',
            oracleType: 'DATE',
            verticalAlign: 'M',
            horizontalAlign: 'L',
            unit: null,
            htmlTag: null
          }"
          prop="datetime"
          label="日付時間"
          size="small"
          required
        ></ifa-date-time-picker>
        <span class="label">入力結果:</span><span class="value">{{ formDateTime.datetime }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaPeriodSelector】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formPeriodSelector')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formPeriodSelector"
      :model="formPeriodSelector"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-period-selector
          ref="periodSelector"
          v-model:period="formPeriodSelector.period"
          v-model:period-type="formPeriodSelector.periodType"
          label="期間"
          prop="periodType"
          :picker-options="checkBusinessDay"
          required
        ></ifa-period-selector>
        <span class="label">入力結果(タイプ):</span><span class="value">{{ formPeriodSelector.periodType }}</span>
        <span class="label">入力結果(期間):</span><span class="value">{{ formPeriodSelector.period }}</span>
      </div>
    </el-form>

    <hr>
    <h4>【Global Functions】</h4>
    <el-input
      v-model="testDate"
      type="text"
    ></el-input>

    <table border="2">
      <tr><th>method</th><th>format</th><th>result</th></tr>
      <tr><td rowspan="5">$_getFormattedDateValue</td><td>default</td><td>{{ $_getFormattedDateValue(testDate) }}</td></tr>
      <tr><td>date6</td><td>{{ $_getFormattedDateValue(testDate, 'date6') }}</td></tr>
      <tr><td>date8</td><td>{{ $_getFormattedDateValue(testDate, 'date8') }}</td></tr>
      <tr><td>date8Kanji</td><td>{{ $_getFormattedDateValue(testDate, 'date8Kanji') }}</td></tr>
      <tr><td>null(same as default)</td><td>{{ $_getFormattedDateValue(testDate, null) }}</td></tr>
      <tr><td>unknown(same as default)</td><td>{{ $_getFormattedDateValue(testDate, 'unknown') }}</td></tr>
      <tr><td rowspan="5">$_getFormattedMonthValue</td><td>default</td><td>{{ $_getFormattedMonthValue(testDate) }}</td></tr>
      <tr><td>date6YearMonth</td><td>{{ $_getFormattedMonthValue(testDate, 'date6YearMonth') }}</td></tr>
      <tr><td>date6YearMonthKanji</td><td>{{ $_getFormattedMonthValue(testDate, 'date6YearMonthKanji') }}</td></tr>
      <tr><td>null(same as default)</td><td>{{ $_getFormattedMonthValue(testDate, null) }}</td></tr>
      <tr><td>unknown(same as default)</td><td>{{ $_getFormattedMonthValue(testDate, 'unknown') }}</td></tr>
      <tr><td rowspan="5">$_getFormattedDateTimeValue</td><td>default</td><td>{{ $_getFormattedDateTimeValue(testDate) }}</td></tr>
      <tr><td>date6</td><td>{{ $_getFormattedDateTimeValue(testDate, 'date6') }}</td></tr>
      <tr><td>datetime14</td><td>{{ $_getFormattedDateTimeValue(testDate, 'datetime14') }}</td></tr>
      <tr><td>datetime12</td><td>{{ $_getFormattedDateTimeValue(testDate, 'datetime12') }}</td></tr>
      <tr><td>null(be applied 'YYYY/MM/DD' as default)</td><td>{{ $_getFormattedDateTimeValue(testDate, null) }}</td></tr>
      <tr><td>unknown(be applied 'YYYY/MM/DD' as default)</td><td>{{ $_getFormattedDateTimeValue(testDate, 'unknown') }}</td></tr>
      <tr><td rowspan="5">$_getFormattedTimeValue</td><td>default</td><td>{{ $_getFormattedTimeValue(testDate) }}</td></tr>
      <tr><td>datetime14</td><td>{{ $_getFormattedTimeValue(testDate, 'time6') }}</td></tr>
      <tr><td>datetime12</td><td>{{ $_getFormattedTimeValue(testDate, 'time4') }}</td></tr>
      <tr><td>null(be applied 'HH/mm/ss' as default)</td><td>{{ $_getFormattedTimeValue(testDate, null) }}</td></tr>
      <tr><td>unknown(be applied 'HH/mm/ss' as default)</td><td>{{ $_getFormattedTimeValue(testDate, 'unknown') }}</td></tr>
    </table>

  </div>
</template>

<script>
export default {
  name: 'IfaInputDateTest',
  data() {
    return {
      formDate: { date: '' },
      formDateRange: { dateRange: [] },
      formMonth: { month: '' },
      formMonthRange: { monthRange: [] },
      formDateTime: { datetime: '' },
      formPeriodSelector: { periodType: null, period: '' },
      testDate: '2023/09/29 12:34:45'
    }
  },
  computed: {
    checkBusinessDay() {
      return {
        labelPosition: 'right',
        disabledDate(date) {
          // 'date'が土日の場合は無効な日付
          if (date.getDay() === 0 || date.getDay() === 6) {
            return true
          }

          // TODO: 'date'が非営業日の時は無効な日付(true)にする処理が必要

          // 翌日を算出する｡現在時刻が15時以降なら翌々日に補正
          const before = new Date()
          const hours = before.getHours()
          before.setHours(0, 0, 0, 0)
          if (hours < 15) {
            before.setDate(before.getDate() + 1)
          } else {
            before.setDate(before.getDate() + 2)
          }
          // 翌日または翌々日が土日の場合は翌月曜日に補正
          if (before.getDay() === 6) {
            before.setDate(before.getDate() + 2)
          } else if (before.getDay() === 0) {
            before.setDate(before.getDate() + 1)
          }
          // 'date'が算出した日付より小さい場合は無効な日付
          if (before > date) {
            return true
          }

          // 14営業日先の日付を算出する
          const after = new Date(before)
          let d = 13
          while (d > 0) {
            after.setDate(after.getDate() + 1)
            if (after.getDay() !== 0 && after.getDay() !== 6) {
              // TODO: 土日以外の非営業日をカウントしない処理が必要
              d--
            }
          }
          // 'date'が算出した日付より大きい場合は無効な日付
          if (after < date) {
            return true
          }

          // 上記以外は有効な日付
          return false
        },
        shortcuts: [
          {
            text: '週末',
            value: () => {
              // 本日を取得
              const date = new Date()
              // 曜日を取得
              const day = date.getDay()
              if (day === 5) {
                // 本日が金曜日(5)なら+7dayして翌週の金曜日に補正
                date.setDate(date.getDate() + 7)
              } else if (day === 6) {
                // 本日が土曜日(6)なら+6dayして翌週の金曜日に補正
                date.setDate(date.getDate() + 6)
              } else if (day === 0) {
                // 本日が日曜日(0)なら+5dayして金曜日に補正
                date.setDate(date.getDate() + 5)
              } else {
                // それ以外は､金曜日(5)までの日数を加算して週末を算出する
                date.setDate(date.getDate() + (5 - day))
              }
              // TODO: 金曜日が非営業日の時に日付を遡って営業日を検索する処理が必要
              return date
            }
          },
          {
            text: '翌営業日',
            value: () => {
              // 翌日を算出する｡現在時刻が15時以降なら翌々日に補正
              const date = new Date()
              const hours = date.getHours()
              date.setHours(0, 0, 0, 0)
              if (hours < 15) {
                date.setDate(date.getDate() + 1)
              } else {
                date.setDate(date.getDate() + 2)
              }
              // 翌日または翌々日が土日の場合は翌月曜日に補正
              if (date.getDay() === 6) {
                date.setDate(date.getDate() + 2)
              } else if (date.getDay() === 0) {
                date.setDate(date.getDate() + 1)
              }
              // TODO: 算出した日付が非営業日の時に日付を進めて営業日を検索する処理が必要
              return date
            }
          },
          {
            text: '最大',
            value: () => {
              // 翌日を算出する｡現在時刻が15時以降なら翌々日に補正
              const dt = new Date()
              const hours = dt.getHours()
              dt.setHours(0, 0, 0, 0)
              if (hours < 15) {
                dt.setDate(dt.getDate() + 1)
              } else {
                dt.setDate(dt.getDate() + 2)
              }
              // 翌日または翌々日が土日の場合は翌月曜日に補正
              if (dt.getDay() === 6) {
                dt.setDate(dt.getDate() + 2)
              } else if (dt.getDay() === 0) {
                dt.setDate(dt.getDate() + 1)
              }
              // 14営業日先の日付を算出する
              const date = new Date(dt)
              let d = 13
              while (d > 0) {
                date.setDate(date.getDate() + 1)
                if (date.getDay() !== 0 && date.getDay() !== 6) {
                  // TODO: 土日以外の非営業日をカウントしない処理が必要
                  d--
                }
              }
              return date
            }
          }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      // フォームのサブミット前に入力値チェックを行う
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$_logDebug('validation success')
        } else {
          this.$_logDebug('validation fail')
          return false
        }
      })
    },
    resetAll() {
      this.formDate.date = ''
      this.formDateRange.dateRange = []
      this.formMonth.month = ''
      this.formMonthRange.monthRange = []
      this.formDateTime.datetime = ''
      this.$refs['periodSelector'].resetComponent()
      this.testDate = '2023/09/29 12:34:45'
    }
  }
}
</script>

<style lang='scss' scoped>
.main {
  margin: 2rem;
}
.result {
  // height: 4rem;
  vertical-align: middle;
  margin-bottom: 16px;
}
.label {
  padding-left: 6.5rem;
  padding-right: 1rem;
  margin-bottom: 16px;
}
.value {
  padding: 0.4rem;
  background-color: white;
  border: 1px solid blue;
  line-height: 24px;
}
</style>
<style lang='scss'>
// label-class="color-blue"
// input-class="input-class-test"
.color-blue {
  .el-form-item__label {
    color: blue;
    text-decoration: underline;
    text-underline-offset: 4px;
    text-justify: newspaper;
  }
}
.date-class-test {
  .el-input__wrapper {
    background-color: yellow;
    width: 300px;
    height: 100px;
    .el-input__inner {
      color: red;
    }
  }
}
.date-class-test.el-input__wrapper {
  background-color: yellow;
  width: 300px;
  height: 100px;
  .el-range-input {
    color: red;
  }
}
</style>
