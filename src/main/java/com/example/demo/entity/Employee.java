package com.example.demo.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EMPLOYEE_NAME", length = 100)
    @NotNull
    private String employeeName;

    @Email
    private String email;

    private String gender;

    private String dateOfBirth;

    private String joinDate;

    private String mobileNumber;

    private String aadhaarNumber;

    private String accountNumber;

    private String department;

    private String designation;

    private String previousCompany;

    private String pfNumber;

    private Double salary;

    // ✅ ADDED (required by your form)
    private String role;

    @Size(max = 1000, min = 10)
    private String currentAddress;

    @Size(max = 1000, min = 10)
    private String permanentAddress;

    private Boolean active = true;
    
    
    @CreationTimestamp
    private LocalDateTime CreateDate;
    
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    
    private String password;
    
    

    public Employee() {
        super();
    }

    // -------- GETTERS & SETTERS --------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPreviousCompany() {
        return previousCompany;
    }

    public void setPreviousCompany(String previousCompany) {
        this.previousCompany = previousCompany;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    // ✅ ROLE getter & setter (CRITICAL)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    
    public LocalDateTime getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		CreateDate = createDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	@Override
    public String toString() {
        return "Employee [id=" + id +
                ", employeeName=" + employeeName +
                ", email=" + email +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", joinDate=" + joinDate +
                ", mobileNumber=" + mobileNumber +
                ", aadhaarNumber=" + aadhaarNumber +
                ", accountNumber=" + accountNumber +
                ", department=" + department +
                ", designation=" + designation +
                ", previousCompany=" + previousCompany +
                ", pfNumber=" + pfNumber +
                ", salary=" + salary +
                ", role=" + role +
                ", currentAddress=" + currentAddress +
                ", permanentAddress=" + permanentAddress +
                ", active=" + active + "]";
    }
}
