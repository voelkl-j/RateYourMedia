package com.rateyourmedia.rym_service;

import amin.aoulkadi.mychat.dtos.ConversationDTO;
import amin.aoulkadi.mychat.dtos.MessageDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscussionService {
    void writeMessage(MessageDTO newMessage, ConversationDTO conversationDTO, String apiKey);
    List<MessageDTO> loadMessages(String apiKey, ConversationDTO conversationDTO, Pageable pageable);
    ConversationDTO createConversation(String apiKey, String title);
}
