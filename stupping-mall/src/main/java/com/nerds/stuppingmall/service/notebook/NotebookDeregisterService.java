package com.nerds.stuppingmall.service.notebook;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.repository.NotebookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotebookDeregisterService {
	final NotebookRepository notebookRepository;
	public void removeNotebook(String supplierId, String notebookId) {
		Optional<Notebook> notebookWrapper = notebookRepository.findById(notebookId);
		if(!notebookWrapper.isPresent())
			throw new NoSuchElementException("존재하지 않는 상품입니다!!");
		if(!notebookWrapper.get().getSupplierId().equals(supplierId))
			throw new NoSuchElementException("잘못된 접근입니다!!");
		
		Notebook notebook = notebookWrapper.get();
		notebookRepository.deleteById(notebook.get_id());
		File folder = new File("C:\\img/" + notebook.getSupplierId() + "/" + notebook.getName());
		if(folder.exists()) {
			for(File file: folder.listFiles())
				file.delete();
			folder.delete();
		}
	}
}
