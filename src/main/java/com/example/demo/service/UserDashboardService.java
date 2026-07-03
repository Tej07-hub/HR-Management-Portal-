package com.example.demo.service;

import java.util.HashMap;
import java.util.List;   // ✅ THIS WAS MISSING
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CreatePost;
import com.example.demo.reposetory.ComposeRepo;
import com.example.demo.reposetory.CreatepostRepo;
import com.example.demo.reposetory.EmployeeRepo;

@Service
public class UserDashboardService {

	@Autowired
	private CreatepostRepo createpostRepo;
	
    @Autowired
    private ComposeRepo composeRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    public Map<String, Object> getUserDashboardData(Integer userId) {

        Map<String, Object> data = new HashMap<>();

        // ✅ ONLY HIS ACTIVITY

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


        data.put("userpendingCount",
                composeRepo.countByParentUkidAndStatusContainingIgnoreCase(userId, "pend"));

        data.put("userapprovedCount",
                composeRepo.countByParentUkidAndStatusContainingIgnoreCase(userId, "approv"));

        data.put("userdeniedCount",
                composeRepo.countByParentUkidAndStatusContainingIgnoreCase(userId, "denied"));

        data.put("usercanceledCount",
                composeRepo.countByParentUkidAndStatusContainingIgnoreCase(userId, "cancel"));

        data.put("userallCount",
                composeRepo.countByParentUkid(userId));


        
        
        List<CreatePost> recentPosts = createpostRepo.findTop5RecentPosts();
        data.put("recentPosts", recentPosts);

        
        // ✅ BIRTHDAYS = EVERYONE
        data.put("birthdayList",
                employeeRepo.findTop5ByOrderByDateOfBirthAsc());

        return data;
    }
}
