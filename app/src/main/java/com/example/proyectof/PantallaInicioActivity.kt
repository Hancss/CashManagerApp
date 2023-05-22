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
aqui ya no tiene color
class PantallaInicioActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPantallaInicioBinding

    private var viewGroup: View? = null

    private val headerList: ArrayList<ExpandedMenuModel> = ArrayList()
    private val childList: HashMap<ExpandedMenuModel, ArrayList<String>> = HashMap()

    private var selectedChildPosition: Pair<Int, Int>? = null

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

        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_menu_ingreso,
                R.id.nav_menu_gasto,
                R.id.nav_menu_transferencia,
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

        val expandableListView: ExpandableListView = binding.expandedListView
        val mMenuAdapter = ExpandedMenuAdapter(this, headerList, childList, expandableListView)
        expandableListView.setAdapter(mMenuAdapter)

        expandableListView.choiceMode = ExpandableListView.CHOICE_MODE_SINGLE

        expandableListView.setOnGroupClickListener { parent, _, groupPosition, _ ->
            if (parent.isGroupExpanded(groupPosition)) {
                parent.collapseGroup(groupPosition)
                collapseAllGroupsExcept(groupPosition)
            } else {
                parent.expandGroup(groupPosition)
                collapseAllGroupsExcept(groupPosition)
            }
            true
        }

        expandableListView.setOnChildClickListener { parent, view, groupPosition, childPosition, _ ->
            view.isSelected = true

            if (selectedChildPosition != null && selectedChildPosition != Pair(groupPosition, childPosition)) {
                val previousSelectedView = parent.getChildAt(selectedChildPosition!!.second)
                previousSelectedView?.isSelected = false
            }

            selectedChildPosition = Pair(groupPosition, childPosition)
            drawerLayout.closeDrawer(GravityCompat.START)

            when (groupPosition) {
                0 -> { // Menu Inicio
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_firstChild)
                    }
                }
                1 -> { // Menu Movimientos
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_menu_ingreso)
                        1 -> navController.navigate(R.id.nav_menu_gasto)
                        2 -> navController.navigate(R.id.nav_menu_transferencia)
                    }
                }
                2 -> { // Menu Manejo de Cuentas
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_secondChild)
                        1 -> navController.navigate(R.id.nav_thirdChild)
                    }
                }
                3 -> { // Menu Reportes
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_fourthChild)
                        1 -> navController.navigate(R.id.nav_fifthChild)
                    }
                }
            }
            true
        }
    }

    private fun collapseAllGroupsExcept(groupPosition: Int) {
        val expandableListView: ExpandableListView = binding.expandedListView
        for (i in 0 until expandableListView.expandableListAdapter.groupCount) {
            if (i != groupPosition) {
                expandableListView.collapseGroup(i)
            }
        }
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

        val childInicio = ArrayList<String>()
        childInicio.add("Inicio")
        childList[headerInicio] = childInicio

        val childMovimientos = ArrayList<String>()
        childMovimientos.add("Ingreso")
        childMovimientos.add("Gasto")
        childMovimientos.add("Transferencia")
        childList[headerMovimientos] = childMovimientos

        val childManejoCuentas = ArrayList<String>()
        childManejoCuentas.add("Consultar Movimientos")
        childManejoCuentas.add("Realizar Depósito")
        childManejoCuentas.add("Realizar Retiro")
        childManejoCuentas.add("Realizar Transferencia")
        childList[headerManejoCuentas] = childManejoCuentas

        val childReportes = ArrayList<String>()
        childReportes.add("Reporte 1")
        childReportes.add("Reporte 2")
        childReportes.add("Reporte 3")
        childList[headerReportes] = childReportes
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.pantalla_inicio, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_pantalla_inicio)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

