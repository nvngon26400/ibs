<template>
  <ifa-input-multi-select
    :id="elmId"
    :ref="elmId"
    v-model="internalValues"
    :label="label"
    :code-list-id="productCodeSelectProp.codeListId"
    :select-pattern="productCodeSelectProp.selectPattern"
    :disp-pattern="productCodeSelectProp.dispPattern"
    :disabled="listDisabled"
    :prop="prop"
    :required="required"
    :control-auth-enabled="controlAuthEnabled"
    @change-selected="handleChangeSelected"
  ></ifa-input-multi-select>
</template>

<script>
export default {
  props: {
    listKind: { required: false, type: String, default: '' },
    idString: { required: false, type: String, default: '' },
    listDisabled: { type: Boolean, required: false, default: false },
    label: { type: String, required: false, default: '' }, // ラベル
    prop: { type: String, required: false, default: '' }, // プロパティ名
    required: { type: Boolean, required: false, default: undefined }, // 必須項目
    controlAuthEnabled: { type: Boolean, required: false, default: true } // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
  },
  emits: ['changeSelected'],
  data() {
    return {
      defaultValues: []
    }
  },
  computed: {
    elmId() {
      return 'iProductCode' + this.idString
    },
    productCodeSelectProp() {
      if (this.listKind === 'pt1') {
        return {
          codeListId: 'SECURITY_CASH_CLASS',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '15', '06', '08', '09', '16', '12', '25', '04', '13', '14', '33', '50', '100', '98', '99']
        }
      } else if (this.listKind === 'pt2') {
        return {
          codeListId: 'MARGIN_FUTURES_OPTIONS_SECURITY_CLASS',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['02', '10', '11', '29']
        }
      } else if (this.listKind === 'pt3') {
        return {
          codeListId: 'TRADE_HISTORY_SECURITY_CLASS',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '02', '03', '24', '15', '29', '27', '05', '07', '21', '12', '25', '04', '13', '14', '33', '10', '11']
        }
      } else if (this.listKind === 'pt4') {
        return {
          codeListId: 'COMMISSION_LIST_SECURITY_CLASS',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['98', '01', '02', '03', '04', '05', '07', '10', '11', '12', '13', '14', '15', '21', '23', '24', '25', '27', '29', '33', '99']
        }
      } else if (this.listKind === 'pt5') {
        return {
          codeListId: 'COMMISSION_LIST_SECURITY_CLASS',
          dispPattern: 1,
          selectPattern: 2,
          initialValues: ['98', '01', '02', '03', '04', '05', '07', '10', '11', '12', '13', '14', '15', '21', '23', '24', '25', '29', '33', '99']
        }
      } else if (this.listKind === 'pt6') {
        return {
          codeListId: 'TRUST_FEE_SECURITY_CLASS',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['06', '08', '09', '16', '22', '28', '32', '34']
        }
      } else if (this.listKind === 'pt7') {
        return {
          codeListId: 'TRADE_HISTORY_SECURITY_CLASS',
          dispPattern: 1,
          selectPattern: 2,
          initialValues: ['01', '02', '03', '24', '15', '29', '05', '07', '21', '12', '25', '04', '13', '14', '33', '10', '11']
        }
      } else if (this.listKind === 'pt8') {
        return {
          codeListId: 'SECURITY_CASH_CLASS',
          dispPattern: 1,
          selectPattern: 2,
          initialValues: ['12', '25', '04', '13', '14']
        }
      } else if (this.listKind === 'pt9') {
        return {
          codeListId: 'SECURITY_STORE_TRADE_CLASS',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '02', '03', '24', '15', '29', '27', '05', '07', '21', '12', '25', '04', '13', '14', '33', '10', '11', '17', '18', '19', '20', '26']
        }
      } else if (this.listKind === 'pt10') {
        return {
          codeListId: 'JOINT_SUBSCRIPT_TRUST_FEE_SECURITY_CLASS',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['06', '08', '09', '16', '28', '34']
        }
      } else if (this.listKind === 'pt11') {
        return {
          codeListId: 'JOINT_SECURITY_CASH_CLASS',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '15', '06', '08', '09', '16', '12', '25', '04', '13', '14', '33', '50', '100', '98', '99']
        }
      } else {
        return {
          codeListId: '',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: []
        }
      }
    }
  },
  created() {
    this.internalValues = [...this.productCodeSelectProp.initialValues]
  },
  methods: {
    handleChangeSelected(values) {
      this.$emit('changeSelected', values)
    },
    clearHandler() {
      // initialValues で再初期化
      this.internalValues = [...this.productCodeSelectProp.initialValues]
      this.$nextTick(() => {
        // el-select の形式 (['A', 'B', ...]) を jquery の形式([{id: 'A', isSelected: true}, {id: 'B', isSelected: true}, ...])に
        // 変換したデータを emit させるため､update() を呼び出す｡
        // internalValues が IfaInputMultiSelect に反映されるのを待つため､$nextTick() から呼び出す｡
        this.$refs[this.elmId].update()
      })
    },
    handleCheckAll(value) {
      this.$refs[this.elmId].handleCheckAll(value)
    }
  }
}
</script>
