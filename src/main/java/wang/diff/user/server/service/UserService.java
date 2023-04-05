package wang.diff.user.server.service;

import diff.wang.user.server.controller.model.UserAddDTO;
import diff.wang.user.server.controller.model.UserDTO;
import diff.wang.user.server.controller.model.UserUpdateDTO;
import diff.wang.user.server.controller.model.UserUpdateSelectedDTO;

public interface UserService {
    
    UserDTO addOne(UserAddDTO userAddDTO);

    Integer updateById(Long id, UserUpdateDTO payload);

    Integer updateByIds(UserUpdateSelectedDTO updateSelectedDTO);

    Integer deleteById(Long id);

    UserDTO getById(Long id);

    com.github.pagehelper.Page<UserDTO> getPage(Integer pageSize, Integer pageNum);
    
}
