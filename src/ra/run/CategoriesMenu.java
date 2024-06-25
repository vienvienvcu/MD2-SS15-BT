package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoriesMenu {
    public static List<Categories> categoriesList = new ArrayList<Categories>();
    public static void catalogMenu(Scanner scanner,List<Categories> categoriesList,List<Product> productList){

        boolean isExit = true;
        do {
            System.out.println("**************MANAGEMENT CATALOG**************************");
            System.out.println("1. Add Category");
            System.out.println("2. Show Category");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("5. update category status");
            System.out.println("6. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addCategory(scanner,categoriesList);
                    break;
                case 2:
                    showCategory();
                    break;
                case 3:
                    updateCategory(scanner,categoriesList);
                    showCategory();
                    break;
                case 4:
                    deleteCategory(scanner,categoriesList,productList);
                    break;
                case 5:
                    updateCategoryStatus(scanner,categoriesList);
                    break;
                case 6:
                    isExit = false;
                    break;
            }
        }while (isExit);
    }

    private static void updateCategoryStatus(Scanner scanner, List<Categories> categoriesList) {
        System.out.println("input id category you want to update status");
        int categoryId = Integer.parseInt(scanner.nextLine());
        int indexStatusUpdate = getIndexById(categoriesList,categoryId);
        if (indexStatusUpdate>=0){
            for (int i=0;i<categoriesList.size();i++) {
                if (categoriesList.get(i).getCatalogId() == categoryId){
                    categoriesList.get(i).setCatalogStatus(!categoriesList.get(i).isCatalogStatus());
                    break;
                }
            }
            System.out.println("update category status success");

        }else {
            System.out.println("category id not found, cannot update status");
        }

    }

    private static void deleteCategory(Scanner scanner, List<Categories> categoriesList,List<Product> productList) {
        System.out.println("input id category you want to delete");
        int categoryId = Integer.parseInt(scanner.nextLine());
        int indexDeleteCategory = getIndexById(categoriesList,categoryId);
        if (indexDeleteCategory>=0){
            boolean isExist = false;
            for (Product p : productList) {
                if (p.getCatalogId() == categoryId) {
                    isExist = true;
                    break;
                }
            }
            if (isExist){
                System.err.println("cannot delete category, because it is already exist in product list");
            }else {
                for (Categories c : categoriesList) {
                    if (c.getCatalogId() == categoryId) {
                        categoriesList.remove(c);
                        break;
                    }
                }
                System.out.println("category deleted successfully");
            }
        }else {
            System.err.println("category id not found, cannot delete category");
        }
    }

    private static void updateCategory(Scanner scanner, List<Categories> categoriesList) {
        System.out.println("input id category you want to update");
        int categoryId = Integer.parseInt(scanner.nextLine());
        int indexUpdateCategory = getIndexById(categoriesList,categoryId);
        if (indexUpdateCategory>=0){
            for (Categories category : categoriesList) {
                if (category.getCatalogId() == categoryId) {
                      category.updateData(scanner,categoriesList);
                }
            }

        }else {
            System.err.println("category id not found,cannot update category");
        }
    }

    private static int getIndexById(List<Categories> categoriesList, int categoryId) {
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCatalogId() == categoryId ){
                return i;
            }
        }
        return -1;
    }

    private static void showCategory() {
        for (Categories category : categoriesList) {
            category.displayData();
        }
    }

    public static void addCategory(Scanner scanner, List<Categories> categoriesList){
        System.out.println("Add book number want input information:");
        int bookNumber = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < bookNumber; i++) {
            System.out.println("Information catalog number " + (i+1) + ":");
           Categories category = new Categories();
           category.inputData(scanner, categoriesList);
           categoriesList.add(category);
        }
    }

}
