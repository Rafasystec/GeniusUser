package br.com.barcadero.geniususer.util

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.support.v4.app.FragmentActivity
import android.view.ViewGroup
import android.widget.RelativeLayout
import br.com.barcadero.geniususer.R
import com.github.amlcurran.showcaseview.ShowcaseView
import com.github.amlcurran.showcaseview.targets.ViewTarget

/**
 * Created by Rafael Rocha on 06/06/2018.
 */
class ShowCaseViewUtil {

    companion object {
        fun singleShot(idTargetComponent: Int, activity: FragmentActivity, contentText: String, title: String,resources: Resources) {
            var parameters = getRealitveLayout(resources)
            var viewTarget = ViewTarget(idTargetComponent, activity)
            var sv = ShowcaseView.Builder(activity)
                    .withMaterialShowcase()
                    .setTarget(viewTarget)
                    .setContentText(contentText)
                    .setContentTitle(title)
                    .singleShot(42)
                    .replaceEndButton(R.layout.view_custom_button)
                    .hideOnTouchOutside()
                    .setStyle(R.style.CustomShowcaseTheme2)
                    .build()
            sv.setButtonPosition(parameters)
        }

        fun getRealitveLayout(resources: Resources) :  RelativeLayout.LayoutParams{
            val lps = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
            val margin = ((resources.displayMetrics.density * 12) as Number).toInt()
            lps.setMargins(margin, margin, margin, margin)
            return lps
        }

    }
}