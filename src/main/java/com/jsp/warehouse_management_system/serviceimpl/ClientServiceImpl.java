package com.jsp.warehouse_management_system.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_management_system.entity.Client;
import com.jsp.warehouse_management_system.exception.ClientNotFoundByIdException;
import com.jsp.warehouse_management_system.mapper.ClientMapper;
import com.jsp.warehouse_management_system.repository.ClientRepository;
import com.jsp.warehouse_management_system.requestdto.ClientRequest;
import com.jsp.warehouse_management_system.responsedto.ApiKeyResponse;
import com.jsp.warehouse_management_system.responsedto.ClientResponse;
import com.jsp.warehouse_management_system.service.ClientService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository  clientRepository;

	@Autowired
	private ClientMapper clientMapper;

	@Override
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(ClientRequest clientRequest) {

		String apiKey = UUID.randomUUID().toString();


		Client client =  clientRepository.save(clientMapper.mapToClient(clientRequest, new Client()));
		client.setApiKey(apiKey);
		clientRepository.save(client);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<ApiKeyResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Api Key Created")
						.setData(clientMapper.mapToApiKeyResponse(client)));

	}

	@Override
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(int clientId, ClientRequest clientRequest) {
		
		
		return clientRepository.findById(clientId).map(existingClient -> {
			existingClient = clientRepository.save(clientMapper.mapToClient(clientRequest, existingClient));
			
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<ClientResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Client Updated")
							.setData(clientMapper.mapClientResponse(existingClient)));
			
		}).orElseThrow(() -> new ClientNotFoundByIdException("client Not found "));
	}

}
