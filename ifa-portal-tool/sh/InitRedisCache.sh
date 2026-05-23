#!/bin/bash
# ==================================================================================================
# ファイル名 : InitRedisCache.sh
# システム名 : Helios
# レイヤー名 : アプリ基盤
# 機能ID     : 
# 機能名     : Redisキャッシュ設定値初期化
# 記述言語   : bash
# 作成       : 2024/04/23 IFAポータル刷新 SCSK宮坂
# 改訂履歴   : 2024/09/26 IFAポータル刷新 全角チェック定義追加 SCSK丹波
# ==================================================================================================

# ==================================================================================================
# グローバル定数
# ==================================================================================================

# ディレクトリ
THIS_SHELL_DIR=$(echo "$(cd $(dirname $0) && pwd)")
PARAM_DIR="${THIS_SHELL_DIR}/param/redis"

# 戻り値
RET_SUCCESS=0
RET_ERROR=9

# spring-redis-cli固有
TOOL_VERSION="1.0.0"
REDIS_HOST="localhost"
REDIS_PORT="6379"

# ==================================================================================================
# ファンクション定義
# ==================================================================================================

# --------------------------------------------------------------------------------------------------
# 環境チェック
# --------------------------------------------------------------------------------------------------
checkEnvironment() {
	which redis-cli &> /dev/null
	if [[ ${PIPESTATUS[0]} -ne 0 ]]; then
		echo "redis-cliコマンドが認識できません。"

		exit ${RET_ERROR}
	fi

	if [[ ! -z ${JAVA_HOME} ]]; then
		export PATH="${JAVA_HOME}/bin/java:${PATH}"
	else
		which java &> /dev/null
		if [[ ${PIPESTATUS[0]} -ne 0 ]]; then
			echo "javaコマンドが認識できません。"

			exit ${RET_ERROR}
		fi
	fi

	if [[ ! -r ${THIS_SHELL_DIR}/ifa-portal-tool-${TOOL_VERSION}.jar ]]; then
		echo "jarファイルが存在しません。 jarファイル=${THIS_SHELL_DIR}/ifa-portal-tool-${TOOL_VERSION}.jar"

		exit ${RET_ERROR}
	fi

	return ${RET_SUCCESS}
}

# --------------------------------------------------------------------------------------------------
# environmentグループのキャッシュキーをリストアップ
# 引数：spring-redis-cliの仕様に基づく
# --------------------------------------------------------------------------------------------------
listEnvironmentKeys() {
	redis-cli -h ${REDIS_HOST} -p ${REDIS_PORT} keys '*' | grep "^environment::"
	if [[ ${PIPESTATUS[0]} -ne 0 ]]; then
		exit ${RET_ERROR}
	fi

	return ${RET_SUCCESS}
}

# --------------------------------------------------------------------------------------------------
# spring-redis-cliのコマンドを実行
# 引数：spring-redis-cliの仕様に基づく
# --------------------------------------------------------------------------------------------------
executeCommand() {
	java -Dredis.host=${REDIS_HOST} -Dredis.port=${REDIS_PORT} -jar ${THIS_SHELL_DIR}/ifa-portal-tool-${TOOL_VERSION}.jar spring-redis-cli $*
	if [[ ${PIPESTATUS[0]} -ne 0 ]]; then
		exit ${RET_ERROR}
	fi

	return ${RET_SUCCESS}
}

# ==================================================================================================
# 処理メイン
# ==================================================================================================

# 実行モードを設定
if [[ $# -eq 0 ]]; then
	runMode="init"
else
	runMode="$1"
fi

# 環境チェック
checkEnvironment

if [[ "${runMode}" = "init" ]]; then
	# Redisに設定値を投入（GW）
	executeCommand set requestTimeOutSec 600
	executeCommand set idleLimitSec 10800
	executeCommand set rPPOptions "30,50,100,200,500"
	executeCommand set loginFilter --json=${PARAM_DIR}/loginFilter.json
	executeCommand set requestEncodingFilter --json=${PARAM_DIR}/requestEncodingFilter.json
	executeCommand set requestRestrictionFilter --json=${PARAM_DIR}/requestRestrictionFilter.json
	executeCommand set accessLogFilter --json=${PARAM_DIR}/accessLogFilter.json
	executeCommand set parameterLogFilter --json=${PARAM_DIR}/parameterLogFilter.json
	executeCommand set parameterLogInterceptor --json=${PARAM_DIR}/parameterLogInterceptor.json
	executeCommand set extApi:authentication --json=${PARAM_DIR}/extApi_authentication.json
	executeCommand set extApi:accessControl --json=${PARAM_DIR}/extApi_accessControl.json
	executeCommand set authCtrlListExclusionSettings --json=${PARAM_DIR}/authCtrlListExclusionSettings.json
	executeCommand set checkFullWidthCharacter --json=${PARAM_DIR}/checkFullWidthCharacter.json

elif [[ "${runMode}" = "list" ]]; then
	# 現状のenvironmentグループのキャッシュキーをリストアップ
	listEnvironmentKeys
else
	# それ以外はspring-redis-cliにそのまま委譲
	executeCommand $*
fi

exit ${RET_SUCCESS}
