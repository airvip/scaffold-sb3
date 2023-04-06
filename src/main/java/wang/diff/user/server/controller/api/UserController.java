package wang.diff.user.server.controller.api;

import jakarta.annotation.Resource;
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
import wang.diff.user.server.service.UserService;

@RestController
@Tag(name="user")
public class UserController implements UserApi{

    @Resource
    private UserService userService;

    @Override
    public ResponseEntity<UserDTO> addOne(@Valid UserAddDTO userAddDTO) {
        final UserDTO userDTO = userService.addOne(userAddDTO);
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<BaseRespWithEffectiveCount> deleteById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> getById(Long id) {
        // TODO Auto-generated method stub
        final UserDTO userDTO = userService.getById(id);
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<UserPageDTO> getPage(@Valid Integer pageNum, @Valid Integer pageSize,
            @Valid String userName) {
        return ResponseEntity.ok(userService.getPage(pageNum,pageSize,userName));
    }

    @Override
    public ResponseEntity<BaseRespWithEffectiveCount> updateById(Long id, @Valid UserUpdateDTO userUpdateDTO) {
        // TODO Auto-generated method stub
        return null;
    }
    

}
