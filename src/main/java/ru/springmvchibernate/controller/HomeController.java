package ru.springmvchibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.springmvchibernate.model.User;
import ru.springmvchibernate.service.abstraction.user.UserService;
import java.util.List;

@Controller
public class HomeController {
	@Autowired
	@Qualifier("userServiceQualifier")
	private UserService userService;

	@RequestMapping("/test")
	public String getIndex(Model model){
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "index";
	}

	@RequestMapping(value="/admin/allusers", method = RequestMethod.GET)
	public String allUsersGet(Model model){
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "allusers";
	}

	@RequestMapping(value="/admin/adduser", method= RequestMethod.POST)
	//TODO  поправить adduser редирект на allusers
	public String add(@RequestParam String name, @RequestParam String login, @RequestParam String password, @RequestParam String role, Model model)
	{
		User newUser = new User(name, login, password, role);
		userService.saveUser(newUser);
		List<User> users =  userService.getAllUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/allusers";
	}


	@RequestMapping(value="/admin/deluser/{id}", method = RequestMethod.GET)
		public String delUser(@PathVariable("id") long id, Model model){
		long userIdToDel = id;
		userService.deleteUser(userIdToDel);
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/allusers";
	}

	@RequestMapping(value="/admin/edituser/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") long id, Model model) {
		long userIdToEdit = id;
		User userToEdit =  userService.getUserById(userIdToEdit);
		model.addAttribute("user", userToEdit);
		return "edituser";
	}

	@RequestMapping(value="/admin/edituser", method = RequestMethod.POST)
	public String saveUser(@RequestParam String id, @RequestParam String name, @RequestParam String login, @RequestParam String password, @RequestParam String role, Model model) {
		long userIdToEdit = Long.parseLong(id);
		User newUser = new User(userIdToEdit, name, login, password, role);
		userService.editUser(newUser);
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/allusers";
	}


	
	@RequestMapping("/welcome")
	public String getWelcome(){
		return "welcome";
	}
}
