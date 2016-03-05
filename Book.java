package com.example.cicctreads.midtermexam2;

/**
 * Created by cicctreads on 3/5/2016.
 */
public class Book {
    private String genre;
    private String id;
    private String Author;
    private String Title;


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }



    public Book(String genre, String id, String author, String title) {
        this.genre = genre;
        this.id = id;
        Author = author;
        Title = title;
    }



}
