package fatec.sp.gov.login;

import fatec.sp.gov.login.entity.PermissionType;
import fatec.sp.gov.login.entity.User;
import fatec.sp.gov.login.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
			// Creating admin user if repository is empty
			if (repository.count() == 0) {
				User adminUser = new User();
				adminUser.setName("ADMIN");
				adminUser.setSurname("User");
				// The password is already hashed using bcrypt (as you provided)
				adminUser.setPassword("$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C");
				adminUser.setEmail("admin@example.com");
				adminUser.setFunction("Administrator");
				adminUser.setPermissionType(PermissionType.ROLE_ADMIN);

				repository.save(adminUser);
				log.info("Admin user created: " + adminUser.getName() + " " + adminUser.getEmail());
			}
		};
	}
}