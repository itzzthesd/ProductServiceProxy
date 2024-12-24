package com.example.productService.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.example.productService.models.Product;
import com.example.productService.models.SortParam;
import com.example.productService.repositories.ProductRepo;

@Service
public class SearchService {
    private ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public List<Product> searchProducts(String query, int pageNumber, int sizeOfPage,
                    List<SortParam> sortParamList) {
        Sort sort;
        if(sortParamList.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortParamList.get(0).getParamName());
        } else {
            sort = Sort.by(sortParamList.get(0).getParamName()).descending();
        }
        

        for(int i = 1; i< sortParamList.size(); i++) {
            if(sortParamList.get(i).getSortType().equals("ASC")) {
                sort = sort.and(Sort.by(sortParamList.get(i).getParamName()));
            } else {
                sort = sort.and(Sort.by(sortParamList.get(i).getParamName())
                        .descending());
            }
        }
        System.out.println(sort.toString());

        return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage, sort));
    }
    
}
