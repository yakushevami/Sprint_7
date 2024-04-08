package courier;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Courier {
    private String login;
    private String password;
    private String firstName;
}