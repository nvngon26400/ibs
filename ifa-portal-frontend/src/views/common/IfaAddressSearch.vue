<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      width="1200px"
      :center="true"
      :title="form.title"
      :show-close="false"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      @open="onOpen"
    >
      <!-- 戻るボタン -->
      <el-row>
        <el-col
          :span="23"
          style="text-align: right"
        >
          <ifa-button
            text="戻る"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <el-row style="margin: 10px 0px 5px 20px">
        <el-col
          :span="23"
          :offset="1"
        >
          <span style="font-weight: bold;">検索郵便番号:</span><span style="margin-left: 5px;font-weight: bold;">{{ addressCode }}</span>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-table :data="tableData"
                    :show-header="false"
                    style="border: 1px solid rgb(215, 215, 215);"
                    @row-click="handleClick"
          >
            <el-table-column prop="address"
                             label="Address"
            >
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </el-dialog>
    <ifa-requester
      id="ifaAddressSearch"
      action-id="SUB07-01#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="handlerSelectAddress($event)"
      @response-error-handler="responseErrorHandler($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaAddressSearchFormModel } from './js/IfaAddressSearchFormModel.js'
import { IfaAddressSearchA001RequestModel } from './js/IfaAddressSearchA001RequestModel.js'

export default {
  props: {
    formData: { type: Object, required: true },
    isVisible: { type: Boolean, required: true }
  },
  emits: ['close-modal', 'result', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      tableData: [],
      form: new IfaAddressSearchFormModel()
    }
  },
  computed: {
    addressCode() {
      if (this.formData.yuusouNumber && this.formData.yuusouNumber.length >= 7) {
        return this.formData.yuusouNumber.slice(0, 3) + '-' + this.formData.yuusouNumber.slice(3, 7)
      }
      return this.formData.yuusouNumber
    },
    A001RequestModel() {
      return new IfaAddressSearchA001RequestModel(this.form)
    }
  },
  methods: {
    onOpen() {
      this.tableData = []
      Object.assign(this.form, this.formData)
      this.$nextTick(() => {
        document.getElementById('ifaAddressSearch').click()
      })
    },
    handlerSelectAddress(res) {
      this.tableData = res.dataList[0]?.addressList
    },
    responseErrorHandler() {
      this.tableData = []
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    handleClick(row) {
      this.onDialogClose()
      this.$emit('result', row)
    }
  }
}

</script>

<style lang="scss" scoped>
:deep(.el-table__row:hover) {
  cursor: pointer;
}
</style>
