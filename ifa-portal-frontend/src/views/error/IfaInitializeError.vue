<template>
  <div class="error_caption">
    <screen-title
      v-show="title"
      :text="title"
    ></screen-title>
    <div class="error_message">
      <div
        v-for="(message, key) in messages"
        :key="key"
      >{{ message }}</div>
    </div>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
export default {
  name: 'IfaInitializeError',
  components: {
    screenTitle
  },
  props: {
    errorInfo: { type: [Object], required: true } // エラー情報
  },
  computed: {
    title() {
      return this.errorInfo.title
    },
    messages() {
      return this.errorInfo.message.split(/\n|<br\s*\/?>/ig)
    }
  }
}
</script>

<style lang="scss" scoped>
.error_caption {
  padding: 5px 15px 20px 15px;
}
.error_message {
  padding-top: 12px;
  padding-left: 8px;
  font-size: 16px;
}
</style>
