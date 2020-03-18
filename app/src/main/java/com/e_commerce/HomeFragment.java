package com.e_commerce;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;
    private List<CategoryModel> categoryModelList;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<CategoryModel>();
        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();
                        }else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

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

        testing = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

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
        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#ff0000"));
        homePageModelList.add(new HomePageModel(2,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#000000"));
        homePageModelList.add(new HomePageModel(3,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner,"#fff000"));
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#ff0000"));
        homePageModelList.add(new HomePageModel(2,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#000000"));
        homePageModelList.add(new HomePageModel(3,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deals of Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner,"#fff000"));


        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /////////////////////

        return view;
    }
}
