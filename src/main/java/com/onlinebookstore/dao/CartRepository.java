package com.onlinebookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebookstore.dto.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
