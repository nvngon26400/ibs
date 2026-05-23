<template>
  <div
    id="faq-display"
    class="faq-display__wrapper"
    :class="[
      {
        'page-top-visible': pageTopVisible
      }
    ]"
  >
    <div>
      <!-- 検索結果表示 -->
      <div
        v-if="displayMode === 'search'"
        class="faq-display search faq-scrollable"
        :class="[
          {
            'is-last-page': isLastPage
          }
        ]"
      >
        <!-- 検索ワード -->
        <!-- 検索結果リストが0件の場合は非表示 -->
        <div v-if="form.searchResultListCount > 0">
          <strong>{{ $_out(searchWord) }}</strong>
          <span>&nbsp;の検索結果</span>
          <strong>{{ $_out($_withCommaInteger(form.searchResultListCount)) }}</strong>
          <span>件</span>
          <div
            v-for="faq in searchResultList"
            :key="faq.contentsNo"
            class="faq-item mt-1rem"
          >
            <div class="list-row">
              <div
                class="question link"
                @click="listClick(faq.contentsNo)"
              >
                <span v-html="showSearchWord(faq.heading)"></span>
              </div>
              <div class="answer text">
                <span v-html="showSearchWord(faq.digest)"></span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- /検索結果表示 -->

      <!-- メニュー選択表示 -->
      <div
        v-if="displayMode === 'list'"
        class="faq-display faq-scrollable"
      >
        <div
          v-for="faq in form.faqList"
          :key="faq.contentsNo"
          class="faq-item mb-1rem"
        >
          <div
            class="question link"
            @click="listClick(faq.contentsNo)"
          >
            <div class="q-icon">Q</div>
            <span>{{ faq.heading }}</span>
          </div>
        </div>
      </div>
      <!-- メニュー選択表示 -->

      <!-- QA詳細表示 -->
      <div
        v-if="displayMode === 'detail'"
        class="faq-display"
        :style="searchCustomStyle"
      >
        <div class="back-button">
          <ifa-button
            text="戻る"
            color="secondary"
            small
            width="120"
            action-type="originalAction"
            @app-action-handler="handleBackClickA007"
          ></ifa-button>
        </div>
        <div class="list-row_detail">
          <div class="question">
            <div class="q-icon">Q</div>
            <span class="text bold">{{ form.heading }}</span>
          </div>
          <div class="answer mt-1rem border faq-scrollable_detail">
            <div class="a-icon">A</div>
            <span
              class="text"
              v-html="form.contentText"
            ></span>
          </div>
        </div>
      </div>
      <!-- メニュー（検索結果）の選択結果表示 -->
    </div>

    <!-- 最下段に表示する -->
    <el-row
      v-if="(displayMode === 'search' && form.searchResultListCount > 0) || pageTopVisible"
      :class="(displayMode === 'search' && form.searchResultListCount > 0)? 'footer': 'footer_detail'"
    >
      <!-- ページネーション -->
      <el-pagination
        v-if="displayMode === 'search' && form.searchResultListCount > 0"
        v-model:current-page="currentPage"
        class="pagination"
        layout="prev, pager, next"
        :page-size="pageSize"
        :pager-count="pagerCount"
        :total="form.searchResultListCount"
        @current-change="setPage"
      ></el-pagination>

      <!-- 一番上に戻る -->
      <el-button
        v-if="pageTopVisible"
        class="scrollTop link"
        link
        @click="scrollTopA006"
      >↑ページトップへ</el-button>
    </el-row>
  </div>
</template>

<script>

