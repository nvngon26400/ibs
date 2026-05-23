<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :close-on-click-modal="false"
      :show-close="false"
      :before-close="onDialogClose"
      @open="onOpen"
    >
      <!-- 戻るボタン -->
      <el-row>
        <el-col
          :span="23"
          class="form-button__wrapper"
          style="margin-right: 0px;"
        >
          <ifa-button
            color="secondary"
            text="戻る"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <!-- BB申込(一括登録) メッセージ一覧  -->
      <caption-card
        style="margin: 1rem 0 1rem 0;"
        :caption="formModel.title.name"
        text-size="20px"
        text-color="#0058a2"
        background-color="Menu"
      >
        <el-row>
          <ul
            v-for="(value, key, index) in msgList"
            :key="index"
            class="message_list"
          >
            <el-alert
              type="info"
              :closable="false"
              effect="dark"
            >
              <template #title>
                <span class="alert_title"
                      v-html="value.msg"
                ></span>
              </template>
            </el-alert>
          </ul>
        </el-row>
      </caption-card>
    </el-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard'
import { IfaBbApplyCsvMassRegisterMsgListFormModel } from './js/IfaBbApplyCsvMassRegisterMsgListFormModel'
export default {
  components: {
    captionCard
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    msgList: {
      type: Array,
      required: false,
      default: null
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
      formModel: new IfaBbApplyCsvMassRegisterMsgListFormModel()
    }
  },
  methods: {
    onOpen() {
    },
    onDialogClose() {
      this.$emit('close-modal')
    }
  }

}
</script>

<style lang="scss" scoped>
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -30px 0.5rem 0 auto;
}
:deep(.el-dialog) {
  top: 60px !important;
  margin-top: 0.5vh !important;
  padding-top: 0.5rem;
  width: 960px;
}
:deep(.el-dialog__header) {
  padding: 0;
}
.alert_title {
  line-height: 1.5;
  font-weight: normal;
}
.message_list {
  padding: 0px 10px;
  width: 100%;
}
:deep(.el-alert) {
  --el-alert-title-font-size: 15px;
}
:deep(.el-alert--info.is-dark) {
  color: black;
}
:deep(.el-alert--info.is-dark) {
  background-color: #D9D9D9;
}
</style>
