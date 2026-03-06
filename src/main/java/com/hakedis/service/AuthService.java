package com.hakedis.service;

import com.hakedis.dao.UserDao;
import com.hakedis.model.Users;

public class AuthService {

    private final UserDao userDao = new UserDao();

    public Users login(String username, String password) {
        Users u = userDao.findByUserName(username);

        if (u == null) {
            System.out.println("kullanıcı bulunamadı");
            return null;
        }

        if (!u.getPassWord().equals(password)) {
            System.out.println("şifre yanlış");
            return null;
        }
        System.out.println("Giriş başarılı. userId = " + u.getId());
        System.out.println("Kullanıcı Adı = " + u.getUserName());
        return u;
    }

}
