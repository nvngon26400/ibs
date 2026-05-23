<template>
  <div>
    <table
      cellspacing="0"
      cellpadding="0"
      border="0"
      class="el-table"
    >
      <colgroup>
        <col span="11">
      </colgroup>
      <tbody>
        <tr>
          <th
            colspan="1"
            rowspan="4"
            class="order-table__left order-table__top"
          >顧客名(漢字)<br>部店-口座番号</th>
          <th
            colspan="1"
            rowspan="4"
            class="order-table__top"
          >注文番号</th>
          <th
            colspan="1"
            rowspan="4"
            class="order-table__top"
          >注文状況</th>
          <th
            colspan="1"
            rowspan="4"
            class="order-table__top"
          >注文種別</th>
          <th
            colspan="6"
            rowspan="1"
            class="order-table__top"
          >銘柄・コード・市場</th>
          <th
            colspan="1"
            rowspan="4"
            class="order-table__top"
          >営業員名<br>営業員コード<br>仲介業者名<br>仲介業者コード</th>
        </tr>
        <tr>
          <th colspan="2">取引<br>預り/手数料</th>
          <th>注文日<br>注文期間</th>
          <th>注文株数<br>(未約定)</th>
          <th>執行<br>条件</th>
          <th>注文単価<br>現在値</th>
        </tr>
        <tr>
          <th colspan="6">条件</th>
        </tr>
        <tr>
          <th>約定</th>
          <th>市場</th>
          <th>約定日時</th>
          <th>約定株数</th>
          <th colspan="2">約定単価</th>
        </tr>
        <tr>
          <td
            rowspan="3"
            class="order-table__left"
          >そのに 太郎<br>XXX-XXXXXXX2</td>
          <td rowspan="3">43</td>
          <td rowspan="3">完了<br>(全部約定)</td>
          <td rowspan="3">通常注文</td>
          <td
            rowspan="1"
            colspan="6"
          >三菱UFJフィナンシャルグループ 8306 SOR</td>
          <td rowspan="3">坂井益<br>4202<br>住信SBIネット株式会社<br>3333</td>
        </tr>
        <tr>
          <td colspan="2">現物買<br>特定/A</td>
          <td>21/05/19<br>21/05/19</td>
          <td>100</td>
          <td>指値</td>
          <td>635<br>634.1</td>
        </tr>
        <tr>
          <td>約定</td>
          <td>東証</td>
          <td>21/05/19<br>13:42:37</td>
          <td>100</td>
          <td colspan="2">634.3</td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<script>

export default {
  data() {
    return {
      sample: 'orderStatusTable'
    }
  }
}
</script>
<style lang="scss" scoped>
.el-table th, .el-table td {
    border-right: 1px solid #EBEEF5;
    border-bottom: 1px solid #EBEEF5;
    text-align: center;
    vertical-align: top;
}
.order-table__left {
  border-left: 1px solid #EBEEF5;
}
.order-table__top {
  border-top: 1px solid #EBEEF5;
}
</style>
