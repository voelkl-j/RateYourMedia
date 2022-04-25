package com.rateyourmedia.rym_web;

import amin.aoulkadi.mychat.dtos.ConversationDTO;
import amin.aoulkadi.mychat.dtos.MessageDTO;
import com.rateyourmedia.rym_service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@ApplicationScope//Wichtig, da rymDiscConversation nach einmaliger Initialisierung beim Applikationsstart gleichbleiben soll.
@Controller
public class DiscussionController{
    @Autowired
    private DiscussionService discussionService;
    private ConversationDTO rymDiscConversation;
    @Value("#{environment.apiKey}")
    String apiKey;

    @RequestMapping(value = "/discussion/{page}", method = RequestMethod.GET)
    public String showdiscussionPage(@PathVariable("page") int page, Model model) {
        Pageable pageable= PageRequest.of(page, 5);
        List<MessageDTO> messages= discussionService.loadMessages(apiKey, rymDiscConversation, pageable);
        model.addAttribute("DiscMessages", messages);
        page=page+1;
        model.addAttribute("page", page);
        model.addAttribute("NewMessage", new MessageDTO());
        return "discussion";
    }
    @RequestMapping(value = "/discussion", method = RequestMethod.POST)
    public String sendMessage(@ModelAttribute("NewMessage") MessageDTO newMessage, Model model){
        discussionService.writeMessage(newMessage,rymDiscConversation,apiKey);
        System.out.println(rymDiscConversation.getConversationId());
        return showdiscussionPage(0, model);
    }

    public void creatingConversation(String apiKey){
        this.apiKey=apiKey;
        rymDiscConversation= discussionService.createConversation(apiKey, "RYM-Discussion");
    }
}