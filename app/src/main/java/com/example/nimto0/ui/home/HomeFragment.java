package com.example.nimto0.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nimto0.R;
import com.example.nimto0.adapters.ThemeAdapter;
import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.models.ThemeDetail;
import com.example.nimto0.models.PaginatedThemes;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MainInterface.AllThemesListener {
    RecyclerView recyclerView;
    ThemeAdapter themeAdapter;
    ProgressBar PBLoading;
    private NestedScrollView nestedSV;
    GridLayoutManager gridLayoutManager;
    SwipeRefreshLayout swiperefreshlayout;
    LinearLayout mainLayout;
    boolean nestedTriggerHasNotHit=true;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nestedSV = view.findViewById(R.id.nestedSV);
        recyclerView = view.findViewById(R.id.dataList);
        PBLoading = view.findViewById(R.id.PBLoading);
        swiperefreshlayout = view.findViewById(R.id.swiperefreshlayout);

        gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);


        //PARMANENT

        if(ResourceManager.getPaginatedThemes().getListOfThemes().size() != 0){
            themeAdapter = new ThemeAdapter((ArrayList<ThemeDetail>) ResourceManager.getPaginatedThemes().getListOfThemes(), getActivity());
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(themeAdapter);
        }else{
            Utils.displayLongToast(getContext(), R.string.no_books);
        }



        //mainLayout.setBackground(getResources().getDrawable(R.drawable.no_books_msg));


        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {

                    if(nestedTriggerHasNotHit){
                        if (Utils.isConnected(getContext())) {
                            nestedTriggerHasNotHit = false;
                            PBLoading.setVisibility(View.VISIBLE);
                            getNextDataFromAPI();
                        } else {
                            Utils.displayLongToast(getActivity(), R.string.noInternet);
                        }
                    }
                }
            }
        });


        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Utils.isConnected(getActivity())){
                    getInitialDataFromAPI();

                }else{
                    Utils.displayShortToast(getContext(),"Please check your Internet connection.");
                    //setting Refreshing to false
                    swiperefreshlayout.setRefreshing(false);
                }
            }
        });


    }

    private void getNextDataFromAPI() {
        PaginatedThemes p = ResourceManager.getPaginatedThemes();
        if(p.getNext()==null) {
            nestedTriggerHasNotHit=true;
            Utils.displayShortToast(getActivity(), "That's all themes we have in our theme pool.");
            PBLoading.setVisibility(View.GONE);
            return;
        }

        WebServices.NextPaginatedCaller(p.getNext(),new MainInterface.NextPaginatedThemesListener() {
            @Override
            public void pagThemesReceived(PaginatedThemes paginatedThemes) {
                List<ThemeDetail> themes= p.getListOfThemes();
                themes.addAll(paginatedThemes.getListOfThemes());
                paginatedThemes.setListOfThemes((ArrayList<ThemeDetail>) themes);
                ResourceManager.setPaginatedThemes(paginatedThemes);

                PBLoading.setVisibility(View.GONE);
                Utils.displayShortToast(getContext(),"Themes Successfully Updated. Enjoy.");

                themeAdapter = new ThemeAdapter((ArrayList<ThemeDetail>) themes,getActivity());
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(themeAdapter);
                nestedTriggerHasNotHit = true;
            }

            @Override
            public void pagThemesFail(String msg) {
                Utils.displayShortToast(getContext(),msg);
                PBLoading.setVisibility(View.GONE);
                Utils.displayShortToast(getContext(),"Please try again.Make your stay comfortable.");
                nestedTriggerHasNotHit = true;
            }
        });
    }

    private void getInitialDataFromAPI() {
        WebServices.AllThemeCaller((MainInterface.AllThemesListener)  HomeFragment.this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void areAllThemesReceived(boolean flag, List<ThemeDetail> allThemes) {

        if(flag){
            if(allThemes.size() != 0){
                themeAdapter = new ThemeAdapter((ArrayList<ThemeDetail>) allThemes, getActivity());
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(themeAdapter);

            }else{
                Utils.displayLongToast(getContext(), R.string.no_books);
            }

        }
        swiperefreshlayout.setRefreshing(false);
    }


}