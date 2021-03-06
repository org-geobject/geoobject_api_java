package org.cendra.commons.util.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cendra.commons.util.error.LogPrinter;

public class ConnectionWrapper {

	public final String OPERATION_TYPE_BEGIN_TRANSACTION = "BEGIN_TRANSACTION";
	public final String OPERATION_TYPE_COMMIT_TRANSACTION = "COMMIT_TRANSACTION";
	public final String OPERATION_TYPE_ROLLBACK_TRANSACTION = "ROLLBACK_TRANSACTION";
	public final String OPERATION_TYPE_CLOSE_CONNECTION = "CLOSE_CONNECTION";
	public final String OPERATION_TYPE_SELECT = "SELECT";
	public final String OPERATION_TYPE_INSERT = "INSERT";
	public final String OPERATION_TYPE_UPDATE = "UPDATE";
	public final String OPERATION_TYPE_DELETE = "DELETE";

	public final String TITLE_BEGIN_TRANSACTION = "Comienzo de Transacción";
	public final String TITLE_COMMIT_TRANSACTION = "Fin de Transacción";
	public final String TITLE_ROLLBACK_TRANSACTION = "Deshacer de Cambios de una Transacción";
	public final String TITLE_CLOSE_CONNECTION = "Cierre de Conexión";
	public final String TITLE_SELECT = "Consultando Listado de Registros";
	public final String TITLE_INSERT = "Insertando un Registro";
	public final String TITLE_UPDATE = "Actualizando un Registro";

	public final String SUBJECT_BEGIN_TRANSACTION = "Error al intentar iniciar una transacción.";
	public final String SUBJECT_COMMIT_TRANSACTION = "Fin de Transacción";
	public final String SUBJECT_ROLLBACK_TRANSACTION = "Error al intentar deshacer los cambios de una transacción.";
	public final String SUBJECT_CLOSE_CONNECTION = "Error al intentar cerrar una conexión.";
	public final String SUBJECT_SELECT = "Error al intentar consultar un listado del total de registros.";
	public final String SUBJECT_INSERT = "Error al intentar insertar un registro.";
	public final String SUBJECT_UPDATE = "Error al intentar actualizar un registro.";

	public final String MSG_1 = "Se pretende agregar un parámetro a una sentencia sql que posee un tipo de dato desconocido. Se recibió [${value}] de tipo ${class}, y se espera String | Boolean | Short | Integer | Long | Float | Double | BigDecimal | Date | Timestamp | Time";

	private Connection connection;
	private DataSourceMetaData dataSourceMetaData;
	private DataSourceProperties dataSourceProperties;
	private LogPrinter errorPrinter;
	private List<String> sqlStatements = new ArrayList<String>();

	private boolean verbose = false;

	// ========================================================================================

	public ConnectionWrapper(Connection connection,
			DataSourceMetaData dataSourceMetaData,
			DataSourceProperties dataSourceProperties, LogPrinter errorPrinter) {

		this.connection = connection;
		this.dataSourceMetaData = dataSourceMetaData;
		this.dataSourceProperties = dataSourceProperties;
		this.errorPrinter = errorPrinter;
		this.verbose = this.dataSourceProperties.isVerbose();
	}

	// ========================================================================================

	public boolean isVerbose() {
		return verbose;
	}

	public void addSqlStatement(String sql) {
		sqlStatements.add(sql);
	}

	public List<String> getSqlStatements() {
		return sqlStatements;
	}

	public Connection getConnection() throws SQLException {
		return connection;
	}

	public DataSourceMetaData getDataSourceMetaData() {
		return dataSourceMetaData;
	}

	public String getUrl() {
		return dataSourceMetaData.url;
	}

	public String getUserName() {
		return dataSourceMetaData.userName;
	}

	public String getDatabaseProductName() {
		return dataSourceMetaData.databaseProductName;
	}

	public String getDatabaseProductVersion() {
		return dataSourceMetaData.databaseProductVersion;
	}

	public String getDriverName() {
		return dataSourceMetaData.driverName;
	}

	public String getDriverVersion() {
		return dataSourceMetaData.driverVersion;
	}

	public int getjDBCMajorVersion() {
		return dataSourceMetaData.jDBCMajorVersion;
	}

	public int getjDBCMinorVersion() {
		return dataSourceMetaData.jDBCMinorVersion;
	}

