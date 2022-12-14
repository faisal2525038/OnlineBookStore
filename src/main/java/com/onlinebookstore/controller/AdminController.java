package com.onlinebookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebookstore.dto.Admin;

import com.onlinebookstore.exception.AdminException;

import com.onlinebookstore.service.AdminService;

@RestController
public class AdminController {
	@Autowired
	AdminService adminService;

	@PostMapping("admin")
	public String registerUser(@Valid @RequestBody Admin admin) throws AdminException, MethodArgumentNotValidException {

		return this.adminService.registerAdmin(admin);

	}


	@GetMapping("admins")
	public List<Admin> getsAdmin() throws AdminException {
		return this.adminService.getAdmin();

	}

	@PutMapping("admin")
	public String updateAdmin(@Valid @RequestBody Admin admin) throws AdminException, MethodArgumentNotValidException {
		return this.adminService.updateAdmin(admin);

	}

	@DeleteMapping("admin/{adminId}/{adminPassword}")
	public String deleteAdmin(@PathVariable Integer adminId, @PathVariable String adminPassword) throws AdminException {
		return this.adminService.deleteAdmin(adminId, adminPassword);

	}
	
	@PostMapping("admin/login/{adminId}/{adminPassword}")
	public Boolean login(@PathVariable Integer adminId,@PathVariable String adminPassword) throws AdminException {
		return this.adminService.login(adminId, adminPassword);

	}
	@PutMapping("admin/updatepassword/{id}/{oldPassword}/{newPassword}")
	public String changePassword(@PathVariable Integer id,@PathVariable String oldPassword,@PathVariable  String newPassword) throws AdminException {
		return this.adminService.changePassword(id,oldPassword, newPassword);
		
	}
}
