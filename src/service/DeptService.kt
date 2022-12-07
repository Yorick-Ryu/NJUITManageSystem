package service

import entity.Department

interface DeptService {

    // 增加部门
    fun addDept(dept: Department): Boolean

    // 修改部门信息
    fun updateDept(dept: Department): Boolean

    // 删除部门
    fun delDept(deptId: Int): Boolean

    // 查询全部部门
    fun queryAllDept(): List<Department>
    fun queryDept(deptId: Int): Department
}