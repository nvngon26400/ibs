package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

/**
 * 仲介業者顧客口座属性
 * @author tinn
 *
 */
public class MediateUserAttributeInfo extends ModelBase {

    private String customerId;
    private String corporationKbn;//法人区分
    private String tcCompRank;    //コンプラランク
    private String age;           //年齢

    public String getCorporationKbn() {
        return corporationKbn;
    }
    public void setCorporationKbn(String corporationKbn) {
        this.corporationKbn = corporationKbn;
    }
    public String getTcCompRank() {
        return tcCompRank;
    }
    public void setTcCompRank(String tcCompRank) {
        this.tcCompRank = tcCompRank;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
