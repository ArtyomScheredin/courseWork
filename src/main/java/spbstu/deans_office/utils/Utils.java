package spbstu.deans_office.utils;

public class Utils {
    public final static String WRONG_STUDENT_ID_MESSAGE = "WRONG student id. Can't find student with id ";
    public  final static String WRONG_TEACHER_ID_MESSAGE = "WRONG teacher id. Can't find teacher with id ";
    public final static String WRONG_SUBJECT_ID_MESSAGE = "WRONG subject id. Can't find subject with id ";
    public final static String WRONG_MARK_ID_MESSAGE = "WRONG subject id. Can't find subject with id ";
    public final static String WRONG_GROUP_ID_MESSAGE = "WRONG group id. Can't find group with id ";
    public final static String WRONG_PERSON_ID_MESSAGE = "WRONG person id. Can't find person with id ";

    public final static int MARK_VALUE_LOWER_BOUND = 0;
    public final static int MARK_VALUE_HIGHER_BOUND = 5;

    public static record Pair<T, K>(T first, K second) {
    }
}