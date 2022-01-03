package kyu.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.dbcp.Context;
import kyu.project.dto.PostDTO;

public class PostDAO {
	// 로그 출력을 위해 템플릿을 이용하여 작성
	private static final Log log = LogFactory.getLog(PostDAO.class);

	// 기본생성자 생성
	public PostDAO() {
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			dataSource.getConnection();
		} catch (SQLException e) {
			log.info("데이터베이스 연결 실패 - " + e);
		}
	}

	// 제네릭 PostDTO 클래스 반환 자료형으로 특정 게임 게시판의 전체 글 데이터를 조회한다.
	public List<PostDTO> postList(int gameNumber) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<PostDTO> list = new ArrayList<PostDTO>();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "select * from postlist"+gameNumber;
			log.info("SQL - " + sql);
			statement =  connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				PostDTO postDTO = new PostDTO();
				postDTO.setPostNumber(resultSet.getInt("postNumber"));
				postDTO.setPostTitle(resultSet.getString("postTitle"));
				postDTO.setPostTopic(resultSet.getString("postTopic"));
				postDTO.setPostContent(resultSet.getString("postContent"));
				postDTO.setPostDate(resultSet.getString("postDate"));
				postDTO.setMemId(resultSet.getString("memId"));
				list.add(postDTO);
				log.info("조회 데이터 확인 - " + postDTO);
			}
		} catch (SQLException e) {
			log.info("게시판 글 조회 실패 - " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public PostDTO PostGameNumberCheck(int gameNumber) {
		PostDTO postDTO = new PostDTO();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "select TNAME from TAB where TNAME like 'POSTLIST" + gameNumber + "'";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				gameNumber = Integer.parseInt(resultSet.getString("TNAME").substring(9));			
				postDTO.setGameNumber(gameNumber);
				log.info("게시판이름 - " +  resultSet.getString("TNAME"));
			}
		} catch (SQLException e) {
			log.info("게시판 게임 번호 체크 - " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return postDTO;
	}

	// 제네릭 PostDTO 클래스 반환 자료형으로 특정 작성자의 글 데이터를 조회한다.
	public List<PostDTO> postSearch(String memNick) {
		List<PostDTO> list = new ArrayList<PostDTO>();
		return list;
	}

	// PostDTO 클래스 반환 자료형으로 특정 글의 데이터를 조회한다.
	public PostDTO postSelect(int gameNumber, int postNumber) {
		PostDTO postDTO = new PostDTO();
		return postDTO;
	}

	// postDTO 클래스 자료형으로 글을 입력한다.
	public void postInsert(PostDTO postDTO) {
	}

	// postDTO 클래스 반환 자료형으로 글을 수정한다.
	public PostDTO postUpdate(PostDTO postDTO) {
		return postDTO;
	}

	//글 번호를 입력받아 글 데이터를 삭제한다.
	public void postDelete(int gameNumber, int postNumber) {
	}
}
