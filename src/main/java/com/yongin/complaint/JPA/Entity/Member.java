package com.yongin.complaint.JPA.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yongin.complaint.Common.MemberRoleEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER")
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBER_SEQ_PK")
    private Long memberSeq;

    @Column(name = "ID")
    private String id;

    @Column(name = "PWD")
    private String pwd;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "BIRTH")
    private LocalDate birth;

    @Column(name = "MAJOR")
    private String major;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

//    @Column(name = "ROLE")
//    @Enumerated(EnumType.STRING)
//    private MemberRoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        Collection<GrantedAuthority> collect = new ArrayList<>();
//        collect.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return role.toString();
//            }
//        });
//        return null;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername(){
        return this.name;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled(){
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword(){
        return this.pwd;
    }
}