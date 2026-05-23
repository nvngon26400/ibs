<template>

  <div>
    <el-card
      class="box-card custom_card"
      shadow="never"
      style="min-width: 1320px;"
    >
      <div class="caption_card">
        <ifa-customer-alert-csv-output
          v-if="shownPrivId.includes(medUsersPrivId)"
          :med-users-priv-id="medUsersPrivId"
          style="bottom:0px;"
          :form="form"
        ></ifa-customer-alert-csv-output>
        <ifa-manager-alert
          v-if="shownPrivId.includes(medUsersPrivId)"
          :form="form"
        ></ifa-manager-alert>
        <captionCard
          v-if="$store.getters.userAccount.userNeedsToReadComplianceLetters === true"
          caption="コンプライアンス通信のお知らせ"
          style="margin: 5px 10px 5px 10px;"
        >
          <!-- TODO 遷移先担当者がA020実装 -->
          <div style="margin: 0.5rem 0 0 0.5rem;"><el-link
            style="text-decoration:underline; text-underline-offset:0.2em;color:#409EFF;"
            @click="a020Action"
          >未読のコンプライアンス通信があります。お早めに閲覧ください。</el-link></div>
        </captionCard>
        <ifa-portal-user-info
          :form="form"
          :notification-list="form.notificationList"
        ></ifa-portal-user-info>
        <ifa-customer-alert-csv-output
          v-if="!shownPrivId.includes(medUsersPrivId)"
          :form="form"
        ></ifa-customer-alert-csv-output>
      </div>
    </el-card>
    <ifa-requester
      id="ifaWholePortalHomeInitializeA001"
      action-id="SUB01-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerinitializeA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import ifaPortalUserInfo from './IfaPortalUserInfo'
import ifaCustomerAlertCsvOutput from './IfaCustomerAlertCsvOutput.vue'
import ifaManagerAlert from './IfaManagerAlert.vue'
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard.vue'
import { IfaWholePortalHomeFormModel } from './js/IfaWholePortalHomeFormModel.js'

export default {
  name: 'WholePortal',
  components: {
    ifaCustomerAlertCsvOutput,
    ifaPortalUserInfo,
    ifaManagerAlert,
    captionCard
  },
  data() {
    return {
      medUsersPrivId: '',
      menuNo: '1',
      form: new IfaWholePortalHomeFormModel(),
      shownPrivId: ['1', '2', '3', '6']
    }
  },
  mounted() {
    this.$store.dispatch('customerPortalMenuList/setMenuList', { menuList: [] })
    this.medUsersPrivId = this.$store.getters.userAccount.medUsers.privId
    this.$nextTick(() => {
      document.getElementById('ifaWholePortalHomeInitializeA001').click()
      this.$store.getters.userAccount.medUsers.privId
    })
    const pageInfo = {
      componentName: 'ifa-whole-portal-home',
      label: '総合ポータルホーム',
      menuId: 'SUB01-01',
      target: ''
    }
    this.$_finishShowMenu(pageInfo)
    // ホーム画面に遷移した場合は通知ポップアップを消去する
    this.$store.dispatch('notifications/resetState')
  },
  methods: {
    responseHandlerinitializeA001(data) {
      if (data.errorLevel === 0) {
        Object.assign(this.form, data.dataList[0])
      } else {
        return
      }
    },
    a020Action() {
      // コンプライアンス通信に遷移
      this.$_startShowMenu('SUB0302-01')
    }
  }
}
</script>

<style lang="scss" scoped>
// .dashboard-container {
//   padding: 32px;
//   position: relative;

// }
.caption_card {
  padding: 0 15px 0 15px;
}
.custom_card > :deep(.el-card__body) {
  padding-top: 0px;
  padding-bottom: 0px;
}
</style>
