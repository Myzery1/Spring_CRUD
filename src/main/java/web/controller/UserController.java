package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userDao;

    @GetMapping()
    public String listUsers(Model model) {
        model.addAttribute("users", userDao.listUsers());
        return "index";
    }

    @GetMapping("/new")
    public String newPage(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userDao.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editPage(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userDao.showId(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userDao.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deletePage(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userDao.showId(id));
        return "/delete";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDao.deleteUser(id);
        return "redirect:/";
    }
}
