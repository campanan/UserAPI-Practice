package br.com.training.service;


import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveAll(User user){
        return userRepository.save(user);
    }

    public User verifyAndGetIfExists(String cpf){
        return userRepository.findByCpf(cpf);
    }


    public User updateUserByCpf(String cpf, User userNovo) {
        User foundUser = verifyAndGetIfExists(cpf);
        userNovo.setId(foundUser.getId());
        User updatedUser = userRepository.save(userNovo);
        return updatedUser;
    }

    public void deleteUserByCpf(String cpf){
        long foundUserId = verifyAndGetIfExists(cpf).getId();
        userRepository.deleteById(foundUserId);
    }

}
