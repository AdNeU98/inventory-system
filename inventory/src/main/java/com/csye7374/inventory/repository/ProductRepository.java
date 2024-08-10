package com.csye7374.inventory.repository;

import com.csye7374.inventory.model.Product;
import com.csye7374.inventory.model.ProductPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findById(int id);

    Optional<Product> findByProductName(String productName);
}
