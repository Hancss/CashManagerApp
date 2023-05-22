package com.example.proyectof

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ExpandableListView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.proyectof.databinding.ActivityPantallaInicioBinding
import com.example.proyectof.ui.expanded.ExpandedMenuAdapter
import com.example.proyectof.ui.expanded.ExpandedMenuModel
import java.util.ArrayList
import java.util.HashMap

class PantallaInicioActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPantallaInicioBinding

    private val headerList: ArrayList<ExpandedMenuModel> = ArrayList()
    private val childList: HashMap<ExpandedMenuModel, ArrayList<String>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPantallaInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarPantallaInicio.toolbar)

        binding.appBarPantallaInicio.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_pantalla_inicio)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top-level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_manejo_cuentas,
                R.id.nav_movimientos,
                R.id.nav_reportes,
                R.id.nav_firstChild,
                R.id.nav_secondChild,
                R.id.nav_thirdChild,
                R.id.nav_fourthChild,
                R.id.nav_fifthChild
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        prepareListData()

        // Initialize and Assign ExpandableListView
        val expandableListView: ExpandableListView = binding.expandedListView

        // Set Adapter in ExpandableListView
        val mMenuAdapter = ExpandedMenuAdapter(this, headerList, childList, expandableListView)
        expandableListView.setAdapter(mMenuAdapter)

        expandableListView.choiceMode = ExpandableListView.CHOICE_MODE_SINGLE

        expandableListView.setOnGroupClickListener { parent, _, groupPosition, _ ->
            if (parent.isGroupExpanded(groupPosition)) {
                parent.collapseGroup(groupPosition)
            } else {
                parent.expandGroup(groupPosition)
            }
            true
        }

        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val navController = findNavController(R.id.nav_host_fragment_content_pantalla_inicio)
            when (groupPosition) {
                0 -> { // Menu Inicio
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_firstChild)
                    }
                }

                1 -> { // Menu Movimientos
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_secondChild)
                        1 -> navController.navigate(R.id.nav_thirdChild)
                        2 -> navController.navigate(R.id.nav_fourthChild)
                    }
                }

                2 -> { // Menu Manejo de Cuentas
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_fifthChild)
                        1 -> navController.navigate(R.id.nav_fifthChild)
                        2 -> navController.navigate(R.id.nav_fifthChild)
                        3 -> navController.navigate(R.id.nav_fifthChild)
                    }
                }

                3 -> { // Menu Reportes
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_fifthChild)
                        1 -> navController.navigate(R.id.nav_fifthChild)
                        2 -> navController.navigate(R.id.nav_fifthChild)
                    }
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.pantalla_inicio, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_pantalla_inicio)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun prepareListData() {
        val headerInicio = ExpandedMenuModel()
        headerInicio.itemName = "Inicio"
        headerInicio.itemIcon = R.drawable.ic_menu_home
        headerList.add(headerInicio)

        val headerMovimientos = ExpandedMenuModel()
        headerMovimientos.itemName = "Movimientos"
        headerMovimientos.itemIcon = R.drawable.ic_menu_movimientos
        headerList.add(headerMovimientos)

        val headerManejoCuentas = ExpandedMenuModel()
        headerManejoCuentas.itemName = "Manejo de Cuentas"
        headerManejoCuentas.itemIcon = R.drawable.ic_menu_manejo_cuentas
        headerList.add(headerManejoCuentas)

        val headerReportes = ExpandedMenuModel()
        headerReportes.itemName = "Reportes"
        headerReportes.itemIcon = R.drawable.ic_menu_reportes
        headerList.add(headerReportes)

        val listInicio: ArrayList<String> = ArrayList()
        listInicio.add("Child Inicio")
        childList[headerList[0]] = listInicio

        val listMovimientos: ArrayList<String> = ArrayList()
        listMovimientos.add("Child Movimientos 1")
        listMovimientos.add("Child Movimientos 2")
        listMovimientos.add("Child Movimientos 3")
        childList[headerList[1]] = listMovimientos

        val listManejoCuentas: ArrayList<String> = ArrayList()
        listManejoCuentas.add("Child Manejo Cuentas 1")
        listManejoCuentas.add("Child Manejo Cuentas 2")
        listManejoCuentas.add("Child Manejo Cuentas 3")
        listManejoCuentas.add("Child Manejo Cuentas 4")
        childList[headerList[2]] = listManejoCuentas

        val listReportes: ArrayList<String> = ArrayList()
        listReportes.add("Child Reportes 1")
        listReportes.add("Child Reportes 2")
        listReportes.add("Child Reportes 3")
        childList[headerList[3]] = listReportes
    }
}
