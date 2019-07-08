package com.zhwl.util;

import com.alibaba.fastjson.JSON;
import com.zhwl.bean.Customer;
import com.zhwl.bean.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


//@Component
public class SysUserUtil {
    public static SysUser getCurrentUser() {
        String sysUser = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getHeader("X-AUTH-ID");
        if (sysUser != null){
            return JSON.parseObject(sysUser, SysUser.class);
        }

        Object principal =  SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if(principal instanceof Customer)
        {
            Customer customer = (Customer)principal;
            customer.setPassword(null);
            return  customer;
        }else {
            return new Customer();
        }
    }
}
