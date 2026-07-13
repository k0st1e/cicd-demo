package dev.kostie.cicddemo.containers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

// Full Application Context with Testing Application Properties.
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    private int port;

    // Belongs to the class rather than instances of the class.
    // One container for many tests.
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    // Instead of Application Properties, do this.
    @DynamicPropertySource
    static void configureDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        // Base address.
        RestAssured.baseURI = "http://localhost:" + port;
        // Needed with multiple tests because if we start
        // a second test, older data might exist from the previous.
        customerRepository.deleteAll();
    }

    @Test
    void shouldGetAllCustomers() {
        List<Customer> customers = List.of(
                new Customer(null, "Kostas", "kostas@gmail.com"),
                new Customer(null, "Ioanna", "ioanna@gmail.com")
        );
        customerRepository.saveAll(customers);

        // Given X -> When Y happens -> Then assert Z.
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/customers/")
                .then()
                .statusCode(200)
                // `.` is a JSONPath expression, meaning the root, the whole response body.
                // expected size of 2.
                .body(".", hasSize(2));
    }
}