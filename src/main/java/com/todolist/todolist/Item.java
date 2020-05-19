package com.todolist.todolist;
import lombok.*;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String name;
    private String content;
    private LocalDate createdAt;

    public boolean isValid(){
        return !this.name.isBlank() &&
                !this.content.isBlank() &&
                this.isDateValid();
    }

    private boolean isDateValid(){
        if(this.createdAt != null){
            return true;
        }
        return false;
    }
}
