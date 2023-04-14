package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Profile;
import com.example.myapplication.ViewModel.UserViewModel;
import com.example.myapplication.ViewModel.WorkersViewModel;
import com.example.myapplication.databinding.FragmentWorkersListBinding;

import java.util.ArrayList;

public class WorkersListFragment extends Fragment implements ViewModelStoreOwner {
    private FragmentWorkersListBinding binding;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.line1);
        }

        public TextView getTextView() {
            return textView;
        }
    }


    ArrayList<Profile> profileArrayList= new ArrayList<Profile>() {{
        add(new Profile("tal","tal","tal@gmail.com","050-1234567",0));
        add(new Profile("Gal","Gal","Gal@gmail.com","050-1234567",1));
        add(new Profile("Yuval","Yuval","Yuval@gmail.com","050-1234567",2));
        add(new Profile("tal","tal","tal@gmail.com","050-1234567",3));
        add(new Profile("Gal","Gal","Gal@gmail.com","050-1234567",4));
        add(new Profile("Yuval","Yuval","Yuval@gmail.com","050-1234567",5));
        add(new Profile("tal","tal","tal@gmail.com","050-1234567",6));
        add(new Profile("Gal","Gal","Gal@gmail.com","050-1234567",7));
        add(new Profile("Yuval","Yuval","Yuval@gmail.com","050-1234567",8));
        add(new Profile("tal","tal","tal@gmail.com","050-1234567",9));
        add(new Profile("Gal","Gal","Gal@gmail.com","050-1234567",10));
    }};

    public WorkersListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        WorkersViewModel workersViewModel = new ViewModelProvider(this).get(WorkersViewModel.class);
        UserViewModel userViewModel  = new ViewModelProvider(this).get(UserViewModel.class);

//        WorkersViewModel.getData(
//                userViewModel.getUserState().getValue().getId(),
//                userViewModel.getUserState().getValue().getAuthToken(),
//                ((shifts, responseError, throwable) -> {
//                    Log.d("Testings", "onCreateView: Finish loading");
//                })
//        );

        binding = FragmentWorkersListBinding.inflate(inflater, container, false);
        binding.rvWorkersList.setAdapter(new RecyclerView.Adapter<ViewHolder>() {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
                View view =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_one_line_dis1,
                                parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                (holder).getTextView().setText("Full Name: "+profileArrayList.get(position).getFirst_Name()+"\t\t"+
                        profileArrayList.get(position).getLast_Name()+"\nPhone Number: "+
                        profileArrayList.get(position).getPhone_Number());
                (holder).getTextView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(requireContext())
                                .setTitle(((TextView) v).getText().toString())
                                .setMessage(profileArrayList.get(holder.getAdapterPosition()).getEmail())
                                .setPositiveButton("Ok", (dialog, which) -> {})
                                .setOnDismissListener(dialog -> {})
                                .create().show();
                    }
                });
            }

            @Override
            public int getItemCount() {
                return profileArrayList.size();
            }
        });
        binding.rvWorkersList.setLayoutManager(new LinearLayoutManager(requireContext()));
        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
