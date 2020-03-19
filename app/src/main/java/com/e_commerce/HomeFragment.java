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
    private RecyclerView homePageRecyclerview;
    private List<CategoryModel> categoryModelList;
    private FirebaseFirestore firebaseFirestore;
    private HomePageAdapter adapter;

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
//
//        ////// horizontal Product Layout
//        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sport, "Motorola g77", "Good Procesor New HIT!", "399$"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.agd, "Motorola g77", "Good Procesor New HIT!", "399$"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.garden, "Motorola g77", "Good Procesor New HIT!", "399$"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone1, "Motorola g77", "Good Procesor New HIT!", "399$"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.dress, "Motorola g77", "Good Procesor New HIT!", "399$"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.pc, "Motorola g77", "Good Procesor New HIT!", "399$"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.car, "Motorola g77", "Good Procesor New HIT!", "399$"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.food, "Motorola g77", "Good Procesor New HIT!", "399$"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bath, "Motorola g77", "Good Procesor New HIT!", "399$"));
//        ////// horizontal Product Layout
        /////////////////////

        homePageRecyclerview = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecyclerview.setLayoutManager(testingLayoutManager);
        final List<HomePageModel> homePageModelList = new ArrayList<>();
        adapter = new HomePageAdapter(homePageModelList);
        homePageRecyclerview.setAdapter(adapter);

        firebaseFirestore.collection("CATEGORIES")
                .document("HOME")
                .collection("TOP_DEALS").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){

                                if ((long)documentSnapshot.get("view_type") == 0){
                                    List<SliderModel> sliderModelList = new ArrayList<>();
                                    long no_of_banners = (long)documentSnapshot.get("no_of_banners");
                                    for (long x = 1; x< no_of_banners + 1;x++){
                                        sliderModelList.add(new SliderModel(documentSnapshot.get("banner_"+x).toString()
                                                , documentSnapshot.get("banner_"+x+"_background").toString()));
                                    }
                                    homePageModelList.add(new HomePageModel(0,sliderModelList));
                                } else if ((long)documentSnapshot.get("view_type") == 1){
                                    homePageModelList.add(new HomePageModel(1,documentSnapshot.get("strip_ad_banner").toString(),
                                            documentSnapshot.get("background").toString()));
                                } else if ((long)documentSnapshot.get("view_type") == 2){
                                    List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
                                    long no_of_products = (long)documentSnapshot.get("no_of_products");
                                    for (long x = 1; x< no_of_products + 1;x++){
                                        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_ID_"+x).toString()
                                        ,documentSnapshot.get("product_image_"+x).toString()
                                        ,documentSnapshot.get("product_title_"+x).toString()
                                        ,documentSnapshot.get("product_subtitle_"+x).toString()
                                        ,documentSnapshot.get("product_price"+x).toString()));

                                    }
                                    homePageModelList.add(new HomePageModel(2,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_background").toString(),horizontalProductScrollModelList));

                                } else if ((long)documentSnapshot.get("view_type") == 3){
                                    List<HorizontalProductScrollModel> GridLayoutModelList = new ArrayList<>();
                                    long no_of_products = (long)documentSnapshot.get("no_of_products");
                                    for (long x = 1; x< no_of_products + 1;x++){
                                        GridLayoutModelList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_ID_"+x).toString()
                                                ,documentSnapshot.get("product_image_"+x).toString()
                                                ,documentSnapshot.get("product_title_"+x).toString()
                                                ,documentSnapshot.get("product_subtitle_"+x).toString()
                                                ,documentSnapshot.get("product_price"+x).toString()));

                                    }
                                    homePageModelList.add(new HomePageModel(3,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_background").toString(),GridLayoutModelList));

                                }

                            }
                            adapter.notifyDataSetChanged();
                        }else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });




        /////////////////////

        return view;
    }
}
