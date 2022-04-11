package spbstu.deans_office.configs;


import spbstu.deans_office.DTO.MarkDTO;
import spbstu.deans_office.models.Group;
import spbstu.deans_office.models.Person;
import spbstu.deans_office.models.Subject;
import spbstu.deans_office.repositories.GroupRepository;
import spbstu.deans_office.repositories.PersonRepository;
import spbstu.deans_office.repositories.SubjectRepository;
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
public class TestCommands {
    private static final int PRINT_LIMIT = 10;

    private final MarkService markService;
    private final PersonService personService;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final PersonRepository personRepository;

    @Autowired
    public TestCommands(MarkService markService,
                        PersonService personService,
                        GroupRepository groupRepository,
                        SubjectRepository subjectRepository,
                        PersonRepository personRepository) {
        this.markService = markService;
        this.personService = personService;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.personRepository = personRepository;
    }



    @Bean
    public CommandLineRunner TestMarkService() {
        Group group = groupRepository.findAll().iterator().next();
        Person student = personRepository.findAllByType('s').get(0);
        Person teacher = personRepository.findAllByType('t').get(0);
        Subject subject = subjectRepository.findAll().iterator().next();
        System.out.println(group.toString() + '\n' + student + '\n' + teacher + '\n' + subject + '\n');

        return args -> {
            checkCommand(markService::getMarksByGroup, group.getName(), "getMarksByGroup");
            checkCommand(markService::getMarksByStudentSecondName, student.getLastName(), "getMarksByStudentSecondName");
            checkCommand(markService::getMarksBySubject, subject.getName(), "getMarksBySubject");
            checkCommand(markService::getMarksByTeacher, teacher.getPersonId(), "getMarksByTeacher");
            checkCommandReturningMap(markService::getAvgForGroups, "getAvgForGroups");
            checkCommandReturningMap(markService::getAvgForSubjects, "getAvgForSubjects");
            checkCommandReturningMap(markService::getMarksAVGByStudentOnAllSubjects,
                    201L, "getMarksAVGByStudentOnAllSubjects");
            markService.addMark(new MarkDTO(500L,student.getPersonId(),subject.getSubjectId(),teacher.getPersonId(),3));
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
