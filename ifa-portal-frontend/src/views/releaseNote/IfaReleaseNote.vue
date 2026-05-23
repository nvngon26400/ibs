<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      :destroy-on-close="isDestroyOnClose"
      width="1200px"
      @open="onShow"
    >
      <el-form
        ref="form"
        :model="form"
        :inline="true"
      >
        <div>
          <el-row>
            <el-col
              :span="17"
            >
              <ifa-input-select
                id="registerDate"
                v-model="form.displayYear"
                label="表示対象年"
                prop="registerDate"
                :required="true"
                code-list-id="original"
                :original-list="form.displayYearList"
                size="small"
                @change="handleChangeSelected"
              ></ifa-input-select>
            </el-col>
            <el-col :span="5">
              <ifa-input-check
                id="nextDisp"
                v-model="form.nextDispFlg"
                code-list-id="original"
                :original-list="nextDispFlgOption"
                :is-button="false"
                class="custom-checkbox"
                @change="changeNextDispFlg"
              >
              </ifa-input-check>
            </el-col>
            <el-col :span="2">
              <ifa-button
                text="戻る"
                color="secondary"
                small
                action-type="originalAction"
                @app-action-handler="onDialogClose"
              ></ifa-button>
            </el-col>
          </el-row>
        </div>
        <el-row class="title-class">
          <el-col>
            <span>
              {{ form.screenTitle.name }}
            </span>
          </el-col>
        </el-row>
        <div class="scrollable-div">
          <el-card
            v-if="form.releaseNoteDataList.length === 0"
            style="height: 650px;text-align: center;align-content: center;"
          >
            対象のリリースノートはありません。
          </el-card>
          <el-row
            v-for="(data, dataIndex) in form.releaseNoteDataList"
            :key="dataIndex"
          >
            <el-card class="content-card">
              <el-row style="margin-bottom: 5px; align-items: center">
                <el-col :span="18">
                  <span
                    style="display: block; padding-left: 20px; font-size: 20px; font-weight: bold; overflow-wrap: break-word;"
                  >
                    {{ data.title }}
                  </span>
                </el-col>
                <el-col :span="6">
                  <span style="margin-left: 20px">
                    更新日時
                    {{ data.updateTime }}
                  </span>
                </el-col>
              </el-row>
              <el-row>
                <el-table
                  id="ifaReleaseNote"
                  :data="data.dataList"
                  border
                  style="margin-bottom: 5px; width: 100%"
                >
                  <el-table-column
                    prop="menuName"
                    label="メニュー名"
                    min-width="300"
                    align="center"
                    header-align="center"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="screenName"
                    label="画面名"
                    min-width="200"
                    align="center"
                    header-align="center"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="content"
                    label="内容"
                    min-width="585"
                    align="left"
                    header-align="center"
                  >
                  </el-table-column>
                </el-table>
              </el-row>
              <el-row>
                <el-col style="text-align: right;">
                  <ifa-button
                    v-show="data.detailedFile==='1'"
                    id="btnRegisterInfo1"
                    text="詳細を見る＞"
                    width="95"
                    small
                    action-type="originalAction"
                    @app-action-handler="detailA005ResponseHandler(data)"
                  ></ifa-button>
                </el-col>
              </el-row>
            </el-card>
          </el-row>
        </div>
      </el-form>
    </el-dialog>
  </div>
  <ifa-requester
    id="ifaReleaseNoteInitializeA001"
    action-id="SUB00-07_1#A001"
    action-type="requestAction"
    :request-model="IfaReleaseNoteA001RequestModel"
    @response-handler="initializeA001($event)"
  ></ifa-requester>
  <ifa-requester
    id="ifaReleaseNoteSelectDisplayYearA002"
    action-id="SUB00-07_1#A002"
    action-type="requestAction"
    :request-model="IfaReleaseNoteA002RequestModel"
    @response-handler="selectDisplayYearA002($event)"
  ></ifa-requester>
  <ifa-requester
    id="ifaReleaseNoteUpdateNextDispFlgA003"
    action-id="SUB00-07_1#A003"
    action-type="requestAction"
    :msg-title="form.screenTitle.name"
    :request-model="IfaReleaseNoteA003RequestModel"
    @response-handler="updateNextDispFlgA003($event)"
    @response-error-handler="updateNextDispFlgErrorA003($event)"
  ></ifa-requester>
  <!-- 詳細を見る＞ ダイアログ -->
  <ifa-release-note-detail
    v-if="isDetailDisplayAndDestroy"
    :is-visible="ifaReleaseNoteDetailVisible"
    :release-note-no="releaseNoteNo"
    @close-modal="closeModal"
  ></ifa-release-note-detail>
</template>

<script>
import { IfaReleaseNoteFormModel } from './js/IfaReleaseNoteFormModel'
import { IfaReleaseNoteA001RequestModel } from './js/IfaReleaseNoteA001RequestModel'
import { IfaReleaseNoteA002RequestModel } from './js/IfaReleaseNoteA002RequestModel'
import { IfaReleaseNoteA003RequestModel } from './js/IfaReleaseNoteA003RequestModel'
import IfaReleaseNoteDetail from './IfaReleaseNoteDetail'
import { useVModel } from 'vue-composable'

