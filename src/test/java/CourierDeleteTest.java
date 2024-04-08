import client.ClientCourier;
import courier.Courier;
import generators.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierDeleteTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final ClientCourier clientCourier = new ClientCourier();

    @Test
    @DisplayName("Delete courier with correct courier id")
    public void deleteCourierWithCorrectIdTest(){
        Courier courier = generator.getCourier();
        clientCourier.create(courier);
        Integer courierId = clientCourier.authorization(generator.getCourierForAuth(courier)).body().path("id");
        Response response = clientCourier.deleteCourier(courierId);
        response.then().assertThat().statusCode(200).body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Delete courier with incorrect courier id")
    public void deleteCourierWithIncorrectIdTest(){
        int courierId = -100;
        Response response = clientCourier.deleteCourier(courierId);
        response.then().assertThat().statusCode(404).body("message", equalTo("Курьера с таким id нет."));
    }

    @Test
    @DisplayName("Delete courier without id")
    public void deleteCourierWithoutIdTest(){
        Response response = clientCourier.deleteCourierWithoutId();
        response.then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для удаления курьера"));
    }
}
