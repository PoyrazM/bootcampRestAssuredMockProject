package builder;

import static io.restassured.RestAssured.given;

// İsteklerimi burada hazırladım Build classımdan tanımlamalarımı da çağırarak isteklerimi oluşturdum

public class ResponseClass {
    public static io.restassured.response.Response post(String path ,Object requestGrocery){

        return given(Build.getRequestSpec()).
                body(requestGrocery).
                when().
                post(path).
                then().
                spec(Build.getResponseSpec()).
                extract().
                response();
    }

    public static io.restassured.response.Response get(String path){

        return given(Build.getRequestSpec()).
                when().
                get(path).
                then().
                spec(Build.getResponseSpec()).
                extract().
                response();
    }

    public static io.restassured.response.Response getAll(String path) {

        return given(Build.getRequestSpec()).
                when().
                get(path).
                then().
                spec(Build.getResponseSpec()).
                extract().
                response();
    }

    public static io.restassured.response.Response update(String path , Object requestGrocery){
        return given(Build.getRequestSpec()).
                body(requestGrocery).
                when().
                put(path).
                then().spec(Build.getResponseSpec()).
                extract().
                response();
    }
}
