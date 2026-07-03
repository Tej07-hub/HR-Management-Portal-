package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Compose;
import com.example.demo.entity.Employee;
import com.example.demo.reposetory.ComposeRepo;
import com.example.demo.reposetory.EmployeeRepo;

@Service
public class DashboardService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ComposeRepo composeRepo;

    public Map<String, Object> getDashboardData() {

        Map<String, Object> data = new HashMap<>();

        data.put("devCount",
                employeeRepo.countByDepartmentContainingIgnoreCase("dev"));

        data.put("qaCount",
                employeeRepo.countByDepartmentContainingIgnoreCase("qa"));

        data.put("networkCount",
                employeeRepo.countByDepartmentContainingIgnoreCase("network"));

        data.put("hrCount",
                employeeRepo.countByDepartmentContainingIgnoreCase("hr"));

        data.put("securityCount",
                employeeRepo.countByDepartmentContainingIgnoreCase("security"));

        data.put("salesCount",
                employeeRepo.countByDepartmentContainingIgnoreCase("sale"));
     // STATUS COUNTS (case + wording safe)
        data.put("pendingCount",
                composeRepo.countByStatusContainingIgnoreCase("pend"));

        data.put("approvedCount",
                composeRepo.countByStatusContainingIgnoreCase("approv"));

        data.put("deniedCount",
                composeRepo.countByStatusContainingIgnoreCase("denied"));

        data.put("canceledCount",
                composeRepo.countByStatusContainingIgnoreCase("cancel"));

        data.put("allCount",
                composeRepo.count());

        // recent activity
        List<Compose> recent = composeRepo.findTop5ByOrderByIdDesc();

        data.put("recentList", recent);

        // birthdays
        List<Employee> birthdays = employeeRepo.findTop5ByOrderByDateOfBirthAsc();
        data.put("birthdayList", birthdays);

        return data;
    }
}
