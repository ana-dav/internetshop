package internetshop.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import internetshop.lib.Inject;
import internetshop.lib.Injector;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.UserService;

public class AuthorisationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR =
            Injector.getInstance("internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    private Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //what for I use here Set??
        protectedUrls.put("/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/admin", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/order/complete", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestedUrl = req.getServletPath();
        if(protectedUrls.get(requestedUrl) == null) {
            chain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null || userService.get(userId) == null) {
            resp.sendRedirect("/login");
            return;
        }
        User user = userService.get(userId);

        if (isAutorized(user, protectedUrls.get(requestedUrl))) {
            chain.doFilter(req, resp);
            return;
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAutorized (User user, Set<Role.RoleName> authorizedRoles) {
        //autorizedRoles = ADMIN, MANAGER
        //user roles = ADMIN, USER
        for(Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole: user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }

            }
        }
        return false;
    }
}
