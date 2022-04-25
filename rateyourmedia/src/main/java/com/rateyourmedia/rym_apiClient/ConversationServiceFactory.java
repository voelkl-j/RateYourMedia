package com.rateyourmedia.rym_apiClient;

import amin.aoulkadi.mychat.services.interfaces.api.PublicConversationServiceIF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversationServiceFactory {
    @Value("#{environment.apiKey}")
    String apiKey;
    String empty="";
    @Bean
    public PublicConversationServiceIF getConversation(){
        if(apiKey.equals(empty)){
            return new AlternativeConversationServiceImpl();
        }
        else return new ConversationServiceImpl();
    }
}
