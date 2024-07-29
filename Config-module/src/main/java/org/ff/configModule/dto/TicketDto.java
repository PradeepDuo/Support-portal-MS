package org.ff.configModule.dto;

import lombok.Data;

@Data
public class TicketDto {
    private String issue;
    private String description;
    private String projectId;
}
