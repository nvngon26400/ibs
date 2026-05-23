<template>
  <div class="main">
    <h3>●通知テスト</h3>

    <hr>
    <h4>
      【notifyMessage】
      <ifa-button
        text="NOTIFY"
        small
        action-type="originalAction"
        @app-action-handler="notification"
      ></ifa-button>
      <span class="label">操作方法:</span><span class="value">1回目: 改行コードで改行､ 2回目: &lt;BR&gt;で改行､ 3回目: &lt;BR/&gt;で改行､ 4回目: 改行なし</span>
    </h4>

    <hr>
    <h4>
      【initializeError】
      <ifa-button
        text="初期化エラー"
        small
        action-type="originalAction"
        @app-action-handler="initializeError"
      ></ifa-button>
      <span class="label">操作方法:</span><span class="value">クリックすると初期化エラー画面に切り替わります｡</span>
    </h4>

    <hr>
    <h3 style="display: flex;">
      ●入力テスト
      <ifa-button
        text="RESET"
        small
        action-type="originalAction"
        @app-action-handler="resetAll"
      ></ifa-button>
      <span style="margin-left: 0.5rem;"></span>
      <ifa-input-check
        v-model="labelPosition"
        code-list-id="original"
        :original-list="[
          { key: 'right', value: '' },
          { key: 'left', value: '左寄せ' }
        ]"
      ></ifa-input-check>
    </h3>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【IfaInputText】
      <ifa-button
        text="SUBMIT"
        small
        :disabled="disabled"
        action-type="originalAction"
        @app-action-handler="submitForm('formText')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formText"
      :model="formText"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-text
          id="IfaInputTextTest"
          v-model="formText.text"
          :domain="{
            domainId: 'ACCOUNT_NUMBER',
            domainName: '口座番号',
            dataType: '数値',
            precision: 12,
            digit: 0,
            lengthFixed: false,
            validate: 'fullWidth',
            format: 'zeroPadding',
            javaType: 'Integer',
            oracleType: 'NUMBER(7,0)',
            formattedDigit: 7,
            verticalAlign: 'M',
            horizontalAlign: 'L',
            unit: null,
            htmlTag: null
          }"
          prop="text"
          label="テキスト"
          size="small"
          support
          required
          :disabled="disabled"
          placeholder="文字列"
        ></ifa-input-text>
        <span class="label">入力結果:</span><span class="value">{{ formText.text }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaInputPrice】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formPrice')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formPrice"
      :model="formPrice"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-price
          id="IfaInputPriceTest"
          v-model="formPrice.price"
          :domain="{
            domainId: 'YEN',
            domainName: '円貨',
            dataType: '金額',
            precision: 12,
            digit: 2,
            lengthFixed: false,
            validate: 'numberFloat',
            format: 'withCommaZeroPadding',
            javaType: 'BigDecimal',
            oracleType: 'NUMBER(15,0)',
            formattedDigit: 19,
            verticalAlign: 'M',
            horizontalAlign: 'R',
            unit: 'USD',
            htmlTag: null
          }"
          prop="price"
          label="価格"
          unit="円"
          size="small"
          support
          required
        ></ifa-input-price>
        <span class="label">価格:</span><span class="value">{{ formPrice.price }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaInputAmout】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formAmount')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formAmount"
      :model="formAmount"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-amount
          v-model="formAmount.amount"
          :domain="{
            domainId: 'STOCKS0',
            domainName: '株数0',
            dataType: '数値',
            precision: 7,
            digit: 0,
            lengthFixed: true,
            validate: 'numberInteger',
            format: 'signedWithCommaInteger',
            javaType: 'BigDecimal',
            oracleType: 'NUMBER(16,0)',
            formattedDigit: 21,
            verticalAlign: 'M',
            horizontalAlign: 'R',
            htmlTag: null
          }"
          prop="amount"
          label="数量"
          unit="株"
          size="small"
          support
          required
        ></ifa-input-amount>
        <span class="label">数量:</span><span class="value">{{ formAmount.amount }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaInputQuantity】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formQuantity')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formQuantity"
      :model="formQuantity"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-quantity
          v-model="formQuantity.quantity"
          :domain="{
            domainId: 'YEN_REMAIN_POWER',
            domainName: '円貨赤残',
            dataType: '金額',
            precision: 7,
            digit: 2,
            lengthFixed: false,
            validate: 'numberFloat',
            format: 'signedWithCommaZeroPadding',
            javaType: 'BigDecimal',
            oracleType: 'NUMBER(16,0)',
            formattedDigit: 16,
            verticalAlign: 'M',
            horizontalAlign: 'R',
            unit: 'YEN',
            htmlTag: null
          }"
          prop="quantity"
          label="金額"
          unit="円"
          size="small"
          support
          required
        ></ifa-input-quantity>
        <span class="label">金額:</span><span class="value">{{ formQuantity.quantity }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaInputRate】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formRate')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formRate"
      :model="formRate"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-rate
          v-model="formRate.rate"
          :domain="{
            domainId: 'RATE0',
            domainName: '比率0',
            dataType: '数値',
            precision: 7,
            digit: 2,
            lengthFixed: false,
            validate: 'numberFloat',
            format: 'signedWithCommaNoneZeroPadding',
            javaType: 'double',
            oracleType: 'NUMBER(9,0)',
            format: null,
            formattedDigit: 3,
            verticalAlign: 'M',
            horizontalAlign: 'R',
            unit: '',
            htmlTag: null
          }"
          prop="rate"
          label="レート"
          unit="%"
          size="small"
          support
          required
        ></ifa-input-rate>
        <span class="label">レート:</span><span class="value">{{ formRate.rate }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaInputCheck】
      <ifa-button
        :text="`CHANGE (${checkOptionsType})`"
        small
        action-type="originalAction"
        @app-action-handler="changeCheckOptions"
      ></ifa-button>
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formCheck')"
      ></ifa-button>
      <ifa-button
        text="RESET"
        small
        action-type="originalAction"
        @app-action-handler="formCheck.check = []"
      ></ifa-button>
    </h4>
    <el-form
      ref="formCheck"
      :model="formCheck"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-check
          v-model="formCheck.check"
          label="チェック"
          :code-list-id="checkOptions.codeListId"
          :original-list="checkOptions.originalList"
          :select-pattern="checkOptions.selectPattern"
          :disp-pattern="checkOptions.dispPattern"
          prop="check"
          required
        ></ifa-input-check>
        <span class="label">チェック:</span><span class="value">{{ formCheck.check }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaInputCheck (single)】
      <ifa-button
        :text="`CHANGE (${checkSingleOptionsType})`"
        small
        action-type="originalAction"
        @app-action-handler="changeCheckSingleOptions"
      ></ifa-button>
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formCheckSingle')"
      ></ifa-button>
      <ifa-button
        text="RESET"
        small
        action-type="originalAction"
        @app-action-handler="formCheckSingle.check = ''"
      ></ifa-button>
    </h4>
    <el-form
      ref="formCheckSingle"
      :model="formCheckSingle"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-check
          v-model="formCheckSingle.check"
          label="チェック"
          :code-list-id="checkSingleOptions.codeListId"
          :original-list="checkSingleOptions.originalList"
          :select-pattern="checkSingleOptions.selectPattern"
          :disp-pattern="checkSingleOptions.dispPattern"
          prop="check"
          required
        ></ifa-input-check>
        <span class="label">チェック:</span><span class="value">{{ formCheckSingle.check }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaInputRadio】
      <ifa-button
        :text="`CHANGE (${radioOptionsType})`"
        small
        action-type="originalAction"
        @app-action-handler="changeRadioOptions"
      ></ifa-button>
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formRadio')"
      ></ifa-button>
      <ifa-button
        text="RESET"
        small
        action-type="originalAction"
        @app-action-handler="formRadio.radio = ''"
      ></ifa-button>
    </h4>
    <el-form
      ref="formRadio"
      :model="formRadio"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-radio
          v-model="formRadio.radio"
          label="ラジオ"
          :code-list-id="radioOptions.codeListId"
          :original-list="radioOptions.originalList"
          :select-pattern="radioOptions.selectPattern"
          :disp-pattern="radioOptions.dispPattern"
          prop="radio"
          required
        ></ifa-input-radio>
        <span class="label">チェック:</span><span class="value">{{ formRadio.radio }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaInputRadio (multi)】
      <ifa-button
        :text="`CHANGE (${radioMultiOptionsType})`"
        small
        action-type="originalAction"
        @app-action-handler="changeRadioMultiOptions"
      ></ifa-button>
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formRadioMulti')"
      ></ifa-button>
      <ifa-button
        text="RESET"
        small
        action-type="originalAction"
        @app-action-handler="formRadioMulti.radio = ''"
      ></ifa-button>
    </h4>
    <el-form
      ref="formRadioMulti"
      :model="formRadioMulti"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-radio
          v-model="formRadioMulti.radio"
          label="ラジオ"
          :code-list-id="radioMultiOptions.codeListId"
          :original-list="radioMultiOptions.originalList"
          is-button
          button-margin="0"
          prop="radio"
          required
        ></ifa-input-radio>
      </div>
      <span class="label">チェック:</span><span class="value">{{ formRadioMulti.radio }}</span>
    </el-form>

    <hr>
    <h4>
      【IfaInputSelect】
      <ifa-button
        :text="`CHANGE (${selectOptionsType})`"
        small
        action-type="originalAction"
        @app-action-handler="changeSelectOptions"
      ></ifa-button>
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formSelect')"
      ></ifa-button>
      <ifa-button
        text="RESET"
        small
        action-type="originalAction"
        @app-action-handler="formSelect.select = ''"
      ></ifa-button>
    </h4>
    <el-form
      ref="formSelect"
      :model="formSelect"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-select
          v-model="formSelect.select"
          :code-list-id="selectOptions.codeListId"
          :original-list="selectOptions.originalList"
          :disp-pattern="selectOptions.dispPattern"
          :select-pattern="selectOptions.selectPattern"
          label="通貨コード"
          prop="select"
          required
        ></ifa-input-select>
        <span class="label">セレクト:</span><span class="value">{{ formSelect.select }}</span>
      </div>
    </el-form>

    <hr>
    <h4>
      【IfaInputMultiSelect】
      <ifa-button
        :text="`CHANGE (${multiSelectOptionsType})`"
        small
        action-type="originalAction"
        @app-action-handler="changeMultiSelectOptions"
      ></ifa-button>
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formMultiSelect')"
      ></ifa-button>
      <ifa-button
        text="RESET"
        small
        action-type="originalAction"
        @app-action-handler="formMultiSelect.select = []"
      ></ifa-button>
    </h4>
    <el-form
      ref="formMultiSelect"
      :model="formMultiSelect"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <ifa-input-multi-select
          v-model="formMultiSelect.select"
          :code-list-id="multiSelectOptions.codeListId"
          :original-list="multiSelectOptions.originalList"
          :disp-pattern="multiSelectOptions.dispPattern"
          :select-pattern="multiSelectOptions.selectPattern"
          label="通貨コード"
          prop="select"
          required
          @change="multiSelectChange"
          @change-selected="multiSelectChangeSelected"
        ></ifa-input-multi-select>
      </div>
      <div>
        <span class="label">セレクト:</span><span class="value">{{ formMultiSelect.select }}</span>
        <br>
        <span class="label">セレクト(互換):</span><div class="value">{{ formMultiSelect.changeSelected }}</div>
      </div>
    </el-form>

    <hr>
    <h4>
      【CourseSelect (pt1)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formCourseSelectPt1')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formCourseSelectPt1"
      :model="formCourseSelectPt1"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <course-select
          ref="courseSelectPt1"
          id-string="pt1"
          list-kind="pt1"
          label="取引コース"
          prop="select"
          required
          @change-selected="courseSelectPt1ChangeSelected"
        ></course-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formCourseSelectPt1.select }}</div>
    </el-form>

    <hr>
    <h4>
      【CourseSelect (pt2)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formCourseSelectPt2')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formCourseSelectPt2"
      :model="formCourseSelectPt2"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <course-select
          ref="courseSelectPt2"
          id-string="pt2"
          list-kind="pt2"
          label="取引コース"
          prop="select"
          required
          @change-selected="courseSelectPt2ChangeSelected"
        ></course-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formCourseSelectPt2.select }}</div>
    </el-form>

    <hr>
    <h4>
      【CourseSelect (pt3)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formCourseSelectPt3')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formCourseSelectPt3"
      :model="formCourseSelectPt3"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <course-select
          ref="courseSelectPt3"
          id-string="pt3"
          list-kind="pt3"
          label="取引コース"
          prop="select"
          required
          @change-selected="courseSelectPt3ChangeSelected"
        ></course-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formCourseSelectPt3.select }}</div>
    </el-form>

    <hr>
    <h4>
      【CourseSelect (pt4)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formCourseSelectPt4')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formCourseSelectPt4"
      :model="formCourseSelectPt4"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <course-select
          ref="courseSelectPt4"
          id-string="pt4"
          list-kind="pt4"
          label="取引コース"
          prop="select"
          required
          @change-selected="courseSelectPt4ChangeSelected"
        ></course-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formCourseSelectPt4.select }}</div>
    </el-form>

    <hr>
    <h4>
      【CustomerAlert】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formCustomerAlert')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formCustomerAlert"
      :model="formCustomerAlert"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <customer-alert
          ref="customerAlert"
          id-string="test"
          label="顧客アラート"
          prop="select"
          required
          @change-selected="customerAlertChangeSelected"
        ></customer-alert>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formCustomerAlert.select }}</div>
    </el-form>

    <hr>
    <h4>
      【OrderSelect】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formOrderSelect')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formOrderSelect"
      :model="formOrderSelect"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <order-select
          ref="orderSelect"
          id-string="test"
          label="商品種別"
          prop="select"
          required
          @change-selected="orderSelectChangeSelected"
        ></order-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formOrderSelect.select }}</div>
    </el-form>

    <hr>
    <h4>
      【ProductCodeSelect (pt1)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formProductCodeSelectPt1')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formProductCodeSelectPt1"
      :model="formProductCodeSelectPt1"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <product-code-select
          ref="productCodeSelectPt1"
          id-string="pt1"
          list-kind="pt1"
          label="証券種別（証券　金銭）"
          prop="select"
          required
          @change-selected="productCodeSelectPt1ChangeSelected"
        ></product-code-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formProductCodeSelectPt1.select }}</div>
    </el-form>

    <hr>
    <h4>
      【ProductCodeSelect (pt2)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formProductCodeSelectPt2')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formProductCodeSelectPt2"
      :model="formProductCodeSelectPt2"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <product-code-select
          ref="productCodeSelectPt2"
          id-string="pt2"
          list-kind="pt2"
          label="証券種別（信用・先OP）"
          prop="select"
          required
          @change-selected="productCodeSelectPt2ChangeSelected"
        ></product-code-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formProductCodeSelectPt2.select }}</div>
    </el-form>

    <hr>
    <h4>
      【ProductCodeSelect (pt3)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formProductCodeSelectPt3')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formProductCodeSelectPt3"
      :model="formProductCodeSelectPt3"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <product-code-select
          ref="productCodeSelectPt3"
          id-string="pt3"
          list-kind="pt3"
          label="証券種別（取引履歴）"
          prop="select"
          required
          @change-selected="productCodeSelectPt3ChangeSelected"
        ></product-code-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formProductCodeSelectPt3.select }}</div>
    </el-form>

    <hr>
    <h4>
      【ProductCodeSelect (pt4)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formProductCodeSelectPt4')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formProductCodeSelectPt4"
      :model="formProductCodeSelectPt4"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <product-code-select
          ref="productCodeSelectPt4"
          id-string="pt4"
          list-kind="pt4"
          label="証券種別（手数料一覧）"
          prop="select"
          required
          @change-selected="productCodeSelectPt4ChangeSelected"
        ></product-code-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formProductCodeSelectPt4.select }}</div>
    </el-form>

    <hr>
    <h4>
      【ProductCodeSelect (pt5)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formProductCodeSelectPt5')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formProductCodeSelectPt5"
      :model="formProductCodeSelectPt5"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <product-code-select
          ref="productCodeSelectPt5"
          id-string="pt5"
          list-kind="pt5"
          label="証券種別（手数料一覧）"
          prop="select"
          required
          @change-selected="productCodeSelectPt5ChangeSelected"
        ></product-code-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formProductCodeSelectPt5.select }}</div>
    </el-form>

    <hr>
    <h4>
      【ProductCodeSelect (pt6)】
      <ifa-button
        text="SUBMIT"
        small
        action-type="originalAction"
        @app-action-handler="submitForm('formProductCodeSelectPt6')"
      ></ifa-button>
    </h4>
    <el-form
      ref="formProductCodeSelectPt6"
      :model="formProductCodeSelectPt6"
      :inline="true"
      :label-position="labelPosition"
    >
      <div class="result">
        <product-code-select
          ref="productCodeSelectPt6"
          id-string="pt6"
          list-kind="pt6"
          label="証券種別（信託報酬）"
          prop="select"
          required
          @change-selected="productCodeSelectPt6ChangeSelected"
        ></product-code-select>
      </div>
      <span class="label">セレクト:</span><div class="value">{{ formProductCodeSelectPt6.select }}</div>
    </el-form>

    <hr>
    <h4>【IfaText(1): 基本パターン】</h4>
    <div class="result">
      <ifa-text
        :model-value="text1"
        code-list-id="INVITATION_TYPE"
        :disp-pattern="1"
        code-key="1"
        @input="obj => { text1 = '<' + obj.key + '>: <' + obj.value + '>' }"
      ></ifa-text>
      <span class="label">テキスト(1):</span><span class="value">{{ text1 }}</span>
    </div>

    <hr>
    <h4>【IfaText(2): $null パターン】</h4>
    <div class="result">
      <ifa-text
        :model-value="text2"
        code-list-id="DUMMY_DATA_FOR_TEST"
        :disp-pattern="1"
        code-key=""
        @input="obj => { text2 = '<' + obj.key + '>: <' + obj.value + '>' }"
      ></ifa-text>
      <span class="label">テキスト(2):</span><span class="value">{{ text2 }}</span>
    </div>

    <hr>
    <h4>【IfaText(3): △パターン】</h4>
    <div class="result">
      <ifa-text
        :model-value="text3"
        code-list-id="ISA_CONTRACT_TYPE"
        :disp-pattern="1"
        code-key=" "
        @input="obj => { text3 = '<' + obj.key + '>: <' + obj.value + '>' }"
      ></ifa-text>
      <span class="label">テキスト(3):</span><span class="value">{{ text3 }}</span>
    </div>

    <hr>
    <h4>【IfaText(4): △△パターン】</h4>
    <div class="result">
      <ifa-text
        :model-value="text4"
        code-list-id="INVESTMENT_POLICY_TYPE"
        :disp-pattern="1"
        code-key="  "
        @input="obj => { text4 = '<' + obj.key + '>: <' + obj.value + '>' }"
      ></ifa-text>
      <span class="label">テキスト(4):</span><span class="value">{{ text4 }}</span>
    </div>

    <hr>
    <h4>【IfaText(5): 1△パターン】</h4>
    <div class="result">
      <ifa-text
        :model-value="text5"
        code-list-id="SECURITY_TYPE"
        :disp-pattern="1"
        code-key="1 "
        @input="obj => { text5 = '<' + obj.key + '>: <' + obj.value + '>' }"
      ></ifa-text>
      <span class="label">テキスト(5):</span><span class="value">{{ text5 }}</span>
    </div>

    <hr>
    <h4>【IfaText(6): *パターン】</h4>
    <div class="result">
      <ifa-text
        :model-value="text6"
        code-list-id="CURRENT_PRICE_FLAG"
        :disp-pattern="1"
        code-key="*"
        @input="obj => { text6 = '<' + obj.key + '>: <' + obj.value + '>' }"
      ></ifa-text>
      <span class="label">テキスト(6):</span><span class="value">{{ text6 }}</span>
    </div>

    <hr>
    <h4>【IfaText(7): dispPettern is null】</h4>
    <div class="result">
      <ifa-text
        :model-value="text7"
        code-list-id="CURRENT_PRICE_FLAG"
        :disp-pattern="null"
        code-key="*"
        @input="obj => { text7 = '<' + obj.key + '>: <' + obj.value + '>' }"
      ></ifa-text>
      <span class="label">テキスト(7):</span><span class="value">{{ text7 }}</span>
    </div>

    <hr>
    <h3>●IfaRouterテスト</h3>

    <hr>
    <h4>
      【$_startShowMenu】
      <el-row>
        <el-col :span="5">
          <ifa-input-text
            v-model="menuId"
            label="メニューID"
            size="small"
          ></ifa-input-text>
        </el-col>
        <el-col :span="3">
          <ifa-button
            text="Go"
            small
            action-type="originalAction"
            @app-action-handler="startShowMenu"
          ></ifa-button>
        </el-col>
      </el-row>
    </h4>

    <hr>
    <h4>【Global Functions】</h4>
    <table border="2">
      <tr><th>method</th><th>value type, digitSpec/precision</th><th>result</th></tr>
      <tr><td rowspan="4">$_withCommaZeroPadding</td><td>plus int, 3</td><td>{{ $_withCommaZeroPadding(testNumber, 3) }}</td></tr>
      <tr><td>minus int, 3</td><td>{{ $_withCommaZeroPadding(testNumberMinus, 3) }}</td></tr>
      <tr><td>plus float, 3</td><td>{{ $_withCommaZeroPadding(testFloat, 3) }}</td></tr>
      <tr><td>minus float, 3</td><td>{{ $_withCommaZeroPadding(testFloatMinus, 3) }}</td></tr>
      <tr><td rowspan="4">$_withCommaNoneZeroPadding</td><td>plus int</td><td>{{ $_withCommaNoneZeroPadding(testNumber) }}</td></tr>
      <tr><td>minus int</td><td>{{ $_withCommaNoneZeroPadding(testNumberMinus) }}</td></tr>
      <tr><td>plus float, 3</td><td>{{ $_withCommaNoneZeroPadding(testFloat, 3) }}</td></tr>
      <tr><td>minus float, 3</td><td>{{ $_withCommaNoneZeroPadding(testFloatMinus, 3) }}</td></tr>
      <tr><td rowspan="4">$_noneWithCommaZeroPadding</td><td>plus int, 3</td><td>{{ $_noneWithCommaZeroPadding(testNumber, 3) }}</td></tr>
      <tr><td>minus int, 3</td><td>{{ $_noneWithCommaZeroPadding(testNumberMinus, 3) }}</td></tr>
      <tr><td>plus float, 3</td><td>{{ $_noneWithCommaZeroPadding(testFloat, 3) }}</td></tr>
      <tr><td>minus float, 3</td><td>{{ $_noneWithCommaZeroPadding(testFloatMinus, 3) }}</td></tr>
      <tr><td rowspan="4">$_noneWithCommaNoneZeroPadding</td><td>plus int</td><td>{{ $_noneWithCommaNoneZeroPadding(testNumber) }}</td></tr>
      <tr><td>minus int</td><td>{{ $_noneWithCommaNoneZeroPadding(testNumberMinus) }}</td></tr>
      <tr><td>plus float, 3</td><td>{{ $_noneWithCommaNoneZeroPadding(testFloat, 3) }}</td></tr>
      <tr><td>minus float, 3</td><td>{{ $_noneWithCommaNoneZeroPadding(testFloatMinus, 3) }}</td></tr>
      <tr><td rowspan="4">$_signedWithCommaZeroPadding</td><td>plus int, 3</td><td>{{ $_signedWithCommaZeroPadding(testNumber, 3) }}</td></tr>
      <tr><td>minus int, 3</td><td>{{ $_signedWithCommaZeroPadding(testNumberMinus, 3) }}</td></tr>
      <tr><td>plus float, 3</td><td>{{ $_signedWithCommaZeroPadding(testFloat, 3) }}</td></tr>
      <tr><td>minus float, 3</td><td>{{ $_signedWithCommaZeroPadding(testFloatMinus, 3) }}</td></tr>
      <tr><td rowspan="4">$_signedWithCommaNoneZeroPadding</td><td>plus int</td><td>{{ $_signedWithCommaNoneZeroPadding(testNumber) }}</td></tr>
      <tr><td>minus int</td><td>{{ $_signedWithCommaNoneZeroPadding(testNumberMinus) }}</td></tr>
      <tr><td>plus float, 3</td><td>{{ $_signedWithCommaNoneZeroPadding(testFloat, 3) }}</td></tr>
      <tr><td>minus float, 3</td><td>{{ $_signedWithCommaNoneZeroPadding(testFloatMinus, 3) }}</td></tr>
      <tr><td rowspan="4">$_withCommaInteger</td><td>plus int</td><td>{{ $_withCommaInteger(testNumber) }}</td></tr>
      <tr><td>minus int</td><td>{{ $_withCommaInteger(testNumberMinus) }}</td></tr>
      <tr><td>plus float, 3</td><td>{{ $_withCommaInteger(testFloat, 3) }}</td></tr>
      <tr><td>minus float, 3</td><td>{{ $_withCommaInteger(testFloatMinus, 3) }}</td></tr>
      <tr><td rowspan="4">$_signedWithCommaInteger</td><td>plus int</td><td>{{ $_signedWithCommaInteger(testNumber) }}</td></tr>
      <tr><td>minus int</td><td>{{ $_signedWithCommaInteger(testNumberMinus) }}</td></tr>
      <tr><td>plus float, 3</td><td>{{ $_signedWithCommaInteger(testFloat, 3) }}</td></tr>
      <tr><td>minus float, 3</td><td>{{ $_signedWithCommaInteger(testFloatMinus, 3) }}</td></tr>
      <tr><td>$_zeroPadding</td><td>String, 12</td><td>{{ $_zeroPadding(testString, 12) }}</td></tr>
    </table>

  </div>
