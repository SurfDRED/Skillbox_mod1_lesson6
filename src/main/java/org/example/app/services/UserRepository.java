package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements ProjectRepository<User> {
    private final Logger logger = Logger.getLogger(UserRepository.class);
    private final List<User> userShop = new ArrayList<>();

    {
        userShop.add(new User(1, "root", "123"));
    }

    @Override
    public List<User> retreiveAll() {
        return new ArrayList<>(userShop);
    }

    @Override
    public void store(User user) {
        if (!user.getUsername().isEmpty() || !user.getPassword().isEmpty()) {
            logger.info("store new user: " + user);
            user.setId(user.hashCode());
            userShop.add(user);
        }
    }

    @Override
    public boolean removeItemById(Integer userIdToRemove) {
        for (User user : retreiveAll()) {
            if (user.getId().equals(userIdToRemove)) {
                logger.info("remove user completed: " + user);
                return userShop.remove(user);
            }
        }
        return false;
    }
}