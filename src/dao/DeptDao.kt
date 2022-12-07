package dao

import entity.Department

interface DeptDao {
    fun addDept(dept: Department): Boolean
    fun updateDept(dept: Department): Boolean
    fun delDept(deptID: Int): Boolean
    fun queryDept(deptName: String): Department
    fun queryDept(deptID: Int): Department
    fun queryAllDept(): List<Department>
}