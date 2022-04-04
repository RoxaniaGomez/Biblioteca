/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author groxa
 */
@Controller
@RequestMapping("")
public class MainController {
    @PreAuthorize("hasAnyRole('ROLE_USUARIO')")
    @GetMapping("")
    public String index() {
        return "index";
    }
}
