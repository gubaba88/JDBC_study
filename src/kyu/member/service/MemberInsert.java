package kyu.member.service;

import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.control.Action;
import kyu.project.dao.MemberDAO;
import kyu.project.dto.MemberDTO;

public class MemberInsert implements Action {
	// 로그 출력을 위해 템플릿으로 작성
	private static final Log log = LogFactory.getLog(MemberInsert.class);

	@Override
	public void execute(Scanner scanner) {
		System.out.println();
		System.out.println("===회원 가입 페이지===");
		MemberDAO memberDAO = new MemberDAO();
		System.out.print("사용할 아이디를 입력해 주세요 : ");
		String memId = scanner.next();
		MemberDTO memberDTO = memberDAO.memberIdCheck(memId);
		if (memberDTO.getMemId() == null) {
			System.out.print("사용할 비밀번호를 입력해 주세요 : ");
			String memPasswd = scanner.next();
			System.out.print("사용할 이메일을 입력해주세요 : ");
			String memEmail = scanner.next();
			memberDTO.setMemId(memId);
			memberDTO.setMemPasswd(memPasswd);
			memberDTO.setmemEmail(memEmail);
			memberDTO.setMemPoint(50);
			memberDAO.memberInsert(memberDTO);
			System.out.println("가입이 완료되었습니다.");
			log.info("회원 등록 MemberDTO - " + memberDTO);
		} else {
			System.out.println("중복된 아이디 입니다. 이전 화면으로 돌아갑니다.");
			log.info("중복 아이디 확인 - " + memberDTO.getMemId().equals(memId));
		}

	}
}
