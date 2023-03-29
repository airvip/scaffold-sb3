package wang.diff.user.server.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import diff.wang.user.server.controller.PingApi;

@RestController
@Tag(name = "ping")
public class PingController implements PingApi {

    @Override
    public ResponseEntity<String> ping(@Valid String name) {
        return ResponseEntity.ok("ping "+name);
    }
}
