package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IProduct {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public void inputProduct(Scanner scanner, List<Product> productList, List<Categories> categoriesList) throws ParseException {
// 1. productId – String: mã sản phẩm đồ uống, gồm 4 ký tự bắt đầu là một trong 3 ký tự (C:, S:, A:), không được trùng lặp
        inputProductId(scanner,productList);
// 2. ProductName – String: tên sản phẩm đồ uống, có từ 10-50 ký tự, không được trùng lặp
        inputProductName(scanner,productList);
// 3. Price – float: giá sản phẩm có giá trị lớn hơn 0
        inputPrice(scanner);
// 4. Description – String: mô tả sản phẩm
        inputDescription(scanner);
// 5. Created – date: ngày nhập sản phẩm có định dạng dd/mm/yyyy
        inputCreated(scanner);
// 6. CatalogId – int: Mã danh mục mà sản phẩm thuộc về
        inputCatalogIdOfProduct(scanner,categoriesList);
// 7. ProductStatus – int: trạng thái sản phẩm, chỉ nhận 1 trong các trạng thái sau (0: Đang bán – 1: Hết hàng – 2: Không bán)
        inputProductStatus(scanner);
    }

    public void UpdateProduct(Scanner scanner, List<Product> productList, List<Categories> categoriesList) throws ParseException {
// 2. ProductName – String: tên sản phẩm đồ uống, có từ 10-50 ký tự, không được trùng lặp
        inputProductName(scanner,productList);
// 3. Price – float: giá sản phẩm có giá trị lớn hơn 0
        inputPrice(scanner);
// 4. Description – String: mô tả sản phẩm
        inputDescription(scanner);
// 5. Created – date: ngày nhập sản phẩm có định dạng dd/mm/yyyy
        inputCreated(scanner);
// 6. CatalogId – int: Mã danh mục mà sản phẩm thuộc về
        inputCatalogIdOfProduct(scanner,categoriesList);
// 7. ProductStatus – int: trạng thái sản phẩm, chỉ nhận 1 trong các trạng thái sau (0: Đang bán – 1: Hết hàng – 2: Không bán)
        inputProductStatus(scanner);
    }


    public String inputProductId(Scanner scanner,List<Product> productList){
        System.out.println("Enter product ID");
        do {
           String productId = scanner.next();
           String productIdRegex = "[CSA]\\w{3}";
            if (Pattern.matches(productIdRegex, productId)) {
                boolean isExist = false;
                for(Product p : productList){
                    if (p.getProductId().equals(productId)){
                        isExist = true;
                        break;
                    }
                }
                if (isExist){
                    System.err.println("Product Id Already Exists");
                }else {
                    return this.productId = productId;
                }
            }else {
                System.err.println("product ID is not correct,must have 4 chars and start C,S or A , please try again");
            }
        }while (true);

    }

    public String inputProductName(Scanner scanner,List<Product> productList){
        System.out.println("Enter product name");
        do {
            String productName = scanner.nextLine();
            if (productName.length()>=10 && productName.length()<=50){
                boolean isExist = false;
                for(Product p : productList){
                    if (p.getProductName().equals(productName)){
                        isExist = true;
                        break;
                    }
                }
                if (isExist){
                    System.err.println("Product name Already Exists");
                }else {
                    return this.productName = productName;
                }
            }else {
                System.err.println("Product name must have form 6 to 50 chars,please try again");
            }
        }while (true);
    }

    public float inputPrice(Scanner scanner){
        System.out.println("Enter price of product");
        do {
            try {
                float priceProduct = Float.parseFloat(scanner.nextLine());
                if (priceProduct>0){
                    return this.price = priceProduct;
                }else {
                    System.err.println("price must hove value is number>0");
                }
            }catch (NumberFormatException e){
                System.err.println("price must hove value is number >0");
            }
        }while (true);
    }

    public String inputDescription(Scanner scanner){
        System.out.println("Enter description of product");
        do {
            String description = scanner.nextLine();
            if (description.isEmpty()){
                System.err.println("Description of product cannot empty");
            }

        }while (true);
    }

    public Date inputCreated (Scanner scanner) throws ParseException {
        System.out.println("Enter created date");
        do {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String date = scanner.nextLine();
                return this.created = formatter.parse(date);
            }catch (DateTimeParseException e){
                System.err.println("created Date format is incorrect");
            }
        }while (true);
    }

    public int inputCatalogIdOfProduct(Scanner scanner,List<Categories> categoryList){
        do {
            try {
                for (int i=0;i<categoryList.size();i++){
                    System.out.println((i+1)+"."+categoryList.get(i).getCatalogName());

                }
                System.out.println("Enter catalog name");
                int choice = Integer.parseInt(scanner.next());
                if (choice >0 && choice<=categoryList.size()){
                    return categoryList.get(choice-1).getCatalogId();
                }else {
                    System.err.println("catalog name not exist,please try again");
                }

            }catch (NumberFormatException e){
                System.err.println("catalogId Of Product format is incorrect");
            };

        }while (true);

    }

    public int inputProductStatus(Scanner scanner){
        System.out.println("Enter status of product");
        do {
            try {
                System.out.println("1.Đang bán");
                System.out.println("2. Hết hàng ");
                System.out.println("3.Không bán");
                System.out.println("your choice");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice>0 && choice<4){
                    return (choice -1);
                }else {
                    System.err.println("Status of product not exist, please try again");
                }
            }catch (NumberFormatException e){
                System.err.println("status Of Product format is incorrect");
            }
        }while (true);
    }

    @Override
    public void displayProduct() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                "Product Id","Product Name","Price","Description","Date","Category","Product Status");
        System.out.printf("%-20s %-20s %-20f %-20s %-20s %-20s %-20s\n",
                this.productId,this.productName,
                this.price,this.description,
                formatter.format(this.created),this.catalogId,
                (this.productStatus==0)?"Đang bán":(this.productStatus==1)?"Hết hàng":"Không bán");
    }
}
