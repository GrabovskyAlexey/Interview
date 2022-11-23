package ru.grabovsky.interview.database;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DbHandler.conn();
//        DbHandler.createDB();
//        DbHandler.writeDB();
        DbHandler.readDB();
        DbHandler.closeDB();
    }
}
