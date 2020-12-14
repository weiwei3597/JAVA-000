package com.ww.rpc.client;

import com.ww.rpc.api.UserService;
import com.ww.rpc.core.client.RpcClient;
import com.ww.rpc.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {


		UserService service = RpcClient.getService(UserService.class, "http://localhost:8080/");
		User user = service.findUserById(1);
		System.out.println(user.getName());

		//SpringApplication.run(ClientApplication.class, args);
	}

}
