package com.jsp.warehouse_management_system.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehouse_management_system.entity.Client;
import com.jsp.warehouse_management_system.requestdto.ClientRequest;
import com.jsp.warehouse_management_system.responsedto.ApiKeyResponse;
import com.jsp.warehouse_management_system.responsedto.ClientResponse;

@Component
public class ClientMapper {

	public Client mapToClient( ClientRequest clientRequest, Client client) {
		client.setBusinessName(clientRequest.getBusinessName());
		client.setEmail(clientRequest.getEmail());
		client.setContactNumber(clientRequest.getContactNumber());

		return client;
	}

	public ApiKeyResponse mapToApiKeyResponse(Client client) {
		return ApiKeyResponse.builder()
				.apiKey(client.getApiKey())
				.build();
				
	}
	
	public ClientResponse mapClientResponse(Client client) {
		return ClientResponse.builder()
				.clientId(client.getClientId())
				.businessName(client.getBusinessName())
				.email(client.getEmail())
				.contactNumber(client.getContactNumber())
				.build();
	}
	
}
