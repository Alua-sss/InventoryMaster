package services;

import models.User;
import repositories.interfaces.IUserRepository;
import services.interfaces.IUserService;

public class UserService implements IUserService {
   private final IUserRepository userRepository;

   public UserService(IUserRepository userRepository) {
       this.userRepository = userRepository;
   }

    @Override
    public User login(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }else{
            System.out.println("Invalid username or password");
        }
        return null;
    }

    @Override
    public boolean registerUser(User user) {
       if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getUsername().length() < 3) {
           System.out.println("Username empty or less than 3 characters ");
           return false;
       }
       if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() < 6) {
           System.out.println("Password empty or less than 6 characters ");
       }
       return userRepository.addUser(user);
    }

}
