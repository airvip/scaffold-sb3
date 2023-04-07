package wang.diff.user.server.controller.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

@SpringBootTest
public class ObjectStoreControllerTest {

    @Resource
    private ObjectStoreController objectStoreController;


    @Test
    void uploadMutilPartTest() {
        objectStoreController.uploadMultipart(null);
    }
    
}
