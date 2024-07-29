package org.ff.configModule.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class BaseEntity {
    String createdOn;
    String lastUpdateOn;
    String createdByName;
    String createdByEmail;
    String modifiedByName;
    String modifiedByEmail;

    public void setCreateEntity(String userName,String userEmail){
        this.createdByEmail=userEmail;
        this.createdByName=userName;
        this.createdOn = LocalDateTime.now().toString();

    }

    public void setModifiedEntity(String userName, String userEmail){
        this.modifiedByEmail = userEmail;
        this.modifiedByName = userName;
        this.lastUpdateOn = LocalDateTime.now().toString();
    }
}
