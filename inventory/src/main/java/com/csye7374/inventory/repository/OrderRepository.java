package com.csye7374.inventory.repository;

import com.csye7374.inventory.model.Invoice;
import com.csye7374.inventory.model.PurchaseOrder;
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
public interface OrderRepository extends JpaRepository<PurchaseOrder,Integer> {

	Optional<PurchaseOrder> findById(int id);

}
