<template>
  <ifa-input-multi-select
    :id="elmId"
    :ref="elmId"
    v-model="internalValues"
    :label="label"
    :code-list-id="PersonalCorporationSelectProp.codeListId"
    :select-pattern="PersonalCorporationSelectProp.selectPattern"
    :disp-pattern="PersonalCorporationSelectProp.dispPattern"
    :disabled="listDisabled"
    :prop="prop"
    :required="required"
    labelClass="corporation_classs"
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
      internalValues: []
    }
  },
  computed: {
    elmId() {
      return 'iProductCode' + this.idString
    },
    PersonalCorporationSelectProp() {
      if (this.listKind === 'pt1') {
        return {
          codeListId: 'PERSONAL_INVESTMENT_PLAN',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '02', '03', '04', '09', '999']
        }
      } else if (this.listKind === 'pt2') {
        return {
          codeListId: 'CORPORATION_INVESTMENT_PLAN',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['3', '4', '5', '6', '7', '9', '999']
        }
      } else if (this.listKind === 'pt3') {
        return {
          codeListId: 'PERSONAL_FUND_CHARACTER',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '02', '03', '04', '05', '06', '09', '999']
        }
      } else if (this.listKind === 'pt4') {
        return {
          codeListId: 'CORPORATION_FUND_CHARACTER',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['1', '2', '3', '9', '999']
        }
      } else if (this.listKind === 'pt5') {
        return {
          codeListId: 'PERSONAL_INCOME_FORM',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '02', '03', '04', '05', '06', '07', '09', '999']
        }
      } else if (this.listKind === 'pt6') {
        return {
          codeListId: 'PERSONAL_CORPORATION_EMPLOYMENT_PERIOD',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '02', '03', '09', '999']
        }
      } else if (this.listKind === 'pt7') {
        return {
          codeListId: 'PERSONAL_ANNUAL_INCOME',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '02', '03', '04', '05', '06', '07', '05', '07', '08', '99', '999']
        }
      } else if (this.listKind === 'pt8') {
        return {
          codeListId: 'PERSONAL_FINANCIAL_ASSETS',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '02', '03', '04', '05', '06', '07', '05', '07', '08', '99', '999']
        }
      } else if (this.listKind === 'pt9') {
        return {
          codeListId: 'PERSONAL_OCCUPATION',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', '999']
        }
      } else if (this.listKind === 'pt10') {
        return {
          codeListId: 'CORPORATION_OCCUPATION',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['10', '11', '12', '13', '14', '15', '16', '17', '18', '20', '21', '22', '30', '31', '32', '33', '40', '41', '42', '43', '50', '51', '60', '61', '62', '63', '70', '71', '72', '73', '74', '75', '80', '81', '82', '86', '87', '88', '90', '91', '95', '97', '98', '999']
        }
      } else if (this.listKind === 'pt11') {
        return {
          codeListId: 'PERSONAL_CORPORATION_INVESTMENT_EXP',
          dispPattern: 1,
          selectPattern: 1,
          initialValues: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18']
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
    this.internalValues = [...this.PersonalCorporationSelectProp.initialValues]
  },
  methods: {
    handleChangeSelected(values) {
      this.$emit('changeSelected', values)
    },
    clearHandler() {
      // initialValues で再初期化
      this.internalValues = [...this.PersonalCorporationSelectProp.initialValues]
      this.$nextTick(() => {
        // el-select の形式 (['A', 'B', ...]) を jquery の形式([{id: 'A', isSelected: true}, {id: 'B', isSelected: true}, ...])に
        // 変換したデータを emit させるため､update() を呼び出す｡
        // internalValues が IfaInputMultiSelect に反映されるのを待つため､$nextTick() から呼び出す｡
        this.$refs[this.elmId].update()
      })
    },
    handleCheckAll(value) {
      this.$refs[this.elmId].handleCheckAll(value)
    },
    handleChangeAny(value) {
      this.$refs[this.elmId].handleCheckAny(value)
    }
  }
}
</script>
