<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      width="1200px"
      :center="true"
      title="銘柄検索"
      :show-close="false"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      @open="onOpen"
    >
      <!-- 戻るボタン -->
      <el-row>
        <el-col
          :span="23"
          class="form-button__wrapper"
          style="margin-left: 0px;"
        >
          <ifa-button
            text="戻る"
            color="secondary"
            style="padding-left: 0px; padding-right: 0px;"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <!-- 検索条件エリア -->
      <div>
        <el-form
          ref="form"
          :model="form"
        >

          <el-row>
            <!-- メッセージ -->
            <el-col :offset="1">
              <!-- 遷移元画面.取引種別="新規売" -->
              <span v-if="form.tradeCd==='3'">銘柄名称 または ティッカー/銘柄コード の先頭から何文字かをご入力ください。</span>
              <!-- 遷移元画面.取引種別="新規売"以外 （信用新規売画面以外からの遷移） -->
              <span v-if="form.tradeCd!=='3'">銘柄名称 または ティッカー/銘柄コード の何文字かをご入力ください。</span>
            </el-col>
          </el-row>

          <el-row style="margin-top: 0.5rem; flex-wrap: nowrap; margin-left: 48px;">

            <!-- 銘柄名称またはコード -->
            <ifa-input-text
              id="brandNameBrandCode"
              v-model="form.brandNameBrandCode"
              prop="brandNameBrandCode"
              type="text"
              class="ifa-input__text-default"
              style="width:340px;"
              original-screen-id="SUB07-04"
              :domain="IfaBrandNameCodeDomainModel"
            ></ifa-input-text>

            <!-- 検索マッチ種別 初期値：“から始まる” -->
            <ifa-input-select
              v-if="form.tradeCd!=='3'"
              v-model="form.searchOptions"
              :clearable="false"
              code-list-id="BRAND_SEARCH_METHOD"
              style="width:120px;"
              :disp-pattern="2"
              :select-pattern="2"
            ></ifa-input-select>

            <!-- 国籍 -->
            <ifa-input-select
              id="nationality"
              v-model="form.countryCodeList"
              style="width:150px; margin-left: -80px;"
              :required="true"
              :clearable="false"
              label="国籍"
              :disabled="handleDisable"
              code-list-id="NATIONALITY_CODE"
              :label-class="labelClass"
              :disp-pattern="2"
              :select-pattern="handleDisable ? 2 : 1"
              @change="changeCountry"
            ></ifa-input-select>

            <!-- 市場選択 （米株信用新規売:0, それ以外:1, 信用新規売以外の時表示） -->
            <ifa-input-select
              v-if="form.tradeCd!=='3'"
              id="market"
              v-model="form.marketList"
              style="width:224px; margin-left: -80px;"
              :clearable="false"
              label="市場"
              prop=""
              code-list-id="original"
              :original-list="marketLists"
              :label-class="labelClass"
              placeholder=" "
            ></ifa-input-select>
          </el-row>
        </el-form>
      </div>
      <!-- 検索ボタン -->
      <el-row>
        <el-col
          :offset="1"
          style="margin-top: 1rem;"
        >
          <ifa-button
            id="btnSearch"
            name="btnSearch"
            text="検索"
            color="primary"
            style="padding-left: 0;"
            action-id="SUB07-04#A002"
            action-type="requestAction"
            :form="$refs.form"
            :request-model="ifaForeignStockBrandSearchPopupA002RequestModel"
            :pre-request-handler="preRequestHandlerA002"
            @response-handler="responseHandlerA002($event)"
            @response-error-handler="responseErrorHandlerA002($event)"
          ></ifa-button>
        </el-col>
      </el-row>

      <el-row style="margin-top: 1rem;">
        <el-col
          :offset="1"
          :span="22"
        >
          <el-card>
            <grid-table
              ref="gridTable"
              :key="gridkey"
              :options="pqGridOption"
              :auto-refresh="false"
              @click="handleClick($event)"
            ></grid-table>
          </el-card>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { getDefaultOption } from '@/utils/pqgridHelper'
import { useVModel } from 'vue-composable'
import { IfaForeignStockBrandSearchPopupFormModel } from './js/IfaForeignStockBrandSearchPopupFormModel.js'
import { IfaForeignStockBrandSearchPopupA002RequestModel } from './js/IfaForeignStockBrandSearchPopupA002RequestModel.js'
import IfaBrandNameCodeDomainModel from '@/resource/domain/IfaBrandNameCodeDomainModel.json'

