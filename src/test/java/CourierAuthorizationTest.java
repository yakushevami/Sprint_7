import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import client.ClientCourier;
import courier.Courier;
import courier.courierForAuth.CourierForAuth;
import courier.courierForAuth.CourierWithoutLoginForAuth;
import courier.courierForAuth.CourierWithoutPasswordForAuth;
import generators.CourierGenerator;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.allOf;

public class CourierAuthorizationTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final ClientCourier clientCourier = new ClientCourier();
    private CourierForAuth courierForAuth;
    private Courier courier;
    private final ClearingMethods clearingMethods = new ClearingMethods();

    @After
    public void deleteCourier(){
        if (courier != null) {
            clearingMethods.deleteCourier(courier);
        }
    }

    @Test
    @DisplayName("Correct courier authorization")
    public void courierCorrectAuthorizationTest(){
        Courier courier = generator.getCourier();
        clientCourier.create(courier);
        courierForAuth = generator.getCourierForAuth(courier);
        Response response = clientCourier.authorization(courierForAuth);
        response.then().statusCode(200).assertThat().body("id", allOf(notNullValue(), greaterThan(0)));
    }

    @Test
    @DisplayName("Courier authorization without login")
    public void courierAuthorizationWithoutLoginTest(){
        Courier courier = generator.getCourier();
        CourierWithoutLoginForAuth courierWithoutLoginForAuth = generator.getCourierWithoutLoginForAuth(courier);
        Response response = clientCourier.authorizationWithoutLogin(courierWithoutLoginForAuth);
        response.then().statusCode(400).body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Courier authorization without password")
    public void courierAuthorizationWithoutPasswordTest(){
        Courier courier = generator.getCourier();
        CourierWithoutPasswordForAuth courierWithoutPasswordForAuth = generator.getCourierWithoutPasswordForAuth(courier);
        Response response = clientCourier.authorizationWithoutPassword(courierWithoutPasswordForAuth);
        response.then().statusCode(400).body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Courier authorization with null login value")
    public void courierAuthorizationWithNullLoginValueTest(){
        Courier courier = generator.getCourier();
        CourierForAuth courierWithLoginNullForAuth = generator.getCourierWithLoginValueNullForAuth(courier);
        Response response = clientCourier.authorization(courierWithLoginNullForAuth);
        response.then().statusCode(400).body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Courier authorization with null password value")
    public void courierAuthorizationWithNullPasswordValueTest(){
        Courier courier = generator.getCourier();
        CourierForAuth courierWithPasswordNullForAuth = generator.getCourierWithPasswordValueNullForAuth(courier);
        Response response = clientCourier.authorization(courierWithPasswordNullForAuth);
        response.then().statusCode(400).body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Courier authorization with incorrect values of login and password")
    public void courierAuthorizationWithLoginAndPasswordIncorrectValuesTest(){
        Courier invalideCourier = generator.getCourier();
        CourierForAuth invalideCourierForAuth = generator.getCourierForAuth(invalideCourier);
        Response response = clientCourier.authorization(invalideCourierForAuth);
        response.then().statusCode(404).body("message", equalTo("Учетная запись не найдена"));
    }
}
