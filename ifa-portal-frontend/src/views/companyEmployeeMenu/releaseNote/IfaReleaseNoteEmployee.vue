<template>
  <div>
    <screen-title :text="form.screenTitle.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
    >
      <el-card class="content-card">
        <div class="filter-container">
          <el-row>
            <el-col style="text-align: left;">
              <ifa-input-select
                id="registerDate"
                v-model="form.displayYear"
                label="表示対象年"
                prop="registerDate"
                :required="true"
                code-list-id="original"
                :original-list="form.displayYearList"
                size="small"
                style="width:120px;margin-bottom: 10px;"
                @change="handleChangeSelected"
              ></ifa-input-select>
            </el-col>
          </el-row>
          <el-row>
            <el-col style="text-align: left;">
              <ifa-button
                id="btnRegisterInfo"
                text="新規登録"
                width="95"
                small
                style="margin-left: 45px"
                action-type="originalAction"
                @app-action-handler="registerA003(true)"
              ></ifa-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <el-card
        v-if="form.releaseNoteDataList.length === 0"
        class="content-card"
        style="height: 650px;text-align: center;align-content: center;"
      >
        対象のリリースノートはありません。
      </el-card>
      <el-row v-for="(data, dataIndex) in form.releaseNoteDataList"
              :key="dataIndex"
      >
        <el-card class="content-card">
          <el-row style="margin-top: 10px; margin-bottom: 10px; align-items: center">
            <el-col :span="16">
              <span
                style="display: block; padding-left: 20px; font-size: 20px; font-weight: bold; overflow-wrap: break-word;"
              >
                {{ data.title }}
              </span>
            </el-col>
            <el-col :span="4"
                    style="text-align: right;"
            >
              <span>
                画面表示開始日
                {{ data.displayedDate }}
              </span>
            </el-col>
            <el-col :span="4"
                    style="text-align: center;"
            >
              <span>
                更新日時
                {{ data.updateTime }}
              </span>
            </el-col>
          </el-row>
          <el-row>
            <el-table
              id="ifaReleaseNoteEmployee"
              :data="data.dataList"
              border
              style="margin-bottom: 5px; width: 100%"
            >
              <el-table-column
                prop="menuName"
                label="メニュー名"
                min-width="400"
                align="center"
                header-align="center"
              >
              </el-table-column>
              <el-table-column
                prop="screenName"
                label="画面名"
                min-width="300"
                align="center"
                header-align="center"
              >
              </el-table-column>
              <el-table-column
                prop="content"
                label="内容"
                min-width="850"
                align="left"
                header-align="center"
              >
              </el-table-column>
            </el-table>
          </el-row>
          <el-row>
            <el-col
              :span="22"
              style="text-align: left;"
            >
              <ifa-button
                id="btnRegisterInfo"
                text="更新"
                width="95"
                small
                style="margin-left: 20px"
                action-type="originalAction"
                @app-action-handler="updateA004(data)"
              ></ifa-button>
            </el-col>
            <el-col
              :span="2"
              style="text-align: right;"
            >
              <ifa-button
                v-if="data.detailedFile==='1'"
                id="btnRegisterInfo"
                text="詳細を見る＞"
                width="95"
                small
                style="margin-right: 20px"
                action-type="originalAction"
                @app-action-handler="detailA005(data)"
              ></ifa-button>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
    </el-form>
  </div>

  <ifa-requester
    id="ifaReleaseNoteEmployeeInitializeA001"
    action-id="SUB0512-01#A001"
    action-type="requestAction"
    :request-model="a001RequestModel"
    @response-handler="initializeA001($event)"
  ></ifa-requester>
  <ifa-requester
    id="ifaReleaseNoteEmployeeSelectDisplayYearA002"
    action-id="SUB0512-01#A002"
    action-type="requestAction"
    :request-model="a002RequestModel"
    @response-handler="selectDisplayYearA002($event)"
  ></ifa-requester>
  <!-- 新規登録 ダイアログ -->
  <ifa-release-note-register
    :is-visible="ifaReleaseNoteRegisterVisible"
    @update-table="handleUpdateTable"
    @close-modal="ifaReleaseNoteRegisterVisible = false"
  ></ifa-release-note-register>
  <!-- 更新 ダイアログ -->
  <ifa-release-note-update
    :is-visible="ifaReleaseNoteUpdateVisible"
    :release-note-no="releaseNoteNo"
    @update-table="handleUpdateTable"
    @close-modal="ifaReleaseNoteUpdateVisible = false"
  ></ifa-release-note-update>
  <!-- 詳細を見る＞ ダイアログ -->
  <ifa-release-note-detail
    v-if="isDetailDisplayAndDestroy"
    :is-visible="ifaReleaseNoteDetailVisible"
    :release-note-no="releaseNoteNo"
    @close-modal="onCloseDialog"
  ></ifa-release-note-detail>
</template>

<script>

