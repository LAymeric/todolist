package fr.esgi.tu.tp.classes;

import fr.esgi.tu.tp.classes.Item;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ItemTest {

    private Item sut;

    @Before
    public void mySetUp() {
        this.sut = Item.builder()
                .name("name")
                .content("this is the content")
                .createdAt(LocalDateTime.now())
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

    @Test
    public void testIsNotValidContentTooLong(){
        String s = "";
        for(int i = 0; i< 1003; i++){
            s += "a";
        }
        this.sut.setContent(s);
        Assert.assertFalse(this.sut.isValid());
    }

}
