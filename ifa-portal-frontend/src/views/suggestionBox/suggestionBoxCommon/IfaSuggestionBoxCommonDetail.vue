<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :show-close="false"
      :center="true"
      :before-close="backA003"
      :close-on-click-modal="false"
      width="1050px"
      class="info_detail_dialog"
      @open="onShow"
    >
      <el-form
        ref="form"
        :model="form"
        :inline="true"
      >
        <el-row>
          <el-col :span="21">
            <page-caption
              :text="form.title? form.title: '-'"
              background-color="rgba(190, 190, 190, 0.35)"
              class="_page_caption"
            ></page-caption>
          </el-col>
          <el-col
            :span="3"
            style="text-align: right;"
          >
            <ifa-button
              text="戻る"
              color="secondary"
              class="cancel-button"
              action-type="originalAction"
              @app-action-handler="backA003"
            ></ifa-button>
          </el-col>
          <!-- 要望エリア -->
          <table class="suggestion-table">
            <!-- 登録日 -->
            <tr>
              <td class="table-label">登録日</td>
              <td
                class="_table_column"
                colspan="2"
              >{{ $_out(form.registerDate) }}</td>
            </tr>
            <!-- ステータス -->
            <tr>
              <td class="table-label">ステータス</td>
              <td
                class="_table_column"
                colspan="2"
              >{{ $_out($_getCodeValue('SUG_BOX_STATUS', 1, form.status)) }}</td>
            </tr>
            <!-- 要望内容 -->
            <tr>
              <td
                class="table-label"
                style="vertical-align:center;"
              >要望内容</td>
              <td
                class="_table_column"
                colspan="2"
                height="200px"
                style=" vertical-align:top; padding-right:0; width: 830px;"
              >
                <div style="height:200px; overflow-y: auto;">
                  <span v-html="formattedContents(form.suggestion)"></span>
                </div>
              </td>
            </tr>
            <!-- 添付ファイル -->
          </table>
          <table class="attachFile-table">
            <td v-if="displayAttachFile1"
                class="table-label"
                style="padding-top:5px;"
            >添付ファイル</td>
            <td v-if="displayAttachFile1"
                class="_table_column"
                width="780"
            >{{ form.attachFile1? $_out(form.attachFile1): '-' }}</td>
            <td v-if="displayAttachFile1"
                class="_table_data_center"
                width="50"
                style="border-left:none;"
            >
              <span
                :class="form.attachFile1? '_link': ''"
                @click="downloadA002(form.attachFile1)"
              >{{ form.attachFile1? 'DL': '-' }}</span>
            </td>
            <tr v-if="form.attachFile2">
              <td v-if="displayFirstAttachFile2"
                  class="table-label"
                  style="padding-top:5px;"
              >添付ファイル</td>
              <td v-else></td>
              <td
                class="_table_column"
                width="780"
              >{{ $_out(form.attachFile2) }}</td>
              <td class="_table_data_center"
                  width="50"
                  style="border-left:none;"
              >
                <span
                  :class="'_link'"
                  @click="downloadA002(form.attachFile2)"
                >DL</span></td>
            </tr>
            <tr v-if="form.attachFile3">
              <td v-if="displayFirstAttachFile3"
                  class="table-label"
                  style="padding-top:5px;"
              >添付ファイル</td>
              <td v-else></td>
              <td
                class="_table_column"
                width="780"
              >{{ $_out(form.attachFile3) }}</td>
              <td class="_table_data_center"
                  width="50"
                  style="border-left:none;"
              >
                <span
                  :class="'_link'"
                  @click="downloadA002(form.attachFile3)"
                >DL</span></td>
            </tr>
          </table>
          <!-- SBI証券からの回答 -->
          <table class="answer-table">
            <tr
              v-for="(item, index) in form.answerList"
              :key="index"
            >
              <!-- ラベル （最初の回答にのみ表示） -->
              <td class="table-label"
                  style="vertical-align:top; width:160px; padding-top:5px;"
              > {{ index == 0 ? 'SBI証券からの回答' :'' }}
              </td>
              <td class="_table_column"
                  colspan="2"
                  height="70"
                  style="vertical-align:top; width:830px;"
              >
                <!-- 回答内容 -->
                <span style="color:gray; font-size: 15px;">
                  {{ answerRegisterDateText(item) }}
                </span>
                <br>
                <span v-html="formattedContents(item.answerContents)">
                </span>
              </td>
            </tr>
          </table>
        </el-row>
      </el-form>
    </el-dialog>

    <!-- A002.ダウンロード リクエスタ -->
    <ifa-requester
      id="ifaSuggestionBoxCommonDetailDonwloadA002"
      action-id="SUB00_02-06_2#A002"
      action-type="outputPdfAction"
      :request-model="IfaSuggestionBoxCommonDetailA002RequestModel"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaSuggestionBoxCommonDetailA002RequestModel } from './js/IfaSuggestionBoxCommonDetailA002RequestModel'
