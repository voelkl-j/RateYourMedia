package com.rateyourmedia.rym_service.impl;

import amin.aoulkadi.mychat.dtos.AccountDTO;
import amin.aoulkadi.mychat.dtos.ConversationDTO;
import amin.aoulkadi.mychat.dtos.MessageDTO;
import amin.aoulkadi.mychat.services.interfaces.api.PublicConversationServiceIF;
import com.rateyourmedia.rym_entity.User;
import com.rateyourmedia.rym_service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private PublicConversationServiceIF conversationService;


    @Override
    public void writeMessage(MessageDTO newMessage, ConversationDTO conversationDTO, String apiKey) {
        if (conversationDTO != null) {
            User writer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AccountDTO accDTO = new AccountDTO();
            accDTO.setAccountId(writer.getChatID());
            newMessage.setSource(accDTO);
            newMessage.setConversation(conversationDTO);
            newMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));
            try {
                conversationService.sendMessage(apiKey, newMessage);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public List<MessageDTO> loadMessages(String apiKey, ConversationDTO conversationDTO, Pageable pageable) {
        System.out.println(conversationDTO.getConversationId());
        if (conversationDTO != null) {
            try {
                MessageDTO[] array = conversationService.getMessagesByConversation(apiKey, conversationDTO.getConversationId(), pageable);
                //Listen sind angenehmer bei Thymleaf in den Templates, da null Probleme bereiten kann.
                List<MessageDTO> list = new ArrayList<>(array.length);
                if (array.length == 0) {
                    return List.of();
                }
                for (int i = 0; i < array.length; i++) {
                    list.add(array[i]);
                }
                return list;
            } catch (Exception e) {
                System.out.println(e);
                return List.of();
            }
        }

        return List.of();
    }

    @Override
    public ConversationDTO createConversation(String apiKey, String title) {
        try {
            return conversationService.createConversation(apiKey, title);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
