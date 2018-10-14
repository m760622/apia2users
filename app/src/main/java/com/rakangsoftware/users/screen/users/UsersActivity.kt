package com.rakangsoftware.users.screen.users

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView

import android.support.design.widget.TextInputEditText
import android.support.v4.app.NavUtils
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.rakangsoftware.users.R
import com.rakangsoftware.users.data.user.User
import com.rakangsoftware.users.databinding.UsersActivityBinding
import kotlinx.android.synthetic.main.users_activity.*

class UsersActivity : AppCompatActivity() {
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "A Navigation "
            subtitle = "Toolbar, bottom, up and drawer navigation"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.abc_ic_menu_overflow_material)
        }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigation)


        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.local_bar -> {
                  //  nav_label.text = getString(R.string.bottom_nav_bar)
                    drawerLayout.closeDrawer(Gravity.START)
                    true
                }
                else -> false
            }
        }

        drawerToggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        )

        drawerLayout.addDrawerListener(drawerToggle)





        /*

val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
bottomNavigationView.setOnNavigationItemReselectedListener {
  when (it.itemId) {
      R.id.action_list -> nav_label.text = "List clicked"
      R.id.action_grid -> nav_label.text = "Grid clicked"
      R.id.action_Map -> nav_label.text = "Map clicked"
      else -> {
          nav_label.text = "Nothing "
      }
  }

        }//bottomNavigationView
*/
        val viewModel = ViewModelProviders.of(this, UsersViewModelFactory(this)).get(UsersViewModel::class.java)
        val binding: UsersActivityBinding = DataBindingUtil.setContentView(this, R.layout.users_activity)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        binding.executePendingBindings()

        val adapter = UserAdapter(viewModel)

        user_list.layoutManager = LinearLayoutManager(this)
        user_list.adapter = adapter

        viewModel.createLiveData.observe(this, object : Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                showCreateDialog(viewModel)
            }
        })
    }//onCreate


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
          //  R.id.shareMenu -> nav_label.text = "Toolbar bar nav pressed."
         //   R.id.settingsMenu -> nav_label.text = "Toolbar Settings nav pressed."
            R.id.CloseMenu -> finish()
            //    android.R.id.home -> onBackPressed()
            //  R.id.home ->  onBackPressed()
         //   R.id.local_florist -> SecondActivity.start(this)
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        //   return true
        return super.onOptionsItemSelected(item)
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
     //   drawerToggle.syncState()
    }


    private fun showCreateDialog(viewModel: UsersViewModel) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add user")

        val view = layoutInflater.inflate(R.layout.user_create_dialog, null)
        builder.setView(view)

        val firstNameView = view.findViewById(R.id.firstName) as TextInputEditText
        val lastNameView = view.findViewById(R.id.lastName) as TextInputEditText

        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            viewModel.createUser(User(firstNameView.text.toString(), lastNameView.text.toString()))
        }

        builder.show()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, UsersActivity::class.java))
        }
    }



}
