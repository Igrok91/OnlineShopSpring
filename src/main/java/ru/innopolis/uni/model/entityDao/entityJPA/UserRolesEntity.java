package ru.innopolis.uni.model.entityDao.entityJPA;

import javax.persistence.*;

/**
 * Created by innopolis on 21.01.2017.
 */
@Entity
@Table(name = "user_roles", schema = "online_shop", catalog = "")
public class UserRolesEntity {
    private int userRoleId;
    private String role;
    private UsersEntity usersEntity;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    @Id
    @Column(name = "user_role_id", nullable = false)
    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRolesEntity entity = (UserRolesEntity) o;

        if (userRoleId != entity.userRoleId) return false;
        if (role != null ? !role.equals(entity.role) : entity.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userRoleId;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
