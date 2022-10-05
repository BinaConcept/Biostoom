//package be.biostoom.certificate.configuration;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import be.biostoom.certificate.model.Company;
//import lombok.Data;
//
//@Data
//public class CompanyLoginDetails implements UserDetails{
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 1L;
//	private Company company;
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//
//		SimpleGrantedAuthority authority = company.getIsBioostoom() ? new SimpleGrantedAuthority("ROLE_BIOSTOOM")
//				: new SimpleGrantedAuthority("ROLE_EXTERNAL");
//
//		List<SimpleGrantedAuthority> authorites =
//				Arrays.asList(authority);
//		return authorites;
//	}
//
//	@Override
//	public String getPassword() {
//		return company.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return company.getEmail();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return company.isBlocked();
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
