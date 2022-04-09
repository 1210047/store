package com.nkb.store.service;

import com.nkb.store.entity.User;

public interface UserService {

    void reg(User user);

    User login(String username, String password);

    void changePawword(Integer uid, String username, String oldPassword, String newPassword);

    User getByUid(Integer uid);

    void changeInfo(Integer uid, String username, User user);

    void changeAvatar(Integer uid, String username, String avatar);
}
