package fr.esgi.tu.tp.classes;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
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
        return StringUtils.isNotBlank(this.name)
                && StringUtils.isNotBlank(this.content)
                && this.isDateValid();
    }

    private boolean isDateValid(){
        if(this.createdAt != null){
            return true;
        }
        return false;
    }
}