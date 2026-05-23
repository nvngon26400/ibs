function getDynamicTableHeight() {
  // const startTime = performance.now()
  // 高さのしきい値（デフォルト170、データ2件表示相当）
  const threshold = 170
  // 右側メインエリア全体の高さを取得 (AppMain.vue height: calc(100vh - 4rem))
  const targetRoot = document.getElementById('app-main')
  const targetRootHeight = getElementHeight(targetRoot)

  // 右側第一階層タブヘッダーの高さを取得（存在しない場合は0）
  let firstHeaderHeight = 0
  const targetFirstHeader = document.querySelector('.el-tabs__header.is-top')
  if (targetFirstHeader) {
    firstHeaderHeight = getElementHeight(targetFirstHeader)
  }
  // 右側第二階層タブヘッダーの高さを取得（表示されているタブ、存在しない場合は0）
  let secondHeaderHeight = 0
  const visibleDiv = document.querySelector('div[aria-hidden="false"]')
  if (visibleDiv) {
    const ulElement = visibleDiv.querySelector('ul')
    if (ulElement) {
      secondHeaderHeight = getElementHeight(ulElement)
    }
  }
  // タイトルの高さを取得
  let titleHeight = 0
  const targetTitle = document.querySelector('.caption-container')
  if (targetTitle) {
    titleHeight = getElementHeight(targetTitle, { includeMargin: true })
  }
  // 複数のel-cardのうち、最後のel-cardを除く他のel-cardの合計高さを取得（el-card数が2未満の場合は0）
  const elCardTotalHeightExcludingLast = getElCardTotalHeightExcludingLast()
  // el-tabs__content の padding + border の高さを取得
  const targetElTablContent = document.querySelector('.el-tabs__content')
  const elTablContentHeight = getElementHeight(targetElTablContent, { onlyPaddingBorder: true })

  const allElCards = document.querySelectorAll('.el-card')
  const count = allElCards.length
  let lastCardElement
  if (count > 0) {
    lastCardElement = allElCards[count - 1]
  } else {
    return 0
  }
  // el-cardの直接の子要素(el-card__body想定)のパディングとボーダーの高さを取得
  const lastCardElementMarginBorderHeight = getElementHeight(lastCardElement, { onlyMarginBorder: true })
  const lastCardBody = lastCardElement.firstElementChild
  const lastCardBodyPaddingBorderHeight = getElementHeight(lastCardBody, { onlyPaddingBorder: true })
  // el-card内の複数のel-rowのうち、最後のel-rowを除く他のel-rowの合計高さを取得（el-row数が2未満の場合は0）
  const elRowTotalHeightExcludingLastHeight = getElRowTotalHeightExcludingLast(lastCardBody, { includeMargin: true })
  // スクロールバーの高さを取得
  const scrollbarHeight = getScrollbarHeight()

  // 残りの高さを計算
  let lastHeight = targetRootHeight - firstHeaderHeight - secondHeaderHeight - titleHeight - elCardTotalHeightExcludingLast - elTablContentHeight - lastCardElementMarginBorderHeight - lastCardBodyPaddingBorderHeight - elRowTotalHeightExcludingLastHeight - scrollbarHeight
  // console.log('targetRootHeight = ' + targetRootHeight)
  // console.log('firstHeaderHeight = ' + firstHeaderHeight)
  // console.log('secondHeaderHeight = ' + secondHeaderHeight)
  // console.log('titleHeight = ' + titleHeight)
  // console.log('elCardTotalHeightExcludingLast = ' + elCardTotalHeightExcludingLast)
  // console.log('lastCardElementMarginBorderHeight = ' + lastCardElementMarginBorderHeight)
  // console.log('lastCardBodyPaddingBorderHeight = ' + lastCardBodyPaddingBorderHeight)
  // console.log('elRowTotalHeightExcludingLastHeight = ' + elRowTotalHeightExcludingLastHeight)
  // console.log('scrollbarHeight = ' + scrollbarHeight)
  // 計算された高さがしきい値を下回らないようにする
  lastHeight = Math.max(lastHeight, threshold)

  // console.log('last height = ' + lastHeight)
  // const endTime = performance.now()
  // const duration = endTime - startTime
  // console.log(`cost time: ${duration.toFixed(3)} ms`)
  return lastHeight
}

