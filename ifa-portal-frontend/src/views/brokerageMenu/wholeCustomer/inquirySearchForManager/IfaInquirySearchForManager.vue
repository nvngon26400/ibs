<template>
  <!-- 画面名：接触履歴（入力）検索 -->
  <div>
    <screen-title :text="formModel.screenTitle.name"></screen-title>
    <el-card class="content-card">
      <el-form
        ref="formObject"
        :model="formModel"
        :inline="true"
        :rules="rules"
      >
        <el-row>
          <el-col :span="23">
            <!-- 画面共通部品：一覧画面の検索条件 -->
            <ifa-multiple-code-search
              ref="commonSearchItem"
              display-pattern="pt1"
              :form="formModel"
              :course-validate="true"
              original-screen-id="SUB020304-01"
            ></ifa-multiple-code-search>
          </el-col>
          <el-col
            :span="1"
            class="right_icon"
          >
            <ifa-balloon-icon
              v-if="setCommentVisible"
              icon-type="info"
            >
              <div v-html="formModel.comment">
              </div>
            </ifa-balloon-icon>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <!-- 問合せ日Fromと問合せ日To -->
            <ifa-date-range-picker
              v-model="formModel.inquiryDate"
              label="問合せ日"
              :picker-options="pickerOptions"
              required
              size="small"
              prop="inquiryDate"
              class="form_label"
            ></ifa-date-range-picker>
          </el-col>
        </el-row>
        <el-row class="el-date-label-row">
          <div class="el-form-item form_label">
            <div class="el-form-item__label"></div>
            <div class="el-form-item__content">※期間は6ヶ月以内を指定してください。（過去全ての履歴を参照いただけます。）</div>
          </div>
        </el-row>
        <el-collapse v-model="formModel.activeCondition">
          <el-collapse-item>
            <template #title>
              <el-icon
                v-if="formModel.activeCondition == 1"
                style="font-size:20px;"
              ><CirclePlus></CirclePlus></el-icon>
              <el-icon
                v-else
                style="font-size:20px;"
              ><Remove></Remove></el-icon>
              <span style="padding-left:10px;">詳細検索条件</span>
            </template>
            <el-row class="detail-input-row">
              <!-- カテゴリ（大） -->
              <ifa-input-select
                v-model="formModel.toiawaseDCd"
                filterable
                label="カテゴリ"
                size="small"
                class="ifa-input__text-default"
                code-list-id="original"
                :original-list="formModel.toiawaseDList"
                @change="toiawaseDCdChangeEvent"
              >
              </ifa-input-select>
              <!-- カテゴリ（中） -->
              <ifa-input-select
                v-model="formModel.toiawaseCd"
                filterable
                size="small"
                class="ifa-input__text-default"
                code-list-id="original"
                :disabled="formModel.toiawaseList.length < 1"
                :original-list="formModel.toiawaseList"
                :visible="setToiawaseListVisible"
                @change="toiawaseCdChangeEvent"
              >
              </ifa-input-select>
              <!-- カテゴリ（小） -->
              <ifa-input-select
                v-model="formModel.toiawaseSCd"
                filterable
                size="small"
                class="ifa-input__text-default"
                code-list-id="original"
                :disabled="formModel.toiawaseSList.length < 1"
                :original-list="formModel.toiawaseSList"
                :visible="setToiawaseSListVisible"
              >
              </ifa-input-select>
            </el-row>
            <el-row class="detail-multi-input-row">
              <!-- クレーム/リクエスト -->
              <ifa-input-select
                v-model="formModel.cr"
                label="クレーム/リクエスト"
                size="small"
                class="ifa-input__text-default"
                code-list-id="original"
                :original-list="formModel.crList"
              >
              </ifa-input-select>
              <!-- 重要度 -->
              <ifa-input-multi-select
                v-model="formModel.juuyoudo"
                label="重要度"
                code-list-id="JUUYOUDO"
                :disp-pattern="1"
                :select-pattern="1"
              ></ifa-input-multi-select>
              <!-- 対応ステータス -->
              <ifa-input-multi-select
                v-model="formModel.taiouSts"
                label="対応ステータス"
                code-list-id="TAIOU_STS"
                :disp-pattern="1"
                :select-pattern="1"
              ></ifa-input-multi-select>
            </el-row>
            <el-row class="detail-text-row">
              <!-- 入力者(ID) -->
              <ifa-input-text
                v-model="formModel.nyuuryokuShaId"
                prop="nyuuryokuShaId"
                label="入力者(ID)"
                size="small"
                :domain="IfaLoginIdDomainModel"
              ></ifa-input-text>
              <!-- 入力者(氏名) -->
              <ifa-input-text
                id="nyuuryokuShaName"
                v-model="formModel.nyuuryokuShaName"
                original-screen-id="SUB020304-01"
                prop="nyuuryokuShaName"
                label="入力者(氏名)"
                size="small"
                :domain="IfaText255DomainModel"
              ></ifa-input-text>
              <span class="name-comment">（部分検索）</span>
            </el-row>
          </el-collapse-item>
        </el-collapse>
        <el-row class="detail-button-row">
          <!-- 「表示」ボタン -->
          <span style="width: 172px; text-align: right;">
            <ifa-button
              text="表示"
              width="90"
              search
              small
              :form="formRef"
              :request-model="IfaInquirySearchForManagerA004RequestModel"
              action-id="SUB020304-01#A004"
              action-type="requestAction"
              @response-handler="responseHandlerA004($event)"
            ></ifa-button>
          </span>
          <!-- 「クリア」ボタン -->
          <ifa-button
            text="クリア"
            width="90"
            color="white"
            small
            action-type="originalAction"
            @app-action-handler="formClearHandlerA005"
          ></ifa-button>
        </el-row>
      </el-form>
    </el-card>
    <el-card
      class="content-card"
      style="width: auto !important;"
    >
      <el-row class="search-button-row">
        <span style="width: 172px; text-align: right;">
          <!-- 「接触履歴詳細へ」ボタン -->
          <ifa-button
            text="接触履歴詳細へ"
            width="120"
            :disabled="!formModel.selRow"
            small
            :request-model="IfaInquirySearchForManagerA004RequestModel"
            action-type="originalAction"
            @app-action-handler="moveToContactDetailHandlerA007($event)"
          ></ifa-button>
        </span>
        <!-- 「CSV出力」ボタン -->
        <ifa-button
          id="btnCsvDownload"
          :disabled="formModel.csvbtn"
          text="CSV出力"
          width="90"
          color="primary"
          small
          action-id="SUB020304-01#A006"
          :request-model="IfaInquirySearchForManagerA006RequestModel"
          csv-file-name="QUESTION_ANSWER_IFA_HIS"
          action-type="outputCsvAction"
          :is-check-csv-download-allowed="true"
          :is-check-csv-download-privacy-confirmation="true"
        ></ifa-button>
      </el-row>
      <grid-table
        ref="pqGrid"
        :options="pqGridOption"
        :auto-refresh="false"
        @click="handleClick"
      ></grid-table>
    </el-card>
    <!-- 問合せ検索初期化 イベント -->
    <ifa-requester
      id="IfaInquirySearchForManagerInitializeA001"
      action-id="SUB020304-01#A001"
      action-type="requestAction"
      @response-handler="initializeA001($event)"
    ></ifa-requester>
    <!-- カテゴリ大	選択イベント -->
    <ifa-requester
      id="ifaInquirySearchForManagerToiawaseDCdChangeA002"
      action-id="SUB020304-01#A002"
      action-type="requestAction"
      :request-model="ifaInquirySearchForManagerA002RequestModel"
      @response-handler="handleToiawaseDCdChange($event)"
    ></ifa-requester>
    <!-- カテゴリ中	選択イベント -->
    <ifa-requester
      id="ifaInquirySearchForManagerToiawaseCdChangeA003"
      action-id="SUB020304-01#A003"
      action-type="requestAction"
      :request-model="ifaInquirySearchForManagerA003RequestModel"
      @response-handler="handleToiawaseCdChange($event)"
    ></ifa-requester>
    <!-- 「全文表示」リンク -->
    <button
      id="show-naiyou-button"
      type="button"
      value=""
      hidden="true"
      @click="showNaiyouA008"
    ></button>
  </div>
