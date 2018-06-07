package br.com.barcadero.geniususer.activities

import android.os.Bundle
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.fragments.CommentsToProfessionalFragment

class CommentsToProfessionalActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments_to_professional)
        includeFragment(R.id.frameCommentsProfessional,CommentsToProfessionalFragment())
    }
}
