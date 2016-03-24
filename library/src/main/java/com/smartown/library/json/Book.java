package com.smartown.library.json;

import java.util.List;

/**
 * Created by Tiger on 2016-03-24.
 */
public class Book {

    @JsonDescription(key = "bookName")
    private String bookName;

    @JsonDescription(key = "page")
    private int page;

    @JsonDescription(key = "description", isObject = true)
    private BookDescription bookDescription;

    @JsonDescription(key = "editors", isList = true, isObject = true, listContentClass = BookEditor.class)
    private List<BookEditor> bookEditors;

    @JsonDescription(key = "sellers", isList = true, listContentClass = String.class)
    private List<String> sellers;

    public BookDescription getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(BookDescription bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<BookEditor> getBookEditors() {
        return bookEditors;
    }

    public void setBookEditors(List<BookEditor> bookEditors) {
        this.bookEditors = bookEditors;
    }

    public List<String> getSellers() {
        return sellers;
    }

    public void setSellers(List<String> sellers) {
        this.sellers = sellers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookDescription=" + bookDescription +
                ", bookName='" + bookName + '\'' +
                ", page=" + page +
                ", bookEditors=" + bookEditors +
                ", sellers=" + sellers +
                '}';
    }

}
