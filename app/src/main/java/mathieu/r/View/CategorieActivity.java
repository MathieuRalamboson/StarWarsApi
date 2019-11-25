package mathieu.r.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import mathieu.r.R;

public class CategorieActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Films", "People", "Planets", "Species", "Starships"};
    String mDescription[] = {"Films Description", "People Description", "Planets Description", "Species Description", "Starships Description"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);
    }
}
