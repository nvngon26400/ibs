import store from '@/store'

/**
 * 項目の認可制御を行う。
 *
 */
export const controlAuthorization = function() {
  const accControls = store.getters.userAccount.accControls
  const pageInfo = store.getters.pageInfo
  const menuId = pageInfo ? pageInfo.menuId : ''
  if (!accControls) {
    return
  }

  for (let i = 0; i < accControls.length; i++) {
    const control = accControls[i]
    if (control.itemId === this.id && (control.menuId === menuId || control.menuId === 'ALL')) {
      switch (control.accControl) {
        case 2:
        // 2:DISABLED(編集不可)
          this.ctrlDisabled = true
          break
        case 3:
        // 3:HIDE(非表示)
          this.ctrlVisible = false
          break
        case 4:
        // 4:READ_ONLY(読取専用)
          this.ctrlReadonly = true
          break
        default:
        // 1:ENABLE(編集可), その他の場合、何もしない。
          break
      }
      break
    }
  }
}

/**
 * 上段メニューの認可制御を行う。
 * @param {Array} menuList メニューリスト
 * @returns アクセス可能なメニューリスト
 */
export const getAccessibleMenuList = function(menuList) {
  const accessibleMenuList = []

  const userAccount = store.getters.userAccount
  if (!userAccount) {
    return accessibleMenuList
  }
  const accessibleViewList = userAccount.userPermissionInfo.accessibleViewList
  if (!accessibleViewList) {
    return accessibleMenuList
  }

  // 全体のメニューから、認可情報をもとにアクセス可能なメニューのみに絞り込む。
  menuList.forEach(m => {
    // m は､`@/router/index.js` の `constantRoutes` の参照が渡される｡
    // そのまま使用すると `constantRoutes` が破壊されるため､オブジェクトのコピーを作って以降の処理を行う｡
    const menu = Object.assign({}, m)
    // 1階層目
    if (accessibleViewList.includes(menu.menuId)) {
      const accessibleMenu = menu
      const accessibleMenuItems = []
      if (!menu.menuItems) {
        accessibleMenuList.push(accessibleMenu)
        return
      }
      // 2階層目
      menu.menuItems.forEach(i => {
        // 2階層目も同様に `constantRoutes` が破壊されないように､オブジェクトのコピーを作って以降の処理を行う｡
        // structuredClone() などの deep copy が使えれば良いのだが function を含んでいるため使用できない｡
        const item = Object.assign({}, i)
        if (accessibleViewList.includes(item.menuId)) {
          const accessibleMenuItem = item
          if (!item.children) {
            accessibleMenuItems.push(accessibleMenuItem)
            return
          // 3階層目もある場合
          } else {
            const accessibleChildren = []
            item.children.forEach(c => {
              // 3階層目も同様に `constantRoutes` が破壊されないように､オブジェクトのコピーを作って以降の処理を行う｡
              // structuredClone() などの deep copy が使えれば良いのだが function を含んでいるため使用できない｡
              const child = Object.assign({}, c)
              if (accessibleViewList.includes(child.menuId)) {
                accessibleChildren.push(child)
                return
              }
            })
            if (accessibleChildren.length > 0) {
              accessibleMenuItem.children = [...accessibleChildren]
              accessibleMenuItems.push(accessibleMenuItem)
            }
          }
        }
      })
      if (accessibleMenuItems.length > 0) {
        accessibleMenu.menuItems = [...accessibleMenuItems]
        accessibleMenuList.push(accessibleMenu)
      }
    }
  })
  return accessibleMenuList
}

/**
 * 左サイドメニューの認可制御を行う。
 * @param {Array} menuList メニューリスト(Router定義)
 * @returns アクセス可能なメニューリスト
 */
export const getAccessibleMenuListForRouter = function(menuList) {
  const accessibleMenuList = []

  const userAccount = store.getters.userAccount
  if (!userAccount) {
    return accessibleMenuList
  }
  const accessibleViewList = userAccount.userPermissionInfo.accessibleViewList
  if (!accessibleViewList) {
    return accessibleMenuList
  }

  // 初期画面は常に認可する
  accessibleViewList.push('INIT00')
  // 全体のメニューから、認可情報をもとにアクセス可能なメニューのみに絞り込む。
  menuList.forEach(m => {
    // m は､`@/router/index.js` の `constantRoutes` の参照が渡される｡
    // そのまま使用すると `constantRoutes` が破壊されるため､オブジェクトのコピーを作って以降の処理を行う｡
    const menu = Object.assign({}, m)
    // 1階層目
    if (!menu.hidden && menu.meta && accessibleViewList.includes(menu.meta.menuId)) {
      const accessibleMenu = menu
      const accessibleMenuItems = []
      const accessibleChildren = []
      if (!menu.children) {
        accessibleMenuList.push(accessibleMenu)
        return
      }
      // 2階層目
      menu.children.forEach(i => {
        // 2階層目も同様に `constantRoutes` が破壊されないように､オブジェクトのコピーを作って以降の処理を行う｡
        // structuredClone() などの deep copy が使えれば良いのだが function を含んでいるため使用できない｡
        const item = Object.assign({}, i)
        if (item.meta && accessibleViewList.includes(item.meta.menuId)) {
          const accessibleMenuItem = item
          if (!item.children) {
            accessibleMenuItems.push(accessibleMenuItem)
            return
          // 3階層目もある場合
          } else {
            item.children.forEach(c => {
              // 3階層目も同様に `constantRoutes` が破壊されないように､オブジェクトのコピーを作って以降の処理を行う｡
              // structuredClone() などの deep copy が使えれば良いのだが function を含んでいるため使用できない｡
              const child = Object.assign({}, c)
              if (child.meta && accessibleViewList.includes(child.meta.menuId)) {
                accessibleChildren.push(child)
                return
              }
            })
            if (accessibleChildren.length > 0) {
              accessibleMenuItem.children = [...accessibleChildren]
              accessibleMenuItems.push(accessibleMenuItem)
              accessibleChildren.length = 0
            }
          }
        }
      })
      if (accessibleMenuItems.length > 0) {
        accessibleMenu.children = [...accessibleMenuItems]
        accessibleMenuList.push(accessibleMenu)
        accessibleMenuItems.length = 0
      }
    }
  })
  return accessibleMenuList
}

/**
 * メニューIDがアクセス可能かを判定する。
 * @param {Array} accessibleViewList
 * @param {String} menuId
 * @returns アクセス可能な場合true, アクセス不可の場合false
 */
export const isAccessible = function(menuId) {
  const userAccount = store.getters.userAccount
  if (!userAccount) {
    return false
  }
  const accessibleViewList = userAccount.userPermissionInfo.accessibleViewList
  if (!accessibleViewList) {
    return false
  }
  return accessibleViewList.includes(menuId) || menuId === 'INIT00'
}
