package org.fastcampus.student_management.application.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;
import org.fastcampus.student_management.ui.student.StudentController;

/*1. 일별 수업 정보 반환:
        - 요일에 해당하는 수업들을 반환해야함
        - 요일 입력은 MONDAY, TUESDAY 와 같은 식으로 콘솔로 입력 받을 수 있어야 함
    - 단, 학생 상태가 비활성화 상태이면 수업을 반환해서는 안됨
2. 수강생들의 상태를 변경 할 수 있음:
        - 학생이 활성 상태 일 때에는 일별 수업에서 포함이 되어야 함
    - 학생이 비활성 상태일 때에는, 일별 수업에 포함되서는 안 됨
    - 활동 상태에서 활동 상태로, 비활성 상태에서 비활성 상태로 변경이 되어서는 안 됨
3. 수강생들의 수강료를 변경 할 수 있음:
        - 특정 학생의 수강료를 변경 시키면 특정 학생 수업에 전체에 적용이 되어야 함*/

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
//    요일에 해당하는 수업들을 반환해야함
    List<Course> courses = courseRepository.getCourseDayOfWeek(dayOfWeek);
    return courses.stream().map(CourseInfoDto::new).toList();
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    List<Course> courses = courseRepository.getCourseListByStudent(studentName);
    CourseList courseList = new CourseList(courses);
    courseList.changeAllCoursesFee(fee);
    }
  }
