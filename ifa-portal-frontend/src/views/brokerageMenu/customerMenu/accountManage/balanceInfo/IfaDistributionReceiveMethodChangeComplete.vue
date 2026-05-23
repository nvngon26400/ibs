<template>
  <div>
    <!-- 分配金受取方法変更受付ダイアログ -->
    <el-dialog
      v-model="vmIsVisible"
      class="custom-dialog"
      style="width: 810px;"
      :title="form.title.name"
      :center="true"
      :show-close="false"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
    >

      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <span class="__message">{{ form.infoMsg }}</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <span class="__title">投資信託(金額)：</span>
        </el-col>
      </el-row>

      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <table class="_table__horizontal _table__body">
            <thead>
              <tr>
                <th class="_table__header __center"
                    style="width: 230px; min-width: 230px; word-wrap: break-all"
                >ファンド名</th>
                <th class="_table__header __center"
                    style="width: 195px; min-width: 195px; word-wrap: break-all"
                >保有口数<br>(売却注文中)</th>
                <th class="_table__header __center"
                    style="width: 110px; min-width: 110px; word-wrap: break-all"
                >変更前分配金<br>受取方法</th>
                <th class="_table__header __center"
                    style="width: 110px; min-width: 110px; word-wrap: break-all"
                >変更後分配金<br>受取方法</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="_table__data __left">{{ $_out(form.fundName) }}</td>
                <td class="_table__data __right">{{ $_out(ifaFormatUtils.withCommaInteger(form.unitVolume)) }}口<br>({{ $_out(ifaFormatUtils.withCommaInteger(form.sellingVolume)) }}口)</td>
                <td class="_table__data __center">{{ $_out(form.method) }}</td>
                <td class="_table__data __center">{{ $_out(form.afterChangeDistributionReceiveMethod) }}</td>
              </tr>
            </tbody>
          </table>
        </el-col>
      </el-row>

      <!-- 保有商品一覧ボタン -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="margin-top: 24px"
        >
          <ifa-button
            text="保有商品一覧へ"
            style="padding-left: 0;"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { IfaDistributionReceiveMethodChangeCompleteFormModel } from './js/IfaDistributionReceiveMethodChangeCompleteFormModel'
import { useVModel } from 'vue-composable'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'

export default {
  props: {
    isVisible: { type: Boolean, required: true },
    dialogData: { type: Object, required: false, default: null }
  },
  emits: ['close', 'update:isVisible', 'display-update'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaDistributionReceiveMethodChangeCompleteFormModel(),
      ifaFormatUtils: ifaFormatUtils
    }
  },

  methods: {
    onShow() {
      Object.assign(this.form, this.dialogData)
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close')
      this.$emit('display-update')
    }
  }
}
</script>

<style lang="scss" scoped>
.__title {
  font-weight: 700;
  font-size: 16px;
  line-height: 4rem;
}
.__message {
  font-size: 16px;
}
</style>
