package cn.itcast.shiro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pe_role")
@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 594829320797158219L;
    @Id
    private String id;
    private String name;
    private String description;

    //角色与用户   多对多
    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<User>(0);

    //角色与权限  多对多
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="pe_role_permission",
            joinColumns={@JoinColumn(name="role_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="permission_id",referencedColumnName="id")})
    private Set<Permission> permissions = new HashSet<Permission>(0);
}