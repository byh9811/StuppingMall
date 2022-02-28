package com.nerds.stuppingmall.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nerds.stuppingmall.StuppingMallApplication;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StuppingMallApplication.class)
@DirtiesContext
@DataMongoTest
public class NotebookRepositoryTest {
	@Autowired
	NotebookRepository notebookRepository;
	
	final int SIZE_PER_PAGE = 10;
	
//	@Test
	public void saveNotebook() {
		for(int i=1; i<20; i++) {
			// given
			List<String> imgs = new ArrayList<>();
			imgs.add("C:\\img\\Samsung\\삼성전자 갤럭시북 프로360 NT950QDB-KC59G/삼성 갤럭시북 15.png");
			imgs.add("C:\\img\\Samsung\\삼성전자 갤럭시북 프로360 NT950QDB-KC59G/삼성 갤럭시북 17.png");
			
			Notebook notebook = Notebook.builder()
						.name("삼성전자 갤럭시북 프로360 NT950QDB-KC59G")
						.supplierId("Samsung")
						.supplierName("삼성")
						.registerDate(LocalDate.now().toString())
						.imgs(imgs)
						.price(959000)
						.view(0)
						.rate(0.0)
						.salesVolume(0)
						.cpuName("i5-1135G7")
						.gpuName("내장 그래픽")
						.weight(1.6)
						.screenSize(15)
						.cpuScore(1056)
						.gpuScore(20000)
						.ramSize(16)
						.ssdSize(512)
						.hddSize(1000)
						.batterySize(65.4)
						.usage("사무용")
						.build();
			
			// when
			Notebook savedNotebook = notebookRepository.save(notebook);
			
			// then
			assertEquals(notebook, savedNotebook);
		}
	}
	
	@Test
	public void readNotebook() {
		// given
		String supplierId = "Samsung";
		
		// when
		List<Notebook> myNotebooks = notebookRepository.findBySupplierId(supplierId);
		
		// then
		assertEquals(1, myNotebooks.size());
		assertEquals("삼성 갤럭시북 15", myNotebooks.get(0).getName());
	}
}
