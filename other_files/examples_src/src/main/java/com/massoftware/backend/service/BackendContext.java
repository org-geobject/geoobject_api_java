package com.massoftware.backend.service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.cendra.commons.AbstractContext;
import org.cendra.commons.util.dao.jdbc.DataSourceProperties;
import org.cendra.commons.util.dao.jdbc.DataSourceWrapper;
import org.cendra.commons.util.dao.jdbc.SQLExceptionWrapper;
import org.cendra.commons.util.error.LogPrinter;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.massoftware.backend.bo.ejerciciocontable.EjercicioContableBO;
import com.massoftware.backend.bo.ejerciciocontable.IEjercicioContableBO;
import com.massoftware.backend.dao.ejerciciocontable.EjercicioContableDAO;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class BackendContext extends AbstractContext {

	private DataSourceProperties dataSourceProperties;
	private DataSource dataSource;
	private DataSourceWrapper dataSourceWrapper;

	public BackendContext() {
		super();
		try {
			// init("Postgresql");
			init("sqlserver");

		} catch (Exception e) {
			e.printStackTrace();
			new LogPrinter().print(AbstractContext.class.getName(),
					LogPrinter.LEVEL_FATAL, e);

		}
	}

	private void init(String dbType) throws JsonGenerationException,
			JsonMappingException, SQLExceptionWrapper, IOException,
			SQLServerException {

		if (dbType.equals("Postgresql")) {
			initContextDbPostgreSql(new LogPrinter());
		} else if (dbType.equals("sqlserver")) {
			initContextDbMsSqlServer(new LogPrinter());
		}

		String msg = "\n\n[Ok] " + ZonedDateTime.now()
				+ " Services Server Context start !!!\n\n";

		new LogPrinter().print(this.getClass().getName(),
				LogPrinter.LEVEL_INFO, msg);
	}

	private void initContextDbMsSqlServer(LogPrinter errorPrinter)
			throws SQLExceptionWrapper, JsonGenerationException,
			JsonMappingException, IOException, SQLServerException {

		SQLServerDataSource ds = new SQLServerDataSource();
		// ds.setIntegratedSecurity(true);
		ds.setServerName("localhost");
		ds.setPortNumber(1433);
		ds.setDatabaseName("VetaroRep");
		ds.setUser("sa");
		ds.setPassword("cordoba");

		Properties properties = new Properties();

		properties.put("jdbc.driverClassName",
				"com.microsoft.sqlserver.jdbc.SQLServerDriver");
		properties.put("jdbc.userName", "");
		properties.put("jdbc.userPassword", "");
		properties.put("jdbc.url", ds.getURL());
		properties.put("jdbc.maxActive", "10");
		properties.put("jdbc.initialSize", "5");
		properties.put("jdbc.maxIdle", "5");
		properties.put("jdbc.validationQuery", "SELECT 1");
		properties.put("jdbc.verbose", "true");

		String path = "jdbc.";

		dataSourceProperties = new DataSourceProperties();

		dataSourceProperties.setDriverClassName(properties.getProperty(path
				+ "driverClassName"));
		dataSourceProperties.setUrl(properties.getProperty(path + "url"));
		dataSourceProperties.setUserName(properties.getProperty(path
				+ "userName"));
		dataSourceProperties.setUserPassword(properties.getProperty(path
				+ "userPassword"));
		dataSourceProperties.setInitialSize(new Integer(properties
				.getProperty(path + "initialSize")));
		dataSourceProperties.setMaxActive(new Integer(properties
				.getProperty(path + "maxActive")));
		dataSourceProperties.setMaxIdle((new Integer(properties
				.getProperty(path + "maxIdle"))));
		dataSourceProperties.setValidationQuery(properties.getProperty(path
				+ "validationQuery"));
		dataSourceProperties.setVerbose((new Boolean(properties
				.getProperty(path + "verbose"))));

		ObjectMapper mapper = new ObjectMapper();
		String msg = "\n\n[..] Conectandose a \n\n"
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
						dataSourceProperties);

		errorPrinter.print(this.getClass().getName(), LogPrinter.LEVEL_INFO,
				msg);

		// BasicDataSource basicDataSource = new BasicDataSource();
		// basicDataSource.setDriverClassName(dataSourceProperties
		// .getDriverClassName());
		// basicDataSource.setUrl(dataSourceProperties.getUrl());
		// basicDataSource.setUsername(dataSourceProperties.getUserName());
		// basicDataSource.setPassword(dataSourceProperties.getUserPassword());
		// basicDataSource.setInitialSize(dataSourceProperties.getInitialSize());
		// basicDataSource.setMaxActive(dataSourceProperties.getMaxActive());
		// basicDataSource.setMaxIdle(dataSourceProperties.getMaxIdle());
		// basicDataSource.setValidationQuery(dataSourceProperties
		// .getValidationQuery());

		dataSource = ds;

		dataSourceWrapper = new DataSourceWrapper(dataSource,
				dataSourceProperties, new LogPrinter());

	}

	private void initContextDbPostgreSql(LogPrinter errorPrinter)
			throws SQLExceptionWrapper, JsonGenerationException,
			JsonMappingException, IOException {

		Properties properties = new Properties();

		properties.put("jdbc.driverClassName", "org.postgresql.Driver");
		properties.put("jdbc.userName", "postgres");
		properties.put("jdbc.userPassword", "cordoba");
		properties
				.put("jdbc.url",
						"jdbc:postgresql://localhost:5432/massoftware?searchpath=massoftware");
		properties.put("jdbc.maxActive", "10");
		properties.put("jdbc.initialSize", "5");
		properties.put("jdbc.maxIdle", "5");
		properties.put("jdbc.validationQuery", "SELECT 1");
		properties.put("jdbc.verbose", "true");

		String path = "jdbc.";

		dataSourceProperties = new DataSourceProperties();

		dataSourceProperties.setDriverClassName(properties.getProperty(path
				+ "driverClassName"));
		dataSourceProperties.setUrl(properties.getProperty(path + "url"));
		dataSourceProperties.setUserName(properties.getProperty(path
				+ "userName"));
		dataSourceProperties.setUserPassword(properties.getProperty(path
				+ "userPassword"));
		dataSourceProperties.setInitialSize(new Integer(properties
				.getProperty(path + "initialSize")));
		dataSourceProperties.setMaxActive(new Integer(properties
				.getProperty(path + "maxActive")));
		dataSourceProperties.setMaxIdle((new Integer(properties
				.getProperty(path + "maxIdle"))));
		dataSourceProperties.setValidationQuery(properties.getProperty(path
				+ "validationQuery"));
		dataSourceProperties.setVerbose((new Boolean(properties
				.getProperty(path + "verbose"))));

		ObjectMapper mapper = new ObjectMapper();
		String msg = "\n\n[..] Conectandose a \n\n"
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
						dataSourceProperties);

		errorPrinter.print(this.getClass().getName(), LogPrinter.LEVEL_INFO,
				msg);

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(dataSourceProperties
				.getDriverClassName());
		basicDataSource.setUrl(dataSourceProperties.getUrl());
		basicDataSource.setUsername(dataSourceProperties.getUserName());
		basicDataSource.setPassword(dataSourceProperties.getUserPassword());
		basicDataSource.setInitialSize(dataSourceProperties.getInitialSize());
		basicDataSource.setMaxActive(dataSourceProperties.getMaxActive());
		basicDataSource.setMaxIdle(dataSourceProperties.getMaxIdle());
		basicDataSource.setValidationQuery(dataSourceProperties
				.getValidationQuery());

		dataSource = basicDataSource;

		dataSourceWrapper = new DataSourceWrapper(dataSource,
				dataSourceProperties, new LogPrinter());

	}

	// -------------------------------------------------------------

	public IEjercicioContableBO buildEjercicioContableBO() {

		try {
			return new EjercicioContableBO(new EjercicioContableDAO(
					dataSourceWrapper));
		} catch (Exception e) {
			e.printStackTrace();
			new LogPrinter().print(AbstractContext.class.getName(),
					LogPrinter.LEVEL_FATAL, e);
		}

		return null;

	}

}
