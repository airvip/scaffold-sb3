package wang.diff.user.server.service.convert;

import org.springframework.stereotype.Component;

import diff.wang.user.server.controller.model.UserDTO;
import wang.diff.user.server.entity.User;

import java.text.SimpleDateFormat;

@Component
public class UserConverter {

    public UserDTO convert2Dto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setBalance(user.getBalance());
        userDTO.setSex(user.getSex());
        userDTO.setStatus(user.getSex());
//        userDTO.setBirthday(DateUtil.format(user.getBirthday(), "yyyy-MM-dd"));
        String birthdayString = "";
        if(user.getBirthday() != null){
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthdayString = simpleDateFormat.format(user.getBirthday());
        }
        userDTO.setBirthday(birthdayString);
        userDTO.setUserName(user.getUserName());
        userDTO.setMobile(user.getMobile());
        userDTO.setCreateTime(user.getCreateTime());

        return userDTO;

    }
    
}
