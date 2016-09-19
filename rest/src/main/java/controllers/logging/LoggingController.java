package controllers.logging;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mzajda on 18/09/2016.
 */
@RestController
@RequestMapping("/api/auth")
public class LoggingController {

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public Authentication getAuthenticationInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping(value = "/isAuthenticated", method = RequestMethod.GET)
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }
}
