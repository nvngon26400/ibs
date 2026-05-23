<template>
  <captionCard
    caption="管理者アラート"
    style="margin: 5px 10px 5px 10px;"
  >
    <div class="filter-container">
      <el-row>
        <el-col :span="7">
          <ifa-manager-alert-table
            :title="title = 'コンプライアンス通信'"
            :manager-alert-compliance-report="managerAlertComplianceReport"
            :manager-alert-self-assessment="{}"
          ></ifa-manager-alert-table>
        </el-col>
        <el-col
          :span="7"
          :offset="1"
        >
          <ifa-manager-alert-table
            :title="title = '自己点検記録簿'"
            :manager-alert-compliance-report="{}"
            :manager-alert-self-assessment="managerAlertSelfAssessment"
          ></ifa-manager-alert-table>
        </el-col>
        <el-col
          :span="8"
        >
          <div class="update-button">
            <ifa-button
              id="btnUpdate"
              class="RefreshRight"
              text=" 更新"
              icon="RefreshRight"
              color="primary"
              action-type="requestAction"
              action-id="SUB01-01#A018"
              style="width: 105px;"
              @response-handler="updateA018($event)"
            ></ifa-button>
          </div>
        </el-col>
      </el-row>

    </div>
  </captionCard>

</template>

<script>
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard.vue'

import ifaManagerAlertTable from './components/ifaManagerAlertTable.vue'
export default {
  components: {
    captionCard,
    ifaManagerAlertTable
  },
  props: {
    form: {
      required: true,
      type: Object
    }
  },
  data() {
    return {
      ifaCsvOutputDialogVisible: false,
      title: '',
      managerAlertComplianceReport: {},
      managerAlertSelfAssessment: {},
      isVisible: false
    }
  },
  watch: {
    form: {
      handler: function() {
        this.onShow()
      },
      deep: true
    }
  },
  methods: {
    onShow() {
      this.managerAlertComplianceReport = this.form.managerAlertComplianceReport
      this.managerAlertSelfAssessment = this.form.managerAlertSelfAssessment
    },
    updateA018(response) {
      this.form.managerAlertComplianceReport = response.dataList[0].managerAlertComplianceReport || {}
      this.form.managerAlertSelfAssessment = response.dataList[0].managerAlertSelfAssessment || {}
    },
    handleCloseModal() {
      this.ifaCsvOutputDialogVisible = false
    }
  }
}

</script>
<style scoped>
  .notice-unchecked {
      color:red;
  }
  .msg-area{
    border: 2px solid;
    background-color: rgba(248, 248, 195, 0.7);;
  }
  :deep(.pq-grid-link) {
    color:#409EFF;
    text-decoration: underline;
    text-underline-offset:0.2em;
    cursor: pointer;
}
.hidden {
    display: none;
}
.area-border-color {
  border: solid 1px rgb(189, 189, 189);
}
.update-button {
  display: flex;
  justify-content: flex-end;
  text-align: right;
  margin-top: 1rem;
}
.filter-container {
  margin: 0 1rem 0 1rem;
  margin-right: 0;
}
</style>
