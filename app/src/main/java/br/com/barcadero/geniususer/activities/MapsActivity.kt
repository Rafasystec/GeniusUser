package br.com.barcadero.geniususer.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.android.adapters.MapInfoWindowsAdapter
import br.com.barcadero.geniususer.model.Quadrant
import br.com.barcadero.geniususer.model.enums.EnumProfessionalArea
import br.com.barcadero.geniususer.model.responses.ProfessionalResponse
import br.com.transferr.extensions.toJson
import br.com.transferr.util.MyLocationLister
import br.com.transferr.util.PermissionUtil
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.toast

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener{
    override fun onLocationChanged(p0: Location?) {
        Log.d("DEBUG","onLocationChanged")
    }

    override fun onConnected(p0: Bundle?) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        startLocationUpdates()

        var fusedLocationProviderClient :
                FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient .getLastLocation()
                .addOnSuccessListener(this, { location ->
                    if (location != null) {
                        mLocation = location
                    }
                })
    }

    override fun onConnectionSuspended(p0: Int) {
        Log.d("MAP_ACTIVITY", "Connection Suspended")
        mGoogleApiClient.connect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.d("DEBUG","Failed on connection")
    }

    private lateinit var mMap: GoogleMap
    private val ZOOM = 15f
    lateinit var locationManager: LocationManager
    private lateinit var mGoogleApiClient: GoogleApiClient
    private var mLocationRequest: LocationRequest? = null
    lateinit var mLocation: Location
    private val UPDATE_INTERVAL = (2 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
        startApi()
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        checkLocation()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        var mylocationListener = MyLocationLister()
        var myLocationLatLng = mylocationListener.getLocation(this)
        if(myLocationLatLng != null) {
            val update = CameraUpdateFactory.newLatLngZoom(myLocationLatLng, 16f)
            googleMap.moveCamera(update)
        }
        this.mMap = googleMap
        if(isMapAllowed()) {
            mMap.isMyLocationEnabled = true
        }else{
            toast("Acesso ao GPS negado. O aplicativo pode não funcionar corretamente.")
        }
        mMap.animateCamera(CameraUpdateFactory.zoomTo(ZOOM))
        mMap.setMaxZoomPreference(15f)
        mMap.setInfoWindowAdapter(MapInfoWindowsAdapter(this))
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        callWebService()
    }

    private fun isMapAllowed():Boolean{
        return PermissionUtil.validate(this,1,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    fun startApi(){
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect()
        }
    }

    private fun checkLocation(): Boolean {
        if(!isLocationEnabled())
            showAlert()
        return isLocationEnabled()
    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Habilitar Localização")
                .setMessage("Precisamos ativar o GPS.\nPor favor ative-o.")
                .setPositiveButton("Ativar GPS", DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
                    val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(myIntent)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { paramDialogInterface, paramInt -> })
        dialog.show()
    }

    private fun isLocationEnabled(): Boolean {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun startLocationUpdates() {

        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL)
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this)

    }

    private fun callWebService(){
        var visibleRegion = mMap.projection.visibleRegion
        var quadrant      = Quadrant()
        if(visibleRegion != null){
            quadrant.farLeftLat   = visibleRegion.farLeft.latitude
            quadrant.farLeftLng   = visibleRegion.farLeft.longitude
            quadrant.farRightLat  = visibleRegion.farRight.latitude
            quadrant.farRightLng  = visibleRegion.farRight.longitude

            quadrant.nearLeftLat  = visibleRegion.nearLeft.latitude
            quadrant.nearLeftLng  = visibleRegion.nearLeft.longitude
            quadrant.nearRightLat = visibleRegion.nearRight.latitude
            quadrant.nearRightLng = visibleRegion.nearRight.longitude

            for(professional in loadProfessionals()){
                mMap.addMarker(addMarkCarOnTheMap(professional))
            }
            /*
            doAsync {
                var carOnlineList   = CarService().getCarOnline(quadrant)
                uiThread {
                    if(carOnlineList != null) {
                        var markers = HelperCar.transformInMarkers(carOnlineList)
                        if(numCarFound == 0){
                            numCarFound = markers.size
                        }else{
                            var size = markers.size
                            if(size != numCarFound){
                                numCarFound = size
                                //Snackbar.make(contex, "Replace with your own action", Snackbar.LENGTH_LONG)
                                //.setAction("Action", null).show()
                            }
                        }

                        for (mark in markers) {
                            mMap.addMarker(mark)
                        }
                    }
                }
            }
            */


        }


    }

    private fun loadProfessionals() : List<ProfessionalResponse>{
        var professional = ProfessionalResponse()
        professional.urlPhoto= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtTG0j1MmEng29JZuTbH7KqM55WOrUD7XfxtzOseyZeuFWJPv7"
        professional.distance="5,6 km"
        professional.id=1L
        professional.name="Gilberto Lira"
        professional.value="R$ 80,00"
        professional.longitude = -38.49405006
        professional.latitude = -3.73277312
        professional.professionalArea = EnumProfessionalArea.BRICKWORK

        var professional2 = ProfessionalResponse()
        professional2.urlPhoto= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTD7u5Dj3Tz7p07cLDeN7uMR2sKdjfwEoUSEnexvV1p3RA95QPZAw"
        professional2.distance="6,5 Km"
        professional2.id=2L
        professional2.name="Luis Brito"
        professional2.value="R$ 150,00"
        professional2.longitude = -38.49799827
        professional2.latitude = -3.73238771
        professional2.professionalArea = EnumProfessionalArea.AIR_CONDITIONING

        var professional3 = ProfessionalResponse()
        professional3.urlPhoto= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSdL1s1tMcbRkQm2VhwJUVrKmFTAqm4FprzAtAMONpZVTkFjFPlA"
        professional3.distance="10,5 Km"
        professional3.id=3L
        professional3.name="Carlos Aires Brito"
        professional3.value="R$ 350,00"
        professional3.longitude = -38.50164607
        professional3.latitude = -3.73688425
        professional3.professionalArea = EnumProfessionalArea.DIARIST

        var professional4 = ProfessionalResponse()
        professional4.urlPhoto= "https://media.glamour.com/photos/58f90f25510a907b04e2cfd9/master/w_1280,c_limit/meredithgolden.jpg"
        professional4.distance="4,6 Km"
        professional4.id=4L
        professional4.name="Elena Santiago"
        professional4.value="R$ 200,00"
        professional4.longitude = -38.49233344
        professional4.latitude = -3.74009605
        professional4.professionalArea = EnumProfessionalArea.MECHANICS

        var professional5 = ProfessionalResponse()
        professional5.urlPhoto= "http://redmondphotography.com/wp-content/uploads/2018/02/Parsons-710-800x600.jpg"
        professional5.distance="11,8 Km"
        professional5.id=5L
        professional5.name="Renata Almeida"
        professional5.value="R$ 200,00"
        professional5.longitude = -38.50349143
        professional5.latitude = -3.73744096
        professional5.professionalArea = EnumProfessionalArea.PAINTING

        return listOf(professional,professional2,professional3,professional4,professional5)

    }

    fun addMarkCarOnTheMap(professional:ProfessionalResponse): MarkerOptions? {
        var marck = MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(getIconByProfessionalArea(professional.professionalArea!!)))
                .title(professional.name)
                .snippet(professional.toJson())
                .position(LatLng(professional.latitude!!, professional.longitude!!))
        return marck
    }

    fun getIconByProfessionalArea(area:EnumProfessionalArea):Int{
        when(area){
            EnumProfessionalArea.PAINTING -> return R.drawable.paintbrush
            EnumProfessionalArea.MECHANICS -> return R.drawable.mechanic
            EnumProfessionalArea.ELETRICAL -> return R.drawable.electricity
            EnumProfessionalArea.DIARIST -> return R.drawable.broom
            EnumProfessionalArea.AIR_CONDITIONING -> return R.drawable.air_conditioner_blue
            EnumProfessionalArea.WOODWORK -> return R.drawable.wood
            EnumProfessionalArea.BRICKWORK -> return R.drawable.worker
            else -> return R.drawable.map_marker_radius_blue
        }
    }

}
