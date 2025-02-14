package com.list.contacts.Controller;

import com.list.contacts.Entities.Contact;
import com.list.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping({"/", ""})
    public String seeIndex(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts",contacts);
        return "index";
    }


    @GetMapping("/new")
    public String showAddRegisterForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "new";
    }

    @PostMapping("/new")
    public String saveRegisterForm(@Validated Contact contact, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("contact",contact);
            return "/new";
        }

        contactRepository.save(contact);
        redirectAttributes.addFlashAttribute("succesMsg", "El contacto fue agregado correctamente");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit/")
    public String showEditContact(@PathVariable Integer id, Model model) {
         Contact contact = contactRepository.getById(id);
        model.addAttribute("contact", contact);
        return "new";
    }

    @PostMapping("/{id}/edit/")
    public String updateContact( @PathVariable Integer id, @Validated Contact contact, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        Contact contactDB = contactRepository.getById(id);

        if(bindingResult.hasErrors()){
            model.addAttribute("contact",contact);
            return "/new";
        }

        contactDB.setName(contact.getName());
        contactDB.setEmail(contact.getEmail());
        contactDB.setCellphoneNumber(contact.getCellphoneNumber());
        //contactDB.setDateOfEntry(contact.getDateOfEntry());

        contactRepository.save(contactDB);
        redirectAttributes.addFlashAttribute("succesMsg", "El contacto fue actualizado correctamente");
        return "redirect:/";
    }

    @PostMapping("/{id}/delete/")
    public String deleteContact(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
        Contact contact = contactRepository.getById(id);
        contactRepository.delete(contact);
        redirectAttributes.addFlashAttribute("succesMsg", "El contacto fue eliminado correctamente");
        return "redirect:/";
    }

}
