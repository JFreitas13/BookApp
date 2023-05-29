package com.svalero.bookApp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapInitOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.ResourceOptions;
import com.mapbox.maps.plugin.Plugin;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.svalero.bookApp.contract.BookstoreListContract;
import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookApp.presenter.BookstoreListPresenter;
import com.svalero.bookapp.R;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements BookstoreListContract.View {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private BookstoreListPresenter bookstoreListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_view);

        // Obtén el token de Mapbox desde tus recursos o cualquier otra fuente
        String accessToken = getString(R.string.mapbox_access_token);

        // Crea las opciones de inicialización del mapa
        ResourceOptions resourceOptions = new ResourceOptions.Builder()
                .accessToken(accessToken)
                .build();
        MapInitOptions mapInitOptions = new MapInitOptions(getApplicationContext(), resourceOptions);

        // Inicializa el MapView utilizando las opciones de inicialización del mapa
        mapView = new MapView(this, mapInitOptions);


        //mapView = findViewById(R.id.mapView); //cargamos el mapa
        initializePointManager(); //inicializamos el pointmanager

        bookstoreListPresenter = new BookstoreListPresenter(this);
        bookstoreListPresenter.loadAllBookstores();
    }

    //iniciar pointManager
    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    //añadir marker
    private void addMarker(Point point, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withTextField(title)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker_icon_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    //fijar camara del mapa en la ubicacion que queremos
    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(13.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }

    //lista de librerias y un for para poner un marker en cada una
    private void addBookstoreToMap(List<Bookstore> bookstores) {
        for (Bookstore bookstore : bookstores) {
            Point point = Point.fromLngLat(bookstore.getLongitud(), bookstore.getLatitude());
            addMarker(point, bookstore.getName());
        }
        Bookstore lastBookstore = bookstores.get(bookstores.size() - 1); //ultima ubicacion
        setCameraPosition(Point.fromLngLat(lastBookstore.getLongitud(), lastBookstore.getLatitude())); //fijamos la camara en la ultima ubicacion
    }


    @Override
    public void showBookstores(List<Bookstore> bookstores) {
        addBookstoreToMap(bookstores);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.actionbar_bookstore, menu); //Inflamos el menu
//        return true;
//    }

}