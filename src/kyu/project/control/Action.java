package kyu.project.control;

import java.io.IOException;
import java.util.Scanner;

import kyu.project.dto.BoardDTO;
import kyu.project.dto.MemberDTO;

public interface Action {
	public void execute(Scanner scanner) throws IOException;
	MemberDTO logMember = new MemberDTO();
	BoardDTO logBoard = new BoardDTO();
}
