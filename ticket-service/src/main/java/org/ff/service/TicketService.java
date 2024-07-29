package org.ff.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.ff.configModule.dto.TicketDto;
import org.ff.configModule.model.Project;
import org.ff.configModule.model.Ticket;
import org.ff.configModule.model.Users;
import org.ff.configModule.util.ApiResponse;
import org.ff.configModule.util.ResponseUtil;
import org.ff.dao.TicketDao;
import org.ff.feign.ProjectClient;
import org.ff.feign.UserClients;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@AllArgsConstructor
public class TicketService {
    private final ModelMapper modelMapper;
    private final UserClients userClients;
    private final ProjectClient projectClient;
    private final TicketDao ticketDao;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public ResponseEntity<ApiResponse> createTicket(TicketDto ticketDto, String email) {
        System.out.println(ticketDto.getProjectId());
        Object project1 = projectClient.getProject(ticketDto.getProjectId()).getBody().getResponse();
       Project project= modelMapper.map(project1,Project.class);
        Ticket ticket = modelMapper.map(ticketDto,Ticket.class);
        Users users= (Users)userClients.getUsersByEmail(email).getBody();
        ticket.setLocalDateTime(LocalDateTime.now().toString());
        Ticket savedTicket = ticketDao.saveTicket(ticket);
        List<Ticket>  tickets = project.getTickets() != null ? project.getTickets() : new ArrayList<>();
        tickets.add(savedTicket);
        project.setTickets(tickets);
        log.info(project+" Project Object in Ticket service before kafka");

//      using Kafka template to update the project with new Ticket
        CompletableFuture<SendResult<String, Object>> tikcet = kafkaTemplate.send("ticket", project);
        System.out.println(ticket +"ticket by kafka template");
//      using feign client to update the project with new Ticket
//      Project result= projectClient.flushProject(project).getBody();
        return ResponseUtil.getOkResponse(project);



    }
}
