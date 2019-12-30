package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sec.project.domain.Account;
import sec.project.domain.Signup;
import sec.project.repository.AccountRepository;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SignupRepository signupRepository;



    @RequestMapping(value = "/signup/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Signup signupByAccountId(@PathVariable Long id) {

        Account account = accountRepository.findOne(id);
        Signup signup = account.getSignup();

        return signup;
}


    @RequestMapping(value = "/signups", method = RequestMethod.GET)
    public String signupByAcoount(Authentication authentication, Model model) {

        Account account = accountRepository.findByUsername(authentication.getName());
        model.addAttribute("signup", accountRepository.findByUsername(authentication.getName()).getSignup());
        model.addAttribute("account", account);
        return "signups";
    }



}
