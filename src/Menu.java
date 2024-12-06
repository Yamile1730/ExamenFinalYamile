

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de Inventario:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Actualizar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Buscar por categoría");
            System.out.println("5. Generar reporte");
            System.out.println("6. Cantidad de productos por categoría");
            System.out.println("7. Producto más caro");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    inventario.agregarProducto();
                }
                case 2 -> {
                    System.out.print("ID del producto a actualizar: ");
                    String id = scanner.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Nueva categoría: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Nuevo precio: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Nueva cantidad: ");
                    int cantidad = scanner.nextInt();
                    inventario.actualizarProducto(Integer.parseInt(id), new Producto(Integer.parseInt(id), nombre, categoria, precio, cantidad));
                }
                case 3 -> {
                    System.out.print("ID del producto a eliminar: ");
                    String id = scanner.nextLine();
                    inventario.eliminarProducto (Integer.parseInt(id));
                }
                case 4 -> {
                    System.out.println("Menu Busqueda");
                    System.out.println("1.Buscar Id");
                    System.out.println("2.Buscar Categoria");
                    System.out.println("3.Buscar Nombre");
                    int categoriaopcion = scanner.nextInt();
                    inventario.buscarPorCategoria(categoriaopcion).forEach(System.out::println);

                }
                case 5 -> inventario.generarReporte();
                case 6 -> inventario.cantidadPorCategoria().forEach((cat, cant) ->
                        System.out.println(cat + ": " + cant));
                case 7 -> {
                    Producto caro = inventario.productoMasCaro();
                    System.out.println(caro != null ? caro : "No hay productos.");
                }
                case 8 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 8);

        scanner.close();
    }
}