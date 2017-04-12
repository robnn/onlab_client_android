package kurovszky.robin.unicalendar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.fragment.adapter.CommentAdapter;
import kurovszky.robin.unicalendar.fragment.adapter.UpcomingAdapter;
import kurovszky.robin.unicalendar.model.Requirement;
import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;

public class SubjectActivity extends AppCompatActivity {

    List<Requirement> requirements = new ArrayList<>();
    RecyclerView recyclerView;
    UpcomingAdapter adapter;

    RecyclerView commentView;
    CommentAdapter commentAdapter;
    RecyclerView.LayoutManager commentManager;
    Button addComment;
    EditText commentText;
    RecyclerView.LayoutManager layoutManager;
    WebService webService;
    List<Comment> comments;
    String subjectName = null;
    User u;
    String finalSubjectName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addComment = (Button) findViewById(R.id.add_comment_button);
        commentText = (EditText) findViewById(R.id.comment_to_add_text);

        recyclerView = (RecyclerView) findViewById(R.id.subjectRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        commentView =  (RecyclerView) findViewById(R.id.commentRecyclerView);
        commentManager = new LinearLayoutManager(this);
        commentView.setLayoutManager(commentManager);

        u = StaticTools.loadUserFromPrefs(getApplicationContext());
        webService = StaticTools.initWebService(getApplicationContext(),u);



        Intent starter = getIntent();

        TextView title = (TextView) findViewById(R.id.toolbar_title);
        if(starter.hasExtra("SubjectName")){
            subjectName = starter.getStringExtra("SubjectName");
            title.setText(subjectName);
            finalSubjectName = subjectName;
            final SubjectActivity this_ = this;
            AsyncGetComments asyncGetComments = new AsyncGetComments(this_);
            asyncGetComments.execute(finalSubjectName);
            addComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Comment toAdd = new Comment();
                    toAdd.setId(9999L);
                    toAdd.setUserId(u.getId());
                    toAdd.setCommentText(commentText.getText().toString());
                    AsyncPostComment asyncPostComment = new AsyncPostComment(this_);
                    asyncPostComment.execute(toAdd);
                }
            });
            requirements = Requirement.listAll(Requirement.class);
            List<Requirement> forSubjectOnly = new ArrayList<>();
            for (Requirement r: requirements){
                if(r.getSubject().equals(subjectName))
                    forSubjectOnly.add(r);
            }
            adapter = new UpcomingAdapter(forSubjectOnly);



            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(R.color.divider).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());
            adapter.setContext(this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            commentView.setHasFixedSize(true);
            commentView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(R.color.divider).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());


        }
    }
    public void startSubjectActivity(View view) {

    }
    private class AsyncGetComments extends AsyncTask<String, Void, Void> {
        ProgressDialog dialog;
        SubjectActivity activity;
        public AsyncGetComments(SubjectActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected Void doInBackground(String... subjects) {
            Subject s = webService.getSubjectByName(subjects[0]);
            comments = webService.getCommentsBySubject(s);
            for(Comment i : comments){
                i.setUserName(webService.getNameById(i.getUserId()));
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Getting comment list");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            commentAdapter = new CommentAdapter(comments);
            commentAdapter.setContext(activity);
            commentView.setAdapter(commentAdapter);
            commentAdapter.notifyDataSetChanged();
            dialog.dismiss();
        }
    }
    private class AsyncPostComment extends AsyncTask<Comment, Void, Void> {
        ProgressDialog dialog;
        SubjectActivity activity;
        public AsyncPostComment(SubjectActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected Void doInBackground(Comment... comments) {
            Subject s = webService.getSubjectByName(subjectName);
            comments[0].setSubjectId(s.getId());
            webService.addComment(comments[0]);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Adding comment");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AsyncGetComments asyncGetComments = new AsyncGetComments(activity);
            asyncGetComments.execute(finalSubjectName);
            dialog.dismiss();
        }
    }
}
