/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.controladores;


import egg.web.libreria.entidades.Libro;
import egg.web.libreria.servicios.LibroServicio;
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
@RequestMapping("/libro")
public class LibroController {
    
    @Autowired
    private LibroServicio libroServicio;
    @GetMapping("")
    public String formulario(Model modelo){
    Libro libro = new Libro();
    modelo.addAttribute("libro", libro);
    return "libro";
    
    }
    @GetMapping("/modificar")
    public String formulario(@RequestParam(name = "id", required = true) String id, Model modelo) {
	Libro libro = libroServicio.buscarPorId(id);
	modelo.addAttribute("libro", libro);
	return "libro";
    }
    @PostMapping("/save")
    public String formularioData(@RequestParam("titulo") String titulo, @RequestParam("id") String id, Model modelo) {
	Libro libro = new Libro();
	try {
	    libro.setTitulo(titulo);
           if(id!=null && !id.isEmpty()){
           libro.setId(id);
           } 
            libro.setAlta(true);
	    libroServicio.guardarLibro(libro);
	    modelo.addAttribute("libro", libro);
	    return "libro";
	} catch (Exception ex) {
	    ex.printStackTrace();
	    modelo.addAttribute("libro", libro);
	    modelo.addAttribute("error", ex.getMessage());
	    return "libro";
	}
    }
    @GetMapping("/list")
    public String listAll(Model modelo) {
	List<Libro> libro = libroServicio.listarLibro();
	modelo.addAttribute("listaDeLibros", libro);
	return "libro-listar";
    }

    @GetMapping("/alta")
    public String alta(@RequestParam("id") String id) {
	try {
	    libroServicio.altaLibro(id);
	    return "redirect:/libro/list";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/libro/list";
	}
    }
     @GetMapping("/baja")
    public String baja(@RequestParam("id") String id) {
	try {
	    libroServicio.darBaja(id);
	    return "redirect:/libro/list";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/libro/list";
	}
    }
    

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id) {
	try {
	    libroServicio.borrarLibro(id);
	    return "redirect:/libro/list";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/libro/list";
	}
    }
    
}