export default {
  components: {
    GridTable
  },
  props: {
    // 信用取引：true, それ以外：false
    isMargin: { type: Boolean, default: false },
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  emits: ['close-modal', 'result', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      IfaBrandNameCodeDomainModel,
      gridkey: 0,
      handleDisable: false,
      labelClass: 'label_class',
      pqGridOption: getDefaultOption(columns1),
      form: new IfaForeignStockBrandSearchPopupFormModel()
    }
  },
  computed: {
    // 市場.取得パターン
    marketLists() {
      if (this.form.countryCodeList === 'US') {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 2)
      } else if (this.form.countryCodeList === 'HK') {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 3)
      } else if (this.form.countryCodeList === 'KR') {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 4)
      } else if (this.form.countryCodeList === 'RU') {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 5)
      } else if (this.form.countryCodeList === 'VN') {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 6)
      } else if (this.form.countryCodeList === 'ID') {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 7)
      } else if (this.form.countryCodeList === 'SG') {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 8)
      } else if (this.form.countryCodeList === 'TH') {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 9)
      } else if (this.form.countryCodeList === 'MY') {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 10)
      } else {
        return this.$_getCodeList('FOREIGN_MARKET_TYPE', 1, 1)
      }
    },
    ifaForeignStockBrandSearchPopupA002RequestModel() {
      const ifaForeignStockBrandSearchPopupA002RequestModel = new IfaForeignStockBrandSearchPopupA002RequestModel(this.form)
      ifaForeignStockBrandSearchPopupA002RequestModel.countryCode = this.form.countryCodeList
      return ifaForeignStockBrandSearchPopupA002RequestModel
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  mounted() {
    if (this.isMargin === true) {
      this.form.countryCode = 'US'
    }
  },
  methods: {
    onOpen() {
      if (this.isMargin === true) {
        this.handleDisable = true
      }

      if (this.form.tradeCd === '3') {
        columns1[4].hidden = false
        columns1[3].width = 317
        this.gridkey += 1
      } else {
        columns1[4].hidden = true
        columns1[3].width = 440
        this.gridkey += 1
      }
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    preRequestHandlerA002() {
      this.ifaForeignStockBrandSearchPopupA002RequestModel.market = this.form.marketList
    },
    responseHandlerA002(response) {
      if (this.form.tradeCd === '3') {
        this.pqGridOption.dataModel.data = response.dataList[0].marginNewSellList
      } else {
        this.pqGridOption.dataModel.data = response.dataList[0].marginNewSellExceptingList
      }
      this.$refs['gridTable'].refreshView(true)
    },
    responseErrorHandlerA002(response) {
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    onDialogClose() {
      this.clear()
      this.$emit('close-modal')
    },
    clear() {
      this.form.brandNameBrandCode = ''
      this.form.marketList = ''
      this.form.searchOptions = '2'
      this.form.countryCodeList = ''
    },
    // 国籍コード('US')を指定して 本ダイアログの国籍を設定する。
    setCuntoryByString(country) {
      this.form.countryCodeList = country
    },
    // 取引種別を設定する
    setTradeCd(tradeCd) {
      this.form.tradeCd = tradeCd
    },
    handleClick(row, rowIndex) {
      if (row.dataIndx === 'brandCode' && row.rowData.positionQuantity !== '0') {
        this.$emit('result', row.rowData)
        this.$emit('close-modal')
      }
    },
    changeCountry() {
      this.form.marketList = ''
    }
  }
}

const columns1 = [
  {
    title: 'ティッカー/銘柄コード',
    dataIndx: 'brandCode',
    minWidth: 200,
    maxWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const rowData = ui.rowData
      if (rowData.brandCode === '' || rowData.brandCode === null) {
        return '<span>-</span>'
      } else {
        if (rowData.positionQuantity === '0') {
          return '<span>' + rowData.brandCode + '</span>'
        } else {
          return '<a><span style="color: #0000FF; text-decoration: underline">' + rowData.brandCode + '</span></a>'
        }
      }
    }

  },
  {
    title: '国コード',
    dataIndx: 'countryCode',
    minWidth: 0,
    dataType: 'string',
    editable: false,
    hidden: true
  },
  {
    title: '銘柄',
    dataIndx: 'brandName',
    minWidth: 400,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '市場',
    dataIndx: 'marketAbbreviatedName',
    dataType: 'string',
    minWidth: 212,
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '売建可能数量',
    dataIndx: 'positionQuantity',
    dataType: 'string',
    minWidth: 212,
    editable: false,
    hidden: false,
    align: 'right',
    halign: 'center',
    render: function(ui) {
      const rowData = ui.rowData
      if (rowData.positionQuantity === '' || rowData.positionQuantity === null) {
        return '<span>-株</span>'
      } else {
        return '<span>' + ifaFormatUtils.withCommaInteger(rowData.positionQuantity) + '株</span>'
      }
    }
  }
]

</script>

<style lang="scss" scoped>
@import "@/styles/table.scss";
.search-form__item {
  margin: 0 0.2rem;
  width: 240px;
}
.small_input {
  width: 120px;
}
.middle_input {
  width: 130px;
}
.large_input {
  width: 330px;
}
:deep(.form_label) .el-form-item__label {
  width: 100px;
  line-height: 2;
  padding-right: 0;
  margin-right: 30px;
  justify-content: left;
  margin-left: 80px;
}
.form_button {
  margin: 0 2rem 0.8rem 2.5rem;
  padding: 0;
}
:deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
.form-button__wrapper {
  text-align: right;
  margin-top: 1rem;
}
.content-card {
  margin: 0.5rem 1rem;
}
.label_class {
  margin-left: -20px;
}
</style>
