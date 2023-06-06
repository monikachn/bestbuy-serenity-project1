package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {
    @Step("Creating Product with name : {0}, type {1},price{2},shipping {3},upc {4} ,description {5},manufacturer {6},model{7},url {8},image {9}")
    public ValidatableResponse createProduct(String name, String type, double price, int shipping, String upc, String description,
                                             String manufacturer, String model, String url, String image) {
        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(productPojo)
                .post()
                .then().log().all();
    }

@Step("Getting the product information with productId :{0}")
    public ValidatableResponse getproductInfoById(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("id",productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all()
                .statusCode(200);

    }
    @Step("Updating product information with name:{0}")
    public ValidatableResponse updateProduct(int productId) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("abcd");
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id",productId)
                .when()
                .body(productPojo)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Deleting product information with productId : {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("id", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Getting product information with productId : {0}")
    public ValidatableResponse getProduct(int productID){
        return  SerenityRest.given().log().all()
                .pathParam("id", productID)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all();

    }
}
