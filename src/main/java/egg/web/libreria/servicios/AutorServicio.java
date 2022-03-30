/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author groxa
 */
@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    public Autor guardarAutor(Autor autor) throws Exception {
	if (autor.getNombre().isEmpty()) {
	    throw new Exception("El nombre del autor no puede estar vacio");
	}
	return autorRepositorio.save(autor);
    }

    public Autor modificarAutor(Autor autor, String nombre) throws Exception {
	if (nombre.isEmpty()) {
	    throw new Exception("El nuevo nombre del autor no puede estar vacio");
	}
	autor.setNombre(nombre);
	return autorRepositorio.save(autor);
    }


public void darBaja(String id) throws Error {
	Optional<Autor> respuesta = autorRepositorio.findById(id);
	if (respuesta.isPresent()) {
	    Autor autor = respuesta.get();
	    autor.setAlta(false);
	    autorRepositorio.save(autor);
	} else {
	    throw new Error("No se encontró un autor con ese nombre");

	}
    }

    public List<Autor> listarAutoresl() {
	return autorRepositorio.findAll();
    }

    public Autor buscarPorNombre(String nombre) {
	return autorRepositorio.buscarAutorPorNombre(nombre);
    }
    public Autor buscarPorId(String id) {
       return autorRepositorio.getById(id);
    }
    public void borrarAutor(String id) throws Error {
	Optional<Autor> respuesta = autorRepositorio.findById(id);
	if (respuesta.isPresent()) {
	    Autor autor = respuesta.get();
	    autor.setAlta(false);
	    autorRepositorio.delete(autor);
	} else {
	    throw new Error("No se encontró un autor con ese nombre");
	}
    }

    public void altaAutor(String id) throws Error {
	Optional<Autor> respuesta = autorRepositorio.findById(id);
	if (respuesta.isPresent()) {
	    Autor autor = respuesta.get();
	    autor.setAlta(true);
	    autorRepositorio.save(autor);
	} else {
	    throw new Error("No se encontró un autor con ese nombre");

	}
    }

}
