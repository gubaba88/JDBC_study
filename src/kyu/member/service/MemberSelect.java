package kyu.member.service;

import java.util.Scanner;

import kyu.project.control.Action;
import kyu.project.dao.MemDetailDAO;
import kyu.project.dao.MemberDAO;
import kyu.project.dto.MemDetailDTO;
import kyu.project.dto.MemberDTO;

public class MemberSelect implements Action {

	@Override
	public void execute(Scanner scanner) {
		System.out.println();
		System.out.println("===회원 상세 페이지===");
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		MemDetailDAO memDetailDAO = new MemDetailDAO();
		MemDetailDTO memDetailDTO = new MemDetailDTO();
		String memId;
		System.out.println("검색할 아이디를 입력해 주세요.");
		System.out.print("아이디 : ");
		memId = scanner.next();
		memberDTO = memberDAO.memberSelect(memId);
		memDetailDTO = memDetailDAO.memDetailSelect(memId);
		if (memberDTO.getMemId() == null) {
			System.out.println("등록되지 않은 아이디입니다.");
		} else {
			System.out.print("회원 번호 : " + memberDTO.getMemNumber() + "  ,");
			System.out.print("회원 아이디 : " + memberDTO.getMemId() + "  ,");
			System.out.println("회원 포인트 : " + memberDTO.getMemPoint());
			System.out.print("회원 이메일 : " + memberDTO.getmemEmail() + "  ,");
			System.out.println("회원 가입일 : " + memberDTO.getJoinDate());
			System.out.println("-----회원 추가 정보-----");
			if (memDetailDTO.getMemName() != null) {
				System.out.println("회원 별명 : " + memDetailDTO.getMemName());
			}
			if (memDetailDTO.getMemBirth() != null) {
				System.out.println("회원 생년월일 : " + memDetailDTO.getMemBirth());
			}
			if (memDetailDTO.getMemGender() == 1 || memDetailDTO.getMemGender() == 3) {
				System.out.println("회원 성별 : 남성");
			}
			if (memDetailDTO.getMemGender() == 2 || memDetailDTO.getMemGender() == 4) {
				System.out.println("회원 성별 : 여성");
			}
			if (memDetailDTO.getMemPhone() != null) {
				System.out.println("회원 전화번호 : " + memDetailDTO.getMemPhone());
			}
			if (memDetailDTO.getMemAddress() != null) {
				System.out.println("회원 주소 : " + memDetailDTO.getMemAddress());
			}
			if (memDetailDTO.getMemComment() != null) {
				System.out.println("회원 소개 : " + memDetailDTO.getMemComment());
			}
		}
	}
}
