package client;

import courier.Courier;
import courier.CourierWithoutLogin;
import courier.CourierWithoutName;
import courier.CourierWithoutPassword;
import courier.courierForAuth.CourierForAuth;
import courier.courierForAuth.CourierWithoutLoginForAuth;
import courier.courierForAuth.CourierWithoutPasswordForAuth;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ClientCourier extends Client{
    private static final String COURIER = "/courier";
    private static final String LOGIN = "/login";

    @Step("Courier creation")
    public Response create(Courier courier){
        return setUp()
                .body(courier)
                .post(COURIER);
    }

    @Step("Courier creation without login value")
    public Response create(CourierWithoutLogin courierWithoutLogin){
        return setUp()
                .body(courierWithoutLogin)
                .post(COURIER);
    }

    @Step("Courier creation without password value")
    public Response create(CourierWithoutPassword courierWithoutPassword){
        return setUp()
                .body(courierWithoutPassword)
                .post(COURIER);
    }

    @Step("Courier creation without name value")
    public Response create(CourierWithoutName courierWithoutName){
        return setUp()
                .body(courierWithoutName)
                .post(COURIER);
    }

    @Step("Courier authorization")
    public Response authorization(CourierForAuth courierForAuth){
        return setUp()
                .body(courierForAuth)
                .post(COURIER + LOGIN);
    }

    @Step("Courier authorization without login value")
    public Response authorizationWithoutLogin(CourierWithoutLoginForAuth courierWithoutLoginForAuth){
        return setUp()
                .body(courierWithoutLoginForAuth)
                .post(COURIER + LOGIN);
    }

    @Step("Courier authorization without password value")
    public Response authorizationWithoutPassword(CourierWithoutPasswordForAuth courierWithoutPasswordForAuth){
        return setUp()
                .body(courierWithoutPasswordForAuth)
                .post(COURIER + LOGIN);
    }

    @Step("Delete courier")
    public Response deleteCourier(Integer courierId){
        return setUp()
                .delete(COURIER + String.format("/%d", courierId));
    }

    @Step("Delete courier without id")
    public Response deleteCourierWithoutId(){
        return setUp()
                .delete(COURIER);
    }
}
