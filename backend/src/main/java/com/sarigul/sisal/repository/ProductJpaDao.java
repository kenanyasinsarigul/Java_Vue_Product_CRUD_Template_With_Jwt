package com.sarigul.sisal.repository;

import com.sarigul.sisal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaDao extends JpaRepository<Product,Long> {
}
