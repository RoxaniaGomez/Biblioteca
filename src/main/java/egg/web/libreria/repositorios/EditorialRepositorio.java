/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author groxa
 */
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
     @Query("select a from Editorial a where a.nombre = :nombre")
    public Editorial buscarEditorialPorNombre(@Param("nombre") String nombre);
}

