package kyu.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyu.project.dbcp.Context;
import kyu.project.dto.MemberDTO;

public class MemberDAO {
	// 로그 출력을 위해 템플릿을 이용하여 작성
	private static final Log log = LogFactory.getLog(MemberDAO.class);

	// 기본 생성자 사용
	public MemberDAO() {
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			dataSource.getConnection();
		} catch (SQLException e) {
			log.info("데이터베이스 연결 실패 - " + e);
		}
	}

	// MemberDTO 클래스 반환 자료형으로 특정 회원 아이디와 비밀번호를 조회한다.
	public MemberDTO login(String memId, String memPasswd) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		MemberDTO memberDTO = new MemberDTO();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			// 쿼리문 작성
			String sql = "select memId, memPasswd from memberlist where memid = '" + memId + "'";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			// SQL문을 실행하고 데이터를 조회한다.
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				memberDTO.setMemId(resultSet.getString("memId"));
				log.info("아이디 확인 - " + resultSet.getString("memId"));
				if (resultSet.getString("memPasswd").equals(memPasswd)) {
					memberDTO.setMemPasswd(resultSet.getString("memPasswd"));
					log.info("비밀번호 확인 - " + resultSet.getString("memPasswd"));
				}
			}
		} catch (SQLException e) {
			log.info("로그인 실패 - " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	}

	// 제네릭 MemberDTO 클래스 반환 자료형으로 전체 회원 데이터를 조회한다.
	public ArrayList<MemberDTO> memberList() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			// 쿼리문 입력
			String sql = "select memNumber, memId, memEmail from memberList ";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemNumber(resultSet.getInt("memNumber"));
				memberDTO.setMemId(resultSet.getString("memId"));
				memberDTO.setmemEmail(resultSet.getString("memEmail"));
				arrayList.add(memberDTO);
				log.info("조회 데이터 확인" + memberDTO);
			}
			if (resultSet.getRow() == 0) {
				log.info("등록된 회원이 없습니다.");
			}
		} catch (SQLException e) {
			log.info("전체 회원 조회 실패 - " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayList;
	}

	// MemberDTO 클래스 반환 자료형으로 특정 회원 데이터를 조회한다.
	public MemberDTO memberSelect(String memId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		MemberDTO memberDTO = new MemberDTO();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			// 쿼리문 작성
			String sql = "select memNumber,memId, memEmail, memPoint, to_char(joinDate,'yyyy-mm-dd') from memberList";
			sql += " where memId = '" + memId + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				log.info("아이디 확인 - " + resultSet.getString("memId"));
				memberDTO.setMemNumber(resultSet.getInt("memNumber"));
				memberDTO.setMemId(resultSet.getString("memId"));
				memberDTO.setmemEmail(resultSet.getString("memEmail"));
				memberDTO.setMemPoint(resultSet.getInt("memPoint"));
				memberDTO.setJoinDate(resultSet.getString("to_char(joinDate,'yyyy-mm-dd')"));
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
		return memberDTO;
	}

	// MemberDTO 클래스 반환 자료형으로 특정 회원 아이디를 조회한다.
	public MemberDTO memberIdCheck(String memId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		MemberDTO memberDTO = new MemberDTO();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "select memId from memberList where memId= '" + memId + "'";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			//SQL문을 실행하고 데이터를 조회한다.
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				memberDTO.setMemId(resultSet.getString("memId"));
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
		return memberDTO;
	}

	// 회원 데이터베이스에 새로운 회원 데이터를 입력한다.
	public void memberInsert(MemberDTO memberDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			// 쿼리문 작성
			String sql = "insert into memberList(memNumber, memId, memPasswd, memEmail, memPoint, joinDate) ";
			sql += " values (memberlist_seq.nextval,?,?,?,?,sysdate)";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getMemId());
			preparedStatement.setString(2, memberDTO.getMemPasswd());
			preparedStatement.setString(3, memberDTO.getmemEmail());
			preparedStatement.setInt(4, memberDTO.getMemPoint());
			int count = preparedStatement.executeUpdate();
			log.info("입력 데이터 확인 - " + memberDTO);
			if (count > 0) {
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (SQLException e) {
			log.info("회원 가입 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// MemberDTO 클래스 반환 자료형으로 특정 회원 데이터를 수정한다.
	public MemberDTO memberUpdate(MemberDTO memberDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "update memberlist set memPasswd = ?, memEmail = ? where memId = ?";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getMemPasswd());
			preparedStatement.setString(2, memberDTO.getmemEmail());
			preparedStatement.setString(3, memberDTO.getMemId());
			int count = preparedStatement.executeUpdate();
			log.info("수정 데이터 확인 - " + memberDTO);
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
		return memberDTO;
	}

	// MemberDTO 클래스 반환 자료형으로 특정 회원 데이터를 삭제한다.
	public void memberDelete(String memId) {
		Connection connection = null;
		Statement statement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "delete from memberlist where memid = '" + memId + "'";
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

	// MemberDTO 클래스 반환 자료형으로 특정 회원의 포인트를 수정한다.
	public MemberDTO memberPointSearch(MemberDTO memberDTO) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "select memId, memPoint from memberlist where = '" + memberDTO.getMemId() + "'";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				memberDTO.setMemId(resultSet.getString("memId"));
				memberDTO.setMemPoint(resultSet.getInt("memPoint"));
			}
		} catch (SQLException e) {
			log.info("회원 포인트 검색 실패 - " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	}

	// MemberDTO 클래스 반환 자료형으로 특정 회원의 포인트를 수정한다.
	public MemberDTO memberPointUpdate(MemberDTO memberDTO) {
		Connection connection = null;
		Statement statement = null;
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "update memberlist set memPoint = '" + memberDTO.getMemPoint() + "' ";
			sql += " where memId = '" + memberDTO.getMemId() + "'";
			log.info("SQL - " + sql);
			statement = connection.createStatement();
			int count = statement.executeUpdate(sql);
			log.info("수정 데이터 확인 - " + memberDTO);
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
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	}
}
