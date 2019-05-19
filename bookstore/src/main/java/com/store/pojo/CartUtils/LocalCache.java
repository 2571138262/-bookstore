package com.store.pojo.CartUtils;

import com.store.pojo.BookInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本地缓存
 */
public final class LocalCache {
//    private LocalCache(){}
//
//    private static Map<Integer, BookInfo> productMap = new HashMap<>();//课程的缓存
//
//    private static Map<Integer, Cart> cartMap = new HashMap<>();//购物车的缓存
//
//    private static Map<Integer, BookInfo> favoriteMap = new HashMap<>();//收藏的缓存
//
//    private static Map<Integer, BookInfo> browseLogMap = new HashMap<>();//浏览记录缓存
//    
//
//    // 获取商品的集合
//    public static List<BookInfo> getProducts(){
//        return new ArrayList<>(productMap.values());
//    }
//
//    // 通过id获取商品
//    public static BookInfo getProduct(Long id){
//        return productMap.get(id);
//    }
//
//    // 将商品添加到购物车
//    public static void addCart(BookInfo bookInfo){
//        if (!cartMap.containsKey(bookInfo.getBookId())){
//            cartMap.put(bookInfo.getBookId(), new Cart(bookInfo.getBookId(), bookInfo.getBookId(), bookInfo.getBookName(), bookInfo.getBookPrice(), 1));
//        }else {
//            incrCart(bookInfo.getBookId());
//        }
//
//    }
//
//    public static List<Cart> getCarts(){
//        return new ArrayList<>(cartMap.values());
//    }
//
//    public static void delCart(Long productId){
//        cartMap.remove(productId);
//    }
//
//    public static void incrCart(Integer bookId) {
//        cartMap.get(bookId).incrCount();
//    }
//
//    public static void decrCart(Integer bookId) {
//        boolean result = cartMap.get(bookId).decrCount();
//        if (result){
//            cartMap.remove(bookId);
//        }
//    }
//
//    public static Cart getCart(Integer bookId){
//        return cartMap.get(bookId);
//    }
//
//    //------------------------------------收藏
//
//    public static void addFavorite (BookInfo bookInfo){
//        if (!favoriteMap.containsKey(bookInfo.getBookId())){
//            favoriteMap.put(bookInfo.getBookId(), bookInfo);
//        }
//    }
//
//    public static void delFavorite (Integer bookId){
//        if (favoriteMap.containsKey(bookId)){
//            favoriteMap.remove(bookId);
//        }
//    }
//
//    public static List<BookInfo> getFavorites(){
//        return new ArrayList<>(favoriteMap.values());
//    }
//
//    // --------------------------------------浏览记录
//    public static void addBrowseLog(BookInfo bookInfo){
//        browseLogMap.put(bookInfo.getBookId(), bookInfo);
//    }
//
//    public static void delBrowseLog(Integer bookId){
//        browseLogMap.remove(bookId);
//    }
//
//    public static List<BookInfo> getBrowseLogs(){
//        return new ArrayList<>(browseLogMap.values());
//    }
//
//    // -------------------------------------分页查询
//    public static List<BookInfo> getProducts(int page, int size, String name){
//        List<Product> products = new ArrayList<>();
//
//        if (null != name && !"".equals(name)){
//            productMap.values().forEach(product -> {
//                if (product.getName().contains(name)) {
//                    products.add(product);
//                }
//            });
//        }else {
//            products.addAll(productMap.values());
//        }
//
//        int start = (page - 1) * size;
//        int end = products.size() >= page * size ? page * size : products.size();
//        return products.subList(start, end);
//    }
//
//    // -------------------------------------查询
//    public static int getProductsCount(String name){
//        List<Product> products = new ArrayList<>();
//
//        if (null != name && !"".equals(name)){
//            productMap.values().forEach(product -> {
//                if (product.getName().contains(name)) {
//                    products.add(product);
//                }
//            });
//        }else {
//            products.addAll(productMap.values());
//        }
//        return products.size();
//    }
}
