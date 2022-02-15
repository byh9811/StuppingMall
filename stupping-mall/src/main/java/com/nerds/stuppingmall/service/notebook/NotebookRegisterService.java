package com.nerds.stuppingmall.service.notebook;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Category;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.NotebookAddRequestDto;
import com.nerds.stuppingmall.enumerate.Usage;
import com.nerds.stuppingmall.repository.CategoryRepository;
import com.nerds.stuppingmall.repository.MemberRepository;
import com.nerds.stuppingmall.repository.NotebookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotebookRegisterService {
	final NotebookRepository notebookRepository;
	final MemberRepository memberRepository;
	final CategoryRepository categoryRepository;
	
	public String addNotebook(String supplierId, NotebookAddRequestDto notebookAddRequestDto) {
		String url = saveImgFile(supplierId, notebookAddRequestDto);
		addCategories(supplierId, notebookAddRequestDto);
		Notebook notebook = Notebook.builder()
								.name(notebookAddRequestDto.getName())
								.supplierId(supplierId)
								.manufactureDate(notebookAddRequestDto.getManufactureDate())
								.img(url)
								.price(notebookAddRequestDto.getPrice())
								.view(0)
								.rate(0.0)
								.salesVolume(0)
								.cpuName(notebookAddRequestDto.getCpuName())
								.gpuName(notebookAddRequestDto.getGpuName())
								.weight(notebookAddRequestDto.getWeight())
								.screenSize(notebookAddRequestDto.getScreenSize())
								.ramSize(notebookAddRequestDto.getRamSize())
								.ssdSize(notebookAddRequestDto.getSsdSize())
								.hddSize(notebookAddRequestDto.getHddSize())
								.batterySize(notebookAddRequestDto.getBatterySize())
								.usage(Usage.DOCUMENT.getValue())
								.build();
								
		return notebookRepository.save(notebook).get_id();
	}

	private void addCategories(String supplierId, NotebookAddRequestDto notebookAddRequestDto) {
		addSupplierNameCategory(memberRepository.findById(supplierId).get().getName());
		addCpuNameCategory(notebookAddRequestDto.getCpuName());
		addGpuNameCategory(notebookAddRequestDto.getGpuName());
		addManuYearCategory(notebookAddRequestDto.getManufactureDate());
	}

	private void addSupplierNameCategory(String name) {
		Category cate = categoryRepository.findById("SupplierName").get();
		List<String> supplierNames = cate.getList();
		if(supplierNames.contains(name))
			return;
		supplierNames.add(name);
		cate.setList(supplierNames);
		categoryRepository.save(cate);
	}

	private void addCpuNameCategory(String name) {
		Category cate = categoryRepository.findById("CpuName").get();
		List<String> cpuNames = cate.getList();
		if(cpuNames.contains(name))
			return;
		cpuNames.add(name);
		cate.setList(cpuNames);
		categoryRepository.save(cate);
	}

	private void addGpuNameCategory(String name) {
		Category cate = categoryRepository.findById("GpuName").get();
		List<String> gpuNames = cate.getList();
		if(gpuNames.contains(name))
			return;
		gpuNames.add(name);
		cate.setList(gpuNames);
		categoryRepository.save(cate);
	}

	private void addManuYearCategory(String year) {
		Category cate = categoryRepository.findById("ManuYear").get();
		List<String> manuYears = cate.getList();
		if(manuYears.contains(year.substring(0, 4)))
			return;
		manuYears.add(year);
		cate.setList(manuYears);
		categoryRepository.save(cate);
	}

	private String saveImgFile(String supplierId, NotebookAddRequestDto notebookAddRequestDto) {
		String basePath = "C:\\img";
		String filePath = basePath + "/" + supplierId;
		String url = null;
		File folder = new File(filePath);
		
		if(!folder.exists())
			folder.mkdirs();
		
		if(!notebookAddRequestDto.getImgFile().isEmpty()) {
            // jpeg, png, gif 파일들만 받아서 처리
            String contentType = notebookAddRequestDto.getImgFile().getContentType();
            String originalFileExtension;
            
            // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
            if (!contentType.isEmpty()) {
                if(contentType.contains("image/jpeg")){
                    originalFileExtension = ".jpg";
                }
                else if(contentType.contains("image/png")){
                    originalFileExtension = ".png";
                }
                else if(contentType.contains("image/gif")){
                    originalFileExtension = ".gif";
                }
                // 다른 파일 명이면 에러 발생
                else {
                	throw new IllegalStateException("허용된 형식이 아닙니다!!");
                }
                // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                String fileName = notebookAddRequestDto.getName() + originalFileExtension;

                // 저장된 파일로 변경하여 이를 보여주기 위함
                url = filePath + "/" + fileName;
                File file = new File(url);
                try {
                	notebookAddRequestDto.getImgFile().transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
		}
		return url;
	}
}