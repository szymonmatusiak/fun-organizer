package com.projekt.zespolowy.fun_organizer.eventItems

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventInfo.NeedNoID
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_event_items.*
import kotterknife.bindView


class EventItemsActivity : AppCompatActivity(), EventItemsView, EventItemsListener {
    private lateinit var eventItemsPresenter: EventItemsPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val recyclerView: RecyclerView by bindView(R.id.event_items_recycle_view)

    private val groupsItemsList: MutableList<SingleItemModel> = mutableListOf<SingleItemModel>()

    private var groupID: Int = -1
    private var groupName: String = ""
    private var groupDescription: String = ""
    private var groupIsEnough: Boolean = false
    private var iAmHost: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_items)
        eventItemsPresenter = EventItemsPresenter(EventItemsUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        eventItemsPresenter.onStart(this)

        groupID = Integer.parseInt(intent.getStringExtra("groupID"))

        eventItemsPresenter.getAllCategoryItems(groupID)

        groupName = intent.getStringExtra("itemName")
        groupDescription = intent.getStringExtra("description")
        groupIsEnough = intent.getStringExtra("enough").toBoolean()
        iAmHost = intent.getStringExtra("iAmHost").toBoolean()

        event_items_activity_group_name.text = groupName
        event_items_activity_group_description.text = groupDescription

        if (groupIsEnough){
            single_event.setBackgroundColor(Color.parseColor("#81adf6ec"))
            event_items_accept.setText("Resume adding to group")
        }

        if (iAmHost){
            event_items_accept.isEnabled = true
            event_items_accept.setOnClickListener({

                var newStatus: Boolean
                if (groupIsEnough)
                    newStatus = false
                else
                    newStatus = true

                var group: NeedNoID = NeedNoID(groupName, groupDescription, newStatus)
                toast(group.toString())
                eventItemsPresenter.confirmCategory(groupID, group)
                this.finish()
            })
        }
        else{
            event_items_accept.isVisible = false
            event_items_accept.isEnabled = false
        }

        event_items_add_new_item.setOnClickListener({
            var itemName : String
            val builder = AlertDialog.Builder(this)
            val viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_add_single_item, findViewById(android.R.id.content) as ViewGroup, false)
            val inputItem = viewInflated.findViewById(R.id.input_item_name) as EditText
            val inputDescription = viewInflated.findViewById(R.id.input_item_description) as EditText
            val inputPrice = viewInflated.findViewById(R.id.input_item_price) as EditText
            builder.setTitle("Add item")
            builder.setView(viewInflated)

            builder.setPositiveButton("Add", DialogInterface.OnClickListener { dialog, which -> itemName = inputItem.text.toString()
                if (itemName != "" && inputPrice.text.toString() != ""){
                    var price = inputPrice.text.toString()
                    var parsedPrice: Int

                    //Jak strczy czasu: Ogarnąć parsowanie przecinka
                    //var temp = price.substring(0, price.lastIndexOf('.',0) + 3)
                    //toast( temp)
                    //toast( price.substring(0, price.lastIndexOf('.',price.length)))

                    if (price == "")
                        parsedPrice = 0
                    else{
                        val regex = "\\D+".toRegex()
                        val result = regex.replace(price, "")
                        parsedPrice = Integer.parseInt(result)
                    }
                    var model = SingleItemSmallModel(inputItem.text.toString(), inputDescription.text.toString(), parsedPrice)

                    eventItemsPresenter.postItemToCategory(this.groupID, model)
                    eventItemsPresenter.getAllCategoryItems(groupID)
                }
                else {
                    toast("Fields cannot be empty")
                }
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        })

    }

    override fun setItems(it: MutableList<SingleItemModel>?) {

        viewManager = LinearLayoutManager(this)
        viewAdapter = EventItemsAdapter(it!!, iAmHost, this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onDeleteClicked(item: SingleItemModel, fieldID: Int){
        var builder = onCreateDialog(item)
        builder.show()
    }

    //Edit item
    override fun onEventClicked(item: SingleItemModel) {
        var itemName : String
        val builder = AlertDialog.Builder(this)
        val viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_add_single_item, findViewById(android.R.id.content) as ViewGroup, false)
        val inputItem = viewInflated.findViewById(R.id.input_item_name) as EditText
        val inputDescription = viewInflated.findViewById(R.id.input_item_description) as EditText
        val inputPrice = viewInflated.findViewById(R.id.input_item_price) as EditText
        builder.setTitle("Edit item")
        builder.setView(viewInflated)

        inputItem.setText(item.name)
        inputDescription.setText(item.description)

        if (item.value.toString().length > 2)
            inputPrice.setText(item.value.toString().substring(0,item.value.toString().length-2) + "." + item.value.toString().substring(item.value.toString().length-2) + " PLN")
        else if (item.value.toString().length > 1)
            inputPrice.setText(item.value.toString().substring(0,item.value.toString().length-2) + "0." + item.value.toString().substring(item.value.toString().length-2) + " PLN")
        else
            inputPrice.setText(item.value.toString())

        builder.setPositiveButton("Change", DialogInterface.OnClickListener { dialog, which -> itemName = inputItem.text.toString()
            if (itemName != "" && inputPrice.text.toString() != ""){
                var price = inputPrice.text.toString()
                var parsedPrice: Int

                //Jak strczy czasu: Ogarnąć parsowanie przecinka
                //var temp = price.substring(0, price.lastIndexOf('.',0) + 3)
                //toast( temp)
                //toast( price.substring(0, price.lastIndexOf('.',price.length)))

                if (price == "")
                    parsedPrice = 0
                else{
                    val regex = "\\D+".toRegex()
                    val result = regex.replace(price, "")
                    parsedPrice = Integer.parseInt(result)
                }
                var model = SingleItemSmallModel(inputItem.text.toString(), inputDescription.text.toString(), parsedPrice)

                eventItemsPresenter.editItemInCategory(item.id, model, this.groupID)
                eventItemsPresenter.getAllCategoryItems(groupID)
            }
            else {
                toast("Fields cannot be empty")
            }
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

    }

    override fun onStop() {
        super.onStop()
        eventItemsPresenter.onStop()
    }

    override fun myToast(s: String) {
        toast(s)
    }

    override fun notifyOnUpdate() {
        viewAdapter.notifyDataSetChanged()
    }

    fun onCreateDialog(item : SingleItemModel): Dialog {

        //builder dialogu
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure to delete '" + item.name + "'?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                    eventItemsPresenter.deleteItemInCategory(item.id, this.groupID)
                })
                .setNegativeButton("no", DialogInterface.OnClickListener { dialog, id ->
                    // Anuluj
                })
        return builder.create()
    }

}
