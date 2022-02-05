package com.alkemy.disney.auth.filter;

import com.alkemy.disney.auth.repository.UserRepository;
import com.alkemy.disney.auth.service.JwtUtil;
import com.alkemy.disney.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
/* este fitro se va a aplicar cada vez que se realice la peticion*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /*Tomamos el head de msj y lo controlamos con los if*/
        final String authotizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authotizationHeader != null && authotizationHeader.startsWith("Bearer ")){
            /*Aca verificamos q el jwt comience con Bearer y quitams esos caracteres para podes utilizar el
            jwt real
             */
            jwt= authotizationHeader.substring(7);
            username= this.jwtUtil.extractUsername(jwt);
            /*Extraemos el nombre de usaurio del jwt*/
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails= this.userDetailsCustomService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)){
                /* validamos el jwt y con los datos recibidos el jwt y el usuario recibido*/
                UsernamePasswordAuthenticationToken authReq =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
                Authentication auth = this.authenticationManager.authenticate(authReq);


            }
        }
        filterChain.doFilter(request, response);

    }
}
