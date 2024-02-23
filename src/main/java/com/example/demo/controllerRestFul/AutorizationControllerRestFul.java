package com.example.demo.controllerRestFul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtUtils;
import com.example.demo.service.to.UsuarioTo;

@RestController
@RequestMapping("/autorizaciones")
public class AutorizationControllerRestFul {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwt;

	@GetMapping(path = "/jwt", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String obtenerToken(@RequestBody UsuarioTo usuario) {
		// Autenticacion
		// Validar que el usuario y el password sean correctos

		// si la autenticacion es correcta, puedo retornar un token
		System.out.println(usuario.getNombre());
		this.autenticacion(usuario.getNombre(), usuario.getPassword());
		System.out.println(usuario.getNombre());
		return new JwtUtils().buildTokenJWT(usuario.getNombre());
	}


	private void autenticacion(String usuario, String password) {
		UsernamePasswordAuthenticationToken usuarioToken = new UsernamePasswordAuthenticationToken(usuario, password);
		Authentication authentication = this.authenticationManager.authenticate(usuarioToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
