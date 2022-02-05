package com.alkemy.disney.auth.service;

import com.alkemy.disney.auth.dto.UserDto;
import com.alkemy.disney.auth.entity.UserEntity;
import com.alkemy.disney.auth.repository.UserRepository;
import com.alkemy.disney.servicio.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
/*Extiende la clase por defecto de Security
por eso se sobre escriben los metodos
 */
@Service
public class UserDetailsCustomService implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private EmailService emailService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException("Usuario o contraseña incorrecta");
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    /*En caso de que no se haya en contrado un usuario, se crea uno,
    la collecion es para los permisos que no vamos a utilizar
     */
    }

    public boolean save(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity =this.userRepository.save(userEntity);
        if(userEntity != null){
            emailService.SendWelcomeEmailTo(userEntity.getUsername());

        }
        return userEntity != null;
    }
}
