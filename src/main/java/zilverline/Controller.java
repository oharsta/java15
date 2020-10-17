package zilverline;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final UserConfig userConfig;

    private final TokenUsers tokenUsers;

    public Controller(UserConfig userConfig, TokenUsers tokenUsers) {
        this.userConfig = userConfig;
        this.tokenUsers = tokenUsers;
    }

    @GetMapping("/conf")
    public UserConfig endPoint() {
        return userConfig;
    }


    @GetMapping("/token")
    public TokenUsers tokenUsers() {
        return tokenUsers;
    }

    @GetMapping("/persons")
    public List<Person> persons() {
        return List.of(new Person("jdoe",18), new Person("mdoe",19));
    }
}
