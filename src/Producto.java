


public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String categoria;
    private double precio;
    private int cantidadDisponible;

    public Producto(int idProducto, String nombreProducto, String categoria, double precio, int cantidadDisponible) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }
    public Producto(){}

    // Getters y Setters
    public int getId() { return idProducto; }
    public void setId(int id) { this.idProducto = id; }

    public String getNombre() { return nombreProducto; }
    public void setNombre(String nombre) { this.nombreProducto = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(int cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }

    public String toString() {
    return idProducto + ", " + nombreProducto + ", " + categoria + ", " + precio + ", " + cantidadDisponible;
    }
}