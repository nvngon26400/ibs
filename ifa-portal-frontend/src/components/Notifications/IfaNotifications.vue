<template>
  <transition name="ifa-notification">
    <div
      v-if="notifications.length > 0"
      class="ifa-notification__wrapper"
      :style="{ zIndex: customZIndex }"
    >
      <div class="ifa-notification__header">
        <span class="ifa-notification__header--item">メッセージ</span>
        <span class="ifa-notification__header--close-button-wrapper">
          <button
            class="ifa-notification__header--close-button"
            @click="handleAllClear"
          >
            <el-icon><Close></Close></el-icon>
          </button>
        </span>
      </div>
      <div class="ifa-notification__container">
        <template
          v-for="notification of notifications"
          :key="notification.serialNumber"
        >
          <ifa-notification-item
            :ref="`ifa-notification-item-id-${notification.serialNumber}`"
            :serial-number="notification.serialNumber"
            :error-level="notification.errorLevel"
            :title="notification.title"
            :message="notification.message"
            :time-stamp="notification.timeStamp"
            :duration="notification.duration"
            @close-item="handleCloseItem"
          ></ifa-notification-item>
        </template>
      </div>
    </div>
  </transition>
</template>

<script>
import IfaNotificationItem from './components/IfaNotificationItem'
const DEFAULT_DURATION = 0
const ERROR_LEVEL_ERROR = -2
const ERROR_LEVEL_SUCCESS = 0
const ERROR_LEVEL_INFO = 1
const ERROR_LEVEL_WARNING = 2
export default {
  name: 'IfaNotifications',
  components: {
    IfaNotificationItem
  },
  data() {
    return {
      customZIndex: 2100, // 初期のz-index値
      observer: null // observerを格納
    }
  },
  computed: {
    notifications() {
      const notifications = [...this.$store.getters.notifications.notifications]
      return notifications.sort((a, b) => {
        const tsa = new Date(a.timeStamp)
        const tsb = new Date(b.timeStamp)
        if (tsa > tsb) return -1
        else if (tsa < tsb) return 1
        else return 0
      })
    }
  },
  watch: {
    // 新しい通知がある場合にz-indexを更新
    'notifications'(newVal) {
      if (newVal.length > 0) {
        this.updateZIndex()
      } else {
        // メモリリークを防ぐために、すべてのobserverを切断
        if (this.observer) {
          this.observer.disconnect()
        }
      }
    }
  },
  mounted() {
    // 通知のクリアタイミングの管理は IfaMenu などで行う｡コンポーネント生成時常に必要な場合はコメントアウト
    // this.$store.dispatch('notifications/resetState')

    // MutationObserverの作成
    this.observer = new MutationObserver(mutations => {
      let shouldUpdateZIndex = false
      // 各MutationRecordを処理
      mutations.forEach(mutation => {
        if (mutation.type === 'childList') {
          mutation.addedNodes.forEach(node => {
            // 新しく追加されたノードを確認
            if (node.nodeType === 1 && node.classList.contains('el-overlay')) {
              shouldUpdateZIndex = true // z-indexの更新が必要
            }
          })
        } else if (mutation.type === 'attributes' && mutation.target.classList.contains('el-overlay')) {
          // .el-overlayの属性が変更された場合
          shouldUpdateZIndex = true
        }
      })
      // z-indexを更新する必要がある場
      if (shouldUpdateZIndex) {
        this.updateZIndex()
      }
    })
    // document.body全体を監視
    this.observer.observe(document.body, {
      childList: true,
      subtree: true,
      attributes: true,
      attributeFilter: ['style'] // style属性に限定
    })

    // コンポーネントがマウントされたときにz-indexを更新
    if (this.notifications.length > 0) {
      this.updateZIndex()
    }
  },
  beforeUnmount() {
    // メモリリークを防ぐために、すべてのobserverを切断
    if (this.observer) {
      this.observer.disconnect()
    }
  },
  methods: {
    // @exposed
    // コンポーネントに登録されている全メッセージの削除を行う｡
    // Duration が指定されている場合にタイマの削除とStorage からの項目消去を行う
    reset() {
      Object.values(this.$refs).forEach(childElement => {
        if (Array.isArray(childElement) && childElement.length > 0) {
          childElement[0].close()
          this.removeItem(childElement[0].serialNumber)
        }
      })
    },
    // @exposed
    // コンポーネントに項目を追加する｡
    // 複数メッセージ(<sep>タグ)の場合は分割される｡ (変更管理 #3369)
    // エラーレベルがエラー、ワーニングの場合は×ボタンを押さないとポップアップを閉じない（時間経過により勝手に消えない） (変更管理 #3369)
    // 基本的に項目追加は errorHandler.js の notifyMessage() を通じて行うので本APIは使用されることはない
    addItem(item) {
      if (item && typeof item.message === 'string') {
        const messages = item.message.split(/<sep>/ig)
        messages.forEach(msg => {
          const errorLevel = [ERROR_LEVEL_SUCCESS, ERROR_LEVEL_INFO, ERROR_LEVEL_WARNING, ERROR_LEVEL_ERROR].includes(Number(item.errorLevel))
            ? Number(item.errorLevel)
            : ERROR_LEVEL_WARNING
          const duration = !item.duration || errorLevel === ERROR_LEVEL_ERROR || errorLevel === ERROR_LEVEL_WARNING ? DEFAULT_DURATION : item.duration
          this.$store.dispatch('notifications/addNotification', {
            errorLevel,
            title: item.title,
            message: msg,
            timeStamp: item.timeStamp || Date.now(),
            duration
          })
        })
      }
    },
    // @exposed
    // コンポーネントから項目を削除する
    removeItem(serialNumber) {
      this.$store.dispatch('notifications/removeNotification', serialNumber)
    },
    handleCloseItem(serialNumber) {
      this.removeItem(serialNumber)
    },
    handleAllClear() {
      this.reset()
    },
    updateZIndex() {
      const elementPlusDialogs = document.querySelectorAll('.el-overlay')
      let maxZIndex = 2000

      // 各ダイアログ要素のz-indexを取得し、最大値を見つける
      elementPlusDialogs.forEach(dialog => {
        // 要素が表示されている場合のみz-indexを考慮
        const display = dialog.style.display || 'block'
        if (display !== 'none' || display !== '') {
          const zIndex = parseInt(window.getComputedStyle(dialog).zIndex, 10)
          if (zIndex > maxZIndex) {
            maxZIndex = zIndex
          }
        }
      })
      // メッセージポップアップが常に最上位に表示されることを確認
      this.customZIndex = maxZIndex + 100
    }
  }
}
</script>

