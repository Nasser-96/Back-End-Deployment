package com.example.MyInterests.User;

import com.example.MyInterests.Role.Role;
import com.example.MyInterests.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("User NOT Found in the DataBase");
        }
        Collection <SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(String id){
        Long user_id = Long.parseLong(id);
        return userRepository.findById(user_id).orElse(null);
    }

    public ResponseEntity<?> createUser(SignUpForm signUpForm){
        User user = signUpForm.getUser();
        Long role_id = signUpForm.getRole_id();
        Role role = roleRepository.findById(role_id).orElse(null);

        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userRepository.findByEmail(user.getEmail()) == null){

            if (userRepository.findByUserName(user.getUserName()) == null){
                 userRepository.save(user);
                 return ResponseEntity.ok().body(userRepository.findByEmail(user.getEmail()));
            }
            else{
                return ResponseEntity.status(404).body("User Name already exist");
            }

        }
        else {
            return ResponseEntity.status(404).body("Email already exist");
        }

    }


    public void updateUser(String id, User data){
        Long user_id = Long.parseLong(id);
        User user = userRepository.findById(user_id).orElse(null);

        if (user !=null){
            user.setUserName(data.getUserName());
            user.setEmail(data.getEmail());
            user.setMoreInfo(data.getMoreInfo());
            user.setPersonalImg(data.getPersonalImg());
            userRepository.save(user);
        }
    }

    public void deleteUser(String id) {
        Long user_id = Long.parseLong(id);
        userRepository.deleteById(user_id);
    }
}
