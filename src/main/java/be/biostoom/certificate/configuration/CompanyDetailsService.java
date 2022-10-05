//package be.biostoom.certificate.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import be.biostoom.certificate.model.Company;
//import be.biostoom.certificate.service.CompanyService;
//
//
//@Service
//public class CompanyDetailsService  implements UserDetailsService{
//
//	@Autowired
//	private CompanyService companyService;
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Company company = companyService.getCompanyByEmail(email);
//		CompanyLoginDetails  companyDetails = null;
//		if(company!=null) {
//			companyDetails = new CompanyLoginDetails();
//			companyDetails.setCompany(company);
//		} else {
//			throw new UsernameNotFoundException("User not found");
//		}
//		return companyDetails;
//	}
//
//
//}
