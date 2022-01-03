package kyu.project.view;

import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.board.service.BoardList;
import kyu.member.service.Login;
import kyu.member.service.MemberInsert;
import kyu.project.control.Action;

public class StartMain implements Action {
	
	private static final Log log = LogFactory.getLog(StartMain.class);
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("===========================");
			System.out.println("-----------------시작 화면-----------------");
			System.out.println("1. 로그인    2.회원가입    3.게시판    0.종료");
			System.out.print("메뉴 번호 입력 : ");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				Login login = new Login();
				login.execute(scanner);
				try {
					if (logMember.getMemId() != null) {
						HomeMain.main(null);
					}
				} catch (IndexOutOfBoundsException e) {
					log.info("로그인 실패");
				}
				break;
			case "2":
				MemberInsert memberInsert = new MemberInsert();
				memberInsert.execute(scanner);
				break;
			case "3":
				BoardList boardList = new BoardList();
				boardList.execute(scanner);
				break;
			case "0":
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}
	}

	@Override
	public void execute(Scanner scanner) {
	}
}
