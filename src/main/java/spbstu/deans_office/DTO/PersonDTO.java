package spbstu.deans_office.DTO;

public record PersonDTO(Long personId,
                        String firstName,
                        String lastName,
                        String patronymic,
                        long groupId,
                        Character type) {

}
