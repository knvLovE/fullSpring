package com.sbrf.fullSpring.controller;

import com.sbrf.fullSpring.domain.Message;
import com.sbrf.fullSpring.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting (Map<String,Object> model){
        return "greeting";
    }
    @GetMapping("/main")
    public String Main (Map<String,Object> model){
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages",messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String,Object> model){
        Message message = new Message(text,tag);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages",messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages;
        if (filter == null || filter.isEmpty()){
            messages = messageRepo.findAll();
        } else {
            messages = messageRepo.findByTag(filter);
        }

        model.put("messages", messages);
        return "main";
    }
}
