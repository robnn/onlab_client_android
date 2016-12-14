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
import android.widget.TextView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.fragment.adapter.UpcomingAdapter;
import kurovszky.robin.unicalendar.model.Requirement;

public class SubjectActivity extends AppCompatActivity {

    List<Requirement> requirements = new ArrayList<>();
    RecyclerView recyclerView;
    UpcomingAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.subjectRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Intent starter = getIntent();
        String subjectName = null;
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        if(starter.hasExtra("SubjectName")){
            subjectName = starter.getStringExtra("SubjectName");
            title.setText(subjectName);
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
        }
    }
    public void startSubjectActivity(View view) {

    }
}
