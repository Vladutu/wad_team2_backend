package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.builder.IStudentBuilder;
import ro.ucv.ace.dto.student.ESStudentDto;
import ro.ucv.ace.dto.student.StudentDto;
import ro.ucv.ace.model.Student;
import ro.ucv.ace.model.Subgroup;
import ro.ucv.ace.repository.ISubgroupRepository;
import ro.ucv.ace.repository.IUserRepository;
import ro.ucv.ace.repository.impl.StudentRepository;
import ro.ucv.ace.service.IStudentService;
import ro.ucv.ace.visitor.StudentVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tzapt on 11/20/2016.
 */
@Service("studentService")
@Transactional(rollbackFor = Exception.class)
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IStudentBuilder studentBuilder;

    @Autowired
    private StudentVisitor studentVisitor;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private ISubgroupRepository subgroupRepository;

    @Override
    public StudentDto save(ESStudentDto studentDto) {

        Integer index = 1;
        String username = (studentDto.getFirstName().substring(0, index) + studentDto.getLastName()).toLowerCase();
        Integer ssnLength = studentDto.getSsn().length();
        String password = studentDto.getSsn().substring(ssnLength - 4, ssnLength);

        while (userRepository.usernameExists(username)) {
            username = (studentDto.getFirstName().substring(0, ++index) + studentDto.getLastName()).toLowerCase();
        }

        Subgroup subgroup = subgroupRepository.findByName(studentDto.getSubgroup());

        Student student = studentRepository.save(studentBuilder.build(studentDto, username, password, subgroup));

        mailService.sendAccountCreationMail(studentDto.getEmail(), username, password);

        student.accept(studentVisitor);

        return studentVisitor.getStudentDto();
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        students.forEach(s -> {
            s.accept(studentVisitor);
            studentDtos.add(studentVisitor.getStudentDto());
        });

        return studentDtos;
    }

    @Override
    public StudentDto delete(int id) {
        Student student = studentRepository.delete(id);
        student.accept(studentVisitor);

        return studentVisitor.getStudentDto();
    }

    @Override
    public StudentDto edit(int id, ESStudentDto studentDto) {
        Student student = studentRepository.findOne(id);

        Subgroup subgroup = subgroupRepository.findByName(studentDto.getSubgroup());

        student.update(studentDto.getFirstName(), studentDto.getLastName(), studentDto.getSsn(),
                studentDto.getEmail(), studentDto.getGender(), subgroup);

        student.accept(studentVisitor);

        return studentVisitor.getStudentDto();
    }
}
