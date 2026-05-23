<template>
  <ifa-input-base
    v-if="ctrlVisible"
    :id="id"
    :domain="priceDomain"
    :support="support"
    :model-value="bigNumberValue"
    :unit="unit"
    :step="step"
    :step-for-validation="stepForValidation"
    :step-table="stepTable"
    :initial-step="initialStep"
    :size="size"
    :min="min"
    :max="max"
    :clearable="clearable"
    :readonly="readonly || ctrlReadonly"
    :disabled="disabled || ctrlDisabled"
    :placeholder="placeholder"
    v-bind="$attrs"
    :input-class="inputClass"
    :required="required"
  ></ifa-input-base>
</template>

<script>
import IfaInputBase from './IfaInputBase.vue'
import { BigNumber } from 'bignumber.js'
import { controlAuthorization } from '@/utils/controlAuth'

export default {
  name: 'IfaInputPrice',
  components: { IfaInputBase },
  inheritAttrs: false,
  props: {
    id: { type: String, required: false, default: null }, // ID
    modelValue: { type: [String, Number], required: true }, // 入力値(v-model用)
    domain: { type: Object, required: false, default: () => ({}) }, // ドメイン情報
    digit: { type: [Number, String], required: false, default: undefined }, // 小数点以下桁数
    unit: { type: String, required: false, default: '' }, // 単位
    support: { type: Boolean, required: false, default: false }, // 入力補助(増減ボタン)有無指定
    step: { type: [Number, String], required: false, default: 0 }, // 増減数指定
    stepForValidation: { type: [String, Number], required: false, default: null }, // バリデーションチェック用増減値
    stepTable: { type: Object, required: false, default: null }, // 呼び値テーブル
    initialStep: { type: [Number, String], required: false, default: 0 }, // step専用初期値
    size: { type: String, required: false, default: '' }, // サイズ指定(small)
    min: { type: [Number, String], required: false, default: NaN }, // 最小値指定
    max: { type: [Number, String], required: false, default: NaN }, // 最大値指定
    clearable: { type: Boolean, required: false, default: false }, // 「クリアする」リンクの表示/非表示フラグ
    readonly: { type: Boolean, required: false, default: false },
    disabled: { type: Boolean, required: false, default: false },
    placeholder: { type: String, required: false, default: '' }, // プレースホルダー
    inputClass: { type: String, required: false, default: '' }, // クラス付与
    required: { type: Boolean, required: false, default: undefined }, // 必須項目
    controlAuthEnabled: { type: Boolean, required: false, default: true } // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
  },
  data() {
    return {
      ctrlReadonly: false, // 認可制御(読取専用)
      ctrlVisible: true, // 認可制御(表示/非表示)
      ctrlDisabled: false // 認可制御(編集不可)
    }
  },
  computed: {
    priceDomain() {
      let digitSpec = 0
      if (!('digit' in this.domain) || this.domain.digit < 0) {
        // digitが未指定の場合、unitによる判断を実行
        if (
          !('unit' in this.domain) ||
          this.domain.unit === '' ||
          this.domain.unit === 'JPY' ||
          this.domain.unit === '円'
        ) {
          // 通貨コードの設定値がない、または「JPY」である時
          digitSpec = 0
        } else {
          // 通貨コードの設定値が「JPY」以外である時
          digitSpec = 2
        }
      } else {
        // プロパティで小数点以下桁数が指定されていたらプロパティの値を
        // プロパティが指定なしならドメインの小数点以下桁数を採用する
        digitSpec = Number(new BigNumber(
          this.digit && !isNaN(this.digit) && this.digit >= 0
            ? this.digit
            : this.domain.digit
        ).toFixed(0, 1))
      }

      return {
        precision: this.domain ? this.domain.precision ?? 0 : 0, // 桁数
        digit: digitSpec, // 小数点以下桁数指定
        unit: this.domain ? this.domain.unit ?? '' : '', // 通貨コード指定
        format: this.domain ? this.domain.format ?? null : null, // 表示形式
        validate: this.domain ? this.domain.validate ?? null : null // チェック仕様
      }
    },
    bigNumberValue() {
      return this.modelValue ? new BigNumber(this.modelValue).toFixed() : this.modelValue
    }
  },
  mounted() {
    if (this.controlAuthEnabled) {
      controlAuthorization.bind(this)() // 認可制御
    }
  }
}
</script>
