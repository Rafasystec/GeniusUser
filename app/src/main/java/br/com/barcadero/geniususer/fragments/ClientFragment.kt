package br.com.barcadero.geniususer.fragments


import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.extensions.getGenderAdapter
import br.com.barcadero.geniususer.extensions.showDialog
import br.com.barcadero.geniususer.model.enums.EnumSexo
import br.com.transferr.util.MyLocationLister
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.basic_profile_layout.*
import kotlinx.android.synthetic.main.fragment_client.*


/**
 * A simple [Fragment] subclass.
 */
class ClientFragment : BaseFragment() {

    private var myAddress: String=""
    private var myLatitude: Double? = null
    private var myLongitude:Double? = null
    var spGender: Spinner?=null
    var gender:EnumSexo?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnMyLocation.setOnClickListener { showDialogConfirmLocal(MyLocationLister().getLocation(activity!!)!!) }
        initSpinnerSexo()
    }

    internal fun showDialogConfirmLocal(latLng: LatLng) {
        AlertDialog.Builder(activity!!)
                .setTitle("Escolher Local")
                .setMessage("Deseja alterar ou inserir o seu endereço?")
                .setPositiveButton("Sim") { dialogInterface, i -> callPlacesActivity() }
                .setNegativeButton("Não") { dialogInterface, i ->
                    myLatitude = latLng.latitude
                    myLongitude = latLng.longitude
                    btnMyLocation.text = myAddress
                    //requestServiceWindows()
                }
                .create().show()
    }

     fun callPlacesActivity() {
        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(activity)
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
        } catch (e: GooglePlayServicesRepairableException) {
            showDialog(activity!!,"Erro no Google Play Services","Localização")
        } catch (e: GooglePlayServicesNotAvailableException) {
            showDialog(activity!!,"Google Play Services não disponível.","Localização")
        }

    }

    private val TAG: String="PLACE"

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                val place = PlaceAutocomplete.getPlace(activity, data)
                myLongitude = place.latLng.longitude
                myLatitude = place.latLng.latitude
                btnMyLocation.setText(place.address)
                //requestServiceWindows()


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                val status = PlaceAutocomplete.getStatus(activity, data)
                // TODO: Handle the error.
                Log.i(TAG, status.statusMessage)

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
                Log.i(TAG, "The user canceled the operation.")
            }

        }
    }

    private fun initSpinnerSexo(){

        spSexoBasic.adapter = getGenderAdapter(activity!!)
        spSexoBasic.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                gender = if(position == 0){
                    EnumSexo.MALE
                }else{
                    EnumSexo.FEMALE
                }
            }
        }
        /*
        spGender =  br.com.barcadero.geniususer.extensions.initSpinnerSexo(activity!!)
        spGender!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                gender = if(position == 0){
                    EnumSexo.MALE
                }else{
                    EnumSexo.FEMALE
                }
            }
        }
        */
    }


}// Required empty public constructor
