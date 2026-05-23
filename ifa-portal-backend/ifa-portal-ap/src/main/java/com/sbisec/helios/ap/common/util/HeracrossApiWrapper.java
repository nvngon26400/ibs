package com.sbisec.helios.ap.common.util;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.extapi.ApiConnectionException;
import com.sbibits.earth.extapi.ApiIOException;

import jp.co.sbisec.pcenter.Config;
import jp.co.sbisec.pcenter.dto.heracross.RealQuoteSnapshotOut;
import jp.co.sbisec.pcenter.dto.heracross.*;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.service.CodeListService;


@Component
public class HeracrossApiWrapper {
    
    @Autowired
    private CodeListService codelistservice;
    
    private static ObjectMapper jacksonObjectMapper = new ObjectMapper();
    
    /* For API CONFIG */
    private static final String CONF_FILE = "pcenter.yml";
    
    private static final Logger logger = LoggerFactory.getLogger(HeracrossApiWrapper.class);
    
    private static final long SUCCESS_CODE = 0L;
    
    //private static final String SUCCESS_CODE_OUTDATA = " ";
    
    /* API CONFIG Load */
    static {
    	final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
          final URL url = classLoader.getResource(CONF_FILE);
          try {
              if (url != null) {
                  Config.init(url.getPath());
              } else {
                  throw new Exception("ec gateway config file not found. [" + CONF_FILE + "]");
              }
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      }

    /**
     * Heracross API結果を検証する．
     * 
     * @param code 結果コード
     * @param message メッセージ
     * @throws Exception 異常
     */
    private void checkApplicationValidationResult(long code, String message) throws Exception {
        
        String errorMessage = message.trim();
        if (SUCCESS_CODE != code) {
            logger.info("ApiError at API call. code:[" + code + "], message:[" + message + "]");
            String errorCode = String.valueOf(code);
            throw new ApiError(errorCode, errorMessage);
        }
    }
    
    private static DateTimeFormatter fmt;
    
    /**
     * hash値を生成する．
     * 
     * @throws Exception 異常
     */
    private String genHashValue() {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            String hashKey = "161df8abb44fb3e3ce17SBISecurities035d6b8afcffcd3041806be0753c74";
            fmt = DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT);

            var date = LocalDate.now(ZoneId.of("Asia/Tokyo"));
            String x = date.format(fmt) + hashKey;
            byte[] sha256Byte = sha256.digest(x.getBytes());
            // バイト配列のハッシュ値を16進数の文字列に変換
            String hexString = bytesToHex(sha256Byte);
            
            //Java17以降からしか使用出来ない
            //HexFormat hex = HexFormat.of().withLowerCase();
            //String hexString = hex.formatHex(sha256Byte);
            return hexString;
        }
        catch (NoSuchAlgorithmException e) {
        	return null;
        }
        
    }
    
    /**
     * バイト配列のハッシュ値を16進数の文字列に変換
     * 
     * @param バイト配列
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    /**
     * RealQuoteSnapshot
     * 
     * @param inputstring リクエストパラメータ
     * @throws Exception 異常
     */ 
    public RealQuoteSnapshotOut getRealQuoteSnapshot(String inputstring) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("HeracrossApiWrapperApiWrapper.getRealQuoteSnapshot : {}", hashCode());
        
        RealQuoteSnapshotOut stockSnapshotOut = null;

        if (inputstring == null) {
            return stockSnapshotOut;
        }
        
        try {
            // API呼び出し
            final HttpApiWrapper wrapper = HttpApiWrapper.get();

            RealQuoteSnapshotInput stockSnapshotIn = new RealQuoteSnapshotInput();
            stockSnapshotIn.setInputstring(inputstring);
            stockSnapshotIn.setHashvalue(genHashValue());
            stockSnapshotIn.setCallback("callbackSPR");
            
            String injson = jacksonObjectMapper.writeValueAsString(stockSnapshotIn);
            logger.info("API input : {}", injson);
            
            stockSnapshotOut = wrapper.callAsGet(stockSnapshotIn, RealQuoteSnapshotOut.class);
            
            String outjson = jacksonObjectMapper.writeValueAsString(stockSnapshotOut);
            logger.info("API output : {}", outjson);

        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Heracross API Exception occured.");
            logger.info("Heracross API Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        //ResponseStatusを参照しエラー判定
        switch (stockSnapshotOut.getResponseStatus().getReturnCode()){
        case 0:
        	logger.info("Heracross-API ResponseStatus: SUCCESS.");
        	break;
        case 1:
        	logger.info("Heracross-API ResponseStatus: WARNING.");
        	break;
        case -1:
        	logger.error("Heracross-API ResponseStatus: SystemError."
        			+ "code:[" + stockSnapshotOut.getResponseStatus().getReturnCode() + "], "
        			+ "message:[" + StringUtils.trim(stockSnapshotOut.getResponseStatus().getMessageText()) + "]");
        	throw new ApiError(ErrorLevel.SYSTEM_ERROR,"-1", "SYSTEMERROR");
        default :
        	logger.info("Status  default.",stockSnapshotOut.getResponseStatus().getReturnCode());
        	break;
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        
        return stockSnapshotOut;
    }
    
    /**
     * 外部API getRealQuoteSnapshotの入力データ「inputstring」設定文字列の作成
     *
     * @param paramArayList 設定値配列のリスト<br>
     *     ※リスト内の要素数は必ず1以上であること、リスト要素である配列の要素数はすべて同一であること
     * @return リスト内の要素数,リスト要素の配列要素数,リスト要素の配列[0],リスト要素の配列[1],...
     */
	
    
    public  String convertInputstring(List<String[]> paramArayList) throws Exception {
        
    	int index = 0;
        StringBuffer rtnInputstring = null;

        if (paramArayList != null) {
            
            for (String [] paramAry : paramArayList) {
                
                if (rtnInputstring == null) {
                    rtnInputstring = new StringBuffer();
                    rtnInputstring.append(paramArayList.size());
                    rtnInputstring.append(",");
                    rtnInputstring.append(paramAry.length);
                }
                index = 0;
                for (String param : paramAry) {
                    rtnInputstring.append(",");
                	if (index == 0) {
                		//選択市場（SELECT_MARKET）外部コード変換
                		String convertedParam = codelistservice.convertKeyToExtKey("SELECT_MARKET","heracross",param);
                		rtnInputstring.append(convertedParam);
                	}else {
                		//銘柄コード
                		rtnInputstring.append(param);
                	}
                	index++;
                }
            }
        }
        return (rtnInputstring == null) ? null : rtnInputstring.toString();
    }
    

}
