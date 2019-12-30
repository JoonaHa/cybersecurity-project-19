package sec.project.controller;

import org.hibernate.jpa.criteria.path.SingularAttributePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Signup;
import sec.project.repository.AccountRepository;
import sec.project.repository.SignupRepository;

@Controller
public class DefaultController {

    @Autowired
    private SignupRepository signupRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/signups";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm(Authentication authectication) {
        if (authectication == null || !authectication.isAuthenticated()) {
            return "form";
        }

        return "redirect:/*";

    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLogin() {
        return "login";
    }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, @RequestParam String city,
                             @RequestParam String zipCode, @RequestParam String country ,
                             @RequestParam String username, @RequestParam String password) {
        Signup singup = new Signup(name, address, city, zipCode, country);
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        singup = signupRepository.save(singup);
        account.setSignup(singup);
        accountRepository.save(account);
        return "redirect:/login";
    }

}
