package com.example.habitnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.habitnote.di.modules.FragmentActivityModule
import com.example.habitnote.di.сomponents.ViewModelComponent
import com.example.habitnote.di.modules.viewModelModules.ViewModelListHabitModule
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_header_navigation_menu.*
import kotlinx.android.synthetic.main.item_header_navigation_menu.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        lateinit var viewModelComponent: ViewModelComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settingViewModelsComponent()
        navigation_view.setNavigationItemSelectedListener(this)

        Glide.with(applicationContext)
                .load("https://tproger.ru/s3/uploads/2015/03/android-development.jpg")
                .placeholder(R.drawable.ic_user_placeholder)
                .error(R.drawable.ic_error)
                .transform(CircleCrop())
                .into(navigation_view.getHeaderView(0).user_photo)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val controller = fragment.findNavController()

        when(item.itemId) {
            R.id.item_main_screen -> controller.popBackStack()
            R.id.item_description_app -> controller.navigate(R.id.descriptionAppFragment)
        }

        return true
    }

    private fun settingViewModelsComponent() {
        viewModelComponent = App.appComponent
            .viewModelComponent(
                ViewModelListHabitModule(),
                FragmentActivityModule(this)
            )
    }
}