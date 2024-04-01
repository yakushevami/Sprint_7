import client.ClientCourier;
import courier.Courier;
import courier.CourierWithoutLogin;
import courier.CourierWithoutPassword;
import courier.CourierWithoutName;
import generators.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreationTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final ClientCourier clientCourier = new ClientCourier();

    private Courier courier;
    private final ClearingMethods clearingMethods = new ClearingMethods();

    @After
    public void deleteCourier(){
        if (courier != null) {
            clearingMethods.deleteCourier(courier);
        }
    }

    @Test
    @DisplayName("Creating courier with correct login, password and name")
    public void createCourierTest(){
        Courier courier = generator.getCourier();
        Response response = clientCourier.create(courier);
        response.then().assertThat().statusCode(201).body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Creating courier with login that is already existing")
    public void createCourierWithAlreadyExistingLoginTest(){
        Courier courier = generator.getCourier();
        clientCourier.create(courier);
        Response responseWithConflict = clientCourier.create(courier);
        responseWithConflict.then().assertThat().statusCode(409).body("message",equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Create courier with login value null")
    public void createCourierWithLoginValueNullTest(){
        Courier courier = generator.getCourierWithLoginValueNull();
        Response response = clientCourier.create(courier);
        response.then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Create courier with password value null")
    public void createCourierWithPasswordValueNullTest(){
        Courier courier = generator.getCourierWithPasswordValueNull();
        Response response = clientCourier.create(courier);
        response.then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Create courier with empty login value")
    public void createCourierWithEmptyLoginValueTest(){
        CourierWithoutLogin courierWithoutLogin = generator.getCourierWithoutLogin();
        Response response = clientCourier.create(courierWithoutLogin);
        response.then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Create courier with empty password value")
    public void createCourierWithEmptyPasswordValueTest(){
        CourierWithoutPassword courierWithoutPassword = generator.getCourierWithoutPassword();
        Response response = clientCourier.create(courierWithoutPassword);
        response.then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Create courier with empty name value")
    public void createCourierWithEmptyNameValueTest(){
        CourierWithoutName courierWithoutName = generator.getCourierWithoutName();
        Response response = clientCourier.create(courierWithoutName);
        response.then().statusCode(201).and().body("ok", equalTo(true));
    }
}