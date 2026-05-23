<template>
  <el-card
    class="box-card _height"
    style="width: 100%;"
  >
    <el-col class=" _title">{{ title }}</el-col>
    <table v-if="title==='コンプライアンス通信'"
           class="_table__body"
    >
      <tbody>
        <tr>
          <th v-if="!managerAlertComplianceReport || !Object.keys(managerAlertComplianceReport).length || managerAlertComplianceReport.thisMonthUnreplyFlag !== '1'"
              class="_table__header _cell _left"
              scope="row"
          >当月末閲覧者あり
          </th>
          <th
            v-else-if="managerAlertComplianceReport.thisMonthUnreplyFlag === '1'"
            class="_table__data _bold _cell _left"
            scope="row"
          >
            <a>
              <span
                v-if="$store.getters.userAccount.medUsers.privId === '1'"
                class="_link"
                @click="transitionA015(managerAlertComplianceReport.titleThisMonth, null)"
              >当月末閲覧者あり
              </span>
              <span
                v-else-if="$store.getters.userAccount.medUsers.privId === '3' || $store.getters.userAccount.medUsers.privId === '6'"
                class="_link"
                @click="transitionA016(managerAlertComplianceReport.titleThisMonth, null)"
              >当月末閲覧者あり
              </span>
              <span
                v-else
                class="_link"
              >当月末閲覧者あり
              </span>
            </a>
          </th>
        </tr>
        <tr>
          <th v-if="!managerAlertComplianceReport || !Object.keys(managerAlertComplianceReport).length || managerAlertComplianceReport.lastMonthUnreplyFlag !== '1'"
              class="_table__header _cell _left"
              scope="row"
          >過去月末閲覧者あり
          </th>
          <th
            v-else-if="managerAlertComplianceReport.lastMonthUnreplyFlag === '1'"
            class="_table__data _bold _cell _left"
            scope="row"
          >
            <a>
              <span
                v-if="$store.getters.userAccount.medUsers.privId === '1'"
                class="_link"
                @click="transitionA015(null, managerAlertComplianceReport.titleLastMonth)"
              >過去月末閲覧者あり
              </span>
              <span
                v-else-if="$store.getters.userAccount.medUsers.privId === '3' || $store.getters.userAccount.medUsers.privId === '6'"
                class="_link"
                @click="transitionA016(null, managerAlertComplianceReport.titleLastMonth)"
              >過去月末閲覧者あり
              </span>
              <span
                v-else
                class="_link"
              >過去月末閲覧者あり
              </span>
            </a>
          </th>
        </tr>
      </tbody>
    </table>
    <table v-if="title==='自己点検記録簿'"
           class="_table__body"
    >
      <tbody>
        <tr>
          <th
            v-if="!managerAlertSelfAssessment || !Object.keys(managerAlertSelfAssessment).length || managerAlertSelfAssessment.registerYearMonthThisMonth === null"
            class="_table__header _cell _left"
            scope="row"
          >
            当月末回答あり
          </th>
          <th
            v-else-if="managerAlertSelfAssessment.registerYearMonthThisMonth !== null"
            class="_table__data _bold _cell _left"
            scope="row"
          >
            <a>
              <span
                class="_link"
                @click="transitionA017(managerAlertSelfAssessment.registerYearMonthThisMonth)"
              >当月末回答あり
              </span>
            </a>
          </th>
        </tr>
        <tr>
          <th
            v-if="!managerAlertSelfAssessment || !Object.keys(managerAlertSelfAssessment).length || managerAlertSelfAssessment.registerYearMonthLastMonth === null"
            class="_table__header _cell _left"
            scope="row"
          >
            過去未回答あり
          </th>
          <th
            v-else-if="managerAlertSelfAssessment.registerYearMonthLastMonth !== null"
            class="_table__data _bold _cell _left"
            scope="row"
          >
            <a>
              <span
                class="_link"
                @click="transitionA017(managerAlertSelfAssessment.registerYearMonthLastMonth)"
              >過去未回答あり
              </span>
            </a>
          </th>
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
    managerAlertComplianceReport: {
      required: true,
      type: Object
    },
    managerAlertSelfAssessment: {
      required: true,
      type: Object
    }
  },
  data() {
    return {
    }
  },
  watch: {
    managerAlertComplianceReport: {
      handler: function() {
        return
      },
      deep: true
    },
    managerAlertSelfAssessment: {
      handler: function() {
        return
      },
      deep: true
    }
  },
  methods: {
    transitionA015(titleThisMonth, titleLastMonth) {
      // コンプライアンス通信閲覧状況照会（管理者用）に遷移
      const params = {
        source: 'SUB01-01',
        data: {
          // タイトル（当月）
          titleThisMonth: titleThisMonth,
          // タイトル（過去月）
          titleLastMonth: titleLastMonth
        }
      }
      this.$_startShowMenu('SUB0505_02', params)
    },
    transitionA016(titleThisMonth, titleLastMonth) {
      // コンプライアンス通信閲覧状況照会(内部管理責任者用)に遷移
      const params = {
        source: 'SUB01-01',
        data: {
          // タイトル（当月）
          titleThisMonth: titleThisMonth,
          // タイトル（過去月）
          titleLastMonth: titleLastMonth
        }
      }
      this.$_startShowMenu('SUB0401_01', params)
    },
    transitionA017(registerYear) {
      const param = {
        assignMonth: registerYear
      }
      // 自己点検記録簿に遷移
      this.$_startShowMenu('SUB0401_02', param)
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
  height: 84px;
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

.box-card {
  margin: 10px 15px 10px 10px;
}
</style>
