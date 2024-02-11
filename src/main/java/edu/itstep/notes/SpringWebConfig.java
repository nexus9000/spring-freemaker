package edu.itstep.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

//@EnableWebMvc
@EnableAutoConfiguration
@Configuration
@ComponentScan({ "edu.itstep.notes" })
@EnableConfigurationProperties(NoteProperties.class)
public class SpringWebConfig extends WebMvcConfigurationSupport {
	@Autowired
	private NoteProperties properties;
	@Bean
	public FreeMarkerViewResolver freemarkerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftlh");
		return resolver;
	}

//	@Bean
//	public FreeMarkerConfigurer freeMarker() {
//		var freeMarkerConf = new FreeMarkerConfigurer();
//		freeMarkerConf.setTemplateLoaderPath("classpath:/templates/");
//
//		return freeMarkerConf;
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("uploads/**")
		.addResourceLocations("file:"+properties.getUploadDir())
		.setCachePeriod(3600)
		.resourceChain(true).addResolver(new PathResourceResolver());
//		registry.addResourceHandler("/resources/**")
//        .addResourceLocations("/resources/");
//		registry.addResourceHandler("/css/**").addResourceLocations("/");
		
	}

}
