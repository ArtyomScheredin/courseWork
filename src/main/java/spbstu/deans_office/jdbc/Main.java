package spbstu.deans_office.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spbstu.deans_office.jdbc.Utils.insertMark;
import static spbstu.deans_office.jdbc.Utils.printMarksDistributionOnGroups;
import static spbstu.deans_office.jdbc.Utils.printStudentsWithGoodMarks;

public class Main {
    final static String dbURL = "jdbc:postgresql://localhost:5432/deans_office";
    final static String user = "Artyom";
    final static String pwd = "123";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, user, pwd);
        if (connection.isValid(1)) {
            System.out.println("");
        }

            printMarksDistributionOnGroups(connection, 3);
            System.out.println('\n');
            printStudentsWithGoodMarks(connection);
            System.out.println('\n');
            insertMark(connection, 291,7,202, 4);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

