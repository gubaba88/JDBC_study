package kyu.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.control.Action;
import kyu.project.dao.PostDAO;
import kyu.project.dto.PostDTO;

public class PostList implements Action {

	private static final Log log = LogFactory.getLog(PostList.class);

	@Override
	public void execute(Scanner scanner) {
		System.out.println("===게시판 글 목록===");
		PostDAO postDAO = new PostDAO();
		List<PostDTO> list = new ArrayList<PostDTO>();
		int gameNumber = logBoard.getGameNumber();
		log.info("게임 번호 정보 - " + gameNumber);
		list = postDAO.postList(postDAO.PostGameNumberCheck(gameNumber).getGameNumber());
		for (PostDTO postDTO : list) {
			int postNumber = postDTO.getPostNumber();
			String postTitle = postDTO.getPostTitle();
			String postTopic = postDTO.getPostTopic();
			String postDate = postDTO.getPostDate();
			String memId = postDTO.getMemId();
			System.out.println(postNumber + ". " + postTopic + "    제목 : " + postTitle + "    작성자 : " + memId
					+ "    작성 시간  : " + postDate);
		}
		System.out.println("=========================================");
		System.out.println("1. 글 상세보기    2. 글 등록하기    3. 글 수정하기    4. 글 삭제하기");
		System.out.print("선택 번호 : ");
		String choice = scanner.next();
		switch (choice) {
		case "1":
			PostSelect postSelect = new PostSelect();
			postSelect.execute(scanner);
			break;
		case "2":
			PostInsert postInsert = new PostInsert();
			postInsert.execute(scanner);
		case "3":
			PostUpdate postUpdate = new PostUpdate();
			postUpdate.execute(scanner);
			break;
		case "4":
			PostDelete postDelete = new PostDelete();
			postDelete.execute(scanner);
			break;
		default:
			System.out.println("잘못된 입력으로 이전 화면으로 돌아갑니다.");
			break;
		}
	}
}
