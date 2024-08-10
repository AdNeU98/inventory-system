package com.csye7374.inventory.repository;

import com.csye7374.inventory.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

    Optional<Buyer> findById(int id);

    Optional<Buyer> findByCompanyName(String companyName);

}
