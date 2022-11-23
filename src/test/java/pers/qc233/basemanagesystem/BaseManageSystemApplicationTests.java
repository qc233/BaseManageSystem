package pers.qc233.basemanagesystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.qc233.basemanagesystem.Windows.Login;

@SpringBootTest
class BaseManageSystemApplicationTests {

    @Test
    void contextLoads() {
        new Login();
    }
}
