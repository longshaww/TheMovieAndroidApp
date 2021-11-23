package vn.edu.huflit.themovieapp1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.huflit.themovieapp1.fragment.AccountFragment;
import vn.edu.huflit.themovieapp1.fragment.HomeFragment;
import vn.edu.huflit.themovieapp1.fragment.MoreFragment;
import vn.edu.huflit.themovieapp1.fragment.SearchFragment;
import vn.edu.huflit.themovieapp1.fragment.TVShowFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new TVShowFragment();
            case 2:
                return new SearchFragment();
            case 3:
                return new AccountFragment();
            case 4:
                return new MoreFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
