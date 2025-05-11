package uniquindio.edu.co.TallerArboles2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniquindio.edu.co.TallerArboles2.model.ArbolBinario;


@Controller
public class ArbolController {

    public static final ArbolBinario arbol = new ArbolBinario();


    @GetMapping("/")
    public String mostrarArbol(Model model) {
        model.addAttribute("inorden", obtenerRecorridoInorden());
        model.addAttribute("amplitud", obtenerAmplitud());
        model.addAttribute("altura", arbol.obtenerAltura());
        model.addAttribute("hojas", arbol.contarHojas());
        return "arbol";
    }

    @PostMapping("/insertar")
    public String insertar(@RequestParam int dato) {
        arbol.agregarDato(dato);
        return "redirect:/";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam int dato) {
        arbol.eliminarDato(dato);
        return "redirect:/";
    }

    @PostMapping("/borrar")
    public String borrar() {
        arbol.borrarArbol();
        return "redirect:/";
    }

    private String obtenerRecorridoInorden() {
        StringBuilder resultado = new StringBuilder();
        recorrerInorden(arbol, resultado);
        return resultado.toString().trim();
    }

    private void recorrerInorden(ArbolBinario arbol, StringBuilder resultado) {
        arbol.inOrden((dato) -> resultado.append(dato).append(" "));
    }

    private String obtenerAmplitud() {
        StringBuilder resultado = new StringBuilder();
        arbol.imprimirAmplitud((dato) -> resultado.append(dato).append(" "));
        return resultado.toString().trim();
    }
}
