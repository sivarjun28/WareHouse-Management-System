package com.jsp.warehouse_management_system.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_management_system.requestdto.ClientRequest;
import com.jsp.warehouse_management_system.responsedto.ApiKeyResponse;
import com.jsp.warehouse_management_system.responsedto.ClientResponse;
import com.jsp.warehouse_management_system.util.ResponseStructure;

public interface ClientService {

	ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(ClientRequest clientRequest);

	ResponseEntity<ResponseStructure<ClientResponse>> updateClient(int clientId, ClientRequest clientRequest);

}
