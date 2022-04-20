package spbstu.deans_office.utils;

public class Utils {
    public final static int MARK_VALUE_LOWER_BOUND = 0;
    public final static int MARK_VALUE_HIGHER_BOUND = 5;

    public final static String WRONG_STUDENT_ID = "WRONG student id. Can't find student with id ";
    public final static String WRONG_STUDENT_NAME = "WRONG student name. Can't find student with name ";
    public  final static String WRONG_TEACHER_ID = "WRONG teacher id. Can't find teacher with id ";
    public final static String WRONG_SUBJECT_ID = "WRONG subject id. Can't find subject with id ";
    public final static String WRONG_SUBJECT_NAME = "WRONG subject name. Can't find subject with name ";
    public final static String WRONG_MARK_ID = "WRONG mark id. Can't find mark with id ";
    public final static String WRONG_GROUP_ID = "WRONG group id. Can't find group with id ";
    public final static String WRONG_GROUP_NAME = "WRONG group name. Can't find group with name ";
    public final static String WRONG_PERSON_ID = "WRONG person id. Can't find person with id ";
    public final static String WRONG_VALUE = "WRONG value. Value must be between "
                                             + MARK_VALUE_LOWER_BOUND + " to " + MARK_VALUE_HIGHER_BOUND + ". Actual is";

    public static record Pair<T, K>(T first, K second) {
    }
}