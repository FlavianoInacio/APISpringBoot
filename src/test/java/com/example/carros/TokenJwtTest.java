package com.example.carros;

import com.example.carros.security.jwt.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class)
public class TokenJwtTest {

    @Autowired
    @Qualifier("UserDetailsService")
    private UserDetailsService userDetailsService;

    @Test
    public void testToken(){
        // busca usuário do banco
        UserDetails user = userDetailsService.loadUserByUsername("admin");
        assertNotNull(user);

        // cria o token
        String jwtToken = JwtUtil.createToken(user);
        System.out.println(jwtToken);
        assertNotNull(jwtToken);

        // valida o token
        boolean ok = JwtUtil.isTokenValid(jwtToken);
        assertTrue(ok);

        // valida login
        String login= JwtUtil.getLogin(jwtToken);
        assertEquals("admin",login);

        // valida Roles
        List<GrantedAuthority> roles = JwtUtil.getRoles(jwtToken);
        assertNotNull(roles);
        System.out.println(roles);
        String role = roles.get(0).getAuthority();
        assertEquals("ROLE_ADMIN", role);


    }
}
