package com.smartown.library.shoppingcart;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiger on 2015-11-27.
 */
public class ShoppingCartController {

    public static ShoppingCartController instance;

    private SQLiteDatabase db;

    private Cursor cursor;

    private void close() {
        if (db != null) {
            db.close();
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public static ShoppingCartController getInstance() {
        if (instance == null) {
            instance = new ShoppingCartController();
        }
        return instance;
    }

    /**
     * 判断购物车中是否已经添加某商品
     *
     * @param productId   商品id
     * @param productType 商品类型，取{@link DataBaseHelper}中的常量
     * @return
     */
    public boolean hasProduct(int productType, String productId) {
        db = DataBaseHelper.getInstance().getReadableDatabase();
        cursor = db.query(DataBaseHelper.table_shopping_cart, null,
                ModelShoppingCart.columnProductType + "=? and " + ModelShoppingCart.columnProductId + "=?",
                new String[]{String.valueOf(productType), productId}, null, null, null);
        boolean hasProduct = cursor.moveToFirst();
        close();
        return hasProduct;
    }

    public void addProduct(int productType, boolean isSelected, int buyCount, String providerId, String providerName,
                           String productId, String productObject) {
        db = DataBaseHelper.getInstance().getReadableDatabase();
        ContentValues values = new ContentValues();
        int selection = 0;
        if (isSelected) {
            selection = 1;
        }
        values.put(ModelShoppingCart.columnProductType, productType);
        values.put(ModelShoppingCart.columnIsSelected, selection);
        values.put(ModelShoppingCart.columnBuyCount, buyCount);
        values.put(ModelShoppingCart.columnProviderId, providerId);
        values.put(ModelShoppingCart.columnProviderName, providerName);
        values.put(ModelShoppingCart.columnProductId, productId);
        values.put(ModelShoppingCart.columnProductObject, productObject);
        db.insert(DataBaseHelper.table_shopping_cart, null, values);
        close();
    }

    public void saveChangedShoppingCart(int productType, List<ModelShoppingCart> shoppingCarts) {
        removeAllProduct(productType);
        for (int i = 0; i < shoppingCarts.size(); i++) {
            addProduct(productType, shoppingCarts.get(i).isSelected(), shoppingCarts.get(i).getBuyCount(),
                    shoppingCarts.get(i).getProviderId(), shoppingCarts.get(i).getProviderName(),
                    shoppingCarts.get(i).getProductId(), shoppingCarts.get(i).getProductObject());
        }
    }

    /**
     * 清空某个购物车
     *
     * @param productType 购物车类型/商品类型
     */
    public void removeAllProduct(int productType) {
        db = DataBaseHelper.getInstance().getReadableDatabase();
        db.delete(DataBaseHelper.table_shopping_cart, ModelShoppingCart.columnProductType + "=?",
                new String[]{String.valueOf(productType)});
        close();
    }

    public void removeSelectedProducts(int productType, List<String> productIds) {
        db = DataBaseHelper.getInstance().getReadableDatabase();
        for (int i = 0; i < productIds.size(); i++) {
            db.delete(DataBaseHelper.table_shopping_cart,
                    ModelShoppingCart.columnProductType + "=? and " + ModelShoppingCart.columnProductId + "=?",
                    new String[]{String.valueOf(productType), productIds.get(i)});
        }
        close();
    }

    public void removeBoughtProducts(int productType) {
        db = DataBaseHelper.getInstance().getReadableDatabase();
        db.delete(DataBaseHelper.table_shopping_cart,
                ModelShoppingCart.columnProductType + "=? and " + ModelShoppingCart.columnIsSelected + "=?",
                new String[]{String.valueOf(productType), "1"});
        close();
    }

    public List<ModelShoppingCart> getAllProducts(int productType) {
        db = DataBaseHelper.getInstance().getReadableDatabase();
        List<ModelShoppingCart> shoppingCarts = new ArrayList<>();
        Cursor cursor = db.query(DataBaseHelper.table_shopping_cart, null, ModelShoppingCart.columnProductType + "=?",
                new String[]{String.valueOf(productType)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ModelShoppingCart shoppingCart = new ModelShoppingCart();
                shoppingCart.setProductType(cursor.getInt(cursor.getColumnIndex(ModelShoppingCart.columnProductType)));
                shoppingCart.setBuyCount(cursor.getInt(cursor.getColumnIndex(ModelShoppingCart.columnBuyCount)));
                shoppingCart
                        .setIsSelected(cursor.getInt(cursor.getColumnIndex(ModelShoppingCart.columnIsSelected)) == 1);
                shoppingCart.setProviderId(cursor.getString(cursor.getColumnIndex(ModelShoppingCart.columnProviderId)));
                shoppingCart
                        .setProviderName(cursor.getString(cursor.getColumnIndex(ModelShoppingCart.columnProviderName)));
                shoppingCart.setProductId(cursor.getString(cursor.getColumnIndex(ModelShoppingCart.columnProductId)));
                shoppingCart.setProductObject(
                        cursor.getString(cursor.getColumnIndex(ModelShoppingCart.columnProductObject)));
                shoppingCarts.add(shoppingCart);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return shoppingCarts;
    }

    public List<ModelShoppingCart> getSelectedProducts(int productType) {
        db = DataBaseHelper.getInstance().getReadableDatabase();
        List<ModelShoppingCart> shoppingCarts = new ArrayList<>();
        Cursor cursor = db.query(DataBaseHelper.table_shopping_cart, null,
                ModelShoppingCart.columnProductType + "=? and " + ModelShoppingCart.columnIsSelected + "=?",
                new String[]{String.valueOf(productType), "1"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ModelShoppingCart shoppingCart = new ModelShoppingCart();
                shoppingCart.setProductType(cursor.getInt(cursor.getColumnIndex(ModelShoppingCart.columnProductType)));
                shoppingCart.setBuyCount(cursor.getInt(cursor.getColumnIndex(ModelShoppingCart.columnBuyCount)));
                shoppingCart
                        .setIsSelected(cursor.getInt(cursor.getColumnIndex(ModelShoppingCart.columnIsSelected)) == 1);
                shoppingCart.setProviderId(cursor.getString(cursor.getColumnIndex(ModelShoppingCart.columnProviderId)));
                shoppingCart
                        .setProviderName(cursor.getString(cursor.getColumnIndex(ModelShoppingCart.columnProviderName)));
                shoppingCart.setProductId(cursor.getString(cursor.getColumnIndex(ModelShoppingCart.columnProductId)));
                shoppingCart.setProductObject(
                        cursor.getString(cursor.getColumnIndex(ModelShoppingCart.columnProductObject)));
                shoppingCarts.add(shoppingCart);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return shoppingCarts;
    }

}
