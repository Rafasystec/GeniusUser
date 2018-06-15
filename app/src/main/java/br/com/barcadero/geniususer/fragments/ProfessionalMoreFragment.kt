package br.com.barcadero.geniususer.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.activities.ProfessionalActivity
import kotlinx.android.synthetic.main.view_professional_more_menu.*


/**
 * A simple [Fragment] subclass.
 */
class ProfessionalMoreFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_professional_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardViewMenuEdtProfile.setOnClickListener { startActivity(Intent(activity,ProfessionalActivity::class.java)) }
    }

}// Required empty public constructor
