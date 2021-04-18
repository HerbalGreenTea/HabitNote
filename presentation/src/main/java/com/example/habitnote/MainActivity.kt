package com.example.habitnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.example.habitnote.di.ActivityModule
import com.example.habitnote.di.ViewModelComponent
import com.example.habitnote.di.ViewModelListHabitModule
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        lateinit var viewModelComponent: ViewModelComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settingViewModelsComponent()
        navigation_view.setNavigationItemSelectedListener(this)
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
                ActivityModule(this)
            )
    }
}