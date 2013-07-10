package de.htw_berlin.f4.ai.kbe.appconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;




@ComponentScan("de.htw_berlin.opentoken")
@Import(PersistenceJpaConfig.class)
@Configuration
public class AppConfig {
	
	@Autowired
	PersistenceJpaConfig persistenceJpaConfig;
	
	
	
}
