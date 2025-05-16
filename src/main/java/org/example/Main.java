package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ProductDAO dao = new ProductImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("Выберите действие: добавить товар; показать все товары; обновить товар; удалить товар. ");

            switch (scanner.nextLine()) {
                case "добавить товар" -> createProduct();
                case "показать все товары" -> showProducts();
                case "обновить товар" -> updateProduct();
                case "удалить товар" -> deleteProduct();
                default -> System.out.println("Неверный ввод.");
            }
        }
    }

    private static void createProduct() {
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Цена: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        dao.create(new Product(null, name, price, quantity));
    }

    private static void showProducts() {
        List<Product> products = dao.getAll();
        products.forEach(System.out::println);
    }

    private static void updateProduct() {
        System.out.print("ID товара: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.print("Новое название: ");
        String name = scanner.nextLine();
        System.out.print("Новая цена: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Новое количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        dao.update(new Product(id, name, price, quantity));
    }

    private static void deleteProduct() {
        System.out.print("ID товара: ");
        long id = Long.parseLong(scanner.nextLine());
        dao.delete(id);
    }
}