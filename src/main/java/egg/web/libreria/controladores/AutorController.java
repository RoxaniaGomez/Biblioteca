/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author groxa
 */
@Controller
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorServicio autorServicio;
    @GetMapping("")
    public String formulario(Model modelo){
    Autor autor = new Autor();
    modelo.addAttribute("autor", autor);
    return "autor";
    
    }
    @GetMapping("/modificar")
    public String formulario(@RequestParam(name = "id", required = true) String id, Model modelo) {
	Autor autor = autorServicio.buscarPorId(id);
	modelo.addAttribute("autor", autor);
	return "autor";
    }
    @PostMapping("/save")
    public String formularioData(@RequestParam("nombre") String nombre, @RequestParam("id") String id, Model modelo) {
	Autor autor = new Autor();
	try {
	    autor.setNombre(nombre);
	    autor.setId(id);
	    autor.setAlta(true);
	    autorServicio.guardarAutor(autor);
	    modelo.addAttribute("autor", autor);
	    return "autor";
	} catch (Exception ex) {
	    ex.printStackTrace();
	    modelo.addAttribute("autor", autor);
	    modelo.addAttribute("error", ex.getMessage());
	    return "autor";
	}
    }
    @GetMapping("/list")
    public String listAll(Model modelo) {
	List<Autor> autores = autorServicio.listarAutoresl();
	modelo.addAttribute("listaDeAutores", autores);
	return "autor-listar";
    }

    @GetMapping("/alta")
    public String alta(@RequestParam("id") String id) {
	try {
	    autorServicio.altaAutor(id);
	    return "redirect:/autor/list";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/autor/list";
	}
    }
      @GetMapping("/baja")
    public String baja(@RequestParam("id") String id) {
	try {
	    autorServicio.darBaja(id);
	    return "redirect:/autor/list";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/autor/list";
	}
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id) {
	try {
	    autorServicio.borrarAutor(id);
	    return "redirect:/autor/list";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/autor/list";
	}
    }
    
}

