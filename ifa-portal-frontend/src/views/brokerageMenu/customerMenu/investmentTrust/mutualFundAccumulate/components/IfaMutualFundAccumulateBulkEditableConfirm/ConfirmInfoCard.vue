<template>
  <!-- bulk list cards -->
  <div
    v-for="(item, idx) in info?.bulkChangeList"
    :key="idx"
  >
    <el-divider style="margin: 16px 0; border-width: 2px;"></el-divider>

    <div style="display: flex; flex-direction: column; row-gap: 10px;">
      <!-- error msg area -->
      <ul
        v-if="status === 'confirm' &&
          (item?.complianceRankCheck?.message || item?.complianceRankCheck?.startCriteriaConfirmMsg)"
        class="error-message-item"
      >
        <li v-if="item?.complianceRankCheck?.message">
          {{ item?.complianceRankCheck?.message }}
        </li>
        <li v-if="item?.complianceRankCheck?.startCriteriaConfirmMsg">
          {{ item?.complianceRankCheck?.startCriteriaConfirmMsg }}
        </li>
      </ul>
      <!-- confirm data area -->
      <confirm-editable-item
        :info="info"
        :item="item"
        :status="status"
      ></confirm-editable-item>
      <!-- warning check area -->
      <warning-check-item
        :form="form"
        :item="item"
        :idx="idx"
        :status="status"
      ></warning-check-item>
    </div>
  </div>
</template>

<script>
import ConfirmEditableItem from './ConfirmEditableItem.vue'
import WarningCheckItem from './WarningCheckItem.vue'

export default {
  components: {
    ConfirmEditableItem,
    WarningCheckItem
  },
  props: {
    status: { type: String, required: true },
    info: { type: Object, required: true },
    form: { type: Object, required: true }
  }
}
</script>

<style lang="scss" scoped>
.error-message-item {
  margin: 0;
  padding: 0 1rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  display: flex;
  flex-direction: column;
  row-gap: 5px;
}

:deep(.el-divider--horizontal) {
  margin: 16px 0;
  border-width: 2px;
}
</style>
