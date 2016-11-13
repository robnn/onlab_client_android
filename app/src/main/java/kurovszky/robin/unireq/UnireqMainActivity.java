package kurovszky.robin.unireq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class UnireqMainActivity extends AppCompatActivity {
    /*for debug only, use view instead*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unireq_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        /*InputStream a;

        a = getResources().openRawResource(R.raw.testsubject);

        SubjectParser b = SubjectParser.getInstance();
        b.setInputStream(a);
        Subject s=null;
        try {
            s = b.parse();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TextView tx = new TextView(this);
        tx.setText(s.toString());
        /*this.addContentView(tx, new Toolbar.LayoutParams(
                Toolbar.LayoutParams.FILL_PARENT,
                Toolbar.LayoutParams.WRAP_CONTENT));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_subject:
                Intent intent = new Intent(this, AddReq.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
