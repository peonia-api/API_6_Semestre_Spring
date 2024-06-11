package fatec.sp.gov.login.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements CommandLineRunner {

    private final Dotenv dotenv;

    public AppStartupRunner(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("EMAIL_USERNAME PORRA DO INFERNOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO: " + dotenv.get("EMAIL_USERNAME"));
        System.out.println("EMAIL_PASSWORD: " + dotenv.get("EMAIL_PASSWORD"));
    }
}
