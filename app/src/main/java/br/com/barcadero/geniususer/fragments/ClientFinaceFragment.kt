package br.com.barcadero.geniususer.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.barcadero.geniususer.R


/**
 * A simple [Fragment] subclass.
 */
class ClientFinaceFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_finace, container, false)
    }

}// Required empty public constructor
