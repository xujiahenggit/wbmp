package com.bank.auth.interceptor;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.bank.core.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Value("${spring.redis.expireTime}")
    private int expireTime;//默认过期时间

    @Resource
    private RedisUtil redisUtil;

    /**
     * 在请求处理之前执行，主要用于权限验证、参数过滤等
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("CustomInterceptor ==> preHandle method: do request before");
        String token = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(token)){
            if (redisUtil.hasKey(token)){
                redisUtil.expire(token);
            }else {
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json; charset=utf-8");
                HashMap<Object, Object> map = MapUtil.newHashMap();
                map.put("code", 401);
                map.put("errorMsg", "token已过期，请重新登录！");
                map.put("status", false);
                response.getWriter().write(JSONUtil.toJsonPrettyStr(map));
                return false;
            }
        }

        return true;
    }

    /**
     * 当前请求进行处理之后执行，主要用于日志记录、权限检查、性能监控、通用行为等
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("CustomInterceptor ==> postHandle method: do request after");
    }

    /**
     * 当前对应的interceptor的perHandle方法的返回值为true时,postHandle执行完成并渲染页面后执行，主要用于资源清理工作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("CustomInterceptor ==> afterCompletion method: do request finshed");
    }
}
