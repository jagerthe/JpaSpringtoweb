package com.alemonesLTDA.course.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.alemonesLTDA.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{


}
