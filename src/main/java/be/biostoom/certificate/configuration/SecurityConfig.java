//package be.biostoom.certificate.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
//	@Autowired
//	private CompanyDetailsService userDetailsService;
//
//	@Autowired
//	JwtRequestFilter jwtRequestFilter;
//
//
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//
//		String[] permitted = new String[] {"/h2-console/**","/favicon.ico", "/swagger-ui/**","/v3/**", "/*.js","/*.jsp", "/*.css","/api/authentication", "/api/authentication/register", "/", "/api/booking/*","/api/image","/api/section","/css/**","/images/*","/home", "/pictureCheckCode", "/include/**", "/css/**",
//				"/icons/**", "/images/**", "/js/**", "/layer/**", "/public/**", "/assets/**" };
//		// We don't need CSRF for this example
//		httpSecurity.csrf().disable();
//		httpSecurity.cors().and()
//				// dont authenticate this particular request
//				.authorizeRequests()
//				.antMatchers("/**").permitAll()
//				/**
//				.antMatchers(HttpMethod.POST, "/api/authentication/register").permitAll()
//				.antMatchers(HttpMethod.POST, "/api/applicant/authentication").permitAll()
//				.antMatchers(HttpMethod.POST, "/api/applicant/start-permit").hasRole("EXTERNAL")
//				.antMatchers(HttpMethod.PUT, "/api/applicant/stop-permit").hasRole("EXTERNAL")
//				.antMatchers(HttpMethod.GET,"/api/applicant/site-introduction").hasRole("EXTERNAL")
//				.antMatchers(HttpMethod.GET,"/api/admin/permits").hasRole("BIOSTOOM")
//				.antMatchers(HttpMethod.GET,"/api/admin/permits/*").hasRole("BIOSTOOM")
//				.antMatchers(HttpMethod.PUT, "/api/admin/permits/start").hasRole("BIOSTOOM")
//				.antMatchers(HttpMethod.PUT, "/api/admin/permits/stop").hasRole("BIOSTOOM")
//				.antMatchers(HttpMethod.DELETE,"/api/admint/permits/*").hasRole("BIOSTOOM")
//
//				 **/
//				.anyRequest().authenticated().and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		httpSecurity.authenticationProvider(daoAuthenticationProvider());
//
//		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//		return httpSecurity.build();
//	}
//
//	@Bean
//	public BCryptPasswordEncoder encodePwd() {
//		return new BCryptPasswordEncoder();
//	}
//
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsService);
//		provider.setPasswordEncoder(encodePwd());
//		return provider;
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
//		return configuration.getAuthenticationManager();
//	}
//
//}
