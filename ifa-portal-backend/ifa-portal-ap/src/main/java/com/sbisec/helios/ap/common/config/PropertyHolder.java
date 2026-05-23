package com.sbisec.helios.ap.common.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.sbisec.helios.ap.common.entity.ActiveDirectoryEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * Processing of all parameter settings.<br>
 * Setting location includes application.yml and context.xml files.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Component
public class PropertyHolder {

    // from application.yml =======================================================
    private @Getter @Value("${app.version}") String apiVersion;
    private @Getter @Value("${app.timeout}") int sysTimeout;
    private @Getter @Value("${app.security.ignore}") String[] securityIgnore;
    private @Getter @Value("${app.xss.enabled}") String xssEnabled;
    private @Getter @Value("${app.xss.excludes}") String[] xssExcludes;

    private @Getter @Value("${cors.addMapping}") String addMapping;
    private @Getter @Value("${cors.allowedMethods}") String[] allowedMethods;
    private @Getter @Value("${cors.maxAge}") long maxAge;
    private @Getter @Value("${cors.allowCredentials}") boolean allowCredentials;
    private @Getter @Value("${cors.allowedHeaders}") String[] allowedHeaders;

    private @Getter @Value("${mail.protocol}") String mailProtocol;
    private @Getter @Value("${mail.timeout}") String mailTimeout;
    private @Getter @Value("${mail.interval}") int mailInterval;

    // from context.xml ===========================================================
    @Autowired
    private Environment environment;

    private @Getter String[] envCrossOrigins = {};
    private @Getter List<ActiveDirectoryEntity> envLdaps = new ArrayList<ActiveDirectoryEntity>();
    private @Getter String envMailHost;
    private @Getter String envMailPort;
    private @Getter String envId;
    private @Getter String envMarkText;
    private @Getter String envApiToken;

    @PostConstruct
    void init() {
        this.envMailHost = "";
        this.envMailPort = "80001";
        // this.envCrossOrigins = ;
        this.envId = "envId";
        this.envMarkText = "envMarkText";
        this.envApiToken = "api_token";

        String domain = "ldap.domain";
        String url = "ldap.url";
        envLdaps.add(ActiveDirectoryEntity.instance(url, domain));
        
    }
}
