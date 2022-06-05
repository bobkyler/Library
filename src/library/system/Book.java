/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system;

/**
 *
 * @author Kyle
 */
public class Book {
    private String bookID;
    private String title;
    private String author;
    
    public Book(){}
    
    public Book(String bookID, String title, String author){
        this.bookID = bookID;
        this.title = title;
        this.author = author;
    }
    
    public String getBookID(){
        return bookID;
    }
    public String getTitle(){
        return title;
    }public String getAuthor(){
        return author;
    }
}
