package kyu.project.view;

import java.io.IOException;
import java.util.Scanner;

import kyu.board.service.BoardList;
import kyu.project.control.Action;

public class HomeMain implements Action {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (logMember.getMemId() == null) {
				System.out.println("홈 메인 화면은 로그인이 필요하므로 시작 화면으로 돌아갑니다.");
				break;
			}
			System.out.println("========================");
			System.out.println("-------------홈 메인 화면-------------");
			System.out.println("1.게시판     2.회원 정보     3.로그아웃");
			System.out.print("선택 번호 : ");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				BoardList boardList = new BoardList();
				boardList.execute(scanner);
				break;
			case "2":
				MemberMain.main(null);
				break;
			case "3":
				System.out.println("로그아웃 : " + logMember.getMemId() + "님 안녕히 가세요.");
				logMember.setMemId(null);
				return;
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
