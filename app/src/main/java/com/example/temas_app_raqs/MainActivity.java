package com.example.temas_app_raqs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_THEME = "pref_theme";
    private Spinner spinnerThemes;
    private EditText username, password;
    private Button clearButton;
    private TextView themeDescription;
    private int currentTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the saved theme
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = prefs.getInt(PREF_THEME, R.style.AppTheme);
        setTheme(currentTheme);

        setContentView(R.layout.activity_main);

        spinnerThemes = findViewById(R.id.spinnerThemes);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        clearButton = findViewById(R.id.clearButton);
        themeDescription = findViewById(R.id.themeDescription);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.themes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThemes.setAdapter(adapter);

        // Set the spinner to the current theme
        int position = getThemePosition(currentTheme);
        spinnerThemes.setSelection(position);

        spinnerThemes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedTheme;
                switch (position) {
                    case 0:
                        selectedTheme = R.style.AppTheme;
                        break;
                    case 1:
                        selectedTheme = R.style.DarkTheme;
                        break;
                    case 2:
                        selectedTheme = R.style.ModernTheme;
                        break;
                    case 3:
                        selectedTheme = R.style.VibrantTheme;
                        break;
                    case 4:
                        selectedTheme = R.style.NaturalTheme;
                        break;
                    default:
                        selectedTheme = R.style.AppTheme;
                }

                // Only recreate if the theme has changed
                if (selectedTheme != currentTheme) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt(PREF_THEME, selectedTheme);
                    editor.apply();
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        updateTheme(currentTheme);

        clearButton.setOnClickListener(v -> {
            username.setText("");
            password.setText("");
        });
    }

    private void updateTheme(int selectedTheme) {
        if (selectedTheme == R.style.AppTheme) {
            username.setTextAppearance(this, R.style.TextThemeLight);
            password.setTextAppearance(this, R.style.TextThemeLight);
            clearButton.setTextAppearance(this, R.style.ButtonThemeLight);
            themeDescription.setText("Tema Claro: Fuente sans-serif, Botón con fondo colorPrimario");
        } else if (selectedTheme == R.style.DarkTheme) {
            username.setTextAppearance(this, R.style.TextThemeDark);
            password.setTextAppearance(this, R.style.TextThemeDark);
            clearButton.setTextAppearance(this, R.style.ButtonThemeDark);
            themeDescription.setText("Tema Oscuro: Fuente serif, Botón con fondo colorPrimarioOscuro");
        } else if (selectedTheme == R.style.ModernTheme) {
            username.setTextAppearance(this, R.style.TextThemeModern);
            password.setTextAppearance(this, R.style.TextThemeModern);
            clearButton.setTextAppearance(this, R.style.ButtonThemeModern);
            themeDescription.setText("Tema Moderno: Fuente monospace, Botón con fondo theme1Primario");
        } else if (selectedTheme == R.style.VibrantTheme) {
            username.setTextAppearance(this, R.style.TextThemeVibrant);
            password.setTextAppearance(this, R.style.TextThemeVibrant);
            clearButton.setTextAppearance(this, R.style.ButtonThemeVibrant);
            themeDescription.setText("Tema Natural: Fuente cursive, Botón con fondo theme2Primario");
        } else if (selectedTheme == R.style.NaturalTheme) {
            username.setTextAppearance(this, R.style.TextThemeNatural);
            password.setTextAppearance(this, R.style.TextThemeNatural);
            clearButton.setTextAppearance(this, R.style.ButtonThemeNatural);
            themeDescription.setText("Tema Vibrante: Fuente casual, Botón con fondo theme3Primario");
        }
    }
    private int getThemePosition(int theme) {
        if (theme == R.style.AppTheme) {
            return 0;
        } else if (theme == R.style.DarkTheme) {
            return 1;
        } else if (theme == R.style.ModernTheme) {
            return 2;
        } else if (theme == R.style.VibrantTheme) {
            return 3;
        } else if (theme == R.style.NaturalTheme) {
            return 4;
        } else {
            return 0;
        }
    }
}
