<!-- TODO: ファイルごと削除予定 -->
<template>
  <el-row>
    <el-col>
      <el-row>
        <el-col
          :offset="19"
          :span="4"
          style="text-align: right;"
        >
          <el-form-item
            v-show="chkBrokerCodeExcludeShow"
            style="padding-top: 5px;"
          >
            <el-checkbox
              v-model="chkBrokerCodeExclude"
              style="padding-left: 30px;"
              label="仲介業者除外"
              :disabled="chkBrokerCodeExcludeDisabled"
            ></el-checkbox>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item
          v-show="brokerCodeShow"
          label="仲介業者コード"
          class="form_label"
          prop="brokerCode"
        >
          <el-tooltip
            :disabled="brokerCode.length < 1"
            :content="brokerCode"
            placement="bottom"
            effect="light"
          >
            <el-input
              id="brokerCode"
              type="text"
              size="small"
              name="brokerCode"
              maxlength="49"
              class="ifa-input__text-default search-form__item middle_input"
              :disabled="brokerCodeDisabled"
              :value="brokerCode"
              @change="handleCodeChanged"
              @keydown="handleKeyDown"
              @input="handleBrokerCodeModified"
            ></el-input>
          </el-tooltip>
        </el-form-item>
        <el-form-item
          v-show="branchCodeShow"
          name="branchCode"
          label="支店コード"
          class="form_label"
          prop="branchCode"
        >
          <el-input
            id="branchCode"
            type="text"
            size="small"
            maxlength="7"
            class="ifa-input__text-default search-form__item middle_input"
            :disabled="branchCodeDisabled"
            :value="branchCode"
            @change="handleCodeChanged"
            @keydown="handleKeyDown"
            @input="handleBranchCodeModified"
          ></el-input>
        </el-form-item>
        <el-form-item
          v-show="empCodeShow"
          class="form_label"
          prop="empCode"
        >
          <template #label>営業員コード</template>
          <el-input
            v-model="empCode"
            type="text"
            size="small"
            maxlength="4"
            class="ifa-input__text-default search-form__item middle_input"
            :disabled="empCodeDisabled"
          ></el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item
          v-show="butenCodeShow"
          label="部店コード"
          class="form_label"
        >
          <el-input
            v-model="butenCode"
            type="text"
            size="small"
            maxlength="3"
            class="ifa-input__text-default search-form__item middle_input"
            :value="searchItemList.butenCode"
          ></el-input>
        </el-form-item>
        <el-form-item
          v-show="accountNumberShow"
          label="口座番号"
          class="form_label"
        >
          <el-input
            v-model="accountNumber"
            type="text"
            size="small"
            maxlength="7"
            class="ifa-input__text-default search-form__item middle_input"
            :value="searchItemList.accountNumber"
          ></el-input>
        </el-form-item>
        <el-form-item
          v-show="customerNameShow"
          label="顧客名(漢字/カナ)"
          class="form_label"
        >
          <div>
            <el-input
              v-model="customerName"
              type="text"
              class="middle_input"
              size="small"
              maxlength="127"
              :value="searchItemList.customerName"
            ></el-input>
            <el-select
              v-model="customerNameSearchType"
              class="dropdown"
              size="small"
              style="width:110px;"
            >
              <el-option
                v-for="item in searchOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                width="100px"
              ></el-option>
            </el-select>
          </div>
        </el-form-item>
        <el-form-item
          v-show="customerAttributeShow"
          label="取引コース"
          class="form_label"
          prop="courseSelected"
        >
          <div>
            <course-select
              :list-kind="searchItemList.listKind"
              id-string="listKind"
              @change-selected="courseSelected = $event"
            ></course-select>
          </div>
        </el-form-item>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
