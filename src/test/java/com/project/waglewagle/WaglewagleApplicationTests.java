package com.project.waglewagle;

import com.project.waglewagle.Entity.Users;
import com.project.waglewagle.Repository.UserRepository;
import com.project.waglewagle.auth.PrincipalDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WaglewagleApplicationTests {
	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
		Users users = userRepository.findByEmail("qnrms2898@naver.com").get();
		PrincipalDetail principalDetail = new PrincipalDetail(users);

	}

}
