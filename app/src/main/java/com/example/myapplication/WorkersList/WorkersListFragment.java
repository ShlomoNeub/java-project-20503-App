package com.example.myapplication.WorkersList;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.Views.Fragments.DateListFragment;
import com.example.myapplication.Model.Profile;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.UserViewModel;
import com.example.myapplication.ViewModel.WorkersViewModel;
import com.example.myapplication.api.Api;
import com.example.myapplication.databinding.FragmentWorkersListBinding;

import java.util.ArrayList;

public class WorkersListFragment extends DateListFragment<Profile> {


    WorkersViewModel workersViewModel;


    public WorkersListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
                            ) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        binding.headerDatePicker.setText("Workers List");
        workersViewModel = new ViewModelProvider(this).get(WorkersViewModel.class);
        UserViewModel userViewModel =
                new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        workersViewModel.getData(
                userViewModel.getUserState().getValue().getId(),
                userViewModel.getUserState().getValue().getAuthToken(),
                this::onDataArrived);
        binding.dpDatePicker.setVisibility(View.GONE);
        binding.btnDatePicker.setVisibility(View.GONE);
        return view;

    }

    @Override
    protected void onPickClicked(View view, String pickerValue) {
    }

    @Override
    public void onDataArrived(@Nullable ArrayList<Profile> profiles, @Nullable Api.ResponseError error, @Nullable Throwable t) {
        super.onDataArrived(profiles, error, t);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onItemClicked(Profile model, View view) {
        new AlertDialog.Builder(requireContext())
                .setTitle("More Details:")
                .setMessage("Phone Number: " + model.getPhoneNumber() + "\n" +
                            "E-Mail: " + model.getEmail())
                .setPositiveButton("Ok", null)
                .create().show();
    }


}