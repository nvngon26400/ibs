<template>
  <ifa-input-multi-select
    :id="elmId"
    :ref="elmId"
    v-model="internalValues"
    :label="label"
    :code-list-id="courseSelectProp.codeListId"
    :select-pattern="courseSelectProp.selectPattern"
    :disp-pattern="courseSelectProp.dispPattern"
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

    // 'on' : コースの選択に「インターネットコース」を追加し初期選択状態にする
    // 'off' : コースの選択に「インターネットコース」を追加
    // '' : コースに追加無し
    // addInternetToCourses: {
    //   type: String,
    //   required: false,
    //   default: ''
    // }

  },
  emits: ['changeSelected'],
  data() {
    return {
      internalValues: []
    }
  },
  computed: {
    elmId() {
      return 'iCourseSelect' + this.idString
    },
    courseSelectProp() {
      let rtn = {}
      let initVal = []
      let selectPat = ''
      if (this.listKind === 'pt1') {
        rtn = {
          codeListId: 'TRADE_COURSE',
          dispPattern: 1,
        }
        selectPat = 2,
        initVal = ['B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'L']
      } else if (this.listKind === 'pt2') {
        rtn = {
          codeListId: 'TRADE_COURSE',
          dispPattern: 1,
        }
        selectPat = 2
        initVal = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'L']
      } else if (this.listKind === 'pt3') {
        rtn = {
          codeListId: 'TRADE_COURSE',
          dispPattern: 1,
        }
        selectPat = 3,
        initVal = ['B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'L']
      } else if (this.listKind === 'pt4') {
        rtn = {
          codeListId: 'TRADE_COURSE',
          dispPattern: 1,
        }
        selectPat = 3,
        initVal = ['E']
      } else if (this.listKind === 'pt5') {
        rtn = {
          codeListId: 'TRADE_COURSE',
          dispPattern: 1,
        }
        selectPat = 2,
        initVal = []
      } else {
        rtn = {
          codeListId: '',
          dispPattern: 1,
        }
        selectPat = 1,
        initVal = []
      }

      if (this.$store.getters.userAccount.courseKDispFlg) {
        if (['pt1','pt2','pt3'].includes(this.listKind)) {
          initVal.push('K')
        }
        selectPat = selectPat + 3
      }
      rtn.selectPattern = selectPat
      rtn.initialValues = initVal
      return rtn
    }
  },
  created() {
    this.internalValues = [...this.courseSelectProp.initialValues]
  },
  methods: {
    handleChangeSelected(values) {
      this.$emit('changeSelected', values)
    },
    clearHandler() {
      // initialValues で再初期化
      this.internalValues = [...this.courseSelectProp.initialValues]
      this.$nextTick(() => {
        // el-select の形式 (['A', 'B', ...]) を jquery の形式([{id: 'A', isSelected: true}, {id: 'B', isSelected: true}, ...])に
        // 変換したデータを emit させるため､update() を呼び出す｡
        // internalValues が IfaInputMultiSelect に反映されるのを待つため､$nextTick() から呼び出す｡
        this.$refs[this.elmId].update()
      })
    },
    handleChangeAny(value) {
      this.$refs[this.elmId].handleCheckAny(value)
    }
  }
}
</script>
