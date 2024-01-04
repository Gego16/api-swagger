package com.init.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;


import com.init.products.model.Logging;

import java.util.Arrays;
import java.util.List;

@Controller  
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String MICROSERVICIO_URL = "http://localhost:8081/logging/lista";

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        Logging[] logArray = restTemplate.getForObject(MICROSERVICIO_URL, Logging[].class);
        List<Logging> logList = Arrays.asList(logArray);
        model.addAttribute("log", logList);
        return "lista"; 
    }
    
    @GetMapping("/usuarios/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Logging logging = new Logging();
        model.addAttribute("logging", logging);
        return "crear_estudiante";
    }

    @PostMapping("/usuarios")
    public String agregarUsuario(@ModelAttribute("logging") Logging logging) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Utiliza un objeto HttpEntity con las cabeceras y el cuerpo de la solicitud
        HttpEntity<Logging> requestEntity = new HttpEntity<>(logging, headers);

        ResponseEntity<Logging> responseEntity = restTemplate.postForEntity("http://localhost:8081/logging/agregar", requestEntity, Logging.class);


        return "redirect:/usuarios"; // Redirige a la lista después de agregar un usuario
    }
    
    
    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        // Crea un objeto Logging con el ID proporcionado y agrégalo al modelo
        Logging estudiante = new Logging();
        estudiante.setId(id);
        model.addAttribute("estudiante", estudiante);
        // Devuelve el nombre de la vista que se mostrará (en este caso, "editar_estudiante")
        return "editar_estudiante";
    }
    
    @PostMapping("/usuarios/{id}")
    public String editarEstudiante(@PathVariable Long id, @ModelAttribute("estudiante") Logging estudiante) {
        // Ajusta el código para realizar la operación PATCH
        restTemplate.patchForObject("http://localhost:8081/logging/editar/{id}", estudiante, Logging.class, id);
        // Redirige a la lista después de editar un estudiante
        return "redirect:/usuarios";
    }



    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        try {
            restTemplate.delete("http://localhost:8081/logging/eliminar/{id}", id);
            return "redirect:/usuarios";
        } catch (HttpClientErrorException.NotFound e) {
            // Manejar la excepción si el usuario no se encuentra
            System.out.println(e);
            return "redirect:/usuarios";
        }
    }


    

}
