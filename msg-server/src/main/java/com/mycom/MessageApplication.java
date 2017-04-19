package com.mycom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ MessageConfig.class })
public class MessageApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

}
