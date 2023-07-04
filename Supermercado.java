import java.security.Key;
import java.util.Hashtable;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Supermercado {
    protected String nombreMercado;
    public Supermercado(String nombreMercado){
        this.nombreMercado=nombreMercado;

    }
    public void agregarProducto(Producto producto, Hashtable listaDeProductos){
        String clave = producto.getNombreDelProducto();

        if(!listaDeProductos.containsKey(clave)){
            listaDeProductos.put(clave , producto);
            System.out.println("Se ha añadido correctamente el producto a la lista.");

        }else{
            System.out.println("Error: Ya existe un producto con ese nombre");
        }
    }

    public Producto buscarProducto(String palabraBuscada, Hashtable listaDeProductos) {

        Pattern pattern = Pattern.compile("(?i)" + Pattern.quote(palabraBuscada) + ".*");

        Set <String> claves = listaDeProductos.keySet();

        for (String clave : claves) {
            Matcher matcher = pattern.matcher(clave);

            if (matcher.matches()) {
                Producto producto = (Producto) listaDeProductos.get(clave);

                return producto;
             }
        }

        return null;


    }

        public double venderProducto(Producto productoAVender, int cantidadSolicitada, Hashtable listaDeProductos){

        String nombreProducto = productoAVender.getNombreDelProducto();
        double precioUnitario = productoAVender.getPrecioUnitario();
        int cantidadDelProducto = productoAVender.getCantidadDelProducto();
        boolean hayStock = productoAVender.getBooleanStock(cantidadSolicitada);
        double precioTotal = 0;

        if (hayStock) {
            int coso =  cantidadDelProducto - cantidadSolicitada;
            precioTotal = cantidadSolicitada * precioUnitario;
            productoAVender.setCantidadDelProducto(coso);

            if(cantidadDelProducto == 0){
                listaDeProductos.remove(nombreProducto);
            }
            return precioTotal;
        }
         return precioTotal;
    }
}


