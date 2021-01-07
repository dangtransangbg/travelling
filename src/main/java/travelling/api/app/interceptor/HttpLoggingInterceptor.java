package travelling.api.app.interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import travelling.api.app.service.HttpLoggingService;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpLoggingInterceptor implements HandlerInterceptor {

    private final HttpLoggingService httpLoggingService;

    public HttpLoggingInterceptor(HttpLoggingService httpLoggingService) {
        this.httpLoggingService = httpLoggingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name()) && request.getMethod().equals(HttpMethod.GET.name())) {
            httpLoggingService.logRequest(request, null);
        }

        return true;
    }

    private boolean isLogin(HttpServletRequest request) {
        return request.getRequestURI().contains("/auth")
                || request.getRequestURI().contains("/customer/auth")
                && request.getMethod().equalsIgnoreCase(HttpMethod.POST.toString());
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