import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaReleaseNoteEmployeeFormModel } from './js/IfaReleaseNoteEmployeeFormModel'
import IfaReleaseNoteRegister from './IfaReleaseNoteRegister'
import IfaReleaseNoteUpdate from './IfaReleaseNoteUpdate'
import IfaReleaseNoteDetail from '@/views/releaseNote/IfaReleaseNoteDetail'
import { IfaReleaseNoteEmployeeA001RequestModel } from './js/IfaReleaseNoteEmployeeA001RequestModel'
import { IfaReleaseNoteEmployeeA002RequestModel } from './js/IfaReleaseNoteEmployeeA002RequestModel'

export default {
  components: {
    IfaReleaseNoteRegister,
    IfaReleaseNoteUpdate,
    IfaReleaseNoteDetail,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      form: new IfaReleaseNoteEmployeeFormModel(),
      ifaReleaseNoteRegisterVisible: false,
      ifaReleaseNoteUpdateVisible: false,
      ifaReleaseNoteDetailVisible: false, // ダイアログを開くおよび閉じるフラグ
      isDetailDisplayAndDestroy: false, // ダイアログの表示と破棄フラグ
      releaseNoteNo: ''
    }
  },
  computed: {
    a001RequestModel() {
      return new IfaReleaseNoteEmployeeA001RequestModel(this.form)
    },
    a002RequestModel() {
      return new IfaReleaseNoteEmployeeA002RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      // リリースノート一覧を取得する。
      this.form.displayYear = new Date().getFullYear().toString()
      this.handleGetReleaseNoteList()
    },
    /**
     * 初期表示対象年のリストを取得する
     */
    setDisplayYearList(minValue, maxValue) {
      const startYear = parseInt(minValue) // 開始年
      const endYear = parseInt(maxValue) // 終了の年
      // 表示対象年のリスト
      this.form.displayYearList = Array.from(
        { length: endYear - startYear + 1 },
        (_, i) => {
          const year = endYear - i
          return { key: String(year), value: `${year}年` }
        }
      )
    },
    /**
     * 取得した結果を画面の一覧に設定する
     */
    initializeA001(response) {
      this.setDisplayYearList(response.dataList[0]?.minYear, response.dataList[0]?.maxYear)
      this.form.releaseNoteDataList = this.transformData(response.dataList[0]?.ifaReleaseNoteEmployeeList)
    },
    selectDisplayYearA002(response) {
      this.form.releaseNoteDataList = this.transformData(response.dataList)
    },
    /**
     * データフォマードを変換する
     */
    transformData(responseList) {
      const groupedList = responseList.reduce((model, item) => {
        const key = `${item.title}_${item.displayedDate}_${item.updateTime}_${item.detailedFile}_${item.releaseNoteNo}`
        if (!model[key]) {
          model[key] = {
            title: item.title,
            displayedDate: item.displayedDate,
            updateTime: item.updateTime,
            detailedFile: item.detailedFile,
            releaseNoteNo: item.releaseNoteNo,
            dataList: []
          }
        }
        model[key].dataList.push({
          menuName: item.menuName,
          screenName: item.screenName,
          content: item.content,
          releaseNoteCoNo: item.releaseNoteCoNo
        })
        return model
      }, {})
      return Object.values(groupedList)
    },
    /**
     * リリースノート登録画面をポップアップ表示する。
     */
    registerA003() {
      this.ifaReleaseNoteRegisterVisible = true
    },
    /**
     * リリースノート更新画面をポップアップ表示する。
     */
    updateA004(data) {
      this.releaseNoteNo = data.releaseNoteNo
      this.ifaReleaseNoteUpdateVisible = true
    },
    /**
     * 選択したリリースノートのリリースノート詳細画面をポップアップ表示する。
     */
    detailA005(data) {
      this.isDetailDisplayAndDestroy = true
      this.releaseNoteNo = data.releaseNoteNo
      this.$nextTick(() => {
        this.ifaReleaseNoteDetailVisible = true
      })
    },
    /**
     * リリースノート一覧を取得する。
     */
    handleGetReleaseNoteList() {
      this.$nextTick(() => {
        document.getElementById('ifaReleaseNoteEmployeeInitializeA001').click()
      })
    },
    /**
     * 表示対象年に切り替えて、リリースノート一覧を再取得する
     */
    handleChangeSelected() {
      this.$nextTick(() => {
        document.getElementById('ifaReleaseNoteEmployeeSelectDisplayYearA002').click()
      })
    },
    /**
     * 「登録/更新」ダイアログが閉じられた後、リリースノート一覧を再取得する
     */
    handleUpdateTable() {
      this.handleGetReleaseNoteList()
    },
    /**
     * ダイアログを閉じる
     */
    onCloseDialog() {
      this.ifaReleaseNoteDetailVisible = false
      this.isDetailDisplayAndDestroy = false
    }
  }
}

</script>

<style scoped>
.content-card {
  margin: 0.5rem 1rem;
}
:deep(.el-table .cell) {
  white-space: pre-wrap;
  line-height: normal;
}
:deep(.el-table th) {
  font-weight: 400;
}
:deep(.el-table .el-table__cell) {
  padding: 5px 0;
}
:deep(.el-table .cell) {
  padding: 0 5px;
}
:deep(.el-table td) {
  height: 30px;
}
</style>