export default {
  components: {
    IfaReleaseNoteDetail
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  emits: ['initializeError', 'close-modal', 'open-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaReleaseNoteFormModel(),
      nextDispFlgOption: [{ key: '1', value: '次回から表示しない' }, { key: '0', value: '次回から表示しない' }],
      ifaReleaseNoteDetailVisible: false, // ダイアログを開くおよび閉じるフラグ
      isDetailDisplayAndDestroy: false, // ダイアログの表示と破棄フラグ
      localNextDispFlg: '',
      isDestroyOnClose: false
    }
  },
  computed: {
    IfaReleaseNoteA001RequestModel() {
      return new IfaReleaseNoteA001RequestModel(this.form)
    },
    IfaReleaseNoteA002RequestModel() {
      return new IfaReleaseNoteA002RequestModel(this.form)
    },
    IfaReleaseNoteA003RequestModel() {
      return new IfaReleaseNoteA003RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      if (!this.form.displayYear) {
        this.form.displayYear = new Date().getFullYear().toString()
      }
      this.handleGetReleaseNoteList()
    },
    /**
     * 表示対象年を取得
     */
    setDisplayYearList(minValue) {
      const currentYear = new Date().getFullYear() // 現在の年
      this.form.displayYearList = Array.from(
        { length: currentYear - parseInt(minValue) + 1 },
        (_, i) => {
          const year = currentYear - i
          return { key: String(year), value: `${year}年` }
        }
      )
    },
    /**
     * リリースノート一覧を取得する。
     */
    handleGetReleaseNoteList() {
      // this.form.releaseNoteDataList = this.transformData(dataList)
      this.$nextTick(() => {
        document.getElementById('ifaReleaseNoteInitializeA001').click()
      })
    },
    /**
     * 表示対象年に切り替えて、リリースノート一覧を再取得する
     */
    handleChangeSelected() {
      this.$nextTick(() => {
        document.getElementById('ifaReleaseNoteSelectDisplayYearA002').click()
      })
    },
    /**
     * 取得した結果を画面の一覧に設定する
     */
    initializeA001(response) {
      this.form.nextDispFlg = response.dataList[0]?.nextDispFlg
      this.localNextDispFlg = response.dataList[0]?.nextDispFlg
      this.setDisplayYearList(response.dataList[0]?.minYear)
      this.form.releaseNoteDataList = this.transformData(response.dataList[0]?.ifaReleaseNoteList)
    },
    /**
     * 取得した結果を画面の一覧に設定する
     */
    selectDisplayYearA002(response) {
      this.form.releaseNoteDataList = this.transformData(response.dataList[0]?.ifaReleaseNoteList)
    },
    /**
     * 「次回から表示しない」を更新
     */
    updateNextDispFlgA003(response) {
      // 更新に成功した場合、「次回から表示しない」最新の値が保存されます。
      this.localNextDispFlg = this.form.nextDispFlg
    },
    /**
     * 「次回から表示しない」を更新失敗した場合
     */
    updateNextDispFlgErrorA003(response) {
      // 更新に失敗した場合、「次回から表示しない」更新前の値が設定されます
      this.form.nextDispFlg = this.localNextDispFlg
    },
    /**
     * 次回から表示しないのチェックボックスの状態を切り替える
     */
    changeNextDispFlg() {
      this.$nextTick(() => {
        document.getElementById('ifaReleaseNoteUpdateNextDispFlgA003').click()
      })
    },
    /**
     * データフォマードを変換する
     */
    transformData(responseList) {
      const groupedList = responseList.reduce((model, item) => {
        const key = `${item.title}_${item.updateTime}_${item.detailedFile}_${item.releaseNoteNo}`
        if (!model[key]) {
          model[key] = {
            title: item.title,
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
     * 詳細を見る＞ ダイアログを開く
     */
    detailA005ResponseHandler(data) {
      this.isDetailDisplayAndDestroy = true
      this.releaseNoteNo = data.releaseNoteNo
      this.$nextTick(() => {
        this.ifaReleaseNoteDetailVisible = true
        this.isDestroyOnClose = false
        this.$emit('close-modal')
      })
    },
    /**
     * 戻るボタンを押下
     */
    onDialogClose() {
      this.$emit('close-modal')
      this.ifaReleaseNoteDetailVisible = false
      this.isDetailDisplayAndDestroy = false
      this.isDestroyOnClose = true
      this.form.displayYear = ''
    },
    /**
     * 詳細を見る＞ ダイアログを閉じる
     */
    closeModal() {
      this.ifaReleaseNoteDetailVisible = false
      this.isDetailDisplayAndDestroy = false
      this.$emit('open-modal')
    }
  }
}

</script>

<style scoped>
.content-card {
  margin: 0.5rem 1rem;
}
.title-class {
  background-color: #005FCC;
  color: white;
  height: 30px;
  width: 1190px;
  font-size: large;
  text-align: center;
  align-items: center;
  margin-left: -20px;
}
.scrollable-div {
  height: 650px;
  overflow-y: auto;
  padding-right: 5px;
}
:deep(.el-dialog__header) {
  padding: 0;
}
:deep(.el-checkbox__input.is-checked + .el-checkbox__label) {
  font-size: 18px;
  color: #092987;
}
:deep(.el-checkbox__label) {
  font-size: 18px;
  color: #092987;
}
:deep(.el-checkbox__inner) {
  width: 18px;
  height: 18px;
}
:deep(.el-checkbox__inner::after) {
  height: 9px;
  left: 6px;
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
