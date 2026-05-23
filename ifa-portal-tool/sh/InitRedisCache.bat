@rem # ==================================================================================================
@rem # ファイル名 : InitRedisCache.bat
@rem # システム名 : Helios
@rem # レイヤー名 : アプリ基盤
@rem # 機能ID     : 
@rem # 機能名     : Redisキャッシュ設定値初期化（簡易版）
@rem # 記述言語   : bat
@rem # 作成       : 2024/04/23 IFAポータル刷新 SCSK宮坂
@rem # 改訂履歴   : 2024/09/26 IFAポータル刷新 全角チェック定義追加 SCSK丹波
@rem # ==================================================================================================

@echo off
@setlocal

@rem # ==================================================================================================
@rem # グローバル定数
@rem # ==================================================================================================

@rem # ディレクトリ
set THIS_BAT_DIR=%~dp0
set PARAM_DIR=%THIS_BAT_DIR%\param\redis

@rem # spring-redis-cli固有
set TOOL_VERSION=1.0.0
set REDIS_HOST=localhost
set REDIS_PORT=6379

@rem # ==================================================================================================
@rem # 処理メイン
@rem # ==================================================================================================

java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set requestTimeOutSec 600
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set idleLimitSec 10800
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set rPPOptions "30,50,100,200,500"
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set loginFilter --json=%PARAM_DIR%/loginFilter.json
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set requestEncodingFilter --json=%PARAM_DIR%/requestEncodingFilter.json
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set requestRestrictionFilter --json=%PARAM_DIR%/requestRestrictionFilter.json
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set accessLogFilter --json=%PARAM_DIR%/accessLogFilter.json
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set parameterLogFilter --json=%PARAM_DIR%/parameterLogFilter.json
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set parameterLogInterceptor --json=%PARAM_DIR%/parameterLogInterceptor.json
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set extApi:authentication --json=%PARAM_DIR%/extApi_authentication.json
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set extApi:accessControl --json=%PARAM_DIR%/extApi_accessControl.json
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set authCtrlListExclusionSettings --json=%PARAM_DIR%/authCtrlListExclusionSettings.json
java -Dredis.host=%REDIS_HOST% -Dredis.port=%REDIS_PORT% -jar %THIS_BAT_DIR%\ifa-portal-tool-%TOOL_VERSION%.jar spring-redis-cli set checkFullWidthCharacter --json=%PARAM_DIR%/checkFullWidthCharacter.json

@endlocal
pause
