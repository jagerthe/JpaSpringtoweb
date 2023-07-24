package com.alemonesLTDA.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alemonesLTDA.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{


}