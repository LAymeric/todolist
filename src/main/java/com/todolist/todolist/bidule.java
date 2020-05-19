package com.todolist.todolist;

import org.springframework.cglib.core.Local;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class bidule {
    public String password;

    public static void main(String[] args) throws Exception {
        String s = "aaaaaaaaa";
        s.length();
        ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        list.size();
        LocalDate t;
//        throw new Exception("All");
    }
    
    private boolean isPasswordValid(){
        if (this.password.length() > 8 && this.password.length() < 40){
            return true;
        }
        return false;
    }
}
