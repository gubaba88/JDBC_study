package kyu.member.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import kyu.project.control.Action;
import kyu.project.dao.MemberDAO;
import kyu.project.dto.MemberDTO;

public class MemberList implements Action {
	@Override
	public void execute(Scanner scanner) throws IOException {
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		MemberDAO memberDAO = new MemberDAO();
		arrayList = memberDAO.memberList();
		System.out.println();
		System.out.println("==================회원 목록==================");
		for (MemberDTO memberDTO : arrayList) {
			int memNumber = memberDTO.getMemNumber();
			String memId = memberDTO.getMemId();
			String memEmail = memberDTO.getmemEmail();
			System.out.println("회원 번호 : " + memNumber + ", 회원 아이디 : " + memId + ", 회원 이메일 : " + memEmail);
		}
		System.out.println("================================================");
		System.out.println("1.회원 상세 정보    2.회원 추가 정보 입력     3.회원 정보 수정     4.회원 삭제");
		System.out.print("번호 선택 : ");
		String choice = scanner.next();
		switch (choice) {
		case "1":
			MemberSelect memberSelect = new MemberSelect();
			memberSelect.execute(scanner);
			break;
		case "2":
			MemDetailInsert memDetailInsert = new MemDetailInsert();
			memDetailInsert.execute(scanner);
			break;
		case "3":
			MemberUpdate memberUpdate = new MemberUpdate();
			memberUpdate.execute(scanner);
			break;
		case "4":
			MemberDelete memberDelete = new MemberDelete();
			memberDelete.execute(scanner);
			break;
		default:
			System.out.println("잘못된 입력으로 이전 화면으로 돌아갑니다.");
			break;
		}
	}
}
