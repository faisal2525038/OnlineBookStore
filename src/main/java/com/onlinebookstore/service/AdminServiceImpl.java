package com.onlinebookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookstore.dao.AdminRepository;
import com.onlinebookstore.dto.Admin;
import com.onlinebookstore.dto.Cart;
import com.onlinebookstore.dto.User;
import com.onlinebookstore.exception.AdminException;
import com.onlinebookstore.exception.UserException;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository adminRepository;

	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		List<Admin> adminList=this.adminRepository.findAll();
		if(!adminList.isEmpty()) {
			throw new AdminException("Admin already exists!");
		}
	
		return adminRepository.save(admin);
		
	}


	@Override
	public String deleteAdmin(Integer adminId,String adminPassword) throws AdminException {
		String isDeleted = "Unsuccessful";
		List<Admin> admin= this.adminRepository.findAll();
		if(admin.isEmpty()) {
			throw new AdminException("Admin does not exist for id "+adminId);
		}
		
		else {
			 Admin first= admin.get(0);
			 if (!first.getAdminPassword().equals(adminPassword)) {
				 throw new AdminException("Incorrect Password");
			 }
			 
			adminRepository.delete(first);
			isDeleted = "Successful";
		}
		return isDeleted;		
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminException {
		
		Optional<Admin> foundAdmin = this.adminRepository.findById(admin.getAdminId());
		if(admin.equals(null)) {
			throw new AdminException("Enter valid Admin Details!");
		}else if(foundAdmin.isEmpty()) {
			throw new AdminException("Admin doesnot exists for id "+admin.getAdminId());
		}
		return this.adminRepository.save(admin);
	}

	@Override
	public List<Admin> getAdmin() throws AdminException {
		
		return this.adminRepository.findAll();
	}
	
}
