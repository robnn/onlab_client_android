package kurovszky.robin.unicalendar.fragment.adapter;

import android.content.Context;
import android.content.res.Resources;
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
    private Context context;

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
        int hardiness = requirements.get(position).getHardiness();
        Resources res = context.getResources();
        String hardinessString;
        switch (hardiness){
            case 0:
                hardinessString = res.getString(R.string.veryeasy);
                break;
            case 1:
                hardinessString = res.getString(R.string.easy);
                break;
            case 2:
                hardinessString = res.getString(R.string.moderate);
                break;
            case 3:
                hardinessString = res.getString(R.string.hard);
                break;
            case 4:
                hardinessString = res.getString(R.string.veryhard);
                break;
            default:
                hardinessString = res.getString(R.string.veryhard);
                break;
        }
        holder.hardinessText.setText(hardinessString);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(requirements==null)
            return 0;
        return requirements.size();
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

