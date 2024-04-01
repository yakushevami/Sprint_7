package courier.courierForAuth;

import courier.Courier;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class CourierWithoutPasswordForAuth {
    private String login;

    public CourierWithoutPasswordForAuth(Courier courier){
        this.login = courier.getLogin();
    }
}