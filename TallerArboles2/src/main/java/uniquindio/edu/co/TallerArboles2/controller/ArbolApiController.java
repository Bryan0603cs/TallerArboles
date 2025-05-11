package uniquindio.edu.co.TallerArboles2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import uniquindio.edu.co.TallerArboles2.model.ArbolBinario;
import uniquindio.edu.co.TallerArboles2.model.Node;

@RestController
public class ArbolApiController {

    // Usa el mismo árbol que en ArbolController
    private static final ArbolBinario arbolCompartido = ArbolController.arbol;

    @GetMapping("/api/arbol")
    public Node obtenerArbolComoJson() {
        return arbolCompartido.getRaiz();  // asegúrate de tener getRaiz() en ArbolBinario
    }
}
