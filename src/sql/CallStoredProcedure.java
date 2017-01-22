package sql;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import multithreads.Thread1;

public class CallStoredProcedure {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/ordermanagement";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "tiger";
	private static Connection dbConnection;
	private static ResultSet rs = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName(DB_DRIVER);
		 dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		 return dbConnection;
	}
	
	public static String getProcedureName()
	{
		Scanner ss=new Scanner(System.in);
		System.out.println("Enter the name of the procedure");
		String procedurename=ss.nextLine();
		return procedurename;
	}
	
	public static Integer executeStoredProcedure(String name,Integer param) throws SQLException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		getConnection();
		
		CallableStatement callableStatement = null;
		
		//getDBUSERByUserId is a stored procedure
		String getDBUSERByUserIdSql = "{call "+name+"(?,?)}";
		callableStatement = dbConnection.prepareCall(getDBUSERByUserIdSql);
		callableStatement.setInt(1, 1);
		callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);
	//	callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
	//	callableStatement.registerOutParameter(4, java.sql.Types.DATE);

		// execute getDBUSERByUserId store procedure
		System.out.println("No of rows updated "+callableStatement.executeUpdate());

		Integer userName = callableStatement.getInt(2);
	/*	String createdBy = callableStatement.getString(3);
		Date createdDate = callableStatement.getDate(4);*/
		
		return userName;

	}
	
	public static void getProcedureNames() throws ClassNotFoundException, SQLException
	{
		DatabaseMetaData meta = getConnection().getMetaData();
	    rs = meta.getProcedures(null, null, "%");

	    while (rs.next()) {
	      String spName = rs.getString("PROCEDURE_NAME");
	      int spType = rs.getInt("PROCEDURE_TYPE");
	      System.out.println("Stored Procedure Name: " + spName);
	      System.out.println("Stored Procedure type: "+spType);
	      if (spType == DatabaseMetaData.procedureReturnsResult) {
	        System.out.println("procedure Returns Result");
	      } else if (spType == DatabaseMetaData.procedureNoResult) {
	        System.out.println("procedure No Result");
	      } else {
	        System.out.println("procedure Result unknown");
	      }
	      
	      

	    }
	}
	
	public static void getProcedureDetails() throws ClassNotFoundException, SQLException
	{
		DatabaseMetaData meta = getConnection().getMetaData();
		
		 rs=meta.getProcedureColumns(getConnection().getCatalog(),
                null,
                "procedureNamePattern",
                "columnNamePattern");
		
		while(rs.next()) {
		      // get stored procedure metadata
		      String procedureCatalog     = rs.getString(1);
		      String procedureSchema      = rs.getString(2);
		      String procedureName        = rs.getString(3);
		      String columnName           = rs.getString(4);
		      short  columnReturn         = rs.getShort(5);
		      int    columnDataType       = rs.getInt(6);
		      String columnReturnTypeName = rs.getString(7);
		      int    columnPrecision      = rs.getInt(8);
		      int    columnByteLength     = rs.getInt(9);
		      short  columnScale          = rs.getShort(10);
		      short  columnRadix          = rs.getShort(11);
		      short  columnNullable       = rs.getShort(12);
		      String columnRemarks        = rs.getString(13);

		      System.out.println("stored Procedure name="+procedureName);
		      System.out.println("procedureCatalog=" + procedureCatalog);
		      System.out.println("procedureSchema=" + procedureSchema);
		      System.out.println("procedureName=" + procedureName);
		      System.out.println("columnName=" + columnName);
		      System.out.println("columnReturn=" + columnReturn);
		      System.out.println("columnDataType=" + columnDataType);
		      System.out.println("columnReturnTypeName=" + columnReturnTypeName);
		      System.out.println("columnPrecision=" + columnPrecision);
		      System.out.println("columnByteLength=" + columnByteLength);
		      System.out.println("columnScale=" + columnScale);
		      System.out.println("columnRadix=" + columnRadix);
		      System.out.println("columnNullable=" + columnNullable);
		      System.out.println("columnRemarks=" + columnRemarks);
		    }
		
		
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		//new Thread1();
		//CallStoredProcedure.executeStoredProcedure();
		//getProcedureNames();
		//getProcedureDetails();
	}

}
