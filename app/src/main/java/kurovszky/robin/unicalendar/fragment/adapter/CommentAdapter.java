package kurovszky.robin.unicalendar.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.web_service.model.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> comments;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView commentWriterUsername;
        TextView commentText;


        ViewHolder(View v) {
            super(v);
            commentWriterUsername = (TextView) v.findViewById(R.id.comment_writer_user);
            commentText = (TextView) v.findViewById(R.id.comment_text);

        }
    }

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.commentWriterUsername.setText(comments.get(position).getUserName());
        holder.commentText.setText(comments.get(position).getCommentText());
    }

    @Override
    public int getItemCount() {
        if(comments==null)
            return 0;
        return comments.size();
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
