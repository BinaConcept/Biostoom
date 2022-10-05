//package be.biostoom.certificate.util;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import be.biostoom.certificate.configuration.CompanyLoginDetails;
//import be.biostoom.certificate.model.Company;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//
//@Component
//public class JwtUtil {
////	private String SECRETE_KEY = "secret";
////
////	public String extractUsername(String token) {
////		return extractClaim(token, Claims::getSubject);
////	}
////
////	public Date extractExpiration(String token) {
////		return extractClaim(token, Claims::getExpiration);
////
////	}
////
////	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
////		final Claims claims = extractAllClaims(token);
////		return claimsResolver.apply(claims);
////	}
////
////	private Claims extractAllClaims(String token) {
////		return Jwts.parser().setSigningKey(SECRETE_KEY).parseClaimsJwt(token).getBody();
////	}
////
////	private Boolean isTokenExpired(String token) {
////		return extractExpiration(token).before(new Date());
////	}
////
////	public String generateToken(UserDetails details) {
////		Map<String, Object> claims = new HashMap<String, Object>();
////		return createToken(claims, details.getUsername());
////	}
////
////	public String createToken(Map<String, Object> claims, String subject) {
////		return Jwts.builder().setClaims(claims)
////				.setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
////				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
////				.signWith(SignatureAlgorithm.HS256, SECRETE_KEY).compact();
////	}
////
////	public Boolean validateToken(String token, UserDetails details) {
////		final String username = extractUsername(token);
////		return (username.equals(details.getUsername()) && ! isTokenExpired(token));
////	}
//
//	private static final long serialVersionUID = -2550185165626007488L;
//
//	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//
//	private String SECRETE_KEY = "secret";
//
//	//retrieve username from jwt token
//	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token, Claims::getSubject);
//	}
//
//	//retrieve expiration date from jwt token
//	public Date getExpirationDateFromToken(String token) {
//		return getClaimFromToken(token, Claims::getExpiration);
//	}
//
//	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = getAllClaimsFromToken(token);
//		return claimsResolver.apply(claims);
//	}
//    //for retrieveing any information from token we will need the secret key
//	private Claims getAllClaimsFromToken(String token) {
//		return Jwts.parser().setSigningKey(SECRETE_KEY).parseClaimsJws(token).getBody();
//	}
//
//	//check if the token has expired
//	private Boolean isTokenExpired(String token) {
//		final Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}
//
//	//generate token for user
//	public String generateToken(UserDetails loginDetails) {
//		Company company = ((CompanyLoginDetails)loginDetails).getCompany();
//		Map<String, Object> claims = new HashMap<>();
//		claims.put("companyName", company.getName());
//		claims.put("email", company.getEmail());
//		claims.put("isAdmin", company.getIsBioostoom());
//
//		return doGenerateToken(claims, loginDetails.getUsername());
//	}
//
//	//while creating the token -
//	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
//	//2. Sign the JWT using the HS512 algorithm and secret key.
//	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//	//   compaction of the JWT to a URL-safe string
//	private String doGenerateToken(Map<String, Object> claims, String subject) {
//
//		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//				.signWith(SignatureAlgorithm.HS512, SECRETE_KEY).compact();
//	}
//
//	//validate token
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = getUsernameFromToken(token);
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}
//
//
//}
