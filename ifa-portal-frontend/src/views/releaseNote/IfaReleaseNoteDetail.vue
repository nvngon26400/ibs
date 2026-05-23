<template>
  <div>
    <el-dialog
      ref="detailDialog"
      v-model="vmIsVisible"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      :destroy-on-close="true"
      style="overflow:hidden;"
      :style="{ '--el-dialog-width': releaseDialogWidth }"
      @open="onShow"
    >
      <el-row>
        <div style="width:97%; text-align: right; margin: 10px;">
          <ifa-button
            text="戻る"
            width="90"
            color="secondary"
            small
            style="padding-right: 0;"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </div>
      </el-row>
      <div
        v-if="form.fileType === '0'"
        class="image-center"
      >
        <el-image
          :src="filePath"
          fit="none"
          style="overflow: auto;"
          @load="handleImageLoad"
          @error="handleImageError"
        >
          <template #error>
            <div class="error-message">{{ errorMessage }}</div>
          </template>
        </el-image>
      </div>
      <div
        v-if="form.fileType === '1'"
        class="pdf-center"
        :style="setAspectRatio"
      >
        <div v-loading="isLoading"></div>
        <pdf
          v-for="i in numPages"
          :id="i"
          ref="pdfContainer"
          :key="i"
          :page="i"
          :src="pdfTask"
          style="margin-bottom: 5px;"
        >
        </pdf>
        <div
          v-if="errorMessage"
          class="error-message"
        >
          {{ errorMessage }}
        </div>
      </div>

    </el-dialog>
  </div>
  <ifa-requester
    id="ifaReleaseNoteDetailInitializeA001"
    action-id="SUB00-07_2#A001"
    action-type="requestAction"
    :request-model="a001RequestModel"
    :msg-title="form.screenTitle.name"
    @response-handler="initializeA001($event)"
  ></ifa-requester>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaReleaseNoteDetailFormModel } from './js/IfaReleaseNoteDetailFormModel'
import { IfaReleaseNoteDetailA001RequestModel } from './js/IfaReleaseNoteDetailA001RequestModel'
import { getMessage } from '@/utils/errorHandler'
import pdf from 'pdfvuer'

export default {
  components: {
    pdf
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    releaseNoteNo: {
      type: String,
      required: true
    }
  },
  emits: ['initializeError', 'close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaReleaseNoteDetailFormModel(),
      a001RequestModel: {},
      filePath: '',
      numPages: 0,
      releaseDialogWidth: '50%',
      setAspectRatio: {},
      errorMessage: '',
      pdfTask: '',
      isLoading: false,
      isLoggingAbort: false
    }
  },
  mounted() {
    window.addEventListener('unhandledrejection', this.handleAbortError)
  },
  beforeUnmount() {
    this.isLoggingAbort = false
    if (this.pdfTask) {
      this.pdfTask.then(proxy => {
        if (proxy && proxy._transport) {
          // 通信レイヤーを破棄して、進行中のリクエストを物理的に中断する
          proxy._transport.destroy()
          if (typeof proxy.destroy === 'function') {
            // PDFドキュメントのインスタンス自体も破棄する
            proxy.destroy()
          }
        }
      })
    }
    setTimeout(() => {
      window.removeEventListener('unhandledrejection', this.handleAbortError)
    }, 1000)
  },
  methods: {
    /* A001.初期化 */
    onShow() {
      this.a001RequestModel = new IfaReleaseNoteDetailA001RequestModel({ releaseNoteNo: this.releaseNoteNo })
      this.$nextTick(() => {
        document.getElementById('ifaReleaseNoteDetailInitializeA001').click()
      })
    },
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.filePath = '/releaseNote/'.concat(this.form.detailedFile)
      // PDFの場合
      if (this.form.fileType === '1') {
        const aspectRatioMap = {
          '0-0': { 'aspect-ratio': '9/16' }, // PDF向き:0 「縦」- 表示サイズ:0 16:9
          '0-1': { 'aspect-ratio': '3/4' }, // PDF向き:0 「縦」- 表示サイズ:1 4:3
          '1-0': { 'aspect-ratio': '16/9' }, // PDF向き:1 「横」- 表示サイズ:0 16:9
          '1-1': { 'aspect-ratio': '4/3' } // PDF向き:1 「横」- 表示サイズ:1 4:3
        }
        const key = `${this.form.pdfDirection}-${this.form.pdfSize}`
        if (key === '0-0') {
          this.releaseDialogWidth = '22%'
        } else if (key === '0-1') {
          this.releaseDialogWidth = '29%'
        }
        this.setAspectRatio = aspectRatioMap[key]
        this.$nextTick(() => {
          this.loadPdf(this.filePath)
        })
      }
    },
    /**
     * 画像のサイズに合わせてダイアログのサイズを設定します。
     */
    handleImageLoad(e) {
      this.$nextTick(() => {
        const img = e.target
        // 画像幅を取得する
        const imgWidth = img.naturalWidth
        // 画像幅と最大幅設定値を比較して、小さい方の値を表示幅として採用する
        const finalWidth = `${Math.min(imgWidth + this.form.DIALOG_PADDING_WIDTH, this.form.MAX_WIDTH)}px`
        // ダイアログ幅を調整する
        this.$refs.detailDialog.dialogContentRef.$el.style.width = finalWidth
      })
    },
    handleImageError() {
      this.errorMessage = getMessage('errors.cmn.file.notfound')
    },
    /**
     * PDFの総ページ数を取得します
     */
    loadPdf(filePath) {
      this.isLoading = true
      // 1. LoadingTask作成
      const loadingTask = pdf.createLoadingTask({
        url: filePath,
        disableAutoFetch: true,
        disableStream: true
      })
      // 2. タスクオブジェクトをsrcバインド用の変数に代入
      this.pdfTask = loadingTask
      // 3. 総ページ数の取得および例外処理
      loadingTask.then(pdf => {
        this.numPages = pdf.numPages
        this.isLoading = false
      }).catch(() => {
        this.isLoading = false
        this.errorMessage = getMessage('errors.cmn.file.notfound')
      })
    },
    /**
     * ダイアログを閉じる
     */
    onDialogClose() {
      this.$emit('close-modal')
    },
    handleAbortError(event) {
      // pdf.jsによる中断エラーであるかを確認
      if (event.reason && (event.reason.name === 'AbortError' || event.reason.message?.includes('aborted without reason'))) {
        // ブラウザのコンソールに赤文字のエラー（Uncaught Error）が表示されるのを防ぐ
        event.preventDefault()
        if (!this.isLoggingAbort) {
          console.log('「リリースノート詳細」画面のPDF通信はユーザー意図通りに中断されました。')
          this.isLoggingAbort = true
        }
      }
    }
  }
}
</script>
<style scoped>
:deep(.el-image__inner) {
    width: unset;
}
:deep(.el-dialog__header) {
  padding: 0;
}
:deep(.textLayer) {
  margin: 0 auto;
}
.pdf-center {
  overflow: auto;
  text-align: center;
  align-content: center;
}
.image-center{
  overflow: auto;
  max-height: 735px;
  text-align: center;
}
.error-message {
  color: red;
}
</style>
