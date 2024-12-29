package com.arifRizal.online_shops.service.product;

import com.arifRizal.online_shops.model.Product;

import java.util.List;


public interface IProductService {

  Product addProduct(Product product);

  Product getProductById(Long id);
  void deleteProductById(Long id);
  void updateProduct(Product product, Long productId);
  List<Product> getAllProducts();
  List<Product> getAllProductsByCategory(Long category);
  List<Product> getProductsByBrand(String brand);
  List<Product> getProductsByCategoryAndBrand(String category, String brand);
  List<Product> getProductByName(String name);
  List<Product> getProductByBrandAndName(String category, String name);
  Long countProductsByBrandAndName(String brand, String name);

}