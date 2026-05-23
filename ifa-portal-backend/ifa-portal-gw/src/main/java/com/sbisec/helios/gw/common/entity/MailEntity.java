package com.sbisec.helios.gw.common.entity;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Mail entity.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class MailEntity {
    private String from;
    private String[] to;
    private String[] cc;
    private String subject;
    private String content;
    private String aliase;
    Map<String, Object> model;

    /**
     * Build mail entry.
     * 
     * @param from    sender
     * @param to      addressee
     * @param cc      addressee(associated)
     * @param subject subject
     * @param content mail content
     */
    public MailEntity(String from, String[] to, String[] cc, String subject, String content) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.content = content;
    }

    public MailEntity() {
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getFrom() {
        return StringUtils.isBlank(this.aliase) ? from
                : new StringBuffer(aliase).append(" <").append(from).append(">").toString();
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAliase() {
        return aliase;
    }

    public void setAliase(String aliase) {
        this.aliase = aliase;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
