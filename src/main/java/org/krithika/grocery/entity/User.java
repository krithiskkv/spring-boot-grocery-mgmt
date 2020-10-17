package org.krithika.grocery.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by administrator on 1/10/20.
 */
@Entity
public class User implements UserDetails {

    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="USER_PASSWORD")
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "USER_GROCERY_LIST_ITEMS", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name="USER_GROCERY_LIST_ITEM")
    private Set<GroceryListItem> groceryList = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<GroceryListItem> getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(Set<GroceryListItem> groceryList) {
        this.groceryList = groceryList;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {return null;}

    public boolean isAccountNonExpired() {return true;}

    public boolean isAccountNonLocked() {return true;}

    public boolean isCredentialsNonExpired() {return true;}

    public boolean isEnabled() {return true;}

    public User() {}

}
