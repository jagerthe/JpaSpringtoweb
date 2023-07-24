package com.alemonesLTDA.course.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.alemonesLTDA.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{


}
