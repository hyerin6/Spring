package net.skhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.domain.Department;
import net.skhu.domain.User;
import net.skhu.model.UserRegistrationModel;
import net.skhu.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createEntity(UserRegistrationModel userModel) {
        User user = new User();
        user.setUserid(userModel.getUserid());
        user.setPassword(userModel.getPasswd1());
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setDepartment(new Department());
        user.getDepartment().setId(userModel.getDepartmentId());
        user.setEnabled(true);
        user.setUserType("학생");
        return user;
    }

    public void save(UserRegistrationModel userModel) {
        User user = createEntity(userModel);
        userRepository.save(user);
    }

}
