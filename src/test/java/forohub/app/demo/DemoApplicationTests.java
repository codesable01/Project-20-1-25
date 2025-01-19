package forohub.app.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import forohub.app.api.DemoApplication;

@SpringBootTest(classes = DemoApplication.class)  // Especifica la clase principal aquí
class DemoApplicationTests {

    @Test
    void contextLoads() {
        // Prueba básica para verificar el contexto
    }
}
