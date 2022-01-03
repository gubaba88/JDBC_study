package kyu.member.service;

import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.control.Action;
import kyu.project.dao.MemberDAO;
import kyu.project.dto.MemberDTO;

public class Login implements Action {
	// 로그 출력을 위해 템플릿으로 작성
	private static final Log log = LogFactory.getLog(Login.class);

	@Override
	public void execute(Scanner scanner) {
		System.out.println();
		System.out.println("===로그인 페이지===");
		System.out.print("아이디 : ");
		String memId = scanner.next();
		System.out.print("비밀번호 : ");
		String memPasswd = scanner.next();
		// memberDAO의 메소드를 호출하기 위해 인스턴스화 해준다.
		MemberDAO memberDAO = new MemberDAO();
		// memberDAO의 login 메소드를 호출하여 값을 반환받아 MemberDTO의 자료형으로 값을 할당한다.
		MemberDTO memberDTO = memberDAO.login(memId, memPasswd);
		log.info("로그인 MemberDTO - " + memberDTO);
		if (memberDTO.getMemId() == null) {
			System.out.println("등록되지 않은 아이디 입니다.");
			System.out.print("회원가입 하려면 Y를 입력해주세요? : ");
			String choice = scanner.next();
			if (choice.equals("y") || choice.equals("Y")) {
				MemberInsert memberInsert = new MemberInsert();
				memberInsert.execute(scanner);
				return;
			} else {
				return;
			}
		} else {
			if (memberDTO.getMemPasswd() == null) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				return;
			}
		}
		logMember.setMemId(memberDTO.getMemId());
		System.out.println(logMember.getMemId() + "님 반갑습니다.");
	}
}
