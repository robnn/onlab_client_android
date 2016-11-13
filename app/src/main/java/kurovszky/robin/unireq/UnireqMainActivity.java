package kurovszky.robin.unireq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import kurovszky.robin.unireq.model.Subject;
import kurovszky.robin.unireq.xml_parser.SubjectParser;

public class UnireqMainActivity extends AppCompatActivity {
    /*for debug only, use view instead*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unireq_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

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
}
