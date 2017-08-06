package io.github.tonnyl.mango.ui.settings

import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.view.View
import io.github.tonnyl.mango.R
import io.github.tonnyl.mango.ui.settings.license.LicensesActivity
import org.jetbrains.anko.browse
import org.jetbrains.anko.email
import org.jetbrains.anko.startActivity

/**
 * Created by lizhaotailang on 2017/7/21.
 */

class SettingsFragment : PreferenceFragmentCompat(), SettingsContract.View {

    private lateinit var mPresenter: SettingsContract.Presenter

    companion object {
        @JvmStatic
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings_screen)

        setHasOptionsMenu(true)

        mPresenter.computeCacheSize(activity)

        val clearCachePreference = findPreference("clear_cache")
        clearCachePreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            mPresenter.clearCache(activity)
            true
        }

        findPreference("open_source_licenses").onPreferenceClickListener = Preference.OnPreferenceClickListener {
            context.startActivity<LicensesActivity>()
            true
        }

        findPreference("contributors").onPreferenceClickListener = Preference.OnPreferenceClickListener {
            context.browse(getString(R.string.contributors_desc), true)
            true
        }

        findPreference("source_code").onPreferenceClickListener = Preference.OnPreferenceClickListener {
            context.browse(getString(R.string.source_code_desc), true)
            true
        }

        findPreference("feedback").onPreferenceClickListener = Preference.OnPreferenceClickListener {
            context.email(getString(R.string.feedback_email), getString(R.string.feedback_email_subject))
            true
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unsubscribe()
    }

    override fun setPresenter(presenter: SettingsContract.Presenter) {
        mPresenter = presenter
    }

    override fun updateCacheSize(size: Long) {
        findPreference("clear_cache").summary = getString(R.string.clear_cache_desc).format(size)
    }

}