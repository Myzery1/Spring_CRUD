package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userDao;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDao.index());
        return "index";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @Transactional
    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDao.save(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.show(id));
        return "/edit";
    }

    @Transactional
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userDao.update(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.show(id));
        return "/delete";
    }

    @Transactional
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/";
    }
}
