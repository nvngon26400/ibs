package com.sbisec.helios.ap.safe.common.exception.dto;

/**
 * 国内株API用エラー詳細
 */
public class PublicErrorInfoDetails {

    /** エラーID */
    private String id;

    /** エラーコード */
    private String code;

    /** エラーメッセージ */
    private String message;

    /**
     * エラーIDを取得する
     * @return エラーID
     */
    public String getId() {
        return id;
    }

    /**
     * エラーIDを設定する
     * @param id エラーID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * エラーコードを取得する
     * @return エラーコード
     */
    public String getCode() {
        return code;
    }

    /**
     * エラーメッセージを設定する
     * @param code エラーメッセージ
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * エラーメッセージを取得する
     * @return エラーメッセージ
     */
    public String getMessage() {
        return message;
    }

    /**
     * エラーメッセージを設定する
     * @param message エラーメッセージ
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * builder
     */
    public static final class PublicErrorInfoDetailsBuilder {
        /** エラーID */
        private String id;
        /** コード */
        private String code;
        /** エラーメッセージ */
        private String message;

        /**
         * コンストラクタ
         */
        private PublicErrorInfoDetailsBuilder() {
        }

        /**
         * Builderのインスタンスを生成
         * @return builder
         */
        public static PublicErrorInfoDetailsBuilder anErrorMessageInfo() {
            return new PublicErrorInfoDetailsBuilder();
        }

        /**
         * code をセット
         * @param code コード
         * @return builder
         */
        public PublicErrorInfoDetailsBuilder code(final String code) {
            this.code = code;
            return this;
        }

        /**
         * message をセット
         * @param message エラーメッセージ
         * @return builder
         */
        public PublicErrorInfoDetailsBuilder message(final String message) {
            this.message = message;
            return this;
        }

        /**
        * id をセット
        * @param id エラーID
        * @return builder
        */
        public PublicErrorInfoDetailsBuilder id(final String id) {
            this.id = id;
            return this;
        }

        /**
         * ErrorInfoDetailsのインスタンスを生成する
         * @return ErrorInfoDetails
         */
        public PublicErrorInfoDetails build() {
            final PublicErrorInfoDetails errorInfoDetails = new PublicErrorInfoDetails();
            errorInfoDetails.setId(id);
            errorInfoDetails.setCode(code);
            errorInfoDetails.setMessage(message);
            return errorInfoDetails;
        }
    }

}
