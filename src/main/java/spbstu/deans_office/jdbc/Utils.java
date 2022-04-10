package spbstu.deans_office.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
 /*   public static void printMarksDistributionOnGroups(Connection connection,
                                                      int precision) throws SQLException {
        String sql = "SELECT g.name, ROUND(AVG(m.value), ?)\n" +
                     "FROM mark AS m\n" +
                     "         JOIN person AS p ON m.student_id = p.id\n" +
                     "         JOIN "group" AS g ON g.group_id = p.group_id\n" +
                     "GROUP BY g.group_id;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, precision);
            try (ResultSet rs = statement.executeQuery()) {
                System.out.println("NAME\t\tVALUE");
                while (rs.next()) {
                    System.out.println(rs.getString("name") + "\t\t" + rs.getString("round" +
                                                                                    ""));
                }
            }
        }
    }

    public static void printStudentsWithGoodMarks(Connection connection) throws SQLException {
        String sql = "SELECT p.first_name, p.last_name, ROUND(AVG(m.value), 2) AS average_rate\n" +
                     "FROM person AS p\n" +
                     "         JOIN mark AS m\n" +
                     "              ON p.id = m.id\n" +
                     "WHERE p.type = 's'\n" +
                     "GROUP BY p.id\n" +
                     "HAVING AVG(m.value) < 4.5 AND AVG(m.value) >= 3.5;";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            System.out.println("first_name\t\tlast_name\t\taverage_rate");
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + "\t\t" + rs.getString("last_name")
                                   + "\t\t" + rs.getString("average_rate"));
            }
        }
    }

    public static void insertMark(Connection connection, int studentID, int subjectID, int teacherID, int value) throws SQLException {
        String sql = "INSERT INTO mark (student_id, subject_id, teacher_id, value)\n" +
                     "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            statement.setInt(2, subjectID);
            statement.setInt(3, teacherID);
            statement.setInt(4, value);
            statement.executeUpdate();
        }
        System.out.println("Insertion has been completed succesfully!");
    }
*/
}
