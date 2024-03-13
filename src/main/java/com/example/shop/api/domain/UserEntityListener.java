package com.example.shop.api.domain;

import com.example.shop.api.repository.UserHistoryRepository;
import com.example.shop.global.support.BeanUtils;
import jakarta.persistence.PreRemove;

public class UserEntityListener {

    @PreRemove
    public void preDelete(User user){
        UserHistoryRepository userHistoryRepository
                = BeanUtils.getBean(UserHistoryRepository.class);

        UserHistory userHistory = new UserHistory(user);
        userHistoryRepository.save(userHistory);
    }
}
