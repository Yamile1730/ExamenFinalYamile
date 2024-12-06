
import java.io.*;
import java.util.*;

public class Inventario {
    private List<Producto> productos = new ArrayList<>();
    private final String archivoInventario = "productos.txt";
    Scanner scanner = new Scanner(System.in);

    // Constructor: Carga los productos desde el archivo
    public Inventario() {
        cargarProductos();
    }
    private void cargarProductos() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoInventario))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(", ");
                if (partes.length == 5) {
                    productos.add(new Producto(
                    Integer.parseInt(partes[0]),
                            partes[1],
                            partes[2],
                            Double.parseDouble(partes[3]),
                            Integer.parseInt(partes[4])
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        }
    }

    public void agregarProducto() {
        Producto nuevoProducto=new Producto();
        nuevoProducto.setId(productos.size()+1);

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        nuevoProducto.setNombre(nombre);
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        nuevoProducto.setCategoria(categoria);
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        nuevoProducto.setPrecio(precio);
        System.out.print("Cantidad disponible: ");
        int cantidad = scanner.nextInt();
        nuevoProducto.setCantidadDisponible(cantidad);
        productos.add(nuevoProducto);
        guardarCambios();
    }

    public void actualizarProducto(int id, Producto nuevoProducto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.set(i, nuevoProducto);
                guardarCambios();
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public void eliminarProducto(int id) {
        productos.removeIf(p -> p.getId() == id);
        guardarCambios();
    }

    public List<Producto> buscarPorCategoria(int opcion) {
        List<Producto> resultado = new ArrayList<>();
        switch (opcion){
            case 1 -> {
                System.out.println("Ingrese ID Producto");
                int id = scanner.nextInt();
                for (Producto p : productos) {
                    if (p.getId()==id) {
                        resultado.add(p);
                    }
                }
            }
            case 2 -> {
                System.out.println("Ingrese Categoria Producto");
                String categoria = scanner.nextLine();
                for (Producto p : productos) {
                    if (p.getCategoria().equalsIgnoreCase(categoria)) {
                        resultado.add(p);
                    }
                }
            }
            case 3 -> {
                System.out.println("Ingrese Nombre Producto");
                String nombre = scanner.nextLine();
                for (Producto p : productos) {
                    if (p.getNombre().equalsIgnoreCase(nombre)) {
                        resultado.add(p);
                    }
                }
            }
        }
        return resultado;
    }

    public Producto productoMasCaro() {
        return productos.stream().max(Comparator.comparingDouble(Producto::getPrecio)).orElse(null);
    }

    public Map<String, Integer> cantidadPorCategoria() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Producto p : productos) {
            conteo.put(p.getCategoria(), conteo.getOrDefault(p.getCategoria(), 0) + p.getCantidadDisponible());
        }
        return conteo;
    }

    public void generarReporte() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("reporte_inventario.txt"))) {
            double valorTotal = 0;
            for (Producto p : productos) {
                bw.write(p.toString());
                bw.newLine();
                valorTotal += p.getPrecio() * p.getCantidadDisponible();
            }
            bw.write("Valor total del inventario: " + valorTotal);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

    private void guardarCambios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoInventario))) {
            for (Producto p : productos) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar cambios: " + e.getMessage());
        }
    }
}