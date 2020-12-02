package corp.netizen.datastore.auth;

import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
public class CorsFilter implements javax.servlet.Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        response = CorsFilter.applyCorsHeaders(request, response);
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("XD");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public static HttpServletResponse applyCorsHeaders(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getHeader("Referer"));
        System.out.println(request.getHeader("Origin"));
        System.out.println(request.getMethod());
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "180");
        response.setHeader("Content-Type", "application/json");
        //application / json
        response.setHeader("Access-Control-Expose-Headers", "*");
        return response;
    }


    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


}
*/