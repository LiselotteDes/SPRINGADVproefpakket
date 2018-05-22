package be.vdab.proefpakket.messaging;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
class MessagingConfiguratie {
	
	@Bean
	Jaxb2Marshaller marshaller() {
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(ProefpakketMessage.class);
		return marshaller;
		
	}
	
	@Bean
	MarshallingMessageConverter converter(Jaxb2Marshaller marshaller) {
		
		return new MarshallingMessageConverter(marshaller, marshaller);
		
	}
	
	@Bean
	DefaultJmsListenerContainerFactory factory(ConnectionFactory connectionFactory, MarshallingMessageConverter converter) {
		
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(converter);
		return factory;
		
	}

}
