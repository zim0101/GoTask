package modules.user;


import com.go_task.dao.UserDao;
import com.go_task.dao.UserDetailsDao;
import com.go_task.entity.User;
import com.go_task.entity.UserDetails;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;


public class UserModuleTest {

    private final UserDao userDao;
    private final UserDetailsDao userDetailsDao;

    public UserModuleTest() {
        this.userDao = new UserDao(User.class);
        this.userDetailsDao = new UserDetailsDao(UserDetails.class);
    }

    private User buildUser(String email) {
        return new User("New User 1", email, "pass");
    }

    private UserDetails buildUserDetails() {
        return new UserDetails(
                "address1",
                "80980980",
                "Bangladesh",
                "Dhaka",
                "90001"
        );
    }

    @Test
    public void testUserModule() {
        String email = "new.user1@email.com";

        Map<String, Integer> ids = createUserWithDetails(email);
        int userId = ids.get("userId");
        int userDetailsId = ids.get("userDetailsId");

        updateUser(userId);
        findUserByEmail(email);
        updateUserDetails(userDetailsId);
        getDetailsByUser(userId);
        getUserFromDetails(userDetailsId);
        deleteUserAndDetails(userId, userDetailsId);
    }

    private Map<String, Integer> createUserWithDetails(String email) {
        User newUser = buildUser(email);
        int userId = userDao.create(newUser);
        assertTrue(userId > -1);

        UserDetails userDetails = buildUserDetails();
        int userDetailsId = userDetailsDao.createDetailsOfUser(
                userId,
                userDetails
        );
        assertTrue(userDetailsId > -1);

        Map<String, Integer> ids = new HashMap<>();
        ids.put("userId", userId);
        ids.put("userDetailsId", userDetailsId);

        return ids;
    }

    private void updateUser(int userId) {
        String newPassword = "new_pass123";
        User user = (User) userDao.find(userId);
        user.setPassword(newPassword);
        userDao.update(user);
        User updatedUser = (User) userDao.find(userId);

        assertEquals(updatedUser.getPassword(), newPassword);
    }

    private void findUserByEmail(String email) {
        User userByEmail = userDao.getUserByEmail(email);
        assertNotNull(userByEmail);
    }

    private void updateUserDetails(int userDetailsId) {
        String newAddress = "new address";
        UserDetails userDetails1 = (UserDetails) userDetailsDao.find(userDetailsId);
        userDetails1.setAddress(newAddress);
        userDetailsDao.update(userDetails1);
        UserDetails userDetails2 = (UserDetails) userDetailsDao.find(userDetailsId);

        assertEquals(userDetails2.getAddress(), newAddress);
    }

    private void getDetailsByUser(int userId) {
        User user1 = (User) userDao.find(userId);
        UserDetails userDetails3 = user1.getUserDetails();

        assertNotNull(userDetails3);
    }

    private void getUserFromDetails(int userDetailsId) {
        UserDetails userDetails4 = (UserDetails) userDetailsDao.find(userDetailsId);
        User user2 = userDetails4.getUser();

        assertNotNull(user2);
    }

    private void deleteUserAndDetails(int userId, int userDetailsId) {
        User userToDelete = (User) userDao.find(userId);
        userDao.delete(userToDelete);
        assertNull(userDao.find(userId));
        assertNull(userDetailsDao.find(userDetailsId));
    }
}
