package com.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);

        /////////// banner Slider

        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.home, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.error_icon, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.email_icon_green, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.email_icon, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_menu_camera, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.cart_black, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.profile_placeholder, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.home, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.error_icon, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.email_icon_green, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.email_icon, "#077AE4"));
        /////////// banner Slider

        ////// horizontal Product Layout
        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sport, "Motorola g77", "Good Procesor New HIT!", "399$"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.agd, "Motorola g77", "Good Procesor New HIT!", "399$"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.garden, "Motorola g77", "Good Procesor New HIT!", "399$"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone1, "Motorola g77", "Good Procesor New HIT!", "399$"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.dress, "Motorola g77", "Good Procesor New HIT!", "399$"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.pc, "Motorola g77", "Good Procesor New HIT!", "399$"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.car, "Motorola g77", "Good Procesor New HIT!", "399$"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.food, "Motorola g77", "Good Procesor New HIT!", "399$"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bath, "Motorola g77", "Good Procesor New HIT!", "399$"));
        ////// horizontal Product Layout
        /////////////////////

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#ff0000"));
        homePageModelList.add(new HomePageModel(2,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#000000"));
        homePageModelList.add(new HomePageModel(3,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner,"#fff000"));
        homePageModelList.add(new HomePageModel(0,sliderModelList));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.main_search_icon){

            return true;
        } else if (id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
