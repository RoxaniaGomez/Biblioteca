/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Libro;
import egg.web.libreria.entidades.Usuario;
import egg.web.libreria.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author groxa
 */
@Controller
@RequestMapping("/foto")
public class FotoController {
    @Autowired
    private LibroServicio libroServicio;
    
    @GetMapping("/libro")
    public ResponseEntity<byte[]> fotoPerfil(@RequestParam("idLibro") String id) {

        try {
            Libro libro = libroServicio.buscarPorId(id);
            byte[] foto = libro.getImg().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);           
        }

    }

}
