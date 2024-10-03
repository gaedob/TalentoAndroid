package com.mirae.desafiofragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configurar la ActionBar inicialmente sin el botón de retroceso
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);  // Oculta el botón "up" por defecto
        }

        // Configura el NavController para la navegación
        NavController navController = NavHostFragment.findNavController(
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container));

        // Configura la visibilidad del botón "up" según el destino actual
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                if (destination.getId() == R.id.firstFragment) {
                    // Si estamos en el fragmento inicial (HomeFragment), oculta el botón "up"
                    ab.setDisplayHomeAsUpEnabled(false);
                } else {
                    // En otros fragmentos, muestra el botón "up"
                    ab.setDisplayHomeAsUpEnabled(true);
                }
            }
        });
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavHostFragment.findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container))).navigateUp() || super.onSupportNavigateUp();
    }

}