package ra.run;

import ra.entity.Categories;

import java.text.ParseException;
import java.util.Scanner;

import static ra.run.CategoriesMenu.categoriesList;
import static ra.run.ProductMenu.productList;

public class ShopManagement {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***************MENU SHOP**********************");
            System.out.println("1. Management catalog ");
            System.out.println("2. Management product ");
            System.out.println("3. Exit");
            System.out.println("your choice");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    CategoriesMenu.catalogMenu(scanner,categoriesList,productList);
                    break;
                case 2:
                    ProductMenu.productMenu(scanner,productList,categoriesList);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println("choice is 1-3");
            }

        }while (true);
    }
}
