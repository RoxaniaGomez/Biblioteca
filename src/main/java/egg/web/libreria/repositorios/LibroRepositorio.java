/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.repositorios;


import egg.web.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author groxa
 */
public interface LibroRepositorio extends JpaRepository<Libro, String>{
    @Query("select a from Libro a where a.titulo = :titulo")
    public Libro buscarLibroPorTitulo(@Param("titulo") String titulo);
}
