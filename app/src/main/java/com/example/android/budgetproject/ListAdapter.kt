package com.example.android.budgetproject

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.os.Bundle
import android.support.annotation.NonNull

//class ListAdapter {
//}

//@Dao
//interface UserDao {
//    @Query("SELECT * FROM user ORDER BY lastName ASC")
//    public abstract LiveData<List<User>> usersByLastName();
//}
//
//class MyViewModel extends ViewModel {
//    public final LiveData<List<User>> usersList;
//    public MyViewModel(UserDao userDao) {
//        usersList = userDao.usersByLastName();
//    }
//}
//
//class MyActivity extends AppCompatActivity {
//    @Override
//    public void onCreate(Bundle savedState) {
//        super.onCreate(savedState);
//        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        RecyclerView recyclerView = findViewById(R.id.user_list);
//        UserAdapter<User> adapter = new UserAdapter();
//        viewModel.usersList.observe(this, list -> adapter.submitList(list));
//        recyclerView.setAdapter(adapter);
//    }
//}
//
//class UserAdapter extends ListAdapter<User, UserViewHolder> {
//    public UserAdapter() {
//        super(User.DIFF_CALLBACK);
//    }
//    @Override
//    public void onBindViewHolder(UserViewHolder holder, int position) {
//        holder.bindTo(getItem(position));
//    }
//    public static final DiffUtil.ItemCallback<User> DIFF_CALLBACK =
//    new DiffUtil.ItemCallback<User>() {
//        @Override
//        public boolean areItemsTheSame(
//                @NonNull User oldUser, @NonNull User newUser) {
//            // User properties may have changed if reloaded from the DB, but ID is fixed
//            return oldUser.getId() == newUser.getId();
//        }
//        @Override
//        public boolean areContentsTheSame(
//                @NonNull User oldUser, @NonNull User newUser) {
//            // NOTE: if you use equals, your object must properly override Object#equals()
//            // Incorrectly returning false here will result in too many animations.
//            return oldUser.equals(newUser);
//        }
//    }
//}