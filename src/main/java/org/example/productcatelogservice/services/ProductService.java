package org.example.productcatelogservice.services;

import org.example.productcatelogservice.dtos.CategoryDto;
import org.example.productcatelogservice.dtos.FakeStoreProductDto;
import org.example.productcatelogservice.dtos.ProductDto;
import org.example.productcatelogservice.models.Category;
import org.example.productcatelogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;


import java.util.List;                                                                          


@Service
public class ProductService implements IProductService {
/*
    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }*/

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {

      /*
        RestTemplate restTemplate = restTemplateBuilder.build();

       ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",FakeStoreProductDto.class,id);

                ->  replaced with our custom genericRequestEntity method
       */
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                genericRequestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products/{id}", null,
                        FakeStoreProductDto.class,id);
       /* if(fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful()) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
             } else {
            System.out.println(fakeStoreProductDtoResponseEntity.getStatusCode());
        }*/
        if (validateFakeStoreResponse(fakeStoreProductDtoResponseEntity)) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }


    public Product replaceProduct(Long id, Product inputProduct){
        RestTemplate restTemplate = restTemplateBuilder.build();

       ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
             /*  putForEntity(HttpMethod"https://fakestoreapi.com/products/{id}", from(inputProduct),
                                                                FakeStoreProductDto.class, id);

                                                                */
               genericRequestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}", from(inputProduct),
                       FakeStoreProductDto.class, id);

       /* if(fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful()) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        } else {
            System.out.println(fakeStoreProductDtoResponseEntity.getStatusCode());
        }

        Replaced with validateFakeStoreResponse method
        */

        if (validateFakeStoreResponse(fakeStoreProductDtoResponseEntity)) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }                                   

   /* As we didn't have any method for put in Rest Template class, we just copied and modified an existing method
    private <T> ResponseEntity<T> putForEntity( String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    Let's make this method generic to be used for all CRUD operations.
    */

    private <T> ResponseEntity<T> genericRequestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    private Boolean validateFakeStoreResponse(ResponseEntity<FakeStoreProductDto>
                                                      fakeStoreProductDtoResponseEntity) {
        if (fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return true;
        } else {
            System.out.println(fakeStoreProductDtoResponseEntity.getStatusCode());
            return false;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }



    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return  product;
    }



    
}
