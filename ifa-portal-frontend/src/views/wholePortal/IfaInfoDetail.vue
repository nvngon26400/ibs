<template>
  <!-- ファイル選択ダイアログ -->
  <el-dialog
    :model-value="isVisible"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="backA004"
    :close-on-click-modal="false"
    class="info_detail_dialog"
    @open="onShow"
  >
    <el-form
      ref="form"
      :model="form"
      :inline="true"
    >
      <!-- 戻るボタン -->
      <el-row>
        <el-col :span="17">
          <page-caption
            :text="form.title? form.title: '-'"
            background-color="rgba(190, 190, 190, 0.35)"
            class="_page_caption"
          ></page-caption>
        </el-col>
        <el-col
          :span="7"
          style="text-align: right;"
        >
          <ifa-button
            text="戻る"
            color="secondary"
            class="cancel-button"
            action-type="originalAction"
            @app-action-handler="backA004"
          ></ifa-button>
        </el-col>
      </el-row>

      <div class="contents_area">
        <span v-html="formattedContents"></span>
      </div>

      <div class="file_label">
        ファイル
      </div>

      <el-row>
        <table class="_table__body">
          <colgroup>
            <col>
            <col style="width: 600px;">
            <col style="width: 50px;">
          </colgroup>
          <tbody>
            <tr v-if="urlTableDisplay">
              <td
                class="_table__data _break"
                :class="commentColDisplay? '': '_border-right_hidden'"
              ><span
                :class="form.url? '_link': ''"
                @click="linkDisplayA002"
              >{{ $_out(form.url) }}</span></td>
              <td
                class="_table__data"
                :class="commentColDisplay? '': '_border-left_hidden'"
              >{{ commentColDisplay? $_out(form.urlComment): '' }}</td>
              <td class="_table__data _center">-</td>
            </tr>

            <tr v-if="form.attachDocument1 || form.attachDocumentComment1">
              <td
                class="_table__data _break"
                :class="commentColDisplay? '': '_border-right_hidden'"
              >{{ $_out(form.attachDocument1) }}</td>
              <td
                class="_table__data"
                :class="commentColDisplay? '': '_border-left_hidden'"
              >{{ commentColDisplay? $_out(form.attachDocumentComment1): '' }}</td>
              <td class="_table__data _center">
                <span
                  :class="form.attachDocument1? '_link': ''"
                  @click="downloadA003(form.attachDocument1)"
                >{{ form.attachDocument1? 'DL': '-' }}</span></td>
            </tr>

            <tr v-if="form.attachDocument2 || form.attachDocumentComment2">
              <td
                class="_table__data _break"
                :class="commentColDisplay? '': '_border-right_hidden'"
              >{{ $_out(form.attachDocument2) }}</td>
              <td
                class="_table__data"
                :class="commentColDisplay? '': '_border-left_hidden'"
              >{{ commentColDisplay? $_out(form.attachDocumentComment2): '' }}</td>
              <td class="_table__data _center">
                <span
                  :class="form.attachDocument2? '_link': ''"
                  @click="downloadA003(form.attachDocument2)"
                >{{ form.attachDocument2? 'DL': '-' }}</span></td>
            </tr>

            <tr v-if="form.attachDocument3 || form.attachDocumentComment3">
              <td
                class="_table__data _break"
                :class="commentColDisplay? '': '_border-right_hidden'"
              >{{ $_out(form.attachDocument3) }}</td>
              <td
                class="_table__data"
                :class="commentColDisplay? '': '_border-left_hidden'"
              >{{ commentColDisplay? $_out(form.attachDocumentComment3): '' }}</td>
              <td class="_table__data _center">
                <span
                  :class="form.attachDocument3? '_link': ''"
                  @click="downloadA003(form.attachDocument3)"
                >{{ form.attachDocument3? 'DL': '-' }}</span></td>
            </tr>
          </tbody>
        </table>
      </el-row>

    </el-form>
    <ifa-requester
      id="ifaInfoDetailDownloadA003"
      action-id="SUB01_03#A003"
      action-type="outputPdfAction"
      :request-model="IfaInfoDetailA003RequestModel"
    ></ifa-requester>
    <!-- IfaLink (非表示) -->
    <ifa-link
      ref="ifaLink"
      :visible="false"
      :param-url="form.url"
      @url-changed="linkDisplayA002"
    ></ifa-link>
  </el-dialog>
