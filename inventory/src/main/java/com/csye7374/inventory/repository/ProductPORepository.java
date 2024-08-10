package com.csye7374.inventory.repository;

import com.csye7374.inventory.model.Product;
import com.csye7374.inventory.model.ProductPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductPORepository extends JpaRepository<ProductPO,Integer> {

	Optional<ProductPO> findById(int id);
}
