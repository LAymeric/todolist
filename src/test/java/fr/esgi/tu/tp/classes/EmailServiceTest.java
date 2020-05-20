package fr.esgi.tu.tp.classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class EmailServiceTest {
    private EmailService sut;

    @Before
    public void mySetUp() {
        this.sut = EmailService.builder().build();
    }


    @Test
    public void sendEmailNominal(){
        User user = User.builder()
                .firstname("fname")
                .lastname("lname")
                .email("test@test.fr")
                .password("azertyuiop")
                .birthday(LocalDate.now().minusYears(20))
                .build();

        Assert.assertTrue(this.sut.sendEmail(user));
    }

    @Test
    public void sendEmailWithUserNotMajor(){
        User user = User.builder()
                .firstname("fname")
                .lastname("lname")
                .email("test@test.fr")
                .password("azertyuiop")
                .birthday(LocalDate.now().minusYears(13))
                .build();

        Assert.assertFalse(this.sut.sendEmail(user));
    }
}
