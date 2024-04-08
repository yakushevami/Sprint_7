package generators;

import order.Order;

public class OrderGenerator {
    public Order getOrder(String firstName, String lastName, String address, String metroStation, String phoneNumber, Integer rentTime, String deliveryDate, String comment, String[] colours){
        return Order.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .metroStation(metroStation)
                .phoneNumber(phoneNumber)
                .rentTime(rentTime)
                .deliveryDate(deliveryDate)
                .comment(comment)
                .colours(colours)
                .build();
    }
}