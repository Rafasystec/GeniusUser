package br.com.barcadero.geniususer.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Andressa on 09/10/2017.
 * Edited by Lunider on 01/11/2017.
 */


public class LocationUtil implements LocationListener {

    public static final int MULTIPLE_PERMISSION_REQUEST_CODE = 123;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 metros
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minuto

    Context context;

    private LocationManager locationManager;

    private Location bestLocation;

    public boolean gpsIsEnabled() {
        //checkPermissionToUseLocation();
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private String getBestProvider(LocationManager lm) {
        List<String> provs = lm.getAllProviders();
        for (String prov : provs) {
            Log.d("", "Provider: " + prov);
        }
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_COARSE);
        c.setAltitudeRequired(false);
        c.setBearingRequired(false);
        c.setCostAllowed(false);
        c.setPowerRequirement(Criteria.NO_REQUIREMENT);
        c.setSpeedRequired(false);
        return lm.getBestProvider(c, false);
    }

   /* private void checkPermissionToUseLocation() {
        boolean isLocationPermitted = PermissionUtil.hasAllPermissions((Activity) context,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);

        if (!isLocationPermitted) {
            PermissionUtil.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    MULTIPLE_PERMISSION_REQUEST_CODE);
        }

    }*/

    @SuppressLint("MissingPermission")
    public LatLng getLocation() {
        if (!gpsIsEnabled()) {
            showGPSSettings();
            return null;
        } else {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            List<String> providers = null;
            if (locationManager != null) {
                providers = locationManager.getProviders(true);
            }
            bestLocation = null;
            if (providers != null) {
                for (String provider : providers) {
                    Location location = locationManager.getLastKnownLocation(provider);
                    if (location == null) {
                        continue;
                    }
                    if (bestLocation == null || location.getAccuracy() < bestLocation.getAccuracy()) {
                        bestLocation = location;
                    }
                }
            }
            LatLng bestLatLngLocation = null;
            if (bestLocation != null) {
                bestLatLngLocation = new LatLng(bestLocation.getLatitude(), bestLocation.getLongitude());
            }
            return bestLatLngLocation;
        }
    }

    public Address getAddressFromLocation(LatLng location) {
        List<Address> addresses = null;
        Address address = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
        } catch (IOException ioException) {
            showToast("Serviço indisponível");

        } catch (IllegalArgumentException illegalArgumentException) {
            showToast("Coordenadas inválidas");
        }

        if (addresses == null || addresses.size() == 0) {
            showToast("Nenhum endereço foi encontrado");

        } else {
            address = addresses.get(0);
        }

        return address;
    }

    public List<Address> getAddressFromLocationName(String locationName) {
        List<Address> addresses = null;
        Address address = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocationName(locationName, 10);
        } catch (IOException ioException) {
            showToast("Serviço indisponível");

        } catch (IllegalArgumentException illegalArgumentException) {
            showToast("Coordenadas inválidas");
        }

        if (addresses == null || addresses.size() == 0) {
            showToast("Nenhum endereço foi encontrado");

        } else {
            return addresses;
        }

        return Collections.emptyList();
    }

    public String getFullAddressDescription(Address address) {
        StringBuilder strReturnedAddress = new StringBuilder("");

        for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
            strReturnedAddress.append(address.getAddressLine(i)).append("\n");
        }

        return strReturnedAddress.toString();
    }


    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }


    public void showGPSSettings() {

        new AlertDialog.Builder(context)
                .setTitle("Ativar GPS")
                .setMessage("Seu GPS está desativado, deseja ativá-lo?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                }).create().show();
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    private void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