</template>

<script>
import { IfaInfoDetailFormModel } from './js/IfaInfoDetailFormModel.js'
import { IfaInfoDetailA003RequestModel } from './js/IfaInfoDetailA003RequestModel.js'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
export default {
  components: {
    pageCaption
  },
  props: {
    isVisible: { type: Boolean, required: true },
    param: { type: Object, required: true }
  },
  emits: ['close-modal'],
  data() {
    return {
      processing: false,
      form: new IfaInfoDetailFormModel(),
      IfaInfoDetailA003RequestModel: new IfaInfoDetailA003RequestModel({}),
      formRef: {},
      selectedUrl: ''
    }
  },
  computed: {
    formattedContents() {
      return this.form.contents?.replace(/\\n/g, '<br>').replace(/\n/g, '<br>')
    },
    urlTableDisplay() {
      if (!this.form.url && !this.form.urlComment && !this.form.attachDocument1 && !this.form.attachDocumentComment1 &&
       !this.form.attachDocument2 && !this.form.attachDocumentComment2 &&
       !this.form.attachDocument3 && !this.form.attachDocumentComment3) {
        return true
      } else if (!this.form.url && !this.form.urlComment) {
        return false
      } else {
        return true
      }
    },
    commentColDisplay() {
      if (!this.form.urlComment && !this.form.attachDocumentComment1 && !this.form.attachDocumentComment2 && !this.form.attachDocumentComment3) {
        return false
      } else {
        return true
      }
    }
  },
  methods: {
    onShow() {
      this.form = Object.assign(this.form, this.param)
      this.formRef = this.$refs['form']
    },
    // リンク先表示
    linkDisplayA002() {
      if (this.processing) return // 連打防止
      this.processing = true
      if (this.form.url) {
        this.$nextTick(() => {
          this.$refs['ifaLink'].initializeRequestUrl()
          this.$refs['ifaLink'].openWindow()
        })
      }
      this.processing = false
    },
    // ダウンロード
    downloadA003(attachFile) {
      if (this.processing) return // 連打防止
      this.processing = true
      if (attachFile) {
        const obj = {
          disclaimer: this.form.disclaimer,
          fileDirectory: this.form.fileDirectory,
          attachFile: attachFile
        }
        this.IfaInfoDetailA003RequestModel = new IfaInfoDetailA003RequestModel(obj)
        this.$nextTick(() => {
          document.getElementById('ifaInfoDetailDownloadA003').click()
        })
      }
      this.processing = false
    },
    // 戻るボタン
    backA004() {
      // モーダルを閉じる
      this.$emit('close-modal')
    }
  }
}
</script>

<style lang="scss">
.info_detail_dialog {
  .el-dialog__header {
      padding: 0;
  }
}
</style>
<style lang="scss" scoped>
@import "@/styles/variables.scss";
.cancel-button :deep(.el-button) {
      margin-top: 0;
}
.contents_area {
  height: 500px;
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 10px;
  margin: 20px 0 20px 0;
  font-size: 20px;
}
.file_label {
  height: 30px;
  border: 1px solid #606266;
  padding: 6px;
  width: 80px;
  text-align: center;
  vertical-align: center;
  margin-bottom: 5px;
}
:deep(.el-dialog__header) {
   padding: 0px;
}
._page_caption {
  height: auto;
  padding-bottom: 3px;
  padding-top: 4px;
  word-break: break-all;
}
._link {
  color: blue;
  text-decoration: underline;
    &:focus, &:hover {
    color: blue;
    text-decoration: underline;
    cursor: pointer;
     opacity: 0.7;
    }
}
._break {
  word-break: break-all;
}
._center {
  text-align: center;
}
._border-right_hidden {
  border-right-width: 0px;
}
._border-left_hidden {
  border-left-width: 0px;
}
</style>
