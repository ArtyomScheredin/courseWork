package spbstu.deans_office.DTO;

public record PersonDTO(Long person_id, String first_name, String last_name,
                        String patronymic, long group_id, Character type) {
}