export default {
  props: {
    // 表示方法
    // 'search' : 検索結果の表示
    // 'list' : メニュー表示
    // 'detail' : よくある質問表示
    displayMode: { required: true, type: String },
    form: { required: true, type: Object },
    searchCounter: { required: true, type: Number }
  },
  emits: ['faq-click', 'back-click'],
  data() {
    return {
      currentPage: 1,
      pageSize: 20,
      pagerCount: 5,
      isLastPage: false,
      questionHeight: 0
    }
  },
  computed: {
    searchWord() {
      return this.form.searchKeyWordList.join('､')
    },
    searchResultList() {
      const index = this.pageSize * (this.currentPage - 1)
      return this.form.searchResultList.slice(index, index + this.pageSize)
    },
    pageTopVisible() {
      switch (this.displayMode) {
        case 'search':
          return this.form.searchResultListCount > 0
        case 'list':
          return this.form.faqList.length > 0
        case 'detail':
          return this.form.heading && this.form.contentText
        default:
          return false
      }
    },
    searchCustomStyle() {
      return {
        '--question-height': `${this.questionHeight ?? 0}px`
      }
    }
  },
  watch: {
    searchCounter() {
      this.currentPage = 1
    },
    displayMode(newValue) {
      if (newValue === 'detail') {
        this.$nextTick(() => {
          const doc = document.getElementsByClassName('question')
          if (doc && doc[0]) {
            if (doc[0].offsetHeight > 20) {
              this.questionHeight = doc[0].offsetHeight - 20
            }
          }
        })
      }
    }
  },
  methods: {
    // 検索結果選択で詳細を表示
    listClick(id) {
      this.$emit('faq-click', id)
    },
    // A006: ページトップ
    scrollTopA006() {
      const doc1 = document.getElementsByClassName('faq-scrollable')
      const doc2 = document.getElementsByClassName('faq-scrollable_detail')
      if (doc1 && typeof doc1 === 'object' && doc1[0]) {
        doc1[0].scrollTop = 0
      } else if (doc2 && typeof doc2 === 'object' && doc2[0]) {
        doc2[0].scrollTop = 0
      }
    },
    // A007: 戻る
    handleBackClickA007() {
      this.$emit('back-click')
    },
    showSearchWord(q) {
      let str = q
      for (const keyword of this.form.searchKeyWordList) {
        str = str.replaceAll(keyword, '<strong>' + keyword + '</strong>')
      }
      return str
    },
    setPage(page) {
      const pageNum = Math.ceil(this.form.searchResultListCount / this.pageSize)
      this.isLastPage = page === pageNum
      this.scrollTopA006()
    }
  }
}
</script>

<style lang="scss" scoped>
/* 項目の個別設定 */
.faq-display__wrapper {
  display: flex;
  flex-direction: column;
  padding-left: 1rem;
  vertical-align: top;
  margin-top: 0.5rem;
  border-top: 3px solid #305496;
  &:not(:has(.search)), &:has(.is-last-page) {
    border-bottom: 3px solid #305496;
  }

  .faq-scrollable {
    overflow-y: scroll;
    max-height: calc(100lvh - 208px);
    &.answer {
      max-height: calc(100lvh - 282px - var(--question-height));
    }
  }

  .faq-scrollable_detail {
    overflow-y: scroll;
    flex-grow: 1;
  }

  .faq-display {
    flex: 1;
    padding-top: 0.5rem;
  }

  .footer {
    display: flex;
    bottom: 0;
    height: 35px;
    .pagination {
      width: 100%;
      justify-content: center;
      margin-bottom: -20px;
    }
    .scrollTop {
      position: absolute;
      right: 40px;
      bottom: 0;
    }
  }

  .footer_detail {
    bottom: 0;
    height: 55px;
    position: absolute;
    right: 40px;
    width: auto;
  }

  .list-row {
    display: grid;
    padding-right: 2rem;
  }

  .list-row_detail {
    display: flex;
    padding-right: 2rem;
    flex-direction: column;
    max-height: calc(-265px + 100lvh);
  }

  .question {
    display: inline-flex;
    white-space: pre-wrap;
    word-break: break-word;
    word-wrap: break-word;
    text-decoration: none;
    background-color: transparent;
    &.link {
      color: #007bff;
      text-decoration: underline;
      &:hover {
        color: #0056b3;
        text-decoration: underline;
      }
    }
  }

  .answer {
    display: inline-flex;
    white-space: pre-wrap;
    word-break: break-word;
    word-wrap: break-word;
    &.border {
      padding-top: 0.5rem;
      border-top: 1px dotted #cccccc;
    }
  }

  .search {
    padding-top: 0.5rem;
    padding-bottom: 0.5rem;
  }

  .faq-item {
    padding-top: 0.5rem;
    &:nth-child(n+2) {
      border-top: 1px dotted #cccccc;
    }
  }

  .back-button {
    display: flex;
    width: 100%;
    justify-content: flex-end;
    padding-right: 2rem;
    padding-bottom: 1rem;
  }

  .q-icon {
    height: 20px;
    width: 20px;
    min-width: 20px;
    font-weight: bold;
    color: #ffffff;
    background-color: #305496;
    border-radius: 50%;
    display: inline-block;
    text-align: center;
    vertical-align: top;
    padding-top: 2px;
  }

  .a-icon {
    height: 20px;
    width: 20px;
    min-width: 20px;
    font-weight: bold;
    color: #305496;
    background-color: #ffffff;
    border: 2px solid #305496;
    border-radius: 50%;
    display: inline-block;
    text-align: center;
    vertical-align: top;
    padding-top: 2px;
  }

  .text {
    display: inline-block;
    white-space: normal;
    &.bold {
      font-weight: 700;
    }
  }
}
.mt-1rem {
  margin-top: 1rem;
}
.mb-1rem {
  margin-bottom: 1rem;
}
.link {
  cursor: pointer;
}
</style>
