package br.com.barcadero.geniususer.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.activities.FindProfessionalActivity
import br.com.barcadero.geniususer.activities.MainActivity
import br.com.barcadero.geniususer.model.enums.EnumProfessionalArea
import br.com.barcadero.geniususer.util.PutExtraKeys
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
        cabinetmaker.setOnClickListener      { startFindBestProfessional(EnumProfessionalArea.WOODWORK)}
        CVAirConditioning.setOnClickListener { startFindBestProfessional(EnumProfessionalArea.AIR_CONDITIONING)}
        cvCleaning.setOnClickListener { startFindBestProfessional(EnumProfessionalArea.DIARIST)}
        cvMasonry.setOnClickListener { startFindBestProfessional(EnumProfessionalArea.BRICKWORK)}
        CVElectrician.setOnClickListener { startFindBestProfessional(EnumProfessionalArea.ELETRICAL)}
        cvMechanics.setOnClickListener { startFindBestProfessional(EnumProfessionalArea.MECHANICS)}
        cvPainting.setOnClickListener { startFindBestProfessional(EnumProfessionalArea.PAINTING)}
    }

    private fun startFindBestProfessional(professionalArea:EnumProfessionalArea){

        val intent = Intent(this@ChooseServiceAreaFragment.context, FindProfessionalActivity::class.java)
        var bundle = Bundle()
        bundle.putSerializable(PutExtraKeys.PROFESSIONAL_AREA,professionalArea)
        intent.putExtras(bundle)
        startActivity(intent)
        //activity?.finish()
    }

}// Required empty public constructor
