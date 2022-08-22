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

public class GrapesScenarioTestNG {
    Response response;

    // Post işlemi Grapes için
    @Description("POST request for the Grapes")
    @Test(priority = 1)
    public void validateToPostGrapes() {
        Grocery requestGrocery = new Grocery();
        requestGrocery.setId(IGroceryProducts.GRAPES_ID);
        requestGrocery.setName(IGroceryProducts.GRAPES);
        requestGrocery.setPrice(IGroceryProducts.GRAPES_PRICE);
        requestGrocery.setStock(IGroceryProducts.GRAPES_STOCK);

        this.response = GroceryApi.post(requestGrocery);

        assertThat(this.response.getBody().path("msg"),equalTo("successfully added the product"));
        assertThat(this.response.getStatusCode() , equalTo(201));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 201 Created"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
        assertThat(this.response.getHeader("Server") , equalTo("Google Frontend"));
    }

    // Get işlemi tüm ürünlerin arasından Grapes'i validate etmek için
    @Description("GET request for the Grapes in all products")
    @Test(priority = 2)
    public void validateToGetGrapesInAllGrocery(){

        this.response = GroceryApi.getAll();

        assertThat(this.response.getBody().path("data[1].id"), equalTo(IGroceryProducts.GRAPES_ID));
        assertThat(this.response.getBody().path("data[1].name"), equalTo(IGroceryProducts.GRAPES));
        assertThat(this.response.getBody().path("data[1].price") , (equalTo(IGroceryProducts.GRAPES_PRICE)));
        assertThat(this.response.getBody().path("data[1].stock") , equalTo(IGroceryProducts.GRAPES_STOCK));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json; charset=UTF-8"));
    }

    //Get işlemi sadece grapes endpointine atılan istek için
    @Description("GET request for the Grapes in Grapes' endpoint")
    @Test(priority = 3)
    public void validaToGetJustGrapesEndpoint(){
        this.response = GroceryApi.get(GroceryPath.epGRAPES);
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getBody().path("id"), equalTo(IGroceryProducts.GRAPES_ID));
        assertThat(this.response.getBody().path("name"), equalTo(IGroceryProducts.GRAPES));
        assertThat(this.response.getBody().path("price") , (equalTo(IGroceryProducts.GRAPES_PRICE)));
        assertThat(this.response.getBody().path("stock") , equalTo(IGroceryProducts.GRAPES_STOCK));
    }
}
