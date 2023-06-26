public class Producto {
    private String nombreDelProducto ;
    private int cantidadDelProducto;
    private double precioUnitario;

    public Producto(String nombreDelProducto , int cantidadDelProducto , double precioUnitario){
        this.nombreDelProducto = nombreDelProducto;
        this.cantidadDelProducto = cantidadDelProducto;
        this.precioUnitario = precioUnitario;
    }

    public boolean getBooleanStock(int numero) {
        if (numero <= cantidadDelProducto){
            return true;
        }
        else {
            System.out.println("NO HAY SUFICIENTE STOCK");
            return false;
        }
    }
    public String getNombreDelProducto(){
        return nombreDelProducto;
    }
    public void setNombreDelProducto(String nombreDelProducto) {
        this.nombreDelProducto = nombreDelProducto;
    }
    public int getCantidadDelProducto(){
        return cantidadDelProducto;
    }
    public void setCantidadDelProducto(int cantidad){
        this.cantidadDelProducto = cantidad;
    }
    public double getPrecioUnitario() {  return precioUnitario;  }
    public void setPrecioUnitario(double precioUnitario){
        this.precioUnitario = precioUnitario;
    }

    public String datosDelProducto(){
        return "NOMBRE:"+ nombreDelProducto + " \nCANTIDAD:" + cantidadDelProducto+ " \nPRECIO DE LA UNIDAD: "+ '$' + precioUnitario + " \n_________________________";
    }

}