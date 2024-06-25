package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.security.cert.CertificateRevokedException;
import java.text.ParseException;
import java.util.*;


public class ProductMenu {
    public static List<Product> productList = new ArrayList<Product>();
    public static void productMenu
            (Scanner scanner, List<Product> productList,List<Categories> categoriesList)
            throws ParseException {
        boolean isExit = true;
        do {
            System.out.println("**************MANAGEMENT PRODUCT**************************");
            System.out.println("1. Add product");
            System.out.println("2. Show product");
            System.out.println("3. Sort product by price ");
            System.out.println("4. Update product by product id ");
            System.out.println("5. Delete product ");
            System.out.println("6. Search product by product name ");
            System.out.println("7. Search for products in the price range a – b (a,b entered from the keyboard)");
            System.out.println("8. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addProduct(scanner,productList,categoriesList);
                    break;
                case 2:
                    showProductMenu();
                    break;
                case 3:
                    sortProductByPrice(productList);
                    break;
                case 4:
                    updateProductMenu(scanner,productList,categoriesList);
                    break;
                case 5:
                    deleteProduct(scanner,productList);
                    break;
                case 6:
                    searchProductByname(scanner,productList);
                    break;
                case 7:
                    searchProductByPriceRange(scanner,productList);
                    break;
                case 8:
                    searchProductByPriceRange(scanner,productList);
                    isExit = false;
                    break;
            }
        }while (isExit);
    }

    private static void searchProductByPriceRange(Scanner scanner, List<Product> productList) {
        System.out.println("Enter price start of product");
        float priceStart = Float.parseFloat(scanner.nextLine());
        System.out.println("Enter price end of product");
        float priceEnd = Float.parseFloat(scanner.nextLine());
        for (Product product : productList){
            if (product.getPrice()>=priceStart && product.getPrice()<= priceEnd){
                product.displayProduct();
            }
        }
        System.out.println("Search product by price range successfully");
    }

    private static void searchProductByname(Scanner scanner, List<Product> productList) {
        System.out.println("Enter product name you want to search: ");
        String productName = scanner.nextLine().toLowerCase();
        boolean isExist = false;
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(productName)){
                isExist = true;
                product.displayProduct();
            }
        }
        if (!isExist) {
            System.err.println("Product does not exist");
        }

    }

    private static void deleteProduct(Scanner scanner, List<Product> productList) {
        System.out.println("Input product id to delete: ");
        String productId = scanner.nextLine();
        int indexDeleted = getIndexByProductId(productId,productList);
        if (indexDeleted >=0) {
            for (Product product : productList) {
                if (product.getProductId().equals(productId)) {
                    productList.remove(product);
                }
            }
            System.out.println("Product deleted successfully");

        }else {
            System.err.println("product id not found, cannot delete product");
        }


    }

    public static void addProduct
            (Scanner scanner,List<Product> productList ,List<Categories> categoriesList )
            throws ParseException {
        System.out.println("Add book number want input information:");
        int numberProduct = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberProduct; i++) {
            System.out.println("Information catalog number " + i + ":");
            Product product = new Product();
            product.inputProduct(scanner,productList,categoriesList);
            productList.add(product);
        }
    }

    public static void showProductMenu(){
        for (Product product : productList) {
            product.displayProduct();
        }
    }

    public static void sortProductByPrice(List<Product> productList){
        productList.sort(new SortProductByPrice());
        System.out.println("you sort successfully");
    }

    public static  void updateProductMenu(Scanner scanner,List<Product> productList,List<Categories> categoriesList) throws ParseException {
        System.out.println("Input product id to update:");
        String productId = scanner.nextLine();
        int indexProductUpdate = getIndexByProductId(productId,productList);
        if (indexProductUpdate>=0){
            for (Product product : productList) {
                if (product.getProductId().equals(productId)){
                    product.UpdateProduct(scanner,productList,categoriesList);
                }
            }
            System.out.println("Product updated successfully");
        }else {
            System.err.println("product id not found, because the product cannot be updated");
        }

    }

    public static int getIndexByProductId(String productId,List<Product> productList){
         for (int i = 0; i < productList.size(); i++) {
             if (productList.get(i).getProductId().equals(productId)) {
                 return i;
             }
         }
         return -1;
    }
}

