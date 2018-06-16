package com.mcompany.coupan.ui.neardealfragment;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.utility.CurrentLocationManager;
import com.mcompany.coupan.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class NearDealsFragment extends BaseFragment implements OnMapReadyCallback {


    private Unbinder mUnbinder;
    private GoogleMap googleMap;

    @BindView(R.id.mapView)
    MapView mMapView;

    private static final int PERMISSION_REQUEST_CODE = 200;
    private  CurrentLocationManager.LocationResult mLocationResult;
    private  CurrentLocationManager mMyLocation;
    public NearDealsFragment() { /* Required empty public constructor*/ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near_deals_maps, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mMapView.onCreate(savedInstanceState);
        setMapView();
        mMapView.getMapAsync(this);
        getLocation();
        return view;
    }

    public void getLocation() {
        mLocationResult = new CurrentLocationManager.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                //Got the location!
                List<Location> locations = new ArrayList<>(1);
                Location loc1 = new Location("dummyprovider1");
                loc1.setLatitude(28.4909262);
                loc1.setLongitude(77.0696101);

                Location loc2 = new Location("dummyprovider2");
                loc2.setLatitude(28.4900420);
                loc2.setLongitude(77.067182);

                Location loc3 = new Location("dummyprovider3");
                loc3.setLatitude(28.5077304);
                loc3.setLongitude(77.071280);

                Location loc4 = new Location("dummyprovider4");
                loc4.setLatitude(28.5029031);
                loc4.setLongitude(77.0638775);

                Location loc5 = new Location("dummyprovider5");
                loc5.setLatitude(28.5013757);
                loc5.setLongitude(77.0701646);

                locations.add(location);
                locations.add(loc1);
                locations.add(loc2);
                locations.add(loc3);
                locations.add(loc4);
                locations.add(loc5);

                drawMarkers(locations);
            }

            @Override
            public void requestPermission() {
                NearDealsFragment.this.requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION},PERMISSION_REQUEST_CODE);
            }

            @Override
            public void permissionDenied() {
                showToast(getString(R.string.permission_denied));
            }
        };
        mMyLocation = new CurrentLocationManager();
        mMyLocation.getLocation(mActivity, mLocationResult);
    }

    private void setMapView() {
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    private void drawMarkers(List<Location> locList) {

        if (null == locList || null == googleMap) {
            return;
        }
        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Location location : locList) {
            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(sydney)
                    .title("Lat Long is " + location.getLatitude() + " " + location.getLongitude()));
            LatLng meanLocation = new LatLng(location.getLatitude(), location.getLongitude());
            builder.include(meanLocation);
        }
        int zoomFactor = 12 + 12 / locList.size();

        final LatLngBounds bounds = builder.build();
        LatLng ne = bounds.northeast;
        LatLng sw = bounds.southwest;
        LatLng center = new LatLng((ne.latitude + sw.latitude)/2,
                (ne.longitude + sw.longitude)/2);

//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(zoomFactor).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                mMyLocation.onRequestPermissionsResult(requestCode,permissions,grantResults);
                break;
        }
    }
}