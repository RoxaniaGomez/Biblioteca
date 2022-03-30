/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Libro;
import egg.web.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author groxa
 */
@Service
public class LibroServicio {
    
     @Autowired
    private LibroRepositorio libroRepositorio;
     
    public Libro guardarLibro(Libro libro) throws Exception {
	if (libro.getTitulo().isEmpty()) {
	    throw new Exception("El nombre libro no puede estar vacio");
	}
	return libroRepositorio.save(libro);
    }

    public Libro modificarLibro(Libro libro, String titulo) throws Exception {
	if (titulo.isEmpty()) {
	    throw new Exception("El titulo del libro no puede estar vacio");
	}
	libro.setTitulo(titulo);
	return libroRepositorio.save(libro);
    }

    

    public List<Libro> listarLibro() {
	return libroRepositorio.findAll();
    }

    public Libro buscarPorTitulo(String titulo) {
	return libroRepositorio.buscarLibroPorTitulo(titulo);
    }
    public Libro buscarPorId(String id) {
       return libroRepositorio.getById(id);
    }
    public void borrarLibro(String id) throws Error {
	Optional<Libro> respuesta = libroRepositorio.findById(id);
	if (respuesta.isPresent()) {
	    Libro libro = respuesta.get();
	    libro.setAlta(false);
	    libroRepositorio.delete(libro);
	} else {
	    throw new Error("no se encontro el titulo");
	}
    }
        public void darBaja(String id) throws Error {
	Optional<Libro> respuesta = libroRepositorio.findById(id);
	if (respuesta.isPresent()) {
	    Libro libro = respuesta.get();
	    libro.setAlta(false);
	    libroRepositorio.save(libro);
	} else {
	    throw new Error("No se encontró un libro");

	}
    }
    public void altaLibro(String id) throws Error {
	Optional<Libro> respuesta = libroRepositorio.findById(id);
	if (respuesta.isPresent()) {
	    Libro libro = respuesta.get();
	    libro.setAlta(true);
	    libroRepositorio.save(libro);
	} else {
	    throw new Error("No se encontró un libro");

	}
    }

}
