package fr.esgi.tu.tp.classes;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private LocalDate birthday;
    private ArrayList<Item> todolist = null;

    public boolean isValid() {
        return EmailValidator.getInstance().isValid(this.email)
                && StringUtils.isNotBlank(this.firstname)
                && StringUtils.isNotBlank(this.lastname)
                && StringUtils.isNotBlank(this.password)
                && this.isPasswordValid()
                && this.birthday != null
                && LocalDate.now().minusYears(13).isAfter(this.birthday);
    }

    private boolean isPasswordValid(){
        if (this.password.length() > 8 && this.password.length() < 40){
            return true;
        }
        return false;
    }

    public void createTodolist() throws Exception {
        if(this.isValid()){
            if(this.todolist == null){
                this.todolist = new ArrayList<Item>();
            } else {
                throw new Exception("todolist already exist");
            }
        } else {
            throw new Exception("user is not valid");
        }
    }

    public void addItemInTodolist(Item item) throws Exception {
        if(this.todolist == null) {
            throw  new Exception("todolist is nor create");
        }
        if(this.canAddItem(item) == null){
            throw new Exception("item is already in todolist");
        }
        if(this.todolist.size()<=10){
            this.todolist.add(item);
        } else {
            throw new Exception("todolist is full");
        }
    }

    public Item canAddItem(Item item) throws Exception{

        if(this.todolist.size() > 0){
            Item last = this.todolist.get(this.todolist.size()-1);
            if(!(last.getCreatedAt().isBefore(LocalDateTime.now().minusMinutes(30)))){
                return null;
            }
        }

        Item found = this.todolist.stream().filter(it -> item.getName().equals(it.getName())).findAny().orElse(null);

        if(found != null){
            return null;
        }
        return item;
    }

}
