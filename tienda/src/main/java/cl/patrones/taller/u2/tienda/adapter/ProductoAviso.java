package cl.patrones.taller.u2.tienda.adapter;

import java.util.List;

import cl.patrones.taller.u2.bodegaje.domain.Producto;
import cl.patrones.taller.u2.bodegaje.domain.Stock;
import cl.patrones.taller.u2.catalogo.domain.Aviso;
import cl.patrones.taller.u2.catalogo.domain.Categoria;

public class ProductoAviso extends Aviso {

    public ProductoAviso(Producto producto, Categoria categoria) {
        super(
                producto.getId(),
                producto.getSku(),
                producto.getNombre(),
                calcularPrecio(producto.getCosto()),
                producto.getImagen(),
                calcularStock(producto),
                categoria
        );
    }

    private static Long calcularPrecio(Long costo) {
        return Math.round(costo * 1.3);
    }

    @SuppressWarnings("unchecked")
    private static int calcularStock(Producto producto) {
        List<Stock> stocks = (List<Stock>) producto.getStocks();
        return stocks.stream().mapToInt(Stock::getCantidad).sum();
    }
}