import { IfaSugBoxCommonDetailFormModel } from './js/IfaSugBoxCommonDetailFormModel'
import pageCaption from './components/suggestionBoxPageCaption'

export default {
  components: {
    pageCaption
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    formData: {
      type: Object,
      required: true
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
      form: new IfaSugBoxCommonDetailFormModel(),
      IfaSuggestionBoxCommonDetailA002RequestModel: {},
      processing: false,
    }
  },
  computed: {
    displayAttachFile1() {
      if (!this.form.attachFile1 && !this.form.attachFile2 && !this.form.attachFile3) {
        return true
      } else if (!this.form.attachFile1) {
        return false
      } else {
        return true
      }
    },
    displayFirstAttachFile2() {
      if (!this.form.attachFile1 && this.form.attachFile2) {
        return true
      } else {
        return false
      }
    },
    displayFirstAttachFile3() {
      if (!this.form.attachFile1 && !this.form.attachFile2 && this.form.attachFile3) {
        return true
      } else {
        return false
      }
    }
  },
  watch: {
  },
  mounted() {
  },
  methods: {
    /* A001.初期化 */
    onShow() {
      this.form = Object.assign(this.form, this.formData)
    },
    /* A002.ダウンロード */
    downloadA002(attachFile) {
      if (this.processing) return // 連打防止
      this.processing = true
      if (attachFile) {
        // DL対象のファイル名をセットしてA003.ダウンロード.リクエスターを呼び出す
        this.IfaSuggestionBoxCommonDetailA002RequestModel = new IfaSuggestionBoxCommonDetailA002RequestModel({ attachFile: attachFile })
        this.$nextTick(() => {
          document.getElementById('ifaSuggestionBoxCommonDetailDonwloadA002').click()
        })
      }
      this.processing = false
    },
    // 戻るボタン
    backA003() {
      // モーダルを閉じる
      this.$emit('close-modal')
    },
    /* 回答登録日 算出処理 */
    answerRegisterDateText(item) {
      let registerDateText = ''
      if (item.answerNo !== null) {
        if (item.answerRegisterDate === item.answerUpdateDate) { // 回答一覧.回答登録日 = 回答一覧.回答更新日 の場合
          // 回答一覧.回答登録日(yyyy/MM/dd形式)+" SBI証券"
          registerDateText = this.$_getFormattedDateValue(item.answerRegisterDate) + ' SBI証券'
        } else { // 回答一覧.回答登録日≠回答一覧.回答更新日 の場合
          // 回答一覧.回答登録日(yyyy/MM/dd形式)+"（"+回答一覧.回答更新日(yyyy/MM/dd形式)＋"更新） SBI証券"
          registerDateText = this.$_getFormattedDateValue(item.answerRegisterDate) + '（' + this.$_getFormattedDateValue(item.answerUpdateDate) + '更新） SBI証券'
        }
      }
      return registerDateText
    },
    formattedContents(req) {
      return req?.replace(/\\n/g, '<br>').replace(/\n/g, '<br>')
    }
  }
}
</script>

<style lang="scss" scoped>
._link {
  color: blue;
  font-size: 16px;
  text-decoration: underline;
    &:focus, &:hover {
    color: blue;
    text-decoration: underline;
    cursor: pointer;
     opacity: 0.7;
    }
}
</style>
<style lang="scss">
.info_detail_dialog {
  .el-dialog__header {
      padding: 0;
  }
}
._page_caption {
  height: auto;
  width:880px;
  padding-bottom: 2px;
  padding-top: 2px;
  word-break: break-all;
  display: inline-block;
}
._table_column {
  vertical-align:top;
  font-size: 16px;
  padding: 5px;
  color: #18181A;
  border:1px solid #a7b1c3;
}
.table-label {
  width:160px;
  font-size: 14px;
  font-weight: bold;
  color: #18181A;
  padding: 0 1rem;
  text-shadow: none;
  text-align: left;
}
._table_data_center {
  text-align:center;
  width:50;
  border:1px solid #a7b1c3;
  vertical-align: middle;
  font-size: 16px;
}
.suggestion-table {
  padding-top: 15px;
  padding-bottom: 0px;
  border-spacing:0px 10px;
}
.attachFile-table {
  border-spacing:0px 0px;
  padding-bottom: 30px;
}
.answer-table {
  border-collapse: collapse;
}

</style>

