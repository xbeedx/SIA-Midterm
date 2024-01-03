package cors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Allow requests from any origin
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");

        // Allow standard HTTP methods
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // Allow standard HTTP headers
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Allow cookies and credentials
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        // Handle preflight requests (OPTIONS)
        if ("OPTIONS".equalsIgnoreCase(httpResponse.getHeader("Access-Control-Request-Method"))) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // Continue with the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
