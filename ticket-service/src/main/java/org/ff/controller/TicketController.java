package org.ff.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.ff.commonModule.dto.TicketDto;
import org.ff.commonModule.util.ApiResponse;
import org.ff.dao.TicketDao;
import org.ff.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RequestMapping("/api/tickets")
@AllArgsConstructor
@RestController
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<ApiResponse> createTicket(@RequestBody TicketDto ticketDto, @RequestParam(name = "email") String email){
        return ticketService.createTicket(ticketDto,email);
    }

}