<style lang="scss" scoped>
.ifa-notification {
  &__wrapper {
    // 表示カスタマイズ: 表示位置: 上から
    // 基本的にコンポーネントを右上にポップアップ表示するため上からの相対座標を指定する｡
    --ifa-notification-position-top: 70px;
    // 表示カスタマイズ: 表示位置: 右から
    // 基本的にコンポーネントを右上にポップアップ表示するため右からの相対座標を指定する｡
    --ifa-notification-position-right: 30px;
    // 表示カスタマイズ: 表示位置: ポップアップの横幅
    --ifa-notification-width: 400px;
    // 表示カスタマイズ: 表示位置: ポップアップの高さ
    --ifa-notification-max-height: calc(100vh - 140px);
    position: fixed;
    top: var(--ifa-notification-position-top);
    right: var(--ifa-notification-position-right);
    width: var(--ifa-notification-width);
    padding: 4px;
    background-color: white;
    border: 2px solid gray;
    border-radius: 8px;
  }
  &__header {
    display: flex;
    height: 2rem;
    line-height: 2rem;
    background-color: lightgray;
    border-radius: 4px;
    &--item {
      display: block;
      margin-left: 1rem;
      color: black;
      font-size: 1.5rem;
    }
    &--close-button-wrapper {
      margin: 2px 0.5rem 0 auto;
      :hover {
        cursor: pointer;
      }
    }
    &--close-button {
      height: 1.3em;
      width: 1.4em;
      padding-left: 3px;
      padding-top: 3px;
      font-size: 1.5em;
      font-weight: 700;
      color: black;
      background-color: white;
      border: 1px solid black;
      border-radius: 4px;
    }
  }
  &__container {
    display: flex;
    flex-flow: column;
    max-height: var(--ifa-notification-max-height);
    overflow-y: scroll;
  }
  // アニメーション
  &-enter-active,
  &-leave-active {
    transition: all 0.5s ease-out;
  }
  &-enter-from,
  &-leave-to {
    transform: translateX(var(--ifa-notification-width));
  }
}
</style>
