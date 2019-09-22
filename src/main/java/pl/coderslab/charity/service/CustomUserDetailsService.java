package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(s);

        CustomUserDetails customUserDetails = null;

        if(user != null){

            customUserDetails = new CustomUserDetails();
            customUserDetails.setUser(user);

        }else{
            throw new UsernameNotFoundException("User " + s + " not exist");
        }

        return null;
    }
}
