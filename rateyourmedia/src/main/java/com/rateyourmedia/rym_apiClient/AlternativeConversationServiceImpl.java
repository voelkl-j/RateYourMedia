package com.rateyourmedia.rym_apiClient;

import amin.aoulkadi.mychat.dtos.AccountDTO;
import amin.aoulkadi.mychat.dtos.ConversationDTO;
import amin.aoulkadi.mychat.dtos.MessageDTO;
import amin.aoulkadi.mychat.exceptions.APIKeyException;
import amin.aoulkadi.mychat.exceptions.AccountNotFoundException;
import amin.aoulkadi.mychat.exceptions.ConversationNotFoundException;
import amin.aoulkadi.mychat.exceptions.MalformedAPICallException;
import amin.aoulkadi.mychat.services.interfaces.api.PublicConversationServiceIF;
import com.rateyourmedia.rym_entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

public class AlternativeConversationServiceImpl implements PublicConversationServiceIF {
    private MessageDTO[] messageDTOS;
    private int current;

    public AlternativeConversationServiceImpl(){
        messageDTOS= new MessageDTO[11];
        current=0;
        AccountDTO acc1=new AccountDTO();
        acc1.setScreenName("TestUser1");
        MessageDTO dto1=new MessageDTO();
        dto1.setSource(acc1);
        dto1.setText("Seems like there is no connection to myChat, but you still can send some Testmessages");
        messageDTOS[0]=dto1;
    }

    @Override
    public MessageDTO[] getMessagesByConversation(String apiKey, long conversationId, Pageable pageable) throws APIKeyException, ConversationNotFoundException {
        return messageDTOS;
    }



    @Override
    public MessageDTO sendMessage(String apiKey, MessageDTO message) throws APIKeyException, ConversationNotFoundException, AccountNotFoundException, MalformedAPICallException {
        if(current<11) {
            User writer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AccountDTO accDTO = new AccountDTO();
            accDTO.setScreenName(writer.getUsername());
            message.setSource(accDTO);
            messageDTOS[current]=message;
        }
            return null;
    }
    @Override
    public ConversationDTO createConversation(String apiKey, String title) throws APIKeyException, MalformedAPICallException {
        return null;
    }

    //Nicht benötigt:
    @Override
    public MessageDTO sendMessage(String apiKey, long targetId, String message) throws APIKeyException, AccountNotFoundException, MalformedAPICallException {
        return null;
    }
}
