package kyu.board.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kyu.project.control.Action;
import kyu.project.dao.BoardDAO;
import kyu.project.dto.BoardDTO;
import kyu.project.view.BoardMain;

public class BoardList implements Action {
	@Override
	public void execute(Scanner scanner) throws IOException {
		System.out.println("===========게임 게시판 목록===========");
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDAO boardDAO = new BoardDAO();
		list = boardDAO.boardList();
		for (BoardDTO gameDTO : list) {
			int gameNumber = gameDTO.getGameNumber();
			String gameName = gameDTO.getGameName();
			System.out.println("게임 번호 : " + gameNumber + ",  게임 이름 : " + gameName);
		}
		while (true) {
			System.out.println("===============================");
			System.out.println("1.게시판 선택    2.게시판 관리     0.메인 화면으로");
			System.out.print("메뉴 번호 입력 : ");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				BoardSelect boardSelect = new BoardSelect();
				boardSelect.execute(scanner);
				break;
			case "2":
				BoardMain.main(null);
				break;
			case "0":
				System.out.println("메인화면으로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}
	}
}
