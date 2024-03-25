package webmvc.task_3_1_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webmvc.task_3_1_1.model.User;
import webmvc.task_3_1_1.repo.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
    }

    @Override
    public void updateUserById(Long id, String firstName, String lastName, int age) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
