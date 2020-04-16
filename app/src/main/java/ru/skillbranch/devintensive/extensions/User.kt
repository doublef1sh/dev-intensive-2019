package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView

fun User.toUserView() : UserView{

    val nickname: String = ""
    val initials = ""
    val status = if(lastVisit == null) "Ещё ни разу не был" else if (isOnline) "online" else "Последний раз в сети был $lastVisit"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickname,
        initials = initials,
        avatar = avatar,
        status = status)
}