<template>
  <div class="form-area__section">
    <el-row>
      <el-col :span="20">
        <ifa-input-select
          v-model="form.kanyuKbn"
          label="勧誘区分"
          prop="kanyuKbn"
          required
          label-class="label-item-class"
          code-list-id="INVITATION_TYPE"
          :disp-pattern="1"
          :select-pattern="1"
        ></ifa-input-select>
      </el-col>
    </el-row>
  </div>

  <div class="form-area__section">
    <el-row>
      <el-col :span="20">
        <ifa-input-select
          v-model="form.receiveMethod"
          label="受付方法"
          code-list-id="ORDER_METHOD_TYPE"
          required
          label-class="label-item-class"
          prop="receiveMethod"
          :disp-pattern="1"
          :select-pattern="1"
        ></ifa-input-select>
      </el-col>
    </el-row>
  </div>

  <div class="form-area__section">
    <el-row>
      <el-col
        :span="24"
        style="font-weight: 700;"
      >
        <el-form-item
          label="ご注意事項"
          prop="checkMadoAki"
          class="checkbox-label-class"
          required
          :rules="[
            {
              validator: (_rule, value, callback) => {
                if(value) {
                  callback()
                } else {
                  callback('ご注意事項を選択してください。')
                }
              },
              trigger: 'change'
            }
          ]"
        >
          <el-checkbox
            v-model="form.checkMadoAki"
            label="以下記載の内容を全て説明済みである。"
          ></el-checkbox>
        </el-form-item>

      </el-col>
    </el-row>
    <el-row>
      <el-form-item label=" ">
        <ifa-mutual-fund-accumulate-input-check-text></ifa-mutual-fund-accumulate-input-check-text>
      </el-form-item>
    </el-row>
  </div>
</template>

<script>
import IfaInputSelect from '@/components/Input/IfaInputSelect.vue'
import IfaMutualFundAccumulateInputCheckText from '../IfaMutualFundAccumulateInputCheckText.vue'

export default {
  components: {
    IfaInputSelect,
    IfaMutualFundAccumulateInputCheckText
  },
  props: {
    form: { type: Object, required: true }
  }
}
</script>

<style lang="scss" scoped>
.form-area__section {
  height: auto;
  white-space: pre-wrap;
}

:deep(.el-checkbox) {
  margin-right: 5px;
}

:deep(.el-checkbox__label) {
  padding-left: 2px;
}

:deep(.el-form.el-form--label-left .el-form-item.asterisk-left .el-form-item__label) {
  align-self: flex-start;
}

:deep(.label-item-class) .el-form-item__label {
  align-self: baseline !important;
}

:deep(.checkbox-label-class) {
  .el-form-item__label {
    align-self: baseline !important;
    font-weight: 700;
  }
  .el-form-item__content {
    align-self: baseline !important;
    font-weight: normal;
  }
}
</style>
