import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;

public class Administrador extends Supermercado{

    public Administrador(String nombreMercado) {
        super(nombreMercado);
    }

    public void modificarNombreProducto (String nombreProducto, Hashtable listaDeProductos, String nuevoNombre) {
       Producto producto = super.buscarProducto(nombreProducto, listaDeProductos);

       producto.setNombreDelProducto(nuevoNombre);
    }

    public void agregarCantidad (String nombreProducto, Hashtable listaDeProductos, int cantidadAgregada) {
        Producto producto = super.buscarProducto(nombreProducto, listaDeProductos);

       producto.setCantidadDelProducto(producto.getCantidadDelProducto() + cantidadAgregada);
    }
    public void restarCantidad (String nombreProducto, Hashtable listaDeProductos, int cantidadRestada) {
        Producto producto = super.buscarProducto(nombreProducto, listaDeProductos);

        if (producto.getCantidadDelProducto() > cantidadRestada){
        producto.setCantidadDelProducto(producto.getCantidadDelProducto() - cantidadRestada);
        } else {
            System.out.println("No hay suficiente stock");
        }
    }

    public void modificarCantidad (String nombreProducto, Hashtable listaDeProductos, int nuevaCantidad){
        Producto producto = super.buscarProducto(nombreProducto, listaDeProductos);
        producto.setCantidadDelProducto(nuevaCantidad);

    }
    public void modificarPrecio (String nombreProducto, Hashtable listaDeProductos, double nuevoPrecio){
        Producto producto = super.buscarProducto(nombreProducto, listaDeProductos);
        producto.setPrecioUnitario(nuevoPrecio);

    }

    // Esto se podria hacer un abstract en supermercado
    @Override public double venderProducto(Producto productoAVender, int cantidadSolicitada, Hashtable listaDeProductos){
        String nombreProducto = productoAVender.getNombreDelProducto();
        double precioUnitario = productoAVender.getPrecioUnitario();
        double precioUnitarioConDescuento = precioUnitario - (precioUnitario * 0.30);
        int cantidadDelProducto = productoAVender.getCantidadDelProducto();
        boolean hayStock = productoAVender.getBooleanStock(cantidadSolicitada);
        double precioTotal = 0;

        if (hayStock) {
            int coso =  cantidadDelProducto - cantidadSolicitada;
            precioTotal = cantidadSolicitada * precioUnitarioConDescuento;
            productoAVender.setCantidadDelProducto(coso);

            if(cantidadDelProducto == 0){
                listaDeProductos.remove(nombreProducto);
            }
            return precioTotal;
        }
        return precioTotal;
    }

}
