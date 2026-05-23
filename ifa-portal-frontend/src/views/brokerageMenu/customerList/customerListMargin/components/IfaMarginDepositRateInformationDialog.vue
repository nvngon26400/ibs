<template>
  <el-dialog
    v-model="vmIsVisible"
    left
    :close-on-click-modal="false"
    :show-close="false"
    :before-close="onDialogClose"
    width="400px"
  >
    <div>
      <el-row>
        <el-col>
          <page-caption
            text="委託保証金率"
            background-color="rgba(190, 190, 190, 0.35)"
          ></page-caption>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <div class="form-cancel-button__wrapper">
            <ifa-button
              text="戻る"
              color="secondary"
              small
              action-type="originalAction"
              @app-action-handler="onDialogClose"
            ></ifa-button>
          </div>
        </el-col>
      </el-row>
    </div>

    <div style="margin-top:0.5rem;">
      <table
        id="t1"
        style="margin-bottom: 0.5rem;width:100%;"
      >
        <tbody>
          <tr>
            <th class="_table__data __left _label">{{ title.butenCode }}</th>
            <td class="_table__data __left">{{ dialogDisplayData.butenCode }}</td>
          </tr>
          <tr>
            <th class="_table__data __left _label">{{ title.accountNumber }}</th>
            <td class="_table__data __left">{{ dialogDisplayData.accountNumber }}</td>
          </tr>
          <tr>
            <th class="_table__data __left _label">{{ title.customerName }}</th>
            <td class="_table__data __left">{{ dialogDisplayData.customerName }}</td>
          </tr>
          <tr>
            <th class="_table__data __left _label">{{ title.marginDepositRate }}</th>
            <td class="_table__data __left">{{ dialogDisplayData.marginDepositRate }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
export default {
  components: {
    pageCaption
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    dialogDisplayData: {
      type: Object,
      required: true
    }
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
      // 項目名
      title: {
        butenCode: '部店コード',
        accountNumber: '口座番号',
        customerName: '顧客名',
        marginDepositRate: '委託保証金率'
      },
      butenCode: '', // 部店
      accountNumber: '', // 口座番号
      customerName: '', // 顧客名
      marginDepositRate: '' // 委託保証金率
    }
  },
  methods: {
    // 初期化
    init() {
      this.butenCode = '' // 部店
      this.accountNumber = '' // 口座番号
      this.customerName = '' // 顧客名
      this.marginDepositRate = '' // 委託保証金率
    },
    setup(ui) {
      this.init()
      this.butenCode = ui.rowData.butenCode // 部店
      this.accountNumber = ui.rowData.accountNumber // 口座番号
      this.customerName = ui.rowData.customerName // 顧客名
      this.marginDepositRate = ui.rowData.marginDepositRate // 委託保証金率
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 数値をコンマを加えて3桁区切りにする
    addComma(num) {
      if (num === '-') {
        return num
      }
      return String(num).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, '$1,')
    }
  }
}
</script>

<style lang="scss" scoped>
.__bold {
  font-weight: bold;
}
.clickable:hover {
  cursor: pointer
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-start;
  padding: 0.5rem 0 0.2rem 0;
}
.form-cancel-button__wrapper {
  display: flex;
  justify-content: flex-end;
  padding: 0.5rem 0 0.2rem 0;
}
.form-area__input-number {
  width: 18rem;
  margin-left: 0.1rem;
}
#t1 tr { line-height: 40px; }
#t1 th { min-width: 7rem; }
#t1 th,td { border: 0px; }
:deep(.el-dialog__title) {
   font-weight: bold;
}
:deep(.el-dialog__header) {
   padding: 0px;
}
</style>
