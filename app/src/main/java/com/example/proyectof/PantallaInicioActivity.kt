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

    private var viewGroup: View? = null

    private val headerList: ArrayList<ExpandedMenuModel> = ArrayList()
    private val childList: HashMap<ExpandedMenuModel, ArrayList<String>> =
        HashMap()

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
        // menu should be considered as top level destinations.
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

        //Initialize and Assign ExpandableListView
        val expandableListView: ExpandableListView = binding.expandedListView


        //Set Adapter in ExpandableListView :
        val mMenuAdapter = ExpandedMenuAdapter(this, headerList, childList, expandableListView)
        expandableListView.setAdapter(mMenuAdapter)

        expandableListView.choiceMode = ExpandableListView.CHOICE_MODE_SINGLE

        expandableListView.setOnGroupClickListener { parent, _, groupPosition, _ ->
            when (groupPosition) {
                0 -> {
                    if (parent.isGroupExpanded(groupPosition)) {
                        parent.collapseGroup(groupPosition)
                        parent.collapseGroup(1)
                        parent.collapseGroup(2)
                        parent.collapseGroup(3)
                    } else {
                        parent.expandGroup(groupPosition)
                        parent.collapseGroup(1)
                        parent.collapseGroup(2)
                        parent.collapseGroup(3)
                    }
                }

                1 -> {
                    if (parent.isGroupExpanded(groupPosition)) {
                        parent.collapseGroup(groupPosition)
                        parent.collapseGroup(0)
                        parent.collapseGroup(2)
                        parent.collapseGroup(3)
                    } else {
                        parent.expandGroup(groupPosition)
                        parent.collapseGroup(0)
                        parent.collapseGroup(2)
                        parent.collapseGroup(3)
                    }
                }

                2 -> {
                    if (parent.isGroupExpanded(groupPosition)) {
                        parent.collapseGroup(groupPosition)
                        parent.collapseGroup(0)
                        parent.collapseGroup(1)
                        parent.collapseGroup(3)
                    } else {
                        parent.expandGroup(groupPosition)
                        parent.collapseGroup(0)
                        parent.collapseGroup(1)
                        parent.collapseGroup(3)
                    }
                }

                3 -> {
                    if (parent.isGroupExpanded(groupPosition)) {
                        parent.collapseGroup(groupPosition)
                        parent.collapseGroup(0)
                        parent.collapseGroup(1)
                        parent.collapseGroup(2)
                    } else {
                        parent.expandGroup(groupPosition)
                        parent.collapseGroup(0)
                        parent.collapseGroup(1)
                        parent.collapseGroup(2)
                    }
                }
            }
            true
        }

        expandableListView.setOnChildClickListener { parent, view, groupPosition, childPosition, _ ->
            view.isSelected = true
            viewGroup?.setBackgroundColor(Color.parseColor("#FFFFFF"))
            viewGroup = view
            viewGroup?.setBackgroundColor(Color.parseColor("#2ba89c"))
            drawerLayout.closeDrawer(GravityCompat.START)

            when (groupPosition) {
                0 -> { // Menu Inicio
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_firstChild)
                    }
                }

                1 -> {  //Menu Movimientos
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_secondChild)
                        1 -> navController.navigate(R.id.nav_thirdChild)
                        2 -> navController.navigate(R.id.nav_fourthChild)
                    }
                }

                2 -> {  //Menu Manejo de Cuentas
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_fifthChild)
                        1 -> navController.navigate(R.id.nav_fifthChild)
                        2 -> navController.navigate(R.id.nav_fifthChild)
                        3 -> navController.navigate(R.id.nav_fifthChild)

                    }
                }

                3 -> {  //Menu Reportes
                    when (childPosition) {
                        0 -> navController.navigate(R.id.nav_fifthChild)
                        1 -> navController.navigate(R.id.nav_fifthChild)
                        2 -> navController.navigate(R.id.nav_fifthChild)
                    }
                }
            }

            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
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

        val childInicio = ArrayList<String>()
        childInicio.add("Inicio")
        childList[headerInicio] = childInicio

        val childMovimientos = ArrayList<String>()
        childMovimientos.add("Ingreso")
        childMovimientos.add("Gasto")
        childMovimientos.add("Transferencias")
        childList[headerMovimientos] = childMovimientos

        val childManejoCuentas = ArrayList<String>()
        childManejoCuentas.add("Consultar Movimientos")
        childManejoCuentas.add("Agregar Cuenta")
        childManejoCuentas.add("Cambiar Nombre de la Cuenta")
        childManejoCuentas.add("Eliminar una Cuenta")
        childList[headerManejoCuentas] = childManejoCuentas

        val childReportes = ArrayList<String>()
        childReportes.add("Resumen Mensual")
        childReportes.add("Últimos Movimientos")
        childReportes.add("Categorías")
        childList[headerReportes] = childReportes

    }
}