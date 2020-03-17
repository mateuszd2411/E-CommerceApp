package com.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.e_commerce.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {

    private RecyclerView myAddressRecyclerView;
    private Button deliverHereBtn;
    private static AddressAdapter addressAdapter;

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
        deliverHereBtn = findViewById(R.id.delivere_here_btn);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressRecyclerView.setLayoutManager(layoutManager);

        List<AddressModel> addressModelList = new ArrayList<>();
        addressModelList.add(new AddressModel("Mateusz Abuelo", "Krakow korakow krakowa krakow ", "454585", true));
        addressModelList.add(new AddressModel("Mateusz Abuzzelo", "Krakodkow krakoakow ", "484841",false));
        addressModelList.add(new AddressModel("Mateusz Abueaalo", "Kwraakow koaow krakowa krakow ", "54542",false));
        addressModelList.add(new AddressModel("Mateusz Abusselo", "Kraadkow kordow krakowa krakow ", "454585",false));
        addressModelList.add(new AddressModel("Matesusz ccAbuelo", "Ksrakow koradkrakowa krakow ", "64595",false));
        addressModelList.add(new AddressModel("Matessusz Adbuelo", "Krdakow kord krakfowa krakow ", "454585",false));

        int mode = getIntent().getIntExtra("MODE",-1);
        if (mode == SELECT_ADDRESS){
            deliverHereBtn.setVisibility(View.VISIBLE);
        }else {
            deliverHereBtn.setVisibility(View.GONE);
        }
        addressAdapter = new AddressAdapter(addressModelList, mode);
        myAddressRecyclerView.setAdapter(addressAdapter);
        ((SimpleItemAnimator)myAddressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressAdapter.notifyDataSetChanged();
    }

    public static void refreshItem(int deselect, int select){
        addressAdapter.notifyItemChanged(deselect);
        addressAdapter.notifyItemChanged(select);
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
