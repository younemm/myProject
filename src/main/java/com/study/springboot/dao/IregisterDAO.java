package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.registerDTO;

@Mapper
public interface IregisterDAO {  // (다른 패키지에 있는 거라)
	public List<registerDTO> listDao(); // 회원목록 select 
	public registerDTO viewDao(int rno); // 회원정보 상세보기(rno)로 불러오고
	public int writeDao(registerDTO dto); // 회원가입
	public int updateDao(registerDTO dto); // 회원정보 수정
	public int deleteDao(int rno); // 회원탈퇴 (int rno)로 지우니까
	public boolean checkPasswordDao(int rno,String rpw); 
	// 비밀번호 체크 (true/false 그래서 boolean 사용)
}
