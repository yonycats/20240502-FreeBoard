package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class FreeDao {
	private static FreeDao instance;

	private FreeDao() {

	}

	public static FreeDao getInstance() {
		if (instance == null) {
			instance = new FreeDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<Map<String, Object>> list() {
		String sql = "SELECT NO, NAME, CONTENT, WRITER, REGDATE\r\n" + 
				 	 "FROM JAVA_FREEBOARD\r\n" + 
					 "WHERE DELYN = 'N'\r\n" + 
					 "ORDER BY 1";
		
		return jdbc.selectList(sql);
	}
	
	
	public Map<String, Object> detail(List<Object> param) {
		String sql = "SELECT NO, NAME, CONTENT, WRITER, REGDATE\r\n" + 
					 "FROM JAVA_FREEBOARD\r\n" + 
					 "WHERE NO = ? AND DELYN = 'N'";
		
		return jdbc.selectOne(sql, param);
	}
	
	
	public void delete(List<Object> param) {
		String sql = "UPDATE JAVA_FREEBOARD\r\n" + 
					 "SET DELYN = 'Y'\r\n" + 
					 "WHERE NO = ?";
		
		jdbc.update(sql, param);
	}

	public void insert(List<Object> param) {
		String sql = "INSERT INTO JAVA_FREEBOARD (NO, NAME, CONTENT, WRITER)\r\n" + 
				"VALUES ((SELECT NVL(MAX(NO), 0)+1 FROM JAVA_FREEBOARD), ?, ?, ?)";
		
		jdbc.update(sql, param);
	}
	
	
}
