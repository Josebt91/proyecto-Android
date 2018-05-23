package miprimeraapp.android.teaching.com.miprimeraapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
@Dao
public interface UserDao {

    @Query("SELECT*FROM User WHERE username IS :username")
    User findUserByUsername(String username);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
