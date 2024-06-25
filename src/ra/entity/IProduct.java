package ra.entity;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public interface IProduct {
    void inputProduct(Scanner scanner, List<Product> productList, List<Categories> categoriesList) throws ParseException;
    ;
    void displayProduct();

}
