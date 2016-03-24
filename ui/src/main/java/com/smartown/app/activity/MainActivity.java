package com.smartown.app.activity;

import android.os.Bundle;
import android.view.View;

import com.smartown.application.R;
import com.smartown.library.json.Book;
import com.smartown.library.json.JsonParser;
import com.smartown.library.ui.base.BaseNotifyActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tiger on 2016-01-11.
 */
public class MainActivity extends BaseNotifyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.activity_main);
        init();
        findViews();
    }

    @Override
    protected void init() {
        try {
            JSONObject jsonObject = new JSONObject("{\n" +
                    "    \"bookName\": \"三国演义\",\n" +
                    "    \"page\": 10000,\n" +
                    "    \"description\": {\n" +
                    "        \"price\": 50\n" +
                    "    },\n" +
                    "    \"editors\": [\n" +
                    "        {\n" +
                    "            \"name\": \"tiger\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"smartown\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"sellers\": [\n" +
                    "        \"seller1\",\n" +
                    "        \"seller2\",\n" +
                    "        \"seller3\"\n" +
                    "    ]\n" +
                    "}");

            Book book = (Book) JsonParser.getInstance().parse(Book.class, jsonObject);
            System.out.println(book.toString());
            System.out.println(JsonParser.getInstance().toJsonObject(book));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void findViews() {
        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {
        setTitle("首页");
        addTitleTextButton("test", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addTitleImageButton(R.mipmap.ic_launcher, "", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void registerViews() {

    }
}
