package com.ww.rpc.service;

import com.ww.rpc.api.UserService;
import com.ww.rpc.core.domain.RpcRequest;
import com.ww.rpc.core.domain.RpcResponse;
import com.ww.rpc.core.service.RpcService;
import com.ww.rpc.service.impl.UserServiceImpl;
import com.ww.rpc.service.resolver.ServiceResolve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}


	@Autowired
	RpcService invoker;

	@Bean
	public ServiceResolve createResolver(){
		return new ServiceResolve();
	}

	@Bean
	public RpcService createInvoker(@Autowired ServiceResolve resolver){
		return new RpcService(resolver);
	}

	@Bean(name = "com.ww.rpc.api.UserService")
	public UserService createUserService(){
		return new UserServiceImpl();
	}

	@PostMapping("/")
	public RpcResponse invoke(@RequestBody RpcRequest request) {
		return invoker.invoke(request);
	}

}
