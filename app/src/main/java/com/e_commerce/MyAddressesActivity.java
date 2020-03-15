package com.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MyAddressesActivity extends AppCompatActivity {

    private RecyclerView myAddressRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAddressRecyclerView = findViewById(R.id.addresses_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressRecyclerView.setLayoutManager(layoutManager);

        List<AddressModel> addressModelList = new ArrayList<>();
        addressModelList.add(new AddressModel("Mateusz Abuelo", "Krakow korakow krakowa krakow ", "454585"));
        addressModelList.add(new AddressModel("Mateusz Abuelo", "Krakow korakow krakowa krakow ", "484841"));
        addressModelList.add(new AddressModel("Mateusz Abuelo", "Krakow korakow krakowa krakow ", "54542"));
        addressModelList.add(new AddressModel("Mateusz Abuelo", "Krakow korakow krakowa krakow ", "454585"));
        addressModelList.add(new AddressModel("Mateusz Abuelo", "Krakow korakow krakowa krakow ", "64595"));
        addressModelList.add(new AddressModel("Mateusz Abuelo", "Krakow korakow krakowa krakow ", "454585"));

        AddressAdapter addressAdapter = new AddressAdapter(addressModelList);
        myAddressRecyclerView.setAdapter(addressAdapter);
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
