
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import client.ClientCourier;
import client.ClientOrder;
import courier.Courier;
import courier.courierForAuth.CourierForAuth;
import generators.CourierGenerator;

import static org.hamcrest.CoreMatchers.equalTo;

public class ClearingMethods {
    private final ClientCourier courierClient = new ClientCourier();
    private final ClientOrder orderClient = new ClientOrder();
    private final CourierGenerator generator = new CourierGenerator();

    public int getCourierId(Courier courier) {
        CourierForAuth courierForAuth = generator.getCourierForAuth(courier);
        Response response = courierClient.authorization(courierForAuth);
        int courierId = response.body().path("id");
        return courierId;
    }

    public void deleteCourier(Courier courier) {
        int courierId = getCourierId(courier);
        Response response = courierClient.deleteCourier(courierId);
        response.then().assertThat().body("ok", equalTo(true)).and().statusCode(HttpStatus.SC_OK);
    }

    public void cancelOrder(Integer id) {
        Response response = orderClient.cancelOrder(id);
        response.then().assertThat().body("ok", equalTo(true)).and().statusCode(HttpStatus.SC_OK);
    }
}