package com.codegenius.shop.serviceimpl;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.codegenius.shop.JWT.JwtFilter;
import com.codegenius.shop.POJO.Category;
import com.codegenius.shop.POJO.Product;
import com.codegenius.shop.constants.ShopConstants;
import com.codegenius.shop.dao.ProductDao;
import com.codegenius.shop.service.ProductService;
import com.codegenius.shop.utils.ShopUtils;
import com.codegenius.shop.wrapper.ProductWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    Cloudinary cloudinary;


    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap, MultipartFile imageFile) {

        try {
            if (jwtFilter.isAdmin()) {
                Product product = getProductFromMap(requestMap);
                if (imageFile != null && !imageFile.isEmpty()) {
                    String folder = "products";
                    Map<String, Object> uploadOptions = ObjectUtils.asMap(
                            "use_filename", true,
                            "folder", folder
                    );
                    Map<?, ?> uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), uploadOptions);

                    product.setImageUrl((String) uploadResult.get("url"));
                    product.setImagePublicId((String) uploadResult.get("public_id"));
                }

                productDao.save(product);
                return ShopUtils.getResponseEntity("Product Added Successfully", HttpStatus.OK);

            } else {
                return ShopUtils.getResponseEntity(ShopConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap) {
        Product product = new Product();
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));

        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setCompany(requestMap.get("company"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));

        return product;
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try {
            return new ResponseEntity<>(productDao.getAllProduct(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = productDao.findById(id);
                if (!optional.isEmpty()) {
                    productDao.deleteById(id);
                    return ShopUtils.getResponseEntity("Product is deleted successfully", HttpStatus.OK);
                }
                return ShopUtils.getResponseEntity("Product doesn't exist", HttpStatus.OK);
            } else {
                return ShopUtils.getResponseEntity(ShopConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
        try {
            List<ProductWrapper> products = productDao.getByCategory(id);

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ProductWrapper> getProductById(Integer id) {
        try {
            ProductWrapper product = productDao.getProductById(id);

            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
