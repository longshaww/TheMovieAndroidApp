package vn.edu.huflit.themovieapp1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.huflit.themovieapp1.R;

public class SearchFragment extends Fragment {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fraqment_search,container,false);
        return mView;
    }

////    @Override
////    public void onCreate(@Nullable Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setHasOptionsMenu(true);
////    }
////
////    @Override
////    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
////        super.onCreateOptionsMenu(menu, inflater);
////        inflater.inflate(R.menu.menu_search, menu);
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
////
////        if (item.getItemId() == R.id.menuSearch) {
////
////        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
