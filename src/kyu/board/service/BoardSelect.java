package kyu.board.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.post.service.PostList;
import kyu.project.control.Action;
import kyu.project.dao.BoardDAO;
import kyu.project.dto.BoardDTO;

public class BoardSelect implements Action {
	
	private static final Log log = LogFactory.getLog(BoardSelect.class);
	@Override
	public void execute(Scanner scanner) {
		System.out.println("===게시판 선택 페이지===");
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		System.out.print("확인하고 싶은 게임의 번호를 입력하세요 : ");
		int select = 0;
		try {
			select = scanner.nextInt();
		} catch (InputMismatchException e) {
			log.info("게임 번호 선택 오류 - " + e);
		}
		boardDTO = boardDAO.gameNumberCheck(select);
		if (boardDTO.getGameName() == null) {
			System.out.println("잘못 입력하셨습니다.");
			return;
		} else {
			logBoard.setGameNumber(select);
			PostList postList = new PostList();
			postList.execute(scanner);
		}
	}
}
