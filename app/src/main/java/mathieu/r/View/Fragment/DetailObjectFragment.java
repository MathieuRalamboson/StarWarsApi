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
            v = inflater.inflate(R.layout.detail_object_fragment_people, container, false); // Choix du layout
            affichageDetailPeople(args,v);
        }
        if(args.containsKey("planets")){ // Differenciation Categorie : etc...
            v = inflater.inflate(R.layout.detail_object_fragment_planets, container, false); // Choix du layout
            affichageDetailPlanets(args,v);
        }
        if(args.containsKey("species")){ // Differenciation Categorie : etc...
            v = inflater.inflate(R.layout.detail_object_fragment_species, container, false); // Choix du layout
            affichageDetailSpecies(args,v);
        }
        if(args.containsKey("starships")){ // Differenciation Categorie : etc...
            v = inflater.inflate(R.layout.detail_object_fragment_starships, container, false); // Choix du layout
            affichageDetailStarships(args,v);
        }
        if(args.containsKey("vehicles")){ // Differenciation Categorie : etc...
            v = inflater.inflate(R.layout.detail_object_fragment_vehicles, container, false); // Choix du layout
            affichageDetailVehicles(args,v);
        }

        return v; // Return du layout
    }

    private void affichageDetailVehicles(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout
        TextView model = v.findViewById(R.id.model); // Declaration text sur layout
        TextView manufacturer = v.findViewById(R.id.manufacturer); // Declaration text sur layout
        TextView cost_in_credits = v.findViewById(R.id.cost_in_credits); // Declaration text sur layout
        TextView max_atmosphering_speed = v.findViewById(R.id.max_atmosphering_speed); // Declaration text sur layout
        TextView crew = v.findViewById(R.id.crew); // Declaration text sur layout
        TextView passengers = v.findViewById(R.id.passengers); // Declaration text sur layout
        TextView cargo_capacity = v.findViewById(R.id.cargo_capacity); // Declaration text sur layout
        TextView consumables = v.findViewById(R.id.consumables); // Declaration text sur layout
        TextView vehicle_class = v.findViewById(R.id.vehicle_class); // Declaration text sur layout

        Vehicles vehicles = (Vehicles) args.getSerializable("vehicles"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + vehicles.getName());

            textView.setText("Name : " + vehicles.getName()); // Setter textLayout
            model.setText("Model : " + vehicles.getModel()); // Setter textLayout
            manufacturer.setText("Manufacturer : " + vehicles.getManufacturer()); // Setter textLayout
            cost_in_credits.setText("Cost_in_credits : " + vehicles.getCost_in_credits()); // Setter textLayout
            max_atmosphering_speed.setText("Max_atmosphering_speed : " + vehicles.getMax_atmosphering_speed()); // Setter textLayout
            crew.setText("Crew : " + vehicles.getCrew()); // Setter textLayout
            passengers.setText("Passengers : " + vehicles.getPassengers()); // Setter textLayout
            cargo_capacity.setText("Cargo_capacity : " + vehicles.getCargo_capacity()); // Setter textLayout
            consumables.setText("Consumables : " + vehicles.getConsumables()); // Setter textLayout
            vehicle_class.setText("Vehicle_class : " + vehicles.getVehicle_class()); // Setter textLayout

            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/vehicles/" + vehicles.getNumber() + ".jpg")
                    .error(R.drawable.imagenotfound)
                    .into(imageView);
        }
    }

    private void affichageDetailStarships(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout
        TextView model = v.findViewById(R.id.model); // Declaration text sur layout
        TextView manufacturer = v.findViewById(R.id.manufacturer); // Declaration text sur layout
        TextView cost_in_credits = v.findViewById(R.id.cost_in_credits); // Declaration text sur layout
        TextView length = v.findViewById(R.id.length); // Declaration text sur layout
        TextView max_atmosphering_speed = v.findViewById(R.id.max_atmosphering_speed); // Declaration text sur layout
        TextView passengers = v.findViewById(R.id.passengers); // Declaration text sur layout
        TextView cargo_capacity = v.findViewById(R.id.cargo_capacity); // Declaration text sur layout
        TextView consumables = v.findViewById(R.id.consumables); // Declaration text sur layout
        TextView hyperdrive_rating = v.findViewById(R.id.hyperdrive_rating); // Declaration text sur layout
        TextView MGLT = v.findViewById(R.id.MGLT); // Declaration text sur layout
        TextView starship_class = v.findViewById(R.id.starship_class); // Declaration text sur layout


        Starships starships = (Starships) args.getSerializable("starships"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + starships.getName());

            textView.setText("Name : " + starships.getName()); // Setter textLayout
            model.setText("Model : " + starships.getModel()); // Setter textLayout
            manufacturer.setText("Manufacturer : " + starships.getManufacturer()); // Setter textLayout
            cost_in_credits.setText("Cost_in_credits : " + starships.getCost_in_credits()); // Setter textLayout
            length.setText("Length : " + starships.getLength()); // Setter textLayout
            max_atmosphering_speed.setText("Max_atmosphering_speed : " + starships.getMax_atmosphering_speed()); // Setter textLayout
            passengers.setText("Passengers : " + starships.getPassengers()); // Setter textLayout
            cargo_capacity.setText("Cargo_capacity : " + starships.getCargo_capacity()); // Setter textLayout
            consumables.setText("Consumables : " + starships.getConsumables()); // Setter textLayout
            hyperdrive_rating.setText("Hyperdrive_rating : " + starships.getHyperdrive_rating()); // Setter textLayout
            MGLT.setText("MGLT : " + starships.getMGLT()); // Setter textLayout
            starship_class.setText("Starship_class : " + starships.getStarship_class()); // Setter textLayout

            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/starships/" + starships.getNumber() + ".jpg")
                    .error(R.drawable.imagenotfound)
                    .into(imageView);
        }
    }

    private void affichageDetailSpecies(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout
        TextView classification = v.findViewById(R.id.classification); // Declaration text sur layout
        TextView designation = v.findViewById(R.id.designation); // Declaration text sur layout
        TextView average_height = v.findViewById(R.id.average_height); // Declaration text sur layout
        TextView skin_colors = v.findViewById(R.id.skin_colors); // Declaration text sur layout
        TextView hair_colors = v.findViewById(R.id.hair_colors); // Declaration text sur layout
        TextView eye_colors = v.findViewById(R.id.eye_colors); // Declaration text sur layout
        TextView average_lifespan = v.findViewById(R.id.average_lifespan); // Declaration text sur layout
        TextView homeworld = v.findViewById(R.id.homeworld); // Declaration text sur layout
        TextView language = v.findViewById(R.id.language); // Declaration text sur layout

        Species species = (Species) args.getSerializable("species"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + species.getName());

            classification.setText("Classification : " + species.getName()); // Setter textLayout
            designation.setText("Designation : " + species.getDesignation()); // Setter textLayout
            average_height.setText("Average_height : " + species.getAverage_height()); // Setter textLayout
            skin_colors.setText("Skin_colors : " + species.getSkin_colors()); // Setter textLayout
            hair_colors.setText("Hair_colors : " + species.getHair_colors()); // Setter textLayout
            eye_colors.setText("Eye_colors : " + species.getEye_colors()); // Setter textLayout
            average_lifespan.setText("Average_lifespan : " + species.getAverage_lifespan()); // Setter textLayout
            homeworld.setText("Homeworld : " + species.getHomeworld()); // Setter textLayout
            language.setText("Language : " + species.getLanguage()); // Setter textLayout

            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/species/" + species.getNumber() + ".jpg")
                    .error(R.drawable.imagenotfound)
                    .into(imageView);
        }
    }

    private void affichageDetailPlanets(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout
        TextView rotation_period = v.findViewById(R.id.rotation_period); // Declaration text sur layout
        TextView orbital_period = v.findViewById(R.id.orbital_period); // Declaration text sur layout
        TextView diameter = v.findViewById(R.id.diameter); // Declaration text sur layout
        TextView climate = v.findViewById(R.id.climate); // Declaration text sur layout
        TextView gravity = v.findViewById(R.id.gravity); // Declaration text sur layout
        TextView terrain = v.findViewById(R.id.terrain); // Declaration text sur layout
        TextView surface_water = v.findViewById(R.id.surface_water); // Declaration text sur layout
        TextView population = v.findViewById(R.id.population); // Declaration text sur layout

        Planets planets = (Planets) args.getSerializable("planets"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + planets.getName());

            textView.setText("Name : " + planets.getName()); // Setter textLayout
            rotation_period.setText("Rotation_period : " + planets.getRotation_period()); // Setter textLayout
            orbital_period.setText("Orbital_period : " + planets.getOrbital_period()); // Setter textLayout
            diameter.setText("Diameter : " + planets.getDiameter()); // Setter textLayout
            climate.setText("Climate : " + planets.getClimate()); // Setter textLayout
            gravity.setText("Gravity : " + planets.getGravity()); // Setter textLayout
            terrain.setText("Terrain : " + planets.getTerrain()); // Setter textLayout
            surface_water.setText("Surface_water : " + planets.getSurface_water()); // Setter textLayout
            population.setText("Population : " + planets.getPopulation()); // Setter textLayout
            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/planets/" + planets.getNumber() + ".jpg")
                    .error(R.drawable.imagenotfound)
                    .into(imageView);
        }
    }

    private void affichageDetailPeople(Bundle args, View v) {
        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout
        TextView height = v.findViewById(R.id.height); // Declaration text sur layout
        TextView mass = v.findViewById(R.id.mass); // Declaration text sur layout
        TextView hair_color = v.findViewById(R.id.hair_color); // Declaration text sur layout
        TextView skin_color = v.findViewById(R.id.skin_color); // Declaration text sur layout
        TextView eye_color = v.findViewById(R.id.eye_color); // Declaration text sur layout
        TextView birth_year = v.findViewById(R.id.birth_year); // Declaration text sur layout
        TextView gender = v.findViewById(R.id.gender); // Declaration text sur layout
        TextView homeworld = v.findViewById(R.id.homeworld); // Declaration text sur layout

        People people = (People) args.getSerializable("people"); // On les serialise dans un object film
        if(getArguments() != null) { // Si les arguments recu ne sont pas null
            Log.d(TAG,"Lancement du fragment : " + people.getName());

            textView.setText("Name : " + people.getName()); // Setter textLayout
            height.setText("Height : " + people.getHeight()); // Setter textLayout
            mass.setText("Mass : " + people.getMass()); // Setter textLayout
            hair_color.setText("Hair_color : " + people.getHair_color()); // Setter textLayout
            skin_color.setText("Skin_color : " + people.getSkin_color()); // Setter textLayout
            eye_color.setText("Eye_color : " + people.getEye_color()); // Setter textLayout
            birth_year.setText("Birth_year : " + people.getBirth_year()); // Setter textLayout
            gender.setText("Gender : " + people.getGender()); // Setter textLayout
            homeworld.setText("Homeworld : " + people.getHomeworld()); // Setter textLayout

            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/characters/" + people.getNumber() + ".jpg")
                    .error(R.drawable.imagenotfound)
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

            textView.setText("Title : " + film.getTitle()); // Setter textLayout
            opening_crawl.setText("Opening_crawl : " + film.getOpening_crawl()); // Setter
            director.setText("Director : " + film.getDirector()); // Setter
            producer.setText("Producer : " + film.getProducer()); // Setter
            release_date.setText("Release_date : " + film.getRelease_date()); // Setter

            Picasso.with(getContext()) // Setter imageLayout
                    .load("https://starwars-visualguide.com/assets/img/films/" + film.getEpisode_id() + ".jpg")
                    .error(R.drawable.imagenotfound)
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
