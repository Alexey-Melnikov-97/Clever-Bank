public class User extends Person {
    private String username;
    private String password;
    public User(){}
    public User(String firstName, String secondName, String lastName, String address, String phoneNumber,
                String username, String password) {
        super(firstName, secondName, lastName, address, phoneNumber);
        this.username = username;
        this.password = password;
    }

    //getters - setters
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //overrides
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;
    }
}
