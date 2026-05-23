<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :close-on-click-modal="false"
      :show-close="false"
      width="960px"
      @open="onOpen"
    >
      <el-row>
        <el-col
          :span="23"
          class="form-button__wrapper"
        >
          <ifa-button
            color="secondary"
            text="戻る"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <caption-card
        style="margin: 1rem 0;"
        :caption="formModel.title.name"
        text-size="20px"
        text-color="#0058a2"
        background-color="Menu"
      >
        <el-row>
          <ul
            v-for="(item, index) in msgList"
            :key="index"
            class="message_list"
          >
            <el-alert
              type="info"
              :closable="false"
              effect="dark"
            >
              <template #title>
                <span
                  class="alert_title"
                  v-html="item.msg"
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
import { IfaEdelivAgreementDataRegisterMsgListFormModel } from './js/IfaEdelivAgreementDataRegisterMsgListFormModel'

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
      default: () => []
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
      formModel: new IfaEdelivAgreementDataRegisterMsgListFormModel()
    }
  },
  methods: {
    onOpen() {},
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
}
:deep(.el-dialog__header) {
  padding: 0;
}
.alert_title {
  line-height: 1.5;
  font-weight: normal;
}
.message_list {
  padding: 0 10px;
  width: 100%;
  list-style: none;
}
:deep(.el-alert--info.is-dark) {
  color: black;
  background-color: #D9D9D9;
}
</style>
