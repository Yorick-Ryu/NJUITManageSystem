function checkInput() {
    console.log("ssssssssssss")
    const name = document.getElementById("name").value
    const pwd = document.getElementById("pwd").value
    if (name === "" || pwd === "") {
        console.log("输入不能为空")
        alert("输入不能为空")
        return false
    }
    return true
}



