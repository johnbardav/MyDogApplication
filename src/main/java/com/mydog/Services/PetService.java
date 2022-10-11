package com.mydog.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydog.Entities.Pet;
import com.mydog.Repositories.PetRepository;

@Service
public class PetService {
	@Autowired
	private PetRepository petRepo;
	
	public List<Pet> listAll() {
		return petRepo.findAll();
	}

	public Pet get(Long id) {
		return petRepo.findById(id).get();
	}
	
	public void save(Pet pet) {
		pet.setStatus(1);
		petRepo.save(pet);
	}
}
