import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class UserTest {

    private User sut;

    @Before
    public void mySetUp() {
        this.sut = User.builder()
                .firstname("fname")
                .lastname("lname")
                .email("test@test.fr")
                .password("azertyuiop")
                .birthday(LocalDate.now().minusYears(20))
                .build();
    }

    @Test
    public void testIsValidNominal() {
        Assert.assertTrue(this.sut.isValid());
    }

    @Test
    public void testIsNotValidBirthDayNull() {
        this.sut.setBirthday(null);
        Assert.assertFalse(sut.isValid());
    }

    @Test
    public void testIsNotValidUserTooYoung() {
        this.sut.setBirthday(LocalDate.now().minusYears(5));
        Assert.assertFalse(sut.isValid());
    }

    @Test
    public void testIsNotValidBadEmailFormat() {
        this.sut.setEmail("test.fr");
        Assert.assertFalse(sut.isValid());
    }
}
