//package be.biostoom.certificate.configuration;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class SecurityHeaderFilter implements Filter {
//
//	 @Override
//	    public void doFilter(ServletRequest request,
//	                        ServletResponse response,
//	                        FilterChain chain) throws IOException, ServletException
//	    {
//	        HttpServletRequest req = (HttpServletRequest) request;
//	        HttpServletResponse res = (HttpServletResponse) response;
//
//	        res.addHeader("X-Content-Type-Options", "nosniff");
//	        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//
//	        chain.doFilter(req, res);
//	    }
//
//
//}
