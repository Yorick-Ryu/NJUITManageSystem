package service

import entity.User

interface UserService {
    // 登录
    public abstract fun login(userName: String, pwd: String): User


}