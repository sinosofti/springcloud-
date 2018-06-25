package com.demo.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.service.StudentManager;
/** 
 * @RequestMapping 可以出现在类级别上，也可以出现在方法上。如果出现在类级别上，那请求的 url 为 类级别 
 * 上的 @RequestMapping + 方法级别上的 @RequestMapping，否则直接取方法级上的 @RequestMapping。 
 * 类级别的 @RequestMapping 不是必需的。 
 */ 
@Controller
@RequestMapping("/student-module")
public class StudentController {
    @Autowired
    StudentManager manager;
    /** 
     * 在方法级别使用 @RequestMapping 来限定请求处理的时候，可以指定两个属性。除了我们在上面刚使用过的 
     * method 属性，还有一个 params 属性。使用 params 属性，可以达到与使用 
     * ParameterMethodNameResolver 作为 MethodResolver的 MultiActionController 类似的功能。 
     * 
     * params 有两种表达形式，这里先说第一种："parameterName=parameterValue" 
     * @RequestParam 用于获取传入参数的值
     * @ResponseBody 作用于方法上，可以将整个返回结果以某种格式返回，如json或xml格式。
     * @PathVariable 用于定义路径参数值
     * 请求方法为 GET 或 POST，且具有 hello 参数，且值为 world 的请求才能匹配到该方法，如： 
     *   /my?hello=world 
     */ 
   // localhost:8080/springmvcdemo/student-module/getStudentInfo1
    @RequestMapping(value = "/getStudentInfo", method = RequestMethod.GET)
    public String getStudentInfo(Model model) {
        model.addAttribute("students", manager.getAllStudents());
        return   "showStudentInfo";
    }
    @RequestMapping(value = "/getStudentInfo1", method = RequestMethod.GET)
      public String requestParams2(@RequestParam(value = "name",required = false) String names){
          System.out.println("name = "+names);
          return "index";
      }
}
