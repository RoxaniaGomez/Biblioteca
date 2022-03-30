/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.servicios;


import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author groxa
 */
@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    public Editorial guardarEditorial(Editorial editorial) throws Exception {
	if (editorial.getNombre().isEmpty()) {
	    throw new Exception("El nombre no puede estar vacio");
	}
	return editorialRepositorio.save(editorial);
    }

    public Editorial modificarAutor(Editorial editorial, String nombre) throws Exception {
	if (nombre.isEmpty()) {
	    throw new Exception("El nuevo nombre de la editorial no puede estar vacio");
	}
	editorial.setNombre(nombre);
	return editorialRepositorio.save(editorial);
    }

    public void darBaja(String id) throws Error {
	Optional<Editorial> respuesta = editorialRepositorio.findById(id);
	if (respuesta.isPresent()) {
	    Editorial edit = respuesta.get();
	    edit.setAlta(false);
	    editorialRepositorio.save(edit);
	} else {
	    throw new Error("No se encontró la editorial con ese id");

	}
    }

    public List<Editorial> listarEditorial() {
	return editorialRepositorio.findAll();
    }

    public Editorial buscarPorNombre(String nombre) {
	return editorialRepositorio.buscarEditorialPorNombre(nombre);
    }
    public Editorial buscarPorId(String id) {
       return editorialRepositorio.getById(id);
    }
    public void borrarEditorial(String id) throws Error {
	Optional<Editorial> respuesta = editorialRepositorio.findById(id);
	if (respuesta.isPresent()) {
	    Editorial edit = respuesta.get();
	    edit.setAlta(false);
	    editorialRepositorio.delete(edit);
	} else {
	    throw new Error("No se encontró una editorial con ese nombre");
	}
    }

    public void altaEditorial(String id) throws Error {
	Optional<Editorial> respuesta = editorialRepositorio.findById(id);
	if (respuesta.isPresent()) {
	    Editorial edit = respuesta.get();
	    edit.setAlta(true);
	    editorialRepositorio.save(edit);
	} else {
	    throw new Error("No se encontró la editorial con ese id");

	}
    }

}
