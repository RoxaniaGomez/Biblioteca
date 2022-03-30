/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.controladores;


import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.servicios.EditorialServicio;
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
 @RequestMapping("/editorial")
public class EditorialController {
    @Autowired
    private EditorialServicio EditorialServicio;
    
    @GetMapping("")
    public String formulario(Model modelo){
    Editorial editorial = new Editorial();
    modelo.addAttribute("editorial", editorial);
    return "editorial";
    
    }
    @GetMapping("/modificar")
    public String formulario(@RequestParam(name = "id", required = true) String id, Model modelo) {
	Editorial editorial = EditorialServicio.buscarPorId(id);
	modelo.addAttribute("editorial", editorial);
	return "editorial";
    }
    @PostMapping("/save")
    public String formularioData(@RequestParam("nombre") String nombre, @RequestParam("id") String id, Model modelo) {
	Editorial editorial = new Editorial();
	try {
	    editorial.setNombre(nombre);
	    editorial.setId(id);
	    editorial.setAlta(true);
	    EditorialServicio.guardarEditorial(editorial);
	    modelo.addAttribute("editorial", editorial);
	    return "editorial";
	} catch (Exception ex) {                                        
	    ex.printStackTrace();
	    modelo.addAttribute("editorial", editorial);
	    modelo.addAttribute("error", ex.getMessage());
	    return "editoral";
	}
    }
    @GetMapping("/list")
    public String listAll(Model modelo) {
	List<Editorial> editorial = EditorialServicio.listarEditorial();
	modelo.addAttribute("listaDeEditoriales", editorial);
	return "editorial-listar";
    }

    @GetMapping("/alta")
    public String alta(@RequestParam("id") String id) {
	try {
	    EditorialServicio.altaEditorial(id);
	    return "redirect:/editorial/list";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/editorial/list";
	}
    }
        @GetMapping("/baja")
    public String baja(@RequestParam("id") String id) {
	try {
	    EditorialServicio.darBaja(id);
	    return "redirect:/editorial/list";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/editorial/list";
	}
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id) {
	try {
	    EditorialServicio.borrarEditorial(id);
	    return "redirect:/editorial/list";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/editorial/list";
	}
    }
    
}

