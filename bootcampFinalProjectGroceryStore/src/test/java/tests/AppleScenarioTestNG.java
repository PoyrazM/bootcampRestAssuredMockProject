package tests;

import builder.GroceryApi;
import datas.GroceryPath;
import datas.IGroceryProducts;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import model.Grocery;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class AppleScenarioTestNG {

    Response response;

    // Post işlemi Apple için
    @Description("POST request for the Apple")
    @Test(priority = 1)
    public void validateToPostApple() {
        log.info("Apple is successfully added to database");
        Grocery requestGrocery = new Grocery();
        requestGrocery.setId(IGroceryProducts.APPLE_ID);
        requestGrocery.setName(IGroceryProducts.APPLE);
        requestGrocery.setPrice(IGroceryProducts.APPLE_PRICE);
        requestGrocery.setStock(IGroceryProducts.APPLE_STOCK);

        this.response = GroceryApi.post(requestGrocery);

        assertThat(this.response.getBody().path("msg"),equalTo("successfully added the product"));
        assertThat(this.response.getStatusCode() , equalTo(201));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 201 Created"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
        assertThat(this.response.getHeader("Server") , equalTo("Google Frontend"));
    }

    // Get işlemi tüm ürünlerin arasından Apple'ı validate etmek için
    @Description("GET request for the Apple in all products")
    @Test(priority = 2)
    public void validateToGetAppleInAllGrocery(){
        log.info("Apple datas are getting from allGrocery");

        this.response = GroceryApi.getAll();

        assertThat(this.response.getBody().path("data[0].id"), equalTo(IGroceryProducts.APPLE_ID));
        assertThat(this.response.getBody().path("data[0].name"), equalTo(IGroceryProducts.APPLE));
        assertThat(this.response.getBody().path("data[0].price") , (equalTo(IGroceryProducts.APPLE_PRICE)));
        assertThat(this.response.getBody().path("data[0].stock") , equalTo(IGroceryProducts.APPLE_STOCK));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
    }

    //Get işlemi sadece apple endpointine atılan istek için
    @Description("GET request for the Apple in Apple's endpoint")
    @Test(priority = 3)
    public void validaToGetJustAppleEndpoint(){
        log.info("Apple datas are getting from Apple Endpoint");

        this.response = GroceryApi.get(GroceryPath.epAPPLE);

        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getBody().path("id"), equalTo(IGroceryProducts.APPLE_ID));
        assertThat(this.response.getBody().path("name"), equalTo(IGroceryProducts.APPLE));
        assertThat(this.response.getBody().path("price") , (equalTo(IGroceryProducts.APPLE_PRICE)));
        assertThat(this.response.getBody().path("stock") , equalTo(IGroceryProducts.APPLE_STOCK));

    }

    // Bu 404 testini sadece burada yazdım yanlış bir endpointe atılan request sonucunda bana döndürdüğü mesaj ve status kod doğrulandı
    @Description("GET request for 404 status code")
    @Test(priority = 4)
    public void validateToWrongPostRequest(){
        log.info("This is wrong request");

        this.response = GroceryApi.get(GroceryPath.epWrong);

        assertThat(this.response.getStatusCode() , equalTo(404));
        assertThat(this.response.path("msg") , equalTo("Endpoint is not found"));
    }
}
