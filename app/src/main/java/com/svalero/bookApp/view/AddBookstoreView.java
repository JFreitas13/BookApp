package com.svalero.bookApp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.svalero.bookApp.contract.AddBookstoreContract;
import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookApp.presenter.AddBookstorePresenter;
import com.svalero.bookapp.R;

public class AddBookstoreView extends AppCompatActivity implements AddBookstoreContract.View {

    private MapView bookstoreMap;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;

    private AddBookstorePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bookstore_view);

        Log.d("add Bookstore", "llamada desde addBookstoreView");

        presenter = new AddBookstorePresenter(this);
        bookstoreMap = findViewById(R.id.bookstoreMapView);

        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(bookstoreMap);
        gesturesPlugin.addOnMapClickListener(point -> {
            removeAllMarkers();
            this.point = point;
            addMarker(point);
            return true;
        });

        initializePointManager();
    }

    //inicializar el PointManager
    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(bookstoreMap);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    //añadir red marker en el mapa
    private void addMarker(Point point) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker_icon_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    //eliminar markers anteriores
    private void removeAllMarkers() {
        pointAnnotationManager.deleteAll();
    }

    //boton AÑADIR
    public void addButton(View view) {
        EditText etName = findViewById(R.id.name_bookstore_edit_text);
        EditText etCity = findViewById(R.id.city_edit_text);
        EditText etZipCode = findViewById(R.id.zip_code_edit_text);
        EditText etPhoneNumber = findViewById(R.id.phone_number_edit_text);

        String name = etName.getText().toString();
        String city = etCity.getText().toString();
        String zipCode = etZipCode.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();

        //hacemos if para el caso de que el usuario no elija una ubicación y no de error a grabar al entrada
        if (point == null) {
            Snackbar.make(etName, R.string.choose_location_message, BaseTransientBottomBar.LENGTH_LONG);
            //Toast.makeText(this,"Elige una ubicación", Toast.LENGTH_SHORT).show();
            return;
        }

        Bookstore bookstore = new Bookstore(name, city, zipCode, phoneNumber, point.latitude(), point.longitude());
        presenter.addBookstore(bookstore);

        finish();

    }

    //boton CANCELAR
    public void cancelButton(View view) {
        onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.name_bookstore_edit_text)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();

    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.name_bookstore_edit_text)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();

    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.name_bookstore_edit_text)).setText("");
        ((EditText) findViewById(R.id.city_edit_text)).setText("");
        ((EditText) findViewById(R.id.pagesTextNumber)).setText("");
        ((EditText) findViewById(R.id.descriptionEditText)).setText("");

        ((EditText) findViewById(R.id.name_bookstore_edit_text)).requestFocus();

    }

    //crear el menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_bookstore, menu);
        return true;
    }

    //eleccion en el actionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Bookstore) {
            Intent intent = new Intent(this, AddBookstoreView.class); //para ir a otra activity
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.view_map) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class); //para ir a otra activity
            startActivity(intent);
            return true;
        }
        return false;
    }

}