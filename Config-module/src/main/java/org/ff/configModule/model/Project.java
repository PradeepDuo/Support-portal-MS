package org.ff.configModule.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ff.configModule.util.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Project extends BaseEntity {
    @Id
    private String id;
    private String projectName;
    private String description;
    private List<Ticket> tickets;

}
