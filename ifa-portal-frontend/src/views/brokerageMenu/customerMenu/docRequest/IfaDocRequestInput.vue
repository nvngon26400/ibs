<template>
  <!-- 画面名：書類請求入力 SUB0202_0704-01 -->
  <div>
    <screen-title :text="inputForm.title"></screen-title>
    <div class="caption_card">
      <!-- 書類請求入力エリア -->
      <el-card class="input_card">
        <div>
          <el-form
            ref="inputForm"
            :model="inputForm"
            label-position="left"
            class="form-main"
            label-width="200px"
            style="padding-top: 1rem;"
            :rules="rules"
          >
            <el-row>
              <el-col style="margin-top: 4px;"
                      :span="10"
                      :offset="0"
              >
                <ifa-input-select
                  v-model="inputForm.bunruiCd"
                  prop="bunruiCd"
                  label="分類"
                  size="small"
                  style="width: 90%;"
                  code-list-id="original"
                  placeholder="選択してください"
                  :original-list="bunruiList"
                  required
                  @change="handlerBunruiCdChange"
                >
                </ifa-input-select>
              </el-col>
              <el-col style="margin-top: 4px;"
                      :span="12"
                      :offset="0"
              >
                <ifa-input-select
                  v-model="inputForm.shoruiCd"
                  prop="shoruiCd"
                  label="書類名"
                  size="small"
                  style="width: 90%;"
                  code-list-id="original"
                  placeholder="選択してください"
                  :original-list="shoruiList"
                  required
                  @change="handlerShoruiCdChange"
                >
                </ifa-input-select>
              </el-col>
              <el-col class="clear_class"
                      style="display: inline-flex;"
                      :span="2"
                      :offset="0"
              >
                <ifa-button
                  id="btnClear"
                  text="リセット"
                  color="secondary"
                  action-type="originalAction"
                  @app-action-handler="handleClear"
                ></ifa-button>
                <ifa-doc-balloon-icon
                  v-if="commentIsVisible"
                  style="margin-top: 5px;"
                  icon-type="info"
                >
                  <div v-html="paperMasterInfo.comment"></div>
                </ifa-doc-balloon-icon>
              </el-col>
            </el-row>
            <div
              v-if="isShoruiCdJ105"
            >
              <el-row>
                <el-col style="display: inline-flex;"
                        :span="10"
                        :offset="0"
                >
                  <div class="fund-brand-class">
                    <span class="fund-required">*</span>
                    <span class="fund-label">投信銘柄コード/投信銘柄名</span>
                    <ifa-input-text
                      id="fundNricode4"
                      v-model="inputForm.fundNricode4"
                      prop="fundNricode4"
                      :min="0"
                      :max="9999"
                      size="small"
                      label="銘柄コード(上4桁)"
                      :domain="IfaNumberLength4DomainModel"
                      style="width: 130px;margin-top: 10px;"
                      required
                      @blur="handlerFundNameSearch"
                    >
                    </ifa-input-text>
                    <span class="fund-connect"> - </span>
                    <ifa-input-text
                      id="fundNricode3"
                      v-model="inputForm.fundNricode3"
                      prop="fundNricode3"
                      size="small"
                      label="銘柄コード(下3桁)"
                      style="width: 120px;margin-top: 10px;"
                      required
                      :maxlength="3"
                      @blur="handlerFundNameSearch"
                    >
                    </ifa-input-text>
                  </div>
                </el-col>
                <el-col style="display: inline-flex;"
                        :span="12"
                        :offset="0"
                >
                  <span class="fund-name">{{ inputForm.fundName }}</span>
                </el-col>
                <el-col style="display: inline-flex;"
                        :span="2"
                        :offset="0"
                >
                  <ifa-button
                    id="btnBrandSearch"
                    text="投信銘柄検索"
                    color="primary"
                    small
                    action-type="originalAction"
                    style="margin-top: 12px;"
                    @app-action-handler="openBrandSearch"
                  ></ifa-button>
                </el-col>
              </el-row>
            </div>
            <el-row>
              <el-col style="margin-top: 10px;display: inline-flex;"
                      :span="10"
              >
                <ifa-input-quantity
                  v-model="inputForm.busuu"
                  label="部数"
                  prop="busuu"
                  :min="1"
                  :max="9999"
                  :placeholder="' '"
                  style="width: 130px;"
                  :domain="IfaBusuu4DomainModel"
                  :disabled="busuuIsDisabled"
                  unit="部"
                  :required="busuuIsRequired"
                  :maxlength="4"
                ></ifa-input-quantity>
              </el-col>
              <el-col style="margin-top: 10px;"
                      :span="12"
              >
                <ifa-input-select
                  v-model="inputForm.hassouSts"
                  prop="hassouSts"
                  label="交付区分"
                  size="small"
                  style="width: 90%;"
                  placeholder="選択してください"
                  code-list-id="original"
                  :original-list="hassouStsList"
                  required
                  @change="handlerHassouStsChange"
                >
                </ifa-input-select>
              </el-col>
            </el-row>
            <div
              v-if="!isShoruiCdJ105"
            >
              <!-- 内容 -->
              <el-row
                v-if="naiyouIsVisible"
              >
                <el-col style="margin-top: 10px;"
                        :span="22"
                >
                  <ifa-input-text
                    id="naiyou"
                    v-model="inputForm.naiyou"
                    prop="naiyou"
                    :label="paperMasterInfo.naiyou"
                    size="small"
                    :required="naiyouIsRequired"
                    :domain="IfaText200DomainModel"
                    original-screen-id="SUB0202_0704-01"
                    :style="naiyouWitdh"
                    :maxlength="naiyouLength"
                    label-class="doc-common-label"
                  >
                  </ifa-input-text>
                </el-col>
              </el-row>
              <!-- 備考 -->
              <el-row v-if="bikouIsVisible">
                <el-col style="margin-top: 10px;"
                        :span="22"
                >
                  <ifa-input-text
                    id="bikou"
                    v-model="inputForm.bikou"
                    prop="bikou"
                    :label="paperMasterInfo.bikou"
                    size="small"
                    :required="bikouIsRequired"
                    :domain="IfaText200DomainModel"
                    original-screen-id="SUB0202_0704-01"
                    :style="bikouWitdh"
                    :maxlength="bikouLength"
                    label-class="doc-common-label"
                  >
                  </ifa-input-text>
                </el-col>
              </el-row>
            </div>
            <!-- 郵便番号 -->
            <el-row v-if="yuusouNumberIsVisible">
              <el-col style="margin-top: 10px;display: inline-flex;"
                      :span="3"
                      :offset="0"
              >
                <ifa-input-text
                  v-model="inputForm.yuusouNumber"
                  style="width: 180px;"
                  label="郵便番号"
                  prop="yuusouNumber"
                  :placeholder="' '"
                  :domain="IfaYuusou7DomainModel"
                  :required="yuusouNumberIsRequired"
                  :maxlength="7"
                  @input="yuusouNumberChange"
                ></ifa-input-text>
              </el-col>
              <el-col style="display: inline-flex; margin-top: 10px;"
                      :span="2"
                      :offset="5"
              >
                <ifa-button
                  v-if="yuusouNumberBtnIsVisible"
                  id="btnAddressSearch"
                  text="住所検索"
                  color="primary"
                  :disabled="yuusouNumberBtnIsDisabled"
                  small
                  action-type="originalAction"
                  @app-action-handler="openAddressSearch"
                ></ifa-button>
              </el-col>
              <el-col style="display: inline-flex;"
                      :span="10"
                      :offset="0"
              >
                <span style="font-size: 14px;margin-top: 18px;margin-left: 18px;">郵便番号は半角数字7文字をハイフンなしで入力してください。</span>
              </el-col>
            </el-row>
            <!-- 住所 -->
            <el-row v-if="address2IsVisible || addressIsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-text
                  id="address02"
                  ref="docAddress02"
                  v-model="inputForm.address02"
                  prop="address02"
                  label="都道府県・市区町村・丁目"
                  size="small"
                  :domain="IfaText100DomainModel"
                  original-screen-id="SUB0202_0704-01"
                  :required="cityIsRequired"
                  :maxlength="100"
                  @blur="clearAddrLengthMsg"
                >
                </ifa-input-text>
              </el-col>
            </el-row>
            <el-row v-if="address3IsVisible || addressIsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-text
                  id="address03"
                  ref="docAddress03"
                  v-model="inputForm.address03"
                  prop="address03"
                  label="番地"
                  size="small"
                  :domain="IfaText100DomainModel"
                  original-screen-id="SUB0202_0704-01"
                  :required="streetIsRequired"
                  :maxlength="100"
                  @blur="clearAddrLengthMsg"
                >
                </ifa-input-text>
              </el-col>
            </el-row>
            <el-row v-if="address4IsVisible || addressIsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-text
                  id="address04"
                  ref="docAddress04"
                  v-model="inputForm.address04"
                  prop="address04"
                  label="建物名・部屋番号"
                  size="small"
                  :domain="IfaText100DomainModel"
                  original-screen-id="SUB0202_0704-01"
                  :maxlength="100"
                  @blur="clearAddrLengthMsg"
                >
                </ifa-input-text>
              </el-col>
            </el-row>
            <!-- オプション1 -->
            <el-row v-if="option1IsVisible">
              <el-col style="margin-top: 10px;display: inline-flex;"
                      :span="22"
              >
                <ifa-input-radio
                  v-model="inputForm.sentakuMei1"
                  prop="sentakuMei1"
                  :label="paperMasterInfo.option1"
                  code-list-id="original"
                  :original-list="option1List"
                  :required="option1IsRequired"
                  :disabled="option1IsDisabled"
                  label-class="doc-common-label"
                ></ifa-input-radio>
                <span v-if="isShoruiCdJ105"
                      style="font-size: 14px; margin: 8px 0px 0px 100px;"
                >
                  ※請求目論見書の交付は「同時請求する」にチェックすること！
                </span>
              </el-col>
            </el-row>
            <!-- オプション2 -->
            <el-row v-if="option2IsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-radio
                  v-model="inputForm.sentakuMei2"
                  prop="sentakuMei2"
                  :label="paperMasterInfo.option2"
                  code-list-id="original"
                  :original-list="option2List"
                  :required="option2IsRequired"
                  label-class="doc-common-label"
                ></ifa-input-radio>
              </el-col>
            </el-row>
            <!-- オプション3 -->
            <el-row v-if="option3IsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-radio
                  v-model="inputForm.sentakuMei3"
                  prop="sentakuMei3"
                  :label="paperMasterInfo.option3"
                  code-list-id="original"
                  :original-list="option3List"
                  :required="option3IsRequired"
                  label-class="doc-common-label"
                ></ifa-input-radio>
              </el-col>
            </el-row>
            <!-- テキスト1 -->
            <el-row v-if="txt1IsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-text
                  id="txt1"
                  v-model="inputForm.txtNaiyou1"
                  prop="txtNaiyou1"
                  :label="paperMasterInfo.txt1"
                  size="small"
                  :domain="IfaText100DomainModel"
                  original-screen-id="SUB0202_0704-01"
                  :required="txt1IsRequired"
                  :style="txt1Witdh"
                  :maxlength="txt1Length"
                  label-class="doc-common-label"
                >
                </ifa-input-text>
              </el-col>
            </el-row>
            <!-- テキスト2 -->
            <el-row v-if="txt2IsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-text
                  id="txt2"
                  v-model="inputForm.txtNaiyou2"
                  prop="txtNaiyou2"
                  :label="paperMasterInfo.txt2"
                  size="small"
                  :domain="IfaText100DomainModel"
                  original-screen-id="SUB0202_0704-01"
                  :required="txt2IsRequired"
                  :style="txt2Witdh"
                  :maxlength="txt2Length"
                  label-class="doc-common-label"
                >
                </ifa-input-text>
              </el-col>
            </el-row>
            <!-- テキスト3 -->
            <el-row v-if="txt3IsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-text
                  id="txt3"
                  v-model="inputForm.txtNaiyou3"
                  prop="txtNaiyou3"
                  :label="paperMasterInfo.txt3"
                  size="small"
                  :domain="IfaText100DomainModel"
                  original-screen-id="SUB0202_0704-01"
                  :required="txt3IsRequired"
                  :style="txt3Witdh"
                  :maxlength="txt3Length"
                  label-class="doc-common-label"
                >
                </ifa-input-text>
              </el-col>
            </el-row>
            <!-- テキスト4 -->
            <el-row v-if="txt4IsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-text
                  id="txt4"
                  v-model="inputForm.txtNaiyou4"
                  prop="txtNaiyou4"
                  :label="paperMasterInfo.txt4"
                  size="small"
                  :domain="IfaText100DomainModel"
                  original-screen-id="SUB0202_0704-01"
                  :required="txt4IsRequired"
                  :style="txt4Witdh"
                  :maxlength="txt4Length"
                  label-class="doc-common-label"
                >
                </ifa-input-text>
              </el-col>
            </el-row>
            <!-- テキスト5 -->
            <el-row v-if="txt5IsVisible">
              <el-col style="margin-top: 10px;"
                      :span="22"
              >
                <ifa-input-text
                  id="txt5"
                  v-model="inputForm.txtNaiyou5"
                  prop="txtNaiyou5"
                  :label="paperMasterInfo.txt5"
                  size="small"
                  :domain="IfaText100DomainModel"
                  original-screen-id="SUB0202_0704-01"
                  :required="txt5IsRequired"
                  :style="txt5Witdh"
                  :maxlength="txt5Length"
                  label-class="doc-common-label"
                >
                </ifa-input-text>
              </el-col>
            </el-row>
            <!-- ご注意事項 -->
            <el-row v-if="tyuiKakiIsVisible">
              <el-col style="margin-top: 10px;display: inline-flex;"
                      :span="23"
              >
                <span style="font-size: 14px; font-weight: bold;margin: 4px 0px 0px 18px; width: 200px;">ご注意事項</span>
                <span style="font-size: 14px; margin-top: 5px; width: 80%;"
                      v-html="paperMasterInfo.tyuiKaki"
                ></span>
              </el-col>
            </el-row>
            <!-- ボタンエリア -->
            <el-row class="row_top">
              <el-col class="button_bottom"
                      :span="2"
              >
                <ifa-button
                  :form="formRef"
                  :disabled="activeBtn"
                  text="請求確認"
                  action-type="originalAction"
                  @app-action-handler="handleDocRequestConfirm"
                ></ifa-button>
                <button
                  id="doc-cancel-button"
                  type="button"
                  value=""
                  hidden="true"
                  @click="handleDocCancel"
                ></button>
                <button
                  id="doc-detail-button"
                  type="button"
                  value=""
                  hidden="true"
                  @click="handleDocDetail"
                ></button>
                <button
                  id="doc-cancel-button-bm"
                  type="button"
                  value=""
                  hidden="true"
                  @click="handleDocBmCancel"
                ></button>
                <button
                  id="doc-detail-button-bm"
                  type="button"
                  value=""
                  hidden="true"
                  @click="handleDocBmDetail"
                ></button>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-card>
    </div>
    <!-- 書類請求一覧エリア -->
    <screen-title :text="listForm.title"></screen-title>
    <div class="caption_card">
      <el-card class="doc_req_card">
        <el-row class="card_row">
          <grid-table
            ref="gridTableByDoc"
            :options="pqGridOption"
            :auto-refresh="false"
            class="grid_table_class"
          ></grid-table>
        </el-row>
      </el-card>
    </div>
    <!-- SUB0202_0704-04_書類請求詳細 -->
    <ifa-doc-request-detail
      ref="ifaDocRequestDetail"
      :is-visible="dialogDetailVisible"
      :form-data="detailData"
      :append-to-body="true"
      @close-modal="handleCloseModal"
    ></ifa-doc-request-detail>
    <!-- SUB0202_0704-03_1_書類請求取消確認 -->
    <ifa-doc-request-cancel-confirm
      ref="ifaDocRequestCancelConfirm"
      :is-visible="dialogCancelVisible"
      :form-data="cancelConfirmData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @close-modal="handleCloseModal"
      @cancel-finish="handleCancelFinish"
    ></ifa-doc-request-cancel-confirm>
    <!-- SUB0202_0704-03_2_書類請求取消完了 -->
    <ifa-doc-request-cancel-complete
      ref="ifaDocRequestCancelComplete"
      :is-visible="dialogCancelFinish"
      :form-data="cancelConfirmData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @close-modal="handleCloseModal"
      @move-to-input-by-cancel="handleMoveToInput"
    ></ifa-doc-request-cancel-complete>
    <!-- SUB0202_0704-02_1_書類請求確認 -->
    <ifa-doc-request-execute-confirm
      ref="ifaDocRequestExecuteConfirm"
      :is-visible="dialogExecuteVisible"
      :form-data="confirmData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @close-modal="handleCloseModal"
      @confirm-finish="handleExecuteFinish"
    ></ifa-doc-request-execute-confirm>
    <!-- SUB0202_0704-02_2_書類請求完了 -->
    <ifa-doc-request-accept-complete
      ref="ifaDocRequestAcceptComplete"
      :is-visible="dialogFinish"
      :form-data="confirmData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @close-modal="handleCloseModal"
      @move-to-input-by-confirm="handleMoveToInput"
    ></ifa-doc-request-accept-complete>
    <!--銘柄検索ダイアログ-->
    <ifa-fund-brand-search
      ref="ifaFundBrandSearch"
      :is-visible="brandSerachIsVisible"
      @close-modal="handleCloseModal"
      @result="handleBrandResult"
    ></ifa-fund-brand-search>
    <!-- 住所検索ダイアログ -->
    <ifa-address-search
      ref="ifaAddressSearch"
      :is-visible="addressSerachIsVisible"
      :form-data="addressData"
      @close-modal="handleCloseModal"
      @result="handleAddressResult"
    ></ifa-address-search>
    <!-- A001	初期化 -->
    <ifa-requester
      id="ifaDocRequestInputInitializeA001"
      action-id="SUB0202_0704-01#A001"
      action-type="requestAction"
      @response-handler="handlerInitializeA001($event)"
      @response-error-handler="errorInitializeHandler($event)"
    ></ifa-requester>
    <!-- A002	分類選択 -->
    <ifa-requester
      id="ifaDocRequestInputSelectShoruiListA002"
      action-id="SUB0202_0704-01#A002"
      action-type="requestAction"
      :request-model="A002RequestModel"
      @response-handler="handlerSelectShoruiListA002($event)"
    ></ifa-requester>
    <!-- A003	書類選択 -->
    <ifa-requester
      id="ifaDocRequestInputSelectShoruiDateA003"
      action-id="SUB0202_0704-01#A003"
      action-type="requestAction"
      :request-model="A003RequestModel"
      @response-handler="handlerSelectShoruiDateA003($event)"
    ></ifa-requester>
    <!-- A005	投信銘柄情報取得 -->
    <ifa-requester
      id="ifaDocRequestInputSelectMFNameA005"
      action-id="SUB0202_0704-01#A005"
      action-type="requestAction"
      :request-model="A005RequestModel"
      @response-handler="handlerSelectMFNameA005($event)"
    ></ifa-requester>
    <!-- A007	書類請求確認 -->
    <ifa-requester
      id="ifaDocRequestExecuteConfirmA007"
      action-id="SUB0202_0704-01#A007"
      action-type="requestAction"
      :request-model="A007RequestModel"
      @response-handler="handleExecuteConfirmA007($event)"
    ></ifa-requester>
    <!-- A008	書類請求取消 -->
    <ifa-requester
      id="ifaDocRequestCancelConfirmA008"
      action-id="SUB0202_0704-01#A008"
      action-type="requestAction"
      :request-model="A008RequestModel"
      @response-handler="handlerCancelConfirmA008($event)"
    ></ifa-requester>
    <!-- A013	書類請求取消 -->
    <ifa-requester
      id="ifaDocRequestCancelConfirmA013"
      action-id="SUB0202_0704-01#A013"
      action-type="requestAction"
      :request-model="A013RequestModel"
      @response-handler="handlerCancelConfirmA013($event)"
    ></ifa-requester>
    <!-- A009	書類請求詳細 -->
    <ifa-requester
      id="ifaDocRequestDetailA009"
      action-id="SUB0202_0704-01#A009"
      action-type="requestAction"
      :request-model="A009RequestModel"
      @response-handler="handlerDetailA009($event)"
    ></ifa-requester>
    <!-- A014	書類請求詳細 -->
    <ifa-requester
      id="ifaDocRequestDetailA014"
      action-id="SUB0202_0704-01#A014"
      action-type="requestAction"
      :request-model="A014RequestModel"
      @response-handler="handlerDetailA014($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import GridTable from '@/components/GridTable'
