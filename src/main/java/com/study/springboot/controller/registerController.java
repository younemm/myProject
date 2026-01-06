package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dao.IregisterDAO;
import com.study.springboot.dto.registerDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class registerController {
	@Autowired// 의존성 자동주입 IregisterDAO를 (일명,객체자동생성)
	IregisterDAO dao; // 객체를 만들었으니까 요기 있는 메소드 다 사용가능
	
	@RequestMapping("/") // 처음 들어갈 때, localhost:8080/
	public String root() {
		return "redirect:list"; // WEB-INF/views/list.jsp, 첫화면
	}
	
	@RequestMapping("/writeForm") // 매핑주소, 이 주소로 접속하면
	public String writeForm() {
		return "writeForm"; // writeForm.jsp, 이 파일이 실행된다
	}
	
	@RequestMapping("/write") // writeForm의 action, 지나가는 맵
	public String write(HttpServletRequest request, registerDTO dto) { // registerDTO호출 
		// 연락처 합쳐서 저장 dto에 저장
		String rtel1 = request.getParameter("rtel1");
		String rtel2 = request.getParameter("rtel2");
		String rtel3 = request.getParameter("rtel3");
		dto.setRtel(rtel1+"-"+rtel2+"-"+rtel3);
		
		// 취미를 배열 처리해서 저장
		String[] hobbies = request.getParameterValues("rhobby");
		String chk="";
		
		if(hobbies != null) { // 체크했다면 실행
			for(int i=0; i<hobbies.length; i++) {
				chk = chk + hobbies[i] + " ";
			}
			dto.setRhobby(chk);
		}else {
			dto.setRhobby("");
		}
		
		dao.writeDao(dto); // dao에 가면 있는 writeDao (insert실행)
		return "redirect:list"; // WEB-INF/views/list.jsp, 강제이동
	}
	
	@RequestMapping("/list") // DAO에 List<registerDTO> 대한거
	public String list(Model model) {
		model.addAttribute("list", dao.listDao());
		return "list";
	}
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, Model model) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		model.addAttribute("detail", dao.viewDao(rno));
		return "detail";
	}
	// 비밀번호 확임폼 (수정/탈퇴 공용)
	@RequestMapping("/passwordCheckForm")
	public String passwordCheckForm(HttpServletRequest request, Model model) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		String mode = request.getParameter("mode"); // update, delete
		
		model.addAttribute("rno", rno);
		model.addAttribute("mode", mode);
		
		return "passwordCheckForm";
	}
	// 비밀번호 확인 처리 (히든처리)
	@RequestMapping("/passwordCheck")
	public String passwordCheck(HttpServletRequest request, Model model) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		String mode = request.getParameter("mode"); // update, delete
		String rpw = request.getParameter("rpw");
		
		if(dao.checkPasswordDao(rno, rpw)) { //비밀번호가 일치한다면 
			if(mode.equals("update")) { // update라면
				model.addAttribute("edit", dao.viewDao(rno));
				return "updateForm";
			}
			else if(mode.equals("delete")) { // delete라면
				dao.deleteDao(rno);
				return "redirect:list";
			}
		}
		model.addAttribute("msg", "비밀번호가 틀렸습니다.");
		model.addAttribute("rno", rno);
		model.addAttribute("mode", mode);
		// 비밀번호가 틀렸으니까 이 페이지로 돌아간다
		return "passwordCheckForm";
	}
	@RequestMapping("/update")
	public String update(HttpServletRequest request, registerDTO dto) { // registerDTO호출 
		// 연락처 합쳐서 저장 dto에 저장
		String rtel1 = request.getParameter("rtel1");
		String rtel2 = request.getParameter("rtel2");
		String rtel3 = request.getParameter("rtel3");
		dto.setRtel(rtel1+"-"+rtel2+"-"+rtel3);
		
		// 취미를 배열 처리해서 저장
		String[] hobbies = request.getParameterValues("rhobby");
		String chk="";
		
		if(hobbies != null) { // 체크했다면 실행
			for(int i=0; i<hobbies.length; i++) {
				chk = chk + hobbies[i] + " ";
			}
			dto.setRhobby(chk);
		}else {
			dto.setRhobby("");
		}
		dao.updateDao(dto); // update실행
		return "redirect:list"; // WEB-INF/views/list.jsp, 강제이동
	}
}
