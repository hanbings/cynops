package io.hanbings.cynops.database;

import io.hanbings.cynops.database.interfaces.SqliteData;
import io.hanbings.cynops.database.interfaces.SqliteDataTable;
import io.hanbings.cynops.database.interfaces.SqliteDataType;
import io.hanbings.cynops.database.sqlite.SqliteSqlBuilder;

@SuppressWarnings("unused")
public class DataBaseTest {
    @SqliteDataTable(table = "test", isToUpper = true)
    static class Test {
        @SqliteData
        private long uuid;
        @SqliteData(type = SqliteDataType.TEXT, isPrimaryKey = true)
        private String name;
        @SqliteData(type = SqliteDataType.TEXT, isUnique = true)
        private String nike;
        @SqliteData(type = SqliteDataType.REAL, isNotNull = true)
        private boolean test;

        public Test(long uuid, String name, String nike, boolean test) {
            this.uuid = uuid;
            this.name = name;
            this.nike = nike;
            this.test = test;
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

        public boolean isTest() {
            return test;
        }

        public void setTest(boolean test) {
            this.test = test;
        }
    }

    public static void main(String[] args) {
        long begin = System.nanoTime();
        String sql = SqliteSqlBuilder.createTable(Test.class);
        System.out.println(sql);
        long end = System.nanoTime();
        System.out.println("[use time]: " + (end - begin) + " ns");

        begin = System.nanoTime();
        sql = SqliteSqlBuilder.deleteTable(Test.class);
        System.out.println(sql);
        end = System.nanoTime();
        System.out.println("[use time]: " + (end - begin) + " ns");

        Test test = new Test(3219065882L, "hanbings", "寒冰", true);

        begin = System.nanoTime();
        sql = SqliteSqlBuilder.create(test);
        System.out.println(sql);
        end = System.nanoTime();
        System.out.println("[use time]: " + (end - begin) + " ns");

        begin = System.nanoTime();
        sql = SqliteSqlBuilder.update(test);
        System.out.println(sql);
        end = System.nanoTime();
        System.out.println("[use time]: " + (end - begin) + " ns");

        begin = System.nanoTime();
        sql = SqliteSqlBuilder.read(test);
        System.out.println(sql);
        end = System.nanoTime();
        System.out.println("[use time]: " + (end - begin) + " ns");

        begin = System.nanoTime();
        sql = SqliteSqlBuilder.delete(test);
        System.out.println(sql);
        end = System.nanoTime();
        System.out.println("[use time]: " + (end - begin) + " ns");
    }
}
