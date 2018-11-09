package ru.springmvchibernate.dao.executor;

import java.sql.ResultSet;
import java.sql.SQLException;


@FunctionalInterface
public interface ExecutorHelper<T> {
	T help(ResultSet set) throws SQLException;
}
