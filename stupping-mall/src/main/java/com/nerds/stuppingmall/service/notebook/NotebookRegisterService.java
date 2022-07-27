package com.nerds.stuppingmall.service.notebook;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.nerds.stuppingmall.dto.NotebookDto;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nerds.stuppingmall.domain.Category;
import com.nerds.stuppingmall.repository.CategoryRepository;
import com.nerds.stuppingmall.repository.member.MemberRepository;
import com.nerds.stuppingmall.repository.NotebookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotebookRegisterService {
	final NotebookRepository notebookRepository;
	final MemberRepository memberRepository;
	final CategoryRepository categoryRepository;
	
	public String addNotebook(String supplierId, NotebookDto.Request notebook) {
		List<String> urls = saveImgFile(supplierId, notebook);
		addCategories(supplierId, notebook);
		int cpuScore = getCpuScore(notebook.getCpuName());
		int gpuScore = getGpuScore(notebook.getGpuName());
								
		return notebookRepository.save(notebook.toNotebook(supplierId, urls, cpuScore, gpuScore)).get_id();
	}

	private int getGpuScore(String gpuName) {
		final String passmarkUrl = "https://www.videocardbenchmark.net/gpu_list.php";
		Connection conn = Jsoup.connect(passmarkUrl);
		int score = -1;
		try {
			Document document = conn.get();
			Element element = document.getElementById("cputable");
			List<Node> nodes = element.child(1).childNodes();
			for(Node node: nodes) {
				if(node.childNode(0).childNode(0).toString().equals(gpuName))
					score = Integer.parseInt(node.childNode(1).toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return score;
	}

	private int getCpuScore(String cpuName) {
		final String passmarkUrl = "https://www.cpubenchmark.net/cpu.php?cpu=" + cpuName;
		Connection conn = Jsoup.connect(passmarkUrl);
		int score = -1;
		try {
			Document document = conn.get();
			Element element = document.getElementsByClass("right-desc").get(0);
			score = Integer.parseInt(element.child(2).text());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return score;
	}

	private void addCategories(String supplierId, NotebookDto.Request notebook) {
		addSupplierNameCategory(memberRepository.findById(supplierId).get().getName());
		addCpuNameCategory(notebook.getCpuName());
		addGpuNameCategory(notebook.getGpuName());
		addRegistYearCategory(notebook.getRegisterDate());
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

	private void addRegistYearCategory(String year) {
		Category cate = categoryRepository.findById("RegistYear").get();
		List<String> registYears = cate.getList();
		if(registYears.contains(year.substring(0, 4)))
			return;
		registYears.add(year);
		cate.setList(registYears);
		categoryRepository.save(cate);
	}

	private List<String> saveImgFile(String supplierId, NotebookDto.Request notebook) {
		String folderPath = supplierId + "/" + notebook.getName();
		File folder = new File(folderPath);
		List<String> urls = new ArrayList<>();
		
		if(!folder.exists())
			folder.mkdirs();
		
		if(!notebook.getImgFiles().isEmpty()) {
			for(MultipartFile imgFile: notebook.getImgFiles()) {
	            // jpeg, png, gif 파일들만 받아서 처리
	            String contentType = imgFile.getContentType();
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
	                String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSSSSS")) + originalFileExtension;

	                // 저장된 파일로 변경하여 이를 보여주기 위함
	                String url = folderPath + "/" + fileName;
	                File file = new File(url);
	                try {
	                	imgFile.transferTo(file);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	                urls.add(url);
	            }
			}
		}
		return urls;
	}
}