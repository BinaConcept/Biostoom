package be.biostoom.certificate.util;

import be.biostoom.certificate.model.Company;
import be.biostoom.certificate.service.CompanyService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class currentCompanyProvider implements ApplicationContextAware {

	ApplicationContext context;
	static CompanyService service;

	public static Company getCompany(Long id){
		return service.getCompany(id);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
		service = this.context.getBean(CompanyService.class);
	}
}
