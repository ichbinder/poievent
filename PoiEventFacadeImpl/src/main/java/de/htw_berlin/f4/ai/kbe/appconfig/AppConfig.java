package de.htw_berlin.f4.ai.kbe.appconfig;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import de.htw_berlin.f4.ai.kbe.appconfig.PersistenceJpaConfig;




@ComponentScan("de.htw_berlin.aStudent")
@Import(PersistenceJpaConfig.class)
@Configuration
public class AppConfig {
	
	
	
	
}
