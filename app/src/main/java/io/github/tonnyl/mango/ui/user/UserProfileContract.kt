package io.github.tonnyl.mango.ui.user

import io.github.tonnyl.mango.data.User
import io.github.tonnyl.mango.mvp.BasePresenter
import io.github.tonnyl.mango.mvp.BaseView

/**
 * Created by lizhaotailang on 2017/6/28.
 */
interface UserProfileContract {

    interface View : BaseView<Presenter> {

        fun showUserInfo(user: User)

        fun setFollowable(followable: Boolean)

        fun setFollowing(isFollowing: Boolean)

    }

    interface Presenter : BasePresenter {

        fun checkFollowing()

        fun toggleFollow()

        fun getUser(): User

    }

}