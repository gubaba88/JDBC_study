package kyu.board.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.control.Action;
import kyu.project.dao.BoardDAO;
import kyu.project.dto.BoardDTO;

public class BoardInsert implements Action {

	private static final Log log = LogFactory.getLog(BoardInsert.class);

	@Override
	public void execute(Scanner scanner) throws IOException {
		System.out.println("===게임 등록 페이지===");
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		System.out.print("등록할 게임 이름을 입력해 주세요 : ");
		String gameName = bufferedReader.readLine();
		boardDTO.setGameName(gameName);
		boardDAO.gameInsert(boardDTO);
		log.info("입력 정보 - " + boardDTO);
		System.out.println("게임 등록이 완료되었습니다.");
	}
}
