package fa.training.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissiveCORSFilter implements Filter {

	private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9 ,-_]*$");

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		String origin;
		String credentialFlag;
		if (request.getHeader("Origin") == null) {
			origin = "*";
			credentialFlag = "false";
		} else {
			origin = request.getHeader("Origin");
			credentialFlag = "true";
		}

		// need to do origin.toString() to avoid findbugs error about response splitting
		response.addHeader("Access-Control-Allow-Origin", origin.toString());
		response.setHeader("Access-Control-Allow-Credentials", credentialFlag);
		if ("OPTIONS".equals(request.getMethod())) {
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,HEAD,OPTIONS,PUT,DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			String headers = request.getHeader("Access-Control-Request-Headers");
			if (!PATTERN.matcher(headers).matches()) {
				throw new ServletException("Invalid value provided for 'Access-Control-Request-Headers' header");
			}
			response.setHeader("Access-Control-Allow-Headers", headers); // allow any headers
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) {
		// Do nothing
	}

	@Override
    public void destroy() {
        // Do nothing
    }
}