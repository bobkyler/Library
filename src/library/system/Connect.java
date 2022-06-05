package library.system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Kyle
 */
public class Connect {
    Connection conn;
    
    public Connect(){
        try{
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Library", "administrator", "qwerty");
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    //user Add AddAccount
    public boolean addAccount(Account acc){
        Statement stmt;
        String sql = "insert into useracc values('" + acc.getID() + "', '" + acc.getFirstname() + "', '" + acc.getMiddlename() + "', '" 
               + acc.getLastname() + "', '" + acc.getTelephone() + "', '" + acc.getUsername() + "', '" + acc.getPassword() + "')";
    
        try{
           if(checkID(acc.getID()) == false && checkUser(acc.getUsername()) == false){
                stmt = conn.createStatement();
                stmt.execute(sql);
                return true;
           }
        }catch (SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    public boolean checkID(String id){
        Statement stmt;
        ResultSet rs = null;
        String sql = "select * from useracc where id='" + id + "'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    public boolean checkUser(String username){
        Statement stmt;
        ResultSet rs = null;
        String sql = "select * from useracc where username='" + username + "'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    //User Login
    public Account login(String username, String password){
        Statement stmt = null; 
        ResultSet rs = null;
        Account acc = null;
        String sql = "select * from useracc where username='" + username + "' and password='"+password+"'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);  
            if(rs.next()){
                acc = new Account(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                rs.getString(6),rs.getString(7));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return acc;
    }
    
    public ArrayList displayAllUser(){
        ArrayList<Account> u = new ArrayList<Account>();
        Statement stmt;
        String sql = "select * from useracc";
        ResultSet rs = null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Account c = new Account(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)
                    ,rs.getString(6), rs.getString(7));
                    u.add(c);
                }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return u;
    }
    
    public boolean updateUser(Account u){
        Statement stmt;
        String sql = "update useracc set firstname='"+u.getFirstname()+ "', " +"middlename='"+u.getMiddlename()+ "', " 
                + "lastname='"+u.getLastname()+ "', "+ "telephone='" + u.getTelephone()+"' where ID='" + u.getID()+"'";
        try{
            stmt = conn.createStatement();   
            stmt.executeUpdate(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    public boolean deleteUser(String userID){
        String sql = "delete from useracc where ID='"+ userID+ "'";
        PreparedStatement stmt;
        try{
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    //Admin Login
    public boolean loginAdmin(String username, String password){
        Statement stmt = null; 
        ResultSet rs = null;
        Account acc = null;
        String sql = "select * from adminacc where username='" + username + "' and password='"+password+"'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);  
            if(rs.next()){
               return true;
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    //Admin Add Book 
    public boolean addBook(String bookid, String title, String author){
        Statement stmt;
        String sql = "insert into booklist values('"+bookid+"', '"+title+"', '"+author+"')";
        try{
            stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    //Admin Update Book
    public boolean updateBook(String bookid, String title, String author){
        Statement stmt;
        String sql = "update booklist set title='"+title+"', author='"+author+"' where bookid='"+bookid+"'";
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    //Admin Delete Book
    public boolean deleteBook(String bookid){
        Statement stmt;
        String sql = "delete from booklist where bookid='"+bookid+"'";
        try{
            stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    //for borrowbooks
    public ArrayList getAllBooks(){
        ArrayList<Book> book = new ArrayList<Book>();
        Statement stmt;
        ResultSet rs = null;
        String sql = "select * from booklist";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Book a = new Book(rs.getString(1),rs.getString(2), rs.getString(3));
                book.add(a);
            } 
 
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return book;
    }
    //storing borrow book transaction
    public boolean addBorrowBook(Book currBook, Account user, String date){
        Statement stmt;
        String sql = "insert into borrowbooks values('"+currBook.getBookID()+"', '"+currBook.getTitle()+"', '"
                    +currBook.getAuthor()+"', '"+user.getID()+"', '"
                    +user.getFirstname()+" "+user.getMiddlename()+" "+user.getLastname()+"', '"+date+"')";
        try{
            stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    //checking the availability of the book
    public boolean availableBook(Book currBook){
        Statement stmt;
        ResultSet rs = null;
        String sql = "select * from borrowbooks";   //cheking in the Borrow Book table
        boolean check = false;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getString(1).equals(currBook.getBookID())){
                    return true;        //book is already borrowed
                }
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    //Return Book
    public ArrayList getBorrowedBooks(Account user){
        ArrayList<Book> book = new ArrayList<Book>();
        Statement stmt;
        ResultSet rs = null;
        String sql = "select * from borrowbooks where borrowerid='" +user.getID()+ "'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Book a = new Book(rs.getString(1),rs.getString(2), rs.getString(3));
                book.add(a);
            } 
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return book;
    }
    public boolean returnBook(Account user, Book currBook, String condition, String date){
        Statement stmt;
        ResultSet rs=null;
        String sql;
        int transID = 0;
        try{
            sql= "delete from borrowbooks where borrowerid='"+ user.getID()+ "' and bookid='"+currBook.getBookID()+"'";
            stmt = conn.createStatement();
            stmt.execute(sql);
            
            sql = "select transid from returnedbooks order by transid desc";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()==true){
                transID = rs.getInt(1)+1;
            }else{
                transID = 1;
            }
            
            
            sql = "insert into returnedbooks values("+ transID+ ", '"+user.getID()+"', '"+currBook.getBookID()+"', '"
                    +currBook.getTitle()+"', '"+currBook.getAuthor()+"', '"
                    +user.getFirstname()+user.getMiddlename()+user.getLastname()+"', '"+condition+"', '"
                    +date+"')";
            stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    //for Book Reserve
    public boolean reserve(Account user, Book currBook, String reserveDate, String dueDate){
        Statement stmt;
        ResultSet rs = null;
        int transID = 0;
        String sql;
        try{
            sql = "select transactionid from bookreservation order by transactionid desc";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()==true){
                transID = rs.getInt(1)+1;
            }else{
                transID = 1;
            }
            
            sql = "insert into bookreservation values("+transID + ", '"+currBook.getBookID() + "', '"
                    +currBook.getTitle() + "', '" +currBook.getAuthor() + "', '" +user.getID() + "', '"
                    +reserveDate + "', '"+dueDate + "')";
            stmt.execute(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
    
    //Record
    public ResultSet borrowDB(Account user){
        ResultSet rs = null;
        Statement stmt;
        String sql = "select * from borrowbooks where borrowerid='" +user.getID()+ "'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return rs;
    }
    
    public ResultSet returnDB(Account user){
        ResultSet rs = null;
        Statement stmt;
        String sql = "select * from returnedbooks where borrowerid='"+user.getID()+"'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return rs;
    }
    public ResultSet reserveDB(Account user){
        ResultSet rs = null;
        Statement stmt;
        String sql = "select * from bookreservation where borrowerid='"+user.getID()+"'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return rs;
    }
    
    public boolean deleteReservation(Account user, String bookID, String date){
        Statement stmt;
        String sql = "delete from bookreservation where borrowerid='"+user.getID()+ "' and bookid='"+bookID
        +"' and reservedate='"+date+"'";
        try{
            stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }
   
}
