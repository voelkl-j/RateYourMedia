package com.rateyourmedia.rym_apiClient;


import amin.aoulkadi.mychat.dtos.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//Implementierung des Interfaces der MyChat Schnittle, um Benutzer von RYM bei MyChat zu registrieren.
@Service
@Primary
public class PublicExternalUserServiceIFImpl implements PublicExternalUserServiceIF {
    @Autowired
    private RestTemplate restServiceClient;

    @Override
    public AccountDTO registerExternalUser(String apiKey, String screenName) {
        AccountDTO accountDTO =new AccountDTO();
        accountDTO.setScreenName(screenName);
        try {
            return restServiceClient.postForObject("http://im-codd.oth-regensburg.de:8982/api/rest/v1/external-users?apiKey={apiKey}&screenName={screenName}", accountDTO,AccountDTO.class,apiKey,screenName);
        } catch (RestClientException e){
            return null;
        }
    }
}
