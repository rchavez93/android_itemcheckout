package com.example.itemcheckout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.itemcheckout.databinding.ActivityItemDetailBinding;

public class ItemDetailHostActivity extends AppCompatActivity {

    private Button btnUpdate;

    private SQLiteOperations sqliteOperations;

    private int itemID;
    private String itemName;
    private String itemCost;
    private String itemDescription;
    private String itemStock;

    public ItemDetailHostActivity() {
        this.itemName = "";
        this.itemCost = "";
        this.itemDescription = "";
        this.itemStock = "";
    }

    public ItemDetailHostActivity(String itemName, String itemCost, String itemDescription, String itemStock, int itemID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemDescription = itemDescription;
        this.itemStock = itemStock;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemStock() {
        return itemStock;
    }

    public void setItemStock(String itemStock) {
        this.itemStock = itemStock;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityItemDetailBinding binding = ActivityItemDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_item_detail);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.
                Builder(navController.getGraph())
                .build();

        sqliteOperations = new SQLiteOperations(ItemDetailHostActivity.this);

        /* Initialize variables here*/
        itemName = new String("Banana");
        itemCost = new String("1.29");
        itemDescription = new String("A yellow, long banana.");
        itemStock = new String("5");

        sqliteOperations.addNewItem(itemName, itemCost, itemDescription, itemStock, itemID);
        Toast.makeText(ItemDetailHostActivity.this, "Default item has been added!", Toast.LENGTH_SHORT).show();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        /* Add Buttons, Text, ect here */
        btnUpdate = findViewById(R.id.buttonUpdate);

        /* Add activity handlers below */

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(ItemDetailHostActivity.this, ViewItems.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_item_detail);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}