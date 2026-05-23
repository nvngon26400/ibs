<template>
  <!-- 画面名：登録情報 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <el-form
      id="inpregisterInfoForm"
      ref="form"
      :model="form"
      :rules="rules"
      :inline="true"
    >
      <div class="container">
        <!-- 登録情報分類エリア -->
        <el-col
          :span="5"
        >
          <div>
            <el-card
              class="content-card-left"
              :style="form.categoryExtensionNameList.length > 30 ? 'overflow-y: scroll;' : ''"
            >
              <el-row style="padding: 5px;">
                <el-col
                  v-for="(itemCategory, indexCategory) in form.categoryExtensionNameList"
                  :key="indexCategory"
                >
                  <el-link
                    style="padding: 4px;"
                    @click="scrollToTop(indexCategory)"
                  >
                    {{ itemCategory.categoryExtensionName }}
                  </el-link>
                </el-col>
              </el-row>
            </el-card>
          </div>
        </el-col>

        <!-- 登録情報詳細エリア -->
        <el-col
          :span="15"
        >
          <el-card style="margin: 0.5rem; height: 745px;">
            <div
              ref="contentArea"
              class="content-card-right"
            >
              <div
                v-for="(itemCategoryRigth, indexRigth) in form.categoryExtensionNameList"
                :id="'section-' + indexRigth"
                :key="indexRigth"
              >
                <el-row>
                  <el-col>
                    <!-- 分類名 -->
                    <screen-title
                      :text="itemCategoryRigth.categoryExtensionName"
                      style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                    >
                      <span
                        v-if="itemCategoryRigth.categoryExtensionName === '顧客カード'"
                        class="ext-link-label"
                      >
                        <ifa-link
                          ref="ifalink1"
                          :url-id="5"
                          :pattern-id="1"
                          disp-name="口座属性変更照会"
                          link-icon-image="externalLink"
                          http-method="GET"
                          :param-object="{
                            TradingPass: '00',
                            AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                            UserName: form.ccsOpId
                          }"
                        ></ifa-link>
                      </span>
                    </screen-title>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col>
                    <!-- 分類名補足説明 -->
                    <div
                      style="border: 1px solid #b9babb; padding:8px;"
                      v-html="itemCategoryRigth.remarks"
                    ></div>
                  </el-col>
                </el-row>

                <div
                  v-for="(itemHeader, indexHeader) in form.registerInfoHeaderValueList"
                  :key="indexHeader"
                >
                  <el-row v-if="itemCategoryRigth.categoryExtensionCd == itemHeader.inforExtensionCd">
                    <el-card style="margin: 1rem;">
                      <div>
                        <!-- 分類詳細補足説明 -->
                        <el-tooltip
                          effect="light"
                          :show-arrow="false"
                          :content="itemHeader.remarks"
                          placement="top"
                          offset="-26"
                        >
                          <!-- 分類詳細名 -->
                          <screen-title
                            :text="itemHeader.inforExtensionName"
                            style="background-color: #eee; margin-left: 0px;"
                          ></screen-title>
                        </el-tooltip>
                      </div>

                      <!-- 登録情報詳細一覧 -->
                      <div :style="itemHeader.valueCount > 2 ? 'overflow-x: scroll;' : ''">
                        <table
                          class="_table__body"
                          style="table-layout: fixed;"
                        >
                          <tbody>
                            <!-- ヘッダ1 -->
                            <tr v-if="itemHeader.maxColumn >= 1">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue01) }}</th>
                              <!-- 値1-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue01) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ2 -->
                            <tr v-if="itemHeader.maxColumn >= 2">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue02) }}</th>
                              <!-- 値2-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue02) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ3 -->
                            <tr v-if="itemHeader.maxColumn >= 3">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue03) }}</th>
                              <!-- 値3-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue03) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ4 -->
                            <tr v-if="itemHeader.maxColumn >= 4">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue04) }}</th>
                              <!-- 値4-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue04) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ5 -->
                            <tr v-if="itemHeader.maxColumn >= 5">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue05) }}</th>
                              <!-- 値5-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue05) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ6 -->
                            <tr v-if="itemHeader.maxColumn >= 6">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue06) }}</th>
                              <!-- 値6-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue06) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ7 -->
                            <tr v-if="itemHeader.maxColumn >= 7">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue07) }}</th>
                              <!-- 値7-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue07) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ8 -->
                            <tr v-if="itemHeader.maxColumn >= 8">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue08) }}</th>
                              <!-- 値8-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue08) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ9 -->
                            <tr v-if="itemHeader.maxColumn >= 9">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue09) }}</th>
                              <!-- 値9-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue09) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ10 -->
                            <tr v-if="itemHeader.maxColumn >= 10">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue10) }}</th>
                              <!-- 値10-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue10) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ11 -->
                            <tr v-if="itemHeader.maxColumn >= 11">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue11) }}</th>
                              <!-- 値11-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue11) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ12 -->
                            <tr v-if="itemHeader.maxColumn >= 12">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue12) }}</th>
                              <!-- 値12-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue12) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ13 -->
                            <tr v-if="itemHeader.maxColumn >= 13">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue13) }}</th>
                              <!-- 値13-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue13) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ14 -->
                            <tr v-if="itemHeader.maxColumn >= 14">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue14) }}</th>
                              <!-- 値14-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue14) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ15 -->
                            <tr v-if="itemHeader.maxColumn >= 15">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue15) }}</th>
                              <!-- 値15-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue15) }}</td>
                              </template>
                            </tr>
                            <!-- ヘッダ16 -->
                            <tr v-if="itemHeader.maxColumn >= 16">
                              <th class="_table__header __left __bold __width_header">{{ $_out(itemHeader.headerValue16) }}</th>
                              <!-- 値16-1~ -->
                              <template
                                v-for="(itemValue, indexValue) in itemHeader.registerInfoValueList"
                                :key="indexValue"
                              >
                                <td
                                  :class="`${itemHeader.valueCount > 2 ? '_table__data __left __width_value' : '_table__data __left'}`"
                                >{{ $_out(itemValue.detailValue16) }}</td>
                              </template>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </el-card>
                  </el-row>
                </div>
              </div>
            </div>
          </el-card>
        </el-col>
      </div>
    </el-form>
    <ifa-requester
      id="IfaRegisterInfoRequest"
      action-id="SUB0202_0701-01#A001"
      action-type="requestAction"
      @response-handler="initializeA001"
    ></ifa-requester>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaRegisterInfoModel } from './js/IfaRegisterInfoModel'

