package com.agile.demo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component 
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}

}
