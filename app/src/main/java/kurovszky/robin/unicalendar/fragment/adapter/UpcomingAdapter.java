package kurovszky.robin.unicalendar.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.TextView;

import java.util.List;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.model.Requirement;

/**
 * Created by robin on 2016. 11. 26..
 */

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {
    private List<Requirement> requirements;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView subjectName;
        public TextView dateText;
        public TextView typeText;
        public TextView hardinessText;

        public ViewHolder(View v) {
            super(v);
            subjectName = (TextView) v.findViewById(R.id.subjectNameText);
            dateText = (TextView) v.findViewById(R.id.dateText);
            typeText = (TextView) v.findViewById(R.id.typeText);
            hardinessText = (TextView) v.findViewById(R.id.hardinessText);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UpcomingAdapter(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    // Create new views (invoked by the layout manager)

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.req_row, parent, false);
        return new ViewHolder(rowView);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.subjectName.setText(requirements.get(position).getSubject());
        holder.typeText.setText(requirements.get(position).getType());
        holder.dateText.setText(requirements.get(position).getTime());
        holder.hardinessText.setText((Integer.toString(requirements.get(position).getHardiness())));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return requirements.size();
    }
}

