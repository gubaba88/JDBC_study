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
import kyu.project.dto.BoardDTO;

public class BoardDAO {
	// 로그 출력을 위해 템플릿으로 작성
	private static final Log log = LogFactory.getLog(BoardDAO.class);

	// 기본생성자 생성
	public BoardDAO() {
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			dataSource.getConnection();
		} catch (SQLException e) {
			log.info("데이터베이스 연결 실패 - " + e);
		}
	}

	// 제네릭 GameDTO 클래스 반환 자료형으로 전체 게임 데이터를 조회한다.
	public List<BoardDTO> boardList() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDTO boardDTO = new BoardDTO();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = " select * from gamelist where gameNumber>0";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				boardDTO.setGameNumber(resultSet.getInt("gameNumber"));
				boardDTO.setGameName(resultSet.getString("gameName"));
				list.add(boardDTO);
				log.info("조회 데이터 확인" + boardDTO);
			}
		} catch (SQLException e) {
			log.info("게임 게시판 목록 불러오기 실패 - " + e);
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
	// GameDTO 클래스 반환 자료형으로 특정 게임의 데이터를 조회한다.
	public BoardDTO gameNumberCheck(int gameNumber) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		BoardDTO boardDTO = new BoardDTO();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "select * from gameList where gameNumber = '" + gameNumber + "'";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				boardDTO.setGameNumber(resultSet.getInt("gameNumber"));
				boardDTO.setGameName(resultSet.getString("gameName"));
			}
		} catch (SQLException e) {
			log.info("게임 번호 체크 - " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardDTO;
	}

	// GameDTO 클래스 반환 자료형으로 특정 게임의 데이터를 입력한다.
	public void gameInsert(BoardDTO boardDTO) {
		Connection connection = null;
		Statement statement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			// 쿼리문 작성
			String sql = "insert into gameList(gameNumber, gameName) ";
			sql += " values (gamelist_seq.nextval,'"+boardDTO+"')";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			int count = statement.executeUpdate(sql);
			log.info("입력 데이터 확인 - " + boardDTO);
			if (count > 0) {
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (SQLException e) {
			log.info("게임 등록 실패 - " + e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// GameDTO 클래스 반환 자료형으로 특정 게임의 데이터를 수정한다.
	public BoardDTO gameUpdate(BoardDTO boardDTO) {
		Connection connection = null;
		Statement statement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "update gamelist set gameName = '"+boardDTO.getGameName()+"' ";
			sql += "where gameNumber = '"+boardDTO.getGameNumber()+"'";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			int count = statement.executeUpdate(sql);
			log.info("수정 데이터 확인 - " + boardDTO);
			if (count > 0) {
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (SQLException e) {
			log.info("게임 이름 수정 실패 - " + e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardDTO;
	}

	// GameDTO 클래스 반환 자료형으로 특정 게임의 데이터를 삭제한다.
	public void gameDelete(int gameNumber) {
		Connection connection = null;
		Statement statement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "delete from gamelist where gameNumber = '" + gameNumber + "'";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			int count = statement.executeUpdate(sql);
			if (count > 0) {
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (SQLException e) {
			log.info("게임 삭제 실패 - " + e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
