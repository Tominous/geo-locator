package de.kyleonaut.geopip.api;

import com.google.inject.Singleton;
import de.kyleonaut.geopip.api.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
@Singleton
public class GeoIpApi {
    private final List<User> users = new ArrayList<>();

    /**
     * Find a User by the given UUID
     *
     * @param uuid The UUID of the User
     * @return An Optional User
     */
    public Optional<User> getUser(UUID uuid) {
        return this.users.stream()
                .filter(user -> user.getUuid().equals(uuid))
                .findFirst();
    }

    /**
     * Find a User by the given Name
     *
     * @param name The UUID of the User
     * @return An Optional User
     */
    public Optional<User> getUser(String name) {
        return this.users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    /**
     * Adds an User to the internal User list
     *
     * @param user The User to add
     */
    public void addUser(User user) {
        this.users.add(user);
    }

    /**
     * Removes an User from the internal User list
     *
     * @param uuid The uuid of the User to remove
     */
    public void removeUser(UUID uuid) {
        final Optional<User> optionalUser = this.users.stream()
                .filter(user -> user.getUuid().equals(uuid)).findFirst();
        optionalUser.ifPresent(this.users::remove);
    }

    /**
     * Get a copy of all Users
     *
     * @return A list of all Users
     */
    public List<User> getUsers() {
        return new ArrayList<>(this.users);
    }
}
