package wang.diff.user.server.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import diff.wang.user.server.controller.model.*;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;

import jakarta.annotation.Resource;
import wang.diff.user.server.dao.UserMapper;
import wang.diff.user.server.entity.User;
import wang.diff.user.server.service.UserService;
import wang.diff.user.server.service.convert.UserConverter;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserConverter userConverter;

    @Override
    public UserDTO addOne(UserAddDTO userAddDTO) {
        // TODO check params
        LocalDate birthday = LocalDate.parse(userAddDTO.getBirthday(), DateTimeFormatter.ISO_LOCAL_DATE);
        final Date birthdayDate = Date.from(birthday.atStartOfDay(ZoneId.systemDefault()).toInstant());
        final User user = new User();
        user.setUserName(userAddDTO.getUserName());
        user.setMobile(userAddDTO.getMobile());
        user.setBirthday(birthdayDate);
        user.setSex(userAddDTO.getSex());

        userMapper.insert(user);
        return userConverter.convert2Dto(user);
    }

    @Override
    public Integer deleteById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDTO getById(Long id) {
        final User user = userMapper.selectById(id);
        return userConverter.convert2Dto(user);
    }

    @Override
    public UserPageDTO getPage(Integer pageNum, Integer pageSize, String username) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(username != null){
            queryWrapper.like(User.USER_NAME, username);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        PageInfo<User> page = new PageInfo<>(list);
        return userConverter.covert2PageDto(page);
    }

    @Override
    public Integer updateById(Long id, UserUpdateDTO payload) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer updateByIds(UserUpdateSelectedDTO updateSelectedDTO) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
