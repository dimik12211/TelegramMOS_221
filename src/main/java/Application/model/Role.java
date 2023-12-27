package Application.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UsersChat> usersChats;

    public Role(){

    }

    public Role(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UsersChat> getUsersChats() {
        return usersChats;
    }

    public void setUsersChats(Set<UsersChat> usersChatSet) {
        this.usersChats = usersChatSet;
    }

    public void addUsersChat(UsersChat usersChat){
        this.usersChats.add(usersChat);
    }

    @Override
    public String getAuthority(){
        return getName();
    }
}
