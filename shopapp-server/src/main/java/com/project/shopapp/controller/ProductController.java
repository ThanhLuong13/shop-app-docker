package com.project.shopapp.controller;


import com.github.javafaker.Faker;
import com.project.shopapp.Responses.ProductListResponse;
import com.project.shopapp.Responses.ProductResponse;
import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.dto.ProductImageDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.InvalidParamException;
import com.project.shopapp.model.ProductEntity;
import com.project.shopapp.model.ProductImageEntity;
import com.project.shopapp.repositories.ProductImageRepository;
import com.project.shopapp.services.servicesImp.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductImageRepository productImageRepository;

    @PostMapping("")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO,
                                        BindingResult result) {
        try {
            if (result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(fieldError -> fieldError.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            ProductResponse newProduct = ProductResponse.formResponse(productService.createProduct(productDTO));
            return ResponseEntity.ok("Add product successfully" + newProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/upload/{product_id}")
    public ResponseEntity<?> uploadProductImage(@PathVariable("product_id") Integer productId,
                                                @ModelAttribute List<MultipartFile> files) {
        try {
             if(files.size() > ProductImageDTO.maxProductFile) {
                 return ResponseEntity.badRequest()
                         .body("Number of images must be less than " + ProductImageDTO.maxProductFile);
             }
                List<String> productImagesList = new ArrayList<>();
                List<ProductImageEntity> productImages = productService.createProductImage(productId, files);
                productImages.forEach(productImage -> productImagesList.add(productImage.getImageUrl()));
            return ResponseEntity.ok(productImagesList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<ProductListResponse> getProducts(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "categoryId", defaultValue = "0") int categoryId,
            @RequestParam("page") int page,
            @RequestParam("limit")  int limit
    ) {
        PageRequest pageRequest = PageRequest.
                of(page - 1, limit, Sort.by("id").ascending() );
        Page<ProductResponse> productsPage = productService.searchProducts(keyword, categoryId, pageRequest);
        ProductListResponse products = new ProductListResponse();
        products.setProducts(productsPage.getContent());
        products.setTotalPages(productsPage.getTotalPages());
        return ResponseEntity.ok(products);
    }
    //http://localhost:8088/api/v1/products/6
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable("id") int productId
    ) {
        try {
            ProductEntity product = productService.getProductById(productId);
            return ResponseEntity.ok(ProductResponse.formResponse(product));
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/ids/{ids}")
    public ResponseEntity<?> getProductsByIds(
            @PathVariable("ids") List<Integer> ids
    ) {
        try {
            List<ProductEntity> products = productService.getProductsByIds(ids);
            List<ProductResponse> productResponseList = new ArrayList<>();
            for (ProductEntity productEntity : products) {
                ProductResponse productResponse = ProductResponse.formResponse(productEntity);
                productResponseList.add(productResponse);
            }
            return ResponseEntity.ok(productResponseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return ResponseEntity.ok(String.format("Product with id = %d deleted successfully", id));
    }

    @PostMapping("/fake-data")
    public ResponseEntity<?> addFakeProduct() {
        Faker faker = new Faker();
        for (int i =0; i < 100; i++) {
            String productName = faker.commerce().productName();
            if (productService.existsByName(productName)) continue;
            ProductDTO newProduct = new ProductDTO();
            newProduct.setName(productName);
            newProduct.setPrice((float) faker.number().numberBetween(10, 10000));
            newProduct.setDescription(faker.lorem().sentence());
            newProduct.setThumbnail("");
            newProduct.setCategoryId(faker.number().numberBetween(1, 4));
            try {
                productService.createProduct(newProduct);
            } catch (DataNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.ok("Fake Products created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable int id,
            @Valid @RequestBody ProductDTO productDTO,
            BindingResult result) {
        try {
            if (result.hasErrors()){
                List<String> errorMessages=result
                        .getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            ProductEntity updatedProduct = productService.updateProduct(id, productDTO);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/images/{image}")
    public ResponseEntity<?> viewImage(@PathVariable String image) {
        try {
            Path imagePath = Paths.get("uploads/"+image);
            UrlResource urlResource = new UrlResource(imagePath.toUri());
            if (urlResource.exists()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(urlResource);
            } else {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(new UrlResource(Paths.get("uploads/notfound.png").toUri()));
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
