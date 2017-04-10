package kurovszky.robin.unicalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import kurovszky.robin.unicalendar.fragment.adapter.CommentAdapter;
import kurovszky.robin.unicalendar.fragment.adapter.UpcomingAdapter;
import kurovszky.robin.unicalendar.model.Requirement;
import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.model.Comment;
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

        final User u = StaticTools.loadUserFromPrefs(getApplicationContext());
        final WebService webService = StaticTools.initWebService(getApplicationContext(),u);



        Intent starter = getIntent();
        String subjectName = null;
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        if(starter.hasExtra("SubjectName")){
            subjectName = starter.getStringExtra("SubjectName");
            title.setText(subjectName);
            Subject s = webService.getSubjectByName(subjectName);
            List<Comment> comments = webService.getCommentsBySubject(s);
            final String finalSubjectName = subjectName;
            addComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Subject subject = webService.getSubjectByName(finalSubjectName);
                    Comment toAdd = new Comment();
                    toAdd.setId(9999L);
                    toAdd.setUserId(u.getId());
                    toAdd.setSubjectId(subject.getId());
                    toAdd.setCommentText(commentText.getText().toString());
                    webService.addComment(toAdd);
                }
            });
            for(Comment i : comments){
                i.setUserName(webService.getNameById(i.getUserId()));
            }
            requirements = Requirement.listAll(Requirement.class);
            List<Requirement> forSubjectOnly = new ArrayList<>();
            for (Requirement r: requirements){
                if(r.getSubject().equals(subjectName))
                    forSubjectOnly.add(r);
            }
            adapter = new UpcomingAdapter(forSubjectOnly);

            commentAdapter = new CommentAdapter(comments);
            commentAdapter.setContext(this);

            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(R.color.divider).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());
            adapter.setContext(this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            commentView.setHasFixedSize(true);
            commentView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(R.color.divider).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());

            commentView.setAdapter(commentAdapter);
            commentAdapter.notifyDataSetChanged();
        }
    }
    public void startSubjectActivity(View view) {

    }
}
