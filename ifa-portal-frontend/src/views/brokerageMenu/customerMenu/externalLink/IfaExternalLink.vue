<template>
  <!-- 画面名：外部リンク -->
  <div style="display: flex">
    <!-- 分類エリア -->
    <div style="width: 25%;">
      <el-card class="content-card">
        <div>
          <el-row
            v-for="(subtitle, id) in form.categoryList"
            :key="id"
          >
            <el-link
              class="link"
              @click="handleClickAnchor(subtitle)"
            >
              {{ subtitle }}
            </el-link>
          </el-row>
        </div>
      </el-card>
    </div>
    <!-- 外部リンクエリア -->
    <el-card
      style="width: 65%;"
      class="content-card"
    >
      <div
        ref="contentArea"
        style="width: 100%; overflow-y: scroll; height: 53.5vh;"
      >
        <div>
          <screen-title
            :text="form.titleList.title"
            style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
          ></screen-title>
        </div>
        <div>
          <!-- 国内株式 -->
          <el-card
            name="国内株式"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="国内株式"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  v-if="form.tenGunId"
                  ref="ifalink1"
                  :url-id="urlLink(1)"
                  :pattern-id="1"
                  disp-name="注文照会"
                  link-icon-image="externalLink"
                  http-method="POST"
                  :param-object="{
                    ccs_op_id: form.ccsOpId,
                    _TENGUN_ID: form.tenGunId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  v-if="form.tenGunId"
                  ref="ifalink58"
                  :url-id="urlLink(58)"
                  :pattern-id="1"
                  disp-name="注文履歴"
                  link-icon-image="externalLink"
                  http-method="POST"
                  :param-object="{
                    ccs_op_id: form.ccsOpId,
                    _TENGUN_ID: form.tenGunId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D' || form.customerAttribute == 'J')"
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  v-if="form.tenGunId"
                  ref="ifalink2"
                  :url-id="urlLink(2)"
                  :pattern-id="1"
                  disp-name="国内株式積立設定一覧"
                  link-icon-image="externalLink"
                  http-method="POST"
                  :param-object="{
                    ccs_op_id: form.ccsOpId,
                    _TENGUN_ID: form.tenGunId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink3"
                  :url-id="109"
                  :pattern-id="1"
                  disp-name="テーマ投資パフォーマンス"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    OperatorID: form.ccsOpId,
                    UserKind: '1',
                    CcsAuth: '5555555555',
                    Param1: 'account',
                    Param2: 'themeInvest',
                    Param3: 'orderHistory'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
            </el-row>
          </el-card>
          <!-- 投資信託 -->
          <el-card
            name="投資信託"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="投資信託"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(110,'1','POST')"
                  > {{ "投信  口座サマリー" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(110,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink5"
                  :url-id="111"
                  :pattern-id="1"
                  disp-name="投信トータルリターン　サマリー"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    OperatorID: form.ccsOpId,
                    UserKind: '1',
                    CcsAuth: '5555555555',
                    Param1: 'account',
                    Param2: 'totalreturn',
                    Param3: 'fund',
                    Param4: 'summary'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink6"
                  :url-id="112"
                  :pattern-id="1"
                  disp-name="投信トータルリターン　銘柄一覧"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    OperatorID: form.ccsOpId,
                    UserKind: '1',
                    CcsAuth: '5555555555',
                    Param1: 'account',
                    Param2: 'totalreturn',
                    Param3: 'fund',
                    Param4: 'fundlist'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink7"
                  :url-id="113"
                  :pattern-id="1"
                  disp-name="外貨建MMF注文照会"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    opid: form.ccsOpId,
                    account: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    page: 'fund_foreign-mmf_refer',
                    type: '1'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink8"
                  :url-id="114"
                  :pattern-id="1"
                  disp-name="外貨建MMF取引（買付）"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    opid: form.ccsOpId,
                    account: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    page: 'fund_foreign-mmf_buyable',
                    type: '1'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink9"
                  :url-id="115"
                  :pattern-id="1"
                  disp-name="外貨建MMF取引（売却）"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    opid: form.ccsOpId,
                    account: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    page: 'fund_foreign-mmf_sellable',
                    type: '1'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink56"
                  :url-id="175"
                  :pattern-id="1"
                  disp-name="投信分配金自動振込サービス"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    TradingPass: '00',
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    UserName: form.ccsOpId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
            </el-row>
          </el-card>
          <!-- 債券 -->
          <el-card
            name="債券"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="債券"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(116,'1','POST')"
                  > {{ "円貨建債券　新発債(買付)" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(116,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(117,'1','POST')"
                  > {{ "円貨建債券　国債(買付)" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(117,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(118,'1','POST')"
                  > {{ "円貨建債券　既発債(買付)" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(118,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(119,'1','POST')"
                  > {{ "外貨建債券　新発債(買付)" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(119,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(120,'1','POST')"
                  > {{ "外貨建債券　既発債(買付)" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(120,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(121,'1','POST')"
                  > {{ "注文履歴(取消)" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(121,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(122,'1','POST')"
                  > {{ "取引履歴" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(122,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(123,'1','POST')"
                  > {{ "保有銘柄一覧(売却)" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(123,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(124,'1','POST')"
                  > {{ "利金・償還金シミュレーション" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(124,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(125,'1','POST')"
                  > {{ "利金・償還受取方法指定" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(125,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(126,'1','POST')"
                  > {{ "償還乗換優遇" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(126,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(127,'1','POST')"
                  > {{ "お気に入り" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(127,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
            </el-row>
          </el-card>
          <!-- ST(セキュリティ・トークン) -->
          <el-card
            v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
              || form.customerAttribute == 'D')"
            name="ST(セキュリティ・トークン)"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="ST(セキュリティ・トークン)"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink22"
                  :url-id="128"
                  :pattern-id="1"
                  disp-name="残高照会"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    OperatorID: form.ccsOpId,
                    UserKind: '1',
                    CcsAuth: '5555555555',
                    Param1: 'account',
                    Param2: 'japan',
                    Param3: 'st',
                    Param4: 'balance'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink23"
                  :url-id="129"
                  :pattern-id="1"
                  disp-name="注文照会"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    OperatorID: form.ccsOpId,
                    UserKind: '1',
                    CcsAuth: '5555555555',
                    Param1: 'trade',
                    Param2: 'st',
                    Param3: 'oldIssueSt',
                    Param4: 'orderInquiryCancel'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink24"
                  :url-id="130"
                  :pattern-id="1"
                  disp-name="注文履歴"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    OperatorID: form.ccsOpId,
                    UserKind: '1',
                    CcsAuth: '5555555555',
                    Param1: 'trade',
                    Param2: 'st',
                    Param3: 'orderHistorySearch'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
            </el-row>
          </el-card>
          <!-- SBIラップ -->
          <el-card
            v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
              || form.customerAttribute == 'D')"
            name="SBIラップ"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="SBIラップ"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink25"
                  :url-id="131"
                  :pattern-id="1"
                  disp-name="SBIラップ"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    account: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    opid: form.ccsOpId,
                    hash: '',
                    type: '3',
                    page: 'account-wrap'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
            </el-row>
          </el-card>
          <!-- 口座管理 -->
          <el-card
            name="口座管理"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="口座管理"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink26"
                  :url-id="132"
                  :pattern-id="1"
                  disp-name="口座(NISA)"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    hash: '',
                    type: '3',
                    page: 'account-nisa',
                    opid: form.ccsOpId,
                    account: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0')
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink27"
                  :url-id="133"
                  :pattern-id="1"
                  disp-name="口座(旧NISA)"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    OperatorID: form.ccsOpId,
                    UserKind: '1',
                    CcsAuth: '5555555555',
                    Param1: 'account',
                    Param2: 'accountnisa',
                    Param3: 'incomeyear'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink59"
                  :url-id="12"
                  :pattern-id="1"
                  disp-name="My資産"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{}"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  v-if="form.tenGunId"
                  ref="ifalink28"
                  :url-id="urlLink(28)"
                  :pattern-id="1"
                  disp-name="約定履歴"
                  link-icon-image="externalLink"
                  http-method="POST"
                  :param-object="{
                    ccs_op_id: form.ccsOpId,
                    _TENGUN_ID: form.tenGunId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  v-if="form.tenGunId"
                  ref="ifalink29"
                  :url-id="urlLink(29)"
                  :pattern-id="1"
                  disp-name="信用決済明細"
                  link-icon-image="externalLink"
                  http-method="POST"
                  :param-object="{
                    ccs_op_id: form.ccsOpId,
                    _TENGUN_ID: form.tenGunId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink30"
                  :url-id="142"
                  :pattern-id="1"
                  disp-name="約定履歴（外貨建）"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    opid: form.ccsOpId,
                    account: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    page: 'account_history_foreign_trade',
                    type: '1'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  v-if="form.tenGunId"
                  ref="ifalink31"
                  :url-id="urlLink(31)"
                  :pattern-id="1"
                  disp-name="カバードワラント損益"
                  link-icon-image="externalLink"
                  http-method="POST"
                  :param-object="{
                    ccs_op_id: form.ccsOpId,
                    _TENGUN_ID: form.tenGunId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  v-if="form.tenGunId"
                  ref="ifalink32"
                  :url-id="urlLink(32)"
                  :pattern-id="1"
                  disp-name="譲渡益税明細"
                  link-icon-image="externalLink"
                  http-method="POST"
                  :param-object="{
                    ccs_op_id: form.ccsOpId,
                    _TENGUN_ID: form.tenGunId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink33"
                  :url-id="151"
                  :pattern-id="1"
                  disp-name="仕組債　保有銘柄一覧"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    UserName: form.ccsOpId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink36"
                  :url-id="154"
                  :pattern-id="1"
                  disp-name="配当金受領方法"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    TradingPass: '00',
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    UserName: form.ccsOpId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
                  || form.customerAttribute == 'D')"
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink37"
                  :url-id="155"
                  :pattern-id="1"
                  disp-name="重要書類郵送サービス"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    TradingPass: '00',
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    UserName: form.ccsOpId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(156,'1','POST')"
                  > {{ "銀行引落サービス" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(156,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink39"
                  :url-id="157"
                  :pattern-id="1"
                  disp-name="振込先指定金融機関"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    agent_id: form.ccsOpId,
                    customer_id: customerInfo.customerCode,
                    branch_no: customerInfo.butenCode,
                    account_no: customerInfo.accountNumber.padStart(7, '0'),
                    srcid: '26809001'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink57"
                  :url-id="2"
                  :pattern-id="1"
                  disp-name="口座開設申込状況"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    agent_id: form.ccsOpId,
                    customer_id: customerInfo.customerCode,
                    branch_no: customerInfo.butenCode,
                    account_no: customerInfo.accountNumber.padStart(7, '0'),
                    srcid: '10000001HELP'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
            </el-row>
          </el-card>
          <!-- 入出金・振替 -->
          <el-card
            name="入出金・振替"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="入出金・振替"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(158,'1','POST')"
                  > {{ "入出金明細（円建）" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(158,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(159,'1','POST')"
                  > {{ "入出金明細（外貨建）" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(159,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(160,'1','POST')"
                  > {{ "入出金・振替・操作履歴（外貨）" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(160,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B')"
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink43"
                  :url-id="161"
                  :pattern-id="1"
                  disp-name="為替取引"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    opid: form.ccsOpId,
                    account: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    page: 'exchange_order',
                    type: '1'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(205,'1','POST')"
                  > {{ "入出金・振替・操作履歴（円貨）" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(205,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
            </el-row>
          </el-card>
          <!-- 新生銀行関連サービス(社員・新生銀行用) -->
          <el-card
            v-if="userAccount.medUsers.privId === '1' || userAccount.medUsers.privId === '2' || customerInfo.brokerCode == '1133'"
            name="新生銀行関連サービス(社員・新生銀行用)"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="新生銀行関連サービス(社員・新生銀行用)"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink44"
                  :url-id="162"
                  :pattern-id="1"
                  disp-name="リアルタイム入金"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    UserName: form.ccsOpId,
                    TradingPass: '00'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink45"
                  :url-id="163"
                  :pattern-id="1"
                  disp-name="リアルタイム出金"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    branch_no: customerInfo.butenCode,
                    account_no: customerInfo.accountNumber.padStart(7, '0'),
                    agent_id: form.ccsOpId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink46"
                  :url-id="164"
                  :pattern-id="1"
                  disp-name="口座振替契約解除"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    branch_no: customerInfo.butenCode,
                    account_no: customerInfo.accountNumber.padStart(7, '0'),
                    agent_id: form.ccsOpId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink47"
                  :url-id="165"
                  :pattern-id="1"
                  disp-name="外貨入金"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    branch_no: customerInfo.butenCode,
                    account_no: customerInfo.accountNumber.padStart(7, '0'),
                    agent_id: form.ccsOpId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink48"
                  :url-id="166"
                  :pattern-id="1"
                  disp-name="外貨出金"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    branch_no: customerInfo.butenCode,
                    account_no: customerInfo.accountNumber.padStart(7, '0'),
                    agent_id: form.ccsOpId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink49"
                  :url-id="167"
                  :pattern-id="1"
                  disp-name="米ドル定期自動入金設定履歴"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    branch_no: customerInfo.butenCode,
                    account_no: customerInfo.accountNumber.padStart(7, '0'),
                    agent_id: form.ccsOpId
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
            </el-row>
          </el-card>
          <!-- 外貨建商品取引サイト -->
          <el-card
            v-if="!(form.customerAttribute == 'A' || form.customerAttribute == 'B'
              || form.customerAttribute == 'D')"
            name="外貨建商品取引サイト"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="外貨建商品取引サイト"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
            </el-row>
          </el-card>
          <!-- その他 -->
          <el-card
            name="その他"
            class="content-card"
          >
            <el-row>
              <el-col>
                <screen-title
                  text="その他"
                  style="background-color: #eee; margin-left: 0px; margin-bottom:5px;"
                ></screen-title>
              </el-col>
            </el-row>
            <el-row class="line-space">
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink61"
                  :url-id="211"
                  :pattern-id="1"
                  disp-name="ログイン履歴"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    agent_id: form.ccsOpId,
                    customer_id: customerInfo.customerCode,
                    branch_no: customerInfo.butenCode,
                    account_no: customerInfo.accountNumber.padStart(7, '0'),
                    srcid: '10000001HELP'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink63"
                  :url-id="213"
                  :pattern-id="1"
                  disp-name="お取引関連･口座情報"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    UserName: form.ccsOpId,
                    Param1: 'trading'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink64"
                  :url-id="214"
                  :pattern-id="1"
                  disp-name="各種サービス"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    UserName: form.ccsOpId,
                    Param1: 'service'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink66"
                  :url-id="216"
                  :pattern-id="1"
                  disp-name="ポイント･外部ID連携"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    OperatorID: form.ccsOpId,
                    UserKind: '1',
                    CcsAuth: '5555555555',
                    Param1: 'account',
                    Param2: 'registinfo',
                    Param3: 'point',
                    Param4: 'top'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaExternalLinkGetNewMainSiteA003(217,'1','POST')"
                  > {{ "サービス設定" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaExternalLinkGetNewMainSiteA003(217,'1','POST')"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </el-col>
              <el-col
                :span="8"
                style="text-align: left;"
              >
                <ifa-link
                  ref="ifalink68"
                  :url-id="218"
                  :pattern-id="1"
                  disp-name="クレジット管理"
                  link-icon-image="externalLink"
                  http-method="GET"
                  :param-object="{
                    AccountID: customerInfo.butenCode + '-' + customerInfo.accountNumber.padStart(7, '0'),
                    OperatorID: form.ccsOpId,
                    UserKind: '1',
                    CcsAuth: '5555555555',
                    Param1: 'trade',
                    Param2: 'fund',
                    Param3: 'reserve',
                    Param4: 'cardSummary'
                  }"
                  :manual-init="true"
                ></ifa-link>
              </el-col>
            </el-row>
          </el-card>
        </div>
      </div>
    </el-card>
    <ifa-requester
      id="IfaExternalLinkInitializeA001"
      action-id="SUB0202_08-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaExternalLinkGetNewMainSiteA003"
      action-id="SUB0202_08-01#A003"
      action-type="requestAction"
      :request-model="ifaLinkRequestModel"
      @response-handler="responseHandlerGetNewMainSiteA003($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaExternalLinkModel } from './js/IfaExternalLinkModel'
import { IfaLinkRequestModel } from './js/IfaLinkRequestModel'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  components: {
    screenTitle
  },
  data() {
    return {
      form: new IfaExternalLinkModel(),
      request: '',
      requestProps: {},
      ifaLinkRequestModel: {}
    }
  },

  computed: {
    userAccount() {
      return this.$store.getters.userAccount
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    target() {
      return 'link' + (this.requestProps.urlId === 0 ? '' : this.requestProps.urlId.toString())
    }
  },
  methods: {
    onShow() {
      // 初期表示時のみA001初期化処理を実行
      this.$nextTick(() => {
        document.getElementById('IfaExternalLinkInitializeA001').click()
      })
    },
    responseHandlerInitializeA001(response) {
      Object.assign(this.form, response?.dataList?.[0])
      this.$nextTick(() => {
        for (let i = 1; i <= 68; i++) {
          if (this.$refs[`ifalink${i}`]) {
            this.$refs[`ifalink${i}`].trigger()
          }
        }
      })
    },
    ifaExternalLinkGetNewMainSiteA003(urlId, patternId, httpMethod) {
      const newData = {
        urlId: urlId,
        patternId: patternId,
        httpMethod: httpMethod
      }
      this.requestProps = newData
      this.ifaLinkRequestModel = new IfaLinkRequestModel(this.requestProps)
      document.getElementById('IfaExternalLinkGetNewMainSiteA003').click()
    },
    responseHandlerGetNewMainSiteA003(response) {
      Object.assign(this.form, response?.dataList?.[0])
      if (this.form.postRequest) {
        this.request = this.form.postRequest
      }
      this.openWindow()
    },
    urlLink(linkId) {
      switch (this.form.tenGunId) {
        case 'Z01':
        case 'Z02':
          switch (linkId) {
            case 1:
              return '101'
            case 2:
              return '105'
            case 28:
              return '134'
            case 29:
              return '138'
            case 31:
              return '143'
            case 32:
              return '147'
            case 58:
              return '206'
          }
          break
        case 'Z03':
        case 'Z04':
          switch (linkId) {
            case 1:
              return '102'
            case 2:
              return '106'
            case 28:
              return '135'
            case 29:
              return '139'
            case 31:
              return '144'
            case 32:
              return '148'
            case 58:
              return '207'
          }
          break
        case 'Z05':
        case 'Z06':
          switch (linkId) {
            case 1:
              return '103'
            case 2:
              return '107'
            case 28:
              return '136'
            case 29:
              return '140'
            case 31:
              return '145'
            case 32:
              return '149'
            case 58:
              return '208'
          }
          break
        case 'Z07':
        case 'Z08':
          switch (linkId) {
            case 1:
              return '104'
            case 2:
              return '108'
            case 28:
              return '137'
            case 29:
              return '141'
            case 31:
              return '146'
            case 32:
              return '150'
            case 58:
              return '209'
          }
          break
      }
      return ''
    },
    handleClickAnchor(selector) {
      // A002 押下された分類が先頭になるよう、外部リンクエリアのスクロールを制御する
      this.$nextTick(() => {
        const anchor = document.getElementsByName(selector)
        if (anchor) {
          this.$refs.contentArea.scrollTop = anchor[0].offsetTop - this.$refs.contentArea.offsetTop
        }
      })
    },
    openWindow() {
      // cursor: not-allowed; と pointer-events: none; が同時に使えなかったため
      this.linkUrl = this.form.linkUrl
      this.paramObject = this.form.newMainSiteParamList[0]
      const newWindow = window.open('', this.target)
      if (newWindow) {
        const linkForm = document.createElement('form')
        linkForm.target = this.target
        linkForm.method = 'POST'
        linkForm.action = this.linkUrl
        // request と paramObject をマージする｡
        // key が同じ場合は､ paramObject を優先する
        const objs = Object.assign({}, this.request, this.paramObject)
        const params = Object.entries(objs)
          .map(e => ({ name: e[0], value: e[1] }))
        if (params) {
          for (const param of params) {
            const linkInput = document.createElement('input')
            linkInput.type = 'hidden'
            linkInput.name = param.name
            linkInput.value = param.value
            linkForm.appendChild(linkInput)
          }
        }
        newWindow.document.body.appendChild(linkForm)
        linkForm.submit()
      } else {
        const label = this.$store.getters.pageInfo.label
        notifyMessage(2, 'ポップアップを許可してください｡', label)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.content-card {
  margin: 0.5rem 1rem;
}
.link {
  font-weight: bold;
  padding: 4px;
  color: black;
}
.line-space {
  margin-top: 0.3rem;
  margin-bottom: 0.5rem;
}
ifa-link {
  color: dodgerblue;
}
.external-link {
  padding: 3px 7px;
  text-decoration: underline;
  &:hover, &:focus {
    text-decoration: underline;
      }
}
.external-link-icon {
  --background-image: url(~@/assets/icons/external-link.svg);
  content: "";

  height: 14px;
  width: 14px;
  display: inline-block;
  vertical-align: middle;
}
.external-link-wrapper {
  display: inline-block
}
.external-link-enabled {
  cursor: pointer;
  .el-link {
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  &:hover {
    opacity: 0.7;
  }
}
.external-link-disabled {
  .el-link {
    // el-link が pointer アイコンにしてしまうので強制的にアイコンを変更
    cursor: not-allowed;
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  cursor: not-allowed;
  &:hover {
    opacity: 0.7;
  }
}
</style>
