package builder;

import datas.GroceryPath;
import io.restassured.response.Response;
import model.Grocery;

public class GroceryApi {

    // Atacağım requstleri response da hazırlamıştım , burada da endpointlerim için pathlarımı koydum ve , body hazırlamam gereken post ve update requesti için tanımlamamı yaptım.

    public static Response getAll(){
        return ResponseClass.getAll(GroceryPath.ALL_GROCERY);
    }

    public static Response post(Grocery requestGrocery){
        return ResponseClass.post(GroceryPath.ALL_GROCERY + GroceryPath.ADD , requestGrocery);
    }

    public static Response get(String productNameEndpoint) {
        return ResponseClass.get(GroceryPath.ALL_GROCERY + productNameEndpoint);
    }

    public static Response update(String productName , Grocery requestGrocery){
        return ResponseClass.update("/" + productName , requestGrocery);
    }

}
