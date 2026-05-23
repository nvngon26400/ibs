<template>
  <transition
    name="ifa-notification-item"
    @after-leave="handleAfterLeave"
  >
    <div
      v-if="isVisible"
      class="ifa-notification-item__wrapper"
      :class="{
        'info': isSuccess || isInfo,
        'warning': isWarning,
        'error': isError
      }"
    >
      <div class="ifa-notification-item__header">
        <span class="ifa-notification-item__header--icon">
          <el-icon size="1.4rem"><WarningFilled></WarningFilled></el-icon>
        </span>
        <span class="ifa-notification-item__header--title">
          <span>{{ title }}</span>
        </span>
        <span class="ifa-notification-item__header--close-button-wrapper">
          <button
            class="ifa-notification-item__header--close-button"
            @click="handleClose"
          >
            <el-icon><Close></Close></el-icon>
          </button>
        </span>
      </div>
      <div class="ifa-notification-item__content">
        <div
          v-for="(msg, key) in messages"
          :key="key"
        >{{ msg }}</div>
      </div>
      <div class="ifa-notification-item__footer">
        <span>{{ formatDateTime(timeStamp) }}</span>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'IfaNotificationItem',
  props: {
    serialNumber: { type: [Number, String], required: true }, // // シリアル番号 (通知毎にユニークな値を割り振る) ※基本的に自動で割り振られるので指定不用
    errorLevel: { type: [Number, String], required: false, default: 2 }, // エラーレベル: 0: success, 1: info, 2: warning (default), -1: error
    title: { type: String, required: true }, // 見出し
    message: { type: String, required: true }, // 内容
    timeStamp: { type: [Number, String, Date], required: false, default: Date.now() }, // 発生日時 (デフォルト値: PCから取得したローカル日時(日本時間))
    duration: { type: [Number, String], required: false, default: 0 } // 表示期間 (デフォルト値: 0 (期間指定なし))
  },
  emits: ['close-item'],
  data() {
    return {
      isVisible: false,
      timeoutId: null
    }
  },
  computed: {
    // テーマ配色: SUCCESS (INFOと同じ)
    isSuccess() {
      return Number(this.errorLevel) === 0
    },
    // テーマ配色: INFO
    isInfo() {
      return Number(this.errorLevel) === 1
    },
    // テーマ配色: WORNING
    isWarning() {
      return Number(this.errorLevel) === 2
    },
    // テーマ配色: ERROR
    isError() {
      return Number(this.errorLevel) === -1
    },
    messages() {
      return this.message.split(/\n|<br\s*\/?>/ig)
    }
  },
  mounted() {
    // 期間が指定されていたらタイマをセットして自動消去を行う
    this.isVisible = true
    const duration = Number(this.duration)
    if (duration > 0) {
      this.$nextTick(() => {
        this.setAutoClose(duration)
      })
    }
  },
  methods: {
    // 項目を削除する
    close() {
      if (this.timeoutId) {
        clearTimeout(this.timeoutId)
        this.timeoutId = null
      }
      this.isVisible = false
    },
    handleClose() {
      this.close()
    },
    handleAfterLeave() {
      this.$emit('close-item', this.serialNumber)
    },
    formatDateTime(ts) {
      const date = new Date(ts)
      const dateString = date.toLocaleDateString('ja-JP', { year: 'numeric', month: '2-digit', day: '2-digit' })
      const timeString = date.toLocaleTimeString('ja-Jp')
      // eslint-disable-next-line eqeqeq
      if (dateString == 'Invalid Date' || timeString == 'Invalid Date') {
        return this.formatDateTime(Date.now())
      }
      return `${dateString} ${timeString}`
    },
    setAutoClose(duration) {
      this.timeoutId = setTimeout(() => {
        this.handleClose()
      }, duration)
    }
  }
}
</script>

<style lang="scss" scoped>
.ifa-notification-item {
  &__wrapper {
    display: block;
    background-color: white;
    border: solid 2px var(--wrapper-border-color);
    border-radius: 4px;
    // テーマ配色: SUCCESS (未使用)
    &.success {
      --wrapper-border-color: rgb(158, 255, 158);
      --header-background-color: rgb(209, 255, 209);
      --header-icon-color: rgb(70, 255, 200);
      --header-icon-background-color: rgb(255, 255, 255);
      --header-icon-border-color: rgb(12, 192, 115);
    }
    // テーマ配色: INFO (SUCCESS もこのテーマで表示する)
    &.info {
      --wrapper-border-color: rgb(101, 189, 255);
      --header-background-color: rgb(155, 213, 255);
      --header-icon-color: rgb(70, 200, 255);
      --header-icon-background-color: rgb(255, 255, 255);
      --header-icon-border-color: rgb(12, 115, 192);
    }
    // テーマ配色: WARNING
    &.warning {
      --wrapper-border-color: rgb(255, 202, 105);
      --header-background-color: rgb(255, 252, 214);
      --header-icon-color: rgb(255, 193, 164);
      --header-icon-background-color: rgb(255, 255, 255);
      --header-icon-border-color: rgb(255, 134, 47);
    }
    // テーマ配色: ERROR
    &.error {
      --wrapper-border-color: rgb(255, 158, 158);
      --header-background-color: rgb(255, 215, 213);
      --header-icon-color: rgb(255, 165, 199);
      --header-icon-background-color: rgb(255, 255, 255);
      --header-icon-border-color: rgb(235, 65, 63);
    }
  }
  &__header {
    display: flex;
    line-height: 1.5rem;
    background-color: var(--header-background-color);
    padding-left: 0.5rem;
    &--icon {
      display: flex;
      height: 1.4rem;
      width: 1.4rem;
      line-height: 1.4rem;
      justify-content: center;
      align-items: center;
      position: relative;
      background-color: var(--header-icon-background-color);
      color: var(--header-icon-color);
      border: 2px solid var(--header-icon-border-color);
      border-radius: 50%;
    }
    &--title {
      display: flex;
      font-size: 16px;
      color: black;
      font-weight: 700;
      & > span {
        margin-left: 0.5rem;
      }
    }
    &--close-button-wrapper {
      margin: 1.5px 0.5em 0 auto;
      :hover {
        cursor: pointer;
      }
    }
    &--close-button {
      height: 1.6em;
      width: 1.6em;
      padding-left: 3px;
      padding-top: 3px;
      font-size: 0.9em;
      font-weight: 700;
      color: white;
      background-color: black;
      border-radius: 6px;
    }
  }
  &__content {
    display: flex;
    flex-flow: column;
    font-size: 16px;
    color: black;
    padding: 6px;
    background-color: white;
  }
  &__footer {
    display: flex;
    justify-content: flex-end;
    font-size: 12px;
    color: black;
    padding-right: 1em;
    background-color: white;
  }
  // アニメーション
  &-enter-active {
    transition: all 0.5s ease-out;
  }
  &-leave-active {
    transition: all 0.2s ease-out;
  }
  &-enter-from, &-leave-to {
    opacity: 0;
  }
}
</style>
