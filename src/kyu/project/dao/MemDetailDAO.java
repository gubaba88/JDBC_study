package kyu.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.dbcp.Context;
import kyu.project.dto.MemDetailDTO;

public class MemDetailDAO {
	// 로그 출력을 위해 템플릿을 통해 작성
	private static final Log log = LogFactory.getLog(MemDetailDAO.class);

	public MemDetailDAO() {
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// MemDetailDTO 클래스 반환 자료형으로 특정 회원 아이디를 조회한다.
	public MemDetailDTO memDetailIdCheck(String memId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		MemDetailDTO memDetailDTO = new MemDetailDTO();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "select memId from memberdetail where memId= '" + memId + "'";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				memDetailDTO.setMemId(resultSet.getString("memId"));
			}
		} catch (SQLException e) {
			log.info("회원 아이디 체크 - " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memDetailDTO;
	}
	// MemDetailDTO 클래스 반환 자료형으로 특정 회원의 데이터를 조회한다.
	public MemDetailDTO memDetailSelect(String memId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		MemDetailDTO memDetailDTO = new MemDetailDTO();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			// 쿼리문 작성
			String sql = "select memId, memName, to_char(memBirth,'yyyy-mm-dd'), memGender, memPhone, memAddress,memComment  from memberDetail ";
			sql += " where memId = '" + memId + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				log.info("아이디 확인 - " + resultSet.getString("memId"));
				memDetailDTO.setMemId(resultSet.getString("memId"));
				memDetailDTO.setMemName(resultSet.getString("memName"));
				memDetailDTO.setMemBirth(resultSet.getString("to_char(memBirth,'yyyy-mm-dd')"));
				memDetailDTO.setMemGender(resultSet.getInt("memGender"));
				memDetailDTO.setMemPhone(resultSet.getString("memPhone"));
				memDetailDTO.setMemAddress(resultSet.getString("memAddress"));
				memDetailDTO.setMemComment(resultSet.getString("memComment"));
			}
		} catch (SQLException e) {
			log.info("개별 회원 조회 실패 - " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memDetailDTO;
	}

	// MemDetailDTO 클래스 자료형으로 특정 회원 추가데이터를 입력한다.
	public void memDetailInsert(MemDetailDTO memDetailDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "insert into memberdetail(memId,MemName,memBirth,memGender,memPhone,memAddress,memComment) ";
			sql += " values ( ?, ?, to_date(?,'yyyy-mm-dd'), ?, ?, ?, ?)";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memDetailDTO.getMemId());
			preparedStatement.setString(2, memDetailDTO.getMemName());
			preparedStatement.setString(3, memDetailDTO.getMemBirth());
			preparedStatement.setInt(4, memDetailDTO.getMemGender());
			preparedStatement.setString(5, memDetailDTO.getMemPhone());
			preparedStatement.setString(6, memDetailDTO.getMemAddress());
			preparedStatement.setString(7, memDetailDTO.getMemComment());
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (SQLException e) {
			log.info("회원 추가정보 입력 실패 - " + e);
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// MemDetailDTO 클래스 반환 자료형으로 특정 회원의 데이터를 수정한다.
	public MemDetailDTO memDetailUpdate(MemDetailDTO memDetailDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "update memberdeail set memName = ?, memBirth = to_date(?,'yyyy-mm-dd'), memGender = ?, memPhone = ?, memAddress = ?, memComment ";
			sql += " where memId = ?";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memDetailDTO.getMemName());
			preparedStatement.setString(2, memDetailDTO.getMemBirth());
			preparedStatement.setInt(3, memDetailDTO.getMemGender());
			preparedStatement.setString(4, memDetailDTO.getMemPhone());
			preparedStatement.setString(5, memDetailDTO.getMemAddress());
			preparedStatement.setString(6, memDetailDTO.getMemComment());
			preparedStatement.setString(7, memDetailDTO.getMemId());
			int count = preparedStatement.executeUpdate();
			log.info("수정 데이터 확인 - " + memDetailDTO);
			if (count > 0) {
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (SQLException e) {
			log.info("회원 기본 수정 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memDetailDTO;
	}
	// void 반환 자료형으로 특정 회원의 데이터를 삭제한다.
	public void memDetailDelete(String memId) {

		Connection connection = null;
		Statement statement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "delete from memberdetail where memid = '" + memId + "'";
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
			log.info("회원 삭제 실패 - " + e);
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
