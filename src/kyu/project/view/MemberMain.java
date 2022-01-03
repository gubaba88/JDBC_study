package kyu.project.view;

import java.io.IOException;
import java.util.Scanner;

import kyu.member.service.MemberInsert;
import kyu.member.service.MemberList;
import kyu.project.control.Action;

public class MemberMain implements Action {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (logMember.getMemId() == null) {
				System.out.println("회원 메인 화면은 로그인이 필요하므로 홈 화면으로 돌아갑니다.");
				break;
			}
			System.out.println("================================");
			System.out.println("---------------------회원 메인---------------------");
			System.out.println("1.회원 목록 확인     2.회원 등록     0.메인 화면으로");
			System.out.print("메뉴 번호 입력 : ");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				MemberList memberList = new MemberList();
				memberList.execute(scanner);
				break;
			case "2":
				MemberInsert memberInsert = new MemberInsert();
				memberInsert.execute(scanner);
				break;
			case "0":
				System.out.println("메인 화면으로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

	@Override
	public void execute(Scanner scanner) throws IOException {
	}
}
