package cl.patrones.taller.u2.tienda.controller;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.tienda.adapter.CategoriaMenuAdapter;
import cl.patrones.taller.u2.tienda.menu.ItemMenu;
import cl.patrones.taller.u2.tienda.menu.util.MenuItem;
import cl.patrones.taller.u2.catalogo.service.CategoriaService;
@ControllerAdvice
public class MenuControllerAdvice {

	private final CategoriaService categoriaService;

	public MenuControllerAdvice(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@ModelAttribute("menu")
	public List<ItemMenu> menu() {

		List<ItemMenu> menu = new ArrayList<>();

		// Enlaces comunes
		menu.add(new MenuItem("Inicio", "/"));

		MenuItem categoriasMenu = new MenuItem("Categorías", "/categoria");

		List<Categoria> categorias = categoriaService.getCategorias();

		Map<Long, CategoriaMenuAdapter> mapa = new LinkedHashMap<>();


		for (Categoria categoria : categorias) {
			mapa.put(categoria.getId(), new CategoriaMenuAdapter(categoria));
		}


		for (Categoria categoria : categorias) {
			CategoriaMenuAdapter actual = mapa.get(categoria.getId());

			if (categoria.getPadre() != null) {
				CategoriaMenuAdapter padre = mapa.get(categoria.getPadre().getId());
				if (padre != null) {
					padre.addHijo(actual);
				}
			}
		}


		for (Categoria categoria : categorias) {
			if (categoria.getPadre() == null) {
				categoriasMenu.addHijo(mapa.get(categoria.getId()));
			}
		}

		menu.add(categoriasMenu);
		menu.add(new MenuItem("Ubicación", "/ubicacion"));
		menu.add(new MenuItem("Contacto", "/contacto"));

		return menu;
	}
}
