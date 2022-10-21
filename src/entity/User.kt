package entity

class User {
    var id: Int = 0
    var userName: String = ""
    var pwd: String = ""
    override fun toString(): String {
        return "{id:${id},userName:${userName},pwd:${pwd}}"
    }
}

//class User(
//    _id: Int,
//    _username: String,
//    _pwd: String
//) {
//    var id: Int = _id
//    var userName: String = _username
//    var pwd: String = _pwd
//}