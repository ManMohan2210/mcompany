package com.mcompany.coupan.ui.neardealfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.constants.IntentKeyConstants;
import com.mcompany.coupan.appcommon.utility.CurrentLocationManager;
import com.mcompany.coupan.dtos.Address;
import com.mcompany.coupan.dtos.Deal;
import com.mcompany.coupan.dtos.Merchant;
import com.mcompany.coupan.dtos.Merchants;
import com.mcompany.coupan.ui.base.BaseFragment;
import com.mcompany.coupan.ui.dealdetails.DealsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class NearDealsFragment extends BaseFragment implements OnMapReadyCallback, NearDealContractor.NearDealView {


    private Unbinder mUnbinder;
    private GoogleMap googleMap;

    @BindView(R.id.mapView)
    MapView mMapView;

    @BindView(R.id.progressbar_nearme_deals)
    ProgressBar progressBar;

    private List<Merchant> mListAllMerchant;

    private static final int PERMISSION_REQUEST_CODE = 200;
    private CurrentLocationManager.LocationResult mLocationResult;
    private CurrentLocationManager mMyLocation;
    private Location mCurrentLocation;
    private boolean isCurrentLocationFetched;
    private boolean isDataFetched;

    private NearDealContractor.NearDealPresenter nearDealPresenter;

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
        nearDealPresenter = new NearDealPresenterImpl(this);
        mListAllMerchant = new ArrayList<>();
        nearDealPresenter.fetchData();

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
                mCurrentLocation = location;
                isCurrentLocationFetched = true;
                if (isCurrentLocationFetched && isDataFetched) {
                    drawMarkers();
                }
            }

            @Override
            public void requestPermission() {
                NearDealsFragment.this.requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
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

    private void drawMarkers() {

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                if (null == mListAllMerchant || null == googleMap) {
                    return;
                }
                LatLng currentLoc = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions().position(currentLoc)
                        .title(getString(R.string.you_are_here));
                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_your_are_here);
                Bitmap smallMarker = Bitmap.createScaledBitmap(largeIcon, 150, 150, false);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                googleMap.addMarker(markerOptions);


                final LatLngBounds.Builder builder = new LatLngBounds.Builder();
                int position = -1;
                for (Merchant merchant : mListAllMerchant) {
                    position++;
                    Address address = merchant.getAddress();
                    if (null != address) {
                        double lat = Double.parseDouble(address.getLatitude());
                        double longitude = Double.parseDouble(address.getLongitude());
                        LatLng sydney = new LatLng(lat, longitude);
                        Marker marker = googleMap.addMarker(new MarkerOptions().position(sydney)
                                .title(merchant.getName()));
                        Deal deal = merchant.getDeals().get(0);
                        marker.setTag(deal);
                        LatLng meanLocation = new LatLng(lat, longitude);
                        builder.include(meanLocation);
                    }
                }

                AppInfoWindowGoogleMap customInfoWindow = new AppInfoWindowGoogleMap(getActivity());
                googleMap.setInfoWindowAdapter(customInfoWindow);


                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Object tag = marker.getTag();

                        if (tag == null) {
                            return;
                        }
                        Deal deal = (Deal) tag;
                        Intent intent = new Intent(mActivity, DealsDetailActivity.class);
                        intent.putExtra(IntentKeyConstants.EXTRA_DEAL_DATA, deal);
                        startActivity(intent);
                    }
                });
                int zoomFactor = 12 + 12 / mListAllMerchant.size();

                final LatLngBounds bounds = builder.build();
                LatLng ne = bounds.northeast;
                LatLng sw = bounds.southwest;
                LatLng center = new LatLng((ne.latitude + sw.latitude) / 2,
                        (ne.longitude + sw.longitude) / 2);

                CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(zoomFactor).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                mMyLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    @Override
    public void onSuccess(Merchants merchants) {
        mListAllMerchant.clear();
        isDataFetched = true;
        if (null != merchants) {
            mListAllMerchant = merchants.getMerchants();
        }
        setViewData();
    }

    private void setViewData() {
        if (isDataFetched && isCurrentLocationFetched) {
            drawMarkers();
        }
    }

    @Override
    public void onError(DatabaseError databaseError) {
        showToast(databaseError.getMessage());
    }

    @Override
    public void setShowLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHideLoader() {
//        progressBar.setVisibility(View.GONE);
    }
}