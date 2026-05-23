<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :close-on-click-modal="false"
      :show-close="false"
      width="1444px"
      :before-close="onDialogClose"
      title="印影照会"
      :center="true"
      @opened="balloonKey = true"
      @close="balloonKey = false"
    >

      <!-- 印影照会 -->
      <!-- 戻るボタン -->
      <el-row style="padding-right: 30px;">
        <el-col style="text-align: right;">
          <el-icon
            v-if="!balloonKey"
            class="el-icon-info"
            style="font-size: 30px; vertical-align: middle; margin-right: 20px;"
          ><infoFilled></infoFilled></el-icon>
          <ifa-balloon-icon
            v-if="balloonKey"
            icon-type="info"
            message="印影は縮小表示のため、顧客伝達時は注意"
            width="300"
            style="vertical-align: middle; margin-right: 20px;"
          >
          </ifa-balloon-icon>
          <ifa-button
            color="secondary"
            text="閉じる"
            style="padding-right: 0px;"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <div v-if="!form.code"
           style="padding-left: 30px; padding-right: 30px;"
      >
        <!-- 顧客情報エリア -->
        <el-row style="margin-top: 10px;">
          <el-col
            :span="24"
            style="display: flex; align-items: flex-start;"
          >
            <div
              style=" display: inline-block;  width: 370px;"
              class="col-item"
            >
              <span class="info-item__header __left">部店コード：</span>
              <span class="info-item__value __left">{{ $_out(form.butenCode) }}</span>
            </div>
            <div
              style=" display: inline-block; margin-left: 20px; width: 370px;"
            >
              <div class="col-item">
                <span class="info-item__header __left">口座番号：</span>
                <span class="info-item__value __left">{{ $_out(form.accountNumber) }}</span>
              </div>
            </div>
          </el-col>
        </el-row>

        <el-row>
          <el-col
            :span="24"
            style="display: flex; align-items: stretch;"
          >
            <div
              style=" display: inline-block;  min-Width: 370px; flex: 0 1 auto;"
              class="col-item"
            >
              <span class="info-item__header __left">顧客名：</span>
              <span class="info-item__value __left">{{ $_out(form.customerNameKanji) }}</span>
            </div>
            <div
              style=" display: inline-block; margin-left: 20px; min-width: 370px; flex: 0 1 auto;"
              class="col-item"
            >
              <span class="info-item__header __left">顧客カナ：</span>
              <span class="info-item__value __left">{{ $_out(form.customerNameKana) }}</span>
            </div>
          </el-col>
        </el-row>

        <el-row>
          <el-col
            :span="24"
            style="display: flex; align-items: stretch;"
          >
            <div
              style=" display: inline-block;  min-Width: 370px; flex: 0 1 auto;"
              class="col-item"
            >
              <span class="info-item__header __left">ID-書類名：</span>
              <span class="info-item__value __left">{{ $_out(form.ledgerCode) + '-' + $_out(form.ledgerName) }}</span>
            </div>
            <div
              style=" display: inline-block; margin-left: 20px; min-width: 370px; flex: 0 1 auto;"
              class="col-item"
            >
              <span class="info-item__header __left">受入基準日：</span>
              <span class="info-item__value __left">{{ $_out(form.acceptStandardDate) }}</span>
            </div>
          </el-col>
        </el-row>
        <div><hr style="border-width: 0px; background-color: #ddd; height: 3px; margin-top: 10px;"></div>
        <el-row>
          <el-col>
            <div style="text-align: center; position: relative; overflow-x: scroll;"
                 oncontextmenu="return false;"
            >
              <div
                style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background-color: transparent; pointer-events: none; user-select: none;"
              ></div>
              <img
                id="imprintImg"
                :src="'data:image/png;base64,' + form.ledgerFile"
                :style="{
                  width: form.width,
                  height: form.height,
                  'object-fit': 'contain'
                }"
                onselectstart="return false;"
                onmousedown="return false;"
              >
            </div>
          </el-col>
        </el-row>
      </div>
      <!-- ワーニングメッセージエリア -->
      <div v-if="form.code !== '' && (form.code ? form.code.indexOf('ERROR') == -1 : false)">
        <el-row>
          <el-col class="warningMessage">
            {{ form.errMessage }}
          </el-col>
        </el-row>
      </div>
      <!-- エラーメッセージエリア -->
      <div v-if="form.code !== '' && (form.code ? form.code.indexOf('ERROR') !== -1 : false)">
        <el-row>
          <el-col class="errorMessage">
            <span>印影書類照会の処理でエラーが発生したため、画面を表示できません。</span>
          </el-col>
        </el-row>
        <el-row>
          <el-col class="errorLabel">
            <span class="errorLabelFont">エラーコード：</span>
            <span class="errorMessageFont">{{ $_out(form.code) }}</span>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <span class="errorLabelFont">エラーメッセージ：</span>
            <span class="errorMessageFont">{{ $_out(form.errMessage) }}</span>
          </el-col>
        </el-row>
      </div>

    </el-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaImprintInquiryFormModel } from './js/IfaImprintInquiryFormModel'
export default {
  components: {
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  emits: ['close-modal'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaImprintInquiryFormModel(),
      balloonKey: false
    }
  },
  methods: {
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 顧客一覧からデータ受け渡し
    setupFromCustomerList(response) {
      this.form = response.dataList[0]
    }
  }
}

</script>

<style lang="scss" scoped>
@media print {
  #imprintImg {
    display: none;
  }
}
.col-item {
  border-bottom: 1px solid #092987;
}
.info-item__header {
  resize: none;
  border: none;
  color: #000000;
  font-size: 18px;
  height: 25px;
  line-height: 25px;
}
.info-item__value {
  resize: none;
  border: none;
  color: #000000;
  font-size: 18px;
  height: 25px;
  line-height: 25px;
}
.__right {
  text-align: right;
}
.__left {
  text-align: left;
}
.separator {
  border: 0;
  height: 1px;
  background: #333;
}
.warningMessage {
  font-size: 18px;
  font-weight: bold;
  margin-left: 30px;
  margin-top: 20px;
}
.errorMessage {
  font-size: 18px;
  font-weight: bold;
  margin-left: 30px;
  margin-top: 20px;
  color: red;
}
.errorLabel {
  margin-top: 30px;
}
.errorLabelFont {
  font-size: 18px;
  margin-left: 30px;
  margin-top: 20px;
  color: red;
}
.errorMessageFont {
  font-size: 18px;
  margin-top: 20px;
  color: red;
}
.form-button__wrapper {
  position: absolute;
  right: 1%;
  top: 100px;
  z-index: 999;
}
</style>
