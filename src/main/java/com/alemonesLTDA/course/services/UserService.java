package com.alemonesLTDA.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alemonesLTDA.course.entities.User;
import com.alemonesLTDA.course.repositories.UserRepository;
import com.alemonesLTDA.course.services.exceptions.DatabaseException;
import com.alemonesLTDA.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;


	//crud

	//retrieve
	public List<User> findAll() {
		return repository.findAll();
	}

	//retrieve
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	//create
	public User insert(User obj) {
		return repository.save(obj);
	}

	//delete
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// caso o delete seja em algum item que nao existe, retornara o body
			// StandardError
			throw new ResourceNotFoundException(id);
			// caso acontecer uma excessao desse tipo, preciso lançar uma exceptionhandling
			// minha propria
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	//update
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
