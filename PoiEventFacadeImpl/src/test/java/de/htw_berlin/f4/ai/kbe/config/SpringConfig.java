package de.htw_berlin.f4.ai.kbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import de.htw_berlin.f4.ai.kbe.poievent.PoiEventFacade;

import de.htw_berlin.f4.ai.kbe.poievent.PoiEventFacadeImpl;
import de.htw_berlin.f4.ai.kbe.appconfig.AppConfig;

@Configuration
@Import(AppConfig.class) 
public class SpringConfig {

	@Bean
	PoiEventFacade poiEventFacade(){
		return new PoiEventFacadeImpl();
	}
	
}
 