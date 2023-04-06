package wang.diff.user.server.service;

import diff.wang.user.server.controller.model.*;

public interface UserService {
    
    UserDTO addOne(UserAddDTO userAddDTO);

    Integer updateById(Long id, UserUpdateDTO payload);

    Integer updateByIds(UserUpdateSelectedDTO updateSelectedDTO);

    Integer deleteById(Long id);

    UserDTO getById(Long id);

    UserPageDTO getPage(Integer pageNum, Integer pageSize, String username);
    
}
