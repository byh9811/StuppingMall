package com.nerds.stuppingmall.service.notebook;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.repository.member.MemberRepository;
import com.nerds.stuppingmall.repository.notebook.NotebookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotebookDetailsService {
	final NotebookRepository notebookRepository;
	
	public Notebook findNotebook(String id) {
		Optional<Notebook> notebookWrapper = notebookRepository.findById(id);

		if(!notebookWrapper.isPresent())
			throw new NoSuchElementException("해당 상품이 존재하지 않습니다!!");
		
		return notebookWrapper.get();
	}
}
