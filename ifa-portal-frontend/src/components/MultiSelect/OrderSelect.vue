<template>
  <ifa-input-multi-select
    :id="elmId"
    :ref="elmId"
    v-model="internalValues"
    :label="label"
    code-list-id="ORDER_LIST_SECURITY_CLASS"
    :disp-pattern="1"
    :select-pattern="1"
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
      internalValues: []
    }
  },
  computed: {
    elmId() {
      return 'iOrderSelect' + this.idString
    },
    orderSelectProp() {
      return {
        initialValues: ['0', '1', '5', '2', '3', '4']
      }
    }
  },
  created() {
    this.internalValues = [...this.orderSelectProp.initialValues]
  },
  methods: {
    handleChangeSelected(value) {
      this.$emit('changeSelected', value)
    },
    clearHandler() {
      // initialValues で再初期化
      this.internalValues = [...this.orderSelectProp.initialValues]
      this.$nextTick(() => {
        // el-select の形式 (['A', 'B', ...]) を jquery の形式([{id: 'A', isSelected: true}, {id: 'B', isSelected: true}, ...])に
        // 変換したデータを emit させるため､update() を呼び出す｡
        // internalValues が IfaInputMultiSelect に反映されるのを待つため､$nextTick() から呼び出す｡
        this.$refs[this.elmId].update()
      })
    }
  }
}
</script>
