package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {
    @Autowired
    PersonRepository personRepository;
    @RequestMapping("/")
    public  String listCourses(Model model){
        model.addAttribute("person",personRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public  String personForm(Model model){
        model.addAttribute("person",new Person());
        return "personform";
    }
    @GetMapping("/search")
    public  String search(Model model){
        model.addAttribute("person",new Person());
        return "try";
    }
    @PostMapping("/process")
    public  String processForm(@Valid Person person, BindingResult result){
        if(result.hasErrors()){
            return "personform";
        }
        personRepository.save(person);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showPerson(@PathVariable("id") long id, Model model){
        model.addAttribute("person",personRepository.findOne(id));
        return "show";
    }
    @RequestMapping("update/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personRepository.findOne(id));
        return "personform";
    }
    @RequestMapping("/delete/{id}")
    public String delPerson(@PathVariable("id") long id){
        personRepository.delete(id);
        return "redirect:/";
    }
    @RequestMapping("/searchform")
    public  String serachByLastName(@RequestParam("lastName") String lastName,Model model){
        List<Person> persons = personRepository.findByLastName(lastName);
        model.addAttribute("person",persons);
        return "list";
    }



}
