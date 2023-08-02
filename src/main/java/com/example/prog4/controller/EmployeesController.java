package com.example.prog4.controller;

import com.example.prog4.entity.EmployeeEntity;
import com.example.prog4.service.CsvFileGenerator;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {
  private final EmployeeService employeeService;
  private final CsvFileGenerator csvFileGenerator;

//@GetMapping("/download")
//public void downloadEmployees(HttpServletResponse response){
//  Iterable<EmployeeEntity> employees;
//  employees = employeeService.findAll(entitySpec);
//    csvFileGenerator.writeEmployeesToCsv((List<EmployeeEntity>) employees, response);
//
//}

  @GetMapping
  public String getAllEmployees(
      @Join(path = "phoneNumbers" ,alias = "p")
      @And({
          @Spec(path = "firstname",params = "firstname",spec = LikeIgnoreCase.class),
          @Spec(path = "lastname", params = "lastname",spec = LikeIgnoreCase.class),
          @Spec(path = "sex", params = "sex",spec = LikeIgnoreCase.class),
          @Spec(path = "position", params = "position",spec = LikeIgnoreCase.class),
          @Spec(path = "hireDate", params = "hire",spec = GreaterThanOrEqual.class),
          @Spec(path = "resignationDate", params = "resignation",spec = GreaterThanOrEqual.class),
          @Spec(path = "p.phoneNumber",params = "phone",spec = LikeIgnoreCase.class),
          @Spec(path = "p.countryCode",params = "code",spec = Like.class)
      })
      Specification<EmployeeEntity> entitySpec, Model model) {

    Iterable<EmployeeEntity> employees = employeeService.findAll(entitySpec);

    model.addAttribute("employees", employees);
    return "employee-list";
  }

  @GetMapping("/{id}")
  public String getEmployeeById(Model model, @PathVariable int id) {
    EmployeeEntity employeeEntity = employeeService.findById(id);
    model.addAttribute("employee", employeeEntity);
    return "employee-card";
  }

  @GetMapping("/add")
  public String showAddEmployeeForm(Model model) {
    model.addAttribute("employee", new EmployeeEntity());
    return "employee-add";
  }

  @PostMapping("/save")
  public String saveEmployee(@ModelAttribute("employee") EmployeeEntity employee,
                             @RequestParam("image") MultipartFile imageFile,
                             @RequestParam("codes") String[] codes,
                             @RequestParam("phones") String[] phones) throws IOException {
    List<String> codesList = Arrays.asList(codes);
    List<String> phonesList = Arrays.asList(phones);
    employeeService.save(employee, imageFile, codesList, phonesList);
    return "redirect:/employees";
  }
}
