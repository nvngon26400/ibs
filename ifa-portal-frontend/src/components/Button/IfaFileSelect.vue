<template>
  <div class="ifa-file-select__wrapper">
    <el-upload
      v-if="ctrlVisible"
      :id="id"
      ref="upload"
      action=""
      :file-list="fileList"
      :show-file-list="showFileList"
      :accept="accept"
      :auto-upload="false"
      :disabled="isDisabled"
      :on-change="handleChange"
      :before-remove="handleRemove"
    >
      <template #trigger>
        <ifa-button
          :text="text"
          :color="color"
          :icon="icon"
          :disabled="isDisabled"
          :width="width"
          :small="small"
          action-type="originalAction"
        ></ifa-button>
      </template>
    </el-upload>
  </div>
</template>

<script>
import { controlAuthorization } from '@/utils/controlAuth'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  name: 'IfaFileSelect',
  props: {
    id: { type: String, required: false, default: null }, // ID
    text: { required: true, type: String }, // ボタン文字列
    color: { required: false, type: String, default: '' }, // ボタン色
    icon: { required: false, type: String, default: null }, // アイコン
    disabled: { required: false, type: Boolean, default: false }, // 非活性
    width: { required: false, type: String, default: '0' }, // ボタン幅
    small: { required: false, type: Boolean, default: false }, // スモールスタイル
    msgTitle: { type: String, required: false, default: '' }, // メッセージダイアログのタイトル
    actionId: { type: String, required: false, default: null }, // アクションID
    showFileList: { type: Boolean, required: false, default: false }, // 選択したファイルを表示
    accept: { type: String, required: false, default: null },
    controlAuthEnabled: { type: Boolean, required: false, default: true }, // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
    customMaxFileSize: { required: false, type: Number, default: 100 }, // 最大アップロードサイズ(MB)
    customErrorMessage: { required: false, type: String, default: '' } // 最大アップロードサイズを超過した場合のエラーメッセージ
  },
  emits: ['change', 'update:modelValue', 'before-remove'],
  data() {
    return {
      fileList: [],
      ctrlVisible: true, // 認可制御(表示/非表示)
      ctrlDisabled: false, // 認可制御(編集不可)
      ctrlReadonly: false // 認可制御(読取専用)
    }
  },
  computed: {
    title() {
      if (this.msgTitle) {
        return this.msgTitle
      } else {
        // メッセージダイアログのタイトルをIfaRouterの情報から設定
        return this.$store.getters.pageInfo?.label ?? ''
      }
    },
    isDisabled() {
      return this.disabled || this.ctrlDisabled || this.ctrlReadonly
    }
  },
  mounted() {
    if (this.controlAuthEnabled) {
      controlAuthorization.bind(this)() // 認可制御
    }
  },
  methods: {
    /**
     * ファイル未選択状態にする
     * @param なし
     * @returns なし
     */
    clearFiles() {
      this.$refs['upload'].clearFiles()
    },

    handleChange(file, fileList) {
      const maxFileSize = this.customMaxFileSize * 1024 * 1024 // ファイルのサイズ（MB）
      if (file.size > maxFileSize) {
        this.fileList = []
        file = null
        if (this.customErrorMessage) {
          notifyMessage(-1, this.customErrorMessage, this.title, new Date().toLocaleString('ja-JP'))
        } else {
          notifyMessage(-1, `ファイルのサイズが${this.customMaxFileSize}MBを越えています。`, this.title, new Date().toLocaleString('ja-JP'))
        }
      } else {
        // 最後に選択された1件だけ保持する
        this.fileList = [file]
      }

      this.$emit('change', file)
    },
    handleRemove() {
      this.$emit('before-remove')
    }
  }
}
</script>

<style lang="scss" scoped>
.ifa-file-select__wrapper {
  display: flex;
  justify-items: start;
  max-width: 600px;
}
</style>
