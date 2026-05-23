<template>
  <!-- 書類請求詳細ダイアログ SUB0202_0704-04 -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    style="background-color: #fef0f0;"
  >
    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          id="btnBack"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          style="padding-right: 0;"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 1.5rem;"></div>
    </el-row>

    <!-- 書類請求内容 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>書類請求内容</span>
          </el-row>
          <hr>
          <!-- 分類 -->
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>分類</span>
            </el-col>
            <el-col
              :span="16"
              class="content-wrapper"
            >
              <span>{{ $_out(form.shoruiBunruiMei) }}</span>
            </el-col>
          </el-row>
          <!-- 書類名 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>書類名</span>
            </el-col>
            <el-col
              :span="16"
              class="content-wrapper"
            >
              <span>{{ $_out(form.shoruimei) }}</span>
            </el-col>
          </el-row>
          <div v-if="form.bmKoufuShubetsu === '0'">
            <!-- 部数 -->
            <el-row class="dotted_border">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>部数</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.busuu) }}</span>
              </el-col>
            </el-row>
          </div>
          <!-- 交付区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>交付区分</span>
            </el-col>
            <el-col
              :span="16"
              class="content-wrapper"
            >
              <span>{{ $_out(form.hassouStsName) }}</span>
            </el-col>
          </el-row>
          <div v-if="form.bmKoufuShubetsu === '0'">
            <!-- 内容 -->
            <el-row v-if="form.naiyouCaption"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.naiyouCaption) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.naiyou) }}</span>
              </el-col>
            </el-row>
            <!-- 備考 -->
            <el-row v-if="form.bikouCaption"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.bikouCaption) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.bikou) }}</span>
              </el-col>
            </el-row>
            <!-- オプション1 -->
            <el-row v-if="form.option1"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.option1) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.sentakuMei1) }}</span>
              </el-col>
            </el-row>
            <!-- オプション2 -->
            <el-row v-if="form.option2"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.option2) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.sentakuMei2) }}</span>
              </el-col>
            </el-row>
            <!-- オプション3 -->
            <el-row v-if="form.option3"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.option3) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.sentakuMei3) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト1 -->
            <el-row v-if="form.txt1"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt1) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou1) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト2 -->
            <el-row v-if="form.txt2"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt2) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou2) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト3 -->
            <el-row v-if="form.txt3"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt3) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou3) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト4 -->
            <el-row v-if="form.txt4"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt4) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou4) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト5 -->
            <el-row v-if="form.txt5"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt5) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou5) }}</span>
              </el-col>
            </el-row>
          </div>
          <div v-if="form.bmKoufuShubetsu === '1'">
            <!-- 投信銘柄コード -->
            <el-row class="dotted_border">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>投信銘柄コード</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.meigaraCd) }}</span>
              </el-col>
            </el-row>
            <!-- 投信銘柄名 -->
            <el-row class="dotted_border">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>投信銘柄名</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.meigaraMei) }}</span>
              </el-col>
            </el-row>
          </div>
          <div v-if="form.bmKoufuShubetsu !== '0'">
            <!-- 配信予定日時 -->
            <el-row class="dotted_border">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>配信予定日時</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out($_getFormattedDateTimeValue(form.bmDeliveryTimeSchedule,'datetime12')) }}</span>
              </el-col>
            </el-row>
          </div>
          <!-- 取扱者 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取扱者</span>
            </el-col>
            <el-col
              :span="16"
              class="content-wrapper"
            >
              <span>{{ $_out(form.userNm) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaDocRequestDetailFormModel } from './js/IfaDocRequestDetailFormModel'
export default {
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true }
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
      form: new IfaDocRequestDetailFormModel()
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)
    },
    onDialogClose() {
      this.$emit('close-modal')
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
.content-wrapper {
  font-size: 16px;
  overflow-wrap: anywhere;
  white-space: break-spaces;
}
</style>
