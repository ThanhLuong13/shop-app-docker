package com.project.shopapp.services.servicesImp;

import com.project.shopapp.Responses.ProductResponse;
import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.dto.ProductImageDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.InvalidParamException;
import com.project.shopapp.model.CategoryEntity;
import com.project.shopapp.model.ProductEntity;
import com.project.shopapp.model.ProductImageEntity;
import com.project.shopapp.repositories.CategoryRepository;
import com.project.shopapp.repositories.ProductImageRepository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;
    @Override
    public ProductEntity createProduct(ProductDTO productDTO) throws DataNotFoundException {
        CategoryEntity existingCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find category with id: "+productDTO.getCategoryId()));
        // Create product entity
        ProductEntity newProduct = new ProductEntity();
        newProduct.setName(productDTO.getName());
        newProduct.setCategory(existingCategory);
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setThumbnail(productDTO.getThumbnail());
        return productRepository.save(newProduct);
    }

    @Override
    public ProductEntity getProductById(int productId) throws DataNotFoundException {
        return productRepository.findProductById(productId)
                        .orElseThrow(() -> new DataNotFoundException(
                            "Cannot find product with id =" + productId));
    }

    @Override
    public List<ProductEntity> getProductsByIds(List<Integer> ids) {
        return productRepository.findProductsByIds(ids);
    }

    @Override
    public Page<ProductResponse> searchProducts(String keyword, int categoryId, PageRequest pageRequest) {
        return productRepository.searchProducts(keyword, categoryId, pageRequest).map(ProductResponse::formResponse);
    }

    @Override
    @Transactional
    public ProductEntity updateProduct(int productId,ProductDTO productDTO) throws DataNotFoundException {
        ProductEntity existingProduct = getProductById(productId);
        CategoryEntity existingCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find category with id: "+productDTO.getCategoryId()));
        existingProduct.setName(productDTO.getName());
        existingProduct.setCategory(existingCategory);
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setThumbnail(productDTO.getThumbnail());
        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(productRepository::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
    @Override
    @Transactional
    public List<ProductImageEntity> createProductImage(int productId,
                                                 List<MultipartFile> files) throws Exception {
        ProductEntity existingProduct = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find product with id: "+productId));

        int size = productImageRepository.findByProductId(productId).size();
        if (size > 5) {
            throw new InvalidParamException("Number of images must be less than " + ProductImageDTO.maxProductFile);
        }
        if(size + files.size() > 5) {
            throw new InvalidParamException("Number of product's images must be less than " + ProductImageDTO.maxProductFile);
        }
        files = files == null ? new ArrayList<>() : files;
        List<ProductImageEntity> listImages = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.getSize() == 0 ) continue;
            if (file.getSize() > 10 * 1024 * 1024) {
                throw new  InvalidParamException("File size large! Maximum file is 10Mb");
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new InvalidParamException("Type must be image");
            }
            String fileName = storeFile(file);
            ProductImageEntity newProductImage = new ProductImageEntity();
            newProductImage.setProduct(existingProduct);
            newProductImage.setImageUrl(fileName);
            productImageRepository.save(newProductImage);
            listImages.add(newProductImage);
        }
        if (existingProduct.getThumbnail() == null) {
            productRepository.updateProductThumbnail(productId,listImages.get(0).getImageUrl());
        }
        return listImages;
    }

    private ProductResponse mapToProductResponse(ProductEntity product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setThumbnail(product.getThumbnail());
        productResponse.setCategory(product.getCategory());
        return  productResponse;
    }

    private ProductEntity mapToProductEntity(ProductResponse product) throws DataNotFoundException {
        CategoryEntity existingCategory = categoryRepository
                .findById(product.getCategory().getId())
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find category with id: "+product.getCategory().getId()));
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setThumbnail(product.getThumbnail());
        productEntity.setCategory(existingCategory);
        return productEntity;
    }

    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueName = UUID.randomUUID().toString() + "-" + fileName;
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        // Pathname of file
        Path fileDir = Paths.get(uploadDir.toString(), uniqueName);
        // Coppy file into Uploads
        Files.copy(file.getInputStream(), fileDir, StandardCopyOption.REPLACE_EXISTING);
        return uniqueName;
    }
}
