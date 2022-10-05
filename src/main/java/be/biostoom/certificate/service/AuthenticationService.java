//package be.biostoom.certificate.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import be.biostoom.certificate.configuration.CompanyDetailsService;
//import be.biostoom.certificate.model.AuthenthicationResponse;
//import be.biostoom.certificate.model.AuthenticationRequest;
//import be.biostoom.certificate.model.Company;
//import be.biostoom.certificate.util.JwtUtil;
//
//@Service
//public class AuthenticationService  extends AbstractEmployeeService{
//
//	@Autowired
//	CompanyService companyService;
//
//	@Autowired
//	private AuthenticationManager authManager;
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Autowired
//	private CompanyDetailsService companyDetailsService;
//
//	@Autowired
//	private JwtUtil jwtUtil;
//
//	public Long register(Company company) {
//		String encryptedPwd = passwordEncoder.encode(company.getPassword());
//		company.setPassword(encryptedPwd);
//		return companyService.register(company);
//	}
//
//
//	public ResponseEntity<?> authenthicate(AuthenticationRequest req) throws Exception {
//		try {
//			authManager.authenticate(
//					new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
//
//		} catch (BadCredentialsException ex) {
//			throw new Exception("Incorrect username or password", ex);
//		}
//
//		final UserDetails details =  companyDetailsService.loadUserByUsername(req.getUsername());
//
//
//		final String  jwt= jwtUtil.generateToken(details);
//
//		AuthenthicationResponse response = new AuthenthicationResponse(jwt);
//
//
//		return ResponseEntity.ok(response);
//	}
//}
