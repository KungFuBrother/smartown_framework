package com.smartown.library.shoppingcart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tiger on 2015-11-27.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    /**
     * 数据库名称常量
     */
    public static final String DATABASE_NAME = "shopping_cart.db";
    /**
     * 数据库版本常量
     */
    private static final int DATABASE_VERSION = 3;
    /**
     * 购物车表
     */
    public static final String table_shopping_cart = "shopping_cart";

    public static final int PRODUCT_TYPE_YITIAN = 1;
    public static final int PRODUCT_TYPE_LOCAL = 2;
    public static final int PRODUCT_TYPE_SUNING = 3;

    private static DataBaseHelper helper;

    private static Context APPLICATION_CONTEXT;

    public static void init(Context context) {
        APPLICATION_CONTEXT = context;
    }

    public DataBaseHelper() {
        super(APPLICATION_CONTEXT, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getInstance() {
        if (helper == null) {
            helper = new DataBaseHelper();
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + table_shopping_cart + "(" + "id integer primary key,"
                + ModelShoppingCart.columnProductType + " integer," + ModelShoppingCart.columnIsSelected + " integer,"
                + ModelShoppingCart.columnBuyCount + " integer," + ModelShoppingCart.columnProviderId + " varchar,"
                + ModelShoppingCart.columnProviderName + " varchar," + ModelShoppingCart.columnProductId + " varchar,"
                + ModelShoppingCart.columnProductObject + " varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
