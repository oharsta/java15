package zilverline;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class User {

    private String name;
    private String password;
    private List<Role> roles;

}
