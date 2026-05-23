package com.sbisec.helios.ap.broker;

import java.lang.reflect.Field;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sbisec.helios.ap.broker.annotation.PapyUrlParm;
import com.sbisec.helios.ap.broker.annotation.SetPapyAttributeName;
import com.sbisec.helios.ap.broker.util.PapyConnectionException;

import okhttp3.MediaType;

/**
 * <p>
 * Description:OkHttpRequest
 * </p>
 *
 * @author katsuhiko.kagoshima
 * @date 12/23/2022
 */
public class PapyOkHttpRequest implements OkHttpBaseRequest {
    
    public interface CONTENT_TYPE {
        
        MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
        
        MediaType MEDIA_TYPE_FORM = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    }
    
    private ObjectMapper objectMapper;
    
    public PapyOkHttpRequest() {
        
        contentType = CONTENT_TYPE.MEDIA_TYPE_FORM;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
    
    private String url;
    
    private MediaType contentType;
    
    private Map<String, Object> headers;
    
    private Map<String, Object> parameters;
    
    public String getUrl() {
        
        return url;
    }
    
    public void setUrl(String url) {
        
        this.url = url;
    }
    
    public MediaType getContentType() {
        
        return contentType;
    }
    
    public void setContentType(MediaType contentType) {
        
        this.contentType = contentType;
    }
    
    public Map<String, Object> getHeaders() {
        
        return headers;
    }
    
    //PAPY-APIでは、Headerの指定がないため、実際は使用されない。
    public void setHeaders(Object obj) throws PapyConnectionException {
        
        try {
            this.headers = objectMapper.readValue(objectMapper.writeValueAsString(obj),
                    new TypeReference<Map<String, Object>>() {
                    });
            
            dealHeaderAnnotation(obj);
        } catch (Exception e) {
            throw new PapyConnectionException(e);
        }
    }
    
    public Map<String, Object> getParameters() {
        
        return parameters;
    }
    
    public void setParameters(Object obj) throws PapyConnectionException {
        
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            this.parameters = objectMapper.readValue(objectMapper.writeValueAsString(obj),
                    new TypeReference<Map<String, Object>>() {
                    });
            
            dealParamAnnotation(obj);
        } catch (Exception e) {
            throw new PapyConnectionException(e);
        }
    }
    
    private void dealHeaderAnnotation(Object obj) {
        
        if (null != obj && null != this.headers && !this.headers.isEmpty()) {
            Class<? extends Object> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (!this.headers.containsKey(f.getName())) {
                    continue;
                }
                // SetAttributeName deal
                SetPapyAttributeName san = f.getDeclaredAnnotation(SetPapyAttributeName.class);
                if (null == san) {
                    continue;
                }
                this.headers.put(san.name(), this.headers.remove(f.getName()));
            }
        }
    }
    
    private void dealParamAnnotation(Object obj) {
        
        if (null != obj && null != this.parameters && !this.parameters.isEmpty()) {
            Class<? extends Object> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (!this.parameters.containsKey(f.getName())) {
                    continue;
                }
                // UrlParm deal
                PapyUrlParm up = f.getDeclaredAnnotation(PapyUrlParm.class);
                if (null != up) {
                    this.url = this.url.replace("{" + up.name() + "}",
                            String.valueOf(this.parameters.get(f.getName())));
                    this.parameters.remove(f.getName());
                }
                // SetAttributeName deal
                SetPapyAttributeName san = f.getDeclaredAnnotation(SetPapyAttributeName.class);
                if (null != san && this.parameters.containsKey(f.getName())) {
                    this.parameters.put(san.name(), this.parameters.remove(f.getName()));
                }
            }
        }
    }
}
