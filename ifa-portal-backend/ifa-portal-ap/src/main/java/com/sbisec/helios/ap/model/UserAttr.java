package com.sbisec.helios.ap.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.util.DateUtil;

// import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import lombok.Data;

@Data
// @Table(name = "m_user_attr")
public class UserAttr implements LdapUserDetails {

    private static final long serialVersionUID = -6618429931736256280L;

    private String userId;
    private String userFullName;
    private String password;
    private Date registerDt;
    private String lastLoginIp;
    private Date lastLoginTis;
    private Date lockedEndTis;
    private Date pwdExpiredTis;
    private Date userExpiredTis;
    private String enabledFlg;
    private String userSts;

    private String createUser;
    private Date createDt;
    private String updateUser;
    private Date updateDt;

    private List<Role> roles;

    /**
     * Build GrantedAuthoritys by user permissions.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getPermissions()
                   .stream()
                   .map(p -> new SimpleGrantedAuthority(
                                                    new StringBuffer(p.getPermissionId()).append("_")
                                                                                         .append(p.getPermissionType())
                                                                                         .toString()))
                   .sorted(Comparator.comparing(GrantedAuthority::getAuthority))
                   .collect(Collectors.toList());
    }

    /**
     * Get user permissions.
     */
    public List<Permission> getPermissions() {
        List<Permission> permissions = new ArrayList<Permission>();
        this.getRoles()
            .stream()
            .forEach(r -> {
                r.getPermissions().stream().forEach(p -> {
                    permissions.add(p);
                });
            });
        return permissions.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    /**
     * The current time is before the account expiration time,<br>
     * or when the account expiration time is not set, return True.
     */
    @Override
    public boolean isAccountNonExpired() {
        if (this.userExpiredTis == null) {
            return true;
        }
        return DateUtil.current().before(this.userExpiredTis);
    }

    /**
     * The current time is after the account lockout termination time,<br>
     * or when the account lockout termination time is not set, return True.
     */
    @Override
    public boolean isAccountNonLocked() {
        if (this.lockedEndTis == null) {
            return true;
        }
        return DateUtil.current().after(this.lockedEndTis);
    }

    /**
     * The current time is before the account password validity time,<br>
     * or when the account password validity time is not set, return True.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        if (this.pwdExpiredTis == null) {
            return true;
        }
        return DateUtil.current().before(this.pwdExpiredTis);
    }

    /**
     * When the account is available, return to True.
     */
    @Override
    public boolean isEnabled() {
        return StringUtils.compare(this.enabledFlg, AppConstants.FLG_ON) == 0;
    }

    @Override
    public String toString() {
        return this.userId;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public String getDn() {
        return this.getDn();
    }
}
