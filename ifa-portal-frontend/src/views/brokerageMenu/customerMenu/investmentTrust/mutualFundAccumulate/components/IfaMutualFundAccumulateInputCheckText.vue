<template>
  <div class="wrapper">
    <span class="dot-line">積立買付可能ファンドのみ積立買付が可能です。</span>
    <span class="dot-line">
      <span>ご希望により年２回まで「ボーナス月コース」の設定が可能です。(「ボーナス月コース」のみの設定はできません。)</span>
      <br>
      <span class="sub-line">また、変更の場合は、変更箇所のみご記入ください。</span>
    </span>
    <span class="dot-line">
      <span>積立金額は各コースともそれぞれ１００円以上、１円単位（手数料・消費税含）となります。</span>
      <br>
      <span class="sub-line">（例外的に１００円で積立設定出来ない投信もありますので、当社WEBサイトよりご確認ください。）</span>
    </span>
    <span class="dot-line">
      <span>積立済み及び入力中の積立設定の１年あたり概算金額を１２０万円から控除した金額の範囲内で設定が可能です。</span>
      <br>
      <span class="sub-line">「１年あたりの積立金額」と「ボーナス月設定の積立金額」の合計が１２０万円を超えないように設定してください。</span>
    </span>
    <span class="dot-line">積立金額が１００円以上１円単位ではない銘柄については、NISA預りを指定した場合でも指定ができません。</span>
    <span class="dot-line">ご指定金額をお客様のMRF、お預り金、委託保証金等の残高から振替し、ご指定のファンドを買付いたします。</span>
    <span class="dot-line">発注時に、買付余力が不足している場合、当該ファンドの発注は行いません。</span>
    <span class="dot-line">
      <span>異なるファンドで申込日が同一である場合、ボーナス月コース以外の協会コード順、続いてボーナス月コースの協会コード順で発注されます。</span>
      <br>
      <span class="sub-line">また、投資信託の約定日はファンドによって異なります。</span>
    </span>
    <span class="dot-line">投資信託の買付単価は約定日の基準価額となります。申込予定日が取引所休場等による申込不可日である場合、原則として発注を行います。</span>
  </div>
</template>

<style lang="scss" scoped>
.wrapper {
  display: flex;
  flex-direction: column;
  line-height: 1.5;
}

.sub-line {
  padding-left: 14px;
}

.dot-line {
  &::before {
    content: '・',
  }
}

</style>
