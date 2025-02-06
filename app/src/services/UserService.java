package services;

import models.User;
import repositories.interfaces.IUserRepository;
import services.interfaces.IUserService;

public class UserService implements IUserService {
   private final IUserRepository userRepository;
   private User currentUser;

   public UserService(IUserRepository userRepository) {
       this.userRepository = userRepository;
   }

    @Override
    public User login(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    @Override
    public boolean registerUser(User user) {
       if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
           System.out.println("Username or Password is empty");
           return false;
       }
       return userRepository.addUser(user);
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
}
