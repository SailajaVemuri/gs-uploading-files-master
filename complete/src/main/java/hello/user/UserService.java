package hello.user;

public interface UserService {
	public UserObject createUser(UserObject user);
	public UserObject updateUser(UserObject user);
	public Boolean deleteUser(String userId);
	public UserObject fetchUser(String userId);
}