	// ========================================================================================

	public void begin() throws SQLException, Exception {
		begin(this);
	}

	public void begin(ConnectionWrapper connectionWrapper) throws SQLException,
			Exception {
		try {
			connectionWrapper.getConnection().setAutoCommit(false);
			connectionWrapper.addSqlStatement("begin();");
		} catch (SQLException e) {
			throw this.buildSQLExceptionWrapper(e,
					OPERATION_TYPE_BEGIN_TRANSACTION, TITLE_BEGIN_TRANSACTION,
					SUBJECT_BEGIN_TRANSACTION);
		}
	}

	public void commit() throws SQLException, Exception {
		commit(this);
	}

	public void commit(ConnectionWrapper connectionWrapper)
			throws SQLException, Exception {
		try {
			connectionWrapper.getConnection().commit();
			connectionWrapper.addSqlStatement("commit();");
			close(connectionWrapper);
		} catch (SQLException e) {
			throw this.buildSQLExceptionWrapper(e,
					OPERATION_TYPE_COMMIT_TRANSACTION,
					TITLE_COMMIT_TRANSACTION, SUBJECT_COMMIT_TRANSACTION);
		}
	}

	public void rollBack() throws SQLException, Exception {
		rollBack(this);
	}

	public void rollBack(ConnectionWrapper connectionWrapper)
			throws SQLException, Exception {
		try {
			connectionWrapper.getConnection().rollback();
			connectionWrapper.addSqlStatement("rollback();");
			close(connectionWrapper);
		} catch (SQLException e) {
			throw this.buildSQLExceptionWrapper(e,
					OPERATION_TYPE_ROLLBACK_TRANSACTION,
					TITLE_ROLLBACK_TRANSACTION, SUBJECT_ROLLBACK_TRANSACTION);
		}
	}

	public void close() throws SQLException, Exception {
		close(this);
	}

	public void close(ConnectionWrapper connectionWrapper) throws SQLException,
			Exception {
		try {
			if (connectionWrapper != null
					&& connectionWrapper.getConnection() != null
					& connectionWrapper.getConnection().isClosed() == false) {
				connectionWrapper.getConnection().close();
				connectionWrapper.addSqlStatement("close();");
			}
		} catch (SQLException e) {
			throw this.buildSQLExceptionWrapper(e,
					OPERATION_TYPE_CLOSE_CONNECTION, TITLE_CLOSE_CONNECTION,
					SUBJECT_CLOSE_CONNECTION);
		}
	}

	// ===================================================================================================

	public Object[][] findToTable(String sql) throws SQLExceptionWrapper,
			Exception {
		return findToTable(sql, new Object[0]);
	}

	public Object[][] findToTable(String sql, Object... args)
			throws SQLExceptionWrapper {

		try {

			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(sql);

			printSQLWarning(preparedStatement.getWarnings());

			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					set(preparedStatement, args[i], (i + 1));
				}
			}

			sql = preparedStatement.toString();

			addSqlStatement(sql);

