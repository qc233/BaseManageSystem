package pers.qc233.basemanagesystem;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pers.qc233.basemanagesystem.Windows.Login;

@SpringBootApplication
public class BaseManageSystemApplication{

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BaseManageSystemApplication.class);
        ConfigurableApplicationContext context = builder.headless(false).web(WebApplicationType.NONE).run(args);
        Login login = context.getBean(Login.class);
    }
}
