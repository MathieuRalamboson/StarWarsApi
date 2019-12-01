package mathieu.r.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Collections;
import java.util.Set;

import mathieu.r.Model.Film;
import mathieu.r.Model.People;
import mathieu.r.Model.Planets;
import mathieu.r.Model.Species;
import mathieu.r.Model.Starships;
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

        if (bundle.containsKey("film")) { // En fonction du type de l'object Film/People...
            Film film = (Film) bundle.getSerializable("film"); //Recuperation de l'object film
            recuperationDataFilmActivity(film);
        }
        if (bundle.containsKey("people")) {
            People people = (People) bundle.getSerializable("people"); //Recuperation de l'object people
            recuperationDataPeopleActivity(people);
        }
        if(bundle.containsKey("planets")) {
            Planets planets = (Planets) bundle.getSerializable("planets"); //Recuperation de l'object planets
            recuperationDataPlanetsActivity(planets);
        }
        if(bundle.containsKey("species")) {
            Species species = (Species) bundle.getSerializable("species"); //Recuperation de l'object planets
            recuperationDataSpeciesActivity(species);
        }
        if(bundle.containsKey("starships")) {
            Starships starships = (Starships) bundle.getSerializable("starships"); //Recuperation de l'object planets
            recuperationDataStarshipsActivity(starships);
        }


    }

    private void recuperationDataStarshipsActivity(Starships starships) {
        DetailObjectFragment detailObjectFragment = new DetailObjectFragment(); // Instanciation du nouveau fragment
        Bundle args = new Bundle();
        args.putSerializable("starships", starships); //on remplie args
        detailObjectFragment.setArguments(args);
        Log.d(TAG,"Lancement de l'activity : " + starships.getName());

        // Lancement du fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, detailObjectFragment)
                .commitNow();
    }

    private void recuperationDataSpeciesActivity(Species species) {
        DetailObjectFragment detailObjectFragment = new DetailObjectFragment(); // Instanciation du nouveau fragment
        Bundle args = new Bundle();
        args.putSerializable("species", species); //on remplie args
        detailObjectFragment.setArguments(args);
        Log.d(TAG,"Lancement de l'activity : " + species.getName());

        // Lancement du fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, detailObjectFragment)
                .commitNow();
    }

    private void recuperationDataPlanetsActivity(Planets planets) {
        DetailObjectFragment detailObjectFragment = new DetailObjectFragment(); // Instanciation du nouveau fragment
        Bundle args = new Bundle();
        args.putSerializable("planets", planets); //on remplie args
        detailObjectFragment.setArguments(args);
        Log.d(TAG,"Lancement de l'activity : " + planets.getName());

        // Lancement du fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, detailObjectFragment)
                .commitNow();
    }

    private void recuperationDataPeopleActivity(People people) { //Recuperer les data de l'activity parent et envoye vers le fragment avec le nom arg
        DetailObjectFragment detailObjectFragment = new DetailObjectFragment(); // Instanciation du nouveau fragment
        Bundle args = new Bundle();
        args.putSerializable("people", people); //on remplie args
        detailObjectFragment.setArguments(args);
        Log.d(TAG,"Lancement de l'activity : " + people.getName());

        // Lancement du fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, detailObjectFragment)
                .commitNow();
    }

    private void recuperationDataFilmActivity(Film film) { //Recuperer les data de l'activity parent et envoye vers le fragment avec le nom arg
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
