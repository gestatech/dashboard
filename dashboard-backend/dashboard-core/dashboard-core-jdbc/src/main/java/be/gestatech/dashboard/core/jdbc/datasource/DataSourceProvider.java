package be.gestatech.dashboard.core.jdbc.datasource;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by amurifa on 28/06/2017.
 */
public abstract class DataSourceProvider {

	private final Logger LOGGER = Logger.getLogger(DataSourceProvider.class.getName());

	@Resource(name = "GST_DASHBOARD_DATASOURCE")
	private DataSource dataSource;

	public DataSourceProvider() {
	}

	public final void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected final DataSource getDataSource() {
		return dataSource;
	}
}
