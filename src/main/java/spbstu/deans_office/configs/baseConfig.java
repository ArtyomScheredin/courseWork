package spbstu.deans_office.configs;


import spbstu.deans_office.DTO.MarkDTO;
import spbstu.deans_office.DTO.PersonDTO;
import spbstu.deans_office.models.Mark;
import spbstu.deans_office.services.MarkService;
import spbstu.deans_office.services.PersonService;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class baseConfig {
    private static final int PRINT_LIMIT = 10;

    private final MarkService markService;
    private final PersonService personService;

    @Autowired
    public baseConfig(MarkService markService, PersonService personService) {
        this.markService = markService;
        this.personService = personService;
    }

/*
    @Bean
    public CommandLineRunner TestMarkService() {
        return args -> {
            checkCommand(markService::getMarksByGroup, "459195980-5", "getMarksByGroup");
            checkCommand(markService::getMarksByStudentSecondName,
                    "Cervantes", "getMarksByStudentSecondName");
            checkCommand(markService::getMarksBySubject, "Art", "getMarksBySubject");
            checkCommand(markService::getMarksByTeacher, 202L, "getMarksByTeacher");
            checkCommandReturningMap(markService::getAvgForGroups, "getAvgForGroups");
            checkCommandReturningMap(markService::getAvgForSubjects, "getAvgForSubjects");
            checkCommandReturningMap(markService::getMarksAVGByStudentOnAllSubjects,
                    201L, "getMarksAVGByStudentOnAllSubjects");
            markService.addMark(new MarkDTO(500L,201,3,202,3));
        };
    }

    @Bean
    public CommandLineRunner TestPersonService() {
        return args -> {
            checkCommand(personService::getStudentOrderedByAVGMark,"getStudentOrderedByAVGMark");
            checkCommand(personService::getPeopleByGroupId, 5L,"getPeopleByGroupId");
            checkCommand(personService::getPeopleWithMark, 2,"getPeopleWithMark");
            checkCommandReturningMap(personService::getAVGForStudents, "getAVGForStudents");
            checkCommandReturningMap(personService::getAVGForTeachers, "getAVGForTeachers");
            //personService.deleteIfAVGMarkLessThan(2.19);
            //personService.deletePersonWithMinimalAVGMark();
            //personService.addPerson(new PersonDTO(null,"Carl","Johnson","Sergeevish",2,'s'));
            //personService.updatePerson(new PersonDTO(306L,"Carl","Johnson","Sergeevish",3,'t'));
        };
    }
*/

    private static <T, K, R extends Collection<K>> void checkCommand(Function<T, R> func, T arg, String methodName) {
        System.out.println(methodName + '\n' + arg);
        R result = func.apply(arg);
        result.stream().limit(PRINT_LIMIT).forEach(System.out::println);
        System.out.println("\n\n");
    }

    private static <R extends Collection<?>> void checkCommand(Supplier<R> func, String methodName) {
        System.out.println(methodName + '\n');
        R result = func.get();
        result.stream().limit(PRINT_LIMIT).forEach(System.out::println);
        System.out.println("\n\n");
    }

    private static <K, V> void checkCommandReturningMap(Supplier<Map<K, V>> func, String methodName) {
        System.out.println(methodName + '\n');
        Map<K, V> result = func.get();
        result.entrySet().stream().limit(PRINT_LIMIT).forEach(System.out::println);
        System.out.println("\n\n");
    }

    private static <T, K, V> void checkCommandReturningMap(Function<T, Map<K, V>> func, T arg, String methodName) {
        System.out.println(methodName + '\n' + arg);
        Map<K, V> result = func.apply(arg);
        result.entrySet().stream().limit(PRINT_LIMIT).forEach(System.out::println);
        System.out.println("\n\n");
    }
}
