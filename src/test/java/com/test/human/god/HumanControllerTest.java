package com.test.human.god;

import com.test.human.god.controller.HumanController;
import com.test.human.god.model.Human;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HumanControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private HumanController humanController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void contextLoads()  {
        assertThat(humanController).isNotNull();
    }

    @Test
    void getHumanTest() {
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/humans", String.class)).isNotNull();
    }

    @Test
    void postStudentTest()  {
        Human human = new Human();
        human.setId(8);
        human.setFirstName("Igor");
        human.setLastName("Snow");
        assertThat(this.restTemplate
                .postForObject("http://localhost:" + port + "/humans", human, String.class));
    }

    @Test
    void updateStudentTest() {
        Human human = new Human(4, "Arnold", "Dadlee");
        long id = this.restTemplate.postForObject
                ("http://localhost:" + port + "/humans", human, Human.class).getId();
        Human human1 = new Human(id, "Garold", "Fill");
        restTemplate.put("http://localhost:" + port + "/humans", human1);
        Assertions
                .assertThat((this.restTemplate.getForObject
                        ("http://localhost:" + port + "/humans/" + id, String.class)))
                .contains("" + id);

    }
    @Test
    void deleteStudentTest()  {
        Human human = new Human(111L,"Ronald","Green");
        long id = this.restTemplate.postForObject
                ("http://localhost:" + port + "/humans", human, Human.class).getId();
        restTemplate.delete("http://localhost:" + port + "/humans/" + id);
        Assertions
                .assertThat((this.restTemplate.getForObject
                        ("http://localhost:" + port + "/humans/" + id, String.class)))
                .toString().contains("500");
    }
}
