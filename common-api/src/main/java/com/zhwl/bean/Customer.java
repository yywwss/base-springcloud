package com.zhwl.bean;


import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@TableName("t_customer")
public class Customer extends SysUser implements UserDetails {


    public void setAuthorities(List<GrantedAuthority> authorities) {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object rhs) {
        return rhs instanceof UserDetails && this.username.equals(((UserDetails) rhs).getUsername());
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

}
