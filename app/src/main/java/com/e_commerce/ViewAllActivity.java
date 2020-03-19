package com.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Deals of the Day");
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        gridView = findViewById(R.id.grid_view);

        int layout_code = getIntent().getIntExtra("layout_code", -1);

        if (layout_code == 0) {
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            List<WishlistModel> wishlistModelList = new ArrayList<>();
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 1, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 0, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 2, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 4, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 1, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 1, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 0, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 2, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 4, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image, "Motorrola f75", 1, "3", 155, "499$ /-", "599$ /-", "Cash on Delivery"));

            WishlistAdapter adapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else if (layout_code == 1) {

            gridView.setVisibility(View.VISIBLE);
            List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();


            GridProductlayoutAdapter gridProductlayoutAdapter = new GridProductlayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductlayoutAdapter);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
