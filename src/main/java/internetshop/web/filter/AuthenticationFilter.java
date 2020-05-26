package internetshop.web.filter;

import internetshop.lib.Injector;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final Set<String> publicUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        publicUrls.add("/login");
        publicUrls.add("/registration");
        publicUrls.add("/logout");
        publicUrls.add("/injectData");
        publicUrls.add("/products/all");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getServletPath();
        if (publicUrls.contains(url)) {
            chain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null || userService.get(userId) == null) {
            resp.sendRedirect("/login");
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
