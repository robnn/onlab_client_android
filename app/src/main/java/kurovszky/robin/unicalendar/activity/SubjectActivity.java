package kurovszky.robin.unicalendar.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.fragment.adapter.CommentAdapter;
import kurovszky.robin.unicalendar.fragment.adapter.UpcomingAdapter;
import kurovszky.robin.unicalendar.fragment.error.ErrorFragmentImpl;
import kurovszky.robin.unicalendar.model.Requirement;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.error.ErrorObject;
import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;
import kurovszky.robin.unicalendar.web_service.type.Transaction;

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
    ErrorFragmentImpl errorFragment;
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


        AsyncInitWebService asyncInitWebService = new AsyncInitWebService(this);
        asyncInitWebService.execute(u);

//        webService = StaticTools.initWebService(getApplicationContext(),u);



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

    private class AsyncInitWebService extends AsyncTask<User, Void, ErrorObject>{

        ProgressDialog dialog;
        SubjectActivity activity;

        public AsyncInitWebService(SubjectActivity activity) {
            this.activity = activity;
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected ErrorObject doInBackground(User... users) {
            try {
                webService = StaticTools.initWebService(getApplicationContext(), users[0], Transaction.COMMENT);
            } catch (BaseException e) {
                return new ErrorObject(e.getErrorObject().getErrorCode(),getApplication());
            }
            return new ErrorObject(ErrorCode.NO_ERROR,getApplication());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Initializing connection...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(ErrorObject errorObject) {
            super.onPostExecute(errorObject);
            ErrorCode errorCode = errorObject.getErrorCode();
            String message = errorObject.getMessage();
            dialog.dismiss();
            if(errorCode == ErrorCode.NO_ERROR)
                return;
            errorFragment = ErrorFragmentImpl.newInstance(message);
            errorFragment.show(activity.getSupportFragmentManager(), "DIALOG");
        }
    }
    private class AsyncGetComments extends AsyncTask<String, Void, ErrorObject> {
        ProgressDialog dialog;
        SubjectActivity activity;
        public AsyncGetComments(SubjectActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected ErrorObject doInBackground(String... subjects) {
            Subject s = null;
            try {
                s = webService.getSubjectByName(subjects[0]);
                if(s == null){
                    return new ErrorObject(ErrorCode.CANNOT_FIND_SUBJECT_ON_SERVER,activity);
                }
                comments = webService.getCommentsBySubject(s);
                for(Comment i : comments){
                    i.setUserName(webService.getNameById(i.getUserId()));
                }
            } catch (BaseException e) {
                return new ErrorObject(ErrorCode.SERVER_DOWN,activity);
            }
            return new ErrorObject(ErrorCode.NO_ERROR,activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Getting comment list");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(ErrorObject errorObject) {
            super.onPostExecute(errorObject);
            ErrorCode errorCode = errorObject.getErrorCode();
            String message = errorObject.getMessage();
            dialog.dismiss();
            if(errorCode == ErrorCode.NO_ERROR){
                commentAdapter = new CommentAdapter(comments);
                commentAdapter.setContext(activity);
                commentView.setAdapter(commentAdapter);
                commentAdapter.notifyDataSetChanged();
                return;
            }
            errorFragment = ErrorFragmentImpl.newInstance(message);
            errorFragment.show(activity.getSupportFragmentManager(), "DIALOG");

        }
    }
    private class AsyncPostComment extends AsyncTask<Comment, Void, ErrorObject> {
        ProgressDialog dialog;
        SubjectActivity activity;
        public AsyncPostComment(SubjectActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected ErrorObject doInBackground(Comment... comments) {
            Subject s = null;
            try {
                s = webService.getSubjectByName(subjectName);
                if(s == null){
                    return new ErrorObject(ErrorCode.CANNOT_FIND_SUBJECT_ON_SERVER,activity);
                }
                comments[0].setSubjectId(s.getId());
                webService.addComment(comments[0]);
            } catch (BaseException e) {
                errorFragment = ErrorFragmentImpl.newInstance(e.getMessage());
                errorFragment.show(activity.getSupportFragmentManager(), "DIALOG");
                return new ErrorObject(ErrorCode.SERVER_DOWN,activity);
            }
            return new ErrorObject(ErrorCode.NO_ERROR,activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Adding comment");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(ErrorObject errorObject) {
            super.onPostExecute(errorObject);
            ErrorCode errorCode = errorObject.getErrorCode();
            String message = errorObject.getMessage();
            dialog.dismiss();
            if(errorCode == ErrorCode.NO_ERROR) {
                AsyncGetComments asyncGetComments = new AsyncGetComments(activity);
                asyncGetComments.execute(finalSubjectName);
                return;
            }
            errorFragment = ErrorFragmentImpl.newInstance(message);
            errorFragment.show(activity.getSupportFragmentManager(), "DIALOG");

        }
    }
}
