package com.wissen.SmartInterviewProcess.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Role;
import com.wissen.SmartInterviewProcess.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {

		Employee employee = employeeRepository.findByEmail(email);
		System.out.println("inside userdetails " + employee);
		if (employee == null)
			throw new UsernameNotFoundException(email);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Role role = employee.getRole();
		grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		System.out.println(grantedAuthorities);
		return new org.springframework.security.core.userdetails.User(employee.getEmail().toLowerCase(), employee.getPassword(),
				grantedAuthorities);
	}
	
	
}