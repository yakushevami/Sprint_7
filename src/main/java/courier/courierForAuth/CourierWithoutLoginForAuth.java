package courier.courierForAuth;

import courier.Courier;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class CourierWithoutLoginForAuth {
    private String password;

    public CourierWithoutLoginForAuth(Courier courier){
        this.password = courier.getPassword();
    }
}