export default {
  components: {
    screenTitle
  },
  data() {
    return {
      form: new IfaRegisterInfoModel()
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  },
  methods: {
    onShow(resume) {
      // 初期表示時のみA001初期化処理を実行
      this.$nextTick(() => {
        document.getElementById('IfaRegisterInfoRequest').click()
      })
    },
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      // 口座属性変更照会リンクの初期化
      this.$nextTick(() => {
        this.$refs['ifalink1']?.[0]?.trigger()
      })
    },
    // 選択された登録情報分類に対し、登録情報エリアの該当箇所にスクロールする。
    scrollToTop(indexRigth) {
      this.$nextTick(() => {
        const targetElement = document.getElementById(`section-${indexRigth}`)
        if (targetElement) {
          this.$refs.contentArea.scrollTop = targetElement.offsetTop - this.$refs.contentArea.offsetTop
        }
      })
    }
  }
}

</script>
<style lang="scss"  scoped>
@import "@/styles/table.scss";
.container {
  display: flex;
}
.content-card-left {
  margin: 0.5rem 2rem;
  height: 745px;
}
.content-card-right {
  height: 731px;
  overflow-y: scroll;
}
.__bold {
  font-weight: bold;
}
.__width_value {
  width: 326px;
}
.__width_header {
  width: 240px;
}
.ext-link-label {
  margin-left: auto;
  margin-right: 1rem;
}
</style>
