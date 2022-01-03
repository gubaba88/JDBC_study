package kyu.board.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.control.Action;
import kyu.project.dao.BoardDAO;
import kyu.project.dao.PostDAO;

public class BoardDelete implements Action {

	private static final Log log = LogFactory.getLog(BoardDelete.class);

	@Override
	public void execute(Scanner scanner) {
		System.out.println("==게임 삭제 페이지===");
		BoardDAO boardDAO = new BoardDAO();
		System.out.print("삭제할 게임의 번호를 선택해 주세요. : ");
		int select = 0;
		try {
			select = scanner.nextInt();
		} catch (InputMismatchException e) {
			log.info("게임 번호 입력 오류 - " + e);
		}
		if (boardDAO.gameNumberCheck(select) == null) {
			System.out.println("해당하는 게임 번호가 없습니다.");
			return;
		} else {
			PostDAO postDAO = new PostDAO();
			if (postDAO.PostGameNumberCheck(select).getGameNumber() > 0) {
				System.out.println("해당 게임의 게시판을 먼저 삭제해야 합니다.");
				return;
			} else {
				boardDAO.gameDelete(select);
				System.out.println("삭제가 완료되었습니다.");
			}
		}
	}
}
