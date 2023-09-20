package com.api.shop_project.controller.chat;

import com.api.shop_project.dto.request.chat.ClientMessage;
import com.api.shop_project.dto.response.chat.BotMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
/*@RequestMapping("/chat")*/
public class ChatBotController {

    @GetMapping("/chat")
    public String chat(){
        return "chat/chat-bot";
    }

    @MessageMapping("/hello") // /app/hello
    @SendTo("/topic/greetings")
    public BotMessage greeting(ClientMessage message) throws Exception {
        Thread.sleep(50);
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formattedDay = today.format(formatter);
        String formattedtime = today.format(DateTimeFormatter.ofPattern("a H:mm"));

        // 처음 실행 되는 -> 답장문
        return new BotMessage(
                "<div class='flex center date'>" + formattedDay + "</div>" +
                        "<div class='msg bot flex'>" +
                        "<div class='icon'>" +
                        "<img src='/images/kurlylogo.jpg'   th:alt=\"#{chat}\" />" +
                        "</div>" +
                        "<div class='message'>" +
                        "<div class='part'>" +
                        "<p style='text-align:center'>안녕하세요. 1대1 상담입니다.. <br> 상담 내용을 작성해주세요." +
                        "</div>" +
                        "<div class='part'>" +
                        "<p>자주하는 질문</p>" +
                        "<div class='flex center menu'>" +
                        "<div class='menu-item'><span onclick='menuclickFn(event)'>환불</span></div>" +
                        "<div class='menu-item'><span onclick='menuclickFn(event)'>결제취소</span></div>" +
                        "<div class='menu-item'><span onclick='menuclickFn(event)'>배송</span></div>" +
                        "</div>" +
                        "</div>" +
                        "<div class='time'>" +
                        formattedtime +
                        "</div>" +
                        "</div>" +
                        "</div>");

    }

    @MessageMapping("/message") // app/message
    @SendTo("/topic/message")  // stompClient.subscribe
    public BotMessage message(ClientMessage message) throws Exception{
        Thread.sleep(100);  // simulated delay
        LocalDateTime today = LocalDateTime.now();
        String formattedtime = today.format(DateTimeFormatter.ofPattern("a H:mm"));

        String responseText = message.getContent() + " 대한 답장입니다.";

        //        if(responseText.contains("영화")){
        //
//        }

        return new BotMessage(
                "<div class='msg bot flex'>"+
                        "<div class='icon'>"+
                        "<img src='/images/kurlylogo.jpg'   th:alt=\"#{chat}\" />" +
                        "</div>"+
                        "<div class='message'>"+
                        "<div class='part'>"+
                        "<p>" + responseText + "</p>"+
                        "</div>"+
                        "<div class='time'>"+
                        formattedtime+
                        "</div>"+
                        "</div>"+
                        "</div>");

    }

}