			return executeQueryToTable(preparedStatement);

		} catch (SQLException e) {

			throw this.buildSQLExceptionWrapper(e, OPERATION_TYPE_SELECT,
					TITLE_SELECT, SUBJECT_SELECT);
		}

	}

	private Object[][] executeQueryToTable(PreparedStatement preparedStatement)
			throws SQLException {

		List<Object[]> listT = new ArrayList<Object[]>();

		String msg = printSQLStart(preparedStatement);

		ResultSet resultSet = preparedStatement.executeQuery();
		printSQLWarning(resultSet.getWarnings());

		printSQLEnd(msg);

		int c = resultSet.getMetaData().getColumnCount();

		while (resultSet.next()) {
			Object[] row = new Object[c];

			for (int j = 0; j < c; j++) {
				row[j] = resultSet.getObject((j + 1));
			}

			listT.add(row);
		}

		// if (resultSet != null && resultSet.isClosed() == false) {
		// resultSet.close();
		// }

		// if (preparedStatement != null && preparedStatement.isClosed() ==
		// false) {
		// preparedStatement.close();
		// }

		Object[][] table = new Object[listT.size()][c];

		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < c; j++) {
				table[i][j] = listT.get(i)[j];
			}

		}

		return table;
	}

	public int insert(String sql) throws SQLExceptionWrapper {
		return insert(sql, new Object[0]);
	}

	public int insert(String sql, Object... args) throws SQLExceptionWrapper {

		try {

			PreparedStatement preparedStatement = this.getConnection()
					.prepareStatement(sql);
			printSQLWarning(preparedStatement.getWarnings());

			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					set(preparedStatement, args[i], (i + 1));
				}
			}

			sql = preparedStatement.toString();

			this.addSqlStatement(sql);

			return executeUpdateByExample(preparedStatement);

		} catch (SQLException e) {
			throw this.buildSQLExceptionWrapper(e, OPERATION_TYPE_INSERT,
					TITLE_INSERT, SUBJECT_INSERT);
		}
	}

	public int update(String sql) throws SQLExceptionWrapper {
		return insert(sql, new Object[0]);
	}

	public int update(String sql, Object... args) throws SQLExceptionWrapper {

		try {

			PreparedStatement preparedStatement = this.getConnection()
					.prepareStatement(sql);
			printSQLWarning(preparedStatement.getWarnings());

			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					set(preparedStatement, args[i], (i + 1));
				}
			}

			sql = preparedStatement.toString();

			this.addSqlStatement(sql);

			return executeUpdateByExample(preparedStatement);

		} catch (SQLException e) {
			throw this.buildSQLExceptionWrapper(e, OPERATION_TYPE_UPDATE,
					TITLE_UPDATE, SUBJECT_UPDATE);
		}
	}

	private int executeUpdateByExample(PreparedStatement preparedStatement)
			throws SQLException {

		String msg = printSQLStart(preparedStatement);

		int r = preparedStatement.executeUpdate();

		printSQLEnd(msg);

		// if (preparedStatement.isClosed() == false) {
		// preparedStatement.close();
		// }

		return r;
	}

	// ===================================================================================================

	private String printSQLStart(PreparedStatement preparedStatement) {

		return printSQLStart(preparedStatement.toString());
	}

	private String printSQLStart(String sql) {

		if (sql.trim().endsWith(";") == false) {
			sql += ";";
		}

		if (isVerbose()) {
			return "\n\n[..] Ejecutando SQL " + ZonedDateTime.now() + "\n\n"
					+ sql;
		}

		return null;
	}

	private void printSQLEnd(String msgSql) {
		if (isVerbose()) {
			msgSql += "\n\n[OK] SQL ejecutando " + ZonedDateTime.now() + "\n\n";

			errorPrinter.print(this.getClass().getName(),
					LogPrinter.LEVEL_INFO, msgSql);
		}
	}

	private void printSQLWarning(SQLWarning sqlWarning) {

		String msg = "\n\nSQL WARNING " + ZonedDateTime.now() + "\n\n";

		String msg2 = "";

		while (sqlWarning != null) {

			msg2 += "Warning : " + sqlWarning.getErrorCode() + " Message : "
					+ sqlWarning.getMessage() + " SQL state "
					+ sqlWarning.getSQLState() + "\n";

			sqlWarning = sqlWarning.getNextWarning();
		}

		msg += "\n\nEND SQL WARNING " + ZonedDateTime.now() + "\n\n";

		if (msg2 != null && msg2.isEmpty() == false) {
			errorPrinter.print(this.getClass().getName(),
					LogPrinter.LEVEL_WARN, msg);
		}
	}

	@SuppressWarnings("rawtypes")
	private void set(PreparedStatement preparedStatement, Object value,
			Integer i) throws SQLException {

		if (value != null) {

			if (value instanceof String) {

				preparedStatement.setString(i, (String) value);

			} else if (value instanceof Boolean) {

				preparedStatement.setBoolean(i, (Boolean) value);

			} else if (value instanceof Short) {

				preparedStatement.setShort(i, (Short) value);

			} else if (value instanceof Integer) {

				preparedStatement.setInt(i, (Integer) value);

			} else if (value instanceof Long) {

				preparedStatement.setLong(i, (Long) value);

			} else if (value instanceof Float) {

				preparedStatement.setFloat(i, (Float) value);

			} else if (value instanceof Double) {

				preparedStatement.setDouble(i, (Double) value);

			} else if (value instanceof BigDecimal) {

				preparedStatement.setBigDecimal(i, (BigDecimal) value);

			} else if (value instanceof Date) {

				preparedStatement.setDate(i, (Date) value);

			} else if (value instanceof java.util.Date) {
				
				Date sqlDate = new Date(((java.util.Date) value).getTime());

				preparedStatement.setDate(i, sqlDate);

			} else if (value instanceof Timestamp) {

				preparedStatement.setTimestamp(i, (Timestamp) value);

			} else if (value instanceof Time) {

				preparedStatement.setTime(i, (Time) value);

			} else if (value instanceof Class) {

				Class c = (Class) value;

				if (c.equals(String.class)) {
					preparedStatement.setNull(i, Types.VARCHAR);
				} else if (c.equals(Boolean.class)) {
					preparedStatement.setNull(i, Types.BOOLEAN);
				} else if (c.equals(Short.class)) {
					preparedStatement.setNull(i, Types.SMALLINT);
				} else if (c.equals(Integer.class)) {
					preparedStatement.setNull(i, Types.INTEGER);
				} else if (c.equals(Long.class)) {
					preparedStatement.setNull(i, Types.BIGINT);
				} else if (c.equals(Float.class)) {
					preparedStatement.setNull(i, Types.REAL);
				} else if (c.equals(Double.class)) {
					preparedStatement.setNull(i, Types.FLOAT);
				} else if (c.equals(BigDecimal.class)) {
					preparedStatement.setNull(i, Types.NUMERIC);
				} else if (c.equals(Date.class)) {
					preparedStatement.setNull(i, Types.DATE);
				} else if (c.equals(java.util.Date.class)) {
					preparedStatement.setNull(i, Types.DATE);
				} else if (c.equals(Timestamp.class)) {
					preparedStatement.setNull(i, Types.TIMESTAMP);
				} else if (c.equals(Time.class)) {
					preparedStatement.setNull(i, Types.TIME);
				} else {
					throw new IllegalArgumentException(MSG_1.replace(
							"${value}", value.toString()));
				}

			} else {				
				throw new IllegalArgumentException(MSG_1.replace("${value}", value.toString()).replace("${class}", value.getClass().getCanonicalName()));
			}
		}
	}

	private SQLExceptionWrapper buildSQLExceptionWrapper(
			SQLException sQLException, String operationType, String title,
			String subject) {

		SQLExceptionWrapper sQLExceptionWrapper = new SQLExceptionWrapper(
				sQLException);

		sQLExceptionWrapper.setTitle(title);
		sQLExceptionWrapper.setSubject(subject);

		sQLExceptionWrapper.setOperationType(operationType);

		if (this.dataSourceProperties != null) {
			sQLExceptionWrapper.setDriverClassName(dataSourceProperties
					.getDriverClassName());
			sQLExceptionWrapper.setInitialSize(dataSourceProperties
					.getInitialSize());
			sQLExceptionWrapper.setMaxActive(dataSourceProperties
					.getMaxActive());
			sQLExceptionWrapper.setMaxIdle(dataSourceProperties.getMaxIdle());
			sQLExceptionWrapper.setValidationQuery(dataSourceProperties
					.getValidationQuery());
		}

		if (dataSourceMetaData != null) {
			sQLExceptionWrapper.setDatabaseProductName(dataSourceMetaData
					.getDatabaseProductName());
			sQLExceptionWrapper.setDatabaseProductVersion(dataSourceMetaData
					.getDatabaseProductVersion());
			sQLExceptionWrapper.setDriverName(dataSourceMetaData
					.getDriverName());
			sQLExceptionWrapper.setDriverVersion(dataSourceMetaData
					.getDriverVersion());
			sQLExceptionWrapper.setjDBCMajorVersion(dataSourceMetaData
					.getjDBCMajorVersion());
			sQLExceptionWrapper.setjDBCMinorVersion(dataSourceMetaData
					.getjDBCMinorVersion());
			sQLExceptionWrapper.setUrl(dataSourceMetaData.getUrl());
			sQLExceptionWrapper.setUserName(dataSourceMetaData.getUserName());

		} else if (this.dataSourceProperties != null) {
			sQLExceptionWrapper.setUrl(dataSourceProperties.getUrl());
			sQLExceptionWrapper.setUserName(dataSourceProperties.getUserName());
		}

		sQLExceptionWrapper.setSqlStatements(sqlStatements);

		return sQLExceptionWrapper;

	}

}
