package br.gov.sp.fatec.server.controller;


import br.gov.sp.fatec.server.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/sendRecord")
    @SendTo("/topic/records")
    public Record broadcastRecord(Record record) {
        return record;
    }

    public void sendRecordUpdate(Record record) {
        template.convertAndSend("/topic/records", record);
    }
}