import IfaYen120DomainModel from '@/resource/domain/IfaYen120DomainModel.json'
import IfaNumberLength4DomainModel from '@/resource/domain/IfaNumberLength4DomainModel.json'
import IfaBusuu4DomainModel from '@/resource/domain/IfaBusuu4DomainModel.json'
import IfaYuusou7DomainModel from '@/resource/domain/IfaYuusou7DomainModel.json'
import IfaText200DomainModel from '@/resource/domain/IfaText200DomainModel.json'
import IfaText100DomainModel from '@/resource/domain/IfaText100DomainModel.json'
import IfaDocRequestExecuteConfirm from './IfaDocRequestExecuteConfirm'
import IfaDocRequestAcceptComplete from './IfaDocRequestAcceptComplete'
import IfaDocRequestCancelConfirm from './IfaDocRequestCancelConfirm'
import IfaDocRequestCancelComplete from './IfaDocRequestCancelComplete'
import IfaDocRequestDetail from './IfaDocRequestDetail'
import ifaUtils from '@/utils/ifaUtils.js'
import { IfaDocRequestInputFormModel } from './js/IfaDocRequestInputFormModel'
import { IfaDocRequestListFormModel } from './js/IfaDocRequestListFormModel'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaDocRequestInputA002RequestModel } from './js/IfaDocRequestInputA002RequestModel'
import { IfaDocRequestInputA003RequestModel } from './js/IfaDocRequestInputA003RequestModel'
import { IfaDocRequestInputA005RequestModel } from './js/IfaDocRequestInputA005RequestModel'
import { IfaDocRequestInputA007RequestModel } from './js/IfaDocRequestInputA007RequestModel'
import { IfaDocRequestInputA008RequestModel } from './js/IfaDocRequestInputA008RequestModel'
import { IfaDocRequestInputA009RequestModel } from './js/IfaDocRequestInputA009RequestModel'
import { IfaDocRequestInputA013RequestModel } from './js/IfaDocRequestInputA013RequestModel'
import { IfaDocRequestInputA014RequestModel } from './js/IfaDocRequestInputA014RequestModel'
import { getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import IfaFundBrandSearch from '@/views/common/IfaFundBrandSearch'
import IfaAddressSearch from '@/views/common/IfaAddressSearch'
import { getMessage } from '@/utils/errorHandler'
import IfaDocBalloonIcon from '@/components/icon/IfaDocBalloonIcon'

export default {
  components: {
    screenTitle,
    GridTable,
    IfaDocRequestExecuteConfirm,
    IfaDocRequestAcceptComplete,
    IfaDocRequestCancelConfirm,
    IfaDocRequestCancelComplete,
    IfaDocRequestDetail,
    IfaFundBrandSearch,
    IfaAddressSearch,
    IfaDocBalloonIcon
  },
  emits: ['initializeError'],
  data() {
    return {
      loading: null,
      inputForm: new IfaDocRequestInputFormModel(),
      listForm: new IfaDocRequestListFormModel(),
      IfaYen120DomainModel: IfaYen120DomainModel,
      IfaNumberLength4DomainModel: IfaNumberLength4DomainModel,
      IfaBusuu4DomainModel: IfaBusuu4DomainModel,
      IfaYuusou7DomainModel: IfaYuusou7DomainModel,
      IfaText200DomainModel: IfaText200DomainModel,
      IfaText100DomainModel: IfaText100DomainModel,
      pqGridOption: getConvertedOption(objDocRequest),
      activeBtn: true,
      dialogDetailVisible: false,
      detailData: {},
      paperMasterInfo: {},
      inputData: {},
      cancelData: {},
      bmCancelData: {},
      formRef: {},
      dialogExecuteVisible: false,
      dialogFinish: false,
      addressData: {},
      dialogCancelVisible: false,
      dialogCancelFinish: false,
      brandSerachIsVisible: false,
      addressSerachIsVisible: false,
      confirmData: {},
      cancelConfirmData: {},
      backupBunruiCd: '',
      backupShoruiCd: '',
      isExecBtn: false,
      isShoruiCdJ105: false,
      busuuIsDisabled: true,
      busuuIsRequired: false,
      // 交付区分
      hassouStsList: [{ key: '01', value: '郵送' }, { key: '06', value: '営業店郵送' }],
      backHassou: '01',
      // 郵便番号
      yuusouNumberIsVisible: false,
      yuusouNumberBtnIsVisible: false,
      yuusouNumberIsRequired: false,
      yuusouNumberBtnIsDisabled: true,
      // 内容
      naiyouIsVisible: false,
      naiyouIsRequired: false,
      naiyouLength: 200,
      // 備考
      bikouIsVisible: false,
      bikouIsRequired: false,
      bikouLength: 200,
      // 住所
      addressIsVisible: false,
      address2IsVisible: false,
      address3IsVisible: false,
      address4IsVisible: false,
      addressLengthCheck: false,
      // 住所 都道府県・市区町村・丁目
      cityIsRequired: false,
      // 住所 番地
      streetIsRequired: false,
      // オプション1
      option1IsVisible: false,
      option1IsRequired: false,
      option1List: [],
      option1IsDisabled: false,
      // オプション2
      option2IsVisible: false,
      option2IsRequired: false,
      option2List: [],
      // オプション3
      option3IsVisible: false,
      option3IsRequired: false,
      option3List: [],
      // テキスト1
      txt1IsVisible: false,
      txt1IsRequired: false,
      txt1Length: 100,
      // テキスト2
      txt2IsVisible: false,
      txt2IsRequired: false,
      txt2Length: 100,
      // テキスト3
      txt3IsVisible: false,
      txt3IsRequired: false,
      txt3Length: 100,
      // テキスト4
      txt4IsVisible: false,
      txt4IsRequired: false,
      txt4Length: 100,
      // テキスト5
      txt5IsVisible: false,
      txt5IsRequired: false,
      txt5Length: 100,
      // 注意事項
      tyuiKakiIsVisible: false,
      // コメント
      commentIsVisible: false,
      naiyouWitdh: null,
      bikouWitdh: null,
      txt1Witdh: null,
      txt2Witdh: null,
      txt3Witdh: null,
      txt4Witdh: null,
      txt5Witdh: null,
      bunruiList: [],
      shoruiList: [],
      rules: {
        address02: { trigger: 'blur', validator: this.checkAddressValidator },
        address03: { trigger: 'blur', validator: this.checkAddressValidator },
        address04: { trigger: 'blur', validator: this.checkAddressValidator },
        naiyou: { trigger: 'blur', validator: this.checkNaiyouValidator },
        bikou: { trigger: 'blur', validator: this.checkBikouValidator },
        txtNaiyou1: { trigger: 'blur', validator: this.checkTxt1Validator },
        txtNaiyou2: { trigger: 'blur', validator: this.checkTxt2Validator },
        txtNaiyou3: { trigger: 'blur', validator: this.checkTxt3Validator },
        txtNaiyou4: { trigger: 'blur', validator: this.checkTxt4Validator },
        txtNaiyou5: { trigger: 'blur', validator: this.checkTxt5Validator },
        fundNricode4: { trigger: 'blur', validator: this.checkFundNameValidator },
        fundNricode3: { trigger: 'blur', validator: this.checkFundNameValidator }
      }
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    A002RequestModel() {
      return new IfaDocRequestInputA002RequestModel(this.inputForm)
    },
    A003RequestModel() {
      return new IfaDocRequestInputA003RequestModel(this.inputForm)
    },
    A005RequestModel() {
      return new IfaDocRequestInputA005RequestModel(this.inputForm)
    },
    A007RequestModel() {
      return new IfaDocRequestInputA007RequestModel(this.inputData)
    },
    A008RequestModel() {
      return new IfaDocRequestInputA008RequestModel(this.cancelData)
    },
    A009RequestModel() {
      return new IfaDocRequestInputA009RequestModel(this.cancelData)
    },
    A013RequestModel() {
      return new IfaDocRequestInputA013RequestModel(this.bmCancelData)
    },
    A014RequestModel() {
      return new IfaDocRequestInputA014RequestModel(this.bmCancelData)
    }
  },
  mounted() {
    this.formRef = this.$refs['inputForm']
  },
  unmounted() {
    if (this.loading) {
      this.loading.close()
    }
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  methods: {
    checkFundNameValidator(rule, value, callback) {
      if (value && rule.field === 'fundNricode3' && !this.isExecBtn) {
        const dateRegex = '^[ 0-9a-zA-Z]+$'
        if (String(value).match(new RegExp(dateRegex)) === null) {
          callback(getMessage('errors.required', ['英数字']))
          return
        }
      }
      if (this.isShoruiCdJ105 && this.isExecBtn && !this.inputForm.fundName && this.inputForm.fundNricode4 && this.inputForm.fundNricode3) {
        callback('有効な銘柄コードを入力してください。')
        return
      }
      callback()
    },
    checkTxt1Validator(rule, value, callback) {
      if (ifaUtils.isByteLengthExceeded(value, 100)) {
        callback('100バイトを超過しています。100バイト以下で入力してください。')
        return
      }
      if (value && this.paperMasterInfo.sujiKomokuT1 === '1') {
        const dateRegex = '^[+-]?\\d+$'
        if (String(value).match(new RegExp(dateRegex)) === null) {
          callback(getMessage('errors.required', ['数値']))
          return
        }
      }
      callback()
    },
    checkTxt2Validator(rule, value, callback) {
      if (ifaUtils.isByteLengthExceeded(value, 100)) {
        callback('100バイトを超過しています。100バイト以下で入力してください。')
        return
      }
      if (value && this.paperMasterInfo.sujiKomokuT2 === '1') {
        const dateRegex = '^[+-]?\\d+$'
        if (String(value).match(new RegExp(dateRegex)) === null) {
          callback(getMessage('errors.required', ['数値']))
          return
        }
      }
      callback()
    },
    checkTxt3Validator(rule, value, callback) {
      if (ifaUtils.isByteLengthExceeded(value, 100)) {
        callback('100バイトを超過しています。100バイト以下で入力してください。')
        return
      }
      if (value && this.paperMasterInfo.sujiKomokuT3 === '1') {
        const dateRegex = '^[+-]?\\d+$'
        if (String(value).match(new RegExp(dateRegex)) === null) {
          callback(getMessage('errors.required', ['数値']))
          return
        }
      }
      callback()
    },
    checkTxt4Validator(rule, value, callback) {
      if (ifaUtils.isByteLengthExceeded(value, 100)) {
        callback('100バイトを超過しています。100バイト以下で入力してください。')
        return
      }
      if (value && this.paperMasterInfo.sujiKomokuT4 === '1') {
        const dateRegex = '^[+-]?\\d+$'
        if (String(value).match(new RegExp(dateRegex)) === null) {
          callback(getMessage('errors.required', ['数値']))
          return
        }
      }
      callback()
    },
    checkTxt5Validator(rule, value, callback) {
      if (ifaUtils.isByteLengthExceeded(value, 100)) {
        callback('100バイトを超過しています。100バイト以下で入力してください。')
        return
      }
      if (value && this.paperMasterInfo.sujiKomokuT5 === '1') {
        const dateRegex = '^[+-]?\\d+$'
        if (String(value).match(new RegExp(dateRegex)) === null) {
          callback(getMessage('errors.required', ['数値']))
          return
        }
      }
      callback()
    },
    checkNaiyouValidator(rule, value, callback) {
      if (ifaUtils.isByteLengthExceeded(value, 200)) {
        callback('200バイトを超過しています。200バイト以下で入力してください。')
        return
      }
      if (value && this.paperMasterInfo.sujiKomokuNaiyou === '1') {
        const dateRegex = '^[+-]?\\d+$'
        if (String(value).match(new RegExp(dateRegex)) === null) {
          callback(getMessage('errors.required', ['数値']))
          return
        }
      }
      callback()
    },
    checkBikouValidator(rule, value, callback) {
      if (ifaUtils.isByteLengthExceeded(value, 200)) {
        callback('200バイトを超過しています。200バイト以下で入力してください。')
        return
      }
      if (value && this.paperMasterInfo.sujiKomokuBikou === '1') {
        const dateRegex = '^[+-]?\\d+$'
        if (String(value).match(new RegExp(dateRegex)) === null) {
          callback(getMessage('errors.required', ['数値']))
          return
        }
      }
      callback()
    },
    checkAddressValidator(rule, value, callback) {
      const adrContent = this.inputForm.address02 + this.inputForm.address03 + this.inputForm.address04
      if (value && this.addressLengthCheck && ifaUtils.isByteLengthExceeded(adrContent, 100)) {
        callback('都道府県・市区町村・丁目、番地、建物名・部屋番号の合計入力量が100バイトとなるように入力してください。')
        return
      } else if (value && rule.field === 'address02' && ifaUtils.isByteLengthExceeded(this.inputForm.address02, 100)) {
        callback('100バイトを超過しています。100バイト以下で入力してください。')
        return
      } else if (value && rule.field === 'address03' && ifaUtils.isByteLengthExceeded(this.inputForm.address03, 100)) {
        callback('100バイトを超過しています。100バイト以下で入力してください。')
        return
      } else if (value && rule.field === 'address04' && ifaUtils.isByteLengthExceeded(this.inputForm.address04, 100)) {
        callback('100バイトを超過しています。100バイト以下で入力してください。')
        return
      }
      callback()
    },
    // 初期表示
    onShow(resume) {
      this.backupBunruiCd = null
      this.handleClear()
      this.$nextTick(() => {
        document.getElementById('ifaDocRequestInputInitializeA001').click()
      })
    },
    // A001-初期化
    handlerInitializeA001(res) {
      const bunruiList = res?.dataList && res?.dataList.length > 0
        ? res.dataList[0].shoruiBunruiSelect.map(v => ({
          key: v.bunruiCd,
          value: v.bunruimei
        }))
        : []
      this.bunruiList = bunruiList
      this.bunruiList.unshift({ key: '', value: '' })
      this.pqGridOption.dataModel.data = res.dataList[0]?.shoruiList
      this.inputForm.hassouSts = '01'
      this.$refs['gridTableByDoc'].refreshView()
    },
    // A002-分類選択-書類リスト取得
    handlerBunruiCdChange() {
      this.backupBunruiCd = this.inputForm.bunruiCd
      this.backupShoruiCd = null
      this.handleShoruiClear()
      if (this.inputForm.bunruiCd) {
        this.$nextTick(() => {
          document.getElementById('ifaDocRequestInputSelectShoruiListA002').click()
        })
      } else {
        this.handleShoruiClear()
      }
    },
    handlerSelectShoruiListA002(res) {
      const shoruiList = res?.dataList && res?.dataList.length > 0
        ? res.dataList[0].shoruiSelect.map(v => ({
          key: v.shoruiCd,
          value: v.shoruimei
        }))
        : []
      this.shoruiList = shoruiList
      this.shoruiList.unshift({ key: '', value: '' })
    },
    // A003-書類選択-書類データ取得
    handlerShoruiCdChange() {
      this.backupShoruiCd = this.inputForm.shoruiCd
      this.handleInit()
      if (this.inputForm.shoruiCd) {
        this.$nextTick(() => {
          document.getElementById('ifaDocRequestInputSelectShoruiDateA003').click()
        })
      }
    },
    handlerSelectShoruiDateA003(res) {
      this.paperMasterInfo = res.dataList[0]?.shoruiDate
      if (this.inputForm.shoruiCd === 'J105') {
        this.isShoruiCdJ105 = true
      } else {
        this.isShoruiCdJ105 = false
      }
      // 交付区分
      this.hassouStsCheck()
      // 部数
      this.busuuCheck()
      // 郵便番号
      this.yuusouNumberCheck()
      // 内容
      this.naiyouCheck()
      // 備考
      this.bikouCheck()
      // 住所
      this.addressCheck()
      // 住所 都道府県・市区町村・丁目
      this.cityCheck()
      // 住所 番地
      this.streetCheck()
      // オプション
      this.optionCheck()
      // テキスト
      this.txtCheck()
      // 注意事項
      this.tyuiKakiCheck()
      // コメント
      this.commentCheck()
      this.inputData.bmKoufuShubetsu = this.paperMasterInfo.bmKoufuShubetsu
      this.inputData.meigaraCd = this.paperMasterInfo.meigaraCd
      // A003チェックしてから、ボタンを有効
      this.activeBtn = false
    },
    handlerHassouStsChange() {
      if (this.inputForm.hassouSts === 'BM') {
        this.$refs['inputForm']?.clearValidate('busuu')
        this.inputForm.busuu = '1'
        this.busuuIsDisabled = true
      } else {
        this.busuuBMCheck()
      }
      this.backHassou = this.inputForm.hassouSts
      this.option1Check()
    },
    // A005-投信銘柄情報取得
    handlerFundNameSearch() {
      this.isExecBtn = false
      if (this.inputForm.fundNricode3 && this.inputForm.fundNricode3.length === 1) {
        this.inputForm.fundNricode3 = ' 0' + this.inputForm.fundNricode3
      } else if (this.inputForm.fundNricode3 && this.inputForm.fundNricode3.length === 2) {
        this.inputForm.fundNricode3 = ' ' + this.inputForm.fundNricode3
      }
      if (this.inputForm.fundNricode4 && this.inputForm.fundNricode3) {
        this.inputForm.fundName = ''
        this.$refs?.inputForm.validateField('fundNricode4', (checkResult) => {
          if (checkResult) {
            this.$refs?.inputForm.validateField('fundNricode3', (checkOk) => {
              if (checkOk) {
                this.$nextTick(() => {
                  document.getElementById('ifaDocRequestInputSelectMFNameA005').click()
                })
              }
            })
          }
        })
      }
    },
    handlerSelectMFNameA005(res) {
      this.inputForm.fundNricode4 = res.dataList[0]?.fundNricode4
      this.inputForm.fundNricode3 = res.dataList[0]?.fundNricode3
      this.inputForm.fundName = res.dataList[0]?.fundName
      this.inputForm.fundCode = res.dataList[0]?.fundCode
      this.inputForm.fundMdBuyKubun = res.dataList[0]?.fundMdBuyKubun
      if (this.inputForm.fundNricode4 && this.inputForm.fundNricode3) {
        this.inputData.meigaraCd = this.inputForm.fundNricode4 + '.' + this.inputForm.fundNricode3
      }
    },
    // A008-書類請求取消
    handlerCancelConfirmA008(res) {
      this.cancelConfirmData = res.dataList[0]?.shoruiModel
      if (this.cancelConfirmData.hassouSts) {
        this.cancelConfirmData.hassouStsName = this.hassouStsList.find((item) => item.key === this.cancelConfirmData.hassouSts)?.value
      }
      this.cancelConfirmData.bmKoufuShubetsu = '0'
      this.$nextTick(() => {
        this.$refs['ifaDocRequestCancelConfirm'].onShow()
        this.dialogCancelVisible = true
      })
    },
    // A013-書類請求取消
    handlerCancelConfirmA013(res) {
      this.cancelConfirmData = res.dataList[0]?.bmShoruiModel
      this.cancelConfirmData.hassouStsName = 'BM交付'
      this.cancelConfirmData.bmKoufuShubetsu = this.cancelConfirmData.shubetsu
      this.$nextTick(() => {
        this.$refs['ifaDocRequestCancelConfirm'].onShow()
        this.dialogCancelVisible = true
      })
    },
    // A009-書類請求詳細
    handlerDetailA009(res) {
      this.detailData = res.dataList[0]?.shoruiModel
      if (this.detailData.hassouSts) {
        this.detailData.hassouStsName = this.hassouStsList.find((item) => item.key === this.detailData.hassouSts)?.value
      }
      this.detailData.bmKoufuShubetsu = '0'
      this.$nextTick(() => {
        this.$refs['ifaDocRequestDetail'].onShow()
        this.dialogDetailVisible = true
      })
    },
    // A014-書類請求詳細
    handlerDetailA014(res) {
      this.detailData = res.dataList[0]?.bmShoruiModel
      this.detailData.hassouStsName = 'BM交付'
      this.detailData.bmKoufuShubetsu = this.detailData.shubetsu
      this.$nextTick(() => {
        this.$refs['ifaDocRequestDetail'].onShow()
        this.dialogDetailVisible = true
      })
    },
    // 請求確認
    async handleDocRequestConfirm() {
      this.inputData.hassouSts = this.inputForm.hassouSts
      this.inputData.fundCode = this.inputForm.fundCode
      this.inputData.fundMdBuyKubun = this.inputForm.fundMdBuyKubun
      this.isExecBtn = true
      this.loading = this.$loading({
        lock: true,
        text: '処理中',
        background: 'rgba(0, 0, 0, 0.4)'
      })
      await this.$refs?.inputForm.validate((callback) => {
        if (callback) {
          this.$nextTick(() => {
            document.getElementById('ifaDocRequestExecuteConfirmA007').click()
          })
        } else {
          this.loading.close()
        }
      })
      this.isExecBtn = false
    },
    handleExecuteConfirmA007(res) {
      Object.assign(this.confirmData, this.inputForm, this.inputData)
      if (this.inputForm.shoruiCd === 'J105') {
        this.confirmData.meigaraCd = this.inputData.meigaraCd
        this.confirmData.meigaraMei = this.inputForm.fundName
      } else {
        this.confirmData.meigaraCd = this.paperMasterInfo.meigaraCd
        this.confirmData.meigaraMei = res.dataList[0]?.fundName
      }
      // エリア表示FLG
      this.confirmData.isShoruiCdJ105 = this.isShoruiCdJ105
      this.confirmData.yuusouNumberIsVisible = this.yuusouNumberIsVisible
      this.confirmData.addressIsVisible = this.addressIsVisible
      this.confirmData.address2IsVisible = this.address2IsVisible
      this.confirmData.address3IsVisible = this.address3IsVisible
      this.confirmData.address4IsVisible = this.address4IsVisible
      this.confirmData.option1IsVisible = this.option1IsVisible
      this.confirmData.option2IsVisible = this.option2IsVisible
      this.confirmData.option3IsVisible = this.option3IsVisible
      this.confirmData.txt1IsVisible = this.txt1IsVisible
      this.confirmData.txt2IsVisible = this.txt2IsVisible
      this.confirmData.txt3IsVisible = this.txt3IsVisible
      this.confirmData.txt4IsVisible = this.txt4IsVisible
      this.confirmData.txt5IsVisible = this.txt5IsVisible
      // BM交付種別
      this.confirmData.bmKoufuShubetsu = this.paperMasterInfo.bmKoufuShubetsu
      if (this.inputForm.hassouSts !== 'BM') {
        this.confirmData.bmKoufuShubetsu = '0'
      }
      // 分類名
      if (this.inputForm.bunruiCd) {
        this.confirmData.bunruimei = this.bunruiList.find((item) => item.key === this.inputForm.bunruiCd)?.value
      }
      // 書類名
      if (this.inputForm.shoruiCd) {
        this.confirmData.shoruimei = this.shoruiList.find((item) => item.key === this.inputForm.shoruiCd)?.value
      }
      // 内容 & 備考
      if (this.isShoruiCdJ105) {
        this.confirmData.naiyouCaption = '投信銘柄コード'
        this.confirmData.naiyou = this.inputForm.fundNricode4 + '.' + this.inputForm.fundNricode3
        this.confirmData.bikouCaption = '投信銘柄名'
        this.confirmData.bikou = this.inputForm.fundName
      } else {
        this.confirmData.naiyouCaption = this.paperMasterInfo.naiyou
        this.confirmData.bikouCaption = this.paperMasterInfo.bikou
      }
      // 交付区分名
      if (this.inputForm.hassouSts) {
        this.confirmData.hassouStsName = this.hassouStsList.find((item) => item.key === this.inputForm.hassouSts)?.value
      }
      // オプション1
      if (this.option1IsVisible) {
        this.confirmData.sentakuMei1 = this.option1List.find((item) => item.key === this.inputForm.sentakuMei1)?.value
      }
      // オプション2
      if (this.option2IsVisible) {
        this.confirmData.sentakuMei2 = this.option2List.find((item) => item.key === this.inputForm.sentakuMei2)?.value
      }
      // オプション3
      if (this.option3IsVisible) {
        this.confirmData.sentakuMei3 = this.option3List.find((item) => item.key === this.inputForm.sentakuMei3)?.value
      }
      // 住所 テキスト1
      if (this.paperMasterInfo.txt1 && this.paperMasterInfo.addressT1) {
        if (this.yuusouNumberIsVisible || this.addressIsVisible) {
          this.confirmData.txt1 = this.paperMasterInfo.txt1
          this.confirmData.txtNaiyou1 = this.getTextContent(this.paperMasterInfo.addressT1)
        } else {
          this.confirmData.txt1 = this.paperMasterInfo.txt1
          this.confirmData.txtNaiyou1 = this.getTextContent2(this.paperMasterInfo.addressT1)
        }
      }
      // 住所 テキスト2
      if (this.paperMasterInfo.txt2 && this.paperMasterInfo.addressT2) {
        if (this.yuusouNumberIsVisible || this.addressIsVisible) {
          this.confirmData.txt2 = this.paperMasterInfo.txt2
          this.confirmData.txtNaiyou2 = this.getTextContent(this.paperMasterInfo.addressT2)
        } else {
          this.confirmData.txt2 = this.paperMasterInfo.txt2
          this.confirmData.txtNaiyou2 = this.getTextContent2(this.paperMasterInfo.addressT2)
        }
      }
      // 住所 テキスト3
      if (this.paperMasterInfo.txt3 && this.paperMasterInfo.addressT3) {
        if (this.yuusouNumberIsVisible || this.addressIsVisible) {
          this.confirmData.txt3 = this.paperMasterInfo.txt3
          this.confirmData.txtNaiyou3 = this.getTextContent(this.paperMasterInfo.addressT3)
        } else {
          this.confirmData.txt3 = this.paperMasterInfo.txt3
          this.confirmData.txtNaiyou3 = this.getTextContent2(this.paperMasterInfo.addressT3)
        }
      }
      // 住所 テキスト4
      if (this.paperMasterInfo.txt4 && this.paperMasterInfo.addressT4) {
        if (this.yuusouNumberIsVisible || this.addressIsVisible) {
          this.confirmData.txt4 = this.paperMasterInfo.txt4
          this.confirmData.txtNaiyou4 = this.getTextContent(this.paperMasterInfo.addressT4)
        } else {
          this.confirmData.txt4 = this.paperMasterInfo.txt4
          this.confirmData.txtNaiyou4 = this.getTextContent2(this.paperMasterInfo.addressT4)
        }
      }
      // 住所 テキスト5
      if (this.paperMasterInfo.txt5 && this.paperMasterInfo.addressT5) {
        if (this.yuusouNumberIsVisible || this.addressIsVisible) {
          this.confirmData.txt5 = this.paperMasterInfo.txt5
          this.confirmData.txtNaiyou5 = this.getTextContent(this.paperMasterInfo.addressT5)
        } else {
          this.confirmData.txt5 = this.paperMasterInfo.txt5
          this.confirmData.txtNaiyou5 = this.getTextContent2(this.paperMasterInfo.addressT5)
        }
      }
      this.$nextTick(() => {
        this.$refs['ifaDocRequestExecuteConfirm'].onShow()
        this.dialogExecuteVisible = true
      })
      this.loading.close()
    },
    // 入力取消のリクエスト
    handleDocCancel() {
      this.cancelData = JSON.parse(unescape(document.getElementById('doc-cancel-button').value))
      this.$nextTick(() => {
        document.getElementById('ifaDocRequestCancelConfirmA008').click()
      })
    },
    handleDocBmCancel() {
      this.bmCancelData = JSON.parse(unescape(document.getElementById('doc-cancel-button-bm').value))
      this.$nextTick(() => {
        document.getElementById('ifaDocRequestCancelConfirmA013').click()
      })
    },
    // 入力詳細のリクエスト
    handleDocDetail() {
      this.cancelData = JSON.parse(unescape(document.getElementById('doc-detail-button').value))
      this.$nextTick(() => {
        document.getElementById('ifaDocRequestDetailA009').click()
      })
    },
    handleDocBmDetail() {
      this.bmCancelData = JSON.parse(unescape(document.getElementById('doc-detail-button-bm').value))
      this.$nextTick(() => {
        document.getElementById('ifaDocRequestDetailA014').click()
      })
    },
    // 入力確認のリスポンス
    async handleExecuteFinish(response) {
      this.dialogExecuteVisible = false
      await this.$nextTick()
      await this.$refs['ifaDocRequestAcceptComplete'].onShow()
      this.dialogFinish = true
    },
    // 取消確認のリスポンス
    async handleCancelFinish(response) {
      this.dialogCancelVisible = false
      await this.$nextTick()
      await this.$refs['ifaDocRequestCancelComplete'].onShow()
      this.dialogCancelFinish = true
    },
    // 完了画面から、入力画面に遷移する時のリクエスト
    handleMoveToInput() {
      this.handleCloseModal()
      this.handleClear()
      this.$nextTick(() => {
        document.getElementById('ifaDocRequestInputInitializeA001').click()
      })
    },
    // 初期化エラー
    errorInitializeHandler(response) {
      const errorInfo = {
        title: this.inputForm.title,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    handleShoruiClear() {
      this.shoruiList = []
      this.handleInit()
    },
    handleInit() {
      this.hassouStsList = [{ key: '01', value: '郵送' }, { key: '06', value: '営業店郵送' }]
      this.backHassou = this.hassouStsList[0]?.key
      this.activeBtn = true
      this.inputForm = new IfaDocRequestInputFormModel()
      this.isExecBtn = false
      this.confirmData = []
      this.inputData = {}
      this.paperMasterInfo = {}
      this.inputForm.hassouSts = this.hassouStsList[0]?.key
      this.inputForm.bunruiCd = this.backupBunruiCd
      this.inputForm.shoruiCd = this.backupShoruiCd
      this.busuuIsDisabled = true
      this.isShoruiCdJ105 = false
      this.$refs['inputForm']?.clearValidate()
      this.yuusouNumberIsVisible = false
      this.yuusouNumberBtnIsVisible = false
      this.addressSerachIsVisible = false
      this.yuusouNumberBtnIsDisabled = true
      this.addressIsVisible = false
      this.address2IsVisible = false
      this.address3IsVisible = false
      this.address4IsVisible = false
      this.addressLengthCheck = false
      this.naiyouIsVisible = false
      this.bikouIsVisible = false
      this.option1IsVisible = false
      this.option2IsVisible = false
      this.option3IsVisible = false
      this.txt1IsVisible = false
      this.txt2IsVisible = false
      this.txt3IsVisible = false
      this.txt4IsVisible = false
      this.txt5IsVisible = false
      this.tyuiKakiIsVisible = false
      this.commentIsVisible = false
      this.busuuIsRequired = false
      this.yuusouNumberIsRequired = false
      this.naiyouIsRequired = false
      this.bikouIsRequired = false
      this.cityIsRequired = false
      this.streetIsRequired = false
      this.option1IsRequired = false
      this.option1IsDisabled = false
      this.option2IsRequired = false
      this.option3IsRequired = false
      this.txt1IsRequired = false
      this.txt2IsRequired = false
      this.txt3IsRequired = false
      this.txt4IsRequired = false
      this.txt5IsRequired = false
      if (this.loading) {
        this.loading.close()
      }
    },
    // クリア
    handleClear() {
      this.backupBunruiCd = null
      this.backupShoruiCd = null
      this.shoruiList = []
      this.handleInit()
    },
    // ダイアログを閉じる
    handleCloseModal() {
      this.dialogDetailVisible = false
      this.dialogExecuteVisible = false
      this.dialogFinish = false
      this.dialogCancelVisible = false
      this.dialogCancelFinish = false
      this.brandSerachIsVisible = false
      this.addressSerachIsVisible = false
    },
    openBrandSearch() {
      this.$refs['ifaFundBrandSearch'].clear()
      this.brandSerachIsVisible = true
    },
    openAddressSearch() {
      this.$refs['inputForm'].validateField('yuusouNumber', (checkResult) => {
        if (checkResult) {
          if (this.inputForm.yuusouNumber && this.inputForm.yuusouNumber.length === 7) {
            this.addressData.yuusouNumber = this.inputForm.yuusouNumber
            this.addressSerachIsVisible = true
          }
        }
      })
    },
    handleBrandResult(result) {
      this.$refs['inputForm']?.clearValidate('fundNricode4')
      this.$refs['inputForm']?.clearValidate('fundNricode3')
      this.inputForm.fundNricode4 = result.fundKaisu
      this.inputForm.fundNricode3 = result.fundGo.padStart(3, ' ')
      this.inputForm.fundName = result.fundName
      // 協会コード
      this.inputForm.fundCode = result.fundCode
      // 購入可否判定区分
      this.inputForm.fundMdBuyKubun = result.fundMdBuyKubun
      if (this.inputForm.fundNricode4 && this.inputForm.fundNricode3) {
        this.inputData.meigaraCd = this.inputForm.fundNricode4 + '.' + this.inputForm.fundNricode3
      }
    },
    clearAddrLengthMsg() {
      const adrContent = this.inputForm.address02 + this.inputForm.address03 + this.inputForm.address04
      if (this.addressLengthCheck && !ifaUtils.isByteLengthExceeded(adrContent, 100)) {
        const addr02 = this.$refs?.docAddress02?.$refs?.address02?.validateMessage
        if (addr02 && addr02.includes('都道府県・市区町村・丁目、番地、建物名・部屋番号の合計入力量が100バイトとなるように入力してください。')) {
          this.$refs['inputForm']?.clearValidate('address02')
        }
        const addr03 = this.$refs?.docAddress03?.$refs?.address03?.validateMessage
        if (addr03 && addr03.includes('都道府県・市区町村・丁目、番地、建物名・部屋番号の合計入力量が100バイトとなるように入力してください。')) {
          this.$refs['inputForm']?.clearValidate('address03')
        }
        const addr04 = this.$refs?.docAddress04?.$refs?.address04?.validateMessage
        if (addr04 && addr04.includes('都道府県・市区町村・丁目、番地、建物名・部屋番号の合計入力量が100バイトとなるように入力してください。')) {
          this.$refs['inputForm']?.clearValidate('address04')
        }
      }
    },
    handleAddressResult(result) {
      this.inputForm.address02 = result?.address
      this.$refs['inputForm']?.clearValidate('address02')
      this.clearAddrLengthMsg()
      this.$refs['inputForm'].validateField('address02')
    },
    busuuCheck() {
      this.busuuIsDisabled = true
      this.busuuIsRequired = false
      if (this.paperMasterInfo.busuuShiteiKahi === '1') {
        this.busuuIsDisabled = false
        this.busuuIsRequired = true
        this.inputForm.busuu = ''
      }
    },
    busuuBMCheck() {
      this.busuuIsDisabled = true
      this.busuuIsRequired = false
      if (this.paperMasterInfo.busuuShiteiKahi === '1') {
        this.busuuIsDisabled = false
        this.busuuIsRequired = true
        if (this.backHassou === 'BM') {
          this.inputForm.busuu = ''
        }
      }
    },
    yuusouNumberChange() {
      if (this.inputForm.yuusouNumber?.length === 7) {
        this.$refs?.inputForm.validateField('yuusouNumber', (checkResult) => {
          if (checkResult) {
            this.yuusouNumberBtnIsDisabled = false
          }
        })
      } else {
        this.yuusouNumberBtnIsDisabled = true
      }
    },
    yuusouNumberCheck() {
      this.yuusouNumberIsVisible = false
      this.yuusouNumberIsRequired = false
      this.yuusouNumberBtnIsVisible = false
      const aryAdr = ['1', '2', '11', '12', '13', '14']
      const aryAdrBtn = ['1', '2']
      const aryAdrYuusou = ['11', '12', '13', '14']
      if (this.paperMasterInfo.txt1 && aryAdr.includes(this.paperMasterInfo.addressT1)) {
        this.yuusouNumberIsVisible = true
        if (this.paperMasterInfo.hissuT1 === '1' && aryAdrYuusou.includes(this.paperMasterInfo.addressT1)) {
          this.yuusouNumberIsRequired = true
        }
        if (aryAdrBtn.includes(this.paperMasterInfo.addressT1)) {
          this.yuusouNumberBtnIsVisible = true
        }
      }
      if (this.paperMasterInfo.txt2 && aryAdr.includes(this.paperMasterInfo.addressT2)) {
        this.yuusouNumberIsVisible = true
        if (this.paperMasterInfo.hissuT2 === '1' && aryAdrYuusou.includes(this.paperMasterInfo.addressT2)) {
          this.yuusouNumberIsRequired = true
        }
        if (aryAdrBtn.includes(this.paperMasterInfo.addressT2)) {
          this.yuusouNumberBtnIsVisible = true
        }
      }
      if (this.paperMasterInfo.txt3 && aryAdr.includes(this.paperMasterInfo.addressT3)) {
        this.yuusouNumberIsVisible = true
        if (this.paperMasterInfo.hissuT3 === '1' && aryAdrYuusou.includes(this.paperMasterInfo.addressT3)) {
          this.yuusouNumberIsRequired = true
        }
        if (aryAdrBtn.includes(this.paperMasterInfo.addressT3)) {
          this.yuusouNumberBtnIsVisible = true
        }
      }
      if (this.paperMasterInfo.txt4 && aryAdr.includes(this.paperMasterInfo.addressT4)) {
        this.yuusouNumberIsVisible = true
        if (this.paperMasterInfo.hissuT4 === '1' && aryAdrYuusou.includes(this.paperMasterInfo.addressT4)) {
          this.yuusouNumberIsRequired = true
        }
        if (aryAdrBtn.includes(this.paperMasterInfo.addressT4)) {
          this.yuusouNumberBtnIsVisible = true
        }
      }
      if (this.paperMasterInfo.txt5 && aryAdr.includes(this.paperMasterInfo.addressT5)) {
        this.yuusouNumberIsVisible = true
        if (this.paperMasterInfo.hissuT5 === '1' && aryAdrYuusou.includes(this.paperMasterInfo.addressT5)) {
          this.yuusouNumberIsRequired = true
        }
        if (aryAdrBtn.includes(this.paperMasterInfo.addressT5)) {
          this.yuusouNumberBtnIsVisible = true
        }
      }
    },
    hassouStsCheck() {
      if (this.paperMasterInfo.hassouSts === '1') {
        this.hassouStsList = [{ key: '01', value: '郵送' }, { key: '06', value: '営業店郵送' }]
      } else if (this.paperMasterInfo.hassouSts === '2') {
        this.hassouStsList = [{ key: '01', value: '郵送' }]
      } else if (this.paperMasterInfo.hassouSts === '3') {
        this.hassouStsList = [{ key: '06', value: '営業店郵送' }]
      } else {
        this.hassouStsList = [{ key: '01', value: '郵送' }, { key: '06', value: '営業店郵送' }]
      }
      if (this.paperMasterInfo.bmKoufuShubetsu === '1' || this.paperMasterInfo.bmKoufuShubetsu === '2') {
        this.hassouStsList.push({ key: 'BM', value: 'BM交付' })
      }
      this.inputForm.hassouSts = this.hassouStsList[0]?.key
      this.backHassou = this.hassouStsList[0]?.key
    },
    naiyouCheck() {
      this.naiyouIsVisible = false
      this.naiyouIsRequired = false
      if (this.paperMasterInfo.naiyou) {
        this.naiyouIsVisible = true
        if (this.paperMasterInfo.hissuNaiyou === '1') {
          this.naiyouIsRequired = true
        }
      }
      if (this.paperMasterInfo.sizeNaiyou === '0') {
        this.naiyouLength = 9
        this.naiyouWitdh = 'width: 180px;'
      } else if (this.paperMasterInfo.sizeNaiyou === '1') {
        this.naiyouLength = 30
        this.naiyouWitdh = 'width: 510px'
      } else if (this.paperMasterInfo.sizeNaiyou === '2') {
        this.naiyouLength = 50
        this.naiyouWitdh = 'width: 830px'
      } else if (this.paperMasterInfo.sizeNaiyou === '3') {
        this.naiyouLength = 100
        this.naiyouWitdh = 'width: 100%'
      } else if (this.paperMasterInfo.sizeNaiyou === '4') {
        this.naiyouLength = 200
        this.naiyouWitdh = 'width: 100%'
      } else {
        this.naiyouLength = 200
        this.naiyouWitdh = 'width: 100%'
      }
    },
    bikouCheck() {
      this.bikouIsVisible = false
      this.bikouIsRequired = false
      if (this.paperMasterInfo.bikou) {
        this.bikouIsVisible = true
        if (this.paperMasterInfo.hissuBikou === '1') {
          this.bikouIsRequired = true
        }
      }
      if (this.paperMasterInfo.sizeBikou === '0') {
        this.bikouLength = 9
        this.bikouWitdh = 'width: 180px;'
      } else if (this.paperMasterInfo.sizeBikou === '1') {
        this.bikouLength = 30
        this.bikouWitdh = 'width: 510px'
      } else if (this.paperMasterInfo.sizeBikou === '2') {
        this.bikouLength = 50
        this.bikouWitdh = 'width: 830px'
      } else if (this.paperMasterInfo.sizeBikou === '3') {
        this.bikouLength = 100
        this.bikouWitdh = 'width: 100%'
      } else if (this.paperMasterInfo.sizeBikou === '4') {
        this.bikouLength = 200
        this.bikouWitdh = 'width: 100%'
      } else {
        this.bikouLength = 200
        this.bikouWitdh = 'width: 100%'
      }
    },
    getTextContent(val) {
      let txtContent = ''
      if (this.yuusouNumberIsVisible || this.addressIsVisible) {
        if (val === '1') {
          txtContent = this.inputForm.address02 + this.inputForm.address03 + this.inputForm.address04
        } else if (val === '2') {
          txtContent = this.inputForm.address02
        } else if (val === '3') {
          txtContent = this.inputForm.address03
        } else if (val === '4') {
          txtContent = this.inputForm.address04
        } else if (val === '11') {
          txtContent = this.inputForm.yuusouNumber
        } else if (val === '12') {
          if (this.inputForm.yuusouNumber && this.inputForm.yuusouNumber.length === 7) {
            txtContent = this.inputForm.yuusouNumber.slice(0, 3) + '-' + this.inputForm.yuusouNumber.slice(3, 7)
          }
        } else if (val === '13') {
          if (this.inputForm.yuusouNumber && this.inputForm.yuusouNumber.length === 7) {
            txtContent = this.inputForm.yuusouNumber.slice(0, 3)
          }
        } else if (val === '14') {
          if (this.inputForm.yuusouNumber && this.inputForm.yuusouNumber.length === 7) {
            txtContent = this.inputForm.yuusouNumber.slice(3, 7)
          }
        }
        return txtContent
      }
    },
    getTextContent2(val) {
      let txtContent = ''
      if (this.address3IsVisible && val === '3') {
        txtContent = this.inputForm.address03
      }
      if (this.address4IsVisible && val === '4') {
        txtContent = this.inputForm.address04
      }
      return txtContent
    },
    addressCheck() {
      this.addressIsVisible = false
      this.address2IsVisible = false
      this.address3IsVisible = false
      this.address4IsVisible = false
      this.addressLengthCheck = false
      const aryAdr = ['1', '2', '3', '4']
      if (this.paperMasterInfo.txt1 && this.paperMasterInfo.addressT1) {
        if (aryAdr.includes(this.paperMasterInfo.addressT1)) {
          this.setAddressVisable(this.paperMasterInfo.addressT1)
        }
      }
      if (this.paperMasterInfo.txt2 && this.paperMasterInfo.addressT2) {
        if (aryAdr.includes(this.paperMasterInfo.addressT2)) {
          this.setAddressVisable(this.paperMasterInfo.addressT2)
        }
      }
      if (this.paperMasterInfo.txt3 && this.paperMasterInfo.addressT3) {
        if (aryAdr.includes(this.paperMasterInfo.addressT3)) {
          this.setAddressVisable(this.paperMasterInfo.addressT3)
        }
      }
      if (this.paperMasterInfo.txt4 && this.paperMasterInfo.addressT4) {
        if (aryAdr.includes(this.paperMasterInfo.addressT4)) {
          this.setAddressVisable(this.paperMasterInfo.addressT4)
        }
      }
      if (this.paperMasterInfo.txt5 && this.paperMasterInfo.addressT5) {
        if (aryAdr.includes(this.paperMasterInfo.addressT5)) {
          this.setAddressVisable(this.paperMasterInfo.addressT5)
        }
      }
    },
    setAddressVisable(addressTn) {
      if (addressTn === '1') {
        this.addressIsVisible = true
        this.addressLengthCheck = true
        this.address2IsVisible = true
        this.address3IsVisible = true
        this.address4IsVisible = true
      } else {
        if (addressTn === '2') {
          this.address2IsVisible = true
        } else if (addressTn === '3') {
          this.address3IsVisible = true
        } else if (addressTn === '4') {
          this.address4IsVisible = true
        }
      }
    },
    cityCheck() {
      this.cityIsRequired = false
      const aryAdr = ['1', '2']
      if (this.paperMasterInfo.txt1 && this.paperMasterInfo.addressT1) {
        if (aryAdr.includes(this.paperMasterInfo.addressT1) && this.paperMasterInfo.hissuT1 === '1') {
          this.cityIsRequired = true
        }
      }
      if (this.paperMasterInfo.txt2 && this.paperMasterInfo.addressT2) {
        if (aryAdr.includes(this.paperMasterInfo.addressT2) && this.paperMasterInfo.hissuT2 === '1') {
          this.cityIsRequired = true
        }
      }
      if (this.paperMasterInfo.txt3 && this.paperMasterInfo.addressT3) {
        if (aryAdr.includes(this.paperMasterInfo.addressT3) && this.paperMasterInfo.hissuT3 === '1') {
          this.cityIsRequired = true
        }
      }
      if (this.paperMasterInfo.txt4 && this.paperMasterInfo.addressT4) {
        if (aryAdr.includes(this.paperMasterInfo.addressT4) && this.paperMasterInfo.hissuT4 === '1') {
          this.cityIsRequired = true
        }
      }
      if (this.paperMasterInfo.txt5 && this.paperMasterInfo.addressT5) {
        if (aryAdr.includes(this.paperMasterInfo.addressT5) && this.paperMasterInfo.hissuT5 === '1') {
          this.cityIsRequired = true
        }
      }
    },
    streetCheck() {
      this.streetIsRequired = false
      const aryAdr = ['1', '3']
      if (this.paperMasterInfo.txt1 && this.paperMasterInfo.addressT1) {
        if (aryAdr.includes(this.paperMasterInfo.addressT1) && this.paperMasterInfo.hissuT1 === '1') {
          this.streetIsRequired = true
        }
      }
      if (this.paperMasterInfo.txt2 && this.paperMasterInfo.addressT2) {
        if (aryAdr.includes(this.paperMasterInfo.addressT2) && this.paperMasterInfo.hissuT2 === '1') {
          this.streetIsRequired = true
        }
      }
      if (this.paperMasterInfo.txt3 && this.paperMasterInfo.addressT3) {
        if (aryAdr.includes(this.paperMasterInfo.addressT3) && this.paperMasterInfo.hissuT3 === '1') {
          this.streetIsRequired = true
        }
      }
      if (this.paperMasterInfo.txt4 && this.paperMasterInfo.addressT4) {
        if (aryAdr.includes(this.paperMasterInfo.addressT4) && this.paperMasterInfo.hissuT4 === '1') {
          this.streetIsRequired = true
        }
      }
      if (this.paperMasterInfo.txt5 && this.paperMasterInfo.addressT5) {
        if (aryAdr.includes(this.paperMasterInfo.addressT5) && this.paperMasterInfo.hissuT5 === '1') {
          this.streetIsRequired = true
        }
      }
    },
    option1Check() {
      // オプション1
      this.option1IsVisible = false
      this.option1IsRequired = false
      this.option1IsDisabled = false
      this.option1List = []
      if (this.paperMasterInfo.option1) {
        this.option1IsVisible = true
        if (this.paperMasterInfo.hissuO1 === '1') {
          this.option1IsRequired = true
        }
        if (this.paperMasterInfo.sentaku11) {
          this.option1List.push({ key: '11', value: this.paperMasterInfo.sentaku11 })
        }
        if (this.paperMasterInfo.sentaku12) {
          this.option1List.push({ key: '12', value: this.paperMasterInfo.sentaku12 })
        }
        if (this.paperMasterInfo.sentaku13) {
          this.option1List.push({ key: '13', value: this.paperMasterInfo.sentaku13 })
        }
        if (this.paperMasterInfo.sentaku14) {
          this.option1List.push({ key: '14', value: this.paperMasterInfo.sentaku14 })
        }
        if (this.paperMasterInfo.sentaku15) {
          this.option1List.push({ key: '15', value: this.paperMasterInfo.sentaku15 })
        }
        // 書類コード="J105"の書類を選択している場合は、この項目が選択されている状態で表示する。
        if (this.isShoruiCdJ105 && this.option1List.length >= 2) {
          this.inputForm.sentakuMei1 = this.option1List[0].key
        }
        if (this.isShoruiCdJ105 && this.option1List.length >= 2 && this.inputForm.hassouSts === 'BM') {
          this.option1IsDisabled = true
        }
        this.inputForm.option1 = this.paperMasterInfo.option1
      }
    },
    optionCheck() {
      // オプション1
      this.option1Check()
      // オプション2
      this.option2IsVisible = false
      this.option2IsRequired = false
      this.option2List = []
      if (this.paperMasterInfo.option2) {
        this.option2IsVisible = true
        if (this.paperMasterInfo.hissuO2 === '1') {
          this.option2IsRequired = true
        }
        if (this.paperMasterInfo.sentaku21) {
          this.option2List.push({ key: '21', value: this.paperMasterInfo.sentaku21 })
        }
        if (this.paperMasterInfo.sentaku22) {
          this.option2List.push({ key: '22', value: this.paperMasterInfo.sentaku22 })
        }
        if (this.paperMasterInfo.sentaku23) {
          this.option2List.push({ key: '23', value: this.paperMasterInfo.sentaku23 })
        }
        if (this.paperMasterInfo.sentaku24) {
          this.option2List.push({ key: '24', value: this.paperMasterInfo.sentaku24 })
        }
        if (this.paperMasterInfo.sentaku25) {
          this.option2List.push({ key: '25', value: this.paperMasterInfo.sentaku25 })
        }
        this.inputForm.option2 = this.paperMasterInfo.option2
      }
      // オプション3
      this.option3IsVisible = false
      this.option3IsRequired = false
      this.option3List = []
      if (this.paperMasterInfo.option3) {
        this.option3IsVisible = true
        if (this.paperMasterInfo.hissuO3 === '1') {
          this.option3IsRequired = true
        }
        if (this.paperMasterInfo.sentaku31) {
          this.option3List.push({ key: '31', value: this.paperMasterInfo.sentaku31 })
        }
        if (this.paperMasterInfo.sentaku32) {
          this.option3List.push({ key: '32', value: this.paperMasterInfo.sentaku32 })
        }
        if (this.paperMasterInfo.sentaku33) {
          this.option3List.push({ key: '33', value: this.paperMasterInfo.sentaku33 })
        }
        if (this.paperMasterInfo.sentaku34) {
          this.option3List.push({ key: '34', value: this.paperMasterInfo.sentaku34 })
        }
        if (this.paperMasterInfo.sentaku35) {
          this.option3List.push({ key: '35', value: this.paperMasterInfo.sentaku35 })
        }
        this.inputForm.option3 = this.paperMasterInfo.option3
      }
    },
    txtCheck() {
      // テキスト1
      this.txt1IsVisible = false
      this.txt1IsRequired = false
      if (!this.paperMasterInfo.addressT1) {
        if (this.paperMasterInfo.txt1) {
          this.txt1IsVisible = true
          if (this.paperMasterInfo.hissuT1 === '1') {
            this.txt1IsRequired = true
          }
          if (this.paperMasterInfo.sizeT1 === '0') {
            this.txt1Length = 30
            this.txt1Witdh = 'width: 510px'
          } else if (this.paperMasterInfo.sizeT1 === '1') {
            this.txt1Length = 50
            this.txt1Witdh = 'width: 830px'
          } else if (this.paperMasterInfo.sizeT1 === '2') {
            this.txt1Length = 100
            this.txt1Witdh = 'width: 100%'
          } else {
            this.txt1Length = 100
            this.txt1Witdh = 'width: 100%'
          }
          this.inputForm.txt1 = this.paperMasterInfo.txt1
        }
      }
      // テキスト2
      this.txt2IsVisible = false
      this.txt2IsRequired = false
      if (!this.paperMasterInfo.addressT2) {
        if (this.paperMasterInfo.txt2) {
          this.txt2IsVisible = true
          if (this.paperMasterInfo.hissuT2 === '1') {
            this.txt2IsRequired = true
          }
          if (this.paperMasterInfo.sizeT2 === '0') {
            this.txt2Length = 30
            this.txt2Witdh = 'width: 510px'
          } else if (this.paperMasterInfo.sizeT2 === '1') {
            this.txt2Length = 50
            this.txt2Witdh = 'width: 830px'
          } else if (this.paperMasterInfo.sizeT2 === '2') {
            this.txt2Length = 100
            this.txt2Witdh = 'width: 100%'
          } else {
            this.txt2Length = 100
            this.txt2Witdh = 'width: 100%'
          }
          this.inputForm.txt2 = this.paperMasterInfo.txt2
        }
      }
      // テキスト3
      this.txt3IsVisible = false
      this.txt3IsRequired = false
      if (!this.paperMasterInfo.addressT3) {
        if (this.paperMasterInfo.txt3) {
          this.txt3IsVisible = true
          if (this.paperMasterInfo.hissuT3 === '1') {
            this.txt3IsRequired = true
          }
          if (this.paperMasterInfo.sizeT3 === '0') {
            this.txt3Length = 30
            this.txt3Witdh = 'width: 510px'
          } else if (this.paperMasterInfo.sizeT3 === '1') {
            this.txt3Length = 50
            this.txt3Witdh = 'width: 830px'
          } else if (this.paperMasterInfo.sizeT3 === '2') {
            this.txt3Length = 100
            this.txt3Witdh = 'width: 100%'
          } else {
            this.txt3Length = 100
            this.txt3Witdh = 'width: 100%'
          }
          this.inputForm.txt3 = this.paperMasterInfo.txt3
        }
      }
      // テキスト4
      this.txt4IsVisible = false
      this.txt4IsRequired = false
      if (!this.paperMasterInfo.addressT4) {
        if (this.paperMasterInfo.txt4) {
          this.txt4IsVisible = true
          if (this.paperMasterInfo.hissuT4 === '1') {
            this.txt4IsRequired = true
          }
          if (this.paperMasterInfo.sizeT4 === '0') {
            this.txt4Length = 30
            this.txt4Witdh = 'width: 510px'
          } else if (this.paperMasterInfo.sizeT4 === '1') {
            this.txt4Length = 50
            this.txt4Witdh = 'width: 830px'
          } else if (this.paperMasterInfo.sizeT4 === '2') {
            this.txt4Length = 100
            this.txt4Witdh = 'width: 100%'
          } else {
            this.txt4Length = 100
            this.txt4Witdh = 'width: 100%'
          }
          this.inputForm.txt4 = this.paperMasterInfo.txt4
        }
      }
      // テキスト5
      this.txt5IsVisible = false
      this.txt5IsRequired = false
      if (!this.paperMasterInfo.addressT5) {
        if (this.paperMasterInfo.txt5) {
          this.txt5IsVisible = true
          if (this.paperMasterInfo.hissuT5 === '1') {
            this.txt5IsRequired = true
          }
          if (this.paperMasterInfo.sizeT5 === '0') {
            this.txt5Length = 30
            this.txt5Witdh = 'width: 510px'
          } else if (this.paperMasterInfo.sizeT5 === '1') {
            this.txt5Length = 50
            this.txt5Witdh = 'width: 830px'
          } else if (this.paperMasterInfo.sizeT5 === '2') {
            this.txt5Length = 100
            this.txt5Witdh = 'width: 100%'
          } else {
            this.txt5Length = 100
            this.txt5Witdh = 'width: 100%'
          }
          this.inputForm.txt5 = this.paperMasterInfo.txt5
        }
      }
    },
    tyuiKakiCheck() {
      this.tyuiKakiIsVisible = false
      if (this.paperMasterInfo.tyuiKaki) {
        this.tyuiKakiIsVisible = true
      }
    },
    commentCheck() {
      this.commentIsVisible = false
      if (this.paperMasterInfo.comment) {
        this.commentIsVisible = true
      }
    }
  }
}

const objDocRequest = {
  width: 0,
  height: 0,
  title: '書類請求一覧',
  flexHeight: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false,
  postRenderInterval: 100
}
objDocRequest.colModel = [
  { title: '書類請求NO', minWidth: 100, dataType: 'string', dataIndx: 'shoruiSeikyuuNo', editable: false, halign: 'center', align: 'left', hidden: true
  },
  { title: '枝番', minWidth: 100, dataType: 'string', dataIndx: 'edaban', editable: false, halign: 'center', align: 'left', hidden: true
  },
  { title: 'BM交付番号', minWidth: 100, dataType: 'string', dataIndx: 'bmDeliveryNo', editable: false, halign: 'center', align: 'left', hidden: true
  },
  { title: '書類請求日時', width: 120, dataType: 'string', dataIndx: 'shoruiSeikyuuNichiji', editable: false, halign: 'center', align: 'left',
    render: function(ui) { return ui.rowData.shoruiSeikyuuNichiji ? getFormattedDateTimeValue(ui.rowData.shoruiSeikyuuNichiji, 'datetime12') : '-' }
  },
  { title: '書類分類', width: 300, dataType: 'string', dataIndx: 'shoruiBunruiMei', editable: false, halign: 'center', align: 'left'
  },
  { title: '書類名', width: 500, dataType: 'string', dataIndx: 'shoruimei', editable: false, halign: 'center', align: 'left'
  },
  { title: '部数', width: 50, dataType: 'string', dataIndx: 'busuu', editable: false, halign: 'center', align: 'right'
  },
  { title: '取扱者', width: 200, dataType: 'string', dataIndx: 'userNm', editable: false, halign: 'center', align: 'left'
  },
  { title: '詳細', width: 60, dataType: 'string', dataIndx: 'detailId', editable: false, halign: 'center', align: 'center',
    render: function(ui) {
      const v = JSON.stringify(ui.rowData)
      if (ui.rowData.bmDeliveryNo) {
        return "<button type='button' class='el-button el-button--small el-button--primary ifa-button primary-class' onclick='const btn = document.getElementById(\"doc-detail-button-bm\"); btn.value = \"" + escape(v) + "\"; btn.click()'><span class='__adjust_button_text'>詳細</span></button>"
      } else {
        return "<button type='button' class='el-button el-button--small el-button--primary ifa-button primary-class' onclick='const btn = document.getElementById(\"doc-detail-button\"); btn.value = \"" + escape(v) + "\"; btn.click()'><span class='__adjust_button_text'>詳細</span></button>"
      }
    }
  },
  { title: '処理内容', width: 80, dataType: 'string', dataIndx: 'torikeshiKbn', editable: false, halign: 'center', align: 'center',
    render: function(ui) {
      if (ui.rowData.torikeshiKbn === '0') {
        const v = JSON.stringify(ui.rowData)
        if (ui.rowData.bmDeliveryNo) {
          return "<button type='button' class='el-button el-button--small el-button--primary ifa-button primary-class' onclick='const btn = document.getElementById(\"doc-cancel-button-bm\"); btn.value = \"" + escape(v) + "\"; btn.click()'><span class='__adjust_button_text'>取消</span></button>"
        } else {
          return "<button type='button' class='el-button el-button--small el-button--primary ifa-button primary-class' onclick='const btn = document.getElementById(\"doc-cancel-button\"); btn.value = \"" + escape(v) + "\"; btn.click()'><span class='__adjust_button_text'>取消</span></button>"
        }
      } else if (ui.rowData.torikeshiKbn === '1') {
        return '取消済'
      } else {
        return '取消不可'
      }
    }
  }
]
objDocRequest.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}

</script>

<style lang="scss" scoped>
.fund-brand-class {
  display: inline-flex;
  margin-left: 7px;
  align-items: flex-start;
}
.fund-required {
  color: #f56c6c;
  margin-right: 4px;
  font-size: 18px;
  margin-top: 20px;
}
.fund-label {
  font-size: 14px;
  font-weight: bold;
  margin-top: 20px;
  width: 196px;
}
.fund-connect {
  margin:0px 10px 0px 10px;
  font-size: 18px;
  margin-top: 15px;
}
.fund-name {
  font-size: 14px;
  font-weight: bold;
  margin-top: 20px;
  margin-left: 18px;
}
:deep(.fund-brand-class .el-form-item__label) {
  display: none !important;
}
:deep(.grid_table_class .pq-grid-cont-outer .pq-grid-row) {
  height: 40px;
}
.caption_card {
  margin: 0.5rem;
}
.doc_req_card {
  margin-bottom: 0.5rem;
  padding: 0 1rem 0 1rem;
}
.card_row {
  padding: 0.5rem 0rem;
}
.clear_class {
  text-align: right;
}
.row_top {
  margin-top: 10px;
}
.button_bottom {
  margin-bottom: 1rem;
}
.input_card {
  background-color: #fef0f0;
  padding: 0 2rem 0 1rem;
}
.__adjust_button_text {
  height: 24px;
  line-height: 16px !important;
  white-space: nowrap;
}
:deep(.doc-common-label) {
  word-break: break-all;
}
:deep(.doc-common-label) .el-form-item__label {
  line-height: initial !important;
}
:deep(.doc-common-label) .el-form-item__error {
  white-space: nowrap;
}
</style>
