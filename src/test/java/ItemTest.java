import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class ItemTest {

    private Item sut;

    @Before
    public void mySetUp() {
        this.sut = Item.builder()
                .name("name")
                .content("this is the content")
                .createdAt(LocalDate.now())
                .build();
    }

    @Test
    public void testIsValidNominal() {
        Assert.assertTrue(this.sut.isValid());
    }

    @Test
    public void testIsNotValidName(){
        this.sut.setName("");
        Assert.assertFalse(this.sut.isValid());
    }

    @Test
    public void testIsNotValidContent(){
        this.sut.setContent("");
        Assert.assertFalse(this.sut.isValid());
    }

    @Test
    public void testIsNotValidDate(){
        this.sut.setCreatedAt(null);
        Assert.assertFalse(this.sut.isValid());
    }



}
