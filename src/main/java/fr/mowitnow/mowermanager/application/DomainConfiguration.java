package fr.mowitnow.mowermanager.application;

import fr.mowitnow.mowermanager.domain.ddd.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "fr.mowitnow.mowermanager"
        , includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = DomainService.class)}
)
public class DomainConfiguration {
}
