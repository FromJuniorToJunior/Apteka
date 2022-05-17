/*
package A1.Apteka.Apteka.Security;

import A1.Apteka.Apteka.Model.User;
import A1.Apteka.Apteka.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpSession;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    HttpSession session;


    public CustomUserDetailsService() {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUserEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("Użytkownik nie istnieje");
        }
        session.setAttribute("user", user);
        return new CustomUserDetails(user);
    }
}
*/
