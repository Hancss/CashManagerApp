package com.example.proyectof.ui.expanded

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import com.example.proyectof.R

class ExpandedMenuAdapter(
    private val mContext: Context,
    private val mListHeader: ArrayList<ExpandedMenuModel>,
    private val mListChild: HashMap<ExpandedMenuModel, ArrayList<String>>,
    private val mExpandableListView: ExpandableListView
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return this.mListHeader.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return mListChild[mListHeader[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.mListHeader[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return mListChild[this.mListHeader[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val header = getGroup(groupPosition) as ExpandedMenuModel
        if (convertView == null) {
            val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.list_header_menu, null)
        }

        val headerName = convertView!!.findViewById(R.id.header_title) as TextView
        val headerIcon = convertView.findViewById(R.id.header_icon) as ImageView

        headerName.setTypeface(null, Typeface.NORMAL)
        headerName.text = header.itemName
        headerIcon.setImageResource(header.itemIcon)

        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val childText = getChild(groupPosition, childPosition) as String

        if (convertView == null) {
            val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.list_child_menu, null)
        }

        val childName = convertView?.findViewById(R.id.child_title) as TextView

        childName.text = childText

        // Establecer el color de fondo del elemento hijo
        if (isLastChild) {
            // Último elemento hijo, sin color de fondo seleccionado
            convertView?.setBackgroundColor(Color.TRANSPARENT)
        } else {
            // Elemento hijo no seleccionado, establecer el color de fondo deseado
            convertView?.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}