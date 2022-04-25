package com.rateyourmedia.rym_apiClient;

import amin.aoulkadi.mychat.dtos.ConversationDTO;
import amin.aoulkadi.mychat.dtos.MessageDTO;
import amin.aoulkadi.mychat.services.interfaces.api.PublicConversationServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//Implementierung des Interfaces der MyChat Schnittstelle, für das Senden und Anzeigen von Messages im DiscussionForum
public class ConversationServiceImpl implements PublicConversationServiceIF {
    @Autowired
    private RestTemplate restServiceClient;

    @Override
    public MessageDTO[] getMessagesByConversation(String apiKey, long ConversationId, Pageable pageable) {
        int page=pageable.getPageNumber();
        int size=pageable.getPageSize();
        try{
            return restServiceClient.getForObject("http://im-codd.oth-regensburg.de:8982/api/rest/v1/conversations/{conversation}/messages?apiKey={apiKey}&page={page}&size={size}",MessageDTO[].class, ConversationId, apiKey, page, size);
        }
        catch (RestClientException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public MessageDTO sendMessage(String apiKey, MessageDTO messageDTO) {
        try{
            return restServiceClient.postForObject("http://im-codd.oth-regensburg.de:8982/api/rest/v1/messages?apiKey={apiKey}", messageDTO, MessageDTO.class, apiKey);
        }
        catch (RestClientException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ConversationDTO createConversation(String apiKey, String title) {
        try{
            ConversationDTO conversationDTO = new ConversationDTO();
            return restServiceClient.postForObject("http://im-codd.oth-regensburg.de:8982/api/rest/v1/conversations?apiKey={apiKey}&title={title}", conversationDTO, ConversationDTO.class, apiKey, title);
        }
        catch (RestClientException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Wird nicht benötigt
    @Override
    public MessageDTO sendMessage(String apiKey, long targetId, String message) {
        return null;
    }

}
