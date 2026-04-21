package cl.patrones.taller.u2.tienda.menu.util;

import java.util.ArrayList;
import java.util.List;

import cl.patrones.taller.u2.tienda.menu.ItemMenu;

public class MenuItem implements ItemMenu {

    private String texto;
    private String enlace;
    private String slug;
    private List<ItemMenu> hijos;

    public MenuItem(String texto, String enlace) {
        this.texto = texto;
        this.enlace = enlace;
        this.slug = Slugger.toSlug(texto);
        this.hijos = new ArrayList<>();
    }

    public void addHijo(ItemMenu item) {
        this.hijos.add(item);
    }

    @Override
    public String getTexto() {
        return texto;
    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public String getEnlace() {
        return enlace;
    }

    @Override
    public boolean tieneHijos() {
        return !hijos.isEmpty();
    }

    @Override
    public List<? extends ItemMenu> getHijos() {
        return hijos;
    }

    @Override
    public String getSlung() {
        return "";
    }
}