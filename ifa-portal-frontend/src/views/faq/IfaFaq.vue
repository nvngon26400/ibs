<template>
  <div class="ifa-faq-wrapper">
    <!-- ヘッダー -->
    <div class="header">
      <!-- SBI証券ロゴ (イメージ) -->
      <img :src="logo">
      <div class="faq-title">{{ form.title.name }}</div>
    </div>

    <!-- メイン -->
    <el-row class="main">
      <!-- 左メニュー -->
      <el-col
        :span="8"
        class="left-side-menu__wrapper"
      >
        <div>
          <ifa-faq-side-menu
            :menu-list="menuList"
            @menu-click="handleMenuClickA003A004"
          ></ifa-faq-side-menu>
        </div>
      </el-col>
      <!-- 左メニュー -->

      <!-- コンテンツ -->
      <el-col :span="16">
        <!-- 検索 -->
        <el-card>
          <div class="input-search--bold">検索する</div>
          <div>
            <el-form
              ref="form"
              :model="form"
            >
              <ifa-input-text
                id="searchWord"
                v-model="form.searchWord"
                prop="searchWord"
                original-screen-id="SUB00-05"
                placeholder="キーワードを入力"
                prefix-icon="Search"
                :domain="IfaText255DomainModel"
                @change="handleSearchWordKeydownA002"
              ></ifa-input-text>
            </el-form>
          </div>
        </el-card>
        <!-- 検索 -->

        <!-- QA本体 -->
        <div class="faq-display">
          <ifa-faq-display
            :display-mode="displayMode"
            :form="form"
            :search-counter="searchCounter"
            @faq-click="handleFaqClickA005"
            @back-click="handleBackClickA007"
          ></ifa-faq-display>
        </div>
        <!-- QA本体 -->
      </el-col>
    </el-row>

    <div class="footer"></div>

    <!-- X001: 初期表示 -->
    <ifa-requester
      id="ifaFaqX001"
      action-id="SUB00-05#X001"
      action-type="requestAction"
      :request-model="ifaFaqX001RequestModel"
      @response-handler="responseHandlerX001"
      @response-error-handler="responseErrorHandlerX001"
    ></ifa-requester>
    <!-- A002: 全文検索 -->
    <ifa-requester
      id="ifaFaqA002"
      action-id="SUB00-05#A002"
      action-type="requestAction"
      :request-model="ifaFaqA002RequestModel"
      @response-handler="responseHandlerA002"
      @response-error-handler="responseErrorHandlerA002"
    ></ifa-requester>
    <!-- A005: コンテンツ表示 -->
    <ifa-requester
      id="ifaFaqA005"
      action-id="SUB00-05#A005"
      action-type="requestAction"
      :request-model="ifaFaqA005RequestModel"
      @response-handler="responseHandlerA005"
      @response-error-handler="responseErrorHandlerA005"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaFaqSideMenu from '@/views/faq/components/IfaFaqSideMenu'
import IfaFaqDisplay from '@/views/faq/components/IfaFaqDisplay'
import IfaText255DomainModel from '@/resource/domain/IfaText255DomainModel.json'
import { IfaFaqFormModel } from './js/IfaFaqFormModel'
import { IfaFaqX001RequestModel } from './js/IfaFaqX001RequestModel'
import { IfaFaqA002RequestModel } from './js/IfaFaqA002RequestModel'
import { IfaFaqA005RequestModel } from './js/IfaFaqA005RequestModel'

