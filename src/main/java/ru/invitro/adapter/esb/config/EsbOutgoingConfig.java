package ru.invitro.adapter.esb.config;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.common.gzip.GZIPOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.invitro.armps.integration.common.validation.IgnoreUnexpectedElementsHandler;
import ru.invitro.armps.integration.esb.remote.IPublicationService;
import ru.invitro.armps.integration.esb.spring.proxy.JaxWsTimeoutProxyFactoryBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Конфигурационный класс отправки данных в ESB.
 */
@EnableAsync
@Configuration
public class EsbOutgoingConfig {

    private String publicationServiceUrl = "http://10.10.10.217:8090/InvitroESB6Services/PublicationService.svc";

    @Bean("integration-esb-outgoing-publication-service")
    public IPublicationService outgoingPublicationService() {
        return (IPublicationService) getPublicationServiceFactory().create();
    }

    @Bean("publicationExecutor")
    public ThreadPoolTaskExecutor publicationExecutor() {
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        return threadPoolTaskExecutor;
    }

    private JaxWsTimeoutProxyFactoryBean getPublicationServiceFactory() {
        final JaxWsTimeoutProxyFactoryBean factory = new JaxWsTimeoutProxyFactoryBean();
        factory.setServiceClass(IPublicationService.class);
        factory.setAddress(publicationServiceUrl);
        factory.setOutInterceptors(getPublicationServiceOutInterceptors());
        factory.setProperties(getPublicationServiceProperties());
        return factory;
    }

    private List<Interceptor<? extends Message>> getPublicationServiceOutInterceptors() {
        final List<Interceptor<? extends Message>> interceptors = new ArrayList<>();
        interceptors.add(new GZIPOutInterceptor());
        return interceptors;
    }

    private Map<String, Object> getPublicationServiceProperties() {
        final Map<String, Object> properties = new HashMap<>();
        properties.put("org.apache.cxf.transport.no_io_exceptions", true);
        properties.put("jaxb-validation-event-handler", new IgnoreUnexpectedElementsHandler());
        return properties;
    }

}