import CourseSelect from '@/components/MultiSelect/CourseSelect'
export default {
  // eslint-disable-next-line vue/no-unused-components
  components: { CourseSelect },
  props: {
    searchItemList: { type: Object, required: true }
  },
  data() {
    return {
      butenCode: this.searchItemList.butenCode,
      butenCodeShow: !this.searchItemList.butenCodeDisabled,
      accountNumber: this.searchItemList.accountNumber,
      accountNumberShow: !this.searchItemList.accountNumberDisabled,
      customerName: this.searchItemList.customerName,
      customerNameShow: !this.searchItemList.customerNameDisabled,
      branchCode: this.searchItemList.branchCode,
      branchCodeDisabled: this.searchItemList.branchCodeDisabled,
      branchCodeShow: !this.searchItemList.branchCodeDisabled,
      brokerCode: this.searchItemList.brokerCode,
      brokerCodeDisabled: this.searchItemList.brokerCodeDisabled,
      brokerCodeShow: !this.searchItemList.brokerCodeDisabled,
      empCode: this.searchItemList.empCode,
      empCodeDisabled: this.searchItemList.empCodeDisabled,
      empCodeShow: !this.searchItemList.empCodeDisabled,
      chkBrokerCodeExclude: false,
      chkBrokerCodeExcludeDisabled: this.searchItemList.chkBrokerCodeExcludeDisabled,
      chkBrokerCodeExcludeShow: !this.searchItemList.chkBrokerCodeExcludeDisabled,
      customerAttributeShow: !this.searchItemList.customerAttributeDisabled,
      customerNameSearchType: '3',
      orderStatusSelect: '',
      courseSelected: [
        {
          id: '',
          isSelected: ''
        }
      ],
      searchOptions: [
        { value: '1', label: 'と等しい' },
        { value: '2', label: 'で始まる' },
        { value: '3', label: 'を含む' }
      ],
      delFlag: false
    }
  },
  methods: {
    // リセット
    formClear() {
      this.butenCode = this.searchItemList.butenCode
      this.accountNumber = this.searchItemList.accountNumber
      this.customerName = this.searchItemList.customerName
      this.brokerCode = this.searchItemList.brokerCode
      this.brokerCodeDisabled = this.searchItemList.brokerCodeDisabled
      this.chkBrokerCodeExclude = false
      this.branchCode = ''
      this.branchCodeDisabled = true
      this.empCode = ''
      this.empCodeDisabled = true
      this.customerNameSearchType = '3'
    },
    // キー入力
    handleKeyDown(event) {
      switch (event.key) {
        case 'Delete': // event.keyCode: 46
          this.delFlag = true
          break
        case 'Backspace': // event.keyCode: 8
          this.delFlag = false
          break
      }
    },
    // 仲介業者コードが編集された
    handleBrokerCodeModified(newValue) {
      const newText = newValue.replace(/[^\d]/g, '')
      const currentText = this.brokerCode.replace(/[^\d]/g, '')

      this.brokerCode = newText.match(/\d{1,4}/g)?.join() || ''

      const obj = this.$el.querySelector('#brokerCode')
      let pos = obj.selectionStart
      if (newText !== currentText) {
        if (newText.length > currentText.length) {
          // 文字が追加された
          if (this.brokerCode.length - pos === 1) {
            // カーソル位置が最後尾かつコンマが追加されたときは､1文字進める (1111|[2] -> 1111,[2]| )
            pos += 1
          } else {
            const m = newValue.match(/\d{5,}/)
            if (m) {
              const i = m[0].length + m.index
              if (pos === i && newValue.charAt(i) === ',') {
                // コンマの前に文字が挿入された場合､1文字進める (1111,9999|[2],222 -> 1111,9999,[2]|222)
                pos += 1
              }
            }
          }
        }
      } else {
        // Backspace キーまたはDEL キーでコンマが削除されたとき
        if (this.delFlag) {
          // DEL キーで削除された場合
          if (pos > 0 && pos % 5 === 4) {
            // コンマが削除されても復活するのでカーソルだけ1文字進める (1111|,2222 -> 1111,|2222)
            pos += 1
          }
        } else {
          // Backspace キーで削除された場合
          if (pos > 0 && pos % 5 === 0) {
            // コンマが削除されても復活するのでカーソルだけ1文字戻す (1111,|2222 -> 1111|,2222)
            pos -= 1
          }
        }
      }

      this.$nextTick(() => {
        this.$nextTick(() => {
          // カーソルを確実に移動するために $nextTick を2回重ねる (1回だと reactive が効いてカーソルが最後尾に移動してしまう)
          obj.selectionStart = pos
          obj.selectionEnd = pos
        })
      })
    },
    // 仲介業者・支店コードが変更された
    handleCodeChanged() {
      // 仲介業者コードが削除されたら､支店コードも削除する
      if (!this.brokerCode || this.brokerCode.length > 4) {
        this.branchCode = ''
        this.branchCodeDisabled = true
        this.empCodeDisabled = true
      } else {
        this.branchCodeDisabled = false
        this.empCodeDisabled = false
      }

      // 支店コードが削除されたら､営業員コードも削除する
      if (!this.branchCode || this.branchCode.length > 3) {
        this.empCode = ''
      }
    },
    // 支店コードが編集された
    handleBranchCodeModified(newValue) {
      const newText = newValue.replace(/[^\d]/g, '')
      const currentText = this.branchCode.replace(/[^\d]/g, '')

      this.branchCode = newText.match(/\d{1,3}/g)?.join() || ''

      const obj = this.$el.querySelector('#branchCode')
      let pos = obj.selectionStart
      if (newText !== currentText) {
        if (newText.length > currentText.length) {
          // 文字が追加された
          if (this.branchCode.length - pos === 1) {
            // カーソル位置が最後尾かつコンマが追加されたときは､1文字進める (1111|[2] -> 1111,[2]| )
            pos += 1
          } else {
            const m = newValue.match(/\d{4,}/)
            if (m) {
              const i = m[0].length + m.index
              if (pos === i && newValue.charAt(i) === ',') {
                // コンマの前に文字が挿入された場合､1文字進める (1111,9999|[2],222 -> 1111,9999,[2]|222)
                pos += 1
              }
            }
          }
        }
      } else {
        // Backspace キーまたはDEL キーでコンマが削除されたとき
        if (this.delFlag) {
          // DEL キーで削除された場合
          if (pos > 0 && pos % 4 === 3) {
            // コンマが削除されても復活するのでカーソルだけ1文字進める (1111|,2222 -> 1111,|2222)
            pos += 1
          }
        } else {
          // Backspace キーで削除された場合
          if (pos > 0 && pos % 4 === 0) {
            // コンマが削除されても復活するのでカーソルだけ1文字戻す (1111,|2222 -> 1111|,2222)
            pos -= 1
          }
        }
      }

      this.$nextTick(() => {
        this.$nextTick(() => {
          // カーソルを確実に移動するために $nextTick を2回重ねる (1回だと reactive が効いてカーソルが最後尾に移動してしまう)
          obj.selectionStart = pos
          obj.selectionEnd = pos
        })
      })
    },
    empCodeValidator(rule, value, callback) {
      if (this.empCode !== '') {
        // OK
        callback()
        return
      }
      callback('営業員コードを入力してください。')
    },
    brokerCodeValidator(rule, value, callback) {
      if (this.brokerCode !== '') {
        // OK
        callback()
        return
      }
      callback('仲介業者コードを入力してください。')
    },
    courseSelectedValidator(rule, value, callback) {
      for (const i in this.courseSelected) {
        if (this.courseSelected[i].isSelected === true) {
          // OK
          callback()
          return
        }
      }
      callback('取引コースを選択してください。')
    }
  }
}

