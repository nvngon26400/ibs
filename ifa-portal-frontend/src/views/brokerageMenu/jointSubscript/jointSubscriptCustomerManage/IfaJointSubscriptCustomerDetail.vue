<template>
  <div>
    <!-- ダイアログ -->
    <el-dialog
      v-model="vmIsVisible"
      width="700px"
      :center="true"
      :show-close="false"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      @open="onShow"
    >
      <el-row style="margin-bottom: 1.5rem;">
        <el-col
          :span="22"
          :offset="1"
        >
          <!-- ページキャプション -->
          <page-caption
            text="顧客情報 詳細"
            background-color="rgba(190, 190, 190, 0.35)"
          ></page-caption>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="23"
          class="close-button"
        >
          <!-- 戻るボタン -->
          <ifa-button
            text="戻る"
            width="90"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
        <!-- 明細情報部 -->
        <el-col
          :span="22"
          :offset="1"
        >
          <el-card class="box-card">
            <table
              class="_table__body"
              style="width:100%; margin: 0 0 10px 0"
            >
              <tbody>
                <tr>
                  <th class="_table__header __center">項目</th>
                  <th class="_table__header __center">内容</th>
                </tr>
                <tr>
                  <td class="_table__data __left">部店コード</td>
                  <td class="_table__data __right">{{ form.butenCode ? form.butenCode : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">口座番号</td>
                  <td class="_table__data __right">{{ form.accountNumber ? form.accountNumber : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">仲介業者コード</td>
                  <td class="_table__data __right">{{ form.brokerCode ? form.brokerCode : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">仲介業者名</td>
                  <td class="_table__data __right">{{ form.brokerName ? form.brokerName : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">仲介業者営業員コード</td>
                  <td class="_table__data __right">{{ form.brokerChargeCode ? form.brokerChargeCode : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">仲介業者営業員名</td>
                  <td class="_table__data __right">{{ form.brokerChargeName ? form.brokerChargeName : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">顧客名(漢字)</td>
                  <td class="_table__data __right">{{ form.nameKanji ? form.nameKanji : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">顧客名(カナ)</td>
                  <td class="_table__data __right">{{ form.nameKana ? form.nameKana : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">住所(漢字)</td>
                  <td class="_table__data __right">{{ form.addressKanji1 ? form.addressKanji1 : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">電話番号</td>
                  <td class="_table__data __right">{{ form.phoneNumber ? form.phoneNumber : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">自宅電話不可フラグ</td>
                  <td class="_table__data __right">{{ form.phoneFlg ? form.phoneFlg : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">性別区分</td>
                  <td class="_table__data __right">{{ genderSexText }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">年齢</td>
                  <td class="_table__data __right">{{ form.age ? form.age : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">口座開設年月日</td>
                  <td class="_table__data __right">{{ form.openAcctDate ? $_getFormattedDateValue(form.openAcctDate, 'date8') : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">コンプラランク</td>
                  <td class="_table__data __right">{{ form.tcCompRank ? form.tcCompRank : '' }}</td>
                </tr>
              </tbody>
            </table>
          </el-card>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'

export default {
  components: {
    pageCaption
  },
  props: {
    isVisible: { type: Boolean, required: true }, // ダイアログの表示状態
    customerDetailForm: { type: Object, required: true } // 顧客詳細情報
  },
  emits: ['close-modal'], // ダイアログを閉じるイベント
  setup(props) {
    // ダイアログの表示状態を管理
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    // 顧客詳細フォーム
    return {
      form: this.customerDetailForm
    }
  },
  computed: {
    genderSexText() {
      const sex = this.form.sex
      switch (sex) {
        case '1':
          return '男'
        case '2':
          return '女'
        default:
          return ''
      }
    }
  },
  methods: {
    // ダイアログ表示時の処理
    onShow() {
      // ダイアログが表示されたときのロジックをここに記述
    },
    // ダイアログ閉じるボタン押下時の処理
    onDialogClose() {
      // ダイアログを閉じるイベントをemit
      this.$emit('close-modal')
    }
  }
}
</script>

<style lang="scss" scoped>
.__center {
  text-align: center;
}
.__left {
  text-align: left;
  width:35%;
}
.__right {
  text-align: left;
  width:65%;
}
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
.box-card {
  margin: 5px;
}
._table__body tbody tr:nth-child(odd) {
  background-color: #f9f9f9;
}
</style>
