package com.arky.cotizaciones.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arky.cotizaciones.config.exception.DuplicateResourceException;
import com.arky.cotizaciones.config.exception.ResourceNotFoundException;
import com.arky.cotizaciones.dto.CustomerRequestDTO;
import com.arky.cotizaciones.dto.CustomerResponseDTO;
import com.arky.cotizaciones.mapper.CustomerMapper;
import com.arky.cotizaciones.model.Customer;
import com.arky.cotizaciones.repository.CustomerRepository;
import com.arky.cotizaciones.service.CustomerService;





@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerMapper mapper;



    // Listar clientes
	@Transactional(readOnly = true)
	@Override
	public List<CustomerResponseDTO> getAll() {

		return customerRepository.findAll().stream()
		.map(mapper::toDto).toList();
	}


	// Obtener un cliente
	@Transactional(readOnly = true)
	@Override
	public CustomerResponseDTO getByCustomer(int customerId) {
		Customer customer = customerRepository.findById(customerId)
		.orElseThrow(() -> new ResourceNotFoundException("No se encontro el cliente"));

		return mapper.toDto(customer);
	}



	// Guardar 
	@Transactional
	@Override
	public CustomerResponseDTO saveCustomer(CustomerRequestDTO dto) {
		Customer customer = mapper.toEntity(dto);
		if(customerRepository.existsByEmail(dto.getEmail())){
			throw new DuplicateResourceException("Ya hay un cliente con ese correo");
		}

		return mapper.toDto(customerRepository.save(customer));
	}



	// Actualizar cliente
	@Transactional
	@Override
	public CustomerResponseDTO updateCustomer(int customerId, CustomerRequestDTO dto) {
		Customer customer = customerRepository.findById(customerId)
		.orElseThrow(() -> new ResourceNotFoundException("No se pudo actulizar"));
		mapper.updateDtoFromCustomer(dto, customer);

		return mapper.toDto(customerRepository.save(customer));
	}




	// Eliminar cliente
	@Transactional
	@Override
	public void deleteCustomer(int customerId) {
	    Customer customer = customerRepository.findById(customerId)
		.orElseThrow(() -> new ResourceNotFoundException("No se pudo eliminar"));

		customerRepository.delete(customer);
	}



	// Buscar cliente
	@Transactional(readOnly = true)
	@Override
	public List<CustomerResponseDTO> searchCustomer(String name) {
		  return customerRepository.findByNameIgnoreCaseContaining(name).stream()
		  .map(mapper::toDto).toList();
	}




	// Reporte en Excel
	@Override
    public ByteArrayInputStream exportCustomersToExcel() {
        List<CustomerResponseDTO> customers = getAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Customers");
            Row headerRow = sheet.createRow(0);


            String[] columns = {"ID", "Nombre", "Email", "Teléfono", "Dirección", "RUT", "Rubro"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }


            int rowIdx = 1;
            for (CustomerResponseDTO customer : customers) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(customer.getCustomerId());
                row.createCell(1).setCellValue(customer.getName());
                row.createCell(2).setCellValue(customer.getEmail());
                row.createCell(3).setCellValue(customer.getPhone());
                row.createCell(4).setCellValue(customer.getAddress());
                row.createCell(5).setCellValue(customer.getRut());
                row.createCell(6).setCellValue(customer.getRubro());
            }


            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Error al generar el archivo Excel", e);
        }
    }


}
