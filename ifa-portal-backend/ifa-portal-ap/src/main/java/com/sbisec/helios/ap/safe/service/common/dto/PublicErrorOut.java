package com.sbisec.helios.ap.safe.service.common.dto;

import java.util.List;

import com.sbisec.helios.ap.safe.common.exception.dto.PublicErrorInfoDetails;

/**
 * 国内株API用エラー出力Dto
 */
public class PublicErrorOut {

    /** エラータイプ */
    private String type;
    /** エラーコード */
    private String code;
    /** エラーメッセージ */
    private String title;
    /** エラー詳細 */
    private List<PublicErrorInfoDetails> errors;

    /**
     * エラータイプを取得する
     * @return エラータイプ
     */
    public String getType() {
        return type;
    }

    /**
     * エラータイプを設定する
     * @param type エラータイプ
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * エラーコードを取得する
     * @return エラーコード
     */
    public String getCode() {
        return code;
    }

    /**
     * エラーコードを設定する
     * @param code エラーコード
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * エラーメッセージを取得する
     * @return エラーメッセージ
     */
    public String getTitle() {
        return title;
    }

    /**
     * エラーメッセージを設定する
     * @param title エラーメッセージ
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * エラー詳細を取得する
     * @return エラー詳細
     */
    public List<PublicErrorInfoDetails> getErrors() {
        return errors == null ? List.of() : errors;
    }

    /**
     * エラー詳細を設定する
     * @param errors エラー詳細
     */
    public void setErrors(final List<PublicErrorInfoDetails> errors) {
        this.errors = errors;
    }

}
