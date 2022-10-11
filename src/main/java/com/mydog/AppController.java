package com.mydog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mydog.Entities.Pet;
import com.mydog.Entities.Role;
import com.mydog.Entities.User;
import com.mydog.Services.PetService;
import com.mydog.Services.UserService;

@Controller
public class AppController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PetService petService;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		userService.registerDefaultUser(user);
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userService.get(id);
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		userService.save(user);
		return "redirect:/users";
	}
	
	/*
	 * Pets
	 */
	@GetMapping("/pets")
	public String petsList(Model model) {
		List<Pet> petList = petService.listAll();
		model.addAttribute("petList", petList);
		return "pets/index";
	}
	
	@GetMapping("/pets/new")
	public String petsNew(Model model) {
		model.addAttribute("pet", new Pet());
		return "pets/create";
	}
	
	@PostMapping("/pets/create")
	public String createPet(Pet pet) {
		petService.save(pet);
		return "pets/success";
	}
	
	@GetMapping("/pets/edit/{id}")
	public String petsEdit(@PathVariable("id") Long id, Model model) {
		Pet pet = petService.get(id);
		model.addAttribute("pet", pet);
		return "pets/edit";
	}
	
	@PostMapping("/pets/save")
	public String savePet(Pet pet) {
		petService.save(pet);
		return "redirect:/pets";
	}
}
