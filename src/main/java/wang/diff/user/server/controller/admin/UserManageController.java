package wang.diff.user.server.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import diff.wang.user.server.controller.UserManageApi;
import diff.wang.user.server.controller.model.BaseRespWithEffectiveCount;
import diff.wang.user.server.controller.model.UserUpdateSelectedDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "user-manage")
public class UserManageController implements UserManageApi {

    @Override
    public ResponseEntity<BaseRespWithEffectiveCount> updateByIdsManage(
            @Valid UserUpdateSelectedDTO userUpdateSelectedDTO) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
