import client.ClientOrder;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersListTest {
    ClientOrder clientOrder = new ClientOrder();

    @Test
    @DisplayName("Get orders list")
    public void getOrdersListTest(){
        Response response = clientOrder.getOrdersList();
        response.then().statusCode(200).assertThat().body("orders", notNullValue());
    }
}
