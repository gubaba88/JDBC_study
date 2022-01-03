package kyu.board.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.control.Action;
import kyu.project.dao.BoardDAO;
import kyu.project.dto.BoardDTO;

public class BoardUpdate implements Action {

	private static final Log log = LogFactory.getLog(BoardUpdate.class);

	@Override
	public void execute(Scanner scanner) throws IOException {
		System.out.println("===게임 수정 페이지===");
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		System.out.print("수정하고 싶은 게임의 번호를 입력하세요 : ");
		int select = 0;
		try {
			select = scanner.nextInt();
		} catch (InputMismatchException e) {
			log.info("게임 번호 선택 오류 - " + e);
		}
		if (boardDAO.gameNumberCheck(select) == null) {
			System.out.println("일치하는 번호가 없습니다.");
		} else {
			log.info("선택 번호 확인 - " + select);
			boardDTO = boardDAO.gameNumberCheck(select);
			log.info("게임 정보 확인 - " + boardDTO);
			System.out.println("게임 이름을 수정하시겠습니까?(Y) : ");
			String choiceName = scanner.next();
			if (choiceName.equals("y") || choiceName.equals("Y")) {
				System.out.println("현재 게임 이름 : " + boardDTO.getGameName() + " 입니다.");
				System.out.print("게임 이름 입력 : ");
				String gameName = bufferedReader.readLine();
				boardDTO.setGameName(gameName);
			}
			log.info("입력할 내용 - " + boardDTO);
			boardDTO = boardDAO.gameUpdate(boardDTO);
			log.info("수정된 내용 - " + boardDTO);
			System.out.println("게임 정보 수정이 완료 되었습니다.");
		}
	}
}
