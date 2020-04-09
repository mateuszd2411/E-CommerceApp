package com.e_commerce;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.e_commerce.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {

    private int previousAddress;
    private LinearLayout addNewAddressBtn;
    private TextView addressSaved;
    private RecyclerView myAddressRecyclerView;
    private Button deliverHereBtn;
    private static AddressAdapter addressAdapter;
    private Dialog loadingDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ///loading dialog

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(this.getDrawable(R.drawable.slider_backgeound));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        ///loading dialog

        previousAddress = DBqueries.selectedAddress;

        myAddressRecyclerView = findViewById(R.id.addresses_recyclerview);
        deliverHereBtn = findViewById(R.id.delivere_here_btn);
        addNewAddressBtn = findViewById(R.id.add_new_addres_btn);
        addressSaved = findViewById(R.id.address_saved);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressRecyclerView.setLayoutManager(layoutManager);

        int mode = getIntent().getIntExtra("MODE",-1);
        if (mode == SELECT_ADDRESS){
            deliverHereBtn.setVisibility(View.VISIBLE);
        }else {
            deliverHereBtn.setVisibility(View.GONE);
        }
        deliverHereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DBqueries.selectedAddress != previousAddress){
                    final int previousAddressIndex = previousAddress;
                    loadingDialog.show();
                    Map<String,Object> updateSelection = new HashMap<>();
                    updateSelection.put("selected_"+String.valueOf(previousAddress+1),false);
                    updateSelection.put("selected_"+String.valueOf(DBqueries.selectedAddress+1),true);

                    previousAddress = DBqueries.selectedAddress;

                    FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA").document("MY_ADDRESSES")
                            .update(updateSelection).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                finish();
                            }else {
                                previousAddress = previousAddressIndex;
                                String error = task.getException().getMessage();
                                Toast.makeText(MyAddressesActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                            loadingDialog.dismiss();
                        }
                    });
                }else {
                    finish();
                }
            }
        });

        addressAdapter = new AddressAdapter(DBqueries.addressModelList, mode);
        myAddressRecyclerView.setAdapter(addressAdapter);
        ((SimpleItemAnimator)myAddressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressAdapter.notifyDataSetChanged();

        addNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAddressIntent = new Intent(MyAddressesActivity.this,AddAddressActivity.class);
                addAddressIntent.putExtra("INTENT","null");
                startActivity(addAddressIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        addressSaved.setText(String.valueOf(DBqueries.addressModelList.size())+ " saved addresses");
    }

    public static void refreshItem(int deselect, int select){
        addressAdapter.notifyItemChanged(deselect);
        addressAdapter.notifyItemChanged(select);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (DBqueries.selectedAddress != previousAddress){
                DBqueries.addressModelList.get(DBqueries.selectedAddress).setSelected(false);
                DBqueries.addressModelList.get(previousAddress).setSelected(true);
                DBqueries.selectedAddress = previousAddress;
            }
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (DBqueries.selectedAddress != previousAddress){
            DBqueries.addressModelList.get(DBqueries.selectedAddress).setSelected(false);
            DBqueries.addressModelList.get(previousAddress).setSelected(true);
            DBqueries.selectedAddress = previousAddress;
        }
        super.onBackPressed();
    }
}
