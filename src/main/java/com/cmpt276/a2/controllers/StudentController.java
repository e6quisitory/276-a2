package com.cmpt276.a2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cmpt276.a2.models.Student;
import com.cmpt276.a2.models.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add_student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveOrUpdateStudent(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentService.getStudentById(id).orElse(null);
        model.addAttribute("student", student);
        return "update_student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @ModelAttribute("student") Student student) {
        studentService.saveOrUpdateStudent(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }
}