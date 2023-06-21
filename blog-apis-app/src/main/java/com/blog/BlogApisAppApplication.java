package com.blog;

import java.util.List;

import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.repository.RoleRepo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogApisAppApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {

		SpringApplication.run(BlogApisAppApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("new_password"));

		try{
			Role role = new Role();
			role.setRoleId(AppConstants.NORMAL_USER);
			role.setName("NORMAL_USER");

			Role role1 = new Role();
			role1.setRoleId(AppConstants.ADMIN_USER);
			role1.setName("ADMIN_USER");

			List<Role> roles = List.of(role,role1);
			this.roleRepo.saveAll(roles);
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}
}
