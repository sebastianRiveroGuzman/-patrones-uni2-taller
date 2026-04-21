package cl.patrones.taller.u2.tienda.catalogo;

import cl.patrones.taller.u2.catalogo.domain.Categoria;

public class Aviso {

    private String titulo;
    private String sku;
    private int precio;
    private String imagen;
    private int stock;
    private String categoria;

    public Aviso(Long id, String sku, String nombre, Long aLong, String imagen, int i, Categoria categoria) {
    }

    public Aviso(String titulo, String sku, int precio, String imagen, int stock, String categoria) {
        this.titulo = titulo;
        this.sku = sku;
        this.precio = precio;
        this.imagen = imagen;
        this.stock = stock;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}