export default {
  components: {
    IfaFaqSideMenu,
    IfaFaqDisplay
  },
  emits: ['close-modal'],
  data() {
    return {
      IfaText255DomainModel: IfaText255DomainModel,
      logo: require('@/assets/logo.png'),

      form: new IfaFaqFormModel(),

      // <ifa-faq-side-menu>連携
      contentsList: [],

      // <ifa-faq-display>連携
      displayMode: '',
      lastDisplayMode: '',
      // 検索した時に1ページ目に戻すために利用する
      // ifa-faq-display ではカウンターをウォッチして変化したらページを1ページに戻す
      searchCounter: 1
    }
  },
  computed: {
    ifaFaqX001RequestModel() {
      return new IfaFaqX001RequestModel(this.form)
    },
    ifaFaqA002RequestModel() {
      return new IfaFaqA002RequestModel(this.form)
    },
    ifaFaqA005RequestModel() {
      return new IfaFaqA005RequestModel(this.form)
    },
    // コンテンツリストを元に左サイドメニューを構築する
    menuList() {
      const menuList = []
      for (const content of this.contentsList) {
        if (!menuList.find(menu => menu.name === content.primaryItem)) {
          // コンテンツリストに大項目が未登録なら登録する
          menuList.push({
            lId: content.contentsNo,
            name: content.primaryItem,
            menuList: []
          })
        }
      }
      for (const ml of menuList) {
        const contentList = this.contentsList.filter(cl => cl.primaryItem === ml.name)
        for (const cl of contentList) {
          // メニューリストに小項目が未登録なら登録する
          if (!ml.menuList.find(ml => ml.name === cl.tertiaryItem)) {
            if (cl.tertiaryItem) {
              ml.menuList.push({
                sId: cl.contentsNo,
                name: cl.tertiaryItem
              })
            }
          }
        }
      }

      return menuList
    }
  },
  // X001: 初期表示
  mounted() {
    // コンテンツNoが指定されている場合は、A005コンテンツ表示を呼出し
    this.form.contentsNo = this.$store.getters.pageInfo.faqParam ?? undefined
    document.getElementById('ifaFaqX001').click()
  },
  methods: {
    // X001: 初期表示レスポンス
    responseHandlerX001(response) {
      Object.assign(this.contentsList, response.dataList[0].contentsList)
      if (response.dataList[0].contentsNo) {
        // コンテンツNo. が含まれている場合は､A005 コンテンツ表示アクションを行う
        this.handleFaqClickA005(response.dataList[0].contentsNo)
      }
    },
    // X001: 初期表示エラーレスポンス
    responseErrorHandlerX001(response) {
      this.$_logDebug('IfaFaq', response)
    },
    // A002: 全文検索レスポンス
    responseHandlerA002(response) {
      if (response.dataList.length > 0) {
        Object.assign(this.form, response.dataList[0])
        if (this.form.searchResultListCount > 1000) {
          // 検索結果リストが1000件超の場合は、前から1000件までで検索結果リストを更新
          this.form.searchResultList = this.form.searchResultList.slice(0, 1000)
          this.form.searchResultListCount = 1000
        }
        // 表示順はコンテンツNoの昇順にするためソート処理を行う
        this.form.searchResultList = this.form.searchResultList.sort((a, b) => Number(a.contentsNo) - Number(b.contentsno))
        // 1ページ目を表示させるためカウンターをインクリメントする
        this.searchCounter++
        this.setDisplayMode('search')
      } else {
        // notFoundエラーの場合、検索結果を0件にする
        this.form.searchResultListCount = 0
        this.setDisplayMode('search')
      }
    },
    // A002: 全文検索エラーレスポンス
    responseErrorHandlerA002(response) {
      this.$_logDebug('IfaFaq', response)
    },
    // A005: コンテンツ表示レスポンス
    responseHandlerA005(response) {
      Object.assign(this.form, response.dataList[0])
      if (this.form.heading && this.form.contentText) {
        this.setDisplayMode('detail')
      }
    },
    // A005: コンテンツ表示エラーレスポンス
    responseErrorHandlerA005(response) {
      this.$_logDebug('IfaFaq', response)
    },
    // A005 検索見出し / 初期表示(A001) でコンテンツNoが指定
    handleFaqClickA005(id) {
      this.form.heading = ''
      this.form.contentText = ''

      this.form.contentsNo = id
      document.getElementById('ifaFaqA005').click()
    },
    // A003 大項目指定(小項目リストが存在しない場合) / A004 小項目指定
    handleMenuClickA003A004(item) {
      const index = item.index
      const representContent = this.contentsList.find(elm => elm.contentsNo === index)
      const primaryItem = representContent.primaryItem
      const tertiaryItem = representContent.tertiaryItem
      this.form.faqList = this.contentsList.filter(content => {
        return content.primaryItem === primaryItem && content.tertiaryItem === tertiaryItem
      })
      this.setDisplayMode('list')

      this.form.searchWord = ''
      this.form.searchResultListCount = 0
    },
    // A007 戻る
    handleBackClickA007() {
      // 初期表示でコンテンツ表示の場合は、動作なしで画面変化なし
      if (this.lastDisplayMode) {
        this.displayMode = this.lastDisplayMode
      }
    },
    // 表示モードを切り替える
    // 戻るボタンで元の画面に戻せるように最後の表示モードを保持する
    setDisplayMode(newMode) {
      this.lastDisplayMode = this.displayMode
      this.displayMode = newMode
    },
    // A002 全文検索
    handleSearchWordKeydownA002(str) {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (str.length) {
            // 検索ワードが1文字以上の場合は検索を実行
            document.getElementById('ifaFaqA002').click()
          } else {
            // 検索ワードが0文字の場合は検索結果を0件にする
            this.form.searchResultListCount = 0
          }
        } else {
          return false
        }
      })
    }
  }
}
</script>

<!-- 本コンポーネント専用 -->
<style lang="scss" scoped>
.ifa-faq-wrapper {
  min-width: 1280px;
  display: flex;
  flex-direction: column;
  height: 100lvh;

  .header {
    top: 0;
    .faq-title {
      text-align: center;
      padding: 20px 20px 10px;
      background-color: #305496;
      color: #FFF;
      line-height: 24px;
      font-size: 18px;
    }
  }
  .main {
    flex: 1;
    overflow-y: hidden;
    .left-side-menu__wrapper {
      background-color: #E6E8F0;
    }
    .input-search--bold {
      font-weight: 700;
    }
  }
  .footer {
    bottom: 0;
  }

  :deep(.el-dialog__header) {
    background-color: #305496;
  }
  :deep(.el-input__wrapper) {
    font-size: 16px;
    border: 3px solid #305496;
  }
  :deep(.el-dialog__title) {
    color: #FFF;
  }
  :deep(.el-menu-item), :deep(.el-sub-menu__title) {
    height: 36px;
    line-height: 36px;
    &:hover {
      background-color: var(--el-menu-hover-bg-color) !important;
    }
  }
}
:deep(.el-input__prefix) {
  font-size: 18px;
}
</style>
