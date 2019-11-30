package mathieu.r.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import mathieu.r.Model.Film;
import mathieu.r.R;
import mathieu.r.View.Fragment.DetailObjectFragment;

public class DetailObjectActivity extends AppCompatActivity {
    private static final String TAG = "DetailObjectActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_object_activity);

        //Recuperer les data de l'activity parent avec le nom bundle
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle.containsKey("film")) {
            Film film = (Film) bundle.getSerializable("film"); //Recuperation de l'object film
            recuperationDataFilmActivity(film);
        }


    }

    private void recuperationDataFilmActivity(Film film) {
        //Recuperer les data de l'activity parent et envoye vers le fragment avec le nom arg
        DetailObjectFragment detailObjectFragment = new DetailObjectFragment(); // Instanciation du nouveau fragment
        Bundle args = new Bundle();
        args.putSerializable("film", film); //on remplie args
        detailObjectFragment.setArguments(args);
        Log.d(TAG,"Lancement de l'activity : " + film.getTitle());

        // Lancement du fragment
        getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, detailObjectFragment)
                    .commitNow();
    }
}
