package com.project.waglewagle;

import com.project.waglewagle.entity.Users;
import com.project.waglewagle.repository.UserRepository;
import com.project.waglewagle.global.config.security.PrincipalDetail;
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
