package com.svalero.bookApp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import com.svalero.bookApp.contract.ModifyBookstoreContract;
import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookApp.presenter.ModifyBookstorePresenter;
import com.svalero.bookapp.R;

public class ModifyBookstoreView extends AppCompatActivity implements ModifyBookstoreContract.View {

    private long id;
    private Bookstore bookstore;
    private ModifyBookstorePresenter presenter;

    private MapView bookstoreMap;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_bookstore_view);

        noticeId();

        Bundle bundle = getIntent().getExtras();
        bookstore = (Bookstore) bundle.getSerializable("bookstore");
        id = bookstore.getId();

        fillData(bookstore);

        presenter = new ModifyBookstorePresenter(this);
        bookstoreMap = findViewById(R.id.bookstoreMapView);

        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(bookstoreMap);
        gesturesPlugin.addOnMapClickListener(point -> {
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


    public void modifyBookstoreButton(View view) {
        EditText etName = findViewById(R.id.nameBookstoreModifyEditText);
        EditText etCity = findViewById(R.id.cityBookstoreModifyEditText);
        EditText etZipCode = findViewById(R.id.zipCodeBookstoreModifyEditText);
        EditText etPhoneNumber = findViewById(R.id.phoneNumberBookstoreModifyEditText);

        String name = etName.getText().toString();
        String city = etCity.getText().toString();
        String zipCode = etZipCode.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();

        if (point == null) {
            Snackbar.make(etName, R.string.choose_location_message, BaseTransientBottomBar.LENGTH_LONG);
            return;
        }

        Bookstore modifiedBookstore = new Bookstore(name, city, zipCode, phoneNumber, point.latitude(), point.longitude());
        presenter.modifyBookstore(id, modifiedBookstore);

        finish();
    }

    //boton cancelar y volver atras
    public void cancelModifyButton(View view) {
        onBackPressed();
    }

    private void fillData(Bookstore bookstore) {
        EditText etName = findViewById(R.id.nameBookstoreModifyEditText);
        EditText etCity = findViewById(R.id.cityBookstoreModifyEditText);
        EditText etZipCode = findViewById(R.id.zipCodeBookstoreModifyEditText);
        EditText etPhoneNumber = findViewById(R.id.phoneNumberBookstoreModifyEditText);

        etName.setText(bookstore.getName());
        etCity.setText(bookstore.getCity());
        etZipCode.setText(bookstore.getZipCode());
        etPhoneNumber.setText(bookstore.getPhoneNumber());

    }

    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_bookstore_message)
                .setTitle(R.string.modify_bookstore_title)
                .setNegativeButton("No", (dialog, id) -> { //boton de si

                    Intent intent = new Intent(this, BookListView.class);
                    intent.putExtra("id", bookstore.getId());
                    this.startActivity(intent);
                })
                .setPositiveButton("Yes", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void showMessage(String s) {

    }

    public void showError(String s) {

    }
}