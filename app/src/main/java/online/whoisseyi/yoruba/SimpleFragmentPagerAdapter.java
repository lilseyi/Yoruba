package online.whoisseyi.yoruba;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{


    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    private String tabTitles[] = new String[] { "Numbers", "Family", "Colors","Phrases" };

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new NumbersFragment();

            case 1:
                return new FamilyFragment();

            case 2:
                return new ColorsFragment();

            case 3:
                return new PhrasesFragment();

                default:
                    return new NumbersFragment();

        }
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
