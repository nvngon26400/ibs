<template>

  <div style="width: 100%;">
    <!-- 通知情報 -->
    <el-row v-if="noticeInfo.noticeInfoList?.length > 0">
      <div class="bold">
        注意情報
      </div>
      <el-table
        :data="noticeInfo.noticeInfoList"
        border
        stripe
      >
        <el-table-column
          prop="cautionKind"
          label="分類"
          header-align="center"
          width="300"
          :resizable="false"
        ></el-table-column>
        <el-table-column
          prop="memo"
          label="メモ"
          header-align="center"
          :resizable="false"
        >
          <template #default="scope">
            <div v-html="scope.row.memo"></div>
          </template>
        </el-table-column>
        <el-table-column
          prop="accuralDate"
          label="発生日"
          header-align="center"
          align="right"
          width="105"
          :resizable="false"
          :formatter="formatter"
        ></el-table-column>
        <el-table-column
          prop="dealtPerson"
          label="取扱者"
          header-align="center"
          width="100"
          :resizable="false"
        ></el-table-column>
      </el-table>
    </el-row>
    <el-row v-else>
      <div class="bold">
        注意情報
      </div>
      <el-row type="flex"
              justify="center"
              align="middle"
              style="height: 3rem;"
      >
        注意情報はありません。
      </el-row>
    </el-row>

    <!-- 未読の重要なお知らせによる取引制限 -->
    <el-row
      v-if="noticeInfo.tradeRestrictionList?.length > 0"
      style="margin-top: 1rem;"
    >
      <el-col :span="13">
        <span class="bold">
          未読の重要なお知らせによる取引制限
        </span>
      </el-col>
      <el-col :span="8">
        <span>
          ※確認期限が空欄の場合、即時制限開始となります。
        </span>
      </el-col>
      <el-table
        :data="noticeInfo.tradeRestrictionList"
        border
        stripe
        class="table-limit"
      >
        <el-table-column
          prop="restrictionCode"
          label="制限番号"
          header-align="center"
          width="100"
          :resizable="false"
        ></el-table-column>
        <el-table-column
          prop="restrictionContents"
          label="制限内容"
          header-align="center"
          :resizable="false"
        ></el-table-column>
        <el-table-column
          prop="confirmDeadline"
          label="確認期限"
          header-align="center"
          align="right"
          width="200"
          :resizable="false"
          :formatter="confirmDeadlineFormatter"
        ></el-table-column>
      </el-table>
    </el-row>
    <el-row v-else
            style="margin-top: 1rem;"
    >
      <el-col>
        <span class="bold">
          未読の重要なお知らせによる取引制限
        </span>
      </el-col>
      <el-row type="flex"
              justify="center"
              align="middle"
              style="height: 3rem;"
      >
        未読の重要なお知らせによる取引制限はありません。
      </el-row>
    </el-row>

  </div>
</template>

<script>
import { getFormattedDateValue, getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  name: 'IfaNoticeInfoContents',
  props: {
    noticeInfoLevel: { type: [String, Number], required: false, default: 0 },
    customerCode: { type: String, required: false, default: '' },
    butenCode: { type: String, required: false, default: '' },
    accountNumber: { type: String, required: false, default: '' },
    noticeInfo: { type: Object, required: false,
      default: () => {
        return { noticeInfoList: [], tradeRestrictionList: [] }
      }
    }
  },
  methods: {
    formatter(row, column) {
      return getFormattedDateValue(row.accuralDate) || '-'
    },
    confirmDeadlineFormatter(row, column) {
      return getFormattedDateTimeValue(row.confirmDeadline) || '-'
    }
  }
}
</script>

<style lang="scss" scoped>
.bold {
  font-weight: bold;
}
.table-limit{
  width: 70%;
}
</style>

<style>
/* テーブル内のフォント色を変えるには scoped 以外の style に定義する */
.color-red {
  color: #D10014;
}
</style>
