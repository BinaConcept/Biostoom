package eu.europa.ec.jrc.milc.utility;

import eu.europa.ec.jrc.milc.backoffice.service.MILCUserService;
import eu.europa.ec.jrc.milc.domain.MILCUser;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider implements ApplicationContextAware {

	ApplicationContext context;
	static MILCUserService service;

	public static MILCUser get(){
		return service.getCurrentUser();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
		service = this.context.getBean(MILCUserService.class);
	}
}
