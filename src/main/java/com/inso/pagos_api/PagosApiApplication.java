package com.inso.pagos_api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import com.inso.pagos_api.model.Category;
import com.inso.pagos_api.model.Client;
import com.inso.pagos_api.model.Company;
import com.inso.pagos_api.service.ICategoryService;
import com.inso.pagos_api.service.IClientService;
import com.inso.pagos_api.service.ICompanyService;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class PagosApiApplication implements ApplicationRunner {

	private final ResourceLoader resourceLoader;
	private final ICategoryService categoryService;
	private final ICompanyService companyService;
	private final IClientService clientService;

	public static void main(String[] args) {
		SpringApplication.run(PagosApiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Resource resource1 = resourceLoader.getResource("classpath:json/companies.json");
		Resource resource2 = resourceLoader.getResource("classpath:json/categories.json");
		Resource resource3 = resourceLoader.getResource("classpath:json/clients.json");
		// Leer el contenido del archivo JSON
		byte[] jsonData1 = FileCopyUtils.copyToByteArray(resource1.getInputStream());
		byte[] jsonData2 = FileCopyUtils.copyToByteArray(resource2.getInputStream());
		byte[] jsonData3 = FileCopyUtils.copyToByteArray(resource3.getInputStream());

		String jsonString1 = new String(jsonData1, StandardCharsets.UTF_8);
		String jsonString2 = new String(jsonData2, StandardCharsets.UTF_8);
		String jsonString3 = new String(jsonData3, StandardCharsets.UTF_8);

		// Puedes usar una librería como Jackson para convertir el JSON a objetos Java
		ObjectMapper objectMapper = new ObjectMapper();
		List<Company> companies = objectMapper.readValue(jsonString1, new TypeReference<>(){});
		List<Category> categories = objectMapper.readValue(jsonString2, new TypeReference<>(){});
		List<Client> clients = objectMapper.readValue(jsonString3, new TypeReference<>(){});

		//companyService.saveAll(companies);
		categoryService.saveAll(categories);
		clientService.saveAll(clients);
	}
}
