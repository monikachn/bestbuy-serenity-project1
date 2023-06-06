package com.bestbuy.bestbuyinfo;
import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
public class StoreSteps {
    @Step("Create Products with Name:{0},type:{1},address:{2},address2:{3},city:{4},state:{5},zip:{6},hours:{7},lat:{8},lng:{9}")
    public ValidatableResponse createProducts(String name, String type, String address, String address2, String city, String state, String zip, String hours, double lat, double lng) {

        StorePojo storePojo = StorePojo.getSTorePojo(name, type, address, address2, city, state, zip, hours, lat, lng);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(storePojo)
                .post()
                .then();
    }
    @Step("Getting the store information with StoreId : {0}")
    public ValidatableResponse getStoreInfoByStoresId(int storeId) {
        return SerenityRest.given().log().all()
                .when()
                .pathParam("id", storeId)
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then().log().all().statusCode(200);

    }

    @Step("Updating store with city : {0}")
    public ValidatableResponse updateStore(int storeId) {
        StorePojo storePojo =new StorePojo();
        storePojo.setCity("Surat");
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("id", storeId)
                .when()
                .body(storePojo)
                .patch(EndPoints.UPDATE_STORES_BY_ID)
                .then();
    }

    @Step("Deleting store information with storeId: {0}")
    public ValidatableResponse deleteStore(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("id", storeId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then();
    }
    @Step("Getting store information with storeId : {0}")
    public ValidatableResponse getStoreById(int storeId){
        return  SerenityRest.given().log().all()
                .pathParam("id", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then();

    }
}