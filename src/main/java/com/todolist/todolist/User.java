package com.todolist.todolist;
import  lombok.*;
import org.apache.commons.validator.routines.EmailValidator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private LocalDate birthday;
    private ArrayList<Item> todolist;

    public boolean isValid(){
        return EmailValidator.getInstance().isValid(this.email) &&
                !this.lastname.isBlank() &&
                !this.firstname.isBlank() &&
                !this.password.isBlank() &&
                this.isPasswordValid() &&
                this.birthday != null &&
                LocalDate.now().minusYears(13).isAfter(this.birthday);
    }

    private boolean isPasswordValid(){
        if (this.password.length() > 8 && this.password.length() < 40){
            return true;
        }
        return false;
    }

    public void createTodolist() throws Exception {
        if(this.isValid()){
            if(this.todolist != null){
                this.todolist = new ArrayList<Item>();
            } else {
                throw new Exception("todolist already exist");
            }
        } else {
            throw new Exception("user is not valid");
        }

    }



}
