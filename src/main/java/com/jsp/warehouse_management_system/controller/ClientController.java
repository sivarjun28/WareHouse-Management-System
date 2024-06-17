package com.jsp.warehouse_management_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_management_system.requestdto.ClientRequest;
import com.jsp.warehouse_management_system.responsedto.ApiKeyResponse;
import com.jsp.warehouse_management_system.responsedto.ClientResponse;
import com.jsp.warehouse_management_system.service.ClientService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class ClientController {
	
@Autowired
private ClientService clientService;

@PostMapping("/client/register")
public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@RequestBody ClientRequest clientRequest) {
    
    
    return clientService.registerClient(clientRequest);
}


@PutMapping("/client/{clientId}/clients")
public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(@PathVariable int clientId, 
		@RequestBody ClientRequest clientRequest) {
    
    
    return clientService.updateClient(clientId,clientRequest);
}

}
