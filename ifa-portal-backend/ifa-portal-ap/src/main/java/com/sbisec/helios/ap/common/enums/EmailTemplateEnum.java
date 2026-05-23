package com.sbisec.helios.ap.common.enums;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * Define email content templates.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@JsonFormat(shape = Shape.OBJECT)
public enum EmailTemplateEnum {

    Template("template", "email-template.ftl");

    private String cd;
    private String template;

    private EmailTemplateEnum(String cd, String template) {
        this.cd = cd;
        this.template = template;
    }

    public String getCd() {
        return cd;
    }

    public String getTemplate() {
        return template;
    }

    /**
     * Get enum value by id.
     * 
     * @param cd code
     * @return
     */
    public static EmailTemplateEnum getValueById(String cd) {
        return Arrays.asList(EmailTemplateEnum.values()).stream()
                     .filter(e -> e.getCd().equals(cd))
                     .findFirst()
                     .orElse(null);
    }

}
