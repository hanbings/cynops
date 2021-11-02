package io.hanbings.cynops.database;

import io.hanbings.cynops.database.interfaces.NotNull;
import io.hanbings.cynops.database.interfaces.PrimaryKey;
import io.hanbings.cynops.database.interfaces.SqliteDataTable;
import io.hanbings.cynops.database.interfaces.Unique;
import io.hanbings.cynops.database.sqlite.SqliteConnector;
import io.hanbings.cynops.database.sqlite.SqliteSqlBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DataBaseTest {
    @SqliteDataTable(table = "test")
    class Test {
        @PrimaryKey
        long uuid;
        @NotNull
        String name;
        @Unique
        @NotNull
        String nike;

        public Test(long uuid, String name, String nike) {
            this.uuid = uuid;
            this.name = name;
            this.nike = nike;
        }

        public long getUuid() {
            return uuid;
        }

        public void setUuid(long uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNike() {
            return nike;
        }

        public void setNike(String nike) {
            this.nike = nike;
        }
    }

    public static void main(String[] args) {
        String sql = SqliteSqlBuilder.createTable(Test.class);
        System.out.println(sql);
        Connection connection = SqliteConnector.getSQLiteConnection("./test.db");
        Statement statement = null;
        try {
            statement = Objects.requireNonNull(connection).createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = SqliteSqlBuilder.deleteTable(Test.class);
        try {
            Objects.requireNonNull(statement).execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