// 複数のel-cardのうち、最後のel-cardを除く他のel-cardの合計高さを取得（el-card数が2未満の場合は0）
function getElCardTotalHeightExcludingLast() {
  const allElCards = document.querySelectorAll('.el-card')
  if (!allElCards) {
    return 0
  }
  const cardCount = allElCards.length
  if (cardCount <= 1) {
    return 0
  }

  // 最初のel-cardの親ノードを取得
  const firstCardParent = allElCards[0].parentElement

  let totalHeightExcludingLast = 0
  for (let i = 0; i < cardCount - 1; i++) {
    const currentCard = allElCards[i]
    const nextCard = allElCards[i + 1]
    const nextCardParent = nextCard.parentElement
    if (nextCardParent !== firstCardParent) {
      const currentHeight = getElementHeight(currentCard, { includeMargin: true })
      totalHeightExcludingLast += currentHeight
    } else {
      const currentHeight = getElementHeight(currentCard, { excludeMarginBottom: true })
      totalHeightExcludingLast += currentHeight
    }
  }
  return totalHeightExcludingLast
}

// el-card内の複数のel-rowのうち、最後のel-rowを除く他のel-rowの合計高さを取得（el-row数が2未満の場合は0）
function getElRowTotalHeightExcludingLast(parentElCard) {
  if (!parentElCard || typeof parentElCard.querySelectorAll !== 'function') {
    return 0
  }
  const allElRows = parentElCard.querySelectorAll('.el-row')
  const rowCount = allElRows.length
  if (rowCount <= 1) {
    return 0
  }
  let totalHeightExcludingLast = 0
  for (let i = 0; i < rowCount - 1; i++) {
    const currentRow = allElRows[i]
    const currentHeight = getElementHeight(currentRow, { includeMargin: true })
    totalHeightExcludingLast += currentHeight
  }
  return totalHeightExcludingLast
}

// 要素の高さを取得する汎用関数
function getElementHeight(element, options = {}) {
  if (!element || typeof element.offsetHeight === 'undefined') {
    return 0
  }

  const {
    includeMargin = false,
    onlyMarginBorder = false,
    excludeMarginBottom = false,
    onlyPaddingBorder = false
  } = options

  let height = 0
  const computedStyle = window.getComputedStyle(element)

  // オプションに応じて高さを計算
  if (onlyPaddingBorder) {
    const paddingTop = parseFloat(computedStyle.paddingTop) || 0
    const paddingBottom = parseFloat(computedStyle.paddingBottom) || 0
    const borderTop = parseFloat(computedStyle.borderTopWidth) || 0
    const borderBottom = parseFloat(computedStyle.borderBottomWidth) || 0
    height = paddingTop + paddingBottom + borderTop + borderBottom
  } else if (onlyMarginBorder) {
    const marginTop = parseFloat(computedStyle.marginTop) || 0
    const marginBottom = parseFloat(computedStyle.marginBottom) || 0
    const borderTop = parseFloat(computedStyle.borderTopWidth) || 0
    const borderBottom = parseFloat(computedStyle.borderBottomWidth) || 0
    height = marginTop + marginBottom + borderTop + borderBottom
  } else if (excludeMarginBottom) {
    height = element.offsetHeight
    const marginTop = parseFloat(computedStyle.marginTop) || 0
    height += marginTop
  } else {
    height = element.offsetHeight

    if (includeMargin) {
      const marginTop = parseFloat(computedStyle.marginTop) || 0
      const marginBottom = parseFloat(computedStyle.marginBottom) || 0
      height += marginTop + marginBottom
    }
  }

  // 計算結果を返す
  return height
}

function getScrollbarHeight() {
  // 外側のコンテナdivを作成
  const outer = document.createElement('div')
  outer.style.visibility = 'hidden' // 不可視にする
  outer.style.overflow = 'scroll' // スクロールバーを表示させる
  outer.style.msOverflowStyle = 'scrollbar' // IE 用：スクロールバーを表示
  outer.style.position = 'absolute' // ドキュメントフローから切り離し、レイアウトへの影響を避ける
  outer.style.top = '-9999px' // 画面外に移動
  outer.style.width = '100px' // 明示的なサイズを指定
  outer.style.height = '100px' // 明示的なサイズを指定
  document.body.appendChild(outer)
  // スクロールバーのサイズを計算
  const scrollbarHeight = outer.offsetHeight - outer.clientHeight
  // 一時的な要素をDOMから削除
  document.body.removeChild(outer)
  return scrollbarHeight
}

export default {
  getDynamicTableHeight
}
