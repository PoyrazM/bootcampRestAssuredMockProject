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
public class BananaScenarioTestNG {

    Response response;

    // Post işlemi Banana için
    @Description("POST request for the Banana")
    @Test(priority = 1)
    public void validateToPostBanana() {
        Grocery requestGrocery = new Grocery();
        requestGrocery.setId(IGroceryProducts.BANANA_ID);
        requestGrocery.setName(IGroceryProducts.BANANA);
        requestGrocery.setPrice(IGroceryProducts.BANANA_PRICE);
        requestGrocery.setStock(IGroceryProducts.BANANA_STOCK);

        this.response = GroceryApi.post(requestGrocery);

        assertThat(this.response.getBody().path("msg"),equalTo("successfully added the product"));
        assertThat(this.response.getStatusCode() , equalTo(201));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 201 Created"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
        assertThat(this.response.getHeader("Server") , equalTo("Google Frontend"));
    }

    // Get işlemi tüm ürünlerin arasından Banana'yı validate etmek için
    @Description("GET request for the Banana in all products")
    @Test(priority = 2)
    public void validateToGetBananaInAllGrocery(){

        this.response = GroceryApi.getAll();

        assertThat(this.response.getBody().path("data[2].id"), equalTo(IGroceryProducts.BANANA_ID));
        assertThat(this.response.getBody().path("data[2].name"), equalTo(IGroceryProducts.BANANA));
        assertThat(this.response.getBody().path("data[2].price") , (equalTo(IGroceryProducts.BANANA_PRICE)));
        assertThat(this.response.getBody().path("data[2].stock") , equalTo(IGroceryProducts.BANANA_STOCK));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
    }

    //Get işlemi sadece banana endpointine atılan istek için
    @Description("GET request for the Banana in Banana's endpoint")
    @Test(priority = 3)
    public void validaToGetJustBananaEndpoint(){
        this.response = GroceryApi.get(GroceryPath.epBANANA);
        assertThat(this.response.getBody().path("id"), equalTo(IGroceryProducts.BANANA_ID));
        assertThat(this.response.getBody().path("name"), equalTo(IGroceryProducts.BANANA));
        assertThat(this.response.getBody().path("price") , (equalTo(IGroceryProducts.BANANA_PRICE)));
        assertThat(this.response.getBody().path("stock") , equalTo(IGroceryProducts.BANANA_STOCK));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
    }
}
