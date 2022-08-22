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

public class MangoScenarioTestNG {

    Response response;

    // Post işlemi Mango için
    @Description("POST request for the Mango")
    @Test(priority = 1)
    public void validateToPostMango() {
        Grocery requestGrocery = new Grocery();
        requestGrocery.setId(IGroceryProducts.MANGO_ID);
        requestGrocery.setName(IGroceryProducts.MANGO);
        requestGrocery.setPrice(IGroceryProducts.MANGO_PRICE);
        requestGrocery.setStock(IGroceryProducts.MANGO_STOCK);

        this.response = GroceryApi.post(requestGrocery);

        assertThat(this.response.getBody().path("msg"),equalTo("successfully added the product"));
        assertThat(this.response.getStatusCode() , equalTo(201));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 201 Created"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
        assertThat(this.response.getHeader("Server") , equalTo("Google Frontend"));
    }

    // Get işlemi tüm ürünlerin arasından Mango'yu validate etmek için
    @Description("GET request for the Mango in all products")
    @Test(priority = 2)
    public void validateToGetMangoInAllGrocery(){

        this.response = GroceryApi.getAll();

        assertThat(this.response.getBody().path("data[5].id"), equalTo(IGroceryProducts.MANGO_ID));
        assertThat(this.response.getBody().path("data[5].name"), equalTo(IGroceryProducts.MANGO));
        assertThat(this.response.getBody().path("data[5].price") , (equalTo(IGroceryProducts.MANGO_PRICE)));
        assertThat(this.response.getBody().path("data[5].stock") , equalTo(IGroceryProducts.MANGO_STOCK));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
    }

    //Get işlemi sadece Mango endpointine atılan istek için
    @Description("GET request for the Mango in Mango's endpoint")
    @Test(priority = 3)
    public void validaToGetJustMangoEndpoint(){
        this.response = GroceryApi.get(GroceryPath.epMANGO);
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getBody().path("id"), equalTo(IGroceryProducts.MANGO_ID));
        assertThat(this.response.getBody().path("name"), equalTo(IGroceryProducts.MANGO));
        assertThat(this.response.getBody().path("price") , (equalTo(IGroceryProducts.MANGO_PRICE)));
        assertThat(this.response.getBody().path("stock") , equalTo(IGroceryProducts.MANGO_STOCK));
    }
}
