package mathieu.r.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import mathieu.r.R;

public class CategorieActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Films", "People", "Planets", "Species", "Starships","Vehicles"};
    String mDescription[] = {"Films Description", "People Description", "Planets Description", "Species Description", "Starships Description", "Vehicles Description"};
    int image[] = {R.drawable.films, R.drawable.people, R.drawable.planets, R.drawable.species, R.drawable.starships, R.drawable.vehicles};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        listView = findViewById(R.id.listView);

        CategorieAdapter categorieAdapter = new CategorieAdapter( this, mTitle, mDescription, image);
        listView.setAdapter(categorieAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(CategorieActivity.this, "Object : " + mTitle[position], Toast.LENGTH_SHORT).show();
                if(position == 0) {
                    startActivity(new Intent(CategorieActivity.this, FilmActivity.class));
                }

            }
        });

    }

    class CategorieAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        CategorieAdapter(Context c , String title[], String description[], int imgs[]) {
            super(c, R.layout.item_categorie, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.item_categorie, parent, false);

            ImageView imageView = row.findViewById(R.id.image);
            TextView title =  row.findViewById(R.id.textView1);
            TextView description =  row.findViewById(R.id.textview2);

            imageView.setImageResource(rImgs[position]);
            title.setText(rTitle[position]);
            description.setText(rDescription[position]);


            return row;
        }
    }


}
