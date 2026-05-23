<template>
  <el-dialog
    v-model="vmIsVisible"
    left
    :close-on-click-modal="false"
    :show-close="false"
    width="525px"
    @close="onCancelDialogClose"
  >
    <el-row>
      <el-col
        :offset="1"
        :span="22"
      >
        <page-caption
          :text="title"
          background-color="rgba(190, 190, 190, 0.35)"
          style="width:100%"
        ></page-caption>
      </el-col>
    </el-row>
    <el-row style="margin: 2rem 2rem 1.5rem 2rem">
      <el-col>
        <span v-html="message"></span>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :offset="1"
        :span="22"
      >
        <div class="form-button__wrapper">
          <ifa-button
            v-if="enableOk"
            text="OK"
            color="primary"
            small
            action-type="originalAction"
            @app-action-handler="onOkDialogClose"
          ></ifa-button>
          <ifa-button
            v-if="enableCancel"
            text="キャンセル"
            color="secondary"
            small
            action-type="originalAction"
            @app-action-handler="onCancelDialogClose"
          ></ifa-button>
        </div>
      </el-col>
    </el-row>
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
    isVisible: { type: Boolean, required: true },
    title: { type: String, required: true },
    message: { type: String, required: true },
    enableOk: { type: Boolean, default: true },
    enableCancel: { type: Boolean, default: true }
  },
  emits: ['close-modal-ok', 'close-modal-cancel', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  methods: {
    onOkDialogClose() {
      this.$emit('close-modal-ok')
    },
    onCancelDialogClose() {
      this.$emit('close-modal-cancel')
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
:deep(.el-dialog__header) {
   padding: 0;
}
</style>
