# spring-security-jwt

## 1. 공통 사항

    * 버전: Spring Boot 2.5.14.RELEASE
    * JPA + spring-boot-configuration-processor 사용
    * 자바 11(Openjdk 11)


## 2. Controller

    * @RestController: ResponseEntity를 활용하여 json 기반 Api 호출을 위해 사용
    * @RequestMapping: URL 매핑을 위해 사용
    * @AllArgsConstructor: @Autowired 없이 자동 주입을 위해 사용


## 3. BaseEntity

    * @PrePersist: DB로 저장 되기 전 호출
    * @PreUpdate: DB update 되기 전 호출


## 4. DataInitializer

    * 서버 재기동 직후 호출
    * 데이터 초기화 등에 사용


## 참고. 개발 패턴
### 1) MailController

메일 발송을 위한 자바 코드

```java

private final JavaMailSender mailSender;

@PostMapping
public ResponseEntity<?> send() throws MessagingException {
        MimeMessage m = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(m,"UTF-8");
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo("to mail");//받는 사람 메일 주소
        mimeMessageHelper.setSubject("테스트메일");
        mimeMessageHelper.setText("메일테스트");
        mailSender.send(m);

        return ResponseEntity.accepted().build();
}
```

### 2) 설정(naver stmp 기준)
<pre>
spring:
  mail:
    host: smtp.naver.com
    port: 465
    username: "your email"
    password: "your password"
    properties:
      mail.smtp.auth: true
      mail.smtp.ssl.enable: true
      mail.smtp.ssl.trust: smtp.naver.com
</pre>

정보 획득은 naver guide 페이지에서 확인
https://help.naver.com/service/5632/contents/18534?osType=PC&lang=ko

### 3) resourceId
```java
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("mail");//authorization client에 추가 필수
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// h2 데이터를 확인하기위해 h2-console url의 권한을 permitAll으로 바꾸어 줍니다.

		http.authorizeRequests()
				.antMatchers("/api/**").authenticated();

		http.cors().disable();

		http.csrf().disable()
				.headers().disable();
	}
}

```