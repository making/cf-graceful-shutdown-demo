package com.example;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
public class AllApplicationEventListener implements ApplicationListener<ApplicationEvent> {
	private final Logger log = LoggerFactory.getLogger(AllApplicationEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ServletRequestHandledEvent) {
			return;
		}
		log.info("Received {}", ToStringBuilder.reflectionToString(event));
	}
}
