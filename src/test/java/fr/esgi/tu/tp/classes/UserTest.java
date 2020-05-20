package fr.esgi.tu.tp.classes;

import fr.esgi.tu.tp.classes.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserTest {

    private User sut;
    private Item item;

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
    public void testIsPasswordIsTooShort(){
        this.sut.setPassword("aaa");
        Assert.assertFalse(sut.isValid());
    }

    @Test
    public void testIsPasswordIsTooLong(){
        this.sut.setPassword("aaaaaaaaaa"+"aaaaaaaaaa"+
                "aaaaaaaaaa"+"aaaaaaaaaa"+"aaaaaaaaaa");
        Assert.assertFalse(sut.isValid());
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

    @Test
    public void testCreateTodolistUserIsValid(){
        try {
            this.sut.createTodolist();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(this.sut.getTodolist());
    }

    @Test
    public void testCreateTodolistAlreadyCall(){
        Assert.assertThrows(Exception.class, () -> {
            this.sut.createTodolist();
            this.sut.createTodolist();
        });
    }

    @Test
    public void testCreateTodolistUserNotValid(){
        this.sut.setEmail("test.fr");
        Assert.assertThrows(Exception.class, () -> {
            this.sut.createTodolist();
        });
    }

    @Test
    public void testAddItemInTodolistNominal(){
        try {
            this.sut.createTodolist();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.item = Item.builder()
                .name("name")
                .content("this is the content")
                .createdAt(LocalDateTime.now())
                .build();

        try {
            this.sut.addItemInTodolist(this.item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1,this.sut.getTodolist().size());
    }

    @Test
    public void testAddItemInNullTodolist(){
        this.item = Item.builder()
                .name("name")
                .content("this is the content")
                .createdAt(LocalDateTime.now())
                .build();

        Assert.assertThrows(Exception.class, () -> {
            this.sut.addItemInTodolist(this.item);
        });
    }

    @Test
    public void testAddItemInFullTodolist(){
        try {
            this.sut.createTodolist();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertThrows(Exception.class, () -> {
            for(int i = 0; i<= 11; i++){
                this.sut.addItemInTodolist(Item.builder()
                        .name("name" + i)
                        .content("this is the content")
                        .createdAt(LocalDateTime.now())
                        .build());
            }
        });
    }

    //this is the test for canAddItem
    @Test
    public void testAddItemAlreadyInTodolist(){
        try {
            this.sut.createTodolist();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertThrows(Exception.class, () -> {
            for(int i = 0; i<= 2; i++){
                this.sut.addItemInTodolist(Item.builder()
                        .name("name")
                        .content("this is the content")
                        .createdAt(LocalDateTime.now().plusMinutes( i * 30))
                        .build());
            }
        });
    }

    @Test
    public void testCanAddOtherItemAfter30Min()  {
        Item item1 = Item.builder()
                .name("name")
                .content("this is the content")
                .createdAt(LocalDateTime.now().minusMinutes(30))
                .build();

        Item item2 = Item.builder()
                .name("name2")
                .content("this is the content")
                .createdAt(LocalDateTime.now())
                .build();

        try {
            this.sut.createTodolist();
            this.sut.addItemInTodolist(item1);
            Assert.assertEquals(item2,this.sut.canAddItem(item2));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCanAddOtherItemBefore30Min()  {
        Item item1 = Item.builder()
                .name("name")
                .content("this is the content")
                .createdAt(LocalDateTime.now().minusMinutes(29))
                .build();

        Item item2 = Item.builder()
                .name("name2")
                .content("this is the content")
                .createdAt(LocalDateTime.now())
                .build();

        try {
            this.sut.createTodolist();
            this.sut.addItemInTodolist(item1);
            Assert.assertNull(this.sut.canAddItem(item2));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