</script>
<style scoped>
.search-form__item {
  margin: 0 0.2rem;
  width: 180px;
}
.search-form__itemL {
  margin: 0 0.2rem;
  width: 420px;
}
.small_input {
  width: 80px;
}
.middle_input {
  width: 200px;
}
.large_input {
  width: 330px;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
  line-height: 2
}
.form_button {
  margin: 0 2rem 0.8rem 2.5rem;
  padding: 0;
}
#gridButtonArea {
  margin: 0.5rem 0;
}
#contentAreaInput, #outputPaneMargins {
  margin: 0.5rem;
}
:deep(.el-form-item__error) {
  white-space: nowrap
}
:deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
.el-icon-info {
  font-size: 30px;
}
:deep(.el-icon-info:hover){
  color: #409eff;
}
:deep(.el-dialog__wrapper) {
  z-index: 3000 !important;
}
:deep(.el-dialog__title) {
  font-size: 16px;
  margin: 0px;
  font-weight: 600;
  padding: 0px;
}
:deep(.el-dialog__body) {
  color: #303133;
}
:deep(.el-dialog) {
  width: 500px;
}
.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 10px;
}
:deep(.el-popove) {
  width: auto;
}
.statusOption.is-disabled  {
  color: #606266;
  cursor: default;
}
 :deep(.el-checkbox__label) {
   font-weight: bold;
 }
</style>
