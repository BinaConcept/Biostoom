package be.biostoom.certificate.configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfiguration  implements WebMvcConfigurer{

	private static final String[] CLASSPATH_RESOURCE_LOCATION = { "classpath:/static/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATION)
		.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
		.resourceChain(true)
		.addResolver(new PathResourceResolver(){
			@Override
			protected Resource getResource(String resourcePath,Resource location) throws IOException{
				Resource requested = location.createRelative(resourcePath);
				return requested.exists() && requested.isReadable() ? requested : new ClassPathResource("static/index.html");
			}
		});
		
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
}
