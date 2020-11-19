package lesson5.controller;

import lesson5.domain.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lesson5.config.UserInfo;
import lesson5.domain.User;
import lesson5.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserInfo userInfo) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(User user) {
        userService.saveAndSet(user);
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String getList(Model model,
                          @RequestParam(required = false) String username,
                          @RequestParam(required = false) String password) {
        model.addAttribute("users", userService.getAll());
        return "user-list";
    }

    @GetMapping("/users/{id}")
    public String getById(Model model, @PathVariable Integer id) {
        User byId = userService.getById(id);
        model.addAttribute("user", byId == null ? new User() : byId);
        model.addAttribute("roles", Role.values());
        return "user";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute User user) {
        userService.updateRole(user.getId(), Optional.ofNullable(user.getRole()));
        return "redirect:/users/{id}";
    }

}
