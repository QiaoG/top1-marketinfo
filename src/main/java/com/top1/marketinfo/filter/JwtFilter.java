package com.top1.marketinfo.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
* Author GQ
* Date:2018/3/2
* Time:下午7:47
*/
@Slf4j
public class JwtFilter extends GenericFilterBean {

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    private String secretkey;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;

        final String method = request.getMethod();
        log.info("##### request method: "+method+" "+secretkey);

        if("POST".equals(method)|| "PUT".equals(method) || "DELETE".equals(method)){
            //客户端将token封装在请求头中，格式为（Bearer后加空格）：Authorization：Bearer +token
            final String authHeader = request.getHeader("Authorization");
            log.info("auth header: "+authHeader);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header.");
            }

            //去除Bearer 后部分
            final String token = authHeader.substring(7);

            try {
                //解密token，拿到里面的对象claims
                final Claims claims = Jwts.parser().setSigningKey(secretkey)
                        .parseClaimsJws(token).getBody();
                //将对象传递给下一个请求
                request.setAttribute("claims", claims);
            }
            catch (final Exception e) {
                throw new ServletException("Invalid token."+e.getMessage());
            }
        }
//        if(true)
//            throw new ServletException("***** Invalid token. ****"+request.getAttribute("claims"));

        chain.doFilter(req, res);
    }
}
