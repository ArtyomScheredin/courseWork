package spbstu.deans_office.DTO;

public record MarkDTO(long markId,
                      long studentId,
                      long subjectId,
                      long teacherId,
                      int value) {
}
