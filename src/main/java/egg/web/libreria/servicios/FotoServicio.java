/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Foto;
import egg.web.libreria.repositorios.FotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author groxa
 */
@Service
public class FotoServicio {
    @Autowired
    private FotoRepositorio fotoRepositorio;

    public Foto guardar(MultipartFile file) throws Exception {

        if (file != null) {
            try {
                Foto f = new Foto();
                f.setName(file.getName());
                f.setMime(file.getContentType());
                f.setContenido(file.getBytes());

                return fotoRepositorio.save(f);
                
            } catch (Exception e) {
                throw new Exception("No se pudo guardar");
            }
        } else {
            return null;
        }

    }

}