</template>

<script>
import $ from 'jquery'
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaButton from '@/components/Button/IfaButton.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { validateDateRangeFromTo } from '@/components/Date/js/IfaDatePickerFunction.js'
import IfaMultipleCodeSearch from '@/components/SearchCondition/IfaMultipleCodeSearch.vue'
import { getMessage } from '@/utils/errorHandler'
import IfaLoginIdDomainModel from '@/resource/domain/IfaLoginIdDomainModel.json'
import IfaText255DomainModel from '@/resource/domain/IfaText255DomainModel.json'
import { IfaInquirySearchForManagerFormModel } from './js/IfaInquirySearchForManagerFormModel'
import { IfaInquirySearchForManagerA002RequestModel } from './js/IfaInquirySearchForManagerA002RequestModel'
import { IfaInquirySearchForManagerA003RequestModel } from './js/IfaInquirySearchForManagerA003RequestModel'
import { IfaInquirySearchForManagerA004RequestModel } from './js/IfaInquirySearchForManagerA004RequestModel'
import { IfaInquirySearchForManagerA006RequestModel } from './js/IfaInquirySearchForManagerA006RequestModel'

// 複数行のカラムフォーマットで生成(「入力日時」、「内容」、「入力者」)
function innerText(inquiryDetailList, rowName, rowIndx) {
  return `<div class="outer-div">${inquiryDetailList.map(function(detail, index) {
    const alignClass = obj.colModel.find(col => col.dataIndx === rowName).align
    if (rowName === 'naiyou') {
      // 「内容」カラムフォーマット生成
      return `<div class="inner-item ${index !== 0 ? 'inner-item-line ' : ''}align-${alignClass}">${detail.naiyou || '-'}${detail.isTruncated ? "<button type='button' class='link-button' onclick='const btn = document.getElementById(\"show-naiyou-button\"); btn.value = \"" + rowIndx + "\"; btn.click(); this.blur();'>全文表示</button>" : ''}</div>`
    } else {
      // 「入力日時」、「入力者」カラムフォーマット生成
      return `<div class="inner-item ${index !== 0 ? 'inner-item-line ' : ''}align-${alignClass}">${detail[rowName] || '-'}</div>`
    }
  }).join(``)}</div>`
}

