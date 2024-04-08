package generators;

import courier.Courier;
import courier.CourierWithoutLogin;
import courier.CourierWithoutName;
import courier.CourierWithoutPassword;
import courier.courierForAuth.CourierForAuth;
import courier.courierForAuth.CourierWithoutLoginForAuth;
import courier.courierForAuth.CourierWithoutPasswordForAuth;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class CourierGenerator {
    private final String password = "12345";
    private final String firstName = "Name";

    public Courier getCourier(){
        return Courier.builder()
                .login(randomAlphanumeric(10))
                .password(password)
                .firstName(firstName)
                .build();
    }

    public Courier getCourierWithLoginValueNull(){
        return Courier.builder()
                .password(password)
                .build();
    }

    public Courier getCourierWithPasswordValueNull(){
        return Courier.builder()
                .login(randomAlphanumeric(10))
                .build();
    }

    public CourierWithoutLogin getCourierWithoutLogin(){
        return new CourierWithoutLogin(password);
    }

    public CourierWithoutPassword getCourierWithoutPassword(){
        return new CourierWithoutPassword(randomAlphanumeric(10));
    }

    public CourierWithoutName getCourierWithoutName(){
        return new CourierWithoutName(randomAlphanumeric(10), password);
    }

    public CourierForAuth getCourierForAuth(Courier courier) {
        return CourierForAuth.builder()
                .login(courier.getLogin())
                .password(courier.getPassword())
                .build();
    }

    public CourierForAuth getCourierWithLoginValueNullForAuth(Courier courier) {
        return CourierForAuth.builder()
                .password(courier.getPassword())
                .build();
    }

    public CourierForAuth getCourierWithPasswordValueNullForAuth(Courier courier) {
        return CourierForAuth.builder()
                .login(courier.getLogin())
                .build();
    }

    public CourierWithoutLoginForAuth getCourierWithoutLoginForAuth(Courier courier) {
        return new CourierWithoutLoginForAuth(courier);
    }

    public CourierWithoutPasswordForAuth getCourierWithoutPasswordForAuth(Courier courier) {
        return new CourierWithoutPasswordForAuth(courier);
    }
}