</template>

<script>
import { notifyMessage } from '@/utils/errorHandler.js'
import { getMessage } from '@/utils/errorHandler'
import CourseSelect from '@/components/MultiSelect/CourseSelect'
import CustomerAlert from '@/components/MultiSelect/CustomerAlert'
import OrderSelect from '@/components/MultiSelect/OrderSelect'
import ProductCodeSelect from '@/components/MultiSelect/ProductCodeSelect'

export default {
  name: 'IfaInputTest',
  components: {
    CourseSelect,
    CustomerAlert,
    OrderSelect,
    ProductCodeSelect
  },
  emits: ['initializeError'],
  data() {
    return {
      labelPosition: 'right',
      formPrice: { price: '' },
      formAmount: { amount: '' },
      formQuantity: { quantity: '' },
      formRate: { rate: '' },
      formCheck: { check: [] },
      formCheckSingle: { check: '' },
      formRadio: { radio: '' },
      formRadioMulti: { radio: '' },
      formSelect: { select: '' },
      formMultiSelect: { select: [], changeSelected: [] },
      formCourseSelectPt1: { select: [] },
      formCourseSelectPt2: { select: [] },
      formCourseSelectPt3: { select: [] },
      formCourseSelectPt4: { select: [] },
      formCustomerAlert: { select: [] },
      formOrderSelect: { select: [] },
      formProductCodeSelectPt1: { select: [] },
      formProductCodeSelectPt2: { select: [] },
      formProductCodeSelectPt3: { select: [] },
      formProductCodeSelectPt4: { select: [] },
      formProductCodeSelectPt5: { select: [] },
      formProductCodeSelectPt6: { select: [] },
      formText: { text: '' },
      text1: '',
      text2: '',
      text3: '',
      text4: '',
      text5: '',
      text6: '',
      text7: '',
      testString: '12345',
      testNumber: 1000000,
      testNumberMinus: -1000000,
      testFloat: 1000000.12,
      testFloatMinus: -1000000.12,
      notifyIndex: 0,
      menuId: '',
      domesticOrderPriceUnits: [
        { over: 0, within: 0.25, orderPriceUnit: 0.05 },
        { over: 0.25, within: 0.5, orderPriceUnit: 0.05 },
        { over: 0.5, within: 10, orderPriceUnit: 0.25 },
        { over: 10, within: 30, orderPriceUnit: 2 },
        { over: 30, within: 100, orderPriceUnit: 10 },
        { over: 100, within: 200, orderPriceUnit: 25 },
        { over: 200, within: 500, orderPriceUnit: 100 },
        { over: 500, within: 1000, orderPriceUnit: 250 },
        { over: 1000, within: 2000, orderPriceUnit: 500 },
        { over: 2000, within: 5000, orderPriceUnit: 1000 },
        { over: 5000, within: 100000, orderPriceUnit: 2500 }
      ],
      foreignOrderPriceUnits: [
        { 'basePriceFrom': 0, 'basePriceTo': 0.25, 'tickSize': 0.05, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 0.25, 'basePriceTo': 0.5, 'tickSize': 0.05, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 0.5, 'basePriceTo': 10, 'tickSize': 0.25, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 10, 'basePriceTo': 30, 'tickSize': 2, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 30, 'basePriceTo': 100, 'tickSize': 10, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 100, 'basePriceTo': 200, 'tickSize': 25, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 200, 'basePriceTo': 500, 'tickSize': 100, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 500, 'basePriceTo': 1000, 'tickSize': 250, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 1000, 'basePriceTo': 2000, 'tickSize': 500, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 2000, 'basePriceTo': 5000, 'tickSize': 1000, 'basePriceType': 'GREATER_LESS_EQUAL' },
        { 'basePriceFrom': 5000, 'basePriceTo': 100000, 'tickSize': 2500, 'basePriceType': 'GREATER_LESS_EQUAL' }
      ],
      radioOptionsType: 0,
      radioOptionsArray: [
        {
          codeListId: 'ORDER_METHOD_TYPE',
          originalList: [],
          dispPattern: 1,
          selectPattern: 1
        },
        {
          codeListId: 'original',
          originalList: [
            { key: '1', value: 'オプション1' },
            { key: '2', value: 'オプション2' }
          ],
          dispPattern: 0,
          selectPattern: 0
        },
        {
          codeListId: 'original',
          originalList: [
            { key: 'A', value: 'Option A' },
            { key: 'B', value: 'Option B' }
          ],
          dispPattern: 0,
          selectPattern: 0
        }
      ],
      radioMultiOptionsType: 0,
      radioMultiOptionsArray: [
        {
          codeListId: 'multi',
          originalList: [
            {
              groupId: 1,
              options: [
                { key: '11', value: 'グループ1のオプション1' },
                { key: '12', value: 'グループ1のオプション2' }
              ]
            },
            {
              groupId: 2,
              options: [
                { key: '21', value: 'グループ2のオプション1' },
                { key: '22', value: 'グループ2のオプション2' }
              ]
            },
            {
              groupId: 3,
              options: [
                { key: '31', value: 'グループ3のオプション1' },
                { key: '32', value: 'グループ3のオプション2' }
              ]
            }
          ]
        },
        {
          codeListId: 'multi',
          originalList: [
            {
              groupId: 'A',
              options: [
                { key: 'A1', value: 'Group A, Option 1' },
                { key: 'A2', value: 'Group A, Option 2' },
                { key: 'A3', value: 'Group A, Option 3' }
              ]
            },
            {
              groupId: 'B',
              options: [
                { key: 'B1', value: 'Group B, Option 1' },
                { key: 'B2', value: 'Group B, Option 2' }
              ]
            }
          ]
        }
      ],
      checkOptionsType: 0,
      checkOptionsArray: [
        {
          codeListId: 'ORDER_METHOD_TYPE',
          originalList: [],
          dispPattern: 1,
          selectPattern: 1
        },
        {
          codeListId: 'original',
          originalList: [
            { key: 'A', value: 'オプション A' },
            { key: 'B', value: 'オプション B' },
            { key: 'C', value: 'オプション C' },
            { key: 'D', value: 'オプション D' }
          ],
          dispPattern: 0,
          selectPattern: 0
        }
      ],
      checkSingleOptionsType: 0,
      checkSingleOptionsArray: [
        {
          codeListId: 'MARGIN_BUYING_POWER_SHORTFALL_SECURITIES',
          originalList: [],
          dispPattern: 1,
          selectPattern: 1
        },
        {
          codeListId: 'original',
          originalList: [
            { key: 'NO', value: '' },
            { key: 'YES', value: '確認してください' }
          ],
          dispPattern: 0,
          selectPattern: 0
        }
      ],
      selectOptionsType: 0,
      selectOptionsArray: [
        {
          codeListId: 'CURRENCY_CODE',
          originalList: [],
          dispPattern: 2,
          selectPattern: 1
        },
        {
          codeListId: 'original',
          originalList: [
            { key: 'A', value: 'オプション A' },
            { key: 'B', value: 'オプション B' },
            { key: 'C', value: 'オプション C' },
            { key: 'D', value: 'オプション D' }
          ],
          dispPattern: 0,
          selectPattern: 0
        }
      ],
      multiSelectOptionsType: 0,
      multiSelectOptionsArray: [
        {
          codeListId: 'CURRENCY_CODE',
          originalList: [],
          dispPattern: 2,
          selectPattern: 1
        },
        {
          codeListId: 'original',
          originalList: [
            { key: 'A', value: 'オプション A' },
            { key: 'B', value: 'オプション B' },
            { key: 'C', value: 'オプション C' },
            { key: 'D', value: 'オプション D' }
          ],
          dispPattern: 0,
          selectPattern: 0
        }
      ]
    }
  },
  computed: {
    disabled() {
      return process.env.NODE_ENV !== 'development'
    },
    radioOptions() {
      return this.radioOptionsArray[this.radioOptionsType]
    },
    radioMultiOptions() {
      return this.radioMultiOptionsArray[this.radioMultiOptionsType]
    },
    checkOptions() {
      return this.checkOptionsArray[this.checkOptionsType]
    },
    checkSingleOptions() {
      return this.checkSingleOptionsArray[this.checkSingleOptionsType]
    },
    selectOptions() {
      return this.selectOptionsArray[this.selectOptionsType]
    },
    multiSelectOptions() {
      return this.multiSelectOptionsArray[this.multiSelectOptionsType]
    }
  },
  methods: {
    submitForm(formName) {
      // フォームのサブミット前に入力値チェックを行う
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$_logDebug('validation success')
        } else {
          this.$_logDebug('validation fail')
          return false
        }
      })
    },
    notification() {
      switch (this.notifyIndex++ % 4) {
        case 0:
          notifyMessage(0, getMessage('errors.dataList.notfound'), 'テスト CR')
          break
        case 1:
          notifyMessage(1, getMessage('warnings.listService.getOrderList'), 'テスト <BR>')
          break
        case 2:
          notifyMessage(2, getMessage('errors.srReasonCheck'), 'テスト <BR/>')
          break
        case 3:
          notifyMessage(-1, getMessage('errors.frs.selectedAccount.outOfService'), 'テスト none')
          break
      }
    },
    initializeError() {
      const errorInfo = {
        title: this.$store.getters.pageInfo.label, // 推奨: this.form.title.name,
        message: getMessage('errors.cmn.selectedAccount.outOfService')
      }
      this.$emit('initializeError', errorInfo)
    },
    startShowMenu() {
      const param = {
        accountNumber: 'Z52-0001776'
      }
      this.$_startShowMenu(this.menuId, param)
    },
    resetAll() {
      this.formPrice.price = ''
      this.formAmount.amount = ''
      this.formQuantity.quantity = ''
      this.formRate.rate = ''
      this.formCheck.check = []
      this.formCheckSingle.check = ''
      this.formRadio.radio = ''
      this.formRadioMulti.radio = ''
      this.formSelect.select = ''
      this.formMultiSelect.select = []
      this.formMultiSelect.changeSelected = []
      this.formCourseSelectPt1.select = []
      this.$refs['courseSelectPt1'].clearHandler()
      this.formCourseSelectPt2.select = []
      this.$refs['courseSelectPt2'].clearHandler()
      this.formCourseSelectPt3.select = []
      this.$refs['courseSelectPt3'].clearHandler()
      this.formCourseSelectPt4.select = []
      this.$refs['courseSelectPt4'].clearHandler()
      this.formCustomerAlert.select = []
      this.$refs['customerAlert'].clearHandler()
      this.formOrderSelect.select = []
      this.$refs['orderSelect'].clearHandler()
      this.formProductCodeSelectPt1.select = []
      this.$refs['productCodeSelectPt1'].clearHandler()
      this.formProductCodeSelectPt2.select = []
      this.$refs['productCodeSelectPt2'].clearHandler()
      this.formProductCodeSelectPt3.select = []
      this.$refs['productCodeSelectPt3'].clearHandler()
      this.formProductCodeSelectPt4.select = []
      this.$refs['productCodeSelectPt4'].clearHandler()
      this.formProductCodeSelectPt5.select = []
      this.$refs['productCodeSelectPt5'].clearHandler()
      this.formProductCodeSelectPt6.select = []
      this.$refs['productCodeSelectPt6'].clearHandler()
      this.formText.text = ''
    },
    multiSelectChange(event) {
      this.formMultiSelect.select = event
    },
    multiSelectChangeSelected(event) {
      this.formMultiSelect.changeSelected = event
    },
    courseSelectPt1ChangeSelected(event) {
      this.formCourseSelectPt1.select = event
    },
    courseSelectPt2ChangeSelected(event) {
      this.formCourseSelectPt2.select = event
    },
    courseSelectPt3ChangeSelected(event) {
      this.formCourseSelectPt3.select = event
    },
    courseSelectPt4ChangeSelected(event) {
      this.formCourseSelectPt4.select = event
    },
    customerAlertChangeSelected(event) {
      this.formCustomerAlert.select = event
    },
    orderSelectChangeSelected(event) {
      this.formOrderSelect.select = event
    },
    productCodeSelectPt1ChangeSelected(event) {
      this.formProductCodeSelectPt1.select = event
    },
    productCodeSelectPt2ChangeSelected(event) {
      this.formProductCodeSelectPt2.select = event
    },
    productCodeSelectPt3ChangeSelected(event) {
      this.formProductCodeSelectPt3.select = event
    },
    productCodeSelectPt4ChangeSelected(event) {
      this.formProductCodeSelectPt4.select = event
    },
    productCodeSelectPt5ChangeSelected(event) {
      this.formProductCodeSelectPt5.select = event
    },
    productCodeSelectPt6ChangeSelected(event) {
      this.formProductCodeSelectPt6.select = event
    },
    changeRadioOptions() {
      this.radioOptionsType = (this.radioOptionsType + 1) % this.radioOptionsArray.length
      this.formRadio.radio = ''
    },
    changeRadioMultiOptions() {
      this.radioMultiOptionsType = (this.radioMultiOptionsType + 1) % this.radioMultiOptionsArray.length
      this.formRadioMulti.radio = ''
    },
    changeCheckOptions() {
      this.checkOptionsType = (this.checkOptionsType + 1) % this.checkOptionsArray.length
      this.formCheck.check = []
    },
    changeCheckSingleOptions() {
      this.checkSingleOptionsType = (this.checkSingleOptionsType + 1) % this.checkSingleOptionsArray.length
      this.formCheckSingle.check = ''
    },
    changeSelectOptions() {
      this.selectOptionsType = (this.selectOptionsType + 1) % this.selectOptionsArray.length
      this.formSelect.select = ''
    },
    changeMultiSelectOptions() {
      this.multiSelectOptionsType = (this.multiSelectOptionsType + 1) % this.multiSelectOptionsArray.length
      this.formMultiSelect.select = []
    }
  }
}
</script>

<style lang='scss' scoped>
.main {
  margin: 2rem;
}
.result {
  // height: 4rem;
  vertical-align: middle;
  margin-bottom: 16px;
}
.label {
  padding-left: 6.5rem;
  padding-right: 1rem;
  margin-bottom: 16px;
}
.value {
  padding: 0.4rem;
  background-color: white;
  border: 1px solid blue;
  line-height: 24px;
}
</style>

<style lang='scss'>
// label-class="color-blue"
// input-class="input-class-test"
.color-blue {
  .el-form-item__label {
    color: blue;
    text-decoration: underline;
    text-underline-offset: 4px;
    text-justify: newspaper;
  }
}
.input-class-test {
  .el-input__wrapper {
    background-color: yellow;
    width: 300px;
    height: 100px;
    .el-input__inner {
      color: red;
    }
  }
}
</style>
