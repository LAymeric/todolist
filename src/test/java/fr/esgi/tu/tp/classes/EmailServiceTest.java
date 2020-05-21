package fr.esgi.tu.tp.classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmailServiceTest {

//    @Mock
    private EmailService sut;

    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
        this.sut = EmailService.builder().build();
    }

    @Test
    public void testSendNominal(){
        Assert.assertThrows(Exception.class, () ->{
            this.sut.send();
        });
    }

    @Test
    public void testMockSendTrue() throws Exception {
        EmailService emailService = Mockito.mock(EmailService.class);
        Mockito.when(emailService.send()).thenReturn(true);
//        Mockito.when(this.sut.send()).thenReturn(true);
        Assert.assertTrue(emailService.send());
    }

    @Test
    public void testMockSendFalse() throws Exception {
        EmailService emailService = Mockito.mock(EmailService.class);
        Mockito.when(emailService.send()).thenReturn(false);
        Assert.assertFalse(emailService.send());
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
