package kyu.member.service;

import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.control.Action;
import kyu.project.dao.MemDetailDAO;
import kyu.project.dao.MemberDAO;
import kyu.project.dto.MemberDTO;

public class MemberDelete implements Action {

	private static final Log log = LogFactory.getLog(MemberDelete.class);

	@Override
	public void execute(Scanner scanner) {
		System.out.println();
		System.out.println("===회원 삭제 페이지===");
		MemberDAO memberDAO = new MemberDAO();
		MemDetailDAO memDetailDAO = new MemDetailDAO();
		MemberDTO memberDTO = new MemberDTO();
		String memId;
		System.out.print("삭제할 회원 아이디를 입력하세요 : ");
		memId = scanner.next();
		memberDTO = memberDAO.memberIdCheck(memId);
		if (memberDTO.getMemId() == null) {
			System.out.println("존재하지 않는 아이디입니다.");
		} else {
			log.info("아이디 확인 - " + memberDTO.getMemId());
			System.out.print("삭제하시겠습니까?(y) : ");
			String choice = scanner.next();
			if (choice.equals("y") || choice.equals("Y")) {
				if (memDetailDAO.memDetailIdCheck(memId) != null) {
					memDetailDAO.memDetailDelete(memId);
				}
				memberDAO.memberDelete(memId);
				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("취소하였습니다.");
			}
		}
	}
}
