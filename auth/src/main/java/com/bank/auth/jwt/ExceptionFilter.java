package com.bank.auth.jwt;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// @Component
public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        }
        catch (Exception e) {
            // 异常捕获，发送到error controller
            request.setAttribute("filter.exception", e);
            //将异常分发到/error/exthrow控制器
            request.getRequestDispatcher("/exception").forward(request, response);
        }

    }

}
