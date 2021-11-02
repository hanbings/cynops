package io.hanbings.cynops.database;

import io.hanbings.cynops.database.interfaces.SqliteData;
import io.hanbings.cynops.database.interfaces.SqliteDataTable;
import io.hanbings.cynops.database.interfaces.SqliteDataType;
import io.hanbings.cynops.database.sqlite.SqliteSqlBuilder;

@SuppressWarnings("unused")
public class DataBaseTest {
    @SqliteDataTable(table = "test")
    class Test {
        @SqliteData
        long uuid;
        @SqliteData(type = SqliteDataType.TEXT, isPrimaryKey = true)
        String name;
        @SqliteData(type = SqliteDataType.TEXT)
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
    }
}
