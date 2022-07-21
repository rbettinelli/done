package com.ioserv.done;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private int mResource;

    public TaskAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Task> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);
        ImageView imageView = convertView.findViewById(R.id.taskImage);
        TextView dueDateTxt = convertView.findViewById(R.id.dueDateText);
        TextView rewardTxt = convertView.findViewById(R.id.rewardText);
        TextView frecuencyTxt = convertView.findViewById(R.id.frecuencyText);

        imageView.setImageResource(getItem(position).getImage());
        dueDateTxt.setText(dueDateTxt.getText() + getItem(position).getDueDate());
        rewardTxt.setText(rewardTxt.getText() + getItem(position).getReward());
        frecuencyTxt.setText(frecuencyTxt.getText() + getItem(position).getTaskFrecuency());

        return convertView;
    }
}
