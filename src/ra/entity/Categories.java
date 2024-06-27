package ra.entity;

import java.util.List;
import java.util.Scanner;

public class Categories implements ICategories {
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;


    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    @Override
    public void inputData(Scanner scanner, List<Categories> categoriesList) {
//  1. catalogId  mã danh mục, tự tăng – Khi tạo danh mục mới mã danh mục lấy mã danh mục lớn nhất + 1
        inputCatalogId(categoriesList);
//  2. catalogName tên danh mục, có độ dài tối đa 50 ký tự, không trùng lặp
        inputCatalogName(scanner,categoriesList);
//  3. descriptions mô tả danh mục
        inputDescriptionCatalog(scanner);
//  4. catalogStatus chỉ nhận khi nhập 1 trong 2 giá trị true hoặc false (true – hoạt động, false – không hoạt động)
        inputStatusCatalog(scanner);
    }
    public void updateData(Scanner scanner, List<Categories> categoriesList) {
//  2. catalogName tên danh mục, có độ dài tối đa 50 ký tự, không trùng lặp
        inputCatalogName(scanner,categoriesList);
//  3. descriptions mô tả danh mục
        inputDescriptionCatalog(scanner);
//  4. catalogStatus chỉ nhận khi nhập 1 trong 2 giá trị true hoặc false (true – hoạt động, false – không hoạt động)
        inputStatusCatalog(scanner);
    }


    public int inputCatalogId(List<Categories> categoriesList) {

      if (categoriesList.isEmpty()){
          return 1;
      }else {
          for (int i = 0; i < categoriesList.size(); i++) {
             int indexMax = categoriesList.get(0).getCatalogId();
             if (indexMax > categoriesList.get(i).getCatalogId()) {
                 indexMax = categoriesList.get(i).getCatalogId();
                 break;
             }
          }
         return indexMax + 1;
      }
    }

    public String inputCatalogName(Scanner scanner,List<Categories> categoriesList){
        System.out.println("Enter Catalog Name");
        do {
            String catalogName = scanner.nextLine();
            if (catalogName.length()<=50){
                boolean isExist = false;
                for (Categories c : categoriesList) {
                    if (c.getCatalogName().equals(catalogName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist){
                    System.err.println("Catalog Name Already Exists");
                }else {
                    return this.catalogName = catalogName;
                }
            }else {
                System.err.println("Catalog Name must be less than 50");
            }

        }while (true);
    }

    public String inputDescriptionCatalog(Scanner scanner){
        System.out.println("Enter Description of catalog");
        do {
            String descriptions = scanner.nextLine();
            if (descriptions.isEmpty()){
                System.err.println("Description cannot is Empty");
            }else {
                return this.descriptions = descriptions;
            }
        }while (true);
    }

    public boolean inputStatusCatalog(Scanner scanner){
        System.out.println("Enter Status of catalog");
        do {
            String status = scanner.nextLine();
            if (status.equals("true")|| status.equals("false")){
                return this.catalogStatus = Boolean.parseBoolean(status);
            }else {
                System.err.println("Status of Catalog only 2 value true || false, please try again");
            }
        }while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("%-20s %-20s %-20s %-20s\n"
                ,"Catalog Id","Catalog Name","Catalog Description","Catalog Status");
        System.out.printf("%-20s %-20s %-20s %-20s\n"
                ,this.catalogId,this.catalogName
                ,this.descriptions
                ,this.catalogStatus?"Active":"Inactive");
    }
}
