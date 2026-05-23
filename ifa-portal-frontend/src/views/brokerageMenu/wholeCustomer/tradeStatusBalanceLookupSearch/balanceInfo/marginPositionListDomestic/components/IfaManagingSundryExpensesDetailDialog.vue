<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :close-on-click-modal="false"
      :show-close="false"
      :before-close="onDialogClose"
    >
      <!-- 戻るボタン -->
      <el-row>
        <ifa-button
          class="form-button__wrapper"
          color="secondary"
          text="戻る"
          padding="100px 10px"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-row>
      <el-card style="margin-top: 25px;">
        <template #header>
          <div
            class="box-card"
          >
            <span>諸経費等 詳細</span>
          </div>
        </template>
        <el-row class="display">
          <el-col
            :span="22"
            :offset="1"
          >
            <el-form
              ref="form"
              :model="form"
              :inline="true"
            >
              <table class="input-table">
                <tr>
                  <td class="table-label">銘柄名</td>
                  <td class="table-body">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.brandName }}
                      </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="table-label">銘柄コード</td>
                  <td class="table-body">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.brandCode }}
                      </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="table-label">市場</td>
                  <td class="table-body">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.market }}
                      </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="table-label">取引</td>
                  <td class="table-body">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.openTradeKbn }}
                      </span>
                    </el-form-item>
                  </td>
                </tr>
              </table>

              <table class="input-table">
                <tr>
                  <td class="table-label">新規建手数料(税込)</td>
                  <td class="table-body __right">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.chargeIncludeTax }}円
                      </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="table-label">管理料</td>
                  <td class="table-body __right">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.managementFee }}円
                      </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="table-label">権利処理等手数料</td>
                  <td class="table-body __right">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.rightsProcessingCharge }}円
                      </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="table-label">日歩</td>
                  <td class="table-body __right">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.dailyInterest }}円
                      </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="table-label">逆日歩</td>
                  <td class="table-body __right">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.reverseDailyInterest }}円
                      </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="table-label">HYPER料</td>
                  <td class="table-body __right">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.hyper }}円
                      </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td class="table-label">合計</td>
                  <td class="table-body __right">
                    <el-form-item>
                      <span style="margin-right: 1rem; color: #18181A;">
                        {{ amount.total }}円
                      </span>
                    </el-form-item>
                  </td>
                </tr>
              </table>
            </el-form>
          </el-col>
        </el-row>
      </el-card>
    </el-dialog>

  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    managingData: {
      type: Array,
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
      form: {
        brandName: '',
        brandCode: '',
        market: '',
        openTradeKbn: '',
        chargeIncludeTax: '',
        managementFee: '',
        rightsProcessingCharge: '',
        dailyInterest: '',
        reverseDailyInterest: '',
        hyper: '',
        total: ''
      },
      amount: {
        brandName: 'ダイセル',
        brandCode: '4202',
        market: '東証',
        openTradeKbn: '買建(6ヶ月)',
        chargeIncludeTax: '123,456,789,012',
        managementFee: '123,456,789,012',
        rightsProcessingCharge: '123,456,789,012',
        dailyInterest: '123,456,789,012',
        reverseDailyInterest: '123,456,789,012',
        hyper: '123,456,789,012',
        total: '123,456,689,012'
      }
    }
  },
  methods: {
    onDialogClose() {
      this.$emit('close-modal')
    }
  }
}
</script>

<style lang="scss" scoped>

@import "@/styles/withdrawal.scss";
// :deep(.el-dialog__header) {
//   padding: 0;
// }
:deep(.el-dialog__body) {
  padding: 15px;
}
.input-table {
  width:95%;
  margin: 10px;
  border-collapse: collapse;
  color: rgb(72,116,173);
  font: 11px/1.3 sans-serif;
  text-shadow:0 1px 0 #fff;
  border:1px solid #d8e8fa
}
.table-label {
  width:180px;
  font-size: 14px;
  font-weight: bold;
  color: #18181A;
  padding: 0 0.5rem;
  background-color: #dfdfdf;
  border: 1px solid #c5c5c5;
  text-shadow: none;
}
.table-body {
  border: 1px solid #c5c5c5;
  background-color: rgb(252, 252, 252);
  padding: 0.5rem 0;

}
</style>
