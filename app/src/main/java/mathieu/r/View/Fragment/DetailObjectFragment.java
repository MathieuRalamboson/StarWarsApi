package mathieu.r.View.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mathieu.r.Model.Film;
import mathieu.r.Model.People;
import mathieu.r.Model.Planets;
import mathieu.r.Model.Species;
import mathieu.r.Model.Starships;
import mathieu.r.Model.Vehicles;
import mathieu.r.R;


public class DetailObjectFragment extends Fragment {

    private static final String TAG = "DetailObjectFragment";
    private DetailObjectViewModel mViewModel;

    public static DetailObjectFragment newInstance() {
        return new DetailObjectFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) { // Instanciation object graphique vue etc

        View v = null;
        Bundle args = getArguments(); // On les recupere

        if(args.containsKey("film")){ // Differenciation Categorie : Film...
            v = inflater.inflate(R.layout.detail_object_fragment_film, container, false); // Choix du layout
            affichageDetailFilm(args,v);

        }
        if(args.containsKey("people")){ // Differenciation Categorie : People...
            v = inflater.inflate(R.layout.detail_object_fragment_film, container, false); // Choix du layout
            affichageDetailPeople(args,v);
        }
        if(args.containsKey("planets")){ // Differenciation Categorie : etc...
            v = inflater.inflate(R.layout.detail_object_fragment_film, container, false); // Choix du layout
            affichageDetailPlanets(args,v);
        }
        if(args.containsKey("species")){ // Differenciation Categorie : etc...
            v = inflater.inflate(R.layout.detail_object_fragment_film, container, false); // Choix du layout
            affichageDetailSpecies(args,v);
        }
        if(args.containsKey("starships")){ // Differenciation Categorie : etc...
            v = inflater.inflate(R.layout.detail_object_fragment_film, container, false); // Choix du layout
            affichageDetailStarships(args,v);
        }
        if(args.containsKey("vehicles")){ // Differenciation Categorie : etc...
            v = inflater.inflate(R.layout.detail_object_fragment_film, container, false); // Choix du layout
            affichageDetailVehicles(args,v);
        }

        return v; // Return du layout
    }

    private void affichageDetailVehicles(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout

        Vehicles vehicles = (Vehicles) args.getSerializable("vehicles"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + vehicles.getName());

            textView.setText(vehicles.getName()); // Setter textLayout
            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/vehicles/" + vehicles.getNumber() + ".jpg")
                    .into(imageView);
        }
    }

    private void affichageDetailStarships(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout

        Starships starships = (Starships) args.getSerializable("starships"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + starships.getName());

            textView.setText(starships.getName()); // Setter textLayout
            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/starships/" + starships.getNumber() + ".jpg")
                    .into(imageView);
        }
    }

    private void affichageDetailSpecies(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout

        Species species = (Species) args.getSerializable("species"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + species.getName());

            textView.setText(species.getName()); // Setter textLayout
            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/species/" + species.getNumber() + ".jpg")
                    .into(imageView);
        }
    }

    private void affichageDetailPlanets(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout

        Planets planets = (Planets) args.getSerializable("planets"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + planets.getName());

            textView.setText(planets.getName()); // Setter textLayout
            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/planets/" + planets.getNumber() + ".jpg")
                    .into(imageView);
        }
    }

    private void affichageDetailPeople(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout

        People people = (People) args.getSerializable("people"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + people.getName());

            textView.setText(people.getName()); // Setter textLayout
            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/characters/" + people.getNumber() + ".jpg")
                    .into(imageView);
        }
    }

    private void affichageDetailFilm(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout
        TextView opening_crawl = v.findViewById(R.id.opening_crawl); // Declaration text sur layout
        TextView director = v.findViewById(R.id.director); // Declaration text sur layout
        TextView producer = v.findViewById(R.id.producer); // Declaration text sur layout
        TextView release_date = v.findViewById(R.id.release_date); // Declaration text sur layout

        Film film = (Film) args.getSerializable("film"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + film.getTitle());

            textView.setText(film.getTitle()); // Setter textLayout
            opening_crawl.setText(film.getOpening_crawl()); // Setter
            director.setText(film.getDirector()); // Setter
            producer.setText(film.getProducer()); // Setter
            release_date.setText(film.getRelease_date()); // Setter

            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/films/" + film.getEpisode_id() + ".jpg")
                    .into(imageView);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailObjectViewModel.class);
        // TODO: Use the ViewModel
    }

}
