//package com.wissen.SmartInterviewProcess.services;
//
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.wissen.SmartInterviewProcess.models.Employee;
//import com.wissen.SmartInterviewProcess.repository.EmployeeRepository;
//
//
//@Service
//public class UserServiceImpl implements UserService {
//	@Autowired
//	private EmployeeRepository employeeRepository;
//	
//	@Autowired
//	private RoleRepository roleRepository;
//	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Override
//	public void save(User user) {
//		User existingUser = userRepository.findByUsername(user.getUsername());
//		if (existingUser != null)
//			throw new RuntimeException("Username Already Exist");
//		System.out.println(user);
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		Set<Role> roles = new HashSet<>(roleRepository.findAll());
//		user.setRoles(roles);
//		userRepository.save(user);
//	}
//
//	@Override
//	public void save(Employee employee) {
//		// TODO Auto-generated method stub
//		Employee existingEmp = employeeRepository.findByEmail(employee.getEmail());
//		if(existingEmp != null)
//			throw new RuntimeException("Email exists");
//		existingEmp.setPassword(employee.getPassword());
//	
//		
//	}
//
//	@Override
//	public Employee findByEmail(String e) {
//		return employeeRepository.findByEmail(e);
//	}
//}