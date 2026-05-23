<template>
  <el-table
    :data="tableData"
    border
    style="width: 100%"
    height="200"
  >
    <el-table-column
      prop="title"
      label="タイトル"
    ></el-table-column>
    <el-table-column
      prop="date"
      label="掲載日"
      width="180"
    ></el-table-column>
    <el-table-column
      prop="limit"
      label="確認期限"
      width="180"
    ></el-table-column>
    <el-table-column
      prop="checked"
      label="確認"
      width="180"
    ></el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      tableData: [{
        title: '仕組債ノックインのお知らせ(株価連動債(日本)電気)利率5.50%(年/税引前)',
        date: '2021/4/1',
        limit: '期限なし',
        checked: '未確認'
      }, {
        title: 'お知らせその２',
        date: '2021/4/1',
        limit: '期限なし',
        checked: '未確認'
      }, {
        title: 'お知らせその３',
        date: '2021/4/1',
        limit: '期限なし',
        checked: '未確認'
      }, {
        title: 'お知らせその４',
        date: '2021/4/1',
        limit: '期限なし',
        checked: '未確認'
      }]
    }
  }
}
</script>
<style scoped>
    .notice-unchecked {
        color:red;
    }
</style>
