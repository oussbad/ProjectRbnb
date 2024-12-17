package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public String listProperties(@RequestParam(required = false) String city,
                                 @RequestParam(required = false) Double maxPrice,
                                 @RequestParam(required = false) Integer bedrooms,
                                 Model model) {
        List<Property> properties = propertyService.searchProperties(city, maxPrice, bedrooms);
        model.addAttribute("properties", properties);
        return "property-list";
    }

    @GetMapping("/new")
    public String showNewPropertyForm(Model model) {
        model.addAttribute("property", new Property());
        return "property-form";
    }

    @PostMapping
    public String saveProperty(@ModelAttribute("property") Property property) {
        propertyService.saveProperty(property);
        return "redirect:/properties";
    }

    @GetMapping("/{id}")
    public String viewProperty(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        model.addAttribute("property", property);
        return "property-details";
    }

    @GetMapping("/{id}/edit")
    public String showEditPropertyForm(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        model.addAttribute("property", property);
        return "property-form";
    }

    @PostMapping("/{id}")
    public String updateProperty(@PathVariable Long id, @ModelAttribute("property") Property property) {
        propertyService.updateProperty(id, property);
        return "redirect:/properties";
    }

    @PostMapping("/{id}/delete")
    public String deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return "redirect:/properties";
    }
}
