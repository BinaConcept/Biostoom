//package be.biostoom.certificate.configuration;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import be.biostoom.certificate.util.JwtUtil;
//
//
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//	@Autowired
//	JwtUtil util;
//
//	@Autowired
//	CompanyDetailsService detailService;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		final String authorizationHeader = request.getHeader("Authorization");
//		String companyUserName = null;
//		String jwt = null;
//
//		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
//			jwt = authorizationHeader.substring(7);
//			companyUserName = util.getUsernameFromToken(jwt);
//		}
//
//		if (companyUserName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			UserDetails userDetails = detailService.loadUserByUsername(companyUserName);
//
//			if (util.validateToken(jwt, userDetails)) {
//				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//
//				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//			}
//
//		}
//
//		filterChain.doFilter(request, response);
//
//	}
//
//}
