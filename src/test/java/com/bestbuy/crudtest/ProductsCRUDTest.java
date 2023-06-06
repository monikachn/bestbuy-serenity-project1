package com.bestbuy.crudtest;

import com.bestbuy.bestbuyinfo.ProductSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {
    static String name = "Duracell - AAA Batteries (10-Pack)";
    static String type = "HardGood";
    static double price = 15.99;
    static int shipping = 0;
    static String upc = "041333424007";
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 10-pack";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4A";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-10-pack/43900.p?id=1051384074145%26skuId=43900%26cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("This will create a new product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productId = response.extract().path("id");
    }

    @Title("Verify if the product was created to the application")
    @Test
    public void test002() {
        productSteps.getproductInfoById(productId);
    }


    @Title("update the product information and verify the updated product")
    @Test
    public void test003() {
        name = name + "abc";
        productSteps.updateProduct(productId).log().all().statusCode(200);

    }

    @Title("Delete the product and verify if the product is deleted")
    @Test
    public void test004() {
        productSteps.deleteProduct(productId)
                .statusCode(200);

        productSteps.getProduct(productId)
                .statusCode(404);
    }
}