package kyu.project.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;

public class Context {
	public BasicDataSource basicDataSource;
	public Context() {
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		basicDataSource.setUsername("scott");
		basicDataSource.setPassword("tiger");
	}
}