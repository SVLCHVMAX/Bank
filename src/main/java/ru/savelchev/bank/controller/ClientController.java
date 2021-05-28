package ru.savelchev.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.savelchev.bank.model.Client;
import ru.savelchev.bank.service.ClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String listClients(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "clients/clients";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client") Client client, Model model) {
        model.addAttribute("path", "new");
        model.addAttribute("name", "Новый клиент");
        model.addAttribute("nameButton", "Добавить");
        return "clients/new";
    }

    @PostMapping("/new")
    public String createClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "new");
            model.addAttribute("name", "Новый клиент");
            model.addAttribute("nameButton", "Добавить");
            return "clients/new";
        }
        clientService.save(client);
        return "redirect:/clients/";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") UUID id) {
        clientService.deleteById(id);
        return "redirect:/clients/";
    }

    @GetMapping("/update/{id}")
    public String updateClientForm(@PathVariable("id") UUID id, Model model) {
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        model.addAttribute("path", "update");
        model.addAttribute("name", "Редактирование данных клиента");
        model.addAttribute("nameButton", "Изменить");
        return "clients/new";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "update");
            model.addAttribute("name", "Редактирование данных клиента");
            model.addAttribute("nameButton", "Изменить");
            return "clients/new";
        }
        clientService.save(client);
        return "redirect:/clients/";
    }

    @GetMapping("/search")
    public String searchClients(@RequestParam("searchName") String searchName, Model model) {
        List<Client> clients = clientService.findByLastNameIgnoreCase(searchName);
        model.addAttribute("clients",clients);
        return "clients/clients";
    }


}
