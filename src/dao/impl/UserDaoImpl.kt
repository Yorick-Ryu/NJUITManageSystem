package dao.impl

import util.BaseDao
import dao.UserDao
import entity.User
import util.SplitPage
import java.sql.SQLException

class UserDaoImpl : UserDao, BaseDao() {
    override fun addUser(user: User): Boolean {
        var flag = false
        try {
            val conn = super.getConnection()
            val sql = "insert into user(userName,pwd,name,dept)values(?,?,?,?)"
            val count = conn?.prepareStatement(sql)?.run {
                setString(1, user.userName)
                setString(2, user.pwd)
                setString(3, user.name)
                setInt(4, user.dept)
                executeUpdate()
            } ?: 0
            flag = count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return flag
    }

    override fun update(user: User): Boolean {
        var flag = false
        try {
            val conn = super.getConnection()
            val sql = "UPDATE `USER` SET pwd = ? , name = ? ,dept = ? where userName = ?"
            val count = conn?.prepareStatement(sql)?.run {
                setString(1, user.pwd)
                setString(2, user.name)
                setInt(3, user.dept)
                setString(4, user.userName)
                executeUpdate()
            } ?: 0
            flag = count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return flag
    }

    override fun delUser(id: Int): Boolean {
        var flag = false
        try {
            val conn = super.getConnection()
            val sql = "delete from user where id = ?"
            val count = conn?.prepareStatement(sql)?.run {
                setInt(1, id)
                executeUpdate()
            } ?: 0
            flag = count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return flag
    }

    override fun queryAllUsers(): List<User> {
        return query("SELECT * FROM `USER`")
    }

    // 数据分页
    override fun queryUsers(sp: SplitPage): List<User> {
        val sql =
            "SELECT * FROM `USER` limit ${sp.pageRows * (sp.currentPage - 1)},${sp.pageRows}"
        return query(sql)
    }

    // 根据账户和密码查找
    override fun queryUser(userName: String, pwd: String): List<User> {
        val sql = "SELECT * FROM `USER` WHERE `userName` = '$userName' AND `pwd` = '$pwd' "
        return query(sql)
    }

    // 根据ID查找
    override fun queryUserById(id: Int): List<User> {
        val sql = "SELECT * FROM `USER` WHERE `id` = '$id' "
        return query(sql)
    }

    // 模糊查找
    override fun queryUserByLike(dept: String, name: String): List<User> {
        val sql = "SELECT * FROM `USER` WHERE `dept` = '$dept' AND `name` LIKE '%$name%' "
        println(sql)
        return query(sql)
    }

    // 模糊查找分页
    override fun queryUserByLike(dept: String, name: String, sp: SplitPage): List<User> {
        val sql = "SELECT * from `USER` WHERE `dept` = '$dept' AND `name` LIKE '%$name%' " +
                "limit ${sp.pageRows * (sp.currentPage - 1)},${sp.pageRows}"
        return query(sql)
    }

    // 通用查找
    private fun query(sql: String): List<User> {
        val userList = mutableListOf<User>()
        try {
            val conn = super.getConnection()
            val rs = conn?.prepareStatement(sql)?.run {
                executeQuery(sql)
            }
            if (rs != null) {
                while (rs.next()) {
                    val user = User().apply {
                        this.id = rs.getInt("id")
                        this.userName = rs.getString("userName")
                        this.pwd = rs.getString("pwd")
                        this.name = rs.getString("name")
                        this.dept = rs.getInt("dept")
                    }
                    userList.add(user)
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return userList
    }
}