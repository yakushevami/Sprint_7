package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import order.Order;

public class ClientOrder extends Client{
    private static final String CANCEL = "/cancel";
    private static final String ORDERS = "/orders";

    @Step("Create an order")
    public Response createOrder(Order order){
        return setUp()
                .body(order)
                .post(ORDERS);
    }

    @Step("Cancel an order")
    public Response cancelOrder(Integer orderId){
        return setUp()
                .queryParam("track", orderId)
                .put(ORDERS + CANCEL);
    }

    @Step("Get orders list")
    public Response getOrdersList(){
        return setUp()
                .get(ORDERS);
    }
}