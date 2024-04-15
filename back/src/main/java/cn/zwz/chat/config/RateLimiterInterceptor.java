package cn.zwz.chat.config;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.revinate.guava.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class RateLimiterInterceptor implements HandlerInterceptor {

    private static final Log log = LogFactory.get();

    /**
     * 令牌桶里放50个令牌，外界调用本系统所有接口，都得先在桶里获取令牌
     * 拿到令牌后，才可以正常调用接口，为了限流
     */
    private static final RateLimiter limiter = RateLimiter.create(50);



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 阻塞等待
        log.info("阻塞中……");
        limiter.acquire();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
