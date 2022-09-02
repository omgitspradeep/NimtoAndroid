package com.pk.nimto.ui.myorders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pk.nimto.R;
import com.pk.nimto.adapters.MyOrdersRVAdapter;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.models.CustOrderModel;
import com.pk.nimto.utils.ResourceManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class MyordersFragment extends Fragment implements MainInterface.orderDeleteListner {

    List<CustOrderModel> orderList;
    RecyclerView recyclerView;
    MyOrdersRVAdapter adapter;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myorders, container, false);
    }


    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set up the RecyclerView
        orderList = ResourceManager.getCustomerOrders();
        recyclerView = view.findViewById(R.id.rv_myorders);
        progressBar = view.findViewById(R.id.fragment_order_PB);
        inflateData(orderList);

    }

    private void inflateData(List<CustOrderModel> orderList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyOrdersRVAdapter(getActivity(),orderList,MyordersFragment.this, progressBar);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onOrderDeleteResponse(boolean flag, List<CustOrderModel> customerOrderList) {
        inflateData(customerOrderList);
    }
}