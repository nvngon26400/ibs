package com.sbisec.helios.gw.common.service.impl;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.FrameworkSessionInfo;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.gw.common.service.TokenService;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * トークンチェックサービスの実装
 */
public class TokenServiceImpl implements TokenService {
    
    /* ロガー */
    // private final static Logger LOGGER =
    // LoggerFactory.getLogger(TokenServiceImpl.class);
    
    private final Long expirationTime = 1000L * 60L * 60L * 10L;
    
    private final String cacheName = "sessionInfo";
    
    private final String secret = "SECRET_KEY";
    
    private final String claimName = "X-USER-ACCOUNT";
    
    Algorithm algorithm = Algorithm.HMAC256(secret);
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private Cache cache;
    
    public TokenServiceImpl(CacheManager cacheManager) {
        
        this.cache = cacheManager.getCache(cacheName);
    }
    
    /**
     * セッション情報の生成。
     *
     * @param frameworkSessionId フレームワークセッションID
     * @param userAccount ユーザ共通情報
     * @return フレームワークセッション情報
     * @throws Exception 任意の例外
     */
    public FrameworkSessionInfo createSessionInfo(String frameworkSessionId, UserAccount userAccount) throws Exception {
        
        FrameworkSessionInfo sessionInfo = new FrameworkSessionInfo(frameworkSessionId);
        ObjectMapper mapper = new ObjectMapper();
        sessionInfo.setJwt(createJwt(mapper.writeValueAsString(userAccount)));
        
        // フレームワークセッションIDをキーとして、セッション情報をキャッシュに登録。
        this.setSessionInfoToCache(sessionInfo);
        return sessionInfo;
    }
    
    /**
     * セッション情報の更新
     *
     * @param sessionInfo FrameworkSessionInfo
     * @param userAccount ユーザ共通情報
     * @throws Exception 任意の例外
     */
    public void updateSessionInfo(FrameworkSessionInfo sessionInfo, UserAccount userAccount) throws Exception {
        
        ObjectMapper mapper = new ObjectMapper();
        sessionInfo.setJwt(createJwt(mapper.writeValueAsString(userAccount)));
        
        // フレームワークセッションIDをキーとして、セッション情報をキャッシュに登録。
        this.setSessionInfoToCache(sessionInfo);
        return;
    }
    
    /**
     *  キャッシュからのセッション情報の取得。
     *
     * @param frameworkSessionId フレームワークセッションID
     * @return フレームワークセッション情報
     * @throws Exception 任意の例外
     */
    public FrameworkSessionInfo getSessionInfo(String frameworkSessionId) throws Exception {
        
        FrameworkSessionInfo output = this.cache.get(frameworkSessionId, FrameworkSessionInfo.class);
        return output;
    }
    
    // JWTの生成。
    private String createJwt(String userAccountString) {
        
        Date issuedAt = new Date();
        Date notBefore = new Date(issuedAt.getTime());
        Date expiresAt = new Date(issuedAt.getTime() + expirationTime);
        
        try {
            // JWTの生成。ユーザアカウント情報もペイロードに追加。
            String token = JWT.create().withSubject("sessionInfo").withIssuedAt(issuedAt).withNotBefore(notBefore)
                    .withExpiresAt(expiresAt).withClaim(claimName, userAccountString).sign(algorithm);
            return token;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 認証トークンの検証
     *
     * @param frameworkSessionId フレームワークセッションID
     * @param targetToken 認証トークン
     * @return 検証結果
     * @throws Exception 任意の例外
     */
    public Boolean verifyAuthenticationToken(String frameworkSessionId, String targetToken) throws Exception {
        
        FrameworkSessionInfo frameworkSessionInfo = this.getSessionInfo(frameworkSessionId);
        
        if (frameworkSessionInfo == null) {
            return false;
        }
        
        return frameworkSessionInfo.isValidEncryptAuthenticationToken(targetToken);
    }
    
    // JWTの改ざんチェックを実行。
    private Boolean verifyToken(String token) {
        
        if (token == null) {
            return false;
        }
        
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * JWTからユーザ情報を取得する処理。
     *
     * @param sessionInfo フレームワークセッション情報
     * @return ユーザ共通情報
     */
    public UserAccount getUserAccountObjectFromJwt(FrameworkSessionInfo sessionInfo) {
        
        if (sessionInfo == null || sessionInfo.getJwt() == null || !verifyToken(sessionInfo.getJwt())) {
            return null;
        }
        
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(sessionInfo.getJwt());
            UserAccount userAccount = jc.toObject(jwt.getClaim(claimName).asString(), UserAccount.class);
            
            return userAccount;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        
    }
    
    public void setSessionInfoToCache(FrameworkSessionInfo frameworkSessionInfo) throws Exception {
        
        this.cache.put(frameworkSessionInfo.getFrameworkSessionId(), frameworkSessionInfo);
    }
    
    public void evictSessionInfoFromCache(String frameworkSessionId) {
        
        this.cache.evict(frameworkSessionId);
    }
    
}
