<template>
  <el-dialog
    id="editable-confirm"
    v-model="isVisible"
    :title="editableConfirmTitle"
    destroy-on-close
    center
    :width="1200"
    :show-close="false"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    style="background-color: #fef0f0;"
  >
    <div class="wrapper">
      <el-row
        v-if="status === 'confirm'"
        style="display: flex; align-items: center; justify-content: flex-end;"
      >
        <ifa-button
          id="btnBack"
          text="戻る"
          color="secondary"
          style="padding-right: 0;"
          action-type="originalAction"
          @app-action-handler="handleCancel"
        ></ifa-button>
      </el-row>

      <ifa-message-area
        :key="messageKey"
        :main-messages="messages.mains"
        :error-messages="messages.errors"
        :warning-messages="messages.warns"
      ></ifa-message-area>

      <div>
        <el-row style="font-weight: bold;">
          <span>{{ info?.accountNumber ?? '-' }}</span>
        </el-row>

        <el-row
          class="_bold_black_l"
          style="padding-top: 0.5rem; font-size: 20px;"
        >
          <div style="display: flex; column-gap: 2px">
            <el-icon>
              <component
                :is="info?.corporationKbn === '1'
                  ? 'OfficeBuilding'
                  : 'Avatar'"
              ></component>
            </el-icon>
            <div>{{ info?.customerName ?? '-' }}</div>
            <div style="padding-left: 10px;">
              <ifa-notice-info
                :notice-info-level="customerInfo.noticeInfoLevel"
                :customer-code="customerInfo.customerCode"
                :buten-code="customerInfo.butenCode"
                :account-number="customerInfo.accountNumber"
              ></ifa-notice-info>
            </div>
          </div>
        </el-row>
      </div>

      <el-card
        class="box-card"
        style="font-size: 16px;"
      >
        <el-row
          class="_bold_black_m"
          style="padding-top: 0.5rem; padding-left: 1rem;"
        >
          <el-col :span="8">
            {{ `ご設定内容${status === 'confirm' ? '（復唱項目）' : ''}` }}
          </el-col>
          <el-col
            :span="7"
            style="padding-left: 6px;"
          >
            変更前
          </el-col>
          <el-col :span="2"></el-col>
          <el-col :span="7">変更後</el-col>
        </el-row>
        <hr>

        <div style="display: flex; flex-direction: column; row-gap: 10px;">
          <div style="padding: 0 0.5rem;">
            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                銘柄名
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                {{ info?.brandName ?? '-' }}
              </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="7"></el-col>
            </el-row>

            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                協会コード
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                {{ info?.fundCode ?? '-' }}
              </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="7"></el-col>
            </el-row>

            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                銘柄コード
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                {{ info?.brandCode ?? '-' }}
              </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="7"></el-col>
            </el-row>

            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                決済方法
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                {{ $_getCodeValue('FUND_RESERVE_PAYMENT_METHOD', 1, beforeInfo?.paymentMethod) }}
              </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="7"></el-col>
            </el-row>

            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                預り区分
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                {{ info?.accountTypeName ?? '-' }}
              </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="7"></el-col>
            </el-row>

            <template v-if="info?.reserveOrderUnit === '0'">
              <el-row
                v-if="info?.accountType === 'H' || info?.accountType === 'I'"
                class="bordered-bottom-solid"
              >
                <el-col
                  :span="8"
                  class="_bold_black_m"
                >
                  NISA枠ぎりぎり注文
                </el-col>
                <el-col
                  :span="7"
                  style="padding-left: 10px;"
                >
                  {{ $_getCodeValue('FUND_RESERVE_NISA_BARELY_BUYING_KBN', 2, beforeInfo?.nisaBarelyBuyingType) }}
                </el-col>
                <el-col
                  :span="2"
                  style="padding-left: 6px;"
                >
                  {{ beforeInfo?.nisaBarelyBuyingType !== afterInfo?.nisaBarelyBuyingType
                    ? changeArrow
                    : '' }}
                </el-col>
                <el-col
                  :span="7"
                  style="padding-left: 6px;"
                >
                  {{ $_getCodeValue('FUND_RESERVE_NISA_BARELY_BUYING_KBN', 2, afterInfo?.nisaBarelyBuyingType) }}
                </el-col>
              </el-row>

              <el-row
                v-if="info?.accountType === 'H'"
                class="bordered-bottom-solid"
              >
                <el-col
                  :span="8"
                  class="_bold_black_m"
                >
                  課税枠シフト注文
                </el-col>
                <el-col
                  :span="7"
                  style="padding-left: 10px"
                >
                  {{ $_getCodeValue('FUND_RESERVE_TAX_SHIFT_KBN', 2, beforeInfo?.taxShiftType) }}
                </el-col>
                <el-col
                  :span="2"
                  style="padding-left: 6px"
                >
                  {{ beforeInfo?.taxShiftType !== afterInfo?.taxShiftType
                    ? changeArrow
                    : '' }}
                </el-col>
                <el-col
                  :span="7"
                  style="padding-left: 6px"
                >
                  {{ $_getCodeValue('FUND_RESERVE_TAX_SHIFT_KBN', 2, afterInfo?.taxShiftType) }}
                </el-col>
              </el-row>
            </template>
          </div>

          <div style="padding: 0 0.5rem;">
            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                コース
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                {{ courseTypeText(beforeInfo?.courseType) }}
              </el-col>
              <el-col
                :span="2"
                style="padding-left: 6px;"
              >
                {{ beforeInfo?.courseType !== afterInfo?.courseType
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 6px;"
              >
                {{ courseTypeText(afterInfo?.courseType) }}
              </el-col>
            </el-row>

            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                金額
              </el-col>
              <el-col
                :span="7"
                style="word-break: break-all;"
              >
                {{ `${$_addComma(beforeInfo?.settingAmount)} 円` }}
              </el-col>
              <el-col :span="2">
                {{ beforeInfo?.settingAmount !== afterInfo?.settingAmount
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col
                :span="7"
                style="word-break: break-all;"
              >
                {{ `${$_addComma(afterInfo?.settingAmount)} 円` }}
              </el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                概算手数料（税込み）
              </el-col>
              <el-col
                :span="7"
                style="word-break: break-all;"
              >
                {{ `${$_addComma(beforeInfo?.estimateFundOrder)} 円` }}
              </el-col>
              <el-col :span="2">
                {{ beforeInfo?.estimateFundOrder !== afterInfo?.estimateFundOrder
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col
                :span="7"
                style="word-break: break-all;"
              >
                {{ `${$_addComma(afterInfo?.estimateFundOrder)} 円` }}
              </el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                設定日
              </el-col>
              <el-col
                :span="7"
                style="word-break: break-all;"
              >
                {{ settingDay(beforeInfo) }}
              </el-col>
              <el-col :span="2">
                {{ settingDay(beforeInfo) !== settingDay(afterInfo)
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col
                :span="7"
                style="word-break: break-all;"
              >
                {{ settingDay(afterInfo) }}
              </el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                1ヶ月あたりの設定金額
              </el-col>
              <el-col
                :span="7"
                style="word-break: break-all;"
              >
                {{ `${$_addComma(beforeInfo?.oneMonthSumAmount)} 円` }}
              </el-col>
              <el-col :span="2">
                {{ beforeInfo?.oneMonthSumAmount !== afterInfo?.oneMonthSumAmount
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col
                :span="7"
                style="word-break: break-all;"
              >
                {{ `${$_addComma(afterInfo?.oneMonthSumAmount)} 円` }}
              </el-col>
            </el-row>

            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                次の発注予定日
              </el-col>
              <el-col :span="7">
                {{ beforeInfo?.planDate ?? '-' }}
              </el-col>
              <el-col :span="2">
                {{ beforeInfo?.planDate !== afterInfo?.planDate
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col :span="7">
                {{ afterInfo?.planDate ?? '-' }}
              </el-col>
            </el-row>
          </div>

          <!-- 1: true; 2: false -->
          <div
            v-if="beforeInfo?.settingBonusFlag === '2' && afterInfo?.settingBonusFlag === '2'"
            style="padding: 0 0.5rem;"
          >
            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                ボーナス月コース
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                ボーナス月設定しない
              </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="7">ボーナス月設定しない</el-col>
            </el-row>
          </div>

          <div
            v-if="beforeInfo?.settingBonusFlag === '1' && afterInfo?.settingBonusFlag === '1'"
            style="padding: 0 0.5rem;"
          >
            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                ボーナス月コース
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                ボーナス月
              </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="7">ボーナス月</el-col>
            </el-row>

            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                金額
              </el-col>
              <el-col :span="7">
                {{ `${$_addComma(beforeInfo?.settingBonusAmount)} 円` }}
              </el-col>
              <el-col :span="2">
                {{ beforeInfo?.settingBonusAmount !== afterInfo?.settingBonusAmount
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col :span="7">
                {{ `${$_addComma(afterInfo?.settingBonusAmount)} 円` }}
              </el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                概算手数料（税込み）
              </el-col>
              <el-col :span="7">
                {{ `${$_addComma(beforeInfo?.estimateFundOrderBonus)} 円` }}
              </el-col>
              <el-col :span="2">
                {{ beforeInfo?.estimateFundOrderBonus !== afterInfo?.estimateFundOrderBonus
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col :span="7">
                {{ `${$_addComma(afterInfo?.estimateFundOrderBonus)} 円` }}
              </el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                設定日
              </el-col>
              <el-col :span="7">{{ settingDate(beforeInfo) }}</el-col>
              <el-col :span="2">
                {{ settingDate(beforeInfo) !== settingDate(afterInfo)
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col :span="7">{{ settingDate(afterInfo) }}</el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                次の発注予定日
              </el-col>
              <el-col :span="7">{{ beforeInfo?.bonusPlanDate }}</el-col>
              <el-col :span="2">
                {{ beforeInfo?.bonusPlanDate !== afterInfo?.bonusPlanDate
                  ? changeArrow
                  : '' }}
              </el-col>
              <el-col :span="7">{{ afterInfo?.bonusPlanDate }}</el-col>
            </el-row>
          </div>

          <div
            v-if="beforeInfo?.settingBonusFlag === '2' && afterInfo?.settingBonusFlag === '1'"
            style="padding: 0 0.5rem;"
          >
            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                ボーナス月コース
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                ボーナス月設定しない
              </el-col>
              <el-col
                :span="2"
                style="padding-left: 6px;"
              >
                {{ changeArrow }}
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 6px;"
              >
                ボーナス月
              </el-col>
            </el-row>

            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                金額
              </el-col>
              <el-col :span="7">-</el-col>
              <el-col :span="2">{{ changeArrow }}</el-col>
              <el-col :span="7">
                {{ `${$_addComma(afterInfo?.settingBonusAmount)} 円` }}
              </el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                概算手数料（税込み）
              </el-col>
              <el-col :span="7">-</el-col>
              <el-col :span="2">{{ changeArrow }}</el-col>
              <el-col :span="7">
                {{ `${$_addComma(afterInfo?.estimateFundOrderBonus)} 円` }}
              </el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                設定日
              </el-col>
              <el-col :span="7">-</el-col>
              <el-col :span="2">{{ changeArrow }}</el-col>
              <el-col :span="7">{{ settingDate(afterInfo) }}</el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                次の発注予定日
              </el-col>
              <el-col :span="7">-</el-col>
              <el-col :span="2">{{ changeArrow }}</el-col>
              <el-col :span="7">{{ afterInfo?.bonusPlanDate }}</el-col>
            </el-row>
          </div>

          <div
            v-if="beforeInfo?.settingBonusFlag === '1' && afterInfo?.settingBonusFlag === '2'"
            style="padding: 0 0.5rem;"
          >
            <el-row class="bordered-bottom-solid">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                ボーナス月コース
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 10px;"
              >
                ボーナス月
              </el-col>
              <el-col
                :span="2"
                style="padding-left: 6px;"
              >
                {{ changeArrow }}
              </el-col>
              <el-col
                :span="7"
                style="padding-left: 6px;"
              >
                ボーナス月設定しない
              </el-col>
            </el-row>

            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                金額
              </el-col>
              <el-col :span="7">
                {{ `${$_addComma(beforeInfo?.settingBonusAmount)} 円` }}
              </el-col>
              <el-col :span="2">{{ changeArrow }}</el-col>
              <el-col :span="7">-</el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                概算手数料（税込み）
              </el-col>
              <el-col :span="7">{{ `${$_addComma(beforeInfo?.estimateFundOrderBonus)} 円` }}</el-col>
              <el-col :span="2">{{ changeArrow }}</el-col>
              <el-col :span="7">-</el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                設定日
              </el-col>
              <el-col :span="7">{{ settingDate(beforeInfo) }}</el-col>
              <el-col :span="2">{{ changeArrow }}</el-col>
              <el-col :span="7">-</el-col>
            </el-row>
            <el-row class="bordered-bottom-solid-sub">
              <el-col
                class="_bold_black_m"
                :span="8"
              >
                次の発注予定日
              </el-col>
              <el-col :span="7">{{ beforeInfo?.bonusPlanDate }}</el-col>
              <el-col :span="2">{{ changeArrow }}</el-col>
              <el-col :span="7">-</el-col>
            </el-row>
          </div>
        </div>
      </el-card>

      <el-card
        class="box-card"
        style="font-size: 16px;"
      >
        <el-row
          class="_bold_black_m"
          style="padding-top: 0.5rem; padding-left: 1rem;"
        >
          その他設定内容
        </el-row>
        <hr>

        <div style="padding: 0 0.5rem">
          <el-row class="bordered-bottom-solid">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              勧誘区分
            </el-col>
            <el-col
              :span="16"
              style="padding-left: 10px;"
            >
              {{ $_getCodeValue('INVITATION_TYPE', 1, info?.kanyuKbn) }}
            </el-col>
          </el-row>

          <el-row class="bordered-bottom-solid">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              受付方法
            </el-col>
            <el-col
              :span="16"
              style="padding-left: 10px;"
            >
              {{ $_getCodeValue('ORDER_METHOD_TYPE', 1, info?.receiveMethod) }}
            </el-col>
          </el-row>

          <el-row class="bordered-bottom-solid">
            <el-col
              :span="8"
              class="_bold_black_m"
              style="align-self: flex-start;"
            >
              ご注意事項
            </el-col>
            <el-col :span="16">
              <div style="padding-left: 10px;">
                <ifa-mutual-fund-accumulate-confirm-text></ifa-mutual-fund-accumulate-confirm-text>
              </div>
            </el-col>
          </el-row>

        </div>
      </el-card>

      <!-- アラート内容確認 -->
      <el-card
        v-if="showWarning"
        style="background-color: #fef0f0;"
      >
        <el-row
          class="_bold_black_m"
          style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
        >
          アラート内容確認
        </el-row>
        <hr>

        <el-form
          ref="formRef"
          :model="form"
        >
          <!-- コンプラランクチェック -->
          <el-row
            v-if="info?.complianceRankCheck?.message"
            class="dotted_border"
            style="align-items: center; min-height: 45px;"
          >
            <el-col
              :span="8"
              class="_bold_red_alert"
            >
              <div
                v-if="status === 'confirm'"
                class="required-mark"
              >*</div>
              <ifa-text
                style="color: #f00; font-size: 16px;"
                code-list-id="COMPLA_CHECK_BOX_WORDING"
                :disp-pattern="3"
                :select-pattern="1"
                :code-key="info?.complianceRankCheck?.invitationCheck"
              ></ifa-text>
            </el-col>
            <el-col :span="12">
              <el-form-item
                v-if="status === 'confirm'"
                prop="complianceRankCheckConfirm"
                :required="true"
                :rules="[
                  {
                    validator: (_rule, value, callback) => {
                      if (value) {
                        callback()
                      } else {
                        callback('項目を選択してください。')
                      }
                    },
                    trigger: 'change'
                  }
                ]"
              >
                <el-checkbox
                  v-model="form.complianceRankCheckConfirm"
                  style="color: #f00; font-size: 16px;"
                  class="checkbox-style"
                  :label="info?.complianceRankCheck?.invitationCheck === '1'
                    ? '△・◇ワーニング申請は「申請・承認済」であることを確認済'
                    :'勧誘なし'"
                ></el-checkbox>
              </el-form-item>
              <ifa-text
                v-else
                code-list-id="COMPLA_CHECK_BOX_WORDING"
                :disp-pattern="1"
                :select-pattern="1"
                :code-key="info?.complianceRankCheck?.invitationCheck"
                style="font-size:16px; color: #f00; font-weight: 700;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- コンプラランクチェック開始基準確認 -->
          <el-row
            v-if="info?.complianceRankCheck?.startCriteriaConfirmMsg"
            class="dotted_border"
            style="align-items: center; min-height: 45px;"
          >
            <el-col
              :span="8"
              class="_bold_red_alert"
            >
              <div
                v-if="status === 'confirm'"
                class="required-mark"
              >*</div>
              <span style="color: #f00;">開始基準の確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-if="status === 'confirm'"
                v-model="form.complianceRankCheckStartBaseConfirm"
                prop="complianceRankCheckStartBaseConfirm"
                label-class="text-red"
                code-list-id="COMPLIANCE_START_CRITERIA_CONFIRM_CHECK_BOX_WORDING"
                :disp-pattern="1"
                :select-pattern="2"
                required
              ></ifa-input-check>
              <ifa-text
                v-else
                code-list-id="COMPLIANCE_START_CRITERIA_CONFIRM_CHECK_BOX_WORDING"
                :disp-pattern="1"
                :select-pattern="2"
                code-key="1"
                style="font-size:16px; color: #f00; font-weight: 700;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 注意情報アラート確認 -->
          <el-row
            v-if="info?.noticeInfoAlert"
            class="dotted_border"
            style="align-items: center; min-height: 45px;"
          >
            <el-col
              :span="8"
              class="_bold_red_alert"
            >
              <span
                v-if="status === 'confirm'"
                class="required-mark"
              >*</span>
              <span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-if="status === 'confirm'"
                v-model="form.noticeInfoAlertConfirm"
                prop="noticeInfoAlertConfirm"
                label-class="text-red"
                code-list-id="NOTICE_INFO_CONFIRM"
                required
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
              <ifa-text
                v-else
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
                :code-key="info?.noticeInfoAlertConfirm"
                style="font-size:16px; color: #f00; font-weight: 700;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- お知らせ情報アラート確認 -->
          <el-row
            v-if="info?.noticeAlert"
            class="dotted_border"
            style="align-items: center; min-height: 45px;"
          >
            <el-col
              :span="8"
              class="_bold_red_alert"
            >
              <span
                v-if="status === 'confirm'"
                class="required-mark"
              >*</span>
              <span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-if="status === 'confirm'"
                v-model="form.noticeAlertConfirm"
                prop="noticeAlertConfirm"
                label-class="text-red"
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                required
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
              <ifa-text
                v-else
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                :code-key="info?.noticeAlertConfirm"
                style="font-size:16px; color: #f00; font-weight: 700;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!--確認書受け入れの確認-->
          <el-row
            v-if="info?.confirmDocumentAlert"
            class="dotted_border"
            style="align-items: center; min-height: 45px;"
          >
            <el-col
              :span="8"
              class="_bold_red_alert"
            >
              <span
                v-if="status === 'confirm'"
                class="required-mark"
              >*</span>
              <span style="color: #f00;">確認書受け入れの確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-if="status === 'confirm'"
                v-model="form.confirmDocumentAlertConfirm"
                prop="confirmDocumentAlertConfirm"
                label-class="text-red"
                style="margin-left: 5px; color: #f00;"
                code-list-id="CONFIRMATION_LETTER_ACCEPT_CONFIRM"
                required
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
              <ifa-text
                v-else
                code-list-id="CONFIRMATION_LETTER_ACCEPT_CONFIRM"
                :disp-pattern="1"
                :code-key="info?.confirmDocumentAlertConfirm"
                style="font-size:16px; color: #f00; font-weight: 700;"
              ></ifa-text>
            </el-col>
          </el-row>
        </el-form>
      </el-card>

      <div>
        <ifa-button
          v-if="status === 'confirm'"
          :disabled="buttonDisabled"
          text="設定変更登録"
          color="primary"
          action-type="originalAction"
          class="confirmBtn"
          @app-action-handler="handleSubmit"
        ></ifa-button>
        <ifa-button
          v-if="status === 'complete'"
          text="投信積立設定済銘柄一覧へ"
          color="primary"
          action-type="originalAction"
          class="confirmBtn"
          @app-action-handler="handleRedirect"
        ></ifa-button>
      </div>

      <ifa-requester
        id="ifaMutualFundAccumulateSettingChangeConfirmA002"
        action-id="SUB0202_0403-03_3#A002"
        action-type="requestAction"
        :request-model="settingChangeConfirmA002RequestModel"
        @response-handler="settingChangeConfirmA002Handler($event)"
        @response-error-handler="settingChangeConfirmA002ErrorHandler"
      ></ifa-requester>
    </div>
  </el-dialog>
</template>

<script>
import IfaButton from '@/components/Button/IfaButton.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea.vue'
import IfaText from '@/components/Input/IfaText.vue'
import IfaInputCheck from '@/components/Input/IfaInputCheck.vue'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'

import IfaMutualFundAccumulateConfirmText from './IfaMutualFundAccumulateConfirmText.vue'
import { IfaMutualFundAccumulateSettingChangeConfirmA002RequestModel } from '../js/IfaMutualFundAccumulateSettingChangeConfirmA002RequestModel'
import { IfaMutualFundAccumulateEditableConfirmFormModel } from '../js/IfaMutualFundAccumulateEditableConfirmFormModel'

export default {
  components: {
    IfaButton,
    IfaRequester,
    IfaMessageArea,
    IfaText,
    IfaInputCheck,
    IfaNoticeInfo,
    IfaMutualFundAccumulateConfirmText
  },
  props: {
    visible: { type: Boolean, required: true },
    confirmPayload: { type: Object, required: false, default: () => {} },
    confirmInfo: { type: Object, required: false, default: () => {} }
  },
  emits: ['on-cancel', 'on-close'],
  data() {
    return {
      // status -> confirm | complete
      status: 'confirm',
      info: this.confirmInfo ?? {},
      form: {},
      messages: {
        mains: [],
        errors: [],
        warns: []
      },
      messageKey: 0
    }
  },
  computed: {
    isVisible() {
      return this.visible
    },
    changeArrow() {
      return '→'
    },
    editableConfirmTitle() {
      return this.status === 'confirm'
        ? '投信積立設定変更確認'
        : '投信積立設定変更完了'
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    beforeInfo() {
      return this.info?.reserveSettingChangeBefore
    },
    afterInfo() {
      return this.info?.reserveSettingChangeAfter
    },
    buttonDisabled() {
      const { medUsers: { privId }} = this.$store.getters.userAccount
      if (privId === '1' || privId === '2') return true

      return (
        (this.info?.complianceRankCheck?.message && !this.form.complianceRankCheckConfirm) ||
        (this.info?.complianceRankCheck?.startCriteriaConfirmMsg && this.form.complianceRankCheckStartBaseConfirm !== '1') ||
        (this.info?.noticeInfoAlert && this.form.noticeInfoAlertConfirm !== '1') ||
        (this.info?.noticeAlert && this.form.noticeAlertConfirm !== '1') ||
        (this.info?.confirmDocumentAlert && this.form.confirmDocumentAlertConfirm !== '1')
      )
    },
    showWarning() {
      const {
        complianceRankCheck: { message, startCriteriaConfirmMsg },
        noticeInfoAlert,
        noticeAlert,
        confirmDocumentAlert
      } = this.info ?? {}

      return message ||
        startCriteriaConfirmMsg ||
        noticeInfoAlert ||
        noticeAlert ||
        confirmDocumentAlert
    },
    settingChangeConfirmA002RequestModel() {
      return new IfaMutualFundAccumulateSettingChangeConfirmA002RequestModel(this.confirmPayload, this.form, this.info)
    }
  },
  watch: {
    status(_curr, _prev) {
      this.setMsgs()
    }
  },
  mounted() {
    this.setMsgs()
    this.info = this.confirmInfo
    this.form = new IfaMutualFundAccumulateEditableConfirmFormModel()
  },
  methods: {
    handleCancel() {
      this.$emit('on-cancel')
    },
    handleSubmit() {
      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingChangeConfirmA002').click()
      })
    },
    handleRedirect() {
      this.$emit('on-close')
    },
    courseTypeText(courseType) {
      if (courseType === '1') return '毎日'
      if (courseType === '2') return '毎週'
      if (courseType === '3') return '毎月'
      if (courseType === '4') return '複数日'
      if (courseType === '5') return '奇数月'
      if (courseType === '6') return '偶数月'

      return '-'
    },
    settingDay(info) {
      switch (info?.courseType) {
        case '1':
          return '毎日'
        case '2':
          return `毎週${this.$_getCodeValue('FUND_RESERVE_WEEKLY_SETTING', 2, info?.settingReserveWeek)}`
        case '3':
          return `毎月${this.$_getCodeValue('FUND_RESERVE_DAY_SETTING', 3, info?.settingReserveDay)}`
        case '4':
          return `毎月${info?.settingReserveMultiDay
            ?.replace(/\b0(\d)\b/g, '$1')
            .replace(/;$/, '')
            .split(';')
            .join(', ')}日`
        case '5':
          return `奇数月${this.$_getCodeValue('FUND_RESERVE_DAY_SETTING', 3, info?.settingReserveDay)}`
        case '6':
          return `偶数月${this.$_getCodeValue('FUND_RESERVE_DAY_SETTING', 3, info?.settingReserveDay)}`
        default:
          return '-'
      }
    },
    settingDate(info) {
      if (info?.settingBonusFlag === '2') return '-'
      if (info?.settingBonus1Month && info?.settingBonus1Day) {
        const formatSettingBonus1Month = info?.settingBonus1Month
        const formatSettingBonus1Day = info?.settingBonus1Day === '31'
          ? '月末'
          : info?.settingBonus1Day

        if (info?.settingBonus2Month && info?.settingBonus2Day) {
          const formatSettingBonus2Month = info?.settingBonus2Month
          const formatSettingBonus2Day = info?.settingBonus2Day === '31'
            ? '月末'
            : info?.settingBonus2Day

          const date1 = `${formatSettingBonus1Month}/${formatSettingBonus1Day}`
          const date2 = `${formatSettingBonus2Month}/${formatSettingBonus2Day}`

          return `${date1}、${date2}`
        }
        return `${formatSettingBonus1Month}/${formatSettingBonus1Day}`
      }

      return '-'
    },
    settingChangeConfirmA002Handler(res) {
      this.$_logDebug(res)

      const data = res?.dataList?.[0] ?? {}
      const { complianceRankCheck } = data?.requestContents

      this.info = { ...data, complianceRankCheck }
      this.status = 'complete'

      this.$nextTick(() => {
        const dialog = document.getElementById('editable-confirm')

        dialog.scrollTo({
          top: 0,
          behavior: 'smooth'
        })
      })
    },
    settingChangeConfirmA002ErrorHandler(error) {
      this.$_logError(error)
    },
    setMsgs() {
      this.messages.mains = []
      this.messages.errors = []
      this.messages.warns = []

      if (this.status === 'confirm') {
        this.messages.mains.push('設定変更はまだ完了していません。画面下の設定変更登録ボタンを押下してください。')

        if (this.info?.complianceRankCheck?.message) {
          this.messages.errors.push(this.info?.complianceRankCheck?.message)
        }

        if (this.info?.complianceRankCheck?.startCriteriaConfirmMsg) {
          this.messages.errors.push(this.info?.complianceRankCheck?.startCriteriaConfirmMsg)
        }

        if (this.info?.noticeInfoAlert) {
          this.messages.errors.push(this.info?.noticeInfoAlert)
        }

        if (this.info?.noticeAlert) {
          this.messages.errors.push(this.info?.noticeAlert)
        }

        if (this.info?.confirmDocumentAlert) {
          this.messages.errors.push(this.info?.confirmDocumentAlert)
        }
      }

      if (this.status === 'complete') {
        this.messages.mains.push('下記の内容で積立設定変更を受け付けました。')
      }

      this.messageKey++
    }
  }
}
</script>

<style lang="scss" scoped>
.wrapper {
  padding: 0 30px;
  display: flex;
  flex-direction: column;
  row-gap: 10px;

  .padding {
    padding-left: 0;
    padding-right: 0;
  }
}

.bordered-bottom-solid {
  border-bottom: 1px solid #dddddd;
  display: flex;
  align-items: center;
  padding: 0.25rem 0;
  line-height: normal;
}

.bordered-bottom-solid-sub {
  border-bottom: 1px solid #dddddd;
  display: flex;
  align-items: center;
  padding: 0.25rem 0 0.25rem 1rem;
  line-height: normal;
}

.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}

:deep(.main-message) {
  padding: 0;
}

:deep(.error-message) {
  padding: 0 1rem;
}

:deep(.warning-message) {
  padding: 0 1rem;
}

._bold_red_alert {
  font-size: 16px;
  font-weight: bold;
  padding-right: 0.5rem;
  color: red;
}

:deep(.text-red) .el-checkbox__label {
  color: #f00;
  font-weight: 700;
}

:deep(.el-checkbox__label) {
  font-size: 16px;
  font-weight: 700;
}

:deep(.checkbox-style) .el-checkbox__input.is-checked + .el-checkbox__label {
  color: #f00;
  font-weight: 700;
}
</style>
