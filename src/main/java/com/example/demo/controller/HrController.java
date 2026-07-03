package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Compose;
import com.example.demo.entity.CreatePost;
import com.example.demo.entity.Employee;
import com.example.demo.reposetory.ComposeRepo;
import com.example.demo.reposetory.CreatepostRepo;
import com.example.demo.reposetory.EmployeeRepo;
import com.example.demo.service.DashboardService;
import com.example.demo.service.HrService;
import com.example.demo.service.UserDashboardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HrController {
	
	@Autowired
	private HrService service ;
	
	@Autowired
	private CreatepostRepo createpostRepo;
	
	@Autowired
	private EmployeeRepo  employeeRepo;
	
	@Autowired 
	private ComposeRepo composeRepo;
	
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private UserDashboardService userDashboardService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    // ⚠ Login should be POST (security + correctness)
    @GetMapping("/home")
    public String home(@RequestParam("username")String username,
                       @RequestParam("password")String password,
                       Model model, HttpSession session) {
    	System.out.println("Username and password:"+username+"-"+password);

    	try {
			String empId = username.substring(3);
			System.out.println("empId:-"+empId);
			
			Employee employee = employeeRepo.findByIdAndPassword(Integer.parseInt(empId),password);
  
			if(employee != null) {
				model.addAttribute("error",false);
				session.setAttribute("userId", Integer.parseInt(empId));
				session.setAttribute("name", employee.getEmployeeName());
				session.setAttribute("desg", employee.getDesignation());
				
				if(employee.getRole().equals("USER")) {
					return "redirect:/user-dashboard";
				}
				else if(employee.getRole().equals("ADMIN")) {
					
					 return "redirect:/dash-board";
				}else {
					  return "redirect:/login";
				}
				
				
				
				
			}else {
				model.addAttribute("error", true);
			    return "login";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			System.err.println(e.getMessage());
			return "redirect:/login";
		}
    	
    	
    	
    }

    @GetMapping("/dash-board")
    public String dashboard(Model model) {

        dashboardService.getDashboardData()
                .forEach(model::addAttribute);

        return "dash-board";
    }


    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @GetMapping("/all-employee")
    public String allEmployee( Model model) {
    	
    	List<Employee>allEmployee =service.getAllEmployee();
    	model.addAttribute("allEmployee",allEmployee);
        return "all-employee";
    }

    @GetMapping("/create-post")
    public String createPost(Model model) {
    	List<CreatePost> findAll = createpostRepo.findAll();
    	model.addAttribute("post", findAll);
    	return "create-post";
    }

    @GetMapping("/status")
    public String status(Model model) {

        List<Compose> findAll = composeRepo.findAll();

        findAll.forEach(k -> {
            Integer empId = k.getParentUkid(); // ✅ correct foreign key

            if (empId != null) {
                employeeRepo.findById(empId)
                    .ifPresent(emp -> k.setPosition(emp.getDesignation()));
            }
        });

        model.addAttribute("statusList", findAll);
        return "status";
    }


    @GetMapping("/my-profile")
    public String myProfile(HttpSession session ,Model model ) {

        Object attribute = session.getAttribute("userId");

        // 🔴 ADD THIS CHECK (required)
        if (attribute == null) {
            return "redirect:/login";
        }

        int userId = Integer.parseInt(attribute.toString());

        // 🔴 SAFE FETCH (replace .get())
        Employee employee = employeeRepo.findById(userId).orElse(null);

        // 🔴 ADD THIS CHECK (required)
        if (employee == null) {
            return "redirect:/login";
        }

        model.addAttribute("employee",employee);
        return "my-profile";
    }


    @GetMapping("/setting")
    public String setting() {
        return "setting";
    }
    
    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute Employee employee) {
    	employee.setPassword(employee.getDateOfBirth());
    	
        Employee save = service.addEmployee(employee);
        return "redirect:/all-employee";
    }
    @PostMapping("/save-post")
    public String savePost(@ModelAttribute CreatePost createPost) {
    	createPost.setAddedDate(new Date().toString());
        CreatePost addPost =service.addPost(createPost);
        return "redirect:/create-post";
    }
    
    @PostMapping("/update-password")
    public String updatePassword(@RequestParam("password") String password , @RequestParam("newPassword1") String newPassword1  ,@RequestParam("newPassword2") String newPassword2,HttpSession  session ,Model model) {
    
    	Object attribute = session.getAttribute("userId");
    	int userId=Integer.parseInt(attribute.toString());
    	
    	Employee employee = employeeRepo.findByIdAndPassword(userId, password);
    	if(employee != null && newPassword1.equals(newPassword2)) {
    	
    		employee.setPassword(newPassword2);
    		employeeRepo.save(employee);
    		model.addAttribute("error",false);
    		
    	}else {
    		
    	model.addAttribute("error",true);	
    		return "setting";
    	}
    	
    	return "redirect:/login";
    }
    
    @GetMapping("/edit-record")
     public String editRecord(@RequestParam("id")int id, Model model) {
    	
    	System.out.println("ID:-"+id);
    	Employee employee = employeeRepo.findById(id).get();
    	model.addAttribute("employee",employee);
    	return "edit-record"; 
     }
    
    @PostMapping("/edit-employee")
    public String updateRecord(@ModelAttribute Employee employee) {
    	int id=employee.getId();
    	Employee getEmp = employeeRepo.findById(id).get();
    	if(getEmp != null) {
    		employeeRepo.save(employee);
    	}
    	return"redirect:/all-employee";
    }
    
    @GetMapping("/deleteRecord-byId")
    public String deleteRecordById(@RequestParam("id") int id) {
    	
    	employeeRepo.deleteById(id);
    	return "redirect:/all-employee";
    }
    

    @GetMapping("/user-dashboard")
    public String userDashBoard(HttpSession session, Model model) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        // 🔥 VERY IMPORTANT FIX
        model.asMap().clear();

        Map<String, Object> data =
                userDashboardService.getUserDashboardData(userId);

        model.addAllAttributes(data);

        return "user-dashboard";
    }


    
    @GetMapping("/user-profile")
    public String userProfile(HttpSession session, Model model) {

        Integer userId = (Integer) session.getAttribute("userId");

        Employee employee = new Employee(); // empty object

        if (userId != null) {
            employee = employeeRepo.findById(userId).orElse(employee);
        }

        model.addAttribute("employee", employee);
        return "user-profile";
    }
    
    @GetMapping("/user-setting")
    public String usersetting() {
        return "user-setting";
    }
    

    @GetMapping("/user-compose")
    public String compose() {
        return "user-compose";
    }
    
    @PostMapping("/compose")
    public String addCompose(
            @RequestParam String subject,
            @RequestParam String text,
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            // user not logged in or session expired
            return "redirect:/login";
        }

        Employee employee = employeeRepo.findById(userId).orElse(null);

        if (employee == null) {
            return "redirect:/login";
        }

        Compose com = new Compose();
        com.setEmpName(employee.getEmployeeName());
        com.setSubject(subject);
        com.setText(text);
        com.setParentUkid(userId);
        com.setAddedDate(new Date().toString());
        com.setStatus("pending");

        composeRepo.save(com);

        return "redirect:/user-compose";
    }
    
    @GetMapping("/approve-byId")
    public String approve(@RequestParam("id")int id,@RequestParam("type") String type) {
    	
    	Compose compose = composeRepo.findById(id).get();
    	compose.setStatus(type);
    	composeRepo.save(compose);
    	
    	return "redirect:/status";
    	
    }
    
}
