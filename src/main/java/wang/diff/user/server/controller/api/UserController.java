package wang.diff.user.server.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import diff.wang.user.server.controller.UserApi;
import diff.wang.user.server.controller.model.BaseRespWithEffectiveCount;
import diff.wang.user.server.controller.model.UserAddDTO;
import diff.wang.user.server.controller.model.UserDTO;
import diff.wang.user.server.controller.model.UserPageDTO;
import diff.wang.user.server.controller.model.UserUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name="user")
public class UserController implements UserApi{

    @Override
    public ResponseEntity<UserDTO> addOne(@Valid UserAddDTO userAddDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<BaseRespWithEffectiveCount> deleteById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<UserPageDTO> getPage(@Valid Integer pageSize, @Valid Integer currentPage,
            @Valid String userName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<BaseRespWithEffectiveCount> updateById(Long id, @Valid UserUpdateDTO userUpdateDTO) {
        // TODO Auto-generated method stub
        return null;
    }
    

}
