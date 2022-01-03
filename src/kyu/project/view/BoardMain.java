package kyu.project.view;

import java.io.IOException;
import java.util.Scanner;

import kyu.board.service.BoardDelete;
import kyu.board.service.BoardInsert;
import kyu.board.service.BoardUpdate;
import kyu.project.control.Action;

public class BoardMain implements Action {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("=======================================");
			System.out.println("--------------------------게시판 관리-------------------------");
			System.out.print("1.게시판 등록    2.게시판 수정    3.게시판 삭제    0.이전화면으로");
			System.out.print("메뉴 번호 입력 : ");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				BoardInsert boardInsert = new BoardInsert();
				boardInsert.execute(scanner);
				break;
			case "2":
				BoardUpdate boardUpdate = new BoardUpdate();
				boardUpdate.execute(scanner);
				break;
			case "3":
				BoardDelete boardDelete = new BoardDelete();
				boardDelete.execute(scanner);
				break;
			case "0":
				System.out.println("이전화면으로 돌아갑니다.");
				return;
			default:
				break;
			}
		}
	}

	@Override
	public void execute(Scanner scanner) throws IOException {
	}
}
