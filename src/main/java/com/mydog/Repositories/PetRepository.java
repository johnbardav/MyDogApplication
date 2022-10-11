package com.mydog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mydog.Entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
}
