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
    private static int nodoBuscado = -1;

    @GetMapping("/")
    public String mostrarArbol(Model model) {
        model.addAttribute("inorden", obtenerRecorridoInorden());
        model.addAttribute("preorden", obtenerPreorden());
        model.addAttribute("postorden", obtenerPostorden());
        model.addAttribute("amplitud", obtenerAmplitud());
        model.addAttribute("altura", arbol.obtenerAltura());
        model.addAttribute("niveles", arbol.obtenerAltura()- 1);
        model.addAttribute("hojas", arbol.contarHojas());
        model.addAttribute("peso", arbol.obtenerPeso());
        model.addAttribute("mayor", arbol.estaVacio() ? "N/A" : arbol.obtenerNodoMayor());
        model.addAttribute("menor", arbol.estaVacio() ? "N/A" : arbol.obtenerNodoMenor());
        model.addAttribute("nodoBuscado", nodoBuscado);
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
        nodoBuscado = -1;
        return "redirect:/";
    }

    @PostMapping("/buscar-nivel")
    public String buscarNivel(@RequestParam int dato, Model model) {
        int nivel = arbol.obtenerNivel(dato);
        model.addAttribute("nivelDato", nivel == -1 ? "No encontrado" : nivel);
        model.addAttribute("datoBuscado", dato);
        nodoBuscado = dato;
        return mostrarArbol(model);
    }

    private String obtenerRecorridoInorden() {
        StringBuilder resultado = new StringBuilder();
        arbol.inOrden(dato -> resultado.append(dato).append(" "));
        return resultado.toString().trim();
    }

    private String obtenerPreorden() {
        StringBuilder resultado = new StringBuilder();
        arbol.preOrden(dato -> resultado.append(dato).append(" "));
        return resultado.toString().trim();
    }

    private String obtenerPostorden() {
        StringBuilder resultado = new StringBuilder();
        arbol.postOrden(dato -> resultado.append(dato).append(" "));
        return resultado.toString().trim();
    }

    private String obtenerAmplitud() {
        StringBuilder resultado = new StringBuilder();
        arbol.imprimirAmplitud(dato -> resultado.append(dato).append(" "));
        return resultado.toString().trim();
    }
}