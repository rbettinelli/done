package com.ioserv.done;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserTasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserTasksFragment extends Fragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserTasksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserTasksFragment.
     */
    // Rename and change types and number of parameters
    public static UserTasksFragment newInstance(String param1, String param2) {
        UserTasksFragment fragment = new UserTasksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            /*ListView taskList = getView().findViewById(R.id.tasksListView);

            //DummyData
            ArrayList<Task> arrayList = new ArrayList<>();
            arrayList.add(new Task(R.drawable.feed_pet, "31-12-2022", "5 CAD", "daily"));
            arrayList.add(new Task(R.drawable.dishes, "08-12-2022", "15 CAD", "3 days at week"));
            arrayList.add(new Task(R.drawable.mop_clean, "09-12-2022", "20 CAD", "1 day at week"));

            //custom adapter
            ArrayAdapter adapter = new ArrayAdapter<String>(this,tasklist,arrayList);
            taskList.setAdapter(adapter); */

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_tasks, container, false);
    }
}