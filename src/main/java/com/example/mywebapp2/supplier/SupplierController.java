package com.example.mywebapp2.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    private SupplierService service;

    @GetMapping("/suppliers")
    public String showSupplierList(Model model){
        List<Supplier> listSuppliers = service.listAll();
        model.addAttribute("listSuppliers", listSuppliers);
        return "suppliers";
    }
    @GetMapping("/suppliers/new")
    public String showNewForm(Model model){
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("pageTitle", "Add New Supplier");
        return "supplier_form";
    }

    @PostMapping("/suppliers/save")
    public String saveSupplier(Supplier supplier, RedirectAttributes ra){
        service.save(supplier);
        ra.addFlashAttribute("message", "The supplier has been saved succesfully");
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/edit/{id}")
    public String ShowEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            Supplier supplier = service.get(id);
            model.addAttribute("supplier", supplier);
            model.addAttribute("pageTitle", "Edit supplier ID -> " +id);
            return "supplier_form";
        }catch (SupplierNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/suppliers";
        }
    }

    @GetMapping("/suppliers/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "The supplier ID -> "+id+" has been deleted");
        }catch (SupplierNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/suppliers";
    }



}
