package zilverline;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private UserConfig userConfig;

    public Controller(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    @GetMapping("/conf")
    public UserConfig endPoint() {
        return userConfig;
    }
}
