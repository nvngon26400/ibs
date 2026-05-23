<template>
  <div>
    <screen-title :text="form.screenTitle"></screen-title>
    <div class="caption_card">
      <el-row>
        <el-row>
          <el-col
            :span="23"
            style="padding-top: 8px; text-align: right; margin-left: -30px;"
          >
            <span>更新日時：</span>
            <span>&nbsp;{{ $_out($_getFormattedDateTimeValue(form.updateTime)) }}</span>
          </el-col>
          <el-col :span="1">
            <div style="text-align: right; margin-right: -32px;">
              <ifa-button
                id="btnUpdate"
                ref="updateButton"
                text="更新"
                small
                color="primary"
                icon="RefreshRight"
                action-type="requestAction"
                action-id="SUB0202_010204-01#A004"
                :request-model="A004RequestModel"
                @response-handler="updateA004($event)"
              ></ifa-button>
            </div>
          </el-col>
        </el-row>
      </el-row>

      <el-row>
        <el-col :span="17">
          <span style="margin-left:0.5rem;font-weight:bold">代用有価証券・担保貸株評価額合計</span>
        </el-col>
      </el-row>

      <el-row>
        <el-table
          :data="totalTable"
          :span-method="objectSpanMethod"
          :cell-class-name="tableCellClassNamTotal"
          class="__table_body total_table"
        >
          <el-table-column
            prop="deliveryDateLabel"
            label="受渡日"
            :min-width="160"
          ></el-table-column>
          <el-table-column :min-width="180">
            <template #header>
              <div>{{ $_getFormattedDateValue(totalLabel[0]) }}</div>
              <div>(当日)</div>
            </template>
            <template #default="scope">
              <el-link
                v-if="scope.row.index < 2 && scope.row[0] > 0"
                :underline="false"
                disable-transitions
                class="__link_text"
                @click="detailA003(scope.row.index, 0)"
              >{{ $_withCommaInteger($_out(scope.row[0])) }}</el-link>
              <div v-else-if="scope.row.index !== 2">{{ $_withCommaInteger($_out(scope.row[0])) }}</div>
            </template>
          </el-table-column>
          <el-table-column :min-width="180">
            <template #header>
              <div>{{ $_getFormattedDateValue(totalLabel[1]) }}</div>
              <div>(1営業日後)</div>
            </template>
            <template #default="scope">
              <el-link
                v-if="scope.row.index < 2 && scope.row[1] > 0"
                :underline="false"
                disable-transitions
                class="__link_text"
                @click="detailA003(scope.row.index, 1)"
              >{{ $_withCommaInteger($_out(scope.row[1])) }}</el-link>
              <div v-else-if="scope.row.index !== 2">{{ $_withCommaInteger($_out(scope.row[1])) }}</div>
            </template>
          </el-table-column>
          <el-table-column :min-width="180">
            <template #header>
              <div>{{ $_getFormattedDateValue(totalLabel[2]) }}</div>
              <div>(2営業日後)</div>
            </template>
            <template #default="scope">
              <el-link
                v-if="scope.row.index < 2 && scope.row[2] > 0"
                :underline="false"
                disable-transitions
                class="__link_text"
                @click="detailA003(scope.row.index, 2)"
              >{{ $_withCommaInteger($_out(scope.row[2])) }}</el-link>
              <div v-else-if="scope.row.index !== 2">{{ $_withCommaInteger($_out(scope.row[2])) }}</div>
            </template>
          </el-table-column>
          <el-table-column :min-width="180">
            <template #header>
              <div>{{ $_getFormattedDateValue(totalLabel[3]) }}</div>
              <div>(3営業日後)</div>
            </template>
            <template #default="scope">
              <el-link
                v-if="scope.row.index < 2 && scope.row[3] > 0"
                :underline="false"
                disable-transitions
                class="__link_text"
                @click="detailA003(scope.row.index, 3)"
              >{{ $_withCommaInteger($_out(scope.row[3])) }}</el-link>
              <div v-else-if="scope.row.index !== 2">{{ $_withCommaInteger($_out(scope.row[3])) }}</div>
            </template>
          </el-table-column>
          <el-table-column :min-width="180">
            <template #header>
              <div>{{ $_getFormattedDateValue(totalLabel[4]) }}</div>
              <div>(4営業日後)</div>
            </template>
            <template #default="scope">
              <el-link
                v-if="scope.row.index < 2 && scope.row[4] > 0"
                :underline="false"
                disable-transitions
                class="__link_text"
                @click="detailA003(scope.row.index, 4)"
              >{{ $_withCommaInteger($_out(scope.row[4])) }}</el-link>
              <div v-else-if="scope.row.index !== 2">{{ $_withCommaInteger($_out(scope.row[4])) }}</div>
            </template>
          </el-table-column>
          <el-table-column :min-width="180">
            <template #header>
              <div>{{ $_getFormattedDateValue(totalLabel[5]) }}～</div>
              <div>(5営業日後～)</div>
            </template>
            <template #default="scope">
              <el-link
                v-if="scope.row.index < 2 && scope.row[5] > 0"
                :underline="false"
                disable-transitions
                class="__link_text"
                @click="detailA003(scope.row.index, 5)"
              >{{ $_withCommaInteger($_out(scope.row[5])) }}</el-link>
              <div v-else-if="scope.row.index !== 2">{{ $_withCommaInteger($_out(scope.row[5])) }}</div>
            </template>
          </el-table-column>
        </el-table>
      </el-row>

      <pre style="margin-top:1rem;font-family:メイリオ">
      ※代用有価証券（株式）は、当社の定める優先市場で評価し、委託保証金率の計算を受渡日ベースで算出いたします。
      ※更新日時は、株式に関しては夕方値洗い処理時および朝方となり、投資信託に関しては朝方のみになります。
         ただし、受渡日が2営業日後以降はお取引状況（約定タイミング）により更新いたします。
      ※夕方値洗い処理時に、受渡日の日付を翌営業日に切り替えいたします。
      ※投資信託の評価単価は、10,000口あたりの基準価格を表示いたします。</pre>

      <pre style="margin-top:1rem;font-family:メイリオ">
      ※担保貸株サービスをご利用の場合、代用有価証券の保有残高および、新規買付分につきましては、
        自動で担保貸株サービスが適用されます。担保貸株として貸出を希望されない銘柄につきましては、
        貸株設定変更画面より「貸株対象外、残りを担保貸株設定」もしくは、「担保貸株を利用しない」を銘柄毎にご設定ください。</pre>

      <div style="margin:2rem 0 0 0.5rem;font-weight:bold">代用有価証券-受渡日別サマリー</div>

      <el-row style="margin-top: 1rem">
        <el-row>
          <el-col
            :span="3"
            style="margin-left:2rem; margin-top:0.5rem; margin-right: -30px;"
          >
            <span>表示基準日（受渡日）</span>
          </el-col>
          <el-col :span="5">
            <el-select
              v-model="form.displayBaseDate"
              size="small"
              @change="$refs['updateButton'].execute()"
            >
              <el-option
                v-for="item in form.displayBaseDateList"
                :key="item.index"
                :label="item"
                :value="item"
              ></el-option>
            </el-select>
          </el-col>
          <el-col
            :span="2"
            style="margin-top:0.5rem; margin-right: -43px;"
          >
            <span>代用種別</span>
          </el-col>
          <el-col :span="12">
            <ifa-input-select
              :model-value="form.collateralClass"
              code-list-id="COLLATERAL_CLASS"
              :disp-pattern="1"
              :select-pattern="1"
              :clearable="false"
              size="small"
              @update:model-value="setCollateralClass"
              @change="$refs['updateButton'].execute()"
            >
            </ifa-input-select>
          </el-col>
        </el-row>
      </el-row>

      <el-row style="margin-top: 1rem;font-size:12px;">
        <el-row v-if="form.collateralClass === '1'">
          <el-col
            :span="4"
            class="__evaluation_price_total_title"
          >
            <span>国内株式評価額</span>
          </el-col>
          <el-col
            :span="4"
            class="__evaluation_price_total_data"
          >
            <span>{{ $_out($_withCommaInteger(form.domesticStockCollateralValuationTotal)) }}</span>
          </el-col>
        </el-row>
        <el-row v-if="form.collateralClass === '2'">
          <el-col
            :span="4"
            class="__evaluation_price_total_title"
          >
            <span>投資信託評価額</span>
          </el-col>
          <el-col
            :span="4"
            class="__evaluation_price_total_data"
          >
            <span>{{ $_out($_withCommaInteger(form.domesticMutualCollateralValuationTotal)) }}</span>
          </el-col>
        </el-row>
        <el-row>
          <el-col
            :span="4"
            class="__evaluation_price_total_title"
          >
            <span>代用有価証券評価額合計</span>
          </el-col>
          <el-col
            :span="4"
            class="__evaluation_price_total_data"
          >
            <span>{{ $_out($_withCommaInteger(form.alternativeSecuritiesTotal)) }}</span>
          </el-col>
        </el-row>
      </el-row>

      <div v-show="form.detailList.length">
        <el-row style="margin-top: 1rem;">
          <el-table
            :data="summaryData"
            height="250"
            :highlight-current-row="true"
            :cell-class-name="tableCellClassNameDetail"
            :scrollbar-always-on="true"
            class="__table_body"
          >
            <el-table-column
              prop="securityClass"
              label="商品分類"
              :min-width="80"
            ></el-table-column>
            <el-table-column
              prop="brandCode"
              label="銘柄コード"
              :min-width="90"
            ></el-table-column>
            <el-table-column
              prop="brandName"
              label="銘柄名"
              :min-width="230"
            ></el-table-column>
            <el-table-column
              prop="depositType"
              label="預り区分"
              :min-width="80"
            ></el-table-column>
            <el-table-column
              prop="contPosition"
              label="株数/口数"
              :min-width="180"
            ></el-table-column>
            <el-table-column
              prop="valuationPrice"
              label="評価単価"
              :min-width="170"
            ></el-table-column>
            <el-table-column
              prop="collateralAssessment"
              label="掛目(%)"
              :min-width="80"
            ></el-table-column>
            <el-table-column
              prop="collateralValuation"
              label="代用評価額"
              :min-width="180"
            ></el-table-column>
            <el-table-column
              prop="securityStockLendingClassification"
              label="担保貸株"
              :min-width="80"
            ></el-table-column>
          </el-table>
        </el-row>
      </div>
      <div
        v-show="form.detailList.length == 0"
        style="margin-top:1rem"
      >
        <span>{{ form.warningMessage }}</span>
      </div>

      <!-- ダイアログ -->
      <ifa-collateral-security-deliver-in-out-detail
        ref="detail"
        v-model="dialogVisible"
        :customer-info="customerInfo"
        @close-modal="handleCloseModal"
      ></ifa-collateral-security-deliver-in-out-detail>
    </div>

    <ifa-requester
      id="IfaCollateralSecurityListA001"
      action-id="SUB0202_010204-01#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="errorHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaCollateralSecurityDeliverInOutDetailA001"
      action-id="SUB0202_010204-02#A001"
      action-type="requestAction"
      :request-model="A003RequestModel"
      @response-handler="responseHandlerDetailA003($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaCollateralSecurityDeliverInOutDetail from './IfaCollateralSecurityDeliverInOutDetail'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { IfaCollateralSecurityListFormModel } from './js/IfaCollateralSecurityListFormModel'
