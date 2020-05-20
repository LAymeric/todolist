package fr.esgi.tu.tp.classes;

import lombok.*;

import java.time.LocalDate;

@Builder
public class EmailService {


    public boolean sendEmail(User user){
           if(LocalDate.now().minusYears(18).isAfter(user.getBirthday())){
               return true;
           }
           return false;
    }
}
