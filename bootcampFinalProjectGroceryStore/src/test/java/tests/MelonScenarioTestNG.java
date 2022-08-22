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

public class MelonScenarioTestNG {

    Response response;

    // Post işlemi Melon için
    @Description("POST request for the Melon")
    @Test(priority = 1)
    public void validateToPostMelon() {
        Grocery requestGrocery = new Grocery();
        requestGrocery.setId(IGroceryProducts.MELON_ID);
        requestGrocery.setName(IGroceryProducts.MELON);
        requestGrocery.setPrice(IGroceryProducts.MELON_PRICE);
        requestGrocery.setStock(IGroceryProducts.MELON_STOCK);

        this.response = GroceryApi.post(requestGrocery);

        assertThat(this.response.getBody().path("msg"),equalTo("successfully added the product"));
        assertThat(this.response.getStatusCode() , equalTo(201));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 201 Created"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
        assertThat(this.response.getHeader("Server") , equalTo("Google Frontend"));
    }

    // Get işlemi tüm ürünlerin arasından Melon'u validate etmek için
    @Description("GET request for the Melon in all products")
    @Test(priority = 2)
    public void validateToGetMelonInAllGrocery(){

        this.response = GroceryApi.getAll();

        assertThat(this.response.getBody().path("data[4].id"), equalTo(IGroceryProducts.MELON_ID));
        assertThat(this.response.getBody().path("data[4].name"), equalTo(IGroceryProducts.MELON));
        assertThat(this.response.getBody().path("data[4].price") , (equalTo(IGroceryProducts.MELON_PRICE)));
        assertThat(this.response.getBody().path("data[4].stock") , equalTo(IGroceryProducts.MELON_STOCK));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
    }

    //Get işlemi sadece Melon endpointine atılan istek için
    @Description("GET request for the Melon in Melon's endpoint")
    @Test(priority = 3)
    public void validaToGetJustMelonEndpoint(){
        this.response = GroceryApi.get(GroceryPath.epMELON);
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getBody().path("id"), equalTo(IGroceryProducts.MELON_ID));
        assertThat(this.response.getBody().path("name"), equalTo(IGroceryProducts.MELON));
        assertThat(this.response.getBody().path("price") , (equalTo(IGroceryProducts.MELON_PRICE)));
        assertThat(this.response.getBody().path("stock") , equalTo(IGroceryProducts.MELON_STOCK));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
    }
}

