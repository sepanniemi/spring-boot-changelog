package com.sepanniemi.changelog;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        properties = {
                "changelog.path=CHANGELOG.MD",
                "git.logs.path=classpath:git/logs/"})
@Disabled(value = "Disabled, waiting for fix")
public class SpringBootChangelogApplicationTests {

    @Test
    @Disabled(value = "Disabled until invent workaround")
    public void contextLoads() {
    }

}
