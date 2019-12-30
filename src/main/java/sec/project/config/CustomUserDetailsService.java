package sec.project.config;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.domain.Signup;
import sec.project.repository.AccountRepository;
import sec.project.repository.SignupRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Account account = new Account();
        account.setUsername("roger");
        account.setPassword(passwordEncoder.encode("carrots"));
       Signup signup = new Signup("Roger Rabbits","92 Park St",
                "Espanola","P5E 1E9", "Canada");
       signupRepository.save(signup);
       account.setSignup(signup);
        accountRepository.save(account);

        account = new Account();
        account.setUsername("test");
        account.setPassword(passwordEncoder.encode("1234"));
        signup = new Signup("Matti Meikäläinen","Teststreet 123",
                "TestCity","0000", "Finland");
        signupRepository.save(signup);
        account.setSignup(signup);
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