import { IfaCollateralSecurityListA001RequestModel } from './js/IfaCollateralSecurityListA001RequestModel'
import { IfaCollateralSecurityListA003RequestModel } from './js/IfaCollateralSecurityListA003RequestModel'
import { IfaCollateralSecurityListA004RequestModel } from './js/IfaCollateralSecurityListA004RequestModel'

export default {
  components: {
    IfaCollateralSecurityDeliverInOutDetail,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      dialogVisible: false,
      form: new IfaCollateralSecurityListFormModel(),
      A003RequestModel: {},
      totalLabel: [],
      summaryData: [],
      totalTable: [{
        index: 0,
        deliveryDateLabel: '代用有価証券入庫',
        0: '', // 当日
        1: '', // 1営業日後
        2: '', // 2営業日後
        3: '', // 3営業日後
        4: '', // 4営業日後
        5: '' // 5営業日後
      }, {
        index: 1,
        deliveryDateLabel: '代用有価証券出庫',
        0: '', // 当日
        1: '', // 1営業日後
        2: '', // 2営業日後
        3: '', // 3営業日後
        4: '', // 4営業日後
        5: '' // 5営業日後
      }, {
        // ダミー行
        index: 2,
        deliveryDateLabel: '',
        0: '', // 当日
        1: '', // 1営業日後
        2: '', // 2営業日後
        3: '', // 3営業日後
        4: '', // 4営業日後
        5: '' // 5営業日後
      }, {
        index: 3,
        deliveryDateLabel: '代用有価証券評価額合計',
        0: '', // 当日
        1: '', // 1営業日後
        2: '', // 2営業日後
        3: '', // 3営業日後
        4: '', // 4営業日後
        5: '' // 5営業日後
      }]
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    A001RequestModel() {
      return new IfaCollateralSecurityListA001RequestModel()
    },
    A004RequestModel() {
      return new IfaCollateralSecurityListA004RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      document.getElementById('IfaCollateralSecurityListA001').click()
    },
    responseHandlerInitializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.setTables()
      this.form.collateralClass = '0' // 代用種別に初期値０（すべて）を設定
      this.form.displayBaseDateList = this.form.settlementDateList.map(item => item.displayBaseDate)
      this.form.displayBaseDate = this.form.displayBaseDateList[0] // 表示基準日に初期値（当日）を設定
      this.form.warningMessage = this.form.displayBaseDate + '基準において、お客様の代用有価証券情報はございません。'
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    detailA003(deliverInOut, date) {
      this.A003RequestModel = new IfaCollateralSecurityListA003RequestModel({
        settlementDate: this.totalLabel[date],
        displayBaseDate: this.form.settlementDateList[date].displayBaseDate,
        deliverInOutClassification: deliverInOut === 0 ? '2' : deliverInOut === 1 ? '1' : ''
      })
      document.getElementById('IfaCollateralSecurityDeliverInOutDetailA001').click()
    },
    responseHandlerDetailA003(response) {
      this.$refs['detail'].onShow(response.dataList[0])
      this.dialogVisible = true
    },
    updateA004(response) {
      Object.assign(this.form, response.dataList[0])
      this.setTables()
      this.form.displayBaseDateList = this.form.settlementDateList.map(item => item.displayBaseDate)
      this.form.warningMessage = this.form.displayBaseDate + '基準において、お客様の代用有価証券情報はございません。'
    },
    setTables() {
      // 合計部
      this.totalLabel = []
      this.form.settlementDateList.forEach((item, index) => {
        this.totalLabel.push(item.settlementDate)
        this.totalTable[0][index] = item.collateralSecurityDeliverIn
        this.totalTable[1][index] = item.collateralSecurityDeliverOut
        this.totalTable[3][index] = this.$_out(item.alternativeSecuritiesTotal)
      })
      // サマリー部
      this.summaryData = []
      this.summaryData = this.form.detailList
      this.summaryData.forEach(item => {
        item.securityClass = this.$_out(item.securityClass)
        item.brandCode = this.$_out(item.brandCode)
        item.brandName = this.$_out(item.brandName)
        item.depositType = this.$_out(item.depositType)
        item.contPosition = this.$_out(this.$_withCommaInteger(item.contPosition))
        item.valuationPrice = this.$_out(this.$_withCommaNoneZeroPadding(item.valuationPrice))
        item.collateralAssessment = this.$_out(this.$_withCommaInteger(item.collateralAssessment))
        item.collateralValuation = this.$_out(this.$_withCommaInteger(item.collateralValuation))
        item.securityStockLendingClassification = this.$_out(item.securityStockLendingClassification)
      })
    },
    setCollateralClass(value) {
      this.form.collateralClass = value
    },
    // 戻るボタン
    handleCloseModal() {
      this.dialogVisible = false
    },
    // テーブル整形処理
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      // ダミー行は一つのセルに結合する
      if (columnIndex === 0 && rowIndex === 2) {
        return {
          rowspan: 1,
          colspan: 7
        }
      }
    },
    // テーブルスタイル調整（代用有価証券評価額合計）
    tableCellClassNamTotal({ row, column, rowIndex, columnIndex }) {
      // ダミー行
      if (rowIndex === 2) {
        return '__dummy_row_height'
      }

      // 受渡日
      if (columnIndex === 0) {
        return '__left'
      // 当日
      } else if (columnIndex === 1) {
        return '__right'
      // 1営業日後
      } else if (columnIndex === 2) {
        return '__right'
      // 2営業日後
      } else if (columnIndex === 3) {
        return '__right'
      // 3営業日後
      } else if (columnIndex === 4) {
        return '__right'
      // 4営業日後
      } else if (columnIndex === 5) {
        return '__right'
      // 5営業日後～
      } else if (columnIndex === 6) {
        return '__right'
      }
    },
    // テーブルスタイル調整（代用有価証券-受渡日別サマリー）
    tableCellClassNameDetail({ row, column, rowIndex, columnIndex }) {
      // 商品分類
      if (columnIndex === 0) {
        return '__center'
      // 銘柄コード
      } else if (columnIndex === 1) {
        return '__left'
      // 銘柄名
      } else if (columnIndex === 2) {
        return '__left'
      // 預り区分
      } else if (columnIndex === 3) {
        return '__center'
      // 株数/口数
      } else if (columnIndex === 4) {
        return '__right'
      // 評価単価
      } else if (columnIndex === 5) {
        return '__right'
      // 掛目(%)
      } else if (columnIndex === 6) {
        return '__right'
      // 代用評価額
      } else if (columnIndex === 7) {
        return '__right'
      // 担保貸株
      } else if (columnIndex === 8) {
        return '__center'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/table.scss";
._bold_black_m {
  font-size: 16px;
  font-weight: 700;
  color: #606266;
}
.__evaluation_price_total_title {
  color: #fff;
  background-color: #004485;
  padding: 0.2rem 0 0.2rem 1rem;
  font-weight:bold;
  box-shadow: 1px 2px 2px #ddd;
  border: 1px solid #eee;
}
.__evaluation_price_total_data {
  text-align: right;
  padding: 0.2rem 1rem 0.2rem 0;
  box-shadow: 1px 2px 2px #ddd;
  border: 1px solid #eee;
}
.__link_text {
  text-decoration:underline;
  text-underline-offset:0.1em;
  font-size:12px;
  color: #092987;
}
.__table_body {
  width: 100%;
  margin-top: 1rem;
  font-size:12px;
  box-shadow: 1px 2px 2px #ddd;
}
.button__wrapper {
   margin-top:0.5rem;
   display: flex;
   justify-content: flex-end;
   padding:0 2rem 0.2rem 0;
}
:deep(.el-table) .__dummy_row_height {
  height: 0;
  padding: 2px 0 2px 0;
}
:deep(.el-table) .__center {
  text-align: center;
}
:deep(.el-table) .__left {
  text-align: left;
}
:deep(.el-table) .__right {
  text-align: right;
}
:deep(.el-table) th {
  text-align: center;
  font-size: 12px;
  border: 1px solid #eee;
}
:deep(.el-table) td {
  padding: 6px 0 6px 0;
  border-collapse: collapse;
  border: 1px solid #eee;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
:deep(.total_table .el-scrollbar__bar) {
    &.is-horizontal {
      display: none !important;
    }
}
</style>
