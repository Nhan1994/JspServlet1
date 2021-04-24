package com.testservlet.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.testservlet.dao.GenericDAO;
import com.testservlet.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("connection");

	public Connection getConnection() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/new-servlet";
//			return DriverManager.getConnection(url, "root", "admin");
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			return DriverManager.getConnection(url, resourceBundle.getString("username"), resourceBundle.getString("password"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... paramters) {
		List<T> result = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			setParameters(preparedStatement, paramters);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.add(rowMapper.mapRow(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				return null;
			}
		}
		return result;
	}

	private void setParameters(PreparedStatement statement, Object... params) {
		try {
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				int index = i + 1;
				if (param instanceof Long) {
					statement.setLong(index, ((Long) param).longValue());
				} else if (param instanceof String) {
					statement.setString(index, ((String) param));
				} else if (param instanceof Integer) {
					statement.setInt(index, ((Integer) param));
				} else if (param instanceof Timestamp) {
					statement.setTimestamp(index, ((Timestamp) param));
				} else if (param == null) {
					statement.setNull(index, Types.NULL);
				} else if (param instanceof Integer) {
					statement.setInt(index, ((Integer) param));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			setParameters(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {

			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long id = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			return null;
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				return null;
			}
		}
	}

	@Override
	public void delete(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			setParameters(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return count;
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				return count;
			}
		}
		return count;
	}
}
