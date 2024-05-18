package com.proyecto.tutorlink.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirebaseTokenFilter extends OncePerRequestFilter {
/*
// Dentro de doFilterInternal después de verificar el token
FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
String uid = decodedToken.getUid();

// Supongamos que tienes un claim que especifica el rol del usuario
String role = decodedToken.getClaims().get("role").toString();  // Asumiendo que el claim se llama "role"

List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));

UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(uid, null, authorities);
SecurityContextHolder.getContext().setAuthentication(authentication);
 */

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String idToken = request.getHeader("Authorization");
        if (idToken != null && !idToken.isEmpty()) {
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                String uid = decodedToken.getUid();
                // Aquí puedes establecer la autenticación en el contexto de seguridad si es necesario
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
