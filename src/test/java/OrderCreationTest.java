import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import client.ClientOrder;
import order.Order;
import generators.OrderGenerator;
import static org.hamcrest.CoreMatchers.notNullValue;
import static constants.ScooterColours.*;

@RunWith(Parameterized.class)
public class OrderCreationTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final Integer rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    private final ClientOrder orderClient = new ClientOrder();
    private final OrderGenerator generator = new OrderGenerator();
    private final ClearingMethods clearingMethods = new ClearingMethods();
    private Integer trackId;

    public OrderCreationTest(String firstName, String lastName, String address, String metroStation, String phoneNumber, Integer rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @After
    public void deleteOrder() {
        if (trackId != null && trackId > 0) {
            clearingMethods.cancelOrder(trackId);
        }
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"Имя", "Фамилия", "Адрес", "Выхино", "79900000001", 1, "2025-01-01", "black", new String[]{COLOUR_BLACK}},
                {"Имя", "Фамилия", "Адрес", "Выхино", "79900000002", 2, "2025-01-01", "grey", new String[]{COLOUR_GREY}},
                {"Имя", "Фамилия", "Адрес", "Выхино", "79900000003", 3, "2025-01-01", "both", new String[]{COLOUR_BLACK, COLOUR_GREY}},
                {"Имя", "Фамилия", "Адрес", "Выхино", "79900000004", 4, "2025-01-01", "not selected", new String[]{}},
        };
    }

    @Test
    @DisplayName("Create order")
    public void createOrder() {
        Order order = generator.getOrder(firstName, lastName, address, metroStation, phoneNumber, rentTime, deliveryDate, comment, color);
        Response response = orderClient.createOrder(order);
        trackId = response.body().path("track");
        response.then().statusCode(201).assertThat().body("track", notNullValue());
    }
}