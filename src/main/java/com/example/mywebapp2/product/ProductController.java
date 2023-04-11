package com.example.mywebapp2.product;

import com.example.mywebapp2.supplier.Supplier;
import com.example.mywebapp2.supplier.SupplierService;
import com.example.mywebapp2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.ListResourceBundle;

@Controller
public class ProductController {
    @Autowired private SupplierService serviceSupp;
    @Autowired private ProductService service;

    @GetMapping("/products")
    public String showProductList (Model model){
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "products";
    }

    @GetMapping ("/products/new")
    public String showNewForm(Model model){
        List<Supplier> suppliers = serviceSupp.listAll();
        model.addAttribute("product", new Product());
        model.addAttribute("supplier", suppliers);
        model.addAttribute("pageTitle", "Add New Product");
        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveUser(Product product, RedirectAttributes ra){
        service.save(product);
        ra.addFlashAttribute("message", "The product has been saved succesfully");
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            List<Supplier> suppliers = serviceSupp.listAll();
            Product product = service.get(id);
            model.addAttribute("product", product);
            model.addAttribute("supplier", suppliers);
            model.addAttribute("pageTitle", "Edit product ID ->" +id);
            return "product_form";
        } catch (ProductNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/products";
        }
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "The product with ID ->" +id + "has been deleted" );
        }catch (ProductNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products";
    }
}
