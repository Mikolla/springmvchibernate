package ru.springmvchibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.springmvchibernate.model.User;
import ru.springmvchibernate.service.abstraction.user.UserService;
import ru.springmvchibernate.service.impl.user.UserServiceImpl;

import java.util.List;

@Controller
public class HomeController {
	private final UserService userService = new UserServiceImpl();

	@RequestMapping("/")
	public String getIndex(Model model){
		List<User> users = null;
		users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "index";
	}

	@RequestMapping("/admin/allusers")
	public String allUsersGet(Model model){
		List<User> users = null;
		users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "allusers";
	}

	@RequestMapping(value="/admin/allusers", method= RequestMethod.POST)
	public String add(@RequestParam String name, @RequestParam String login, @RequestParam String password, @RequestParam String role, Model model)
	{
		User newUser = new User(name, login, password, role);
		userService.saveUser(newUser);
		List<User> users = null;
		users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "allusers";
	}

	@RequestMapping(value="/admin/deluser", method = RequestMethod.GET)
	public String delUser(@RequestParam String id, Model model){
		long userIdToDel = Long.parseLong(id);
		userService.deleteUser(userIdToDel);
		List<User> users = null;
		users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/allusers";
	}

	@RequestMapping(value="/admin/edituser", method = RequestMethod.GET)
	public String editUser(@RequestParam String id, Model model) {
		User userToEdit = null;
		long userIdToEdit = Long.parseLong(id);
		userToEdit =  userService.getUserById(userIdToEdit);
		model.addAttribute("user", userToEdit);
		return "edituser";
	}

	@RequestMapping(value="/admin/edituser", method = RequestMethod.POST)
	public String saveUser(@RequestParam String id, @RequestParam String name, @RequestParam String login, @RequestParam String password, @RequestParam String role, Model model) {
		long userIdToEdit = Long.parseLong(id);
		User newUser = new User(userIdToEdit, name, login, password, role);
		userService.editUser(newUser);
		List<User> users = null;
		users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/allusers";
	}





/*
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userIdToEdit = Long.parseLong(request.getParameter("id"));
        User userToEdit = null;
        userToEdit =  userService.getUserById(userIdToEdit);
        request.setAttribute("user", userToEdit);
        request.getRequestDispatcher("/edituser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  Enumeration<String> stringEnum =  request.getParameterNames();
        long userIdToEdit = Long.parseLong(request.getParameter("id"));
        String newUserName = request.getParameter("name");
        String newUserLogin = request.getParameter("login");
        String newUserPassword = request.getParameter("password");
        String newUserRole = request.getParameter("role");
        User newUser = new User(userIdToEdit, newUserName, newUserLogin, newUserPassword, newUserRole);
        userService.editUser(newUser);
        response.sendRedirect("/admin/allusers");
    }
@GetMapping("/delmessage")
    public String delmessage(@RequestParam String id, Map<String, Object> model) {
        Integer idToDel = Integer.parseInt(id);
        messageRepo.deleteById(idToDel);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "redirect:";
    }


*/
	
	@RequestMapping("/welcome")
	public String getWelcome(){
		return "welcome";
	}
}
