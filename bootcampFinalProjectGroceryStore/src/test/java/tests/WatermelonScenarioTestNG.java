package tests;

import builder.GroceryApi;
import datas.GroceryPath;
import datas.IGroceryProducts;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import model.Grocery;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WatermelonScenarioTestNG {
    Response response;

    // Post işlemi Watermelon için
    @Description("POST request for the Watermelon")
    @Test(priority = 1)
    public void validateToPostMango() {
        Grocery requestGrocery = new Grocery();
        requestGrocery.setId(IGroceryProducts.WATERMELON_ID);
        requestGrocery.setName(IGroceryProducts.WATERMELON);
        requestGrocery.setPrice(IGroceryProducts.WATERMELON_PRICE);
        requestGrocery.setStock(IGroceryProducts.WATERMELON_STOCK);

        this.response = GroceryApi.post(requestGrocery);

        assertThat(this.response.getBody().path("msg"),equalTo("successfully added the product"));
        assertThat(this.response.getStatusCode() , equalTo(201));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 201 Created"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
        assertThat(this.response.getHeader("Server") , equalTo("Google Frontend"));
    }

    // Get işlemi tüm ürünlerin arasından Watermelon'u validate etmek için
    @Description("GET request for the Watermelon in all products")
    @Test(priority = 2)
    public void validateToGetWatermelonInAllGrocery(){

        this.response = GroceryApi.getAll();

        assertThat(this.response.getBody().path("data[3].id"), equalTo(IGroceryProducts.WATERMELON_ID));
        assertThat(this.response.getBody().path("data[3].name"), equalTo(IGroceryProducts.WATERMELON));
        assertThat(this.response.getBody().path("data[3].price") , (equalTo(IGroceryProducts.WATERMELON_PRICE)));
        assertThat(this.response.getBody().path("data[3].stock") , equalTo(IGroceryProducts.WATERMELON_STOCK));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
    }

    //Get işlemi sadece Watermelon endpointine atılan istek için
    @Description("GET request for the Watermelon in Watermelon's endpoint")
    @Test(priority = 3)
    public void validaToGetJustWatermelonEndpoint(){
        this.response = GroceryApi.get(GroceryPath.epWATERMELON);
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getBody().path("id"), equalTo(IGroceryProducts.WATERMELON_ID));
        assertThat(this.response.getBody().path("name"), equalTo(IGroceryProducts.WATERMELON));
        assertThat(this.response.getBody().path("price") , (equalTo(IGroceryProducts.WATERMELON_PRICE)));
        assertThat(this.response.getBody().path("stock") , equalTo(IGroceryProducts.WATERMELON_STOCK));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
    }
}
