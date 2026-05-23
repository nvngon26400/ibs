<template>
  <div class="container">
    <caption-card
      caption="書類請求付加情報"
      style="width:100%;"
    >
      <div style="padding: 5px;">
        <el-descriptions
          :column="1"
          size="small"
          border
          style="width: 100%"
        >
          <el-descriptions-item>
            <template #label>
              <div>顧客ID</div>
            </template>
            {{ customerInfo.customerCode }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div>部店・口座番号</div>
            </template>
            {{ `${customerInfo.butenCode}-${customerInfo.accountNumber}` }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div>資料No.</div>
            </template>
            {{ requestNo ?? '-' }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div>資料名</div>
            </template>
            {{ paperName ?? '-' }}
          </el-descriptions-item>
          <template v-if="!!docRequestAddInfo">
            <el-descriptions-item v-if="docRequestAddInfo?.[0]?.option1q">
              <template #label>
                <div>{{ docRequestAddInfo?.[0]?.option1q }}</div>
              </template>
              {{ docRequestAddInfo?.[0]?.option1a ?? '-' }}
            </el-descriptions-item>
            <el-descriptions-item v-if="docRequestAddInfo?.[0]?.option2q">
              <template #label>
                <div>{{ docRequestAddInfo?.[0]?.option2q }}</div>
              </template>
              {{ docRequestAddInfo?.[0]?.option2a ?? '-' }}
            </el-descriptions-item>
            <el-descriptions-item v-if="docRequestAddInfo?.[0]?.option3q">
              <template #label>
                <div>{{ docRequestAddInfo?.[0]?.option3q }}</div>
              </template>
              {{ docRequestAddInfo?.[0]?.option3a ?? '-' }}
            </el-descriptions-item>
            <el-descriptions-item v-if="docRequestAddInfo?.[0]?.text1q">
              <template #label>
                <div>{{ docRequestAddInfo?.[0]?.text1q }}</div>
              </template>
              {{ docRequestAddInfo?.[0]?.text1a ?? '-' }}
            </el-descriptions-item>
            <el-descriptions-item v-if="docRequestAddInfo?.[0]?.text2q">
              <template #label>
                <div>{{ docRequestAddInfo?.[0]?.text2q }}</div>
              </template>
              {{ docRequestAddInfo?.[0]?.text2a ?? '-' }}
            </el-descriptions-item>
            <el-descriptions-item v-if="docRequestAddInfo?.[0]?.text3q">
              <template #label>
                <div>{{ docRequestAddInfo?.[0]?.text3q }}</div>
              </template>
              {{ docRequestAddInfo?.[0]?.text3a ?? '-' }}
            </el-descriptions-item>
            <el-descriptions-item v-if="docRequestAddInfo?.[0]?.text4q">
              <template #label>
                <div>{{ docRequestAddInfo?.[0]?.text4q }}</div>
              </template>
              {{ docRequestAddInfo?.[0]?.text4a ?? '-' }}
            </el-descriptions-item>
            <el-descriptions-item v-if="docRequestAddInfo?.[0]?.text5q">
              <template #label>
                <div>{{ docRequestAddInfo?.[0]?.text5q }}</div>
              </template>
              {{ docRequestAddInfo?.[0]?.text5a ?? '-' }}
            </el-descriptions-item>
          </template>
        </el-descriptions>
        <div
          v-if="docRequestAddInfo && docRequestAddInfo?.length === 0"
          style="padding-top: 10px; color: red;"
        >
          付加情報はありません
        </div>
      </div>
    </caption-card>
    <caption-card
      v-if="selectDocDeficiencyInfoList && selectDocDeficiencyInfoList.length > 0"
      caption="書類不備付加情報"
      style="width:100%"
    >
      <div style="padding: 5px;">
        <el-table
          :data="selectDocDeficiencyInfoList"
          border
          stripe
          size="small"
          row-key="id"
        >
          <el-table-column
            prop="ddiRequestSubNo"
            label="No."
            header-align="center"
            :width="80"
            :resizable="false"
            align="right"
          ></el-table-column>
          <el-table-column
            prop="ddiReason"
            label="不備理由"
            header-align="center"
          >
            <template #default="scope">{{ scope.row.ddiReason ?? '-' }}</template>
          </el-table-column>
          <el-table-column
            prop="ddiRemarks"
            label="備考"
            header-align="center"
          >
            <template #default="scope">{{ scope.row.ddiRemarks ?? '-' }}</template>
          </el-table-column>
        </el-table>
      </div>
    </caption-card>
  </div>
</template>

<script>
import CaptionCard from '@/views/brokerageMenu/customerMenu/components/captionCard.vue'

export default {
  name: 'AdditionalInfoPanel',
  components: {
    CaptionCard
  },
  props: {
    requestNo: { type: String, required: true },
    paperName: { type: String, required: true },
    detailInfo: { type: Object, required: true }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    docRequestAddInfo() {
      return this.detailInfo?.docRequestAddInfo
    },
    selectDocDeficiencyInfoList() {
      return this.detailInfo.selectDocDeficiencyInfoList
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  row-gap: 25px;

  .box-card {
    margin: 0;
  }

  .caption-container {
    margin-left: 0;
    margin-top: 0;
  }

}

:deep(.el-descriptions__body .el-descriptions__table.is-bordered .el-descriptions__cell) {
  border: 1px solid #a7b1c3;
}

:deep(.el-descriptions__label.el-descriptions__cell.is-bordered-label) {
  width: 150px;
  background: #e6e8f0;
  color: #18181a;
  font-weight: bold;
  font-size: 14px;
}

:deep(.el-descriptions--small .el-descriptions__body .el-descriptions__table .el-descriptions__cell) {
  font-size: 14px;
  color: #18181a;
}

:deep(.el-descriptions__body) {
  box-shadow: 1px 2px 2px #ddd;
}
</style>
