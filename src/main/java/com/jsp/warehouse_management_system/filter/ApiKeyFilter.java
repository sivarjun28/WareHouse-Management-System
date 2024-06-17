package com.jsp.warehouse_management_system.filter;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jsp.warehouse_management_system.entity.Client;
import com.jsp.warehouse_management_system.exception.IllegalOperationException;
import com.jsp.warehouse_management_system.exception.UsernameNotFoundException;
import com.jsp.warehouse_management_system.repository.ClientRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter{

	
	private ClientRepository clientRepository;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getSession(false) != null) {
			throw new IllegalOperationException("illegal operation ");
		}
		
		if(!request.getRequestURI().equals("/api/v1/client/register")) {
		
		String username   = request.getHeader("USERNAME");
		String apiKey   = request.getHeader("API-KEY");
		
		if(username != null && apiKey != null) {
			
			Client client=clientRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));	
			
			
			if(!apiKey.equals(client.getApiKey()))
				throw new BadCredentialsException("Invalid credentials");
	}
		else { 
			throw new UsernameNotFoundException("User Not Found");
		}
		}
		filterChain.doFilter(request, response);
		
		
}
}
