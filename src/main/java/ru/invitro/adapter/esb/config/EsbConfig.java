package ru.invitro.adapter.esb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Общий конфигурационный класс для ESB.
 */
@Configuration
@Import({ EsbOutgoingConfig.class })
public class EsbConfig {

}
