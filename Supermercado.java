import java.util.Enumeration;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

// Estos dos paquetes son para utilizar expresiones regulares
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Supermercado {
    String nombreMercado;
    static Hashtable < String , Producto> listaDeProductos = new Hashtable<>();
    public Supermercado(String nombre){
        this.nombreMercado=nombre;
    }

    public void agregarProducto(Producto producto){
        String clave = producto.getNombreDelProducto();
        if(!listaDeProductos.containsKey(clave)){
            listaDeProductos.put(clave , producto);
            System.out.println("Se ha añadido correctamente el producto a la lista.");
        }else{
            System.out.println("Error , no se pudo agregar.");
        }
    }

    public Producto buscarProducto(String palabraBuscada) {

        Pattern pattern = Pattern.compile("(?i)" + Pattern.quote(palabraBuscada) + ".*");

        for (String clave : listaDeProductos.keySet()) {
            Matcher matcher = pattern.matcher(clave);

            if (matcher.matches()) {
                Producto producto = (Producto) listaDeProductos.get(clave);
                return producto;
             }
        }

        return null;
    }


    public double venderProducto(Producto productoAVender, int cantidadSolicitada){

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
    public static void menu(){
        System.out.println("1._Añadir un Producto.");
        System.out.println("2._Mostrar todos los productos.");
        System.out.println("3._Buscar un producto.");
        System.out.println("4._Ventas");
        System.out.println("5._Salir del programa");
        System.out.println("SELECCIONE UNA OPCIION");
    }

    private static void salir(){
        System.out.println("SALIENDO DEL PROGRAMA.....");
        System. exit(0);
    }

    public static void main(String[] args) {

        System.out.println("SISTEMA DE GESTION DE TIENDA");
        System.out.println("*****************************");
        Scanner entrada = new Scanner(System.in);
        Supermercado mi_Mercado = new Supermercado("ArgenChinos");
        int opcion = 0;

        do {
            try {
                menu();
                opcion = entrada.nextInt();

                switch (opcion) {
                    case 1: {

                        entrada.nextLine();
                        System.out.println("Nombre del producto");
                        String nombre = entrada.nextLine();
                        String nombreBonito = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();

                        System.out.println("Cantidad: ");
                        int cantidad = Integer.parseInt(entrada.nextLine());

                        System.out.println("Precio por unidad");
                        double precio = Double.parseDouble(entrada.nextLine());

                        Producto nuevoProducto = new Producto(nombreBonito, cantidad, precio);

                        mi_Mercado.agregarProducto(nuevoProducto);

                        break;
                    }
                    case 2: {;
                        System.out.println("Productos: ");
                        Enumeration productos = listaDeProductos.keys();

                        while (productos.hasMoreElements()) {
                            Object clave = productos.nextElement();
                            Producto producto = listaDeProductos.get(clave);
                            System.out.println(producto.datosDelProducto());
                        }
                        break;
                    }
                    case 3: {
                        entrada.nextLine();
                        System.out.println("Nombre del producto a buscar: ");
                        String palabraBuscada = entrada.nextLine();

                        Producto productoEncontrado = mi_Mercado.buscarProducto(palabraBuscada);

                        if (productoEncontrado != null) {
                            System.out.println("Producto encontrado");
                            System.out.println(productoEncontrado.datosDelProducto());

                        } else {
                            System.out.println("No se encontró el producto");

                        }                        ;

                        break;
                    }
                    case 4: {
                        entrada.nextLine();
                        System.out.println("Nombre del Producto: ");
                        String nombreProducto = entrada.nextLine();

                        Producto productoAVender = mi_Mercado.buscarProducto(nombreProducto);

                        if (productoAVender == null){
                            System.out.println("NO SE ENCONTRÓ EL PRODUCTO" );

                        } else {

                            System.out.println(mi_Mercado.buscarProducto(nombreProducto).datosDelProducto());

                            System.out.println("Cantidad a comprar: ");
                            int cantidad = Integer.parseInt(entrada.nextLine());

                            double precioTotal = mi_Mercado.venderProducto(productoAVender, cantidad);

                            System.out.println("TOTAL VENTA: " + "$" + precioTotal + "\n"
                                    + "El stock fue modificado: " + "\n"
                                    + (mi_Mercado.buscarProducto(nombreProducto) != null ?
                                    mi_Mercado.buscarProducto(nombreProducto).datosDelProducto():
                                    "No hay más Stock" ));
                        }


                        break;

                    }

                }


            } catch (InputMismatchException error) {
                System.err.println("Error de entrada");
                // System.err.println(error);
                entrada.nextLine();

            }

        } while (opcion != 5);

        salir();
    }
}