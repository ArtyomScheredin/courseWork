package spbstu.deans_office.DTO;

public record PersonDTO(long person_id, String first_name, String last_name,
                        String patronymic, long group_id, Character type) {
}
