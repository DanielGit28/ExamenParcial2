package com.cenfotec.examen2.controller;

import com.cenfotec.examen2.domain.Auditor;
import com.cenfotec.examen2.domain.Cliente;
import com.cenfotec.examen2.domain.Contacto;
import com.cenfotec.examen2.domain.Trabajo;
import com.cenfotec.examen2.service.AuditorService;
import com.cenfotec.examen2.service.ClienteService;
import com.cenfotec.examen2.service.ContactoService;
import com.cenfotec.examen2.service.TrabajoService;
import com.cenfotec.examen2.service.TrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuditorController {
    @Autowired
    AuditorService auditorService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    ContactoService contactoService;
    @Autowired
    TrabajoService trabajoService;

    @RequestMapping("/")
    public String index(Model model){
        //model.addAttribute("persona", personaService.getAll());
        //System.out.println("Index");
        return "index";
    }

/*---Auditores*/
    @RequestMapping(value = "/registroAuditores", method = RequestMethod.GET)
    public String registroAuditores(Model model){
        model.addAttribute(new Auditor());
        return "registroAuditores";
    }

    @RequestMapping("/consultores")
    public String consultores(Model model){
        model.addAttribute("auditor", auditorService.getAll());
        //System.out.println("Index");
        return "consultores";
    }

    @PostMapping("/guardarAuditor")
    public String saveAuditor(@ModelAttribute("auditor") Auditor auditor) {
        auditor.setEstado("Activo");
        auditorService.saveAuditor(auditor);
        return "consultores";
    }

    @RequestMapping("/actualizarAuditor/{id}")
    public String auditorUpdateForm(@PathVariable(value = "id") long id, Model model) {
        Auditor auditor = auditorService.getById(id);

        model.addAttribute("auditor", auditor);
        return "actualizarAuditores";
    }

    @PostMapping("/actualizarAuditor/{id}")
    public String updateAuditor(@PathVariable("id") long id, @Valid Auditor auditor, BindingResult result, Model model) {
        System.out.println("Fechanac: "+auditor.getFechaNacimiento());
        auditorService.updateAuditor(auditor);
        model.addAttribute("auditores", auditorService.getAll());
        return "consultores";
    }

    /*------------------------*/

    /*---CLIENTES---*/
    @RequestMapping(value = "/registroClientes", method = RequestMethod.GET)
    public String registroClientes(Model model){
        model.addAttribute(new Cliente());
        return "registroClientes";
    }

    @RequestMapping("/clientes")
    public String clientes(Model model){
        model.addAttribute("cliente", clienteService.getAll());
        //System.out.println("Index");
        return "clientes";
    }

    @PostMapping("/guardarCliente")
    public String saveCliente(@ModelAttribute("cliente") Cliente cliente, Model model) {
        clienteService.saveCliente(cliente);
        model.addAttribute(new Contacto());
        model.addAttribute("cliente", cliente);

        return "registroContactos";
    }

    @RequestMapping("/actualizarCliente/{id}")
    public String clienteUpdateForm(@PathVariable(value = "id") long id, Model model) {
        Cliente cliente = clienteService.getById(id);

        model.addAttribute("cliente", cliente);
        return "actualizarClientes";
    }

    @PostMapping("/actualizarCliente/{id}")
    public String updateCliente(@PathVariable("id") long id, @Valid Cliente cliente, BindingResult result, Model model) {
        clienteService.updateCliente(cliente);
        model.addAttribute("clientes", auditorService.getAll());
        return "clientes";
    }

    @GetMapping(value = "/eliminarCliente/{id}")
    public String deleteCliente(@PathVariable("id") long id, Model model){
        clienteService.deleteCliente(id);
        return "clientes";
    }

    //PERSONAS CONTACTO
    @GetMapping(value = "/registroContactos/{id}")
    public String registroContactos(@PathVariable("id") long id, Model model){
        model.addAttribute(new Contacto());
        Cliente cliente = clienteService.getById(id);
        model.addAttribute("cliente", cliente);
        return "registroContactos";
    }

    @RequestMapping("/allcontactos")
    public String allContactos(Model model){
        model.addAttribute("contactos", contactoService.getAll());
        //System.out.println("Index");
        return "allcontactos";
    }

    @RequestMapping("/contactos/{id}")
    public String contactos(@PathVariable(value = "id") long id, Model model){
        List<Contacto> contactos = contactoService.getContactosByIdCliente(id);
        model.addAttribute("contactos", contactos);
        List<Cliente> clientesList = new ArrayList<Cliente>();
        for (int i = 0; i < contactos.size(); i++) {

            if(contactos.get(i).getIdCliente().equals(clienteService.getById(contactos.get(i).getIdCliente()).getId()) == true) {
                clientesList.add(clienteService.getById(contactos.get(i).getIdCliente()));
            }
        }

        model.addAttribute("clientes", clientesList);

        return "contactos";
    }

    @PostMapping("/guardarContacto")
    public String saveContacto(@ModelAttribute("contacto") Contacto contacto) {
        contactoService.saveContacto(contacto);
        return "redirect:/clientes";
    }

    @RequestMapping("/actualizarContacto/{id}")
    public String contactoUpdateForm(@PathVariable(value = "id") long id, Model model) {
        Contacto contacto = contactoService.getById(id);

        model.addAttribute("contacto", contacto);
        return "actualizarContactos";
    }

    @PostMapping("/actualizarContacto/{id}")
    public String updateContacto(@PathVariable("id") long id, @Valid Contacto contacto, BindingResult result, Model model) {
        contactoService.updateContacto(contacto);
        //model.addAttribute("personasContacto", personaContactoService.getAll());
        return "redirect:/clientes";
    }

    @GetMapping(value = "/eliminarContacto/{id}")
    public String deleteContacto(@PathVariable("id") long id, Model model){
        contactoService.deletePersona(id);
        return "redirect:/clientes";
    }
    /*--------------*/

    /*----Trabajo-----*/
    @RequestMapping(value = "/registroTrabajos", method = RequestMethod.GET)
    public String registroTrabajaos(Model model){
        model.addAttribute(new Trabajo());
        List<Cliente> clientes =  clienteService.getAll();
        model.addAttribute("clientes", clientes);
        return "registroTrabajos";
    }

    @RequestMapping("/trabajos")
    public String trabajos(Model model){
        List<Trabajo> trabajos =  trabajoService.getAll();
        model.addAttribute("trabajos", trabajos);
        List<Cliente> clientes = new ArrayList<Cliente>();
        for (int i = 0; i < trabajos.size(); i++) {

            if(trabajos.get(i).getIdCliente().equals(clienteService.getById(trabajos.get(i).getIdCliente()).getId())) {
                clientes.add(clienteService.getById(trabajos.get(i).getIdCliente()));
            }
        }

        model.addAttribute("clientes", clientes);
        return "trabajos";
    }

    @PostMapping("/guardarTrabajo")
    public String saveTrabajo(@ModelAttribute("trabajo") Trabajo trabajo) {
        trabajoService.saveTrabajo(trabajo);
        return "redirect:/trabajos";
    }

    /*----------------*/


}
