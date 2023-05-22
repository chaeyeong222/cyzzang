package com.ssafit.cheajong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafit.cheajong.interceptor.JwtInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	// 스웨거 위한 핸들러 추가
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources.webjars/springfox-swagger-ui/");
	}

	// 컨트롤러에 있는 모든 cors 설정 전역화
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
	}
	
	// 이 아래는 토큰을 이용한 실행 제한 인터셉터, 프론트 설계에 따라서 exclude 내용추가 필요
	@Autowired
	private JwtInterceptor jwtinter;
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(jwtinter).addPathPatterns("/**").excludePathPatterns("/userapi/user/login");
//	}
}
