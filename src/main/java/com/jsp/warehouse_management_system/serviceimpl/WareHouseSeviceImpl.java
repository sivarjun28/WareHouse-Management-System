package com.jsp.warehouse_management_system.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_management_system.entity.WareHouse;
import com.jsp.warehouse_management_system.enums.AdminType;
import com.jsp.warehouse_management_system.enums.Privilege;
import com.jsp.warehouse_management_system.exception.WareHouseNotFoundByIdException;
import com.jsp.warehouse_management_system.mapper.WareHouseMapper;
import com.jsp.warehouse_management_system.repository.WareHouseRespository;
import com.jsp.warehouse_management_system.requestdto.WareHouseRequest;
import com.jsp.warehouse_management_system.responsedto.AdminResponse;
import com.jsp.warehouse_management_system.responsedto.WareHouseResponse;
import com.jsp.warehouse_management_system.service.WareHouseService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

@Service
public class WareHouseSeviceImpl implements WareHouseService{


	@Autowired
	private WareHouseMapper wareHouseMapper;
	@Autowired
	private WareHouseRespository wareHouseRespository;
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest) {
		WareHouse wareHouse =wareHouseRespository.save(wareHouseMapper.mapToWareHouse(wareHouseRequest, new WareHouse()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<WareHouseResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("WareHouse Created")
				.setData(wareHouseMapper.mapToWareHouseResponse(wareHouse)));
	}
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(int wareHouseId,WareHouseRequest wareHouseRequest) {


		return wareHouseRespository.findById(wareHouseId).map(existingWareHouse ->{
			wareHouseMapper.mapToWareHouse(wareHouseRequest, existingWareHouse);
			existingWareHouse = wareHouseRespository.save(existingWareHouse);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<WareHouseResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("WareHouse Updated")
							.setData(wareHouseMapper.mapToWareHouseResponse(existingWareHouse)));
		}).orElseThrow(()-> new WareHouseNotFoundByIdException("wareHouse Not fouind"));


	}
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWareHouse(int wareHouseId) {
		return wareHouseRespository.findById(wareHouseId).map(wareHouse ->{

			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<WareHouseResponse>().
							setStatus(HttpStatus.FOUND.value())
							.setMessage("WareHouse found")
							.setData(wareHouseMapper.mapToWareHouseResponse(wareHouse)));
		}).orElseThrow(()-> new WareHouseNotFoundByIdException("WareHouse Not Found"));

	}
	@Override
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWareHouses() {
	  
	    List<WareHouseResponse> wareHouseResponses = wareHouseRespository.findAll()
	            .stream()
	            .map(wareHouse -> wareHouseMapper.mapToWareHouseResponse(wareHouse))
	            .toList();

	    
	    return ResponseEntity.status(HttpStatus.FOUND)
	            .body(new ResponseStructure<List<WareHouseResponse>>()
	                    .setStatus(HttpStatus.FOUND.value())
	                    .setMessage("WareHouse Found")
	                    .setData(wareHouseResponses));
	}


} 
