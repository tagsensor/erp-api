package com.carleonis.erpapi.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UtilSecurity {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public String getEmpresa() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("empresa");
	}
	
	public String getNome() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("nome");
	}
	
	public String getPerfil() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("perfil") + "";
	}

}
