package com.smartown.library.json;

/**
 * Created by Tiger on 2016-03-24.
 */
public class BookEditor {

    @JsonDescription(key = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BookEditor{" +
                "name='" + name + '\'' +
                '}';
    }
}