export default {
  components: {
    GridTable,
    IfaButton,
    screenTitle,
    IfaMultipleCodeSearch
  },
  emits: ['initializeError'],
  data() {
    return {
      // フォームモデル
      formModel: new IfaInquirySearchForManagerFormModel(),
      pqGridOption: getConvertedOption(obj),
      IfaLoginIdDomainModel: IfaLoginIdDomainModel,
      IfaText255DomainModel: IfaText255DomainModel,
      // 選択行データ
      selectedInfo: {},
      formRef: {},
      inquirySearchList: [],
      ifaInquirySearchForManagerA002RequestModel: {},
      ifaInquirySearchForManagerA003RequestModel: {},
      // 日付コンポーネントショートカット
      pickerOptions: {
        // ショートカット="当月", "前月"
        shortcuts: [
          {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date()
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // 当月”指定の場合、endDateに当日を設定
              const endDate = new Date()
              endDate.setHours(0, 0, 0, 0)
              return [startDate, endDate]
            }
          },
          {
            text: '前月',
            value: () => {
              const date = new Date()
              const year = date.getFullYear()
              const month = date.getMonth()
              return [new Date(year, month - 1, 1), new Date(year, month, 0)]
            }
          }
        ]
      },
      rules: {
        inquiryDate: { required: true, trigger: 'change', validator: this.inquiryDateScopeValidator }
      },
      // クリアフォーム
      initialForm: {
        toiawaseDCd: '',
        toiawaseCd: '',
        toiawaseList: [],
        toiawaseSCd: '',
        toiawaseSList: [],
        nyuuryokuShaId: '',
        nyuuryokuShaName: '',
        selectedInfo: {}
      }
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.userAccount
    },
    // A004「表示」ボタンのインプットフォーム設定
    IfaInquirySearchForManagerA004RequestModel() {
      return new IfaInquirySearchForManagerA004RequestModel(
        {
          ...this.formModel,
          juuyoudo: this.convertInputList(this.$_getCodeList('JUUYOUDO', '1', '1'), this.formModel.juuyoudo),
          taiouSts: this.convertInputList(this.$_getCodeList('TAIOU_STS', '1', '1'), this.formModel.taiouSts)
        }
      )
    },
    // A006「CSV出力」ボタンのインプットフォーム設定
    IfaInquirySearchForManagerA006RequestModel() {
      return new IfaInquirySearchForManagerA006RequestModel(
        {
          ...this.formModel,
          juuyoudo: this.convertInputList(this.$_getCodeList('JUUYOUDO', '1', '1'), this.formModel.juuyoudo),
          taiouSts: this.convertInputList(this.$_getCodeList('TAIOU_STS', '1', '1'), this.formModel.taiouSts)
        }
      )
    },
    // バルーンアイコン表示/非表示制御
    setCommentVisible() {
      if (this.formModel.comment) {
        return this.formModel.comment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
    },
    // カテゴリ（中）リスト表示/非表示制御
    setToiawaseListVisible() {
      // カテゴリ（大）が未選択の場合、カテゴリ（中）リストが空の場合は非表示
      if (this.formModel.toiawaseDCd !== '' && this.formModel.toiawaseList?.length > 0) {
        return true
      } else {
        return false
      }
    },
    // カテゴリ（小）リスト表示/非表示制御
    setToiawaseSListVisible() {
      // カテゴリ（中）が未選択の場合、カテゴリ（小）リストが空の場合は非表示
      if (this.formModel.toiawaseCd !== '' && this.formModel.toiawaseSList?.length > 0) {
        return true
      } else {
        return false
      }
    }
  },
  watch: {
    'formModel.inquiryDate': {
      handler(newValue) {
        this.formModel.inquiryDateYmFrom = newValue[0]
        this.formModel.inquiryDateYmTo = newValue[1]
      }
    }
  },
  created() {
    this.pqGridOption.wrap = true
    this.pqGridOption.pageModel = obj.pageModel
    this.pqGridOption._maxColWidth = '100%'
    this.pqGridOption.maxHeight = this.formModel.varVal.maxHeight
    // 「クレーム/リクエスト」リスト初期化設定
    this.formModel.crList = this.$_getCodeList('CREAM_REQUEST', '2', '2')
    this.formModel.crList[0] = { key: '', value: '' }
    //　初期化処理
    this.initialize()
    // 「CSV出力」ボタンの非活性化
    this.formModel.csvbtn = true
    // 「接触履歴詳細へ」ボタンの非活性化
    this.formModel.selRow = false
    // 初期表示時は一定の幅×高さで表示する
    this.formModel.varVal.showNaiyouShort = true
    // render設定
    this.setRender()
  },
  mounted() {
    const vm = this
    this.formRef = this.$refs.formObject
    const pqGridInstance = this.$refs.pqGrid.grid
    // pqgridリフレッシュ処理
    pqGridInstance.on('refresh', function() {
      const splitLineArrs = []
      for (let index = 0; index < obj.colModel.length; index++) {
        if (obj.colModel[index].splitLine) {
          splitLineArrs.push({ index: index, colName: obj.colModel[index].dataIndx })
        }
      }
      // 各行をループして、複数行の各項目の高さを取得して、その中で最大の高さを他の行に適用する
      $('.pq-grid-table .pq-grid-row').each(function(index) {
        let fixHeight = true
        if (index === vm.formModel.varVal.selRowIndex) {
          fixHeight = false
        }
        // 各項目の高さを取得する
        const columnDataMap = splitLineArrs.map(item => vm.calculateRowHeight($(this), item, fixHeight)).filter(Boolean)
        if (columnDataMap.length > 0) {
          const itemCount = columnDataMap[0].innerItems.length
          for (let i = 0; i < itemCount; i++) {
            const maxHeight = columnDataMap.reduce((max, data) => {
              return Math.max(max, data.heights[i])
            }, 0)

            // すべての関連列のinnerItemの高さをその位置に設定する
            columnDataMap.forEach(data => {
              data.innerItems.eq(i).height(maxHeight)
            })
          }
        }
      })
      vm.formModel.varVal.selRowIndex = -1
      vm.handleOneRecord()
    })
  },
  methods: {
    // 画面表示項目初期化設定
    initialize() {
      // 「問合せ日From」と「問合せ日To」初期化当日表示
      this.formModel.inquiryDate = [this.formatDate(new Date()), this.formatDate(new Date())]
      // 「クレーム/リクエスト」初期化未選択
      this.formModel.cr = ''
      // 「重要度」初期化全選択
      this.formModel.juuyoudo = this.$_getCodeList('JUUYOUDO', '1', '1').map(item => item.key)
      // 「対応ステータス」初期化全選択
      this.formModel.taiouSts = this.$_getCodeList('TAIOU_STS', '1', '1').map(item => item.key)
    },
    // 画面初期化表示イベント(A001)
    onShow() {
      // 「CSV出力」ボタンの非活性化
      this.formModel.csvbtn = true
      // 「接触履歴詳細へ」ボタンの非活性化
      this.formModel.selRow = false
      this.pqGridOption.dataModel.data = []
      this.$refs['pqGrid'].refreshView(true)
      this.$nextTick(() => {
        if (!this.formModel.toiawaseDList || this.formModel.toiawaseDList.length === 0) {
          document.getElementById('IfaInquirySearchForManagerInitializeA001').click()
        }
      })
    },
    // 初期化表示
    initializeA001(response) {
      Object.assign(this.formModel, response.dataList[0])
      // カテゴリリスト(大)取得
      this.formModel.toiawaseDList = response.dataList[0].toiawaseDList.map((item) => ({ key: item.toiawaseCd, value: item.toiawaseMei }))
      this.formModel.toiawaseDList.unshift({ key: '', value: '' })
    },
    // カテゴリリスト(大)ドロップダウンのonchangeイベント(A002)
    toiawaseDCdChangeEvent() {
      this.formModel.toiawaseCd = ''
      this.formModel.toiawaseSCd = ''
      this.formModel.toiawaseSList = []
      this.ifaInquirySearchForManagerA002RequestModel = new IfaInquirySearchForManagerA002RequestModel(
        {
          toiawaseDCd: this.formModel.toiawaseDCd,
          toiawaseDMei: this.displayToiawaseMei(this.formModel.toiawaseDList, this.formModel.toiawaseDCd)
        }
      )
      // 問合せカテゴリリスト（中）取得イベント
      this.$nextTick(() => {
        document.getElementById('ifaInquirySearchForManagerToiawaseDCdChangeA002').click()
      })
    },
    // カテゴリリスト(大)ドロップダウンのonchangeイベント応答処理
    // カテゴリ中のリストを取得する。カテゴリ大が未選択の場合は空のリストとする
    // カテゴリ中の選択状態を未選択に戻って、リストを設定する
    handleToiawaseDCdChange(res) {
      let toiawaseList = []
      if (res?.dataList && res?.dataList[0]?.toiawaseList?.length > 0) {
        toiawaseList = res.dataList[0].toiawaseList.map(item => ({
          key: item.toiawaseCd,
          value: item.toiawaseMei
        }))
        toiawaseList.unshift({ key: '', value: '' })
      }
      this.formModel.toiawaseList = toiawaseList
    },
    // カテゴリリスト(中)ドロップダウンのonchangeイベント(A003)
    toiawaseCdChangeEvent() {
      this.formModel.toiawaseSCd = ''
      this.ifaInquirySearchForManagerA003RequestModel = new IfaInquirySearchForManagerA003RequestModel(
        {
          toiawaseDCd: this.formModel.toiawaseDCd,
          toiawaseDMei: this.displayToiawaseMei(this.formModel.toiawaseDList, this.formModel.toiawaseDCd),
          toiawaseCd: this.formModel.toiawaseCd,
          toiawaseMei: this.displayToiawaseMei(this.formModel.toiawaseList, this.formModel.toiawaseCd)
        }
      )
      // 問合せカテゴリリスト（小）取得イベント
      this.$nextTick(() => {
        document.getElementById('ifaInquirySearchForManagerToiawaseCdChangeA003').click()
      })
    },
    // カテゴリリスト(中)ドロップダウンのonchangeイベント応答処理
    // カテゴリ小のリストを取得する。カテゴリ中が未選択の場合は空のリストとする
    // カテゴリ小の選択状態を未選択に戻って、リストを設定する
    handleToiawaseCdChange(res) {
      let toiawaseSList = []
      if (res?.dataList && res?.dataList[0]?.toiawaseSList?.length > 0) {
        toiawaseSList = res.dataList[0].toiawaseSList.map(item => ({
          key: item.toiawaseCd,
          value: item.toiawaseMei
        }))
        toiawaseSList.unshift({ key: '', value: '' })
      }
      this.formModel.toiawaseSList = toiawaseSList
    },
    // 接触履歴一覧の「表示」イベント(A004)
    responseHandlerA004: function(data) {
      // 「接触履歴詳細へ」ボタンの非活性化
      this.formModel.selRow = false
      this.formModel.varVal.recordSize = 0
      // 一覧へのデータの反映
      if (data.dataList.length > 0 && data.dataList[0].inquirySearchList.length > 0) {
        this.formModel.varVal.recordSize = data.dataList[0].inquirySearchList.length
        this.inquirySearchList = data.dataList[0].inquirySearchList
        for (let i = 0; i < this.inquirySearchList.length; i++) {
          this.inquirySearchList[i].rowNo = i
          this.inquirySearchList[i].inquiryDetailList.forEach((item) => {
            // 「内容」を切り捨てる(一定の幅×高さで表示の「内容」)
            this.truncateNaiyou(item, this.formModel.varVal.widthShort, this.formModel.varVal.maxLines, 'Short')
            // 「内容」を切り捨てる(拡大表示の「内容」)
            this.truncateNaiyou(item, this.formModel.varVal.widthLong, this.formModel.varVal.maxLines, 'Long')
          })
        }
        // 「内容」は一定の幅×高さで表示する
        this.inquirySearchList.forEach(item => {
          item.inquiryDetailList.forEach(item => {
            item.naiyou = item.naiyouShort
            item.isTruncated = item.isTruncatedShort
          })
        })
        // CSVボタン活性化の権限
        //  - 権限1(SBI証券本店)
        //  - 権限2(SBI証券支店)
        //  - 権限3(仲介業者　-　内部管理責任者)
        if (this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2' || this.userInfo.medUsers.privId === '3') {
          this.formModel.csvbtn = false // CSV出力ボタンの活性化
        }
        if (this.formModel.varVal.recordSize !== 1) {
          this.pqGridOption.dataModel.data = this.inquirySearchList
        } else {
          this.pqGridOption.dataModel.data = [...this.inquirySearchList, { __emptyRow: true, 'inquiryDetailList': [{ 'nyuuryokuNichiji': null, 'naiyou': null, 'nyuuryokuSha': null }] }]
        }
        // データ変換、内容のフィールド切り取り
        obj.colModel.find(item => item.dataIndx === 'naiyou').width = this.formModel.varVal.widthShort
      } else {
        this.formModel.csvbtn = true // CSV出力ボタンの非活性化
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['pqGrid'].refreshView(true)
      this.handleOneRecord()
    },
    // フォームクリアイベント(A005)
    formClearHandlerA005() {
      // 通知ポップアップを消去する
      this.$store.dispatch('notifications/resetState')
      this.$refs['formObject'].clearValidate()
      Object.assign(this.formModel, this.initialForm)
      this.$refs.commonSearchItem.formClear()
      this.initialize()
    },
    // 「接触履歴詳細画面へ」ボタン押下のイベント(A007)
    moveToContactDetailHandlerA007: function() {
      const param = {}
      param.nextActionId = 'SUB020304-01#A007' // 遷移先ActionId
      param.butenCode = this.selectedInfo.butenCode // 部店コード
      param.accountNumber = this.selectedInfo.accountNumber // 口座No
      param.ifaToiawaseNo = this.selectedInfo.ifaToiawaseNo // 問合せNo
      // 「接触履歴詳細」画面へ遷移する
      this.$_startShowMenu('SUB0202_0106', param)
    },
    // 「全文表示」リンク押下のイベント(A008)
    // 「全文表示」クリックで、クリックされた「内容」欄を拡大表示する
    // 隠れてしまう列はスクロールバーを動かして表示する
    // 「内容」欄がクリックされた明細以外の「内容」欄も横に広がるが、行の高さは変更しない
    showNaiyouA008() {
      this.formModel.varVal.selRowIndex = JSON.parse(decodeURIComponent(document.getElementById('show-naiyou-button').value))
      // 「全文表示」をクリックすると、レコード行情報は変数selectedInfoを設定する
      this.selectedInfo = this.pqGridOption.dataModel.data[this.formModel.varVal.selRowIndex]
      // 「接触履歴詳細へ」ボタンの活性化
      this.formModel.selRow = true
      let rowIndex = 0
      // 「内容」欄を拡大表示する
      obj.colModel.find(item => item.dataIndx === 'naiyou').width = $('.pq-grid-header').width() && $('.pq-grid-header').width() > this.formModel.varVal.widthLong ? $('.pq-grid-header').width() : this.formModel.varVal.widthLong
      this.pqGridOption.dataModel.data.forEach(item => {
        if (this.formModel.varVal.selRowIndex === rowIndex) {
          // 選択行「内容」欄全文表示
          item.inquiryDetailList.forEach(item => {
            item.naiyou = item.naiyouOrigin
            item.isTruncated = false
          })
        } else {
          // 非選択行の「内容」欄拡大表示
          item.inquiryDetailList.forEach(item => {
            item.naiyou = item.naiyouLong
            item.isTruncated = item.isTruncatedLong
          })
        }
        rowIndex++
      })
      this.formModel.varVal.showNaiyouShort = false
      this.$refs['pqGrid'].refresh()
      if ($('.pq-grid').outerHeight() === this.formModel.varVal.maxHeight) {
        obj.colModel.find(item => item.dataIndx === 'naiyou').width = this.formModel.varVal.widthLong
      } else {
        obj.colModel.find(item => item.dataIndx === 'naiyou').width = $('.pq-grid-header').width()
      }
      this.$refs['pqGrid'].refresh()
      $(this.$refs.pqGrid.$el).pqGrid('scrollColumn', { dataIndx: 'naiyou' })
      this.handleOneRecord()
      $(`.pq-sb-horiz .pq-sb-slider`)[0].style.left = this.formModel.varVal.leftSliderPos + 'px'
      $(`.pq-sb-horiz`).pqScrollBar('drag')
    },
    // 行選択イベント(A008)
    // 明細の「内容」が、元の幅、高さに戻る
    handleClick(ui) {
      // 行を選択すると、「接触履歴詳細」画面遷移ボタンが有効になる
      this.formModel.selRow = true
      this.selectedInfo = ui.rowData
      // 拡大表示の「内容」欄クリックで元の大きさに戻す(元の幅、高さに戻る)
      if (ui.dataIndx === 'naiyou' && !this.formModel.varVal.showNaiyouShort) {
        obj.colModel.find(item => item.dataIndx === 'naiyou').width = this.formModel.varVal.widthShort
        this.pqGridOption.dataModel.data.forEach(item => {
          item.inquiryDetailList.forEach(item => {
            item.naiyou = item.naiyouShort
            item.isTruncated = item.isTruncatedShort
          })
        })
        this.formModel.varVal.showNaiyouShort = true
        this.$refs['pqGrid'].refresh()
        this.handleOneRecord()
      }
    },
    // クエリ結果が1つのデータだけを返す場合のフォーマット処理
    handleOneRecord() {
      if (this.formModel.varVal.recordSize === 1) {
        $(`table tr[pq-row-indx="1"]`).css('display', 'none')
        $(`.pq-pager`).pqPager('option', 'totalRecords', 1)
      }
    },
    // 問合せ日Fromと問合せ日Toの期間は6ヶ月指定チェック
    inquiryDateScopeValidator(_, value, callback) {
      // 以下の条件の時エラー
      // 問合せ日Fromと問合せ日Toの期間は6ヶ月より大きい
      if (validateDateRangeFromTo(value, 6, false)) {
        callback(getMessage('errors.dateRange', ['問合せ日From～Toの期間指定', '6ヶ月']))
        return
      }
      // OK
      callback()
    },
    // 日付を文字列に変換する
    formatDate(date, f = false) {
      date = date || new Date()
      return date.getFullYear() + '/' +
        (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
        ('0' + date.getDate()).slice(-2)
    },
    // 問合せ名取得
    displayToiawaseMei(toiawaseList, toiawaseCd) {
      const item = toiawaseList.find(item => item.key === toiawaseCd)
      return item ? item.value : ''
    },
    // 問合せリストフォーマット変換
    convertInputList(jsonArray, selectedIds) {
      return jsonArray.map(item => {
        const idStr = item.key
        const isSelected = selectedIds.includes(idStr)
        return {
          id: idStr,
          isSelected: isSelected
        }
      })
    },
    // 「内容」を切り捨てる
    truncateNaiyou(obj, divWidth, maxLines, shortCut) {
      if (!obj.naiyou) {
        obj['isTruncated' + shortCut] = false
        obj['naiyou' + shortCut] = ''
        obj.naiyouOrigin = ''
        return
      }
      obj.naiyouOrigin = obj.naiyou
      // 表示用の幅設定(px)
      let availableWidth = divWidth - 10 - 1
      const canvas = document.createElement('canvas')
      const ctx = canvas.getContext('2d')
      // divと同じフォントスタイルを設定
      ctx.font = this.formModel.varVal.font
      const result = []
      let remainingString = obj.naiyou
      // 使用済み行数カウンター
      let lineCount = 0
      while (remainingString.length > 0 && lineCount < maxLines) {
        let currentLine = ''
        if (lineCount === maxLines - 1) {
          availableWidth -= 52
        }
        // 残りの内容
        while (remainingString.length > 0) {
          const nextChar = remainingString[0]
          // 改行コードに設定したら、現在の行を終了して、新しい行を開始する
          if (nextChar === '\n') {
            currentLine += '\n'
            // 改行コードを除いた残りの文字列
            remainingString = remainingString.substring(1)
            break
          } else {
            // 次の文字を追加できるかどうか、利用可能な幅を超えないかを確認する
            const tempText = currentLine + nextChar
            if (ctx.measureText(tempText).width <= availableWidth) {
              currentLine += nextChar
              remainingString = remainingString.substring(1)
            } else {
              currentLine += '\n'
              break // これ以上文字を追加できない場合、現在の行を終了
            }
          }
        }
        result.push(currentLine)
        lineCount++
      }
      // 切り捨てられたかどうかを判断：残りの内容が有りの場合、切り捨てられている
      obj['isTruncated' + shortCut] = remainingString.length > 0
      obj['naiyou' + shortCut] = result.join(``)
    },
    // 各行の高さを自動的に計算して個別に列を設定する
    calculateRowHeight($row, item) {
      const $td = $row.find(`td[pq-col-indx="${item.index}"]`)
      if ($td.length === 0) {
        return
      }
      const $outerDiv = $td.find('.outer-div')
      const $innerItems = $outerDiv.find('.inner-item')
      if ($innerItems?.length > 0) {
        const heights = []
        $innerItems.each(function() {
          const height = $(this)[0].clientHeight
          heights.push(height)
        })
        return {
          innerItems: $innerItems,
          heights: heights
        }
      }
    },
    // render設定
    setRender() {
      obj.colModel.forEach((column) => {
        if (column.splitLine) {
          // 複数行表示の列設定
          column.render = function(item) {
            return {
              cls: 'pg-grid-cell',
              text: innerText(item.rowData.inquiryDetailList, item.dataIndx, item.rowIndx)
            }
          }
        } else if (column.dataIndx === 'toiawaseMei') {
          // 「問合せ名」設定
          column.render = function(item) {
            const toiawaseMei = []
            if (item.rowData.toiawaseDMei) toiawaseMei.push(item.rowData.toiawaseDMei)
            if (item.rowData.toiawaseMei) toiawaseMei.push(item.rowData.toiawaseMei)
            if (item.rowData.toiawaseSMei) toiawaseMei.push(item.rowData.toiawaseSMei)
            return `${toiawaseMei.join('　') || '-'}`
          }
        }
      })
    }
  }
}
const obj = {
  width: 0,
  height: 0,
  title: '接触履歴（入力）一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}
obj.colModel = [
  /* eslint-disable no-multi-spaces */
  { dataIndx: 'butenCode',        width: 25,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: '部店' },
  { dataIndx: 'accountNumber',    width: 70,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'right',  title: '口座番号' },
  { dataIndx: 'nameKanji',        width: 110, valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: '顧客名（漢字）' },
  { dataIndx: 'nameKana',         width: 110, valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: '顧客名（カナ）' },
  { dataIndx: 'toiawaseMei',      width: 140, valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: 'カテゴリ' },
  { dataIndx: 'cr',               width: 85,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: 'クレーム/リクエスト' },
  { dataIndx: 'juuyoudo',         width: 40,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: '重要度' },
  { dataIndx: 'houkou',           width: 55,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: '入電方向' },
  { dataIndx: 'taiouSts',         width: 70,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: '対応ステータス' },
  { dataIndx: 'nyuuryokuNichiji', width: 144, valueType: 'date',   sortable: false, splitLine: true,  halign: 'center', align: 'center', title: '入力日時' },
  { dataIndx: 'naiyou',           width: 138, valueType: 'string', sortable: false, splitLine: true,  halign: 'center', align: 'left',   title: '内容' },
  { dataIndx: 'nyuuryokuSha',     width: 90,  valueType: 'string', sortable: false, splitLine: true,  halign: 'center', align: 'left',   title: '入力者' },
  { dataIndx: 'brokerCode',       width: 70,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'center', title: '仲介業者コード' },
  { dataIndx: 'brokerName',       width: 90,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: '仲介業者名' },
  { dataIndx: 'branchCode',       width: 55,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'center', title: '支店コード' },
  { dataIndx: 'branchName',       width: 90,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: '支店名' },
  { dataIndx: 'brokerChargeCode', width: 55,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'center', title: '営業員コード' },
  { dataIndx: 'brokerChargeName', width: 90,  valueType: 'string', sortable: true,  splitLine: false, halign: 'center', align: 'left',   title: '営業員名' },
  { title: '', dataIndx: 'ifaToiawaseNo', hidden: true }
  /* eslint-enable no-multi-spaces */
]
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>

<style lang="scss" scoped>
.right_icon {
  text-align: center;
  position: inherit;
  padding-top: 12px;
}
.content-card {
  margin: 0.5rem 1rem;
}
:deep(.form_label) .el-form-item__label {
  width: 160px;
}
:deep(.el-collapse-item__header){
  padding-left: 20px;
  background-color: transparent;
}
:deep(.el-collapse-item__header:hover){
  color: #409eff;
  background-color: #f9fafc;
}
.inner-line {
  margin-bottom:1rem;
  margin-top:1rem;
  margin-left: 0.5rem;
}
:deep(.el-form-item__error) {
  white-space: nowrap
}
:deep(.el-date-label-row) {
  margin-top: -20px;
}
.el-date-label-row :deep(.el-form-item) {
  margin-bottom: -10px !important;
}
:deep(.el-form-item) {
  margin-bottom: 0.8rem !important;
  margin-right: 10px;
}
.detail-input-row {
  margin-top: 0.8rem;
}
.detail-input-row :deep(.el-select) {
  width: 276px;
  margin-right: 80px;
}
.detail-multi-input-row :deep(.el-select), .detail-text-row :deep(.el-input) {
  width: 180px;
}
.detail-text-row :deep(div) {
  margin-bottom: 0px !important;
}
.search-button-row {
  margin-bottom: 0.4rem;
}
:deep(.el-collapse) {
  margin: 0.8rem 0 0.8rem 0;
}
.name-comment {
  display: flex;
  align-items: center;
  margin-left: 20px;
}
:deep(.pg-grid-cell) {
  padding-left: 0;
  padding-right: 0;
}
:deep(.outer-div) {
  margin-top: -5px;
  margin-bottom: -5px;
}
:deep(.inner-item) {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  word-break: break-word;
  white-space: pre-wrap;
  padding: 2.5px 5px;
}
:deep(.align-left),
:deep([data-halign="left"]) {
  justify-content: flex-start !important;
}
:deep(.align-right),
:deep([data-halign="right"]) {
  justify-content: flex-end !important;
}
:deep(.inner-item-line) {
  border-top: 1px solid #d0d0d0;
}
:deep(.link-button) {
  position: absolute;
  right: 5px;
  bottom: 5px;
  background-color: transparent;
  border: none;
  cursor: pointer;
  color: #3399FF;
  text-decoration: none;
  padding: 7px 2px 7px 2px;
  margin-bottom: -1px;
  line-height: 0.5;
  font-size: 12px;
}
:deep(.link-button:hover) {
  color: #66CCFF; /* ホバー時の色の変化 */
  text-decoration: underline;
}
</style>
