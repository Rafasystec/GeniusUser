package br.com.barcadero.geniususer.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.activities.FindProfessionalActivity
import kotlinx.android.synthetic.main.fragment_choose_service_area.*


/**
 * A simple [Fragment] subclass.
 */
class ChooseServiceAreaFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_service_area, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cabinetmaker.setOnClickListener      { startFindBestProfessional()}
        CVAirConditioning.setOnClickListener { startFindBestProfessional()}
    }

    private fun startFindBestProfessional(){
        val intent = Intent(activity, FindProfessionalActivity::class.java)
        startActivity(intent)
    }

}// Required empty public constructor
