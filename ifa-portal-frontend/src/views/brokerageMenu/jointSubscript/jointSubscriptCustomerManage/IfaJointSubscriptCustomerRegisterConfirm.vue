<template>
  <div>  <!-- 画面：SUB0206_01-02 共同募集　顧客管理　新規登録確認 -->
    <el-dialog
      v-model="vmIsVisible"
      left
      :close-on-click-modal="false"
      :show-close="false"
      width="600px"
      @open="setOkCancelDialogParams"
      @close="onDialogCloseA004"
    >
      <div>
        <el-row>
          <el-col>  <!-- ページキャプション -->
            <page-caption
              text="共同募集　顧客管理　新規登録確認"
            ></page-caption>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <div class="form-cancel-button__wrapper">
              <ifa-button
                text="戻る"
                color="secondary"
                small
                action-type="originalAction"
                @app-action-handler="onDialogCloseA004"
              ></ifa-button>
            </div>
          </el-col>
        </el-row>
      </div>
      <div style="margin-top:0.5rem;">
        <table
          id="t1"
          style="margin-bottom: 0.5rem;width:100%"
        > <!-- 新規登録確認エリア -->
          <tbody>
            <tr>
              <th class="_table__header __left">{{ itemTitle.brokerCodeTitle }}</th>
              <td class="_table__data __left">{{ formModel.brokerCode }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ itemTitle.brokerNameTitle }}</th>
              <td class="_table__data __left">{{ formModel.brokerName }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ itemTitle.butenCodeTitle }}</th>
              <td class="_table__data __left">{{ formModel.butenCode }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ itemTitle.accountNumberTitle }}</th>
              <td class="_table__data __left">{{ formModel.accountNumber }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ itemTitle.jointBranchCodeTitle }}</th>
              <td class="_table__data __left">{{ formModel.jointBranchCode }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ itemTitle.jointBranchNameTitle }}</th>
              <td class="_table__data __left">{{ formModel.jointBranchName }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ itemTitle.contractDateTitle }}</th>
              <td class="_table__data __left">{{ formModel.contractDate }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ itemTitle.startDateTitle }}</th>
              <td class="_table__data __left">{{ formModel.startDate }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ itemTitle.endDateTitle }}</th>
              <td class="_table__data __left">{{ formModel.endDate }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ itemTitle.jointRewardRateTitle }}</th>
              <td class="_table__data __right">{{ formModel.jointRewardRate }}&nbsp;&nbsp;%</td>
            </tr>
          </tbody>
        </table>
      </div>
      <el-row style="margin-top: 20px;">  <!-- 操作部エリア -->
        <el-col
          :offset="0"
          :span="22"
          style="text-align: left;"
        >
          <ifa-button id="ifaJointSubscriptCustomerRegisterA004"
                      name="ifaJointSubscriptCustomerRegisterA004"
                      text="登　録"
                      color="primary"
                      small
                      style="margin-left: -2px;"
                      action-id="SUB0206_01-02#A004"
                      action-type="requestAction"
                      :form="formRef"
                      :request-model="ifaJointSubscriptCustomerRegisterA004RequestModel"
                      @response-handler="responseA004Handler"
                      @response-error-handler="responseA004ErrorHandler"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
/* 以下は各々コンポーネントをエクスポートする */
import { useVModel } from 'vue-composable'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
import { IfaJointSubscriptCustomerRegisterA004RequestModel } from './js/IfaJointSubscriptCustomerRegisterA004RequestModel'

/* 以下はVUEコンポーネントのプロパティ：エクスポートされたオブジェクトを指定する */
export default {
  /* 使用可能な子コンポーネントを定義する */
  components: {
    pageCaption
  },
  /* 受け取る入力パラメータを定義する */
  props: {
    isVisible: {
      type: Boolean,
      reqdatared: true
    },
    formModel: { type: Object, required: true }
  },
  /* 以下はVUEコンポーネントのプロパティ：イベントを宣言する */
  emits: ['close-modal', 'update:isVisible', 'joint-subscript-customer-registered'],
  /* 以下はVUEコンポーネントのプロパティ：ビルドステップ */
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  /* 以下はVUEコンポーネントのプロパティ：画面変数を初期化する */
  data() {
    return {
      // 項目名
      okCancelDialog: {
        visible: false,
        title: '',
        message: '',
        enableOk: true,
        enableCancel: true
      },
      itemTitle: {
        brokerCodeTitle: '仲介業者コード',
        brokerNameTitle: '仲介業者名',
        butenCodeTitle: '部店',
        accountNumberTitle: '口座番号',
        jointBranchCodeTitle: '共募支店コード',
        jointBranchNameTitle: '共募支店名',
        contractDateTitle: '契約締結日',
        startDateTitle: '同意日',
        endDateTitle: '終了日',
        jointRewardRateTitle: '支払率'
      }
    }
  },
  /* 以下はVUEコンポーネントのプロパティ：計算プロパティ */
  computed: {
    // A004アクション 登録(新規登録確認)のリクエストモデル
    ifaJointSubscriptCustomerRegisterA004RequestModel() {
      return new IfaJointSubscriptCustomerRegisterA004RequestModel(this.formModel)
    }
  },
  /* 以下はVUEコンポーネントのプロパティ： 使用される関数を定義する */
  methods: {
    /**
     * 関数：A006アクションの戻る(新規登録確認)
     * @returns なし
     */
    onDialogCloseA004() {
      this.$emit('close-modal')
    },
    /**
     * 関数：A004アクションの処理：正常登録の後続処理
     * @returns なし
     */
    responseA004Handler(response) {
      this.$emit('joint-subscript-customer-registered')
    },
    responseA004ErrorHandler(error) {
      this.$_logError(error)
    }
  }
}
</script>

<style lang="scss" scoped>
.__bold {
  font-weight: bold;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-start;
  padding: 0.5rem 0 0.2rem 0;
}
.form-cancel-button__wrapper {
  display: flex;
  justify-content: flex-end;
  padding: 0.5rem 0 0.2rem 0;
}
#t1 tr { line-height: 25px; }
:deep(.el-dialog__title) {
   font-weight: bold;
}
._table__header {
  text-align: right;
}
:deep(._table__header.__left) {
  width: 155px;
}
:deep(._table__data.__left) {
  width: 375px;
}
:deep(._table__data.__right) {
  width: 375px;
  text-align: right;
}
</style>
