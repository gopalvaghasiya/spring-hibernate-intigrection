package com.dineshonjava.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;






import com.dineshonjava.bean.EmployeeBean;
import com.dineshonjava.bean.LoginBean;
import com.dineshonjava.model.Employee;
import com.dineshonjava.model.FileBucket;
import com.dineshonjava.model.Login;
import com.dineshonjava.service.EmployeeService;

/**
 * @author Gopal Vaghasiya
 * 
 */
@Controller
public class EmployeeController {

	
	private static String UPLOAD_LOCATION="C:/mytemp/";
    
	@Autowired
	private EmployeeService employeeService;
   
 
     
    @RequestMapping(value={"/","/welcome"}, method = RequestMethod.GET)
    public String getHomePage(ModelMap model) {
        return "welcome";
    }
 
    @RequestMapping(value="/singleUpload", method = RequestMethod.GET)
    public ModelAndView getSingleUploadPage(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return new ModelAndView("singleFileUploader");
    }
    
    
    @RequestMapping(value = "/singleFileUploader", method = RequestMethod.GET)
	public ModelAndView welcomeFileUpload() {
		return new ModelAndView("singleFileUploader");
	}
 
    @RequestMapping(value="/success", method = RequestMethod.POST)
    public ModelAndView singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {
 
        System.out.println("file upload");
    	if (result.hasErrors()) {
            System.out.println("validation errors");
            return new ModelAndView("singleFileUploader");
        } else {            
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();
            //System.out.println("get a file");
            //Now do something with file...	
            
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
             
            System.out.println("create a copy of file");
            String fileName = multipartFile.getOriginalFilename();
            	
            System.out.println("store a file");
            model.addAttribute("fileName", fileName);
            
            System.out.println("add into model");
            return new ModelAndView("success");
        }
    }
 
   

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("command") EmployeeBean employeeBean, 
			BindingResult result) {
		Employee employee = prepareModel(employeeBean);
		employeeService.addEmployee(employee);
		return new ModelAndView("redirect:/add.html");
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView loginEmployee(
			@ModelAttribute("command") LoginBean loginBean,
			BindingResult result) {
		/*Employee employee = prepareModel(employeeBean);*/
		List<Login> Login=(List<Login>) employeeService.getLogin(loginBean.getId(), loginBean.getPassword());
		int user_id=loginBean.getId();
		int user_password=loginBean.getPassword();
		if(user_id==1 && user_password==123){
			return new ModelAndView("employeesList");
		}else if(user_id==2 && user_password==123){
			return new ModelAndView("home");
		}
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView welcomeLogin() {
		return new ModelAndView("login");
	}

	
	
	/***
	 * list employee
	 * @return
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",
				prepareListofBean(employeeService.listEmployees()));
		System.out.println("gopallist");
		return new ModelAndView("employeesList", model);
	}
	
	/***
	 * search employee controller
	 * @return
	 */
	
	@RequestMapping(value = "/employeesearch", method = RequestMethod.GET)
	public ModelAndView getSearch(
			@ModelAttribute("command") EmployeeBean empBean,
			BindingResult result) {
		
		System.out.println("gopalsearch"+empBean.getName());
		Map<String, Object> model = new HashMap<String, Object>();
		List list = employeeService.getSearch(empBean.getName());
		System.out.println(list.size());
		model.put("empname", list);
		System.out.println("employee search");
		return new ModelAndView("employeesList", model);
	
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEmployee(
			@ModelAttribute("command") EmployeeBean employeeBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",
				prepareListofBean(employeeService.listEmployees()));
		return new ModelAndView("addEmployee", model);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView editEmployee(
			@ModelAttribute("command") EmployeeBean employeeBean,
			BindingResult result) {
		employeeService.deleteEmployee(prepareModel(employeeBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", null);
		model.put("employees",
				prepareListofBean(employeeService.listEmployees()));
		return new ModelAndView("addEmployee", model);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") EmployeeBean employeeBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", prepareEmployeeBean(employeeService
				.getEmployee(employeeBean.getId())));
		model.put("employees",
				prepareListofBean(employeeService.listEmployees()));
		return new ModelAndView("addEmployee", model);
	}

	private Employee prepareModel(EmployeeBean employeeBean){
		Employee employee = new Employee();
		employee.setEmpAddress(employeeBean.getAddress());
		employee.setEmpAge(employeeBean.getAge());
		employee.setEmpName(employeeBean.getName());
		employee.setSalary(employeeBean.getSalary());
		employee.setEmpId(employeeBean.getId());
		employeeBean.setId(null);
		return employee;
	}

	private List<EmployeeBean> prepareListofBean(List<Employee> employees){
		List<EmployeeBean> beans = null;
		if(employees != null && !employees.isEmpty()){
			beans = new ArrayList<EmployeeBean>();
			EmployeeBean bean = null;
			for(Employee employee : employees){
				bean = new EmployeeBean();
				bean.setName(employee.getEmpName());
				bean.setId(employee.getEmpId());
				bean.setAddress(employee.getEmpAddress());
				bean.setSalary(employee.getSalary());
				bean.setAge(employee.getEmpAge());
				beans.add(bean);
			}
		}
		return beans;
	}

	private EmployeeBean prepareEmployeeBean(Employee employee){
		EmployeeBean bean = new EmployeeBean();
		bean.setAddress(employee.getEmpAddress());
		bean.setAge(employee.getEmpAge());
		bean.setName(employee.getEmpName());
		bean.setSalary(employee.getSalary());
		bean.setId(employee.getEmpId());
		return bean;
	}
}
