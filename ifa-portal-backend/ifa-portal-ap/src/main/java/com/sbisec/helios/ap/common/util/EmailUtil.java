package com.sbisec.helios.ap.common.util;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import com.sbisec.helios.ap.common.config.PropertyHolder;
import com.sbisec.helios.ap.common.entity.MailEntity;
import com.sbisec.helios.ap.common.enums.EmailTemplateEnum;
import com.sbisec.helios.ap.common.exception.SendEmailException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

/**
 * Tools for sending e-mails.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Component
@Slf4j
public class EmailUtil {

    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    PropertyHolder prop;

    /**
     * Build java mail sender.
     * 
     * @param protocol protocol
     * @param host     host
     * @param port     port
     * @param timeout  timeout
     * @return
     */
    public static JavaMailSender mailSender(String protocol, String host, int port, String timeout) {
        log.info("Mail connect information service={}, port={} protocol={}, timeout={}",
                 host, port, protocol, timeout);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setProtocol(protocol);
        mailSender.setHost(host);
        mailSender.setPort(port);

        Properties props = new Properties();
        props.setProperty("mail.smtp.timeout", timeout);
        props.setProperty("mail.smtp.connectiontimeout", timeout);
        props.setProperty("mail.smtp.writetimeout", timeout);
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }

    /**
     * Send email.<br>
     * This method is a singleton mode. <br>
     * When the service sends multiple messages, it needs to be sent in sequence according to the
     * queue.<br>
     * You can specify the interval between transmissions.
     * 
     * @param mail     mail bean
     * @param template mail template
     * @return
     */
    public synchronized boolean send(MailEntity mail, EmailTemplateEnum template) {
        try {
            logger.info("The mail of template is {}", template.getTemplate());
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                                                             StandardCharsets.UTF_8.name());

            if (StringUtils.isNotEmpty(template.getTemplate())) {
                // Set a template for sending email.
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
                Template t = freemarkerConfig.getTemplate(template.getTemplate());
                String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());
                logger.info(html);
                helper.setText(html, true);
            } else {
                // Set the content of the sent mail, plain text content, without using templates.
                helper.setText(mail.getContent());
            }

            // Set the email address of the sender who sends the email.
            helper.setFrom(mail.getFrom());
            // Set the email address of the recipient of the email.
            helper.setTo(mail.getTo());
            if (mail.getCc() != null && mail.getCc().length != 0) {
                helper.setCc(mail.getCc());
            }
            // Set the subject of the email.
            helper.setSubject(mail.getSubject());
            try {
                // Send email.
                emailSender.send(message);
            } catch (MailException e) {
                logger.warn(e.getMessage(), e);
                // When sending mail, the mail server may be temporarily interrupted.
                // Therefore, it is necessary to send the mail again after the mail server terminal.
                emailSender.send(message);
            }
            // Set the time interval for sending e-mails.
            Thread.sleep(prop.getMailInterval());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SendEmailException(e.getMessage(), e);
        }
    }

    /**
     * Build the model for mail without CC.
     */
    public static MailEntity buildMail(
            String from,
            String aliase,
            String[] to,
            String subject,
            String content,
            Map<String, Object> detail) {
        MailEntity mail = new MailEntity();
        mail.setFrom(from);
        mail.setAliase(aliase);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setContent(content);
        mail.setModel(detail);
        return mail;
    }

    /**
     * Build the model for mail.
     */
    public static MailEntity buildMail(
            String from,
            String aliase,
            String[] to,
            String[] cc,
            String subject,
            String content,
            Map<String, Object> detail) {
        MailEntity mail = new MailEntity();
        mail.setFrom(from);
        mail.setAliase(aliase);
        mail.setTo(to);
        mail.setCc(cc);
        mail.setSubject(subject);
        mail.setContent(content);
        mail.setModel(detail);
        return mail;
    }

    /**
     * Validate the mail format.
     * 
     * @param emails mail address<br>
     *               can be multiple, must be separated by commas
     * @return
     */
    public static boolean validateEmail(String emails) {
        EmailValidator v = EmailValidator.getInstance();
        for (String email : emails.split(",")) {
            return v.isValid(email);
        }
        return true;
    }
}
