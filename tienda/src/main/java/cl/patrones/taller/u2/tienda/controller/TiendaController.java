package cl.patrones.taller.u2.tienda.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.patrones.taller.u2.bodegaje.domain.Producto;
import cl.patrones.taller.u2.bodegaje.service.BodegajeService;
import cl.patrones.taller.u2.catalogo.domain.Aviso;
import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.catalogo.domain.Clasificacion;
import cl.patrones.taller.u2.catalogo.repository.ClasificacionRepository;
import cl.patrones.taller.u2.catalogo.service.CategoriaService;
import cl.patrones.taller.u2.tienda.adapter.ProductoAviso;

@Controller
public class TiendaController {

	private final BodegajeService bodegajeService;
	private final ClasificacionRepository clasificacionRepository;
	private final CategoriaService categoriaService;

	public TiendaController(
			BodegajeService bodegajeService,
			ClasificacionRepository clasificacionRepository,
			CategoriaService categoriaService) {
		this.bodegajeService = bodegajeService;
		this.clasificacionRepository = clasificacionRepository;
		this.categoriaService = categoriaService;
	}

	@GetMapping("/")
	public String inicio(Model model) {

		List<Aviso> avisos = bodegajeService.getProductos()
				.stream()
				.map(producto -> clasificacionRepository.findFirstBySku(producto.getSku())
						.map(Clasificacion::getCategoria)
						.map(categoria -> new ProductoAviso(producto, categoria))
						.orElse(null))
				.filter(aviso -> aviso != null)
				.collect(Collectors.toList());

		model.addAttribute("avisos", avisos);
		return "inicio";
	}

	@GetMapping("/categoria/{categoriaId}/{slug}")
	public String categoria(
			@PathVariable(name = "categoriaId") Long categoriaId,
			@PathVariable(name = "slug") String slug,
			Model model
	) {
		Categoria categoria = categoriaService.getCategoriaPorId(categoriaId).orElse(null);

		if (categoria == null) {
			model.addAttribute("avisos", Collections.emptyList());
			return "categoria";
		}

		List<Clasificacion> clasificaciones = clasificacionRepository.findByCategoriaId(categoriaId);

		Map<String, Categoria> categoriasPorSku = clasificaciones.stream()
				.collect(Collectors.toMap(
						Clasificacion::getSku,
						Clasificacion::getCategoria
				));

		String[] skus = clasificaciones.stream()
				.map(Clasificacion::getSku)
				.toArray(String[]::new);

		List<Aviso> avisos = (List<Aviso>) (skus.length == 0
                        ? Collections.emptyList()
                        : bodegajeService.getProductosBySku(skus)
                          .stream()
                          .map(producto -> new ProductoAviso(producto, categoriasPorSku.get(producto.getSku())))
                          .collect(Collectors.toList()));

		model.addAttribute("avisos", avisos);
		model.addAttribute("categoria", categoria);
		return "categoria";
	}

	@GetMapping("/ingresar")
	public String login() {
		return "login";
	}

	@GetMapping("/ubicacion")
	public String ubicacion() {
		return "ubicacion";
	}

	@GetMapping("/contacto")
	public String contacto() {
		return "contacto";
	}
}